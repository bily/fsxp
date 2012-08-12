// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UnknownItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class UnknownItem extends Item
{

    public UnknownItem()
    {
        name = "unknown";
        dataType = "DWord";
        offset = 0;
        length = 4;
        hexData = null;
        decodedData = null;
        decodedType = 1;
    }
}
