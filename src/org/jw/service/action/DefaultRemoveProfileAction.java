/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.jw.service.entity.Contact;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultRemoveProfileAction extends DependentAbstractAction{    
    private final UtilityTable<Contact> utilTable;
    
    public DefaultRemoveProfileAction(JButton command, UtilityTable<Contact> utilTable) {
        super(command.getText(), command.getIcon());
        this.utilTable = utilTable;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        try{
            Contact contact = utilTable.getSelectedItem();
            contact.setProfilePicture(null);
        } catch (NullPointerException ex){
            return false;
        }    
        return true;
    }
    
}
