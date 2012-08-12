// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayDetailsData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.RunwayDetailModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
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

public class RunwayDetailsData extends JPanel
    implements ActionListener, ChangeListener, PropertyChangeListener
{

    public RunwayDetailsData()
    {
        listeners = new Vector();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        primaryOffsetCB = new JCheckBox("Primary Offset Threshold");
        primaryOffsetCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryOffsetCB.setForeground(Color.black);
        primaryOffsetCB.addActionListener(this);
        pOffsetLengthLabel = new JLabel("Length");
        pOffsetLengthLabel.setFont(Utilities.LABEL_FONT);
        pOffsetLengthLabel.setForeground(Color.black);
        pOffsetLengthLabel.setEnabled(false);
        pOffsetLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pOffsetLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pOffsetLengthSpinner, "0.000"));
        pOffsetLengthSpinner.addChangeListener(this);
        pOffsetLengthSpinner.setEnabled(false);
        pOffsetLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pOffsetLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOffsetLengthComboBox.setForeground(Color.black);
        pOffsetLengthComboBox.addActionListener(this);
        pOffsetLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pOffsetLengthComboBox.setPrototypeDisplayValue("--------");
        pOffsetWidthLabel = new JLabel("Width");
        pOffsetWidthLabel.setFont(Utilities.LABEL_FONT);
        pOffsetWidthLabel.setForeground(Color.black);
        pOffsetWidthLabel.setEnabled(false);
        pOffsetWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pOffsetWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pOffsetWidthSpinner, "0.000"));
        pOffsetWidthSpinner.addChangeListener(this);
        pOffsetWidthSpinner.setEnabled(false);
        pOffsetWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pOffsetWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOffsetWidthComboBox.setForeground(Color.black);
        pOffsetWidthComboBox.addActionListener(this);
        pOffsetWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pOffsetWidthComboBox.setPrototypeDisplayValue("--------");
        pOffsetSurfaceLabel = new JLabel("Surface");
        pOffsetSurfaceLabel.setFont(Utilities.LABEL_FONT);
        pOffsetSurfaceLabel.setForeground(Color.black);
        pOffsetSurfaceLabel.setEnabled(false);
        pOffsetSurfaceComboBox = new JComboBox(getSurfaceModel());
        pOffsetSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOffsetSurfaceComboBox.setForeground(Color.black);
        pOffsetSurfaceComboBox.setEnabled(false);
        pOffsetSurfaceComboBox.addActionListener(this);
        secondaryOffsetCB = new JCheckBox("Secondary Offset Threshold");
        secondaryOffsetCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryOffsetCB.setForeground(Color.black);
        secondaryOffsetCB.addActionListener(this);
        sOffsetLengthLabel = new JLabel("Length");
        sOffsetLengthLabel.setFont(Utilities.LABEL_FONT);
        sOffsetLengthLabel.setForeground(Color.black);
        sOffsetLengthLabel.setEnabled(false);
        sOffsetLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sOffsetLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sOffsetLengthSpinner, "0.000"));
        sOffsetLengthSpinner.addChangeListener(this);
        sOffsetLengthSpinner.setEnabled(false);
        sOffsetLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sOffsetLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOffsetLengthComboBox.setForeground(Color.black);
        sOffsetLengthComboBox.addActionListener(this);
        sOffsetLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sOffsetLengthComboBox.setPrototypeDisplayValue("--------");
        sOffsetWidthLabel = new JLabel("Width");
        sOffsetWidthLabel.setFont(Utilities.LABEL_FONT);
        sOffsetWidthLabel.setForeground(Color.black);
        sOffsetWidthLabel.setEnabled(false);
        sOffsetWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sOffsetWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sOffsetWidthSpinner, "0.000"));
        sOffsetWidthSpinner.addChangeListener(this);
        sOffsetWidthSpinner.setEnabled(false);
        sOffsetWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sOffsetWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOffsetWidthComboBox.setForeground(Color.black);
        sOffsetWidthComboBox.addActionListener(this);
        sOffsetWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sOffsetWidthComboBox.setPrototypeDisplayValue("--------");
        sOffsetSurfaceLabel = new JLabel("Surface");
        sOffsetSurfaceLabel.setFont(Utilities.LABEL_FONT);
        sOffsetSurfaceLabel.setForeground(Color.black);
        sOffsetSurfaceLabel.setEnabled(false);
        sOffsetSurfaceComboBox = new JComboBox(getSurfaceModel());
        sOffsetSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOffsetSurfaceComboBox.setForeground(Color.black);
        sOffsetSurfaceComboBox.setEnabled(false);
        sOffsetSurfaceComboBox.addActionListener(this);
        primaryBlastCB = new JCheckBox("Primary Blast Pad");
        primaryBlastCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryBlastCB.setForeground(Color.black);
        primaryBlastCB.addActionListener(this);
        pBlastLengthLabel = new JLabel("Length");
        pBlastLengthLabel.setFont(Utilities.LABEL_FONT);
        pBlastLengthLabel.setForeground(Color.black);
        pBlastLengthLabel.setEnabled(false);
        pBlastLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pBlastLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pBlastLengthSpinner, "0.000"));
        pBlastLengthSpinner.addChangeListener(this);
        pBlastLengthSpinner.setEnabled(false);
        pBlastLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pBlastLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pBlastLengthComboBox.setForeground(Color.black);
        pBlastLengthComboBox.addActionListener(this);
        pBlastLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pBlastLengthComboBox.setPrototypeDisplayValue("--------");
        pBlastWidthLabel = new JLabel("Width");
        pBlastWidthLabel.setFont(Utilities.LABEL_FONT);
        pBlastWidthLabel.setForeground(Color.black);
        pBlastWidthLabel.setEnabled(false);
        pBlastWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pBlastWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pBlastWidthSpinner, "0.000"));
        pBlastWidthSpinner.addChangeListener(this);
        pBlastWidthSpinner.setEnabled(false);
        pBlastWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pBlastWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pBlastWidthComboBox.setForeground(Color.black);
        pBlastWidthComboBox.addActionListener(this);
        pBlastWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pBlastWidthComboBox.setPrototypeDisplayValue("--------");
        pBlastSurfaceLabel = new JLabel("Surface");
        pBlastSurfaceLabel.setFont(Utilities.LABEL_FONT);
        pBlastSurfaceLabel.setForeground(Color.black);
        pBlastSurfaceLabel.setEnabled(false);
        pBlastSurfaceComboBox = new JComboBox(getSurfaceModel());
        pBlastSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pBlastSurfaceComboBox.setForeground(Color.black);
        pBlastSurfaceComboBox.setEnabled(false);
        pBlastSurfaceComboBox.addActionListener(this);
        secondaryBlastCB = new JCheckBox("Secondary Blast Pad");
        secondaryBlastCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryBlastCB.setForeground(Color.black);
        secondaryBlastCB.addActionListener(this);
        sBlastLengthLabel = new JLabel("Length");
        sBlastLengthLabel.setFont(Utilities.LABEL_FONT);
        sBlastLengthLabel.setForeground(Color.black);
        sBlastLengthLabel.setEnabled(false);
        sBlastLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sBlastLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sBlastLengthSpinner, "0.000"));
        sBlastLengthSpinner.addChangeListener(this);
        sBlastLengthSpinner.setEnabled(false);
        sBlastLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sBlastLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sBlastLengthComboBox.setForeground(Color.black);
        sBlastLengthComboBox.addActionListener(this);
        sBlastLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sBlastLengthComboBox.setPrototypeDisplayValue("--------");
        sBlastWidthLabel = new JLabel("Width");
        sBlastWidthLabel.setFont(Utilities.LABEL_FONT);
        sBlastWidthLabel.setForeground(Color.black);
        sBlastWidthLabel.setEnabled(false);
        sBlastWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sBlastWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sBlastWidthSpinner, "0.000"));
        sBlastWidthSpinner.addChangeListener(this);
        sBlastWidthSpinner.setEnabled(false);
        sBlastWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sBlastWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sBlastWidthComboBox.setForeground(Color.black);
        sBlastWidthComboBox.addActionListener(this);
        sBlastWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sBlastWidthComboBox.setPrototypeDisplayValue("--------");
        sBlastSurfaceLabel = new JLabel("Surface");
        sBlastSurfaceLabel.setFont(Utilities.LABEL_FONT);
        sBlastSurfaceLabel.setForeground(Color.black);
        sBlastSurfaceLabel.setEnabled(false);
        sBlastSurfaceComboBox = new JComboBox(getSurfaceModel());
        sBlastSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sBlastSurfaceComboBox.setForeground(Color.black);
        sBlastSurfaceComboBox.setEnabled(false);
        sBlastSurfaceComboBox.addActionListener(this);
        primaryOverrunCB = new JCheckBox("Primary Overrun");
        primaryOverrunCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryOverrunCB.setForeground(Color.black);
        primaryOverrunCB.addActionListener(this);
        pOverrunLengthLabel = new JLabel("Length");
        pOverrunLengthLabel.setFont(Utilities.LABEL_FONT);
        pOverrunLengthLabel.setForeground(Color.black);
        pOverrunLengthLabel.setEnabled(false);
        pOverrunLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pOverrunLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pOverrunLengthSpinner, "0.000"));
        pOverrunLengthSpinner.addChangeListener(this);
        pOverrunLengthSpinner.setEnabled(false);
        pOverrunLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pOverrunLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOverrunLengthComboBox.setForeground(Color.black);
        pOverrunLengthComboBox.addActionListener(this);
        pOverrunLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pOverrunLengthComboBox.setPrototypeDisplayValue("--------");
        pOverrunWidthLabel = new JLabel("Width");
        pOverrunWidthLabel.setFont(Utilities.LABEL_FONT);
        pOverrunWidthLabel.setForeground(Color.black);
        pOverrunWidthLabel.setEnabled(false);
        pOverrunWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        pOverrunWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pOverrunWidthSpinner, "0.000"));
        pOverrunWidthSpinner.addChangeListener(this);
        pOverrunWidthSpinner.setEnabled(false);
        pOverrunWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        pOverrunWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOverrunWidthComboBox.setForeground(Color.black);
        pOverrunWidthComboBox.addActionListener(this);
        pOverrunWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            pOverrunWidthComboBox.setPrototypeDisplayValue("--------");
        pOverrunSurfaceLabel = new JLabel("Surface");
        pOverrunSurfaceLabel.setFont(Utilities.LABEL_FONT);
        pOverrunSurfaceLabel.setForeground(Color.black);
        pOverrunSurfaceLabel.setEnabled(false);
        pOverrunSurfaceComboBox = new JComboBox(getSurfaceModel());
        pOverrunSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pOverrunSurfaceComboBox.setForeground(Color.black);
        pOverrunSurfaceComboBox.setEnabled(false);
        pOverrunSurfaceComboBox.addActionListener(this);
        secondaryOverrunCB = new JCheckBox("Secondary Overrun");
        secondaryOverrunCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryOverrunCB.setForeground(Color.black);
        secondaryOverrunCB.addActionListener(this);
        sOverrunLengthLabel = new JLabel("Length");
        sOverrunLengthLabel.setFont(Utilities.LABEL_FONT);
        sOverrunLengthLabel.setForeground(Color.black);
        sOverrunLengthLabel.setEnabled(false);
        sOverrunLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sOverrunLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sOverrunLengthSpinner, "0.000"));
        sOverrunLengthSpinner.addChangeListener(this);
        sOverrunLengthSpinner.setEnabled(false);
        sOverrunLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sOverrunLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOverrunLengthComboBox.setForeground(Color.black);
        sOverrunLengthComboBox.addActionListener(this);
        sOverrunLengthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sOverrunLengthComboBox.setPrototypeDisplayValue("--------");
        sOverrunWidthLabel = new JLabel("Width");
        sOverrunWidthLabel.setFont(Utilities.LABEL_FONT);
        sOverrunWidthLabel.setForeground(Color.black);
        sOverrunWidthLabel.setEnabled(false);
        sOverrunWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        sOverrunWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sOverrunWidthSpinner, "0.000"));
        sOverrunWidthSpinner.addChangeListener(this);
        sOverrunWidthSpinner.setEnabled(false);
        sOverrunWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sOverrunWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOverrunWidthComboBox.setForeground(Color.black);
        sOverrunWidthComboBox.addActionListener(this);
        sOverrunWidthComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sOverrunWidthComboBox.setPrototypeDisplayValue("--------");
        sOverrunSurfaceLabel = new JLabel("Surface");
        sOverrunSurfaceLabel.setFont(Utilities.LABEL_FONT);
        sOverrunSurfaceLabel.setForeground(Color.black);
        sOverrunSurfaceLabel.setEnabled(false);
        sOverrunSurfaceComboBox = new JComboBox(getSurfaceModel());
        sOverrunSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sOverrunSurfaceComboBox.setForeground(Color.black);
        sOverrunSurfaceComboBox.setEnabled(false);
        sOverrunSurfaceComboBox.addActionListener(this);
        Utilities.addComponent(this, primaryOffsetCB, 0, 4, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetLengthLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetLengthSpinner, 1, 5, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pOffsetLengthComboBox, 2, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetWidthLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetWidthSpinner, 1, 6, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pOffsetWidthComboBox, 2, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetSurfaceLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOffsetSurfaceComboBox, 1, 7, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryOffsetCB, 0, 8, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetLengthLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetLengthSpinner, 1, 9, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sOffsetLengthComboBox, 2, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetWidthLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetWidthSpinner, 1, 10, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sOffsetWidthComboBox, 2, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetSurfaceLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOffsetSurfaceComboBox, 1, 11, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primaryBlastCB, 0, 12, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastLengthLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastLengthSpinner, 1, 13, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pBlastLengthComboBox, 2, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastWidthLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastWidthSpinner, 1, 14, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pBlastWidthComboBox, 2, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastSurfaceLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pBlastSurfaceComboBox, 1, 15, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryBlastCB, 0, 16, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastLengthLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastLengthSpinner, 1, 17, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sBlastLengthComboBox, 2, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastWidthLabel, 0, 18, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastWidthSpinner, 1, 18, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sBlastWidthComboBox, 2, 18, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastSurfaceLabel, 0, 19, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sBlastSurfaceComboBox, 1, 19, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primaryOverrunCB, 0, 20, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunLengthLabel, 0, 21, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunLengthSpinner, 1, 21, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pOverrunLengthComboBox, 2, 21, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunWidthLabel, 0, 22, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunWidthSpinner, 1, 22, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pOverrunWidthComboBox, 2, 22, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunSurfaceLabel, 0, 23, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pOverrunSurfaceComboBox, 1, 23, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryOverrunCB, 0, 24, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunLengthLabel, 0, 25, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunLengthSpinner, 1, 25, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sOverrunLengthComboBox, 2, 25, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunWidthLabel, 0, 26, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunWidthSpinner, 1, 26, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sOverrunWidthComboBox, 2, 26, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunSurfaceLabel, 0, 27, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sOverrunSurfaceComboBox, 1, 27, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
    }

    private DefaultComboBoxModel getSurfaceModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("ASPHALT");
        model.addElement("BITUMINOUS");
        model.addElement("BRICK");
        model.addElement("CLAY");
        model.addElement("CEMENT");
        model.addElement("CONCRETE");
        model.addElement("CORAL");
        model.addElement("DIRT");
        model.addElement("GRASS");
        model.addElement("GRAVEL");
        model.addElement("ICE");
        model.addElement("MACADAM");
        model.addElement("OIL_TREATED, PLANKS");
        model.addElement("SAND");
        model.addElement("SHALE");
        model.addElement("SNOW");
        model.addElement("STEEL_MATS");
        model.addElement("TARMAC");
        model.addElement("UNKNOWN");
        model.addElement("WATER");
        return model;
    }

    public void updateDisplay(RunwayModel model)
    {
        updateModel();
        if(this.model != null)
        {
            if(this.model.getPrimaryOffsetModel() != null)
                this.model.getPrimaryOffsetModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryOffsetModel() != null)
                this.model.getSecondaryOffsetModel().removePropertyChangeListener(this);
            if(this.model.getPrimaryBlastPadModel() != null)
                this.model.getPrimaryBlastPadModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryBlastPadModel() != null)
                this.model.getSecondaryBlastPadModel().removePropertyChangeListener(this);
            if(this.model.getPrimaryOverrunModel() != null)
                this.model.getPrimaryOverrunModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryOverrunModel() != null)
                this.model.getSecondaryOverrunModel().removePropertyChangeListener(this);
        }
        this.model = model;
        primaryOffsetCB.removeActionListener(this);
        secondaryOffsetCB.removeActionListener(this);
        primaryBlastCB.removeActionListener(this);
        secondaryBlastCB.removeActionListener(this);
        primaryOverrunCB.removeActionListener(this);
        secondaryOverrunCB.removeActionListener(this);
        pOffsetLengthSpinner.removeChangeListener(this);
        pOffsetLengthComboBox.removeActionListener(this);
        pOffsetWidthSpinner.removeChangeListener(this);
        pOffsetWidthComboBox.removeActionListener(this);
        pOffsetSurfaceComboBox.removeActionListener(this);
        sOffsetLengthSpinner.removeChangeListener(this);
        sOffsetLengthComboBox.removeActionListener(this);
        sOffsetWidthSpinner.removeChangeListener(this);
        sOffsetWidthComboBox.removeActionListener(this);
        sOffsetSurfaceComboBox.removeActionListener(this);
        pBlastLengthSpinner.removeChangeListener(this);
        pBlastLengthComboBox.removeActionListener(this);
        pBlastWidthSpinner.removeChangeListener(this);
        pBlastWidthComboBox.removeActionListener(this);
        pBlastSurfaceComboBox.removeActionListener(this);
        sBlastLengthSpinner.removeChangeListener(this);
        sBlastLengthComboBox.removeActionListener(this);
        sBlastWidthSpinner.removeChangeListener(this);
        sBlastWidthComboBox.removeActionListener(this);
        sBlastSurfaceComboBox.removeActionListener(this);
        pOverrunLengthSpinner.removeChangeListener(this);
        pOverrunLengthComboBox.removeActionListener(this);
        pOverrunWidthSpinner.removeChangeListener(this);
        pOverrunWidthComboBox.removeActionListener(this);
        pOverrunSurfaceComboBox.removeActionListener(this);
        sOverrunLengthSpinner.removeChangeListener(this);
        sOverrunLengthComboBox.removeActionListener(this);
        sOverrunWidthSpinner.removeChangeListener(this);
        sOverrunWidthComboBox.removeActionListener(this);
        sOverrunSurfaceComboBox.removeActionListener(this);
        if(model != null)
        {
            if(model.getPrimaryOffsetModel() == null)
            {
                primaryOffsetCB.setSelected(false);
                pOffsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                pOffsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                pOffsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                pOffsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                pOffsetSurfaceComboBox.setSelectedIndex(0);
                setPrimaryOffsetEnabled(false);
            } else
            {
                primaryOffsetCB.setSelected(true);
                pOffsetLengthSpinner.setValue(new Double(model.getPrimaryOffsetModel().getLength()));
                pOffsetLengthComboBox.setSelectedItem(model.getPrimaryOffsetModel().getLengthMeasure());
                pOffsetWidthSpinner.setValue(new Double(model.getPrimaryOffsetModel().getWidth()));
                pOffsetWidthComboBox.setSelectedItem(model.getPrimaryOffsetModel().getWidthMeasure());
                pOffsetSurfaceComboBox.setSelectedItem(model.getPrimaryOffsetModel().getSurface());
                setPrimaryOffsetEnabled(true);
                model.getPrimaryOffsetModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryOffsetModel() == null)
            {
                secondaryOffsetCB.setSelected(false);
                sOffsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                sOffsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                sOffsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                sOffsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                sOffsetSurfaceComboBox.setSelectedIndex(0);
                setSecondaryOffsetEnabled(false);
            } else
            {
                secondaryOffsetCB.setSelected(true);
                sOffsetLengthSpinner.setValue(new Double(model.getSecondaryOffsetModel().getLength()));
                sOffsetLengthComboBox.setSelectedItem(model.getSecondaryOffsetModel().getLengthMeasure());
                sOffsetWidthSpinner.setValue(new Double(model.getSecondaryOffsetModel().getWidth()));
                sOffsetWidthComboBox.setSelectedItem(model.getSecondaryOffsetModel().getWidthMeasure());
                sOffsetSurfaceComboBox.setSelectedItem(model.getSecondaryOffsetModel().getSurface());
                setSecondaryOffsetEnabled(true);
                model.getSecondaryOffsetModel().addPropertyChangeListener(this);
            }
            if(model.getPrimaryBlastPadModel() == null)
            {
                primaryBlastCB.setSelected(false);
                pBlastLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                pBlastLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                pBlastWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                pBlastWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                pBlastSurfaceComboBox.setSelectedIndex(0);
                setPrimaryBlastEnabled(false);
            } else
            {
                primaryBlastCB.setSelected(true);
                pBlastLengthSpinner.setValue(new Double(model.getPrimaryBlastPadModel().getLength()));
                pBlastLengthComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getLengthMeasure());
                pBlastWidthSpinner.setValue(new Double(model.getPrimaryBlastPadModel().getWidth()));
                pBlastWidthComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getWidthMeasure());
                pBlastSurfaceComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getSurface());
                setPrimaryBlastEnabled(true);
                model.getPrimaryBlastPadModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryBlastPadModel() == null)
            {
                secondaryBlastCB.setSelected(false);
                sBlastLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                sBlastLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                sBlastWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                sBlastWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                sBlastSurfaceComboBox.setSelectedIndex(0);
                setSecondaryBlastEnabled(false);
            } else
            {
                secondaryBlastCB.setSelected(true);
                sBlastLengthSpinner.setValue(new Double(model.getSecondaryBlastPadModel().getLength()));
                sBlastLengthComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getLengthMeasure());
                sBlastWidthSpinner.setValue(new Double(model.getSecondaryBlastPadModel().getWidth()));
                sBlastWidthComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getWidthMeasure());
                sBlastSurfaceComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getSurface());
                setSecondaryBlastEnabled(true);
                model.getSecondaryBlastPadModel().addPropertyChangeListener(this);
            }
            if(model.getPrimaryOverrunModel() == null)
            {
                primaryOverrunCB.setSelected(false);
                pOverrunLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                pOverrunLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                pOverrunWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                pOverrunWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                pOverrunSurfaceComboBox.setSelectedIndex(0);
                setPrimaryOverrunEnabled(false);
            } else
            {
                primaryOverrunCB.setSelected(true);
                pOverrunLengthSpinner.setValue(new Double(model.getPrimaryOverrunModel().getLength()));
                pOverrunLengthComboBox.setSelectedItem(model.getPrimaryOverrunModel().getLengthMeasure());
                pOverrunWidthSpinner.setValue(new Double(model.getPrimaryOverrunModel().getWidth()));
                pOverrunWidthComboBox.setSelectedItem(model.getPrimaryOverrunModel().getWidthMeasure());
                pOverrunSurfaceComboBox.setSelectedItem(model.getPrimaryOverrunModel().getSurface());
                setPrimaryOverrunEnabled(true);
                model.getPrimaryOverrunModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryOverrunModel() == null)
            {
                secondaryOverrunCB.setSelected(false);
                sOverrunLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
                sOverrunLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
                sOverrunWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
                sOverrunWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
                sOverrunSurfaceComboBox.setSelectedIndex(0);
                setSecondaryOverrunEnabled(false);
            } else
            {
                secondaryOverrunCB.setSelected(true);
                sOverrunLengthSpinner.setValue(new Double(model.getSecondaryOverrunModel().getLength()));
                sOverrunLengthComboBox.setSelectedItem(model.getSecondaryOverrunModel().getLengthMeasure());
                sOverrunWidthSpinner.setValue(new Double(model.getSecondaryOverrunModel().getWidth()));
                sOverrunWidthComboBox.setSelectedItem(model.getSecondaryOverrunModel().getWidthMeasure());
                sOverrunSurfaceComboBox.setSelectedItem(model.getSecondaryOverrunModel().getSurface());
                setSecondaryOverrunEnabled(true);
                model.getSecondaryOverrunModel().addPropertyChangeListener(this);
            }
        } else
        {
            primaryOffsetCB.setSelected(false);
            pOffsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pOffsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pOffsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pOffsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pOffsetSurfaceComboBox.setSelectedIndex(0);
            setPrimaryOffsetEnabled(false);
            secondaryOffsetCB.setSelected(false);
            sOffsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sOffsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sOffsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sOffsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sOffsetSurfaceComboBox.setSelectedIndex(0);
            setSecondaryOffsetEnabled(false);
            primaryBlastCB.setSelected(false);
            pBlastLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pBlastLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pBlastWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pBlastWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pBlastSurfaceComboBox.setSelectedIndex(0);
            setPrimaryBlastEnabled(false);
            secondaryBlastCB.setSelected(false);
            sBlastLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sBlastLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sBlastWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sBlastWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sBlastSurfaceComboBox.setSelectedIndex(0);
            setSecondaryBlastEnabled(false);
            primaryOverrunCB.setSelected(false);
            pOverrunLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pOverrunLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pOverrunWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            pOverrunWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            pOverrunSurfaceComboBox.setSelectedIndex(0);
            setPrimaryOverrunEnabled(false);
            secondaryOverrunCB.setSelected(false);
            sOverrunLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sOverrunLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sOverrunWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
            sOverrunWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
            sOverrunSurfaceComboBox.setSelectedIndex(0);
            setSecondaryOverrunEnabled(false);
        }
        primaryOffsetCB.addActionListener(this);
        secondaryOffsetCB.addActionListener(this);
        primaryBlastCB.addActionListener(this);
        secondaryBlastCB.addActionListener(this);
        primaryOverrunCB.addActionListener(this);
        secondaryOverrunCB.addActionListener(this);
        pOffsetLengthSpinner.addChangeListener(this);
        pOffsetLengthComboBox.addActionListener(this);
        pOffsetWidthSpinner.addChangeListener(this);
        pOffsetWidthComboBox.addActionListener(this);
        pOffsetSurfaceComboBox.addActionListener(this);
        sOffsetLengthSpinner.addChangeListener(this);
        sOffsetLengthComboBox.addActionListener(this);
        sOffsetWidthSpinner.addChangeListener(this);
        sOffsetWidthComboBox.addActionListener(this);
        sOffsetSurfaceComboBox.addActionListener(this);
        pBlastLengthSpinner.addChangeListener(this);
        pBlastLengthComboBox.addActionListener(this);
        pBlastWidthSpinner.addChangeListener(this);
        pBlastWidthComboBox.addActionListener(this);
        pBlastSurfaceComboBox.addActionListener(this);
        sBlastLengthSpinner.addChangeListener(this);
        sBlastLengthComboBox.addActionListener(this);
        sBlastWidthSpinner.addChangeListener(this);
        sBlastWidthComboBox.addActionListener(this);
        sBlastSurfaceComboBox.addActionListener(this);
        pOverrunLengthSpinner.addChangeListener(this);
        pOverrunLengthComboBox.addActionListener(this);
        pOverrunWidthSpinner.addChangeListener(this);
        pOverrunWidthComboBox.addActionListener(this);
        pOverrunSurfaceComboBox.addActionListener(this);
        sOverrunLengthSpinner.addChangeListener(this);
        sOverrunLengthComboBox.addActionListener(this);
        sOverrunWidthSpinner.addChangeListener(this);
        sOverrunWidthComboBox.addActionListener(this);
        sOverrunSurfaceComboBox.addActionListener(this);
        boolean status = model != null;
        primaryOffsetCB.setEnabled(status);
        secondaryOffsetCB.setEnabled(status);
        primaryBlastCB.setEnabled(status);
        secondaryBlastCB.setEnabled(status);
        primaryOverrunCB.setEnabled(status);
        secondaryOverrunCB.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            try
            {
                pOffsetLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                pOffsetWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sOffsetLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sOffsetWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                pBlastLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                pBlastWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sBlastLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sBlastWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                pOverrunLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                pOverrunWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sOverrunLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sOverrunWidthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
        }
    }

    private void setPrimaryOffsetEnabled(boolean status)
    {
        pOffsetLengthLabel.setEnabled(status);
        pOffsetLengthSpinner.setEnabled(status);
        pOffsetLengthComboBox.setEnabled(status);
        pOffsetWidthLabel.setEnabled(status);
        pOffsetWidthSpinner.setEnabled(status);
        pOffsetWidthComboBox.setEnabled(status);
        pOffsetSurfaceLabel.setEnabled(status);
        pOffsetSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(primaryOffsetCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryOffsetModel() != null)
                model.getPrimaryOffsetModel().removePropertyChangeListener(this);
            RunwayDetailModel offsetThresholdModel = new RunwayDetailModel();
            offsetThresholdModel.addPropertyChangeListener(this);
            offsetThresholdModel.setEnd("PRIMARY");
            setRunwayProperties(offsetThresholdModel);
            model.setPrimaryOffsetModel(offsetThresholdModel);
            pOffsetLengthSpinner.removeChangeListener(this);
            pOffsetLengthComboBox.removeActionListener(this);
            pOffsetWidthSpinner.removeChangeListener(this);
            pOffsetWidthComboBox.removeActionListener(this);
            pOffsetSurfaceComboBox.removeActionListener(this);
            pOffsetLengthSpinner.setValue(new Double(model.getPrimaryOffsetModel().getLength()));
            pOffsetLengthComboBox.setSelectedItem(model.getPrimaryOffsetModel().getLengthMeasure());
            pOffsetWidthSpinner.setValue(new Double(model.getPrimaryOffsetModel().getWidth()));
            pOffsetWidthComboBox.setSelectedItem(model.getPrimaryOffsetModel().getWidthMeasure());
            pOffsetSurfaceComboBox.setSelectedItem(model.getPrimaryOffsetModel().getSurface());
            pOffsetLengthSpinner.addChangeListener(this);
            pOffsetLengthComboBox.addActionListener(this);
            pOffsetWidthSpinner.addChangeListener(this);
            pOffsetWidthComboBox.addActionListener(this);
            pOffsetSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getPrimaryOffsetModel() != null)
                model.getPrimaryOffsetModel().removePropertyChangeListener(this);
            model.setPrimaryOffsetModel(null);
        }
        firePropertyChange("update-primaryOffset", new Boolean(true), new Boolean(false));
    }

    private void setSecondaryOffsetEnabled(boolean status)
    {
        sOffsetLengthLabel.setEnabled(status);
        sOffsetLengthSpinner.setEnabled(status);
        sOffsetLengthComboBox.setEnabled(status);
        sOffsetWidthLabel.setEnabled(status);
        sOffsetWidthSpinner.setEnabled(status);
        sOffsetWidthComboBox.setEnabled(status);
        sOffsetSurfaceLabel.setEnabled(status);
        sOffsetSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(secondaryOffsetCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryOffsetModel() != null)
                model.getSecondaryOffsetModel().removePropertyChangeListener(this);
            RunwayDetailModel offsetThresholdModel = new RunwayDetailModel();
            offsetThresholdModel.addPropertyChangeListener(this);
            offsetThresholdModel.setEnd("SECONDARY");
            setRunwayProperties(offsetThresholdModel);
            model.setSecondaryOffsetModel(offsetThresholdModel);
            sOffsetLengthSpinner.removeChangeListener(this);
            sOffsetLengthComboBox.removeActionListener(this);
            sOffsetWidthSpinner.removeChangeListener(this);
            sOffsetWidthComboBox.removeActionListener(this);
            sOffsetSurfaceComboBox.removeActionListener(this);
            sOffsetLengthSpinner.setValue(new Double(model.getSecondaryOffsetModel().getLength()));
            sOffsetLengthComboBox.setSelectedItem(model.getSecondaryOffsetModel().getLengthMeasure());
            sOffsetWidthSpinner.setValue(new Double(model.getSecondaryOffsetModel().getWidth()));
            sOffsetWidthComboBox.setSelectedItem(model.getSecondaryOffsetModel().getWidthMeasure());
            sOffsetSurfaceComboBox.setSelectedItem(model.getSecondaryOffsetModel().getSurface());
            sOffsetLengthSpinner.addChangeListener(this);
            sOffsetLengthComboBox.addActionListener(this);
            sOffsetWidthSpinner.addChangeListener(this);
            sOffsetWidthComboBox.addActionListener(this);
            sOffsetSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getSecondaryOffsetModel() != null)
                model.getSecondaryOffsetModel().removePropertyChangeListener(this);
            model.setSecondaryOffsetModel(null);
        }
        firePropertyChange("update-secondaryOffset", new Boolean(true), new Boolean(false));
    }

    private void setPrimaryBlastEnabled(boolean status)
    {
        pBlastLengthLabel.setEnabled(status);
        pBlastLengthSpinner.setEnabled(status);
        pBlastLengthComboBox.setEnabled(status);
        pBlastWidthLabel.setEnabled(status);
        pBlastWidthSpinner.setEnabled(status);
        pBlastWidthComboBox.setEnabled(status);
        pBlastSurfaceLabel.setEnabled(status);
        pBlastSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(primaryBlastCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryBlastPadModel() != null)
                model.getPrimaryBlastPadModel().removePropertyChangeListener(this);
            RunwayDetailModel blastPadModel = new RunwayDetailModel();
            blastPadModel.addPropertyChangeListener(this);
            blastPadModel.setEnd("PRIMARY");
            setRunwayProperties(blastPadModel);
            model.setPrimaryBlastPadModel(blastPadModel);
            pBlastLengthSpinner.removeChangeListener(this);
            pBlastLengthComboBox.removeActionListener(this);
            pBlastWidthSpinner.removeChangeListener(this);
            pBlastWidthComboBox.removeActionListener(this);
            pBlastSurfaceComboBox.removeActionListener(this);
            pBlastLengthSpinner.setValue(new Double(model.getPrimaryBlastPadModel().getLength()));
            pBlastLengthComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getLengthMeasure());
            pBlastWidthSpinner.setValue(new Double(model.getPrimaryBlastPadModel().getWidth()));
            pBlastWidthComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getWidthMeasure());
            pBlastSurfaceComboBox.setSelectedItem(model.getPrimaryBlastPadModel().getSurface());
            pBlastLengthSpinner.addChangeListener(this);
            pBlastLengthComboBox.addActionListener(this);
            pBlastWidthSpinner.addChangeListener(this);
            pBlastWidthComboBox.addActionListener(this);
            pBlastSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getPrimaryBlastPadModel() != null)
                model.getPrimaryBlastPadModel().removePropertyChangeListener(this);
            model.setPrimaryBlastPadModel(null);
        }
        firePropertyChange("update-primaryBlast", new Boolean(true), new Boolean(false));
    }

    private void setSecondaryBlastEnabled(boolean status)
    {
        sBlastLengthLabel.setEnabled(status);
        sBlastLengthSpinner.setEnabled(status);
        sBlastLengthComboBox.setEnabled(status);
        sBlastWidthLabel.setEnabled(status);
        sBlastWidthSpinner.setEnabled(status);
        sBlastWidthComboBox.setEnabled(status);
        sBlastSurfaceLabel.setEnabled(status);
        sBlastSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(secondaryBlastCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryBlastPadModel() != null)
                model.getSecondaryBlastPadModel().removePropertyChangeListener(this);
            RunwayDetailModel blastPadModel = new RunwayDetailModel();
            blastPadModel.addPropertyChangeListener(this);
            blastPadModel.setEnd("SECONDARY");
            setRunwayProperties(blastPadModel);
            model.setSecondaryBlastPadModel(blastPadModel);
            sBlastLengthSpinner.removeChangeListener(this);
            sBlastLengthComboBox.removeActionListener(this);
            sBlastWidthSpinner.removeChangeListener(this);
            sBlastWidthComboBox.removeActionListener(this);
            sBlastSurfaceComboBox.removeActionListener(this);
            sBlastLengthSpinner.setValue(new Double(model.getSecondaryBlastPadModel().getLength()));
            sBlastLengthComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getLengthMeasure());
            sBlastWidthSpinner.setValue(new Double(model.getSecondaryBlastPadModel().getWidth()));
            sBlastWidthComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getWidthMeasure());
            sBlastSurfaceComboBox.setSelectedItem(model.getSecondaryBlastPadModel().getSurface());
            sBlastLengthSpinner.addChangeListener(this);
            sBlastLengthComboBox.addActionListener(this);
            sBlastWidthSpinner.addChangeListener(this);
            sBlastWidthComboBox.addActionListener(this);
            sBlastSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getSecondaryBlastPadModel() != null)
                model.getSecondaryBlastPadModel().removePropertyChangeListener(this);
            model.setSecondaryBlastPadModel(null);
        }
        firePropertyChange("update-secondaryBlast", new Boolean(true), new Boolean(false));
    }

    private void setPrimaryOverrunEnabled(boolean status)
    {
        pOverrunLengthLabel.setEnabled(status);
        pOverrunLengthSpinner.setEnabled(status);
        pOverrunLengthComboBox.setEnabled(status);
        pOverrunWidthLabel.setEnabled(status);
        pOverrunWidthSpinner.setEnabled(status);
        pOverrunWidthComboBox.setEnabled(status);
        pOverrunSurfaceLabel.setEnabled(status);
        pOverrunSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(primaryOverrunCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryOverrunModel() != null)
                model.getPrimaryOverrunModel().removePropertyChangeListener(this);
            RunwayDetailModel overrunModel = new RunwayDetailModel();
            overrunModel.addPropertyChangeListener(this);
            overrunModel.setEnd("PRIMARY");
            setRunwayProperties(overrunModel);
            model.setPrimaryOverrunModel(overrunModel);
            pOverrunLengthSpinner.removeChangeListener(this);
            pOverrunLengthComboBox.removeActionListener(this);
            pOverrunWidthSpinner.removeChangeListener(this);
            pOverrunWidthComboBox.removeActionListener(this);
            pOverrunSurfaceComboBox.removeActionListener(this);
            pOverrunLengthSpinner.setValue(new Double(model.getPrimaryOverrunModel().getLength()));
            pOverrunLengthComboBox.setSelectedItem(model.getPrimaryOverrunModel().getLengthMeasure());
            pOverrunWidthSpinner.setValue(new Double(model.getPrimaryOverrunModel().getWidth()));
            pOverrunWidthComboBox.setSelectedItem(model.getPrimaryOverrunModel().getWidthMeasure());
            pOverrunSurfaceComboBox.setSelectedItem(model.getPrimaryOverrunModel().getSurface());
            pOverrunLengthSpinner.addChangeListener(this);
            pOverrunLengthComboBox.addActionListener(this);
            pOverrunWidthSpinner.addChangeListener(this);
            pOverrunWidthComboBox.addActionListener(this);
            pOverrunSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getPrimaryOverrunModel() != null)
                model.getPrimaryOverrunModel().removePropertyChangeListener(this);
            model.setPrimaryOverrunModel(null);
        }
        firePropertyChange("update-primaryOverrun", new Boolean(true), new Boolean(false));
    }

    private void setSecondaryOverrunEnabled(boolean status)
    {
        sOverrunLengthLabel.setEnabled(status);
        sOverrunLengthSpinner.setEnabled(status);
        sOverrunLengthComboBox.setEnabled(status);
        sOverrunWidthLabel.setEnabled(status);
        sOverrunWidthSpinner.setEnabled(status);
        sOverrunWidthComboBox.setEnabled(status);
        sOverrunSurfaceLabel.setEnabled(status);
        sOverrunSurfaceComboBox.setEnabled(status);
        if(!containsActionListener(secondaryOverrunCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryOverrunModel() != null)
                model.getSecondaryOverrunModel().removePropertyChangeListener(this);
            RunwayDetailModel overrunModel = new RunwayDetailModel();
            overrunModel.addPropertyChangeListener(this);
            overrunModel.setEnd("SECONDARY");
            setRunwayProperties(overrunModel);
            model.setSecondaryOverrunModel(overrunModel);
            sOverrunLengthSpinner.removeChangeListener(this);
            sOverrunLengthComboBox.removeActionListener(this);
            sOverrunWidthSpinner.removeChangeListener(this);
            sOverrunWidthComboBox.removeActionListener(this);
            sOverrunSurfaceComboBox.removeActionListener(this);
            sOverrunLengthSpinner.setValue(new Double(model.getSecondaryOverrunModel().getLength()));
            sOverrunLengthComboBox.setSelectedItem(model.getSecondaryOverrunModel().getLengthMeasure());
            sOverrunWidthSpinner.setValue(new Double(model.getSecondaryOverrunModel().getWidth()));
            sOverrunWidthComboBox.setSelectedItem(model.getSecondaryOverrunModel().getWidthMeasure());
            sOverrunSurfaceComboBox.setSelectedItem(model.getSecondaryOverrunModel().getSurface());
            sOverrunLengthSpinner.addChangeListener(this);
            sOverrunLengthComboBox.addActionListener(this);
            sOverrunWidthSpinner.addChangeListener(this);
            sOverrunWidthComboBox.addActionListener(this);
            sOverrunSurfaceComboBox.addActionListener(this);
        } else
        {
            if(model.getSecondaryOverrunModel() != null)
                model.getSecondaryOverrunModel().removePropertyChangeListener(this);
            model.setSecondaryOverrunModel(null);
        }
        firePropertyChange("update-secondaryOverrun", new Boolean(true), new Boolean(false));
    }

    private void setRunwayProperties(RunwayDetailModel runwayDetailModel)
    {
        if(SettingsEngine.getInstance().getOffsetWidthRunway())
        {
            runwayDetailModel.setWidth(model.getWidth());
            runwayDetailModel.setWidthMeasure(model.getWidthMeasure());
        }
        if(SettingsEngine.getInstance().getOffsetSurfaceRunway())
            runwayDetailModel.setSurface(model.getSurface());
    }

    private boolean containsActionListener(ActionListener list[])
    {
        for(int i = list.length - 1; i >= 0; i--)
            if(list[i] == this)
                return true;

        return false;
    }

    private void updateSurface(RunwayDetailModel runwayDetailModel, JComboBox comboBox)
    {
        if(runwayDetailModel == null)
        {
            return;
        } else
        {
            firePropertyChange("update-surface", runwayDetailModel.getSurface(), (String)comboBox.getSelectedItem());
            runwayDetailModel.setSurface((String)comboBox.getSelectedItem());
            return;
        }
    }

    private void updateLengthMeasure(RunwayDetailModel runwayDetailModel, JComboBox comboBox, JSpinner spinner)
    {
        if(runwayDetailModel == null)
            return;
        if(((String)comboBox.getSelectedItem()).equals(runwayDetailModel.getLengthMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(runwayDetailModel.getLengthMeasure().equals("M"))
                runwayDetailModel.setLength(runwayDetailModel.getLength() * 3.28F);
            else
                runwayDetailModel.setLength(runwayDetailModel.getLength() / 3.28F);
            spinner.setValue(new Double(runwayDetailModel.getLength()));
        }
        firePropertyChange("update-lengthMeasure", runwayDetailModel.getLengthMeasure(), (String)comboBox.getSelectedItem());
        runwayDetailModel.setLengthMeasure((String)comboBox.getSelectedItem());
    }

    private void updateWidthMeasure(RunwayDetailModel runwayDetailModel, JComboBox comboBox, JSpinner spinner)
    {
        if(runwayDetailModel == null)
            return;
        if(((String)comboBox.getSelectedItem()).equals(runwayDetailModel.getWidthMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(runwayDetailModel.getWidthMeasure().equals("M"))
                runwayDetailModel.setWidth(runwayDetailModel.getWidth() * 3.28F);
            else
                runwayDetailModel.setWidth(runwayDetailModel.getWidth() / 3.28F);
            spinner.setValue(new Double(runwayDetailModel.getWidth()));
        }
        firePropertyChange("update-widthMeasure", runwayDetailModel.getWidthMeasure(), (String)comboBox.getSelectedItem());
        runwayDetailModel.setWidthMeasure((String)comboBox.getSelectedItem());
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
        if(event.getSource() == primaryOffsetCB)
            setPrimaryOffsetEnabled(primaryOffsetCB.isSelected());
        else
        if(event.getSource() == secondaryOffsetCB)
            setSecondaryOffsetEnabled(secondaryOffsetCB.isSelected());
        else
        if(event.getSource() == primaryBlastCB)
            setPrimaryBlastEnabled(primaryBlastCB.isSelected());
        else
        if(event.getSource() == secondaryBlastCB)
            setSecondaryBlastEnabled(secondaryBlastCB.isSelected());
        else
        if(event.getSource() == primaryOverrunCB)
            setPrimaryOverrunEnabled(primaryOverrunCB.isSelected());
        else
        if(event.getSource() == secondaryOverrunCB)
            setSecondaryOverrunEnabled(secondaryOverrunCB.isSelected());
        else
        if(event.getSource() == pOffsetLengthComboBox)
            updateLengthMeasure(model.getPrimaryOffsetModel(), pOffsetLengthComboBox, pOffsetLengthSpinner);
        else
        if(event.getSource() == pOffsetWidthComboBox)
            updateWidthMeasure(model.getPrimaryOffsetModel(), pOffsetWidthComboBox, pOffsetWidthSpinner);
        else
        if(event.getSource() == sOffsetLengthComboBox)
            updateLengthMeasure(model.getSecondaryOffsetModel(), sOffsetLengthComboBox, sOffsetLengthSpinner);
        else
        if(event.getSource() == sOffsetWidthComboBox)
            updateWidthMeasure(model.getSecondaryOffsetModel(), sOffsetWidthComboBox, sOffsetWidthSpinner);
        else
        if(event.getSource() == pBlastLengthComboBox)
            updateLengthMeasure(model.getPrimaryBlastPadModel(), pBlastLengthComboBox, pBlastLengthSpinner);
        else
        if(event.getSource() == pBlastWidthComboBox)
            updateWidthMeasure(model.getPrimaryBlastPadModel(), pBlastWidthComboBox, pBlastWidthSpinner);
        else
        if(event.getSource() == sBlastLengthComboBox)
            updateLengthMeasure(model.getSecondaryBlastPadModel(), sBlastLengthComboBox, sBlastLengthSpinner);
        else
        if(event.getSource() == sBlastWidthComboBox)
            updateWidthMeasure(model.getSecondaryBlastPadModel(), sBlastWidthComboBox, sBlastWidthSpinner);
        else
        if(event.getSource() == pOverrunLengthComboBox)
            updateLengthMeasure(model.getPrimaryOverrunModel(), pOverrunLengthComboBox, pOverrunLengthSpinner);
        else
        if(event.getSource() == pOverrunWidthComboBox)
            updateWidthMeasure(model.getPrimaryOverrunModel(), pOverrunWidthComboBox, pOverrunWidthSpinner);
        else
        if(event.getSource() == sOverrunLengthComboBox)
            updateLengthMeasure(model.getSecondaryOverrunModel(), sOverrunLengthComboBox, sOverrunLengthSpinner);
        else
        if(event.getSource() == sOverrunWidthComboBox)
            updateWidthMeasure(model.getSecondaryOverrunModel(), sOverrunWidthComboBox, sOverrunWidthSpinner);
        else
        if(event.getSource() == pOffsetSurfaceComboBox)
            updateSurface(model.getPrimaryOffsetModel(), pOffsetSurfaceComboBox);
        else
        if(event.getSource() == sOffsetSurfaceComboBox)
            updateSurface(model.getSecondaryOffsetModel(), sOffsetSurfaceComboBox);
        else
        if(event.getSource() == pBlastSurfaceComboBox)
            updateSurface(model.getPrimaryBlastPadModel(), pBlastSurfaceComboBox);
        else
        if(event.getSource() == sBlastSurfaceComboBox)
            updateSurface(model.getSecondaryBlastPadModel(), sBlastSurfaceComboBox);
        else
        if(event.getSource() == pOverrunSurfaceComboBox)
            updateSurface(model.getPrimaryOverrunModel(), pOverrunSurfaceComboBox);
        else
        if(event.getSource() == sOverrunSurfaceComboBox)
            updateSurface(model.getSecondaryOverrunModel(), sOverrunSurfaceComboBox);
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == pOffsetLengthSpinner && model != null && model.getPrimaryOffsetModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)pOffsetLengthSpinner.getValue()).doubleValue()));
            model.getPrimaryOffsetModel().setLength((float)((Double)pOffsetLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == pOffsetWidthSpinner && model != null && model.getPrimaryOffsetModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)pOffsetWidthSpinner.getValue()).doubleValue()));
            model.getPrimaryOffsetModel().setWidth((float)((Double)pOffsetWidthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sOffsetLengthSpinner && model != null && model.getSecondaryOffsetModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)sOffsetLengthSpinner.getValue()).doubleValue()));
            model.getSecondaryOffsetModel().setLength((float)((Double)sOffsetLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sOffsetWidthSpinner && model != null && model.getSecondaryOffsetModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)sOffsetWidthSpinner.getValue()).doubleValue()));
            model.getSecondaryOffsetModel().setWidth((float)((Double)sOffsetWidthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == pBlastLengthSpinner && model != null && model.getPrimaryBlastPadModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)pBlastLengthSpinner.getValue()).doubleValue()));
            model.getPrimaryBlastPadModel().setLength((float)((Double)pBlastLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == pBlastWidthSpinner && model != null && model.getPrimaryBlastPadModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)pBlastWidthSpinner.getValue()).doubleValue()));
            model.getPrimaryBlastPadModel().setWidth((float)((Double)pBlastWidthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sBlastLengthSpinner && model != null && model.getSecondaryBlastPadModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)sBlastLengthSpinner.getValue()).doubleValue()));
            model.getSecondaryBlastPadModel().setLength((float)((Double)sBlastLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sBlastWidthSpinner && model != null && model.getSecondaryBlastPadModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)sBlastWidthSpinner.getValue()).doubleValue()));
            model.getSecondaryBlastPadModel().setWidth((float)((Double)sBlastWidthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == pOverrunLengthSpinner && model != null && model.getPrimaryOverrunModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)pOverrunLengthSpinner.getValue()).doubleValue()));
            model.getPrimaryOverrunModel().setLength((float)((Double)pOverrunLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == pOverrunWidthSpinner && model != null && model.getPrimaryOverrunModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)pOverrunWidthSpinner.getValue()).doubleValue()));
            model.getPrimaryOverrunModel().setWidth((float)((Double)pOverrunWidthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sOverrunLengthSpinner && model != null && model.getSecondaryOverrunModel() != null)
        {
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)sOverrunLengthSpinner.getValue()).doubleValue()));
            model.getSecondaryOverrunModel().setLength((float)((Double)sOverrunLengthSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == sOverrunWidthSpinner && model != null && model.getSecondaryOverrunModel() != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)sOverrunWidthSpinner.getValue()).doubleValue()));
            model.getSecondaryOverrunModel().setWidth((float)((Double)sOverrunWidthSpinner.getValue()).doubleValue());
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model.getPrimaryOffsetModel())
        {
            if(event.getPropertyName().equals("surface"))
            {
                pOffsetSurfaceComboBox.removeActionListener(this);
                pOffsetSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                pOffsetSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                pOffsetLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                pOffsetWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                pOffsetLengthSpinner.removeChangeListener(this);
                pOffsetLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pOffsetLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                pOffsetWidthSpinner.removeChangeListener(this);
                pOffsetWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pOffsetWidthSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryOffsetModel())
        {
            if(event.getPropertyName().equals("surface"))
            {
                sOffsetSurfaceComboBox.removeActionListener(this);
                sOffsetSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                sOffsetSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                sOffsetLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                sOffsetWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                sOffsetLengthSpinner.removeChangeListener(this);
                sOffsetLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sOffsetLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                sOffsetWidthSpinner.removeChangeListener(this);
                sOffsetWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sOffsetWidthSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getPrimaryBlastPadModel())
        {
            if(event.getPropertyName().equals("surface"))
            {
                pBlastSurfaceComboBox.removeActionListener(this);
                pBlastSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                pBlastSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                pBlastLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                pBlastWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                pBlastLengthSpinner.removeChangeListener(this);
                pBlastLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pBlastLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                pBlastWidthSpinner.removeChangeListener(this);
                pBlastWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pBlastWidthSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryBlastPadModel())
        {
            if(event.getPropertyName().equals("surface"))
            {
                sBlastSurfaceComboBox.removeActionListener(this);
                sBlastSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                sBlastSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                sBlastLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                sBlastWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                sBlastLengthSpinner.removeChangeListener(this);
                sBlastLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sBlastLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                sBlastWidthSpinner.removeChangeListener(this);
                sBlastWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sBlastWidthSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getPrimaryOverrunModel())
        {
            if(event.getPropertyName().equals("surface"))
            {
                pOverrunSurfaceComboBox.removeActionListener(this);
                pOverrunSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                pOverrunSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                pOverrunLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                pOverrunWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                pOverrunLengthSpinner.removeChangeListener(this);
                pOverrunLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pOverrunLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                pOverrunWidthSpinner.removeChangeListener(this);
                pOverrunWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                pOverrunWidthSpinner.addChangeListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryOverrunModel())
            if(event.getPropertyName().equals("surface"))
            {
                sOverrunSurfaceComboBox.removeActionListener(this);
                sOverrunSurfaceComboBox.setSelectedItem((String)event.getNewValue());
                sOverrunSurfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                sOverrunLengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("widthMeasure"))
                sOverrunWidthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("length"))
            {
                sOverrunLengthSpinner.removeChangeListener(this);
                sOverrunLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sOverrunLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                sOverrunWidthSpinner.removeChangeListener(this);
                sOverrunWidthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sOverrunWidthSpinner.addChangeListener(this);
            }
    }

    private RunwayModel model;
    private Vector listeners;
    private JCheckBox primaryOffsetCB;
    private JLabel pOffsetLengthLabel;
    private JSpinner pOffsetLengthSpinner;
    private JComboBox pOffsetLengthComboBox;
    private JLabel pOffsetWidthLabel;
    private JSpinner pOffsetWidthSpinner;
    private JComboBox pOffsetWidthComboBox;
    private JLabel pOffsetSurfaceLabel;
    private JComboBox pOffsetSurfaceComboBox;
    private JCheckBox secondaryOffsetCB;
    private JLabel sOffsetLengthLabel;
    private JSpinner sOffsetLengthSpinner;
    private JComboBox sOffsetLengthComboBox;
    private JLabel sOffsetWidthLabel;
    private JSpinner sOffsetWidthSpinner;
    private JComboBox sOffsetWidthComboBox;
    private JLabel sOffsetSurfaceLabel;
    private JComboBox sOffsetSurfaceComboBox;
    private JCheckBox primaryBlastCB;
    private JLabel pBlastLengthLabel;
    private JSpinner pBlastLengthSpinner;
    private JComboBox pBlastLengthComboBox;
    private JLabel pBlastWidthLabel;
    private JSpinner pBlastWidthSpinner;
    private JComboBox pBlastWidthComboBox;
    private JLabel pBlastSurfaceLabel;
    private JComboBox pBlastSurfaceComboBox;
    private JCheckBox secondaryBlastCB;
    private JLabel sBlastLengthLabel;
    private JSpinner sBlastLengthSpinner;
    private JComboBox sBlastLengthComboBox;
    private JLabel sBlastWidthLabel;
    private JSpinner sBlastWidthSpinner;
    private JComboBox sBlastWidthComboBox;
    private JLabel sBlastSurfaceLabel;
    private JComboBox sBlastSurfaceComboBox;
    private JCheckBox primaryOverrunCB;
    private JLabel pOverrunLengthLabel;
    private JSpinner pOverrunLengthSpinner;
    private JComboBox pOverrunLengthComboBox;
    private JLabel pOverrunWidthLabel;
    private JSpinner pOverrunWidthSpinner;
    private JComboBox pOverrunWidthComboBox;
    private JLabel pOverrunSurfaceLabel;
    private JComboBox pOverrunSurfaceComboBox;
    private JCheckBox secondaryOverrunCB;
    private JLabel sOverrunLengthLabel;
    private JSpinner sOverrunLengthSpinner;
    private JComboBox sOverrunLengthComboBox;
    private JLabel sOverrunWidthLabel;
    private JSpinner sOverrunWidthSpinner;
    private JComboBox sOverrunWidthComboBox;
    private JLabel sOverrunSurfaceLabel;
    private JComboBox sOverrunSurfaceComboBox;
}
