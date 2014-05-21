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
import javax.swing.tree.TreePath;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.tree.cell.renderer.AppsTreeCellRenderer;
import org.jw.service.worker.DefaultSearchTreePathWorker;
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
    
    public void updateNode(Contact contact){
        try {
            TreePath treePath = findTreePath(contact);
            if(treePath != null){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addNode(Contact contact){
        try {        
            TreePath treePath = findTreePath(contact.getServiceGroupId());
            if(treePath != null){
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(contact);
                model.insertNodeInto(childNode, parent, 0);
                model.reload(parent);                
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeNode(Contact contact){
        try {
            TreePath treePath = findTreePath(contact);
            if(treePath != null){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                model.removeNodeFromParent(node);
                model.reload();
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refresh(){ constructTree(); }
    public Object getLastSelectedPathComponent(){ return tree.getLastSelectedPathComponent(); }
    
    private TreePath findTreePath(ServiceGroup serviceGroup) throws InterruptedException, ExecutionException{        
        serviceGroupSearchWorker = new DefaultSearchTreePathWorker(root, ServiceGroup.class, serviceGroup);
        serviceGroupSearchWorker.execute();
        return serviceGroupSearchWorker.get();
    } 
    
    private TreePath findTreePath(Contact contact) throws InterruptedException, ExecutionException{
        contactSearchWorker = new DefaultSearchTreePathWorker(root, Contact.class, contact);
        contactSearchWorker.execute();
        return contactSearchWorker.get();
    }
    
    
    DefaultSearchTreePathWorker<ServiceGroup> serviceGroupSearchWorker; 
    DefaultSearchTreePathWorker<Contact> contactSearchWorker; 
}
