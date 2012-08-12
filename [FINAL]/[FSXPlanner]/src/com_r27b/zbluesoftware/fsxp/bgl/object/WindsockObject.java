// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WindsockObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class WindsockObject extends BaseObject
{

    public WindsockObject()
    {
        this(0, 0);
    }

    public WindsockObject(int offset, int length)
    {
        name = "WindsockObject";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem("size", "Word", 2), true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new PitchItem("pitch", "Word", 2), true);
        addItem(new BankItem("bank", "Word", 2), true);
        addItem(new HeadingItem("heading", "Word", 2), true);
        addItem(new ImageComplexityItem(), true);
        addItem(new GUIDItem("instanceId"), true);
        addItem(new WidthItem("poleHeight"), true);
        addItem(new WidthItem("sockLength"), true);
        addItem(new ColorItem("poleBlue"), true);
        addItem(new ColorItem("poleGreen"), true);
        addItem(new ColorItem("poleRed"), true);
        addItem(new ColorItem("poleAlpha"), true);
        addItem(new ColorItem("sockBlue"), true);
        addItem(new ColorItem("sockGreen"), true);
        addItem(new ColorItem("sockRed"), true);
        addItem(new ColorItem("sockAlpha"), true);
        addItem(new FlagItem("lighted"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "0C";
    public static final String ID2 = "00";
}
