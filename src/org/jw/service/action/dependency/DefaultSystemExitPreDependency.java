/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson
 */
public class DefaultSystemExitPreDependency implements DependencyCommand{
    private final EntityManager em;
    private final Window parent;
    
    public DefaultSystemExitPreDependency(Window parent, EntityManager em){
        this.parent = parent;
        this.em = em;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        JOptionPane.showMessageDialog(parent, "Exiting system...", "System Exit", JOptionPane.INFORMATION_MESSAGE);
        em.close();
        return true;
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
