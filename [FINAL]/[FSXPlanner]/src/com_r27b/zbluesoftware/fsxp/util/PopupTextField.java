// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PopupTextField.java

package com.zbluesoftware.fsxp.util;

import com.zbluesoftware.fsxp.menu.TextPopupMenu;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupTextField extends JTextField
    implements MouseListener, PopupMenuListener
{

    public PopupTextField(int columns)
    {
        this("", columns);
    }

    public PopupTextField(String text)
    {
        this(text, 0);
    }

    public PopupTextField(String text, int columns)
    {
        super(text, columns);
        popupDisplayed = false;
        addMouseListener(this);
    }

    private void displayPopupMenu(MouseEvent event)
    {
        popupDisplayed = true;
        TextPopupMenu.getInstance().setPopupListener(this);
        TextPopupMenu.getInstance().setTextComponent(this);
        TextPopupMenu.getInstance().show(this, event.getX(), event.getY());
    }

    public boolean isPopupDisplayed()
    {
        return popupDisplayed;
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent event)
    {
        if(event.getSource() == this && event.isPopupTrigger())
            displayPopupMenu(event);
    }

    public void mouseReleased(MouseEvent event)
    {
        if(event.getSource() == this && event.isPopupTrigger())
            displayPopupMenu(event);
    }

    public void popupMenuCanceled(PopupMenuEvent event)
    {
        if(event.getSource() == TextPopupMenu.getInstance())
            popupDisplayed = false;
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent event)
    {
        if(event.getSource() == TextPopupMenu.getInstance())
            popupDisplayed = false;
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent popupmenuevent)
    {
    }

    private boolean popupDisplayed;
}
