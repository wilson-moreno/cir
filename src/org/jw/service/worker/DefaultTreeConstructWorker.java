/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultTreeConstructWorker extends SwingWorker<DefaultMutableTreeNode, String>{        
    private final List<ServiceGroup> list;    
    
    public DefaultTreeConstructWorker(List<ServiceGroup> list, DefaultTaskListener listener){
        this.list = list;
        this.addPropertyChangeListener(listener);
    }
    
    @Override
    protected DefaultMutableTreeNode doInBackground() throws Exception {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contacts");
        createTree(root);
        return root;
    }
    
    private void createTree(DefaultMutableTreeNode root){
        for(ServiceGroup serviceGroup : sortServiceGroupList(list)){
            DefaultMutableTreeNode serviceGroupNode = new DefaultMutableTreeNode(serviceGroup);
                        
            for(Contact contact : sortContactCollection(serviceGroup.getContactCollection())){
                DefaultMutableTreeNode contactNode = new DefaultMutableTreeNode(contact);
                serviceGroupNode.add(contactNode);
            }
            root.add(serviceGroupNode);
        }
    }
    
    private List<Contact> sortContactCollection(Collection<Contact> collection){
        List<Contact> contactList = new ArrayList(collection);
        Collections.sort(contactList);
        return contactList;
    }
    
    private List<ServiceGroup> sortServiceGroupList(List<ServiceGroup> serviceGroupList){
        Collections.sort(serviceGroupList);
        return serviceGroupList;
    }
    
}
