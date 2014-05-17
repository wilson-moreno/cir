/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class RecordNumberPreDependency<T> implements DependencyCommand{
    private final Window parent;
    private final JComboBox sgComboBox;
    private final DataAccessObject<ServiceGroup> dao;
    private String recordNumber;
    
    public RecordNumberPreDependency(Window parent, DataAccessObject<ServiceGroup> dao, JComboBox sgComboBox){
        this.parent = parent;
        this.sgComboBox = sgComboBox;
        this.dao = dao;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        if(sgComboBox.getModel().getSize() == 0){
            JOptionPane.showMessageDialog(parent,"Please add Service Groups.", "Service Groups", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else{            
            return true;
        }    
    }

    /**
     *
     * @return
     */
    @Override
    public T get() {
        return (T) recordNumber;
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
