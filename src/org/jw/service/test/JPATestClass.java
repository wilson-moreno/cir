/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jw.service.entity.Contact;

/**
 *
 * @author Wilson
 */
public class JPATestClass {
    private final EntityManager em;
    private final UserClass userClass;
    
    public JPATestClass(EntityManager em){
        this.em = em;
        this.userClass = new UserClass(em);
    }
    
    public void runTest(){
        Contact contact = em.find(Contact.class, 32);
        show(contact);
        em.getTransaction().begin();
        em.remove(contact);
        em.getTransaction().commit();
        userClass.run();
    }
    
    public void show(Object entity){
        System.out.println();
        System.out.println(entity);
        
    }
    
    
    
}

class UserClass{
    private EntityManager em;
    
    public UserClass(EntityManager em){
        this.em = em;
    }
    
    public void run(){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c FROM Contact c");
        em.getTransaction().commit();
        showList(query.getResultList());        
    }
    
    public void showList(List<Object> list){
        for(Object item : list)
            show(item);
    }
    
    public void show(Object entity){        
        System.out.println(entity);
    }
}
