/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;

import java.io.InputStream;

/**
 *
 * @author Wilson
 */
public class DefaultUtility {
    protected InputStream getResourceAsStream(String resource){
        return getClass().getResourceAsStream(resource);
    }
}
