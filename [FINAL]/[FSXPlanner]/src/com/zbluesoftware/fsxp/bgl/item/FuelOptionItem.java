// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FuelOptionItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class FuelOptionItem extends Item
{

    public FuelOptionItem()
    {
        this("fuelOption", 0, 1);
    }

    public FuelOptionItem(String name, int bit1, int bit2)
    {
        this.name = name;
        this.bit1 = bit1;
        this.bit2 = bit2;
        dataType = "DWord";
        length = 4;
        offset = 0;
        hexData = null;
        decodedData = null;
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
        if((tempData & testValue1) == 0 && (tempData & testValue2) == 0)
            decodedData = "NO";
        else
        if((tempData & testValue1) == 0 && (tempData & testValue2) > 0)
            decodedData = "UNKNOWN";
        else
        if((tempData & testValue1) > 0 && (tempData & testValue2) == 0)
            decodedData = "PRIOR_REQUEST";
        else
        if((tempData & testValue1) > 0 && (tempData & testValue2) > 0)
            decodedData = "YES";
    }

    private int bit1;
    private int bit2;
}
