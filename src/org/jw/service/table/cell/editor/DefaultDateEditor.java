/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.editor;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Wilson
 */
public class DefaultDateEditor extends DefaultCellEditor{    
    private java.util.Date dateValue;
    private java.text.SimpleDateFormat dateFormat;
    private JDateChooser dateChooser;
        
    public DefaultDateEditor() {
        super(new JTextField());    
        this.dateValue = new java.util.Date();
        this.dateFormat = new java.text.SimpleDateFormat("MMM d, yyyy");
        this.dateChooser = new JDateChooser();
        this.dateChooser.setDateFormatString("MMM d, yyyy");
    }    
    
    @Override
    public Object getCellEditorValue(){
        return dateFormat.format(dateChooser.getDate());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column){        
        dateValue = convertToDate(value.toString());        
        dateChooser.setDate(dateValue);
        return dateChooser;
    }
 
    
    private java.util.Date convertToDate(String value){
        try {
            return dateFormat.parse(value);
        } catch (ParseException ex) {
            return new java.util.Date();
        }
    }
}
