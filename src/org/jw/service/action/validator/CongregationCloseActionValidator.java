/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;



import java.awt.Window;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.entity.ObservableEntity;

/**
 *
 * @author Wilson
 */
public class CongregationCloseActionValidator extends AbstractActionValidator{
    private final ObservableEntity entity;
    
    public CongregationCloseActionValidator(Window parent, ObservableEntity entity){
        super(parent);
        this.entity = entity;
    }    
    
    @Override
    public void validate() {        
        if(entity != null && entity.getSaveState() != null && entity.getSaveState().contains("*")){
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage("Some information are not yet saved.");
            addValidationResult(result);
        }
    }
    
}
