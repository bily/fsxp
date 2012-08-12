// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WeatherTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class WeatherTypeItem extends Item
{

    public WeatherTypeItem()
    {
        this("type");
    }

    public WeatherTypeItem(String name)
    {
        this(name, "Word", 2);
    }

    public WeatherTypeItem(String name, String dataType, int length)
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
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        switch(tempData)
        {
        case 1: // '\001'
            decodedData = "RIDGE_LIFT";
            break;

        case 2: // '\002'
            decodedData = "UNIDIRECTIONAL_TURBULENCE";
            break;

        case 3: // '\003'
            decodedData = "DIRECTIONAL_TURBULENCE";
            break;

        case 4: // '\004'
            decodedData = "THERMAL";
            break;
        }
    }
}
