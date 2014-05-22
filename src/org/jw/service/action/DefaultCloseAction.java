/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultCloseAction<T> extends DependentAbstractAction{
    private final Window window;
    
    public DefaultCloseAction(JButton command, Window window){
        super(command.getText(), command.getIcon());        
        this.window = window;
        command.setAction(this);
    }
    
    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        window.dispose();
        return true;
    }
    
}
