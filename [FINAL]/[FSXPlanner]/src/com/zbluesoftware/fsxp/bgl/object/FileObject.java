// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileObject.java

package com.zbluesoftware.fsxp.bgl.object;

import com.zbluesoftware.fsxp.bgl.item.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl.object:
//            BaseObject

public class FileObject extends BaseObject
{

    public FileObject()
    {
        this(0, 0);
    }

    public FileObject(int offset, int length)
    {
        name = "File";
        this.offset = offset;
        this.length = length;
        addItem(new IDItem(), true);
        addItem(new IDItem("version"), true);
        addItem(new SizeItem(), true);
        addItem(new SectionCountItem(), true);
    }

    public String toString()
    {
        return name;
    }

    public static final String ID1 = "38";
    public static final String ID2 = "00";
}
