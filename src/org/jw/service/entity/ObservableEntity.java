/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.entity;

import java.beans.PropertyChangeListener;

/**
 *
 * @author Wilson
 */
public interface ObservableEntity {
    public String getSaveState();
    public Integer getId();
    public String getName();
    public String getImplementingClassName();
    public boolean hasDependentEntities();
    public boolean isMissingRequiredFields();
    public void setSaveState(String saveState);
    public void addPropertyChangeListener(PropertyChangeListener listener);
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
    public void removePropertyChangeListener(PropertyChangeListener listener);
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
