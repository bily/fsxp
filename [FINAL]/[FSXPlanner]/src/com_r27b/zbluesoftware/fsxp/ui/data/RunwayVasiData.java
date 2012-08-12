// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayVasiData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.VasiModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RunwayVasiData extends JPanel
    implements ActionListener, ChangeListener, PropertyChangeListener
{

    public RunwayVasiData()
    {
        listeners = new Vector();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        primaryLeftVasiCB = new JCheckBox("Primary VASI Left");
        primaryLeftVasiCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryLeftVasiCB.setForeground(Color.black);
        primaryLeftVasiCB.addActionListener(this);
        plTypeLabel = new JLabel("Type");
        plTypeLabel.setFont(Utilities.LABEL_FONT);
        plTypeLabel.setForeground(Color.black);
        plTypeLabel.setEnabled(false);
        plTypeComboBox = new JComboBox(getTypeModel());
        plTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        plTypeComboBox.setForeground(Color.black);
        plTypeComboBox.setEnabled(false);
        plTypeComboBox.addActionListener(this);
        plBiasXLabel = new JLabel("Bias X");
        plBiasXLabel.setFont(Utilities.LABEL_FONT);
        plBiasXLabel.setForeground(Color.black);
        plBiasXLabel.setEnabled(false);
        plBiasXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        plBiasXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(plBiasXSpinner, "0.000"));
        plBiasXSpinner.addChangeListener(this);
        plBiasXSpinner.setEnabled(false);
        plBiasXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        plBiasXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        plBiasXComboBox.setForeground(Color.black);
        plBiasXComboBox.addActionListener(this);
        plBiasXComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            plBiasXComboBox.setPrototypeDisplayValue("--------");
        plBiasZLabel = new JLabel("Bias Z");
        plBiasZLabel.setFont(Utilities.LABEL_FONT);
        plBiasZLabel.setForeground(Color.black);
        plBiasZLabel.setEnabled(false);
        plBiasZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        plBiasZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(plBiasZSpinner, "0.000"));
        plBiasZSpinner.addChangeListener(this);
        plBiasZSpinner.setEnabled(false);
        plBiasZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        plBiasZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        plBiasZComboBox.setForeground(Color.black);
        plBiasZComboBox.addActionListener(this);
        plBiasZComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            plBiasZComboBox.setPrototypeDisplayValue("--------");
        plSpacingLabel = new JLabel("Spacing");
        plSpacingLabel.setFont(Utilities.LABEL_FONT);
        plSpacingLabel.setForeground(Color.black);
        plSpacingLabel.setEnabled(false);
        plSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        plSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(plSpacingSpinner, "0.000"));
        plSpacingSpinner.addChangeListener(this);
        plSpacingSpinner.setEnabled(false);
        plSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        plSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        plSpacingComboBox.setForeground(Color.black);
        plSpacingComboBox.addActionListener(this);
        plSpacingComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            plSpacingComboBox.setPrototypeDisplayValue("--------");
        plAngleLabel = new JLabel("Approach Angle");
        plAngleLabel.setFont(Utilities.LABEL_FONT);
        plAngleLabel.setForeground(Color.black);
        plAngleLabel.setEnabled(false);
        plAngleSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10D, 0.10000000000000001D));
        plAngleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(plAngleSpinner, "0.000"));
        plAngleSpinner.addChangeListener(this);
        plAngleSpinner.setEnabled(false);
        primaryRightVasiCB = new JCheckBox("Primary VASI Right");
        primaryRightVasiCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryRightVasiCB.setForeground(Color.black);
        primaryRightVasiCB.addActionListener(this);
        prTypeLabel = new JLabel("Type");
        prTypeLabel.setFont(Utilities.LABEL_FONT);
        prTypeLabel.setForeground(Color.black);
        prTypeLabel.setEnabled(false);
        prTypeComboBox = new JComboBox(getTypeModel());
        prTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        prTypeComboBox.setForeground(Color.black);
        prTypeComboBox.setEnabled(false);
        prTypeComboBox.addActionListener(this);
        prBiasXLabel = new JLabel("Bias X");
        prBiasXLabel.setFont(Utilities.LABEL_FONT);
        prBiasXLabel.setForeground(Color.black);
        prBiasXLabel.setEnabled(false);
        prBiasXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        prBiasXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(prBiasXSpinner, "0.000"));
        prBiasXSpinner.addChangeListener(this);
        prBiasXSpinner.setEnabled(false);
        prBiasXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        prBiasXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        prBiasXComboBox.setForeground(Color.black);
        prBiasXComboBox.addActionListener(this);
        prBiasXComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            prBiasXComboBox.setPrototypeDisplayValue("--------");
        prBiasZLabel = new JLabel("Bias Z");
        prBiasZLabel.setFont(Utilities.LABEL_FONT);
        prBiasZLabel.setForeground(Color.black);
        prBiasZLabel.setEnabled(false);
        prBiasZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        prBiasZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(prBiasZSpinner, "0.000"));
        prBiasZSpinner.addChangeListener(this);
        prBiasZSpinner.setEnabled(false);
        prBiasZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        prBiasZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        prBiasZComboBox.setForeground(Color.black);
        prBiasZComboBox.addActionListener(this);
        prBiasZComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            prBiasZComboBox.setPrototypeDisplayValue("--------");
        prSpacingLabel = new JLabel("Spacing");
        prSpacingLabel.setFont(Utilities.LABEL_FONT);
        prSpacingLabel.setForeground(Color.black);
        prSpacingLabel.setEnabled(false);
        prSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        prSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(prSpacingSpinner, "0.000"));
        prSpacingSpinner.addChangeListener(this);
        prSpacingSpinner.setEnabled(false);
        prSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        prSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        prSpacingComboBox.setForeground(Color.black);
        prSpacingComboBox.addActionListener(this);
        prSpacingComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            prSpacingComboBox.setPrototypeDisplayValue("--------");
        prAngleLabel = new JLabel("Approach Angle");
        prAngleLabel.setFont(Utilities.LABEL_FONT);
        prAngleLabel.setForeground(Color.black);
        prAngleLabel.setEnabled(false);
        prAngleSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10D, 0.10000000000000001D));
        prAngleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(prAngleSpinner, "0.0"));
        prAngleSpinner.addChangeListener(this);
        prAngleSpinner.setEnabled(false);
        secondaryLeftVasiCB = new JCheckBox("Secondary VASI Left");
        secondaryLeftVasiCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryLeftVasiCB.setForeground(Color.black);
        secondaryLeftVasiCB.addActionListener(this);
        slTypeLabel = new JLabel("Type");
        slTypeLabel.setFont(Utilities.LABEL_FONT);
        slTypeLabel.setForeground(Color.black);
        slTypeLabel.setEnabled(false);
        slTypeComboBox = new JComboBox(getTypeModel());
        slTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        slTypeComboBox.setForeground(Color.black);
        slTypeComboBox.setEnabled(false);
        slTypeComboBox.addActionListener(this);
        slBiasXLabel = new JLabel("Bias X");
        slBiasXLabel.setFont(Utilities.LABEL_FONT);
        slBiasXLabel.setForeground(Color.black);
        slBiasXLabel.setEnabled(false);
        slBiasXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        slBiasXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(slBiasXSpinner, "0.000"));
        slBiasXSpinner.addChangeListener(this);
        slBiasXSpinner.setEnabled(false);
        slBiasXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        slBiasXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        slBiasXComboBox.setForeground(Color.black);
        slBiasXComboBox.addActionListener(this);
        slBiasXComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            slBiasXComboBox.setPrototypeDisplayValue("--------");
        slBiasZLabel = new JLabel("Bias Z");
        slBiasZLabel.setFont(Utilities.LABEL_FONT);
        slBiasZLabel.setForeground(Color.black);
        slBiasZLabel.setEnabled(false);
        slBiasZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        slBiasZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(slBiasZSpinner, "0.000"));
        slBiasZSpinner.addChangeListener(this);
        slBiasZSpinner.setEnabled(false);
        slBiasZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        slBiasZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        slBiasZComboBox.setForeground(Color.black);
        slBiasZComboBox.addActionListener(this);
        slBiasZComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            slBiasZComboBox.setPrototypeDisplayValue("--------");
        slSpacingLabel = new JLabel("Spacing");
        slSpacingLabel.setFont(Utilities.LABEL_FONT);
        slSpacingLabel.setForeground(Color.black);
        slSpacingLabel.setEnabled(false);
        slSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        slSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(slSpacingSpinner, "0.000"));
        slSpacingSpinner.addChangeListener(this);
        slSpacingSpinner.setEnabled(false);
        slSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        slSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        slSpacingComboBox.setForeground(Color.black);
        slSpacingComboBox.addActionListener(this);
        slSpacingComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            slSpacingComboBox.setPrototypeDisplayValue("--------");
        slAngleLabel = new JLabel("Approach Angle");
        slAngleLabel.setFont(Utilities.LABEL_FONT);
        slAngleLabel.setForeground(Color.black);
        slAngleLabel.setEnabled(false);
        slAngleSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10D, 0.10000000000000001D));
        slAngleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(slAngleSpinner, "0.0"));
        slAngleSpinner.addChangeListener(this);
        slAngleSpinner.setEnabled(false);
        secondaryRightVasiCB = new JCheckBox("Secondary VASI Right");
        secondaryRightVasiCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryRightVasiCB.setForeground(Color.black);
        secondaryRightVasiCB.addActionListener(this);
        srTypeLabel = new JLabel("Type");
        srTypeLabel.setFont(Utilities.LABEL_FONT);
        srTypeLabel.setForeground(Color.black);
        srTypeLabel.setEnabled(false);
        srTypeComboBox = new JComboBox(getTypeModel());
        srTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        srTypeComboBox.setForeground(Color.black);
        srTypeComboBox.setEnabled(false);
        srTypeComboBox.addActionListener(this);
        srBiasXLabel = new JLabel("Bias X");
        srBiasXLabel.setFont(Utilities.LABEL_FONT);
        srBiasXLabel.setForeground(Color.black);
        srBiasXLabel.setEnabled(false);
        srBiasXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        srBiasXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(srBiasXSpinner, "0.000"));
        srBiasXSpinner.addChangeListener(this);
        srBiasXSpinner.setEnabled(false);
        srBiasXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        srBiasXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        srBiasXComboBox.setForeground(Color.black);
        srBiasXComboBox.addActionListener(this);
        srBiasXComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            srBiasXComboBox.setPrototypeDisplayValue("--------");
        srBiasZLabel = new JLabel("Bias Z");
        srBiasZLabel.setFont(Utilities.LABEL_FONT);
        srBiasZLabel.setForeground(Color.black);
        srBiasZLabel.setEnabled(false);
        srBiasZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        srBiasZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(srBiasZSpinner, "0.000"));
        srBiasZSpinner.addChangeListener(this);
        srBiasZSpinner.setEnabled(false);
        srBiasZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        srBiasZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        srBiasZComboBox.setForeground(Color.black);
        srBiasZComboBox.addActionListener(this);
        srBiasZComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            srBiasZComboBox.setPrototypeDisplayValue("--------");
        srSpacingLabel = new JLabel("Spacing");
        srSpacingLabel.setFont(Utilities.LABEL_FONT);
        srSpacingLabel.setForeground(Color.black);
        srSpacingLabel.setEnabled(false);
        srSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        srSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(srSpacingSpinner, "0.000"));
        srSpacingSpinner.addChangeListener(this);
        srSpacingSpinner.setEnabled(false);
        srSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        srSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        srSpacingComboBox.setForeground(Color.black);
        srSpacingComboBox.addActionListener(this);
        srSpacingComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            srSpacingComboBox.setPrototypeDisplayValue("--------");
        srAngleLabel = new JLabel("Approach Angle");
        srAngleLabel.setFont(Utilities.LABEL_FONT);
        srAngleLabel.setForeground(Color.black);
        srAngleLabel.setEnabled(false);
        srAngleSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10D, 0.10000000000000001D));
        srAngleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(srAngleSpinner, "0.0"));
        srAngleSpinner.addChangeListener(this);
        srAngleSpinner.setEnabled(false);
        Utilities.addComponent(this, primaryLeftVasiCB, 0, 0, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plTypeLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plTypeComboBox, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, plBiasXLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plBiasXSpinner, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, plBiasXComboBox, 2, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plBiasZLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plBiasZSpinner, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, plBiasZComboBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plSpacingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plSpacingSpinner, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, plSpacingComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plAngleLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, plAngleSpinner, 1, 5, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primaryRightVasiCB, 0, 6, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prTypeLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prTypeComboBox, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, prBiasXLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prBiasXSpinner, 1, 8, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, prBiasXComboBox, 2, 8, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prBiasZLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prBiasZSpinner, 1, 9, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, prBiasZComboBox, 2, 9, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prSpacingLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prSpacingSpinner, 1, 10, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, prSpacingComboBox, 2, 10, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prAngleLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, prAngleSpinner, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryLeftVasiCB, 0, 12, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slTypeLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slTypeComboBox, 1, 13, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, slBiasXLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slBiasXSpinner, 1, 14, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, slBiasXComboBox, 2, 14, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slBiasZLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slBiasZSpinner, 1, 15, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, slBiasZComboBox, 2, 15, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slSpacingLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slSpacingSpinner, 1, 16, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, slSpacingComboBox, 2, 16, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slAngleLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, slAngleSpinner, 1, 17, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryRightVasiCB, 0, 18, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srTypeLabel, 0, 19, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srTypeComboBox, 1, 19, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, srBiasXLabel, 0, 20, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srBiasXSpinner, 1, 20, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, srBiasXComboBox, 2, 20, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srBiasZLabel, 0, 21, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srBiasZSpinner, 1, 21, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, srBiasZComboBox, 2, 21, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srSpacingLabel, 0, 22, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srSpacingSpinner, 1, 22, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, srSpacingComboBox, 2, 22, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srAngleLabel, 0, 23, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, srAngleSpinner, 1, 23, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
    }

    private DefaultComboBoxModel getSystemModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("NONE");
        model.addElement("ALSF1");
        model.addElement("ALSF2");
        model.addElement("CALVERT");
        model.addElement("CALVERT2");
        model.addElement("MALS");
        model.addElement("MALSF");
        model.addElement("MALSR");
        model.addElement("ODALS");
        model.addElement("RAIL");
        model.addElement("SALS");
        model.addElement("SALSF");
        model.addElement("SSALF");
        model.addElement("SSALR");
        model.addElement("SSALS");
        return model;
    }

    private DefaultComboBoxModel getTypeModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("PAPI2");
        model.addElement("PAPI4");
        model.addElement("PVASI");
        model.addElement("TRICOLOR");
        model.addElement("TVASI");
        model.addElement("VASI21");
        model.addElement("VASI22");
        model.addElement("VASI23");
        model.addElement("VASI31");
        model.addElement("VASI32");
        model.addElement("VASI33");
        model.addElement("BALL");
        model.addElement("APAP");
        model.addElement("PANELS");
        return model;
    }

    public void updateDisplay(RunwayModel model)
    {
        updateModel();
        if(this.model != null)
        {
            if(this.model.getPrimaryLeftVasiModel() != null)
                this.model.getPrimaryLeftVasiModel().removePropertyChangeListener(this);
            if(this.model.getPrimaryRightVasiModel() != null)
                this.model.getPrimaryRightVasiModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryLeftVasiModel() != null)
                this.model.getSecondaryLeftVasiModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryRightVasiModel() != null)
                this.model.getSecondaryRightVasiModel().removePropertyChangeListener(this);
        }
        this.model = model;
        primaryLeftVasiCB.removeActionListener(this);
        plTypeComboBox.removeActionListener(this);
        plBiasXSpinner.removeChangeListener(this);
        plBiasXComboBox.removeActionListener(this);
        plBiasZSpinner.removeChangeListener(this);
        plBiasZComboBox.removeActionListener(this);
        plSpacingSpinner.removeChangeListener(this);
        plSpacingComboBox.removeActionListener(this);
        plAngleSpinner.removeChangeListener(this);
        primaryRightVasiCB.removeActionListener(this);
        prTypeComboBox.removeActionListener(this);
        prBiasXSpinner.removeChangeListener(this);
        prBiasXComboBox.removeActionListener(this);
        prBiasZSpinner.removeChangeListener(this);
        prBiasZComboBox.removeActionListener(this);
        prSpacingSpinner.removeChangeListener(this);
        prSpacingComboBox.removeActionListener(this);
        prAngleSpinner.removeChangeListener(this);
        secondaryLeftVasiCB.removeActionListener(this);
        slTypeComboBox.removeActionListener(this);
        slBiasXSpinner.removeChangeListener(this);
        slBiasXComboBox.removeActionListener(this);
        slBiasZSpinner.removeChangeListener(this);
        slBiasZComboBox.removeActionListener(this);
        slSpacingSpinner.removeChangeListener(this);
        slSpacingComboBox.removeActionListener(this);
        slAngleSpinner.removeChangeListener(this);
        secondaryRightVasiCB.removeActionListener(this);
        srTypeComboBox.removeActionListener(this);
        srBiasXSpinner.removeChangeListener(this);
        srBiasXComboBox.removeActionListener(this);
        srBiasZSpinner.removeChangeListener(this);
        srBiasZComboBox.removeActionListener(this);
        srSpacingSpinner.removeChangeListener(this);
        srSpacingComboBox.removeActionListener(this);
        srAngleSpinner.removeChangeListener(this);
        if(model != null)
        {
            if(model.getPrimaryLeftVasiModel() == null)
            {
                primaryLeftVasiCB.setSelected(false);
                plTypeComboBox.setSelectedIndex(0);
                plBiasXSpinner.setValue(new Double(0.0D));
                plBiasXComboBox.setSelectedItem("M");
                plBiasZSpinner.setValue(new Double(0.0D));
                plBiasZComboBox.setSelectedItem("M");
                plSpacingSpinner.setValue(new Double(2D));
                plSpacingComboBox.setSelectedItem("M");
                plAngleSpinner.setValue(new Double(3D));
                setPrimaryLeftVasiEnabled(false);
            } else
            {
                primaryLeftVasiCB.setSelected(true);
                plTypeComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getType());
                plBiasXSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getBiasX()));
                plBiasXComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getBiasXMeasure());
                plBiasZSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getBiasZ()));
                plBiasZComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getBiasZMeasure());
                plSpacingSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getSpacing()));
                plSpacingComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getSpacingMeasure());
                plAngleSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getPitch()));
                setPrimaryLeftVasiEnabled(true);
                model.getPrimaryLeftVasiModel().addPropertyChangeListener(this);
            }
            if(model.getPrimaryRightVasiModel() == null)
            {
                primaryRightVasiCB.setSelected(false);
                prTypeComboBox.setSelectedIndex(0);
                prBiasXSpinner.setValue(new Double(0.0D));
                prBiasXComboBox.setSelectedItem("M");
                prBiasZSpinner.setValue(new Double(0.0D));
                prBiasZComboBox.setSelectedItem("M");
                prSpacingSpinner.setValue(new Double(2D));
                prSpacingComboBox.setSelectedItem("M");
                prAngleSpinner.setValue(new Double(3D));
                setPrimaryRightVasiEnabled(false);
            } else
            {
                primaryRightVasiCB.setSelected(true);
                prTypeComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getType());
                prBiasXSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getBiasX()));
                prBiasXComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getBiasXMeasure());
                prBiasZSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getBiasZ()));
                prBiasZComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getBiasZMeasure());
                prSpacingSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getSpacing()));
                prSpacingComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getSpacingMeasure());
                prAngleSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getPitch()));
                setPrimaryRightVasiEnabled(true);
                model.getPrimaryRightVasiModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryLeftVasiModel() == null)
            {
                secondaryLeftVasiCB.setSelected(false);
                slTypeComboBox.setSelectedIndex(0);
                slBiasXSpinner.setValue(new Double(0.0D));
                slBiasXComboBox.setSelectedItem("M");
                slBiasZSpinner.setValue(new Double(0.0D));
                slBiasZComboBox.setSelectedItem("M");
                slSpacingSpinner.setValue(new Double(2D));
                slSpacingComboBox.setSelectedItem("M");
                slAngleSpinner.setValue(new Double(3D));
                setSecondaryLeftVasiEnabled(false);
            } else
            {
                secondaryLeftVasiCB.setSelected(true);
                slTypeComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getType());
                slBiasXSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getBiasX()));
                slBiasXComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getBiasXMeasure());
                slBiasZSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getBiasZ()));
                slBiasZComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getBiasZMeasure());
                slSpacingSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getSpacing()));
                slSpacingComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getSpacingMeasure());
                slAngleSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getPitch()));
                setSecondaryLeftVasiEnabled(true);
                model.getSecondaryLeftVasiModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryRightVasiModel() == null)
            {
                secondaryRightVasiCB.setSelected(false);
                srTypeComboBox.setSelectedIndex(0);
                srBiasXSpinner.setValue(new Double(0.0D));
                srBiasXComboBox.setSelectedItem("M");
                srBiasZSpinner.setValue(new Double(0.0D));
                srBiasZComboBox.setSelectedItem("M");
                srSpacingSpinner.setValue(new Double(2D));
                srSpacingComboBox.setSelectedItem("M");
                srAngleSpinner.setValue(new Double(3D));
                setSecondaryRightVasiEnabled(false);
            } else
            {
                secondaryRightVasiCB.setSelected(true);
                srTypeComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getType());
                srBiasXSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getBiasX()));
                srBiasXComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getBiasXMeasure());
                srBiasZSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getBiasZ()));
                srBiasZComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getBiasZMeasure());
                srSpacingSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getSpacing()));
                srSpacingComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getSpacingMeasure());
                srAngleSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getPitch()));
                setSecondaryRightVasiEnabled(true);
                model.getSecondaryRightVasiModel().addPropertyChangeListener(this);
            }
        } else
        {
            primaryLeftVasiCB.setSelected(false);
            plTypeComboBox.setSelectedIndex(0);
            plBiasXSpinner.setValue(new Double(0.0D));
            plBiasXComboBox.setSelectedItem("M");
            plBiasZSpinner.setValue(new Double(0.0D));
            plBiasZComboBox.setSelectedItem("M");
            plSpacingSpinner.setValue(new Double(2D));
            plSpacingComboBox.setSelectedItem("M");
            plAngleSpinner.setValue(new Double(3D));
            setPrimaryLeftVasiEnabled(false);
            primaryRightVasiCB.setSelected(false);
            prTypeComboBox.setSelectedIndex(0);
            prBiasXSpinner.setValue(new Double(0.0D));
            prBiasXComboBox.setSelectedItem("M");
            prBiasZSpinner.setValue(new Double(0.0D));
            prBiasZComboBox.setSelectedItem("M");
            prSpacingSpinner.setValue(new Double(2D));
            prSpacingComboBox.setSelectedItem("M");
            prAngleSpinner.setValue(new Double(3D));
            setPrimaryRightVasiEnabled(false);
            secondaryLeftVasiCB.setSelected(false);
            slTypeComboBox.setSelectedIndex(0);
            slBiasXSpinner.setValue(new Double(0.0D));
            slBiasXComboBox.setSelectedItem("M");
            slBiasZSpinner.setValue(new Double(0.0D));
            slBiasZComboBox.setSelectedItem("M");
            slSpacingSpinner.setValue(new Double(2D));
            slSpacingComboBox.setSelectedItem("M");
            slAngleSpinner.setValue(new Double(3D));
            setSecondaryLeftVasiEnabled(false);
            secondaryRightVasiCB.setSelected(false);
            srTypeComboBox.setSelectedIndex(0);
            srBiasXSpinner.setValue(new Double(0.0D));
            srBiasXComboBox.setSelectedItem("M");
            srBiasZSpinner.setValue(new Double(0.0D));
            srBiasZComboBox.setSelectedItem("M");
            srSpacingSpinner.setValue(new Double(2D));
            srSpacingComboBox.setSelectedItem("M");
            srAngleSpinner.setValue(new Double(3D));
            setSecondaryRightVasiEnabled(false);
        }
        primaryLeftVasiCB.addActionListener(this);
        plTypeComboBox.addActionListener(this);
        plBiasXSpinner.addChangeListener(this);
        plBiasXComboBox.addActionListener(this);
        plBiasZSpinner.addChangeListener(this);
        plBiasZComboBox.addActionListener(this);
        plSpacingSpinner.addChangeListener(this);
        plSpacingComboBox.addActionListener(this);
        plAngleSpinner.addChangeListener(this);
        primaryRightVasiCB.addActionListener(this);
        prTypeComboBox.addActionListener(this);
        prBiasXSpinner.addChangeListener(this);
        prBiasXComboBox.addActionListener(this);
        prBiasZSpinner.addChangeListener(this);
        prBiasZComboBox.addActionListener(this);
        prSpacingSpinner.addChangeListener(this);
        prSpacingComboBox.addActionListener(this);
        prAngleSpinner.addChangeListener(this);
        secondaryLeftVasiCB.addActionListener(this);
        slTypeComboBox.addActionListener(this);
        slBiasXSpinner.addChangeListener(this);
        slBiasXComboBox.addActionListener(this);
        slBiasZSpinner.addChangeListener(this);
        slBiasZComboBox.addActionListener(this);
        slSpacingSpinner.addChangeListener(this);
        slSpacingComboBox.addActionListener(this);
        slAngleSpinner.addChangeListener(this);
        secondaryRightVasiCB.addActionListener(this);
        srTypeComboBox.addActionListener(this);
        srBiasXSpinner.addChangeListener(this);
        srBiasXComboBox.addActionListener(this);
        srBiasZSpinner.addChangeListener(this);
        srBiasZComboBox.addActionListener(this);
        srSpacingSpinner.addChangeListener(this);
        srSpacingComboBox.addActionListener(this);
        srAngleSpinner.addChangeListener(this);
        boolean status = model != null;
        primaryLeftVasiCB.setEnabled(status);
        primaryRightVasiCB.setEnabled(status);
        secondaryLeftVasiCB.setEnabled(status);
        secondaryRightVasiCB.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            try
            {
                plBiasXSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                plBiasZSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                plSpacingSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                plAngleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                prBiasXSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                prBiasZSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                prSpacingSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                prAngleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                slBiasXSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                slBiasZSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                slSpacingSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                slAngleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                srBiasXSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                srBiasZSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                srSpacingSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                srAngleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
        }
    }

    private void setPrimaryLeftVasiEnabled(boolean status)
    {
        plTypeLabel.setEnabled(status);
        plTypeComboBox.setEnabled(status);
        plBiasXLabel.setEnabled(status);
        plBiasXSpinner.setEnabled(status);
        plBiasXComboBox.setEnabled(status);
        plBiasZLabel.setEnabled(status);
        plBiasZSpinner.setEnabled(status);
        plBiasZComboBox.setEnabled(status);
        plSpacingLabel.setEnabled(status);
        plSpacingSpinner.setEnabled(status);
        plSpacingComboBox.setEnabled(status);
        plAngleLabel.setEnabled(status);
        plAngleSpinner.setEnabled(status);
        if(!containsActionListener(primaryLeftVasiCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryLeftVasiModel() != null)
                model.getPrimaryLeftVasiModel().removePropertyChangeListener(this);
            VasiModel vasiModel = new VasiModel();
            vasiModel.addPropertyChangeListener(this);
            vasiModel.setEnd("PRIMARY");
            vasiModel.setSide("LEFT");
            if(SettingsEngine.getInstance().getVasiZEnd().equals("from end"))
                setBiasZ(vasiModel);
            model.setPrimaryLeftVasiModel(vasiModel);
            primaryLeftVasiCB.removeActionListener(this);
            plTypeComboBox.removeActionListener(this);
            plBiasXSpinner.removeChangeListener(this);
            plBiasXComboBox.removeActionListener(this);
            plBiasZSpinner.removeChangeListener(this);
            plBiasZComboBox.removeActionListener(this);
            plSpacingSpinner.removeChangeListener(this);
            plSpacingComboBox.removeActionListener(this);
            plAngleSpinner.removeChangeListener(this);
            plTypeComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getType());
            plBiasXSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getBiasX()));
            plBiasXComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getBiasXMeasure());
            plBiasZSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getBiasZ()));
            plBiasZComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getBiasZMeasure());
            plSpacingSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getSpacing()));
            plSpacingComboBox.setSelectedItem(model.getPrimaryLeftVasiModel().getSpacingMeasure());
            plAngleSpinner.setValue(new Double(model.getPrimaryLeftVasiModel().getPitch()));
            setPrimaryLeftVasiEnabled(true);
            primaryLeftVasiCB.addActionListener(this);
            plTypeComboBox.addActionListener(this);
            plBiasXSpinner.addChangeListener(this);
            plBiasXComboBox.addActionListener(this);
            plBiasZSpinner.addChangeListener(this);
            plBiasZComboBox.addActionListener(this);
            plSpacingSpinner.addChangeListener(this);
            plSpacingComboBox.addActionListener(this);
            plAngleSpinner.addChangeListener(this);
        } else
        {
            if(model.getPrimaryLeftVasiModel() != null)
                model.getPrimaryLeftVasiModel().removePropertyChangeListener(this);
            model.setPrimaryLeftVasiModel(null);
        }
        firePropertyChange("update-vasiprimaryleft", new Boolean(!status), new Boolean(status));
    }

    private void setPrimaryRightVasiEnabled(boolean status)
    {
        prTypeLabel.setEnabled(status);
        prTypeComboBox.setEnabled(status);
        prBiasXLabel.setEnabled(status);
        prBiasXSpinner.setEnabled(status);
        prBiasXComboBox.setEnabled(status);
        prBiasZLabel.setEnabled(status);
        prBiasZSpinner.setEnabled(status);
        prBiasZComboBox.setEnabled(status);
        prSpacingLabel.setEnabled(status);
        prSpacingSpinner.setEnabled(status);
        prSpacingComboBox.setEnabled(status);
        prAngleLabel.setEnabled(status);
        prAngleSpinner.setEnabled(status);
        if(!containsActionListener(primaryRightVasiCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryRightVasiModel() != null)
                model.getPrimaryRightVasiModel().removePropertyChangeListener(this);
            VasiModel vasiModel = new VasiModel();
            vasiModel.addPropertyChangeListener(this);
            vasiModel.setEnd("PRIMARY");
            vasiModel.setSide("RIGHT");
            if(SettingsEngine.getInstance().getVasiZEnd().equals("from end"))
                setBiasZ(vasiModel);
            model.setPrimaryRightVasiModel(vasiModel);
            primaryRightVasiCB.removeActionListener(this);
            prTypeComboBox.removeActionListener(this);
            prBiasXSpinner.removeChangeListener(this);
            prBiasXComboBox.removeActionListener(this);
            prBiasZSpinner.removeChangeListener(this);
            prBiasZComboBox.removeActionListener(this);
            prSpacingSpinner.removeChangeListener(this);
            prSpacingComboBox.removeActionListener(this);
            prAngleSpinner.removeChangeListener(this);
            prTypeComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getType());
            prBiasXSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getBiasX()));
            prBiasXComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getBiasXMeasure());
            prBiasZSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getBiasZ()));
            prBiasZComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getBiasZMeasure());
            prSpacingSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getSpacing()));
            prSpacingComboBox.setSelectedItem(model.getPrimaryRightVasiModel().getSpacingMeasure());
            prAngleSpinner.setValue(new Double(model.getPrimaryRightVasiModel().getPitch()));
            primaryRightVasiCB.addActionListener(this);
            prTypeComboBox.addActionListener(this);
            prBiasXSpinner.addChangeListener(this);
            prBiasXComboBox.addActionListener(this);
            prBiasZSpinner.addChangeListener(this);
            prBiasZComboBox.addActionListener(this);
            prSpacingSpinner.addChangeListener(this);
            prSpacingComboBox.addActionListener(this);
            prAngleSpinner.addChangeListener(this);
        } else
        {
            if(model.getPrimaryRightVasiModel() != null)
                model.getPrimaryRightVasiModel().removePropertyChangeListener(this);
            model.setPrimaryRightVasiModel(null);
        }
        firePropertyChange("update-vasiprimaryright", new Boolean(!status), new Boolean(status));
    }

    private void setSecondaryLeftVasiEnabled(boolean status)
    {
        slTypeLabel.setEnabled(status);
        slTypeComboBox.setEnabled(status);
        slBiasXLabel.setEnabled(status);
        slBiasXSpinner.setEnabled(status);
        slBiasXComboBox.setEnabled(status);
        slBiasZLabel.setEnabled(status);
        slBiasZSpinner.setEnabled(status);
        slBiasZComboBox.setEnabled(status);
        slSpacingLabel.setEnabled(status);
        slSpacingSpinner.setEnabled(status);
        slSpacingComboBox.setEnabled(status);
        slAngleLabel.setEnabled(status);
        slAngleSpinner.setEnabled(status);
        if(!containsActionListener(secondaryLeftVasiCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryLeftVasiModel() != null)
                model.getSecondaryLeftVasiModel().removePropertyChangeListener(this);
            VasiModel vasiModel = new VasiModel();
            vasiModel.addPropertyChangeListener(this);
            vasiModel.setEnd("SECONDARY");
            vasiModel.setSide("LEFT");
            if(SettingsEngine.getInstance().getVasiZEnd().equals("from end"))
                setBiasZ(vasiModel);
            model.setSecondaryLeftVasiModel(vasiModel);
            secondaryLeftVasiCB.removeActionListener(this);
            slTypeComboBox.removeActionListener(this);
            slBiasXSpinner.removeChangeListener(this);
            slBiasXComboBox.removeActionListener(this);
            slBiasZSpinner.removeChangeListener(this);
            slBiasZComboBox.removeActionListener(this);
            slSpacingSpinner.removeChangeListener(this);
            slSpacingComboBox.removeActionListener(this);
            slAngleSpinner.removeChangeListener(this);
            slTypeComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getType());
            slBiasXSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getBiasX()));
            slBiasXComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getBiasXMeasure());
            slBiasZSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getBiasZ()));
            slBiasZComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getBiasZMeasure());
            slSpacingSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getSpacing()));
            slSpacingComboBox.setSelectedItem(model.getSecondaryLeftVasiModel().getSpacingMeasure());
            slAngleSpinner.setValue(new Double(model.getSecondaryLeftVasiModel().getPitch()));
            setSecondaryLeftVasiEnabled(true);
            secondaryLeftVasiCB.addActionListener(this);
            slTypeComboBox.addActionListener(this);
            slBiasXSpinner.addChangeListener(this);
            slBiasXComboBox.addActionListener(this);
            slBiasZSpinner.addChangeListener(this);
            slBiasZComboBox.addActionListener(this);
            slSpacingSpinner.addChangeListener(this);
            slSpacingComboBox.addActionListener(this);
            slAngleSpinner.addChangeListener(this);
        } else
        {
            if(model.getSecondaryLeftVasiModel() != null)
                model.getSecondaryLeftVasiModel().removePropertyChangeListener(this);
            model.setSecondaryLeftVasiModel(null);
        }
        firePropertyChange("update-vasisecondaryleft", new Boolean(!status), new Boolean(status));
    }

    private void setSecondaryRightVasiEnabled(boolean status)
    {
        srTypeLabel.setEnabled(status);
        srTypeComboBox.setEnabled(status);
        srBiasXLabel.setEnabled(status);
        srBiasXSpinner.setEnabled(status);
        srBiasXComboBox.setEnabled(status);
        srBiasZLabel.setEnabled(status);
        srBiasZSpinner.setEnabled(status);
        srBiasZComboBox.setEnabled(status);
        srSpacingLabel.setEnabled(status);
        srSpacingSpinner.setEnabled(status);
        srSpacingComboBox.setEnabled(status);
        srAngleLabel.setEnabled(status);
        srAngleSpinner.setEnabled(status);
        if(!containsActionListener(secondaryRightVasiCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryRightVasiModel() != null)
                model.getSecondaryRightVasiModel().removePropertyChangeListener(this);
            VasiModel vasiModel = new VasiModel();
            vasiModel.addPropertyChangeListener(this);
            vasiModel.setEnd("SECONDARY");
            vasiModel.setSide("RIGHT");
            if(SettingsEngine.getInstance().getVasiZEnd().equals("from end"))
                setBiasZ(vasiModel);
            model.setSecondaryRightVasiModel(vasiModel);
            secondaryRightVasiCB.removeActionListener(this);
            srTypeComboBox.removeActionListener(this);
            srBiasXSpinner.removeChangeListener(this);
            srBiasXComboBox.removeActionListener(this);
            srBiasZSpinner.removeChangeListener(this);
            srBiasZComboBox.removeActionListener(this);
            srSpacingSpinner.removeChangeListener(this);
            srSpacingComboBox.removeActionListener(this);
            srAngleSpinner.removeChangeListener(this);
            srTypeComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getType());
            srBiasXSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getBiasX()));
            srBiasXComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getBiasXMeasure());
            srBiasZSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getBiasZ()));
            srBiasZComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getBiasZMeasure());
            srSpacingSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getSpacing()));
            srSpacingComboBox.setSelectedItem(model.getSecondaryRightVasiModel().getSpacingMeasure());
            srAngleSpinner.setValue(new Double(model.getSecondaryRightVasiModel().getPitch()));
            setSecondaryRightVasiEnabled(true);
            secondaryRightVasiCB.addActionListener(this);
            srTypeComboBox.addActionListener(this);
            srBiasXSpinner.addChangeListener(this);
            srBiasXComboBox.addActionListener(this);
            srBiasZSpinner.addChangeListener(this);
            srBiasZComboBox.addActionListener(this);
            srSpacingSpinner.addChangeListener(this);
            srSpacingComboBox.addActionListener(this);
            srAngleSpinner.addChangeListener(this);
        } else
        {
            if(model.getSecondaryRightVasiModel() != null)
                model.getSecondaryRightVasiModel().removePropertyChangeListener(this);
            model.setSecondaryRightVasiModel(null);
        }
        firePropertyChange("update-vasisecondaryright", new Boolean(!status), new Boolean(status));
    }

    private void setBiasZ(VasiModel vasiModel)
    {
        if(SettingsEngine.getInstance().getVasiZMeasure().equals("M"))
        {
            float runwayLength = model.getLength() / 2.0F;
            if(model.getLengthMeasure().equals("F"))
                runwayLength /= 3.28F;
            vasiModel.setBiasZ(runwayLength - SettingsEngine.getInstance().getVasiZ());
        } else
        {
            float runwayLength = model.getLength() / 2.0F;
            if(model.getLengthMeasure().equals("M"))
                runwayLength *= 3.28F;
            vasiModel.setBiasZ(runwayLength - SettingsEngine.getInstance().getVasiZ());
        }
    }

    private boolean containsActionListener(ActionListener list[])
    {
        for(int i = list.length - 1; i >= 0; i--)
            if(list[i] == this)
                return true;

        return false;
    }

    private void updateBiasXMeasure(VasiModel vasiModel, JComboBox comboBox, JSpinner spinner)
    {
        if(vasiModel == null)
            return;
        if(((String)comboBox.getSelectedItem()).equals(vasiModel.getBiasXMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(vasiModel.getBiasXMeasure().equals("M"))
                vasiModel.setBiasX(vasiModel.getBiasX() * 3.28F);
            else
                vasiModel.setBiasX(vasiModel.getBiasX() / 3.28F);
            spinner.setValue(new Double(vasiModel.getBiasX()));
        }
        vasiModel.setBiasXMeasure((String)comboBox.getSelectedItem());
    }

    private void updateBiasZMeasure(VasiModel vasiModel, JComboBox comboBox, JSpinner spinner)
    {
        if(vasiModel == null)
            return;
        if(((String)comboBox.getSelectedItem()).equals(vasiModel.getBiasZMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(vasiModel.getBiasZMeasure().equals("M"))
                vasiModel.setBiasZ(vasiModel.getBiasZ() * 3.28F);
            else
                vasiModel.setBiasZ(vasiModel.getBiasZ() / 3.28F);
            spinner.setValue(new Double(vasiModel.getBiasZ()));
        }
        vasiModel.setBiasZMeasure((String)comboBox.getSelectedItem());
    }

    private void updateSpacingMeasure(VasiModel vasiModel, JComboBox comboBox, JSpinner spinner)
    {
        if(vasiModel == null)
            return;
        if(((String)comboBox.getSelectedItem()).equals(vasiModel.getSpacingMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(vasiModel.getSpacingMeasure().equals("M"))
                vasiModel.setSpacing(vasiModel.getSpacing() * 3.28F);
            else
                vasiModel.setSpacing(vasiModel.getSpacing() / 3.28F);
            spinner.setValue(new Double(vasiModel.getSpacing()));
        }
        vasiModel.setSpacingMeasure((String)comboBox.getSelectedItem());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.remove(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        super.firePropertyChange(propertyName, oldValue, newValue);
        if(listeners == null)
            return;
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        for(int i = list.size() - 1; i >= 0; i--)
            ((PropertyChangeListener)list.elementAt(i)).propertyChange(event);

    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == primaryLeftVasiCB)
            setPrimaryLeftVasiEnabled(primaryLeftVasiCB.isSelected());
        else
        if(event.getSource() == primaryRightVasiCB)
            setPrimaryRightVasiEnabled(primaryRightVasiCB.isSelected());
        else
        if(event.getSource() == secondaryLeftVasiCB)
            setSecondaryLeftVasiEnabled(secondaryLeftVasiCB.isSelected());
        else
        if(event.getSource() == secondaryRightVasiCB)
            setSecondaryRightVasiEnabled(secondaryRightVasiCB.isSelected());
        else
        if(event.getSource() == plTypeComboBox)
        {
            firePropertyChange("update-vasitype", model.getPrimaryLeftVasiModel().getType(), (String)plTypeComboBox.getSelectedItem());
            model.getPrimaryLeftVasiModel().setType((String)plTypeComboBox.getSelectedItem());
        } else
        if(event.getSource() == plBiasXComboBox)
            updateBiasXMeasure(model.getPrimaryLeftVasiModel(), plBiasXComboBox, plBiasXSpinner);
        else
        if(event.getSource() == plBiasZComboBox)
            updateBiasZMeasure(model.getPrimaryLeftVasiModel(), plBiasZComboBox, plBiasZSpinner);
        else
        if(event.getSource() == plSpacingComboBox)
            updateSpacingMeasure(model.getPrimaryLeftVasiModel(), plSpacingComboBox, plSpacingSpinner);
        else
        if(event.getSource() == prTypeComboBox)
        {
            firePropertyChange("update-vasitype", model.getPrimaryRightVasiModel().getType(), (String)prTypeComboBox.getSelectedItem());
            model.getPrimaryRightVasiModel().setType((String)prTypeComboBox.getSelectedItem());
        } else
        if(event.getSource() == prBiasXComboBox)
            updateBiasXMeasure(model.getPrimaryRightVasiModel(), prBiasXComboBox, prBiasXSpinner);
        else
        if(event.getSource() == prBiasZComboBox)
            updateBiasZMeasure(model.getPrimaryRightVasiModel(), prBiasZComboBox, prBiasZSpinner);
        else
        if(event.getSource() == prSpacingComboBox)
            updateSpacingMeasure(model.getPrimaryRightVasiModel(), prSpacingComboBox, prSpacingSpinner);
        else
        if(event.getSource() == slTypeComboBox)
        {
            firePropertyChange("update-vasitype", model.getSecondaryLeftVasiModel().getType(), (String)slTypeComboBox.getSelectedItem());
            model.getSecondaryLeftVasiModel().setType((String)slTypeComboBox.getSelectedItem());
        } else
        if(event.getSource() == slBiasXComboBox)
            updateBiasXMeasure(model.getSecondaryLeftVasiModel(), slBiasXComboBox, slBiasXSpinner);
        else
        if(event.getSource() == slBiasZComboBox)
            updateBiasZMeasure(model.getSecondaryLeftVasiModel(), slBiasZComboBox, slBiasZSpinner);
        else
        if(event.getSource() == slSpacingComboBox)
            updateSpacingMeasure(model.getSecondaryLeftVasiModel(), slSpacingComboBox, slSpacingSpinner);
        else
        if(event.getSource() == srTypeComboBox)
        {
            firePropertyChange("update-vasitype", model.getSecondaryRightVasiModel().getType(), (String)srTypeComboBox.getSelectedItem());
            model.getSecondaryRightVasiModel().setType((String)srTypeComboBox.getSelectedItem());
        } else
        if(event.getSource() == srBiasXComboBox)
            updateBiasXMeasure(model.getSecondaryRightVasiModel(), srBiasXComboBox, srBiasXSpinner);
        else
        if(event.getSource() == srBiasZComboBox)
            updateBiasZMeasure(model.getSecondaryRightVasiModel(), srBiasZComboBox, srBiasZSpinner);
        else
        if(event.getSource() == srSpacingComboBox)
            updateSpacingMeasure(model.getSecondaryRightVasiModel(), srSpacingComboBox, srSpacingSpinner);
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == plBiasXSpinner)
        {
            firePropertyChange("update-vasibias", new Float(model.getPrimaryLeftVasiModel().getBiasX()), new Float(((Double)plBiasXSpinner.getValue()).floatValue()));
            model.getPrimaryLeftVasiModel().setBiasX(((Double)plBiasXSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == plBiasZSpinner)
        {
            firePropertyChange("update-vasibiasz", new Float(model.getPrimaryLeftVasiModel().getBiasZ()), new Float(((Double)plBiasZSpinner.getValue()).floatValue()));
            model.getPrimaryLeftVasiModel().setBiasZ(((Double)plBiasZSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == plSpacingSpinner)
        {
            firePropertyChange("update-vasispacing", new Float(model.getPrimaryLeftVasiModel().getSpacing()), new Float(((Double)plSpacingSpinner.getValue()).floatValue()));
            model.getPrimaryLeftVasiModel().setSpacing(((Double)plSpacingSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == plAngleSpinner)
            model.getPrimaryLeftVasiModel().setPitch(((Double)plAngleSpinner.getValue()).floatValue());
        else
        if(event.getSource() == prBiasXSpinner)
        {
            firePropertyChange("update-vasibias", new Float(model.getPrimaryRightVasiModel().getBiasX()), new Float(((Double)prBiasXSpinner.getValue()).floatValue()));
            model.getPrimaryRightVasiModel().setBiasX(((Double)prBiasXSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == prBiasZSpinner)
        {
            firePropertyChange("update-vasibiasz", new Float(model.getPrimaryRightVasiModel().getBiasZ()), new Float(((Double)prBiasZSpinner.getValue()).floatValue()));
            model.getPrimaryRightVasiModel().setBiasZ(((Double)prBiasZSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == prSpacingSpinner)
        {
            firePropertyChange("update-vasispacing", new Float(model.getPrimaryRightVasiModel().getSpacing()), new Float(((Double)prSpacingSpinner.getValue()).floatValue()));
            model.getPrimaryRightVasiModel().setSpacing(((Double)prSpacingSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == prAngleSpinner)
            model.getPrimaryRightVasiModel().setPitch(((Double)prAngleSpinner.getValue()).floatValue());
        else
        if(event.getSource() == slBiasXSpinner)
        {
            firePropertyChange("update-vasibias", new Float(model.getSecondaryLeftVasiModel().getBiasX()), new Float(((Double)slBiasXSpinner.getValue()).floatValue()));
            model.getSecondaryLeftVasiModel().setBiasX(((Double)slBiasXSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == slBiasZSpinner)
        {
            firePropertyChange("update-vasibiasz", new Float(model.getSecondaryLeftVasiModel().getBiasZ()), new Float(((Double)slBiasZSpinner.getValue()).floatValue()));
            model.getSecondaryLeftVasiModel().setBiasZ(((Double)slBiasZSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == slSpacingSpinner)
        {
            firePropertyChange("update-vasispacing", new Float(model.getSecondaryLeftVasiModel().getSpacing()), new Float(((Double)slSpacingSpinner.getValue()).floatValue()));
            model.getSecondaryLeftVasiModel().setSpacing(((Double)slSpacingSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == slAngleSpinner)
            model.getSecondaryLeftVasiModel().setPitch(((Double)slAngleSpinner.getValue()).floatValue());
        else
        if(event.getSource() == srBiasXSpinner)
        {
            firePropertyChange("update-vasibias", new Float(model.getSecondaryRightVasiModel().getBiasX()), new Float(((Double)srBiasXSpinner.getValue()).floatValue()));
            model.getSecondaryRightVasiModel().setBiasX(((Double)srBiasXSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == srBiasZSpinner)
        {
            firePropertyChange("update-vasibiasz", new Float(model.getSecondaryRightVasiModel().getBiasZ()), new Float(((Double)srBiasZSpinner.getValue()).floatValue()));
            model.getSecondaryRightVasiModel().setBiasZ(((Double)srBiasZSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == srSpacingSpinner)
        {
            firePropertyChange("update-vasispacing", new Float(model.getSecondaryRightVasiModel().getSpacing()), new Float(((Double)srSpacingSpinner.getValue()).floatValue()));
            model.getSecondaryRightVasiModel().setSpacing(((Double)srSpacingSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == srAngleSpinner)
            model.getSecondaryRightVasiModel().setPitch(((Double)srAngleSpinner.getValue()).floatValue());
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model.getPrimaryLeftVasiModel())
        {
            if(event.getPropertyName().equals("type"))
            {
                plTypeComboBox.removeActionListener(this);
                plTypeComboBox.setSelectedItem((String)event.getNewValue());
                plTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("biasXMeasure"))
                plBiasXComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasZMeasure"))
                plBiasZComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("spacingMeasure"))
                plSpacingComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasX"))
            {
                plBiasXSpinner.removeChangeListener(this);
                plBiasXSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                plBiasXSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("biasZ"))
            {
                plBiasZSpinner.removeChangeListener(this);
                plBiasZSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                plBiasZSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("spacing"))
            {
                plSpacingSpinner.removeChangeListener(this);
                plSpacingSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                plSpacingSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("pitch"))
            {
                plAngleSpinner.removeChangeListener(this);
                plAngleSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                plAngleSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getPrimaryRightVasiModel())
        {
            if(event.getPropertyName().equals("type"))
            {
                prTypeComboBox.removeActionListener(this);
                prTypeComboBox.setSelectedItem((String)event.getNewValue());
                prTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("biasXMeasure"))
                prBiasXComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasZMeasure"))
                prBiasZComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("spacingMeasure"))
                prSpacingComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasX"))
            {
                prBiasXSpinner.removeChangeListener(this);
                prBiasXSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                prBiasXSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("biasZ"))
            {
                prBiasZSpinner.removeChangeListener(this);
                prBiasZSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                prBiasZSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("spacing"))
            {
                prSpacingSpinner.removeChangeListener(this);
                prSpacingSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                prSpacingSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("pitch"))
            {
                prAngleSpinner.removeChangeListener(this);
                prAngleSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                prAngleSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryLeftVasiModel())
        {
            if(event.getPropertyName().equals("type"))
            {
                slTypeComboBox.removeActionListener(this);
                slTypeComboBox.setSelectedItem((String)event.getNewValue());
                slTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("biasXMeasure"))
                slBiasXComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasZMeasure"))
                slBiasZComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("spacingMeasure"))
                slSpacingComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasX"))
            {
                slBiasXSpinner.removeChangeListener(this);
                slBiasXSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                slBiasXSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("biasZ"))
            {
                slBiasZSpinner.removeChangeListener(this);
                slBiasZSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                slBiasZSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("spacing"))
            {
                slSpacingSpinner.removeChangeListener(this);
                slSpacingSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                slSpacingSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("pitch"))
            {
                slAngleSpinner.removeChangeListener(this);
                slAngleSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                slAngleSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryRightVasiModel())
            if(event.getPropertyName().equals("type"))
            {
                srTypeComboBox.removeActionListener(this);
                srTypeComboBox.setSelectedItem((String)event.getNewValue());
                srTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("biasXMeasure"))
                srBiasXComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasZMeasure"))
                srBiasZComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("spacingMeasure"))
                srSpacingComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("biasX"))
            {
                srBiasXSpinner.removeChangeListener(this);
                srBiasXSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                srBiasXSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("biasZ"))
            {
                srBiasZSpinner.removeChangeListener(this);
                srBiasZSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                srBiasZSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("spacing"))
            {
                srSpacingSpinner.removeChangeListener(this);
                srSpacingSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                srSpacingSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("pitch"))
            {
                srAngleSpinner.removeChangeListener(this);
                srAngleSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                srAngleSpinner.addChangeListener(this);
            }
    }

    private RunwayModel model;
    private Vector listeners;
    private JCheckBox primaryLeftVasiCB;
    private JLabel plTypeLabel;
    private JComboBox plTypeComboBox;
    private JLabel plBiasXLabel;
    private JSpinner plBiasXSpinner;
    private JComboBox plBiasXComboBox;
    private JLabel plBiasZLabel;
    private JSpinner plBiasZSpinner;
    private JComboBox plBiasZComboBox;
    private JLabel plSpacingLabel;
    private JSpinner plSpacingSpinner;
    private JComboBox plSpacingComboBox;
    private JLabel plAngleLabel;
    private JSpinner plAngleSpinner;
    private JCheckBox primaryRightVasiCB;
    private JLabel prTypeLabel;
    private JComboBox prTypeComboBox;
    private JLabel prBiasXLabel;
    private JSpinner prBiasXSpinner;
    private JComboBox prBiasXComboBox;
    private JLabel prBiasZLabel;
    private JSpinner prBiasZSpinner;
    private JComboBox prBiasZComboBox;
    private JLabel prSpacingLabel;
    private JSpinner prSpacingSpinner;
    private JComboBox prSpacingComboBox;
    private JLabel prAngleLabel;
    private JSpinner prAngleSpinner;
    private JCheckBox secondaryLeftVasiCB;
    private JLabel slTypeLabel;
    private JComboBox slTypeComboBox;
    private JLabel slBiasXLabel;
    private JSpinner slBiasXSpinner;
    private JComboBox slBiasXComboBox;
    private JLabel slBiasZLabel;
    private JSpinner slBiasZSpinner;
    private JComboBox slBiasZComboBox;
    private JLabel slSpacingLabel;
    private JSpinner slSpacingSpinner;
    private JComboBox slSpacingComboBox;
    private JLabel slAngleLabel;
    private JSpinner slAngleSpinner;
    private JCheckBox secondaryRightVasiCB;
    private JLabel srTypeLabel;
    private JComboBox srTypeComboBox;
    private JLabel srBiasXLabel;
    private JSpinner srBiasXSpinner;
    private JComboBox srBiasXComboBox;
    private JLabel srBiasZLabel;
    private JSpinner srBiasZSpinner;
    private JComboBox srBiasZComboBox;
    private JLabel srSpacingLabel;
    private JSpinner srSpacingSpinner;
    private JComboBox srSpacingComboBox;
    private JLabel srAngleLabel;
    private JSpinner srAngleSpinner;
}
