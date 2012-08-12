// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayVasiObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class RunwayVasiObject extends BaseObject
{

    public RunwayVasiObject()
    {
        this("vasi", 0, 0);
    }

    public RunwayVasiObject(String name, int offset, int length)
    {
        this.name = name;
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new VasiTypeItem(), true);
        addItem(new BiasItem("biasX"), true);
        addItem(new BiasItem("biasZ"), true);
        addItem(new SpacingItem(), true);
        addItem(new PitchItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String PL_ID1 = "0B";
    public static final String PL_ID2 = "00";
    public static final String PR_ID1 = "0C";
    public static final String PR_ID2 = "00";
    public static final String SL_ID1 = "0D";
    public static final String SL_ID2 = "00";
    public static final String SR_ID1 = "0E";
    public static final String SR_ID2 = "00";
}
