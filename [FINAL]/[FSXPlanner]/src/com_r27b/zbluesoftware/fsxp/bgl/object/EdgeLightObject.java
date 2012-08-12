// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EdgeLightObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.PointCountItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class EdgeLightObject extends BaseObject
{

    public EdgeLightObject()
    {
        this(0, 0);
    }

    public EdgeLightObject(int offset, int length)
    {
        name = "EdgeLights";
        this.offset = offset;
        this.length = length;
        addItem(new PointCountItem("startIndex"), true);
        addItem(new PointCountItem("endIndex"), true);
    }

    public String toString()
    {
        return name;
    }
}
