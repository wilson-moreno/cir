/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultSaveWorker<T> extends SwingWorker<T, String>{
    private final DataAccessObject dao;
    private final UtilityTable<T> utilTable;
    private final List<T> list;
    
    public DefaultSaveWorker(DataAccessObject dao, JTable table, List<T> list, DefaultTaskListener listener){
       this.dao = dao; 
       this.list = list;
       this.utilTable = UtilityTable.create(table, list);
       this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected T doInBackground() throws Exception {
        int modelIndex = utilTable.getSelectedModelIndex();                                
        ObservableEntity entity = null;
        if(modelIndex >= 0){
            entity = (ObservableEntity)list.get(modelIndex);
            entity.setSaveState("");            
            entity = (ObservableEntity)dao.save(entity);                    
        }
        return (T)entity;
    }
    
}
