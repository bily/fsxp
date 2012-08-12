// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkingCodesItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ParkingCodesItem extends Item
{

    public ParkingCodesItem()
    {
        this("airlineCodes");
    }

    public ParkingCodesItem(String name)
    {
        this.name = name;
        dataType = "String";
        offset = 0;
        length = 0;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < tempHex.length(); i += 2)
        {
            if(!tempHex.substring(i, i + 2).equals("00"))
                buffer.append(Character.toString((char)Long.valueOf(tempHex.substring(i, i + 2), 16).intValue()));
            if((i + 2) % 8 == 0 && i < tempHex.length() - 2)
                buffer.append(",");
        }

        decodedData = buffer.toString();
    }
}
