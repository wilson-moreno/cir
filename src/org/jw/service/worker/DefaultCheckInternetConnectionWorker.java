/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import javax.swing.SwingWorker;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;

/**
 *
 * @author Wilson
 */
public class DefaultCheckInternetConnectionWorker extends SwingWorker<Boolean, String>{
    private final UtilityDownload utilDownload;
    
    public DefaultCheckInternetConnectionWorker(UtilityDownload utilDownload, DefaultTaskListener listener){
        this.utilDownload = utilDownload;
        this.addPropertyChangeListener(listener);
    }
    
    protected Boolean doInBackground() throws Exception {
        return utilDownload.isAPIReachable();
    }
    
}
