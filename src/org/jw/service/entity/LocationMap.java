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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wilson
 */
@Entity
@Table(name = "LOCATION_MAP", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocationMap.findAll", query = "SELECT l FROM LocationMap l"),
    @NamedQuery(name = "LocationMap.findById", query = "SELECT l FROM LocationMap l WHERE l.id = :id"),
    @NamedQuery(name = "LocationMap.findByLatitude", query = "SELECT l FROM LocationMap l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "LocationMap.findByLongitude", query = "SELECT l FROM LocationMap l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "LocationMap.findByWidth", query = "SELECT l FROM LocationMap l WHERE l.width = :width"),
    @NamedQuery(name = "LocationMap.findByHeight", query = "SELECT l FROM LocationMap l WHERE l.height = :height"),
    @NamedQuery(name = "LocationMap.findByScale", query = "SELECT l FROM LocationMap l WHERE l.scale = :scale"),
    @NamedQuery(name = "LocationMap.findByZoom", query = "SELECT l FROM LocationMap l WHERE l.zoom = :zoom"),
    @NamedQuery(name = "LocationMap.findByImageFormat", query = "SELECT l FROM LocationMap l WHERE l.imageFormat = :imageFormat"),
    @NamedQuery(name = "LocationMap.findByMapType", query = "SELECT l FROM LocationMap l WHERE l.mapType = :mapType"),
    @NamedQuery(name = "LocationMap.findByCreatedDatetime", query = "SELECT l FROM LocationMap l WHERE l.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "LocationMap.findByUpdatedDatetime", query = "SELECT l FROM LocationMap l WHERE l.updatedDatetime = :updatedDatetime")})
public class LocationMap implements Serializable, ObservableEntity, SilentSetter {
    public static final String PROP_ACCURACY = "accuracy";
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
    @Column(name = "MARKER_COLOR")
    private String markerColor;
    @JoinColumn(name = "CONTACT_ID", referencedColumnName = "ID")
    @ManyToOne
    private Contact contactId;
    private static final long serialVersionUID = 1L;
    public static final String PROP_ID = "id";
    public static final String PROP_IMAGE = "image";
    public static final String PROP_LATITUDE = "latitude";
    public static final String PROP_LONGITUDE = "longitude";
    public static final String PROP_WIDTH = "width";
    public static final String PROP_HEIGHT = "height";
    public static final String PROP_SCALE = "scale";
    public static final String PROP_ZOOM = "zoom";
    public static final String PROP_IMAGEFORMAT = "imageFormat";
    public static final String PROP_MAPTYPE = "mapType";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_DIRECTIONMAPCOLLECTION = "directionCollection";
    public static final String PROP_CONTACTCOLLECTION = "contactCollection";
    public static final String PROP_SAVESTATE = "saveState";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "WIDTH")
    private Integer width;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "SCALE")
    private Integer scale;
    @Column(name = "ZOOM")
    private Integer zoom;
    @Column(name = "IMAGE_FORMAT")
    private String imageFormat;
    @Column(name = "MAP_TYPE")
    private String mapType;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "locationMapId")
    private Collection<DirectionMap> directionMapCollection;    
    @Transient
    private String saveState;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Column(name = "ACCURACY")  
    private Double accuracy;
    
    public LocationMap() {
        this.scale = 1;
        this.zoom = 16;
        this.mapType = "roadmap";
        this.imageFormat = "jpeg";
        this.width = 353;
        this.height = 353;
        this.saveState = "";
        this.createdDatetime = new Date();
        this.updatedDatetime = new Date();
        this.markerColor = "red";
        this.accuracy = 0.0d;
    }

    public LocationMap(Integer id) {
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
        if (!(object instanceof LocationMap)) {
            return false;
        }
        LocationMap other = (LocationMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.LocationMap[ id=" + id + " ]";
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
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        java.lang.Integer oldWidth = this.width;
        this.width = width;
        propertyChangeSupport.firePropertyChange(PROP_WIDTH, oldWidth, width);
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        java.lang.Integer oldHeight = this.height;
        this.height = height;
        propertyChangeSupport.firePropertyChange(PROP_HEIGHT, oldHeight, height);
    }

    /**
     * @return the scale
     */
    public Integer getScale() {
        return scale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(Integer scale) {
        java.lang.Integer oldScale = this.scale;
        this.scale = scale;
        propertyChangeSupport.firePropertyChange(PROP_SCALE, oldScale, scale);
    }

    /**
     * @return the zoom
     */
    public Integer getZoom() {
        return zoom;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(Integer zoom) {
        java.lang.Integer oldZoom = this.zoom;
        this.zoom = zoom;
        propertyChangeSupport.firePropertyChange(PROP_ZOOM, oldZoom, zoom);
    }

    /**
     * @return the imageFormat
     */
    public String getImageFormat() {
        return imageFormat;
    }

    /**
     * @param imageFormat the imageFormat to set
     */
    public void setImageFormat(String imageFormat) {
        java.lang.String oldImageFormat = this.imageFormat;
        this.imageFormat = imageFormat;
        propertyChangeSupport.firePropertyChange(PROP_IMAGEFORMAT, oldImageFormat, imageFormat);
    }

    /**
     * @return the mapType
     */
    public String getMapType() {
        return mapType;
    }

    /**
     * @param mapType the mapType to set
     */
    public void setMapType(String mapType) {
        java.lang.String oldMapType = this.mapType;
        this.mapType = mapType;
        propertyChangeSupport.firePropertyChange(PROP_MAPTYPE, oldMapType, mapType);
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

    public String getMarkerColor() {
        return markerColor;
    }

    public void setMarkerColor(String markerColor) {
        String oldMarkerColor = this.markerColor;
        this.markerColor = markerColor;
        propertyChangeSupport.firePropertyChange("markerColor", oldMarkerColor, markerColor);
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        Contact oldContactId = this.contactId;
        this.contactId = contactId;
        propertyChangeSupport.firePropertyChange("contactId", oldContactId, contactId);
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        byte[] oldImage = this.image;
        this.image = image;
        propertyChangeSupport.firePropertyChange("image", oldImage, image);
    }
 
    public String getName(){ return ""; }

    @Override
    public boolean hasDependentEntities() {
        return !getDirectionMapCollection().isEmpty();
    }

    @Override
    public boolean isMissingRequiredFields() {
        return  this.latitude == null ||
                this.longitude == null ||
                this.width == null ||
                this.height == null ||
                this.width.intValue() < 200 ||
                this.height.intValue() < 200;
    }

    @Override
    public String getImplementingClassName() {
        return "Location.Map";
    }

    /**
     * @return the accuracy
     */
    public Double getAccuracy() {
        return accuracy;
    }

    /**
     * @param accuracy the accuracy to set
     */
    public void setAccuracy(Double accuracy) {
        java.lang.Double oldAccuracy = this.accuracy;
        this.accuracy = accuracy;
        propertyChangeSupport.firePropertyChange(PROP_ACCURACY, oldAccuracy, accuracy);
    }
}
