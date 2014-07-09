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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "DIRECTION_MAP", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirectionMap.findAll", query = "SELECT d FROM DirectionMap d"),
    @NamedQuery(name = "DirectionMap.findById", query = "SELECT d FROM DirectionMap d WHERE d.id = :id"),
    @NamedQuery(name = "DirectionMap.findByDirection", query = "SELECT d FROM DirectionMap d WHERE d.direction = :direction"),
    @NamedQuery(name = "DirectionMap.findByTravelMode", query = "SELECT d FROM DirectionMap d WHERE d.travelMode = :travelMode"),
    @NamedQuery(name = "DirectionMap.findByWidth", query = "SELECT d FROM DirectionMap d WHERE d.width = :width"),
    @NamedQuery(name = "DirectionMap.findByHeight", query = "SELECT d FROM DirectionMap d WHERE d.height = :height"),
    @NamedQuery(name = "DirectionMap.findByZoom", query = "SELECT d FROM DirectionMap d WHERE d.zoom = :zoom"),
    @NamedQuery(name = "DirectionMap.findByScale", query = "SELECT d FROM DirectionMap d WHERE d.scale = :scale"),
    @NamedQuery(name = "DirectionMap.findByPathColor", query = "SELECT d FROM DirectionMap d WHERE d.pathColor = :pathColor"),
    @NamedQuery(name = "DirectionMap.findByCreatedDatetime", query = "SELECT d FROM DirectionMap d WHERE d.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "DirectionMap.findByUpdatedDatetime", query = "SELECT d FROM DirectionMap d WHERE d.updatedDatetime = :updatedDatetime")})
public class DirectionMap implements Serializable, ObservableEntity, SilentSetter {
    private static final long serialVersionUID = 1L;    
    public static final String PROP_ID = "id";
    public static final String PROP_IMAGE = "image";
    public static final String PROP_DIRECTION = "direction";
    public static final String PROP_TRAVELMODE = "travelMode";
    public static final String PROP_WIDTH = "width";
    public static final String PROP_HEIGHT = "height";
    public static final String PROP_ZOOM = "zoom";
    public static final String PROP_SCALE = "scale";
    public static final String PROP_PATHCOLOR = "pathColor";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_MEETINGPLACEID = "meetingPlaceId";
    public static final String PROP_LOCATIONMAPID = "locationMapId";

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
    @Column(name = "DIRECTION")
    private String direction;
    @Column(name = "TRAVEL_MODE")
    private String travelMode;
    @Column(name = "WIDTH")
    private Integer width;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "ZOOM")
    private Integer zoom;
    @Column(name = "SCALE")
    private Integer scale;
    @Column(name = "PATH_COLOR")
    private String pathColor;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @JoinColumn(name = "MEETING_PLACE_ID", referencedColumnName = "ID")
    @ManyToOne
    private MeetingPlace meetingPlaceId;
    @JoinColumn(name = "LOCATION_MAP_ID", referencedColumnName = "ID")
    @OneToOne
    private LocationMap locationMapId;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Transient
    private String saveState;
    
    
    public DirectionMap() {
        this.createdDatetime = new Date();
        this.direction = "";
        this.height = 248;
        this.image = null;
        this.locationMapId = null;
        this.meetingPlaceId = null;
        this.pathColor = "blue";
        this.scale = 1;
        this.travelMode = "driving";
        this.updatedDatetime = new Date();
        this.width = 522;
        this.zoom = 14;
        this.saveState = "";
    }

    public DirectionMap(Integer id) {
        super();
        this.id = id;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirectionMap)) {
            return false;
        }
        DirectionMap other = (DirectionMap) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.DirectionMap[ id=" + getId() + " ]";
    }

    @Override
    public String getSaveState() {
        return saveState;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getImplementingClassName() {
        return "Direction.Map";
    }

    @Override
    public boolean hasDependentEntities() {
        return false;
    }

    @Override
    public boolean isMissingRequiredFields() {
        return this.locationMapId == null ||
               this.meetingPlaceId == null ||
               this.pathColor.trim().equals("") ||
               this.travelMode.trim().equals("");
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
            case "updatedDatetime" : this.setUpdatedDatetime((Date) value); break;
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
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        byte[] oldImage = this.image;
        this.image = image;
        propertyChangeSupport.firePropertyChange(PROP_IMAGE, oldImage, image);
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
        propertyChangeSupport.firePropertyChange(PROP_DIRECTION, oldDirection, direction);
    }

    /**
     * @return the travelMode
     */
    public String getTravelMode() {
        return travelMode;
    }

    /**
     * @param travelMode the travelMode to set
     */
    public void setTravelMode(String travelMode) {
        java.lang.String oldTravelMode = this.travelMode;
        this.travelMode = travelMode;
        propertyChangeSupport.firePropertyChange(PROP_TRAVELMODE, oldTravelMode, travelMode);
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
     * @return the pathColor
     */
    public String getPathColor() {
        return pathColor;
    }

    /**
     * @param pathColor the pathColor to set
     */
    public void setPathColor(String pathColor) {
        java.lang.String oldPathColor = this.pathColor;
        this.pathColor = pathColor;
        propertyChangeSupport.firePropertyChange(PROP_PATHCOLOR, oldPathColor, pathColor);
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
     * @return the locationMapId
     */
    public LocationMap getLocationMapId() {
        return locationMapId;
    }

    /**
     * @param locationMapId the locationMapId to set
     */
    public void setLocationMapId(LocationMap locationMapId) {
        org.jw.service.entity.LocationMap oldLocationMapId = this.locationMapId;
        this.locationMapId = locationMapId;
        propertyChangeSupport.firePropertyChange(PROP_LOCATIONMAPID, oldLocationMapId, locationMapId);
    }
    
}
