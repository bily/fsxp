// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DMEArcObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class DMEArcObject extends BaseObject
{

    public DMEArcObject()
    {
        this(0, 0);
    }

    public DMEArcObject(int offset, int length)
    {
        name = "DMEArc";
        this.offset = offset;
        this.length = length;
        addItem(new IcaoItem("dmeIdent", true), true);
        addItem(new ApproachFixIdentItem("dmeRegion", false, 21, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 21), true);
        addItem(new RadialItem(), true);
        addItem(new HeadingItem("distance"), true);
    }

    public String toString()
    {
        return name;
    }
}
