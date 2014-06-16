/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.tree.cell.renderer;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jw.service.entity.Contact;
import org.jw.service.entity.ContactStatus;
import org.jw.service.entity.ServiceGroup;
import org.jw.service.entity.Territory;
import org.jw.service.util.UtilityImageIcon;

/**
 *
 * @author Wilson
 */
public class AppsTreeCellRenderer extends DefaultTreeCellRenderer{
    private final UtilityImageIcon utilImageIcon;
    
    public static AppsTreeCellRenderer create() {
        return new AppsTreeCellRenderer();
    }
 
    private AppsTreeCellRenderer(){
        utilImageIcon = UtilityImageIcon.create();
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        if (tree.getModel().getRoot() != value) {
            DefaultMutableTreeNode leafNode = (DefaultMutableTreeNode) value;
            if(leafNode.getUserObject() instanceof Contact){
                Contact contact = (Contact) leafNode.getUserObject();
                ContactStatus status = (ContactStatus) contact.getStatusId();
                if(status != null && status.getIcon() != null){
                    this.setIcon(getContactNodeIcon(status));
                }
            }else if(leafNode.getUserObject() instanceof ServiceGroup){
                setIcon(getServiceGroupNodeIcon());
            }else if(leafNode.getUserObject() instanceof Territory){
                setIcon(getTerritoryNodeIcon());
            }
        } else {
            setIcon(getRootNodeIcon());
        }
        
        return this;
    }
    
    private ImageIcon getContactNodeIcon(ContactStatus status){
        return utilImageIcon.scaleImage(new ImageIcon(status.getIcon()), 1);
    }
    
    private ImageIcon getServiceGroupNodeIcon(){
        return utilImageIcon.scaleImage(utilImageIcon.getIcon("default.service.groups.png"),1);
    }
    
    private ImageIcon getRootNodeIcon(){
        return utilImageIcon.getIcon("default.file.cabinet.png");
    }
    
    private ImageIcon getTerritoryNodeIcon(){
        return utilImageIcon.getIcon("default.territory.node.icon.png");
    }
    
}
