/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.selection;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class ContactListSelectionListener implements ListSelectionListener{
    public final List<AbstractAction> actions = new ArrayList<>();
        
    
    public static ContactListSelectionListener create() {
        return new ContactListSelectionListener();
    }
    
    private ContactListSelectionListener(){}
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {          
        if(!lse.getValueIsAdjusting()){            
            for(AbstractAction action : actions){
                action.setEnabled(true);
            }
        }
    }
    
    
    public void addAction(AbstractAction action){
        actions.add(action);
    }
    
    public void removeAction(AbstractAction action){
        actions.remove(action);
    }
}
