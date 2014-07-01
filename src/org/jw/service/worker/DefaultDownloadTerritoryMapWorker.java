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
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.Territory;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadTerritoryMapWorker extends SwingWorker<byte[], String>{
    private final EntityIO<Territory> entityIO;    
    
    public DefaultDownloadTerritoryMapWorker(EntityIO<Territory> entityIO,
                                             DefaultTaskListener taskListener){
        this.entityIO = entityIO;
        addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected byte[] doInBackground() throws Exception {
        UtilityDownload utilDownload;
        utilDownload = UtilityDownload.create();
        byte[] territoryMap;
        
        entityIO.write();       
        territoryMap = utilDownload.downloadProximityMapAsBytes(entityIO.getTarget());
        
        return territoryMap;
    }
    
    @Override
    public void done(){        
        try {            
            entityIO.getSource().setMapImage(this.get());             
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultDownloadTerritoryMapWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
