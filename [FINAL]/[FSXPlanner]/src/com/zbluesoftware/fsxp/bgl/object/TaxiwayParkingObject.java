// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayParkingObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TaxiwayParkingObject extends BaseObject
{

    public TaxiwayParkingObject()
    {
        this(0, 0);
    }

    public TaxiwayParkingObject(int offset, int length)
    {
        name = "TaxiwayParking";
        this.offset = offset;
        this.length = length;
        addItem(new ParkingNameItem(), true);
        addItem(new ParkingTypeItem(), true);
        addItem(new ParkingNumberItem(), true);
        addItem(new ParkingPushBackItem(), true);
        addItem(new ParkingACItem(), true);
        addItem(new ParkingRadiusItem(), true);
        addItem(new HeadingItem(), true);
        addItem(new ParkingOffsetItem("teeOffset1"), true);
        addItem(new ParkingOffsetItem("teeOffset2"), true);
        addItem(new ParkingOffsetItem("teeOffset3"), true);
        addItem(new ParkingOffsetItem("teeOffset4"), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new ParkingCodesItem(), true);
    }

    public String toString()
    {
        return name;
    }
}
