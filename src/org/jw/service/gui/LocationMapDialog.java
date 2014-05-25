/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDownloadMapAction;
import org.jw.service.action.DefaultSingleSaveAction;
import org.jw.service.action.dependency.DownloadMapPreDependency;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class LocationMapDialog extends javax.swing.JDialog {
    private final UtilityTable<Contact> utilTable;
    private final DataAccessObject<LocationMap> mapDAO;    
    private final DataAccessObject<Contact> contactDAO;    
    private LocationMap mapTarget;
    private Contact contactTarget;
    private final EntityIO<LocationMap> mapIO;
    private final EntityIO<Contact> contactIO;
    private final UtilityDownload utilDownload;
    
    /**
     * Creates new form LocationMapDialog
     * @param parent
     * @param modal
     * @param utilTable
     * @param em
     */
    public LocationMapDialog(java.awt.Frame parent, boolean modal, EntityManager em, UtilityTable<Contact> utilTable) {
        super(parent, modal);
        this.utilTable = utilTable;                
        this.utilDownload = UtilityDownload.create();
        this.mapDAO = DataAccessObject.create(em, LocationMap.class);        
        this.contactDAO = DataAccessObject.create(em, Contact.class);    
        this.contactIO = EntityIO.create(Contact.class);
        this.mapIO = EntityIO.create(LocationMap.class);
        initComponents();        
        initMyComponents();
    }
    
    private void initMyComponents(){
        DefaultTaskListener mapDownloadListener = this.taskMonitorPanel.createDefaultTaskListener(utilProperties.getProperty("map.download.start.message"), utilProperties.getProperty("map.download.done.message"));
        DefaultTaskListener connectionCheckListener = this.taskMonitorPanel.createDefaultTaskListener("Checking for internet connection...", "Checking connection finished...");
        
        closeAction = new DefaultCloseAction(this.mapCrudPanel.getCloseCommand(), this);
        saveAction = new DefaultSingleSaveAction(this.mapCrudPanel.getSaveCommand(), mapDAO, mapIO);
        downloadMapAction = new DefaultDownloadMapAction(this.mapCrudPanel.getDownlaodCommand(),mapImageLabel, mapIO,utilDownload, mapDownloadListener);        
        
        downloadMapPreDependency = new DownloadMapPreDependency(this, utilDownload, connectionCheckListener);
        downloadMapAction.addPreActionCommands("downloadMapPreDependency", downloadMapPreDependency);
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

        contactSource = new org.jw.service.entity.Contact();
        mapSource = new org.jw.service.entity.LocationMap();
        mapTypeListBean = new org.jw.service.beans.ListBean("map_types.properties");
        ;
        imageFormatListBean = new org.jw.service.beans.ListBean("image_formats.properties");
        scaleListBean = new org.jw.service.beans.ListBean(new Integer[]{1, 2, 4});
        markerColorListBean = new org.jw.service.beans.ListBean("marker_colors.properties");
        contactInfoPanel = new org.jw.service.gui.component.ContactInfoPanel();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        locationMapPanel = new javax.swing.JPanel();
        latitudeLabel = new javax.swing.JLabel();
        longitudeLabel = new javax.swing.JLabel();
        mapWidthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        latitudeTextField = new javax.swing.JTextField();
        longitudeTextField = new javax.swing.JTextField();
        mapWidthTextField = new javax.swing.JTextField();
        mapHeightTextField = new javax.swing.JTextField();
        scaleValueLabel = new javax.swing.JLabel();
        zoomValueLabel = new javax.swing.JLabel();
        mapTypeLabel = new javax.swing.JLabel();
        imageFormatLabel = new javax.swing.JLabel();
        zoomValueSpinner = new javax.swing.JSpinner();
        mapTypeComboBox = new javax.swing.JComboBox();
        imageFormatComboBox = new javax.swing.JComboBox();
        scaleValueComboBox = new javax.swing.JComboBox();
        markerColorLabel = new javax.swing.JLabel();
        markerColorsComboBox = new javax.swing.JComboBox();
        mapImagePanel = new javax.swing.JPanel();
        mapImageLabel = new javax.swing.JLabel();
        mapCrudPanel = new org.jw.service.gui.component.MapCrudPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("location.map.dialog.title")); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${firstName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("firstName"));
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${lastName}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("lastName"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordDate}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordDate"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, contactSource, org.jdesktop.beansbinding.ELProperty.create("${recordNumber}"), contactInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("recordNumber"));
        bindingGroup.addBinding(binding);

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/location_map_dialog"); // NOI18N
        locationMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle1.getString("location.map.propties.panel.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        latitudeLabel.setText("Latitude:");

        longitudeLabel.setText("Longitude:");

        mapWidthLabel.setText("Width:");

        heightLabel.setText("Height:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${latitude}"), latitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${longitude}"), longitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${width}"), mapWidthTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${height}"), mapHeightTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        scaleValueLabel.setText("Scale:");

        zoomValueLabel.setText("Zoom:");

        mapTypeLabel.setText("Type:");

        imageFormatLabel.setText("Format:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${zoom}"), zoomValueSpinner, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapTypeListBean, eLProperty, mapTypeComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${mapType}"), mapTypeComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, imageFormatListBean, eLProperty, imageFormatComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${imageFormat}"), imageFormatComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, scaleListBean, eLProperty, scaleValueComboBox);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${scale}"), scaleValueComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        markerColorLabel.setText("Marker Color:");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${list}");
        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, markerColorListBean, eLProperty, markerColorsComboBox);
        jComboBoxBinding.setSourceNullValue(null);
        jComboBoxBinding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${markerColor}"), markerColorsComboBox, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout locationMapPanelLayout = new javax.swing.GroupLayout(locationMapPanel);
        locationMapPanel.setLayout(locationMapPanelLayout);
        locationMapPanelLayout.setHorizontalGroup(
            locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(imageFormatLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageFormatComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(markerColorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(markerColorsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(mapTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationMapPanelLayout.createSequentialGroup()
                        .addComponent(scaleValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scaleValueComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(heightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapHeightTextField))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(mapWidthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mapWidthTextField))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(longitudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(longitudeTextField))
                    .addGroup(locationMapPanelLayout.createSequentialGroup()
                        .addComponent(latitudeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(zoomValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomValueSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        locationMapPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {heightLabel, imageFormatLabel, latitudeLabel, longitudeLabel, mapTypeLabel, mapWidthLabel, markerColorLabel, scaleValueLabel, zoomValueLabel});

        locationMapPanelLayout.setVerticalGroup(
            locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(latitudeLabel)
                    .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(longitudeLabel)
                    .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mapWidthLabel)
                    .addComponent(mapWidthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightLabel)
                    .addComponent(mapHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scaleValueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scaleValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomValueLabel)
                    .addComponent(zoomValueSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mapTypeLabel)
                    .addComponent(mapTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(markerColorLabel)
                    .addComponent(markerColorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imageFormatLabel)
                    .addComponent(imageFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mapImagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle1.getString("location.map.image.panel"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        mapImageLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, mapSource, org.jdesktop.beansbinding.ELProperty.create("${image}"), mapImageLabel, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        binding.setConverter(org.jw.service.beansbinding.converter.ByteToImageConverter.create(mapImageLabel));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout mapImagePanelLayout = new javax.swing.GroupLayout(mapImagePanel);
        mapImagePanel.setLayout(mapImagePanelLayout);
        mapImagePanelLayout.setHorizontalGroup(
            mapImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mapImagePanelLayout.setVerticalGroup(
            mapImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mapCrudPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mapImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contactInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(locationMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mapImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setSelectedContact();
        setContactLocationMap();
    }//GEN-LAST:event_formWindowActivated

    private void setContactLocationMap(){
        contactDAO.refresh(contactTarget);        
        if(contactTarget.getLocationMapCollection().isEmpty()){
            LocationMap map = mapDAO.create();
            map.setContactId(contactTarget);
            mapTarget = mapDAO.save(map);    
            mapIO.setSource(mapSource);
            mapIO.setTarget(mapTarget);
            mapIO.read();
        } else {
            for(LocationMap map : new ArrayList<>(contactTarget.getLocationMapCollection())){
                mapTarget = map;
            }
            mapIO.setSource(mapSource);
            mapIO.setTarget(mapTarget);
            mapIO.read();
        }
    }
    
    private void setSelectedContact(){
        contactTarget = utilTable.getSelectedItem();
        contactIO.setSource(contactSource);
        contactIO.setTarget(contactTarget);
        contactIO.read();
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.gui.component.ContactInfoPanel contactInfoPanel;
    private org.jw.service.entity.Contact contactSource;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JComboBox imageFormatComboBox;
    private javax.swing.JLabel imageFormatLabel;
    private org.jw.service.beans.ListBean imageFormatListBean;
    private javax.swing.JLabel latitudeLabel;
    private javax.swing.JTextField latitudeTextField;
    private javax.swing.JPanel locationMapPanel;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JTextField longitudeTextField;
    private org.jw.service.gui.component.MapCrudPanel mapCrudPanel;
    private javax.swing.JTextField mapHeightTextField;
    private javax.swing.JLabel mapImageLabel;
    private javax.swing.JPanel mapImagePanel;
    private org.jw.service.entity.LocationMap mapSource;
    private javax.swing.JComboBox mapTypeComboBox;
    private javax.swing.JLabel mapTypeLabel;
    private org.jw.service.beans.ListBean mapTypeListBean;
    private javax.swing.JLabel mapWidthLabel;
    private javax.swing.JTextField mapWidthTextField;
    private javax.swing.JLabel markerColorLabel;
    private org.jw.service.beans.ListBean markerColorListBean;
    private javax.swing.JComboBox markerColorsComboBox;
    private org.jw.service.beans.ListBean scaleListBean;
    private javax.swing.JComboBox scaleValueComboBox;
    private javax.swing.JLabel scaleValueLabel;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JLabel zoomValueLabel;
    private javax.swing.JSpinner zoomValueSpinner;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.TASK_MESSAGE_PROPERTIES);
    DefaultDownloadMapAction downloadMapAction;
    DefaultSingleSaveAction<LocationMap> saveMapAction;
    DefaultCloseAction<LocationMap> closeAction;
    DefaultSingleSaveAction<LocationMap> saveAction;
    DownloadMapPreDependency downloadMapPreDependency;
}
