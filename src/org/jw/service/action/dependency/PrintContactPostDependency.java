/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import static java.sql.Types.BOOLEAN;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.Contact;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityReportPrint;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class PrintContactPostDependency implements DependencyCommand{
    private final Window parent;
    private final UtilityReportPrint utilPrint;
    private final UtilityTable<Contact> utilTable;
    
    public PrintContactPostDependency(Window parent, UtilityTable<Contact> utilTable, UtilityReportPrint utilPrint){
        this.parent = parent;
        this.utilPrint = utilPrint;
        this.utilTable = utilTable;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        try{
            AppsReport report = (AppsReport) workerResult;
            Contact contact = utilTable.getSelectedItem();
            List<PrintParameter> parameters = new ArrayList();
            parameters.add(new PrintParameter("START_RECORD_NUMBER","",contact.getRecordNumber(), "Query"));
            parameters.add(new PrintParameter("END_RECORD_NUMBER","",contact.getRecordNumber(), "Query"));
            JCheckBox previewCheckBox = new JCheckBox();
            previewCheckBox.setSelected(Boolean.TRUE);
            utilPrint.printReport(report, parameters, previewCheckBox);
        } catch (NullPointerException ex){
            JOptionPane.showMessageDialog(parent, "Default template not found", "Report Template", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
