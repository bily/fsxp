// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteRunwayObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class DeleteRunwayObject extends BaseObject
{

    public DeleteRunwayObject()
    {
        this(0, 0);
    }

    public DeleteRunwayObject(int offset, int length)
    {
        name = "DeleteRunway";
        this.offset = offset;
        this.length = length;
        addItem(new SurfaceItem("surface", "Byte", 1), true);
        addItem(new RunwayNumberItem("primaryRunway"), true);
        addItem(new RunwayNumberItem("secondaryRunway"), true);
        addItem(new RunwayDesigItem("primaryDesignator", 0, 3), true);
        addItem(new RunwayDesigItem("secondaryDesignator", 4, 7), true);
    }

    public String toString()
    {
        return name;
    }
}
