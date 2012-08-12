// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DirectoryFileFilter.java

package com.zbluesoftware.fsxp.filechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class DirectoryFileFilter extends FileFilter
{

    public DirectoryFileFilter()
    {
    }

    public boolean accept(File file)
    {
        return file.isDirectory();
    }

    public String getDescription()
    {
        return "Directories";
    }
}
