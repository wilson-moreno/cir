/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.LocationMap;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.util.UtilityDownload;

/**
 *
 * @author Wilson
 */
public class DefaultMapDownloadWorker extends SwingWorker<ImageIcon, String> {
    public final UtilityDownload utilDownload;
    public final EntityIO<LocationMap> mapIO;
    public final JLabel label;
    
    public DefaultMapDownloadWorker(JLabel label,
                                    UtilityDownload utilDownload,
                                    EntityIO<LocationMap> mapIO,
                                    DefaultTaskListener listener){
        this.label = label;
        this.utilDownload = utilDownload;
        this.mapIO = mapIO;
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected ImageIcon doInBackground() throws Exception {
        mapIO.write();
        String url = utilDownload.createUrl(mapIO.getTarget());
        label.setIcon(utilDownload.downloadMap(url));
        return null;
    }
    
    
    
}
