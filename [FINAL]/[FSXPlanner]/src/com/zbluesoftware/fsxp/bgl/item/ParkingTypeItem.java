// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParkingTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ParkingTypeItem extends Item
{

    public ParkingTypeItem()
    {
        this("parkingType");
    }

    public ParkingTypeItem(String name)
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

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        StringBuffer binary = new StringBuffer(Long.toBinaryString(tempData));
        for(int i = binary.length(); i < 32; i++)
            binary.insert(0, "0");

        int dataInt = Long.valueOf(binary.substring(20, 24), 2).intValue();
        if(dataInt >= 0 && dataInt <= 13)
            decodedData = types[dataInt];
    }

    public static String types[];

    static 
    {
        types = new String[14];
        types[0] = "NONE";
        types[1] = "RAMP_GA";
        types[2] = "RAMP_GA_SMALL";
        types[3] = "RAMP_GA_MEDIUM";
        types[4] = "RAMP_GA_LARGE";
        types[5] = "RAMP_CARGO";
        types[6] = "RAMP_MIL_CARGO";
        types[7] = "RAMP_MIL_COMBAT";
        types[8] = "GATE_SMALL";
        types[9] = "GATE_MEDIUM";
        types[10] = "GATE_HEAVY";
        types[11] = "DOCK_GA";
        types[12] = "FUEL";
        types[13] = "VEHICLE";
    }
}
