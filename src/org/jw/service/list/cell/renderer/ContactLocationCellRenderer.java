/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.list.cell.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.jw.service.entity.Contact;
import org.jw.service.entity.LocationMap;

/**
 *
 * @author Wilson
 */
public class ContactLocationCellRenderer extends JLabel implements ListCellRenderer<Contact>{

    public ContactLocationCellRenderer(){
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Contact> list, Contact value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(getDisplayValue(value));

        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            background = Color.BLUE;
            foreground = Color.WHITE;

         // check if this cell is selected
        } else if (isSelected) {
            background = Color.BLUE;
            foreground = Color.WHITE;

         // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
         };

        setBackground(background);
        setForeground(foreground);
        setBorder(BorderFactory.createEmptyBorder(1, 5, 1, 2));
        
        return this;
    }

    
    private String getDisplayValue(Contact contact){
        String display = "";
        if(contact != null){            
            if(!contact.getLocationMapCollection().isEmpty()){
                LocationMap locationMap;
                locationMap = contact.getLocationMapCollection().iterator().next();
                display = display.concat(locationMap.getMarkerLabel()).concat(" - ").concat(contact.toString());
            }
        }
        
        return display;
    }
}
