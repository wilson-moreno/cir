/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.validator;

import java.awt.Window;
import java.util.List;
import org.jw.service.action.AbstractActionValidator;
import org.jw.service.print.PrintParameter;

/**
 *
 * @author Wilson
 */
public class ReportPrintActionValidator extends AbstractActionValidator{
    private final List<PrintParameter> paramList;
    private final javax.swing.JTable table;
    
    public ReportPrintActionValidator(Window parent, List<PrintParameter> paramList, javax.swing.JTable table) {
        super(parent);
        this.paramList = paramList;
        this.table = table;
    }

    @Override
    protected void validate() {     
        if(table.isEditing())table.getCellEditor().stopCellEditing();
        java.util.Collections.sort(paramList);
        for(PrintParameter param : paramList){            
            if(param.isRequired() && (param.getValue() == null || param.getValue().equals(""))){
                AbstractActionValidator.ValidationResult result;
                result = new AbstractActionValidator.ValidationResult();
                result.setValidationMessage(param.getLabel().concat(" is required."));
                result.setValidationSuccess(false);
                addValidationResult(result);
            }    
        }        
    }    
}
