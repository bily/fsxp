// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NDBObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class NDBObject extends BaseObject
{

    public NDBObject()
    {
        this(0, 0);
    }

    public NDBObject(int offset, int length)
    {
        name = "NDBObject";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size"), true);
        addItem(new NDBTypeItem(), true);
        addItem(new ComFrequencyItem("frequency", 0, 31, 1000F), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new HeadingItem("range"), true);
        addItem(new MagVarItem(), true);
        addItem(new IcaoItem(), true);
        addItem(new ApproachFixIdentItem("region", false, 21, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 21), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "17";
    public static final String ID2 = "00";
}
