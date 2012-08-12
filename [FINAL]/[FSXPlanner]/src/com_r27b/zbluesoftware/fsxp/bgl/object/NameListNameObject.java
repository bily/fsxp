// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameListNameObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.StringItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class NameListNameObject extends BaseObject
{

    public NameListNameObject()
    {
        this(0, 0);
    }

    public NameListNameObject(int offset, int length)
    {
        name = "Name";
        this.offset = offset;
        this.length = length;
        addItem(new StringItem("name"), true);
    }

    public String toString()
    {
        return name;
    }
}
