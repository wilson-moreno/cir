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
 * @param <T>
 */
public class DefaultDeletePreDependency<T> implements DependencyCommand {
    private final Window parent;
    
    public DefaultDeletePreDependency(Window parent){
        this.parent = parent;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        boolean result = true;
        switch(JOptionPane.showConfirmDialog(parent, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION)){
            case JOptionPane.YES_OPTION : result = true; break;
            case JOptionPane.NO_OPTION : result = false; break;
        }
        
        return result;        
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
