/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import javax.persistence.EntityManager;
import javax.swing.JButton;

import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultFileChooserOpenAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.AppsReportParameter;
import org.jw.service.gui.component.DefaultCrudPanel;
import org.jw.service.listener.selection.ReportListSelectionListener;
import org.jw.service.util.UtilityProperties;
import org.jw.service.action.dependency.ParameterNewPostDependency;
import org.jw.service.action.dependency.JasperFileChoosePostDependency;
import org.jw.service.file.filter.FileFilterJasper;

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
    }
    
    private void taskBuilder2(){
        DefaultCloseAction<AppsReportParameter> closeAction = null;
        DefaultNewAction<AppsReportParameter> newAction = null;
        DefaultDeleteAction<AppsReportParameter> deleteAction = null;
        DefaultRefreshAction<AppsReportParameter> refreshAction = null;
        DefaultSaveAction<AppsReportParameter> saveAction = null;        
        DataAccessObject<AppsReportParameter> dao;    
        
        DefaultCrudPanel paramCrudPanel = new DefaultCrudPanel();
        paramCrudPanel.setNewCommand(newParamCommand);
        paramCrudPanel.setDeleteCommand(deleteParamCommand);
        paramCrudPanel.setRefreshCommand(refreshParamCommand);
        paramCrudPanel.setSaveCommand(saveParamCommand);
        paramCrudPanel.setCloseCommand(new JButton());
        
        reportTable.getSelectionModel().addListSelectionListener(ReportListSelectionListener.create(reportList, parameterList));
        
        dao = DataAccessObject.create(em, AppsReportParameter.class);            
        //((ObservableList)serviceGroupList).addObservableListListener(listListener);                
        parameterList.addAll(dao.readAll());
        DefaultTaskBuilder<AppsReport> taskBuilder = new DefaultTaskBuilder();
        taskBuilder.setEntityName("report.parameter");
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setCrudPanel(paramCrudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(parameterList);
        taskBuilder.setTable(parameterTable);
        taskBuilder.setWindow(this);
        taskBuilder.setDao(dao);
        taskBuilder.buildDefaultTasks();
        
        ParameterNewPostDependency parameterNewPostDependency = new ParameterNewPostDependency(dao, reportList, reportTable);
        taskBuilder.getNewAction().addPostActionCommands("parameterNewPostDependency", parameterNewPostDependency);
    }
    
    private void taskBuilder1(){
        DefaultCloseAction<AppsReport> closeAction = null;
        DefaultNewAction<AppsReport> newAction = null;
        DefaultDeleteAction<AppsReport> deleteAction = null;
        DefaultRefreshAction<AppsReport> refreshAction = null;
        DefaultSaveAction<AppsReport> saveAction = null;        
        DefaultFileChooserOpenAction fcOpenAction = null;
        DataAccessObject<AppsReport> dao;    
        
        dao = DataAccessObject.create(em, AppsReport.class);            
        //((ObservableList)serviceGroupList).addObservableListListener(listListener);        
        reportList.addAll(dao.readAll());
        DefaultTaskBuilder<AppsReport> taskBuilder = new DefaultTaskBuilder();
        taskBuilder.setEntityName("apps.report");
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setCrudPanel(crudPanel);
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
        fcOpenAction = new DefaultFileChooserOpenAction(this.chooseFileCommand, this, FileFilterJasper.create() ,null);
        fcOpenAction.addPostActionCommands("jasperFileChoosePostDependency", jasperFileChoosePostDependency);
    }
    
    private  void initMyComponents(){
        taskBuilder1();
        taskBuilder2();
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
        parameterList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.AppsReportParameter>());
        byteArrayBean = new org.jw.service.beans.ByteArrayBean();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        crudPanel = new org.jw.service.gui.component.DefaultCrudPanel();
        templatesPanel = new javax.swing.JPanel();
        reportScrollPane = new javax.swing.JScrollPane();
        reportTable = new javax.swing.JTable();
        reportTabbedPane = new javax.swing.JTabbedPane();
        reportTab = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        queryLabel = new javax.swing.JLabel();
        codeTextField = new javax.swing.JTextField();
        reportDateLabel = new javax.swing.JLabel();
        reportDateChooser = new com.toedter.calendar.JDateChooser();
        enableCheckBox = new javax.swing.JCheckBox();
        nameTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        queryTextArea = new javax.swing.JTextArea();
        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        visibleCheckBox = new javax.swing.JCheckBox();
        descriptionTextField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        reportTypeLabel = new javax.swing.JLabel();
        reportTypeComboBox = new javax.swing.JComboBox();
        lineLimitLabel = new javax.swing.JLabel();
        lineLimitTextField = new javax.swing.JTextField();
        reportFileTab = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jasperNameLabel = new javax.swing.JLabel();
        jasperCreatedLabel = new javax.swing.JLabel();
        jasperModifiedLabel = new javax.swing.JLabel();
        fileNameTextField = new javax.swing.JTextField();
        chooseFileCommand = new javax.swing.JButton();
        fileCreatedDateChooser = new com.toedter.calendar.JDateChooser();
        fileModifiedDateChooser = new com.toedter.calendar.JDateChooser();
        parametersTab = new javax.swing.JPanel();
        parametersPanel = new javax.swing.JPanel();
        sequenceLabel = new javax.swing.JLabel();
        paramNameLabel = new javax.swing.JLabel();
        defaultValueLabel = new javax.swing.JLabel();
        sequenceTextField = new javax.swing.JTextField();
        paramNameTextField = new javax.swing.JTextField();
        datalTypeLabel = new javax.swing.JLabel();
        paramDataTypeComboBox = new javax.swing.JComboBox();
        parameterScrollPane = new javax.swing.JScrollPane();
        parameterTable = new javax.swing.JTable();
        saveParamCommand = new javax.swing.JButton();
        refreshParamCommand = new javax.swing.JButton();
        deleteParamCommand = new javax.swing.JButton();
        newParamCommand = new javax.swing.JButton();
        paramLabel = new javax.swing.JLabel();
        paramLabelTextField = new javax.swing.JTextField();
        defaultValueTextField = new javax.swing.JTextField();
        enableParamCheckBox = new javax.swing.JCheckBox();
        dependsOnLabel = new javax.swing.JLabel();
        dependsOnTextField = new javax.swing.JTextField();
        requiredCheckBox = new javax.swing.JCheckBox();
        lovCommand = new javax.swing.JButton();

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
                .addComponent(reportScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        codeLabel.setText("Code:");

        nameLabel.setText("Name:");

        queryLabel.setText("Query:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.code}"), codeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "name");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        codeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeTextFieldActionPerformed(evt);
            }
        });

        reportDateLabel.setText("Date:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.reportDate}"), reportDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"), "reportDate");
        bindingGroup.addBinding(binding);

        enableCheckBox.setText("Enable");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"), "enable");
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "description");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        queryTextArea.setColumns(20);
        queryTextArea.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.query}"), queryTextArea, org.jdesktop.beansbinding.BeanProperty.create("text"), "query");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(queryTextArea);

        titleLabel.setText("Title:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.title}"), titleTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        visibleCheckBox.setText("Visible");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.visible}"), visibleCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        descriptionLabel.setText("Description:");

        reportTypeLabel.setText("Type:");

        reportTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.reportTypesProperties.getValues().toArray()));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.reportType}"), reportTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        lineLimitLabel.setText("Line Limit:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.lineLimit}"), lineLimitTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(queryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(descriptionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionTextField)
                            .addComponent(titleTextField)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(codeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(reportTypeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reportTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lineLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(reportDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reportDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enableCheckBox)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(visibleCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(lineLimitTextField))))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {codeLabel, descriptionLabel, nameLabel, queryLabel, titleLabel});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {reportDateLabel, reportTypeLabel});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(codeLabel)
                            .addComponent(codeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reportDateLabel))
                        .addComponent(reportDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enableCheckBox)
                        .addComponent(visibleCheckBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reportTypeLabel)
                    .addComponent(reportTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lineLimitLabel)
                    .addComponent(lineLimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(queryLabel)
                        .addGap(0, 68, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout reportTabLayout = new javax.swing.GroupLayout(reportTab);
        reportTab.setLayout(reportTabLayout);
        reportTabLayout.setHorizontalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        reportTabLayout.setVerticalGroup(
            reportTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        reportTabbedPane.addTab("Report", reportTab);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jasper Report", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jasperNameLabel.setText("Name:");

        jasperCreatedLabel.setText("Created:");

        jasperModifiedLabel.setText("Modified:");

        fileNameTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, reportTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fileName}"), fileNameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "fileName");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        chooseFileCommand.setText("Choose");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jasperNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jasperModifiedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileModifiedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jasperCreatedLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileCreatedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseFileCommand)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jasperCreatedLabel, jasperModifiedLabel, jasperNameLabel});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jasperNameLabel)
                    .addComponent(fileNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseFileCommand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fileCreatedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jasperCreatedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fileModifiedDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jasperModifiedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(111, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout reportFileTabLayout = new javax.swing.GroupLayout(reportFileTab);
        reportFileTab.setLayout(reportFileTabLayout);
        reportFileTabLayout.setHorizontalGroup(
            reportFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportFileTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        reportFileTabLayout.setVerticalGroup(
            reportFileTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportFileTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        reportTabbedPane.addTab("Jasper File", reportFileTab);

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parameters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        sequenceLabel.setText("Sequence:");

        paramNameLabel.setText("Name:");

        defaultValueLabel.setText("Default Value:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.sequence}"), sequenceTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), paramNameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        datalTypeLabel.setText("Datal Type:");

        paramDataTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.dataTypesProperties.getValues().toArray()));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dataType}"), paramDataTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        parameterTable.setAutoCreateRowSorter(true);
        parameterTable.setShowHorizontalLines(false);
        parameterTable.setShowVerticalLines(false);

        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterList, parameterTable);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${sequence}"));
        columnBinding.setColumnName("Sequence");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${name}"));
        columnBinding.setColumnName("Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${label}"));
        columnBinding.setColumnName("Label");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dataType}"));
        columnBinding.setColumnName("Data Type");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${defaultValue}"));
        columnBinding.setColumnName("Default");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enable}"));
        columnBinding.setColumnName("Enable");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${required}"));
        columnBinding.setColumnName("Required");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        parameterScrollPane.setViewportView(parameterTable);

        saveParamCommand.setText("Save");

        refreshParamCommand.setText("Refresh");

        deleteParamCommand.setText("Delete");

        newParamCommand.setText("New");

        paramLabel.setText("Label:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.label}"), paramLabelTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.defaultValue}"), defaultValueTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        enableParamCheckBox.setText("Enable");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableParamCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        dependsOnLabel.setText("Depends On:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.dependsOn}"), dependsOnTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        requiredCheckBox.setText("Required");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, parameterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.required}"), requiredCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        binding.setSourceNullValue(false);
        binding.setSourceUnreadableValue(false);
        bindingGroup.addBinding(binding);

        lovCommand.setText("LOV");
        lovCommand.setToolTipText("List of Values");

        javax.swing.GroupLayout parametersPanelLayout = new javax.swing.GroupLayout(parametersPanel);
        parametersPanel.setLayout(parametersPanelLayout);
        parametersPanelLayout.setHorizontalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parametersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newParamCommand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteParamCommand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshParamCommand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveParamCommand)
                .addGap(9, 9, 9))
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(parameterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                    .addGroup(parametersPanelLayout.createSequentialGroup()
                        .addComponent(sequenceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sequenceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datalTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paramDataTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(parametersPanelLayout.createSequentialGroup()
                        .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, parametersPanelLayout.createSequentialGroup()
                                .addComponent(paramLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paramLabelTextField))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, parametersPanelLayout.createSequentialGroup()
                                .addComponent(paramNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paramNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addComponent(defaultValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(defaultValueTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lovCommand))
                            .addGroup(parametersPanelLayout.createSequentialGroup()
                                .addComponent(dependsOnLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dependsOnTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enableParamCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(requiredCheckBox)))))
                .addContainerGap())
        );

        parametersPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {paramLabel, paramNameLabel, sequenceLabel});

        parametersPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {datalTypeLabel, defaultValueLabel, dependsOnLabel});

        parametersPanelLayout.setVerticalGroup(
            parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sequenceLabel)
                    .addComponent(sequenceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datalTypeLabel)
                    .addComponent(paramDataTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramNameLabel)
                    .addComponent(paramNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultValueLabel)
                    .addComponent(defaultValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lovCommand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paramLabel)
                    .addComponent(paramLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enableParamCheckBox)
                    .addComponent(dependsOnLabel)
                    .addComponent(dependsOnTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requiredCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(parameterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(parametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteParamCommand)
                    .addComponent(refreshParamCommand)
                    .addComponent(saveParamCommand)
                    .addComponent(newParamCommand)))
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

        reportTabbedPane.addTab("Parameters", parametersTab);

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
                    .addComponent(crudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(templatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportTabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(templatesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTextFieldActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.beans.ByteArrayBean byteArrayBean;
    private javax.swing.JButton chooseFileCommand;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTextField;
    private org.jw.service.gui.component.DefaultCrudPanel crudPanel;
    private javax.swing.JLabel datalTypeLabel;
    private javax.swing.JLabel defaultValueLabel;
    private javax.swing.JTextField defaultValueTextField;
    private javax.swing.JButton deleteParamCommand;
    private javax.swing.JLabel dependsOnLabel;
    private javax.swing.JTextField dependsOnTextField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JCheckBox enableParamCheckBox;
    private com.toedter.calendar.JDateChooser fileCreatedDateChooser;
    private com.toedter.calendar.JDateChooser fileModifiedDateChooser;
    private javax.swing.JTextField fileNameTextField;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jasperCreatedLabel;
    private javax.swing.JLabel jasperModifiedLabel;
    private javax.swing.JLabel jasperNameLabel;
    private javax.swing.JLabel lineLimitLabel;
    private javax.swing.JTextField lineLimitTextField;
    private javax.swing.JButton lovCommand;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton newParamCommand;
    private javax.swing.JComboBox paramDataTypeComboBox;
    private javax.swing.JLabel paramLabel;
    private javax.swing.JTextField paramLabelTextField;
    private javax.swing.JLabel paramNameLabel;
    private javax.swing.JTextField paramNameTextField;
    private java.util.List<org.jw.service.entity.AppsReportParameter> parameterList;
    private javax.swing.JScrollPane parameterScrollPane;
    private javax.swing.JTable parameterTable;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.JPanel parametersTab;
    private javax.swing.JLabel queryLabel;
    private javax.swing.JTextArea queryTextArea;
    private javax.swing.JButton refreshParamCommand;
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
    private javax.swing.JCheckBox requiredCheckBox;
    private javax.swing.JButton saveParamCommand;
    private javax.swing.JLabel sequenceLabel;
    private javax.swing.JTextField sequenceTextField;
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
