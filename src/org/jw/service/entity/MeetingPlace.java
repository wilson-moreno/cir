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
public class MeetingPlace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "ADDRESS", length = 100)
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE", precision = 64)
    private Double latitude;
    @Column(name = "LONGITUDE", precision = 64)
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

    public MeetingPlace() {
    }

    public MeetingPlace(Integer id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ServiceGroup getServiceGroupId() {
        return serviceGroupId;
    }

    public void setServiceGroupId(ServiceGroup serviceGroupId) {
        this.serviceGroupId = serviceGroupId;
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
        return "org.jw.service.entity.MeetingPlace[ id=" + id + " ]";
    }
    
}
