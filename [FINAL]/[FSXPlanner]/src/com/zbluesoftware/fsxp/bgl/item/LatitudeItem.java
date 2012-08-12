// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LatitudeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class LatitudeItem extends Item
{

    public LatitudeItem()
    {
        this("latitude");
    }

    public LatitudeItem(String name)
    {
        this.name = name;
        dataType = "DWord";
        offset = 0;
        length = 4;
        hexData = null;
        decodedData = null;
        decodedType = 3;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        double tempLat = ((double)tempData * 180D) / 536870912D;
        double latitude = 0.0D;
        if(tempLat > 90D)
            latitude = -((tempLat - 180D) + 90D);
        else
            latitude = 90D - tempLat;
        decodedData = new Double(latitude);
    }
}
