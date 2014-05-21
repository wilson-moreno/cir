/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class SaveServiceGroupPostDependency implements DependencyCommand{        
    private final UtilityTree utilTree;
    
    public SaveServiceGroupPostDependency(UtilityTree utilTree){
        this.utilTree = utilTree;  
    }
    
    @Override
    public boolean run(ActionEvent ae) {        
        return true;
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        ServiceGroup serviceGroup = (ServiceGroup) workerResult;
        utilTree.refresh(serviceGroup);
    }
    
}
