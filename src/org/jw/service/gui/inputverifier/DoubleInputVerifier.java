/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.inputverifier;

import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Wilson
 */
public class DoubleInputVerifier extends InputVerifier{
    private DecimalFormat format = new DecimalFormat("0.000000");
    
    public DoubleInputVerifier(){}
    
    @Override
    public boolean verify(JComponent component) {
        boolean result = true;
        
        if(component instanceof JTextField){
            JTextField textField = (JTextField)component;
            try {
                format.parse(textField.getText());
            } catch (ParseException ex) {
                result = false;
                //Logger.getLogger(DoubleInputVerifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
    }
    
}
