/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import org.eclipse.persistence.sessions.Session;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.SilentSetter;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DataAccessObject<T> {
    private final Class<T> entityClass;    
    private final EntityManager em;
    
    public static <T> DataAccessObject<T> create(EntityManager em, Class<T> entityClass) {
        return new DataAccessObject<>(em, entityClass);
    }
    
    
    private DataAccessObject(EntityManager em, Class<T> entityClass){
        this.em = em;
        this.entityClass = entityClass;
    }
    
    public T create(){
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();          
        T entity = this.instantiateEntity();
        em.persist(entity);
        entityTransaction.commit();        
        em.refresh(entity);        
        return entity;
    }
    
    public T createEmpty(){
        return this.instantiateEntity();
    }
    
    public void flush(){
        em.getTransaction().begin();
        em.flush();
        em.clear();
        em.getTransaction().commit();
    }
    
    public T merge(T entity){
        em.getTransaction().begin();
        T entityObject = em.merge(entity);
        em.getTransaction().commit();
        return entityObject;
    }
    
    public T read(Integer id){                
        em.getTransaction().begin();        
        T entity = em.find(entityClass, id);
        em.getTransaction().commit();
        return entity;
    }
    
    public List<T> runQuery(String queryString){        
        em.getTransaction().begin();
        Query query = em.createQuery(queryString);
        em.getTransaction().commit();
        return query.getResultList();
    }
    
    public List<T> readAll(){
        List<T> list;                        
        em.getTransaction().begin();                                
        Query query = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
        list = query.getResultList();
        em.getTransaction().commit();
        return list;
    }
    
    public List<T> findBy(){
        return null;
    }
    
    public T persist(T entity){
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        ((SilentSetter)entity).silentSetProperty("updatedDatetime", new java.util.Date());        
        em.persist(entity);
        entityTransaction.commit();        
        return entity;               
    }
    
    public void delete(T entity) 
        throws SQLIntegrityConstraintViolationException{        
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();                
        em.remove(entity);
        entityTransaction.commit();                        
    }
    
    public void refresh(T entity, LockModeType lockMode) {
        em.getTransaction().begin();        
        em.refresh(entity);
        em.getTransaction().commit();
    }
    
    public void refresh(T entity) {
        //em.getTransaction().begin();        
        em.refresh(entity);
        //em.getTransaction().commit();
    }
    
    private T instantiateEntity(){
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void showSessionInformation(){
        em.getTransaction().begin();
        Session session = em.unwrap(Session.class);
        em.getTransaction().commit();        
        
        Iterator iterator = session.getProperties().entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry pairs = (Map.Entry<Object, Object>)iterator.next();
            System.out.println(pairs.getKey() + " : " + pairs.getValue());            
        }
    }
    
    public void testQueryResult(String queryString){        
        Query query = em.createQuery(queryString);
        
        List<ServiceGroup> resultList = query.getResultList();
        System.out.println("\nStart:");
        for(ServiceGroup item : resultList){
            System.out.println(item);            
            for(Contact contact : item.getContactCollection()){
                System.out.println(contact);
            }
        }
        System.out.println("End:\n");
    }
}
