/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.table.cell.editor;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import org.jw.service.print.ListOption;
import org.jw.service.print.PrintParameter;
import org.jw.service.util.UtilityDatabase;


/**
 *
 * @author Wilson
 */
public class DefaultDataTypeCellEditor extends AbstractCellEditor implements TableCellEditor{
    private final List<PrintParameter> list;
    private final EntityManager em;
    private final UtilityDatabase utilDB;
    
    public static DefaultDataTypeCellEditor create(List<PrintParameter> list, EntityManager em) {
        return new DefaultDataTypeCellEditor(list, em);
    }
    
    private TableCellEditor editor;
    
    private DefaultDataTypeCellEditor(List<PrintParameter> list, EntityManager em){
        this.list = list;
        this.em = em;
        this.utilDB = UtilityDatabase.create(em);
    }
    
    @Override
    public Object getCellEditorValue() {
         if (editor != null) {
            return editor.getCellEditorValue();
         }         
         return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PrintParameter param = list.get(row);
        
        switch(param.getControlType()){
            case "Single Input": editor = getSingleInputEditor(param.getDataType()); break;
            case "Single Select Query": editor = getSingleSelectQuery(param); break;    
        }
        
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
    
    private DefaultCellEditor getSingleSelectQuery(PrintParameter param){
        JComboBox comboBox = new JComboBox();
        
        DefaultComboBoxModel comboBoxModel = getComboBoxModel(param);
        comboBox.setModel(comboBoxModel);
        
        return new DefaultCellEditor(comboBox);
    }
    
    private DefaultComboBoxModel getComboBoxModel(PrintParameter param){
        try {
            Connection connection = utilDB.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(param.getQueryText().getText());
            
            List<ListOption> list = new ArrayList<>();
            
            while(resultSet.next()){
                ListOption listOption = createListOption(param.getDataType());
                setListOption(listOption, param.getDataType(), resultSet, param.getDisplayColumn(), param.getValueColumn());
                list.add(listOption);
            }            
            
            Collections.sort(list);
            
            resultSet.close();
            statement.close();            
            
            return new DefaultComboBoxModel(list.toArray());
        } catch (SQLException ex) {
            Logger.getLogger(DefaultDataTypeCellEditor.class.getName()).log(Level.SEVERE, null, ex);
            return new DefaultComboBoxModel();
        }
    }
    
    private void setListOption(ListOption listOption, String dataType, ResultSet resultSet, String displayColumn, String valueColumn) throws SQLException{
        listOption.setName(resultSet.getString(displayColumn));
        
        switch(dataType){
            case "Integer" : listOption.setValue(resultSet.getInt(valueColumn)); break;
            case "String" : listOption.setValue(resultSet.getString(valueColumn));break;    
        }
    }
    
    
    private ListOption createListOption(String dataType){
        switch(dataType){
            case "Integer" : ListOption<Integer> intOption = new ListOption<>();
                             return intOption;
            case "String"  : ListOption<String> stringOption = new ListOption<>();
                             return stringOption;
            default : return new ListOption();    
        }
    }
    
    
    private DefaultCellEditor getSingleInputEditor(String dataType){
        DefaultCellEditor editor = null;
        
        switch(dataType){
            case "String" : editor = new DefaultCellEditor(new JTextField()); break;
            case "Boolean" : editor = new DefaultCellEditor(new JCheckBox()); break;            
            case "Integer" : editor = new DefaultCellEditor(createIntegerField()); break;
            default : editor = new DefaultCellEditor(new JTextField()); break;
        }
        
        return editor;
    }
    
    
    
    private JFormattedTextField createIntegerField(){
        try {
            JFormattedTextField textField;
            DefaultFormatterFactory factory;
            MaskFormatter formatter;
            
            textField = new JFormattedTextField();
            formatter = new MaskFormatter("####");
            factory = new DefaultFormatterFactory(formatter);
            textField.setFormatterFactory(factory);
            
            return textField;
        } catch (ParseException ex) {
            return new JFormattedTextField();
        }
    }
    
}
