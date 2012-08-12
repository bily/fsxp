// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPointsObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwayPointsObject extends BaseObject
{

    public TaxiwayPointsObject()
    {
        this(0, 0);
    }

    public TaxiwayPointsObject(int offset, int length)
    {
        name = "TaxiwayPoints";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new PointCountItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "1A";
    public static final String ID2 = "00";
}
