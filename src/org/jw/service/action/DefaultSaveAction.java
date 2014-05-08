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
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultSaveAction<T> extends AbstractAction{
    private final List<T> list;
    private final UtilityTable utilTable;
    private final DefaultTaskListener listener;
    private final DataAccessObject dao;
    
    public DefaultSaveAction(JButton command, DataAccessObject dao, List<T> list, JTable table, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        this.list = list;
        this.utilTable = UtilityTable.create(table);
        this.listener = listener;
        this.dao = dao;
        command.setAction(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        int modelIndex = utilTable.getSelectedModelIndex();
        Object save = dao.save(list.get(modelIndex));
    }
    
}
