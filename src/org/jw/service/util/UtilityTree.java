/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactStatus;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;
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
    private final DataAccessObject<ServiceGroup> dao;
    private final DefaultTaskListener listener;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private final Deque<ContactNode> stack;
    private boolean isActiveOnly;
    
    
    private UtilityTree(JTree tree, DataAccessObject<ServiceGroup> dao, DefaultTaskListener listener){
        this.tree = tree;        
        this.dao = dao;
        this.listener = listener;
        this.isActiveOnly = true;
        this.stack = new ArrayDeque<ContactNode>();
        this.constructTree();
    }
    
    public static UtilityTree create(JTree tree, DataAccessObject<ServiceGroup> dao, DefaultTaskListener listener) {
        return new UtilityTree(tree, dao, listener);            
    }
    
    private void constructTree(){           
        List<ServiceGroup> serviceGroupList = dao.runQuery("SELECT s FROM ServiceGroup s");
        DefaultTreeConstructWorker worker = new DefaultTreeConstructWorker(serviceGroupList, listener);
        worker.execute();

        try {
            if(root != null)root.removeAllChildren();
            root = worker.get();
            if(model == null)model = new DefaultTreeModel(root);
            else {
                model.setRoot(root);
                model.reload(root);
            }
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
    
    public void addNode(ServiceGroup serviceGroup){
        root.add(new DefaultMutableTreeNode(serviceGroup));
        model.reload();
    }
    
    public void refresh(ServiceGroup serviceGroup){
        try {
            TreePath treePath = findTreePath(serviceGroup);
            if(treePath != null){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                node.setUserObject(serviceGroup);
                model.reload(node);
            }
                
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refresh(Contact contact){                
        try {
            TreePath treePath = findTreePath(contact);
            if(treePath != null){
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();                
                node.setUserObject(contact);
                updateParent(node);
                model.reload(node);
                hideIfNotActive(node);
            } else {
                java.util.Iterator<ContactNode> i = stack.iterator();
                while(i.hasNext()){
                    ContactNode contactNode = i.next();
                    DefaultMutableTreeNode node = contactNode.getNode();
                    Contact c = (Contact)node.getUserObject();
                    if(contact.getId().equals(c.getId())){
                        stack.remove(contactNode);
                        model.insertNodeInto(contactNode.getNode(), contactNode.getParent(), contactNode.getIndex());
                    }                        
                }
            }               
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void hideIfNotActive(DefaultMutableTreeNode node){
        Contact contact;
        ContactStatus status;
        contact = (Contact) node.getUserObject();
        status = contact.getStatusId();                    
        if(status != null && status.getActive() != null && !status.getActive()){                    
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
            int index = parent.getIndex(node);
            pushNode(new ContactNode(parent, node, index));                    
            model.removeNodeFromParent(node);
            model.reload(parent);                                        
        }
    }
    
    private void updateParent(DefaultMutableTreeNode node) throws InterruptedException, ExecutionException{
        Contact userObject = (Contact) node.getUserObject();
        
        // Contacts node parent can either be a Service Group or Territory
        
        // Test if Territory is not null, if null do nothing
        
        if(userObject.getTerritoryId() != null){ // Territory is not null
            // Test if parent is Service Group
            if(isParentServiceGroup(node)){
                // If yes test if territory is in service group
                DefaultMutableTreeNode serviceGroupNode = (DefaultMutableTreeNode) node.getParent();
                changeParentNode(serviceGroupNode, node);
            } else { // Parent is Territory
                if(territoryChanged(node)){
                    DefaultMutableTreeNode serviceGroupNode = findNode(userObject.getServiceGroupId());
                    if(serviceGroupNode != null)changeParentNode(serviceGroupNode, node);
                }
            }
        } 
    }
    
    private void changeParentNode(DefaultMutableTreeNode serviceGroupNode, DefaultMutableTreeNode contactNode) throws InterruptedException, ExecutionException{
        Contact userObject = (Contact) contactNode.getUserObject();
        TreePath territoryTreePath = findTreePath(serviceGroupNode, userObject.getTerritoryId());        
        if(territoryTreePath != null){ // Territory is in the tree
            DefaultMutableTreeNode territoryNode = (DefaultMutableTreeNode) territoryTreePath.getLastPathComponent();
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)contactNode.getParent();
            model.removeNodeFromParent(contactNode);
            model.insertNodeInto(contactNode, territoryNode, 0);
            if(parentNode.isLeaf())model.removeNodeFromParent(parentNode);
        } else { // Territory is not yet in the tree
            DefaultMutableTreeNode territoryNode = new DefaultMutableTreeNode(userObject.getTerritoryId());                    
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode)contactNode.getParent();
            model.removeNodeFromParent(contactNode);
            model.insertNodeInto(contactNode, territoryNode, 0);
            model.insertNodeInto(territoryNode, serviceGroupNode, 0);
            if(parentNode.isLeaf())model.removeNodeFromParent(parentNode);
        }
    }
    
    private boolean territoryChanged(DefaultMutableTreeNode node){
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        Territory territoryParent = (Territory)parent.getUserObject();
        Territory territoryCurrent = ((Contact)node.getUserObject()).getTerritoryId();
        return territoryParent.getId() != territoryCurrent.getId();
    }
    
    private boolean isParentServiceGroup(DefaultMutableTreeNode node){
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
        return ServiceGroup.class.isInstance(parentNode.getUserObject());
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
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
                model.removeNodeFromParent(node);
                model.reload(parent);
                if(parent.isLeaf() && !parent.isRoot()){
                    DefaultMutableTreeNode grandParent = (DefaultMutableTreeNode) parent.getParent();
                    model.removeNodeFromParent(parent);
                    model.reload(grandParent);
                }
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UtilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void refresh(){ constructTree(); }
    public Object getLastSelectedPathComponent(){ return tree.getLastSelectedPathComponent(); }
    
    private TreePath findTreePath(DefaultMutableTreeNode parent, Territory territory) throws InterruptedException, ExecutionException{
        if(territory == null)return null;
        DefaultSearchTreePathWorker<Territory> territorySearchWorker = new DefaultSearchTreePathWorker(parent, Territory.class, territory);
        territorySearchWorker.execute();        
        return territorySearchWorker.get();
    }
    
    private DefaultMutableTreeNode findNode(ServiceGroup serviceGroup) throws InterruptedException, ExecutionException{
        DefaultMutableTreeNode node = null;        
        
        TreePath treePath = findTreePath(serviceGroup);
        if(treePath == null) return null;
        
        return (DefaultMutableTreeNode) treePath.getLastPathComponent();
    }
    
    private TreePath findTreePath(ServiceGroup serviceGroup) throws InterruptedException, ExecutionException{        
        if(serviceGroup == null)return null;
        DefaultSearchTreePathWorker<ServiceGroup> serviceGroupSearchWorker = new DefaultSearchTreePathWorker(root, ServiceGroup.class, serviceGroup);
        serviceGroupSearchWorker.execute();
        return serviceGroupSearchWorker.get();
    } 
    
    private TreePath findTreePath(Contact contact) throws InterruptedException, ExecutionException{        
        if(contact ==  null)return null;
        DefaultSearchTreePathWorker<Contact> contactSearchWorker = new DefaultSearchTreePathWorker(root, Contact.class, contact);
        contactSearchWorker.execute();
        return contactSearchWorker.get();
    }
    
    private TreePath findTreePath(Territory territory) throws InterruptedException, ExecutionException{        
        if(territory == null)return null;
        DefaultSearchTreePathWorker<Territory> territorySearchWorker = new DefaultSearchTreePathWorker(root, Territory.class, territory);
        territorySearchWorker.execute();        
        return territorySearchWorker.get();
    }
    
    public void showActiveOnly(boolean showActive){        
        isActiveOnly = showActive;
        
        if(showActive){
            Enumeration children = root.breadthFirstEnumeration();        
        
            while(children.hasMoreElements()){
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)children.nextElement();
                if(treeNode.getUserObject() instanceof Contact){
                    Contact contact;
                    ContactStatus status;
                    contact = (Contact) treeNode.getUserObject();
                    status = contact.getStatusId();                    
                    if(status != null && status.getActive() != null && !status.getActive()){                    
                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)treeNode.getParent();
                        int index = parent.getIndex(treeNode);
                        pushNode(new ContactNode(parent, treeNode, index));                    
                        model.removeNodeFromParent(treeNode);
                        model.reload(parent);                                        
                    }
                }
            }
        }else{
            while(!stack.isEmpty()){
                ContactNode node;
                node = stack.pop();
                model.insertNodeInto(node.getNode(), node.getParent(), node.getIndex());
            }
        }
    }
    
    private void pushNode(ContactNode node){
        stack.push(node);
    }
    
    
}

class ContactNode{
    private final DefaultMutableTreeNode parent;
    private final DefaultMutableTreeNode node;
    private final int index;
    
    public ContactNode(DefaultMutableTreeNode parent,
                       DefaultMutableTreeNode node,
                       int index){
        this.parent = parent;
        this.node = node;
        this.index = index;
    }

    /**
     * @return the parent
     */
    public DefaultMutableTreeNode getParent() {
        return parent;
    }

    /**
     * @return the node
     */
    public DefaultMutableTreeNode getNode() {
        return node;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
    
    
}