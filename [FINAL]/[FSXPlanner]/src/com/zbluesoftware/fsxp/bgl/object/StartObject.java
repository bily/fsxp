// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class StartObject extends BaseObject
{

    public StartObject()
    {
        this(0, 0);
    }

    public StartObject(int offset, int length)
    {
        name = "Start Location";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new RunwayNumberItem(), true);
        addItem(new StartRunwayDesigItem(), true);
        addItem(new StartTypeItem(), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new HeadingItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "11";
    public static final String ID2 = "00";
}
