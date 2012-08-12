// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayDetailObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class RunwayDetailObject extends BaseObject
{

    public RunwayDetailObject()
    {
        this("details", 0, 0);
    }

    public RunwayDetailObject(String name, int offset, int length)
    {
        this.name = name;
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new SurfaceItem(), true);
        addItem(new LengthItem(), true);
        addItem(new WidthItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String POFFSET_ID1 = "05";
    public static final String POFFSET_ID2 = "00";
    public static final String SOFFSET_ID1 = "06";
    public static final String SOFFSET_ID2 = "00";
    public static final String PBLAST_ID1 = "07";
    public static final String PBLAST_ID2 = "00";
    public static final String SBLAST_ID1 = "08";
    public static final String SBLAST_ID2 = "00";
    public static final String POVERRUN_ID1 = "09";
    public static final String POVERRUN_ID2 = "00";
    public static final String SOVERRUN_ID1 = "0A";
    public static final String SOVERRUN_ID2 = "00";
}
