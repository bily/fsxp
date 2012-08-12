// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame.java

package gmapimagecutter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Referenced classes of package gmapimagecutter:
//            MainFrame

class MainFrame_jMenuItem2_actionAdapter
    implements ActionListener
{

    MainFrame_jMenuItem2_actionAdapter(MainFrame adaptee)
    {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e)
    {
        adaptee.jMenuItem2_actionPerformed(e);
    }

    private MainFrame adaptee;
}
