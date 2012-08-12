// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TowerSceneryObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.IDItem;
import com.zbluesoftware.fsxp.bgl.item.SizeItem;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class TowerSceneryObject extends BaseObject
{

    public TowerSceneryObject(int offset, int length)
    {
        name = "TowerScenery";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new SizeItem(), true);
        addItem(new SizeItem("scenerySize"), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "66";
    public static final String ID2 = "00";
}
