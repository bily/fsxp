// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VORILSObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class VORILSObject extends BaseObject
{

    public VORILSObject()
    {
        this(0, 0);
    }

    public VORILSObject(int offset, int length)
    {
        name = "VORILSObject";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size"), true);
        addItem(new VORILSTypeItem(), true);
        addItem(new OptionItem("dmeOnly", 0, "Byte", 1, true), true);
        addItem(new OptionItem("backCourse", 2, "Byte", 1), true);
        addItem(new OptionItem("glideSlope", 3, "Byte", 1), true);
        addItem(new OptionItem("dme", 4, "Byte", 1), true);
        addItem(new OptionItem("navTrue", 5, "Byte", 1), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new ComFrequencyItem("frequency"), true);
        addItem(new HeadingItem("range"), true);
        addItem(new MagVarItem(), true);
        addItem(new IcaoItem(), true);
        addItem(new ApproachFixIdentItem("region", false, 21, 32), true);
        addItem(new ApproachFixIdentItem("airportIcao", false, 0, 21), true);
        addItem(new EndItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "13";
    public static final String ID2 = "00";
}
