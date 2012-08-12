// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachLightObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ApproachLightObject extends BaseObject
{

    public ApproachLightObject()
    {
        this("approachLights", 0, 0);
    }

    public ApproachLightObject(String name, int offset, int length)
    {
        this.name = name;
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new ApproachSystemItem(), true);
        addItem(new ALOptionItem("endLights", 5), true);
        addItem(new ALOptionItem("reil", 6), true);
        addItem(new ALOptionItem("touchdown", 7), true);
        PointCountItem countItem = new PointCountItem("strobes");
        countItem.setDataType("Byte");
        countItem.setLength(1);
        addItem(countItem, true);
    }

    public String toString()
    {
        return name;
    }

    public static final String P_ID1 = "0F";
    public static final String P_ID2 = "00";
    public static final String S_ID1 = "10";
    public static final String S_ID2 = "00";
}
