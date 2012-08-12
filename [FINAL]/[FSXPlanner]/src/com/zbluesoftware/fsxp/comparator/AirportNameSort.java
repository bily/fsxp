// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportNameSort.java

package com.zbluesoftware.fsxp.comparator;

import java.util.Comparator;
import java.util.HashMap;

public class AirportNameSort
    implements Comparator
{

    public AirportNameSort()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof HashMap) && (o2 instanceof HashMap))
            return ((String)((HashMap)o1).get("name")).compareToIgnoreCase((String)((HashMap)o2).get("name"));
        else
            return 0;
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }
}
