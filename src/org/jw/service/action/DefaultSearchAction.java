/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JButton;
import org.jw.service.entity.Contact;
import org.jw.service.list.ContactMatcher;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityCollections;
import org.jw.service.util.UtilityTable;
import org.jw.service.worker.DefaultSearchWorker;

/**
 *
 * @author Wilson
 */
public class DefaultSearchAction extends DependentAbstractAction{
    private final Contact keyContact;    
    private final List<Contact> searchList;
    private final List<Contact> foundList;
    private final Window parent;
    private final DefaultTaskListener listener;
    
    public DefaultSearchAction(JButton command, Window parent, Contact keyContact, List<Contact> searchList, List<Contact> foundList, DefaultTaskListener listener) {
        super(command.getText(), command.getIcon());
        this.parent = parent;
        this.keyContact = keyContact;
        this.searchList = searchList;
        this.foundList = foundList;        
        this.listener = listener;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        ContactMatcher matcher = new ContactMatcher();
        DefaultSearchWorker worker = new DefaultSearchWorker(listener, searchList, foundList, keyContact, matcher);
        worker.execute();        
        return true;
    }
    
}
