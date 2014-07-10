/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list.selection.listener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jw.service.entity.Contact;
import org.jw.service.entity.Territory;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class ContactTableSelectionListener implements ListSelectionListener{
    private final UtilityTable<Contact> utilTable;
    private final JComboBox territoryComboBox;
    
    public ContactTableSelectionListener(UtilityTable<Contact> utilTable, JComboBox territoryComboBox){
        this.utilTable = utilTable;
        this.territoryComboBox = territoryComboBox;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            Contact contact = utilTable.getSelectedItem();
            DefaultComboBoxModel model = (DefaultComboBoxModel) territoryComboBox.getModel();                                    
        }
    }
    
}
