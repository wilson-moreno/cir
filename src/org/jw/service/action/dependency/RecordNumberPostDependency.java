/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class RecordNumberPostDependency implements DependencyCommand{
    private final JComboBox sgComboBox;
    private final DataAccessObject<ServiceGroup> serviceGroupDAO;    
    private final DataAccessObject<Contact> contactDAO;    
    
    public RecordNumberPostDependency(DataAccessObject<Contact> contactDAO, DataAccessObject<ServiceGroup> serviceGroupDAO, JComboBox sgComboBox){        
        this.sgComboBox = sgComboBox;
        this.serviceGroupDAO = serviceGroupDAO;
        this.contactDAO = contactDAO;
    }
    
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        Contact contact = (Contact) workerResult;
        ServiceGroup serviceGroup = (ServiceGroup) sgComboBox.getSelectedItem();
        String recordNumber = serviceGroup.useNextRecordNumber();
        serviceGroup.setSaveState("");
        contact.silentSetProperty("recordNumber",recordNumber);
        contact.silentSetProperty("serviceGroupId", serviceGroup);
        contact.setSaveState("");
        ServiceGroup saveServiceGroup = serviceGroupDAO.save(serviceGroup);
        Contact saveContact = contactDAO.save(contact);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
