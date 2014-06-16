/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultDeletePreDependency<T> implements DependencyCommand {
    private final Window parent;
    private final UtilityTable utilTable;
    private final DataAccessObject<T> dao;
    
    public DefaultDeletePreDependency(Window parent, UtilityTable utilTable, DataAccessObject<T> dao){
        this.parent = parent;
        this.utilTable = utilTable;
        this.dao = dao;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        boolean result = true;
        switch(JOptionPane.showConfirmDialog(parent, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION)){
            case JOptionPane.YES_OPTION : result = !hasDependentEntities(); break;
            case JOptionPane.NO_OPTION : result = false; break;
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
 
    private boolean hasDependentEntities(){        
        try{
            T entity = (T)utilTable.getSelectedItem();
            dao.refresh(entity);
            ObservableEntity observable = (ObservableEntity) entity;
            if(observable.hasDependentEntities()){
                JOptionPane.showMessageDialog(parent, "This record cannot be deleted because it has dependent records.", "Parent Record", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {                
                return false;
            }            
        }catch(ArrayIndexOutOfBoundsException ex){
            return true;
        }
    }
    
}
