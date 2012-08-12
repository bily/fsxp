// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DmeArcModel.java

package com.zbluesoftware.fsxp.model;


public class DmeArcModel
{

    public DmeArcModel()
    {
        dmeRegion = "";
        dmeIdent = "";
        distanceMeasure = "N";
        radial = 0.0F;
        distance = 27F;
    }

    public String getDmeRegion()
    {
        return dmeRegion;
    }

    public void setDmeRegion(String dmeRegion)
    {
        this.dmeRegion = dmeRegion;
    }

    public String getDmeIdent()
    {
        return dmeIdent;
    }

    public void setDmeIdent(String dmeIdent)
    {
        this.dmeIdent = dmeIdent;
    }

    public String getDistanceMeasure()
    {
        return distanceMeasure;
    }

    public void setDistanceMeasure(String distanceMeasure)
    {
        this.distanceMeasure = distanceMeasure;
    }

    public float getRadial()
    {
        return radial;
    }

    public void setRadial(float radial)
    {
        this.radial = radial;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }

    private String dmeRegion;
    private String dmeIdent;
    private String distanceMeasure;
    private float radial;
    private float distance;
}
