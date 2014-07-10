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
import javax.persistence.OneToOne;
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
@Table(name = "TERRITORY", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Territory.findAll", query = "SELECT t FROM Territory t"),
    @NamedQuery(name = "Territory.findById", query = "SELECT t FROM Territory t WHERE t.id = :id"),
    @NamedQuery(name = "Territory.findByName", query = "SELECT t FROM Territory t WHERE t.name = :name"),
    @NamedQuery(name = "Territory.findByDescription", query = "SELECT t FROM Territory t WHERE t.description = :description"),
    @NamedQuery(name = "Territory.findByEnable", query = "SELECT t FROM Territory t WHERE t.enable = :enable"),
    @NamedQuery(name = "Territory.findByCreatedDatetime", query = "SELECT t FROM Territory t WHERE t.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "Territory.findByUpdatedDatetime", query = "SELECT t FROM Territory t WHERE t.updatedDatetime = :updatedDatetime")})
public class Territory implements Serializable, ObservableEntity, SilentSetter, Comparable<Territory> {
    private static final long serialVersionUID = 1L;
    public static final String PROP_DIRECTION = "direction";        
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_DESCRIPTION = "description";
    public static final String PROP_ENABLE = "enable";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_MEETINGPLACEID = "meetingPlaceId";
    public static final String PROP_MARKERCOLOR = "markerColor";
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
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
    @Column(name = "DIRECTION")
    private String direction;    
    @Transient
    private String saveState;
    @Lob
    @Column(name = "MAP_IMAGE")
    private byte[] mapImage;
    @OneToMany(mappedBy = "territoryId")
    private Collection<Contact> contactCollection;
    @JoinColumn(name = "SERVICE_GROUP_ID", referencedColumnName = "ID")
    @ManyToOne
    private ServiceGroup serviceGroupId;
    @JoinColumn(name="MEETING_PLACE_ID", referencedColumnName = "ID")
    @OneToOne
    private MeetingPlace meetingPlaceId;
    @Column(name = "MARKER_COLOR")    
    private String markerColor;    
    

    public Territory() {
        this.createdDatetime = new Date();
        this.description = "";
        this.enable = true;
        this.name = "";
        this.saveState = "";
        this.serviceGroupId = null;
        this.updatedDatetime = new Date();
        this.direction = "";
        this.markerColor = "red";
    }

    public Territory(Integer id) {
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
        if (!(object instanceof Territory)) {
            return false;
        }
        Territory other = (Territory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.jw.service.entity.Territory[ id=" + id + " ]";
        return this.name;
    }

    /**
     * @return the id
     */
    @Override
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

    public byte[] getMapImage() {
        return mapImage;
    }

    public void setMapImage(byte[] mapImage) {
        byte[] oldMapImage = this.mapImage;
        this.mapImage = mapImage;
        propertyChangeSupport.firePropertyChange("mapImage", oldMapImage, mapImage);
    }

    public ServiceGroup getServiceGroupId() {
        return serviceGroupId;
    }

    public void setServiceGroupId(ServiceGroup serviceGroupId) {
        ServiceGroup oldServiceGroupId = this.serviceGroupId;
        this.serviceGroupId = serviceGroupId;
        propertyChangeSupport.firePropertyChange("serviceGroupId", oldServiceGroupId, serviceGroupId);
    }

    @Override
    public int compareTo(Territory t) {
        return this.name.compareTo(t.getName());
    }
    

    @XmlTransient
    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    @Override
    public boolean hasDependentEntities() {
        return !getContactCollection().isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return getName().trim().equals("") ||
               getDescription().trim().equalsIgnoreCase("") ||
               getServiceGroupId() == null;
    }

    @Override
    public String getImplementingClassName() {
        return "Territory";
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        java.lang.String oldDirection = this.direction;
        this.direction = direction;
        propertyChangeSupport.firePropertyChange("direction", oldDirection, direction);
    }

    /**
     * @return the meetingPlaceId
     */
    public MeetingPlace getMeetingPlaceId() {
        return meetingPlaceId;
    }

    /**
     * @param meetingPlaceId the meetingPlaceId to set
     */
    public void setMeetingPlaceId(MeetingPlace meetingPlaceId) {
        org.jw.service.entity.MeetingPlace oldMeetingPlaceId = this.meetingPlaceId;
        this.meetingPlaceId = meetingPlaceId;
        propertyChangeSupport.firePropertyChange(PROP_MEETINGPLACEID, oldMeetingPlaceId, meetingPlaceId);
    }

    /**
     * @return the markerColor
     */
    public String getMarkerColor() {
        return markerColor;
    }

    /**
     * @param markerColor the markerColor to set
     */
    public void setMarkerColor(String markerColor) {
        java.lang.String oldMarkerColor = this.markerColor;
        this.markerColor = markerColor;
        propertyChangeSupport.firePropertyChange(PROP_MARKERCOLOR, oldMarkerColor, markerColor);
    }

    
}
