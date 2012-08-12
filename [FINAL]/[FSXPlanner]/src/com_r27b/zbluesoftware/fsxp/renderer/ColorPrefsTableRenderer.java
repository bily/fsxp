// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorPrefsTableRenderer.java

package com.zbluesoftware.fsxp.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorPrefsTableRenderer extends DefaultTableCellRenderer
{

    public ColorPrefsTableRenderer()
    {
        setOpaque(true);
        setText("");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if(value != null)
        {
            if(value instanceof Color)
                setBackground((Color)value);
            else
                setBackground(Color.white);
        } else
        {
            setBackground(Color.white);
        }
        return this;
    }
}
