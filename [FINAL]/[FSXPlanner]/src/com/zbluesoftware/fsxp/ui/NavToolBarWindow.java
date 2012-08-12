// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NavToolBarWindow.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.menu.FSXPMenuBar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            NavToolBarPanel, WindowBorder

public class NavToolBarWindow extends JDialog
    implements MouseListener, MouseMotionListener
{

    private NavToolBarWindow(Frame frame)
    {
        super(frame);
        setUndecorated(true);
        setFocusableWindowState(false);
        dragMode = 1;
        inHeader = false;
        getContentPane().setLayout(new BorderLayout(0, 0));
        navToolBar = new NavToolBarPanel();
        navToolBar.addMouseListener(this);
        navToolBar.addMouseMotionListener(this);
        getContentPane().add(navToolBar, "Center");
        pack();
        setLocation(260, 90);
    }

    public static void showWindow(Frame frame)
    {
        if(window == null)
            window = new NavToolBarWindow(frame);
        window.setVisible(true);
    }

    public static void hideWindow()
    {
        window.setVisible(false);
    }

    private void moveWindow(MouseEvent event)
    {
        if(isVisible())
        {
            Point location = getLocationOnScreen();
            setLocation((location.x + event.getX()) - mouseXPos, (location.y + event.getY()) - mouseYPos);
            dragMode = 2;
        }
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == navToolBar && navToolBar.getWindowBorder().isInCloseBox(event.getPoint()))
        {
            ((JCheckBoxMenuItem)FSXPMenuBar.getInstance().getMenu("View").getItem(2)).setState(false);
            setVisible(false);
        }
    }

    public void mousePressed(MouseEvent event)
    {
        if(event.getSource() == navToolBar)
        {
            mouseXPos = event.getX();
            mouseYPos = event.getY();
            inHeader = navToolBar.getWindowBorder().isInTitleBar(event.getPoint());
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if(event.getSource() == navToolBar)
            dragMode = 1;
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent event)
    {
        if(event.getSource() == navToolBar && inHeader)
            moveWindow(event);
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
    }

    private NavToolBarPanel navToolBar;
    private int mouseXPos;
    private int mouseYPos;
    private int dragMode;
    private boolean inHeader;
    public static final int DM_NONE = 1;
    public static final int DM_MOVE = 2;
    public static final int DM_RESIZE = 3;
    public static final int DM_RESIZE_WIDTH = 4;
    public static final int DM_RESIZE_HEIGHT = 5;
    private static NavToolBarWindow window = null;

}
