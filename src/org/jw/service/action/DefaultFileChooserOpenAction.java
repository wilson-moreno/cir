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
public class DefaultFileChooserOpenAction extends DependentAbstractAction{
    private final Window parent;
    private final DefaultTaskListener listener;
    private final UtilityFileChooser utilFC;
    
    public DefaultFileChooserOpenAction(JButton command, Window parent, FileFilter filter, DefaultTaskListener listener) {
        super(command.getText(), command.getIcon());
        this.parent = parent;
        this.listener = listener;
        this.utilFC = UtilityFileChooser.create(parent);
        utilFC.setFileFilter(filter);
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        boolean result;
        
        switch(utilFC.showOpenDialog()){
            case JFileChooser.APPROVE_OPTION : this.workerResult = utilFC.getSelectedFile(); 
                                               result = true;
                                               break;
            default : result = false;    
        }                       
        
        return result;
    }
    
}
