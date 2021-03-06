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
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultDeleteWorker<T> extends SwingWorker<T, String>{
    private final DataAccessObject<T> dao;
    private final UtilityTable utilTable;
    private final List<T> list;
    
    public DefaultDeleteWorker(DataAccessObject<T> dao, List<T> list, JTable table, DefaultTaskListener listener){
        this.dao = dao;
        this.list = list;
        this.utilTable = UtilityTable.create(table, list);
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected T doInBackground() throws Exception {
        T remove = null;        
        
        int modelIndex = utilTable.getSelectedModelIndex();
        if(modelIndex >= 0){
            remove = list.remove(modelIndex);
            dao.delete(remove);
        }
        
        return remove;
    }
    
}
