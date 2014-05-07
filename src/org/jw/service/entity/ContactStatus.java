/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    public static final String PROP_ID = "PROP_ID";
    public static final String PROP_NAME = "PROP_NAME";
    public static final String PROP_DESCRIPTION = "PROP_DESCRIPTION";
    public static final String PROP_MODIFIABLE = "PROP_MODIFIABLE";
    public static final String PROP_PRINTABLE = "PROP_PRINTABLE";
    public static final String PROP_ICON = "PROP_ICON";
    public static final String PROP_CREATEDDATETIME = "PROP_CREATEDDATETIME";
    public static final String PROP_UPDATEDDATETIME = "PROP_UPDATEDDATETIME";
    public static final String PROP_CONTACTCOLLECTION = "PROP_CONTACTCOLLECTION";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
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
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    public ContactStatus() {
        this.name = "";
        this.modifiable = true;
        this.printable = true;
        this.createdDatetime = new Date();
        this.updatedDatetime = new Date();
    }

    public ContactStatus(Integer id) {
        super();
        this.id = id;
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
     * @return the modifiable
     */
    public Boolean getModifiable() {
        return modifiable;
    }

    /**
     * @param modifiable the modifiable to set
     */
    public void setModifiable(Boolean modifiable) {
        java.lang.Boolean oldModifiable = this.modifiable;
        this.modifiable = modifiable;
        propertyChangeSupport.firePropertyChange(PROP_MODIFIABLE, oldModifiable, modifiable);
    }

    /**
     * @return the printable
     */
    public Boolean getPrintable() {
        return printable;
    }

    /**
     * @param printable the printable to set
     */
    public void setPrintable(Boolean printable) {
        java.lang.Boolean oldPrintable = this.printable;
        this.printable = printable;
        propertyChangeSupport.firePropertyChange(PROP_PRINTABLE, oldPrintable, printable);
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
    
}
