// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignsObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwaySignsObject extends BaseObject
{

    public TaxiwaySignsObject()
    {
        this(0, 0);
    }

    public TaxiwaySignsObject(int offset, int length)
    {
        name = "TaxiwaySigns";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size", "Word", 2), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new AboveGLItem(), true);
        addItem(new PitchItem("pitch", "Word", 2), true);
        addItem(new BankItem("bank", "Word", 2), true);
        addItem(new HeadingItem("heading", "Word", 2), true);
        addItem(new ImageComplexityItem(), true);
        addItem(new GUIDItem("instanceId"), true);
        addItem(new PointCountItem("signCount", "DWord", 4), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "0E";
    public static final String ID2 = "00";
}
