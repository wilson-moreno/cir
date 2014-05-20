/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.jw.service.entity.AppsReport;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityReportPrint;

/**
 *
 * @author Wilson
 */
public class DefaultPrintAction  extends DependentAbstractAction{
    private final UtilityReportPrint utilPrint;
    private final JComboBox comboBox;
    private final List<PrintParameter> list;
    
    public DefaultPrintAction(JButton command, JComboBox comboBox, UtilityReportPrint utilPrint, List<PrintParameter> list) {
        super(command.getText(), command.getIcon());
        this.utilPrint = utilPrint;
        this.comboBox = comboBox;
        this.list = list;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        AppsReport report = (AppsReport) comboBox.getSelectedItem();
        
        for(PrintParameter param : list){
            System.out.println(param.getName());
        }
        
        return true;
    }
    
}
