/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.jw.service.beans.ByteArrayBean;
import org.jw.service.entity.Territory;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;



/**
 *
 * @author Wilson
 */
public class DefaultProximityMapDownloadWorker extends SwingWorker<byte[], String>{
    private final Territory territory;
    private final ByteArrayBean byteArrayBean;
    private final UtilityDownload utilDownload;
    
    public DefaultProximityMapDownloadWorker(Territory territory, ByteArrayBean byteArrayBean, DefaultTaskListener listener){
        this.territory = territory;
        this.byteArrayBean = byteArrayBean;
        this.utilDownload = UtilityDownload.create();
        this.addPropertyChangeListener(listener);
    }

    @Override
    protected byte[] doInBackground() throws Exception {        
        byte[] directionMapImage = null;
        
        directionMapImage = utilDownload.downloadProximityMapAsBytes(territory);
        
        return directionMapImage;
    }
    
    
    @Override
    protected void done(){
        try {
            byteArrayBean.setByteArray(get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultProximityMapDownloadWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
