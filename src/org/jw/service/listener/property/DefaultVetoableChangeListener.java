/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.listener.property;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 *
 * @author Wilson
 */
public class DefaultVetoableChangeListener implements VetoableChangeListener{

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
