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
public class FileFilterDirectory extends FileFilter{

    public static FileFilterDirectory create() {
        return new FileFilterDirectory();
    }

    private FileFilterDirectory(){}
    
    @Override
    public boolean accept(File file) {
        return file.isDirectory();
    }

    @Override
    public String getDescription() {
        return "File Directories";
    }
    
}
