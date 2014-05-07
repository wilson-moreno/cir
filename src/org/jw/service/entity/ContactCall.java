/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

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
    @NamedQuery(name = "ContactCall.findByScriptures", query = "SELECT c FROM ContactCall c WHERE c.scriptures = :scriptures"),
    @NamedQuery(name = "ContactCall.findByLiterature", query = "SELECT c FROM ContactCall c WHERE c.literature = :literature"),
    @NamedQuery(name = "ContactCall.findByPublishers", query = "SELECT c FROM ContactCall c WHERE c.publishers = :publishers"),
    @NamedQuery(name = "ContactCall.findByNotes", query = "SELECT c FROM ContactCall c WHERE c.notes = :notes"),
    @NamedQuery(name = "ContactCall.findByNextVisit", query = "SELECT c FROM ContactCall c WHERE c.nextVisit = :nextVisit"),
    @NamedQuery(name = "ContactCall.findByNextTopic", query = "SELECT c FROM ContactCall c WHERE c.nextTopic = :nextTopic"),
    @NamedQuery(name = "ContactCall.findByCreatedDatetime", query = "SELECT c FROM ContactCall c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "ContactCall.findByUpdatedDatetime", query = "SELECT c FROM ContactCall c WHERE c.updatedDatetime = :updatedDatetime")})
public class ContactCall implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CALL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date callDate;
    @Column(name = "CALL_DAY", length = 15)
    private String callDay;
    @Column(name = "CALL_TIME", length = 10)
    private String callTime;
    @Column(name = "SCRIPTURES", length = 75)
    private String scriptures;
    @Column(name = "LITERATURE", length = 75)
    private String literature;
    @Column(name = "PUBLISHERS", length = 75)
    private String publishers;
    @Column(name = "NOTES", length = 16777216)
    private String notes;
    @Column(name = "NEXT_VISIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextVisit;
    @Column(name = "NEXT_TOPIC", length = 16777216)
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

    public ContactCall() {
    }

    public ContactCall(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallDay() {
        return callDay;
    }

    public void setCallDay(String callDay) {
        this.callDay = callDay;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getScriptures() {
        return scriptures;
    }

    public void setScriptures(String scriptures) {
        this.scriptures = scriptures;
    }

    public String getLiterature() {
        return literature;
    }

    public void setLiterature(String literature) {
        this.literature = literature;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getNextVisit() {
        return nextVisit;
    }

    public void setNextVisit(Date nextVisit) {
        this.nextVisit = nextVisit;
    }

    public String getNextTopic() {
        return nextTopic;
    }

    public void setNextTopic(String nextTopic) {
        this.nextTopic = nextTopic;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

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
    
}
