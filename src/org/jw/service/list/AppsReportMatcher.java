/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

import org.jw.service.entity.AppsReport;

/**
 *
 * @author Wilson
 */
public class AppsReportMatcher implements Matcher<AppsReport, AppsReport>{

    @Override
    public boolean isMatch(AppsReport object1, AppsReport object2) {
        return object1.getName().trim().equalsIgnoreCase(object2.getName().trim()) || 
               object1.getCode().trim().equalsIgnoreCase(object2.getCode().trim());
    }

    @Override
    public boolean isExactMatch(AppsReport object1, AppsReport object2) {
        return object1.getName().trim().equals(object2.getName().trim());
    }

    @Override
    public boolean isDuplicate(AppsReport object1, AppsReport object2) {
        if(object1.getId().intValue() == object2.getId().intValue())return false;
        else if(isMatch(object1, object2))return true;
        return false;
    }

    
    
}
