// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameIDItem.java

package com.zbluesoftware.fsxp.bgl.item;


// Referenced classes of package com.zbluesoftware.fsxp.bgl.item:
//            Item

public class NameIDItem extends Item
{

    public NameIDItem()
    {
        name = "nameID";
        dataType = "Word";
        offset = 0;
        length = 2;
        hexData = null;
        decodedData = null;
    }

    public static final String ID1 = "33";
    public static final String ID2 = "00";
}
