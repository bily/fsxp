// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainFrame.java

package gmapimagecutter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package gmapimagecutter:
//            MainFrame

class MainFrame_jSlider1_changeAdapter
    implements ChangeListener
{

    MainFrame_jSlider1_changeAdapter(MainFrame adaptee)
    {
        this.adaptee = adaptee;
    }

    public void stateChanged(ChangeEvent e)
    {
        adaptee.jSlider1_stateChanged(e);
    }

    private MainFrame adaptee;
}
