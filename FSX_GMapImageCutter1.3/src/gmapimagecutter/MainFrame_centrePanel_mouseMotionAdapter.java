// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame.java

package gmapimagecutter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// Referenced classes of package gmapimagecutter:
//            MainFrame

class MainFrame_centrePanel_mouseMotionAdapter extends MouseMotionAdapter
{

    MainFrame_centrePanel_mouseMotionAdapter(MainFrame adaptee)
    {
        this.adaptee = adaptee;
    }

    public void mouseMoved(MouseEvent e)
    {
        adaptee.centrePanel_mouseMoved(e);
    }

    private MainFrame adaptee;
}
