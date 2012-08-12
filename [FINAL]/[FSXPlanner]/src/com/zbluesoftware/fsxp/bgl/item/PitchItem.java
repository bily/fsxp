// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PitchItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class PitchItem extends Item
{

    public PitchItem()
    {
        this("pitch");
    }

    public PitchItem(String name)
    {
        this(name, "Float", 4);
    }

    public PitchItem(String name, String dataType, int length)
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
        if(length == 2)
        {
            decodedData = new Float(((float)tempData * 360F) / 65536F);
        } else
        {
            float pitch = Float.intBitsToFloat(tempData);
            decodedData = new Float(pitch);
        }
    }
}
