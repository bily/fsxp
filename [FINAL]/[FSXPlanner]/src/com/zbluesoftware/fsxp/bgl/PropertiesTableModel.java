// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PropertiesTableModel.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class PropertiesTableModel extends AbstractTableModel
{

    public PropertiesTableModel()
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
        if(row < 0 || row > data.size())
            return "";
        Item item = (Item)((HashMap)data.get(row)).get("item");
        switch(column)
        {
        case 0: // '\0'
            return item.getName();

        case 1: // '\001'
            return Integer.valueOf(item.getOffset());

        case 2: // '\002'
            return Integer.valueOf(item.getLength());

        case 3: // '\003'
            return item.getDataType();

        case 4: // '\004'
            return item.getHexData();

        case 5: // '\005'
            return item.getDecodedData();
        }
        return "";
    }

    public void setData(BaseObject baseObject)
    {
        data = baseObject.getItemAL();
        fireTableDataChanged();
    }

    private ArrayList data;
    private String columnNames[] = {
        "Property", "Offset", "Length", "Type", "Hex Data", "Decoded Data"
    };
}
