/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import javax.swing.JTable;

/**
 *
 * @author Wilson
 */
public class UtilityTable {

    public static UtilityTable create(JTable table) {
        return new UtilityTable(table);
    }
    private final JTable table;
    
    private UtilityTable(JTable table){
        this.table = table;
    }
    
    public int getSelectedModelIndex(){
        int viewRowIndex;
        viewRowIndex = table.getSelectedRow();
        return table.getRowSorter().convertRowIndexToModel(viewRowIndex);
    }
}
