// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPointElementSort.java

package com.zbluesoftware.fsxp.comparator;

import java.util.Comparator;
import org.w3c.dom.Element;

public class TaxiwayPointElementSort
    implements Comparator
{

    public TaxiwayPointElementSort()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof Element) && (o2 instanceof Element))
        {
            Integer index1 = new Integer(0);
            Integer index2 = new Integer(0);
            if(((Element)o1).hasAttribute("index"))
                index1 = new Integer(((Element)o1).getAttribute("index"));
            if(((Element)o2).hasAttribute("index"))
                index2 = new Integer(((Element)o2).getAttribute("index"));
            return index2.compareTo(index1);
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
