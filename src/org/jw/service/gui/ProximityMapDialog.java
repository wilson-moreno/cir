/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultDownloadProximityMapAction;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class ProximityMapDialog extends javax.swing.JDialog {
    private final DataAccessObject<ServiceGroup> serviceGroupDAO;
    private final DataAccessObject<Territory> territoryDAO;
    
    /**
     * Creates new form LocationProximityCheckerDialog
     */
    public ProximityMapDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);        
        this.serviceGroupDAO = DataAccessObject.create(em, ServiceGroup.class);
        this.territoryDAO = DataAccessObject.create(em, Territory.class);
        initComponents();        
        initMyComponents();
    }
    
    private void initMyComponents(){
        DefaultTaskListener downloadListener;
        
        this.serviceGroupComboBox.setSelectedIndex(-1);        
        setServiceGroupComboBoxItemListener();
        setTerritoryComboBoxItemListener();        
        
        
        downloadListener = this.taskMonitorPanel.createDefaultTaskListener("Downloading map from Google...", "Finished downloading map from Google...");
        closeAction = new DefaultCloseAction(this.mapCrudPanel.getCloseCommand(), this);
        downloadMapAction = new DefaultDownloadProximityMapAction(this.mapCrudPanel.getDownloadCommand(), this.territoryComboBox, this.byteArrayBean, downloadListener);
        
        setSaveCommandActionListener();
        addByteArrayBeanPropertyListener();
    }
    
    private void addByteArrayBeanPropertyListener(){
        byteArrayBean.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                javax.swing.JButton saveCommand;
                saveCommand = mapCrudPanel.getSaveCommand();
                saveCommand.setEnabled(true);
            }        
        });        
    }
    
    private void setSaveCommandActionListener(){
        javax.swing.JButton saveCommand = this.mapCrudPanel.getSaveCommand();
        
        saveCommand.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.JButton command = (javax.swing.JButton) e.getSource();
                Territory territory = (Territory) territoryComboBox.getSelectedItem();
                territory.setMapImage(byteArrayBean.getByteArray());
                territoryDAO.persist(territory);                
                command.setEnabled(false);
            }        
        });        
    }
    
    private void setTerritoryComboBoxItemListener(){
        this.territoryComboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    Territory territory = (Territory) territoryComboBox.getSelectedItem();
                    byteArrayBean.setByteArray(territory.getMapImage());
                    addContactsToList(territory.getContactCollection(), territoryContactList, false);
                }
            }        
        });        
    }
    
    private void addContactsToList(Collection<Contact> contactList, JList list, boolean orphanOnly){
        DefaultListModel<Contact> listModel = new DefaultListModel<>();                    
        for(Contact contact : contactList){
            if(orphanOnly && contact.getTerritoryId() == null)
                listModel.addElement(contact);
            else if(!orphanOnly){
                listModel.addElement(contact);
            }
        }
        list.setModel(listModel);
    }
    
    private void addTerritoryToComboBox(Collection<Territory> territoryList, JComboBox comboBox){
        DefaultComboBoxModel<Territory> comboBoxModel = new DefaultComboBoxModel();
        for(Territory territory : territoryList){
            comboBoxModel.addElement(territory);
        }                    
        territoryComboBox.setModel(comboBoxModel);
        territoryComboBox.setSelectedIndex(-1);
    }
    
    private void setServiceGroupComboBoxItemListener(){
        this.serviceGroupComboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if(ie.getStateChange() == ItemEvent.SELECTED){
                    ServiceGroup serviceGroup = (ServiceGroup)serviceGroupComboBox.getSelectedItem();                                        
                    addTerritoryToComboBox(serviceGroup.getTerritoryCollection(), territoryComboBox);
                }
            }
        });        
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

        byteArrayBean = new org.jw.service.beans.ByteArrayBean();
        serviceGroupListBean = new org.jw.service.beans.ListBean(this.serviceGroupDAO)
        ;
        contactLocationCellRenderer = new org.jw.service.list.cell.renderer.ContactLocationCellRenderer();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        proximityMapPanel = new javax.swing.JPanel();
        proximityMapLabel = new javax.swing.JLabel();
        mapCrudPanel = new org.jw.service.gui.component.MapCrudPanel();
        contactsPanel = new javax.swing.JPanel();
        serviceGroupLabel = new javax.swing.JLabel();
        serviceGroupComboBox = new javax.swing.JComboBox();
        contactsScrollPane = new javax.swing.JScrollPane();
        territoryContactList = new javax.swing.JList();
        territoryLabel = new javax.swing.JLabel();
        territoryComboBox = new javax.swing.JComboBox<>();

        contactLocationCellRenderer.setText("contactLocationCellRenderer1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("proximity.checker.dialog.title")); // NOI18N
        setResizable(false);

        proximityMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proximity Map", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        proximityMapLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, byteArrayBean, org.jdesktop.beansbinding.ELProperty.create("${byteArray}"), proximityMapLabel, org.jdesktop.beansbinding.BeanProperty.create("icon"));
        binding.setConverter(org.jw.service.beansbinding.converter.ByteToImageConverter.create());
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout proximityMapPanelLayout = new javax.swing.GroupLayout(proximityMapPanel);
        proximityMapPanel.setLayout(proximityMapPanelLayout);
        proximityMapPanelLayout.setHorizontalGroup(
            proximityMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proximityMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proximityMapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        proximityMapPanelLayout.setVerticalGroup(
            proximityMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proximityMapPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(proximityMapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        contactsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contacts", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        serviceGroupLabel.setText("Service Group:");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${sortedList}");
        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, serviceGroupListBean, eLProperty, serviceGroupComboBox);
        bindingGroup.addBinding(jComboBoxBinding);

        territoryContactList.setCellRenderer(contactLocationCellRenderer);
        contactsScrollPane.setViewportView(territoryContactList);

        territoryLabel.setText("Territory:");

        territoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                territoryComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contactsPanelLayout = new javax.swing.GroupLayout(contactsPanel);
        contactsPanel.setLayout(contactsPanelLayout);
        contactsPanelLayout.setHorizontalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(contactsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(contactsPanelLayout.createSequentialGroup()
                        .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(serviceGroupLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(territoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serviceGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(territoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contactsPanelLayout.setVerticalGroup(
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serviceGroupLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(territoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(territoryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contactsScrollPane)
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
                    .addComponent(mapCrudPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(proximityMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximityMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contactsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void territoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_territoryComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_territoryComboBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.beans.ByteArrayBean byteArrayBean;
    private org.jw.service.list.cell.renderer.ContactLocationCellRenderer contactLocationCellRenderer;
    private javax.swing.JPanel contactsPanel;
    private javax.swing.JScrollPane contactsScrollPane;
    private org.jw.service.gui.component.MapCrudPanel mapCrudPanel;
    private javax.swing.JLabel proximityMapLabel;
    private javax.swing.JPanel proximityMapPanel;
    private javax.swing.JComboBox serviceGroupComboBox;
    private javax.swing.JLabel serviceGroupLabel;
    private org.jw.service.beans.ListBean serviceGroupListBean;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private javax.swing.JComboBox<org.jw.service.entity.Territory> territoryComboBox;
    private javax.swing.JList territoryContactList;
    private javax.swing.JLabel territoryLabel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    DefaultCloseAction closeAction;
    DefaultDownloadProximityMapAction downloadMapAction;
}
