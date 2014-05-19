/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.tree.cell.renderer.AppsTreeCellRenderer;
import org.jw.service.worker.DefaultTreeConstructWorker;

/**
 *
 * @author Wilson
 */
public class UtilityTree {
    private final JTree tree;
    private final List<ServiceGroup> list;          
    private final DefaultTaskListener listener;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    
    private UtilityTree(JTree tree, List<ServiceGroup> list, DefaultTaskListener listener){
        this.tree = tree;
        this.list = list;        
        this.listener = listener;
        this.constructTree();
    }
    
    public static UtilityTree create(JTree tree, List<ServiceGroup> list, DefaultTaskListener listener) {
        return new UtilityTree(tree, list, listener);            
    }
    
    private void constructTree(){
        DefaultTreeConstructWorker worker = new DefaultTreeConstructWorker(list, listener);
        worker.execute();

        try {
            root = worker.get();
            model = new DefaultTreeModel(root);
            tree.setCellRenderer(AppsTreeCellRenderer.create());
            tree.setModel(model);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
