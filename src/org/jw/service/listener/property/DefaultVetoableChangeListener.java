/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.property;

import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.JOptionPane;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactStatus;

/**
 *
 * @author Wilson
 */
public class DefaultVetoableChangeListener implements VetoableChangeListener{
    private final Window parent;
    
    public DefaultVetoableChangeListener(Window parent){
        this.parent = parent;
    }
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {        
        if(evt.getSource() instanceof Contact){
            Contact contact;
            ContactStatus status;
            
            contact = (Contact) evt.getSource();
            status = contact.getStatusId();
            
            if(status != null && !status.getModifiable()){
                JOptionPane.showMessageDialog(parent, "The status of the contact does not permit it to be modified","Unmodifiable",JOptionPane.WARNING_MESSAGE);
                throw new PropertyVetoException("Not modifiable", evt);
            }
        }
    }
    
}
