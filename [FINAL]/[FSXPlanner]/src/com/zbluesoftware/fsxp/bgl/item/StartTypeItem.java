// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class StartTypeItem extends Item
{

    public StartTypeItem()
    {
        this("startType");
    }

    public StartTypeItem(String name)
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
        tempData &= 0xf0;
        switch(tempData)
        {
        case 16: // '\020'
            decodedData = "RUNWAY";
            return;

        case 32: // ' '
            decodedData = "WATER";
            return;

        case 48: // '0'
            decodedData = "HELIPAD";
            return;
        }
        decodedData = " ";
    }
}
