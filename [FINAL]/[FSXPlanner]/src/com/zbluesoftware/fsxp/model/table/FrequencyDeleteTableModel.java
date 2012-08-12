// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FrequencyDeleteTableModel.java

package com.zbluesoftware.fsxp.model.table;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class FrequencyDeleteTableModel extends AbstractTableModel
{

    public FrequencyDeleteTableModel()
    {
        data = new ArrayList();
    }

    public int getRowCount()
    {
        return data.size();
    }

    public int getColumnCount()
    {
        return columnNames.length;
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
            return (Boolean)((HashMap)data.get(row)).get("d");

        case 1: // '\001'
            return (String)((HashMap)data.get(row)).get("frequency");

        case 2: // '\002'
            return (String)((HashMap)data.get(row)).get("type");
        }
        return "";
    }

    public void setValueAt(Object value, int row, int column)
    {
        if(row < 0 || row >= data.size())
            return;
        if(column == 0)
        {
            ((HashMap)data.get(row)).put("d", value);
            fireTableCellUpdated(row, column);
        }
    }

    public boolean isCellEditable(int row, int column)
    {
        return column == 0;
    }

    public void addRow(HashMap hashMap)
    {
        hashMap.put("d", new Boolean(false));
        data.add(hashMap);
        fireTableRowsInserted(data.size(), data.size());
    }

    public void clearData()
    {
        data.clear();
        fireTableDataChanged();
    }

    public ArrayList getDeleted()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = data.size() - 1; i >= 0; i--)
            if(((Boolean)((HashMap)data.get(i)).get("d")).booleanValue())
                arrayList.add(data.get(i));

        return arrayList;
    }

    public void setDeleteRow(String frequency, String type)
    {
        for(int i = data.size() - 1; i >= 0; i--)
            if(((String)getValueAt(i, 1)).equals(frequency) && ((String)getValueAt(i, 2)).equals(type))
                setValueAt(new Boolean(true), i, 0);

    }

    private ArrayList data;
    private String columnNames[] = {
        "D", "Frequency", "Type"
    };
    private Class columnClasses[] = {
        java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
    };
}
