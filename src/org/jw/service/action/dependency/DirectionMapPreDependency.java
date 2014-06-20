/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.Contact;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DirectionMapPreDependency implements DependencyCommand{
    private final UtilityTable<Contact> utilTable;    
    
    public DirectionMapPreDependency(UtilityTable<Contact> utilTable){
        this.utilTable = utilTable;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        return true;
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
