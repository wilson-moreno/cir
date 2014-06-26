/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.util.Date;
import javax.persistence.EntityManager;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.action.DefaultSingleSaveAction;
import org.jw.service.action.validator.CongregationCloseActionValidator;
import org.jw.service.action.validator.CongregationSaveActionValidator;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Congregation;
import org.jw.service.entity.EntityIO;
import org.jw.service.listener.state.DefaultEntityStateListener;

/**
 *
 * @author Wilson
 */
public class CongregationDialog extends javax.swing.JDialog {
    private final DataAccessObject<Congregation> dao;
    private Congregation target;
    
    /**
     * Creates new form CongregationDialog
     * @param parent
     */
    public CongregationDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);
        dao = DataAccessObject.create(em, Congregation.class);
        initComponents();
        initMyComponents();
    }

    private void initMyComponents(){
        target = getCongregation();    
        EntityIO<Congregation> entityIO = EntityIO.create(source, target, Congregation.class);
        entityIO.read();
        saveAction = new DefaultSingleSaveAction(this.singleRecordCrudPanel.getSaveCommand(), dao, entityIO);
        closeAction = new DefaultCloseAction(this.singleRecordCrudPanel.getCloseCommand(), this);
        saveAction.setEnabled(false);
        stateListener = DefaultEntityStateListener.create(saveAction);
        source.addPropertyChangeListener(stateListener);
        
        closeValidator = new CongregationCloseActionValidator(this, source);
        saveValidator = new CongregationSaveActionValidator(this, source);
        closeAction.addActionValidator(closeValidator);
        saveAction.addActionValidator(saveValidator);
        
        bindingGroup.addBindingListener(this.taskMonitorPanel.getLogger());
    }
    
    private Congregation getCongregation(){
        Congregation congregation = dao.read(1);
        if(congregation == null)congregation = dao.create();
        return congregation;
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

        source = new org.jw.service.entity.Congregation();
        documentFilterFactory = new org.jw.service.document.filter.DocumentFilterFactory();
        congregationPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        address1Label = new javax.swing.JLabel();
        address2Label = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        latitudeLabel = new javax.swing.JLabel();
        longitudeLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        address1TextField = new javax.swing.JTextField();
        address2TextField = new javax.swing.JTextField();
        cityTextField = new javax.swing.JTextField();
        latitudeTextField = new javax.swing.JFormattedTextField();
        longitudeTextField = new javax.swing.JFormattedTextField();
        singleRecordCrudPanel = new org.jw.service.gui.component.SingleRecordCrudPanel();
        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("congregation.dialog.title")); // NOI18N
        setResizable(false);

        congregationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Congregation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        nameLabel.setLabelFor(nameTextField);
        nameLabel.setText("Name:");

        address1Label.setLabelFor(address1TextField);
        address1Label.setText("Address:");

        cityLabel.setLabelFor(cityTextField);
        cityLabel.setText("City:");

        latitudeLabel.setLabelFor(latitudeTextField);
        latitudeLabel.setText("Latitude:");

        longitudeLabel.setLabelFor(longitudeTextField);
        longitudeLabel.setText("Longitude:");

        nameTextField.setDocument(documentFilterFactory.getSizeFilter50());
        nameTextField.setNextFocusableComponent(address1TextField);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Name");
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        address1TextField.setDocument(documentFilterFactory.getSizeFilter100());
        address1TextField.setNextFocusableComponent(address2TextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${address1}"), address1TextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "Address");
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        address2TextField.setDocument(documentFilterFactory.getSizeFilter100());
        address2TextField.setNextFocusableComponent(cityTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${address2}"), address2TextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        cityTextField.setDocument(documentFilterFactory.getSizeFilter50());
        cityTextField.setNextFocusableComponent(latitudeTextField);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${city}"), cityTextField, org.jdesktop.beansbinding.BeanProperty.create("text"), "City");
        binding.setSourceNullValue("null");
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);

        latitudeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.000000"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${latitude}"), latitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        longitudeTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.000000"))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, source, org.jdesktop.beansbinding.ELProperty.create("${longitude}"), longitudeTextField, org.jdesktop.beansbinding.BeanProperty.create("value"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout congregationPanelLayout = new javax.swing.GroupLayout(congregationPanel);
        congregationPanel.setLayout(congregationPanelLayout);
        congregationPanelLayout.setHorizontalGroup(
            congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(congregationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField))
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(address1Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(address1TextField))
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(address2Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(address2TextField))
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(cityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cityTextField))
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(longitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(longitudeTextField))
                    .addGroup(congregationPanelLayout.createSequentialGroup()
                        .addComponent(latitudeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(latitudeTextField)))
                .addContainerGap())
        );

        congregationPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {address1Label, address2Label, cityLabel, latitudeLabel, longitudeLabel, nameLabel});

        congregationPanelLayout.setVerticalGroup(
            congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(congregationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address1Label)
                    .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address2Label)
                    .addComponent(address2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(latitudeLabel)
                    .addComponent(latitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(congregationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(longitudeLabel)
                    .addComponent(longitudeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        congregationPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {address1Label, address2Label, cityLabel, latitudeLabel, longitudeLabel, nameLabel});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(congregationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(singleRecordCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(congregationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(singleRecordCrudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel address1Label;
    private javax.swing.JTextField address1TextField;
    private javax.swing.JLabel address2Label;
    private javax.swing.JTextField address2TextField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JPanel congregationPanel;
    private org.jw.service.document.filter.DocumentFilterFactory documentFilterFactory;
    private javax.swing.JLabel latitudeLabel;
    private javax.swing.JFormattedTextField latitudeTextField;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JFormattedTextField longitudeTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private org.jw.service.gui.component.SingleRecordCrudPanel singleRecordCrudPanel;
    private org.jw.service.entity.Congregation source;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    DefaultEntityStateListener stateListener;
    DefaultSingleSaveAction<Congregation> saveAction;
    DefaultCloseAction closeAction;
    CongregationCloseActionValidator closeValidator;
    CongregationSaveActionValidator saveValidator;
}
