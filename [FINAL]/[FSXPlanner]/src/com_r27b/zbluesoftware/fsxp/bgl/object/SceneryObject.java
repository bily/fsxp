// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SceneryObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class SceneryObject extends BaseObject
{

    public SceneryObject()
    {
        this(0, 0);
    }

    public SceneryObject(int offset, int length)
    {
        name = "SceneryObject";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        SizeItem sizeItem = new SizeItem();
        sizeItem.setDataType("Word");
        sizeItem.setLength(2);
        addItem(sizeItem, true);
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new AltitudeItem(), true);
        addItem(new AboveGLItem(), true);
        PitchItem pitchItem = new PitchItem();
        pitchItem.setDataType("Word");
        pitchItem.setLength(2);
        addItem(pitchItem, true);
        BankItem bankItem = new BankItem();
        bankItem.setDataType("Word");
        bankItem.setLength(2);
        addItem(bankItem, true);
        HeadingItem headingItem = new HeadingItem();
        headingItem.setDataType("Word");
        headingItem.setLength(2);
        addItem(headingItem, true);
        addItem(new ImageComplexityItem(), true);
        addItem(new GUIDItem("instanceId"), true);
        addItem(new GUIDItem("name"), true);
        addItem(new ScaleItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "0B";
    public static final String ID2 = "00";
}
