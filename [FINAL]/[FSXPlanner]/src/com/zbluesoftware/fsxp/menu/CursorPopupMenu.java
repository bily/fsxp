// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CursorPopupMenu.java

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.engine.FontEngine;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

// Referenced classes of package com.zbluesoftware.fsxp.menu:
//            MenuFactory

public class CursorPopupMenu extends JPopupMenu
    implements PropertyChangeListener
{

    private CursorPopupMenu()
    {
        super("Cursor");
        cutMI = add(MenuFactory.getInstance().getMenuAction(new Integer(21)));
        cutMI.setFont(Utilities.MENU_FONT);
        cutMI.setForeground(Color.black);
        copyMI = add(MenuFactory.getInstance().getMenuAction(new Integer(22)));
        copyMI.setFont(Utilities.MENU_FONT);
        copyMI.setForeground(Color.black);
        pasteMI = add(MenuFactory.getInstance().getMenuAction(new Integer(23)));
        pasteMI.setFont(Utilities.MENU_FONT);
        pasteMI.setForeground(Color.black);
        addSeparator();
        moveToPlaneMI = add(MenuFactory.getInstance().getMenuAction(new Integer(75)));
        moveToPlaneMI.setFont(Utilities.MENU_FONT);
        moveToPlaneMI.setForeground(Color.black);
        moveFSXToIconMI = add(MenuFactory.getInstance().getMenuAction(new Integer(76)));
        moveFSXToIconMI.setFont(Utilities.MENU_FONT);
        moveFSXToIconMI.setForeground(Color.black);
        movePlaneMI = add(MenuFactory.getInstance().getMenuAction(new Integer(77)));
        movePlaneMI.setFont(Utilities.MENU_FONT);
        movePlaneMI.setForeground(Color.black);
        showPlaneMI = add(MenuFactory.getInstance().getMenuAction(new Integer(78)));
        showPlaneMI.setFont(Utilities.MENU_FONT);
        showPlaneMI.setForeground(Color.black);
        addSeparator();
        breakFenceMI = add(MenuFactory.getInstance().getMenuAction(new Integer(188)));
        breakFenceMI.setFont(Utilities.MENU_FONT);
        breakFenceMI.setForeground(Color.black);
        primaryILSMI = add(MenuFactory.getInstance().getMenuAction(new Integer(189)));
        primaryILSMI.setFont(Utilities.MENU_FONT);
        primaryILSMI.setForeground(Color.black);
        secondaryILSMI = add(MenuFactory.getInstance().getMenuAction(new Integer(190)));
        secondaryILSMI.setFont(Utilities.MENU_FONT);
        secondaryILSMI.setForeground(Color.black);
        triggerMI = add(MenuFactory.getInstance().getMenuAction(new Integer(191)));
        triggerMI.setFont(Utilities.MENU_FONT);
        triggerMI.setForeground(Color.black);
        stationMI = add(MenuFactory.getInstance().getMenuAction(new Integer(192)));
        stationMI.setFont(Utilities.MENU_FONT);
        stationMI.setForeground(Color.black);
        localizerMI = add(MenuFactory.getInstance().getMenuAction(new Integer(193)));
        localizerMI.setFont(Utilities.MENU_FONT);
        localizerMI.setForeground(Color.black);
        vorStationMI = add(MenuFactory.getInstance().getMenuAction(new Integer(194)));
        vorStationMI.setFont(Utilities.MENU_FONT);
        vorStationMI.setForeground(Color.black);
        ndbAntennaMI = add(MenuFactory.getInstance().getMenuAction(new Integer(195)));
        ndbAntennaMI.setFont(Utilities.MENU_FONT);
        ndbAntennaMI.setForeground(Color.black);
        FontEngine.getInstance().addPropertyChangeListener(this);
    }

    public static CursorPopupMenu getInstance()
    {
        if(popupMenu == null)
            popupMenu = new CursorPopupMenu();
        return popupMenu;
    }

    public void updateMenu(boolean vertexModel)
    {
        for(int i = getComponentCount() - 1; i >= 8; i--)
            remove(i);

        if(vertexModel && ((SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel) || (SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel) || (SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel)))
        {
            addSeparator();
            add(breakFenceMI);
        }
        if(SelectedItem.getInstance().getBaseModel() instanceof RunwayModel)
        {
            addSeparator();
            add(primaryILSMI);
            add(secondaryILSMI);
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof TaxiwayParkingModel)
        {
            addSeparator();
            add(triggerMI);
            add(stationMI);
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof ILSModel)
        {
            addSeparator();
            add(localizerMI);
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof VORModel)
        {
            addSeparator();
            add(vorStationMI);
        } else
        if(SelectedItem.getInstance().getBaseModel() instanceof NDBModel)
        {
            addSeparator();
            add(ndbAntennaMI);
        }
    }

    private void updateFonts(Font font)
    {
        cutMI.setFont(font);
        copyMI.setFont(font);
        pasteMI.setFont(font);
        breakFenceMI.setFont(font);
        primaryILSMI.setFont(font);
        secondaryILSMI.setFont(font);
        triggerMI.setFont(font);
        stationMI.setFont(font);
        localizerMI.setFont(font);
        vorStationMI.setFont(font);
        ndbAntennaMI.setFont(font);
        moveToPlaneMI.setFont(font);
        moveFSXToIconMI.setFont(font);
        movePlaneMI.setFont(font);
        showPlaneMI.setFont(font);
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == FontEngine.getInstance() && event.getPropertyName().equals("menuFont"))
            updateFonts((Font)event.getNewValue());
    }

    private JMenuItem cutMI;
    private JMenuItem copyMI;
    private JMenuItem pasteMI;
    private JMenuItem breakFenceMI;
    private JMenuItem primaryILSMI;
    private JMenuItem secondaryILSMI;
    private JMenuItem triggerMI;
    private JMenuItem stationMI;
    private JMenuItem localizerMI;
    private JMenuItem vorStationMI;
    private JMenuItem ndbAntennaMI;
    private JMenuItem moveToPlaneMI;
    private JMenuItem moveFSXToIconMI;
    private JMenuItem movePlaneMI;
    private JMenuItem showPlaneMI;
    private static CursorPopupMenu popupMenu = null;

}
