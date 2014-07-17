/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.editor;

import com.toedter.calendar.JYearChooser;
import java.awt.Component;
import java.util.Calendar;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Wilson
 */
public class DefaultYearEditor extends DefaultCellEditor{    
    private JYearChooser yearChooser;
    private int yearValue;
    
    public DefaultYearEditor() {
        super(new JTextField());            
        this.yearChooser = new JYearChooser();
    }    
    
    @Override
    public Object getCellEditorValue(){
        return yearChooser.getYear();
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column){        
        yearValue = convertToInteger(value.toString());        
        yearChooser.setYear(yearValue);
        return yearChooser;
    }
 
    
    private int convertToInteger(String value){
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return Calendar.getInstance().get(Calendar.YEAR);            
        }
    }
}
