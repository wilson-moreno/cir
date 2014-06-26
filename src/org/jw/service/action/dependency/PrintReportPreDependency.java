/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import org.jw.service.action.DependencyCommand;

/**
 *
 * @author Wilson
 */
public class PrintReportPreDependency implements DependencyCommand{
    private final javax.swing.JTable table;
    
    public PrintReportPreDependency(javax.swing.JTable table){
        this.table = table;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        if(table.isEditing())return table.getCellEditor().stopCellEditing();
        return true;
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
