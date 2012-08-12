// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorPrefsTableModel.java

package com.zbluesoftware.fsxp.model.table;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import java.awt.Color;
import java.util.*;
import javax.swing.table.AbstractTableModel;

public class ColorPrefsTableModel extends AbstractTableModel
{

    public ColorPrefsTableModel()
    {
        data = new LinkedHashMap();
        data.put("Asphalt:", ColorsEngine.getInstance().getSurfaceColor("ASPHALT"));
        data.put("Bituminous:", ColorsEngine.getInstance().getSurfaceColor("BITUMINOUS"));
        data.put("Brick:", ColorsEngine.getInstance().getSurfaceColor("BRICK"));
        data.put("Clay:", ColorsEngine.getInstance().getSurfaceColor("CLAY"));
        data.put("Cement:", ColorsEngine.getInstance().getSurfaceColor("CEMENT"));
        data.put("Concrete:", ColorsEngine.getInstance().getSurfaceColor("CONCRETE"));
        data.put("Coral:", ColorsEngine.getInstance().getSurfaceColor("CORAL"));
        data.put("Dirt:", ColorsEngine.getInstance().getSurfaceColor("DIRT"));
        data.put("Grass:", ColorsEngine.getInstance().getSurfaceColor("GRASS"));
        data.put("Gravel:", ColorsEngine.getInstance().getSurfaceColor("GRAVEL"));
        data.put("Ice:", ColorsEngine.getInstance().getSurfaceColor("ICE"));
        data.put("Macadam:", ColorsEngine.getInstance().getSurfaceColor("MACADAM"));
        data.put("Oil Treated Planks:", ColorsEngine.getInstance().getSurfaceColor("OIL_TREATED_PLANKS"));
        data.put("Sand:", ColorsEngine.getInstance().getSurfaceColor("SAND"));
        data.put("Shale:", ColorsEngine.getInstance().getSurfaceColor("SHALE"));
        data.put("Snow:", ColorsEngine.getInstance().getSurfaceColor("SNOW"));
        data.put("Steel Mats:", ColorsEngine.getInstance().getSurfaceColor("STEEL_MATS"));
        data.put("Tarmac:", ColorsEngine.getInstance().getSurfaceColor("TARMAC"));
        data.put("Unknown:", ColorsEngine.getInstance().getSurfaceColor("UNKNOWN"));
        data.put("Water:", ColorsEngine.getInstance().getSurfaceColor("WATER"));
        data.put("Lat/Lon Background", ColorsEngine.getInstance().getLatLonColor());
        data.put("Main Background", ColorsEngine.getInstance().getBackgroundColor());
        data.put("Night Color", ColorsEngine.getInstance().getNightColor());
        data.put("Taxiway Line Color", ColorsEngine.getInstance().getTaxiwayLineColor());
        data.put("ILS Beam Color", ColorsEngine.getInstance().getILSColor());
        data.put("DME Color", ColorsEngine.getInstance().getDMEColor());
        data.put("Glide Slope Beam Color", ColorsEngine.getInstance().getGlideSlopeColor());
        data.put("Exclusion Color", ColorsEngine.getInstance().getExclusionColor());
        data.put("Fuel Trigger Color", ColorsEngine.getInstance().getTriggerColor());
        data.put("Scenery Color", ColorsEngine.getInstance().getSceneryColor());
        data.put("Selected Object Color", ColorsEngine.getInstance().getSelectedColor());
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
        int index = 0;
        for(Iterator iterator = data.keySet().iterator(); iterator.hasNext();)
        {
            String key = (String)iterator.next();
            if(index == row)
                if(column == 0)
                    return key;
                else
                    return (Color)data.get(key);
            index++;
        }

        if(column == 0)
            return "";
        else
            return Color.white;
    }

    public void setColor(String key, Color color)
    {
        data.put(key, color);
        fireTableDataChanged();
    }

    private LinkedHashMap data;
    private String columnNames[] = {
        "Item", "Color"
    };
    private Class columnClasses[] = {
        java.lang.String.class, java.awt.Color.class
    };
}
