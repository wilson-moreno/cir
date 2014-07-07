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
@Table(name = "QUERY_TEXT", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QueryText.findAll", query = "SELECT q FROM QueryText q"),
    @NamedQuery(name = "QueryText.findById", query = "SELECT q FROM QueryText q WHERE q.id = :id"),
    @NamedQuery(name = "QueryText.findByName", query = "SELECT q FROM QueryText q WHERE q.name = :name"),
    @NamedQuery(name = "QueryText.findByDescription", query = "SELECT q FROM QueryText q WHERE q.description = :description"),
    @NamedQuery(name = "QueryText.findByText", query = "SELECT q FROM QueryText q WHERE q.text = :text"),
    @NamedQuery(name = "QueryText.findByCreatedDatetime", query = "SELECT q FROM QueryText q WHERE q.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "QueryText.findByUpdatedDatetime", query = "SELECT q FROM QueryText q WHERE q.updatedDatetime = :updatedDatetime")})
public class QueryText implements Serializable , ObservableEntity, SilentSetter, Comparable<QueryText> {
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_TEXT = "text";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_APPSREPORTPARAMETERCOLLECTION = "appsReportParameterCollection";
    public static final String PROP_SAVESTATE = "saveState";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "queryTextId")
    private Collection<AppsReportParameter> appsReportParameterCollection;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Transient
    private String saveState;
    
    public QueryText() {
        this.createdDatetime = new Date();
        this.description = "";
        this.name = "";
        this.saveState = "";
        this.text = "";
        this.updatedDatetime = new Date();
    }

    public QueryText(Integer id) {
        this();
        this.id = id;
    }

    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QueryText)) {
            return false;
        }
        QueryText other = (QueryText) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        java.lang.String oldDescription = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange(PROP_DESCRIPTION, oldDescription, description);
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        java.lang.String oldText = this.text;
        this.text = text;
        propertyChangeSupport.firePropertyChange(PROP_TEXT, oldText, text);
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
     * @return the appsReportParameterCollection
     */
    @XmlTransient
    public Collection<AppsReportParameter> getAppsReportParameterCollection() {
        return appsReportParameterCollection;
    }

    /**
     * @param appsReportParameterCollection the appsReportParameterCollection to set
     */
    public void setAppsReportParameterCollection(Collection<AppsReportParameter> appsReportParameterCollection) {
        java.util.Collection<org.jw.service.entity.AppsReportParameter> oldAppsReportParameterCollection = this.appsReportParameterCollection;
        this.appsReportParameterCollection = appsReportParameterCollection;
        propertyChangeSupport.firePropertyChange(PROP_APPSREPORTPARAMETERCOLLECTION, oldAppsReportParameterCollection, appsReportParameterCollection);
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

    @Override
    public String getImplementingClassName() {
        return "QueryText";
    }

    @Override
    public boolean hasDependentEntities() {
        return !this.appsReportParameterCollection.isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return this.name.equals("") ||
               this.description.equals("") ||
               this.text.equals("");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public void silentSetProperty(String name, Object value) {
        switch(name){
            case "updatedDatetime" : this.updatedDatetime = (Date) value; break;
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }

    @Override
    public int compareTo(QueryText o) {
        return name.compareTo(o.getName());
    }
    
}
