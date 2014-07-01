/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import org.jw.service.entity.EntityIO;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultDownloadTerritoryMapWorker;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadTerritoryMapAction extends DependentAbstractAction{
    private final EntityIO territoryIO;
    private final DefaultTaskListener taskListener;
    
    public DefaultDownloadTerritoryMapAction(javax.swing.JButton command, EntityIO territoryIO, DefaultTaskListener taskListener) {
        super(command.getText(), command.getIcon());
        this.territoryIO = territoryIO;
        this.taskListener = taskListener;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        DefaultDownloadTerritoryMapWorker worker;
        worker = new DefaultDownloadTerritoryMapWorker(territoryIO, taskListener);
        worker.execute();        
        return true;
    }
    
}
