// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrepareDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.PrepareEngine;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.model.table.*;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PrepareDialog extends JDialog
    implements ActionListener
{

    private PrepareDialog(Frame parent)
    {
        super(parent, "Prepare Options", true);
        setResizable(false);
        airportIdent = "";
        JTextArea writeTA = new JTextArea(2, 5);
        writeTA.setFont(Utilities.BOLD_LABEL_FONT);
        writeTA.setForeground(Color.black);
        writeTA.setOpaque(false);
        writeTA.setEditable(false);
        writeTA.setLineWrap(true);
        writeTA.setWrapStyleWord(true);
        StringBuffer writeBuffer = new StringBuffer();
        writeBuffer.append("These options allow you to specify which of your newly modified objects should be inserted into FSX.");
        writeTA.setText(writeBuffer.toString());
        apronCB = new JCheckBox("Write Apron Data", PrepareEngine.getInstance().getWriteAprons());
        apronCB.setFont(Utilities.LABEL_FONT);
        apronCB.setForeground(Color.black);
        blastFenceCB = new JCheckBox("Write Blast Fences", PrepareEngine.getInstance().getWriteBlastFences());
        blastFenceCB.setFont(Utilities.LABEL_FONT);
        blastFenceCB.setForeground(Color.black);
        boundaryFenceCB = new JCheckBox("Write Boundary Fences", PrepareEngine.getInstance().getWriteBoundaryFences());
        boundaryFenceCB.setFont(Utilities.LABEL_FONT);
        boundaryFenceCB.setForeground(Color.black);
        helipadCB = new JCheckBox("Write Helipads", PrepareEngine.getInstance().getWriteHelipads());
        helipadCB.setFont(Utilities.LABEL_FONT);
        helipadCB.setForeground(Color.black);
        jetwayCB = new JCheckBox("Write Jetways", PrepareEngine.getInstance().getWriteJetways());
        jetwayCB.setFont(Utilities.LABEL_FONT);
        jetwayCB.setForeground(Color.black);
        runwayCB = new JCheckBox("Write Runways", PrepareEngine.getInstance().getWriteRunways());
        runwayCB.setFont(Utilities.LABEL_FONT);
        runwayCB.setForeground(Color.black);
        startAliasCB = new JCheckBox("Write Starting Locations", PrepareEngine.getInstance().getWriteStarts());
        startAliasCB.setFont(Utilities.LABEL_FONT);
        startAliasCB.setForeground(Color.black);
        taxiwayCB = new JCheckBox("Write Taxiway Data", PrepareEngine.getInstance().getWriteTaxiways());
        taxiwayCB.setFont(Utilities.LABEL_FONT);
        taxiwayCB.setForeground(Color.black);
        taxiSignCB = new JCheckBox("Write Taxiway Signs", PrepareEngine.getInstance().getWriteTaxiwaySigns());
        taxiSignCB.setFont(Utilities.LABEL_FONT);
        taxiSignCB.setForeground(Color.black);
        triggerCB = new JCheckBox("Write Triggers", PrepareEngine.getInstance().getWriteTriggers());
        triggerCB.setFont(Utilities.LABEL_FONT);
        triggerCB.setForeground(Color.black);
        towerCB = new JCheckBox("Write Towers", PrepareEngine.getInstance().getWriteTowers());
        towerCB.setFont(Utilities.LABEL_FONT);
        towerCB.setForeground(Color.black);
        sceneryCB = new JCheckBox("Write Scenery", PrepareEngine.getInstance().getWriteScenery());
        sceneryCB.setFont(Utilities.LABEL_FONT);
        sceneryCB.setForeground(Color.black);
        TitledBorder airportBorder = new TitledBorder(" Airport Options ");
        airportBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        airportBorder.setTitleColor(Color.black);
        JPanel airportPanel = new JPanel(new GridBagLayout());
        airportPanel.setBorder(airportBorder);
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
        comCB = new JCheckBox("Write COMs", PrepareEngine.getInstance().getWriteCOMs());
        comCB.setFont(Utilities.LABEL_FONT);
        comCB.setForeground(Color.black);
        dmeCB = new JCheckBox("Write DMEs", PrepareEngine.getInstance().getWriteDMEs());
        dmeCB.setFont(Utilities.LABEL_FONT);
        dmeCB.setForeground(Color.black);
        markerCB = new JCheckBox("Write Markers", PrepareEngine.getInstance().getWriteMarkers());
        markerCB.setFont(Utilities.LABEL_FONT);
        markerCB.setForeground(Color.black);
        ndbCB = new JCheckBox("Write NDBs", PrepareEngine.getInstance().getWriteVORs());
        ndbCB.setFont(Utilities.LABEL_FONT);
        ndbCB.setForeground(Color.black);
        vorCB = new JCheckBox("Write VORs", PrepareEngine.getInstance().getWriteVORs());
        vorCB.setFont(Utilities.LABEL_FONT);
        vorCB.setForeground(Color.black);
        TitledBorder navigationBorder = new TitledBorder(" Navigation Options ");
        navigationBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        navigationBorder.setTitleColor(Color.black);
        JPanel navigationPanel = new JPanel(new GridBagLayout());
        navigationPanel.setBorder(navigationBorder);
        Utilities.addComponent(navigationPanel, approachCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, comCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, dmeCB, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, markerCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, ndbCB, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(navigationPanel, vorCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        JPanel writePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(writePanel, writeTA, 0, 0, 1, 1, 2, 17, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(writePanel, airportPanel, 0, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(writePanel, navigationPanel, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(writePanel, Box.createGlue(), 0, 3, 1, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JTextArea deleteTA = new JTextArea(2, 5);
        deleteTA.setFont(Utilities.BOLD_LABEL_FONT);
        deleteTA.setForeground(Color.black);
        deleteTA.setOpaque(false);
        deleteTA.setEditable(false);
        deleteTA.setLineWrap(true);
        deleteTA.setWrapStyleWord(true);
        StringBuffer deleteBuffer = new StringBuffer();
        deleteBuffer.append("These options allow you to specify which default objects in FSX should be deleted.\n");
        deleteBuffer.append("(Exclusion rectangles can be used for additional delete options.)");
        deleteTA.setText(deleteBuffer.toString());
        deleteApproachCB = new JCheckBox("Delete All Approaches", PrepareEngine.getInstance().getDeleteApproaches());
        deleteApproachCB.setFont(Utilities.LABEL_FONT);
        deleteApproachCB.setForeground(Color.black);
        deleteApronsCB = new JCheckBox("Delete All Aprons", PrepareEngine.getInstance().getDeleteAprons());
        deleteApronsCB.setFont(Utilities.LABEL_FONT);
        deleteApronsCB.setForeground(Color.black);
        deleteApronLightsCB = new JCheckBox("Delete All Apron Lights", PrepareEngine.getInstance().getDeleteApronLights());
        deleteApronLightsCB.setFont(Utilities.LABEL_FONT);
        deleteApronLightsCB.setForeground(Color.black);
        deleteBlastFencesCB = new JCheckBox("Delete All Blast Fences", PrepareEngine.getInstance().getDeleteBlastFences());
        deleteBlastFencesCB.setFont(Utilities.LABEL_FONT);
        deleteBlastFencesCB.setForeground(Color.black);
        deleteBoundaryFencesCB = new JCheckBox("Delete All Boundary Fences", PrepareEngine.getInstance().getDeleteBoundaryFences());
        deleteBoundaryFencesCB.setFont(Utilities.LABEL_FONT);
        deleteBoundaryFencesCB.setForeground(Color.black);
        deleteHelipadsCB = new JCheckBox("Delete All Helipads", PrepareEngine.getInstance().getDeleteHelipads());
        deleteHelipadsCB.setFont(Utilities.LABEL_FONT);
        deleteHelipadsCB.setForeground(Color.black);
        deleteJetwaysCB = new JCheckBox("Delete All Jetways", PrepareEngine.getInstance().getDeleteJetways());
        deleteJetwaysCB.setFont(Utilities.LABEL_FONT);
        deleteJetwaysCB.setForeground(Color.black);
        deleteTaxiwaysCB = new JCheckBox("Delete All Taxiways", PrepareEngine.getInstance().getDeleteTaxiways());
        deleteTaxiwaysCB.setFont(Utilities.LABEL_FONT);
        deleteTaxiwaysCB.setForeground(Color.black);
        deleteTowersCB = new JCheckBox("Delete All Control Towers", PrepareEngine.getInstance().getDeleteTowers());
        deleteTowersCB.setFont(Utilities.LABEL_FONT);
        deleteTowersCB.setForeground(Color.black);
        TitledBorder deleteBorder = new TitledBorder(" General Delete Options ");
        deleteBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        deleteBorder.setTitleColor(Color.black);
        deleteRunwaysCB = new JCheckBox("Delete All Runways", PrepareEngine.getInstance().getDeleteRunways());
        deleteRunwaysCB.setFont(Utilities.LABEL_FONT);
        deleteRunwaysCB.setForeground(Color.black);
        deleteRunwaysCB.addActionListener(this);
        deleteRunwayLabel = new JLabel("Delete specific runways:");
        deleteRunwayLabel.setFont(Utilities.LABEL_FONT);
        deleteRunwayLabel.setForeground(Color.black);
        deleteRunwayTable = new JTable(new RunwayDeleteTableModel());
        deleteRunwayTable.setPreferredScrollableViewportSize(new Dimension(200, 60));
        deleteRunwaySP = new JScrollPane(deleteRunwayTable);
        JPanel deleteRunwayPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(deleteRunwayPanel, deleteRunwaysCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteRunwayPanel, deleteRunwayLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteRunwayPanel, deleteRunwaySP, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        deleteStartsCB = new JCheckBox("Delete All Start Points", PrepareEngine.getInstance().getDeleteStarts());
        deleteStartsCB.setFont(Utilities.LABEL_FONT);
        deleteStartsCB.setForeground(Color.black);
        deleteStartsCB.addActionListener(this);
        deleteStartLabel = new JLabel("Delete specific start points:");
        deleteStartLabel.setFont(Utilities.LABEL_FONT);
        deleteStartLabel.setForeground(Color.black);
        deleteStartTable = new JTable(new StartDeleteTableModel());
        deleteStartTable.setPreferredScrollableViewportSize(new Dimension(200, 60));
        deleteStartSP = new JScrollPane(deleteStartTable);
        JPanel deleteStartPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(deleteStartPanel, deleteStartsCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteStartPanel, deleteStartLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteStartPanel, deleteStartSP, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        deleteFrequenciesCB = new JCheckBox("Delete All Frequencies", PrepareEngine.getInstance().getDeleteFrequencies());
        deleteFrequenciesCB.setFont(Utilities.LABEL_FONT);
        deleteFrequenciesCB.setForeground(Color.black);
        deleteFrequenciesCB.addActionListener(this);
        deleteFrequencyLabel = new JLabel("Delete specific frequencies:");
        deleteFrequencyLabel.setFont(Utilities.LABEL_FONT);
        deleteFrequencyLabel.setForeground(Color.black);
        deleteFrequencyTable = new JTable(new FrequencyDeleteTableModel());
        deleteFrequencyTable.setPreferredScrollableViewportSize(new Dimension(200, 60));
        deleteFrequencySP = new JScrollPane(deleteFrequencyTable);
        JPanel deleteFrequencyPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(deleteFrequencyPanel, deleteFrequenciesCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteFrequencyPanel, deleteFrequencyLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteFrequencyPanel, deleteFrequencySP, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        JTabbedPane deleteTabbedPane = new JTabbedPane();
        deleteTabbedPane.setFont(Utilities.LABEL_FONT);
        deleteTabbedPane.add(deleteRunwayPanel, "Delete Runways");
        deleteTabbedPane.add(deleteStartPanel, "Delete Start Points");
        deleteTabbedPane.add(deleteFrequencyPanel, "Delete Frequencies");
        deleteDefaultCB = new JCheckBox("Set as defaults");
        deleteDefaultCB.setFont(Utilities.LABEL_FONT);
        deleteDefaultCB.setForeground(Color.black);
        JPanel deleteOptionsPanel = new JPanel(new GridBagLayout());
        deleteOptionsPanel.setBorder(deleteBorder);
        Utilities.addComponent(deleteOptionsPanel, deleteApproachCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteApronsCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteApronLightsCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteBlastFencesCB, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteBoundaryFencesCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteHelipadsCB, 1, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteJetwaysCB, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteTaxiwaysCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteTowersCB, 2, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteTabbedPane, 0, 3, 3, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, deleteDefaultCB, 0, 4, 3, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(deleteOptionsPanel, Box.createGlue(), 0, 5, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JPanel deletePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(deletePanel, deleteTA, 0, 0, 1, 1, 2, 10, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(deletePanel, deleteOptionsPanel, 0, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(writePanel, "Write Options");
        tabbedPane.add(deletePanel, "Delete Options");
        getContentPane().add(tabbedPane, "Center");
        okButton = new JButton("OK");
        okButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        okButton.setForeground(Color.black);
        okButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        setRunwaysEnabled(!deleteRunwaysCB.isSelected());
        setStartsEnabled(!deleteStartsCB.isSelected());
        setFrequenciesEnabled(!deleteFrequenciesCB.isSelected());
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new PrepareDialog(parent);
        dialog.updateDialog(airportModel);
        dialog.setVisible(true);
    }

    private void updateDialog(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        if(airportModel == null)
        {
            airportIdent = "";
            ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).clearData();
            ((StartDeleteTableModel)deleteStartTable.getModel()).clearData();
            ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).clearData();
        } else
        {
            ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).clearData();
            ((StartDeleteTableModel)deleteStartTable.getModel()).clearData();
            ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).clearData();
            ArrayList runwayAL = airportModel.getRunwayAL();
            for(int i = 0; i < runwayAL.size(); i++)
            {
                RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
                HashMap hashMap = new HashMap();
                hashMap.put("number", runwayModel.getNumber());
                hashMap.put("designator", runwayModel.getDesignator());
                hashMap.put("surface", runwayModel.getSurface());
                ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).addRow(hashMap);
            }

            ArrayList startAL = airportModel.getStartAL();
            for(int i = 0; i < startAL.size(); i++)
            {
                StartModel startModel = (StartModel)startAL.get(i);
                HashMap hashMap = new HashMap();
                hashMap.put("type", startModel.getType());
                hashMap.put("number", startModel.getNumber());
                hashMap.put("designator", startModel.getDesignator());
                ((StartDeleteTableModel)deleteStartTable.getModel()).addRow(hashMap);
            }

            ArrayList comAL = airportModel.getComAL();
            for(int i = 0; i < comAL.size(); i++)
            {
                ComModel comModel = (ComModel)comAL.get(i);
                HashMap hashMap = new HashMap();
                hashMap.put("frequency", (new StringBuilder()).append("").append(comModel.getFrequency()).toString());
                hashMap.put("type", comModel.getType());
                ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).addRow(hashMap);
            }

            airportIdent = airportModel.getIdent();
            ArrayList arrayList = airportModel.getDeleteModel().getRunwayAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).setDeleteRow((String)hashMap.get("number"), (String)hashMap.get("designator"), (String)hashMap.get("surface"));
            }

            arrayList = airportModel.getDeleteModel().getStartAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                ((StartDeleteTableModel)deleteStartTable.getModel()).setDeleteRow((String)hashMap.get("type"), (String)hashMap.get("number"), (String)hashMap.get("designator"));
            }

            arrayList = airportModel.getDeleteModel().getFrequencyAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).setDeleteRow((String)hashMap.get("frequency"), (String)hashMap.get("type"));
            }

            deleteApproachCB.setSelected(airportModel.getDeleteModel().getDeleteAllApproaches());
            deleteApronLightsCB.setSelected(airportModel.getDeleteModel().getDeleteAllApronLights());
            deleteApronsCB.setSelected(airportModel.getDeleteModel().getDeleteAllAprons());
            deleteFrequenciesCB.setSelected(airportModel.getDeleteModel().getDeleteAllFrequencies());
            deleteHelipadsCB.setSelected(airportModel.getDeleteModel().getDeleteAllHelipads());
            deleteRunwaysCB.setSelected(airportModel.getDeleteModel().getDeleteAllRunways());
            deleteStartsCB.setSelected(airportModel.getDeleteModel().getDeleteAllStarts());
            deleteTaxiwaysCB.setSelected(airportModel.getDeleteModel().getDeleteAllTaxiways());
            deleteBlastFencesCB.setSelected(airportModel.getDeleteModel().getDeleteAllBlastFences());
            deleteBoundaryFencesCB.setSelected(airportModel.getDeleteModel().getDeleteAllBoundaryFences());
            deleteTowersCB.setSelected(airportModel.getDeleteModel().getDeleteAllControlTowers());
            deleteJetwaysCB.setSelected(airportModel.getDeleteModel().getDeleteAllJetways());
        }
    }

    private void updateOptions()
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
        if(deleteDefaultCB.isSelected())
        {
            PrepareEngine.getInstance().setDeleteApproaches(deleteApproachCB.isSelected());
            PrepareEngine.getInstance().setDeleteApronLights(deleteApronLightsCB.isSelected());
            PrepareEngine.getInstance().setDeleteAprons(deleteApronsCB.isSelected());
            PrepareEngine.getInstance().setDeleteFrequencies(deleteFrequenciesCB.isSelected());
            PrepareEngine.getInstance().setDeleteHelipads(deleteHelipadsCB.isSelected());
            PrepareEngine.getInstance().setDeleteRunways(deleteRunwaysCB.isSelected());
            PrepareEngine.getInstance().setDeleteStarts(deleteStartsCB.isSelected());
            PrepareEngine.getInstance().setDeleteTaxiways(deleteTaxiwaysCB.isSelected());
            PrepareEngine.getInstance().setDeleteBlastFences(deleteBlastFencesCB.isSelected());
            PrepareEngine.getInstance().setDeleteBoundaryFences(deleteBoundaryFencesCB.isSelected());
            PrepareEngine.getInstance().setDeleteTowers(deleteTowersCB.isSelected());
            PrepareEngine.getInstance().setDeleteJetways(deleteJetwaysCB.isSelected());
            ArrayList arrayList = new ArrayList();
            ArrayList runwayAL = ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).getDeleted();
            for(int i = runwayAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("number", ((HashMap)runwayAL.get(i)).get("number"));
                hashMap.put("designator", ((HashMap)runwayAL.get(i)).get("designator"));
                hashMap.put("surface", ((HashMap)runwayAL.get(i)).get("surface"));
                arrayList.add(hashMap);
            }

            PrepareEngine.getInstance().getRunwayHM().put(airportIdent, arrayList);
            arrayList = new ArrayList();
            ArrayList startAL = ((StartDeleteTableModel)deleteStartTable.getModel()).getDeleted();
            for(int i = startAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("type", ((HashMap)startAL.get(i)).get("type"));
                hashMap.put("number", ((HashMap)startAL.get(i)).get("number"));
                hashMap.put("designator", ((HashMap)startAL.get(i)).get("designator"));
                arrayList.add(hashMap);
            }

            PrepareEngine.getInstance().getStartHM().put(airportIdent, arrayList);
            arrayList = new ArrayList();
            ArrayList frequencyAL = ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).getDeleted();
            for(int i = frequencyAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("frequency", ((HashMap)frequencyAL.get(i)).get("frequency"));
                hashMap.put("type", ((HashMap)frequencyAL.get(i)).get("type"));
                arrayList.add(hashMap);
            }

            PrepareEngine.getInstance().getFrequencyHM().put(airportIdent, arrayList);
        }
        if(airportModel != null)
        {
            airportModel.getDeleteModel().setDeleteAllApproaches(deleteApproachCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllApronLights(deleteApronLightsCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllAprons(deleteApronsCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllFrequencies(deleteFrequenciesCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllHelipads(deleteHelipadsCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllRunways(deleteRunwaysCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllStarts(deleteStartsCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllTaxiways(deleteTaxiwaysCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllBlastFences(deleteBlastFencesCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllBoundaryFences(deleteBoundaryFencesCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllControlTowers(deleteTowersCB.isSelected());
            airportModel.getDeleteModel().setDeleteAllJetways(deleteJetwaysCB.isSelected());
            ArrayList arrayList = new ArrayList();
            ArrayList runwayAL = ((RunwayDeleteTableModel)deleteRunwayTable.getModel()).getDeleted();
            for(int i = runwayAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("number", ((HashMap)runwayAL.get(i)).get("number"));
                hashMap.put("designator", ((HashMap)runwayAL.get(i)).get("designator"));
                hashMap.put("surface", ((HashMap)runwayAL.get(i)).get("surface"));
                arrayList.add(hashMap);
            }

            airportModel.getDeleteModel().setRunwayAL(arrayList);
            arrayList = new ArrayList();
            ArrayList startAL = ((StartDeleteTableModel)deleteStartTable.getModel()).getDeleted();
            for(int i = startAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("type", ((HashMap)startAL.get(i)).get("type"));
                hashMap.put("number", ((HashMap)startAL.get(i)).get("number"));
                hashMap.put("designator", ((HashMap)startAL.get(i)).get("designator"));
                arrayList.add(hashMap);
            }

            airportModel.getDeleteModel().setStartAL(arrayList);
            arrayList = new ArrayList();
            ArrayList frequencyAL = ((FrequencyDeleteTableModel)deleteFrequencyTable.getModel()).getDeleted();
            for(int i = frequencyAL.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("frequency", ((HashMap)frequencyAL.get(i)).get("frequency"));
                hashMap.put("type", ((HashMap)frequencyAL.get(i)).get("type"));
                arrayList.add(hashMap);
            }

            airportModel.getDeleteModel().setFrequencyAL(arrayList);
        }
        PrepareEngine.getInstance().writePreferences();
    }

    private void setFrequenciesEnabled(boolean enabled)
    {
        deleteFrequencyLabel.setEnabled(enabled);
        deleteFrequencyTable.setEnabled(enabled);
        deleteFrequencySP.setEnabled(enabled);
    }

    private void setRunwaysEnabled(boolean enabled)
    {
        deleteRunwayLabel.setEnabled(enabled);
        deleteRunwayTable.setEnabled(enabled);
        deleteRunwaySP.setEnabled(enabled);
    }

    private void setStartsEnabled(boolean enabled)
    {
        deleteStartLabel.setEnabled(enabled);
        deleteStartTable.setEnabled(enabled);
        deleteStartSP.setEnabled(enabled);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            updateOptions();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
        else
        if(event.getSource() == deleteFrequenciesCB)
            setFrequenciesEnabled(!deleteFrequenciesCB.isSelected());
        else
        if(event.getSource() == deleteRunwaysCB)
            setRunwaysEnabled(!deleteRunwaysCB.isSelected());
        else
        if(event.getSource() == deleteStartsCB)
            setStartsEnabled(!deleteStartsCB.isSelected());
    }

    private AirportModel airportModel;
    private String airportIdent;
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
    private JCheckBox deleteApproachCB;
    private JCheckBox deleteApronLightsCB;
    private JCheckBox deleteApronsCB;
    private JCheckBox deleteFrequenciesCB;
    private JCheckBox deleteHelipadsCB;
    private JCheckBox deleteRunwaysCB;
    private JCheckBox deleteStartsCB;
    private JCheckBox deleteTaxiwaysCB;
    private JCheckBox deleteBlastFencesCB;
    private JCheckBox deleteBoundaryFencesCB;
    private JCheckBox deleteTowersCB;
    private JCheckBox deleteJetwaysCB;
    private JCheckBox deleteDefaultCB;
    private JTable deleteRunwayTable;
    private JTable deleteStartTable;
    private JTable deleteFrequencyTable;
    private JLabel deleteRunwayLabel;
    private JLabel deleteStartLabel;
    private JLabel deleteFrequencyLabel;
    private JScrollPane deleteRunwaySP;
    private JScrollPane deleteStartSP;
    private JScrollPane deleteFrequencySP;
    private JButton okButton;
    private JButton cancelButton;
    private static PrepareDialog dialog = null;

}
