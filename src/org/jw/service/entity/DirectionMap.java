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
import javax.persistence.Lob;
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
public class DirectionMap implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @ManyToOne
    private LocationMap locationMapId;

    public DirectionMap() {
    }

    public DirectionMap(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getPathColor() {
        return pathColor;
    }

    public void setPathColor(String pathColor) {
        this.pathColor = pathColor;
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

    public MeetingPlace getMeetingPlaceId() {
        return meetingPlaceId;
    }

    public void setMeetingPlaceId(MeetingPlace meetingPlaceId) {
        this.meetingPlaceId = meetingPlaceId;
    }

    public LocationMap getLocationMapId() {
        return locationMapId;
    }

    public void setLocationMapId(LocationMap locationMapId) {
        this.locationMapId = locationMapId;
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
        if (!(object instanceof DirectionMap)) {
            return false;
        }
        DirectionMap other = (DirectionMap) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.DirectionMap[ id=" + id + " ]";
    }
    
}
