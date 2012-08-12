// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageComplexityItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class ImageComplexityItem extends Item
{

    public ImageComplexityItem()
    {
        this("imageComplexity");
    }

    public ImageComplexityItem(String name)
    {
        this.name = name;
        dataType = "Word";
        offset = 0;
        length = 2;
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
        if(tempData >= 0 && tempData <= 5)
            decodedData = complexity[tempData];
    }

    public static String complexity[];

    static 
    {
        complexity = new String[38];
        complexity[0] = "VERY_SPARSE";
        complexity[1] = "SPARSE";
        complexity[2] = "NORMAL";
        complexity[3] = "DENSE";
        complexity[4] = "VERY_DENSE";
        complexity[5] = "EXTREMELY_DENSE";
    }
}
