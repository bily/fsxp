// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TowerAltitudeItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class TowerAltitudeItem extends Item
{

    public TowerAltitudeItem()
    {
        name = "towerAltitude";
        dataType = "DWord";
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

        String tempData = (new StringBuilder()).append("").append(Long.valueOf(buffer.toString(), 16)).toString();
        Float whole = new Float(0.0F);
        if(tempData.length() > 3)
            whole = Float.valueOf(tempData.substring(0, tempData.length() - 3));
        Float decimal = Float.valueOf(tempData.substring(tempData.length() - Math.min(3, tempData.length())));
        decodedData = new Float(whole.floatValue() + decimal.floatValue() / 1000F);
    }
}
