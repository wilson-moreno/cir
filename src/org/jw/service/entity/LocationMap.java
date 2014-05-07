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
public class LocationMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE", precision = 64)
    private Double latitude;
    @Column(name = "LONGITUDE", precision = 64)
    private Double longitude;
    @Column(name = "WIDTH")
    private Integer width;
    @Column(name = "HEIGHT")
    private Integer height;
    @Column(name = "SCALE")
    private Integer scale;
    @Column(name = "ZOOM")
    private Integer zoom;
    @Column(name = "IMAGE_FORMAT", length = 15)
    private String imageFormat;
    @Column(name = "MAP_TYPE", length = 15)
    private String mapType;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "locationMapId")
    private Collection<DirectionMap> directionMapCollection;
    @OneToMany(mappedBy = "locationMapId")
    private Collection<Contact> contactCollection;

    public LocationMap() {
    }

    public LocationMap(Integer id) {
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
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
    public Collection<DirectionMap> getDirectionMapCollection() {
        return directionMapCollection;
    }

    public void setDirectionMapCollection(Collection<DirectionMap> directionMapCollection) {
        this.directionMapCollection = directionMapCollection;
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
    
}
