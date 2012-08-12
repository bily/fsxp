// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportDatFileFilter.java

package com.zbluesoftware.fsxp.filechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class AirportDatFileFilter extends FileFilter
{

    public AirportDatFileFilter()
    {
    }

    public boolean accept(File file)
    {
        if(file.getName().endsWith("Airports.dat"))
            return true;
        return file.isDirectory();
    }

    public String getDescription()
    {
        return "Airport.dat Files";
    }
}
