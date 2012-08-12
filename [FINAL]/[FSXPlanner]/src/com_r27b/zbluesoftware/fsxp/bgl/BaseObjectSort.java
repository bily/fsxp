// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:26:49 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
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