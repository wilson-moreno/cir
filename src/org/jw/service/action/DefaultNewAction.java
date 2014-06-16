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
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.listener.state.DefaultEntityStateListener;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityTable;
import org.jw.service.worker.DefaultNewWorker;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultNewAction<T> extends DependentAbstractAction{
    private final DataAccessObject<T> dao;
    private final List<T> list;
    private final JTable table;
    private final DefaultTaskListener taskListener;
    private final DefaultEntityStateListener stateListener;
    private final UtilityTable utilTable;
    
    public DefaultNewAction(JButton command, DataAccessObject<T> dao, List<T> list, JTable table, DefaultTaskListener taskListener, DefaultEntityStateListener stateListener){
        super(command.getText(), command.getIcon());
        this.dao = dao;        
        this.list = list;
        this.table = table;
        this.utilTable = UtilityTable.create(table, list);
        this.taskListener = taskListener;        
        this.stateListener = stateListener;
        command.setAction(this);    
    }
    
    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        try {
            DefaultNewWorker<T> worker = new DefaultNewWorker(dao, list, table, taskListener, stateListener);
            worker.execute();
            workerResult = worker.get();                          
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultNewAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
