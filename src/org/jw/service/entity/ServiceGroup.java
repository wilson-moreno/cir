/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
public class ServiceGroup implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public ServiceGroup() {
    }

    public ServiceGroup(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverseer() {
        return overseer;
    }

    public void setOverseer(String overseer) {
        this.overseer = overseer;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public Integer getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(Integer nextNumber) {
        this.nextNumber = nextNumber;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
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

    public Congregation getCongregationId() {
        return congregationId;
    }

    public void setCongregationId(Congregation congregationId) {
        this.congregationId = congregationId;
    }

    @XmlTransient
    public Collection<MeetingPlace> getMeetingPlaceCollection() {
        return meetingPlaceCollection;
    }

    public void setMeetingPlaceCollection(Collection<MeetingPlace> meetingPlaceCollection) {
        this.meetingPlaceCollection = meetingPlaceCollection;
    }

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
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
        return "org.jw.service.entity.ServiceGroup[ id=" + id + " ]";
    }
    
}
