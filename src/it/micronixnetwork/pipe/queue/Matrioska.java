package it.micronixnetwork.pipe.queue;

import it.micronixnetwork.pipe.Adapter;

import java.io.Serializable;
import java.util.HashMap;

public class Matrioska implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private final HashMap<String,Object> data;
    
    private final Matrioska inner;
    
    public Matrioska(Matrioska inner,HashMap<String, Object> data) {
	this.inner=inner;
	this.data=data;
    }
    
    public Matrioska getInner() {
	return inner;
    }
    
    public Object get(String key){
	if(data==null) return null;
	return this.data.get(key);
    }
    
    public Object remove(Adapter.Ticket ticket,String key){
	if(ticket==null) return null; 
	if(data!=null) return data.remove(key);
	return null;
    }
    
    public void put(Adapter.Ticket ticket,String key,Object value){ 
	if(data!=null && ticket!=null) data.put(key,value);
    }
    
}
