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
import org.jw.service.action.dependency.NewParameterPostDependency;
import org.jw.service.action.validator.DefaultCloseActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.action.validator.DefaultUniqueFieldsSaveActionValidator;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.AppsReportParameter;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.QueryText;
import org.jw.service.list.ReportParameterMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class ReportParameterDialog extends javax.swing.JDialog {
    private final EntityManager em;
    private final UtilityTable<AppsReport> utilTable;
    private final DataAccessObject<AppsReportParameter> parameterDAO;
    private final DataAccessObject<AppsReport> reportDAO;
    private final DataAccessObject<QueryText> queryTextDAO;
    private final EntityIO<AppsReport> reportIO;
    private AppsReport appsReportTarget;
    private final DefaultTaskBuilder<AppsReportParameter> taskBuilder;   
    
    /**
     * Creates new form ReportParameterDialog
     */
    public ReportParameterDialog(java.awt.Dialog parent, boolean modal, EntityManager em, UtilityTable<AppsReport> utilTable) {
        super(parent, modal);
        this.em = em;
        this.utilTable = utilTable;
        this.parameterDAO = DataAccessObject.create(em, AppsReportParameter.class);
        this.reportDAO = DataAccessObject.create(em, AppsReport.class);
        this.queryTextDAO = DataAccessObject.create(em, QueryText.class);
        this.reportIO = EntityIO.create(AppsReport.class);
        this.taskBuilder = new DefaultTaskBuilder();
        initComponents();        
        initMyComponents();
    }
    
    private void initMyComponents(){
        setSelectedReport();
        setReportParameters();
        taskBuilder.setEntityName("apps.report.parameters");
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(this.multipleRecordCrudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(parameterList);
        taskBuilder.setTable(parametersTable);
        taskBuilder.setWindow(this); 
        taskBuilder.setDao(parameterDAO);
        taskBuilder.buildDefaultTasks();
        
        NewParameterPostDependency newParameterPostDependency = new NewParameterPostDependency(parameterDAO, this.appsReportTarget);
        taskBuilder.getNewAction().addPostActionCommands("newParameterPostDependency", newParameterPostDependency);
        
        UtilityTable<AppsReportParameter> utilTable = UtilityTable.create(parametersTable, parameterList);
        setActionValidators(taskBuilder, utilTable);
    }
    
    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<AppsReportParameter> utilTable){
        // Create Matchers
        ReportParameterMatcher matcher = new ReportParameterMatcher();
        
        // Create Validators
        DefaultUniqueFieldsSaveActionValidator uniqueFieldSaveActionValidator = new DefaultUniqueFieldsSaveActionValidator(this, parameterList, utilTable, matcher, "parameter");
        DefaultRequiredFieldsSaveActionValidator requiredFieldsValidator = new DefaultRequiredFieldsSaveActionValidator(this, utilTable, "parameter");
        DefaultCloseActionValidator closeActionValidator = new DefaultCloseActionValidator(this, utilTable);
        
        // Set Validators
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);
        taskBuilder.getSaveAction().addActionValidator(uniqueFieldSaveActionValidator);
        taskBuilder.getSaveAction().addActionValidator(requiredFieldsValidator);
    }
    
    private void setReportParameters(){
        reportDAO.refresh(appsReportTarget);
        parameterList.clear();
        parameterList.addAll(appsReportTarget.getAppsReportParameterCollection());
    }
    
    private void setSelectedReport(){
        appsReportTarget = utilTable.getSelectedItem();
        reportIO.setSource(appsReportSource);
        reportIO.setTarget(appsReportTarget);
        reportIO.read();
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

        parameterList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.AppsReportParameter>());
        appsReportSource = new org.jw.service.entity.AppsReport();
        dataTypeListBean = new org.jw.service.beans.ListBean("data_type.properties");
        parameterTypeListBean = new org.jw.service.beans.ListBean("parameter_type.properties");
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        controTypeListBean = new org.jw.service.beans.ListBean("control_types.properties");
        queryTextListBean = new org.jw.service.beans.ListBean<QueryText>(this.queryTextDAO);
        ;
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        multipleRecordCrudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        appsReportPanel = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        titleTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        parametersPanel = new javax.swing.JPanel();
        parametersScrollPane = new javax.swing.JScrollPane();
        parametersTable = new javax.swing.JTable();
        parameterTabbedPane = new javax.swing.JTabbedPane();
        parameterTab = new javax.swing.JPanel();
        parameterPanel = new javax.swing.JPanel();
        sequenceLabel = new javax.swing.JLabel();
        sequenceTextField = new javax.swing.JFormattedTextField();
        paramNameLabel = new javax.swing.JLabel();
        paramNameTextField = new javax.swing.JTextField();
        paramLabel = new javax.swing.JLabel();
        labelTextField = new javax.swing.JTextField();
        dataTypeLabel = new javax.swing.JLabel();
        dataTypeComboBox = new javax.swing.JComboBox();
        controlTab = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        controlTypeLabel = new javax.swing.JLabel();
        controlTypeComboBox = new javax.swing.JComboBox();
        queryLabel = new javax.swing.JLabel();
        queryComboBox = new javax.swing.JComboBox();
        displayColumnLabel = new javax.swing.JLabel();
        displayColumnTextField = new javax.swing.JTextField();
        valueColumnLabel = new javax.swing.JLabel();
        valueColumnTextField = new javax.swing.JTextField();
        otherInformationTab = new javax.swing.JPanel();
        otherInformationPanel = new javax.swing.JPanel();
        defaultValueLabel = new javax.swing.JLabel();
        defaultValueTextField = new javax.swing.JTextField();
        dependsOnLabel = new javax.swing.JLabel();
        dependsOnTextField = new javax.swing.JFormattedTextField();
        parameterTypeLabel = new javax.swing.JLabel();
        parameterTypeComboBox = new javax.swing.JComboBox();
        enableCheckBox = new javax.swing.JCheckBox();
        requiredCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("apps.report.parameters.dialog.title")); // NOI18N
        setResizable(false);

        appsReportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        codeLabel.setText("Code:");

        nameLabel.setText("Name:");

        titleLabel.setText("Title:");

        descriptionLabel.setText("Description:");

        codeTextField.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, appsReportSource, org.jdesktop.beansbinding.ELProperty.create("${code}"), codeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        nameTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, appsReportSource, org.jdesktop.beansbinding.ELProperty.create("${name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        titleTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, appsReportSource, org.jdesktop.beansbinding.ELProperty.create("${title}"), titleTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        descriptionTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, appsReportSource, org.jdesktop.beansbinding.ELProperty.create("${description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout appsReportPanelLayout = new javax.swing.GroupLayout(appsReportPanel);
        appsReportPanel.setLayout(appsReportPanelLayout);
        appsReportPanelLayout.setHorizontalGroup(
            appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appsReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(appsReportPanelLayout.createSequentialGroup()
                        .addComponent(codeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeTextField))
                    .addGroup(appsReportPanelLayout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionTextField))
                    .addGroup(appsReportPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(appsReportPanelLayout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleTextField)))
                .addContainerGap())
        );

        appsReportPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {codeLabel, descriptionLabel, nameLabel, titleLabel});

        appsReportPanelLayout.setVerticalGroup(
            appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(appsReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel)
                    .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(appsReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List of Parameters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        parametersTable.setAutoCreateRowSorter(true);
        parametersTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterList, parametersTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${sequence}"));
        columnBinding.setColumnName("Sequence");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${label}"));
        columnBinding.setColumnName("Label");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataType}"));
        columnBinding.setColumnName("Data Type");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enable}"));
        columnBinding.setColumnName("Enable");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${required}"));
        columnBinding.setColumnName("Required");
        columnBinding.setColumnClass(Boolean.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        parametersScrollPane.setViewportView(parametersTable);
        if (parametersTable.getColumnModel().getColumnCount() > 0) {
            parametersTable.getColumnModel().getColumn(0).setResizable(false);
            parametersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            parametersTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
        }

        javax.swing.GroupLayout parametersPanelLayout = new javax.swing.GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parametersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        parametersPanelLayout.setVerticalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parametersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        parameterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parameter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        sequenceLabel.setText("Sequence:");

        sequenceTextField.setColumns(5);
        sequenceTextField.setDocument(documentFilterFactory.getSizeFilter30());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.sequence}"), sequenceTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        paramNameLabel.setText("Name:");

        paramNameTextField.setColumns(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), paramNameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        paramLabel.setText("Label:");

        labelTextField.setColumns(5);
        labelTextField.setDocument(documentFilterFactory.getSizeFilter30());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.label}"), labelTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        dataTypeLabel.setText("Data Type:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dataTypeListBean, eLProperty, dataTypeComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dataType}"), dataTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout parameterPanelLayout = new javax.swing.GroupLayout(parameterPanel);
        parameterPanel.setLayout(parameterPanelLayout);
        parameterPanelLayout.setHorizontalGroup(
            parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sequenceLabel)
                    .addComponent(paramNameLabel)
                    .addComponent(paramLabel)
                    .addComponent(dataTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sequenceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(paramNameTextField)
                    .addComponent(labelTextField)
                    .addComponent(dataTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        parameterPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {paramLabel, paramNameLabel, sequenceLabel});

        parameterPanelLayout.setVerticalGroup(
            parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sequenceLabel)
                    .addComponent(sequenceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramNameLabel)
                    .addComponent(paramNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramLabel)
                    .addComponent(labelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataTypeLabel)
                    .addComponent(dataTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout parameterTabLayout = new javax.swing.GroupLayout(parameterTab);
        parameterTab.setLayout(parameterTabLayout);
        parameterTabLayout.setHorizontalGroup(
            parameterTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
            .addGroup(parameterTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(parameterTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(parameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        parameterTabLayout.setVerticalGroup(
            parameterTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
            .addGroup(parameterTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parameterTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(parameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        parameterTabbedPane.addTab("Parameter", parameterTab);

        controlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        controlTypeLabel.setText("Control Type:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, controTypeListBean, eLProperty, controlTypeComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.controlType}"), controlTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        controlTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlTypeComboBoxActionPerformed(evt);
            }
        });

        queryLabel.setText("Query:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, queryTextListBean, eLProperty, queryComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.queryTextId}"), queryComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        displayColumnLabel.setText("Display Column:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.displayColumn}"), displayColumnTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        valueColumnLabel.setText("Value Column:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.valueColumn}"), valueColumnTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(controlTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlTypeComboBox, 0, 306, Short.MAX_VALUE))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(displayColumnLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(queryLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(queryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayColumnTextField)))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(valueColumnLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valueColumnTextField)))
                .addContainerGap())
        );

        controlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {controlTypeLabel, displayColumnLabel, queryLabel, valueColumnLabel});

        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controlTypeLabel)
                    .addComponent(controlTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(queryLabel)
                    .addComponent(queryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayColumnLabel)
                    .addComponent(displayColumnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valueColumnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueColumnLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout controlTabLayout = new javax.swing.GroupLayout(controlTab);
        controlTab.setLayout(controlTabLayout);
        controlTabLayout.setHorizontalGroup(
            controlTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        controlTabLayout.setVerticalGroup(
            controlTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        parameterTabbedPane.addTab("Control", controlTab);

        otherInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Other", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        defaultValueLabel.setText("Default Value:");

        defaultValueTextField.setColumns(5);
        defaultValueTextField.setDocument(documentFilterFactory.getSizeFilter30());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.defaultValue}"), defaultValueTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        dependsOnLabel.setText("Depends On:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dependsOn}"), dependsOnTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        parameterTypeLabel.setText("Type:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTypeListBean, eLProperty, parameterTypeComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.parameterType}"), parameterTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        enableCheckBox.setText("Enable");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        enableCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableCheckBoxActionPerformed(evt);
            }
        });

        requiredCheckBox.setText("Required");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parametersTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.required}"), requiredCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout otherInformationPanelLayout = new javax.swing.GroupLayout(otherInformationPanel);
        otherInformationPanel.setLayout(otherInformationPanelLayout);
        otherInformationPanelLayout.setHorizontalGroup(
            otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(otherInformationPanelLayout.createSequentialGroup()
                        .addComponent(defaultValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(defaultValueTextField)
                        .addContainerGap())
                    .addGroup(otherInformationPanelLayout.createSequentialGroup()
                        .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(parameterTypeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dependsOnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(otherInformationPanelLayout.createSequentialGroup()
                                .addComponent(enableCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(requiredCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 189, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otherInformationPanelLayout.createSequentialGroup()
                                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(parameterTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dependsOnTextField))
                                .addContainerGap())))))
        );

        otherInformationPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {defaultValueLabel, dependsOnLabel});

        otherInformationPanelLayout.setVerticalGroup(
            otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultValueLabel)
                    .addComponent(defaultValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dependsOnLabel)
                    .addComponent(dependsOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parameterTypeLabel)
                    .addComponent(parameterTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(otherInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enableCheckBox)
                    .addComponent(requiredCheckBox))
                .addContainerGap())
        );

        javax.swing.GroupLayout otherInformationTabLayout = new javax.swing.GroupLayout(otherInformationTab);
        otherInformationTab.setLayout(otherInformationTabLayout);
        otherInformationTabLayout.setHorizontalGroup(
            otherInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherInformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        otherInformationTabLayout.setVerticalGroup(
            otherInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherInformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(otherInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        parameterTabbedPane.addTab("Other", otherInformationTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(multipleRecordCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(parameterTabbedPane)
                    .addComponent(appsReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(parametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appsReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parameterTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(multipleRecordCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enableCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_enableCheckBoxActionPerformed

    private void controlTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlTypeComboBoxActionPerformed
        // TODO add your handling code here:
        String selected = (String) this.controlTypeComboBox.getSelectedItem();
        
        if(selected == null){
            setEditableControlAttributes(false);
            return;
        }
        
        switch(selected){
            case "Single Input":setEditableControlAttributes(false);break;
            case "Single Select Query":setEditableControlAttributes(true);break;
            default: setEditableControlAttributes(false);                
        }
    }//GEN-LAST:event_controlTypeComboBoxActionPerformed

    private void setEditableControlAttributes(boolean editable){
        this.queryComboBox.setEditable(editable);
        this.displayColumnTextField.setEditable(editable);
        this.valueColumnTextField.setEditable(editable);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel appsReportPanel;
    private org.jw.service.entity.AppsReport appsReportSource;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTextField;
    private org.jw.service.beans.ListBean controTypeListBean;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel controlTab;
    private javax.swing.JComboBox controlTypeComboBox;
    private javax.swing.JLabel controlTypeLabel;
    private javax.swing.JComboBox dataTypeComboBox;
    private javax.swing.JLabel dataTypeLabel;
    private org.jw.service.beans.ListBean dataTypeListBean;
    private javax.swing.JLabel defaultValueLabel;
    private javax.swing.JTextField defaultValueTextField;
    private javax.swing.JLabel dependsOnLabel;
    private javax.swing.JFormattedTextField dependsOnTextField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JLabel displayColumnLabel;
    private javax.swing.JTextField displayColumnTextField;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JTextField labelTextField;
    private org.jw.service.gui.component.MultipleRecordCrudPanel multipleRecordCrudPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPanel otherInformationPanel;
    private javax.swing.JPanel otherInformationTab;
    private javax.swing.JLabel paramLabel;
    private javax.swing.JLabel paramNameLabel;
    private javax.swing.JTextField paramNameTextField;
    private java.util.List<org.jw.service.entity.AppsReportParameter> parameterList;
    private javax.swing.JPanel parameterPanel;
    private javax.swing.JPanel parameterTab;
    private javax.swing.JTabbedPane parameterTabbedPane;
    private javax.swing.JComboBox parameterTypeComboBox;
    private javax.swing.JLabel parameterTypeLabel;
    private org.jw.service.beans.ListBean parameterTypeListBean;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.JScrollPane parametersScrollPane;
    private javax.swing.JTable parametersTable;
    private javax.swing.JComboBox queryComboBox;
    private javax.swing.JLabel queryLabel;
    private org.jw.service.beans.ListBean queryTextListBean;
    private javax.swing.JCheckBox requiredCheckBox;
    private javax.swing.JLabel sequenceLabel;
    private javax.swing.JFormattedTextField sequenceTextField;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JLabel valueColumnLabel;
    private javax.swing.JTextField valueColumnTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    DefaultCloseAction<AppsReportParameter> closeAction;
    DefaultNewAction<AppsReportParameter> newAction;
    DefaultDeleteAction<AppsReportParameter> deleteAction;
    DefaultRefreshAction<AppsReportParameter> refreshAction;
    DefaultSaveAction<AppsReportParameter> saveAction;     
}
