// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.comparator.AirportICAOSort;
import com.zbluesoftware.fsxp.comparator.AirportNameSort;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AirportTableModel extends AbstractTableModel
{

    public AirportTableModel(NodeList airports)
    {
        data = new ArrayList();
        for(int i = 0; i < airports.getLength(); i++)
        {
            Element airportElement = (Element)airports.item(i);
            HashMap hashMap = new HashMap();
            hashMap.put("icao", airportElement.getAttribute("ident"));
            if(airportElement.hasAttribute("name"))
                hashMap.put("name", airportElement.getAttribute("name"));
            else
                hashMap.put("name", "");
            hashMap.put("open", new Boolean(false));
            data.add(hashMap);
        }

        Collections.sort(data, new AirportNameSort());
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
        HashMap hashMap = (HashMap)data.get(row);
        switch(column)
        {
        case 0: // '\0'
            return (String)hashMap.get("icao");

        case 1: // '\001'
            return (String)hashMap.get("name");

        case 2: // '\002'
            return (Boolean)hashMap.get("open");
        }
        return "";
    }

    public void setValueAt(Object value, int row, int column)
    {
        if(row < 0 || row >= data.size())
            return;
        if(column == 2)
        {
            ((HashMap)data.get(row)).put("open", (Boolean)value);
            fireTableCellUpdated(row, column);
        }
    }

    public boolean isCellEditable(int row, int column)
    {
        return column == 2;
    }

    public ArrayList getSelectedAirports()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = data.size() - 1; i >= 0; i--)
            if(((Boolean)getValueAt(i, 2)).booleanValue())
                arrayList.add(getValueAt(i, 0));

        return arrayList;
    }

    public void sortDataByColumn(int column)
    {
        if(column == 0)
        {
            Collections.sort(data, new AirportICAOSort());
            fireTableDataChanged();
        } else
        if(column == 1)
        {
            Collections.sort(data, new AirportNameSort());
            fireTableDataChanged();
        }
    }

    private ArrayList data;
    private String columnNames[] = {
        "ICAO", "Airport Name", "Open"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
    };
}
