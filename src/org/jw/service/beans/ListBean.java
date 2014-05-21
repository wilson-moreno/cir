/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beans;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jw.service.util.UtilityProperties;

/**
 *
 * @author Wilson
 */
public class ListBean {
    public static final String PROP_LIST = "list";
    private final List<Object> list;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    public ListBean(){
        this.list = new ArrayList<>();
    }
    
    public ListBean(String name){
        UtilityProperties utilProperties = UtilityProperties.create(name);
        this.list = new ArrayList(utilProperties.getValues());
    }

    public ListBean(Object[] objects){
        this.list = Arrays.asList(objects);
    }
    
    /**
     * @return the list
     */
    public List<Object> getList() {
        return list;
    }
}
