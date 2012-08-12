// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkerObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class MarkerObject extends BaseObject
{

    public MarkerObject()
    {
        this(0, 0);
    }

    public MarkerObject(int offset, int length)
    {
        name = "MarkerObject";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size", "Word", 2), true);
        addItem(new HeadingItem("heading", "Word", 2), true);
        addItem(new MarkerTypeItem(), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new IcaoItem(), true);
        addItem(new RegionItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "18";
    public static final String ID2 = "00";
}
