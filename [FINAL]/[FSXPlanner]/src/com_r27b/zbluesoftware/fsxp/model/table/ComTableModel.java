// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.ComModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

public class ComTableModel extends AbstractTableModel
{

    public ComTableModel()
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
            return "";
        switch(column)
        {
        case 0: // '\0'
            return new Float(((ComModel)data.get(row)).getFrequency());

        case 1: // '\001'
            return ((ComModel)data.get(row)).getType();

        case 2: // '\002'
            return ((ComModel)data.get(row)).getName();

        case 3: // '\003'
            return IconFactory.getInstance().getIcon("delete");
        }
        return "";
    }

    public void setValueAt(Object value, int row, int column)
    {
        ComModel comModel = null;
        if(row < data.size())
        {
            comModel = (ComModel)data.get(row);
        } else
        {
            comModel = new ComModel();
            data.add(comModel);
            fireTableRowsInserted(data.size(), data.size());
        }
        if(comModel != null)
        {
            switch(column)
            {
            default:
                break;

            case 0: // '\0'
                if(value instanceof Double)
                {
                    ((ComModel)data.get(row)).setFrequency(((Double)value).floatValue());
                    break;
                }
                if(value instanceof Float)
                    ((ComModel)data.get(row)).setFrequency(((Float)value).floatValue());
                break;

            case 1: // '\001'
                ((ComModel)data.get(row)).setType((String)value);
                break;

            case 2: // '\002'
                if(!(value instanceof String))
                    break;
                String vString = (String)value;
                if(vString.length() > 48)
                    vString = vString.substring(0, 47);
                ((ComModel)data.get(row)).setName(vString);
                break;
            }
            fireTableCellUpdated(row, column);
        }
    }

    public boolean isCellEditable(int row, int column)
    {
        return column != 3;
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

    private ArrayList data;
    private String columnNames[] = {
        "Frequency", "Type", "Name", "X"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, javax.swing.ImageIcon.class
    };
}
