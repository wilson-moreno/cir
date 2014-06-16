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
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultRequiredFieldsSaveActionValidator<T> extends AbstractActionValidator{
    private final UtilityTable<T> utilTable;    
    private final UtilityProperties utilProperties;
    private final String message;
    
    public DefaultRequiredFieldsSaveActionValidator(Window parent, UtilityTable<T>utilTable, String entityName) {
        super(parent);
        this.utilTable = utilTable;                
        this.utilProperties = UtilityProperties.create(UtilityProperties.VALIDATION_MESSAGES_PROPERTIES);
        this.message = utilProperties.getProperty(entityName.trim().toLowerCase() + ".required.field.message");        
    }

    @Override
    protected void validate() {        
        ObservableEntity entity = (ObservableEntity) utilTable.getSelectedItem(); 
        if(entity != null && entity.isMissingRequiredFields()){
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage(message);
            this.addValidationResult(result);
        }
    }
}
