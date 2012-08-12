// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrintPrefsEngine.java

package com.zbluesoftware.fsxp.engine;

import java.util.prefs.Preferences;

public class PrintPrefsEngine
{

    private PrintPrefsEngine()
    {
        printAirport = true;
        printTestRadius = false;
        printBGColor = false;
        paperWidth = 8.5F;
        paperHeight = 11F;
        topMargin = 0.5F;
        leftMargin = 0.5F;
        rightMargin = 0.5F;
        bottomMargin = 0.5F;
        readPreferences();
    }

    public static PrintPrefsEngine getInstance()
    {
        if(engine == null)
            engine = new PrintPrefsEngine();
        return engine;
    }

    public boolean getPrintAirport()
    {
        return printAirport;
    }

    public void setPrintAirport(boolean printAirport)
    {
        this.printAirport = printAirport;
    }

    public boolean getPrintTestRadius()
    {
        return printTestRadius;
    }

    public void setPrintTestRadius(boolean printTestRadius)
    {
        this.printTestRadius = printTestRadius;
    }

    public boolean getPrintBGColor()
    {
        return printBGColor;
    }

    public void setPrintBGColor(boolean printBGColor)
    {
        this.printBGColor = printBGColor;
    }

    public float getPaperWidth()
    {
        return paperWidth;
    }

    public void setPaperWidth(float paperWidth)
    {
        this.paperWidth = paperWidth;
    }

    public float getPaperHeight()
    {
        return paperHeight;
    }

    public void setPaperHeight(float paperHeight)
    {
        this.paperHeight = paperHeight;
    }

    public float getTopMargin()
    {
        return topMargin;
    }

    public void setTopMargin(float topMargin)
    {
        this.topMargin = topMargin;
    }

    public float getLeftMargin()
    {
        return leftMargin;
    }

    public void setLeftMargin(float leftMargin)
    {
        this.leftMargin = leftMargin;
    }

    public float getRightMargin()
    {
        return rightMargin;
    }

    public void setRightMargin(float rightMargin)
    {
        this.rightMargin = rightMargin;
    }

    public float getBottomMargin()
    {
        return bottomMargin;
    }

    public void setBottomMargin(float bottomMargin)
    {
        this.bottomMargin = bottomMargin;
    }

    public void writePreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putBoolean("printAirport", printAirport);
        userPrefs.putBoolean("printTestRadius", printTestRadius);
        userPrefs.putBoolean("printBGColor", printBGColor);
        userPrefs.putFloat("paperWidth", paperWidth);
        userPrefs.putFloat("paperHeight", paperHeight);
        userPrefs.putFloat("topMargin", topMargin);
        userPrefs.putFloat("leftMargin", leftMargin);
        userPrefs.putFloat("rightMargin", rightMargin);
        userPrefs.putFloat("bottomMargin", bottomMargin);
    }

    private void readPreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        printAirport = userPrefs.getBoolean("printAirport", printAirport);
        printTestRadius = userPrefs.getBoolean("printTestRadius", printTestRadius);
        printBGColor = userPrefs.getBoolean("printBGColor", printBGColor);
        paperWidth = userPrefs.getFloat("paperWidth", paperWidth);
        paperHeight = userPrefs.getFloat("paperHeight", paperHeight);
        topMargin = userPrefs.getFloat("topMargin", topMargin);
        leftMargin = userPrefs.getFloat("leftMargin", leftMargin);
        rightMargin = userPrefs.getFloat("rightMargin", rightMargin);
        bottomMargin = userPrefs.getFloat("bottomMargin", bottomMargin);
    }

    private boolean printAirport;
    private boolean printTestRadius;
    private boolean printBGColor;
    private float paperWidth;
    private float paperHeight;
    private float topMargin;
    private float leftMargin;
    private float rightMargin;
    private float bottomMargin;
    private static PrintPrefsEngine engine = null;

}
