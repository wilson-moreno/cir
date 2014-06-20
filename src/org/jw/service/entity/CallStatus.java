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
@Table(name = "CALL_STATUS", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallStatus.findAll", query = "SELECT c FROM CallStatus c"),
    @NamedQuery(name = "CallStatus.findById", query = "SELECT c FROM CallStatus c WHERE c.id = :id"),
    @NamedQuery(name = "CallStatus.findByName", query = "SELECT c FROM CallStatus c WHERE c.name = :name"),
    @NamedQuery(name = "CallStatus.findByDescription", query = "SELECT c FROM CallStatus c WHERE c.description = :description"),
    @NamedQuery(name = "CallStatus.findByEnable", query = "SELECT c FROM CallStatus c WHERE c.enable = :enable"),
    @NamedQuery(name = "CallStatus.findByCreatedDatetime", query = "SELECT c FROM CallStatus c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "CallStatus.findByUpdatedDatetime", query = "SELECT c FROM CallStatus c WHERE c.updatedDatetime = :updatedDatetime")})
public class CallStatus implements Serializable, ObservableEntity, SilentSetter, Comparable<CallStatus> {
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_ENABLE = "enable";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_CONTACTCALLCOLLECTION = "contactCallCollection";
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
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "callStatusId")
    private Collection<ContactCall> contactCallCollection;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Transient
    private String saveState;
    
    public CallStatus() {
        this.name = "";
        this.description = "";
        this.createdDatetime = new Date();
        this.updatedDatetime = new Date();
        this.enable = true;
        this.saveState = "";
    }

    public CallStatus(Integer id) {
        super();
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
        if (!(object instanceof CallStatus)) {
            return false;
        }
        CallStatus other = (CallStatus) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.jw.service.entity.CallStatus[ id=" + getId() + " ]";
        return this.name;
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
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnable(Boolean enable) {
        java.lang.Boolean oldEnable = this.enable;
        this.enable = enable;
        propertyChangeSupport.firePropertyChange(PROP_ENABLE, oldEnable, enable);
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
     * @return the contactCallCollection
     */
    public Collection<ContactCall> getContactCallCollection() {
        return contactCallCollection;
    }

    /**
     * @param contactCallCollection the contactCallCollection to set
     */
    public void setContactCallCollection(Collection<ContactCall> contactCallCollection) {
        java.util.Collection<org.jw.service.entity.ContactCall> oldContactCallCollection = this.contactCallCollection;
        this.contactCallCollection = contactCallCollection;
        propertyChangeSupport.firePropertyChange(PROP_CONTACTCALLCOLLECTION, oldContactCallCollection, contactCallCollection);
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
    public boolean hasDependentEntities() {
        return !this.contactCallCollection.isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return this.getName().trim().equals("") ||
               this.getDescription().trim().equals("");
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
    public String getImplementingClassName() {
        return "Class.Status";
    }

    @Override
    public int compareTo(CallStatus o) {
        return this.getName().compareTo(o.getName());
    }
    
}
