/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.worker;

import java.awt.Frame;
import java.awt.Window;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JDialog;
import javax.swing.SwingWorker;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JRViewer;
import org.jw.service.entity.AppsReport;
import org.jw.service.entity.Contact;
import org.jw.service.entity.DirectionMap;
import org.jw.service.listener.task.DefaultTaskListener;
import org.jw.service.print.ListOption;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityGoogleDirectionXML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Wilson
 */
public class DefaultDirectionMapPrintWorker extends SwingWorker<JasperPrint, String>{      
    private final List<PrintParameter> paramList;
    private final Connection connection;    
    private final Window parent;
    private final AppsReport report;
    private final EntityManager em;
    
    public DefaultDirectionMapPrintWorker(Window parent, AppsReport report, List<PrintParameter> paramList, Connection connection, EntityManager em, DefaultTaskListener taskListener){
        this.parent = parent;
        this.report = report;
        this.paramList = paramList;
        this.connection = connection;        
        this.em = em;
        this.addPropertyChangeListener(taskListener);
    }
    
    @Override
    protected JasperPrint doInBackground() throws Exception {        
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
            JRViewer viewer = new JRViewer(print);
            JDialog dialog = new JDialog((Frame)parent, "Print Preview", true);
            dialog.add(viewer);            
            dialog.setSize(parent.getSize());
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DefaultDirectionMapPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private JasperPrint createReport() throws SQLException, JRException{
        ResultSet recordNumbers = fetchRecordNumbers(paramList);
        JasperPrint directionMapPrint = null;
        JasperPrint collatedPrint = createEmptyJasperPrint();
        
        while(recordNumbers.next()){
            String recordNumber = recordNumbers.getString("RECORD_NUMBER");
            directionMapPrint = createDirectionMap(report, recordNumber);            
            for(JRPrintPage page : directionMapPrint.getPages()){
                collatedPrint.addPage(page);
            }
        }
        
        return collatedPrint;
    }
    
    private JasperPrint createDirectionMap(AppsReport report, String recordNumber) throws SQLException{
        Query query;
        Contact contact;
        Document xmlDocument;
        DirectionMap directionMap;
        UtilityGoogleDirectionXML utility;
        JRXmlDataSource xmlDataSource;
        Map parameters = new HashMap();
        JasperPrint jasperPrint = null;
        InputStream inputStream;
        
        query = em.createQuery("SELECT c FROM Contact c WHERE c.recordNumber = :recordNumber");
        query.setParameter("recordNumber", recordNumber);
        contact = (Contact)query.getSingleResult();
        utility = UtilityGoogleDirectionXML.getInstance();
        
        directionMap = contact.getLocationMapId().getDirectionMap();
        
        try {
            xmlDocument = utility.loadXMLFromString(directionMap.getDirection());
            xmlDataSource = new JRXmlDataSource(xmlDocument,"/DirectionsResponse/route/leg/step");                
            parameters.put("CONTACT", contact);
            parameters.put("START_ADDRESS", directionMap.getMeetingPlaceId().getAddress());                        
            parameters.put("DIRECTION_IMAGE", directionMap.getImage());
            inputStream = createInputStream(report);
            jasperPrint = JasperFillManager.fillReport(inputStream, parameters, xmlDataSource);                    
        } catch (ParserConfigurationException | SAXException | IOException | JRException ex) {
            Logger.getLogger(DefaultDirectionMapPrintWorker.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return jasperPrint;
    }
    
    private ResultSet fetchRecordNumbers(List<PrintParameter> paramList) throws SQLException{
        String recordNumberStart = "";
        String recordNumberEnd = "";
        
        for(PrintParameter param : paramList){
            switch(param.getName()){
                case "START_RECORD_NUMBER" : recordNumberStart = getParamValue(param.getValue()); break;
                case "END_RECORD_NUMBER" : recordNumberEnd = getParamValue(param.getValue()); break;
            }
        }
        
        return getRecordNumberResultSet(recordNumberStart, recordNumberEnd);
    }
    
    private Map<String, Object> createParameterMap(AppsReport report, List<PrintParameter> paramList){
        Map<String, Object> parameterMap = new HashMap<>();
        
        parameterMap.put("REPORT_TITLE", report.getTitle());
        for(PrintParameter param : paramList)
            parameterMap.put(param.getName(), param.getValue());
        
        return parameterMap;
    }
 
    
    private JasperPrint createEmptyJasperPrint() throws JRException{
        InputStream inputStream = new ByteArrayInputStream(report.getFileJasper());        
        JasperPrint empty = JasperFillManager.fillReport(inputStream, null, new JREmptyDataSource());                    
        empty.removePage(0);
        return empty;
    }
    
    private InputStream createInputStream(AppsReport report){
        return new ByteArrayInputStream(report.getFileJasper());
    }
    
    private ResultSet getRecordNumberResultSet(String recordNumberStart, String recordNumberEnd) throws SQLException{
        String queryString = "SELECT c.record_number FROM CIR.Contact c " +
                             "INNER JOIN CIR.Location_Map l ON c.id = l.contact_id " +
                             "INNER JOIN CIR.Direction_Map d ON l.id = d.location_map_id " +
                             "WHERE record_number BETWEEN ? AND ? " +
                             "ORDER BY record_number ";
        PreparedStatement statement = connection.prepareStatement(queryString);
        statement.setString(1, recordNumberStart);
        statement.setString(2, recordNumberEnd);        
        return statement.executeQuery();
    }
    
    
    private String getParamValue(Object value){
        if(value instanceof ListOption){
            return (String)((ListOption)value).getValue();
        } else {
            return (String)value;
        }            
    }
    
    
}
