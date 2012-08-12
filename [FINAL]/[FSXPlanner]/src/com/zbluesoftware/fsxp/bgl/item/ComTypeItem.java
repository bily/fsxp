// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ComTypeItem extends Item
{

    public ComTypeItem()
    {
        this("comType");
    }

    public ComTypeItem(String name)
    {
        this(name, "Word", 2, 0, 15);
    }

    public ComTypeItem(String name, String dataType, int length, int startBit, int endBit)
    {
        this.name = name;
        this.dataType = dataType;
        this.length = length;
        this.startBit = startBit;
        this.endBit = endBit;
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
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < length * 8; i++)
            binary.insert(0, "0");

        tempData = Long.valueOf(binary.substring(length * 8 - 1 - endBit, length * 8 - startBit), 2).intValue();
        if(tempData >= 0 && tempData <= 15)
            decodedData = types[tempData];
    }

    private int startBit;
    private int endBit;
    private static String types[];

    static 
    {
        types = new String[16];
        types[0] = "";
        types[1] = "ATIS";
        types[2] = "MULTICOM";
        types[3] = "UNICOM";
        types[4] = "CTAF";
        types[5] = "GROUND";
        types[6] = "TOWER";
        types[7] = "CLEARANCE";
        types[8] = "APPROACH";
        types[9] = "DEPARTURE";
        types[10] = "CENTER";
        types[11] = "FSS";
        types[12] = "AWOS";
        types[13] = "ASOS";
        types[14] = "CLEARANCE_PRE_TAXI";
        types[15] = "REMOTE_CLEARANCE_DELIVERY";
    }
}
