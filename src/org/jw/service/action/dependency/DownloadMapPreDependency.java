/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.worker.DefaultCheckInternetConnectionWorker;

/**
 *
 * @author Wilson
 */
public class DownloadMapPreDependency implements DependencyCommand{
    private final Window parent;
    private final UtilityDownload utilDownload;
    private final DefaultTaskListener listener;
    private final DefaultCheckInternetConnectionWorker worker;
    
    public DownloadMapPreDependency(Window parent, UtilityDownload utilDownload, DefaultTaskListener listener){
        this.parent = parent;
        this.utilDownload = utilDownload;
        this.listener = listener; 
        this.worker = new DefaultCheckInternetConnectionWorker(utilDownload, listener);
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        boolean result = false;
        
        try {
            worker.execute();
            result = worker.get();
            if(Boolean.FALSE == result){
                JOptionPane.showMessageDialog(parent, "Requires internet connection.", "No Internet Connection", JOptionPane.ERROR_MESSAGE);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DownloadMapPreDependency.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
