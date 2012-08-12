// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartupPanel.java

package com.zbluesoftware.fsxp.startup;

import javax.swing.JPanel;

public interface StartupPanel
{

    public abstract JPanel getPanel();

    public abstract void recordSettings();

    public abstract void setTotalCount(int i, int j);
}
