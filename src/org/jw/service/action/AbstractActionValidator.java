/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson
 */
public abstract class AbstractActionValidator {
    private Window parent;
    private List<ValidationResult> validationResults = new ArrayList<>();
    
    public AbstractActionValidator(Window parent){
        this.parent = parent;
    }
    
    protected abstract void validate();
    
    public boolean runValidate(){
        clearResults();
        validate();        
        showResultMessages();
        return isValidationSuccess();
    }
    
    public void addValidationResult(ValidationResult result){
        validationResults.add(result);
    }
    
    protected void clearResults(){
        validationResults.clear();
    }
    
    protected boolean isValidationSuccess(){
        boolean success = true;
        
        for(ValidationResult  result : validationResults)
            success = success && result.getValidationSuccess();
            
        return success;
    }
    
    protected void showResultMessages(){
        String messages = "";
        
        for(ValidationResult  result : validationResults){            
            messages = result.getValidationMessage();
        }   
            
        if(!validationResults.isEmpty())JOptionPane.showMessageDialog(parent, messages);
    }
    
    
    
    public class ValidationResult{
        private boolean validationSuccess;
        private String validationMessage;
        
        public ValidationResult(){
            validationSuccess = true;
            validationMessage = "";
        }
        
        public void setValidationSuccess(boolean success){
            validationSuccess = success;
        }
        
        public void setValidationMessage(String message){
            validationMessage = message;
        }
        
        public boolean getValidationSuccess(){
            return validationSuccess;
        }
        
        public String getValidationMessage(){
            return validationMessage;
        }
    }
}
