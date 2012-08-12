// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ICAONameListObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ICAONameListObject extends BaseObject
{

    public ICAONameListObject()
    {
        this(0, 0);
    }

    public ICAONameListObject(int offset, int length)
    {
        name = "Icao";
        this.offset = offset;
        this.length = length;
        addItem(new PointCountItem("regionIndex", "Byte", 1), true);
        addItem(new PointCountItem("countryIndex", "Byte", 1), true);
        addItem(new StateNameIndexItem(), true);
        addItem(new PointCountItem("cityIndex"), true);
        addItem(new PointCountItem("airportNameIndex"), true);
        addItem(new IcaoItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
