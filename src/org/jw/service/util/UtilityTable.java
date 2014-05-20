/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.util.List;
import javax.swing.JTable;
import org.jw.service.entity.Contact;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class UtilityTable<T> {
    private final List<T> list;
    private final JTable table;
    
    public UtilityTable(JTable table, List<T> list){
        this.table = table;
        this.list = list;
    }
    
    public int getSelectedModelIndex(){
        int viewRowIndex;
        viewRowIndex = table.getSelectedRow();        
        return table.getRowSorter().convertRowIndexToModel(viewRowIndex);
    }
    
    public void selectRow(int index){
        int viewIndex = table.getRowSorter().convertRowIndexToView(index);
        table.getSelectionModel().setSelectionInterval(viewIndex, viewIndex);                    
        table.scrollRectToVisible(table.getCellRect(viewIndex, 0, true));                                 
    }
    
    public void selectItemRow(T item){
        int modelIndex = list.indexOf(item);        
        selectRow(modelIndex);
    }
    
    public T getSelectedItem(){
        return list.get(this.getSelectedModelIndex());
    }
}
