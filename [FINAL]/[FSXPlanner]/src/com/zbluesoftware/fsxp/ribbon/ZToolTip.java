// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZToolTip.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPToolTipUI;
import java.awt.Graphics;
import javax.swing.JToolTip;

public class ZToolTip extends JToolTip
{

    public ZToolTip()
    {
        setOpaque(false);
    }

    public void updateUI()
    {
        setUI(new FSXPToolTipUI());
    }

    public void paintBorder(Graphics g1)
    {
    }
}