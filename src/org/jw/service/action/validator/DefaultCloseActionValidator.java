/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultCloseActionValidator extends AbstractActionValidator{
    private final UtilityTable utilTable;
    
    public DefaultCloseActionValidator(Window parent, UtilityTable utilTable) {
        super(parent);
        this.utilTable = utilTable;
    }

    @Override
    protected void validate() {
        if(utilTable.isSomeRecordUnsaved()){
            AbstractActionValidator.ValidationResult result = new AbstractActionValidator.ValidationResult();
            result.setValidationSuccess(false);
            result.setValidationMessage("Some records are not yet saved.");
            this.addValidationResult(result);
        }
    }
    
}
