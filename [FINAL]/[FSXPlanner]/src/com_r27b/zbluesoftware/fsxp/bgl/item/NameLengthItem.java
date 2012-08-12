// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameLengthItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class NameLengthItem extends Item
{

    public NameLengthItem()
    {
        name = "nameLength";
        dataType = "DWord";
        offset = 0;
        length = 4;
        hexData = null;
        decodedData = Integer.valueOf(0);
    }
}
