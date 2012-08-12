// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CADPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class CADPanel extends JPanel
    implements StartupPanel
{

    public CADPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("CAD Display Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        altitudeCB = new JCheckBox("Use airport altitude for new object altitudes", SettingsEngine.getInstance().getUseAirportAlt());
        altitudeCB.setFont(Utilities.LABEL_FONT);
        altitudeCB.setForeground(Color.black);
        altitudeCB.setOpaque(false);
        adjustMeasureCB = new JCheckBox("Adjust measurements when switching between Feet and Meters", SettingsEngine.getInstance().getAdjustMeasurements());
        adjustMeasureCB.setFont(Utilities.LABEL_FONT);
        adjustMeasureCB.setForeground(Color.black);
        adjustMeasureCB.setOpaque(false);
        drawPathsCB = new JCheckBox("Draw taxiway type PATH with width", SettingsEngine.getInstance().getDrawTaxiwayPaths());
        drawPathsCB.setFont(Utilities.LABEL_FONT);
        drawPathsCB.setForeground(Color.black);
        drawPathsCB.setOpaque(false);
        taxiwayPropertiesCB = new JCheckBox("Set new taxiway properties based on existing ones.", SettingsEngine.getInstance().getTaxiwayProperties());
        taxiwayPropertiesCB.setFont(Utilities.LABEL_FONT);
        taxiwayPropertiesCB.setForeground(Color.black);
        taxiwayPropertiesCB.setOpaque(false);
        taxiwayRunwayCB = new JCheckBox("Taxiways drawn on runways should inherit the Runway's properties.", SettingsEngine.getInstance().getTaxiwayRunways());
        taxiwayRunwayCB.setFont(Utilities.LABEL_FONT);
        taxiwayRunwayCB.setForeground(Color.black);
        taxiwayRunwayCB.setOpaque(false);
        jetwayCB = new JCheckBox("Update Jetways when Parking Gate information changes.", SettingsEngine.getInstance().getUpdateJetways());
        jetwayCB.setFont(Utilities.LABEL_FONT);
        jetwayCB.setForeground(Color.black);
        jetwayCB.setOpaque(false);
        edgeLightCB = new JCheckBox("Display lines between Apron Edge Lights.", SettingsEngine.getInstance().getEdgeLightLines());
        edgeLightCB.setFont(Utilities.LABEL_FONT);
        edgeLightCB.setForeground(Color.black);
        edgeLightCB.setOpaque(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        Utilities.addComponent(mainPanel, altitudeCB, 0, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, adjustMeasureCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, drawPathsCB, 0, 2, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayPropertiesCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayRunwayCB, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetwayCB, 0, 5, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, edgeLightCB, 0, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the CAD tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, mainPanel, 0, 1, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 2, 1, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 4, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        SettingsEngine.getInstance().setUseAirportAlt(altitudeCB.isSelected());
        SettingsEngine.getInstance().setAdjustMeasurements(adjustMeasureCB.isSelected());
        SettingsEngine.getInstance().setDrawTaxiwayPaths(drawPathsCB.isSelected());
        SettingsEngine.getInstance().setUpdateJetways(jetwayCB.isSelected());
        SettingsEngine.getInstance().setTaxiwayProperties(taxiwayPropertiesCB.isSelected());
        SettingsEngine.getInstance().setTaxiwayRunways(taxiwayRunwayCB.isSelected());
        SettingsEngine.getInstance().setEdgeLightLines(edgeLightCB.isSelected());
        SettingsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JCheckBox altitudeCB;
    private JCheckBox adjustMeasureCB;
    private JCheckBox drawPathsCB;
    private JCheckBox jetwayCB;
    private JCheckBox taxiwayPropertiesCB;
    private JCheckBox taxiwayRunwayCB;
    private JCheckBox edgeLightCB;
}
