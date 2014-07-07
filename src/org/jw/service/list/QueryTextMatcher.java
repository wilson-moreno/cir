/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

import org.jw.service.entity.QueryText;


/**
 *
 * @author Wilson
 */
public class QueryTextMatcher implements Matcher<QueryText, QueryText>{

    @Override
    public boolean isMatch(QueryText object1, QueryText object2) {
        return object1.getName().trim().equalsIgnoreCase(object2.getName().trim());
    }

    @Override
    public boolean isExactMatch(QueryText object1, QueryText object2) {
        return object1.getName().trim().equals(object2.getName().trim());
    }

    @Override
    public boolean isDuplicate(QueryText object1, QueryText object2) {
        if(object1.getId().intValue() == object2.getId().intValue())return false;
        else if(isMatch(object1, object2))return true;
        return false;
    }
}
