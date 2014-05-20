/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultRefreshWorker;

/**
 *
 * @author Wilson
 */
public class DefaultRefreshAction<T> extends DependentAbstractAction{
    private final List<T> list;
    private final DataAccessObject<T> dao;
    private final DefaultTaskListener listener;
    
    public DefaultRefreshAction(JButton command, DataAccessObject<T> dao, List<T> list, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        this.list = list;
        this.dao = dao;
        this.listener = listener;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        DefaultRefreshWorker<T> worker = new DefaultRefreshWorker<>(dao, list, listener);
        worker.execute();
        return true;
    }
    
}
