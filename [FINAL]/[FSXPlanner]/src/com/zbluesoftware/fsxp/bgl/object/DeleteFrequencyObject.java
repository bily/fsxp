// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteFrequencyObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.ComFrequencyItem;
import com.zbluesoftware.fsxp.bgl.item.ComTypeItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class DeleteFrequencyObject extends BaseObject
{

    public DeleteFrequencyObject()
    {
        this(0, 0);
    }

    public DeleteFrequencyObject(int offset, int length)
    {
        name = "DeleteFrequency";
        this.offset = offset;
        this.length = length;
        addItem(new ComTypeItem("type", "DWord", 4, 28, 31), true);
        addItem(new ComFrequencyItem("frequency", 0, 27), true);
    }

    public String toString()
    {
        return name;
    }
}
