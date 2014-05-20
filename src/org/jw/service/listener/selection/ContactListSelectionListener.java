/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.selection;

import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class ContactListSelectionListener implements ListSelectionListener{

    public static ContactListSelectionListener create(AbstractAction contactCallsAction, AbstractAction contactPrintAction) {
        return new ContactListSelectionListener(contactCallsAction, contactPrintAction);
    }
    
    private final AbstractAction contactCallsAction;
    private final AbstractAction contactPrintAction;
    
    private ContactListSelectionListener(AbstractAction contactCallsAction, AbstractAction contactPrintAction){    
        this.contactCallsAction = contactCallsAction;
        this.contactPrintAction = contactPrintAction;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {          
        if(!lse.getValueIsAdjusting()){            
            contactCallsAction.setEnabled(true);
            contactPrintAction.setEnabled(false);
        }
    }
    
}
