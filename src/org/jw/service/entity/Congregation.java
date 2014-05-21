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
@Table(name = "CONGREGATION", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Congregation.findAll", query = "SELECT c FROM Congregation c"),
    @NamedQuery(name = "Congregation.findById", query = "SELECT c FROM Congregation c WHERE c.id = :id"),
    @NamedQuery(name = "Congregation.findByName", query = "SELECT c FROM Congregation c WHERE c.name = :name"),
    @NamedQuery(name = "Congregation.findByAddress1", query = "SELECT c FROM Congregation c WHERE c.address1 = :address1"),
    @NamedQuery(name = "Congregation.findByAddress2", query = "SELECT c FROM Congregation c WHERE c.address2 = :address2"),
    @NamedQuery(name = "Congregation.findByCity", query = "SELECT c FROM Congregation c WHERE c.city = :city"),
    @NamedQuery(name = "Congregation.findByCreatedDatetime", query = "SELECT c FROM Congregation c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "Congregation.findByUpdatedDatetime", query = "SELECT c FROM Congregation c WHERE c.updatedDatetime = :updatedDatetime")})
public class Congregation implements Serializable , ObservableEntity, SilentSetter{
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_NAME = "name";
    public static final String PROP_ADDRESS1 = "address1";
    public static final String PROP_ADDRESS2 = "address2";
    public static final String PROP_CITY = "city";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_SERVICEGROUPCOLLECTION = "serviceGroupCollection";
    public static final String PROP_LATITUDE = "latitude";
    public static final String PROP_LONGITUDE = "longitudes";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS_1")
    private String address1;
    @Column(name = "ADDRESS_2")
    private String address2;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "CITY")
    private String city;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "congregationId")
    private Collection<ServiceGroup> serviceGroupCollection;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Transient
    private String saveState;
    
    
    public Congregation() {
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.createdDatetime = new Date();
        this.name ="";
        this.serviceGroupCollection = null;
        this.updatedDatetime = new Date();
        this.latitude = null;
        this.longitude = null;
    }

    public Congregation(Integer id) {
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
        if (!(object instanceof Congregation)) {
            return false;
        }
        Congregation other = (Congregation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.Congregation[ id=" + id + " ]";
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
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        java.lang.String oldAddress1 = this.address1;
        this.address1 = address1;
        propertyChangeSupport.firePropertyChange(PROP_ADDRESS1, oldAddress1, address1);
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        java.lang.String oldAddress2 = this.address2;
        this.address2 = address2;
        propertyChangeSupport.firePropertyChange(PROP_ADDRESS2, oldAddress2, address2);
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        java.lang.String oldCity = this.city;
        this.city = city;
        propertyChangeSupport.firePropertyChange(PROP_CITY, oldCity, city);
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
     * @return the serviceGroupCollection
     */
    @XmlTransient
    public Collection<ServiceGroup> getServiceGroupCollection() {
        return serviceGroupCollection;
    }

    /**
     * @param serviceGroupCollection the serviceGroupCollection to set
     */
    public void setServiceGroupCollection(Collection<ServiceGroup> serviceGroupCollection) {
        java.util.Collection<org.jw.service.entity.ServiceGroup> oldServiceGroupCollection = this.serviceGroupCollection;
        this.serviceGroupCollection = serviceGroupCollection;
        propertyChangeSupport.firePropertyChange(PROP_SERVICEGROUPCOLLECTION, oldServiceGroupCollection, serviceGroupCollection);
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
        String oldSaveState = this.saveState;
        this.saveState = saveState;
        propertyChangeSupport.firePropertyChange("saveState", oldSaveState, saveState);
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
 
    
}
