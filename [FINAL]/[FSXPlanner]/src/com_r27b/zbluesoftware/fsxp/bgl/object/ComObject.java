// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ComObject extends BaseObject
{

    public ComObject()
    {
        this(0, 0);
    }

    public ComObject(int offset, int length)
    {
        name = "Com";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new ComTypeItem(), true);
        addItem(new ComFrequencyItem(), true);
        addItem(new ComNameItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "12";
    public static final String ID2 = "00";
}
