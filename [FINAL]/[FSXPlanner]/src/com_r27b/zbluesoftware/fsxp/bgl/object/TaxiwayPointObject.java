// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPointObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwayPointObject extends BaseObject
{

    public TaxiwayPointObject()
    {
        this(0, 0);
    }

    public TaxiwayPointObject(int offset, int length)
    {
        name = "TaxiwayPoint";
        this.offset = offset;
        this.length = length;
        addItem(new TaxiwayTypeItem(), true);
        addItem(new TaxiwayOrientationItem(), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
