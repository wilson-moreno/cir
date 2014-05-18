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
public class FileFilterImage extends FileFilter{

    public static FileFilterImage create() {
        return new FileFilterImage();
    }

    private FileFilterImage(){}
    
    @Override
    public boolean accept(File file) {
        if(file.isDirectory()){
            return true;
        }
        
        String extension = FileExtensionUtility.getExtension(file);
        if (extension != null) {
            return extension.equals(FileExtensionUtility.tiff) ||
                    extension.equals(FileExtensionUtility.tif) ||
                    extension.equals(FileExtensionUtility.gif) ||
                    extension.equals(FileExtensionUtility.jpeg) ||
                    extension.equals(FileExtensionUtility.jpg) ||
                    extension.equals(FileExtensionUtility.png);
        }
        
        return false;        
    }

    @Override
    public String getDescription() {
        return "Image Files (*.tiff, *.tif, *.gif, *.jpg, *.jpeg, *.png)";
    }
    
}

