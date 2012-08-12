// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SurfaceItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class SurfaceItem extends Item
{

    public SurfaceItem()
    {
        this("surface");
    }

    public SurfaceItem(String name)
    {
        this(name, "Word", 2);
    }

    public SurfaceItem(String name, String dataType, int length)
    {
        this.name = name;
        this.dataType = dataType;
        this.length = length;
        offset = 0;
        hexData = null;
        decodedData = null;
    }

    public void setDecodedData()
    {
        String tempHex = hexData.replaceAll(" ", "");
        StringBuffer buffer = new StringBuffer();
        for(int i = tempHex.length() - 1; i >= 0; i -= 2)
            buffer.append(tempHex.charAt(i - 1)).append(tempHex.charAt(i));

        tempHex = buffer.toString().toLowerCase();
        if(tempHex.length() == 4)
            tempHex = tempHex.substring(2, 4);
        if(tempHex.equals("00"))
            decodedData = "CONCRETE";
        else
        if(tempHex.equals("01"))
            decodedData = "GRASS";
        else
        if(tempHex.equals("02"))
            decodedData = "WATER";
        else
        if(tempHex.equals("04"))
            decodedData = "ASPHALT";
        else
        if(tempHex.equals("07"))
            decodedData = "CLAY";
        else
        if(tempHex.equals("08"))
            decodedData = "SNOW";
        else
        if(tempHex.equals("09"))
            decodedData = "ICE";
        else
        if(tempHex.equals("0c"))
            decodedData = "DIRT";
        else
        if(tempHex.equals("0d"))
            decodedData = "CORAL";
        else
        if(tempHex.equals("0e"))
            decodedData = "GRAVEL";
        else
        if(tempHex.equals("0f"))
            decodedData = "OIL_TREATED";
        else
        if(tempHex.equals("10"))
            decodedData = "STEEL_MATS";
        else
        if(tempHex.equals("11"))
            decodedData = "BITUMINOUS";
        else
        if(tempHex.equals("12"))
            decodedData = "BRICK";
        else
        if(tempHex.equals("13"))
            decodedData = "MACADAM";
        else
        if(tempHex.equals("14"))
            decodedData = "PLANKS";
        else
        if(tempHex.equals("15"))
            decodedData = "SAND";
        else
        if(tempHex.equals("16"))
            decodedData = "SHALE";
        else
        if(tempHex.equals("17"))
            decodedData = "TARMAC";
        else
        if(tempHex.equals("fe"))
            decodedData = "UNKNOWN";
    }
}
