/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list;

/**
 *
 * @author Wilson
 * @param <T>
 */
public interface Matcher<T, K> {
    public boolean isMatch(T object1, K object2);
    public boolean isExactMatch(T object1, K object2);
    public boolean isDuplicate(T object1, K object2);
    
}
