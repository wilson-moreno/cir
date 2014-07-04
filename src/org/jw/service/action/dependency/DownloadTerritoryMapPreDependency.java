/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.LocationMap;

/**
 *
 * @author Wilson
 */
public class DownloadTerritoryMapPreDependency implements DependencyCommand{
    private final List<LocationMap> mapsList;
    private final java.awt.Window parent;
    
    public DownloadTerritoryMapPreDependency(java.awt.Window parent, List<LocationMap> mapsList){
        this.mapsList = mapsList;
        this.parent = parent;
    }

    @Override
    public boolean run(ActionEvent ae) {
        boolean result = true;
        boolean unknownLocations = this.isSomeLocationsUnknown(mapsList);
        
        if(unknownLocations){
            int answer = JOptionPane.showConfirmDialog(parent, "Some locations are unknown, do you wan to proceed?", "Unknown Locations", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(answer == JOptionPane.NO_OPTION)result = false;
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
    
    private boolean isSomeLocationsUnknown(List<LocationMap> list){
        boolean result = false;
        
        for(LocationMap map : list){
            if(map.getLatitude() == null || map.getLongitude() == null)return true;
        }
        
        return result;
    }
}
