/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.entity.Congregation;

/**
 *
 * @author Wilson
 */
public class CongregationSaveActionValidator extends AbstractActionValidator{
    private Congregation congregation;
    
    public CongregationSaveActionValidator(Window parent, Congregation congregation) {
        super(parent);
        this.congregation = congregation;
    }

    @Override
    protected void validate() {
        if(congregation != null){
            if(isMissingRequiredFields(congregation)){
                AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
                result.setValidationSuccess(false);
                result.setValidationMessage("Congregation name, address & city are required.");
                this.addValidationResult(result);
            }
        }
    }
    
    private boolean isMissingRequiredFields(Congregation congregation){
        return congregation.getName().trim().equals("") ||
               congregation.getAddress1().trim().equals("") ||
               congregation.getCity().trim().equals("");
    }
    
}
