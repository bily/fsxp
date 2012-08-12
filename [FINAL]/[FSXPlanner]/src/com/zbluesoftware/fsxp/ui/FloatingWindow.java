// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FloatingWindow.java

package com.zbluesoftware.fsxp.ui;

import java.awt.*;
import javax.swing.JDialog;

public class FloatingWindow extends JDialog
{

    public FloatingWindow(Frame frame)
    {
        this(frame, true);
    }

    public FloatingWindow(Frame frame, boolean shouldPaint)
    {
        super(frame);
        this.shouldPaint = shouldPaint;
        setUndecorated(true);
        setFocusableWindowState(false);
        getContentPane().setLayout(new BorderLayout());
    }

    public void paint(Graphics g)
    {
        if(shouldPaint)
            super.paint(g);
    }

    private boolean shouldPaint;
}
