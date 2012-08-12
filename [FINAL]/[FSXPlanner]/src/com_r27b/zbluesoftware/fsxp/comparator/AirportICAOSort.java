// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportICAOSort.java

package com.zbluesoftware.fsxp.comparator;

import java.util.Comparator;
import java.util.HashMap;

public class AirportICAOSort
    implements Comparator
{

    public AirportICAOSort()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof HashMap) && (o2 instanceof HashMap))
            return ((String)((HashMap)o1).get("icao")).compareToIgnoreCase((String)((HashMap)o2).get("icao"));
        else
            return 0;
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }
}
