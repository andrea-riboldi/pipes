package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.Matrioska;
import it.micronixnetwork.pipe.queue.UnitQueue;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PipeLayer implements Runnable {

    private final Log log = LogFactory.getLog(PipeLayer.class);

    private boolean noinput = true;

    public List<Thread> thd;

    private String name;

    private Unit unit;

    private int workers = 1;

    boolean running = true;

    UnitQueue in;
    UnitQueue out;

    public void run() {
        try {

            Unit workUnit = null;

            if (unit == null) {
                throw new UnitException("Unit for layer " + name + " not exist");
            }

            if (workers == 1) {
                workUnit = unit;
            } else {
                workUnit = (Unit) unit.clone();
            }

            boolean ok = true;
            if (workUnit instanceof PrepareContext) {
                ok = ((PrepareContext) workUnit).prepareContext();
            }

            if (!ok) {
                throw new UnitException("Unit " + name + " not started, initialization problem (PrepareContext)");
            }

            while (running) {
                Matrioska input = null;
                if (in != null) {
                    input = (Matrioska) in.get();
                    synchronized (this) {
                        while (running && input == null) {
                            noinput = true;
                            wait();
                            input = (Matrioska) in.get();
                        }
                        noinput = false;
                    }
                }

                if (running) {

                    long start = System.currentTimeMillis();

                    Adapter adapter = unit.getAdapter();

                    if (adapter != null) {
                        input = adapter.convert(input);
                    }

                    if (workUnit instanceof BeforeExecute) {
                        ((BeforeExecute) workUnit).beforeExecute(input);
                    }

                    List<HashMap<String, Object>> output = workUnit.Exe(input);

                    if (workUnit instanceof AfterExecute) {
                        ((AfterExecute) unit).afterExecute(input, output);
                    }

                    if (log.isDebugEnabled()) {
                        log.debug("End Execute Thread's unit: " + Thread.currentThread().getName() + " in: " + (System.currentTimeMillis() - start) + "ms");
                        if (output != null) {
                            int i = 1;
                            for (HashMap<String, Object> line : output) {
                                for (String key : line.keySet()) {
                                    log.debug(Thread.currentThread().getName() + " OUT: line " + i + " " + key + " = " + line.get(key));
                                }
                                i++;
                            }
                        }
                    }
                    if (output != null) {
                        for (HashMap<String, Object> out_map : output) {
                            Matrioska toNext = new Matrioska(input, out_map);
                            if (out != null) {
                                out.add(toNext);
                            }
                        }
                    }

                    synchronized (this) {
                        wait();
                    }

                }
            }

            log.info(Thread.currentThread().getName() + " stopped");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    public void start() {
        log.info("Switch on : " + name);
        thd = new ArrayList<Thread>();
        for (int idx = 0; idx < workers; idx++) {
            log.info("Create worker : " + name + "[" + idx + "]");
            Thread t = new Thread(this, name + "[" + idx + "]");
            thd.add(t);
            t.start();
        }
    }

    synchronized void stop() {
        log.info(name + " stop called");
        running = false;
        notifyAll();
    }
    
    public boolean isRunning(){
        return running;
    }

    synchronized void active() {
        notifyAll();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setWorkers(int workers) {
        this.workers = workers;
    }

    public int getWorkers() {
        return workers;
    }

    List<State> getState() {
        if (thd != null) {
            List<State> result = new ArrayList<Thread.State>();
            for (Thread t : thd) {
                result.add(t.getState());
            }
            return result;
        }
        return null;
    }

    public boolean noInput() {
        return noinput;
    }

}
