// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoundaryFenceObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class BoundaryFenceObject extends BaseObject
{

    public BoundaryFenceObject()
    {
        this(0, 0);
    }

    public BoundaryFenceObject(int offset, int length)
    {
        name = "BoundaryFence";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new PointCountItem("vertexCount"), true);
        addItem(new GUIDItem("instanceId"), true);
        addItem(new GUIDItem("profile"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "39";
    public static final String ID2 = "00";
}
