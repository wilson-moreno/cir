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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "SERVICE_GROUP", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceGroup.findAll", query = "SELECT s FROM ServiceGroup s"),
    @NamedQuery(name = "ServiceGroup.findById", query = "SELECT s FROM ServiceGroup s WHERE s.id = :id"),
    @NamedQuery(name = "ServiceGroup.findByName", query = "SELECT s FROM ServiceGroup s WHERE s.name = :name"),
    @NamedQuery(name = "ServiceGroup.findByOverseer", query = "SELECT s FROM ServiceGroup s WHERE s.overseer = :overseer"),
    @NamedQuery(name = "ServiceGroup.findByAssistant", query = "SELECT s FROM ServiceGroup s WHERE s.assistant = :assistant"),
    @NamedQuery(name = "ServiceGroup.findByPrefix", query = "SELECT s FROM ServiceGroup s WHERE s.prefix = :prefix"),
    @NamedQuery(name = "ServiceGroup.findByStartNumber", query = "SELECT s FROM ServiceGroup s WHERE s.startNumber = :startNumber"),
    @NamedQuery(name = "ServiceGroup.findByNextNumber", query = "SELECT s FROM ServiceGroup s WHERE s.nextNumber = :nextNumber"),
    @NamedQuery(name = "ServiceGroup.findByCreatedDatetime", query = "SELECT s FROM ServiceGroup s WHERE s.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "ServiceGroup.findByUpdatedDatetime", query = "SELECT s FROM ServiceGroup s WHERE s.updatedDatetime = :updatedDatetime")})
public class ServiceGroup implements Serializable, ObservableEntity, SilentSetter {
    private static final long serialVersionUID = 1L;
    public static final String PROP_SAVESTATE = "saveState";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "OVERSEER")
    private String overseer;
    @Column(name = "ASSISTANT")
    private String assistant;
    @Column(name = "PREFIX")
    private String prefix;
    @Column(name = "START_NUMBER")
    private Integer startNumber;
    @Column(name = "NEXT_NUMBER")
    private Integer nextNumber;
    @Lob
    @Column(name = "ICON")
    private byte[] icon;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @JoinColumn(name = "CONGREGATION_ID", referencedColumnName = "ID")
    @ManyToOne
    private Congregation congregationId;
    @OneToMany(mappedBy = "serviceGroupId")
    private Collection<MeetingPlace> meetingPlaceCollection;
    @OneToMany(mappedBy = "serviceGroupId")
    private Collection<Contact> contactCollection;
    @Transient
    private String saveState;

    public ServiceGroup() {
        this.congregationId = null;
        this.contactCollection = null;
        this.createdDatetime = new Date();
        this.icon = null;
        this.meetingPlaceCollection = null;
        this.name = "";
        this.nextNumber = new Integer(0);
        this.overseer = "";
        this.prefix = "";
        this.saveState = "";
        this.startNumber = new Integer(0);
        this.updatedDatetime = new Date();
    }

    public ServiceGroup(Integer id) {
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
     * @return the overseer
     */
    public String getOverseer() {
        return overseer;
    }

    /**
     * @param overseer the overseer to set
     */
    public void setOverseer(String overseer) {
        java.lang.String oldOverseer = this.overseer;
        this.overseer = overseer;
        propertyChangeSupport.firePropertyChange(PROP_OVERSEER, oldOverseer, overseer);
    }

    /**
     * @return the assistant
     */
    public String getAssistant() {
        return assistant;
    }

    /**
     * @param assistant the assistant to set
     */
    public void setAssistant(String assistant) {
        java.lang.String oldAssistant = this.assistant;
        this.assistant = assistant;
        propertyChangeSupport.firePropertyChange(PROP_ASSISTANT, oldAssistant, assistant);
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        java.lang.String oldPrefix = this.prefix;
        this.prefix = prefix;
        propertyChangeSupport.firePropertyChange(PROP_PREFIX, oldPrefix, prefix);
    }

    /**
     * @return the startNumber
     */
    public Integer getStartNumber() {
        return startNumber;
    }

    /**
     * @param startNumber the startNumber to set
     */
    public void setStartNumber(Integer startNumber) {
        java.lang.Integer oldStartNumber = this.startNumber;
        this.startNumber = startNumber;
        propertyChangeSupport.firePropertyChange(PROP_STARTNUMBER, oldStartNumber, startNumber);
    }

    /**
     * @return the nextNumber
     */
    public Integer getNextNumber() {
        return nextNumber;
    }

    /**
     * @param nextNumber the nextNumber to set
     */
    public void setNextNumber(Integer nextNumber) {
        java.lang.Integer oldNextNumber = this.nextNumber;
        this.nextNumber = nextNumber;
        propertyChangeSupport.firePropertyChange(PROP_NEXTNUMBER, oldNextNumber, nextNumber);
    }

    /**
     * @return the icon
     */
    public byte[] getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(byte[] icon) {
        byte[] oldIcon = this.icon;
        this.icon = icon;
        propertyChangeSupport.firePropertyChange(PROP_ICON, oldIcon, icon);
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
     * @return the congregationId
     */
    public Congregation getCongregationId() {
        return congregationId;
    }

    /**
     * @param congregationId the congregationId to set
     */
    public void setCongregationId(Congregation congregationId) {
        org.jw.service.entity.Congregation oldCongregationId = this.congregationId;
        this.congregationId = congregationId;
        propertyChangeSupport.firePropertyChange(PROP_CONGREGATIONID, oldCongregationId, congregationId);
    }

    /**
     * @return the meetingPlaceCollection
     */
    public Collection<MeetingPlace> getMeetingPlaceCollection() {
        return meetingPlaceCollection;
    }

    /**
     * @param meetingPlaceCollection the meetingPlaceCollection to set
     */
    public void setMeetingPlaceCollection(Collection<MeetingPlace> meetingPlaceCollection) {
        java.util.Collection<org.jw.service.entity.MeetingPlace> oldMeetingPlaceCollection = this.meetingPlaceCollection;
        this.meetingPlaceCollection = meetingPlaceCollection;
        propertyChangeSupport.firePropertyChange(PROP_MEETINGPLACECOLLECTION, oldMeetingPlaceCollection, meetingPlaceCollection);
    }

    /**
     * @return the contactCollection
     */
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    /**
     * @param contactCollection the contactCollection to set
     */
    public void setContactCollection(Collection<Contact> contactCollection) {
        java.util.Collection<org.jw.service.entity.Contact> oldContactCollection = this.contactCollection;
        this.contactCollection = contactCollection;
        propertyChangeSupport.firePropertyChange(PROP_CONTACTCOLLECTION, oldContactCollection, contactCollection);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_OVERSEER = "overseer";
    public static final String PROP_ASSISTANT = "assistant";
    public static final String PROP_PREFIX = "prefix";
    public static final String PROP_STARTNUMBER = "startNumber";
    public static final String PROP_NEXTNUMBER = "nextNumber";
    public static final String PROP_ICON = "icon";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_CONGREGATIONID = "congregationId";
    public static final String PROP_MEETINGPLACECOLLECTION = "meetingPlaceCollection";
    public static final String PROP_CONTACTCOLLECTION = "contactCollection";

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceGroup)) {
            return false;
        }
        ServiceGroup other = (ServiceGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.jw.service.entity.ServiceGroup[ id=" + id + " ]";
        return this.name;
    }
    
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }   
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
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

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
    
    @Override
    public void silentSetProperty(String name, Object value) {
        switch(name){
            case "updatedDatetime" : this.updatedDatetime = (Date) value; break;
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }
    
    public String useNextRecordNumber(){
        String prefixString = prefix.trim();
        Integer nextNumberInteger = nextNumber;
        nextNumber += 1;        
        return prefixString + nextNumberInteger.toString().trim();        
    }    
}
