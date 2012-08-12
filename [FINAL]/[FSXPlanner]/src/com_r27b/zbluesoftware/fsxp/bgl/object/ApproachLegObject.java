// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachLegObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ApproachLegObject extends BaseObject
{

    public ApproachLegObject()
    {
        this(0, 0);
    }

    public ApproachLegObject(int offset, int length)
    {
        name = "ApproachLeg";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new PointCountItem("legCount"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "2D";
    public static final String ID2 = "00";
}
