/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.util.UtilityDownload;
import org.jw.service.util.UtilityProperties;

/**
 *
 * @author leah
 */
public class DefaultInternetConnectivityValidator extends AbstractActionValidator{
    private final UtilityDownload utilDownload;
    
    public DefaultInternetConnectivityValidator(Window parent, UtilityDownload utilDownload) {
        super(parent);
        this.utilDownload = utilDownload;
    }

    @Override
    protected void validate() {
        if(!utilDownload.isAPIReachable()){            
            UtilityProperties utilProperties = UtilityProperties.create(UtilityProperties.VALIDATION_MESSAGES_PROPERTIES);
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            String message = utilProperties.getProperty("internet.connectivity.required.message");
            result.setValidationSuccess(false);
            result.setValidationMessage(message);
            this.addValidationResult(result);
        }        
    }
    
}
