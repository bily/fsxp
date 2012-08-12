// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileTableModel.java

package com.zbluesoftware.fsxp.bgl;

import java.io.*;
import javax.swing.table.AbstractTableModel;

public class FileTableModel extends AbstractTableModel
{

    public FileTableModel()
    {
        raFile = null;
        itemOffset = -1;
        itemLength = -1;
        objectOffset = -1;
        objectLength = -1;
    }

    public int getColumnCount()
    {
        return columnNames.length;
    }

    public Class getColumnClass(int column)
    {
        return java.lang.String.class;
    }

    public int getRowCount()
    {
        if(raFile == null)
            return 0;
        else
            return (int)(fileLength / 16L + 1L);
    }

    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    public Object getValueAt(int row, int column)
    {
        if(row < 0 || (long)row > fileLength / 16L)
            return "";
        if(column == 0)
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append(Long.toHexString(row * 16));
            for(int i = buffer.length(); i < 8; i++)
                buffer.insert(0, "0");

            return buffer.toString();
        }
        if(column == 1)
            return (new StringBuilder()).append("").append(row * 16).toString();
        if(column >= 2 && column <= 17)
        {
            if((long)(row * 16 + (column - 2)) < fileLength)
            {
                try
                {
                    raFile.seek(row * 16 + (column - 2));
                    return convertByteToHex(raFile.read());
                }
                catch(IOException ioe)
                {
                    ioe.printStackTrace();
                }
                return "";
            } else
            {
                return "";
            }
        } else
        {
            return "";
        }
    }

    public void setFileName(String fileName)
    {
        if(raFile != null)
            try
            {
                raFile.close();
            }
            catch(IOException ignored) { }
        try
        {
            raFile = new RandomAccessFile(fileName, "r");
            fileLength = 0L;
            try
            {
                fileLength = raFile.length();
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            fireTableDataChanged();
        }
        catch(FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
    }

    public void setItemLocation(int itemOffset, int itemLength)
    {
        this.itemOffset = itemOffset;
        this.itemLength = itemLength;
    }

    public int getItemStart()
    {
        return objectOffset + itemOffset;
    }

    public int getItemEnd()
    {
        return objectOffset + itemOffset + itemLength;
    }

    public void setObjectLocation(int objectOffset, int objectLength)
    {
        this.objectOffset = objectOffset;
        this.objectLength = objectLength;
    }

    public int getObjectOffset()
    {
        return objectOffset;
    }

    public int getObjectEnd()
    {
        return objectOffset + objectLength;
    }

    private String convertByteToHex(long byteNumber)
    {
        String hexValue = Long.toHexString(byteNumber);
        if(hexValue.length() > 2)
            hexValue = hexValue.substring(hexValue.length() - 2);
        else
        if(hexValue.length() == 1)
            hexValue = (new StringBuilder()).append("0").append(hexValue).toString();
        return hexValue.toUpperCase();
    }

    private RandomAccessFile raFile;
    private String columnNames[] = {
        "Offset (H)", "Offset (I)", "0", "1", "2", "3", "4", "5", "6", "7", 
        "8", "9", "A", "B", "C", "D", "E", "F"
    };
    private int itemOffset;
    private int itemLength;
    private int objectOffset;
    private int objectLength;
    private long fileLength;
}
