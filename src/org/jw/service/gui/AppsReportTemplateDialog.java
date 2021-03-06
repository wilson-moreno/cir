/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultFileChooserAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.action.dependency.JasperFileChoosePostDependency;
import org.jw.service.action.dependency.JasperFileExtractPostDependency;
import org.jw.service.action.validator.DefaultCloseActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.action.validator.DefaultUniqueFieldsSaveActionValidator;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.file.filter.FileFilterDirectory;
import org.jw.service.file.filter.FileFilterJasper;
import org.jw.service.list.AppsReportMatcher;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class AppsReportTemplateDialog extends javax.swing.JDialog {
    private final EntityManager em;
    
    /**
     * Creates new form AppsReportDialog
     * @param parent
     * @param modal
     * @param em
     */
    public AppsReportTemplateDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);
        this.em = em;
        initComponents();
        initMyComponents();
        initDateFormatString();
    }
    
    public void initDateFormatString(){
        this.reportDateChooser.setDateFormatString("MMM d, yyyy");
    }
    
    private void taskBuilder1(){
        DefaultCloseAction<AppsReport> closeAction = null;
        DefaultNewAction<AppsReport> newAction = null;
        DefaultDeleteAction<AppsReport> deleteAction = null;
        DefaultRefreshAction<AppsReport> refreshAction = null;
        DefaultSaveAction<AppsReport> saveAction = null;        
        DefaultFileChooserAction fcOpenAction = null;
        DefaultFileChooserAction dirOpenAction = null;
        DataAccessObject<AppsReport> dao;    
        
        dao = DataAccessObject.create(em, AppsReport.class);            
        //((ObservableList)serviceGroupList).addObservableListListener(listListener);        
        reportList.addAll(dao.readAll());
        DefaultTaskBuilder<AppsReport> taskBuilder = new DefaultTaskBuilder();
        taskBuilder.setEntityName("apps.report");
        taskBuilder.setQuery(em.createNamedQuery("AppsReport.findAll", AppsReport.class));
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(crudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(reportList);
        taskBuilder.setTable(reportTable);
        taskBuilder.setWindow(this);
        taskBuilder.setDao(dao);
        taskBuilder.buildDefaultTasks();
        
        JasperFileChoosePostDependency jasperFileChoosePostDependency = new JasperFileChoosePostDependency(this.byteArrayBean, this.fileNameTextField, this.fileCreatedDateChooser, this.fileModifiedDateChooser);
        fcOpenAction = new DefaultFileChooserAction(this.chooseFileCommand, this, FileFilterJasper.create(), "Open" , JFileChooser.FILES_ONLY,null);
        fcOpenAction.addPostActionCommands("jasperFileChoosePostDependency", jasperFileChoosePostDependency);
        fcOpenAction.setEnabled(false);
        
        
        
        UtilityTable<AppsReport> utilTable = UtilityTable.create(reportTable, reportList);
        setActionValidators(taskBuilder, utilTable);
        
        JasperFileExtractPostDependency jasperFileExtractPostDependency = new JasperFileExtractPostDependency(this,utilTable);
        dirOpenAction = new DefaultFileChooserAction(this.extractFileCommand, this, FileFilterDirectory.create(), "Save", JFileChooser.DIRECTORIES_ONLY, null);        
        dirOpenAction.addPostActionCommands("", jasperFileExtractPostDependency);
        dirOpenAction.setEnabled(false);
        
        this.reportTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener(){
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if(!e.getValueIsAdjusting()){
                            parametersCommand.setEnabled(true);
                            chooseFileCommand.setEnabled(true);
                            extractFileCommand.setEnabled(true);
                        }
                    }                    
                }
        );
        
    }
    
    private void setActionValidators(DefaultTaskBuilder taskBuilder, UtilityTable<AppsReport> utilTable){
        // Create Matchers
        AppsReportMatcher matcher = new AppsReportMatcher();
        
        // Create Validators
        DefaultUniqueFieldsSaveActionValidator uniqueFieldValueValidator = new DefaultUniqueFieldsSaveActionValidator(this, reportList, utilTable, matcher, "report");
        DefaultRequiredFieldsSaveActionValidator requiredFieldValueValidator = new DefaultRequiredFieldsSaveActionValidator(this, utilTable, "report");
        DefaultCloseActionValidator closeActionValidator = new DefaultCloseActionValidator(this, utilTable);
        
        // Set Validators
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);
        taskBuilder.getSaveAction().addActionValidator(uniqueFieldValueValidator);
        taskBuilder.getSaveAction().addActionValidator(requiredFieldValueValidator);
    }
    
        
    
    private  void initMyComponents(){
        taskBuilder1();
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

        reportList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.AppsReport>());
        byteArrayBean = new org.jw.service.beans.ByteArrayBean();
        datasourceTypeListBean = new org.jw.service.beans.ListBean("datasource_types.properties");
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        templatesPanel = new javax.swing.JPanel();
        reportScrollPane = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        reportTabbedPane = new javax.swing.JTabbedPane();
        reportTab = new javax.swing.JPanel();
        propertyPanel = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        reportDateLabel = new javax.swing.JLabel();
        reportDateChooser = new com.toedter.calendar.JDateChooser();
        enableCheckBox = new javax.swing.JCheckBox();
        nameTextField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        visibleCheckBox = new javax.swing.JCheckBox();
        descriptionTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        reportTypeLabel = new javax.swing.JLabel();
        reportTypeComboBox = new javax.swing.JComboBox();
        lineLimitLabel = new javax.swing.JLabel();
        lineLimitTextField = new javax.swing.JTextField();
        parametersCommand = new javax.swing.JButton();
        dataSourceLabel = new javax.swing.JLabel();
        dataSourceComboBox = new javax.swing.JComboBox();
        reportFileTab = new javax.swing.JPanel();
        jasperReportPanel = new javax.swing.JPanel();
        jasperNameLabel = new javax.swing.JLabel();
        jasperCreatedLabel = new javax.swing.JLabel();
        jasperModifiedLabel = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        chooseFileCommand = new javax.swing.JButton();
        fileCreatedDateChooser = new com.toedter.calendar.JDateChooser();
        fileModifiedDateChooser = new com.toedter.calendar.JDateChooser();
        defaultTemplateCheckBox = new javax.swing.JCheckBox();
        extractFileCommand = new javax.swing.JButton();
        parametersTab = new javax.swing.JPanel();
        parametersPanel = new javax.swing.JPanel();
        queryScrollPane = new javax.swing.JScrollPane();
        queryTextArea = new javax.swing.JTextArea();
        crudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fileJasper}"), byteArrayBean, org.jdesktop.beansbinding.BeanProperty.create("byteArray"), "jasperContent");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("report.templates.dialog.title")); // NOI18N
        setResizable(false);

        templatesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Templates", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        reportTable.setAutoCreateRowSorter(true);
        reportTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        reportTable.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportList, reportTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${code}"));
        columnBinding.setColumnName("Code");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${title}"));
        columnBinding.setColumnName("Title");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${reportType}"));
        columnBinding.setColumnName("Type");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enable}"));
        columnBinding.setColumnName("Enable");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${visible}"));
        columnBinding.setColumnName("Visible");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        reportScrollPane.setViewportView(reportTable);
        if (reportTable.getColumnModel().getColumnCount() > 0) {
            reportTable.getColumnModel().getColumn(0).setResizable(false);
            reportTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            reportTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
        }

        javax.swing.GroupLayout templatesPanelLayout = new javax.swing.GroupLayout(templatesPanel);
        templatesPanel.setLayout(templatesPanelLayout);
        templatesPanelLayout.setHorizontalGroup(
            templatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(templatesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportScrollPane)
                .addContainerGap())
        );
        templatesPanelLayout.setVerticalGroup(
            templatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(templatesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        propertyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        codeLabel.setLabelFor(codeTextField);
        codeLabel.setText("Code:");

        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText("Name:");

        codeTextField.setDocument(documentFilterFactory.getSizeFilter30());
        codeTextField.setNextFocusableComponent(reportDateChooser);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.code}"), codeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Code");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        reportDateLabel.setLabelFor(reportDateChooser);
        reportDateLabel.setText("Date:");

        reportDateChooser.setNextFocusableComponent(enableCheckBox);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.reportDate}"), reportDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"), "reportDate");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        enableCheckBox.setText("Enable");
        enableCheckBox.setNextFocusableComponent(visibleCheckBox);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"), "enable");
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        nameTextField.setDocument(documentFilterFactory.getSizeFilter30());
        nameTextField.setNextFocusableComponent(reportTypeComboBox);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Report Name");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        titleLabel.setLabelFor(titleTextField);
        titleLabel.setText("Title:");

        titleTextField.setDocument(documentFilterFactory.getSizeFilter50());
        titleTextField.setNextFocusableComponent(descriptionTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.title}"), titleTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Title");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        visibleCheckBox.setText("Visible");
        visibleCheckBox.setNextFocusableComponent(nameTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.visible}"), visibleCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        descriptionTextField.setDocument(documentFilterFactory.getSizeFilter150());
        descriptionTextField.setNextFocusableComponent(queryTextArea);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        descriptionLabel.setLabelFor(descriptionTextField);
        descriptionLabel.setText("Description:");

        reportTypeLabel.setLabelFor(reportTypeComboBox);
        reportTypeLabel.setText("Type:");

        reportTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.reportTypesProperties.getValues().toArray()));
        reportTypeComboBox.setNextFocusableComponent(lineLimitTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.reportType}"), reportTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        lineLimitLabel.setLabelFor(lineLimitTextField);
        lineLimitLabel.setText("Line Limit:");

        lineLimitTextField.setNextFocusableComponent(titleTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.lineLimit}"), lineLimitTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        parametersCommand.setText("Parameters");
        parametersCommand.setEnabled(false);
        parametersCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametersCommandActionPerformed(evt);
            }
        });

        dataSourceLabel.setText("Data Source:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, datasourceTypeListBean, eLProperty, dataSourceComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.datasourceType}"), dataSourceComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout propertyPanelLayout = new javax.swing.GroupLayout(propertyPanel);
        propertyPanel.setLayout(propertyPanelLayout);
        propertyPanelLayout.setHorizontalGroup(
            propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(descriptionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parametersCommand, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(codeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(reportDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(reportTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reportTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(lineLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lineLimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(propertyPanelLayout.createSequentialGroup()
                        .addComponent(dataSourceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(propertyPanelLayout.createSequentialGroup()
                                .addComponent(enableCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(visibleCheckBox))
                            .addComponent(dataSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        propertyPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {codeLabel, descriptionLabel, nameLabel, titleLabel});

        propertyPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dataSourceLabel, lineLimitLabel, reportDateLabel, reportTypeLabel});

        propertyPanelLayout.setVerticalGroup(
            propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(codeLabel)
                        .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reportDateLabel))
                    .addComponent(reportDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportTypeLabel)
                    .addComponent(reportTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lineLimitLabel)
                    .addComponent(lineLimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel)
                    .addComponent(dataSourceLabel)
                    .addComponent(dataSourceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parametersCommand)
                    .addComponent(enableCheckBox)
                    .addComponent(visibleCheckBox))
                .addContainerGap())
        );

        javax.swing.GroupLayout reportTabLayout = new javax.swing.GroupLayout(reportTab);
        reportTab.setLayout(reportTabLayout);
        reportTabLayout.setHorizontalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        reportTabLayout.setVerticalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propertyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        reportTabbedPane.addTab("Report", reportTab);

        jasperReportPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jasper Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jasperNameLabel.setLabelFor(fileNameTextField);
        jasperNameLabel.setText("Name:");

        jasperCreatedLabel.setLabelFor(fileCreatedDateChooser);
        jasperCreatedLabel.setText("Created:");

        jasperModifiedLabel.setLabelFor(fileModifiedDateChooser);
        jasperModifiedLabel.setText("Modified:");

        fileNameTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fileName}"), fileNameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "fileName");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        chooseFileCommand.setText("Choose");
        chooseFileCommand.setEnabled(false);

        fileCreatedDateChooser.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fileCreatedDatetime}"), fileCreatedDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        fileModifiedDateChooser.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fileModifiedDatetime}"), fileModifiedDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        defaultTemplateCheckBox.setText("Default Contact Record Report Template");

        extractFileCommand.setText("Extract");
        extractFileCommand.setEnabled(false);
        extractFileCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extractFileCommandActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jasperReportPanelLayout = new javax.swing.GroupLayout(jasperReportPanel);
        jasperReportPanel.setLayout(jasperReportPanelLayout);
        jasperReportPanelLayout.setHorizontalGroup(
            jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jasperReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jasperReportPanelLayout.createSequentialGroup()
                        .addComponent(jasperModifiedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jasperReportPanelLayout.createSequentialGroup()
                                .addComponent(defaultTemplateCheckBox)
                                .addGap(0, 179, Short.MAX_VALUE))
                            .addComponent(fileModifiedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jasperReportPanelLayout.createSequentialGroup()
                        .addComponent(jasperNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooseFileCommand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(extractFileCommand, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jasperReportPanelLayout.createSequentialGroup()
                        .addComponent(jasperCreatedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileCreatedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jasperReportPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jasperCreatedLabel, jasperModifiedLabel, jasperNameLabel});

        jasperReportPanelLayout.setVerticalGroup(
            jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jasperReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jasperNameLabel)
                    .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseFileCommand)
                    .addComponent(extractFileCommand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fileCreatedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jasperCreatedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jasperReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fileModifiedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jasperModifiedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultTemplateCheckBox)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reportFileTabLayout = new javax.swing.GroupLayout(reportFileTab);
        reportFileTab.setLayout(reportFileTabLayout);
        reportFileTabLayout.setHorizontalGroup(
            reportFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportFileTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jasperReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        reportFileTabLayout.setVerticalGroup(
            reportFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportFileTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jasperReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        reportTabbedPane.addTab("Jasper File", reportFileTab);

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Query", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        queryTextArea.setColumns(20);
        queryTextArea.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.query}"), queryTextArea, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        queryScrollPane.setViewportView(queryTextArea);

        javax.swing.GroupLayout parametersPanelLayout = new javax.swing.GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        parametersPanelLayout.setVerticalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout parametersTabLayout = new javax.swing.GroupLayout(parametersTab);
        parametersTab.setLayout(parametersTabLayout);
        parametersTabLayout.setHorizontalGroup(
            parametersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        parametersTabLayout.setVerticalGroup(
            parametersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(parametersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        reportTabbedPane.addTab("Query", parametersTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(templatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportTabbedPane)
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(templatesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void parametersCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametersCommandActionPerformed
        // TODO add your handling code here:
        ReportParameterDialog paramDialog = new ReportParameterDialog(this, true, em, UtilityTable.create(reportTable, reportList));
        paramDialog.setLocationRelativeTo(this);
        paramDialog.setVisible(true);
    }//GEN-LAST:event_parametersCommandActionPerformed

    private void extractFileCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extractFileCommandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_extractFileCommandActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.beans.ByteArrayBean byteArrayBean;
    private javax.swing.JButton chooseFileCommand;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTextField;
    private org.jw.service.gui.component.MultipleRecordCrudPanel crudPanel;
    private javax.swing.JComboBox dataSourceComboBox;
    private javax.swing.JLabel dataSourceLabel;
    private org.jw.service.beans.ListBean datasourceTypeListBean;
    private javax.swing.JCheckBox defaultTemplateCheckBox;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JButton extractFileCommand;
    private com.toedter.calendar.JDateChooser fileCreatedDateChooser;
    private com.toedter.calendar.JDateChooser fileModifiedDateChooser;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JLabel jasperCreatedLabel;
    private javax.swing.JLabel jasperModifiedLabel;
    private javax.swing.JLabel jasperNameLabel;
    private javax.swing.JPanel jasperReportPanel;
    private javax.swing.JLabel lineLimitLabel;
    private javax.swing.JTextField lineLimitTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton parametersCommand;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.JPanel parametersTab;
    private javax.swing.JPanel propertyPanel;
    private javax.swing.JScrollPane queryScrollPane;
    private javax.swing.JTextArea queryTextArea;
    private com.toedter.calendar.JDateChooser reportDateChooser;
    private javax.swing.JLabel reportDateLabel;
    private javax.swing.JPanel reportFileTab;
    private java.util.List<org.jw.service.entity.AppsReport> reportList;
    private javax.swing.JScrollPane reportScrollPane;
    private javax.swing.JPanel reportTab;
    private javax.swing.JTabbedPane reportTabbedPane;
    private javax.swing.JTable reportTable;
    private javax.swing.JComboBox reportTypeComboBox;
    private javax.swing.JLabel reportTypeLabel;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JPanel templatesPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JCheckBox visibleCheckBox;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    UtilityProperties controlTypesProperties = UtilityProperties.create(UtilityProperties.CONTROL_TYPES_PROPERTIES);
    UtilityProperties dataTypesProperties = UtilityProperties.create(UtilityProperties.DATA_TYPE_PROPERTIES);        
    UtilityProperties reportTypesProperties = UtilityProperties.create(UtilityProperties.REPORT_TYPE_PROPERTIES);        
}
