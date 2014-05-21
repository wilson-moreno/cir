/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Wilson
 */
public class DefaultContactPrintAction extends DependentAbstractAction{

    public DefaultContactPrintAction(JButton command) {
        super(command.getText(), command.getIcon());
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
