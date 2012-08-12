// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TextPopupMenu.java

package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.engine.FontEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.text.JTextComponent;

public class TextPopupMenu extends JPopupMenu
    implements ActionListener, PopupMenuListener, PropertyChangeListener
{

    private TextPopupMenu()
    {
        cutMI = new JMenuItem("Cut");
        cutMI.setFont(Utilities.MENU_FONT);
        cutMI.setForeground(Color.black);
        cutMI.addActionListener(this);
        add(cutMI);
        copyMI = new JMenuItem("Copy");
        copyMI.setFont(Utilities.MENU_FONT);
        copyMI.setForeground(Color.black);
        copyMI.addActionListener(this);
        add(copyMI);
        pasteMI = new JMenuItem("Paste");
        pasteMI.setFont(Utilities.MENU_FONT);
        pasteMI.setForeground(Color.black);
        pasteMI.addActionListener(this);
        add(pasteMI);
        selectAllMI = new JMenuItem("Select All");
        selectAllMI.setFont(Utilities.MENU_FONT);
        selectAllMI.setForeground(Color.black);
        selectAllMI.addActionListener(this);
        add(selectAllMI);
        popupListener = null;
        addPopupMenuListener(this);
        FontEngine.getInstance().addPropertyChangeListener(this);
    }

    public static TextPopupMenu getInstance()
    {
        if(popupMenu == null)
            popupMenu = new TextPopupMenu();
        return popupMenu;
    }

    public void setTextComponent(JTextComponent textComponent)
    {
        this.textComponent = textComponent;
    }

    public void setPopupListener(PopupMenuListener popupListener)
    {
        this.popupListener = popupListener;
    }

    private void updateFonts(Font font)
    {
        cutMI.setFont(font);
        copyMI.setFont(font);
        pasteMI.setFont(font);
        selectAllMI.setFont(font);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == cutMI)
        {
            if(textComponent != null)
                textComponent.cut();
        } else
        if(event.getSource() == copyMI)
        {
            if(textComponent != null)
                textComponent.copy();
        } else
        if(event.getSource() == pasteMI)
        {
            if(textComponent != null)
                textComponent.paste();
        } else
        if(event.getSource() == selectAllMI && textComponent != null)
            textComponent.selectAll();
    }

    public void popupMenuCanceled(PopupMenuEvent event)
    {
        if(popupListener != null)
            popupListener.popupMenuCanceled(event);
    }

    public void popupMenuWillBecomeInvisible(PopupMenuEvent event)
    {
        if(popupListener != null)
            popupListener.popupMenuWillBecomeInvisible(event);
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent popupmenuevent)
    {
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == FontEngine.getInstance() && event.getPropertyName().equals("menuFont"))
            updateFonts((Font)event.getNewValue());
    }

    private JMenuItem cutMI;
    private JMenuItem copyMI;
    private JMenuItem pasteMI;
    private JMenuItem selectAllMI;
    private JTextComponent textComponent;
    private PopupMenuListener popupListener;
    private static TextPopupMenu popupMenu = null;

}
