/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.awt.Window;
import javax.swing.SwingWorker;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityFileChooser;

/**
 *
 * @author Wilson
 */
public class DefaultFileChooserOpenWorker extends SwingWorker<Object, String>{
    private final UtilityFileChooser utilFC;
    
    public DefaultFileChooserOpenWorker(Window parent, DefaultTaskListener listener){
        this.utilFC = UtilityFileChooser.create(parent);
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected Object doInBackground() throws Exception {        
        utilFC.showOpenDialog();
        return utilFC.getSelectedFile();
    }
    
}
