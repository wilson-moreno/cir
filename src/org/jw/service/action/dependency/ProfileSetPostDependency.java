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
import javax.swing.JLabel;
import org.jw.service.action.DependencyCommand;

/**
 *
 * @author Wilson
 */
public class ProfileSetPostDependency implements DependencyCommand{
    private final JLabel label;
    
    public ProfileSetPostDependency(JLabel label){
        this.label = label;
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
                label.setIcon(new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH)));                
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
