// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FSXPMenuBar.java

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.engine.FontEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;

// Referenced classes of package com.zbluesoftware.fsxp.menu:
//            MenuAction

public class FSXPMenuBar extends JMenuBar
    implements PropertyChangeListener
{

    private FSXPMenuBar()
    {
        menus = new HashMap();
        createMenus();
    }

    public static FSXPMenuBar getInstance()
    {
        if(fsxpMenuBar == null)
            fsxpMenuBar = new FSXPMenuBar();
        return fsxpMenuBar;
    }

    private void createMenus()
    {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(Utilities.MENU_FONT);
        fileMenu.setMnemonic('F');
        if(SettingsEngine.getInstance().getRibbonMenus())
            fileMenu.setBackground(new Color(191, 219, 255));
        add(fileMenu);
        menus.put("File", fileMenu);
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(Utilities.MENU_FONT);
        editMenu.setMnemonic('E');
        menus.put("Edit", editMenu);
        JMenu displayMenu = new JMenu("Display");
        displayMenu.setFont(Utilities.MENU_FONT);
        displayMenu.setMnemonic('D');
        menus.put("Display", displayMenu);
        JMenu navigationMenu = new JMenu("Navigation");
        navigationMenu.setFont(Utilities.MENU_FONT);
        navigationMenu.setMnemonic('N');
        menus.put("Navigation", navigationMenu);
        JMenu simConnectMenu = new JMenu("SimConnect");
        simConnectMenu.setFont(Utilities.MENU_FONT);
        simConnectMenu.setMnemonic('S');
        menus.put("SimConnect", simConnectMenu);
        JMenu compileMenu = new JMenu("Compile");
        compileMenu.setFont(Utilities.MENU_FONT);
        compileMenu.setMnemonic('C');
        menus.put("Compile", compileMenu);
        JMenu viewMenu = new JMenu("View");
        viewMenu.setFont(Utilities.MENU_FONT);
        viewMenu.setMnemonic('V');
        menus.put("View", viewMenu);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(Utilities.MENU_FONT);
        helpMenu.setMnemonic('H');
        menus.put("Help", helpMenu);
        if(!SettingsEngine.getInstance().getRibbonMenus())
        {
            add(editMenu);
            add(displayMenu);
            add(navigationMenu);
            add(simConnectMenu);
            add(compileMenu);
            add(viewMenu);
            add(helpMenu);
        }
        FontEngine.getInstance().addPropertyChangeListener(this);
    }

    public void recreateMenuBar()
    {
        int menus = getMenuCount();
        for(int i = menus - 1; i >= 0; i--)
            remove(getMenu(i));

        createMenus();
    }

    public boolean addMenuItem(String menu, MenuAction menuAction)
    {
        return addMenuItem(menu, menuAction, true);
    }

    public boolean addMenuItem(String menu, MenuAction menuAction, boolean visible)
    {
        if(menus.get(menu) == null)
        {
            return false;
        } else
        {
            JMenuItem jMenuItem = ((JMenu)menus.get(menu)).add(menuAction);
            jMenuItem.setFont(Utilities.MENU_FONT);
            jMenuItem.setVisible(visible);
            return true;
        }
    }

    public boolean addCheckBoxMenuItem(String menu, MenuAction menuAction, boolean selected)
    {
        if(menus.get(menu) == null)
        {
            return false;
        } else
        {
            JCheckBoxMenuItem jMenuItem = new JCheckBoxMenuItem(menuAction);
            jMenuItem.setFont(Utilities.MENU_FONT);
            jMenuItem.setState(selected);
            ((JMenu)menus.get(menu)).add(jMenuItem);
            return true;
        }
    }

    public boolean addMenuItem(String menu, JMenu subMenu)
    {
        if(menus.get(menu) == null)
        {
            return false;
        } else
        {
            JMenuItem jMenuItem = ((JMenu)menus.get(menu)).add(subMenu);
            jMenuItem.setFont(Utilities.MENU_FONT);
            return true;
        }
    }

    public boolean addMenuSeparator(String menu)
    {
        if(menus.get(menu) == null)
        {
            return false;
        } else
        {
            ((JMenu)menus.get(menu)).addSeparator();
            return true;
        }
    }

    public JMenu getMenu(String menu)
    {
        return (JMenu)menus.get(menu);
    }

    private void updateFonts(Font font)
    {
        JMenu menu;
        for(Iterator iterator = menus.keySet().iterator(); iterator.hasNext(); updateSubMenuFonts(menu, font))
        {
            String key = (String)iterator.next();
            menu = (JMenu)menus.get(key);
            menu.setFont(font);
        }

    }

    private void updateSubMenuFonts(JMenu menu, Font font)
    {
        int count = menu.getItemCount();
        for(int i = 0; i < count; i++)
        {
            JMenuItem menuItem = menu.getItem(i);
            if(menuItem == null)
                continue;
            menuItem.setFont(font);
            if(menuItem instanceof JMenu)
                updateSubMenuFonts((JMenu)menuItem, font);
        }

    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == FontEngine.getInstance() && event.getPropertyName().equals("menuFont"))
            updateFonts((Font)event.getNewValue());
    }

    private HashMap menus;
    private static FSXPMenuBar fsxpMenuBar = null;

}
