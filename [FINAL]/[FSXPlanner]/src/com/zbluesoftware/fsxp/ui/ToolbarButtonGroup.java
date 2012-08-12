// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ToolbarButtonGroup.java

package com.zbluesoftware.fsxp.ui;

import javax.swing.ButtonGroup;

public class ToolbarButtonGroup extends ButtonGroup
{

    private ToolbarButtonGroup()
    {
    }

    public static ToolbarButtonGroup getInstance()
    {
        if(buttonGroup == null)
            buttonGroup = new ToolbarButtonGroup();
        return buttonGroup;
    }

    private static ToolbarButtonGroup buttonGroup = null;

}
