// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UpdateTableModel.java

package com.zbluesoftware.fsxp.model.table;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class UpdateTableModel extends AbstractTableModel
    implements TableModelListener
{

    public UpdateTableModel()
    {
        data = new ArrayList();
        sizeFormat = NumberFormat.getInstance();
        sizeFormat.setMaximumFractionDigits(1);
        addTableModelListener(this);
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
        HashMap hashMap = (HashMap)data.get(row);
        switch(column)
        {
        case 0: // '\0'
            return (Boolean)hashMap.get("Selected");

        case 1: // '\001'
            return (String)hashMap.get("Type");

        case 2: // '\002'
            return (String)hashMap.get("Desc");

        case 3: // '\003'
            float size = 0.0F;
            try
            {
                size = Float.parseFloat((String)hashMap.get("Size"));
            }
            catch(NumberFormatException nfe)
            {
                size = 0.0F;
            }
            if(size / 1024F > 1000F)
                return (new StringBuilder()).append(sizeFormat.format(size / 1024F / 1024F)).append(" MB").toString();
            else
                return (new StringBuilder()).append(sizeFormat.format(size / 1024F)).append(" KB").toString();

        case 4: // '\004'
            return (String)hashMap.get("Date");
        }
        return "";
    }

    public void setValueAt(Object value, int row, int column)
    {
        if(column == 0)
        {
            HashMap hashMap = (HashMap)data.get(row);
            hashMap.put("Selected", value);
            fireTableCellUpdated(row, column);
        }
    }

    public boolean isCellEditable(int row, int column)
    {
        if(column == 0)
        {
            HashMap hashMap = (HashMap)data.get(row);
            return !hashMap.containsKey("Lock");
        } else
        {
            return false;
        }
    }

    public void addRow(HashMap hashMap)
    {
        hashMap.put("Selected", new Boolean(false));
        data.add(hashMap);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public ArrayList getSelectedUpdates()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < getRowCount(); i++)
            if(((Boolean)getValueAt(i, 0)).booleanValue())
                arrayList.add(data.get(i));

        return arrayList;
    }

    public boolean isUpdateSelected()
    {
        for(int i = 0; i < getRowCount(); i++)
            if(((Boolean)getValueAt(i, 0)).booleanValue())
                return true;

        return false;
    }

    public void removeUpdateItem(String id)
    {
        for(int i = 0; i < getRowCount(); i++)
        {
            HashMap hashMap = (HashMap)data.get(i);
            if(id.equals((String)hashMap.get("ID")))
            {
                data.remove(i);
                fireTableRowsDeleted(i, i);
                return;
            }
        }

    }

    public void tableChanged(TableModelEvent event)
    {
        if(event.getType() == 0)
        {
            int column = event.getColumn();
            int row = event.getFirstRow();
            if(column != 0)
                return;
            if(((Boolean)getValueAt(row, column)).booleanValue())
            {
                ArrayList arrayList = (ArrayList)((HashMap)data.get(row)).get("Dependencies");
                String id = (String)((HashMap)data.get(row)).get("ID");
                for(int i = 0; i < arrayList.size(); i++)
                {
                    String dependencyID = (String)arrayList.get(i);
                    int j = 0;
                    do
                    {
                        if(j >= data.size())
                            break;
                        if(dependencyID.equals((String)((HashMap)data.get(j)).get("ID")))
                        {
                            setValueAt(new Boolean(true), j, 0);
                            ((HashMap)data.get(j)).put("Lock", new Boolean(true));
                            break;
                        }
                        j++;
                    } while(true);
                }

            } else
            {
                ArrayList arrayList = (ArrayList)((HashMap)data.get(row)).get("Dependencies");
                String id = (String)((HashMap)data.get(row)).get("ID");
label0:
                for(int i = 0; i < arrayList.size(); i++)
                {
                    String dependencyID = (String)arrayList.get(i);
                    int j = 0;
                    do
                    {
                        if(j >= data.size())
                            continue label0;
                        if(dependencyID.equals((String)((HashMap)data.get(j)).get("ID")))
                        {
                            ((HashMap)data.get(j)).remove("Lock");
                            continue label0;
                        }
                        j++;
                    } while(true);
                }

            }
        }
    }

    private String columnNames[] = {
        "U", "Type", "Description", "Size", "Date"
    };
    private Class columnClasses[] = {
        java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
    };
    private ArrayList data;
    private NumberFormat sizeFormat;
}
