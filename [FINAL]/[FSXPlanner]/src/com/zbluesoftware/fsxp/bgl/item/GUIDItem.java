// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GUIDItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class GUIDItem extends Item
{

    public GUIDItem()
    {
        this("guid");
    }

    public GUIDItem(String name)
    {
        this.name = name;
        dataType = "Guide";
        offset = 0;
        length = 16;
        hexData = null;
        decodedData = null;
        decodedType = 1;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        buffer.append(tempHex.substring(6, 8));
        buffer.append(tempHex.substring(4, 6));
        buffer.append(tempHex.substring(2, 4));
        buffer.append(tempHex.substring(0, 2));
        buffer.append("-");
        buffer.append(tempHex.substring(10, 12));
        buffer.append(tempHex.substring(8, 10));
        buffer.append("-");
        buffer.append(tempHex.substring(14, 16));
        buffer.append(tempHex.substring(12, 14));
        buffer.append("-");
        buffer.append(tempHex.substring(16, 18));
        buffer.append(tempHex.substring(18, 20));
        buffer.append("-");
        buffer.append(tempHex.substring(20, 22));
        buffer.append(tempHex.substring(22, 24));
        buffer.append(tempHex.substring(24, 26));
        buffer.append(tempHex.substring(26, 28));
        buffer.append(tempHex.substring(28, 30));
        buffer.append(tempHex.substring(30, 32));
        decodedData = buffer.toString().toLowerCase();
    }
}
