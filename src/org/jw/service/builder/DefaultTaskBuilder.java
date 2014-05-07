/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.builder;

import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JTable;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.gui.component.CRUDPanel;
import org.jw.service.gui.component.TaskMonitorPanel;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityProperties;

/**
 *
 * @author Wilson
 */
public class DefaultTaskBuilder {
    public static final String PROP_CRUDPANEL = "PROP_CRUDPANEL";
    public static final String PROP_TASKMONITORPANEL = "PROP_TASKMONITORPANEL";
    public static final String PROP_UTILITYPROPERTIES = "PROP_UTILITYPROPERTIES";
    public static final String PROP_ENTITYNAME = "PROP_ENTITYNAME";
    public static final String PROP_NEWACTION = "PROP_NEWACTION";
    public static final String PROP_DELETEACTION = "PROP_DELETEACTION";
    public static final String PROP_REFRESHACTION = "PROP_REFRESHACTION";
    public static final String PROP_SAVEACTION = "PROP_SAVEACTION";
    public static final String PROP_LIST = "PROP_LIST";
    public static final String PROP_TABLE = "PROP_TABLE";
    public static final String PROP_DIALOG = "PROP_DIALOG";
    public static final String PROP_CLOSEACTION = "PROP_CLOSEACTION";
    public static final String PROP_DAO = "PROP_DAO";
    private CRUDPanel crudPanel;
    private TaskMonitorPanel taskMonitorPanel;
    private UtilityProperties properties;
    private String entityName;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    private DefaultNewAction newAction;
    private DefaultDeleteAction deleteAction;
    private DefaultRefreshAction refreshAction;
    private DefaultSaveAction saveAction;
    private DefaultCloseAction closeAction;
    private List list;
    private JTable table;
    private JDialog dialog;
    private DataAccessObject dao;
    
    public DefaultTaskBuilder(){
        this.crudPanel = null;
        this.taskMonitorPanel = null;
        this.properties = null;
        this.entityName = "";
    }

    /**
     * @param crudPanel the crudPanel to set
     */
    public void setCrudPanel(CRUDPanel crudPanel) {
        org.jw.service.gui.component.CRUDPanel oldCrudPanel = this.crudPanel;
        this.crudPanel = crudPanel;
        propertyChangeSupport.firePropertyChange(PROP_CRUDPANEL, oldCrudPanel, crudPanel);
    }

    /**
     * @param taskMonitorPanel the taskMonitorPanel to set
     */
    public void setTaskMonitorPanel(TaskMonitorPanel taskMonitorPanel) {
        org.jw.service.gui.component.TaskMonitorPanel oldTaskMonitorPanel = this.taskMonitorPanel;
        this.taskMonitorPanel = taskMonitorPanel;
        propertyChangeSupport.firePropertyChange(PROP_TASKMONITORPANEL, oldTaskMonitorPanel, taskMonitorPanel);
    }

    /**
     * @param properties the utilityProperties to set
     */
    public void setProperties(UtilityProperties properties) {
        org.jw.service.util.UtilityProperties oldProperties = this.properties;
        this.properties = properties;
        propertyChangeSupport.firePropertyChange(PROP_UTILITYPROPERTIES, oldProperties, properties);
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        java.lang.String oldEntityName = this.entityName;
        this.entityName = entityName;
        propertyChangeSupport.firePropertyChange(PROP_ENTITYNAME, oldEntityName, entityName);
    }
    
    
    public void buildDefaultTasks(){
        DefaultTaskListener newTaskListener = taskMonitorPanel.createDefaultTaskListener(properties.getProperty(entityName + ".new.start.message"), properties.getProperty(entityName + ".new.done.message"));
        DefaultTaskListener deleteTaskListener = taskMonitorPanel.createDefaultTaskListener(properties.getProperty(entityName + ".delete.start.message"), properties.getProperty(entityName + ".delete.done.message"));
        DefaultTaskListener refreshTaskListener = taskMonitorPanel.createDefaultTaskListener(properties.getProperty(entityName + ".refresh.start.message"), properties.getProperty(entityName + ".refresh.done.message"));
        DefaultTaskListener saveTaskListener = taskMonitorPanel.createDefaultTaskListener(properties.getProperty(entityName + ".save.start.message"), properties.getProperty(entityName + ".save.done.message"));
          
        closeAction = new DefaultCloseAction(crudPanel.getCloseCommand(), dialog);
        newAction = new DefaultNewAction(crudPanel.getNewCommand(), dao, this.list, this.table, newTaskListener);
        deleteAction = new DefaultDeleteAction(crudPanel.getDeleteCommand(), this.list, this.table, deleteTaskListener);
        refreshAction = new DefaultRefreshAction(crudPanel.getRefreshCommand(), this.list, this.table, refreshTaskListener);
        saveAction = new DefaultSaveAction(crudPanel.getSaveCommand(), this.list, this.table, saveTaskListener);        
    }

    /**
     * @param newAction the newAction to set
     */
    public void setNewAction(DefaultNewAction newAction) {
        org.jw.service.action.DefaultNewAction oldNewAction = this.newAction;
        this.newAction = newAction;
        propertyChangeSupport.firePropertyChange(PROP_NEWACTION, oldNewAction, newAction);
    }

    /**
     * @param deleteAction the deleteAction to set
     */
    public void setDeleteAction(DefaultDeleteAction deleteAction) {
        org.jw.service.action.DefaultDeleteAction oldDeleteAction = this.deleteAction;
        this.deleteAction = deleteAction;
        propertyChangeSupport.firePropertyChange(PROP_DELETEACTION, oldDeleteAction, deleteAction);
    }

    /**
     * @param refreshAction the refreshAction to set
     */
    public void setRefreshAction(DefaultRefreshAction refreshAction) {
        org.jw.service.action.DefaultRefreshAction oldRefreshAction = this.refreshAction;
        this.refreshAction = refreshAction;
        propertyChangeSupport.firePropertyChange(PROP_REFRESHACTION, oldRefreshAction, refreshAction);
    }

    /**
     * @param saveAction the saveAction to set
     */
    public void setSaveAction(DefaultSaveAction saveAction) {
        org.jw.service.action.DefaultSaveAction oldSaveAction = this.saveAction;
        this.saveAction = saveAction;
        propertyChangeSupport.firePropertyChange(PROP_SAVEACTION, oldSaveAction, saveAction);
    }

    /**
     * @param list the list to set
     */
    public void setList(List list) {
        java.util.List oldList = this.list;
        this.list = list;
        propertyChangeSupport.firePropertyChange(PROP_LIST, oldList, list);
    }

    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        javax.swing.JTable oldTable = this.table;
        this.table = table;
        propertyChangeSupport.firePropertyChange(PROP_TABLE, oldTable, table);
    }

    /**
     * @param dialog the dialog to set
     */
    public void setDialog(JDialog dialog) {
        javax.swing.JDialog oldDialog = this.dialog;
        this.dialog = dialog;
        propertyChangeSupport.firePropertyChange(PROP_DIALOG, oldDialog, dialog);
    }

    /**
     * @param closeAction the closeAction to set
     */
    public void setCloseAction(DefaultCloseAction closeAction) {
        org.jw.service.action.DefaultCloseAction oldCloseAction = this.closeAction;
        this.closeAction = closeAction;
        propertyChangeSupport.firePropertyChange(PROP_CLOSEACTION, oldCloseAction, closeAction);
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(DataAccessObject dao) {
        org.jw.service.dao.DataAccessObject oldDao = this.dao;
        this.dao = dao;
        propertyChangeSupport.firePropertyChange(PROP_DAO, oldDao, dao);
    }
}
