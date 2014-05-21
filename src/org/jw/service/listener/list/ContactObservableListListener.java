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
public class ContactObservableListListener implements ObservableListListener{
    private final List<AbstractAction> actions = new ArrayList<>();
        
    
    public static ContactObservableListListener create() {
        return new ContactObservableListListener();
    }
    
    
    private ContactObservableListListener(){}
    
    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {        
        for(AbstractAction action : actions)
            action.setEnabled(false);
    }       

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {
        for(AbstractAction action : actions)
            action.setEnabled(false);
    }

    @Override
    public void listElementReplaced(ObservableList list, int index, Object oldElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listElementPropertyChanged(ObservableList list, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addAction(AbstractAction action){
        actions.add(action);
    }
    
    public void removeAction(AbstractAction action){
        actions.remove(action);
    }
}
