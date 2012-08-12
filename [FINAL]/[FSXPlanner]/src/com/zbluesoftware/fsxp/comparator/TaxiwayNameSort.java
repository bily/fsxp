// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayNameSort.java

package com.zbluesoftware.fsxp.comparator;

import java.util.Comparator;
import java.util.HashMap;

public class TaxiwayNameSort
    implements Comparator
{

    public TaxiwayNameSort()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof HashMap) && (o2 instanceof HashMap))
        {
            String name1 = (String)((HashMap)o1).get("name");
            String name2 = (String)((HashMap)o2).get("name");
            if(name1.equals("[none]"))
                return -1;
            if(name2.equals("[none]"))
                return 1;
            else
                return name1.compareTo(name2);
        } else
        {
            return 0;
        }
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }
}
