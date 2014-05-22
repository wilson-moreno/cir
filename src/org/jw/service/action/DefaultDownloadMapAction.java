/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.worker.DefaultMapDownloadWorker;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadMapAction extends DependentAbstractAction{
    public final EntityIO<LocationMap> mapIO;
    public final UtilityDownload utilDownload;
    public final DefaultTaskListener listener;
    public final JLabel label;
    
    public DefaultDownloadMapAction(JButton command, 
                                    JLabel label,
                                    EntityIO<LocationMap> mapIO,
                                    UtilityDownload utilDownload, 
                                    DefaultTaskListener listener){
        super(command.getText(), command.getIcon());
        this.label = label;
        this.mapIO = mapIO;
        this.utilDownload = utilDownload;
        this.listener = listener;        
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        DefaultMapDownloadWorker worker = new DefaultMapDownloadWorker(label, utilDownload, mapIO, listener);
        worker.execute();                    
        return true;
    }
}
