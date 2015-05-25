package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.UnitQueue;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xml.sax.SAXParseException;

public class PipeRunner extends Observable{
   
    private final Log log = LogFactory.getLog(PipeRunner.class);

    private static String context_files = "pipe.xml";

    boolean running = false;
    
    boolean inError = false;

    private PipeLayer loader;

    private List<PipeLayer> layers;
    
    private List<UnitQueue> queues;

    private Pipe pipe = null;

    private ApplicationContext sctx;

    public PipeRunner(String context) {
        if (context == null) {
            context = context_files;
        }
        try {
            sctx = new FileSystemXmlApplicationContext(new String[]{context});
            pipe = (Pipe) sctx.getBean("pipe");
            log.info("Pipe created");
        } catch (BeansException ex) {
            inError=true;
            if(ex.getMessage().indexOf(context)!=-1){
                System.err.println(context+" file not found");
            }else
            if(ex.getMostSpecificCause() instanceof SAXParseException){
                System.err.println(context+" file not valid");
                System.err.println(ex.getMessage());
            }else
            System.err.println(ex.getCause().getMessage());
        } 
    }

    public PipeRunner() {
        this(null);
    }

    public void start() throws UnitException {

        if (pipe != null) {

            layers = pipe.getLayers();
            
            loader= pipe.getLoader();

            queues = new ArrayList<UnitQueue>();

            if (loader!=null && layers != null && layers.size() > 0) {
                UnitQueue queue = null;

                //Inizializzazione layer e unit
                // Creazione code
                for (int i = 0; i < layers.size(); i++) {
                    queue = new UnitQueue();
                    queues.add(queue);
                }

                //Assegnamento code e organizzazione layer

                loader.out=queues.get(0);
                if (loader.getName() == null) {
                        loader.setName(loader.getUnit().getClass().getSimpleName());
                    }

                int i = 0;
                for (PipeLayer layer : layers) {
                    layer.in = queues.get(i);
                    if (i < (layers.size() - 1)) {
                        layer.out = queues.get(i+1);
                    }
                    if (layer.getName() == null) {
                        layer.setName(layer.getUnit().getClass().getSimpleName());
                    }
                    layer.getUnit().init(sctx);
                    i++;
                }

                Integer clock = pipe.getClock();
                Integer slowFactor = pipe.getSlowFactor();

                if (clock == null) {
                    clock = 1;
                }

                if (slowFactor == null) {
                    slowFactor = 1;
                }

                final Integer time = (1000 / clock) * slowFactor;

                final Integer loaderInterval = pipe.getLoaderInterval();

                running = true;

                Thread loaderThread = new Thread() {
                    @Override
                    public void run() {

                        while (running) {
                            try {
                                if (loader.getState() == null) {
                                    loader.start();
                                } else {
                                    boolean faund = false;
                                    int i = 0;
                                    List<State> stati = loader.getState();
                                    while (!faund && i < stati.size()) {
                                        if (stati.get(i) == State.WAITING) {
                                            loader.active();
                                            faund = true;
                                        }
                                        i++;
                                    }
                                }
                                if (loaderInterval != null) {
                                    this.sleep(loaderInterval);
                                } else {
                                    this.sleep(time);
                                }
                            } catch (RuntimeException e) {
                                inError=true;
                                e.printStackTrace();
                            } catch (Exception e) {
                                inError=true;
                                e.printStackTrace();
                            }
                        }
                    }
                };

                Thread executor = new Thread() {
                    @Override
                    public void run() {
                        while (running) {
                            try {
                                for (PipeLayer layer : layers) {
                                    if (layer.getState() == null) {
                                        layer.start();
                                    } else {
                                        boolean faund = false;
                                        int i = 0;
                                        List<State> stati = layer.getState();
                                        while (!faund && i < stati.size()) {
                                            if (stati.get(i) == State.WAITING) {
                                                layer.active();
                                                faund = true;
                                            }
                                            i++;
                                        }
                                    }
                                }
                                this.sleep(time);
                            } catch (RuntimeException e) {
                                inError=true;
                                e.printStackTrace();
                            } catch (Exception e) {
                                inError=true;
                                e.printStackTrace();
                            }
                        }
                    }
                };

                loaderThread.start();
                executor.start();
            }
        }
        setChanged();
        notifyObservers();
    }

    public void stop() {
        running = false;
        if (loader != null) {
            loader.stop();
            setChanged();
        }
        if (layers != null) {
            for (PipeLayer layer : layers) {
                layer.stop();
            }
            setChanged();
        }
        notifyObservers();
    }
    
    
    public boolean isRunning(){
        if(running) return true;
        if(loader!=null){
            if(loader.isRunning()) return true;
        }
        if (layers != null) {
            for (PipeLayer layer : layers) {
                if(layer.isRunning()) return true;
            }
        }
        return false;
    }
    
    public boolean isInError(){
        return inError;
    }
    

    public static void main(final String[] args) {
        PipeRunner runner = null;
        try {
            if (args.length > 0) {
                runner = new PipeRunner(args[0]);
            } else {
                runner = new PipeRunner();
            }
            if (runner != null) {
                runner.start();
            }
        } catch (PipeException ex) {
            ex.printStackTrace();
        }
    }
}
