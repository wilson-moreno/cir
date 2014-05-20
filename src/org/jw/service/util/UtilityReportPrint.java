/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

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
import org.jw.service.worker.DefaultJDBCReportPrintWorker;

/**
 *
 * @author Wilson
 */
public class UtilityReportPrint {
    private final DefaultTaskListener listener;
    private final UtilityDatabase utilDB;
    
    private UtilityReportPrint(UtilityDatabase utilDB, DefaultTaskListener listener){
        this.listener = listener;
        this.utilDB = utilDB;
    }
    
    public static UtilityReportPrint create(UtilityDatabase utilDB, DefaultTaskListener listener) {
        return new UtilityReportPrint(utilDB, listener);
    }
    
    public void print(AppsReport report, List<PrintParameter> parameters){
        DefaultJDBCReportPrintWorker worker = new DefaultJDBCReportPrintWorker(report, parameters, this, listener);
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

