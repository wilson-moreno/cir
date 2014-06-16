/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.beansbinding.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author Wilson
 */
public class StringToTimeConverter  extends Converter<String, Date>{

    public StringToTimeConverter(){}
    
    @Override
    public Date convertForward(String value) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
            return formatter.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(StringToTimeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String convertReverse(Date value) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        return formatter.format(value);     
    }
    
}
