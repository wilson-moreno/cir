/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jw.service.util;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jw.service.entity.Contact;
import org.jw.service.gui.AppsReportTemplateDialog;
import org.jw.service.gui.CallStatusDialog;
import org.jw.service.gui.CongregationDialog;
import org.jw.service.gui.ContactCallsDialog;
import org.jw.service.gui.ContactSearchDialog;
import org.jw.service.gui.ContactStatusDialog;
import org.jw.service.gui.DirectionMapDialog;
import org.jw.service.gui.LocationMapDialog;
import org.jw.service.gui.MeetingPlaceDialog;
import org.jw.service.gui.ProximityMapDialog;
import org.jw.service.gui.ReportPrintDialog;
import org.jw.service.gui.ServiceGroupDialog;
import org.jw.service.gui.StatisticsDialog;
import org.jw.service.gui.TerritoryDialog;
import org.jw.service.listener.combobox.DefaultComboBoxModelListListener;

/**
 *
 * @author Wilson
 */
public class UtilityDialog {
    public static final String APPS_REPORT_TEMPLATE = "apps.report.template";
    public static final String CONGREGATION = "congregation";
    public static final String CONTACT_CALLS = "contact";
    public static final String CONTACT_STATUS = "contact.status";
    public static final String DIRECTION_MAP = "direction.map";
    public static final String LOCATION_MAP = "location.map";
    public static final String MEETING_PLACE = "meeting.place";
    public static final String PROXIMITY_MAP = "proximity.map";
    public static final String REPORT_PRINT = "report.print";
    public static final String SERVICE_GROUP = "service.group";
    public static final String TERRITORY = "territory";
    public static final String CONTACT_SEARCH =  "contact.search";
    public static final String APPS_REPORT_PARAMETERS = "apps.report.parameters";
    public static final String STATISTICS = "statistics";
    public static final String CALL_STATUS = "call.status";

    public static UtilityDialog create(JFrame parent, boolean modal, EntityManager em, UtilityTree utilTree, UtilityTable utilTable, UtilityReportPrint utilPrint, List<Contact> contactList) {
        return new UtilityDialog(parent, modal, em, utilTree, utilTable, utilPrint, contactList);
    }
    private final JFrame parent;
    private final boolean modal;
    private final EntityManager em;
    private final UtilityTree utilTree;
    private final UtilityTable utilTable;
    private final UtilityReportPrint utilPrint;
    private final Map<String, DefaultComboBoxModelListListener> comboBoxModelListListener;
    private final List<Contact> contactList;
    
    private UtilityDialog(JFrame parent,
                         boolean modal,
                         EntityManager em,
                         UtilityTree utilTree,
                         UtilityTable utilTable,
                         UtilityReportPrint utilPrint,
                         List<Contact> contactList){
        this.parent = parent;
        this.modal = modal;
        this.em = em;
        this.utilTree = utilTree;
        this.utilTable = utilTable;
        this.utilPrint = utilPrint;
        this.comboBoxModelListListener = new HashMap<>();
        this.contactList = contactList;
    }
    
    public void addComboBoxModelListListener(String name, DefaultComboBoxModelListListener listener){
        this.comboBoxModelListListener.put(name, listener);
    }
    
    public JDialog createDialog(String dialogName){
        JDialog dialog = null;
        
        switch(dialogName){
            case UtilityDialog.APPS_REPORT_TEMPLATE :
                    dialog = new AppsReportTemplateDialog(parent, true, em); break;
            case UtilityDialog.CONGREGATION :
                    dialog = new CongregationDialog(parent, true, em); break;
            case UtilityDialog.CONTACT_CALLS :
                    dialog = new ContactCallsDialog(parent, true, em, utilTable); break;
            case UtilityDialog.CONTACT_STATUS :
                    dialog = new ContactStatusDialog(parent, true, em, this.comboBoxModelListListener.get("status.model.list.listener")); break;
            case UtilityDialog.DIRECTION_MAP :
                    dialog = new DirectionMapDialog(parent, true); break;
            case UtilityDialog.LOCATION_MAP :
                    dialog = new LocationMapDialog(parent, true, em, utilTable); break;
            case UtilityDialog.MEETING_PLACE :
                    dialog = new MeetingPlaceDialog(parent, true, em); break;
            case UtilityDialog.PROXIMITY_MAP :
                    dialog = new ProximityMapDialog(parent, true, em); break;
            case UtilityDialog.REPORT_PRINT :
                    dialog = new ReportPrintDialog(parent, true, em, utilPrint); break;
            case UtilityDialog.SERVICE_GROUP :
                    dialog = new ServiceGroupDialog(parent, true, em, comboBoxModelListListener.get("service.group.model.list.listener"), utilTree);break;
            case UtilityDialog.TERRITORY :
                    dialog = new TerritoryDialog(parent, true, em, comboBoxModelListListener.get("territory.model.list.listener"));break;        
            case UtilityDialog.CONTACT_SEARCH :
                    dialog = new ContactSearchDialog(parent, true, contactList, utilTable); break;            
            case UtilityDialog.STATISTICS :
                    dialog = new StatisticsDialog(parent, true, em); 
                    dialog.pack();
                    break;
            case UtilityDialog.CALL_STATUS :
                    dialog = new CallStatusDialog(parent, true, em);
                    dialog.pack();                    
                    break;
        }
        
        if(dialog != null){
            dialog.setLocationRelativeTo(parent); 
            dialog.setModal(modal);
        }
        
        return dialog;
    }
}
