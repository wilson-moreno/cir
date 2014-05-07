/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultOpenAction<T> extends AbstractAction{
    private final JFrame parent;
    private final JDialog dialog;
    
    public DefaultOpenAction(AbstractButton command, JFrame parent, JDialog dialog){
        super(command.getText(), command.getIcon());
        command.setAction(this);
        this.parent = parent;
        this.dialog = dialog;
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.setLocationRelativeTo(parent);
        dialog.setModal(true);
        dialog.setVisible(true);
    }
    
}
