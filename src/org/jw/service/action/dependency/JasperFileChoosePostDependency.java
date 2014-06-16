/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.action.dependency;

import org.jw.service.action.DependencyCommand;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import org.jw.service.beans.ByteArrayBean;

/**
 *
 * @author Wilson
 */
public class JasperFileChoosePostDependency implements DependencyCommand{
    private final ByteArrayBean byteArrayBean;
    private final JTextField fileNameTextField;
    private final JDateChooser createdDateChooser;
    private final JDateChooser modifiedDateChooser;
    
    public JasperFileChoosePostDependency(ByteArrayBean byteArrayBean,
                                          JTextField fileNameTextField,
                                          JDateChooser createdDateChooser,
                                          JDateChooser modifiedDateChooser){
        this.byteArrayBean = byteArrayBean;
        this.fileNameTextField = fileNameTextField;
        this.createdDateChooser = createdDateChooser;
        this.modifiedDateChooser = modifiedDateChooser;
    }
    
    @Override
    public boolean run(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run(Object workerResult, ActionEvent ae) {
        try {
            File file = (File)workerResult;
            Path path = FileSystems.getDefault().getPath(file.getPath());
            BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            this.fileNameTextField.setText(file.getName());
            this.createdDateChooser.setDate(new Date(attr.creationTime().toMillis()));
            this.modifiedDateChooser.setDate(new Date(attr.lastModifiedTime().toMillis()));            
            this.byteArrayBean.setByteArray(Files.readAllBytes(path));
        } catch (IOException ex) {
            Logger.getLogger(JasperFileChoosePostDependency.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Object get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
