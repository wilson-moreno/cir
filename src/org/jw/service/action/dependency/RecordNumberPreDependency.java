/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;

/**
 *
 * @author Wilson
 * @param <String>
 */
public class RecordNumberPreDependency<String> implements DependencyCommand{
    private final Window parent;
    private final JComboBox sgComboBox;
    
    public RecordNumberPreDependency(Window parent, JComboBox sgComboBox){
        this.parent = parent;
        this.sgComboBox = sgComboBox;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        if(sgComboBox.getModel().getSize() == 0){
            JOptionPane.showMessageDialog(parent,"Please add Service Groups.", "Service Groups", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else{
            return true;
        }    
    }

    /**
     *
     * @return
     */
    @Override
    public String get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
