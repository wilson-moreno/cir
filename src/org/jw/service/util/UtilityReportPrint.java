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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jw.service.dao.DataAccessObject;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.pojo.ReportCode;
import org.jw.service.print.PrintParameter;
import org.jw.service.worker.DefaultContactRecordPrintWorker;
import org.jw.service.worker.DefaultJDBCPrintWorker;


/**
 *
 * @author Wilson
 */
public class UtilityReportPrint {
    private final Window parent;
    private final DefaultTaskListener listener;
    private final UtilityDatabase utilDB;
    private final DataAccessObject<AppsReport> reportDAO;
    private final EntityManager em;
    
    private UtilityReportPrint(Window parent, EntityManager em, UtilityDatabase utilDB, DefaultTaskListener listener){
        this.parent = parent;
        this.listener = listener;
        this.utilDB = utilDB;
        this.em = em;
        this.reportDAO = DataAccessObject.create(em, AppsReport.class);
    }
    
    public static UtilityReportPrint create(Window parent, EntityManager em, UtilityDatabase utilDB, DefaultTaskListener listener) {
        return new UtilityReportPrint(parent, em, utilDB, listener);
    }
    
    public void printReport(AppsReport report, List<PrintParameter> parameters){
        switch(report.getReportType()){
            case "Default" : buildReport(report, parameters); break;
            case "Contact Record" : buildContactRecordReport(report, parameters); break;   
        }
    }
    
    private void buildContactRecordReport(AppsReport report, List<PrintParameter> parameters){
        listener.setStartMessage("Creating " + report.getName() + " report...");
        listener.setDoneMessage("Finished creating " + report.getName() + " report...");
        InputStream inputStream = createInputStream(report.getFileJasper());
        Map<Integer, AppsReport> templateMap = fetchTemplates(report);        
        DefaultContactRecordPrintWorker worker = new DefaultContactRecordPrintWorker(parent, templateMap, parameters, utilDB.getConnection(), inputStream, listener);        
        worker.execute();
    }
    
    private Map<Integer, AppsReport> fetchTemplates(AppsReport report){
        Map<Integer, AppsReport> templates = new HashMap<>();
        templates.put(1, report);
        
        ReportCode reportCode = this.decipherCode(report.getCode());
        for(int t = 2; t <= Integer.parseInt(reportCode.getPageTotal()); t++){
            Query query = em.createNamedQuery("AppsReport.findByCode", AppsReport.class);
            query.setParameter("code", reconstructCode(reportCode, t));
            templates.put(t, (AppsReport)query.getSingleResult());
        }        
        
        return templates;
    }
    
    private String reconstructCode(ReportCode code, int order){
        return code.getTemplateName() + "." + code.getTemplateVersion() + "." + order + "." + code.getPageTotal();
    }
    
    private void buildReport(AppsReport report, List<PrintParameter> parameters){
        listener.setStartMessage("Creating " + report.getName() + " report...");
        listener.setDoneMessage("Finished creating " + report.getName() + " report...");
        InputStream inputStream = createInputStream(report.getFileJasper());
        DefaultJDBCPrintWorker worker = new DefaultJDBCPrintWorker(parent, report, parameters, utilDB.getConnection(), inputStream, listener);        
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

