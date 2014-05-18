/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JButton;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityFileChooser;
import org.jw.service.worker.DefaultFileChooserOpenWorker;

/**
 *
 * @author Wilson
 */
public class DefaultFileChooserOpenAction extends DependentAbstractAction{
    private final Window parent;
    private final DefaultTaskListener listener;
    private final UtilityFileChooser utilFC;
    
    public DefaultFileChooserOpenAction(JButton command, Window parent, DefaultTaskListener listener) {
        super(command.getText(), command.getIcon());
        this.parent = parent;
        this.listener = listener;
        this.utilFC = UtilityFileChooser.create(parent);
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        utilFC.showOpenDialog();
        this.workerResult = utilFC.getSelectedFile();
        return true;
    }
    
}
