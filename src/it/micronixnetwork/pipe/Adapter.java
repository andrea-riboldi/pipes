package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.Matrioska;

public abstract class Adapter {
    
    public static class Ticket { private Ticket() {} }
    protected static Ticket ticket = new Ticket();

    
    public abstract Matrioska convert(Matrioska input);

}
