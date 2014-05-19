/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Wilson
 */
@Entity
@Table(name = "APPS_REPORT", catalog = "PUBLIC", schema = "CIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppsReport.findAll", query = "SELECT a FROM AppsReport a"),
    @NamedQuery(name = "AppsReport.findById", query = "SELECT a FROM AppsReport a WHERE a.id = :id"),
    @NamedQuery(name = "AppsReport.findByName", query = "SELECT a FROM AppsReport a WHERE a.name = :name"),
    @NamedQuery(name = "AppsReport.findByDescription", query = "SELECT a FROM AppsReport a WHERE a.description = :description"),
    @NamedQuery(name = "AppsReport.findByReportDate", query = "SELECT a FROM AppsReport a WHERE a.reportDate = :reportDate"),
    @NamedQuery(name = "AppsReport.findByFileName", query = "SELECT a FROM AppsReport a WHERE a.fileName = :fileName"),
    @NamedQuery(name = "AppsReport.findByFileModifiedDatetime", query = "SELECT a FROM AppsReport a WHERE a.fileModifiedDatetime = :fileModifiedDatetime"),
    @NamedQuery(name = "AppsReport.findByFileCreatedDatetime", query = "SELECT a FROM AppsReport a WHERE a.fileCreatedDatetime = :fileCreatedDatetime"),
    @NamedQuery(name = "AppsReport.findByQuery", query = "SELECT a FROM AppsReport a WHERE a.query = :query"),
    @NamedQuery(name = "AppsReport.findByEnable", query = "SELECT a FROM AppsReport a WHERE a.enable = :enable"),
    @NamedQuery(name = "AppsReport.findByVisible", query = "SELECT a FROM AppsReport a WHERE a.visible = :visible"),
    @NamedQuery(name = "AppsReport.findByCreatedDatetime", query = "SELECT a FROM AppsReport a WHERE a.createdDatetime = :createdDatetime"),
    @NamedQuery(name = "AppsReport.findByUpdatedDatetime", query = "SELECT a FROM AppsReport a WHERE a.updatedDatetime = :updatedDatetime")})
public class AppsReport implements Serializable, ObservableEntity, SilentSetter {
    private static final long serialVersionUID = 1L;
    public static final String PROP_CODE = "code";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CODE")
    private String code;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "REPORT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    @Lob
    @Column(name = "FILE_JASPER")
    private byte[] fileJasper;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "FILE_MODIFIED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileModifiedDatetime;
    @Column(name = "FILE_CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fileCreatedDatetime;
    @Column(name = "QUERY")
    private String query;
    @Column(name = "REPORT_TYPE")
    private String reportType;
    @Column(name = "LINE_LIMIT")
    private Integer lineLimit;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "VISIBLE")
    private Boolean visible;
    @Column(name = "CREATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDatetime;
    @Column(name = "UPDATED_DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDatetime;
    @OneToMany(mappedBy = "reportId")
    private Collection<AppsReportParameter> appsReportParameterCollection;
    @Transient
    private String saveState;

    public AppsReport() {
        this.appsReportParameterCollection = new ArrayList<AppsReportParameter>();
        this.createdDatetime = new Date();
        this.description = "";
        this.enable = true;
        this.fileCreatedDatetime = null;
        this.fileJasper = null;
        this.fileModifiedDatetime = null;
        this.fileName = "";
        this.name = "";
        this.query = "";
        this.reportDate = new Date();
        this.updatedDatetime = new Date();
    }

    public AppsReport(Integer id) {
        super();
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        java.lang.String oldTitle = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange(PROP_TITLE, oldTitle, title);
    }

    /**
     * @return the visible
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(Boolean visible) {
        java.lang.Boolean oldVisible = this.visible;
        this.visible = visible;
        propertyChangeSupport.firePropertyChange(PROP_VISIBLE, oldVisible, visible);
    }
    public static final String PROP_TITLE = "title";    
    public static final String PROP_VISIBLE = "visible";

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the reportDate
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @return the fileJasper
     */
    public byte[] getFileJasper() {
        return fileJasper;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the fileModifiedDatetime
     */
    public Date getFileModifiedDatetime() {
        return fileModifiedDatetime;
    }

    /**
     * @return the fileCreatedDatetime
     */
    public Date getFileCreatedDatetime() {
        return fileCreatedDatetime;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @return the createdDatetime
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * @return the updatedDatetime
     */
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    /**
     * @return the appsReportParameterCollection
     */
    @XmlTransient
    public Collection<AppsReportParameter> getAppsReportParameterCollection() {
        return appsReportParameterCollection;
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_ID = "id";

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        java.lang.Integer oldId = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange(PROP_ID, oldId, id);
    }
    public static final String PROP_NAME = "name";

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        java.lang.String oldName = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange(PROP_NAME, oldName, name);
    }
    public static final String PROP_DESCRIPTION = "description";

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        java.lang.String oldDescription = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange(PROP_DESCRIPTION, oldDescription, description);
    }
    public static final String PROP_REPORTDATE = "reportDate";

    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(Date reportDate) {
        java.util.Date oldReportDate = this.reportDate;
        this.reportDate = reportDate;
        propertyChangeSupport.firePropertyChange(PROP_REPORTDATE, oldReportDate, reportDate);
    }
    public static final String PROP_FILEJASPER = "fileJasper";

    /**
     * @param fileJasper the fileJasper to set
     */
    public void setFileJasper(byte[] fileJasper) {
        byte[] oldFileJasper = this.fileJasper;
        this.fileJasper = fileJasper;
        propertyChangeSupport.firePropertyChange(PROP_FILEJASPER, oldFileJasper, fileJasper);
    }
    public static final String PROP_FILENAME = "fileName";

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        java.lang.String oldFileName = this.fileName;
        this.fileName = fileName;
        propertyChangeSupport.firePropertyChange(PROP_FILENAME, oldFileName, fileName);
    }
    public static final String PROP_FILEMODIFIEDDATETIME = "fileModifiedDatetime";

    /**
     * @param fileModifiedDatetime the fileModifiedDatetime to set
     */
    public void setFileModifiedDatetime(Date fileModifiedDatetime) {
        java.util.Date oldFileModifiedDatetime = this.fileModifiedDatetime;
        this.fileModifiedDatetime = fileModifiedDatetime;
        propertyChangeSupport.firePropertyChange(PROP_FILEMODIFIEDDATETIME, oldFileModifiedDatetime, fileModifiedDatetime);
    }
    public static final String PROP_FILECREATEDDATETIME = "fileCreatedDatetime";

    /**
     * @param fileCreatedDatetime the fileCreatedDatetime to set
     */
    public void setFileCreatedDatetime(Date fileCreatedDatetime) {
        java.util.Date oldFileCreatedDatetime = this.fileCreatedDatetime;
        this.fileCreatedDatetime = fileCreatedDatetime;
        propertyChangeSupport.firePropertyChange(PROP_FILECREATEDDATETIME, oldFileCreatedDatetime, fileCreatedDatetime);
    }
    public static final String PROP_QUERY = "query";

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        java.lang.String oldQuery = this.query;
        this.query = query;
        propertyChangeSupport.firePropertyChange(PROP_QUERY, oldQuery, query);
    }
    public static final String PROP_ENABLE = "enable";

    /**
     * @param enable the enable to set
     */
    public void setEnable(Boolean enable) {
        java.lang.Boolean oldEnable = this.enable;
        this.enable = enable;
        propertyChangeSupport.firePropertyChange(PROP_ENABLE, oldEnable, enable);
    }
    public static final String PROP_CREATEDDATETIME = "createdDatetime";

    /**
     * @param createdDatetime the createdDatetime to set
     */
    public void setCreatedDatetime(Date createdDatetime) {
        java.util.Date oldCreatedDatetime = this.createdDatetime;
        this.createdDatetime = createdDatetime;
        propertyChangeSupport.firePropertyChange(PROP_CREATEDDATETIME, oldCreatedDatetime, createdDatetime);
    }
    public static final String PROP_UPDATEDDATETIME = "updatedDatetime";

    /**
     * @param updatedDatetime the updatedDatetime to set
     */
    public void setUpdatedDatetime(Date updatedDatetime) {
        java.util.Date oldUpdatedDatetime = this.updatedDatetime;
        this.updatedDatetime = updatedDatetime;
        propertyChangeSupport.firePropertyChange(PROP_UPDATEDDATETIME, oldUpdatedDatetime, updatedDatetime);
    }
    public static final String PROP_APPSREPORTPARAMETERCOLLECTION = "appsReportParameterCollection";

    /**
     * @param appsReportParameterCollection the appsReportParameterCollection to set
     */
    public void setAppsReportParameterCollection(Collection<AppsReportParameter> appsReportParameterCollection) {
        java.util.Collection<org.jw.service.entity.AppsReportParameter> oldAppsReportParameterCollection = this.appsReportParameterCollection;
        this.appsReportParameterCollection = appsReportParameterCollection;
        propertyChangeSupport.firePropertyChange(PROP_APPSREPORTPARAMETERCOLLECTION, oldAppsReportParameterCollection, appsReportParameterCollection);
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
        if (!(object instanceof AppsReport)) {
            return false;
        }
        AppsReport other = (AppsReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.jw.service.entity.AppsReport[ id=" + id + " ]";
        return this.getName();
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
        propertyChangeSupport.firePropertyChange("saveState", oldSaveState, saveState);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(name, listener);
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }   
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.removePropertyChangeListener(listener);
    }   
    
    @Override
    public void silentSetProperty(String name, Object value) {
        switch(name){
            case "updatedDatetime" : this.updatedDatetime = (Date) value; break;
            default : throw new UnsupportedOperationException("Property not Supported: " + name);
        }
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        java.lang.String oldCode = this.code;
        this.code = code;
        propertyChangeSupport.firePropertyChange(PROP_CODE, oldCode, code);
    }

    /**
     * @return the lineLimit
     */
    public Integer getLineLimit() {
        return lineLimit;
    }

    /**
     * @param lineLimit the lineLimit to set
     */
    public void setLineLimit(Integer lineLimit) {
        Integer oldLineLimit = this.lineLimit;
        this.lineLimit = lineLimit;
        propertyChangeSupport.firePropertyChange("lineLimit", oldLineLimit, lineLimit);
    }

    /**
     * @return the reportType
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * @param reportType the reportType to set
     */
    public void setReportType(String reportType) {
        String oldReportType = this.reportType;
        this.reportType = reportType;
        propertyChangeSupport.firePropertyChange("reportType", oldReportType, reportType);
    }
    
}
