// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComFrequencyItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ComFrequencyItem extends Item
{

    public ComFrequencyItem()
    {
        this("comFrequency");
    }

    public ComFrequencyItem(String name)
    {
        this(name, 0, 31);
    }

    public ComFrequencyItem(String name, int startBit, int endBit)
    {
        this(name, startBit, endBit, 1000000F);
    }

    public ComFrequencyItem(String name, int startBit, int endBit, float divisor)
    {
        this.name = name;
        this.startBit = startBit;
        this.endBit = endBit;
        this.divisor = divisor;
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

        Float whole = new Float(Long.valueOf(binary.substring(31 - endBit, 32 - startBit), 2).longValue());
        decodedData = new Float(whole.floatValue() / divisor);
    }

    private int startBit;
    private int endBit;
    private float divisor;
}
