// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AltitudeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class AltitudeItem extends Item
{

    public AltitudeItem()
    {
        this("altitude");
    }

    public AltitudeItem(String name)
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

        long tempLong = Long.valueOf(buffer.toString(), 16).longValue();
        if(tempLong > 0xb5b8b2c8L)
            tempLong -= 0x100000000L;
        String tempData = (new StringBuilder()).append("").append(tempLong).toString();
        Float whole = new Float(0.0F);
        if(tempData.length() > 3 && !tempData.substring(0, tempData.length() - 3).equals("-"))
            try
            {
                whole = Float.valueOf(tempData.substring(0, tempData.length() - 3));
            }
            catch(NumberFormatException nfe)
            {
                nfe.printStackTrace();
            }
        Float decimal = Float.valueOf(tempData.substring(tempData.length() - Math.min(3, tempData.length())));
        if(tempLong >= 0L)
            decodedData = new Float(whole.floatValue() + decimal.floatValue() / 1000F);
        else
            decodedData = new Float(whole.floatValue() - decimal.floatValue() / 1000F);
    }
}
