/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.jw.service.action.DependencyCommand;

/**
 *
 * @author Wilson
 */
public class StatusIconSetPostDependency implements DependencyCommand{
    private final JButton button;
    
    public StatusIconSetPostDependency(JButton button){
        this.button = button;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        if(workerResult != null){                                     
            try {
                File file = (File) workerResult;                                
                BufferedImage img = ImageIO.read(file);                     
                button.setIcon(new ImageIcon(img));                                                
            } catch (IOException ex) {
                Logger.getLogger(ProfileSetPostDependency.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
