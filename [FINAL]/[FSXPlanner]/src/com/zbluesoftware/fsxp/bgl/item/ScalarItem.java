// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScalarItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ScalarItem extends Item
{

    public ScalarItem()
    {
        this("trafficScalar");
    }

    public ScalarItem(String name)
    {
        this(name, "Byte", 1);
    }

    public ScalarItem(String name, String dataType, int length)
    {
        this.name = name;
        this.dataType = dataType;
        this.length = length;
        offset = 0;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        long tempData = Long.valueOf(hexData, 16).longValue();
        double scalar = Double.longBitsToDouble(tempData);
        decodedData = new Double(scalar * 65536D);
    }
}
