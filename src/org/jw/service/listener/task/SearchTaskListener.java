/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;

/**
 *
 * @author Wilson
 */
public class SearchTaskListener implements PropertyChangeListener{
    private final JButton startSearchCommand;
    private final JButton stopSearchCommand;
    
    
    public SearchTaskListener(JButton startSearchCommand,
                              JButton stopSearchCommand){
        this.startSearchCommand = startSearchCommand;
        this.stopSearchCommand = stopSearchCommand;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setPropertyChangeAction(evt);
    }
    
    protected void startTask(){
        this.startSearchCommand.setVisible(false);
        this.stopSearchCommand.setVisible(true);
    }
    
    protected void doneTask(){
        this.startSearchCommand.setVisible(true);
        this.stopSearchCommand.setVisible(false);
    }
    
    
    protected void setPropertyChangeAction(PropertyChangeEvent evt){
        if("state".equals(evt.getPropertyName().trim())){
            if("STARTED".equals(evt.getNewValue().toString())){
                startTask();
            }else if("DONE".equals(evt.getNewValue().toString())){
                doneTask();
            }
        }
    }
}
