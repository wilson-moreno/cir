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
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;

/**
 *
 * @author Wilson
 */
public class VetoableTerritoryIdChangeListener implements VetoableChangeListener{

    public static VetoableTerritoryIdChangeListener create(Window parent) {
        return new VetoableTerritoryIdChangeListener(parent);
    }
    private final Window parent;
    
    private VetoableTerritoryIdChangeListener(Window parent){
        this.parent = parent;
    }
    
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {        
        Contact contact = (Contact) evt.getSource();
        Territory territory = (Territory) evt.getNewValue();
        ServiceGroup serviceGroup = (ServiceGroup) contact.getServiceGroupId();     
        if(!serviceGroup.getTerritoryCollection().contains(territory)){
            JOptionPane.showMessageDialog(parent, "The territory you selected belongs to a different service group", "Invalid Territory", JOptionPane.ERROR_MESSAGE);            
            throw new PropertyVetoException("Invalid Territory", evt);
        }
    }
    
}
