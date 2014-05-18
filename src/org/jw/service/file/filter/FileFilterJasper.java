/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.file.filter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Wilson
 */
public class FileFilterJasper extends FileFilter{

    public static FileFilterJasper create() {
        return new FileFilterJasper();
    }
    
    private FileFilterJasper(){}
    
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        
        String extension = FileExtensionUtility.getExtension(file);
        if (extension != null) {
            return extension.equals(FileExtensionUtility.jasper);
        }
        
        return false;        
    }

    @Override
    public String getDescription() {
        return "Jasper Compiled Reports (*.jasper)";
    }
}
