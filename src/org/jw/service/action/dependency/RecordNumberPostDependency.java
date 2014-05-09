/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class RecordNumberPostDependency implements DependencyCommand{
    private final Window parent;
    private final JComboBox sgComboBox;
    
    public RecordNumberPostDependency(Window parent, JComboBox sgComboBox){
        this.parent = parent;
        this.sgComboBox = sgComboBox;
    }
    
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        ServiceGroup serviceGroup = (ServiceGroup) sgComboBox.getSelectedItem();
        Contact contact = (Contact)workerResult;
        String prefix = serviceGroup.getPrefix().trim();
        String nextNumber = serviceGroup.getNextNumber().toString().trim();
        serviceGroup.setNextNumber(serviceGroup.getNextNumber().intValue() + 1);
        contact.setRecordNumber(prefix + nextNumber);
    }
    
}
