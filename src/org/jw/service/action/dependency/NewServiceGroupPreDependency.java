/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Congregation;

/**
 *
 * @author Wilson
 */
public class NewServiceGroupPreDependency implements DependencyCommand{
    private DataAccessObject<Congregation> dao;
    private Window parent;
    
    public NewServiceGroupPreDependency(Window parent, DataAccessObject<Congregation> dao){
        this.dao = dao;
        this.parent = parent;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        Congregation congregation = dao.read(new Integer(1));        
        if(congregation == null){
            JOptionPane.showMessageDialog(parent, "Please set congregation information first.", "Congregation Information", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(isRequiredFieldsNull(congregation)){
            JOptionPane.showMessageDialog(parent, "Please provide congregation name, address and city first.", "Congregation Information", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else if(isRequiredFieldsBlank(congregation)){
            JOptionPane.showMessageDialog(parent, "Please provide congregation name, address and city first.", "Congregation Information", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }else{
            return true;
        }
    }
    
    private boolean isRequiredFieldsBlank(Congregation congregation){
        return congregation.getName().trim().equalsIgnoreCase("") ||
               congregation.getAddress1().trim().equalsIgnoreCase("") ||
               congregation.getCity().trim().equalsIgnoreCase("");
    }
    
    private boolean isRequiredFieldsNull(Congregation congregation){
        return congregation.getName() == null ||
               congregation.getAddress1() == null ||
               congregation.getCity() == null;
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
