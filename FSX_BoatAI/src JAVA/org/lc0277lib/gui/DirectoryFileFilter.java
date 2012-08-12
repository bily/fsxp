// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryFileFilter.java

package org.lc0277lib.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class DirectoryFileFilter extends FileFilter
{

    public DirectoryFileFilter(String desc)
    {
        description = desc;
    }

    public DirectoryFileFilter()
    {
        this("");
    }

    public boolean accept(File f)
    {
        return f.isDirectory();
    }

    public String getDescription()
    {
        return description;
    }

    private String description;
}
