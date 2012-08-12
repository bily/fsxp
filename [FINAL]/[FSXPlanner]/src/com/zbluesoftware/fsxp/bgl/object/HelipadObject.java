// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HelipadObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class HelipadObject extends BaseObject
{

    public HelipadObject()
    {
        this(0, 0);
    }

    public HelipadObject(int offset, int length)
    {
        name = "Helipad";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        SurfaceItem surfaceItem = new SurfaceItem();
        surfaceItem.setLength(1);
        surfaceItem.setDataType("Byte");
        addItem(surfaceItem, true);
        addItem(new HelipadTypeItem(), true);
        addItem(new HelipadOptionItem("transparent", 4), true);
        addItem(new HelipadOptionItem("closed", 5), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new LengthItem(), true);
        addItem(new WidthItem(), true);
        addItem(new HeadingItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "26";
    public static final String ID2 = "00";
}
