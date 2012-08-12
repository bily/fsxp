// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServicesTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.graphics.IconFactory;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ServicesTableModel extends AbstractTableModel
{

    public ServicesTableModel()
    {
        data = new ArrayList();
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return data.size() + 1;
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
            return null;
        switch(column)
        {
        case 0: // '\0'
            return (String)((HashMap)data.get(row)).get("type");

        case 1: // '\001'
            return (String)((HashMap)data.get(row)).get("availability");

        case 2: // '\002'
            return IconFactory.getInstance().getIcon("delete");
        }
        return null;
    }

    public void setValueAt(Object value, int row, int column)
    {
        if(row < 0)
            return;
        if(row < data.size())
        {
            if(column == 0)
                ((HashMap)data.get(row)).put("type", value);
            else
            if(column == 1)
                ((HashMap)data.get(row)).put("availability", value);
            fireTableCellUpdated(row, column);
        } else
        if(value.toString().trim().length() > 0)
        {
            HashMap hashMap = new HashMap();
            if(column == 0)
                hashMap.put("type", value);
            else
            if(column == 1)
                hashMap.put("availability", value);
            data.add(hashMap);
            fireTableRowsInserted(row, row);
        }
    }

    public boolean isCellEditable(int row, int column)
    {
        return column <= 1;
    }

    public void setData(ArrayList data)
    {
        this.data = data;
        fireTableDataChanged();
    }

    public ArrayList getData()
    {
        return data;
    }

    public boolean deleteRow(int row)
    {
        if(row < 0 || row >= data.size())
        {
            return false;
        } else
        {
            data.remove(row);
            fireTableRowsDeleted(row, row);
            return true;
        }
    }

    private ArrayList data;
    private String columnNames[] = {
        "Fuel Type", "Fuel Availability", "X"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.lang.String.class, javax.swing.ImageIcon.class
    };
}
