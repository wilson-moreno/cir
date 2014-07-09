/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import java.util.ArrayList;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.LocationMap;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultRequiredLocationMapValidator extends AbstractActionValidator{
    private final UtilityTable<Contact> utilTable;
    private final DataAccessObject<Contact> dao;
    
    public DefaultRequiredLocationMapValidator(Window parent, UtilityTable<Contact> utilTable, DataAccessObject<Contact> dao) {
        super(parent);
        this.utilTable = utilTable;
        this.dao = dao;
    }

    @Override
    protected void validate() {
        Contact contact = utilTable.getSelectedItem();
        dao.refresh(contact);
        if(contact.getLocationMapId() != null){
            LocationMap locationMap;
            
            locationMap = contact.getLocationMapId();
            
            
            if(locationMap.isMissingRequiredFields()){                
                AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
                result.setValidationSuccess(false);
                result.setValidationMessage("Direction map requires complete location map");
                this.addValidationResult(result);
            }
        } else {            
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage("Direction map requires complete location map");
            this.addValidationResult(result);
        }        
    }
    
}
