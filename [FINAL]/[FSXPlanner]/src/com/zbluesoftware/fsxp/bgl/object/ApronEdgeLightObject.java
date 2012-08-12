// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronEdgeLightObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ApronEdgeLightObject extends BaseObject
{

    public ApronEdgeLightObject()
    {
        this(0, 0);
    }

    public ApronEdgeLightObject(int offset, int length)
    {
        name = "ApronEdgeLights";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new PointCountItem("vertexCount"), true);
        addItem(new PointCountItem("edgeCount"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "31";
    public static final String ID2 = "00";
}
