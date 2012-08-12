// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OptionItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class OptionItem extends Item
{

    public OptionItem()
    {
        this("option", 4);
    }

    public OptionItem(String name, int bit)
    {
        this(name, bit, "Word", 2);
    }

    public OptionItem(String name, int bit, String dataType, int length)
    {
        this(name, bit, dataType, length, false);
    }

    public OptionItem(String name, int bit, String dataType, int length, boolean flip)
    {
        this.name = name;
        this.bit = bit;
        this.dataType = dataType;
        this.length = length;
        this.flip = flip;
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
        int testValue = (int)Math.pow(2D, bit);
        if((tempData & testValue) > 0)
            decodedData = flip ? "FALSE" : "TRUE";
        else
            decodedData = flip ? "TRUE" : "FALSE";
    }

    private int bit;
    private boolean flip;
}
