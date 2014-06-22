/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

import org.jw.service.entity.Contact;

/**
 *
 * @author Wilson
 */
public class ContactMatcher implements Matcher<Contact, Contact>{

    @Override
    public boolean isMatch(Contact object1, Contact object2) {
        if(object2.getLastName().trim().equals("") && object2.getFirstName().trim().equals(""))return false;
        return (object1.getLastName().contains(object2.getLastName())) && (object1.getFirstName().contains(object2.getFirstName()));        
    }

    @Override
    public boolean isExactMatch(Contact object1, Contact object2) {        
       if(object2.getLastName().trim().equals("") && object2.getFirstName().trim().equals("") && object2.getCity().trim().equals(""))return false;
       return (object1.getLastName().equalsIgnoreCase(object2.getLastName())) &&
              (object1.getFirstName().equalsIgnoreCase(object2.getFirstName())) &&
              (object1.getCity().equalsIgnoreCase(object2.getCity()));
    }

    @Override
    public boolean isDuplicate(Contact object1, Contact object2) {        
        if(object1.getId().intValue() == object2.getId().intValue())return false;        
        else if(isExactMatch(object1, object2))return true;
        return false;
    }
    
}
