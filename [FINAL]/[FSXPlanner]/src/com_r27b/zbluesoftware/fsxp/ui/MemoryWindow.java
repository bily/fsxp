// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MemoryWindow.java

package com.zbluesoftware.fsxp.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JDialog;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            MemoryPanel, WindowBorder

public class MemoryWindow extends JDialog
    implements MouseListener, MouseMotionListener
{

    private MemoryWindow(Frame frame)
    {
        super(frame);
        setUndecorated(true);
        setFocusableWindowState(false);
        dragMode = 1;
        inHeader = false;
        getContentPane().setLayout(new BorderLayout());
        memoryPanel = new MemoryPanel();
        memoryPanel.addMouseListener(this);
        memoryPanel.addMouseMotionListener(this);
        getContentPane().add(memoryPanel, "Center");
        pack();
        setLocation(frame.getSize().width - getSize().width - 10, 70);
    }

    public static void showWindow(Frame frame)
    {
        if(window == null)
            window = new MemoryWindow(frame);
        window.setVisible(true);
        window.getMemoryPanel().setWorking(true);
        window.getMemoryPanel().startPoll();
    }

    public MemoryPanel getMemoryPanel()
    {
        return memoryPanel;
    }

    private void resizeWindow(MouseEvent event)
    {
        int resizeWidth = Math.max(event.getX(), getMinimumSize().width);
        int resizeHeight = Math.max(event.getY(), getMinimumSize().height);
        setSize(resizeWidth, resizeHeight);
        validate();
        dragMode = 3;
    }

    private void resizeWindowHeight(MouseEvent event)
    {
        int resizeHeight = Math.max(event.getY(), getMinimumSize().height);
        setSize(getSize().width, resizeHeight);
        validate();
        dragMode = 5;
    }

    private void resizeWindowWidth(MouseEvent event)
    {
        int resizeWidth = Math.max(event.getX(), getMinimumSize().width);
        setSize(resizeWidth, getSize().height);
        validate();
        dragMode = 4;
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
        if(event.getSource() == memoryPanel && memoryPanel.getWindowBorder().isInCloseBox(event.getPoint()))
        {
            setVisible(false);
            memoryPanel.setWorking(false);
        }
    }

    public void mousePressed(MouseEvent event)
    {
        if(event.getSource() == memoryPanel)
        {
            mouseXPos = event.getX();
            mouseYPos = event.getY();
            inHeader = memoryPanel.getWindowBorder().isInTitleBar(event.getPoint());
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if(event.getSource() == memoryPanel)
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
        if(event.getSource() == memoryPanel)
            if(dragMode == 1)
            {
                if(memoryPanel.getWindowBorder().isInResizeBox(event.getPoint()))
                    resizeWindow(event);
                else
                if(memoryPanel.getWindowBorder().isInBottomLine(event.getPoint()))
                    resizeWindowHeight(event);
                else
                if(memoryPanel.getWindowBorder().isInRightLine(event.getPoint()))
                    resizeWindowWidth(event);
                else
                    moveWindow(event);
            } else
            if(dragMode == 3)
                resizeWindow(event);
            else
            if(dragMode == 5)
                resizeWindowHeight(event);
            else
            if(dragMode == 4)
                resizeWindowWidth(event);
            else
            if(inHeader)
                moveWindow(event);
    }

    public void mouseMoved(MouseEvent event)
    {
        if(event.getSource() == memoryPanel)
            if(memoryPanel.getWindowBorder().isInResizeBox(event.getPoint()))
            {
                if(memoryPanel.getCursor().getType() != 5)
                    memoryPanel.setCursor(new Cursor(5));
            } else
            if(memoryPanel.getWindowBorder().isInBottomLine(event.getPoint()))
            {
                if(memoryPanel.getCursor().getType() != 9)
                    memoryPanel.setCursor(new Cursor(9));
            } else
            if(memoryPanel.getWindowBorder().isInRightLine(event.getPoint()))
            {
                if(memoryPanel.getCursor().getType() != 11)
                    memoryPanel.setCursor(new Cursor(11));
            } else
            if(memoryPanel.getCursor().getType() != 0)
                memoryPanel.setCursor(new Cursor(0));
    }

    private MemoryPanel memoryPanel;
    private int mouseXPos;
    private int mouseYPos;
    private int dragMode;
    private boolean inHeader;
    public static final int DM_NONE = 1;
    public static final int DM_MOVE = 2;
    public static final int DM_RESIZE = 3;
    public static final int DM_RESIZE_WIDTH = 4;
    public static final int DM_RESIZE_HEIGHT = 5;
    private static MemoryWindow window = null;

}
