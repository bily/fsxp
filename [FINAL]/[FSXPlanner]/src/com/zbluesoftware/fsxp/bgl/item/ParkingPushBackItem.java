// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkingPushBackItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ParkingPushBackItem extends Item
{

    public ParkingPushBackItem()
    {
        this("pushBack");
    }

    public ParkingPushBackItem(String name)
    {
        this.name = name;
        dataType = "DWord";
        offset = 0;
        length = 4;
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
        for(int i = binary.length(); i < 32; i++)
            binary.insert(0, "0");

        int dataInt = Long.valueOf(binary.substring(24, 26), 2).intValue();
        switch(dataInt)
        {
        case 0: // '\0'
            decodedData = "NONE";
            break;

        case 1: // '\001'
            decodedData = "LEFT";
            break;

        case 2: // '\002'
            decodedData = "RIGHT";
            break;

        case 3: // '\003'
            decodedData = "BOTH";
            break;
        }
    }
}
