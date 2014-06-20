/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.component;

/**
 *
 * @author Wilson
 */
public class MapCrudPanel extends javax.swing.JPanel {

    /**
     * Creates new form MapCrudPanel
     */
    public MapCrudPanel() {
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

        refreshCommand = new javax.swing.JButton();
        closeToolBar = new javax.swing.JToolBar();
        closeCommand = new javax.swing.JButton();
        crudToolBar = new javax.swing.JToolBar();
        downloadCommand = new javax.swing.JButton();
        saveCommand = new javax.swing.JButton();

        refreshCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.refresh.png"))); // NOI18N
        refreshCommand.setText("Refresh");
        refreshCommand.setFocusable(false);
        refreshCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        closeToolBar.setFloatable(false);
        closeToolBar.setRollover(true);

        closeCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.close.png"))); // NOI18N
        closeCommand.setText("Close");
        closeCommand.setFocusable(false);
        closeToolBar.add(closeCommand);

        crudToolBar.setFloatable(false);
        crudToolBar.setRollover(true);

        downloadCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.download.png"))); // NOI18N
        downloadCommand.setText("Download Map");
        downloadCommand.setFocusable(false);
        downloadCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(downloadCommand);

        saveCommand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/jw/service/gui/resources/icon/default.save.png"))); // NOI18N
        saveCommand.setText("Save");
        saveCommand.setFocusable(false);
        saveCommand.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        crudToolBar.add(saveCommand);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(closeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 401, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 298, Short.MAX_VALUE)
                    .addComponent(crudToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(closeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(crudToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeCommand;
    private javax.swing.JToolBar closeToolBar;
    private javax.swing.JToolBar crudToolBar;
    private javax.swing.JButton downloadCommand;
    private javax.swing.JButton refreshCommand;
    private javax.swing.JButton saveCommand;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the closeCommand
     */
    public javax.swing.JButton getCloseCommand() {
        return closeCommand;
    }

    /**
     * @return the newCommand
     */
    public javax.swing.JButton getDownloadCommand() {
        return downloadCommand;
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
}
