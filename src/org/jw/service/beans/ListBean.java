/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.util.UtilityProperties;

/**
 *
 * @author Wilson
 */
public class ListBean<T> {
    public static final String PROP_LIST = "list";
    public static final String PROP_DEFAULTCOMBOBOXMODEL = "defaultComboBoxModel";
    private List list;    
    private DefaultComboBoxModel<T> defaultComboBoxModel;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    
    public ListBean(){
        this.list = new ArrayList<>();
    }
    
    public ListBean(String name){
        UtilityProperties utilProperties = UtilityProperties.create(name);
        this.list = new ArrayList(utilProperties.getValues());
    }

    public ListBean(Object[] objects){
        this.list = (List<T>) Arrays.asList(objects);
    }
    
    public ListBean(DataAccessObject dao){
        this.list = new ArrayList(dao.readAll());
    }
    
    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }
    
    public List<T> getSortedList(){
        Collections.sort(list);
        return list;
    }
    
    public void setList(List<T> list){
        List<T> oldList = this.list;
        this.list = list;
        propertyChangeSupport.firePropertyChange(PROP_LIST, oldList, list);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
    
    
    public javax.swing.DefaultComboBoxModel getDefaultComboBoxModel(){
        Collections.sort(list);
        return new javax.swing.DefaultComboBoxModel(list.toArray());
    }
    
    public void setDefaultComboBoxModel(javax.swing.DefaultComboBoxModel defaultComboBoxModel){
        this.defaultComboBoxModel = defaultComboBoxModel;
    }
}
