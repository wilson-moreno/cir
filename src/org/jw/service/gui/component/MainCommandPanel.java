/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.component;

import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Wilson
 */
public class MainCommandPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainCommandPanel
     */
    public MainCommandPanel() {
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

        toolBar = new javax.swing.JToolBar();
        exitCommand = new javax.swing.JButton();
        newCommand = new javax.swing.JButton();
        deleteCommand = new javax.swing.JButton();
        refreshCommand = new javax.swing.JButton();
        saveCommand = new javax.swing.JButton();
        contactCallsCommand = new javax.swing.JButton();
        backupCommand = new javax.swing.JButton();
        searchCommand = new javax.swing.JButton();
        printCommand = new javax.swing.JButton();
        proximityCommand = new javax.swing.JButton();
        reportsCommand = new javax.swing.JButton();
        serviceGroupComboBox = new javax.swing.JComboBox();
        separator = new javax.swing.JSeparator();

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        exitCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.system.exit.png"))); // NOI18N
        exitCommand.setText("Exit");
        exitCommand.setFocusable(false);
        exitCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(exitCommand);

        newCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.new.contact.png"))); // NOI18N
        newCommand.setText("New");
        newCommand.setFocusable(false);
        newCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(newCommand);

        deleteCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.delete.contact.png"))); // NOI18N
        deleteCommand.setText("Delete");
        deleteCommand.setFocusable(false);
        deleteCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(deleteCommand);

        refreshCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.refresh.png"))); // NOI18N
        refreshCommand.setText("Refresh");
        refreshCommand.setFocusable(false);
        refreshCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(refreshCommand);

        saveCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.save.png"))); // NOI18N
        saveCommand.setText("Save");
        saveCommand.setFocusable(false);
        saveCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(saveCommand);

        contactCallsCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.show.calls.png"))); // NOI18N
        contactCallsCommand.setText("Calls");
        contactCallsCommand.setFocusable(false);
        contactCallsCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(contactCallsCommand);

        backupCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.database.backup.png"))); // NOI18N
        backupCommand.setText("Backup");
        backupCommand.setFocusable(false);
        backupCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(backupCommand);

        searchCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.search.contact.png"))); // NOI18N
        searchCommand.setText("Search");
        searchCommand.setFocusable(false);
        searchCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(searchCommand);

        printCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.print.contact.png"))); // NOI18N
        printCommand.setText("Print");
        printCommand.setFocusable(false);
        printCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(printCommand);

        proximityCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.compass.icon.png"))); // NOI18N
        proximityCommand.setText("Proximity");
        proximityCommand.setFocusable(false);
        proximityCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(proximityCommand);

        reportsCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.report.print.icon.png"))); // NOI18N
        reportsCommand.setText("Reports");
        reportsCommand.setFocusable(false);
        reportsCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        toolBar.add(reportsCommand);

        toolBar.add(serviceGroupComboBox);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(separator)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backupCommand;
    private javax.swing.JButton contactCallsCommand;
    private javax.swing.JButton deleteCommand;
    private javax.swing.JButton exitCommand;
    private javax.swing.JButton newCommand;
    private javax.swing.JButton printCommand;
    private javax.swing.JButton proximityCommand;
    private javax.swing.JButton refreshCommand;
    private javax.swing.JButton reportsCommand;
    private javax.swing.JButton saveCommand;
    private javax.swing.JButton searchCommand;
    private javax.swing.JSeparator separator;
    private javax.swing.JComboBox serviceGroupComboBox;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables

    public JButton getContactCallsCommand(){ return contactCallsCommand; }
    public JComboBox getServiceGroupComboBox(){ return serviceGroupComboBox; }

    /**
     * @return the backupCommand
     */
    public javax.swing.JButton getBackupCommand() {
        return backupCommand;
    }

    /**
     * @param backupCommand the backupCommand to set
     */
    public void setBackupCommand(javax.swing.JButton backupCommand) {
        this.backupCommand = backupCommand;
    }

    /**
     * @return the deleteCommand
     */
    public javax.swing.JButton getDeleteCommand() {
        return deleteCommand;
    }

    /**
     * @return the exitCommand
     */
    public javax.swing.JButton getExitCommand() {
        return exitCommand;
    }

    /**
     * @return the newCommand
     */
    public javax.swing.JButton getNewCommand() {
        return newCommand;
    }

    /**
     * @return the printContactCommand
     */
    public javax.swing.JButton getPrintCommand() {
        return printCommand;
    }

    /**
     * @return the refreshCommand
     */
    public javax.swing.JButton getRefreshCommand() {
        return refreshCommand;
    }

    /**
     * @return the saveCommand
     */
    public javax.swing.JButton getSaveCommand() {
        return saveCommand;
    }

    /**
     * @return the searchCommand
     */
    public javax.swing.JButton getSearchCommand() {
        return searchCommand;
    }
    
    public javax.swing.JButton getReportsCommand(){
        return reportsCommand;
    }
    
    
}
