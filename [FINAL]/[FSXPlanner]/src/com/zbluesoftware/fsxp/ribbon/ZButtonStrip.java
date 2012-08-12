// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZButtonStrip.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPButtonStripUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZButtonStripUI;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;

public class ZButtonStrip extends JComponent
{

    public enum StripOrientation
    {
                HORIZONTAL, VERTICAL
	}

    public ZButtonStrip()
    {
        this(StripOrientation.HORIZONTAL);
    }

    public ZButtonStrip(StripOrientation orientation)
    {
        this.orientation = orientation;
        updateUI();
    }

    public void add(Component comp, Object constraints, int index)
    {
        throw new UnsupportedOperationException();
    }

    public void add(Component comp, Object constraints)
    {
        throw new UnsupportedOperationException();
    }

    public Component add(Component comp, int index)
    {
        if(!(comp instanceof AbstractButton))
            throw new UnsupportedOperationException();
        else
            return super.add(comp, index);
    }

    public Component add(Component comp)
    {
        if(!(comp instanceof AbstractButton))
            throw new UnsupportedOperationException();
        else
            return super.add(comp);
    }

    public Component add(String name, Component comp)
    {
        throw new UnsupportedOperationException();
    }

    public void setUI(ZButtonStripUI ui)
    {
        super.setUI(ui);
    }

    public void updateUI()
    {
        if(UIManager.get(getUIClassID()) != null)
            setUI((ZButtonStripUI)UIManager.getUI(this));
        else
            setUI(new FSXPButtonStripUI());
    }

    public ZButtonStripUI getUI()
    {
        return (ZButtonStripUI)ui;
    }

    public String getUIClassID()
    {
        return "ZButtonStripUI";
    }

    public int getButtonCount()
    {
        return getComponentCount();
    }

    public AbstractButton getButton(int index)
    {
        return (AbstractButton)getComponent(index);
    }

    public boolean isFirst(AbstractButton button)
    {
        return button == getButton(0);
    }

    public boolean isLast(AbstractButton button)
    {
        return button == getButton(getButtonCount() - 1);
    }

    protected void paintBorder(Graphics g1)
    {
    }

    protected void paintChildren(Graphics g1)
    {
    }

    public StripOrientation getOrientation()
    {
        return orientation;
    }

    private StripOrientation orientation;
    private static final String uiClassID = "ZButtonStripUI";
}