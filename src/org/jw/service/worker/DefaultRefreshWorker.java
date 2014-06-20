/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.List;
import javax.persistence.Query;
import javax.swing.SwingWorker;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultRefreshWorker<T> extends SwingWorker<List<T>, String>{
    private final Query query;
    
    public DefaultRefreshWorker(Query query, DefaultTaskListener listener){
        this.query = query;
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected List<T> doInBackground() throws Exception {   
        List<T> list = query.getResultList();
        return list;
    }
    
}
