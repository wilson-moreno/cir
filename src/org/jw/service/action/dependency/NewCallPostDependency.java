/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactCall;

/**
 *
 * @author Wilson
 */
public class NewCallPostDependency implements DependencyCommand{
    private final DataAccessObject<ContactCall> dao;
    private final Contact contactTarget;
    private final JComponent component;
    
    public NewCallPostDependency(DataAccessObject<ContactCall> dao, Contact contactTarget, JComponent component){
        this.dao = dao;
        this.contactTarget = contactTarget;
        this.component = component;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        ContactCall call = (ContactCall) workerResult;
        call.setContactId(contactTarget);        
        dao.persist(call);
        component.requestFocusInWindow();        
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
