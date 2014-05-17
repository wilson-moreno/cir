/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.enumeration;

/**
 *
 * @author Wilson
 */
public enum SexEnumeration {
    
    Male("Male","Male contact"),
    Female("Female","Female contact");
    
    private final String sex;
    private final String description;        
    
    SexEnumeration(String sex, String description){
        this.sex = sex;
        this.description = description;
    }
    
    public String getSex(){ return sex; }
    public String getDescription(){ return description; }
    @Override
    public String toString(){ return sex; }
}
