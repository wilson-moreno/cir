/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service;

import javax.swing.JFrame;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.gui.ApplicationFrame;

/**
 *
 * @author Wilson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        java.awt.EventQueue.invokeLater(new RunnableImpl());        
        
    }
    
    private static class RunnableImpl implements Runnable {

        public RunnableImpl(){}

        @Override
        public void run() {                          
            JFrame.setDefaultLookAndFeelDecorated(true);
            ApplicationFrame applicationFrame = new ApplicationFrame();            
            //contactFrame.setIconImage(ImageIconUtil.getInstance().getImageIcon(CIMS_LOGO, contactFrame).getImage());
            applicationFrame.setVisible(true);
        }
    }
    
}
