// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TopBar.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.engine.DisplayEngine;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import com.zbluesoftware.fsxp.model.AlertModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            ToolbarButtonGroup

public class TopBar extends JPanel
    implements PropertyChangeListener
{

    private TopBar()
    {
        setLayout(new GridBagLayout());
        setBackground(Utilities.LIGHT_BG_COLOR);
        setBorder(new EtchedBorder());
        JToolBar toolbar = new JToolBar();
        toolbar.setOpaque(false);
        toolbar.setBorder(new EmptyBorder(0, 0, 0, 0));
        toolbar.setFloatable(false);
        JToggleButton selectButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(115)));
        selectButton.setOpaque(false);
        selectButton.setText("");
        selectButton.setToolTipText("Select Objects");
        selectButton.setRolloverIcon(IconFactory.getInstance().getIcon("selectOver"));
        selectButton.setSelectedIcon(IconFactory.getInstance().getIcon("selectOn"));
        selectButton.setSelected(true);
        toolbar.add(selectButton);
        JToggleButton scrollButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(116)));
        scrollButton.setOpaque(false);
        scrollButton.setText("");
        scrollButton.setToolTipText("Scroll");
        scrollButton.setRolloverIcon(IconFactory.getInstance().getIcon("scrollOver"));
        scrollButton.setSelectedIcon(IconFactory.getInstance().getIcon("scrollOn"));
        scrollButton.setSelected(true);
        toolbar.add(scrollButton);
        JToggleButton zoomInButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(117)));
        zoomInButton.setOpaque(false);
        zoomInButton.setText("");
        zoomInButton.setToolTipText("Zoom In");
        zoomInButton.setRolloverIcon(IconFactory.getInstance().getIcon("zoomInOver"));
        zoomInButton.setSelectedIcon(IconFactory.getInstance().getIcon("zoomInOn"));
        zoomInButton.setSelected(true);
        toolbar.add(zoomInButton);
        JToggleButton zoomOutButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(118)));
        zoomOutButton.setOpaque(false);
        zoomOutButton.setText("");
        zoomOutButton.setToolTipText("Zoom Out");
        zoomOutButton.setRolloverIcon(IconFactory.getInstance().getIcon("zoomOutOver"));
        zoomOutButton.setSelectedIcon(IconFactory.getInstance().getIcon("zoomOutOn"));
        zoomOutButton.setSelected(true);
        toolbar.add(zoomOutButton);
        toolbar.addSeparator();
        JToggleButton runwayButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(147)));
        runwayButton.setOpaque(false);
        runwayButton.setText("");
        runwayButton.setToolTipText("Insert Runway");
        runwayButton.setRolloverIcon(IconFactory.getInstance().getIcon("runwayOver"));
        runwayButton.setSelectedIcon(IconFactory.getInstance().getIcon("runwayOn"));
        toolbar.add(runwayButton);
        toolbar.addSeparator();
        JToggleButton taxiPointButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(134)));
        taxiPointButton.setOpaque(false);
        taxiPointButton.setText("");
        taxiPointButton.setToolTipText("Insert Taxiway Points");
        taxiPointButton.setRolloverIcon(IconFactory.getInstance().getIcon("taxiPointOver"));
        taxiPointButton.setSelectedIcon(IconFactory.getInstance().getIcon("taxiPointOn"));
        toolbar.add(taxiPointButton);
        JToggleButton ilsTaxiPointButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(135)));
        ilsTaxiPointButton.setOpaque(false);
        ilsTaxiPointButton.setText("");
        ilsTaxiPointButton.setToolTipText("Insert ILS Taxiway Points");
        ilsTaxiPointButton.setRolloverIcon(IconFactory.getInstance().getIcon("ilsTaxiPointOver"));
        ilsTaxiPointButton.setSelectedIcon(IconFactory.getInstance().getIcon("ilsTaxiPointOn"));
        toolbar.add(ilsTaxiPointButton);
        JToggleButton hsTaxiPointButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(136)));
        hsTaxiPointButton.setOpaque(false);
        hsTaxiPointButton.setText("");
        hsTaxiPointButton.setToolTipText("Insert Hold Short Taxiway Points");
        hsTaxiPointButton.setRolloverIcon(IconFactory.getInstance().getIcon("hsTaxiPointOver"));
        hsTaxiPointButton.setSelectedIcon(IconFactory.getInstance().getIcon("hsTaxiPointOn"));
        toolbar.add(hsTaxiPointButton);
        JToggleButton taxiwayButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(142)));
        taxiwayButton.setOpaque(false);
        taxiwayButton.setText("");
        taxiwayButton.setToolTipText("Insert Taxiway");
        taxiwayButton.setRolloverIcon(IconFactory.getInstance().getIcon("taxiwayOver"));
        taxiwayButton.setSelectedIcon(IconFactory.getInstance().getIcon("taxiwayOn"));
        toolbar.add(taxiwayButton);
        JToggleButton taxiwaySignButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(137)));
        taxiwaySignButton.setOpaque(false);
        taxiwaySignButton.setText("");
        taxiwaySignButton.setToolTipText("Insert Taxiway Signs");
        taxiwaySignButton.setRolloverIcon(IconFactory.getInstance().getIcon("taxiwaySignOver"));
        taxiwaySignButton.setSelectedIcon(IconFactory.getInstance().getIcon("taxiwaySignOn"));
        toolbar.add(taxiwaySignButton);
        toolbar.addSeparator();
        JToggleButton jetwayButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(177)));
        jetwayButton.setOpaque(false);
        jetwayButton.setText("");
        jetwayButton.setToolTipText("Insert Jetways");
        jetwayButton.setRolloverIcon(IconFactory.getInstance().getIcon("jetwayOver"));
        jetwayButton.setSelectedIcon(IconFactory.getInstance().getIcon("jetwayOn"));
        toolbar.add(jetwayButton);
        JToggleButton parkingButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(122)));
        parkingButton.setOpaque(false);
        parkingButton.setText("");
        parkingButton.setToolTipText("Insert Parking Locations");
        parkingButton.setRolloverIcon(IconFactory.getInstance().getIcon("parkingOver"));
        parkingButton.setSelectedIcon(IconFactory.getInstance().getIcon("parkingOn"));
        toolbar.add(parkingButton);
        JToggleButton startPointButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(132)));
        startPointButton.setOpaque(false);
        startPointButton.setText("");
        startPointButton.setToolTipText("Insert Starting Locations");
        startPointButton.setRolloverIcon(IconFactory.getInstance().getIcon("startPointOver"));
        startPointButton.setSelectedIcon(IconFactory.getInstance().getIcon("startPointOn"));
        toolbar.add(startPointButton);
        JToggleButton helipadButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(139)));
        helipadButton.setOpaque(false);
        helipadButton.setText("");
        helipadButton.setToolTipText("Insert Helipads");
        helipadButton.setRolloverIcon(IconFactory.getInstance().getIcon("helipadOver"));
        helipadButton.setSelectedIcon(IconFactory.getInstance().getIcon("helipadOn"));
        toolbar.add(helipadButton);
        JToggleButton towerButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(133)));
        towerButton.setOpaque(false);
        towerButton.setText("");
        towerButton.setToolTipText("Insert Control Towers");
        towerButton.setRolloverIcon(IconFactory.getInstance().getIcon("towerOver"));
        towerButton.setSelectedIcon(IconFactory.getInstance().getIcon("towerOn"));
        toolbar.add(towerButton);
        toolbar.addSeparator();
        JToggleButton apronButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(146)));
        apronButton.setOpaque(false);
        apronButton.setText("");
        apronButton.setToolTipText("Insert/Modify Aprons");
        apronButton.setRolloverIcon(IconFactory.getInstance().getIcon("apronOver"));
        apronButton.setSelectedIcon(IconFactory.getInstance().getIcon("apronOn"));
        toolbar.add(apronButton);
        JToggleButton apronEdgeLightButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(138)));
        apronEdgeLightButton.setOpaque(false);
        apronEdgeLightButton.setText("");
        apronEdgeLightButton.setToolTipText("Insert/Modify Apron Edge Lights");
        apronEdgeLightButton.setRolloverIcon(IconFactory.getInstance().getIcon("apronEdgeLightOver"));
        apronEdgeLightButton.setSelectedIcon(IconFactory.getInstance().getIcon("apronEdgeLightOn"));
        toolbar.add(apronEdgeLightButton);
        toolbar.addSeparator();
        JToggleButton fenceButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(170)));
        fenceButton.setOpaque(false);
        fenceButton.setText("");
        fenceButton.setToolTipText("Insert/Modify Boundary Fences");
        fenceButton.setRolloverIcon(IconFactory.getInstance().getIcon("fenceOver"));
        fenceButton.setSelectedIcon(IconFactory.getInstance().getIcon("fenceOn"));
        toolbar.add(fenceButton);
        JToggleButton blastFenceButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(175)));
        blastFenceButton.setOpaque(false);
        blastFenceButton.setText("");
        blastFenceButton.setToolTipText("Insert/Modify Blast Fences");
        blastFenceButton.setRolloverIcon(IconFactory.getInstance().getIcon("blastFenceOver"));
        blastFenceButton.setSelectedIcon(IconFactory.getInstance().getIcon("blastFenceOn"));
        toolbar.add(blastFenceButton);
        toolbar.addSeparator();
        JToggleButton excludeButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(183)));
        excludeButton.setOpaque(false);
        excludeButton.setText("");
        excludeButton.setToolTipText("Insert/Modify Exclusion Rectangles");
        excludeButton.setRolloverIcon(IconFactory.getInstance().getIcon("excludeOver"));
        excludeButton.setSelectedIcon(IconFactory.getInstance().getIcon("excludeOn"));
        toolbar.add(excludeButton);
        ToolbarButtonGroup.getInstance().add(selectButton);
        ToolbarButtonGroup.getInstance().add(scrollButton);
        ToolbarButtonGroup.getInstance().add(zoomInButton);
        ToolbarButtonGroup.getInstance().add(zoomOutButton);
        ToolbarButtonGroup.getInstance().add(runwayButton);
        ToolbarButtonGroup.getInstance().add(taxiPointButton);
        ToolbarButtonGroup.getInstance().add(ilsTaxiPointButton);
        ToolbarButtonGroup.getInstance().add(hsTaxiPointButton);
        ToolbarButtonGroup.getInstance().add(taxiwayButton);
        ToolbarButtonGroup.getInstance().add(taxiwaySignButton);
        ToolbarButtonGroup.getInstance().add(jetwayButton);
        ToolbarButtonGroup.getInstance().add(parkingButton);
        ToolbarButtonGroup.getInstance().add(startPointButton);
        ToolbarButtonGroup.getInstance().add(helipadButton);
        ToolbarButtonGroup.getInstance().add(towerButton);
        ToolbarButtonGroup.getInstance().add(apronButton);
        ToolbarButtonGroup.getInstance().add(apronEdgeLightButton);
        ToolbarButtonGroup.getInstance().add(fenceButton);
        ToolbarButtonGroup.getInstance().add(blastFenceButton);
        ToolbarButtonGroup.getInstance().add(excludeButton);
        fsxLabel = new JLabel(IconFactory.getInstance().getIcon("fsxDisconnected"));
        fsxLabel.setToolTipText("Not connected to FSX");
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
        Utilities.addComponent(this, toolbar, 0, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createHorizontalGlue(), 1, 0, 1, 1, 2, 10, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, fsxLabel, 2, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latLabel, 3, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latDataLabel, 4, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, lonLabel, 5, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, lonDataLabel, 6, 0, 1, 1, 0, 17, new Insets(5, 1, 5, 10), 0, 0, 0.0D, 0.0D);
        AlertModel.getInstance().addPropertyChangeListener(this);
        FSXConnection.getInstance().addPropertyChangeListener(this);
    }

    public static TopBar getInstance()
    {
        if(topBar == null)
            topBar = new TopBar();
        return topBar;
    }

    private void updateLatLon(LatLonPoint point)
    {
        lonDataLabel.setText(DisplayEngine.getInstance().formatLongitude(point.getLon()));
        latDataLabel.setText(DisplayEngine.getInstance().formatLatitude(point.getLat()));
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
        } else
        if((event.getSource() instanceof FSXConnection) && event.getPropertyName().equals("connectedToFSX"))
            updateConnectionImage(((Boolean)event.getNewValue()).booleanValue());
    }

    private JLabel fsxLabel;
    private JLabel latDataLabel;
    private JLabel lonDataLabel;
    private static TopBar topBar = null;

}
