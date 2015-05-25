package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.Matrioska;

public interface BeforeExecute  {
    void beforeExecute(Matrioska mtrioska) throws UnitException;
}
