/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultOpenWorker;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultOpenAction<T> extends AbstractAction{
    private final JFrame parent;
    private final JDialog dialog;
    private final DefaultTaskListener listener;
    
    public DefaultOpenAction(AbstractButton command, JFrame parent, JDialog dialog, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        this.parent = parent;
        this.dialog = dialog;
        this.listener = listener;
        command.setAction(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.setLocationRelativeTo(parent);
        dialog.setModal(true);
        DefaultOpenWorker worker = new DefaultOpenWorker(dialog, listener);
        worker.execute();
    }
    
}
