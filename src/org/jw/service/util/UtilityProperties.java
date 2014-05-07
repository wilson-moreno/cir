/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson
 */
public class UtilityProperties extends DefaultUtility{
    public static final String MARITAL_STATUS_PROPERTIES = "/org/jw/service/gui/resources/properties/marital_status.properties";
    public static final String SEX_PROPERTIES = "/org/jw/service/gui/resources/properties/sex.properties";
    private final Properties property;
    
    public static UtilityProperties create(String propertyFile) {
        return new UtilityProperties(propertyFile);
    }
    
    private UtilityProperties(String propertyFile){
        this.property = getProperties(propertyFile);
    }
    
    
    private Properties getProperties(String file){
        Properties properties = new Properties();
        try {
            properties.load(getResourceAsStream(file));
        } catch (IOException ex) {
            Logger.getLogger(UtilityProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
    
    public Collection getValues(){
        return this.property.values();
    }
    
    public Enumeration getKeys(){
        return this.property.keys();
    }
}
