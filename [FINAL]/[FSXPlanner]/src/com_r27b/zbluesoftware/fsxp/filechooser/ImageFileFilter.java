// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageFileFilter.java

package com.zbluesoftware.fsxp.filechooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageFileFilter extends FileFilter
{

    public ImageFileFilter()
    {
    }

    public boolean accept(File file)
    {
        String fileName = file.getName().toLowerCase();
        if(fileName.endsWith(".jpg") || fileName.endsWith("gif") || fileName.endsWith("jpeg"))
            return true;
        return file.isDirectory();
    }

    public String getDescription()
    {
        return "JPG and Gif Files";
    }
}
