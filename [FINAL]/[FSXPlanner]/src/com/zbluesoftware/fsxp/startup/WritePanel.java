// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WritePanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.PrepareEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class WritePanel extends JPanel
    implements StartupPanel
{

    public WritePanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Data To Be Written to BGL Files");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JTextArea writeTA = new JTextArea(2, 5);
        writeTA.setFont(Utilities.BOLD_LABEL_FONT);
        writeTA.setForeground(Color.black);
        writeTA.setOpaque(false);
        writeTA.setEditable(false);
        writeTA.setLineWrap(true);
        writeTA.setWrapStyleWord(true);
        StringBuffer writeBuffer = new StringBuffer();
        writeBuffer.append("These options allow you to specify which of your newly modified objects should be compiled into the BGL file.");
        writeTA.setText(writeBuffer.toString());
        apronCB = new JCheckBox("Write Apron Data", PrepareEngine.getInstance().getWriteAprons());
        apronCB.setFont(Utilities.LABEL_FONT);
        apronCB.setForeground(Color.black);
        apronCB.setOpaque(false);
        blastFenceCB = new JCheckBox("Write Blast Fences", PrepareEngine.getInstance().getWriteBlastFences());
        blastFenceCB.setFont(Utilities.LABEL_FONT);
        blastFenceCB.setForeground(Color.black);
        blastFenceCB.setOpaque(false);
        boundaryFenceCB = new JCheckBox("Write Boundary Fences", PrepareEngine.getInstance().getWriteBoundaryFences());
        boundaryFenceCB.setFont(Utilities.LABEL_FONT);
        boundaryFenceCB.setForeground(Color.black);
        boundaryFenceCB.setOpaque(false);
        helipadCB = new JCheckBox("Write Helipads", PrepareEngine.getInstance().getWriteHelipads());
        helipadCB.setFont(Utilities.LABEL_FONT);
        helipadCB.setForeground(Color.black);
        helipadCB.setOpaque(false);
        jetwayCB = new JCheckBox("Write Jetways", PrepareEngine.getInstance().getWriteJetways());
        jetwayCB.setFont(Utilities.LABEL_FONT);
        jetwayCB.setForeground(Color.black);
        jetwayCB.setOpaque(false);
        runwayCB = new JCheckBox("Write Runways", PrepareEngine.getInstance().getWriteRunways());
        runwayCB.setFont(Utilities.LABEL_FONT);
        runwayCB.setForeground(Color.black);
        runwayCB.setOpaque(false);
        startAliasCB = new JCheckBox("Write Starting Locations", PrepareEngine.getInstance().getWriteStarts());
        startAliasCB.setFont(Utilities.LABEL_FONT);
        startAliasCB.setForeground(Color.black);
        startAliasCB.setOpaque(false);
        taxiwayCB = new JCheckBox("Write Taxiway Data", PrepareEngine.getInstance().getWriteTaxiways());
        taxiwayCB.setFont(Utilities.LABEL_FONT);
        taxiwayCB.setForeground(Color.black);
        taxiwayCB.setOpaque(false);
        taxiSignCB = new JCheckBox("Write Taxiway Signs", PrepareEngine.getInstance().getWriteTaxiwaySigns());
        taxiSignCB.setFont(Utilities.LABEL_FONT);
        taxiSignCB.setForeground(Color.black);
        taxiSignCB.setOpaque(false);
        triggerCB = new JCheckBox("Write Triggers", PrepareEngine.getInstance().getWriteTriggers());
        triggerCB.setFont(Utilities.LABEL_FONT);
        triggerCB.setForeground(Color.black);
        triggerCB.setOpaque(false);
        towerCB = new JCheckBox("Write Towers", PrepareEngine.getInstance().getWriteTowers());
        towerCB.setFont(Utilities.LABEL_FONT);
        towerCB.setForeground(Color.black);
        towerCB.setOpaque(false);
        sceneryCB = new JCheckBox("Write Scenery", PrepareEngine.getInstance().getWriteScenery());
        sceneryCB.setFont(Utilities.LABEL_FONT);
        sceneryCB.setForeground(Color.black);
        sceneryCB.setOpaque(false);
        TitledBorder airportBorder = new TitledBorder(" Airport Options ");
        airportBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        airportBorder.setTitleColor(Color.black);
        JPanel airportPanel = new JPanel(new GridBagLayout());
        airportPanel.setBorder(airportBorder);
        airportPanel.setOpaque(false);
        Utilities.addComponent(airportPanel, apronCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, blastFenceCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, boundaryFenceCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, helipadCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, jetwayCB, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, runwayCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, sceneryCB, 1, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, startAliasCB, 1, 3, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, taxiwayCB, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, taxiSignCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, triggerCB, 2, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(airportPanel, towerCB, 2, 3, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        approachCB = new JCheckBox("Write Approaches", PrepareEngine.getInstance().getWriteApproaches());
        approachCB.setFont(Utilities.LABEL_FONT);
        approachCB.setForeground(Color.black);
        approachCB.setOpaque(false);
        comCB = new JCheckBox("Write COMs", PrepareEngine.getInstance().getWriteCOMs());
        comCB.setFont(Utilities.LABEL_FONT);
        comCB.setForeground(Color.black);
        comCB.setOpaque(false);
        dmeCB = new JCheckBox("Write DMEs", PrepareEngine.getInstance().getWriteDMEs());
        dmeCB.setFont(Utilities.LABEL_FONT);
        dmeCB.setForeground(Color.black);
        dmeCB.setOpaque(false);
        markerCB = new JCheckBox("Write Markers", PrepareEngine.getInstance().getWriteMarkers());
        markerCB.setFont(Utilities.LABEL_FONT);
        markerCB.setForeground(Color.black);
        markerCB.setOpaque(false);
        ndbCB = new JCheckBox("Write NDBs", PrepareEngine.getInstance().getWriteVORs());
        ndbCB.setFont(Utilities.LABEL_FONT);
        ndbCB.setForeground(Color.black);
        ndbCB.setOpaque(false);
        vorCB = new JCheckBox("Write VORs", PrepareEngine.getInstance().getWriteVORs());
        vorCB.setFont(Utilities.LABEL_FONT);
        vorCB.setForeground(Color.black);
        vorCB.setOpaque(false);
        TitledBorder navigationBorder = new TitledBorder(" Navigation Options ");
        navigationBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        navigationBorder.setTitleColor(Color.black);
        JPanel navigationPanel = new JPanel(new GridBagLayout());
        navigationPanel.setBorder(navigationBorder);
        navigationPanel.setOpaque(false);
        Utilities.addComponent(navigationPanel, approachCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, comCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, dmeCB, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, markerCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, ndbCB, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, vorCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the Write Options tab of the Prepare Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(Compile -> Prepare Options)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, writeTA, 0, 1, 1, 1, 2, 17, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, airportPanel, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, navigationPanel, 0, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 4, 1, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 6, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        PrepareEngine.getInstance().setWriteAprons(apronCB.isSelected());
        PrepareEngine.getInstance().setWriteBlastFences(blastFenceCB.isSelected());
        PrepareEngine.getInstance().setWriteBoundaryFences(boundaryFenceCB.isSelected());
        PrepareEngine.getInstance().setWriteHelipads(helipadCB.isSelected());
        PrepareEngine.getInstance().setWriteJetways(jetwayCB.isSelected());
        PrepareEngine.getInstance().setWriteRunways(runwayCB.isSelected());
        PrepareEngine.getInstance().setWriteStarts(startAliasCB.isSelected());
        PrepareEngine.getInstance().setWriteTaxiways(taxiwayCB.isSelected());
        PrepareEngine.getInstance().setWriteTaxiwaySigns(taxiSignCB.isSelected());
        PrepareEngine.getInstance().setWriteTriggers(triggerCB.isSelected());
        PrepareEngine.getInstance().setWriteTowers(towerCB.isSelected());
        PrepareEngine.getInstance().setWriteScenery(sceneryCB.isSelected());
        PrepareEngine.getInstance().setWriteApproaches(approachCB.isSelected());
        PrepareEngine.getInstance().setWriteCOMs(comCB.isSelected());
        PrepareEngine.getInstance().setWriteDMEs(dmeCB.isSelected());
        PrepareEngine.getInstance().setWriteMarkers(markerCB.isSelected());
        PrepareEngine.getInstance().setWriteNDBs(ndbCB.isSelected());
        PrepareEngine.getInstance().setWriteVORs(vorCB.isSelected());
        PrepareEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JCheckBox apronCB;
    private JCheckBox blastFenceCB;
    private JCheckBox boundaryFenceCB;
    private JCheckBox helipadCB;
    private JCheckBox jetwayCB;
    private JCheckBox runwayCB;
    private JCheckBox sceneryCB;
    private JCheckBox startAliasCB;
    private JCheckBox taxiwayCB;
    private JCheckBox taxiSignCB;
    private JCheckBox triggerCB;
    private JCheckBox towerCB;
    private JCheckBox approachCB;
    private JCheckBox comCB;
    private JCheckBox dmeCB;
    private JCheckBox markerCB;
    private JCheckBox ndbCB;
    private JCheckBox vorCB;
}
