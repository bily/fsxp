// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegIDItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class LegIDItem extends Item
{

    public LegIDItem()
    {
        this("legID");
    }

    public LegIDItem(String name)
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

        int dataInt = Long.valueOf(buffer.toString(), 16).intValue();
        if(dataInt >= 0 && dataInt <= 23)
            decodedData = types[dataInt];
    }

    public static String types[];

    static 
    {
        types = new String[24];
        types[0] = "";
        types[1] = "AF";
        types[2] = "CA";
        types[3] = "CD";
        types[4] = "CF";
        types[5] = "CI";
        types[6] = "CR";
        types[7] = "DF";
        types[8] = "FA";
        types[9] = "FC";
        types[10] = "FD";
        types[11] = "FM";
        types[12] = "HA";
        types[13] = "HF";
        types[14] = "HM";
        types[15] = "IF";
        types[16] = "PI";
        types[17] = "RF";
        types[18] = "TF";
        types[19] = "VA";
        types[20] = "VD";
        types[21] = "VI";
        types[22] = "VM";
        types[23] = "VR";
    }
}
