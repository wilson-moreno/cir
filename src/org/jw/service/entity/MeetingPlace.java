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
import javax.persistence.ManyToOne;
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
@Table(name = "MEETING_PLACE", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeetingPlace.findAll", query = "SELECT m FROM MeetingPlace m"),
    @NamedQuery(name = "MeetingPlace.findById", query = "SELECT m FROM MeetingPlace m WHERE m.id = :id"),
    @NamedQuery(name = "MeetingPlace.findByName", query = "SELECT m FROM MeetingPlace m WHERE m.name = :name"),
    @NamedQuery(name = "MeetingPlace.findByAddress", query = "SELECT m FROM MeetingPlace m WHERE m.address = :address"),
    @NamedQuery(name = "MeetingPlace.findByLatitude", query = "SELECT m FROM MeetingPlace m WHERE m.latitude = :latitude"),
    @NamedQuery(name = "MeetingPlace.findByLongitude", query = "SELECT m FROM MeetingPlace m WHERE m.longitude = :longitude"),
    @NamedQuery(name = "MeetingPlace.findByCreatedDatetime", query = "SELECT m FROM MeetingPlace m WHERE m.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "MeetingPlace.findByUpdatedDatetime", query = "SELECT m FROM MeetingPlace m WHERE m.updatedDatetime = :updatedDatetime")})
public class MeetingPlace implements Serializable, ObservableEntity, SilentSetter, Comparable<MeetingPlace>{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "meetingPlaceId")
    private Collection<DirectionMap> directionMapCollection;
    @JoinColumn(name = "SERVICE_GROUP_ID", referencedColumnName = "ID")
    @ManyToOne
    private ServiceGroup serviceGroupId;
    @Transient
    private String saveState;

    public MeetingPlace() {
        this.address = "";
        this.createdDatetime = new Date();
        this.latitude = null;
        this.longitude = null;
        this.name = "";
        this.saveState = "";        
    }

    public MeetingPlace(Integer id) {
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        java.lang.String oldAddress = this.address;
        this.address = address;
        propertyChangeSupport.firePropertyChange(PROP_ADDRESS, oldAddress, address);
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        java.lang.Double oldLatitude = this.latitude;
        this.latitude = latitude;
        propertyChangeSupport.firePropertyChange(PROP_LATITUDE, oldLatitude, latitude);
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        java.lang.Double oldLongitude = this.longitude;
        this.longitude = longitude;
        propertyChangeSupport.firePropertyChange(PROP_LONGITUDE, oldLongitude, longitude);
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
     * @return the directionMapCollection
     */
    @XmlTransient
    public Collection<DirectionMap> getDirectionMapCollection() {
        return directionMapCollection;
    }

    /**
     * @param directionMapCollection the directionMapCollection to set
     */
    public void setDirectionMapCollection(Collection<DirectionMap> directionMapCollection) {
        java.util.Collection<org.jw.service.entity.DirectionMap> oldDirectionMapCollection = this.directionMapCollection;
        this.directionMapCollection = directionMapCollection;
        propertyChangeSupport.firePropertyChange(PROP_DIRECTIONMAPCOLLECTION, oldDirectionMapCollection, directionMapCollection);
    }

    /**
     * @return the serviceGroupId
     */
    public ServiceGroup getServiceGroupId() {
        return serviceGroupId;
    }

    /**
     * @param serviceGroupId the serviceGroupId to set
     */
    public void setServiceGroupId(ServiceGroup serviceGroupId) {
        org.jw.service.entity.ServiceGroup oldServiceGroupId = this.serviceGroupId;
        this.serviceGroupId = serviceGroupId;
        propertyChangeSupport.firePropertyChange(PROP_SERVICEGROUPID, oldServiceGroupId, serviceGroupId);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_ADDRESS = "address";
    public static final String PROP_LATITUDE = "latitude";
    public static final String PROP_LONGITUDE = "longitude";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_DIRECTIONMAPCOLLECTION = "directionMapCollection";
    public static final String PROP_SERVICEGROUPID = "serviceGroupId";

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeetingPlace)) {
            return false;
        }
        MeetingPlace other = (MeetingPlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.jw.service.entity.MeetingPlace[ id=" + id + " ]";
        return name;
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
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
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
    public boolean hasDependentEntities() {
        return !getDirectionMapCollection().isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return getName().trim().equals("") ||
               getAddress().trim().equals("") ||
               getLatitude() == null ||
               getLongitude() == null;
    }

    @Override
    public String getImplementingClassName() {
        return "Meeting.Place";
    }

    @Override
    public int compareTo(MeetingPlace o) {
        return this.getName().compareTo(o.getName());
    }
}
