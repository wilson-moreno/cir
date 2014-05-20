/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.selection;

import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultListSelectionListener<T> implements ListSelectionListener{

    public static <T> DefaultListSelectionListener<T> create(List<T> list, JTable table, AbstractAction saveAction, AbstractAction deleteAction) {
        return new DefaultListSelectionListener<>(list, table, saveAction, deleteAction);
    }
    private final List<T> list;
    private final UtilityTable utilTable;
    private final AbstractAction saveAction;
    private final AbstractAction deleteAction;
    
    private DefaultListSelectionListener(List<T> list, JTable table, AbstractAction saveAction, AbstractAction deleteAction){
        this.list = list;
        this.utilTable = new UtilityTable(table, list);
        this.saveAction = saveAction;
        this.deleteAction = deleteAction;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent lse) {          
        if(!lse.getValueIsAdjusting()){            
            deleteAction.setEnabled(true);
            ObservableEntity entity = (ObservableEntity)utilTable.getSelectedItem();
            if(entity.getSaveState().equalsIgnoreCase("*")){
                saveAction.setEnabled(true);
            }else{
                saveAction.setEnabled(false);
            }
        }
    }
    
}
