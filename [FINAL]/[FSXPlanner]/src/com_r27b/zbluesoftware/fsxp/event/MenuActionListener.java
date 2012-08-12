// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuActionListener.java

package com.zbluesoftware.fsxp.event;

import java.util.EventListener;

// Referenced classes of package com.zbluesoftware.fsxp.event:
//            MenuActionEvent

public interface MenuActionListener
    extends EventListener
{

    public abstract void menuItemSelected(MenuActionEvent menuactionevent);
}
