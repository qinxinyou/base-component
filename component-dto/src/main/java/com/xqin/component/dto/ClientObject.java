package com.xqin.component.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the object communicate with Client.
 * The clients could be view layer or other HSF Consumers
 */
public abstract class ClientObject implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * This is for extended values
     */
    protected Map<String, Object> extValues = new HashMap<String, Object>();

    public Object getExtField(String key){
        if(extValues != null){
            return extValues.get(key);
        }
        return null;
    }

    public void putExtField(String fieldName, Object value){
        this.extValues.put(fieldName, value);
    }

    public Map<String, Object> getExtValues() {
        return extValues;
    }

    public void setExtValues(Map<String, Object> extValues) {
        this.extValues = extValues;
    }
}
