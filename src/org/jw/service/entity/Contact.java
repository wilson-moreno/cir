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
    @NamedQuery(name = "Contact.findByNationaltiy", query = "SELECT c FROM Contact c WHERE c.nationality = :nationality"),
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
public class Contact implements Serializable, ObservableEntity {
    private static final long serialVersionUID = 1L;
    public static final String PROP_GUARDIANSNAME = "guardiansName";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "RECORD_NUMBER")
    private String recordNumber;
    @Column(name = "RECORD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate;
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "NICK_NAME")
    private String nickName;
    @Column(name = "SEX")
    private String sex;
    @Column(name = "MARITAL_STATUS")
    private String maritalStatus;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Lob
    @Column(name = "PROFILE_PICTURE")
    private byte[] profilePicture;
    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;
    @Column(name = "STREET")
    private String street;
    @Column(name = "BARANGAY")
    private String barangay;
    @Column(name = "CITY")
    private String city;
    @Column(name = "AREA")
    private String area;
    @Column(name = "PERSONAL_BACKGROUND")
    private String personalBackground;
    @Column(name = "FAMILY_BACKGROUND")
    private String familyBackground;
    @Column(name = "WORK_BACKGROUND")
    private String workBackground;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "SKYPE_ACCOUNT")
    private String skypeAccount;
    @Column(name = "FACEBOOK_ACCOUNT")
    private String facebookAccount;
    @Column(name = "FATHERS_NAME")
    private String fathersName;
    @Column(name = "MOTHERS_NAME")
    private String mothersName;
    @Column(name = "GUARDIANS_NAME")
    private String guardiansName;    
    @Column(name = "RELIGION")
    private String religion;
    @Column(name = "FOUND_BY")
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
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    @Transient
    private String saveState;
    
    public Contact() {
        this.area = "";
        this.barangay = "";
        this.birthdate = null;
        this.city = "";
        this.contactCallCollection = null;
        this.createdDatetime = new Date();
        this.emailAddress = "";
        this.facebookAccount = "";
        this.familyBackground = "";
        this.fathersName = "";
        this.firstName = "";
        this.foundBy = "";
        this.guardiansName = "";
        this.houseNumber = "";
        this.lastName = "";
        this.locationMapId = null;
        this.maritalStatus = "";
        this.mobileNumber = "";
        this.mothersName = "";
        this.nationality = "";
        this.nickName = "";
        this.personalBackground = "";
        this.phoneNumber = "";
        this.profilePicture = null;
        this.recordDate = new Date();
        this.recordNumber = "";
        this.religion = "";
        this.saveState = "";
        this.serviceGroupId = null;
        this.sex = "";
        this.skypeAccount = "";
        this.statusId = null;
        this.street = "";
        this.updatedDatetime = new Date();
        this.workBackground = "";        
    }

    public Contact(Integer id) {
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
     * @return the recordNumber
     */
    public String getRecordNumber() {
        return recordNumber;
    }

    /**
     * @param recordNumber the recordNumber to set
     */
    public void setRecordNumber(String recordNumber) {
        java.lang.String oldRecordNumber = this.recordNumber;
        this.recordNumber = recordNumber;
        propertyChangeSupport.firePropertyChange(PROP_RECORDNUMBER, oldRecordNumber, recordNumber);
    }

    /**
     * @return the recordDate
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * @param recordDate the recordDate to set
     */
    public void setRecordDate(Date recordDate) {
        java.util.Date oldRecordDate = this.recordDate;
        this.recordDate = recordDate;
        propertyChangeSupport.firePropertyChange(PROP_RECORDDATE, oldRecordDate, recordDate);
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        java.util.Date oldBirthdate = this.birthdate;
        this.birthdate = birthdate;
        propertyChangeSupport.firePropertyChange(PROP_BIRTHDATE, oldBirthdate, birthdate);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        java.lang.String oldLastName = this.lastName;
        this.lastName = lastName;
        propertyChangeSupport.firePropertyChange(PROP_LASTNAME, oldLastName, lastName);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        java.lang.String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertyChangeSupport.firePropertyChange(PROP_FIRSTNAME, oldFirstName, firstName);
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        java.lang.String oldNickName = this.nickName;
        this.nickName = nickName;
        propertyChangeSupport.firePropertyChange(PROP_NICKNAME, oldNickName, nickName);
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        java.lang.String oldSex = this.sex;
        this.sex = sex;
        propertyChangeSupport.firePropertyChange(PROP_SEX, oldSex, sex);
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        java.lang.String oldMaritalStatus = this.maritalStatus;
        this.maritalStatus = maritalStatus;
        propertyChangeSupport.firePropertyChange(PROP_MARITALSTATUS, oldMaritalStatus, maritalStatus);
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationaltiy the nationaltiy to set
     */
    public void setNationality(String nationality) {
        java.lang.String oldNationality = this.nationality;
        this.nationality = nationality;
        propertyChangeSupport.firePropertyChange(PROP_NATIONALITY, oldNationality, nationality);
    }

    /**
     * @return the profilePicture
     */
    public byte[] getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profilePicture to set
     */
    public void setProfilePicture(byte[] profilePicture) {
        byte[] oldProfilePicture = this.profilePicture;
        this.profilePicture = profilePicture;
        propertyChangeSupport.firePropertyChange(PROP_PROFILEPICTURE, oldProfilePicture, profilePicture);
    }

    /**
     * @return the houseNumber
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * @param houseNumber the houseNumber to set
     */
    public void setHouseNumber(String houseNumber) {
        java.lang.String oldHouseNumber = this.houseNumber;
        this.houseNumber = houseNumber;
        propertyChangeSupport.firePropertyChange(PROP_HOUSENUMBER, oldHouseNumber, houseNumber);
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        java.lang.String oldStreet = this.street;
        this.street = street;
        propertyChangeSupport.firePropertyChange(PROP_STREET, oldStreet, street);
    }

    /**
     * @return the barangay
     */
    public String getBarangay() {
        return barangay;
    }

    /**
     * @param barangay the barangay to set
     */
    public void setBarangay(String barangay) {
        java.lang.String oldBarangay = this.barangay;
        this.barangay = barangay;
        propertyChangeSupport.firePropertyChange(PROP_BARANGAY, oldBarangay, barangay);
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
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        java.lang.String oldArea = this.area;
        this.area = area;
        propertyChangeSupport.firePropertyChange(PROP_AREA, oldArea, area);
    }

    /**
     * @return the personalBackground
     */
    public String getPersonalBackground() {
        return personalBackground;
    }

    /**
     * @param personalBackground the personalBackground to set
     */
    public void setPersonalBackground(String personalBackground) {
        java.lang.String oldPersonalBackground = this.personalBackground;
        this.personalBackground = personalBackground;
        propertyChangeSupport.firePropertyChange(PROP_PERSONALBACKGROUND, oldPersonalBackground, personalBackground);
    }

    /**
     * @return the familyBackground
     */
    public String getFamilyBackground() {
        return familyBackground;
    }

    /**
     * @param familyBackground the familyBackground to set
     */
    public void setFamilyBackground(String familyBackground) {
        java.lang.String oldFamilyBackground = this.familyBackground;
        this.familyBackground = familyBackground;
        propertyChangeSupport.firePropertyChange(PROP_FAMILYBACKGROUND, oldFamilyBackground, familyBackground);
    }

    /**
     * @return the workBackground
     */
    public String getWorkBackground() {
        return workBackground;
    }

    /**
     * @param workBackground the workBackground to set
     */
    public void setWorkBackground(String workBackground) {
        java.lang.String oldWorkBackground = this.workBackground;
        this.workBackground = workBackground;
        propertyChangeSupport.firePropertyChange(PROP_WORKBACKGROUND, oldWorkBackground, workBackground);
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        java.lang.String oldPhoneNumber = this.phoneNumber;
        this.phoneNumber = phoneNumber;
        propertyChangeSupport.firePropertyChange(PROP_PHONENUMBER, oldPhoneNumber, phoneNumber);
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        java.lang.String oldMobileNumber = this.mobileNumber;
        this.mobileNumber = mobileNumber;
        propertyChangeSupport.firePropertyChange(PROP_MOBILENUMBER, oldMobileNumber, mobileNumber);
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        java.lang.String oldEmailAddress = this.emailAddress;
        this.emailAddress = emailAddress;
        propertyChangeSupport.firePropertyChange(PROP_EMAILADDRESS, oldEmailAddress, emailAddress);
    }

    /**
     * @return the skypeAccount
     */
    public String getSkypeAccount() {
        return skypeAccount;
    }

    /**
     * @param skypeAccount the skypeAccount to set
     */
    public void setSkypeAccount(String skypeAccount) {
        java.lang.String oldSkypeAccount = this.skypeAccount;
        this.skypeAccount = skypeAccount;
        propertyChangeSupport.firePropertyChange(PROP_SKYPEACCOUNT, oldSkypeAccount, skypeAccount);
    }

    /**
     * @return the facebookAccount
     */
    public String getFacebookAccount() {
        return facebookAccount;
    }

    /**
     * @param facebookAccount the facebookAccount to set
     */
    public void setFacebookAccount(String facebookAccount) {
        java.lang.String oldFacebookAccount = this.facebookAccount;
        this.facebookAccount = facebookAccount;
        propertyChangeSupport.firePropertyChange(PROP_FACEBOOKACCOUNT, oldFacebookAccount, facebookAccount);
    }

    /**
     * @return the fathersName
     */
    public String getFathersName() {
        return fathersName;
    }

    /**
     * @param fathersName the fathersName to set
     */
    public void setFathersName(String fathersName) {
        java.lang.String oldFathersName = this.fathersName;
        this.fathersName = fathersName;
        propertyChangeSupport.firePropertyChange(PROP_FATHERSNAME, oldFathersName, fathersName);
    }

    /**
     * @return the mothersName
     */
    public String getMothersName() {
        return mothersName;
    }

    /**
     * @param mothersName the mothersName to set
     */
    public void setMothersName(String mothersName) {
        java.lang.String oldMothersName = this.mothersName;
        this.mothersName = mothersName;
        propertyChangeSupport.firePropertyChange(PROP_MOTHERSNAME, oldMothersName, mothersName);
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        java.lang.String oldReligion = this.religion;
        this.religion = religion;
        propertyChangeSupport.firePropertyChange(PROP_RELIGION, oldReligion, religion);
    }

    /**
     * @return the foundBy
     */
    public String getFoundBy() {
        return foundBy;
    }

    /**
     * @param foundBy the foundBy to set
     */
    public void setFoundBy(String foundBy) {
        java.lang.String oldFoundBy = this.foundBy;
        this.foundBy = foundBy;
        propertyChangeSupport.firePropertyChange(PROP_FOUNDBY, oldFoundBy, foundBy);
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
     * @return the contactCallCollection
     */
    @XmlTransient
    public Collection<ContactCall> getContactCallCollection() {
        return contactCallCollection;
    }

    /**
     * @param contactCallCollection the contactCallCollection to set
     */
    public void setContactCallCollection(Collection<ContactCall> contactCallCollection) {
        java.util.Collection<org.jw.service.entity.ContactCall> oldContactCallCollection = this.contactCallCollection;
        this.contactCallCollection = contactCallCollection;
        propertyChangeSupport.firePropertyChange(PROP_CONTACTCALLCOLLECTION, oldContactCallCollection, contactCallCollection);
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

    /**
     * @return the statusId
     */
    public ContactStatus getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(ContactStatus statusId) {
        org.jw.service.entity.ContactStatus oldStatusId = this.statusId;
        this.statusId = statusId;
        propertyChangeSupport.firePropertyChange(PROP_STATUSID, oldStatusId, statusId);
    }
    public static final String PROP_ID = "id";
    public static final String PROP_RECORDNUMBER = "recordNumber";
    public static final String PROP_RECORDDATE = "recordDate";
    public static final String PROP_BIRTHDATE = "birthdate";
    public static final String PROP_LASTNAME = "lastName";
    public static final String PROP_FIRSTNAME = "firstName";
    public static final String PROP_NICKNAME = "nickName";
    public static final String PROP_SEX = "sex";
    public static final String PROP_MARITALSTATUS = "maritalStatus";
    public static final String PROP_NATIONALITY = "nationality";
    public static final String PROP_PROFILEPICTURE = "profilePicture";
    public static final String PROP_HOUSENUMBER = "houseNumber";
    public static final String PROP_STREET = "street";
    public static final String PROP_BARANGAY = "barangay";
    public static final String PROP_CITY = "city";
    public static final String PROP_AREA = "area";
    public static final String PROP_PERSONALBACKGROUND = "personalBackground";
    public static final String PROP_FAMILYBACKGROUND = "familyBackground";
    public static final String PROP_WORKBACKGROUND = "workBackground";
    public static final String PROP_PHONENUMBER = "phoneNumber";
    public static final String PROP_MOBILENUMBER = "mobileNumber";
    public static final String PROP_EMAILADDRESS = "emailAddress";
    public static final String PROP_SKYPEACCOUNT = "skypeAccount";
    public static final String PROP_FACEBOOKACCOUNT = "facebookAccount";
    public static final String PROP_FATHERSNAME = "fathersName";
    public static final String PROP_MOTHERSNAME = "mothersName";
    public static final String PROP_RELIGION = "religion";
    public static final String PROP_FOUNDBY = "foundBy";
    public static final String PROP_CREATEDDATETIME = "createdDatetime";
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";
    public static final String PROP_CONTACTCALLCOLLECTION = "contactCallCollection";
    public static final String PROP_SERVICEGROUPID = "serviceGroupId";
    public static final String PROP_LOCATIONMAPID = "locationMapId";
    public static final String PROP_STATUSID = "statusId";
    public static final String PROP_SAVESTATE = "saveState";

    

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

    /**
     * @return the guardiansName
     */
    public String getGuardiansName() {
        return guardiansName;
    }

    /**
     * @param guardiansName the guardiansName to set
     */
    public void setGuardiansName(String guardiansName) {
        java.lang.String oldGuardiansName = this.guardiansName;
        this.guardiansName = guardiansName;
        propertyChangeSupport.firePropertyChange(PROP_GUARDIANSNAME, oldGuardiansName, guardiansName);
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
    
}
