// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CountWindow.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.model.AirportListModel;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            WindowBorder

public class CountWindow extends JDialog
    implements MouseListener, MouseMotionListener, PropertyChangeListener
{

    private CountWindow(Frame frame)
    {
        super(frame);
        this.frame = frame;
        setUndecorated(true);
        setFocusableWindowState(false);
        getContentPane().setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.addMouseListener(this);
        mainPanel.addMouseMotionListener(this);
        getContentPane().add(mainPanel, "Center");
        itemAL = new ArrayList();
        ArrayList spacingAL = new ArrayList();
        itemAL.add("Aprons:");
        spacingAL.add(new Integer(1));
        itemAL.add("Edge Light Strings:");
        spacingAL.add(new Integer(1));
        itemAL.add("Runways:");
        spacingAL.add(new Integer(10));
        itemAL.add("Starting Locations:");
        spacingAL.add(new Integer(1));
        itemAL.add("Parking Locations:");
        spacingAL.add(new Integer(1));
        itemAL.add("Taxiway Nodes:");
        spacingAL.add(new Integer(1));
        itemAL.add("Taxiway Paths:");
        spacingAL.add(new Integer(1));
        itemAL.add("Control Towers:");
        spacingAL.add(new Integer(10));
        itemAL.add("Helipads:");
        spacingAL.add(new Integer(1));
        itemAL.add("Jetways:");
        spacingAL.add(new Integer(1));
        itemAL.add("Boundary Fences:");
        spacingAL.add(new Integer(10));
        itemAL.add("Blast Fences:");
        spacingAL.add(new Integer(1));
        itemAL.add("Exclusion Rectangles:");
        spacingAL.add(new Integer(1));
        itemAL.add("ILS Feathers:");
        spacingAL.add(new Integer(10));
        itemAL.add("Markers:");
        spacingAL.add(new Integer(1));
        itemAL.add("VORs:");
        spacingAL.add(new Integer(1));
        itemAL.add("NDBs:");
        spacingAL.add(new Integer(1));
        itemAL.add("Windsocks:");
        spacingAL.add(new Integer(1));
        itemAL.add("Fuel Triggers:");
        spacingAL.add(new Integer(10));
        itemAL.add("Scenery Objects:");
        spacingAL.add(new Integer(1));
        labels = new JLabel[itemAL.size()];
        dataLabels = new JLabel[itemAL.size()];
        for(int i = 0; i < itemAL.size(); i++)
        {
            labels[i] = new JLabel((String)itemAL.get(i));
            labels[i].setFont(Utilities.LABEL_FONT);
            labels[i].setForeground(Color.black);
            labels[i].setBackground(new Color(255, 255, 153));
            labels[i].addMouseListener(this);
            Utilities.addComponent(mainPanel, labels[i], 0, i, 1, 1, 2, 17, new Insets(((Integer)spacingAL.get(i)).intValue(), 1, 1, 0), 0, 0, 0.5D, 0.0D);
            dataLabels[i] = new JLabel(" 0");
            dataLabels[i].setFont(Utilities.LABEL_FONT);
            dataLabels[i].setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
            dataLabels[i].setBackground(new Color(255, 255, 153));
            dataLabels[i].addMouseListener(this);
            Utilities.addComponent(mainPanel, dataLabels[i], 1, i, 1, 1, 0, 17, new Insets(((Integer)spacingAL.get(i)).intValue(), 0, 1, 1), 0, 0, 0.0D, 0.0D);
        }

        windowBorder = new WindowBorder("Object Counts");
        mainPanel.setBorder(new CompoundBorder(windowBorder, new EmptyBorder(2, 2, 2, 2)));
        pack();
        setLocation(frame.getSize().width - getSize().width - 5, 200);
        AirportListModel.getInstance().addPropertyChangeListener(this);
    }

    public static CountWindow getInstance(Frame frame, AirportModel airportModel)
    {
        if(window == null)
            window = new CountWindow(frame);
        window.setAirportModel(airportModel);
        return window;
    }

    public static CountWindow getInstance()
    {
        return window;
    }

    public void setAirportModel(AirportModel airportModel)
    {
        if(this.airportModel != null)
            this.airportModel.addPropertyChangeListener(this);
        this.airportModel = airportModel;
        if(airportModel != null)
        {
            airportModel.addPropertyChangeListener(this);
            windowBorder.setTitle((new StringBuilder()).append("Object Counts [").append(airportModel.getIdent()).append("]").toString());
            updateCounts();
            repaint();
        }
    }

    private void updateCounts()
    {
        if(airportModel != null)
        {
            dataLabels[0].setText((new StringBuilder()).append("").append(airportModel.getApronAL().size()).toString());
            dataLabels[1].setText((new StringBuilder()).append("").append(airportModel.getApronEdgeLightAL().size()).toString());
            dataLabels[2].setText((new StringBuilder()).append("").append(airportModel.getRunwayAL().size()).toString());
            dataLabels[3].setText((new StringBuilder()).append("").append(airportModel.getStartAL().size()).toString());
            dataLabels[4].setText((new StringBuilder()).append("").append(airportModel.getTaxiwayParkingHM().size()).toString());
            dataLabels[5].setText((new StringBuilder()).append("").append(airportModel.getTaxiwayPointHM().size()).toString());
            dataLabels[6].setText((new StringBuilder()).append("").append(airportModel.getTaxiwayPathAL().size()).toString());
            dataLabels[7].setText((new StringBuilder()).append("").append(airportModel.getTowerAL().size()).toString());
            dataLabels[8].setText((new StringBuilder()).append("").append(airportModel.getHelipadAL().size()).toString());
            dataLabels[9].setText((new StringBuilder()).append("").append(airportModel.getJetwayAL().size()).toString());
            dataLabels[10].setText((new StringBuilder()).append("").append(airportModel.getBoundaryFenceAL().size()).toString());
            dataLabels[11].setText((new StringBuilder()).append("").append(airportModel.getBlastFenceAL().size()).toString());
            dataLabels[12].setText((new StringBuilder()).append("").append(airportModel.getExclusionAL().size()).toString());
            dataLabels[13].setText((new StringBuilder()).append("").append(airportModel.getILSModels().size()).toString());
            dataLabels[14].setText((new StringBuilder()).append("").append(airportModel.getMarkerAL().size()).toString());
            dataLabels[15].setText((new StringBuilder()).append("").append(airportModel.getVORAL().size()).toString());
            dataLabels[16].setText((new StringBuilder()).append("").append(airportModel.getNDBAL().size()).toString());
            dataLabels[17].setText((new StringBuilder()).append("").append(airportModel.getWindsockAL().size()).toString());
            dataLabels[18].setText((new StringBuilder()).append("").append(airportModel.getTriggerAL().size()).toString());
            dataLabels[19].setText((new StringBuilder()).append("").append(airportModel.getSceneryAL().size()).toString());
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

    public void mouseEntered(MouseEvent event)
    {
        for(int i = labels.length - 1; i >= 0; i--)
            if(event.getSource() == labels[i])
            {
                labels[i].setBackground(new Color(255, 255, 153));
                labels[i].setOpaque(true);
                dataLabels[i].setBackground(new Color(255, 255, 153));
                dataLabels[i].setOpaque(true);
                return;
            }

    }

    public void mouseExited(MouseEvent event)
    {
        for(int i = labels.length - 1; i >= 0; i--)
            if(event.getSource() == labels[i])
            {
                labels[i].setBackground(Color.white);
                labels[i].setOpaque(false);
                dataLabels[i].setBackground(Color.white);
                dataLabels[i].setOpaque(false);
                return;
            }

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
        if(event.getSource() == airportModel)
            updateCounts();
        else
        if((event.getSource() instanceof AirportListModel) && event.getPropertyName().equals("currentAirport"))
            setAirportModel(AirportListModel.getInstance().getCurrentAirport());
    }

    private Frame frame;
    private JPanel mainPanel;
    private WindowBorder windowBorder;
    private ArrayList itemAL;
    private JLabel labels[];
    private JLabel dataLabels[];
    private AirportModel airportModel;
    private int mouseXPos;
    private int mouseYPos;
    private int dragMode;
    public static final int DM_NONE = 1;
    public static final int DM_MOVE = 2;
    public static final int DM_RESIZE = 3;
    public static final int DM_RESIZE_WIDTH = 4;
    public static final int DM_RESIZE_HEIGHT = 5;
    private static CountWindow window = null;

}
