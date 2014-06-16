/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.component;

import java.awt.Color;
import javax.swing.JLabel;
import org.jdesktop.beansbinding.AbstractBindingListener;
import org.jdesktop.beansbinding.Binding;

/**
 *
 * @author Wilson
 */
public class LoggingBindingListener extends AbstractBindingListener{
    private JLabel label;
    
    public LoggingBindingListener(JLabel label){
        this.label = label;
    }
    
    @Override
    public void syncFailed(Binding binding, Binding.SyncFailure fail){
        String description;
        if ((fail != null) && (fail.getType() == Binding.SyncFailureType.VALIDATION_FAILED)) {
            description = "[" + binding.getName() + "] " + fail.getValidationResult().getDescription();
        } else {
            description = "Sync failed!";
        }
        String message = description;        
        showMessage(message);
    }    
    
    @Override
    public void synced(Binding binding) {
        String bindName = binding.getName();
        String msg = "[" + bindName + "] Synced";        
        label.setText("Ready...");
    }
    
    private void showMessage(String message){
        label.setText(message);
    }
}
