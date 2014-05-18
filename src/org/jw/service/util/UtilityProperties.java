/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson
 */
public class UtilityProperties extends UtilityDefault{
    public static final String PROPERTIES_DIRECTORY = "/org/jw/service/gui/resources/properties/";
    public static final String MARITAL_STATUS_PROPERTIES = "marital_status.properties";
    public static final String SEX_PROPERTIES = "sex.properties";
    public static final String CONTROL_TYPES_PROPERTIES = "control_types.properties";
    public static final String TASK_MESSAGE_PROPERTIES = "task_message.properties";
    private final Properties property;
    private final String propertyFile;
    
    public static UtilityProperties create(String propertyFile) {
        return new UtilityProperties(PROPERTIES_DIRECTORY + propertyFile);
    }
    
    private UtilityProperties(String propertyFile){
        this.propertyFile = propertyFile;
        this.property = getProperties(propertyFile);
    }
    
    
    private InputStream getPropertiesAsStream(){
        return getResourceAsStream(propertyFile);
    }
    
    private Properties getProperties(String file){
        Properties properties = new Properties();
        
        try (InputStream inputStream = getResourceAsStream(file)) {
             properties.load(inputStream);
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
    
    public String getProperty(String key){
        return this.property.getProperty(key);
    }
}
