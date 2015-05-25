package it.micronixnetwork.pipe.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class UnitQueue {

    LinkedList<Matrioska> matrioski = new LinkedList<Matrioska>();

    public void add(Matrioska m) {
	matrioski.add(m);
    }

    public Matrioska get() {
	try {
	    return matrioski.removeFirst();
	} catch (NoSuchElementException nse) {
	    return null;
	}
    }

    public boolean isEmpty() {
	return matrioski.isEmpty();
    }

    public int size() {
	return matrioski.size();
    }

}
