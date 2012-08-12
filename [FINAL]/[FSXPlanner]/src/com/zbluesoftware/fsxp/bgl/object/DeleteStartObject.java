// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteStartObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class DeleteStartObject extends BaseObject
{

    public DeleteStartObject()
    {
        this(0, 0);
    }

    public DeleteStartObject(int offset, int length)
    {
        name = "DeleteStart";
        this.offset = offset;
        this.length = length;
        addItem(new RunwayNumberItem("runwayNumber"), true);
        addItem(new RunwayDesigItem("runwayDesignator"), true);
        addItem(new DeleteStartTypeItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
