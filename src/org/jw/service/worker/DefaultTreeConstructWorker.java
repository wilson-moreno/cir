/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;
import org.jw.service.listener.task.DefaultTaskListener;

/**
 *
 * @author Wilson
 */
public class DefaultTreeConstructWorker extends SwingWorker<DefaultMutableTreeNode, String>{        
    private final List<ServiceGroup> list;  
    private final List<Contact> noTerritoryContact = new ArrayList();
    private final Map<Territory, List<Contact>> contactTerritoryMap = new HashMap();
    
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
            noTerritoryContact.clear();
            contactTerritoryMap.clear();
            for(Contact contact : sortContactCollection(serviceGroup.getContactCollection())){
                organizeContact(contact);
            }
            insertNodes(serviceGroupNode, noTerritoryContact, contactTerritoryMap);
            root.add(serviceGroupNode);
        }
    }
    
    private void insertNodes(DefaultMutableTreeNode serviceGroupNode, List<Contact> list, Map<Territory, List<Contact>> map){
        for(Contact contact : sortContactCollection(list))
            serviceGroupNode.add(new DefaultMutableTreeNode(contact));
        
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pairs = (Map.Entry<Territory, List<Contact>>)iterator.next();
            DefaultMutableTreeNode territoryNode = new DefaultMutableTreeNode(pairs.getKey());
            for(Contact contact : sortContactCollection((List<Contact>)pairs.getValue())){
                DefaultMutableTreeNode contactNode = new DefaultMutableTreeNode(contact);
                territoryNode.add(contactNode);
            }
            serviceGroupNode.add(territoryNode);
        }        
    }
    
    private void organizeContact(Contact contact){
        if(contact.getTerritoryId() == null){
            noTerritoryContact.add(contact);
        }else{
            putContactIntoMap(contact);
        }   
    }
    
    private void putContactIntoMap(Contact contact){
        List<Contact> listContact = contactTerritoryMap.get(contact.getTerritoryId());

        if(listContact == null){
            listContact = new ArrayList<>();
            listContact.add(contact);
            contactTerritoryMap.put(contact.getTerritoryId(), listContact);
        } else {
            listContact.add(contact);
        }
    }
    
    
    
    private List<Contact> sortContactCollection(Collection<Contact> collection){
        List<Contact> contactList = new ArrayList(collection);
        Collections.sort(contactList);
        return contactList;
    }
    
    private List<Territory> sortTerritoryCollection(Collection<Territory> collection){
        List<Territory> territoryList = new ArrayList(collection);
        Collections.sort(territoryList);
        return territoryList;
    }
    
    private List<ServiceGroup> sortServiceGroupList(List<ServiceGroup> serviceGroupList){
        Collections.sort(serviceGroupList);
        return serviceGroupList;
    }
    
}
