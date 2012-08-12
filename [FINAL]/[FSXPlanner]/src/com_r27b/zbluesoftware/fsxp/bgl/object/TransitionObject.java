// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransitionObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TransitionObject extends BaseObject
{

    public TransitionObject()
    {
        this(0, 0);
    }

    public TransitionObject(int offset, int length)
    {
        name = "Transition";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new TransitionTypeItem(), true);
        PointCountItem legCount = new PointCountItem("legCount");
        legCount.setDataType("Byte");
        legCount.setLength(1);
        addItem(legCount, true);
        addItem(new ApproachFixTypeItem(), true);
        addItem(new ApproachFixIdentItem("fixIdent", false, 0, 27), true);
        addItem(new ApproachFixIdentItem("fixRegion", false, 21, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 21), true);
        addItem(new HeadingItem("altitude"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "2C";
    public static final String ID2 = "00";
}
