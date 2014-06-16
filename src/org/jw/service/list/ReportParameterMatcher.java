/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

import org.jw.service.entity.AppsReportParameter;

/**
 *
 * @author Wilson
 */
public class ReportParameterMatcher implements Matcher<AppsReportParameter, AppsReportParameter>{

    @Override
    public boolean isMatch(AppsReportParameter object1, AppsReportParameter object2) {
        if(object1.getName().trim().equalsIgnoreCase(object2.getName().trim()) ||
           object1.getSequence().intValue() == object2.getSequence().intValue())
           return true; 
        return false;
    }

    @Override
    public boolean isExactMatch(AppsReportParameter object1, AppsReportParameter object2) {        
        if(object1.getName().trim().equalsIgnoreCase(object2.getName().trim()) &&
           object1.getSequence().intValue() == object2.getSequence().intValue())
           return true; 
        return false;    
    }

    @Override
    public boolean isDuplicate(AppsReportParameter object1, AppsReportParameter object2) {
        if(object1.getId().intValue() == object2.getId().intValue())return false;
        else if(isMatch(object1, object2))return true;
        return false;
    }
    
}
