// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameListObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.SizeItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class NameListObject extends BaseObject
{

    public NameListObject(String name)
    {
        this(name, 0, 0);
    }

    public NameListObject(String name, int offset, int length)
    {
        this.name = name;
        this.offset = offset;
        this.length = length;
        addItem(new SizeItem("index", "DWord", 4), true);
    }

    public String toString()
    {
        return name;
    }
}
