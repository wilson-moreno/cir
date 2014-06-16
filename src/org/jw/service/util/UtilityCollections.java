/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.util.ArrayList;
import java.util.List;
import org.jw.service.list.Matcher;


/**
 *
 * @author Wilson
 * @param <T>
 */
public class UtilityCollections<T> {

    public static <T> UtilityCollections<T> create() {
        return new UtilityCollections<T>();
    }
    
    private UtilityCollections(){};

    /**
     *
     * @param list
     * @param object
     * @param matcher
     * @return
     */
    public List findAllContains(List<T> list, T object, Matcher matcher){
        List<T> resultList = new ArrayList<>();
        
        for(T item : list){
            if(matcher.isMatch(item, object)){
                resultList.add(item);
            }
        }
        
        return resultList;
    }
    
    public boolean isDuplicate(List<T> list, T object, Matcher matcher){
        boolean result = false;
        
        for(T item : list){
            if(matcher.isDuplicate(item, object)){
                result = true;
                break;
            }
        }
        
        return result;
    }
    
    
   
}
