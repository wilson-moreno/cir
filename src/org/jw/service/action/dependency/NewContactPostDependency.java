/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class NewContactPostDependency implements DependencyCommand{
    private final JComboBox sgComboBox;
    private final DataAccessObject<ServiceGroup> serviceGroupDAO;    
    private final DataAccessObject<Contact> contactDAO;   
    private final UtilityTree utilTree;
    private final JTabbedPane tabbedPane;
    private final JTextField lastNameTextField;
    
    public NewContactPostDependency(DataAccessObject<Contact> contactDAO, 
                                    DataAccessObject<ServiceGroup> serviceGroupDAO, 
                                    JComboBox sgComboBox, 
                                    UtilityTree utilTree, 
                                    JTabbedPane tabbedPane,
                                    JTextField lastNameTextField){        
        this.sgComboBox = sgComboBox;
        this.serviceGroupDAO = serviceGroupDAO;
        this.contactDAO = contactDAO;
        this.utilTree = utilTree;
        this.tabbedPane = tabbedPane;
        this.lastNameTextField = lastNameTextField;
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
        contact.setSaveState("*");
        ServiceGroup saveServiceGroup = serviceGroupDAO.persist(serviceGroup);
        Contact saveContact = contactDAO.persist(contact);
        utilTree.addNode(contact);
        tabbedPane.setSelectedIndex(0);
        lastNameTextField.requestFocusInWindow();
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
