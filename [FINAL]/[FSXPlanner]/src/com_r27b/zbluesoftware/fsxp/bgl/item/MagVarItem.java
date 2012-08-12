// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MagVarItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class MagVarItem extends Item
{

    public MagVarItem()
    {
        name = "magVar";
        dataType = "Float";
        offset = 0;
        length = 4;
        hexData = null;
        decodedData = null;
        decodedType = 2;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        int tempData = Long.valueOf(buffer.toString(), 16).intValue();
        float magVar = Float.intBitsToFloat(tempData);
        if(magVar > 180F)
            magVar -= 360F;
        decodedData = new Float(magVar);
    }
}
