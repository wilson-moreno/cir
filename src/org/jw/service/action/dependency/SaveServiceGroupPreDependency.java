/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 *
 * @author Wilson
 */
public class SaveServiceGroupPreDependency implements DependencyCommand{
    private final JTextField startNumber;
    private final JTextField nextNumber;    
    
    public SaveServiceGroupPreDependency(JTextField startNumber, JTextField nextNumber){
        this.startNumber = startNumber;
        this.nextNumber = nextNumber;    
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        if(nextNumber.getText().equals("0")){
            nextNumber.setText(startNumber.getText());
        }
        return true;
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
