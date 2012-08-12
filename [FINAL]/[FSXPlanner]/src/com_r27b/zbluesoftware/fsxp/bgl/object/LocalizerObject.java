// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalizerObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class LocalizerObject extends BaseObject
{

    public LocalizerObject()
    {
        this(0, 0);
    }

    public LocalizerObject(int offset, int length)
    {
        name = "Localizer";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size"), true);
        addItem(new HeadingItem(), true);
        addItem(new WidthItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "14";
    public static final String ID2 = "00";
}
