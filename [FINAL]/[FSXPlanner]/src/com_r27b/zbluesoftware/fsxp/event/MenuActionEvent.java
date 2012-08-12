// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuActionEvent.java

package com.zbluesoftware.fsxp.event;

import java.util.EventObject;
import javax.swing.AbstractAction;

public class MenuActionEvent extends EventObject
{

    public MenuActionEvent(AbstractAction source, Object actionCommand)
    {
        super(source);
        this.actionCommand = actionCommand;
    }

    public Object getActionCommand()
    {
        return actionCommand;
    }

    private Object actionCommand;
}
