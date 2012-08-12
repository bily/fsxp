// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransitionLegObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TransitionLegObject extends BaseObject
{

    public TransitionLegObject()
    {
        this(0, 0);
    }

    public TransitionLegObject(int offset, int length)
    {
        name = "TransitionLeg";
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

    public static final String ID1 = "2F";
    public static final String ID2 = "00";
}
