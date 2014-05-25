/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.awt.Window;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.pojo.ReportCode;
import org.jw.service.print.PrintParameter;
import org.jw.service.worker.DefaultCustomJDBCPrintWorker;


/**
 *
 * @author Wilson
 */
public class UtilityReportPrint {
    private final Window parent;
    private final DefaultTaskListener listener;
    private final UtilityDatabase utilDB;
    
    private UtilityReportPrint(Window parent, UtilityDatabase utilDB, DefaultTaskListener listener){
        this.parent = parent;
        this.listener = listener;
        this.utilDB = utilDB;
    }
    
    public static UtilityReportPrint create(Window parent, UtilityDatabase utilDB, DefaultTaskListener listener) {
        return new UtilityReportPrint(parent, utilDB, listener);
    }
    
    public void printReport(AppsReport report, List<PrintParameter> parameters){
        switch(report.getReportType()){
            case "Default" : break;
            case "Custom" : buildCustomReport(report, parameters); break;   
        }
    }
    
    private void buildCustomReport(AppsReport report, List<PrintParameter> parameters){
        listener.setStartMessage("Creating " + report.getName() + " report...");
        listener.setDoneMessage("Finished creating " + report.getName() + " report...");
        InputStream inputStream = createInputStream(report.getFileJasper());
        DefaultCustomJDBCPrintWorker worker = new DefaultCustomJDBCPrintWorker(parent, report, parameters, utilDB.getConnection(), inputStream, listener);        
        worker.execute();
    }
    
    public Map createParameterMap(List<PrintParameter> parameters){
        Map<String, Object> paramMap = new HashMap<>();
        
        for(PrintParameter parameter : parameters){
            paramMap.put(parameter.getName(), parameter.getValue());
        }
        
        return paramMap;
    }
 
    
    public InputStream createInputStream(byte[] byteStream){
        return new ByteArrayInputStream(byteStream);
    }
    
    public Connection getConnection(){
        return utilDB.getConnection();
    }
    
    public ReportCode decipherCode(String codeString){
        StringTokenizer tokenizer = new StringTokenizer(codeString, ".");
        ReportCode reportCode = new ReportCode();
        
        int token = 1;
        while(tokenizer.hasMoreTokens()){
            switch(token){
                case 1 : reportCode.setTemplateName(tokenizer.nextToken()); break;
                case 2 : reportCode.setTemplateVersion(tokenizer.nextToken()); break;
                case 3 : reportCode.setPageOrder(tokenizer.nextToken()); break;    
                case 4 : reportCode.setPageTotal(tokenizer.nextToken()); break;
            }
            token++;
        }
        
        return reportCode;
    }
    
}

