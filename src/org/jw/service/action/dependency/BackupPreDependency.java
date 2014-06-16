/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;

/**
 *
 * @author Wilson
 */
public class BackupPreDependency implements DependencyCommand{
    private final Window parent;
    
    
    public BackupPreDependency(Window parent){
        this.parent = parent;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        int result = JOptionPane.showConfirmDialog(parent, "Database backup will be created, proceed?","Database Backup", JOptionPane.INFORMATION_MESSAGE);        
        switch(result){
            case JOptionPane.YES_OPTION : return true;
            default : return false;
        }
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
