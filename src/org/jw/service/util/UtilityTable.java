/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.util.List;
import javax.swing.JTable;
import org.jw.service.entity.ObservableEntity;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class UtilityTable<T> {
    private final List<T> list;
    private final JTable table;
    
    
    public static <T> UtilityTable <T> create(JTable table, List<T> list){
        return new UtilityTable(table, list);
    }
    
    private UtilityTable(JTable table, List<T> list){
        this.table = table;
        this.list = list;            
    }    
    
    
    public void refreshAll(){
        table.getRowSorter().allRowsChanged();
    }
    
    public int getSelectedModelIndex(){
        int modelRowIndex = -1;
        int viewRowIndex = table.getSelectedRow();        
        if(viewRowIndex >= 0)
            modelRowIndex = table.getRowSorter().convertRowIndexToModel(viewRowIndex);
        return modelRowIndex;
    }
    
    public void selectRow(int index){
        if(index >= 0){
            int viewIndex = table.getRowSorter().convertRowIndexToView(index);
            table.getSelectionModel().setSelectionInterval(viewIndex, viewIndex);                    
            table.scrollRectToVisible(table.getCellRect(viewIndex, 0, true));                                 
        }
    }
    
    public void selectItemRow(T item){
        int modelIndex = list.indexOf(item);        
        selectRow(modelIndex);
    }
    
    public T getSelectedItem(){
        T selectedItem = null;
        int modelIndex = this.getSelectedModelIndex();
        if(modelIndex >= 0)
            selectedItem = list.get(modelIndex);
        return selectedItem;
    }
    
    public boolean isSomeRecordUnsaved(){
        boolean result = false;
        
        for(T item : list){
            ObservableEntity entity = (ObservableEntity)item;
            if(entity.getSaveState().contains("*")){
                result = true;
                break;
            }                
        }
        
        return result;
    }
    
    public boolean isSelectedItemNameUnique(){
        boolean result = true;
        
        ObservableEntity selectedItem = (ObservableEntity) this.getSelectedItem();
        
        for(T item : list){
            ObservableEntity currentItem = (ObservableEntity)item;
            result = isNameUnique(selectedItem, currentItem);
            if(result == false)break;
        }
        
        return result;
    }
    
    private boolean isNameUnique(ObservableEntity e1, ObservableEntity e2){
        if(e1.getId().intValue() == e2.getId().intValue())return true;
        return !e1.getName().trim().equalsIgnoreCase(e2.getName().trim());               
    }
    
    public boolean isRowSelected(){
        if(this.getSelectedModelIndex() > -1)return true;
        else return false;
    }
}
