/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import javax.persistence.EntityManager;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDownloadDirectionMapAction;
import org.jw.service.action.DefaultSingleSaveAction;
import org.jw.service.action.validator.DefaultEntitySaveActionValidator;
import org.jw.service.action.validator.DefaultRequiredFieldsSaveActionValidator;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.DirectionMap;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.entity.MeetingPlace;
import org.jw.service.key.binder.CndrsKeyBinders;
import org.jw.service.listener.state.DefaultEntityStateListener;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DirectionMapDialog extends javax.swing.JDialog {
    private final UtilityTable<Contact> utilTable;
    private final UtilityDownload utilDownload;
    private final DataAccessObject<LocationMap> locationMapDAO;
    private final DataAccessObject<DirectionMap> directionMapDAO;
    private final DataAccessObject<Contact> contactDAO;
    private final DataAccessObject<MeetingPlace> meetingPlaceDAO;
    private final EntityIO contactIO;
    private final EntityIO mapIO;
    private Contact contactTarget;
    private DirectionMap directionMapTarget;
    
    /**
     * Creates new form DirectionMapDialog
     */
    public DirectionMapDialog(java.awt.Frame parent, boolean modal, EntityManager em, UtilityTable<Contact> utilTable) {
        super(parent, modal);
        this.utilTable = utilTable;
        this.utilDownload = UtilityDownload.create();
        this.directionMapDAO = DataAccessObject.create(em, DirectionMap.class);
        this.locationMapDAO = DataAccessObject.create(em, LocationMap.class);
        this.contactDAO = DataAccessObject.create(em, Contact.class);
        this.meetingPlaceDAO = DataAccessObject.create(em, MeetingPlace.class);
        this.mapIO = EntityIO.create(DirectionMap.class);
        this.contactIO = EntityIO.create(Contact.class);
        initComponents();
        initMyComponents();
        setSelectedContact();
        setContactDirectionMap();
    }
    
    private void setContactDirectionMap(){        
        LocationMap locationMap = contactTarget.getLocationMapId();
        locationMapDAO.refresh(locationMap);
        if(locationMap.getDirectionMap() == null){
            DirectionMap map = directionMapDAO.create();
            map.setLocationMapId(locationMap);
            directionMapTarget = directionMapDAO.persist(map);
            mapIO.setSource(directionMapSource);
            mapIO.setTarget(directionMapTarget);
            mapIO.read();
        }else{
            DirectionMap map = locationMap.getDirectionMap();
            directionMapTarget = map;
            mapIO.setSource(directionMapSource);
            mapIO.setTarget(directionMapTarget);
            mapIO.read();
        }
        
        saveAction.setEnabled(false);
        DefaultEntityStateListener stateListener = DefaultEntityStateListener.create(saveAction);
        directionMapSource.addPropertyChangeListener(stateListener);
    }
    
    
    
    private void setSelectedContact(){
        contactTarget = utilTable.getSelectedItem();
        contactIO.setSource(contactSource);
        contactIO.setTarget(contactTarget);
        contactIO.read();
    }
    
    private void initMyComponents(){
        DefaultTaskListener mapDownloadListener = this.taskMonitorPanel.createDefaultTaskListener(utilProperties.getProperty("map.download.start.message"), utilProperties.getProperty("map.download.done.message"));
        
        closeAction = new DefaultCloseAction(this.mapCrudPanel.getCloseCommand(), this);
        saveAction = new DefaultSingleSaveAction(this.mapCrudPanel.getSaveCommand(), directionMapDAO, mapIO);
        downloadMapAction = new DefaultDownloadDirectionMapAction(this.mapCrudPanel.getDownloadCommand(), this.directionMapImageLabel, mapIO, utilDownload, mapDownloadListener);
        
        setActionValidators();
        setKeyBinders();
    }
    
    private void setKeyBinders(){
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getCloseCommand(), CndrsKeyBinders.controlAltX, CndrsKeyBinders.CLOSE_MAP_KEY, closeAction);
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getDownloadCommand(), CndrsKeyBinders.controlAltL, CndrsKeyBinders.DOWNLOAD_MAP_KEY, this.downloadMapAction);
        CndrsKeyBinders.setKeyBinder(this.mapCrudPanel.getSaveCommand(), CndrsKeyBinders.controlS, CndrsKeyBinders.SAVE_MAP_KEY, this.saveAction);
    }
    
    private void setActionValidators(){
        DefaultEntitySaveActionValidator saveActionValidator = new DefaultEntitySaveActionValidator(this, directionMapSource);
        
        saveAction.addActionValidator(saveActionValidator);
        downloadMapAction.addActionValidator(saveActionValidator);
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

        meetingPlaceListBean = new org.jw.service.beans.ListBean(meetingPlaceDAO);
        pathColorListBean = new org.jw.service.beans.ListBean("marker_colors.properties");
        directionMapSource = new org.jw.service.entity.DirectionMap();
        contactSource = new org.jw.service.entity.Contact();
        travelModeListBean = new org.jw.service.beans.ListBean("travel_modes.properties");
        scaleListBean = new org.jw.service.beans.ListBean(new Integer[]{1, 2, 4});
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        mapCrudPanel = new org.jw.service.gui.component.MapCrudPanel();
        contactInfoPanel = new org.jw.service.gui.component.ContactInfoPanel();
        directionMapPanel = new javax.swing.JPanel();
        directionMapImageLabel = new javax.swing.JLabel();
        mapPropertiesPanel = new javax.swing.JPanel();
        meetingPlaceLabel = new javax.swing.JLabel();
        meetingPlaceComboBox = new javax.swing.JComboBox();
        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        scaleLabel = new javax.swing.JLabel();
        zoomLabel = new javax.swing.JLabel();
        pathColorLabel = new javax.swing.JLabel();
        widthTextField = new javax.swing.JTextField();
        heightTextField = new javax.swing.JTextField();
        zoomSpinner = new javax.swing.JSpinner();
        pathColorComboBox = new javax.swing.JComboBox();
        travelModeLabel = new javax.swing.JLabel();
        travelModeComboBox = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("direction.map.dialog.title")); // NOI18N
        setResizable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${firstName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("firstName"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${lastName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("lastName"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordDate}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordDate"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordNumber}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordNumber"));
        bindingGroup.addBinding(binding);

        directionMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Direction Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        directionMapImageLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${image}"), directionMapImageLabel, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        binding.setConverter(org.jw.service.beansbinding.converter.ByteToImageConverter.create(this.directionMapImageLabel));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout directionMapPanelLayout = new javax.swing.GroupLayout(directionMapPanel);
        directionMapPanel.setLayout(directionMapPanelLayout);
        directionMapPanelLayout.setHorizontalGroup(
            directionMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directionMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directionMapImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        directionMapPanelLayout.setVerticalGroup(
            directionMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directionMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directionMapImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mapPropertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        meetingPlaceLabel.setText("Meeting Place:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, meetingPlaceListBean, eLProperty, meetingPlaceComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${meetingPlaceId}"), meetingPlaceComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        widthLabel.setText("Width:");

        heightLabel.setText("Height:");

        scaleLabel.setText("Scale:");

        zoomLabel.setText("Zoom:");

        pathColorLabel.setText("Path Color:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${width}"), widthTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${height}"), heightTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${zoom}"), zoomSpinner, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pathColorListBean, eLProperty, pathColorComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${pathColor}"), pathColorComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        travelModeLabel.setText("Travel Mode:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, travelModeListBean, eLProperty, travelModeComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${travelMode}"), travelModeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, scaleListBean, eLProperty, jComboBox1);
        jComboBoxBinding.setSourceNullValue(null);
        jComboBoxBinding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, directionMapSource, org.jdesktop.beansbinding.ELProperty.create("${scale}"), jComboBox1, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout mapPropertiesPanelLayout = new javax.swing.GroupLayout(mapPropertiesPanel);
        mapPropertiesPanel.setLayout(mapPropertiesPanelLayout);
        mapPropertiesPanelLayout.setHorizontalGroup(
            mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(meetingPlaceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(meetingPlaceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(widthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(widthTextField))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(heightLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightTextField))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(scaleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(pathColorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pathColorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(zoomLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                    .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                        .addComponent(travelModeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(travelModeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        mapPropertiesPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {heightLabel, meetingPlaceLabel, pathColorLabel, scaleLabel, travelModeLabel, widthLabel, zoomLabel});

        mapPropertiesPanelLayout.setVerticalGroup(
            mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetingPlaceLabel)
                    .addComponent(meetingPlaceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(widthLabel)
                    .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightLabel)
                    .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scaleLabel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomLabel)
                    .addComponent(zoomSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathColorLabel)
                    .addComponent(pathColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mapPropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(travelModeLabel)
                    .addComponent(travelModeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mapCrudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(contactInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(directionMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mapPropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contactInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(directionMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mapPropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.gui.component.ContactInfoPanel contactInfoPanel;
    private org.jw.service.entity.Contact contactSource;
    private javax.swing.JLabel directionMapImageLabel;
    private javax.swing.JPanel directionMapPanel;
    private org.jw.service.entity.DirectionMap directionMapSource;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JComboBox jComboBox1;
    private org.jw.service.gui.component.MapCrudPanel mapCrudPanel;
    private javax.swing.JPanel mapPropertiesPanel;
    private javax.swing.JComboBox meetingPlaceComboBox;
    private javax.swing.JLabel meetingPlaceLabel;
    private org.jw.service.beans.ListBean meetingPlaceListBean;
    private javax.swing.JComboBox pathColorComboBox;
    private javax.swing.JLabel pathColorLabel;
    private org.jw.service.beans.ListBean pathColorListBean;
    private javax.swing.JLabel scaleLabel;
    private org.jw.service.beans.ListBean scaleListBean;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JComboBox travelModeComboBox;
    private javax.swing.JLabel travelModeLabel;
    private org.jw.service.beans.ListBean travelModeListBean;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JTextField widthTextField;
    private javax.swing.JLabel zoomLabel;
    private javax.swing.JSpinner zoomSpinner;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);
    DefaultDownloadDirectionMapAction downloadMapAction;
    DefaultCloseAction<DirectionMap> closeAction;
    DefaultSingleSaveAction<DirectionMap> saveAction;
}
