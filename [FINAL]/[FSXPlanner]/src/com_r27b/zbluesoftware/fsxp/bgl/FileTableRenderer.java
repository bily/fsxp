// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:27:20 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FileTableRenderer.java

package com.zbluesoftware.fsxp.bgl;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

// Referenced classes of package com.zbluesoftware.fsxp.bgl:
//            FileTableModel

public class FileTableRenderer extends DefaultTableCellRenderer
{

    public FileTableRenderer()
    {
        setOpaque(true);
        setText("");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if(value == null)
            setText("");
        else
        if(value instanceof String)
            setText((String)value);
        else
            setText("");
        int cell = row * 16 + (column - 2);
        if(hasFocus)
        {
            setBackground(Color.black);
            setForeground(Color.white);
        } else
        {
            if(column >= 2 && cell >= ((FileTableModel)table.getModel()).getItemStart() && cell < ((FileTableModel)table.getModel()).getItemEnd())
                setBackground(Color.green);
            else
                setBackground(Color.white);
            if(column >= 2 && cell >= ((FileTableModel)table.getModel()).getObjectOffset() && cell < ((FileTableModel)table.getModel()).getObjectEnd())
                setForeground(Color.blue);
            else
                setForeground(Color.black);
        }
        return this;
    }
}