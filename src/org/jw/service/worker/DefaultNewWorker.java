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
import org.jw.service.listener.state.DefaultEntityStateListener;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultNewWorker<T> extends SwingWorker<T, String>{
    private final DataAccessObject<T> dao;
    private final DefaultEntityStateListener stateListener;
    private final UtilityTable utilTable;
    private final List<T> list;
    
    public DefaultNewWorker(DataAccessObject dao, List<T> list, JTable table, DefaultTaskListener taskListener, DefaultEntityStateListener stateListener){
        this.dao = dao;
        this.list = list;
        this.addPropertyChangeListener(taskListener);
        this.stateListener = stateListener;
        this.utilTable = UtilityTable.create(table, list);
    }

    @Override
    protected T doInBackground() throws Exception {
        T entity = dao.create();        
        ((ObservableEntity)entity).addPropertyChangeListener(stateListener);
        list.add(0, entity);
        utilTable.selectRow(0);     
        return entity;                
    }
    
}
