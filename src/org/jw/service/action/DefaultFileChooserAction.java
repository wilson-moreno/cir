/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityFileChooser;

/**
 *
 * @author Wilson
 */
public class DefaultFileChooserAction extends DependentAbstractAction{
    private final Window parent;
    private final DefaultTaskListener listener;
    private final UtilityFileChooser utilFC;
    private final String mode;
    
    public DefaultFileChooserAction(JButton command, Window parent, FileFilter filter, String mode, int selectionMode, DefaultTaskListener listener) {
        super(command.getText(), command.getIcon());
        this.parent = parent;
        this.listener = listener;
        this.utilFC = UtilityFileChooser.create(parent);
        utilFC.setFileFilter(filter);
        utilFC.setFileSelectionMode(selectionMode);
        this.mode = mode;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        boolean result = false;
        
        
        switch(mode){
            case "Open" : result = showOpenDialog(); break;
            case "Save" : result = showSaveDialog(); break;
        }
        
        return result;
    }
    
    protected boolean showSaveDialog(){
        boolean result = false;        
        
        switch(utilFC.showSaveDialog()){
            case JFileChooser.APPROVE_OPTION : this.workerResult = utilFC.getSelectedFile(); 
                                               result = true;
                                               break;
            default : result = false;    
        }                       
        
        return result;
    }
    
    protected boolean showOpenDialog(){
        boolean result = false;
        
        switch(utilFC.showOpenDialog()){
            case JFileChooser.APPROVE_OPTION : this.workerResult = utilFC.getSelectedFile(); 
                                               result = true;
                                               break;
            default : result = false;    
        }                       
        
        return result;
    }
    
}
