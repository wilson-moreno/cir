/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.awt.Frame;
import java.awt.Window;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JRViewer;
import org.jw.service.entity.AppsReport;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.ListOption;
import org.jw.service.print.PrintParameter;

/**
 *
 * @author Wilson
 */
public class DefaultContactRecordPrintWorker extends SwingWorker<JasperPrint, String>{
    private final String LIMIT_OFFSET = "limit ? offset ?";    
    private final List<PrintParameter> paramList;
    private final Connection connection;
    private final InputStream inputStream;
    private final Window parent;
    private final Map<Integer, AppsReport> templateMap;
    private final boolean preview;
    private int lastLineCount = 0;
    
    public DefaultContactRecordPrintWorker(Window parent, Map<Integer, AppsReport> templateMap, List<PrintParameter> paramList, Connection connection, InputStream inputStream, DefaultTaskListener taskListener, boolean preview){
        this.parent = parent;
        this.templateMap = templateMap;
        this.paramList = paramList;
        this.connection = connection;
        this.inputStream = inputStream;
        this.preview = preview;
        this.addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected JasperPrint doInBackground() throws Exception {
        //PreparedStatement statement = createPreparedStatement(report.getQuery(), paramList);
        //Map<String, Object> paramMap = createParameterMap(report, paramList);        
        //ResultSet resultSet = statement.executeQuery();
        //JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
        //return JasperFillManager.fillReport(inputStream, paramMap, resultSetDataSource);        
        return createReport();
    }
    
    private PreparedStatement createPreparedStatement(String query, List<PrintParameter> paramList) throws SQLException{
        Collections.sort(paramList);
        PreparedStatement statement = connection.prepareStatement(query);
        for(PrintParameter param : paramList){
            if(param.getParameterType().trim().equalsIgnoreCase("Query"))
                setParameter(statement, param);        
        }        
        return statement;
    }
    
    private void setParameter(PreparedStatement statement, PrintParameter param) throws SQLException{
        switch(param.getDataType()){
            case "String" : statement.setString(param.getSequence(), (String) param.getValue());
        }
    }
    
    @Override
    protected void done(){
        try {
            JasperPrint print = get();                                    
            if(preview){
                JRViewer viewer = new JRViewer(print);
                JDialog dialog = new JDialog((Frame)parent, "Print Preview", true);
                dialog.add(viewer);            
                dialog.setSize(parent.getSize());
                dialog.setLocationRelativeTo(parent);                
                dialog.setVisible(true);
            } else {
                JasperPrintManager.printReport(print, true);
            }
        } catch (InterruptedException | ExecutionException | JRException ex) {
            Logger.getLogger(DefaultContactRecordPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JasperPrint createReport() throws SQLException, JRException{
        ResultSet recordNumbers = fetchRecordNumbers(paramList);
        JasperPrint contactRecordPrint = null;
        JasperPrint collatedPrint = createEmptyJasperPrint();
        
        while(recordNumbers.next()){
            String recordNumber = recordNumbers.getString("RECORD_NUMBER");
            String lastName = recordNumbers.getString("LAST_NAME");
            String firstName = recordNumbers.getString("FIRST_NAME");            
            contactRecordPrint = createContactRecord(templateMap.get(1), recordNumber, lastName, firstName);            
            for(JRPrintPage page : contactRecordPrint.getPages()){
                collatedPrint.addPage(page);
            }
        }
        
        return collatedPrint;
    }
    
    private ResultSet fetchRecordNumbers(List<PrintParameter> paramList) throws SQLException{
        String recordNumberStart = "";
        String recordNumberEnd = "";
        
        for(PrintParameter param : paramList){
            switch(param.getName()){
                case "START_RECORD_NUMBER" : recordNumberStart = (String)getValue(param.getValue()); break;
                case "END_RECORD_NUMBER" : recordNumberEnd = (String)getValue(param.getValue()); break;
            }
        }
        
        return getRecordNumberResultSet(recordNumberStart, recordNumberEnd);
    }
    
    private Object getValue(Object value){
        if(value instanceof ListOption){
            ListOption option = (ListOption)value;
            return option.getValue();
        }else{
            return value;
        }
    }
    
    private Map<String, Object> createParameterMap(AppsReport report, List<PrintParameter> paramList){
        Map<String, Object> parameterMap = new HashMap<>();
        
        parameterMap.put("REPORT_TITLE", report.getTitle());
        for(PrintParameter param : paramList)
            parameterMap.put(param.getName(), param.getValue());
        
        return parameterMap;
    }
 
    
    private JasperPrint createEmptyJasperPrint() throws JRException{
        InputStream inputStream = new ByteArrayInputStream(templateMap.get(1).getFileJasper());        
        JasperPrint empty = JasperFillManager.fillReport(inputStream, null, new JREmptyDataSource());                    
        empty.removePage(0);
        return empty;
    }
    
    private InputStream createInputStream(AppsReport report){
        return new ByteArrayInputStream(report.getFileJasper());
    }
    
    private ResultSet getRecordNumberResultSet(String recordNumberStart, String recordNumberEnd) throws SQLException{
        String queryString = "SELECT c.record_number, c.last_name, c.first_name FROM CIR.Contact c WHERE record_number BETWEEN ? AND ? ORDER BY record_number";
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setString(1, recordNumberStart);
        statement.setString(2, recordNumberEnd);        
        return statement.executeQuery();
    }
    
    private JasperPrint createContactRecord(AppsReport report, String recordNumber, String lastName, String firstName) throws SQLException, JRException{
        JasperPrint firstPage;
        JasperPrint succeedingPages;
        ResultSet resultSet;
        int pageCount = 0;
        
        firstPage = createFirstPage(report, recordNumber);
        pageCount++;
        
        appendSucceedingPages(report, recordNumber, firstPage, pageCount, lastName, firstName);        
        
        return firstPage;
    }
    
    private void appendSucceedingPages(AppsReport report, String recordNumber, JasperPrint firstPage, int pageCount, String lastName, String firstName) throws SQLException, JRException{
        int succeedingOffset = report.getLineLimit();        
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint succeedingPage = null;
        
        PreparedStatement statement = connection.prepareStatement(report.getQuery().concat(" ").concat(LIMIT_OFFSET),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);        
        statement.setString(1, recordNumber);
        statement.setString(2, recordNumber);
        statement.setInt(3, templateMap.get(2).getLineLimit());
        statement.setInt(4, succeedingOffset);
        
        ResultSet resultSet = statement.executeQuery();        
        
        while(!empty(resultSet)){            
            parameters.put("PAGE_NUMBER", ++pageCount);
            parameters.put("RECORD_NUMBER", recordNumber);  
            parameters.put("CONTACT_NAME", lastName.trim().equalsIgnoreCase("") ? firstName.trim():lastName.trim().concat(", ").concat(firstName.trim()));
            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
            InputStream inputStream = createInputStream(pageCount % 2 == 0 ? templateMap.get(2):templateMap.get(3));
            succeedingPage = JasperFillManager.fillReport(inputStream, parameters, resultSetDataSource);            
            firstPage.addPage(succeedingPage.getPages().get(0));
            succeedingOffset += pageCount % 2 == 0 ? templateMap.get(2).getLineLimit():templateMap.get(3).getLineLimit();
            
            statement = connection.prepareStatement(report.getQuery().concat(" ").concat(LIMIT_OFFSET), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.setString(1, recordNumber);
            statement.setString(2, recordNumber);
            statement.setInt(3, templateMap.get(2).getLineLimit());
            statement.setInt(4, succeedingOffset);
            resultSet = statement.executeQuery();            
        }
        
        if(pageCount % 2 == 1){
            parameters.put("PAGE_NUMBER", ++pageCount);
            parameters.put("RECORD_NUMBER", recordNumber);
            parameters.put("CONTACT_NAME", lastName.trim().equalsIgnoreCase("") ? firstName.trim():lastName.trim().concat(", ").concat(firstName.trim()));
            succeedingPage = JasperFillManager.fillReport(createInputStream(templateMap.get(2)), parameters, new JREmptyDataSource());            
            firstPage.addPage(succeedingPage.getPages().get(0));
        } else if(lastLineCount > 15) {
            parameters.put("PAGE_NUMBER", ++pageCount);
            parameters.put("RECORD_NUMBER", recordNumber);
            parameters.put("CONTACT_NAME", lastName.trim().equalsIgnoreCase("") ? firstName.trim():lastName.trim().concat(", ").concat(firstName.trim()));
            
            succeedingPage = JasperFillManager.fillReport(createInputStream(templateMap.get(3)), parameters, new JREmptyDataSource());            
            firstPage.addPage(succeedingPage.getPages().get(0));            
            
            parameters.remove("PAGE_NUMBER");
            parameters.put("PAGE_NUMBER", ++pageCount);            
            
            succeedingPage = JasperFillManager.fillReport(createInputStream(templateMap.get(2)), parameters, new JREmptyDataSource());            
            firstPage.addPage(succeedingPage.getPages().get(0));            
        }
        
    }
    
    private JasperPrint createFirstPage(AppsReport report, String recordNumber) throws SQLException, JRException{
        PreparedStatement statement = connection.prepareStatement(report.getQuery().concat(" ").concat(LIMIT_OFFSET));
        statement.setString(1, recordNumber);
        statement.setString(2, recordNumber);
        statement.setInt(3, report.getLineLimit());
        statement.setInt(4, 0);
        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(statement.executeQuery());        
        InputStream inputStream = new ByteArrayInputStream(report.getFileJasper());        
        Map<String, Object> paramMap = createParameterMap(report, paramList);        
        return JasperFillManager.fillReport(inputStream, paramMap, resultSetDataSource);
    }
    
    protected boolean empty(ResultSet resultSet) throws SQLException{
        boolean empty = !resultSet.last();        
        if(!empty)lastLineCount = resultSet.getRow();
        resultSet.beforeFirst();        
        return empty;
    }
}
