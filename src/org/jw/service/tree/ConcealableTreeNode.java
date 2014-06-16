/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.tree;

import java.beans.PropertyChangeSupport;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Wilson
 */
public class ConcealableTreeNode extends DefaultMutableTreeNode{
    public static final String PROP_VISIBLE = "PROP_VISIBLE";
    private boolean visible;
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    
    public ConcealableTreeNode(){
        super();
    }
    
    public ConcealableTreeNode(Object userObject){
        super(userObject);
    }
    
    public ConcealableTreeNode(Object userObject, boolean allowsChildren){
        super(userObject, allowsChildren);
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        boolean oldVisible = this.visible;
        this.visible = visible;
        propertyChangeSupport.firePropertyChange(PROP_VISIBLE, oldVisible, visible);
    }
}
