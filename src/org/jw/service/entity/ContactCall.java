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
@Table(name = "CONTACT_CALL", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactCall.findAll", query = "SELECT c FROM ContactCall c"),
    @NamedQuery(name = "ContactCall.findById", query = "SELECT c FROM ContactCall c WHERE c.id = :id"),
    @NamedQuery(name = "ContactCall.findByCallDate", query = "SELECT c FROM ContactCall c WHERE c.callDate = :callDate"),
    @NamedQuery(name = "ContactCall.findByCallDay", query = "SELECT c FROM ContactCall c WHERE c.callDay = :callDay"),
    @NamedQuery(name = "ContactCall.findByCallTime", query = "SELECT c FROM ContactCall c WHERE c.callTime = :callTime"),
    @NamedQuery(name = "ContactCall.findByStatus", query = "SELECT c FROM ContactCall c WHERE c.status = :status"),
    @NamedQuery(name = "ContactCall.findByScriptures", query = "SELECT c FROM ContactCall c WHERE c.scriptures = :scriptures"),
    @NamedQuery(name = "ContactCall.findByLiterature", query = "SELECT c FROM ContactCall c WHERE c.literature = :literature"),
    @NamedQuery(name = "ContactCall.findByPublishers", query = "SELECT c FROM ContactCall c WHERE c.publishers = :publishers"),
    @NamedQuery(name = "ContactCall.findByNotes", query = "SELECT c FROM ContactCall c WHERE c.notes = :notes"),
    @NamedQuery(name = "ContactCall.findByNextVisit", query = "SELECT c FROM ContactCall c WHERE c.nextVisit = :nextVisit"),
    @NamedQuery(name = "ContactCall.findByNextTopic", query = "SELECT c FROM ContactCall c WHERE c.nextTopic = :nextTopic"),
    @NamedQuery(name = "ContactCall.findByCreatedDatetime", query = "SELECT c FROM ContactCall c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "ContactCall.findByUpdatedDatetime", query = "SELECT c FROM ContactCall c WHERE c.updatedDatetime = :updatedDatetime")})
public class ContactCall implements Serializable, ObservableEntity, SilentSetter {
    @Column(name = "CALL_TIME")
    @Temporal(TemporalType.TIME)
    private Date callTime;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CALL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date callDate;
    @Column(name = "CALL_DAY")
    private String callDay;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "SCRIPTURES")
    private String scriptures;
    @Column(name = "LITERATURE")
    private String literature;
    @Column(name = "PUBLISHERS")
    private String publishers;
    @Column(name = "NOTES")
    private String notes;
    @Column(name = "NEXT_VISIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextVisit;
    @Column(name = "NEXT_TOPIC")
    private String nextTopic;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact contactId;
    @Transient
    private String saveState;

    public ContactCall() {
        this.callDate = new Date();
        this.callDay = "";
        this.callTime = new Date();
        this.contactId = null;
        this.createdDatetime = new Date();
        this.literature = "";
        this.nextTopic = "";
        this.nextVisit = new Date();
        this.notes = "";
        this.publishers = "";
        this.saveState = "";
        this.scriptures = "";
        this.status = "";
        this.updatedDatetime = new Date();
    }

    public ContactCall(Integer id) {
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
     * @return the callDate
     */
    public Date getCallDate() {
        return callDate;
    }

    /**
     * @param callDate the callDate to set
     */
    public void setCallDate(Date callDate) {
        java.util.Date oldCallDate = this.callDate;
        this.callDate = callDate;
        propertyChangeSupport.firePropertyChange(PROP_CALLDATE, oldCallDate, callDate);
    }

    /**
     * @return the callDay
     */
    public String getCallDay() {
        return callDay;
    }

    /**
     * @param callDay the callDay to set
     */
    public void setCallDay(String callDay) {
        java.lang.String oldCallDay = this.callDay;
        this.callDay = callDay;
        propertyChangeSupport.firePropertyChange(PROP_CALLDAY, oldCallDay, callDay);
    }

    /**
     * @return the callTime
     */
    public Date getCallTime() {
        return callTime;
    }

    /**
     * @param callTime the callTime to set
     */
    public void setCallTime(Date callTime) {
        Date oldCallTime = this.callTime;
        this.callTime = callTime;
        propertyChangeSupport.firePropertyChange(PROP_CALLTIME, oldCallTime, callTime);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        java.lang.String oldStatus = this.status;
        this.status = status;
        propertyChangeSupport.firePropertyChange(PROP_STATUS, oldStatus, status);
    }

    /**
     * @return the scriptures
     */
    public String getScriptures() {
        return scriptures;
    }

    /**
     * @param scriptures the scriptures to set
     */
    public void setScriptures(String scriptures) {
        java.lang.String oldScriptures = this.scriptures;
        this.scriptures = scriptures;
        propertyChangeSupport.firePropertyChange(PROP_SCRIPTURES, oldScriptures, scriptures);
    }

    /**
     * @return the literature
     */
    public String getLiterature() {
        return literature;
    }

    /**
     * @param literature the literature to set
     */
    public void setLiterature(String literature) {
        java.lang.String oldLiterature = this.literature;
        this.literature = literature;
        propertyChangeSupport.firePropertyChange(PROP_LITERATURE, oldLiterature, literature);
    }

    /**
     * @return the publishers
     */
    public String getPublishers() {
        return publishers;
    }

    /**
     * @param publishers the publishers to set
     */
    public void setPublishers(String publishers) {
        java.lang.String oldPublishers = this.publishers;
        this.publishers = publishers;
        propertyChangeSupport.firePropertyChange(PROP_PUBLISHERS, oldPublishers, publishers);
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        java.lang.String oldNotes = this.notes;
        this.notes = notes;
        propertyChangeSupport.firePropertyChange(PROP_NOTES, oldNotes, notes);
    }

    /**
     * @return the nextVisit
     */
    public Date getNextVisit() {
        return nextVisit;
    }

    /**
     * @param nextVisit the nextVisit to set
     */
    public void setNextVisit(Date nextVisit) {
        java.util.Date oldNextVisit = this.nextVisit;
        this.nextVisit = nextVisit;
        propertyChangeSupport.firePropertyChange(PROP_NEXTVISIT, oldNextVisit, nextVisit);
    }

    /**
     * @return the nextTopic
     */
    public String getNextTopic() {
        return nextTopic;
    }

    /**
     * @param nextTopic the nextTopic to set
     */
    public void setNextTopic(String nextTopic) {
        java.lang.String oldNextTopic = this.nextTopic;
        this.nextTopic = nextTopic;
        propertyChangeSupport.firePropertyChange(PROP_NEXTTOPIC, oldNextTopic, nextTopic);
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
     * @return the contactId
     */
    public Contact getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Contact contactId) {
        org.jw.service.entity.Contact oldContactId = this.contactId;
        this.contactId = contactId;
        propertyChangeSupport.firePropertyChange(PROP_CONTACTID, oldContactId, contactId);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_CALLDATE = "callDate";
    public static final String PROP_CALLDAY = "callDay";
    public static final String PROP_CALLTIME = "callTime";
    public static final String PROP_STATUS = "status";
    public static final String PROP_SCRIPTURES = "scriptures";
    public static final String PROP_LITERATURE = "literature";
    public static final String PROP_PUBLISHERS = "publishers";
    public static final String PROP_NOTES = "notes";
    public static final String PROP_NEXTVISIT = "nextVisit";
    public static final String PROP_NEXTTOPIC = "nextTopic";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_CONTACTID = "contactId";
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactCall)) {
            return false;
        }
        ContactCall other = (ContactCall) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.ContactCall[ id=" + id + " ]";
    }

    @Override
    public String getSaveState() {
        return saveState;
    }

    @Override
    public void setSaveState(String saveState) {
        String oldSaveState = this.saveState;
        this.saveState = saveState;
        propertyChangeSupport.firePropertyChange("saveState", oldSaveState, saveState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

    @Override
    public void silentSetProperty(String name, Object value) {
        switch(name){
            case "updatedDatetime" : this.updatedDatetime = (Date) value; break;
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }
}
