/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.print;

import java.beans.PropertyChangeSupport;
import org.jw.service.entity.QueryText;



/**
 *
 * @author Wilson
 */
public class PrintParameter implements Comparable<PrintParameter>{
    public static final String PROP_SEQUENCE = "sequence";
    public static final String PROP_NAME = "name";
    public static final String PROP_LABEL = "label";
    public static final String PROP_VALUE = "value";
    public static final String PROP_DATATYPE = "dataType";
    public static final String PROP_DEFAULTVALUE = "defaultValue";    
    public static final String PROP_PARAMETERTYPE = "parameterType";
    public static final String PROP_CONTROLTYPE = "controlType";
    public static final String PROP_QUERYTEXT = "queryText";
    public static final String PROP_DISPLAYCOLUMN = "displayColumn";
    public static final String PROP_VALUECOLUMN = "valueColumn";
    public static final String PROP_REQUIRED = "PROP_REQUIRED";
    private Integer sequence;
    private String name;
    private String label;
    private Object value;
    private String dataType;
    private String defaultValue;
    private String parameterType;
    private String controlType;
    private String displayColumn;
    private String valueColumn;
    private QueryText queryText;
    private boolean required;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
        
    public PrintParameter(){
        this.name = "";
        this.label = "";
        this.value = "";
        this.parameterType = "";
        this.required = false;
    }
    
    public PrintParameter(String name, String label, Object value, String parameterType){
        this.name = name;
        this.label = label;
        this.value = value;
        this.parameterType = parameterType;
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

    @Override
    public int compareTo(PrintParameter t) {
        return this.sequence.compareTo(t.getSequence());
    }

    /**
     * @return the sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(Integer sequence) {
        java.lang.Integer oldSequence = this.sequence;
        this.sequence = sequence;
        propertyChangeSupport.firePropertyChange(PROP_SEQUENCE, oldSequence, sequence);
    }

    /**
     * @return the parameterType
     */
    public String getParameterType() {
        return parameterType;
    }

    /**
     * @param parameterType the parameterType to set
     */
    public void setParameterType(String parameterType) {
        java.lang.String oldParameterType = this.parameterType;
        this.parameterType = parameterType;
        propertyChangeSupport.firePropertyChange(PROP_PARAMETERTYPE, oldParameterType, parameterType);
    }

    /**
     * @return the controlType
     */
    public String getControlType() {
        return controlType;
    }

    /**
     * @param controlType the controlType to set
     */
    public void setControlType(String controlType) {
        java.lang.String oldControlType = this.controlType;
        this.controlType = controlType;
        propertyChangeSupport.firePropertyChange(PROP_CONTROLTYPE, oldControlType, controlType);
    }

    /**
     * @return the queryText
     */
    public QueryText getQueryText() {
        return queryText;
    }

    /**
     * @param queryText the queryText to set
     */
    public void setQueryText(QueryText queryText) {
        org.jw.service.entity.QueryText oldQueryText = this.queryText;
        this.queryText = queryText;
        propertyChangeSupport.firePropertyChange(PROP_QUERYTEXT, oldQueryText, queryText);
    }

    /**
     * @return the displayColumn
     */
    public String getDisplayColumn() {
        return displayColumn;
    }

    /**
     * @param displayColumn the displayColumn to set
     */
    public void setDisplayColumn(String displayColumn) {
        java.lang.String oldDisplayColumn = this.displayColumn;
        this.displayColumn = displayColumn;
        propertyChangeSupport.firePropertyChange(PROP_DISPLAYCOLUMN, oldDisplayColumn, displayColumn);
    }

    /**
     * @return the valueColumn
     */
    public String getValueColumn() {
        return valueColumn;
    }

    /**
     * @param valueColumn the valueColumn to set
     */
    public void setValueColumn(String valueColumn) {
        java.lang.String oldValueColumn = this.valueColumn;
        this.valueColumn = valueColumn;
        propertyChangeSupport.firePropertyChange(PROP_VALUECOLUMN, oldValueColumn, valueColumn);
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(boolean required) {
        boolean oldRequired = this.required;
        this.required = required;
        propertyChangeSupport.firePropertyChange(PROP_REQUIRED, oldRequired, required);
    }
        
        
}
