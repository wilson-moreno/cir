/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author Wilson
 */
public class DefaultCloseAction<T> extends AbstractAction{
    private final JDialog dialog;
    
    public DefaultCloseAction(JButton command, JDialog dialog){
        super(command.getText(), command.getIcon());        
        this.dialog = dialog;
        command.setAction(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.dispose();
    }
    
}
