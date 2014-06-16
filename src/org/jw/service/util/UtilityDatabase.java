/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void backupTo(String directoryName){
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            System.out.println("Backing-up database...");
            stmt.executeUpdate("CHECKPOINT");
            stmt.executeUpdate("BACKUP DATABASE TO " + directoryName + " BLOCKING" );
            stmt.close();
            conn.close();
            System.out.println("Backing-up database complete...");
        } catch (SQLException ex) {
            Logger.getLogger(UtilityDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
