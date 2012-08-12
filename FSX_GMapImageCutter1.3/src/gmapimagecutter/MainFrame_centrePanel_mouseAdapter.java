// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame.java

package gmapimagecutter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Referenced classes of package gmapimagecutter:
//            MainFrame

class MainFrame_centrePanel_mouseAdapter extends MouseAdapter
{

    MainFrame_centrePanel_mouseAdapter(MainFrame adaptee)
    {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e)
    {
        adaptee.centrePanel_mouseClicked(e);
    }

    private MainFrame adaptee;
}
