// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApronObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ApronObject extends BaseObject
{

    public ApronObject()
    {
        this(0, 0);
    }

    public ApronObject(int offset, int length)
    {
        name = "Apron";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem("id1"), true);
        addItem(new SizeItem("size1"), true);
        SurfaceItem surfaceItem = new SurfaceItem("surface1");
        surfaceItem.setLength(1);
        addItem(surfaceItem, true);
        addItem(new PointCountItem("vertexCount1"), true);
        addItem(new IDItem("id2"), true);
        addItem(new SizeItem("size2"), true);
        SurfaceItem surfaceItem2 = new SurfaceItem("surface2");
        surfaceItem2.setLength(1);
        addItem(surfaceItem2, true);
        addItem(new ApronOptionItem("drawSurface", 0), true);
        addItem(new ApronOptionItem("drawDetail", 0), true);
        addItem(new PointCountItem("vertexCount2"), true);
        addItem(new PointCountItem("triangleCount"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "37";
    public static final String ID2 = "00";
}
