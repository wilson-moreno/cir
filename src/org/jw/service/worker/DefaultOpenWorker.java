/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import javax.swing.JDialog;
import javax.swing.SwingWorker;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultOpenWorker extends SwingWorker<String, String>{
    private final JDialog dialog;
    
    public DefaultOpenWorker(JDialog dialog, DefaultTaskListener listener){
        this.dialog = dialog;
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected String doInBackground() throws Exception {        
        dialog.setVisible(true);
        return "";
    }
    
}
