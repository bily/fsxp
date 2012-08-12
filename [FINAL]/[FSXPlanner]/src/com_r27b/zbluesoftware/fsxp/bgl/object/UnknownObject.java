// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnknownObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.IDItem;
import com.zbluesoftware.fsxp.bgl.item.SizeItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class UnknownObject extends BaseObject
{

    public UnknownObject()
    {
        this(0, 0);
    }

    public UnknownObject(int offset, int length)
    {
        name = "Unknown";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "3B";
    public static final String ID2 = "00";
}
