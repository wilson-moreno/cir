/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.list;

import java.util.List;
import javax.swing.AbstractAction;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;

/**
 *
 * @author Wilson
 */
public class ContactObservableListListener implements ObservableListListener{

    public static ContactObservableListListener create(AbstractAction callsAction, AbstractAction printAction) {
        return new ContactObservableListListener(callsAction, printAction);
    }
    private final AbstractAction callsAction;
    private final AbstractAction printAction;
    
    private ContactObservableListListener(AbstractAction callsAction, AbstractAction printAction){
        this.callsAction = callsAction;
        this.printAction = printAction;
    }
    
    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {
        callsAction.setEnabled(true);
        printAction.setEnabled(true);
    }

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {
        printAction.setEnabled(false);
        callsAction.setEnabled(false);
    }

    @Override
    public void listElementReplaced(ObservableList list, int index, Object oldElement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listElementPropertyChanged(ObservableList list, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
