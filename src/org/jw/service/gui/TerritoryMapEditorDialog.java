/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import javax.persistence.EntityManager;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDownloadTerritoryMapAction;
import org.jw.service.action.DefaultSingleSaveAction;
import org.jw.service.action.DependentAbstractAction;
import org.jw.service.action.dependency.DownloadTerritoryMapPreDependency;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactStatus;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.entity.MeetingPlace;
import org.jw.service.entity.Territory;
import org.jw.service.key.binder.CndrsKeyBinders;
import org.jw.service.listener.state.DefaultEntityStateListener;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;


/**
 *
 * @author Wilson
 */
public class TerritoryMapEditorDialog extends javax.swing.JDialog implements PropertyChangeListener, ActionListener{    
    private final DataAccessObject<Territory> territoryDAO;
    private final DataAccessObject<LocationMap> locationMapDAO;
    private final DataAccessObject<Contact> contactDAO;
    private final DataAccessObject<MeetingPlace> meetingPlaceDAO;
    private final Territory territoryTarget;
    private final EntityIO<Territory> territoryIO;
    
    /**
     * Creates new form ProximityMapEditorDialog
     * @param utilTable
     */
    public TerritoryMapEditorDialog(javax.swing.JDialog parent, boolean modal, EntityManager em, UtilityTable<Territory> utilTable, Territory territory) {
        super(parent, modal);
        this.territoryDAO = DataAccessObject.create(em, Territory.class);
        this.locationMapDAO = DataAccessObject.create(em, LocationMap.class);
        this.contactDAO = DataAccessObject.create(em, Contact.class);
        this.meetingPlaceDAO = DataAccessObject.create(em, MeetingPlace.class);        
        initComponents();
        this.territoryTarget = utilTable == null ? territory:utilTable.getSelectedItem();        
        territoryDAO.refresh(territoryTarget);
        this.territoryIO = EntityIO.create(territorySource, territoryTarget, Territory.class);
        initMyComponents();
    }
    
    private void initMyComponents(){        
        territoryIO.read();
        
        locationMapList.clear();
        
        territoryDAO.refresh(territoryTarget);
        for(Contact contact : territoryTarget.getContactCollection()){
            contactDAO.refresh(contact);
            LocationMap map = contact.getLocationMapId();     
            ContactStatus status = contact.getStatusId();
            if(!status.getActive())continue;
            if(map != null){
                map.addPropertyChangeListener(this);
                locationMapList.add(map);
            }else{ 
                map = locationMapDAO.create();
                map.setContactId(contact);
                map = locationMapDAO.persist(map);                
                map.addPropertyChangeListener(this);
                locationMapList.add(map);
            } 
        }
        
        Collections.sort(locationMapList);

        DefaultTaskListener downloadTaskListener;
        downloadTaskListener = this.taskMonitorPanel.createDefaultTaskListener(utilProperties.getProperty("map.download.start.message"), utilProperties.getProperty("map.download.done.message"));
        
        DownloadTerritoryMapPreDependency downloadPreDependency = new DownloadTerritoryMapPreDependency(this, locationMapList);
        
        closeAction = new DefaultCloseAction(this.mapCrudPanel.getCloseCommand(), this);
        downloadAction = new DefaultDownloadTerritoryMapAction(this.mapCrudPanel.getDownloadCommand(), territoryIO, downloadTaskListener);
        saveAction = new DefaultSingleSaveAction(this.mapCrudPanel.getSaveCommand(), territoryDAO, territoryIO);
        
        saveAction.setEnabled(false);
        downloadAction.addPreActionCommands("downloadPreDependency", downloadPreDependency);
        this.mapCrudPanel.getSaveCommand().addActionListener(this);
        DefaultEntityStateListener stateListener = DefaultEntityStateListener.create(saveAction);
        territorySource.addPropertyChangeListener(stateListener);        
        setKeyBinders();
    }
    
    private void setKeyBinders(){
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getCloseCommand(), CndrsKeyBinders.controlAltX, CndrsKeyBinders.CLOSE_MAP_KEY, closeAction);
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getDownloadCommand(), CndrsKeyBinders.controlAltL, CndrsKeyBinders.DOWNLOAD_MAP_KEY, this.downloadAction);
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getSaveCommand(), CndrsKeyBinders.controlS, CndrsKeyBinders.SAVE_MAP_KEY, this.saveAction);
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

        territorySource = new org.jw.service.entity.Territory();
        meetingPlaceListBean = new org.jw.service.beans.ListBean<MeetingPlace>(meetingPlaceDAO)
        ;
        markerColorListBean = new org.jw.service.beans.ListBean("marker_colors.properties");
        locationMapList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.LocationMap>());
        markerColorComboBox = new javax.swing.JComboBox();
        locationLabelTextField = new javax.swing.JFormattedTextField();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        mapCrudPanel = new org.jw.service.gui.component.MapCrudPanel();
        territoryPanel = new javax.swing.JPanel();
        territoryLabel = new javax.swing.JLabel();
        territoryTextField = new javax.swing.JTextField();
        serviceGroupLabel = new javax.swing.JLabel();
        serviceGroupTextField = new javax.swing.JTextField();
        meetingPlaceLabel = new javax.swing.JLabel();
        meetingPlaceComboBox = new javax.swing.JComboBox();
        territoryMarkerLabel = new javax.swing.JLabel();
        territoryMarkerComboBox = new javax.swing.JComboBox();
        proximityMapPanel = new javax.swing.JPanel();
        territoryMapLabel = new javax.swing.JLabel();
        locationMapsPanel = new javax.swing.JPanel();
        locationMapScrollPane = new javax.swing.JScrollPane();
        locationMapTable = new javax.swing.JTable();
        markerLabel = new javax.swing.JLabel();
        markerLabelTextField = new javax.swing.JFormattedTextField();
        markerColorLabel = new javax.swing.JLabel();
        colorComboBox = new javax.swing.JComboBox();
        latitudeLabel = new javax.swing.JLabel();
        latitudeTextField = new javax.swing.JFormattedTextField();
        longitudeLabel = new javax.swing.JLabel();
        longitudeTextField = new javax.swing.JFormattedTextField();

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, markerColorListBean, eLProperty, markerColorComboBox);
        bindingGroup.addBinding(jComboBoxBinding);

        try {
            locationLabelTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("territory.map.editor.dialog")); // NOI18N
        setResizable(false);

        territoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryLabel.setText("Territory:");

        territoryTextField.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territorySource, org.jdesktop.beansbinding.ELProperty.create("${name}"), territoryTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        territoryTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                territoryTextFieldActionPerformed(evt);
            }
        });

        serviceGroupLabel.setText("Service Group:");

        serviceGroupTextField.setEditable(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territorySource, org.jdesktop.beansbinding.ELProperty.create("${serviceGroupId.name}"), serviceGroupTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        serviceGroupTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceGroupTextFieldActionPerformed(evt);
            }
        });

        meetingPlaceLabel.setText("Landmark:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceListBean, eLProperty, meetingPlaceComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territorySource, org.jdesktop.beansbinding.ELProperty.create("${meetingPlaceId}"), meetingPlaceComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        meetingPlaceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetingPlaceComboBoxActionPerformed(evt);
            }
        });

        territoryMarkerLabel.setText("Marker:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, markerColorListBean, eLProperty, territoryMarkerComboBox);
        jComboBoxBinding.setSourceNullValue(null);
        jComboBoxBinding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territorySource, org.jdesktop.beansbinding.ELProperty.create("${markerColor}"), territoryMarkerComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout territoryPanelLayout = new javax.swing.GroupLayout(territoryPanel);
        territoryPanel.setLayout(territoryPanelLayout);
        territoryPanelLayout.setHorizontalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(territoryLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(territoryTextField))
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(territoryMarkerLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(meetingPlaceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(serviceGroupLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serviceGroupTextField)
                            .addComponent(meetingPlaceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(territoryMarkerComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        territoryPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {meetingPlaceLabel, serviceGroupLabel, territoryLabel, territoryMarkerLabel});

        territoryPanelLayout.setVerticalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(territoryLabel)
                    .addComponent(territoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceGroupLabel)
                    .addComponent(serviceGroupTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetingPlaceLabel)
                    .addComponent(meetingPlaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(territoryMarkerLabel)
                    .addComponent(territoryMarkerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        proximityMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territory Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryMapLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territorySource, org.jdesktop.beansbinding.ELProperty.create("${mapImage}"), territoryMapLabel, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        binding.setSourceNullValue(null);
        binding.setConverter(org.jw.service.beansbinding.converter.ByteToImageConverter.create(this.territoryMapLabel));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout proximityMapPanelLayout = new javax.swing.GroupLayout(proximityMapPanel);
        proximityMapPanel.setLayout(proximityMapPanelLayout);
        proximityMapPanelLayout.setHorizontalGroup(
            proximityMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proximityMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        proximityMapPanelLayout.setVerticalGroup(
            proximityMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proximityMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryMapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        locationMapsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Location Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, locationMapList, locationMapTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${saveState}"));
        columnBinding.setColumnName("");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${contactId}"));
        columnBinding.setColumnName("Contact Name");
        columnBinding.setColumnClass(org.jw.service.entity.Contact.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${markerLabel}"));
        columnBinding.setColumnName("Label");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${markerColor}"));
        columnBinding.setColumnName("Color");
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
        locationMapScrollPane.setViewportView(locationMapTable);
        if (locationMapTable.getColumnModel().getColumnCount() > 0) {
            locationMapTable.getColumnModel().getColumn(0).setResizable(false);
            locationMapTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            locationMapTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
            locationMapTable.getColumnModel().getColumn(1).setResizable(false);
            locationMapTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            locationMapTable.getColumnModel().getColumn(2).setResizable(false);
            locationMapTable.getColumnModel().getColumn(2).setPreferredWidth(60);
            locationMapTable.getColumnModel().getColumn(3).setResizable(false);
            locationMapTable.getColumnModel().getColumn(3).setPreferredWidth(60);
            locationMapTable.getColumnModel().getColumn(4).setResizable(false);
            locationMapTable.getColumnModel().getColumn(5).setResizable(false);
        }

        markerLabel.setText("Marker Label:");

        markerLabelTextField.setColumns(3);
        try {
            markerLabelTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, locationMapTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.markerLabel}"), markerLabelTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        markerColorLabel.setText("Marker Color:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, markerColorListBean, eLProperty, colorComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, locationMapTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.markerColor}"), colorComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        latitudeLabel.setText("Latitude:");

        latitudeTextField.setColumns(10);
        latitudeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.000000"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, locationMapTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.latitude}"), latitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        longitudeLabel.setText("Longitude:");

        longitudeTextField.setColumns(10);
        longitudeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.000000"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, locationMapTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.longitude}"), longitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout locationMapsPanelLayout = new javax.swing.GroupLayout(locationMapsPanel);
        locationMapsPanel.setLayout(locationMapsPanelLayout);
        locationMapsPanelLayout.setHorizontalGroup(
            locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationMapsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(locationMapsPanelLayout.createSequentialGroup()
                        .addComponent(locationMapScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationMapsPanelLayout.createSequentialGroup()
                        .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, locationMapsPanelLayout.createSequentialGroup()
                                .addComponent(markerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(markerLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(locationMapsPanelLayout.createSequentialGroup()
                                .addComponent(markerColorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(colorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(latitudeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationMapsPanelLayout.createSequentialGroup()
                                .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(latitudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(longitudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        locationMapsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {markerColorLabel, markerLabel});

        locationMapsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {latitudeLabel, longitudeLabel});

        locationMapsPanelLayout.setVerticalGroup(
            locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationMapsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(locationMapScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markerLabelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(markerLabel)
                    .addComponent(latitudeLabel)
                    .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markerColorLabel)
                    .addComponent(colorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(longitudeLabel)
                    .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapCrudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(proximityMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(territoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(locationMapsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(territoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationMapsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(proximityMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void territoryTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_territoryTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_territoryTextFieldActionPerformed

    private void serviceGroupTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceGroupTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviceGroupTextFieldActionPerformed

    private void meetingPlaceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetingPlaceComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_meetingPlaceComboBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox colorComboBox;
    private javax.swing.JLabel latitudeLabel;
    private javax.swing.JFormattedTextField latitudeTextField;
    private javax.swing.JFormattedTextField locationLabelTextField;
    private java.util.List<org.jw.service.entity.LocationMap> locationMapList;
    private javax.swing.JScrollPane locationMapScrollPane;
    private javax.swing.JTable locationMapTable;
    private javax.swing.JPanel locationMapsPanel;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JFormattedTextField longitudeTextField;
    private org.jw.service.gui.component.MapCrudPanel mapCrudPanel;
    private javax.swing.JComboBox markerColorComboBox;
    private javax.swing.JLabel markerColorLabel;
    private org.jw.service.beans.ListBean markerColorListBean;
    private javax.swing.JLabel markerLabel;
    private javax.swing.JFormattedTextField markerLabelTextField;
    private javax.swing.JComboBox meetingPlaceComboBox;
    private javax.swing.JLabel meetingPlaceLabel;
    private org.jw.service.beans.ListBean meetingPlaceListBean;
    private javax.swing.JPanel proximityMapPanel;
    private javax.swing.JLabel serviceGroupLabel;
    private javax.swing.JTextField serviceGroupTextField;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JLabel territoryLabel;
    private javax.swing.JLabel territoryMapLabel;
    private javax.swing.JComboBox territoryMarkerComboBox;
    private javax.swing.JLabel territoryMarkerLabel;
    private javax.swing.JPanel territoryPanel;
    private org.jw.service.entity.Territory territorySource;
    private javax.swing.JTextField territoryTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);
    DependentAbstractAction<Territory> closeAction;
    DependentAbstractAction<Territory> downloadAction;
    DefaultSingleSaveAction<Territory> saveAction;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {        
        if(!evt.getPropertyName().equals("saveState")){
            LocationMap map = (LocationMap) evt.getSource();
            map.setSaveState("*");
            this.territorySource.setSaveState("*");
        }    
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        for(LocationMap map : this.locationMapList){
            if(map.getSaveState().equals("*")){                
                locationMapDAO.persist(map);
                map.setSaveState("");                
            }
        }
    }
}
