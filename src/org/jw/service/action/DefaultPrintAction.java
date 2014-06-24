/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityReportPrint;

/**
 *
 * @author Wilson
 */
public class DefaultPrintAction  extends DependentAbstractAction{
    private final UtilityReportPrint utilPrint;
    private final JComboBox comboBox;
    private final List<PrintParameter> paramList;
    private final JCheckBox previewCheckBox;
    
    public DefaultPrintAction(JButton command, JComboBox comboBox, UtilityReportPrint utilPrint, List<PrintParameter> paramList, JCheckBox previewCheckBox) {
        super(command.getText(), command.getIcon());
        this.utilPrint = utilPrint;
        this.comboBox = comboBox;
        this.paramList = paramList;
        this.previewCheckBox = previewCheckBox;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        AppsReport report = (AppsReport) comboBox.getSelectedItem();
        utilPrint.printReport(report, paramList, previewCheckBox);        
        return true;
    }
    
}
