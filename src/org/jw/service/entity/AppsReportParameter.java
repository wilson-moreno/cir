/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wilson
 */
@Entity
@Table(name = "APPS_REPORT_PARAMETER", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppsReportParameter.findAll", query = "SELECT a FROM AppsReportParameter a"),
    @NamedQuery(name = "AppsReportParameter.findById", query = "SELECT a FROM AppsReportParameter a WHERE a.id = :id"),
    @NamedQuery(name = "AppsReportParameter.findBySequence", query = "SELECT a FROM AppsReportParameter a WHERE a.sequence = :sequence"),
    @NamedQuery(name = "AppsReportParameter.findByName", query = "SELECT a FROM AppsReportParameter a WHERE a.name = :name"),
    @NamedQuery(name = "AppsReportParameter.findByLabel", query = "SELECT a FROM AppsReportParameter a WHERE a.label = :label"),
    @NamedQuery(name = "AppsReportParameter.findByControlType", query = "SELECT a FROM AppsReportParameter a WHERE a.controlType = :controlType"),
    @NamedQuery(name = "AppsReportParameter.findByCreatedDatetime", query = "SELECT a FROM AppsReportParameter a WHERE a.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "AppsReportParameter.findByUpdatedDatetime", query = "SELECT a FROM AppsReportParameter a WHERE a.updatedDatetime = :updatedDatetime")})
public class AppsReportParameter implements Serializable, ObservableEntity, SilentSetter, Comparable<AppsReportParameter> {
    public static final String PROP_DEFAULTVALUE = "defaultValue";
    public static final String PROP_PARAMETERTYPE = "parameterType";
    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;
    public static final String PROP_DATATYPE = "dataType";
    public static final String PROP_ENABLE = "enable";
    public static final String PROP_DEPENDSON = "dependsOn";
    @Column(name = "DATA_TYPE")
    private String dataType;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "REQUIRED")
    private Boolean required;
    @Column(name = "DEPENDS_ON")
    private Integer dependsOn;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "SEQUENCE")
    private Integer sequence;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LABEL")
    private String label;
    @Column(name = "CONTROL_TYPE")
    private String controlType;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "ID")
    @ManyToOne
    private AppsReport reportId;
    @OneToMany(mappedBy = "parameterId")
    private Collection<PossibleValue> possibleValueCollection;
    @Transient
    private String saveState;
    @Column (name = "PARAMETER_TYPE")
    private String parameterType;

    public AppsReportParameter() {
        this.controlType = "";
        this.createdDatetime = new Date();
        this.label = "";
        this.name = "";
        this.possibleValueCollection = null;
        this.reportId = null;
        this.saveState = "";
        this.sequence = 0;
        this.updatedDatetime = new Date();
        this.defaultValue = "";
        this.dataType = "String";
        this.enable = Boolean.TRUE;
        this.required = Boolean.TRUE;
        this.parameterType = "";
    }

    public AppsReportParameter(Integer id) {
        super();
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        java.lang.Integer oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange(PROP_ID, oldId, id);
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
     * @return the createdDatetime
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * @param createdDatetime the createdDatetime to set
     */
    public void setCreatedDatetime(Date createdDatetime) {
        java.util.Date oldCreatedDatetime = this.createdDatetime;
        this.createdDatetime = createdDatetime;
        propertyChangeSupport.firePropertyChange(PROP_CREATEDDATETIME, oldCreatedDatetime, createdDatetime);
    }

    /**
     * @return the updatedDatetime
     */
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    /**
     * @param updatedDatetime the updatedDatetime to set
     */
    public void setUpdatedDatetime(Date updatedDatetime) {
        java.util.Date oldUpdatedDatetime = this.updatedDatetime;
        this.updatedDatetime = updatedDatetime;
        propertyChangeSupport.firePropertyChange(PROP_UPDATEDDATETIME, oldUpdatedDatetime, updatedDatetime);
    }

    /**
     * @return the reportId
     */
    public AppsReport getReportId() {
        return reportId;
    }

    /**
     * @param reportId the reportId to set
     */
    public void setReportId(AppsReport reportId) {
        org.jw.service.entity.AppsReport oldReportId = this.reportId;
        this.reportId = reportId;
        propertyChangeSupport.firePropertyChange(PROP_REPORTID, oldReportId, reportId);
    }

    /**
     * @return the possibleValueCollection
     */
    @XmlTransient
    public Collection<PossibleValue> getPossibleValueCollection() {
        return possibleValueCollection;
    }

    /**
     * @param possibleValueCollection the possibleValueCollection to set
     */
    public void setPossibleValueCollection(Collection<PossibleValue> possibleValueCollection) {
        java.util.Collection<org.jw.service.entity.PossibleValue> oldPossibleValueCollection = this.possibleValueCollection;
        this.possibleValueCollection = possibleValueCollection;
        propertyChangeSupport.firePropertyChange(PROP_POSSIBLEVALUECOLLECTION, oldPossibleValueCollection, possibleValueCollection);
    }

    /**
     * @return the saveState
     */
    @Override
    public String getSaveState() {
        return saveState;
    }

    /**
     * @param saveState the saveState to set
     */
    @Override
    public void setSaveState(String saveState) {
        java.lang.String oldSaveState = this.saveState;
        this.saveState = saveState;
        propertyChangeSupport.firePropertyChange(PROP_SAVESTATE, oldSaveState, saveState);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_SEQUENCE = "sequence";
    public static final String PROP_NAME = "name";
    public static final String PROP_LABEL = "label";
    public static final String PROP_CONTROLTYPE = "controlType";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_REPORTID = "reportId";
    public static final String PROP_POSSIBLEVALUECOLLECTION = "possibleValueCollection";
    public static final String PROP_SAVESTATE = "saveState";

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppsReportParameter)) {
            return false;
        }
        AppsReportParameter other = (AppsReportParameter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.AppsReportParameter[ id=" + id + " ]";
    }

    

   @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }   
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }   
    
    @Override
    public void silentSetProperty(String name, Object value) {
        switch(name){
            case "updatedDatetime" : this.updatedDatetime = (Date) value; break;
            case "reportId" : this.reportId = (AppsReport) value; break;                 
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        String oldDataType = this.dataType;
        this.dataType = dataType;
        propertyChangeSupport.firePropertyChange("dataType", oldDataType, dataType);
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        Boolean oldEnable = this.enable;
        this.enable = enable;
        propertyChangeSupport.firePropertyChange("enable", oldEnable, enable);
    }

    public Integer getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Integer dependsOn) {
        Integer oldDependsOn = this.dependsOn;
        this.dependsOn = dependsOn;
        propertyChangeSupport.firePropertyChange("dependsOn", oldDependsOn, dependsOn);
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        String oldDefaultValue = this.defaultValue;
        this.defaultValue = defaultValue;
        propertyChangeSupport.firePropertyChange("defaultValue", oldDefaultValue, defaultValue);
    }

    /**
     * @return the required
     */
    public Boolean getRequired() {
        return required;
    }

    /**
     * @param required the required to set
     */
    public void setRequired(Boolean required) {
        Boolean oldRequired = this.required;
        this.required = required;
        propertyChangeSupport.firePropertyChange("required", oldRequired, required);
    }

    @Override
    public int compareTo(AppsReportParameter t) {
        return this.sequence.compareTo(t.sequence);
    }

    @Override
    public boolean hasDependentEntities() {
        return !getPossibleValueCollection().isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return getSequence() == null ||
               getName().trim().equals("") ||
               getLabel().trim().equals("");
        
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
    
}
