// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseObjectSort.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import java.util.Comparator;

public class BaseObjectSort
    implements Comparator
{

    public BaseObjectSort()
    {
    }

    public int compare(Object o1, Object o2)
    {
        if((o1 instanceof BaseObject) && (o2 instanceof BaseObject))
            return ((BaseObject)o1).getName().compareToIgnoreCase(((BaseObject)o2).getName());
        else
            return 0;
    }

    public boolean equals(Object object)
    {
        return super.equals(object);
    }
}
