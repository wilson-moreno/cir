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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Wilson
 */
public class DefaultStateCellRenderer extends DefaultTableCellRenderer{

    public static DefaultStateCellRenderer create() {
        return new DefaultStateCellRenderer();
    }
    
    private DefaultStateCellRenderer(){}
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        setHorizontalAlignment(JLabel.CENTER);
        if(table != null){
            JTableHeader header = table.getTableHeader();
            if(header != null){
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }    

        if(isSelected)setFont(getFont().deriveFont(Font.BOLD));

        setText((value == null) ? "":value.toString());
        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        
        return this;
    }
}
