// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SectionHeaderObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.SectionCountItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class SectionHeaderObject extends BaseObject
{

    public SectionHeaderObject(int offset)
    {
        this.offset = offset;
        name = "sectionHeader";
        length = 16;
        addItem(new SectionCountItem("id"), true);
        addItem(new SectionCountItem("numberRecords"), true);
        addItem(new SectionCountItem("offset"), true);
        addItem(new SectionCountItem("subsectionSize"), true);
    }

    public String toString()
    {
        return name;
    }
}
