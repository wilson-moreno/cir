/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Wilson
 */
@Entity
@Table(name = "POSSIBLE_VALUE", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PossibleValue.findAll", query = "SELECT p FROM PossibleValue p"),
    @NamedQuery(name = "PossibleValue.findById", query = "SELECT p FROM PossibleValue p WHERE p.id = :id"),
    @NamedQuery(name = "PossibleValue.findByName", query = "SELECT p FROM PossibleValue p WHERE p.name = :name"),
    @NamedQuery(name = "PossibleValue.findByValue", query = "SELECT p FROM PossibleValue p WHERE p.value = :value"),
    @NamedQuery(name = "PossibleValue.findByQuery", query = "SELECT p FROM PossibleValue p WHERE p.query = :query"),
    @NamedQuery(name = "PossibleValue.findByType", query = "SELECT p FROM PossibleValue p WHERE p.type = :type"),
    @NamedQuery(name = "PossibleValue.findByCreatedDatetime", query = "SELECT p FROM PossibleValue p WHERE p.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "PossibleValue.findByUpdatedDatetime", query = "SELECT p FROM PossibleValue p WHERE p.updatedDatetime = :updatedDatetime")})
public class PossibleValue implements Serializable, ObservableEntity, SilentSetter {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "QUERY")
    private String query;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @JoinColumn(name = "PARAMETER_ID", referencedColumnName = "ID")
    @ManyToOne
    private AppsReportParameter parameterId;
    @Transient
    private String saveState;

    public PossibleValue() {
        this.createdDatetime = new Date();
        this.name = "";
        this.parameterId = null;
        this.query = "";
        this.saveState = "";
        this.type = "";
        this.updatedDatetime = new Date();
        this.value = "";
    }

    public PossibleValue(Integer id) {
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
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        java.lang.String oldValue = this.value;
        this.value = value;
        propertyChangeSupport.firePropertyChange(PROP_VALUE, oldValue, value);
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        java.lang.String oldQuery = this.query;
        this.query = query;
        propertyChangeSupport.firePropertyChange(PROP_QUERY, oldQuery, query);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        java.lang.String oldType = this.type;
        this.type = type;
        propertyChangeSupport.firePropertyChange(PROP_TYPE, oldType, type);
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
     * @return the parameterId
     */
    public AppsReportParameter getParameterId() {
        return parameterId;
    }

    /**
     * @param parameterId the parameterId to set
     */
    public void setParameterId(AppsReportParameter parameterId) {
        org.jw.service.entity.AppsReportParameter oldParameterId = this.parameterId;
        this.parameterId = parameterId;
        propertyChangeSupport.firePropertyChange(PROP_PARAMETERID, oldParameterId, parameterId);
    }

    /**
     * @return the saveState
     */
    public String getSaveState() {
        return saveState;
    }

    /**
     * @param saveState the saveState to set
     */
    public void setSaveState(String saveState) {
        java.lang.String oldSaveState = this.saveState;
        this.saveState = saveState;
        propertyChangeSupport.firePropertyChange(PROP_SAVESTATE, oldSaveState, saveState);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_VALUE = "value";
    public static final String PROP_QUERY = "query";
    public static final String PROP_TYPE = "type";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_PARAMETERID = "parameterId";
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
        if (!(object instanceof PossibleValue)) {
            return false;
        }
        PossibleValue other = (PossibleValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.PossibleValue[ id=" + id + " ]";
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
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }
    
}
