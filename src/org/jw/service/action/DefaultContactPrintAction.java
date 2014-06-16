/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action;

import java.awt.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JButton;
import org.jw.service.entity.AppsReport;
import org.jw.service.util.UtilityReportPrint;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class DefaultContactPrintAction extends DependentAbstractAction{        
    private final EntityManager em;
    
    public DefaultContactPrintAction(JButton command, EntityManager em) {
        super(command.getText(), command.getIcon());        
        this.em = em;
        command.setAction(this);
    }

    @Override
    public boolean mainActionPerformed(ActionEvent ae) {        
        Query query = em.createNamedQuery("AppsReport.findByCode", AppsReport.class);
        query.setParameter("code", "contact_record.1.1.3");
        AppsReport report = (AppsReport)query.getSingleResult();
        this.workerResult = report;
        return true;
    }
    
}
