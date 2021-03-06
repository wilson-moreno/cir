/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import javax.persistence.EntityManager;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.action.validator.DefaultCloseActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.action.validator.DefaultUniqueFieldsSaveActionValidator;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.MeetingPlace;
import org.jw.service.entity.Territory;
import org.jw.service.list.MeetingPlaceMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class MeetingPlaceDialog extends javax.swing.JDialog {
    private final DataAccessObject<MeetingPlace> dao;
    private final EntityManager em;
    
    /**
     * Creates new form MeetingPlaceDialog
     */
    public MeetingPlaceDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);
        this.dao = DataAccessObject.create(em, MeetingPlace.class);
        this.em = em;
        initComponents();
        initMyComponents();
    }
    
    private void initMyComponents(){
        meetingPlaceList.clear();
        meetingPlaceList.addAll(dao.readAll());
        java.util.Collections.sort(meetingPlaceList);
        DefaultTaskBuilder<MeetingPlace> taskBuilder = new DefaultTaskBuilder<>();
        taskBuilder.setEntityName("meeting.place");
        taskBuilder.setQuery(em.createNamedQuery("MeetingPlace.findAll", MeetingPlace.class));        
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(crudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(meetingPlaceList);
        taskBuilder.setTable(meetingPlaceTable);
        taskBuilder.setWindow(this);
        taskBuilder.setDao(dao);
        taskBuilder.buildDefaultTasks();
        
        UtilityTable<MeetingPlace> utilTable = UtilityTable.create(meetingPlaceTable, meetingPlaceList);
        setActionValidators(taskBuilder, utilTable);
    }
    
    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<MeetingPlace> utilTable){
        // Create Matchers
        MeetingPlaceMatcher meetingPlaceMatcher = new MeetingPlaceMatcher();
        
        // Create Action Validators
        DefaultUniqueFieldsSaveActionValidator uniqueFieldValidator = new DefaultUniqueFieldsSaveActionValidator(this, meetingPlaceList, utilTable, meetingPlaceMatcher, "meeting.place");
        DefaultRequiredFieldsSaveActionValidator requiredFieldsValidator = new DefaultRequiredFieldsSaveActionValidator(this, utilTable, "meeting.place");
        DefaultCloseActionValidator closeActionValidator = new DefaultCloseActionValidator(this, utilTable);
        
        // Set Validators
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);
        taskBuilder.getSaveAction().addActionValidator(uniqueFieldValidator);
        taskBuilder.getSaveAction().addActionValidator(requiredFieldsValidator);
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

        meetingPlaceList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.MeetingPlace>());
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        meetingPlacePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        latitudeLabel = new javax.swing.JLabel();
        longitudeLabel = new javax.swing.JLabel();
        latitudeTextField = new javax.swing.JTextField();
        longitudeTextField = new javax.swing.JTextField();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        crudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        meetingPlacesPanel = new javax.swing.JPanel();
        meetingPlaceScrollPane = new javax.swing.JScrollPane();
        meetingPlaceTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("meeting.place.dialog.title")); // NOI18N

        meetingPlacePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Meeting Place", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nameLabel.setText("Name:");

        addressLabel.setText("Address:");

        nameTextField.setColumns(10);
        nameTextField.setDocument(documentFilterFactory.getSizeFilter50());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        addressTextField.setColumns(10);
        addressTextField.setDocument(documentFilterFactory.getSizeFilter100());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.address}"), addressTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        latitudeLabel.setText("Latitude:");

        longitudeLabel.setText("Longitude:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.latitude}"), latitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.longitude}"), longitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout meetingPlacePanelLayout = new javax.swing.GroupLayout(meetingPlacePanel);
        meetingPlacePanel.setLayout(meetingPlacePanelLayout);
        meetingPlacePanelLayout.setHorizontalGroup(
            meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                        .addComponent(addressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressTextField))
                    .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                        .addComponent(longitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(longitudeTextField))
                    .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                        .addComponent(latitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(latitudeTextField)))
                .addContainerGap())
        );

        meetingPlacePanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addressLabel, latitudeLabel, longitudeLabel, nameLabel});

        meetingPlacePanelLayout.setVerticalGroup(
            meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingPlacePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(latitudeLabel)
                    .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meetingPlacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(longitudeLabel)
                    .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        meetingPlacesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Places", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        meetingPlaceTable.setAutoCreateRowSorter(true);
        meetingPlaceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceList, meetingPlaceTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${address}"));
        columnBinding.setColumnName("Address");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${latitude}"));
        columnBinding.setColumnName("Latitude");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${longitude}"));
        columnBinding.setColumnName("Longitude");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        meetingPlaceScrollPane.setViewportView(meetingPlaceTable);
        if (meetingPlaceTable.getColumnModel().getColumnCount() > 0) {
            meetingPlaceTable.getColumnModel().getColumn(0).setResizable(false);
            meetingPlaceTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            meetingPlaceTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
            meetingPlaceTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            meetingPlaceTable.getColumnModel().getColumn(2).setResizable(false);
            meetingPlaceTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            meetingPlaceTable.getColumnModel().getColumn(3).setPreferredWidth(75);
            meetingPlaceTable.getColumnModel().getColumn(4).setResizable(false);
            meetingPlaceTable.getColumnModel().getColumn(4).setPreferredWidth(75);
        }

        javax.swing.GroupLayout meetingPlacesPanelLayout = new javax.swing.GroupLayout(meetingPlacesPanel);
        meetingPlacesPanel.setLayout(meetingPlacesPanelLayout);
        meetingPlacesPanelLayout.setHorizontalGroup(
            meetingPlacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingPlacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meetingPlaceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        meetingPlacesPanelLayout.setVerticalGroup(
            meetingPlacesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meetingPlacesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meetingPlaceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(meetingPlacePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meetingPlacesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meetingPlacePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meetingPlacesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private org.jw.service.gui.component.MultipleRecordCrudPanel crudPanel;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JLabel latitudeLabel;
    private javax.swing.JTextField latitudeTextField;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JTextField longitudeTextField;
    private java.util.List<org.jw.service.entity.MeetingPlace> meetingPlaceList;
    private javax.swing.JPanel meetingPlacePanel;
    private javax.swing.JScrollPane meetingPlaceScrollPane;
    private javax.swing.JTable meetingPlaceTable;
    private javax.swing.JPanel meetingPlacesPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    DefaultCloseAction closeAction;
    DefaultNewAction<MeetingPlace> newAction;
    DefaultDeleteAction<MeetingPlace> deleteAction;
    DefaultRefreshAction<MeetingPlace> refreshAction;
    DefaultSaveAction<MeetingPlace> saveAction;
}
