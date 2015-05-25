package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.Matrioska;

import java.util.HashMap;
import java.util.List;

public interface AfterExecute  {
    void afterExecute( Matrioska in,List<HashMap<String,Object>> out) throws UnitException;
}
