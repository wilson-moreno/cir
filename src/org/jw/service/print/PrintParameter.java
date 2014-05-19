/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.print;

import java.beans.PropertyChangeSupport;



/**
 *
 * @author Wilson
 */
public class PrintParameter {
    public static final String PROP_NAME = "name";
    public static final String PROP_LABEL = "label";
    public static final String PROP_VALUE = "value";
    public static final String PROP_DATATYPE = "dataType";
    public static final String PROP_DEFAULTVALUE = "defaultValue";
    private String name;
    private String label;
    private Object value;
    private String dataType;
    private String defaultValue;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
        
    public PrintParameter(){
        this.name = "";
        this.label = "";
        this.value = "";
    }

    /**
    * @return the name
    */
    public String getName() {
        return name;
    }

        /**
        * @param name the name to set
        */
        public void setName(String name) {
            java.lang.String oldName = this.name;
            this.name = name;
            propertyChangeSupport.firePropertyChange(PROP_NAME, oldName, name);
        }

        /**
        * @return the label
        */
        public String getLabel() {
            return label;
        }

        /**
        * @param label the label to set
        */
        public void setLabel(String label) {
            java.lang.String oldLabel = this.label;
            this.label = label;
            propertyChangeSupport.firePropertyChange(PROP_LABEL, oldLabel, label);
        }

        /**
        * @return the value
        */
        public Object getValue() {
            return value;
        }

        /**
        * @param value the value to set
        */
        public void setValue(Object value) {
            Object oldValue = this.value;
            this.value = value;
            propertyChangeSupport.firePropertyChange(PROP_VALUE, oldValue, value);
        }

    /**
     * @return the dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @param dataType the dataType to set
     */
    public void setDataType(String dataType) {
        java.lang.String oldDataType = this.dataType;
        this.dataType = dataType;
        propertyChangeSupport.firePropertyChange(PROP_DATATYPE, oldDataType, dataType);
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        java.lang.String oldDefaultValue = this.defaultValue;
        this.defaultValue = defaultValue;
        propertyChangeSupport.firePropertyChange(PROP_DEFAULTVALUE, oldDefaultValue, defaultValue);
    }
        
        
}
