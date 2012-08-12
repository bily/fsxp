// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlideSlopeObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class GlideSlopeObject extends BaseObject
{

    public GlideSlopeObject()
    {
        this(0, 0);
    }

    public GlideSlopeObject(int offset, int length)
    {
        name = "GlideSlope";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size"), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new HeadingItem("range"), true);
        addItem(new PitchItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "15";
    public static final String ID2 = "00";
}
