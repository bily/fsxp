// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServicesObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.FuelOptionItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class ServicesObject extends BaseObject
{

    public ServicesObject()
    {
        this(0, 0);
    }

    public ServicesObject(int offset, int length)
    {
        name = "Services";
        this.offset = offset;
        this.length = length;
        addItem(new FuelOptionItem("type73", 0, 1), true);
        addItem(new FuelOptionItem("type87", 2, 3), true);
        addItem(new FuelOptionItem("type100", 4, 5), true);
        addItem(new FuelOptionItem("type130", 6, 7), true);
        addItem(new FuelOptionItem("type145", 8, 9), true);
        addItem(new FuelOptionItem("typeMOGAS", 10, 11), true);
        addItem(new FuelOptionItem("typeJET", 12, 13), true);
        addItem(new FuelOptionItem("typeJETA", 14, 15), true);
        addItem(new FuelOptionItem("typeJETA1", 16, 17), true);
        addItem(new FuelOptionItem("typeJETAP", 18, 19), true);
        addItem(new FuelOptionItem("typeJETB", 20, 21), true);
        addItem(new FuelOptionItem("typeJET4", 22, 23), true);
        addItem(new FuelOptionItem("typeJET5", 24, 25), true);
    }

    public String toString()
    {
        return name;
    }
}
