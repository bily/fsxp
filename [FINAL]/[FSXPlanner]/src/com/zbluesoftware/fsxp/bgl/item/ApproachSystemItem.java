// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachSystemItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ApproachSystemItem extends Item
{

    public ApproachSystemItem()
    {
        this("approachSystem");
    }

    public ApproachSystemItem(String name)
    {
        this.name = name;
        dataType = "Byte";
        offset = 0;
        length = 1;
        hexData = null;
        decodedData = null;
        decodedType = 3;
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

        int dataInt = Long.valueOf(binary.substring(3, 8), 2).intValue();
        if(dataInt >= 0 && dataInt <= 14)
            decodedData = types[dataInt];
    }

    public static String types[];

    static 
    {
        types = new String[15];
        types[0] = "NONE";
        types[1] = "ODALS";
        types[2] = "MALSF";
        types[3] = "MALSR";
        types[4] = "SSALF";
        types[5] = "SSALR";
        types[6] = "ALSF1";
        types[7] = "ALSF2";
        types[8] = "RAIL";
        types[9] = "CALVERT";
        types[10] = "CALVERT2";
        types[11] = "MALS";
        types[12] = "SALS";
        types[13] = "NONE";
        types[14] = "SSALS";
    }
}
