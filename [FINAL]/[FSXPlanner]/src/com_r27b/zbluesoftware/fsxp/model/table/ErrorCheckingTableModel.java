// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorCheckingTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.model.BaseModel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class ErrorCheckingTableModel extends AbstractTableModel
{

    public ErrorCheckingTableModel()
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
        HashMap hashMap = (HashMap)data.get(row);
        switch(column)
        {
        case 0: // '\0'
            return (String)hashMap.get("error");

        case 1: // '\001'
            return (String)hashMap.get("details");
        }
        return "";
    }

    public void clearData()
    {
        data.clear();
        fireTableDataChanged();
    }

    public BaseModel getModel(int row)
    {
        return (BaseModel)((HashMap)data.get(row)).get("model");
    }

    public void addRow(BaseModel model, String error, String details)
    {
        HashMap hashMap = new HashMap();
        hashMap.put("model", model);
        hashMap.put("error", error);
        hashMap.put("details", details);
        data.add(hashMap);
        fireTableRowsInserted(data.size(), data.size());
    }

    private ArrayList data;
    private String columnNames[] = {
        "Error/Warning", "Details"
    };
}
