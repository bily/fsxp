// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IDItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class IDItem extends Item
{

    public IDItem()
    {
        this("id");
    }

    public IDItem(String name)
    {
        this.name = name;
        dataType = "Word";
        offset = 0;
        length = 2;
        hexData = null;
        decodedData = null;
        decodedType = 1;
    }
}
