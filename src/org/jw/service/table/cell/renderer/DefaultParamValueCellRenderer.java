/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.renderer;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Wilson
 */
public class DefaultParamValueCellRenderer extends DefaultTableCellRenderer{
   
    public DefaultParamValueCellRenderer(){}
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        setHorizontalAlignment(JLabel.CENTER);

        if(isSelected)setFont(getFont().deriveFont(Font.BOLD));

        setText((value == null) ? "":value.toString());
        
        return this;
    }    
    
}
