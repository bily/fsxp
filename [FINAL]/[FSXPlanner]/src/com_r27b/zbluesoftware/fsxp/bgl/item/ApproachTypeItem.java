// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ApproachTypeItem extends Item
{

    public ApproachTypeItem()
    {
        this("type");
    }

    public ApproachTypeItem(String name)
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
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < 8; i++)
            binary.insert(0, "0");

        int dataInt = Long.valueOf(binary.substring(4, 8), 2).intValue();
        if(dataInt >= 0 && dataInt <= 12)
            decodedData = types[dataInt];
    }

    public static String types[];

    static 
    {
        types = new String[12];
        types[0] = "";
        types[1] = "GPS";
        types[2] = "VOR";
        types[3] = "NDB";
        types[4] = "ILS";
        types[5] = "LOCALIZER";
        types[6] = "SDF";
        types[7] = "LDA";
        types[8] = "VORDME";
        types[9] = "NDBDME";
        types[10] = "RNAV";
        types[11] = "LOCALIZER_BACKCOURSE";
    }
}
