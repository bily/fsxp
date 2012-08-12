// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatLonPoint.java

package com.zbluesoftware.fsxp.util;

import java.text.NumberFormat;

public class LatLonPoint
{

    public LatLonPoint()
    {
        this(0.0D, 0.0D);
    }

    public LatLonPoint(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(6);
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double lon)
    {
        this.lon = lon;
    }

    public void adjustLat(double latDifference)
    {
        lat += latDifference;
    }

    public void adjustLon(double lonDifference)
    {
        lon += lonDifference;
    }

    public boolean equals(Object point)
    {
        if(!(point instanceof LatLonPoint))
            return false;
        return ((LatLonPoint)point).getLat() == lat && ((LatLonPoint)point).getLon() == lon;
    }

    public String toString()
    {
        return (new StringBuilder()).append(numberFormat.format(lat)).append("/").append(numberFormat.format(lon)).toString();
    }

    public Object clone()
    {
        return new LatLonPoint(getLat(), getLon());
    }

    private NumberFormat numberFormat;
    private double lat;
    private double lon;
}
