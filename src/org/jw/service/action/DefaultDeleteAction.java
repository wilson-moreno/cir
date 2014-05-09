/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultDeleteWorker;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultDeleteAction<T> extends AbstractAction{
    private final List<T> list;
    private final JTable table;
    private final DefaultTaskListener listener;
    private final DataAccessObject<T> dao;
    
    public DefaultDeleteAction(JButton command,DataAccessObject<T> dao, List<T> list, JTable table, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        this.dao = dao;
        this.list = list;
        this.table = table;
        this.listener = listener;
        this.setEnabled(false);
        command.setAction(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        DefaultDeleteWorker<T> worker;
        worker = new DefaultDeleteWorker<>(dao, list, table, listener);
        worker.execute();
    }
    
}
