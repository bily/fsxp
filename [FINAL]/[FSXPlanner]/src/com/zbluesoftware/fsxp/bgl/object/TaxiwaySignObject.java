// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwaySignObject extends BaseObject
{

    public TaxiwaySignObject()
    {
        this(0, 0);
    }

    public TaxiwaySignObject(int offset, int length)
    {
        name = "TaxiwaySign";
        this.offset = offset;
        this.length = length;
        addItem(new HeadingItem("lonOffset"), true);
        addItem(new HeadingItem("latOffset"), true);
        addItem(new HeadingItem("heading", "Word", 2), true);
        addItem(new TWSizeItem(), true);
        addItem(new TWJustificationItem(), true);
        addItem(new TWSignLabelItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "0E";
    public static final String ID2 = "00";
}
