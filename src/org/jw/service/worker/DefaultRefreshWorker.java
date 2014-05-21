/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.Collection;
import java.util.List;
import javax.swing.SwingWorker;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultRefreshWorker<T> extends SwingWorker<List<T>, String>{
    private final DataAccessObject<T> dao;        
    
    public DefaultRefreshWorker(DataAccessObject<T> dao, DefaultTaskListener listener){
        this.dao = dao;                
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected List<T> doInBackground() throws Exception {        
        return dao.readAll();
    }
    
}
