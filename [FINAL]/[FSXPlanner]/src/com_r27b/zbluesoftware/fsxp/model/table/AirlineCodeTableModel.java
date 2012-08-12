// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirlineCodeTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.engine.AirlineCodeEngine;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class AirlineCodeTableModel extends AbstractTableModel
{

    public AirlineCodeTableModel()
    {
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public int getRowCount()
    {
        return AirlineCodeEngine.getInstance().getAirlineAL().size();
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
        if(row < 0 || row >= AirlineCodeEngine.getInstance().getAirlineAL().size())
            if(column != 2)
                return "";
            else
                return Color.white;
        switch(column)
        {
        case 0: // '\0'
            return (String)((HashMap)AirlineCodeEngine.getInstance().getAirlineAL().get(row)).get("N");

        case 1: // '\001'
            return (String)((HashMap)AirlineCodeEngine.getInstance().getAirlineAL().get(row)).get("C");

        case 2: // '\002'
            return new Color(((Integer)((HashMap)AirlineCodeEngine.getInstance().getAirlineAL().get(row)).get("color")).intValue());
        }
        return "";
    }

    private String columnNames[] = {
        "Airline", "Code", "Color"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.lang.String.class, java.awt.Color.class
    };
}
