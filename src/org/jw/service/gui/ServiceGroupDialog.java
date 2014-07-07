/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import javax.persistence.EntityManager;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.action.dependency.NewServiceGroupPostDependency;
import org.jw.service.action.dependency.NewServiceGroupPreDependency;
import org.jw.service.action.dependency.SaveServiceGroupPostDependency;
import org.jw.service.action.dependency.SaveServiceGroupPreDependency;
import org.jw.service.action.validator.DefaultCloseActionValidator;
import org.jw.service.action.validator.DefaultUniqueFieldsSaveActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Congregation;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.list.ServiceGroupMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class ServiceGroupDialog extends javax.swing.JDialog {
    private final EntityManager em;    
    private final ObservableListListener listListener;
    private final UtilityTree utilTree;
    
    /**
     * Creates new form ServiceGroupDialog
     * @param parent
     * @param modal
     * @param em
     * @param listListener
     */
    public ServiceGroupDialog(java.awt.Frame parent, boolean modal, EntityManager em, ObservableListListener listListener, UtilityTree utilTree) {
        super(parent, modal);
        this.em = em;        
        this.listListener = listListener;
        this.utilTree = utilTree;
        initComponents();        
        initMyComponents();
    }
    
    private void initMyComponents(){        
        dao = DataAccessObject.create(em, ServiceGroup.class);     
        serviceGroupList.clear();        
        serviceGroupList.addAll(dao.readAll());
        ((ObservableList)serviceGroupList).addObservableListListener(listListener);        
        DefaultTaskBuilder<ServiceGroup> taskBuilder = new DefaultTaskBuilder();
        taskBuilder.setEntityName("service");
        taskBuilder.setQuery(em.createNamedQuery("ServiceGroup.findAll", ServiceGroup.class));
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(crudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(serviceGroupList);
        taskBuilder.setTable(serviceGroupsTable);
        taskBuilder.setWindow(this);
        taskBuilder.setDao(dao);
        taskBuilder.buildDefaultTasks();        
        
        DataAccessObject<Congregation> congregationDAO = DataAccessObject.create(em, Congregation.class);
        UtilityTable utilTable = UtilityTable.create(serviceGroupsTable, serviceGroupList);
        
        setActionDependencies(taskBuilder, congregationDAO);
        setActionValidators(taskBuilder, utilTable);
        
        bindingGroup.addBindingListener(this.taskMonitorPanel.getLogger());
    }
    
    private void setActionDependencies(DefaultTaskBuilder taskBuilder, DataAccessObject<Congregation> congregationDAO){
        SaveServiceGroupPreDependency saveServiceGroupPreDependency = new SaveServiceGroupPreDependency(this.startNumberTextField, this.nextNumberTextField);
        SaveServiceGroupPostDependency saveServiceGroupPostDependency = new SaveServiceGroupPostDependency(utilTree);
        NewServiceGroupPreDependency newServiceGroupPreDependency = new NewServiceGroupPreDependency(this, congregationDAO);        
        NewServiceGroupPostDependency newServiceGroupPostDependency = new NewServiceGroupPostDependency(em, utilTree);
        taskBuilder.getSaveAction().addPreActionCommands("saveServiceGroupPreDependency", saveServiceGroupPreDependency);        
        taskBuilder.getSaveAction().addPostActionCommands("saveServiceGroupPostDependency", saveServiceGroupPostDependency);
        taskBuilder.getNewAction().addPreActionCommands("newServiceGroupPreDependency", newServiceGroupPreDependency);        
        taskBuilder.getNewAction().addPostActionCommands("newServiceGroupPostDependency", newServiceGroupPostDependency);
    }

    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<ServiceGroup> utilTable){
        // Create Matchers
        ServiceGroupMatcher serviceGroupMatcher = new ServiceGroupMatcher();
        
        // Create Validators
        closeActionValidator = new DefaultCloseActionValidator(this, UtilityTable.create(serviceGroupsTable, serviceGroupList));
        //uniqueNameValidator = new DefaultUniqueNameValidator(this, UtilityTable.create(serviceGroupsTable, serviceGroupList));
        saveServiceGroupValidator = new DefaultRequiredFieldsSaveActionValidator(this, UtilityTable.create(serviceGroupsTable, serviceGroupList), "Name, Prefix & Start Number are required");
        DefaultUniqueFieldsSaveActionValidator uniqueFieldSaveActionValidator = new DefaultUniqueFieldsSaveActionValidator(this, serviceGroupList, utilTable, serviceGroupMatcher, "Name and Prefix should be unique");
        
        // Add Validators
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);        
        taskBuilder.getSaveAction().addActionValidator(uniqueFieldSaveActionValidator);
        taskBuilder.getSaveAction().addActionValidator(saveServiceGroupValidator);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        serviceGroupList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.ServiceGroup>());
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        crudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        serviceGroupPanel = new javax.swing.JPanel();
        serviceGroupNameLabel = new javax.swing.JLabel();
        overseerLabel = new javax.swing.JLabel();
        assitantLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        overseerTextField = new javax.swing.JTextField();
        assistantTextField = new javax.swing.JTextField();
        prefixLabel = new javax.swing.JLabel();
        startNumberLabel = new javax.swing.JLabel();
        nextNumberLabel = new javax.swing.JLabel();
        nextNumberTextField = new javax.swing.JTextField();
        startNumberTextField = new javax.swing.JFormattedTextField();
        prefixTextField = new javax.swing.JFormattedTextField();
        groupsPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        serviceGroupsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("service.group.dialog.title")); // NOI18N
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        serviceGroupPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Service Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        serviceGroupNameLabel.setText("Name:");

        overseerLabel.setText("Overseer:");

        assitantLabel.setText("Assistant:");

        nameTextField.setColumns(5);
        nameTextField.setDocument(documentFilterFactory.getSizeFilter50());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Name");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        overseerTextField.setColumns(5);
        overseerTextField.setDocument(documentFilterFactory.getSizeFilter75());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.overseer}"), overseerTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Overseer");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        assistantTextField.setColumns(5);
        assistantTextField.setDocument(documentFilterFactory.getSizeFilter75());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.assistant}"), assistantTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Assistant");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        prefixLabel.setText("Prefix:");

        startNumberLabel.setText("Start Number:");

        nextNumberLabel.setText("Next Number:");

        nextNumberTextField.setEditable(false);
        nextNumberTextField.setColumns(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nextNumber}"), nextNumberTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "nextNumber");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        startNumberTextField.setEditable(false);
        startNumberTextField.setColumns(5);
        startNumberTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.startNumber}"), startNumberTextField, org.jdesktop.beansbinding.BeanProperty.create("value"), "Start Number");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        prefixTextField.setColumns(5);
        try {
            prefixTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.prefix}"), prefixTextField, org.jdesktop.beansbinding.BeanProperty.create("value"), "Prefix");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout serviceGroupPanelLayout = new javax.swing.GroupLayout(serviceGroupPanel);
        serviceGroupPanel.setLayout(serviceGroupPanelLayout);
        serviceGroupPanelLayout.setHorizontalGroup(
            serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(serviceGroupNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(overseerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(overseerTextField))
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(assitantLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(assistantTextField)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(startNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startNumberTextField))
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(prefixLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prefixTextField))
                    .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                        .addComponent(nextNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        serviceGroupPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {assitantLabel, overseerLabel, serviceGroupNameLabel});

        serviceGroupPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {nextNumberLabel, prefixLabel, startNumberLabel});

        serviceGroupPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {assistantTextField, nameTextField, nextNumberTextField, overseerTextField});

        serviceGroupPanelLayout.setVerticalGroup(
            serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serviceGroupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceGroupNameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prefixLabel)
                    .addComponent(prefixTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(overseerLabel)
                    .addComponent(overseerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startNumberLabel)
                    .addComponent(startNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serviceGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assitantLabel)
                    .addComponent(assistantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextNumberLabel)
                    .addComponent(nextNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        groupsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Groups", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        serviceGroupsTable.setAutoCreateRowSorter(true);
        serviceGroupsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupList, serviceGroupsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${overseer}"));
        columnBinding.setColumnName("Overseer");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${assistant}"));
        columnBinding.setColumnName("Assistant");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${prefix}"));
        columnBinding.setColumnName("Prefix");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${startNumber}"));
        columnBinding.setColumnName("Start Number");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nextNumber}"));
        columnBinding.setColumnName("Next Number");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        scrollPane.setViewportView(serviceGroupsTable);
        if (serviceGroupsTable.getColumnModel().getColumnCount() > 0) {
            serviceGroupsTable.getColumnModel().getColumn(0).setResizable(false);
            serviceGroupsTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            serviceGroupsTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
        }

        javax.swing.GroupLayout groupsPanelLayout = new javax.swing.GroupLayout(groupsPanel);
        groupsPanel.setLayout(groupsPanelLayout);
        groupsPanelLayout.setHorizontalGroup(
            groupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane)
                .addContainerGap())
        );
        groupsPanelLayout.setVerticalGroup(
            groupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serviceGroupPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(groupsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serviceGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowActivated

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField assistantTextField;
    private javax.swing.JLabel assitantLabel;
    private org.jw.service.gui.component.MultipleRecordCrudPanel crudPanel;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JPanel groupsPanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel nextNumberLabel;
    private javax.swing.JTextField nextNumberTextField;
    private javax.swing.JLabel overseerLabel;
    private javax.swing.JTextField overseerTextField;
    private javax.swing.JLabel prefixLabel;
    private javax.swing.JFormattedTextField prefixTextField;
    private javax.swing.JScrollPane scrollPane;
    private java.util.List<org.jw.service.entity.ServiceGroup> serviceGroupList;
    private javax.swing.JLabel serviceGroupNameLabel;
    private javax.swing.JPanel serviceGroupPanel;
    private javax.swing.JTable serviceGroupsTable;
    private javax.swing.JLabel startNumberLabel;
    private javax.swing.JFormattedTextField startNumberTextField;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    DefaultCloseAction<ServiceGroup> closeAction;
    DefaultNewAction<ServiceGroup> newAction;
    DefaultDeleteAction<ServiceGroup> deleteAction;
    DefaultRefreshAction<ServiceGroup> refreshAction;
    DefaultSaveAction<ServiceGroup> saveAction;        
    DataAccessObject<ServiceGroup> dao;    
    DefaultCloseActionValidator closeActionValidator;    
    DefaultRequiredFieldsSaveActionValidator saveServiceGroupValidator;
}
