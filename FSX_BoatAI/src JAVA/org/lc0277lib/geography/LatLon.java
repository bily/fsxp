// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatLon.java

package org.lc0277lib.geography;

import org.lc0277lib.geom.Point2D;

public class LatLon extends Point2D
{

    public void setFromDegrees(double latDegrees, double lonDegrees)
    {
        set(Math.toRadians(latDegrees), Math.toRadians(lonDegrees));
    }

    public void setFromRadians(double latRadians, double lonRadians)
    {
        set(latRadians, lonRadians);
    }

    public LatLon(double lat, double lon)
    {
        super(Math.toRadians(lat), Math.toRadians(lon));
    }

    public LatLon()
    {
    }

    public double getLatitude()
    {
        return Math.toDegrees(x);
    }

    public double getLongitude()
    {
        return Math.toDegrees(y);
    }
}
