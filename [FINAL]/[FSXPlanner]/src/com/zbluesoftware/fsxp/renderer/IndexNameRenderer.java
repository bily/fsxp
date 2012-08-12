// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexNameRenderer.java

package com.zbluesoftware.fsxp.renderer;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.util.HashMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class IndexNameRenderer extends DefaultListCellRenderer
{

    public IndexNameRenderer()
    {
        this(0x7fffffff);
    }

    public IndexNameRenderer(int maxLength)
    {
        this.maxLength = maxLength;
        setOpaque(true);
        setFont(Utilities.LABEL_FONT);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
        String text = "";
        if(value instanceof HashMap)
            text = (String)((HashMap)value).get("name");
        else
        if(value instanceof String)
            text = (String)value;
        else
        if(value == null)
            text = "";
        else
            text = value.toString();
        if(text.length() > maxLength)
            text = text.substring(0, maxLength);
        setText(text);
        if(!list.isEnabled())
        {
            setBackground(Color.white);
            setForeground(SystemColor.textInactiveText);
        } else
        if(isSelected)
        {
            setBackground(SystemColor.textHighlight);
            setForeground(SystemColor.textHighlightText);
        } else
        {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }

    private int maxLength;
}
