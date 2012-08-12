// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiNameObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.TaxiNameItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiNameObject extends BaseObject
{

    public TaxiNameObject()
    {
        this(0, 0);
    }

    public TaxiNameObject(int offset, int length)
    {
        name = "TaxiName";
        this.offset = offset;
        this.length = length;
        addItem(new TaxiNameItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
