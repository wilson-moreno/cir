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
public class DefaultRefreshWorker<T> extends SwingWorker<String, String>{
    private final DataAccessObject<T> dao;    
    private final List<T> list;
    
    public DefaultRefreshWorker(DataAccessObject<T> dao, List<T> list, DefaultTaskListener listener){
        this.dao = dao;        
        this.list = list;
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected String doInBackground() throws Exception {
        list.clear();
        list.addAll((Collection<? extends T>) (T) dao.readAll());
        return "";
    }
    
}
