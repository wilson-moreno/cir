/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.pojo;

import java.beans.PropertyChangeSupport;

/**
 *
 * @author Wilson
 */
public class ReportCode {
    public static final String PROP_TEMPLATENAME = "templateName";
    public static final String PROP_TEMPLATEVERSION = "templateVersion";
    public static final String PROP_PAGEORDER = "pageOrder";
    public static final String PROP_PAGETOTAL = "pageTotal";
    private String templateName;
    private String templateVersion;
    private String pageOrder;
    private String pageTotal;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    public ReportCode(){}
    
    public String toString(){
        return templateName + templateVersion + pageOrder + pageTotal;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        java.lang.String oldTemplateName = this.templateName;
        this.templateName = templateName;
        propertyChangeSupport.firePropertyChange(PROP_TEMPLATENAME, oldTemplateName, templateName);
    }

    /**
     * @return the templateVersion
     */
    public String getTemplateVersion() {
        return templateVersion;
    }

    /**
     * @param templateVersion the templateVersion to set
     */
    public void setTemplateVersion(String templateVersion) {
        java.lang.String oldTemplateVersion = this.templateVersion;
        this.templateVersion = templateVersion;
        propertyChangeSupport.firePropertyChange(PROP_TEMPLATEVERSION, oldTemplateVersion, templateVersion);
    }

    /**
     * @return the pageOrder
     */
    public String getPageOrder() {
        return pageOrder;
    }

    /**
     * @param pageOrder the pageOrder to set
     */
    public void setPageOrder(String pageOrder) {
        java.lang.String oldPageOrder = this.pageOrder;
        this.pageOrder = pageOrder;
        propertyChangeSupport.firePropertyChange(PROP_PAGEORDER, oldPageOrder, pageOrder);
    }

    /**
     * @return the pageTotal
     */
    public String getPageTotal() {
        return pageTotal;
    }

    /**
     * @param pageTotal the pageTotal to set
     */
    public void setPageTotal(String pageTotal) {
        java.lang.String oldPageTotal = this.pageTotal;
        this.pageTotal = pageTotal;
        propertyChangeSupport.firePropertyChange(PROP_PAGETOTAL, oldPageTotal, pageTotal);
    }
}
