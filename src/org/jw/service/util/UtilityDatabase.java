/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.sql.Connection;
import javax.persistence.EntityManager;

/**
 *
 * @author Wilson
 */
public class UtilityDatabase {

    public static UtilityDatabase create(EntityManager em) {
        return new UtilityDatabase(em);
    }
    private final EntityManager em;
    
    private UtilityDatabase(EntityManager em){
        this.em = em;
    }
 
    
    public Connection getConnection(){
        Connection connection;
        
        em.getTransaction().begin();
        connection = em.unwrap(java.sql.Connection.class);        
        em.getTransaction().commit();
        
        return connection;
    }
    
}
