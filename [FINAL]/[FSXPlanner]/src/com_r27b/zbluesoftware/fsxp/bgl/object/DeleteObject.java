// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class DeleteObject extends BaseObject
{

    public DeleteObject()
    {
        this(0, 0);
    }

    public DeleteObject(int offset, int length)
    {
        name = "DeleteAirport";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new OptionItem("allApproaches", 0), true);
        addItem(new OptionItem("allApronLights", 1), true);
        addItem(new OptionItem("allAprons", 2), true);
        addItem(new OptionItem("allFrequencies", 3), true);
        addItem(new OptionItem("allHelipads", 4), true);
        addItem(new OptionItem("allRunways", 5), true);
        addItem(new OptionItem("allStarts", 6), true);
        addItem(new OptionItem("allTaxiways", 7), true);
        addItem(new OptionItem("allBlastFences", 8), true);
        addItem(new OptionItem("allBoundaryFences", 9), true);
        addItem(new OptionItem("allJetways", 10), true);
        addItem(new OptionItem("allControlTowers", 11), true);
        addItem(new PointCountItem("runwayCount"), true);
        addItem(new PointCountItem("startCount"), true);
        addItem(new PointCountItem("frequencyCount"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "33";
    public static final String ID2 = "00";
}
