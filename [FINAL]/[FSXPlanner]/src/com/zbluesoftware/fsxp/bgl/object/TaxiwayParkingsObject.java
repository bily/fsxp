// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayParkingsObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwayParkingsObject extends BaseObject
{

    public TaxiwayParkingsObject()
    {
        this(0, 0);
    }

    public TaxiwayParkingsObject(int offset, int length)
    {
        name = "TaxiwayParking";
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

    public static final String ID1 = "3D";
    public static final String ID2 = "00";
}
