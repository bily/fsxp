// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CopiedItem.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.menu.MenuAction;
import com.zbluesoftware.fsxp.menu.MenuFactory;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel

public class CopiedItem
{

    private CopiedItem()
    {
        baseModel = null;
    }

    public static CopiedItem getInstance()
    {
        if(item == null)
            item = new CopiedItem();
        return item;
    }

    public void setCopiedItem(BaseModel baseModel)
    {
        this.baseModel = baseModel;
        if(baseModel != null)
        {
            if(baseModel.isCopyable())
            {
                MenuFactory.getInstance().getMenuAction(new Integer(23)).setEnabled(true);
                MenuFactory.getInstance().getMenuAction(new Integer(23)).putValue("Name", (new StringBuilder()).append("Paste ").append(baseModel.getModelName()).toString());
            } else
            {
                MenuFactory.getInstance().getMenuAction(new Integer(23)).setEnabled(false);
                MenuFactory.getInstance().getMenuAction(new Integer(23)).putValue("Name", "Paste");
            }
        } else
        {
            MenuFactory.getInstance().getMenuAction(new Integer(23)).setEnabled(false);
            MenuFactory.getInstance().getMenuAction(new Integer(23)).putValue("Name", "Paste");
        }
    }

    public BaseModel getCopiedItem()
    {
        return baseModel;
    }

    private BaseModel baseModel;
    private static CopiedItem item = null;

}
