/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jw.service.action.DefaultCloseAction;
import org.jw.service.factory.StatisticsChartFactory;
import org.jw.service.util.UtilityDatabase;
import org.jw.service.util.UtilityProperties;
import static org.jw.service.key.binder.CndrsKeyBinders.*;


/**
 *
 * @author Wilson
 */
public class StatisticsDialog extends javax.swing.JDialog {
    private final EntityManager em;
    private final java.awt.Frame parent;
    
    /**
     * Creates new form StatisticsDialog
     */
    public StatisticsDialog(java.awt.Frame parent, boolean modal, EntityManager em) {
        super(parent, modal);        
        this.parent = parent;
        this.em = em;
        initComponents();
        initMyComponents();
    }
    
    private void initMyComponents(){
        UtilityDatabase utilDB = UtilityDatabase.create(em);
        setCommandActions();
        createContactDistributionChart(utilDB);        
        createContactAgeGroupDistributionChart(utilDB);
        createCivilStatusDistributionChart(utilDB);
        setKeyBinders();
        this.closeRefreshPanel.getRefreshCommand().setVisible(false);
    }
    
    private void setKeyBinders(){
        setKeyBinder(this.closeRefreshPanel.getCloseCommand(), controlAltX, CLOSE_MAP_KEY);
        setKeyBinder(this.closeRefreshPanel.getRefreshCommand(), controlR, REFRESH_MAP_KEY);
    }
    
    private void createCivilStatusDistributionChart(UtilityDatabase utilDB){
        try {
            CategoryDataset dataset = StatisticsChartFactory.createCategoryDataset(utilDB.getConnection(), utilProperties.getProperty("civil.status.distribution"));
            JFreeChart chart = StatisticsChartFactory.createStackedBarChart3D(dataset, "Civil Status Distribution", "Civil Status", "Distribution", PlotOrientation.VERTICAL);
            StatisticsChartFactory.connectChartPanel(this.civilStatusDistributionTab, chart);
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setCommandActions(){
        closeAction = new DefaultCloseAction(this.closeRefreshPanel.getCloseCommand(), this);
    }
    
    private void createContactAgeGroupDistributionChart(UtilityDatabase utilDB){
        try {
            CategoryDataset dataset = StatisticsChartFactory.createCategoryDataset(utilDB.getConnection(), utilProperties.getProperty("contact.age.group.distribution"));
            JFreeChart chart = StatisticsChartFactory.createStackedBarChart3D(dataset, "Age Group Distribution", "Age Groups", "Distribution", PlotOrientation.HORIZONTAL);
            StatisticsChartFactory.connectChartPanel(this.ageGroupDistributionTab, chart);
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createContactDistributionChart(UtilityDatabase utilDB){
        try {
            PieDataset dataset = StatisticsChartFactory.createPieDataset(utilDB.getConnection(), utilProperties.getProperty("service.group.contact.distribution"));
            JFreeChart chart = StatisticsChartFactory.createPieChart3D(dataset, "Contact Distribution");        
            StatisticsChartFactory.connectChartPanel(this.contactDistributionTab, chart);
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskMonitorPanel = new org.jw.service.gui.component.TaskMonitorPanel();
        closeRefreshPanel = new org.jw.service.gui.component.CloseRefreshPanel();
        statisticsTabbedPane = new javax.swing.JTabbedPane();
        contactDistributionTab = new javax.swing.JPanel();
        ageGroupDistributionTab = new javax.swing.JPanel();
        civilStatusDistributionTab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/jw/service/gui/resources/properties/dialog_titles"); // NOI18N
        setTitle(bundle.getString("statistics.dialog.title")); // NOI18N
        setResizable(false);

        javax.swing.GroupLayout contactDistributionTabLayout = new javax.swing.GroupLayout(contactDistributionTab);
        contactDistributionTab.setLayout(contactDistributionTabLayout);
        contactDistributionTabLayout.setHorizontalGroup(
            contactDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        contactDistributionTabLayout.setVerticalGroup(
            contactDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        statisticsTabbedPane.addTab("Contact Distribution", contactDistributionTab);

        javax.swing.GroupLayout ageGroupDistributionTabLayout = new javax.swing.GroupLayout(ageGroupDistributionTab);
        ageGroupDistributionTab.setLayout(ageGroupDistributionTabLayout);
        ageGroupDistributionTabLayout.setHorizontalGroup(
            ageGroupDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        ageGroupDistributionTabLayout.setVerticalGroup(
            ageGroupDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        statisticsTabbedPane.addTab("Age Distribution", ageGroupDistributionTab);

        javax.swing.GroupLayout civilStatusDistributionTabLayout = new javax.swing.GroupLayout(civilStatusDistributionTab);
        civilStatusDistributionTab.setLayout(civilStatusDistributionTabLayout);
        civilStatusDistributionTabLayout.setHorizontalGroup(
            civilStatusDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        civilStatusDistributionTabLayout.setVerticalGroup(
            civilStatusDistributionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );

        statisticsTabbedPane.addTab("Civil Status Distribution", civilStatusDistributionTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskMonitorPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeRefreshPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statisticsTabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statisticsTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeRefreshPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taskMonitorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ageGroupDistributionTab;
    private javax.swing.JPanel civilStatusDistributionTab;
    private org.jw.service.gui.component.CloseRefreshPanel closeRefreshPanel;
    private javax.swing.JPanel contactDistributionTab;
    private javax.swing.JTabbedPane statisticsTabbedPane;
    private org.jw.service.gui.component.TaskMonitorPanel taskMonitorPanel;
    // End of variables declaration//GEN-END:variables

    UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.STATISTICS_QUERY_PROPERTIES);
    DefaultCloseAction closeAction;
}
