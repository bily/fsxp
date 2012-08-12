// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoryPopupMenu.java

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.engine.FontEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

// Referenced classes of package com.zbluesoftware.fsxp.menu:
//            MenuFactory

public class HistoryPopupMenu extends JPopupMenu
    implements PropertyChangeListener
{

    private HistoryPopupMenu()
    {
        super("History");
        detailsMI = add(MenuFactory.getInstance().getMenuAction(new Integer(119)));
        detailsMI.setFont(Utilities.MENU_FONT);
        detailsMI.setForeground(Color.black);
        undoMI = add(MenuFactory.getInstance().getMenuAction(new Integer(120)));
        undoMI.setFont(Utilities.MENU_FONT);
        undoMI.setForeground(Color.black);
        FontEngine.getInstance().addPropertyChangeListener(this);
    }

    public static HistoryPopupMenu getInstance()
    {
        if(popupMenu == null)
            popupMenu = new HistoryPopupMenu();
        return popupMenu;
    }

    private void updateFonts(Font font)
    {
        detailsMI.setFont(font);
        undoMI.setFont(font);
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == FontEngine.getInstance() && event.getPropertyName().equals("menuFont"))
            updateFonts((Font)event.getNewValue());
    }

    private JMenuItem detailsMI;
    private JMenuItem undoMI;
    private static HistoryPopupMenu popupMenu = null;

}
