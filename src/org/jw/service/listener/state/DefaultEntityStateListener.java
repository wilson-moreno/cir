/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.state;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import org.jw.service.entity.ObservableEntity;

/**
 *
 * @author Wilson
 */
public class DefaultEntityStateListener implements PropertyChangeListener{

    public static DefaultEntityStateListener create(AbstractAction saveAction) {
        return new DefaultEntityStateListener(saveAction);
    }
    private final AbstractAction saveAction;
    
    private DefaultEntityStateListener(AbstractAction saveAction){
        this.saveAction = saveAction;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        ObservableEntity entity = (ObservableEntity) pce.getSource();
        
        switch(pce.getPropertyName()){
            case "saveState" : switch(pce.getNewValue().toString()){
                                 case "*" : saveAction.setEnabled(true);break;
                                 case ""  : saveAction.setEnabled(false);break;
                              }break;
           default : entity.setSaveState("*");                      
                     break;  
        }
    }
    
}
