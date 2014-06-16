/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.gui.focus;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wilson
 */
public class DefaultFocusTraversalPolicy extends FocusTraversalPolicy{

    public static DefaultFocusTraversalPolicy create() {
        return new DefaultFocusTraversalPolicy();
    }
    private final List<Component> list;
    
    private DefaultFocusTraversalPolicy(){
        this.list = new ArrayList<>();
    }
    
    @Override
    public Component getComponentAfter(Container container, Component component) {        
        int index = (list.indexOf(component) + 1) % list.size();
        return list.get(index);
    }

    @Override
    public Component getComponentBefore(Container container, Component component) {
        int index = list.indexOf(component) - 1;
        if(index < 0)index = list.size() - 1;
        return list.get(index);
    }

    @Override
    public Component getFirstComponent(Container container) {
        return list.get(0);
    }

    @Override
    public Component getLastComponent(Container cntnr) {
        return list.get(list.size() - 1);
    }

    @Override
    public Component getDefaultComponent(Container cntnr) {
        return list.get(0);
    }

    public void addComponent(Component component){
        list.add(component);
    }
    
    public void removeComponent(Component component){
        list.remove(component);
    }
}
