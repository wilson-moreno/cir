/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.jw.service.entity.DirectionMap;
import org.jw.service.entity.EntityIO;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityImageIcon;

/**
 *
 * @author Wilson
 */
public class DefaultDirectionMapDownloadWorker extends SwingWorker<byte[], String>{
    public final UtilityDownload utilDownload;
    public final EntityIO<DirectionMap> mapIO;
    public final JLabel label;
    public final UtilityImageIcon utilImage;
    public String direction;
    
    public DefaultDirectionMapDownloadWorker(JLabel label,
                                            UtilityDownload utilDownload,
                                            EntityIO<DirectionMap> mapIO,
                                            DefaultTaskListener listener){
        this.label = label;
        this.utilDownload = utilDownload;
        this.utilImage = UtilityImageIcon.create();
        this.mapIO = mapIO;
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected byte[] doInBackground() throws Exception {        
        byte[] directionMapImage;
        
        mapIO.write();
        direction = utilDownload.getDirection(mapIO.getTarget());
        mapIO.getSource().setDirection(direction);
        mapIO.write();
        directionMapImage = utilDownload.getDirectionMapAsBytes(mapIO.getTarget());        
        
        return directionMapImage;
    }
    
    @Override
    public void done(){
        try {
            mapIO.getSource().setImage(this.get());
            mapIO.getSource().setDirection(direction);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultLocationMapDownloadWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
