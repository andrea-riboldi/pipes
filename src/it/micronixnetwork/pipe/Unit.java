/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micronixnetwork.pipe;

import it.micronixnetwork.pipe.queue.Matrioska;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;


/**
 * 
 * @author kobo
 */
public abstract class Unit implements Cloneable{

    private static final Set<Class> SCALAR = new HashSet<Class>(Arrays.asList(String.class, Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Float.class, Long.class,
	    Double.class, BigInteger.class, BigDecimal.class, AtomicBoolean.class, AtomicInteger.class, AtomicLong.class, Date.class, Calendar.class, GregorianCalendar.class, Class.class, UUID.class,
	    Number.class, Object.class, Timestamp.class));

    private final Log log = LogFactory.getLog(Unit.class);
    
    private Adapter adapter=null;

    public abstract List<HashMap<String,Object>> Exe(Matrioska mtrioska) throws UnitException;

    protected void inject(Object obj, ApplicationContext ctx) {
	ArrayList<Field> fileds = new ArrayList<Field>();
	getAllFields(obj.getClass(), fileds);
	for (Field field : fileds) {
	    
	    try {
		Object value = ctx.getBean(field.getName());
		if (value != null) {  
		    if(log.isDebugEnabled()){
			log.debug("Inject terget: "+field.getName());
		    }
		    setValue(field.getName(), obj, value);
		}
	    } catch (Exception ex) {
		if(ex instanceof NoSuchBeanDefinitionException){
		    //info("target "+field.getName()+" not found");
		}else{
		    log.error("Errore nell'iniezione del valore",ex); 
		}
		
	    }
	}
    }

    public void init(ApplicationContext ctx) {
	inject(this, ctx);
    }

    private void getAllFields(Class bean, ArrayList<Field> fields) {
	if (bean != null && !SCALAR.contains(bean)) {
	    Field[] partial = bean.getDeclaredFields();
	    fields.addAll(Arrays.asList(partial));
	    Class superC = bean.getSuperclass();
	    getAllFields(superC, fields);
	}
    }

    private Object getValue(String attribute, Object bean) {
	try {
	    char first = Character.toUpperCase(attribute.charAt(0));
	    String methodName = "get" + first + attribute.substring(1);
	    Class paramTypes[] = new Class[0];
	    Method method = bean.getClass().getMethod(methodName, paramTypes);
	    return method.invoke(bean, new Object[0]);
	} catch (Exception e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    private void setValue(String attribute, Object bean, Object value) {
	String methodName = "";
	try {
	    char first = Character.toUpperCase(attribute.charAt(0));
	    methodName = "set" + first + attribute.substring(1);
	    for (Method method : bean.getClass().getMethods()) {
	        if (!method.getName().equals(methodName)) {
	            continue;
	        }
	        Class<?>[] parameterTypes = method.getParameterTypes();
	        if(parameterTypes.length>1){
	            continue;
	        }
	        if (parameterTypes[0].isAssignableFrom(value.getClass())) {
	            	method.invoke(bean, new Object[] { value });
	                break;
	            }
	    }
	} catch (Exception e) {
	    log.error("metodo set: " + methodName + "("+value.getClass().getSimpleName()+") per la calsse " + bean.getClass().getSimpleName() + " non trovato",e);
	}
    }
    
    protected static List<String> stringToList(String val,String sep) {
	if (val != null) {
	    if (val.trim().length() == 0) {
		return new ArrayList<String>();
	    }
	    String[] list = val.split("[ ]*"+sep+"[ ]*");
	    return new ArrayList<String>(Arrays.asList(list));
	} else {
	    return new ArrayList<String>();
	}
    }
    
    public void setAdapter(Adapter adapter) {
	this.adapter = adapter;
    }
    
    public Adapter getAdapter() {
	return adapter;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

   
}
