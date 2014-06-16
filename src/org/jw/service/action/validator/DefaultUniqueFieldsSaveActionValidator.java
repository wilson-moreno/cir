/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import java.util.List;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.list.Matcher;
import org.jw.service.util.UtilityCollections;
import org.jw.service.util.UtilityProperties;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultUniqueFieldsSaveActionValidator<T> extends AbstractActionValidator{    
    private final List<T> list;
    private final Matcher<T, T> matcher;
    private final UtilityTable<T> utilTable;    
    private final UtilityProperties utilProperties;
    private final String message;
    
    public DefaultUniqueFieldsSaveActionValidator(Window parent, List<T> list, UtilityTable<T> utilTable, Matcher<T, T> matcher, String entityName) {
        super(parent);        
        this.list = list;
        this.matcher = matcher;
        this.utilTable = utilTable;        
        this.utilProperties = UtilityProperties.create(UtilityProperties.VALIDATION_MESSAGES_PROPERTIES);
        this.message = utilProperties.getProperty(entityName.toLowerCase().trim() + ".unique.field.message") == null ? "":utilProperties.getProperty(entityName.toLowerCase().trim() + ".unique.field.message");
    }

    @Override
    protected void validate() {        
        T key = utilTable.getSelectedItem();
        UtilityCollections<T> utilCollections = UtilityCollections.create();
        boolean duplicate = utilCollections.isDuplicate(list, key, matcher);        
        if(duplicate){
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage(message);
            this.addValidationResult(result);
        }
    }
    
}
