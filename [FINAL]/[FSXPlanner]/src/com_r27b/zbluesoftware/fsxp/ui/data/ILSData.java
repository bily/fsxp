// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ILSData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class ILSData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public ILSData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        sceneryButton = new JButton(IconFactory.getInstance().getIcon("scenery"));
        sceneryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        sceneryButton.setToolTipText("Add Localizer Array");
        sceneryButton.addActionListener(this);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock ILS Feathers");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getILSLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(sceneryButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getILSLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getILSLocked())
            lonTF.setBackground(Color.red);
        altLabel = new JLabel("Altitude");
        altLabel.setFont(Utilities.LABEL_FONT);
        altLabel.setForeground(Color.black);
        altSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -5000D, 100000D, 1.0D));
        altSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(altSpinner, "0.000"));
        altSpinner.addChangeListener(this);
        altComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        altComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altComboBox.setForeground(Color.black);
        altComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            altComboBox.setPrototypeDisplayValue("--------");
        headingLabel = new JLabel("Heading");
        headingLabel.setFont(Utilities.LABEL_FONT);
        headingLabel.setForeground(Color.black);
        headingTF = new PopupTextField(5);
        headingTF.setFont(Utilities.TEXT_FIELD_FONT);
        headingTF.setForeground(Color.black);
        headingTF.addActionListener(this);
        headingTF.addFocusListener(this);
        headingSlider = new JSlider(0);
        headingSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        headingSlider.setPreferredSize(new Dimension(100, headingSlider.getPreferredSize().height));
        headingSlider.addChangeListener(this);
        JPanel headingPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(headingPanel, headingTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(headingPanel, headingSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        frequencyLabel = new JLabel("Frequency");
        frequencyLabel.setFont(Utilities.LABEL_FONT);
        frequencyLabel.setForeground(Color.black);
        frequencySpinner = new JSpinner(new SpinnerNumberModel(108D, 108D, 136.99199999999999D, 0.001D));
        frequencySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(frequencySpinner, "0.000"));
        frequencySpinner.addChangeListener(this);
        runwayLabel = new JLabel("Runway");
        runwayLabel.setFont(Utilities.LABEL_FONT);
        runwayLabel.setForeground(Color.black);
        runwayComboBox = new JComboBox(new String[] {
            "RUNWAY"
        });
        runwayComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayComboBox.setForeground(Color.black);
        runwayComboBox.addActionListener(this);
        endLabel = new JLabel("End");
        endLabel.setFont(Utilities.LABEL_FONT);
        endLabel.setForeground(Color.black);
        endComboBox = new JComboBox(new String[] {
            "PRIMARY", "SECONDARY"
        });
        endComboBox.setFont(Utilities.COMBO_BOX_FONT);
        endComboBox.setForeground(Color.black);
        endComboBox.addActionListener(this);
        rangeLabel = new JLabel("Range");
        rangeLabel.setFont(Utilities.LABEL_FONT);
        rangeLabel.setForeground(Color.black);
        rangeSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 10D));
        rangeSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(rangeSpinner, "0.000"));
        rangeSpinner.addChangeListener(this);
        rangeComboBox = new JComboBox(new String[] {
            "M", "N"
        });
        rangeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        rangeComboBox.setForeground(Color.black);
        rangeComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            rangeComboBox.setPrototypeDisplayValue("--------");
        JPanel rangePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(rangePanel, rangeSpinner, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(rangePanel, rangeComboBox, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        magvarLabel = new JLabel("Magvar");
        magvarLabel.setFont(Utilities.LABEL_FONT);
        magvarLabel.setForeground(Color.black);
        magvarSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -360D, 360D, 1.0D));
        magvarSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(magvarSpinner, "0.000"));
        magvarSpinner.addChangeListener(this);
        identLabel = new JLabel("Ident");
        identLabel.setFont(Utilities.LABEL_FONT);
        identLabel.setForeground(Color.black);
        identTF = new PopupTextField(10);
        identTF.setFont(Utilities.TEXT_FIELD_FONT);
        identTF.setForeground(Color.black);
        identTF.setDocument(new MaxLengthDocument(5));
        identTF.addFocusListener(this);
        identTF.addActionListener(this);
        widthLabel = new JLabel("Width");
        widthLabel.setFont(Utilities.LABEL_FONT);
        widthLabel.setForeground(Color.black);
        widthSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        widthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(widthSpinner, "0.000"));
        widthSpinner.addChangeListener(this);
        nameLabel = new JLabel("Name");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new PopupTextField(10);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.setDocument(new MaxLengthDocument(48));
        nameTF.addFocusListener(this);
        nameTF.addActionListener(this);
        backLabel = new JLabel("Back Course");
        backLabel.setFont(Utilities.LABEL_FONT);
        backLabel.setForeground(Color.black);
        backComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        backComboBox.setFont(Utilities.COMBO_BOX_FONT);
        backComboBox.setForeground(Color.black);
        backComboBox.addActionListener(this);
        dmeCB = new JCheckBox("DME");
        dmeCB.setFont(Utilities.LABEL_FONT);
        dmeCB.setForeground(Color.black);
        dmeCB.addActionListener(this);
        dmeLatLabel = new JLabel("Latitude");
        dmeLatLabel.setFont(Utilities.LABEL_FONT);
        dmeLatLabel.setForeground(Color.black);
        dmeLatLabel.setEnabled(false);
        dmeLatTF = new PopupTextField(20);
        dmeLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        dmeLatTF.setForeground(Color.black);
        dmeLatTF.addActionListener(this);
        dmeLatTF.addFocusListener(this);
        dmeLatTF.setEnabled(false);
        dmeLonLabel = new JLabel("Longitude");
        dmeLonLabel.setFont(Utilities.LABEL_FONT);
        dmeLonLabel.setForeground(Color.black);
        dmeLonLabel.setEnabled(false);
        dmeLonTF = new PopupTextField(20);
        dmeLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        dmeLonTF.setForeground(Color.black);
        dmeLonTF.addActionListener(this);
        dmeLonTF.addFocusListener(this);
        dmeLonTF.setEnabled(false);
        dmeAltLabel = new JLabel("Altitude");
        dmeAltLabel.setFont(Utilities.LABEL_FONT);
        dmeAltLabel.setForeground(Color.black);
        dmeAltLabel.setEnabled(false);
        dmeAltSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -5000D, 100000D, 1.0D));
        dmeAltSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(dmeAltSpinner, "0.000"));
        dmeAltSpinner.addChangeListener(this);
        dmeAltSpinner.setEnabled(false);
        dmeAltComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        dmeAltComboBox.setFont(Utilities.COMBO_BOX_FONT);
        dmeAltComboBox.setForeground(Color.black);
        dmeAltComboBox.addActionListener(this);
        dmeAltComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            dmeAltComboBox.setPrototypeDisplayValue("--------");
        glideSlopeCB = new JCheckBox("Glide Slope");
        glideSlopeCB.setFont(Utilities.LABEL_FONT);
        glideSlopeCB.setForeground(Color.black);
        glideSlopeCB.addActionListener(this);
        gsLatLabel = new JLabel("Latitude");
        gsLatLabel.setFont(Utilities.LABEL_FONT);
        gsLatLabel.setForeground(Color.black);
        gsLatLabel.setEnabled(false);
        gsLatTF = new PopupTextField(20);
        gsLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        gsLatTF.setForeground(Color.black);
        gsLatTF.addActionListener(this);
        gsLatTF.addFocusListener(this);
        gsLatTF.setEnabled(false);
        gsLonLabel = new JLabel("Longitude");
        gsLonLabel.setFont(Utilities.LABEL_FONT);
        gsLonLabel.setForeground(Color.black);
        gsLonLabel.setEnabled(false);
        gsLonTF = new PopupTextField(20);
        gsLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        gsLonTF.setForeground(Color.black);
        gsLonTF.addActionListener(this);
        gsLonTF.addFocusListener(this);
        gsLonTF.setEnabled(false);
        gsAltLabel = new JLabel("Altitude");
        gsAltLabel.setFont(Utilities.LABEL_FONT);
        gsAltLabel.setForeground(Color.black);
        gsAltLabel.setEnabled(false);
        gsAltSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -5000D, 100000D, 1.0D));
        gsAltSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(gsAltSpinner, "0.000"));
        gsAltSpinner.addChangeListener(this);
        gsAltSpinner.setEnabled(false);
        gsAltComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        gsAltComboBox.setFont(Utilities.COMBO_BOX_FONT);
        gsAltComboBox.setForeground(Color.black);
        gsAltComboBox.addActionListener(this);
        gsAltComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            gsAltComboBox.setPrototypeDisplayValue("--------");
        gsRangeLabel = new JLabel("Range");
        gsRangeLabel.setFont(Utilities.LABEL_FONT);
        gsRangeLabel.setForeground(Color.black);
        gsRangeLabel.setEnabled(false);
        gsRangeSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 10D));
        gsRangeSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(gsRangeSpinner, "0.000"));
        gsRangeSpinner.addChangeListener(this);
        gsRangeSpinner.setEnabled(false);
        gsRangeComboBox = new JComboBox(new String[] {
            "M", "N"
        });
        gsRangeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        gsRangeComboBox.setForeground(Color.black);
        gsRangeComboBox.addActionListener(this);
        gsRangeComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            gsRangeComboBox.setPrototypeDisplayValue("--------");
        gsPitchLabel = new JLabel("Pitch");
        gsPitchLabel.setFont(Utilities.LABEL_FONT);
        gsPitchLabel.setForeground(Color.black);
        gsPitchLabel.setEnabled(false);
        gsPitchSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        gsPitchSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(gsPitchSpinner, "0.000"));
        gsPitchSpinner.addChangeListener(this);
        gsPitchSpinner.setEnabled(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, identLabel, 0, 5, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, identTF, 1, 5, 2, 1, 2, 17, new Insets(5, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, runwayLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayComboBox, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, endLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, endComboBox, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, frequencyLabel, 0, 9, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, frequencySpinner, 1, 9, 2, 1, 2, 17, new Insets(5, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, rangeLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, rangePanel, 1, 10, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, magvarLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, magvarSpinner, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, widthLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, widthSpinner, 1, 12, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, backLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, backComboBox, 1, 13, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeCB, 0, 14, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLatLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLatTF, 1, 15, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLonLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLonTF, 1, 16, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltSpinner, 1, 17, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltComboBox, 2, 17, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, glideSlopeCB, 0, 18, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsLatLabel, 0, 19, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsLatTF, 1, 19, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gsLonLabel, 0, 20, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsLonTF, 1, 20, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gsAltLabel, 0, 21, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsAltSpinner, 1, 21, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gsAltComboBox, 2, 21, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsRangeLabel, 0, 22, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsRangeSpinner, 1, 22, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gsRangeComboBox, 2, 22, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsPitchLabel, 0, 23, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gsPitchSpinner, 1, 23, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 24, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("ILS Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        ILSModel model = null;
        if(baseModel instanceof ILSModel)
            model = (ILSModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        headingSlider.removeChangeListener(this);
        frequencySpinner.removeChangeListener(this);
        rangeSpinner.removeChangeListener(this);
        rangeComboBox.removeActionListener(this);
        magvarSpinner.removeChangeListener(this);
        widthSpinner.removeChangeListener(this);
        runwayComboBox.removeActionListener(this);
        endComboBox.removeActionListener(this);
        identTF.removeFocusListener(this);
        identTF.removeActionListener(this);
        nameTF.removeFocusListener(this);
        nameTF.removeActionListener(this);
        backComboBox.removeActionListener(this);
        dmeCB.removeActionListener(this);
        dmeLatTF.removeActionListener(this);
        dmeLatTF.removeFocusListener(this);
        dmeLonTF.removeActionListener(this);
        dmeLonTF.removeFocusListener(this);
        dmeAltSpinner.removeChangeListener(this);
        dmeAltComboBox.removeActionListener(this);
        glideSlopeCB.removeActionListener(this);
        gsLatTF.removeActionListener(this);
        gsLatTF.removeFocusListener(this);
        gsLonTF.removeActionListener(this);
        gsLonTF.removeFocusListener(this);
        gsAltSpinner.removeChangeListener(this);
        gsAltComboBox.removeActionListener(this);
        gsRangeSpinner.removeChangeListener(this);
        gsRangeComboBox.removeActionListener(this);
        gsPitchSpinner.removeChangeListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            frequencySpinner.setValue(new Double(model.getFrequency()));
            runwayComboBox.setSelectedItem(model.getRunway());
            endComboBox.setSelectedItem(model.getEnd());
            rangeSpinner.setValue(new Double(model.getRange()));
            rangeComboBox.setSelectedItem(model.getRangeMeasure());
            magvarSpinner.setValue(new Double(model.getMagvar()));
            identTF.setText(model.getIdent());
            widthSpinner.setValue(new Double(model.getWidth()));
            nameTF.setText(model.getName());
            backComboBox.setSelectedIndex(model.getBackCourse() ? 0 : 1);
            if(model.getDMEModel() != null)
            {
                dmeCB.setSelected(true);
                dmeLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getDMEModel().getLatLon().getLat()));
                dmeLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getDMEModel().getLatLon().getLon()));
                dmeAltSpinner.setValue(new Double(model.getDMEModel().getAlt()));
                dmeAltComboBox.setSelectedItem(model.getDMEModel().getAltMeasure());
                setDMEEnabled(true);
            } else
            {
                dmeCB.setSelected(false);
                dmeLatTF.setText("");
                dmeLonTF.setText("");
                dmeAltSpinner.setValue(new Double(0.0D));
                dmeAltComboBox.setSelectedIndex(0);
                setDMEEnabled(false);
            }
            if(model.getGlideSlopeModel() != null)
            {
                glideSlopeCB.setSelected(true);
                gsLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getGlideSlopeModel().getLatLon().getLat()));
                gsLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getGlideSlopeModel().getLatLon().getLon()));
                gsAltSpinner.setValue(new Double(model.getGlideSlopeModel().getAlt()));
                gsAltComboBox.setSelectedItem(model.getGlideSlopeModel().getAltMeasure());
                gsRangeSpinner.setValue(new Double(model.getGlideSlopeModel().getRange()));
                gsRangeComboBox.setSelectedItem(model.getGlideSlopeModel().getRangeMeasure());
                gsPitchSpinner.setValue(new Double(model.getGlideSlopeModel().getPitch()));
                setGSEnabled(true);
            } else
            {
                glideSlopeCB.setSelected(false);
                gsLatTF.setText("");
                gsLonTF.setText("");
                gsAltSpinner.setValue(new Double(0.0D));
                gsAltComboBox.setSelectedItem("M");
                gsRangeSpinner.setValue(new Double(27D));
                gsRangeComboBox.setSelectedItem("N");
                gsPitchSpinner.setValue(new Double(3D));
                setGSEnabled(false);
            }
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            headingSlider.setValue(0);
            headingTF.setText("");
            frequencySpinner.setValue(new Double(108D));
            runwayComboBox.setSelectedIndex(0);
            endComboBox.setSelectedIndex(0);
            rangeSpinner.setValue(new Double(27D));
            rangeComboBox.setSelectedItem("N");
            magvarSpinner.setValue(new Double(0.0D));
            identTF.setText("");
            widthSpinner.setValue(new Double(5D));
            nameTF.setText("");
            backComboBox.setSelectedIndex(1);
            dmeCB.setSelected(false);
            dmeLatTF.setText("");
            dmeLonTF.setText("");
            dmeAltSpinner.setValue(new Double(0.0D));
            dmeAltComboBox.setSelectedIndex(0);
            setDMEEnabled(false);
            glideSlopeCB.setSelected(false);
            gsLatTF.setText("");
            gsLonTF.setText("");
            gsAltSpinner.setValue(new Double(0.0D));
            gsAltComboBox.setSelectedItem("M");
            gsRangeSpinner.setValue(new Double(27D));
            gsRangeComboBox.setSelectedItem("N");
            gsPitchSpinner.setValue(new Double(3D));
            setGSEnabled(false);
            status = false;
        }
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        headingSlider.addChangeListener(this);
        frequencySpinner.addChangeListener(this);
        rangeSpinner.addChangeListener(this);
        rangeComboBox.addActionListener(this);
        magvarSpinner.addChangeListener(this);
        widthSpinner.addChangeListener(this);
        runwayComboBox.addActionListener(this);
        endComboBox.addActionListener(this);
        identTF.addFocusListener(this);
        identTF.addActionListener(this);
        nameTF.addFocusListener(this);
        nameTF.addActionListener(this);
        backComboBox.addActionListener(this);
        dmeCB.addActionListener(this);
        dmeLatTF.addActionListener(this);
        dmeLatTF.addFocusListener(this);
        dmeLonTF.addActionListener(this);
        dmeLonTF.addFocusListener(this);
        dmeAltSpinner.addChangeListener(this);
        dmeAltComboBox.addActionListener(this);
        glideSlopeCB.addActionListener(this);
        gsLatTF.addActionListener(this);
        gsLatTF.addFocusListener(this);
        gsLonTF.addActionListener(this);
        gsLonTF.addFocusListener(this);
        gsAltSpinner.addChangeListener(this);
        gsAltComboBox.addActionListener(this);
        gsRangeSpinner.addChangeListener(this);
        gsRangeComboBox.addActionListener(this);
        gsPitchSpinner.addChangeListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        sceneryButton.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        altLabel.setEnabled(status);
        altSpinner.setEnabled(status);
        altComboBox.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        frequencyLabel.setEnabled(status);
        frequencySpinner.setEnabled(status);
        runwayLabel.setEnabled(status);
        runwayComboBox.setEnabled(status);
        endLabel.setEnabled(status);
        endComboBox.setEnabled(status);
        rangeLabel.setEnabled(status);
        rangeSpinner.setEnabled(status);
        rangeComboBox.setEnabled(status);
        magvarLabel.setEnabled(status);
        magvarSpinner.setEnabled(status);
        identLabel.setEnabled(status);
        identTF.setEnabled(status);
        widthLabel.setEnabled(status);
        widthSpinner.setEnabled(status);
        nameLabel.setEnabled(status);
        nameTF.setEnabled(status);
        backLabel.setEnabled(status);
        backComboBox.setEnabled(status);
        dmeCB.setEnabled(status);
        glideSlopeCB.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            try
            {
                altSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                frequencySpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                magvarSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                widthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                dmeAltSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                gsAltSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                gsRangeSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                gsPitchSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
            model.setAltMeasure((String)altComboBox.getSelectedItem());
        }
    }

    public void updateRunways(ArrayList runwayAL)
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = runwayAL.size() - 1; i >= 0; i--)
        {
            RunwayModel runwayModel = (RunwayModel)runwayAL.get(i);
            String runwayName = runwayModel.getNumber();
            if(runwayModel.getDesignator().length() > 0 && !runwayModel.getDesignator().equals("NONE"))
                runwayName = (new StringBuilder()).append(runwayName).append(" ").append(runwayModel.getDesignator()).toString();
            else
            if(runwayModel.getPrimaryDesignator().length() > 0 && !runwayModel.getPrimaryDesignator().equals("NONE"))
                runwayName = runwayName = (new StringBuilder()).append(" ").append(runwayModel.getPrimaryDesignator()).toString();
            model.addElement(runwayName);
        }

        runwayComboBox.setModel(model);
    }

    private void setDMEEnabled(boolean enabled)
    {
        dmeLatLabel.setEnabled(enabled);
        dmeLatTF.setEnabled(enabled);
        dmeLonLabel.setEnabled(enabled);
        dmeLonTF.setEnabled(enabled);
        dmeAltLabel.setEnabled(enabled);
        dmeAltSpinner.setEnabled(enabled);
        dmeAltComboBox.setEnabled(enabled);
    }

    private void setGSEnabled(boolean enabled)
    {
        gsLatLabel.setEnabled(enabled);
        gsLatTF.setEnabled(enabled);
        gsLonLabel.setEnabled(enabled);
        gsLonTF.setEnabled(enabled);
        gsAltLabel.setEnabled(enabled);
        gsAltSpinner.setEnabled(enabled);
        gsAltComboBox.setEnabled(enabled);
        gsRangeLabel.setEnabled(enabled);
        gsRangeSpinner.setEnabled(enabled);
        gsRangeComboBox.setEnabled(enabled);
        gsPitchLabel.setEnabled(enabled);
        gsPitchSpinner.setEnabled(enabled);
    }

    private void updateLatitude()
    {
        if(latTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = model.getLatLon().getLat();
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(model.getLatLon().getLat()), new Double(lat));
        model.setLatLon(new LatLonPoint(lat, model.getLatLon().getLon()));
    }

    private void updateDMELatitude()
    {
        if(dmeLatTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = model.getDMEModel().getLatLon().getLat();
        dmeLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(model.getDMEModel().getLatLon().getLat()), new Double(lat));
        model.getDMEModel().setLatLon(new LatLonPoint(lat, model.getDMEModel().getLatLon().getLon()));
    }

    private void updateGSLatitude()
    {
        if(gsLatTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = model.getGlideSlopeModel().getLatLon().getLat();
        gsLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(model.getGlideSlopeModel().getLatLon().getLat()), new Double(lat));
        model.getGlideSlopeModel().setLatLon(new LatLonPoint(lat, model.getGlideSlopeModel().getLatLon().getLon()));
    }

    private void updateLongitude()
    {
        if(lonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = model.getLatLon().getLon();
        lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        firePropertyChange("update-lon", new Double(model.getLatLon().getLon()), new Double(lon));
        model.setLatLon(new LatLonPoint(model.getLatLon().getLat(), lon));
    }

    private void updateDMELongitude()
    {
        if(dmeLonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = model.getDMEModel().getLatLon().getLon();
        dmeLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        firePropertyChange("update-lon", new Double(model.getDMEModel().getLatLon().getLon()), new Double(lon));
        model.getDMEModel().setLatLon(new LatLonPoint(model.getDMEModel().getLatLon().getLat(), lon));
    }

    private void updateGSLongitude()
    {
        if(gsLonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = model.getGlideSlopeModel().getLatLon().getLon();
        gsLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        firePropertyChange("update-lon", new Double(model.getGlideSlopeModel().getLatLon().getLon()), new Double(lon));
        model.getGlideSlopeModel().setLatLon(new LatLonPoint(model.getGlideSlopeModel().getLatLon().getLat(), lon));
    }

    private void updateHeading()
    {
        if(headingTF.isPopupDisplayed())
            return;
        float heading = model.getHeading();
        try
        {
            heading = Float.parseFloat(headingTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            heading = model.getHeading();
        }
        if(heading < 0.0F)
            heading = 0.0F;
        else
        if(heading > 359F)
            heading = 359F;
        headingSlider.setValue((int)heading);
        headingTF.setText((new StringBuilder()).append("").append(heading).toString());
        firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
        model.setHeading(heading);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this ILS Beam?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    public void addLocalizerArray()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to add a Localizer Array to this ILS?", "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addLocalizerArray", model, model);
            return;
        }
    }

    private void updateAltMeasure()
    {
        if(((String)altComboBox.getSelectedItem()).equals(model.getAltMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getAltMeasure().equals("M"))
                model.setAlt(model.getAlt() * 3.28F);
            else
                model.setAlt(model.getAlt() / 3.28F);
            altSpinner.setValue(new Double(model.getAlt()));
        }
        model.setAltMeasure((String)altComboBox.getSelectedItem());
    }

    private void updateDMEAltMeasure()
    {
        if(((String)dmeAltComboBox.getSelectedItem()).equals(model.getDMEModel().getAltMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getDMEModel().getAltMeasure().equals("M"))
                model.getDMEModel().setAlt(model.getDMEModel().getAlt() * 3.28F);
            else
                model.getDMEModel().setAlt(model.getDMEModel().getAlt() / 3.28F);
            dmeAltSpinner.setValue(new Double(model.getDMEModel().getAlt()));
        }
        model.getDMEModel().setAltMeasure((String)dmeAltComboBox.getSelectedItem());
    }

    private void updateGSAltMeasure()
    {
        if(((String)gsAltComboBox.getSelectedItem()).equals(model.getGlideSlopeModel().getAltMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getGlideSlopeModel().getAltMeasure().equals("M"))
                model.getGlideSlopeModel().setAlt(model.getGlideSlopeModel().getAlt() * 3.28F);
            else
                model.getGlideSlopeModel().setAlt(model.getGlideSlopeModel().getAlt() / 3.28F);
            gsAltSpinner.setValue(new Double(model.getGlideSlopeModel().getAlt()));
        }
        model.getGlideSlopeModel().setAltMeasure((String)gsAltComboBox.getSelectedItem());
    }

    private void updateRangeMeasure()
    {
        if(((String)rangeComboBox.getSelectedItem()).equals(model.getRangeMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getRangeMeasure().equals("M"))
                model.setRange(model.getRange() / 1852F);
            else
                model.setRange(model.getRange() * 1852F);
            rangeSpinner.setValue(new Double(model.getRange()));
        }
        model.setRangeMeasure((String)rangeComboBox.getSelectedItem());
    }

    private void updateGSRangeMeasure()
    {
        if(((String)gsRangeComboBox.getSelectedItem()).equals(model.getGlideSlopeModel().getRangeMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getGlideSlopeModel().getRangeMeasure().equals("M"))
                model.getGlideSlopeModel().setRange(model.getGlideSlopeModel().getRange() / 1852F);
            else
                model.getGlideSlopeModel().setRange(model.getGlideSlopeModel().getRange() * 1852F);
            gsRangeSpinner.setValue(new Double(model.getGlideSlopeModel().getRange()));
        }
        model.getGlideSlopeModel().setRangeMeasure((String)gsRangeComboBox.getSelectedItem());
    }

    private void updateDMEModel()
    {
        dmeLatTF.removeActionListener(this);
        dmeLatTF.removeFocusListener(this);
        dmeLonTF.removeActionListener(this);
        dmeLonTF.removeFocusListener(this);
        dmeAltSpinner.removeChangeListener(this);
        dmeAltComboBox.removeActionListener(this);
        if(dmeCB.isSelected())
        {
            DMEModel dmeModel = new DMEModel();
            dmeModel.setLatLon(new LatLonPoint(model.getLatLon().getLat(), model.getLatLon().getLon()));
            dmeModel.setAlt(model.getAlt());
            dmeModel.setAltMeasure(model.getAltMeasure());
            dmeModel.setSelected(true);
            model.setDMEModel(dmeModel);
            dmeLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getDMEModel().getLatLon().getLat()));
            dmeLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getDMEModel().getLatLon().getLon()));
            dmeAltSpinner.setValue(new Double(model.getDMEModel().getAlt()));
            dmeAltComboBox.setSelectedItem(model.getDMEModel().getAltMeasure());
        } else
        {
            model.setDMEModel(null);
            dmeLatTF.setText("");
            dmeLonTF.setText("");
            dmeAltSpinner.setValue(new Double(0.0D));
            dmeAltComboBox.setSelectedIndex(0);
        }
        dmeLatTF.addActionListener(this);
        dmeLatTF.addFocusListener(this);
        dmeLonTF.addActionListener(this);
        dmeLonTF.addFocusListener(this);
        dmeAltSpinner.addChangeListener(this);
        dmeAltComboBox.addActionListener(this);
        firePropertyChange("update-dmeModel", "", "");
    }

    private void updateGSModel()
    {
        gsLatTF.removeActionListener(this);
        gsLatTF.removeFocusListener(this);
        gsLonTF.removeActionListener(this);
        gsLonTF.removeFocusListener(this);
        gsAltSpinner.removeChangeListener(this);
        gsAltComboBox.removeActionListener(this);
        gsRangeSpinner.removeChangeListener(this);
        gsRangeComboBox.removeActionListener(this);
        gsPitchSpinner.removeChangeListener(this);
        if(glideSlopeCB.isSelected())
        {
            GlideSlopeModel glideSlopeModel = new GlideSlopeModel();
            glideSlopeModel.setLatLon(new LatLonPoint(model.getLatLon().getLat() + 0.001D, model.getLatLon().getLon() + 0.001D));
            glideSlopeModel.setAlt(model.getAlt());
            glideSlopeModel.setAltMeasure(model.getAltMeasure());
            glideSlopeModel.setSelected(true);
            model.setGlideSlopeModel(glideSlopeModel);
            gsLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getGlideSlopeModel().getLatLon().getLat()));
            gsLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getGlideSlopeModel().getLatLon().getLon()));
            gsAltSpinner.setValue(new Double(model.getGlideSlopeModel().getAlt()));
            gsAltComboBox.setSelectedItem(model.getGlideSlopeModel().getAltMeasure());
            gsRangeSpinner.setValue(new Double(model.getGlideSlopeModel().getRange()));
            gsRangeComboBox.setSelectedItem(model.getGlideSlopeModel().getRangeMeasure());
            gsPitchSpinner.setValue(new Double(model.getGlideSlopeModel().getPitch()));
        } else
        {
            model.setGlideSlopeModel(null);
            gsLatTF.setText("");
            gsLonTF.setText("");
            gsAltSpinner.setValue(new Double(0.0D));
            gsAltComboBox.setSelectedItem("M");
            gsRangeSpinner.setValue(new Double(27D));
            gsRangeComboBox.setSelectedItem("N");
            gsPitchSpinner.setValue(new Double(3D));
        }
        gsLatTF.addActionListener(this);
        gsLatTF.addFocusListener(this);
        gsLonTF.addActionListener(this);
        gsLonTF.addFocusListener(this);
        gsAltSpinner.addChangeListener(this);
        gsAltComboBox.addActionListener(this);
        gsRangeSpinner.addChangeListener(this);
        gsRangeComboBox.addActionListener(this);
        gsPitchSpinner.addChangeListener(this);
        firePropertyChange("update-glideSlopeModel", "", "");
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
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setILSLocked(!LockingEngine.getInstance().getILSLocked());
        else
        if(event.getSource() == sceneryButton)
            addLocalizerArray();
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == rangeComboBox)
            updateRangeMeasure();
        else
        if(event.getSource() == runwayComboBox)
            model.setRunway((String)runwayComboBox.getSelectedItem());
        else
        if(event.getSource() == endComboBox)
            model.setEnd((String)endComboBox.getSelectedItem());
        else
        if(event.getSource() == identTF)
        {
            firePropertyChange("update-ident", model.getIdent(), identTF.getText());
            model.setIdent(identTF.getText());
        } else
        if(event.getSource() == nameTF)
            model.setName(nameTF.getText());
        else
        if(event.getSource() == backComboBox)
            model.setBackCourse(backComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == dmeCB)
        {
            setDMEEnabled(dmeCB.isSelected());
            updateDMEModel();
        } else
        if(event.getSource() == dmeLatTF)
            updateDMELatitude();
        else
        if(event.getSource() == dmeLonTF)
            updateDMELongitude();
        else
        if(event.getSource() == dmeAltComboBox)
            updateDMEAltMeasure();
        else
        if(event.getSource() == glideSlopeCB)
        {
            setGSEnabled(glideSlopeCB.isSelected());
            updateGSModel();
        } else
        if(event.getSource() == gsLatTF)
            updateGSLatitude();
        else
        if(event.getSource() == gsLonTF)
            updateGSLongitude();
        else
        if(event.getSource() == gsAltComboBox)
            updateGSAltMeasure();
        else
        if(event.getSource() == gsRangeComboBox)
            updateGSRangeMeasure();
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
        else
        if(event.getSource() == rangeSpinner && model != null)
            model.setRange(((Double)rangeSpinner.getValue()).floatValue());
        else
        if(event.getSource() == widthSpinner && model != null)
        {
            firePropertyChange("update-width", new Float(model.getWidth()), widthSpinner.getValue());
            model.setWidth(((Double)widthSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == frequencySpinner && model != null)
            model.setFrequency(((Double)frequencySpinner.getValue()).floatValue());
        else
        if(event.getSource() == magvarSpinner && model != null)
            model.setMagvar(((Double)magvarSpinner.getValue()).floatValue());
        else
        if(event.getSource() == dmeAltSpinner && model != null)
            model.getDMEModel().setAlt(((Double)dmeAltSpinner.getValue()).floatValue());
        else
        if(event.getSource() == gsAltSpinner && model != null)
            model.getGlideSlopeModel().setAlt(((Double)gsAltSpinner.getValue()).floatValue());
        else
        if(event.getSource() == gsRangeSpinner && model != null)
            model.getGlideSlopeModel().setRange(((Double)gsRangeSpinner.getValue()).floatValue());
        else
        if(event.getSource() == gsPitchSpinner && model != null)
            model.getGlideSlopeModel().setPitch(((Double)gsPitchSpinner.getValue()).floatValue());
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == identTF && !identTF.isPopupDisplayed())
        {
            firePropertyChange("update-ident", model.getIdent(), identTF.getText());
            model.setIdent(identTF.getText());
        } else
        if(event.getSource() == nameTF && !nameTF.isPopupDisplayed())
            model.setName(nameTF.getText());
        else
        if(event.getSource() == dmeLatTF)
            updateDMELatitude();
        else
        if(event.getSource() == dmeLonTF)
            updateDMELongitude();
        else
        if(event.getSource() == gsLatTF)
            updateGSLatitude();
        else
        if(event.getSource() == gsLonTF)
            updateGSLongitude();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model)
        {
            if(event.getPropertyName().equals("latLon"))
            {
                latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            } else
            if(event.getPropertyName().equals("dmeLatLon"))
            {
                dmeLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                dmeLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            } else
            if(event.getPropertyName().equals("gsLatLon"))
            {
                gsLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                gsLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            } else
            if(event.getPropertyName().equals("runway"))
            {
                runwayComboBox.removeActionListener(this);
                runwayComboBox.setSelectedItem(event.getNewValue());
                runwayComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("altMeasure"))
                altComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("rangeMeasure"))
                rangeComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("end"))
            {
                endComboBox.removeActionListener(this);
                endComboBox.setSelectedItem(event.getNewValue());
                endComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("ident"))
                identTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("name"))
                nameTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("alt"))
            {
                altSpinner.removeChangeListener(this);
                altSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                altSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("frequency"))
            {
                frequencySpinner.removeChangeListener(this);
                frequencySpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                frequencySpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("range"))
            {
                rangeSpinner.removeChangeListener(this);
                rangeSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                rangeSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("magvar"))
            {
                magvarSpinner.removeChangeListener(this);
                magvarSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                magvarSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("width"))
            {
                widthSpinner.removeChangeListener(this);
                widthSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                widthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("backCourse"))
            {
                backComboBox.removeActionListener(this);
                backComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                backComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("dmeAlt"))
            {
                dmeAltSpinner.removeChangeListener(this);
                dmeAltSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                dmeAltSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("dmeAltMeasure"))
                dmeAltComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("gsAltMeasure"))
                gsAltComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("gsRangeMeasure"))
                gsRangeComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("gsAlt"))
            {
                gsAltSpinner.removeChangeListener(this);
                gsAltSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                gsAltSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("gsRange"))
            {
                gsRangeSpinner.removeChangeListener(this);
                gsRangeSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                gsRangeSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("gsPitch"))
            {
                gsPitchSpinner.removeChangeListener(this);
                gsPitchSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                gsPitchSpinner.addChangeListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("ilsLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private ILSModel model;
    private Vector listeners;
    private JButton sceneryButton;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JSpinner frequencySpinner;
    private JComboBox runwayComboBox;
    private JComboBox endComboBox;
    private JSpinner rangeSpinner;
    private JComboBox rangeComboBox;
    private JSpinner magvarSpinner;
    private PopupTextField identTF;
    private JSpinner widthSpinner;
    private PopupTextField nameTF;
    private JComboBox backComboBox;
    private JCheckBox dmeCB;
    private JLabel dmeLatLabel;
    private PopupTextField dmeLatTF;
    private JLabel dmeLonLabel;
    private PopupTextField dmeLonTF;
    private JLabel dmeAltLabel;
    private JSpinner dmeAltSpinner;
    private JComboBox dmeAltComboBox;
    private JCheckBox glideSlopeCB;
    private JLabel gsLatLabel;
    private PopupTextField gsLatTF;
    private JLabel gsLonLabel;
    private PopupTextField gsLonTF;
    private JLabel gsAltLabel;
    private JSpinner gsAltSpinner;
    private JComboBox gsAltComboBox;
    private JLabel gsRangeLabel;
    private JSpinner gsRangeSpinner;
    private JComboBox gsRangeComboBox;
    private JLabel gsPitchLabel;
    private JSpinner gsPitchSpinner;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel headingLabel;
    private JLabel frequencyLabel;
    private JLabel runwayLabel;
    private JLabel endLabel;
    private JLabel rangeLabel;
    private JLabel magvarLabel;
    private JLabel identLabel;
    private JLabel widthLabel;
    private JLabel nameLabel;
    private JLabel backLabel;
}
