/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.Window;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Wilson
 */
public class UtilityFileChooser {
    private final JFileChooser fc;
    private final Window parent;            
    
    public static UtilityFileChooser create(Window parent) {
        return new UtilityFileChooser(parent);
    }
    

    private UtilityFileChooser(Window parent){
        this.parent = parent;
        this.fc = new JFileChooser();
    }
    
    public int showOpenDialog(){
        return fc.showOpenDialog(parent);        
    }
    
    public File getSelectedFile(){
        return fc.getSelectedFile();
    }
    
    public void setFileFilter(FileFilter filter){
        fc.setFileFilter(filter);
    }
    
}
