// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VertexObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class VertexObject extends BaseObject
{

    public VertexObject()
    {
        this(0);
    }

    public VertexObject(int offset)
    {
        name = "Vertex";
        this.offset = offset;
        length = 8;
        addItem(new LongitudeItem(), true);
        addItem(new LatitudeItem(), true);
        addItem(new BiasItem("biasX"), true);
        addItem(new BiasItem("biasZ"), true);
    }

    public String toString()
    {
        return name;
    }
}
