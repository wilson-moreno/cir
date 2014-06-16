/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.renderer;

import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author leah
 */
public class DefaultDateCellRenderer extends DefaultTableCellRenderer{
    
    public DefaultDateCellRenderer(){}
    
    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column){
        if( value instanceof java.util.Date){
            SimpleDateFormat f = new SimpleDateFormat("MMM d, yyyy");
            value = f.format(value);
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
