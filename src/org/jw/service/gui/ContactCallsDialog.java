/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.awt.KeyboardFocusManager;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDeleteAction;
import org.jw.service.action.DefaultNewAction;
import org.jw.service.action.DefaultRefreshAction;
import org.jw.service.action.DefaultSaveAction;
import org.jw.service.action.dependency.NewCallPostDependency;
import org.jw.service.action.validator.DefaultCloseActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.builder.DefaultTaskBuilder;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.CallStatus;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactCall;
import org.jw.service.entity.EntityIO;
import org.jw.service.gui.focus.DefaultFocusTraversalPolicy;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class ContactCallsDialog extends javax.swing.JDialog {    
    private final UtilityTable<Contact> utilTable;
    private final EntityIO<Contact> contactIO;
    private final DataAccessObject<Contact> contactDAO;
    private final DataAccessObject<ContactCall> callDAO;
    private final DataAccessObject<CallStatus> callStatusDAO;
    private final DefaultTaskBuilder<ContactCall> taskBuilder;    
    private final EntityManager em;
    private Contact contactTarget;
    
    /**
     * Creates new form ContactCallDialog
     */
    public ContactCallsDialog(java.awt.Frame parent, boolean modal, EntityManager em, UtilityTable<Contact> utilTable) {
        super(parent, modal);
        this.contactDAO = DataAccessObject.create(em, Contact.class);
        this.callDAO = DataAccessObject.create(em, ContactCall.class);
        this.callStatusDAO = DataAccessObject.create(em, CallStatus.class);
        this.utilTable = utilTable;
        this.contactIO = EntityIO.create(Contact.class);
        this.em = em;
        this.taskBuilder = new DefaultTaskBuilder();
        initComponents(); 
        initMyComponents();
        initDateFormatString();
    }
    
    public void initDateFormatString(){
        this.callDateChooser.setDateFormatString("MMM d, yyyy");
    }
    
    private void setSelectedContact(){        
        contactTarget = utilTable.getSelectedItem();
        contactIO.setSource(contactSource);
        contactIO.setTarget(contactTarget);
        contactIO.read();        
    }
    
    private void initMyComponents(){     
        setSelectedContact();
        setContactCalls();        
        taskBuilder.setEntityName("calls");
        taskBuilder.setProperties(taskMessageProperties);
        taskBuilder.setMultipleRecordCrudPanel(crudPanel);
        taskBuilder.setTaskMonitorPanel(taskMonitorPanel);
        taskBuilder.setCloseAction(closeAction);
        taskBuilder.setNewAction(newAction);
        taskBuilder.setDeleteAction(deleteAction);
        taskBuilder.setRefreshAction(refreshAction);
        taskBuilder.setSaveAction(saveAction);        
        taskBuilder.setList(contactCallsList);
        taskBuilder.setTable(callsTable);
        taskBuilder.setWindow(this); 
        taskBuilder.setDao(callDAO);
        
        Query query = em.createQuery("SELECT c FROM ContactCall c WHERE c.contactId = :contactId");
        query.setParameter("contactId", contactTarget);
        taskBuilder.setQuery(query);
        
        taskBuilder.buildDefaultTasks();
        
        traversalPolicy = DefaultFocusTraversalPolicy.create();
        traversalPolicy.addComponent(this.callDateChooser);
        traversalPolicy.addComponent(this.dayComboBox);
        traversalPolicy.addComponent(this.callTimeTextField);
        traversalPolicy.addComponent(this.statusComboBox);
        traversalPolicy.addComponent(this.callNotesTextField);
        traversalPolicy.addComponent(this.scripturesTextField);
        traversalPolicy.addComponent(this.literatureTextField);
        traversalPolicy.addComponent(this.publishersTextField);
        this.setFocusTraversalPolicy(traversalPolicy);
        
        UtilityTable utilTable = UtilityTable.create(callsTable, contactCallsList);
        setActionValidators(taskBuilder, utilTable);
        
        NewCallPostDependency newCallPostDependency = new NewCallPostDependency(callDAO, contactTarget, this.callDateChooser);
        taskBuilder.getNewAction().addPostActionCommands("newCallPostDependency", newCallPostDependency);                                     
    }
    
    
    
    
    private void setActionValidators(DefaultTaskBuilder<ContactCall> taskBuilder, UtilityTable<ContactCall> utilTable){
        
        
        // Create Validators
        DefaultRequiredFieldsSaveActionValidator requiredFieldValidator = new DefaultRequiredFieldsSaveActionValidator(this, utilTable, "call");
        DefaultCloseActionValidator closeActionValidator = new DefaultCloseActionValidator(this, utilTable);
        
        // Set Validators
        taskBuilder.getCloseAction().addActionValidator(closeActionValidator);
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

        contactCallsList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.ContactCall>());
        contactSource = new org.jw.service.entity.Contact();
        stringToTimeConverter = new org.jw.service.beansbinding.converter.StringToTimeConverter();
        statusListBean = new org.jw.service.beans.ListBean(callStatusDAO);
        defaultDateCellRenderer = new org.jw.service.table.cell.renderer.DefaultDateCellRenderer();
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        crudPanel = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        callsPanel = new javax.swing.JPanel();
        callsScrollPane = new javax.swing.JScrollPane();
        callsTable = new javax.swing.JTable();
        contactInfoPanel = new org.jw.service.gui.component.ContactInfoPanel();
        callInformationTabbedPane = new javax.swing.JTabbedPane();
        primaryCallTab = new javax.swing.JPanel();
        contactCallPanel = new javax.swing.JPanel();
        callDateLabel = new javax.swing.JLabel();
        callDateChooser = new com.toedter.calendar.JDateChooser();
        callDayLabel = new javax.swing.JLabel();
        callTimeLabel = new javax.swing.JLabel();
        dayComboBox = new javax.swing.JComboBox();
        stausLabel = new javax.swing.JLabel();
        callNotesLabel = new javax.swing.JLabel();
        callNotesTextField = new javax.swing.JTextField();
        scripturesLabel = new javax.swing.JLabel();
        scripturesTextField = new javax.swing.JTextField();
        literatureLabel = new javax.swing.JLabel();
        literatureTextField = new javax.swing.JTextField();
        publishersLabel = new javax.swing.JLabel();
        publishersTextField = new javax.swing.JTextField();
        callTimeTextField = new javax.swing.JFormattedTextField();
        statusComboBox = new javax.swing.JComboBox();
        extraInformationTab = new javax.swing.JPanel();
        extraDetailsPanel = new javax.swing.JPanel();
        nextVisitLabel = new javax.swing.JLabel();
        nextVisitDateChooser = new com.toedter.calendar.JDateChooser();
        nextTopicLabel = new javax.swing.JLabel();
        nextTopicTextField = new javax.swing.JTextField();

        defaultDateCellRenderer.setText("defaultDateCellRenderer1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("calls.dialog.title")); // NOI18N
        setModal(true);

        callsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Calls", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        callsTable.setAutoCreateRowSorter(true);
        callsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        callsTable.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactCallsList, callsTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${callDate}"));
        columnBinding.setColumnName("Call Date");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${callDay}"));
        columnBinding.setColumnName("Call Day");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${callTime}"));
        columnBinding.setColumnName("Call Time");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${callStatusId}"));
        columnBinding.setColumnName("Status");
        columnBinding.setColumnClass(org.jw.service.entity.CallStatus.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${notes}"));
        columnBinding.setColumnName("Notes");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        callsScrollPane.setViewportView(callsTable);
        if (callsTable.getColumnModel().getColumnCount() > 0) {
            callsTable.getColumnModel().getColumn(0).setResizable(false);
            callsTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            callsTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
            callsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            callsTable.getColumnModel().getColumn(1).setCellRenderer(defaultDateCellRenderer);
            callsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            callsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            callsTable.getColumnModel().getColumn(4).setPreferredWidth(50);
            callsTable.getColumnModel().getColumn(5).setPreferredWidth(400);
        }

        javax.swing.GroupLayout callsPanelLayout = new javax.swing.GroupLayout(callsPanel);
        callsPanel.setLayout(callsPanelLayout);
        callsPanelLayout.setHorizontalGroup(
            callsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(callsScrollPane)
                .addContainerGap())
        );
        callsPanelLayout.setVerticalGroup(
            callsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(callsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(callsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${firstName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("firstName"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${lastName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("lastName"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordDate}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordDate"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordNumber}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordNumber"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        contactCallPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Call Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        callDateLabel.setLabelFor(callDateChooser);
        callDateLabel.setText("Date:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.callDate}"), callDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"), "callDate");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        callDayLabel.setText("Day:");
        callDayLabel.setNextFocusableComponent(dayComboBox);

        callTimeLabel.setText("Time:");

        dayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.callDay}"), dayComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"), "callDay");
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        stausLabel.setText("Status:");

        callNotesLabel.setLabelFor(callNotesTextField);
        callNotesLabel.setText("Notes:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.notes}"), callNotesTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "callNotes");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        scripturesLabel.setLabelFor(scripturesTextField);
        scripturesLabel.setText("Scriptures:");

        scripturesTextField.setDocument(documentFilterFactory.getSizeFilter75());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.scriptures}"), scripturesTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "scriptures");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        literatureLabel.setLabelFor(literatureTextField);
        literatureLabel.setText("Literature:");

        literatureTextField.setDocument(documentFilterFactory.getSizeFilter75());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.literature}"), literatureTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "literature");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        publishersLabel.setLabelFor(publishersTextField);
        publishersLabel.setText("Publishers:");

        publishersTextField.setDocument(documentFilterFactory.getSizeFilter75());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.publishers}"), publishersTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "publishers");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        callTimeTextField.setDocument(documentFilterFactory.getSizeFilter10());
        callTimeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm a"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.callTime}"), callTimeTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setConverter(stringToTimeConverter);
        bindingGroup.addBinding(binding);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, statusListBean, eLProperty, statusComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.callStatusId}"), statusComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout contactCallPanelLayout = new javax.swing.GroupLayout(contactCallPanel);
        contactCallPanel.setLayout(contactCallPanelLayout);
        contactCallPanelLayout.setHorizontalGroup(
            contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactCallPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(callDayLabel)
                            .addComponent(callDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(callDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dayComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(callTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(callTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(stausLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(callNotesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(callNotesTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(scripturesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scripturesTextField))
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(literatureLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(literatureTextField))
                    .addGroup(contactCallPanelLayout.createSequentialGroup()
                        .addComponent(publishersLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(publishersTextField)))
                .addContainerGap())
        );

        contactCallPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {callDateLabel, callDayLabel, callTimeLabel, stausLabel});

        contactCallPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {callNotesLabel, literatureLabel, scripturesLabel});

        contactCallPanelLayout.setVerticalGroup(
            contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactCallPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(callNotesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(callNotesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(callDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(callDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(callDayLabel)
                    .addComponent(dayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scripturesLabel)
                    .addComponent(scripturesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(callTimeLabel)
                    .addComponent(literatureLabel)
                    .addComponent(literatureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(callTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactCallPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stausLabel)
                    .addComponent(publishersLabel)
                    .addComponent(publishersTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout primaryCallTabLayout = new javax.swing.GroupLayout(primaryCallTab);
        primaryCallTab.setLayout(primaryCallTabLayout);
        primaryCallTabLayout.setHorizontalGroup(
            primaryCallTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryCallTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contactCallPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        primaryCallTabLayout.setVerticalGroup(
            primaryCallTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryCallTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contactCallPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        callInformationTabbedPane.addTab("Primary Call Information", primaryCallTab);

        extraDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Extra Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nextVisitLabel.setText("Next Visit:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nextVisit}"), nextVisitDateChooser, org.jdesktop.beansbinding.BeanProperty.create("date"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        nextTopicLabel.setText("Next Topic:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, callsTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.nextTopic}"), nextTopicTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout extraDetailsPanelLayout = new javax.swing.GroupLayout(extraDetailsPanel);
        extraDetailsPanel.setLayout(extraDetailsPanelLayout);
        extraDetailsPanelLayout.setHorizontalGroup(
            extraDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extraDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nextVisitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextVisitDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextTopicLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextTopicTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );
        extraDetailsPanelLayout.setVerticalGroup(
            extraDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extraDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(extraDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nextVisitDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nextVisitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(extraDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nextTopicLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextTopicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout extraInformationTabLayout = new javax.swing.GroupLayout(extraInformationTab);
        extraInformationTab.setLayout(extraInformationTabLayout);
        extraInformationTabLayout.setHorizontalGroup(
            extraInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extraInformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(extraDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        extraInformationTabLayout.setVerticalGroup(
            extraInformationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(extraInformationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(extraDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        callInformationTabbedPane.addTab("Extra Information", extraInformationTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(callsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crudPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(callInformationTabbedPane, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(contactInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(callInformationTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(callsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setContactCalls(){
        contactDAO.refresh(contactTarget);
        contactCallsList.clear();
        contactCallsList.addAll(contactTarget.getContactCallCollection());        
        java.util.Collections.sort(contactCallsList);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser callDateChooser;
    private javax.swing.JLabel callDateLabel;
    private javax.swing.JLabel callDayLabel;
    private javax.swing.JTabbedPane callInformationTabbedPane;
    private javax.swing.JLabel callNotesLabel;
    private javax.swing.JTextField callNotesTextField;
    private javax.swing.JLabel callTimeLabel;
    private javax.swing.JFormattedTextField callTimeTextField;
    private javax.swing.JPanel callsPanel;
    private javax.swing.JScrollPane callsScrollPane;
    private javax.swing.JTable callsTable;
    private javax.swing.JPanel contactCallPanel;
    private java.util.List<org.jw.service.entity.ContactCall> contactCallsList;
    private org.jw.service.gui.component.ContactInfoPanel contactInfoPanel;
    private org.jw.service.entity.Contact contactSource;
    private org.jw.service.gui.component.MultipleRecordCrudPanel crudPanel;
    private javax.swing.JComboBox dayComboBox;
    private org.jw.service.table.cell.renderer.DefaultDateCellRenderer defaultDateCellRenderer;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JPanel extraDetailsPanel;
    private javax.swing.JPanel extraInformationTab;
    private javax.swing.JLabel literatureLabel;
    private javax.swing.JTextField literatureTextField;
    private javax.swing.JLabel nextTopicLabel;
    private javax.swing.JTextField nextTopicTextField;
    private com.toedter.calendar.JDateChooser nextVisitDateChooser;
    private javax.swing.JLabel nextVisitLabel;
    private javax.swing.JPanel primaryCallTab;
    private javax.swing.JLabel publishersLabel;
    private javax.swing.JTextField publishersTextField;
    private javax.swing.JLabel scripturesLabel;
    private javax.swing.JTextField scripturesTextField;
    private javax.swing.JComboBox statusComboBox;
    private org.jw.service.beans.ListBean statusListBean;
    private javax.swing.JLabel stausLabel;
    private org.jw.service.beansbinding.converter.StringToTimeConverter stringToTimeConverter;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties taskMessageProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);        
    DefaultCloseAction<ContactCall> closeAction;
    DefaultNewAction<ContactCall> newAction;
    DefaultDeleteAction<ContactCall> deleteAction;
    DefaultRefreshAction<ContactCall> refreshAction;
    DefaultSaveAction<ContactCall> saveAction;            
    DefaultFocusTraversalPolicy traversalPolicy;    
}
