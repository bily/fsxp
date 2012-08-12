// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuAction.java

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.event.MenuActionEvent;
import com.zbluesoftware.fsxp.event.MenuActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public class MenuAction extends AbstractAction
{

    public MenuAction(String text, Icon icon)
    {
        super(text, icon);
        listeners = new Vector();
    }

    public synchronized void addMenuListener(MenuActionListener listener)
    {
        if(!listeners.contains(listener))
            listeners.addElement(listener);
    }

    public synchronized void removeMenuListener(MenuActionListener listener)
    {
        listeners.removeElement(listener);
    }

    public void actionPerformed(ActionEvent event)
    {
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        MenuActionEvent menuActionEvent = new MenuActionEvent(this, getValue("ActionCommandKey"));
        for(int i = list.size() - 1; i >= 0; i--)
            ((MenuActionListener)list.elementAt(i)).menuItemSelected(menuActionEvent);

    }

    private Vector listeners;
}
