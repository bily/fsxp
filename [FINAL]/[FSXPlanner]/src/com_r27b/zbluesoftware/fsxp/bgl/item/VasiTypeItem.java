// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VasiTypeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class VasiTypeItem extends Item
{

    public VasiTypeItem()
    {
        this("type");
    }

    public VasiTypeItem(String name)
    {
        this.name = name;
        dataType = "Word";
        offset = 0;
        length = 2;
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

        tempHex = buffer.toString().toUpperCase();
        if(tempHex.length() == 4)
            tempHex = tempHex.substring(2, 4);
        if(tempHex.equals("01"))
            decodedData = "VASI21";
        else
        if(tempHex.equals("02"))
            decodedData = "VASI31";
        else
        if(tempHex.equals("03"))
            decodedData = "VASI22";
        else
        if(tempHex.equals("04"))
            decodedData = "VASI32";
        else
        if(tempHex.equals("05"))
            decodedData = "VASI23";
        else
        if(tempHex.equals("06"))
            decodedData = "VASI33";
        else
        if(tempHex.equals("07"))
            decodedData = "PAPI2";
        else
        if(tempHex.equals("08"))
            decodedData = "PAPI4";
        else
        if(tempHex.equals("09"))
            decodedData = "TRICOLOR";
        else
        if(tempHex.equals("0A"))
            decodedData = "PVASI";
        else
        if(tempHex.equals("0B"))
            decodedData = "TVASI";
        else
        if(tempHex.equals("0C"))
            decodedData = "BALL";
        else
        if(tempHex.equals("0D"))
            decodedData = "APAP/PANELS";
    }
}
