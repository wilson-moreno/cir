/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 *
 * @author Wilson
 */
public class DefaultCloseAction<T> extends AbstractAction{
    private final Window window;
    
    public DefaultCloseAction(JButton command, Window window){
        super(command.getText(), command.getIcon());        
        this.window = window;
        command.setAction(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        window.dispose();
    }
    
}
