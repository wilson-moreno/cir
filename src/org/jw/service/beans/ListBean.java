/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beans;

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
public class ListBean {
    public static final String PROP_LIST = "list";
    public static final String PROP_DEFAULTCOMBOBOXMODEL = "defaultComboBoxModel";
    private List list;    
    private DefaultComboBoxModel<Object> defaultComboBoxModel;
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
    
    public ListBean(DataAccessObject dao){
        this.list = new ArrayList(dao.readAll());
    }
    
    /**
     * @return the list
     */
    public List<Object> getList() {
        return list;
    }
    
    public List getSortedList(){
        Collections.sort(list);
        return list;
    }
    
    public void setList(List<Object> list){
        this.list = list;
    }
    
    
    public javax.swing.DefaultComboBoxModel getDefaultComboBoxModel(){
        return new javax.swing.DefaultComboBoxModel(list.toArray());
    }
    
    public void setDefaultComboBoxModel(javax.swing.DefaultComboBoxModel defaultComboBoxModel){
        this.defaultComboBoxModel = defaultComboBoxModel;
    }
}
