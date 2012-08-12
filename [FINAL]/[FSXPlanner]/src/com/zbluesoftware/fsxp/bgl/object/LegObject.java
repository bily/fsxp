// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class LegObject extends BaseObject
{

    public LegObject()
    {
        this(0, 0);
    }

    public LegObject(int offset, int length)
    {
        name = "Leg";
        this.offset = offset;
        this.length = length;
        addItem(new LegIDItem(), true);
        addItem(new AltDescriptorItem(), true);
        addItem(new OptionItem("turnLeft", 0), true);
        addItem(new OptionItem("turnRight", 1), true);
        addItem(new CourseTypeItem(), true);
        addItem(new DistanceTimeItem(), true);
        addItem(new OptionItem("flyOver", 10), true);
        addItem(new ApproachFixIdentItem("fixIdent", false, 0, 27), true);
        addItem(new ApproachFixTypeItem(), true);
        addItem(new ApproachFixIdentItem("fixRegion", false, 22, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 22), true);
        addItem(new ApproachFixIdentItem("recommendedIdent", false, 0, 27), true);
        addItem(new ApproachFixTypeItem("recommendedType"), true);
        addItem(new ApproachFixIdentItem("recommendedRegion", false, 21, 32), true);
        addItem(new ApproachFixIdentItem("recommendedIcao", false, 0, 21), true);
        addItem(new HeadingItem("theta"), true);
        addItem(new HeadingItem("rho"), true);
        addItem(new HeadingItem("course"), true);
        addItem(new HeadingItem("distanceTimeMeasure"), true);
        addItem(new LegAltitudeItem("altitude1"), true);
        addItem(new LegAltitudeItem("altitude2"), true);
    }

    public String toString()
    {
        return name;
    }
}
