// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BottomBar.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.engine.DisplayEngine;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.AlertModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class BottomBar extends JPanel
    implements PropertyChangeListener
{

    private BottomBar()
    {
        scaleFormat = NumberFormat.getInstance();
        scaleFormat.setMaximumFractionDigits(2);
        scaleFormat.setMinimumFractionDigits(2);
        setLayout(new GridBagLayout());
        setBackground(Utilities.LIGHT_BG_COLOR);
        setBorder(new EtchedBorder());
        fsxLabel = new JLabel(IconFactory.getInstance().getIcon("fsxDisconnected"));
        fsxLabel.setToolTipText("Not connected to FSX");
        JLabel zoomLabel = new JLabel("zoom:");
        zoomLabel.setFont(Utilities.LABEL_FONT);
        zoomLabel.setForeground(Color.black);
        zoomDataLabel = new JLabel("---");
        zoomDataLabel.setFont(Utilities.LABEL_FONT);
        zoomDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        JLabel rotationLabel = new JLabel("rotation:");
        rotationLabel.setFont(Utilities.LABEL_FONT);
        rotationLabel.setForeground(Color.black);
        rotationDataLabel = new JLabel("---");
        rotationDataLabel.setFont(Utilities.LABEL_FONT);
        rotationDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        JLabel latLabel = new JLabel("latitude:");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latDataLabel = new JLabel("---");
        latDataLabel.setFont(Utilities.LABEL_FONT);
        latDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        JLabel lonLabel = new JLabel("longitude:");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonDataLabel = new JLabel("---");
        lonDataLabel.setFont(Utilities.LABEL_FONT);
        lonDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        Utilities.addComponent(this, Box.createHorizontalGlue(), 0, 0, 1, 1, 2, 10, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, fsxLabel, 1, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, new JSeparator(1), 2, 0, 1, 1, 3, 17, new Insets(5, 10, 5, 10), 0, 0, 0.0D, 0.5D);
        Utilities.addComponent(this, zoomLabel, 3, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, zoomDataLabel, 4, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, new JSeparator(1), 5, 0, 1, 1, 3, 17, new Insets(5, 10, 5, 10), 0, 0, 0.0D, 0.5D);
        Utilities.addComponent(this, rotationLabel, 6, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, rotationDataLabel, 7, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, new JSeparator(1), 8, 0, 1, 1, 3, 17, new Insets(5, 10, 5, 10), 0, 0, 0.0D, 0.5D);
        Utilities.addComponent(this, latLabel, 9, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latDataLabel, 10, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, lonLabel, 11, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, lonDataLabel, 12, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 10), 0, 0, 0.0D, 0.0D);
        AlertModel.getInstance().addPropertyChangeListener(this);
        FSXConnection.getInstance().addPropertyChangeListener(this);
    }

    public static BottomBar getInstance()
    {
        if(bottomBar == null)
            bottomBar = new BottomBar();
        return bottomBar;
    }

    private void updateLatLon(LatLonPoint point)
    {
        lonDataLabel.setText(DisplayEngine.getInstance().formatLongitude(point.getLon()));
        latDataLabel.setText(DisplayEngine.getInstance().formatLatitude(point.getLat()));
    }

    private void updateScale(float scale)
    {
        zoomDataLabel.setText((new StringBuilder()).append(scaleFormat.format(1.0F / scale)).append(" feet per pixel").toString());
    }

    private void updateRotation(double rotation)
    {
        rotationDataLabel.setText((new StringBuilder()).append(scaleFormat.format(rotation)).append(" degrees").toString());
    }

    private void updateConnectionImage(boolean connected)
    {
        if(connected)
        {
            fsxLabel.setIcon(IconFactory.getInstance().getIcon("fsxConnected"));
            fsxLabel.setToolTipText("Connected to FSX");
        } else
        {
            fsxLabel.setIcon(IconFactory.getInstance().getIcon("fsxDisconnected"));
            fsxLabel.setToolTipText("Not connected to FSX");
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof AlertModel)
        {
            if(event.getPropertyName().equals("LatLon"))
                updateLatLon((LatLonPoint)event.getNewValue());
            else
            if(event.getPropertyName().equals("scale"))
                updateScale(((Float)event.getNewValue()).floatValue());
            else
            if(event.getPropertyName().equals("rotation"))
                updateRotation(((Double)event.getNewValue()).floatValue());
        } else
        if((event.getSource() instanceof FSXConnection) && event.getPropertyName().equals("connectedToFSX"))
            updateConnectionImage(((Boolean)event.getNewValue()).booleanValue());
    }

    private JLabel fsxLabel;
    private JLabel zoomDataLabel;
    private JLabel rotationDataLabel;
    private JLabel latDataLabel;
    private JLabel lonDataLabel;
    private NumberFormat scaleFormat;
    private static BottomBar bottomBar = null;

}
