/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.combobox;

import java.util.List;
import javax.swing.JComboBox;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.observablecollections.ObservableListListener;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultComboBoxModelListListener<T> implements ObservableListListener{

    public static <T> DefaultComboBoxModelListListener<T> create(JComboBox comboBox) {
        return new DefaultComboBoxModelListListener<>(comboBox);
    }
    private final JComboBox comboBox;
    
    
    private DefaultComboBoxModelListListener(JComboBox comboBox){
        this.comboBox = comboBox;    
    }

    @Override
    public void listElementsAdded(ObservableList list, int index, int length) {                
        List<T> subList = list.subList(index, index + length);
        for(Object item : subList)
            comboBox.addItem(item);        
    }

    @Override
    public void listElementsRemoved(ObservableList list, int index, List oldElements) {        
        for(Object item : oldElements)
            comboBox.removeItem(item);
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
