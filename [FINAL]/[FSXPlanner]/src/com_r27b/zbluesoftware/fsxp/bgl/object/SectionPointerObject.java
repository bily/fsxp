// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SectionPointerObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class SectionPointerObject extends BaseObject
{

    public SectionPointerObject(String name, int offset)
    {
        this.name = name;
        this.offset = offset;
        addItem(new IDItem(), true);
        addItem(new SectionCountItem("subsectionPointers"), true);
        addItem(new SectionCountItem("offset"), true);
        addItem(new SizeItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
