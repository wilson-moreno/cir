/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.component;

import javax.swing.JButton;

/**
 *
 * @author Wilson
 */
public class MultipleRecordCrudPanel extends javax.swing.JPanel implements CndrsCommandPanel{

    /**
     * Creates new form CRUDPanel
     */
    public MultipleRecordCrudPanel() {
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

        closeToolBar = new javax.swing.JToolBar();
        closeCommand = new javax.swing.JButton();
        crudToolBar = new javax.swing.JToolBar();
        newCommand = new javax.swing.JButton();
        deleteCommand = new javax.swing.JButton();
        refreshCommand = new javax.swing.JButton();
        saveCommand = new javax.swing.JButton();

        closeToolBar.setFloatable(false);
        closeToolBar.setRollover(true);

        closeCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.close.png"))); // NOI18N
        closeCommand.setText("Close");
        closeCommand.setFocusable(false);
        closeToolBar.add(closeCommand);

        crudToolBar.setFloatable(false);
        crudToolBar.setRollover(true);

        newCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.add.to.list.png"))); // NOI18N
        newCommand.setText("New");
        newCommand.setFocusable(false);
        newCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(newCommand);

        deleteCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.delete.from.list.png"))); // NOI18N
        deleteCommand.setText("Delete");
        deleteCommand.setEnabled(false);
        deleteCommand.setFocusable(false);
        deleteCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(deleteCommand);

        refreshCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.refresh.png"))); // NOI18N
        refreshCommand.setText("Refresh");
        refreshCommand.setFocusable(false);
        refreshCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(refreshCommand);

        saveCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.save.png"))); // NOI18N
        saveCommand.setText("Save");
        saveCommand.setEnabled(false);
        saveCommand.setFocusable(false);
        saveCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(saveCommand);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(closeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(crudToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(closeToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crudToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeCommand;
    private javax.swing.JToolBar closeToolBar;
    private javax.swing.JToolBar crudToolBar;
    private javax.swing.JButton deleteCommand;
    private javax.swing.JButton newCommand;
    private javax.swing.JButton refreshCommand;
    private javax.swing.JButton saveCommand;
    // End of variables declaration//GEN-END:variables

    @Override
    public JButton getCloseCommand(){ return closeCommand; }
    @Override
    public JButton getDeleteCommand(){ return deleteCommand; }
    @Override
    public JButton getNewCommand(){ return newCommand; }
    @Override
    public JButton getRefreshCommand(){ return refreshCommand; }
    @Override
    public JButton getSaveCommand(){ return saveCommand; }
    
    public void setCloseCommand(JButton closeCommand){ this.closeCommand = closeCommand; }
    public void setDeleteCommand(JButton deleteCommand){ this.deleteCommand = deleteCommand; }
    public void setNewCommand(JButton newCommand){ this.newCommand = newCommand; }
    public void setRefreshCommand(JButton refreshCommand){ this.refreshCommand = refreshCommand; }
    public void setSaveCommand(JButton saveCommand){ this.saveCommand = saveCommand; }
    
}
