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
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultRefreshAction<T> extends AbstractAction{
    private final List<T> list;
    private final JTable table;
    private final DefaultTaskListener listener;
    
    public DefaultRefreshAction(JButton command, List<T> list, JTable table, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        command.setAction(this);
        this.list = list;
        this.table = table;
        this.listener = listener;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
