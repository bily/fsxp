// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameListsObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class NameListsObject extends BaseObject
{

    public NameListsObject()
    {
        this(0, 0);
    }

    public NameListsObject(int offset, int length)
    {
        name = "NameList";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size", "DWord", 4), true);
        addItem(new PointCountItem("regionCount"), true);
        addItem(new PointCountItem("countryCount"), true);
        addItem(new PointCountItem("stateCount"), true);
        addItem(new PointCountItem("cityCount"), true);
        addItem(new PointCountItem("airportNameCount"), true);
        addItem(new PointCountItem("icaoCount"), true);
        addItem(new PointCountItem("regionOffset", "DWord", 4), true);
        addItem(new PointCountItem("countryOffset", "DWord", 4), true);
        addItem(new PointCountItem("stateOffset", "DWord", 4), true);
        addItem(new PointCountItem("cityOffset", "DWord", 4), true);
        addItem(new PointCountItem("airportNameOffset", "DWord", 4), true);
        addItem(new PointCountItem("icaoOffset", "DWord", 4), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "27";
    public static final String ID2 = "00";
}
