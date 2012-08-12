// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachLegTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.LegModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ApproachLegTableModel extends AbstractTableModel
{

    public ApproachLegTableModel()
    {
        data = new ArrayList();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return data.size();
    }

    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public Class getColumnClass(int column)
    {
        return columnClasses[column];
    }

    public Object getValueAt(int row, int column)
    {
        if(row < 0 || row >= data.size())
            return "";
        switch(column)
        {
        case 0: // '\0'
            return ((LegModel)data.get(row)).getType();

        case 1: // '\001'
            return ((LegModel)data.get(row)).getFixType();

        case 2: // '\002'
            return ((LegModel)data.get(row)).getFixIdent();

        case 3: // '\003'
            return ((LegModel)data.get(row)).getFixRegion();

        case 4: // '\004'
            return ((LegModel)data.get(row)).getTurnDirection();

        case 5: // '\005'
            return IconFactory.getInstance().getIcon("delete");
        }
        return "";
    }

    public void setData(ArrayList data)
    {
        this.data = data;
        fireTableDataChanged();
    }

    public void deleteRow(int row)
    {
        if(row < 0 || row >= data.size())
        {
            return;
        } else
        {
            data.remove(row);
            fireTableRowsDeleted(row, row);
            return;
        }
    }

    public LegModel getLegModel(int row)
    {
        if(row < 0 || row >= data.size())
            return null;
        else
            return (LegModel)data.get(row);
    }

    public void addLeg(LegModel legModel)
    {
        data.add(legModel);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    private ArrayList data;
    private String columnNames[] = {
        "Type", "Fix Type", "Fix Ident", "Region", "Turn", "X"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, javax.swing.ImageIcon.class
    };
}
