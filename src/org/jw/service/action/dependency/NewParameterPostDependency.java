/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import java.awt.event.ActionEvent;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.AppsReportParameter;

/**
 *
 * @author Wilson
 */
public class NewParameterPostDependency implements DependencyCommand{
    private final DataAccessObject<AppsReportParameter> dao;
    private final AppsReport appsReportTarget;
    
    public NewParameterPostDependency(DataAccessObject<AppsReportParameter> dao, AppsReport appsReportTarget){
        this.dao = dao;
        this.appsReportTarget = appsReportTarget;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        AppsReportParameter parameter = (AppsReportParameter) workerResult;
        parameter.setReportId(appsReportTarget);
        dao.persist(parameter);
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
