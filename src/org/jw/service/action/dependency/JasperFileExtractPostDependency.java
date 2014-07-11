/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jw.service.action.DependencyCommand;
import org.jw.service.entity.AppsReport;
import org.jw.service.util.UtilityTable;

/**
 *
 * @author Wilson
 */
public class JasperFileExtractPostDependency  implements DependencyCommand{
    private final UtilityTable<AppsReport> utilTable;
    private final java.awt.Window parent;
    
    public JasperFileExtractPostDependency(java.awt.Window parent, UtilityTable<AppsReport> utilTable){
        this.parent = parent;
        this.utilTable = utilTable;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        AppsReport report = utilTable.getSelectedItem();
        
        if(report.getFileJasper() == null){
            JOptionPane.showMessageDialog(parent, "There is no file to extract", "File Not Set", JOptionPane.INFORMATION_MESSAGE);
            return;
        }        
        
        File file = (File)workerResult;
        String absolutePath = file.getAbsolutePath();    
        String fileName = report.getFileName();
        
        String fullFilePath = absolutePath.concat("\\").concat(fileName);
        
        
        try (FileOutputStream fos = new FileOutputStream(fullFilePath)) {
            fos.write(report.getFileJasper());      
            JOptionPane.showMessageDialog(parent, "File successfully extracted.", "File Extracted", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JasperFileExtractPostDependency.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JasperFileExtractPostDependency.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
