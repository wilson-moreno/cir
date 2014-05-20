/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.swing.SwingWorker;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityReportPrint;

/**
 *
 * @author Wilson
 */
public class DefaultJDBCReportPrintWorker extends SwingWorker{
    private final AppsReport report;
    private final List<PrintParameter> paramList;
    private final UtilityReportPrint utilPrint;
        
    public DefaultJDBCReportPrintWorker(AppsReport report, List<PrintParameter> paramList, UtilityReportPrint utilPrint, DefaultTaskListener listener){
        this.report = report;
        this.paramList = paramList;
        this.utilPrint = utilPrint;
        this.addPropertyChangeListener(listener);
    }
    
    
    @Override
    protected Object doInBackground() throws Exception {
        //Map<String, Object> parameters = utilPrint.createParameterMap(paramList);
        //InputStream reportInputStream = utilPrint.createInputStream(report.getFileJasper());
        Connection connection = utilPrint.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(report.getQuery());
        
        while(resultSet.next()){
            System.out.printf("First Name: %s \n", resultSet.getString("firstName"));
        }
        
        return null;
    }
    
    
}
