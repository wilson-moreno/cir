/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.entity.ObservableEntity;
import org.jw.service.util.UtilityProperties;

/**
 *
 * @author leah
 */
public class DefaultEntitySaveActionValidator extends AbstractActionValidator{
    private final ObservableEntity entity;

    public DefaultEntitySaveActionValidator(Window parent, ObservableEntity entity) {
        super(parent);
        this.entity = entity;
    }
       
    
    @Override
    protected void validate() {
        try{
            if(entity.isMissingRequiredFields()){
                UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.VALIDATION_MESSAGES_PROPERTIES);
                AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
                String message = utilProperties.getProperty(entity.getImplementingClassName().toLowerCase() + ".required.field.message");
                result.setValidationSuccess(false);
                result.setValidationMessage(message);
                this.addValidationResult(result);
            }
        }catch(NullPointerException ex){
            System.out.println("Entity is null");
        }
    }
    
}
