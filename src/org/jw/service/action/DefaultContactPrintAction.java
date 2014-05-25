/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.jw.service.util.UtilityReportPrint;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultContactPrintAction extends DependentAbstractAction{
    private final UtilityTable utilTable;
    private final UtilityReportPrint utilPrint;
    
    public DefaultContactPrintAction(JButton command, UtilityTable utilTable, UtilityReportPrint utilPrint) {
        super(command.getText(), command.getIcon());
        this.utilTable = utilTable;
        this.utilPrint = utilPrint;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        
        return true;
    }
    
}
