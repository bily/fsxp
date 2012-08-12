// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionFileFilter.java

package org.lc0277lib.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter
{

    public ExtensionFileFilter(String ext, String desc)
    {
        this.ext = ext.toLowerCase();
        this.desc = desc;
    }

    public ExtensionFileFilter(String ext)
    {
        this(ext, (new StringBuilder(String.valueOf(ext.toUpperCase()))).append(" files").toString());
    }

    public boolean accept(File f)
    {
        if(f.isDirectory())
            return true;
        return f.getName().toLowerCase().endsWith((new StringBuilder(".")).append(ext).toString());
    }

    public String getDescription()
    {
        return desc;
    }

    public String getExtension()
    {
        return ext;
    }

    private String ext;
    private String desc;
}
