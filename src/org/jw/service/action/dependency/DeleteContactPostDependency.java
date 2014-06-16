/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.event.ActionEvent;
import org.jw.service.entity.Contact;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class DeleteContactPostDependency implements DependencyCommand{
    public UtilityTree utilTree;
    
    public DeleteContactPostDependency(UtilityTree utilTree){
        this.utilTree = utilTree;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        Contact remove = (Contact) workerResult;
        utilTree.removeNode(remove);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
