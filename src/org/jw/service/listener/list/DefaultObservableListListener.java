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
public class DefaultObservableListListener implements ObservableListListener{

    public static DefaultObservableListListener create(AbstractAction deleteAction, AbstractAction saveAction) {
        return new DefaultObservableListListener(deleteAction, saveAction);
    }
    private final AbstractAction deleteAction;
    private final AbstractAction saveAction;
    
    private DefaultObservableListListener(AbstractAction deleteAction, AbstractAction saveAction){
        this.deleteAction = deleteAction;
        this.saveAction = saveAction;
    }
    
    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {
        saveAction.setEnabled(false);
    }

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {
        saveAction.setEnabled(false);
        deleteAction.setEnabled(false);
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
