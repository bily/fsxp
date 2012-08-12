// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZRibbonGallery.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPRibbonGalleryUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZRibbonGalleryUI;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.plaf.UIResource;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ZRibbonButton, GalleryElementState, ZPopupGallery

public class ZRibbonGallery extends JComponent
{

    public ZRibbonGallery()
    {
        buttonAL = new ArrayList();
        buttonGroup = new ButtonGroup();
        preferredWidthHM = new HashMap();
        GalleryElementState arr$[] = GalleryElementState.values();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            GalleryElementState state = arr$[i$];
            preferredWidthHM.put(state, Integer.valueOf(100));
        }

        invisible = new JToggleButton();
        invisible.setVisible(false);
        buttonGroup.add(invisible);
        updateUI();
    }

    public void setUI(ZRibbonGalleryUI ui)
    {
        super.setUI(ui);
    }

    public void updateUI()
    {
        if(UIManager.get(getUIClassID()) != null)
            setUI((ZRibbonGalleryUI)UIManager.getUI(this));
        else
            setUI(new FSXPRibbonGalleryUI());
        if(popupGallery != null)
            SwingUtilities.updateComponentTreeUI(popupGallery);
    }

    public ZRibbonGalleryUI getUI()
    {
        return (ZRibbonGalleryUI)ui;
    }

    public String getUIClassID()
    {
        return "ZRibbonGalleryUI";
    }

    public void addGalleryButton(ZRibbonButton button)
    {
        buttonAL.add(button);
        button.setToggle(true);
        buttonGroup.add(button);
        button.setState(GalleryElementState.BIG, true);
        super.add(button);
    }

    public Component add(Component comp, int index)
    {
        if(comp instanceof UIResource)
            return super.add(comp, index);
        else
            throw new UnsupportedOperationException("Use addGalleryButton()");
    }

    public void add(Component comp, Object constraints, int index)
    {
        if(comp instanceof UIResource)
        {
            super.add(comp, constraints, index);
            return;
        } else
        {
            throw new UnsupportedOperationException("Use addGalleryButton()");
        }
    }

    public void add(Component comp, Object constraints)
    {
        if(comp instanceof UIResource)
        {
            super.add(comp, constraints);
            return;
        } else
        {
            throw new UnsupportedOperationException("Use addGalleryButton()");
        }
    }

    public Component add(Component comp)
    {
        if(comp instanceof UIResource)
            return super.add(comp);
        else
            throw new UnsupportedOperationException("Use addGalleryButton()");
    }

    public Component add(String name, Component comp)
    {
        if(comp instanceof UIResource)
            return super.add(name, comp);
        else
            throw new UnsupportedOperationException("Use addGalleryButton()");
    }

    public void remove(Component comp)
    {
        if(comp instanceof UIResource)
        {
            super.remove(comp);
            return;
        } else
        {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    public void setPreferredWidth(GalleryElementState state, int width)
    {
        preferredWidthHM.put(state, Integer.valueOf(width));
    }

    public int getPreferredWidth(GalleryElementState state)
    {
        return ((Integer)preferredWidthHM.get(state)).intValue();
    }

    public void setState(GalleryElementState state)
    {
        this.state = state;
    }

    public GalleryElementState getState()
    {
        return state;
    }

    public int getButtonCount()
    {
        return buttonAL.size();
    }

    public ZRibbonButton getButtonAt(int index)
    {
        return (ZRibbonButton)buttonAL.get(index);
    }

    public ZRibbonButton getSelectedButton()
    {
        return selectedButton;
    }

    public void setSelectedButton(ZRibbonButton selectedButton)
    {
        if(selectedButton != null)
            selectedButton.setSelected(true);
        else
            invisible.setSelected(true);
        this.selectedButton = selectedButton;
        repaint();
    }

    public ZPopupGallery getPopupGallery()
    {
        return popupGallery;
    }

    public void setPopupGallery(ZPopupGallery popupGallery)
    {
        this.popupGallery = popupGallery;
    }

    protected ArrayList buttonAL;
    protected ButtonGroup buttonGroup;
    protected GalleryElementState state;
    protected HashMap preferredWidthHM;
    protected ZRibbonButton selectedButton;
    protected ZPopupGallery popupGallery;
    protected JToggleButton invisible;
    private static final String uiClassID = "ZRibbonGalleryUI";
}