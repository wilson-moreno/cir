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
import javax.persistence.Lob;
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
@Table(name = "CONTACT", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
    @NamedQuery(name = "Contact.findByRecordNumber", query = "SELECT c FROM Contact c WHERE c.recordNumber = :recordNumber"),
    @NamedQuery(name = "Contact.findByRecordDate", query = "SELECT c FROM Contact c WHERE c.recordDate = :recordDate"),
    @NamedQuery(name = "Contact.findByBirthdate", query = "SELECT c FROM Contact c WHERE c.birthdate = :birthdate"),
    @NamedQuery(name = "Contact.findByLastName", query = "SELECT c FROM Contact c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Contact.findByFirstName", query = "SELECT c FROM Contact c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Contact.findByNickName", query = "SELECT c FROM Contact c WHERE c.nickName = :nickName"),
    @NamedQuery(name = "Contact.findBySex", query = "SELECT c FROM Contact c WHERE c.sex = :sex"),
    @NamedQuery(name = "Contact.findByMaritalStatus", query = "SELECT c FROM Contact c WHERE c.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "Contact.findByNationaltiy", query = "SELECT c FROM Contact c WHERE c.nationaltiy = :nationaltiy"),
    @NamedQuery(name = "Contact.findByHouseNumber", query = "SELECT c FROM Contact c WHERE c.houseNumber = :houseNumber"),
    @NamedQuery(name = "Contact.findByStreet", query = "SELECT c FROM Contact c WHERE c.street = :street"),
    @NamedQuery(name = "Contact.findByBarangay", query = "SELECT c FROM Contact c WHERE c.barangay = :barangay"),
    @NamedQuery(name = "Contact.findByCity", query = "SELECT c FROM Contact c WHERE c.city = :city"),
    @NamedQuery(name = "Contact.findByArea", query = "SELECT c FROM Contact c WHERE c.area = :area"),
    @NamedQuery(name = "Contact.findByPersonalBackground", query = "SELECT c FROM Contact c WHERE c.personalBackground = :personalBackground"),
    @NamedQuery(name = "Contact.findByFamilyBackground", query = "SELECT c FROM Contact c WHERE c.familyBackground = :familyBackground"),
    @NamedQuery(name = "Contact.findByWorkBackground", query = "SELECT c FROM Contact c WHERE c.workBackground = :workBackground"),
    @NamedQuery(name = "Contact.findByPhoneNumber", query = "SELECT c FROM Contact c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Contact.findByMobileNumber", query = "SELECT c FROM Contact c WHERE c.mobileNumber = :mobileNumber"),
    @NamedQuery(name = "Contact.findByEmailAddress", query = "SELECT c FROM Contact c WHERE c.emailAddress = :emailAddress"),
    @NamedQuery(name = "Contact.findBySkypeAccount", query = "SELECT c FROM Contact c WHERE c.skypeAccount = :skypeAccount"),
    @NamedQuery(name = "Contact.findByFacebookAccount", query = "SELECT c FROM Contact c WHERE c.facebookAccount = :facebookAccount"),
    @NamedQuery(name = "Contact.findByFathersName", query = "SELECT c FROM Contact c WHERE c.fathersName = :fathersName"),
    @NamedQuery(name = "Contact.findByMothersName", query = "SELECT c FROM Contact c WHERE c.mothersName = :mothersName"),
    @NamedQuery(name = "Contact.findByReligion", query = "SELECT c FROM Contact c WHERE c.religion = :religion"),
    @NamedQuery(name = "Contact.findByFoundBy", query = "SELECT c FROM Contact c WHERE c.foundBy = :foundBy"),
    @NamedQuery(name = "Contact.findByCreatedDatetime", query = "SELECT c FROM Contact c WHERE c.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "Contact.findByUpdatedDatetime", query = "SELECT c FROM Contact c WHERE c.updatedDatetime = :updatedDatetime")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "RECORD_NUMBER", length = 15)
    private String recordNumber;
    @Column(name = "RECORD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;
    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;
    @Column(name = "NICK_NAME", length = 30)
    private String nickName;
    @Column(name = "SEX", length = 6)
    private String sex;
    @Column(name = "MARITAL_STATUS", length = 15)
    private String maritalStatus;
    @Column(name = "NATIONALTIY", length = 30)
    private String nationaltiy;
    @Lob
    @Column(name = "PROFILE_PICTURE")
    private byte[] profilePicture;
    @Column(name = "HOUSE_NUMBER", length = 15)
    private String houseNumber;
    @Column(name = "STREET", length = 200)
    private String street;
    @Column(name = "BARANGAY", length = 20)
    private String barangay;
    @Column(name = "CITY", length = 50)
    private String city;
    @Column(name = "AREA", length = 50)
    private String area;
    @Column(name = "PERSONAL_BACKGROUND", length = 150)
    private String personalBackground;
    @Column(name = "FAMILY_BACKGROUND", length = 150)
    private String familyBackground;
    @Column(name = "WORK_BACKGROUND", length = 150)
    private String workBackground;
    @Column(name = "PHONE_NUMBER", length = 50)
    private String phoneNumber;
    @Column(name = "MOBILE_NUMBER", length = 50)
    private String mobileNumber;
    @Column(name = "EMAIL_ADDRESS", length = 50)
    private String emailAddress;
    @Column(name = "SKYPE_ACCOUNT", length = 50)
    private String skypeAccount;
    @Column(name = "FACEBOOK_ACCOUNT", length = 50)
    private String facebookAccount;
    @Column(name = "FATHERS_NAME", length = 50)
    private String fathersName;
    @Column(name = "MOTHERS_NAME", length = 50)
    private String mothersName;
    @Column(name = "RELIGION", length = 50)
    private String religion;
    @Column(name = "FOUND_BY", length = 50)
    private String foundBy;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "contactId")
    private Collection<ContactCall> contactCallCollection;
    @JoinColumn(name = "SERVICE_GROUP_ID", referencedColumnName = "ID")
    @ManyToOne
    private ServiceGroup serviceGroupId;
    @JoinColumn(name = "LOCATION_MAP_ID", referencedColumnName = "ID")
    @ManyToOne
    private LocationMap locationMapId;
    @JoinColumn(name = "STATUS_ID", referencedColumnName = "ID")
    @ManyToOne
    private ContactStatus statusId;

    public Contact() {
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationaltiy() {
        return nationaltiy;
    }

    public void setNationaltiy(String nationaltiy) {
        this.nationaltiy = nationaltiy;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonalBackground() {
        return personalBackground;
    }

    public void setPersonalBackground(String personalBackground) {
        this.personalBackground = personalBackground;
    }

    public String getFamilyBackground() {
        return familyBackground;
    }

    public void setFamilyBackground(String familyBackground) {
        this.familyBackground = familyBackground;
    }

    public String getWorkBackground() {
        return workBackground;
    }

    public void setWorkBackground(String workBackground) {
        this.workBackground = workBackground;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSkypeAccount() {
        return skypeAccount;
    }

    public void setSkypeAccount(String skypeAccount) {
        this.skypeAccount = skypeAccount;
    }

    public String getFacebookAccount() {
        return facebookAccount;
    }

    public void setFacebookAccount(String facebookAccount) {
        this.facebookAccount = facebookAccount;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getFoundBy() {
        return foundBy;
    }

    public void setFoundBy(String foundBy) {
        this.foundBy = foundBy;
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
    public Collection<ContactCall> getContactCallCollection() {
        return contactCallCollection;
    }

    public void setContactCallCollection(Collection<ContactCall> contactCallCollection) {
        this.contactCallCollection = contactCallCollection;
    }

    public ServiceGroup getServiceGroupId() {
        return serviceGroupId;
    }

    public void setServiceGroupId(ServiceGroup serviceGroupId) {
        this.serviceGroupId = serviceGroupId;
    }

    public LocationMap getLocationMapId() {
        return locationMapId;
    }

    public void setLocationMapId(LocationMap locationMapId) {
        this.locationMapId = locationMapId;
    }

    public ContactStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(ContactStatus statusId) {
        this.statusId = statusId;
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
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.jw.service.entity.Contact[ id=" + id + " ]";
    }
    
}
