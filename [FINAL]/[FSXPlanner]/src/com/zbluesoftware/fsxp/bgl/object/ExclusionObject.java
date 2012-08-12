// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExclusionObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ExclusionObject extends BaseObject
{

    public ExclusionObject()
    {
        this(0, 0);
    }

    public ExclusionObject(int offset, int length)
    {
        name = "ExclusionRectangle";
        this.offset = offset;
        this.length = length;
        addItem(new ExclusionTypeItem("excludeAll", -1), true);
        addItem(new ExclusionTypeItem("beaconObjects", 4), true);
        addItem(new ExclusionTypeItem("effectObjects", 5), true);
        addItem(new ExclusionTypeItem("genericBuildingObjects", 6), true);
        addItem(new ExclusionTypeItem("libraryObjects", 7), true);
        addItem(new ExclusionTypeItem("taxiwaySignObjects", 8), true);
        addItem(new ExclusionTypeItem("triggerObjects", 9), true);
        addItem(new ExclusionTypeItem("windsockObjects", 10), true);
        addItem(new ExclusionTypeItem("extrusionBridges", 11), true);
        addItem(new SizeItem("size", "Word", 2), true);
        addItem(new LongitudeItem("topLeftLon"), true);
        addItem(new LatitudeItem("topLeftLat"), true);
        addItem(new LongitudeItem("bottomRightLon"), true);
        addItem(new LatitudeItem("bottomRightLat"), true);
    }

    public String toString()
    {
        return name;
    }
}
