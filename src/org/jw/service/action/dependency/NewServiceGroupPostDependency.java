/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.event.ActionEvent;
import javax.persistence.EntityManager;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Congregation;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.util.UtilityTree;

/**
 *
 * @author Wilson
 */
public class NewServiceGroupPostDependency implements DependencyCommand{
    private final DataAccessObject<Congregation> congregationDAO;
    private final DataAccessObject<ServiceGroup> serviceGroupDAO;
    private final UtilityTree utilTree;
    
    public NewServiceGroupPostDependency(EntityManager em, UtilityTree utilTree){
        this.congregationDAO = DataAccessObject.create(em, Congregation.class);
        this.serviceGroupDAO = DataAccessObject.create(em, ServiceGroup.class);
        this.utilTree = utilTree;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        if(workerResult != null && ServiceGroup.class.isInstance(workerResult)){
            ServiceGroup serviceGroup = (ServiceGroup) workerResult;
            Congregation congregation = congregationDAO.read(new Integer(1));
            if(congregation != null){                
                serviceGroup.silentSetProperty("congregationId", congregation);                
                serviceGroupDAO.persist(serviceGroup);
                utilTree.addNode(serviceGroup);
            }
        }
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
