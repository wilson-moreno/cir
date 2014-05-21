/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JTable;
import org.jw.service.action.DependencyCommand;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.AppsReportParameter;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class ParameterNewPostDependency implements DependencyCommand{    
    private final DataAccessObject<AppsReportParameter> dao;
    private final List<AppsReport> list;
    private final UtilityTable utilTable;
            
    public ParameterNewPostDependency(DataAccessObject<AppsReportParameter> dao,
                                      List<AppsReport> list,
                                      JTable table){        
        this.dao = dao;
        this.list = list;
        this.utilTable = UtilityTable.create(table, list);        
    }     
    
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        AppsReportParameter parameter = (AppsReportParameter) workerResult;
        AppsReport report = (AppsReport) list.get(utilTable.getSelectedModelIndex());
        parameter.silentSetProperty("reportId", report);
        dao.save(parameter);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
