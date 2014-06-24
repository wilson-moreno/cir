/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import org.jw.service.beans.ByteArrayBean;
import org.jw.service.entity.Territory;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.worker.DefaultProximityMapDownloadWorker;

/**
 *
 * @author Wilson
 */
public class DefaultDownloadProximityMapAction extends DependentAbstractAction{
    private final JComboBox territoryComboBox;
    private final ByteArrayBean byteArrayBean;
    private final DefaultTaskListener listener;
    
    public DefaultDownloadProximityMapAction(JButton command, JComboBox territoryComboBox, ByteArrayBean byteArrayBean, DefaultTaskListener listener) {
        super(command.getText(), command.getIcon());
        this.territoryComboBox = territoryComboBox;
        this.byteArrayBean = byteArrayBean;
        this.listener = listener;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {
        try{
            Territory territory = (Territory) territoryComboBox.getSelectedItem();
            DefaultProximityMapDownloadWorker worker = new DefaultProximityMapDownloadWorker(territory, byteArrayBean, listener);
            worker.execute();
            return true;
        } catch (NullPointerException ex){
            return false;
        }
    }
    
}
