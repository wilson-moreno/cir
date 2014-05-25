/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

/**
 *
 * @author Wilson
 */
public class TerritoryDialog extends javax.swing.JDialog {

    /**
     * Creates new form TerritoryDialog
     */
    public TerritoryDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

        territoryList = org.jdesktop.observablecollections.ObservableCollections.observableList(new java.util.ArrayList<org.jw.service.entity.Territory>());
        taskMonitorPanel1 = new org.jw.service.gui.component.TaskMonitorPanel();
        defaultCrudPanel1 = new org.jw.service.gui.component.MultipleRecordCrudPanel();
        territoryPanel = new javax.swing.JPanel();
        territoryNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        enableCheckBox = new javax.swing.JCheckBox();
        serviceGroupLabel = new javax.swing.JLabel();
        serviceGroupComboBox = new javax.swing.JComboBox();
        territoryListPanel = new javax.swing.JPanel();
        territoryScrollPane = new javax.swing.JScrollPane();
        territoryTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("territory.dialog.title")); // NOI18N

        territoryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        territoryNameLabel.setText("Name:");

        descriptionLabel.setText("Description:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.name}"), nameTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.description}"), descriptionTextField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        enableCheckBox.setText("Enable");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.enable}"), enableCheckBox, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        serviceGroupLabel.setText("Service Group:");

        javax.swing.GroupLayout territoryPanelLayout = new javax.swing.GroupLayout(territoryPanel);
        territoryPanel.setLayout(territoryPanelLayout);
        territoryPanelLayout.setHorizontalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(serviceGroupLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serviceGroupComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(territoryNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enableCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(territoryPanelLayout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionTextField)))
                .addContainerGap())
        );

        territoryPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {descriptionLabel, serviceGroupLabel, territoryNameLabel});

        territoryPanelLayout.setVerticalGroup(
            territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(territoryNameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enableCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(territoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceGroupLabel)
                    .addComponent(serviceGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        territoryListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Territories", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, territoryList, territoryTable);
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${serviceGroupId}"));
        columnBinding.setColumnName("Service Group");
        columnBinding.setColumnClass(org.jw.service.entity.ServiceGroup.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enable}"));
        columnBinding.setColumnName("Enable");
        columnBinding.setColumnClass(Boolean.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        territoryScrollPane.setViewportView(territoryTable);
        if (territoryTable.getColumnModel().getColumnCount() > 0) {
            territoryTable.getColumnModel().getColumn(0).setResizable(false);
            territoryTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            territoryTable.getColumnModel().getColumn(0).setCellRenderer(org.jw.service.table.cell.renderer.DefaultStateCellRenderer.create());
        }

        javax.swing.GroupLayout territoryListPanelLayout = new javax.swing.GroupLayout(territoryListPanel);
        territoryListPanel.setLayout(territoryListPanelLayout);
        territoryListPanelLayout.setHorizontalGroup(
            territoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        territoryListPanelLayout.setVerticalGroup(
            territoryListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(territoryListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(defaultCrudPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(territoryListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(territoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(territoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(territoryListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultCrudPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jw.service.gui.component.MultipleRecordCrudPanel defaultCrudPanel1;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JCheckBox enableCheckBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox serviceGroupComboBox;
    private javax.swing.JLabel serviceGroupLabel;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel1;
    private java.util.List<org.jw.service.entity.Territory> territoryList;
    private javax.swing.JPanel territoryListPanel;
    private javax.swing.JLabel territoryNameLabel;
    private javax.swing.JPanel territoryPanel;
    private javax.swing.JScrollPane territoryScrollPane;
    private javax.swing.JTable territoryTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
