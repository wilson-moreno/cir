/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.dao;

import javax.persistence.EntityManager;

/**
 *
 * @author Wilson
 */
public final class EntityManagerHolder {
    private static EntityManagerHolder instance = null;
    private EntityManager em;
    
    protected EntityManagerHolder(EntityManager em){
        this.em = em;
    }
    
    public static EntityManagerHolder getInstance(EntityManager em){
        if(instance == null){
            instance = new EntityManagerHolder(em);            
        }
        return instance;
    }
    
}
