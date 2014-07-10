/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.util.Collections;
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
import org.jw.service.entity.QueryText;
import org.jw.service.list.QueryTextMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public final class QueryTextDialog extends javax.swing.JDialog {
    private final DataAccessObject<QueryText> dao;
    private final EntityManager em;
    
    /**
     * Creates new form QueryTextDialog
     */
    public QueryTextDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);
        this.dao = DataAccessObject.create(em, QueryText.class);
        this.em = em;
        initComponents();
        initMyComponents();
    }
    
    public void initMyComponents(){
        queryTextList.addAll(dao.readAll());
        Collections.sort(queryTextList);
        DefaultTaskBuilder taskBuilder = new DefaultTaskBuilder();
        taskBuilder.setEntityName("query.text");
        taskBuilder.setQuery(em.createNamedQuery("QueryText.findAll", QueryText.class));
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(this.multipleRecordCrudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(this.queryTextList);
        taskBuilder.setTable(this.queryTextTable);
        taskBuilder.setWindow(this); 
        taskBuilder.setDao(this.dao);
        taskBuilder.buildDefaultTasks();        
        
        UtilityTable<QueryText> utilTable = UtilityTable.create(queryTextTable, queryTextList);
        setActionValidators(taskBuilder, utilTable);
    }
    
    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<QueryText> utilTable){
        // Create Matcher
        QueryTextMatcher matcher = new QueryTextMatcher();
        
        // Create Validators
        DefaultUniqueFieldsSaveActionValidator<QueryText> uniqueFieldValidator = new DefaultUniqueFieldsSaveActionValidator<>(this, this.queryTextList, utilTable, matcher, "Query.Text");
        DefaultRequiredFieldsSaveActionValidator<QueryText> requiredFieldValidator = new DefaultRequiredFieldsSaveActionValidator<>(this, utilTable, "Query.Text");
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

        queryTextList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.QueryText>());
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        multipleRecordCrudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        queryTextPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        queryScrollPane = new javax.swing.JScrollPane();
        queryTextArea = new javax.swing.JTextArea();
        queryLabel = new javax.swing.JLabel();
        queriesTextPanel = new javax.swing.JPanel();
        queryTextScrollPane = new javax.swing.JScrollPane();
        queryTextTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("query.dialog.title")); // NOI18N

        queryTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Query", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nameLabel.setText("Name:");

        descriptionLabel.setText("Description:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, queryTextTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, queryTextTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        queryTextArea.setColumns(20);
        queryTextArea.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, queryTextTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.text}"), queryTextArea, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        queryScrollPane.setViewportView(queryTextArea);

        queryLabel.setText("Query:");

        javax.swing.GroupLayout queryTextPanelLayout = new javax.swing.GroupLayout(queryTextPanel);
        queryTextPanel.setLayout(queryTextPanelLayout);
        queryTextPanelLayout.setHorizontalGroup(
            queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queryTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(queryTextPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(queryTextPanelLayout.createSequentialGroup()
                        .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(queryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField)
                            .addComponent(queryScrollPane))))
                .addContainerGap())
        );

        queryTextPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {descriptionLabel, nameLabel});

        queryTextPanelLayout.setVerticalGroup(
            queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queryTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(queryTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(queryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(queryLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        queriesTextPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Queries", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        queryTextTable.setAutoCreateRowSorter(true);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, queryTextList, queryTextTable);
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
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        queryTextScrollPane.setViewportView(queryTextTable);
        if (queryTextTable.getColumnModel().getColumnCount() > 0) {
            queryTextTable.getColumnModel().getColumn(0).setResizable(false);
            queryTextTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            queryTextTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
            queryTextTable.getColumnModel().getColumn(1).setResizable(false);
            queryTextTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            queryTextTable.getColumnModel().getColumn(2).setResizable(false);
            queryTextTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        }

        javax.swing.GroupLayout queriesTextPanelLayout = new javax.swing.GroupLayout(queriesTextPanel);
        queriesTextPanel.setLayout(queriesTextPanelLayout);
        queriesTextPanelLayout.setHorizontalGroup(
            queriesTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queriesTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryTextScrollPane)
                .addContainerGap())
        );
        queriesTextPanelLayout.setVerticalGroup(
            queriesTextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queriesTextPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multipleRecordCrudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(queryTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(queriesTextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(queriesTextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(multipleRecordCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private org.jw.service.gui.component.MultipleRecordCrudPanel multipleRecordCrudPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPanel queriesTextPanel;
    private javax.swing.JLabel queryLabel;
    private javax.swing.JScrollPane queryScrollPane;
    private javax.swing.JTextArea queryTextArea;
    private java.util.List<org.jw.service.entity.QueryText> queryTextList;
    private javax.swing.JPanel queryTextPanel;
    private javax.swing.JScrollPane queryTextScrollPane;
    private javax.swing.JTable queryTextTable;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    DefaultCloseAction<QueryText> closeAction;
    DefaultNewAction<QueryText> newAction;
    DefaultDeleteAction<QueryText> deleteAction;
    DefaultRefreshAction<QueryText> refreshAction;
    DefaultSaveAction<QueryText> saveAction;  
}
