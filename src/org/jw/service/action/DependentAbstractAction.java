/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 *
 * @author Wilson
 */
public abstract class DependentAbstractAction<T> extends AbstractAction{    
    private final Map<String, DependencyCommand> preActionCommands = new HashMap<>();
    private final Map<String, DependencyCommand> postActionCommands = new HashMap<>();
    protected T workerResult = null;
    private boolean preActionCommandsSuccess = true;
    
    public DependentAbstractAction(String name, Icon icon){
        super(name, icon);
    }
    
    public void addPreActionCommands(String name, DependencyCommand command){
        preActionCommands.put(name, command);
    }
    public void addPostActionCommands(String name, DependencyCommand command){
        postActionCommands.put(name, command);
    }

    public void preActionPerformed(ActionEvent ae){        
        for(DependencyCommand command : this.preActionCommands.values())this.preActionCommandsSuccess = command.run(ae);
        
    }
    
    public void postActionPerformed(ActionEvent ae){
        for(DependencyCommand command : this.postActionCommands.values())command.run(workerResult, ae);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        preActionPerformed(ae);
        if(preActionCommandsSuccess){        
            if(mainActionPerformed(ae)){
                postActionPerformed(ae);
            }
        }
    }
    
    public abstract boolean mainActionPerformed(ActionEvent ae);
}
