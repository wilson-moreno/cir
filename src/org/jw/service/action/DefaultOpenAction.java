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
import org.jw.service.util.UtilityDialog;
import org.jw.service.worker.DefaultOpenWorker;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultOpenAction<T> extends DependentAbstractAction{    
    private final String dialogName;
    private final UtilityDialog utilDialog;
    private final DefaultTaskListener listener;
    
    public DefaultOpenAction(AbstractButton command, String dialogName, UtilityDialog utilDialog, DefaultTaskListener listener){
        super(command.getText(), command.getIcon());        
        this.dialogName = dialogName;
        this.listener = listener;
        this.utilDialog = utilDialog;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        JDialog dialog = utilDialog.createDialog(dialogName);        
        DefaultOpenWorker worker = new DefaultOpenWorker(dialog, listener);
        worker.execute();
        return true;
    }
    
}
