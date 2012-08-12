// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPathObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwayPathObject extends BaseObject
{

    public TaxiwayPathObject()
    {
        this(0, 0);
    }

    public TaxiwayPathObject(int offset, int length)
    {
        name = "TaxiwayPath";
        this.offset = offset;
        this.length = length;
        addItem(new TWPathStartItem(), true);
        addItem(new TWPathEndItem(), true);
        addItem(new TWPathRunwayDesigItem(), true);
        addItem(new TWPathTypeItem(), true);
        addItem(new TWPathRunwayNumItem(), true);
        addItem(new TWPathNameItem(), true);
        addItem(new TWPathOptionItem("centerLine", 0, -1), true);
        addItem(new TWPathOptionItem("centerLineLighted", 1, -1), true);
        addItem(new TWPathOptionItem("leftEdge", 2, 3), true);
        addItem(new TWPathOptionItem("leftEdgeLighted", 4, -1), true);
        addItem(new TWPathOptionItem("rightEdge", 5, 6), true);
        addItem(new TWPathOptionItem("rightEdgeLighted", 7, -1), true);
        SurfaceItem surfaceItem = new SurfaceItem();
        surfaceItem.setDataType("Byte");
        surfaceItem.setLength(1);
        addItem(surfaceItem, true);
        addItem(new WidthItem(), true);
        addItem(new WeightItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
