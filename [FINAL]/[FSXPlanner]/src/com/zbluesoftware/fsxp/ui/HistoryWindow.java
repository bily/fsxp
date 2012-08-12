// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HistoryWindow.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.dialog.HistoryDetailsDialog;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            WindowBorder, HistoryItem

public class HistoryWindow extends JDialog
    implements AdjustmentListener, MouseListener, MouseMotionListener, PropertyChangeListener
{

    private HistoryWindow(Frame frame)
    {
        super(frame);
        this.frame = frame;
        setUndecorated(true);
        setFocusableWindowState(false);
        itemAL = new ArrayList();
        topRow = 0;
        totalModels = 5;
        timeFormat = DateFormat.getTimeInstance(3);
        getContentPane().setLayout(new BorderLayout());
        HistoryListModel.getInstance().addPropertyChangeListener(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(3, 0));
        mainPanel.addMouseListener(this);
        mainPanel.addMouseMotionListener(this);
        getContentPane().add(mainPanel, "Center");
        windowBorder = new WindowBorder("History");
        mainPanel.setBorder(new CompoundBorder(windowBorder, new EmptyBorder(2, 2, 2, 2)));
        historyPanel = new JPanel(new GridLayout(0, 1, 1, 1));
        historyPanel.setBackground(Color.white);
        mainPanel.add(historyPanel, "Center");
        scrollBar = new JScrollBar(1, 0, totalModels, 0, totalModels);
        scrollBar.addAdjustmentListener(this);
        mainPanel.add(scrollBar, "East");
        setHistoryItems();
        pack();
        setLocation(frame.getSize().width - getSize().width - 5, 200);
    }

    public static HistoryWindow getInstance(Frame frame)
    {
        if(dialog == null)
            dialog = new HistoryWindow(frame);
        return dialog;
    }

    private void setHistoryItems()
    {
        for(int i = 0; i < totalModels; i++)
        {
            HistoryItem historyItem = new HistoryItem();
            historyPanel.add(historyItem);
            itemAL.add(historyItem);
            historyItem.addPropertyChangeListener(this);
        }

        ArrayList historyModels = HistoryListModel.getInstance().getModels();
        for(int i = 0; i < historyModels.size() && i < totalModels; i++)
        {
            HistoryModel model = (HistoryModel)historyModels.get(i);
            HistoryItem item = (HistoryItem)itemAL.get(i);
            item.setChangeData(model.getPropertyDesc());
            item.setObjectData(model.getBaseModel().getModelName());
            item.setAirportData(model.getAirportName());
            item.setTimeData(timeFormat.format(model.getDate()));
            item.setLabelsEnabled(true);
        }

        if(historyModels.size() > totalModels)
            scrollBar.setMaximum(historyModels.size());
    }

    private void updateHistoryItems()
    {
        ArrayList historyModels = HistoryListModel.getInstance().getModels();
        for(int i = topRow; i < topRow + totalModels; i++)
            if(i < historyModels.size())
            {
                HistoryModel model = (HistoryModel)historyModels.get(i);
                HistoryItem item = (HistoryItem)itemAL.get(i - topRow);
                item.setChangeData(model.getPropertyDesc());
                item.setObjectData(model.getBaseModel().getModelName());
                item.setAirportData(model.getAirportName());
                item.setTimeData(timeFormat.format(model.getDate()));
                item.setLabelsEnabled(true);
            } else
            {
                HistoryItem item = (HistoryItem)itemAL.get(i - topRow);
                item.setChangeData(HistoryItem.BLANK_ITEM);
                item.setObjectData(HistoryItem.BLANK_ITEM);
                item.setAirportData(HistoryItem.BLANK_ITEM);
                item.setTimeData(HistoryItem.BLANK_ITEM);
                item.setLabelsEnabled(false);
            }

        if(historyModels.size() > totalModels)
            scrollBar.setMaximum(historyModels.size());
        pack();
        if(getLocation().x + getSize().width > frame.getSize().width)
            setLocation(frame.getSize().width - getSize().width - 5, getLocation().y);
    }

    public void displayDetails()
    {
        ArrayList historyModels = HistoryListModel.getInstance().getModels();
        for(int i = 0; i < totalModels; i++)
            if(triggerHistoryItem == itemAL.get(i))
            {
                HistoryDetailsDialog.showDialog(Utilities.MAIN_FRAME, (HistoryModel)historyModels.get(topRow + i), this, getLocation().x, getLocation().y);
                return;
            }

    }

    public void undoChange()
    {
        ArrayList historyModels = HistoryListModel.getInstance().getModels();
        for(int i = 0; i < totalModels; i++)
            if(triggerHistoryItem == itemAL.get(i))
            {
                HistoryListModel.getInstance().undoItem((HistoryModel)historyModels.get(topRow + i));
                return;
            }

    }

    private void resizeWindow(MouseEvent event)
    {
        int resizeWidth = Math.max(event.getX(), 70);
        int resizeHeight = Math.max(event.getY(), 30);
        setSize(resizeWidth, resizeHeight);
        validate();
        dragMode = 3;
    }

    private void resizeWindowHeight(MouseEvent event)
    {
        int resizeHeight = Math.max(event.getY(), 30);
        setSize(getSize().width, resizeHeight);
        validate();
        dragMode = 5;
    }

    private void resizeWindowWidth(MouseEvent event)
    {
        int resizeWidth = Math.max(event.getX(), 70);
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

    public void adjustmentValueChanged(AdjustmentEvent event)
    {
        if(event.getSource() == scrollBar && !event.getValueIsAdjusting())
        {
            topRow = scrollBar.getValue();
            updateHistoryItems();
        }
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == mainPanel && windowBorder.isInCloseBox(event.getPoint()))
            setVisible(false);
    }

    public void mousePressed(MouseEvent event)
    {
        if(event.getSource() == mainPanel)
        {
            mouseXPos = event.getX();
            mouseYPos = event.getY();
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if(event.getSource() == mainPanel)
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
        if(event.getSource() == mainPanel)
            if(dragMode == 1)
            {
                if(windowBorder.isInResizeBox(event.getPoint()))
                    resizeWindow(event);
                else
                if(windowBorder.isInBottomLine(event.getPoint()))
                    resizeWindowHeight(event);
                else
                if(windowBorder.isInRightLine(event.getPoint()))
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
                moveWindow(event);
    }

    public void mouseMoved(MouseEvent event)
    {
        if(event.getSource() == mainPanel)
            if(windowBorder.isInResizeBox(event.getPoint()))
            {
                if(mainPanel.getCursor().getType() != 5)
                    mainPanel.setCursor(new Cursor(5));
            } else
            if(windowBorder.isInBottomLine(event.getPoint()))
            {
                if(mainPanel.getCursor().getType() != 9)
                    mainPanel.setCursor(new Cursor(9));
            } else
            if(windowBorder.isInRightLine(event.getPoint()))
            {
                if(mainPanel.getCursor().getType() != 11)
                    mainPanel.setCursor(new Cursor(11));
            } else
            if(mainPanel.getCursor().getType() != 0)
                mainPanel.setCursor(new Cursor(0));
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == HistoryListModel.getInstance())
        {
            if(event.getPropertyName().equals("addModel"))
            {
                int addIndex = ((Integer)event.getNewValue()).intValue();
                if(addIndex < topRow)
                {
                    topRow++;
                    if(topRow + totalModels >= HistoryListModel.getInstance().getModels().size())
                    {
                        topRow--;
                        updateHistoryItems();
                    }
                    scrollBar.setValue(topRow);
                    scrollBar.setMaximum(HistoryListModel.getInstance().getModels().size());
                } else
                if(addIndex < topRow + totalModels)
                    updateHistoryItems();
            } else
            if(event.getPropertyName().equals("removeModel"))
            {
                int removeIndex = ((Integer)event.getNewValue()).intValue();
                if(removeIndex < topRow)
                {
                    topRow--;
                    if(topRow < 0)
                    {
                        topRow = 0;
                        updateHistoryItems();
                    }
                    scrollBar.setValue(topRow);
                    scrollBar.setMaximum(HistoryListModel.getInstance().getModels().size());
                } else
                if(removeIndex < topRow + totalModels)
                    updateHistoryItems();
            }
        } else
        if(event.getSource() instanceof HistoryDetailsDialog)
        {
            if(event.getPropertyName().equals("modelSize"))
                updateHistoryItems();
        } else
        if(event.getSource() instanceof HistoryItem)
            if(event.getPropertyName().equals("setTrigger"))
                triggerHistoryItem = (HistoryItem)event.getSource();
            else
            if(event.getPropertyName().equals("displayDetails"))
            {
                triggerHistoryItem = (HistoryItem)event.getSource();
                displayDetails();
            }
    }

    private Frame frame;
    private JPanel mainPanel;
    private JPanel historyPanel;
    private WindowBorder windowBorder;
    private ArrayList itemAL;
    private DateFormat timeFormat;
    private JScrollBar scrollBar;
    private HistoryItem triggerHistoryItem;
    private int mouseXPos;
    private int mouseYPos;
    private int dragMode;
    private int topRow;
    private int totalModels;
    public static final int DM_NONE = 1;
    public static final int DM_MOVE = 2;
    public static final int DM_RESIZE = 3;
    public static final int DM_RESIZE_WIDTH = 4;
    public static final int DM_RESIZE_HEIGHT = 5;
    private static HistoryWindow dialog = null;

}
