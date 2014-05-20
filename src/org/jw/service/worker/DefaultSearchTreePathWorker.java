/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.Enumeration;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.jw.service.entity.ServiceGroup;

/**
 *
 * @author Wilson
 */
public class DefaultSearchTreePathWorker<T> extends SwingWorker<TreePath, String> {
    private final DefaultMutableTreeNode root;
    private final Class entityClass;
    private final Comparable key;
    
    public DefaultSearchTreePathWorker(DefaultMutableTreeNode root, Class entityClass, Comparable key){
        this.root = root;
        this.entityClass = entityClass;
        this.key = key;
    }
    
    @Override
    protected TreePath doInBackground() throws Exception {
        TreePath result = null;
            
        Enumeration<DefaultMutableTreeNode> e = root.depthFirstEnumeration();
        
        while(e.hasMoreElements()){
            DefaultMutableTreeNode node = e.nextElement();
            Object userObject = node.getUserObject();
            
            if(entityClass.isInstance(userObject) && key.compareTo((T)userObject) == 0){
                result = new TreePath(node.getPath());
            }
        }        
        
        return result;
    }
    
}
