// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TWPathTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class TWPathTypeItem extends Item
{

    public TWPathTypeItem()
    {
        this("type");
    }

    public TWPathTypeItem(String name)
    {
        this.name = name;
        dataType = "Byte";
        offset = 0;
        length = 1;
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
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < 8; i++)
            binary.insert(0, "0");

        int dataInt = Long.valueOf(binary.substring(4, 8), 2).intValue();
        switch(dataInt)
        {
        case 1: // '\001'
            decodedData = "TAXI";
            break;

        case 2: // '\002'
            decodedData = "RUNWAY";
            break;

        case 3: // '\003'
            decodedData = "PARKING";
            break;

        case 4: // '\004'
            decodedData = "PATH";
            break;

        case 5: // '\005'
            decodedData = "CLOSED";
            break;

        case 6: // '\006'
            decodedData = "VEHICLE";
            break;
        }
    }
}
