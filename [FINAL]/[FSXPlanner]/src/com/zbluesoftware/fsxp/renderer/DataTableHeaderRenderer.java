// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataTableHeaderRenderer.java

package com.zbluesoftware.fsxp.renderer;

import java.awt.*;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class DataTableHeaderRenderer extends DefaultTableCellRenderer
{

    public DataTableHeaderRenderer()
    {
        setFont(new Font("Arial", 1, 11));
        setForeground(Color.black);
        setBackground(new Color(152, 152, 152));
        setHorizontalAlignment(0);
        setBorder(new BevelBorder(0, getBackground().brighter(), getBackground(), getBackground().darker(), getBackground()));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if(value != null)
            setText(value.toString());
        else
            setText("");
        return this;
    }
}
