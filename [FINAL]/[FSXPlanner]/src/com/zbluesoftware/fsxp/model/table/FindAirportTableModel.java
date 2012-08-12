// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FindAirportTableModel.java

package com.zbluesoftware.fsxp.model.table;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class FindAirportTableModel extends AbstractTableModel
{

    public FindAirportTableModel()
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

    public Object getValueAt(int row, int column)
    {
        if(row < 0 || row >= data.size())
            return "";
        switch(column)
        {
        case 0: // '\0'
            return (String)((HashMap)data.get(row)).get("name");

        case 1: // '\001'
            return (String)((HashMap)data.get(row)).get("icao");

        case 2: // '\002'
            return (String)((HashMap)data.get(row)).get("city");

        case 3: // '\003'
            return (String)((HashMap)data.get(row)).get("state");

        case 4: // '\004'
            return (String)((HashMap)data.get(row)).get("country");

        case 5: // '\005'
            return (String)((HashMap)data.get(row)).get("folder");

        case 6: // '\006'
            return (String)((HashMap)data.get(row)).get("file");
        }
        return "";
    }

    public void addRow(String name, String icao, String city, String state, String country, String folder, String file)
    {
        HashMap hashMap = new HashMap();
        hashMap.put("name", name);
        hashMap.put("icao", icao);
        hashMap.put("city", city);
        hashMap.put("state", state);
        hashMap.put("country", country);
        hashMap.put("folder", folder);
        hashMap.put("file", file);
        data.add(hashMap);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void clearData()
    {
        data.clear();
        fireTableDataChanged();
    }

    private ArrayList data;
    private String columnNames[] = {
        "Name", "ICAO", "City", "State", "Country", "Folder", "File"
    };
}
