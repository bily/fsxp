// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachFixTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ApproachFixTypeItem extends Item
{

    public ApproachFixTypeItem()
    {
        this("fixType");
    }

    public ApproachFixTypeItem(String name)
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

        long tempData = Long.valueOf(buffer.toString(), 16).longValue();
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < 32; i++)
            binary.insert(0, "0");

        int dataInt = Long.valueOf(binary.substring(27, 32), 2).intValue();
        if(dataInt >= 0 && dataInt <= 9)
            decodedData = types[dataInt];
    }

    public static String types[];

    static 
    {
        types = new String[10];
        types[0] = "";
        types[1] = "";
        types[2] = "VOR";
        types[3] = "NDB";
        types[4] = "TERMINAL_NDB";
        types[5] = "WAYPOINT";
        types[6] = "TERMINAL_WAYPOINT";
        types[7] = "";
        types[8] = "LOCALIZER";
        types[9] = "RUNWAY";
    }
}
