/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.util.UtilityDatabase;

/**
 *
 * @author Wilson
 */
public class BackupPostDependency implements DependencyCommand{
    private final Window parent;
    private final UtilityDatabase utilDB;
    
    public BackupPostDependency(Window parent, UtilityDatabase utilDB){
        this.parent = parent;
        this.utilDB = utilDB; 
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        File file = (File)workerResult;
        String absolutePath = file.getAbsolutePath();        
        utilDB.backupTo("'" + absolutePath + "\\'");
        JOptionPane.showMessageDialog(parent, "Database backup successful!", "Database Backup", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
