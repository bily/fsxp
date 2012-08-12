// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TWSizeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class TWSizeItem extends Item
{

    public TWSizeItem()
    {
        this("size");
    }

    public TWSizeItem(String name)
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
        if(tempData >= 0 && tempData <= 5)
            decodedData = size[tempData];
    }

    public static String size[];

    static 
    {
        size = new String[6];
        size[0] = "";
        size[1] = "SIZE1";
        size[2] = "SIZE2";
        size[3] = "SIZE3";
        size[4] = "SIZE4";
        size[5] = "SIZE5";
    }
}
