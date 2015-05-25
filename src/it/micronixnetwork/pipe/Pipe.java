package it.micronixnetwork.pipe;

import java.util.ArrayList;
import java.util.List;

public class Pipe {
    
    private Integer clock;
    
    private Integer slowFactor;
    
    private Integer loaderInterval;
    
    private PipeLayer loader;
    
    private List<PipeLayer> layers=new ArrayList<PipeLayer>();
    
    public void setLayers(List<PipeLayer> layers) {
	this.layers = layers;
    }
    
    public List<PipeLayer> getLayers() {
	return layers;
    }
    
    public void setClock(Integer clock) {
	this.clock = clock;
    }
    
    public Integer getClock() {
	return clock;
    }
    
    public void setSlowFactor(Integer slowFactor) {
	this.slowFactor = slowFactor;
    }
    
    public Integer getSlowFactor() {
	return slowFactor;
    }
    
    public int size(){
	return layers.size();
    }
    
    public void setLoaderInterval(Integer loaderInterval) {
	this.loaderInterval = loaderInterval;
    }
    
    public Integer getLoaderInterval() {
	return loaderInterval;
    }

    public PipeLayer getLoader() {
        return loader;
    }

    public void setLoader(PipeLayer loader) {
        this.loader = loader;
    }
    
}
