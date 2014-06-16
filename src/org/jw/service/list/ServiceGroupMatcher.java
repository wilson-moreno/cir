/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class ServiceGroupMatcher implements Matcher<ServiceGroup, ServiceGroup>{

    @Override
    public boolean isMatch(ServiceGroup object1, ServiceGroup object2) {
        if(object1.getName().trim().equalsIgnoreCase(object2.getName().trim()) ||
           object1.getPrefix().trim().equalsIgnoreCase(object2.getPrefix().trim()))
           return true; 
        return false;
    }

    @Override
    public boolean isExactMatch(ServiceGroup object1, ServiceGroup object2) {        
        if(object1.getName().trim().equalsIgnoreCase(object2.getName().trim()) &&
           object1.getPrefix().trim().equalsIgnoreCase(object2.getPrefix().trim()))
           return true; 
        return false;    
    }

    @Override
    public boolean isDuplicate(ServiceGroup object1, ServiceGroup object2) {
        if(object1.getId().intValue() == object2.getId().intValue())return false;
        else if(isMatch(object1, object2))return true;
        return false;
    }
    
}
