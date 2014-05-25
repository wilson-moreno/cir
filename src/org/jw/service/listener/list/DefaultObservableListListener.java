/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.list;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;

/**
 *
 * @author Wilson
 */
public class DefaultObservableListListener implements ObservableListListener{
    private final List<AbstractAction> addedEnable = new ArrayList<>();
    private final List<AbstractAction> addedDisable = new ArrayList<>();
    private final List<AbstractAction> removedEnable = new ArrayList<>();
    private final List<AbstractAction> removedDisable = new ArrayList<>();
    
    public static DefaultObservableListListener create() {
        return new DefaultObservableListListener();
    }    
    
    private DefaultObservableListListener(){}
    
    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {
        enableAdded();
        disableAdded();
        //saveAction.setEnabled(false);
    }

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {
        enableRemoved();
        disableRemoved();        
    }

    @Override
    public void listElementReplaced(ObservableList list, int index, Object oldElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listElementPropertyChanged(ObservableList list, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addEnableAdded(AbstractAction action){
        addedEnable.add(action);        
    }
    
    public void addDisableAdded(AbstractAction action){
        addedDisable.add(action);        
    }
    
    public void addEnableRemoved(AbstractAction action){
        removedEnable.add(action);        
    }
    
    public void addDisableRemoved(AbstractAction action){
        removedDisable.add(action);        
    }
 
    private void enableAdded(){
        for(AbstractAction action : addedEnable)
            action.setEnabled(true);
    }
    
    private void disableAdded(){
        for(AbstractAction action : addedDisable)
            action.setEnabled(false);
    }
    
    private void enableRemoved(){
        for(AbstractAction action : removedEnable)
            action.setEnabled(true);
    }
    
    private void disableRemoved(){
        for(AbstractAction action : removedDisable)
            action.setEnabled(false);
    }
    
}
