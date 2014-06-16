/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.item;

import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class DefaultServiceGroupItemListener implements ItemListener{
    private final Window parent;
    
    public DefaultServiceGroupItemListener(Window parent){
        this.parent = parent;
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie) {        
        if(ie.getStateChange() == ItemEvent.SELECTED){
            ServiceGroup serviceGroup = (ServiceGroup) ie.getItem();
            if(serviceGroup != null){
                JOptionPane.showMessageDialog(parent, "Service Group: " + serviceGroup.getName() + " is set.", "Service Group", JOptionPane.INFORMATION_MESSAGE);                
            }
        }
    }
    
}
