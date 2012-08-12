// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZPopupGallery.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPPopupGalleryUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZPopupGalleryUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.PanelUI;

public class ZPopupGallery extends JPanel
{

    public ZPopupGallery()
    {
    }

    public ZPopupGallery(Component component, Dimension originalSize)
    {
        this(component, originalSize, null);
    }

    public ZPopupGallery(Component component, Dimension originalSize, ActionListener listener)
    {
        this();
        this.component = component;
        this.listener = listener;
        setLayout(new BorderLayout());
        add(component, "Center");
        setPreferredSize(originalSize);
        setSize(originalSize);
    }

    public void setActionListener(ActionListener listener)
    {
        this.listener = listener;
    }

    public ActionListener getActionListener()
    {
        return listener;
    }

    public void updateUI()
    {
        if(UIManager.get(getUIClassID()) != null)
            setUI((ZPopupGalleryUI)UIManager.getUI(this));
        else
            setUI(new FSXPPopupGalleryUI());
    }

    public ZPopupGalleryUI getUI()
    {
        return (ZPopupGalleryUI)ui;
    }

    public void setUI(ZPopupGalleryUI ui)
    {
        super.setUI(ui);
    }

    public String getUIClassID()
    {
        return "ZPopupGalleryUI";
    }

    public Component removeComponent()
    {
        remove(component);
        return component;
    }

    public Component getComponent()
    {
        return component;
    }

    public boolean isEmpty()
    {
        return component == null;
    }

    public static void hidePopups(Component comp)
    {
        if(comp != null)
        {
label0:
            for(Component c = comp; c != null; c = c.getParent())
            {
                if(!(c instanceof ZPopupGallery))
                    continue;
                do
                {
                    if(currShownList.size() <= 0)
                        continue label0;
                    if(currShownList.getLast() == c)
                        return;
                    ZPopupGallery jpg = (ZPopupGallery)currShownList.removeLast();
                    Popup popup = (Popup)popupGalleryHM.get(jpg);
                    popup.hide();
                    popupGalleryHM.remove(jpg);
                } while(true);
            }

        }
        Iterator iterator = popupGalleryHM.keySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ZPopupGallery gallery = (ZPopupGallery)iterator.next();
            ((Popup)popupGalleryHM.get(gallery)).hide();
            if(gallery.getActionListener() != null)
                gallery.getActionListener().actionPerformed(new ActionEvent(gallery, 1, "Hidden"));
        } while(true);
        popupGalleryHM.clear();
    }

    public static void addPopup(Popup popup, ZPopupGallery popupInitiator)
    {
        popupGalleryHM.put(popupInitiator, popup);
        currShownList.addLast(popupInitiator);
    }

    protected Component component;
    protected ActionListener listener;
    protected static HashMap popupGalleryHM = new HashMap();
    protected static LinkedList currShownList = new LinkedList();
    private static final String uiClassID = "ZPopupGalleryUI";
}