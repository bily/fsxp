// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WeatherTriggerObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class WeatherTriggerObject extends BaseObject
{

    public WeatherTriggerObject()
    {
        this(0, 0);
    }

    public WeatherTriggerObject(int offset, int length)
    {
        name = "WeatherTriggerObject";
        this.offset = offset;
        this.length = length;
        addItem(new WeatherTypeItem(), true);
        addItem(new HeadingItem(), true);
        addItem(new ScalarItem("scalar", "Float", 4), true);
        addItem(new PointCountItem("vertexCount", "DWord", 4), true);
    }

    public String toString()
    {
        return name;
    }
}
