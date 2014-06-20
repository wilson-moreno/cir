/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.JButton;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultRefreshWorker;

/**
 *
 * @author Wilson
 */
public class DefaultRefreshAction<T> extends DependentAbstractAction{    
    private final Query query;
    private final DefaultTaskListener listener;
    private final List<T> list;
    
    public DefaultRefreshAction(JButton command, Query query, List<T> list, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());        
        this.list = list;        
        this.query = query;
        this.listener = listener;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        try {
            DefaultRefreshWorker<T> worker = new DefaultRefreshWorker<>(query, listener);
            list.clear();
            worker.execute();
            list.addAll(worker.get());
            return true;
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultRefreshAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
