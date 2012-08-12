// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegPanel.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.LegModel;
import com.zbluesoftware.fsxp.renderer.IndexNameRenderer;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;

public class LegPanel extends JPanel
    implements ActionListener
{

    public LegPanel(ActionListener actionListener)
    {
        legTypeLabel = new JLabel("Type");
        legTypeLabel.setFont(Utilities.LABEL_FONT);
        legTypeLabel.setForeground(Color.black);
        DefaultComboBoxModel legTypeModel = new DefaultComboBoxModel();
        legTypeModel.addElement("AF");
        legTypeModel.addElement("CA");
        legTypeModel.addElement("CD");
        legTypeModel.addElement("CF");
        legTypeModel.addElement("CI");
        legTypeModel.addElement("CR");
        legTypeModel.addElement("DF");
        legTypeModel.addElement("FA");
        legTypeModel.addElement("FC");
        legTypeModel.addElement("FD");
        legTypeModel.addElement("FM");
        legTypeModel.addElement("HA");
        legTypeModel.addElement("HF");
        legTypeModel.addElement("HM");
        legTypeModel.addElement("IF");
        legTypeModel.addElement("PI");
        legTypeModel.addElement("RF");
        legTypeModel.addElement("TF");
        legTypeModel.addElement("VA");
        legTypeModel.addElement("VD");
        legTypeModel.addElement("VI");
        legTypeModel.addElement("VM");
        legTypeModel.addElement("VR");
        legTypeComboBox = new JComboBox(legTypeModel);
        legTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        legTypeComboBox.setForeground(Color.black);
        legTypeComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            legTypeComboBox.setPrototypeDisplayValue("--------");
        legTurnLabel = new JLabel("Turn Direction");
        legTurnLabel.setFont(Utilities.LABEL_FONT);
        legTurnLabel.setForeground(Color.black);
        legTurnComboBox = new JComboBox(getTurnModel());
        legTurnComboBox.setFont(Utilities.COMBO_BOX_FONT);
        legTurnComboBox.setForeground(Color.black);
        legTurnComboBox.setRenderer(new IndexNameRenderer());
        legTurnComboBox.setSelectedIndex(2);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            legTurnComboBox.setPrototypeDisplayValue("---------------");
        legFlyOverLabel = new JLabel("Fly Over");
        legFlyOverLabel.setFont(Utilities.LABEL_FONT);
        legFlyOverLabel.setForeground(Color.black);
        legFlyOverComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        legFlyOverComboBox.setFont(Utilities.COMBO_BOX_FONT);
        legFlyOverComboBox.setForeground(Color.black);
        legFlyOverComboBox.setSelectedIndex(1);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            legFlyOverComboBox.setPrototypeDisplayValue("--------------");
        JPanel legTypePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(legTypePanel, legTypeLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(legTypePanel, legTypeComboBox, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(legTypePanel, legTurnLabel, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(legTypePanel, legTurnComboBox, 3, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(legTypePanel, legFlyOverLabel, 4, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(legTypePanel, legFlyOverComboBox, 5, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        legFixTypeLabel = new JLabel("Fix Type");
        legFixTypeLabel.setFont(Utilities.LABEL_FONT);
        legFixTypeLabel.setForeground(Color.black);
        DefaultComboBoxModel legFixTypeModel = new DefaultComboBoxModel();
        legFixTypeModel.addElement("----");
        legFixTypeModel.addElement("NDB");
        legFixTypeModel.addElement("TERMINAL_NDB");
        legFixTypeModel.addElement("TERMINAL_WAYPOINT");
        legFixTypeModel.addElement("VOR");
        legFixTypeModel.addElement("WAYPOINT");
        legFixTypeModel.addElement("RUNWAY");
        legFixTypeModel.addElement("LOCALIZER");
        legFixTypeComboBox = new JComboBox(legFixTypeModel);
        legFixTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        legFixTypeComboBox.setForeground(Color.black);
        legFixRegionLabel = new JLabel("Fix Region");
        legFixRegionLabel.setFont(Utilities.LABEL_FONT);
        legFixRegionLabel.setForeground(Color.black);
        legFixRegionTF = new PopupTextField(5);
        legFixRegionTF.setFont(Utilities.TEXT_FIELD_FONT);
        legFixRegionTF.setForeground(Color.black);
        legFixRegionTF.setDocument(new MaxLengthDocument(2));
        legFixIdentLabel = new JLabel("Fix Ident");
        legFixIdentLabel.setFont(Utilities.LABEL_FONT);
        legFixIdentLabel.setForeground(Color.black);
        legFixIdentTF = new PopupTextField(5);
        legFixIdentTF.setFont(Utilities.TEXT_FIELD_FONT);
        legFixIdentTF.setForeground(Color.black);
        legFixIdentTF.setDocument(new MaxLengthDocument(5));
        JPanel fixPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(fixPanel, legFixTypeLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fixPanel, legFixTypeComboBox, 1, 0, 5, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fixPanel, legFixRegionLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fixPanel, legFixRegionTF, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fixPanel, legFixIdentLabel, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(fixPanel, legFixIdentTF, 3, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        legRecTypeLabel = new JLabel("Recommended Type");
        legRecTypeLabel.setFont(Utilities.LABEL_FONT);
        legRecTypeLabel.setForeground(Color.black);
        DefaultComboBoxModel legRecTypeModel = new DefaultComboBoxModel();
        legRecTypeModel.addElement("----");
        legRecTypeModel.addElement("NDB");
        legRecTypeModel.addElement("TERMINAL_NDB");
        legRecTypeModel.addElement("TERMINAL_WAYPOINT");
        legRecTypeModel.addElement("VOR");
        legRecTypeModel.addElement("WAYPOINT");
        legRecTypeModel.addElement("RUNWAY");
        legRecTypeModel.addElement("LOCALIZER");
        legRecTypeComboBox = new JComboBox(legRecTypeModel);
        legRecTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        legRecTypeComboBox.setForeground(Color.black);
        legRecRegionLabel = new JLabel("Recommended Region");
        legRecRegionLabel.setFont(Utilities.LABEL_FONT);
        legRecRegionLabel.setForeground(Color.black);
        legRecRegionTF = new PopupTextField(5);
        legRecRegionTF.setFont(Utilities.TEXT_FIELD_FONT);
        legRecRegionTF.setForeground(Color.black);
        legRecRegionTF.setDocument(new MaxLengthDocument(2));
        legRecIdentLabel = new JLabel("Recommended Ident");
        legRecIdentLabel.setFont(Utilities.LABEL_FONT);
        legRecIdentLabel.setForeground(Color.black);
        legRecIdentTF = new PopupTextField(5);
        legRecIdentTF.setFont(Utilities.TEXT_FIELD_FONT);
        legRecIdentTF.setForeground(Color.black);
        legRecIdentTF.setDocument(new MaxLengthDocument(5));
        JPanel recPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(recPanel, legRecTypeLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(recPanel, legRecTypeComboBox, 1, 0, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(recPanel, legRecRegionLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(recPanel, legRecRegionTF, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(recPanel, legRecIdentLabel, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(recPanel, legRecIdentTF, 3, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        thetaLabel = new JLabel("Heading");
        thetaLabel.setFont(Utilities.LABEL_FONT);
        thetaLabel.setForeground(Color.black);
        thetaSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        thetaSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(thetaSpinner, "0.000"));
        rhoLabel = new JLabel("Distance");
        rhoLabel.setFont(Utilities.LABEL_FONT);
        rhoLabel.setForeground(Color.black);
        rhoSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        rhoSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(rhoSpinner, "0.0"));
        rhoComboBox = new JComboBox(new String[] {
            "M", "N"
        });
        rhoComboBox.setFont(Utilities.COMBO_BOX_FONT);
        rhoComboBox.setForeground(Color.black);
        rhoComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            rhoComboBox.setPrototypeDisplayValue("--------");
        JPanel headingPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(headingPanel, thetaLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(headingPanel, thetaSpinner, 1, 0, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(headingPanel, rhoLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(headingPanel, rhoSpinner, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(headingPanel, rhoComboBox, 2, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        trueCB = new JCheckBox("True North Course", true);
        trueCB.setFont(Utilities.LABEL_FONT);
        trueCB.setForeground(Color.black);
        trueCB.addActionListener(this);
        trueSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        trueSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(trueSpinner, "0.000"));
        magneticCB = new JCheckBox("Magnetic North Course", false);
        magneticCB.setFont(Utilities.LABEL_FONT);
        magneticCB.setForeground(Color.black);
        magneticCB.addActionListener(this);
        magneticSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        magneticSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(magneticSpinner, "0.000"));
        magneticSpinner.setEnabled(false);
        ButtonGroup magneticBG = new ButtonGroup();
        magneticBG.add(trueCB);
        magneticBG.add(magneticCB);
        distanceCB = new JCheckBox("Distance", true);
        distanceCB.setFont(Utilities.LABEL_FONT);
        distanceCB.setForeground(Color.black);
        distanceCB.addActionListener(this);
        distanceSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        distanceSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(distanceSpinner, "0.0"));
        distanceComboBox = new JComboBox(new String[] {
            "M", "N"
        });
        distanceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        distanceComboBox.setForeground(Color.black);
        distanceComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            distanceComboBox.setPrototypeDisplayValue("--------");
        timeCB = new JCheckBox("Time In Minutes", false);
        timeCB.setFont(Utilities.LABEL_FONT);
        timeCB.setForeground(Color.black);
        timeCB.addActionListener(this);
        timeSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100D, 1.0D));
        timeSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(timeSpinner, "0.0"));
        timeSpinner.setEnabled(false);
        ButtonGroup distanceBG = new ButtonGroup();
        distanceBG.add(distanceCB);
        distanceBG.add(timeCB);
        descriptorLabel = new JLabel("Altitude Descriptor");
        descriptorLabel.setFont(Utilities.LABEL_FONT);
        descriptorLabel.setForeground(Color.black);
        DefaultComboBoxModel descriptorModel = new DefaultComboBoxModel();
        descriptorModel.addElement("+");
        descriptorModel.addElement("-");
        descriptorModel.addElement(" ");
        descriptorModel.addElement("A");
        descriptorModel.addElement("B");
        descriptorModel.addElement("C");
        descriptorModel.addElement("G");
        descriptorModel.addElement("H");
        descriptorModel.addElement("I");
        descriptorModel.addElement("J");
        descriptorModel.addElement("V");
        descriptorComboBox = new JComboBox(descriptorModel);
        descriptorComboBox.setFont(Utilities.COMBO_BOX_FONT);
        descriptorComboBox.setForeground(Color.black);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            descriptorComboBox.setPrototypeDisplayValue("--------");
        alt1Label = new JLabel("Altitude 1");
        alt1Label.setFont(Utilities.LABEL_FONT);
        alt1Label.setForeground(Color.black);
        alt1Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        alt1Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(alt1Spinner, "0.0"));
        alt1ComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        alt1ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        alt1ComboBox.setForeground(Color.black);
        alt1ComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            alt1ComboBox.setPrototypeDisplayValue("--------");
        alt2Label = new JLabel("Altitude 2");
        alt2Label.setFont(Utilities.LABEL_FONT);
        alt2Label.setForeground(Color.black);
        alt2Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        alt2Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(alt2Spinner, "0.0"));
        alt2ComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        alt2ComboBox.setFont(Utilities.COMBO_BOX_FONT);
        alt2ComboBox.setForeground(Color.black);
        alt2ComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            alt2ComboBox.setPrototypeDisplayValue("--------");
        JPanel altPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(altPanel, descriptorLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, descriptorComboBox, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt1Label, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt1Spinner, 3, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt1ComboBox, 4, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt2Label, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt2Spinner, 3, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(altPanel, alt2ComboBox, 4, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        requiredLabel = new JLabel("required attribute");
        requiredLabel.setFont(Utilities.LABEL_FONT);
        requiredLabel.setForeground(Color.red);
        optionalLabel = new JLabel("optional attribute");
        optionalLabel.setFont(Utilities.LABEL_FONT);
        optionalLabel.setForeground(Color.blue);
        addButton = new JButton("Add Leg");
        addButton.setFont(Utilities.BUTTON_FONT);
        addButton.setForeground(Color.black);
        addButton.addActionListener(actionListener);
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(bottomPanel, requiredLabel, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bottomPanel, optionalLabel, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(bottomPanel, Box.createGlue(), 2, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(bottomPanel, addButton, 3, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        TitledBorder legBorder = new TitledBorder(" Leg Information ");
        legBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        legBorder.setTitleColor(Color.black);
        setBorder(new CompoundBorder(legBorder, new EmptyBorder(5, 5, 5, 5)));
        setLayout(new GridBagLayout());
        Utilities.addComponent(this, legTypePanel, 0, 0, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, fixPanel, 0, 1, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, recPanel, 0, 7, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, headingPanel, 0, 9, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, trueCB, 0, 11, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, trueSpinner, 1, 11, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, magneticCB, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, magneticSpinner, 1, 12, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, distanceCB, 0, 13, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, distanceSpinner, 1, 13, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, distanceComboBox, 2, 13, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, timeCB, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, timeSpinner, 1, 14, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, altPanel, 0, 15, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, new JSeparator(), 0, 16, 3, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, bottomPanel, 0, 17, 3, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        createLegTypes();
    }

    public JButton getAddButton()
    {
        return addButton;
    }

    private void createLegTypes()
    {
        legTypeHM = new HashMap();
        legAL = new ArrayList();
        legAL.add(legFixTypeLabel);
        legAL.add(legFixRegionLabel);
        legAL.add(legFixIdentLabel);
        legAL.add(legFlyOverLabel);
        legAL.add(legTurnLabel);
        legAL.add(legRecTypeLabel);
        legAL.add(legRecRegionLabel);
        legAL.add(legRecIdentLabel);
        legAL.add(thetaLabel);
        legAL.add(rhoLabel);
        legAL.add(trueCB);
        legAL.add(magneticCB);
        legAL.add(distanceCB);
        legAL.add(timeCB);
        legAL.add(descriptorLabel);
        legAL.add(alt1Label);
        legAL.add(alt2Label);
        ArrayList requiredAL = new ArrayList();
        ArrayList optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legTurnLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("AF-required", requiredAL);
        legTypeHM.put("AF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(descriptorLabel);
        requiredAL.add(alt1Label);
        optionalAL.add(legTurnLabel);
        legTypeHM.put("CA-required", requiredAL);
        legTypeHM.put("CA-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("CD-required", requiredAL);
        legTypeHM.put("CD-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legFlyOverLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("CF-required", requiredAL);
        legTypeHM.put("CF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("CI-required", requiredAL);
        legTypeHM.put("CI-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("CR-required", requiredAL);
        legTypeHM.put("CR-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("DF-required", requiredAL);
        legTypeHM.put("DF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        legTypeHM.put("FA-required", requiredAL);
        legTypeHM.put("FA-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legFlyOverLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("FC-required", requiredAL);
        legTypeHM.put("FC-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("FD-required", requiredAL);
        legTypeHM.put("FD-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        legTypeHM.put("FM-required", requiredAL);
        legTypeHM.put("FM-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        requiredAL.add(timeCB);
        requiredAL.add(descriptorLabel);
        requiredAL.add(alt1Label);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        legTypeHM.put("HA-required", requiredAL);
        legTypeHM.put("HA-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        requiredAL.add(timeCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        legTypeHM.put("HF-required", requiredAL);
        legTypeHM.put("HF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        requiredAL.add(timeCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        legTypeHM.put("HM-required", requiredAL);
        legTypeHM.put("HM-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("IF-required", requiredAL);
        legTypeHM.put("IF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legTurnLabel);
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(thetaLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        requiredAL.add(timeCB);
        requiredAL.add(descriptorLabel);
        requiredAL.add(alt1Label);
        legTypeHM.put("PI-required", requiredAL);
        legTypeHM.put("PI-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(legTurnLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        requiredAL.add(distanceCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("RF-required", requiredAL);
        legTypeHM.put("RF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legFixTypeLabel);
        requiredAL.add(legFixRegionLabel);
        requiredAL.add(legFixIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(thetaLabel);
        optionalAL.add(rhoLabel);
        optionalAL.add(distanceCB);
        optionalAL.add(timeCB);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("TF-required", requiredAL);
        legTypeHM.put("TF-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(descriptorLabel);
        requiredAL.add(alt1Label);
        optionalAL.add(legTurnLabel);
        optionalAL.add(trueCB);
        optionalAL.add(magneticCB);
        legTypeHM.put("VA-required", requiredAL);
        legTypeHM.put("VA-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legTurnLabel);
        optionalAL.add(distanceCB);
        optionalAL.add(timeCB);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("VD-required", requiredAL);
        legTypeHM.put("VD-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(legRecTypeLabel);
        optionalAL.add(legRecRegionLabel);
        optionalAL.add(legRecIdentLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("VI-required", requiredAL);
        legTypeHM.put("VI-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legFixTypeLabel);
        optionalAL.add(legFixRegionLabel);
        optionalAL.add(legFixIdentLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        legTypeHM.put("VM-required", requiredAL);
        legTypeHM.put("VM-optional", optionalAL);
        requiredAL = new ArrayList();
        optionalAL = new ArrayList();
        requiredAL.add(legRecTypeLabel);
        requiredAL.add(legRecRegionLabel);
        requiredAL.add(legRecIdentLabel);
        requiredAL.add(rhoLabel);
        requiredAL.add(trueCB);
        requiredAL.add(magneticCB);
        optionalAL.add(legFlyOverLabel);
        optionalAL.add(legTurnLabel);
        optionalAL.add(descriptorLabel);
        optionalAL.add(alt1Label);
        optionalAL.add(alt2Label);
        legTypeHM.put("VR-required", requiredAL);
        legTypeHM.put("VR-optional", optionalAL);
    }

    private DefaultComboBoxModel getTurnModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("name", "Left");
        hashMap.put("index", "L");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("name", "Right");
        hashMap.put("index", "R");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("name", "Either");
        hashMap.put("index", "E");
        model.addElement(hashMap);
        return model;
    }

    public void setLegEnabled(boolean status)
    {
        legTypeLabel.setEnabled(status);
        legTypeComboBox.setEnabled(status);
        legTurnLabel.setEnabled(status);
        legTurnComboBox.setEnabled(status);
        legFlyOverLabel.setEnabled(status);
        legFlyOverComboBox.setEnabled(status);
        legFixTypeLabel.setEnabled(status);
        legFixTypeComboBox.setEnabled(status);
        legFixRegionLabel.setEnabled(status);
        legFixRegionTF.setEnabled(status);
        legFixIdentLabel.setEnabled(status);
        legFixIdentTF.setEnabled(status);
        legRecTypeLabel.setEnabled(status);
        legRecTypeComboBox.setEnabled(status);
        legRecRegionLabel.setEnabled(status);
        legRecRegionTF.setEnabled(status);
        legRecIdentLabel.setEnabled(status);
        legRecIdentTF.setEnabled(status);
        thetaLabel.setEnabled(status);
        thetaSpinner.setEnabled(status);
        rhoLabel.setEnabled(status);
        rhoSpinner.setEnabled(status);
        rhoComboBox.setEnabled(status);
        trueCB.setEnabled(status);
        trueSpinner.setEnabled(status);
        magneticCB.setEnabled(status);
        magneticSpinner.setEnabled(status);
        distanceCB.setEnabled(status);
        distanceSpinner.setEnabled(status);
        distanceComboBox.setEnabled(status);
        timeCB.setEnabled(status);
        timeSpinner.setEnabled(status);
        descriptorLabel.setEnabled(status);
        descriptorComboBox.setEnabled(status);
        alt1Label.setEnabled(status);
        alt1Spinner.setEnabled(status);
        alt1ComboBox.setEnabled(status);
        alt2Label.setEnabled(status);
        alt2Spinner.setEnabled(status);
        alt2ComboBox.setEnabled(status);
        requiredLabel.setEnabled(status);
        optionalLabel.setEnabled(status);
        addButton.setEnabled(status);
        if(status)
        {
            setCourseEnabled();
            setDistanceEnabled();
        }
    }

    private void setCourseEnabled()
    {
        boolean status = trueCB.isSelected();
        trueSpinner.setEnabled(status);
        magneticSpinner.setEnabled(!status);
    }

    private void setDistanceEnabled()
    {
        boolean status = distanceCB.isSelected();
        distanceSpinner.setEnabled(status);
        distanceComboBox.setEnabled(status);
        timeSpinner.setEnabled(!status);
    }

    private void updateLegDisplayColors()
    {
        String type = (String)legTypeComboBox.getSelectedItem();
        for(int i = legAL.size() - 1; i >= 0; i--)
            ((JComponent)legAL.get(i)).setForeground(Color.black);

        ArrayList requiredAL = (ArrayList)legTypeHM.get((new StringBuilder()).append(type).append("-required").toString());
        for(int i = requiredAL.size() - 1; i >= 0; i--)
            ((JComponent)requiredAL.get(i)).setForeground(Color.red);

        ArrayList optionalAL = (ArrayList)legTypeHM.get((new StringBuilder()).append(type).append("-optional").toString());
        for(int i = optionalAL.size() - 1; i >= 0; i--)
            ((JComponent)optionalAL.get(i)).setForeground(Color.blue);

    }

    public void displayLeg(LegModel legModel)
    {
        this.legModel = legModel;
        rhoComboBox.removeActionListener(this);
        distanceComboBox.removeActionListener(this);
        alt1ComboBox.removeActionListener(this);
        alt2ComboBox.removeActionListener(this);
        setLegEnabled(true);
        legTypeComboBox.setSelectedItem(legModel.getType());
        legFlyOverComboBox.setSelectedIndex(legModel.getFlyOver() ? 0 : 1);
        if(legModel.getFixType().length() == 0)
            legFixTypeComboBox.setSelectedIndex(0);
        else
            legFixTypeComboBox.setSelectedItem(legModel.getFixType());
        legFixRegionTF.setText(legModel.getFixRegion());
        legFixIdentTF.setText(legModel.getFixIdent());
        if(legModel.getRecommendedType().length() == 0)
            legRecTypeComboBox.setSelectedIndex(0);
        else
            legRecTypeComboBox.setSelectedItem(legModel.getRecommendedType());
        legRecRegionTF.setText(legModel.getRecommendedRegion());
        legRecIdentTF.setText(legModel.getRecommendedIdent());
        thetaSpinner.setValue(new Double(legModel.getTheta()));
        rhoSpinner.setValue(new Double(legModel.getRho()));
        rhoComboBox.setSelectedItem(legModel.getRhoMeasure());
        trueCB.setSelected(legModel.getTrueCourse() >= 0.0F);
        trueSpinner.setValue(new Double(Math.max(0.0F, legModel.getTrueCourse())));
        magneticCB.setSelected(legModel.getMagneticCourse() >= 0.0F);
        magneticSpinner.setValue(new Double(Math.max(0.0F, legModel.getMagneticCourse())));
        distanceCB.setSelected(legModel.getDistance() != 0.0F);
        distanceSpinner.setValue(new Double(legModel.getDistance()));
        distanceComboBox.setSelectedItem(legModel.getDistanceMeasure());
        timeCB.setSelected(legModel.getTime() != 0.0F);
        timeSpinner.setValue(new Double(legModel.getTime()));
        descriptorComboBox.setSelectedItem(legModel.getAltitudeDescriptor());
        alt1Spinner.setValue(new Double(legModel.getAltitude1()));
        alt2Spinner.setValue(new Double(legModel.getAltitude2()));
        alt1ComboBox.setSelectedItem(legModel.getAltitude1Measure());
        alt2ComboBox.setSelectedItem(legModel.getAltitude2Measure());
        Utilities.setCodeDescComboBox(legTurnComboBox, legModel.getTurnDirection(), "");
        origRhoMeasure = legModel.getRhoMeasure();
        origDistMeasure = legModel.getDistanceMeasure();
        origAlt1Measure = legModel.getAltitude1Measure();
        origAlt2Measure = legModel.getAltitude2Measure();
        rhoComboBox.addActionListener(this);
        distanceComboBox.addActionListener(this);
        alt1ComboBox.addActionListener(this);
        alt2ComboBox.addActionListener(this);
        addButton.setText("Update Leg");
    }

    public void resetLeg()
    {
        rhoComboBox.removeActionListener(this);
        distanceComboBox.removeActionListener(this);
        alt1ComboBox.removeActionListener(this);
        alt2ComboBox.removeActionListener(this);
        legTypeComboBox.setSelectedIndex(0);
        legTurnComboBox.setSelectedIndex(0);
        legFlyOverComboBox.setSelectedIndex(0);
        legFixTypeComboBox.setSelectedIndex(0);
        legFixRegionTF.setText("");
        legFixIdentTF.setText("");
        legRecTypeComboBox.setSelectedIndex(0);
        legRecRegionTF.setText("");
        legRecIdentTF.setText("");
        thetaSpinner.setValue(new Double(0.0D));
        rhoSpinner.setValue(new Double(0.0D));
        rhoComboBox.setSelectedIndex(0);
        trueCB.setSelected(true);
        trueSpinner.setValue(new Double(0.0D));
        magneticCB.setSelected(false);
        magneticSpinner.setValue(new Double(0.0D));
        distanceCB.setSelected(true);
        distanceSpinner.setValue(new Double(0.0D));
        distanceComboBox.setSelectedIndex(0);
        timeCB.setSelected(false);
        timeSpinner.setValue(new Double(0.0D));
        descriptorComboBox.setSelectedIndex(0);
        alt1Spinner.setValue(new Double(0.0D));
        alt2Spinner.setValue(new Double(0.0D));
        alt1ComboBox.setSelectedIndex(0);
        alt2ComboBox.setSelectedIndex(0);
        rhoComboBox.addActionListener(this);
        distanceComboBox.addActionListener(this);
        alt1ComboBox.addActionListener(this);
        alt2ComboBox.addActionListener(this);
        legModel = new LegModel();
    }

    public LegModel updateAddLeg()
    {
        legModel.setType((String)legTypeComboBox.getSelectedItem());
        legModel.setTurnDirection((String)((HashMap)legTurnComboBox.getSelectedItem()).get("index"));
        legModel.setFlyOver(legFlyOverComboBox.getSelectedIndex() == 0);
        if(legFixTypeComboBox.getSelectedIndex() == 0)
            legModel.setFixType("");
        else
            legModel.setFixType((String)legFixTypeComboBox.getSelectedItem());
        legModel.setFixRegion(legFixRegionTF.getText());
        legModel.setFixIdent(legFixIdentTF.getText());
        if(legRecTypeComboBox.getSelectedIndex() == 0)
            legModel.setRecommendedType("");
        else
            legModel.setRecommendedType((String)legRecTypeComboBox.getSelectedItem());
        legModel.setRecommendedRegion(legRecRegionTF.getText());
        legModel.setRecommendedIdent(legRecIdentTF.getText());
        legModel.setTheta(((Double)thetaSpinner.getValue()).floatValue());
        legModel.setRho(((Double)rhoSpinner.getValue()).floatValue());
        legModel.setRhoMeasure((String)rhoComboBox.getSelectedItem());
        legModel.setAltitudeDescriptor((String)descriptorComboBox.getSelectedItem());
        legModel.setAltitude1(((Double)alt1Spinner.getValue()).floatValue());
        legModel.setAltitude2(((Double)alt2Spinner.getValue()).floatValue());
        legModel.setAltitude1Measure((String)alt1ComboBox.getSelectedItem());
        legModel.setAltitude2Measure((String)alt2ComboBox.getSelectedItem());
        if(trueCB.isSelected())
            legModel.setTrueCourse(((Double)trueSpinner.getValue()).floatValue());
        else
            legModel.setTrueCourse(-1F);
        if(magneticCB.isSelected())
            legModel.setMagneticCourse(((Double)magneticSpinner.getValue()).floatValue());
        else
            legModel.setMagneticCourse(-1F);
        if(distanceCB.isSelected())
        {
            legModel.setDistance(((Double)distanceSpinner.getValue()).floatValue());
            legModel.setDistanceMeasure((String)distanceComboBox.getSelectedItem());
        } else
        {
            legModel.setDistance(0.0F);
            legModel.setDistanceMeasure("M");
        }
        if(timeCB.isSelected())
            legModel.setTime(((Double)timeSpinner.getValue()).floatValue());
        else
            legModel.setTime(0.0F);
        return legModel;
    }

    private void updateDistanceMeasure(JSpinner spinner, JComboBox combobox)
    {
        if(combobox == rhoComboBox && ((String)combobox.getSelectedItem()).equals(origRhoMeasure))
            return;
        if(combobox == distanceComboBox && ((String)combobox.getSelectedItem()).equals(origDistMeasure))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            float distance = ((Double)spinner.getValue()).floatValue();
            if(combobox.getSelectedItem().equals("M"))
                distance *= 1852F;
            else
                distance /= 1852F;
            spinner.setValue(new Double(distance));
            if(combobox == rhoComboBox)
                origRhoMeasure = (String)combobox.getSelectedItem();
            else
            if(combobox == distanceComboBox)
                origDistMeasure = (String)combobox.getSelectedItem();
        }
    }

    private void updateAltMeasure(JSpinner spinner, JComboBox combobox)
    {
        if(combobox == alt1ComboBox && ((String)combobox.getSelectedItem()).equals(origAlt1Measure))
            return;
        if(combobox == alt2ComboBox && ((String)combobox.getSelectedItem()).equals(origAlt2Measure))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            float altitude = ((Double)spinner.getValue()).floatValue();
            if(combobox.getSelectedItem().equals("M"))
                altitude /= 3.28F;
            else
                altitude *= 3.28F;
            spinner.setValue(new Double(altitude));
            if(combobox == alt1ComboBox)
                origAlt1Measure = (String)combobox.getSelectedItem();
            else
            if(combobox == alt2ComboBox)
                origAlt2Measure = (String)combobox.getSelectedItem();
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == trueCB)
            setCourseEnabled();
        else
        if(event.getSource() == magneticCB)
            setCourseEnabled();
        else
        if(event.getSource() == distanceCB)
            setDistanceEnabled();
        else
        if(event.getSource() == timeCB)
            setDistanceEnabled();
        else
        if(event.getSource() == legTypeComboBox)
            updateLegDisplayColors();
        else
        if(event.getSource() == rhoComboBox)
            updateDistanceMeasure(rhoSpinner, rhoComboBox);
        else
        if(event.getSource() == distanceComboBox)
            updateDistanceMeasure(distanceSpinner, distanceComboBox);
        else
        if(event.getSource() == alt1ComboBox)
            updateAltMeasure(alt1Spinner, alt1ComboBox);
        else
        if(event.getSource() == alt2ComboBox)
            updateAltMeasure(alt2Spinner, alt2ComboBox);
    }

    private LegModel legModel;
    private JLabel legTypeLabel;
    private JComboBox legTypeComboBox;
    private JLabel legFixTypeLabel;
    private JComboBox legFixTypeComboBox;
    private JLabel legFixRegionLabel;
    private PopupTextField legFixRegionTF;
    private JLabel legFixIdentLabel;
    private PopupTextField legFixIdentTF;
    private JLabel legFlyOverLabel;
    private JComboBox legFlyOverComboBox;
    private JLabel legTurnLabel;
    private JComboBox legTurnComboBox;
    private JLabel legRecTypeLabel;
    private JComboBox legRecTypeComboBox;
    private JLabel legRecRegionLabel;
    private PopupTextField legRecRegionTF;
    private JLabel legRecIdentLabel;
    private PopupTextField legRecIdentTF;
    private JLabel thetaLabel;
    private JSpinner thetaSpinner;
    private JLabel rhoLabel;
    private JSpinner rhoSpinner;
    private JComboBox rhoComboBox;
    private JCheckBox trueCB;
    private JSpinner trueSpinner;
    private JCheckBox magneticCB;
    private JSpinner magneticSpinner;
    private JCheckBox distanceCB;
    private JSpinner distanceSpinner;
    private JComboBox distanceComboBox;
    private JCheckBox timeCB;
    private JSpinner timeSpinner;
    private JLabel descriptorLabel;
    private JComboBox descriptorComboBox;
    private JLabel alt1Label;
    private JSpinner alt1Spinner;
    private JComboBox alt1ComboBox;
    private JLabel alt2Label;
    private JSpinner alt2Spinner;
    private JComboBox alt2ComboBox;
    private JLabel requiredLabel;
    private JLabel optionalLabel;
    private HashMap legTypeHM;
    private ArrayList legAL;
    private JButton addButton;
    private String origRhoMeasure;
    private String origDistMeasure;
    private String origAlt1Measure;
    private String origAlt2Measure;
}
