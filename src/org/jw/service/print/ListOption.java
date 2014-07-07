/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.print;

/**
 *
 * @author Wilson
 */
public class ListOption<T> implements Comparable<ListOption>{
    private T value;
    private String name;
    
    public ListOption(){
        value = null;
        name = "";
    }
    
    public void setValue(T value){
        this.value = value;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public T getValue(){
        return value;
    }
    
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(ListOption o) {
        return this.getName().compareTo(o.getName());
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
}
