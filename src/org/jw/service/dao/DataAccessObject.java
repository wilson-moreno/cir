/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import org.jw.service.entity.SilentSetter;

/**
 *
 * @author Wilson
 * @param <T>
 */
public class DataAccessObject<T> {
    private final Class<T> entityClass;    
    
    public static <T> DataAccessObject<T> create(EntityManager em, Class<T> entityClass) {
        return new DataAccessObject<>(em, entityClass);
    }
    private final EntityManager em;
    
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
    
    public T save(T entity){
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        ((SilentSetter)entity).silentSetProperty("updatedDatetime", new java.util.Date());
        em.persist(entity);
        entityTransaction.commit();
        em.refresh(entity);
        return entity;               
    }
    
    public void delete(T entity) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();        
        em.remove(entity);
        entityTransaction.commit();                
    }
    
    public void refresh(T entity, LockModeType lockMode) {
        em.refresh(entity);
    }
    
    
    public void refresh(T entity) {
        em.refresh(entity);
    }
    
    private T instantiateEntity(){
        try {
            return entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
