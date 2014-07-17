/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.inputverifier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.InputVerifier;
import javax.swing.JComponent;

/**
 *
 * @author Wilson
 */
public class DateInputVerifier extends InputVerifier{

    public static DateInputVerifier create() {
        return new DateInputVerifier();
    }
    private final SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
    
    private DateInputVerifier(){}
    
    @Override
    public boolean verify(JComponent input) {
        
        if(input instanceof javax.swing.JTextField){
            javax.swing.JTextField textField = (javax.swing.JTextField)input;
            
            try {                
                format.parse(textField.getText());                
            } catch (ParseException ex) {        
                return false;
            }
        }
        
        return true;
    }    
}
