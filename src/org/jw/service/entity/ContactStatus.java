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
import javax.persistence.Lob;
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
@Table(name = "CONTACT_STATUS", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactStatus.findAll", query = "SELECT c FROM ContactStatus c"),
    @NamedQuery(name = "ContactStatus.findById", query = "SELECT c FROM ContactStatus c WHERE c.id = :id"),
    @NamedQuery(name = "ContactStatus.findByName", query = "SELECT c FROM ContactStatus c WHERE c.name = :name"),
    @NamedQuery(name = "ContactStatus.findByModifiable", query = "SELECT c FROM ContactStatus c WHERE c.modifiable = :modifiable"),
    @NamedQuery(name = "ContactStatus.findByPrintable", query = "SELECT c FROM ContactStatus c WHERE c.printable = :printable"),
    @NamedQuery(name = "ContactStatus.findByCreatedDatetime", query = "SELECT c FROM ContactStatus c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "ContactStatus.findByUpdatedDatetime", query = "SELECT c FROM ContactStatus c WHERE c.updatedDatetime = :updatedDatetime")})
public class ContactStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "MODIFIABLE")
    private Boolean modifiable;
    @Column(name = "PRINTABLE")
    private Boolean printable;
    @Lob
    @Column(name = "ICON")
    private byte[] icon;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "statusId")
    private Collection<Contact> contactCollection;

    public ContactStatus() {
    }

    public ContactStatus(Integer id) {
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

    public Boolean getModifiable() {
        return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    public Boolean getPrintable() {
        return printable;
    }

    public void setPrintable(Boolean printable) {
        this.printable = printable;
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
        if (!(object instanceof ContactStatus)) {
            return false;
        }
        ContactStatus other = (ContactStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.ContactStatus[ id=" + id + " ]";
    }
    
}
