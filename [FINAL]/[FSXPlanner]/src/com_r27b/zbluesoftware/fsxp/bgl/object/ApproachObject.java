// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ApproachObject extends BaseObject
{

    public ApproachObject()
    {
        this(0, 0);
    }

    public ApproachObject(int offset, int length)
    {
        name = "Approach";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new SuffixItem(), true);
        addItem(new RunwayNumberItem(), true);
        addItem(new ApproachTypeItem(), true);
        addItem(new ApproachRunwayDesigItem(), true);
        addItem(new GPSOverlayItem("gpsOverlay", 7), true);
        PointCountItem transitionCount = new PointCountItem("transitionCount");
        transitionCount.setDataType("Byte");
        transitionCount.setLength(1);
        addItem(transitionCount, true);
        PointCountItem approachCount = new PointCountItem("approachLegCount");
        approachCount.setDataType("Byte");
        approachCount.setLength(1);
        addItem(approachCount, true);
        PointCountItem missedCount = new PointCountItem("missedApproachLegCount");
        missedCount.setDataType("Byte");
        missedCount.setLength(1);
        addItem(missedCount, true);
        addItem(new ApproachFixTypeItem(), true);
        addItem(new ApproachFixIdentItem("fixIdent", false, 0, 27), true);
        addItem(new ApproachFixIdentItem("fixRegion", false, 22, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 22), true);
        addItem(new HeadingItem("altitude"), true);
        addItem(new HeadingItem(), true);
        addItem(new HeadingItem("missedAltitude"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "24";
    public static final String ID2 = "00";
}
