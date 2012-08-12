// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EndItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class EndItem extends Item
{

    public EndItem()
    {
        this("end");
    }

    public EndItem(String name)
    {
        this.name = name;
        bit = bit;
        dataType = "Word";
        offset = 0;
        length = 2;
        hexData = "---";
        decodedData = "---";
    }

    public void setDecodedData()
    {
        decodedData = hexData;
    }

    private int bit;
}
