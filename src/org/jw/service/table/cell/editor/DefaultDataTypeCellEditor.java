/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.editor;

import java.awt.Component;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.jw.service.print.PrintParameter;

/**
 *
 * @author Wilson
 */
public class DefaultDataTypeCellEditor extends AbstractCellEditor implements TableCellEditor{
    private final List<PrintParameter> list;
    
    public static DefaultDataTypeCellEditor create(List<PrintParameter> list) {
        return new DefaultDataTypeCellEditor(list);
    }
    private TableCellEditor editor;
    
    
    private DefaultDataTypeCellEditor(List<PrintParameter> list){
        this.list = list;
    }
    
    @Override
    public Object getCellEditorValue() {
         if (editor != null) {
            return editor.getCellEditorValue();
         }         
         return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PrintParameter param = list.get(row);
        
        switch(param.getDataType()){
            case "String" : editor = new DefaultCellEditor(new JTextField()); break;
            case "Boolean" : editor = new DefaultCellEditor(new JCheckBox()); break;
            case "List" : editor = new DefaultCellEditor(new JComboBox()); break;
            default : editor = new DefaultCellEditor(new JTextField()); break;
        }
        
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
    
}
