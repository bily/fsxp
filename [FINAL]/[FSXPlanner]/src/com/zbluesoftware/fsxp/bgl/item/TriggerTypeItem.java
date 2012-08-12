// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TriggerTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class TriggerTypeItem extends Item
{

    public TriggerTypeItem()
    {
        this("type");
    }

    public TriggerTypeItem(String name)
    {
        this.name = name;
        dataType = "Word";
        offset = 0;
        length = 2;
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
        if(tempData == 0)
            decodedData = "REFUEL_REPAIR";
        else
            decodedData = "WEATHER";
    }
}
