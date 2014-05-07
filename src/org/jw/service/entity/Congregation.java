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
public class Congregation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "ADDRESS_1", length = 100)
    private String address1;
    @Column(name = "ADDRESS_2", length = 100)
    private String address2;
    @Column(name = "CITY", length = 50)
    private String city;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "congregationId")
    private Collection<ServiceGroup> serviceGroupCollection;

    public Congregation() {
    }

    public Congregation(Integer id) {
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
    public Collection<ServiceGroup> getServiceGroupCollection() {
        return serviceGroupCollection;
    }

    public void setServiceGroupCollection(Collection<ServiceGroup> serviceGroupCollection) {
        this.serviceGroupCollection = serviceGroupCollection;
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
    
}
