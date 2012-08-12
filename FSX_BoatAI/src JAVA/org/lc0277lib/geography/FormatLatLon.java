// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FormatLatLon.java

package org.lc0277lib.geography;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatLatLon
{

    public FormatLatLon()
    {
    }

    public static String formatLat(double lat)
    {
        StringBuffer sgb = new StringBuffer("");
        if(lat < 0.0D)
        {
            sgb.append('S');
            lat = Math.abs(lat);
        } else
        {
            sgb.append('N');
        }
        double deg = Math.floor(lat);
        double min = (lat - deg) * 60D;
        min = (double)(int)(min * 100D) / 100D;
        sgb.append(DF0.format(deg));
        sgb.append("* ");
        sgb.append(DF2.format(min));
        sgb.append("'");
        return sgb.toString();
    }

    public static String formatLon(double lon)
    {
        StringBuffer sgb = new StringBuffer("");
        if(lon < 0.0D)
        {
            sgb.append('W');
            lon = Math.abs(lon);
        } else
        {
            sgb.append('E');
        }
        double deg = Math.floor(lon);
        double min = (lon - deg) * 60D;
        min = (double)(int)(min * 100D) / 100D;
        sgb.append(DF0.format(deg));
        sgb.append("* ");
        sgb.append(DF2.format(min));
        sgb.append("'");
        return sgb.toString();
    }

    public static final DecimalFormat DF0;
    public static final DecimalFormat DF1;
    public static final DecimalFormat DF2;
    public static final DecimalFormat DF3;

    static 
    {
        DF0 = new DecimalFormat("#########0", new DecimalFormatSymbols(Locale.ENGLISH));
        DF1 = new DecimalFormat("#########0.0", new DecimalFormatSymbols(Locale.ENGLISH));
        DF2 = new DecimalFormat("#########0.00", new DecimalFormatSymbols(Locale.ENGLISH));
        DF3 = new DecimalFormat("#########0.000", new DecimalFormatSymbols(Locale.ENGLISH));
    }
}
