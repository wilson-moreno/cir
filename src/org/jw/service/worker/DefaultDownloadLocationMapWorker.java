/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityImageIcon;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadLocationMapWorker extends SwingWorker<byte[], String> {
    public final UtilityDownload utilDownload;
    public final EntityIO<LocationMap> mapIO;
    public final JLabel label;
    public final UtilityImageIcon utilImage;
    
    public DefaultDownloadLocationMapWorker(JLabel label,
                                            UtilityDownload utilDownload,
                                            EntityIO<LocationMap> mapIO,
                                            DefaultTaskListener listener){
        this.label = label;
        this.utilDownload = utilDownload;
        this.utilImage = UtilityImageIcon.create();
        this.mapIO = mapIO;
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected byte[] doInBackground() throws Exception {
        mapIO.write();
        String url = utilDownload.createUrl(mapIO.getTarget());
        byte[] imageBytes = utilDownload.downloadMapAsBytes(url);        
        return imageBytes;
    }
    
    @Override
    protected void done(){
        try {
            mapIO.getSource().setImage(this.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultDownloadLocationMapWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
