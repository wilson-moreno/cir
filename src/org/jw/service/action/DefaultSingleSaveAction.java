/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.EntityIO;
import org.jw.service.entity.ObservableEntity;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DefaultSingleSaveAction<T> extends DependentAbstractAction{
    private final DataAccessObject<T> dao;
    private final EntityIO<T> entityIO;    
    
    public DefaultSingleSaveAction(JButton command, DataAccessObject<T> dao, EntityIO entityIO) {
        super(command.getText(), command.getIcon());
        this.dao = dao;
        this.entityIO = entityIO;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {        
        entityIO.write();                
        T save = dao.persist(entityIO.getTarget());        
        ((ObservableEntity)entityIO.getSource()).setSaveState("");
        ((ObservableEntity)entityIO.getTarget()).setSaveState("");
        return true;
    }    
}
