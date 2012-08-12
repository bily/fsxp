// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LockingDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.LockingEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LockingDialog extends JDialog
    implements ActionListener
{

    public LockingDialog(Frame parent)
    {
        super(parent, "Object Locking", true);
        JTextArea instTA = new JTextArea(4, 50);
        instTA.setFont(Utilities.LABEL_FONT);
        instTA.setForeground(Color.black);
        instTA.setEditable(false);
        instTA.setOpaque(false);
        instTA.setLineWrap(true);
        instTA.setWrapStyleWord(true);
        StringBuffer buffer = new StringBuffer();
        buffer.append("This dialog lets you specify which types of objects are locked.");
        buffer.append(" A locked object cannot be moved.  This can be useful if you have placed");
        buffer.append(" objects, such as runways, and do not want to accidentally move them.");
        instTA.setText(buffer.toString());
        runwayCB = new JCheckBox("Runways", LockingEngine.getInstance().getRunwaysLocked());
        runwayCB.setFont(Utilities.LABEL_FONT);
        runwayCB.setForeground(Color.black);
        taxiwayCB = new JCheckBox("Taxiway Points", LockingEngine.getInstance().getTaxiwaysLocked());
        taxiwayCB.setFont(Utilities.LABEL_FONT);
        taxiwayCB.setForeground(Color.black);
        taxiwaySignCB = new JCheckBox("Taxiway Signs", LockingEngine.getInstance().getTaxiwaySignsLocked());
        taxiwaySignCB.setFont(Utilities.LABEL_FONT);
        taxiwaySignCB.setForeground(Color.black);
        jetwayCB = new JCheckBox("Jetways", LockingEngine.getInstance().getJetwaysLocked());
        jetwayCB.setFont(Utilities.LABEL_FONT);
        jetwayCB.setForeground(Color.black);
        parkingCB = new JCheckBox("Parking Locations", LockingEngine.getInstance().getParkingLocked());
        parkingCB.setFont(Utilities.LABEL_FONT);
        parkingCB.setForeground(Color.black);
        startingCB = new JCheckBox("Starting Locations", LockingEngine.getInstance().getStartingLocked());
        startingCB.setFont(Utilities.LABEL_FONT);
        startingCB.setForeground(Color.black);
        helipadCB = new JCheckBox("Helipads", LockingEngine.getInstance().getHelipadsLocked());
        helipadCB.setFont(Utilities.LABEL_FONT);
        helipadCB.setForeground(Color.black);
        towerCB = new JCheckBox("Towers", LockingEngine.getInstance().getTowersLocked());
        towerCB.setFont(Utilities.LABEL_FONT);
        towerCB.setForeground(Color.black);
        apronCB = new JCheckBox("Aprons", LockingEngine.getInstance().getApronsLocked());
        apronCB.setFont(Utilities.LABEL_FONT);
        apronCB.setForeground(Color.black);
        edgeLightCB = new JCheckBox("Apron Edge Lights", LockingEngine.getInstance().getEdgeLightsLocked());
        edgeLightCB.setFont(Utilities.LABEL_FONT);
        edgeLightCB.setForeground(Color.black);
        boundaryCB = new JCheckBox("Boundary Fences", LockingEngine.getInstance().getBoundaryLocked());
        boundaryCB.setFont(Utilities.LABEL_FONT);
        boundaryCB.setForeground(Color.black);
        blastCB = new JCheckBox("Blast Fences", LockingEngine.getInstance().getBlastLocked());
        blastCB.setFont(Utilities.LABEL_FONT);
        blastCB.setForeground(Color.black);
        exclusionCB = new JCheckBox("Exclusion Rectangles", LockingEngine.getInstance().getExclusionLocked());
        exclusionCB.setFont(Utilities.LABEL_FONT);
        exclusionCB.setForeground(Color.black);
        ilsCB = new JCheckBox("ILS Feathers", LockingEngine.getInstance().getILSLocked());
        ilsCB.setFont(Utilities.LABEL_FONT);
        ilsCB.setForeground(Color.black);
        markerCB = new JCheckBox("Markers", LockingEngine.getInstance().getMarkersLocked());
        markerCB.setFont(Utilities.LABEL_FONT);
        markerCB.setForeground(Color.black);
        vorCB = new JCheckBox("VORs", LockingEngine.getInstance().getVORLocked());
        vorCB.setFont(Utilities.LABEL_FONT);
        vorCB.setForeground(Color.black);
        ndbCB = new JCheckBox("NDBs", LockingEngine.getInstance().getNDBLocked());
        ndbCB.setFont(Utilities.LABEL_FONT);
        ndbCB.setForeground(Color.black);
        windsockCB = new JCheckBox("Windsocks", LockingEngine.getInstance().getWindsocksLocked());
        windsockCB.setFont(Utilities.LABEL_FONT);
        windsockCB.setForeground(Color.black);
        triggerCB = new JCheckBox("Fuel Triggers", LockingEngine.getInstance().getTriggersLocked());
        triggerCB.setFont(Utilities.LABEL_FONT);
        triggerCB.setForeground(Color.black);
        sceneryCB = new JCheckBox("Scenery Objects", LockingEngine.getInstance().getSceneryLocked());
        sceneryCB.setFont(Utilities.LABEL_FONT);
        sceneryCB.setForeground(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, instTA, 0, 0, 4, 1, 2, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, apronCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, edgeLightCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, blastCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, boundaryCB, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, exclusionCB, 0, 5, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, ilsCB, 1, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, jetwayCB, 1, 3, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, markerCB, 1, 4, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, ndbCB, 1, 5, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, parkingCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayCB, 2, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryCB, 2, 3, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, startingCB, 2, 4, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayCB, 2, 5, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwaySignCB, 3, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, towerCB, 3, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, triggerCB, 3, 3, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vorCB, 3, 4, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, windsockCB, 3, 5, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, new JSeparator(), 0, 6, 4, 1, 2, 10, new Insets(10, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        okButton = new JButton("OK");
        okButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        okButton.setForeground(Color.black);
        okButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent)
    {
        if(dialog == null)
            dialog = new LockingDialog(parent);
        dialog.setVisible(true);
    }

    private void recordLocks()
    {
        LockingEngine.getInstance().setRunwaysLocked(runwayCB.isSelected());
        LockingEngine.getInstance().setTaxiwaysLocked(taxiwayCB.isSelected());
        LockingEngine.getInstance().setTaxiwaySignsLocked(taxiwaySignCB.isSelected());
        LockingEngine.getInstance().setJetwaysLocked(jetwayCB.isSelected());
        LockingEngine.getInstance().setParkingLocked(parkingCB.isSelected());
        LockingEngine.getInstance().setStartingLocked(startingCB.isSelected());
        LockingEngine.getInstance().setHelipadsLocked(helipadCB.isSelected());
        LockingEngine.getInstance().setTowersLocked(towerCB.isSelected());
        LockingEngine.getInstance().setApronsLocked(apronCB.isSelected());
        LockingEngine.getInstance().setEdgeLightsLocked(edgeLightCB.isSelected());
        LockingEngine.getInstance().setBoundaryLocked(boundaryCB.isSelected());
        LockingEngine.getInstance().setBlastLocked(blastCB.isSelected());
        LockingEngine.getInstance().setExclusionLocked(exclusionCB.isSelected());
        LockingEngine.getInstance().setILSLocked(ilsCB.isSelected());
        LockingEngine.getInstance().setMarkersLocked(markerCB.isSelected());
        LockingEngine.getInstance().setVORLocked(vorCB.isSelected());
        LockingEngine.getInstance().setNDBLocked(ndbCB.isSelected());
        LockingEngine.getInstance().setWindsocksLocked(windsockCB.isSelected());
        LockingEngine.getInstance().setTriggersLocked(triggerCB.isSelected());
        LockingEngine.getInstance().setSceneryLocked(sceneryCB.isSelected());
        LockingEngine.getInstance().writeLocks();
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            recordLocks();
            setVisible(false);
        }
    }

    private JCheckBox runwayCB;
    private JCheckBox taxiwayCB;
    private JCheckBox taxiwaySignCB;
    private JCheckBox jetwayCB;
    private JCheckBox parkingCB;
    private JCheckBox startingCB;
    private JCheckBox helipadCB;
    private JCheckBox towerCB;
    private JCheckBox apronCB;
    private JCheckBox edgeLightCB;
    private JCheckBox boundaryCB;
    private JCheckBox blastCB;
    private JCheckBox exclusionCB;
    private JCheckBox ilsCB;
    private JCheckBox markerCB;
    private JCheckBox vorCB;
    private JCheckBox ndbCB;
    private JCheckBox windsockCB;
    private JCheckBox triggerCB;
    private JCheckBox sceneryCB;
    private JButton okButton;
    private static LockingDialog dialog = null;

}
