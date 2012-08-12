// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayNumberItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class RunwayNumberItem extends Item
{

    public RunwayNumberItem()
    {
        this("runwayNumber");
    }

    public RunwayNumberItem(String name)
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
        if(tempData < 37)
        {
            decodedData = (new StringBuilder()).append("").append(tempData).toString();
            if(((String)decodedData).length() < 2)
                decodedData = (new StringBuilder()).append("0").append(decodedData).toString();
        } else
        {
            switch(tempData)
            {
            case 37: // '%'
                decodedData = "NORTH";
                return;

            case 38: // '&'
                decodedData = "NORTHEAST";
                return;

            case 39: // '\''
                decodedData = "EAST";
                return;

            case 40: // '('
                decodedData = "SOUTHEAST";
                return;

            case 41: // ')'
                decodedData = "SOUTH";
                return;

            case 42: // '*'
                decodedData = "SOUTHWEST";
                return;

            case 43: // '+'
                decodedData = "WEST";
                return;

            case 44: // ','
                decodedData = "NORTHWEST";
                return;
            }
        }
    }
}
