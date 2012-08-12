// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayLightItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class RunwayLightItem extends Item
{

    public RunwayLightItem()
    {
        this("runwayLight", 0, 0);
    }

    public RunwayLightItem(String name, int bit1, int bit2)
    {
        this.name = name;
        this.bit1 = bit1;
        this.bit2 = bit2;
        dataType = "Byte";
        offset = 0;
        length = 1;
        hexData = null;
        decodedData = null;
        decodedType = 4;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        int testValue1 = (int)Math.pow(2D, bit1);
        int testValue2 = (int)Math.pow(2D, bit2);
        if(bit2 > 0)
        {
            if((tempData & testValue1) == 0 && (tempData & testValue2) == 0)
                decodedData = "NONE";
            else
            if((tempData & testValue1) == 0 && (tempData & testValue2) > 0)
                decodedData = "LOW";
            else
            if((tempData & testValue1) > 0 && (tempData & testValue2) == 0)
                decodedData = "MEDIUM";
            else
            if((tempData & testValue1) > 0 && (tempData & testValue2) > 0)
                decodedData = "HIGH";
        } else
        if((tempData & testValue1) > 0)
            decodedData = "TRUE";
        else
            decodedData = "FALSE";
    }

    private int bit1;
    private int bit2;
}
