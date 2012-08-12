// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CreateDialog.java

package gmapimagecutter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package gmapimagecutter:
//            CreateDialog

class CreateDialog_cancelButton_actionAdapter
    implements ActionListener
{

    CreateDialog_cancelButton_actionAdapter(CreateDialog adaptee)
    {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e)
    {
        adaptee.cancelButton_actionPerformed(e);
    }

    private CreateDialog adaptee;
}
