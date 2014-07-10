/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.persistence.EntityManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;
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
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;
import org.jw.service.list.TerritoryMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class TerritoryDialog extends javax.swing.JDialog {
    private final DataAccessObject<Territory> territoryDAO;
    private final DataAccessObject<ServiceGroup> sgDAO;
    private final ObservableListListener listListener;
    private final EntityManager em;
    
    /**
     * Creates new form TerritoryDialog
     * @param parent
     * @param modal
     * @param em
     */
    public TerritoryDialog(java.awt.Frame parent, boolean modal, EntityManager em, ObservableListListener listListener) {
        super(parent, modal);        
        this.territoryDAO = DataAccessObject.create(em, Territory.class);
        this.sgDAO = DataAccessObject.create(em, ServiceGroup.class);
        this.listListener = listListener;
        this.em = em;
        initComponents();
        initMyComponents();
    }
    
    private void initMyComponents(){                
        territoryList.addAll(territoryDAO.readAll());
        Collections.sort(territoryList);
        //UtilityTable<Territory> utilTable = UtilityTable.create(territoryTable, territoryList);
        ((ObservableList)territoryList).addObservableListListener(listListener);        
        DefaultTaskBuilder<Territory> taskBuilder = new DefaultTaskBuilder<>();
        taskBuilder.setEntityName("status");
        taskBuilder.setQuery(em.createNamedQuery("Territory.findAll", Territory.class));
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(crudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(territoryList);
        taskBuilder.setTable(territoryTable);
        taskBuilder.setWindow(this);
        taskBuilder.setDao(territoryDAO);
        taskBuilder.buildDefaultTasks();
        for(ServiceGroup serviceGroup : sgDAO.readAll())
            this.serviceGroupComboBox.addItem(serviceGroup);
        
        final UtilityTable<Territory> utilTable = UtilityTable.create(territoryTable, territoryList);
        setActionValidators(taskBuilder, utilTable);
        
        final javax.swing.JDialog parent = this;
        
        this.mapTerritoryCommand.setEnabled(false);
        this.mapTerritoryCommand.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    TerritoryMapEditorDialog dialog = new TerritoryMapEditorDialog(parent, true, em, utilTable, null);
                    dialog.pack();
                    dialog.setLocationRelativeTo(parent);
                    dialog.setVisible(true);
                }                    
            }
        );
        this.territoryTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting()){
                            mapTerritoryCommand.setEnabled(true);
                        }
                    }                    
                }
        );
    }
    
    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<Territory> utilTable){
        // Create Matchers
        TerritoryMatcher territoryMatcher = new TerritoryMatcher();
        
        // Create Action Validators
        
        DefaultUniqueFieldsSaveActionValidator uniqueFieldValidator = new DefaultUniqueFieldsSaveActionValidator(this, territoryList, utilTable, territoryMatcher, "territory");
        DefaultRequiredFieldsSaveActionValidator requiredFieldValidator = new DefaultRequiredFieldsSaveActionValidator(this, utilTable, "territory");
        DefaultCloseActionValidator closeActionValidator = new DefaultCloseActionValidator(this, utilTable);
        
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);
        taskBuilder.getSaveAction().addActionValidator(uniqueFieldValidator);
        taskBuilder.getSaveAction().addActionValidator(requiredFieldValidator);
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

        territoryList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.Territory>());
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        crudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        territoryListPanel = new javax.swing.JPanel();
        territoryScrollPane = new javax.swing.JScrollPane();
        territoryTable = new javax.swing.JTable();
        territoryTabbedPane = new javax.swing.JTabbedPane();
        territoryTab = new javax.swing.JPanel();
        territoryPanel = new javax.swing.JPanel();
        territoryNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        enableCheckBox = new javax.swing.JCheckBox();
        serviceGroupLabel = new javax.swing.JLabel();
        serviceGroupComboBox = new javax.swing.JComboBox();
        territoryDirectionTab = new javax.swing.JPanel();
        territoryDirectionPanel = new javax.swing.JPanel();
        directionScrollPane = new javax.swing.JScrollPane();
        directionTextArea = new javax.swing.JTextArea();
        territoryMapTab = new javax.swing.JPanel();
        territoryMapPanel = new javax.swing.JPanel();
        territoryMapToolBar = new javax.swing.JToolBar();
        mapTerritoryCommand = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("territory.dialog.title")); // NOI18N

        territoryListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territories", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryTable.setAutoCreateRowSorter(true);
        territoryTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryList, territoryTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${serviceGroupId}"));
        columnBinding.setColumnName("Service Group");
        columnBinding.setColumnClass(org.jw.service.entity.ServiceGroup.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enable}"));
        columnBinding.setColumnName("Enable");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        territoryScrollPane.setViewportView(territoryTable);
        if (territoryTable.getColumnModel().getColumnCount() > 0) {
            territoryTable.getColumnModel().getColumn(0).setResizable(false);
            territoryTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            territoryTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
        }

        javax.swing.GroupLayout territoryListPanelLayout = new javax.swing.GroupLayout(territoryListPanel);
        territoryListPanel.setLayout(territoryListPanelLayout);
        territoryListPanelLayout.setHorizontalGroup(
            territoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        territoryListPanelLayout.setVerticalGroup(
            territoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        territoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryNameLabel.setText("Name:");

        descriptionLabel.setText("Description:");

        nameTextField.setColumns(10);
        nameTextField.setDocument(documentFilterFactory.getSizeFilter30());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        descriptionTextField.setColumns(10);
        descriptionTextField.setDocument(documentFilterFactory.getSizeFilter100());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        enableCheckBox.setText("Enable");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        serviceGroupLabel.setText("Service Group:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.serviceGroupId}"), serviceGroupComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout territoryPanelLayout = new javax.swing.GroupLayout(territoryPanel);
        territoryPanel.setLayout(territoryPanelLayout);
        territoryPanelLayout.setHorizontalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(serviceGroupLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceGroupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(territoryNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enableCheckBox))
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                .addContainerGap())
        );

        territoryPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {descriptionLabel, serviceGroupLabel, territoryNameLabel});

        territoryPanelLayout.setVerticalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(territoryNameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enableCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceGroupLabel)
                    .addComponent(serviceGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout territoryTabLayout = new javax.swing.GroupLayout(territoryTab);
        territoryTab.setLayout(territoryTabLayout);
        territoryTabLayout.setHorizontalGroup(
            territoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
            .addGroup(territoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(territoryTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(territoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        territoryTabLayout.setVerticalGroup(
            territoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(territoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(territoryTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(territoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        territoryTabbedPane.addTab("Territory", territoryTab);

        territoryDirectionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direction", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        directionTextArea.setColumns(20);
        directionTextArea.setDocument(documentFilterFactory.getSizeFilter200());
        directionTextArea.setLineWrap(true);
        directionTextArea.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.direction}"), directionTextArea, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        directionScrollPane.setViewportView(directionTextArea);

        javax.swing.GroupLayout territoryDirectionPanelLayout = new javax.swing.GroupLayout(territoryDirectionPanel);
        territoryDirectionPanel.setLayout(territoryDirectionPanelLayout);
        territoryDirectionPanelLayout.setHorizontalGroup(
            territoryDirectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryDirectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );
        territoryDirectionPanelLayout.setVerticalGroup(
            territoryDirectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryDirectionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout territoryDirectionTabLayout = new javax.swing.GroupLayout(territoryDirectionTab);
        territoryDirectionTab.setLayout(territoryDirectionTabLayout);
        territoryDirectionTabLayout.setHorizontalGroup(
            territoryDirectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryDirectionTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryDirectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        territoryDirectionTabLayout.setVerticalGroup(
            territoryDirectionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryDirectionTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryDirectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        territoryTabbedPane.addTab("Direction", territoryDirectionTab);

        territoryMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territory Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryMapToolBar.setFloatable(false);
        territoryMapToolBar.setRollover(true);

        mapTerritoryCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.add.map.png"))); // NOI18N
        mapTerritoryCommand.setText("Map");
        mapTerritoryCommand.setFocusable(false);
        mapTerritoryCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        territoryMapToolBar.add(mapTerritoryCommand);

        javax.swing.GroupLayout territoryMapPanelLayout = new javax.swing.GroupLayout(territoryMapPanel);
        territoryMapPanel.setLayout(territoryMapPanelLayout);
        territoryMapPanelLayout.setHorizontalGroup(
            territoryMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
        );
        territoryMapPanelLayout.setVerticalGroup(
            territoryMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout territoryMapTabLayout = new javax.swing.GroupLayout(territoryMapTab);
        territoryMapTab.setLayout(territoryMapTabLayout);
        territoryMapTabLayout.setHorizontalGroup(
            territoryMapTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryMapTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        territoryMapTabLayout.setVerticalGroup(
            territoryMapTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryMapTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        territoryTabbedPane.addTab("Map", territoryMapTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(territoryListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(territoryTabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(territoryListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.gui.component.MultipleRecordCrudPanel crudPanel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JScrollPane directionScrollPane;
    private javax.swing.JTextArea directionTextArea;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JButton mapTerritoryCommand;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox serviceGroupComboBox;
    private javax.swing.JLabel serviceGroupLabel;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JPanel territoryDirectionPanel;
    private javax.swing.JPanel territoryDirectionTab;
    private java.util.List<org.jw.service.entity.Territory> territoryList;
    private javax.swing.JPanel territoryListPanel;
    private javax.swing.JPanel territoryMapPanel;
    private javax.swing.JPanel territoryMapTab;
    private javax.swing.JToolBar territoryMapToolBar;
    private javax.swing.JLabel territoryNameLabel;
    private javax.swing.JPanel territoryPanel;
    private javax.swing.JScrollPane territoryScrollPane;
    private javax.swing.JPanel territoryTab;
    private javax.swing.JTabbedPane territoryTabbedPane;
    private javax.swing.JTable territoryTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);            
    DefaultCloseAction closeAction;
    DefaultNewAction<Territory> newAction;
    DefaultDeleteAction<Territory> deleteAction;
    DefaultRefreshAction<Territory> refreshAction;
    DefaultSaveAction<Territory> saveAction;    
}
