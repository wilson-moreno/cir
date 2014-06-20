/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import java.util.ArrayList;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.entity.Contact;
import org.jw.service.entity.LocationMap;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultRequiredLocationMapValidator extends AbstractActionValidator{
    private final UtilityTable<Contact> utilTable;
    
    public DefaultRequiredLocationMapValidator(Window parent, UtilityTable<Contact> utilTable) {
        super(parent);
        this.utilTable = utilTable;
    }

    @Override
    protected void validate() {
        Contact contact = utilTable.getSelectedItem();
        if(!contact.getLocationMapCollection().isEmpty()){
            LocationMap locationMap = new LocationMap();
            for(LocationMap map : new ArrayList<>(contact.getLocationMapCollection())){
                locationMap = map;
            }
            
            if(locationMap.isMissingRequiredFields()){
                System.out.println("Missing: " + locationMap.isMissingRequiredFields());
                AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
                result.setValidationSuccess(false);
                result.setValidationMessage("Direction map requires complete location map");
                this.addValidationResult(result);
            }
        } else {
            System.out.println("Empty: " + contact.getContactCallCollection().isEmpty());
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage("Direction map requires complete location map");
            this.addValidationResult(result);
        }        
    }
    
}
