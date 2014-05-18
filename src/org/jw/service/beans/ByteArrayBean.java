/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Wilson
 */
public class ByteArrayBean {
    private byte[] byteArray;
    
    public ByteArrayBean(){}

    /**
     * @return the byteArray
     */
    public byte[] getByteArray() {
        return byteArray;
    }

    /**
     * @param byteArray the byteArray to set
     */
    public void setByteArray(byte[] byteArray) {
        byte[] oldByteArray = this.byteArray;
        this.byteArray = byteArray;
        propertyChangeSupport.firePropertyChange(PROP_BYTEARRAY, oldByteArray, byteArray);
    }
    
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_BYTEARRAY = "byteArray";
    
    public void addPropertyChangeListener(PropertyChangeListener listener){ propertyChangeSupport.addPropertyChangeListener(listener); }
    public void addPropertyChangeListener(String name, PropertyChangeListener listener){ propertyChangeSupport.addPropertyChangeListener(name, listener); }
    
    public void removePropertyChangeListener(PropertyChangeListener listener){ propertyChangeSupport.removePropertyChangeListener(listener); }
    public void removePropertyChangeListener(String name, PropertyChangeListener listener){ propertyChangeSupport.removePropertyChangeListener(name, listener); }
}
