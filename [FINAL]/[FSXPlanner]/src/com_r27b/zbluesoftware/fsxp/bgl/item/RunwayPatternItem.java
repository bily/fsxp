// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayPatternItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class RunwayPatternItem extends Item
{

    public RunwayPatternItem()
    {
        this("runwayPattern", 0, false);
    }

    public RunwayPatternItem(String name, int bit, boolean leftRight)
    {
        this.name = name;
        this.bit = bit;
        this.leftRight = leftRight;
        dataType = "Byte";
        offset = 0;
        length = 1;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        int tempData = Long.valueOf(tempHex, 16).intValue();
        int testValue = (int)Math.pow(2D, bit);
        if((tempData & testValue) == 0)
        {
            if(leftRight)
                decodedData = "LEFT";
            else
                decodedData = "TRUE";
        } else
        if(leftRight)
            decodedData = "RIGHT";
        else
            decodedData = "FALSE";
    }

    private int bit;
    private boolean leftRight;
}
