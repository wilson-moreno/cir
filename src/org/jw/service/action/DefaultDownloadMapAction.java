/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.jw.service.entity.Contact;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadMapAction extends DependentAbstractAction{
    public final UtilityTable<Contact> utilTable;
    public final UtilityDownload utilDownload;
    
    public DefaultDownloadMapAction(JButton command, UtilityTable<Contact> utilTable, UtilityDownload utilDownload){
        super(command.getText(), command.getIcon());
        this.utilTable = utilTable;
        this.utilDownload = utilDownload;
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        Contact contact = utilTable.getSelectedItem();
        
        return true;
    }
}
