// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData, RunwayMarkingsData, RunwayDetailsData, RunwayVasiData, 
//            RunwayLightsData

public class RunwayData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public RunwayData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        ilsPrimaryButton = new JButton(IconFactory.getInstance().getIcon("ilsRunwayPrimary"));
        ilsPrimaryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        ilsPrimaryButton.setToolTipText("Add ILS to Primary End");
        ilsPrimaryButton.addActionListener(this);
        ilsSecondaryButton = new JButton(IconFactory.getInstance().getIcon("ilsRunwaySecondary"));
        ilsSecondaryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        ilsSecondaryButton.setToolTipText("Add ILS to Secondary End");
        ilsSecondaryButton.addActionListener(this);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Runways");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getRunwaysLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(ilsPrimaryButton);
        buttonPanel.add(ilsSecondaryButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(18);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getRunwaysLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(18);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getRunwaysLocked())
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
        surfaceLabel = new JLabel("Surface");
        surfaceLabel.setFont(Utilities.LABEL_FONT);
        surfaceLabel.setForeground(Color.black);
        DefaultComboBoxModel surfaceModel = new DefaultComboBoxModel();
        surfaceModel.addElement("ASPHALT");
        surfaceModel.addElement("BITUMINOUS");
        surfaceModel.addElement("BRICK");
        surfaceModel.addElement("CLAY");
        surfaceModel.addElement("CEMENT");
        surfaceModel.addElement("CONCRETE");
        surfaceModel.addElement("CORAL");
        surfaceModel.addElement("DIRT");
        surfaceModel.addElement("GRASS");
        surfaceModel.addElement("GRAVEL");
        surfaceModel.addElement("ICE");
        surfaceModel.addElement("MACADAM");
        surfaceModel.addElement("OIL_TREATED, PLANKS");
        surfaceModel.addElement("SAND");
        surfaceModel.addElement("SHALE");
        surfaceModel.addElement("SNOW");
        surfaceModel.addElement("STEEL_MATS");
        surfaceModel.addElement("TARMAC");
        surfaceModel.addElement("UNKNOWN");
        surfaceModel.addElement("WATER");
        surfaceComboBox = new JComboBox(surfaceModel);
        surfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        surfaceComboBox.setForeground(Color.black);
        surfaceComboBox.addActionListener(this);
        headingLabel = new JLabel("Heading:");
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
        lengthLabel = new JLabel("Length");
        lengthLabel.setFont(Utilities.LABEL_FONT);
        lengthLabel.setForeground(Color.black);
        lengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 100000D, 1.0D));
        lengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(lengthSpinner, "0.000"));
        lengthSpinner.addChangeListener(this);
        lengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        lengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        lengthComboBox.setForeground(Color.black);
        lengthComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            lengthComboBox.setPrototypeDisplayValue("--------");
        widthLabel = new JLabel("Width");
        widthLabel.setFont(Utilities.LABEL_FONT);
        widthLabel.setForeground(Color.black);
        widthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 100000D, 1.0D));
        widthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(widthSpinner, "0.000"));
        widthSpinner.addChangeListener(this);
        widthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        widthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        widthComboBox.setForeground(Color.black);
        widthComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            widthComboBox.setPrototypeDisplayValue("--------");
        numberLabel = new JLabel("Number");
        numberLabel.setFont(Utilities.LABEL_FONT);
        numberLabel.setForeground(Color.black);
        DefaultComboBoxModel numberModel = new DefaultComboBoxModel();
        for(int i = 0; i < 10; i++)
            numberModel.addElement((new StringBuilder()).append("0").append(i).toString());

        for(int i = 10; i < 37; i++)
            numberModel.addElement((new StringBuilder()).append("").append(i).toString());

        numberModel.addElement("EAST");
        numberModel.addElement("NORTH");
        numberModel.addElement("NORTHEAST");
        numberModel.addElement("NORTHWEST");
        numberModel.addElement("SOUTH");
        numberModel.addElement("SOUTHEAST");
        numberModel.addElement("SOUTHWEST");
        numberModel.addElement("WEST");
        numberComboBox = new JComboBox(numberModel);
        numberComboBox.setFont(Utilities.COMBO_BOX_FONT);
        numberComboBox.setForeground(Color.black);
        numberComboBox.addActionListener(this);
        designatorLabel = new JLabel("Designator");
        designatorLabel.setFont(Utilities.LABEL_FONT);
        designatorLabel.setForeground(Color.black);
        designatorComboBox = new JComboBox(getDesignatorModel());
        designatorComboBox.setFont(Utilities.COMBO_BOX_FONT);
        designatorComboBox.setForeground(Color.black);
        designatorComboBox.addActionListener(this);
        primaryDesigLabel = new JLabel("Prim. Designator");
        primaryDesigLabel.setFont(Utilities.LABEL_FONT);
        primaryDesigLabel.setForeground(Color.black);
        primaryDesigComboBox = new JComboBox(getDesignatorModel());
        primaryDesigComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryDesigComboBox.setForeground(Color.black);
        primaryDesigComboBox.addActionListener(this);
        secondaryDesigLabel = new JLabel("Sec. Designator");
        secondaryDesigLabel.setFont(Utilities.LABEL_FONT);
        secondaryDesigLabel.setForeground(Color.black);
        secondaryDesigComboBox = new JComboBox(getDesignatorModel());
        secondaryDesigComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryDesigComboBox.setForeground(Color.black);
        secondaryDesigComboBox.addActionListener(this);
        patternAltLabel = new JLabel("Pattern Altitude");
        patternAltLabel.setFont(Utilities.LABEL_FONT);
        patternAltLabel.setForeground(Color.black);
        patternAltSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        patternAltSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(patternAltSpinner, "0.000"));
        patternAltSpinner.addChangeListener(this);
        patternAltComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        patternAltComboBox.setFont(Utilities.COMBO_BOX_FONT);
        patternAltComboBox.setForeground(Color.black);
        patternAltComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            patternAltComboBox.setPrototypeDisplayValue("--------");
        primaryTOLabel = new JLabel("Prim. Takeoff");
        primaryTOLabel.setFont(Utilities.LABEL_FONT);
        primaryTOLabel.setForeground(Color.black);
        primaryTOComboBox = new JComboBox(getTrueFalseModel());
        primaryTOComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryTOComboBox.setForeground(Color.black);
        primaryTOComboBox.addActionListener(this);
        primaryLandLabel = new JLabel("Prim. Landing");
        primaryLandLabel.setFont(Utilities.LABEL_FONT);
        primaryLandLabel.setForeground(Color.black);
        primaryLandComboBox = new JComboBox(getTrueFalseModel());
        primaryLandComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryLandComboBox.setForeground(Color.black);
        primaryLandComboBox.addActionListener(this);
        primaryPatLabel = new JLabel("Prim. Pattern");
        primaryPatLabel.setFont(Utilities.LABEL_FONT);
        primaryPatLabel.setForeground(Color.black);
        primaryPatComboBox = new JComboBox(getLeftRightModel());
        primaryPatComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryPatComboBox.setForeground(Color.black);
        primaryPatComboBox.addActionListener(this);
        secondaryTOLabel = new JLabel("Sec. Takeoff");
        secondaryTOLabel.setFont(Utilities.LABEL_FONT);
        secondaryTOLabel.setForeground(Color.black);
        secondaryTOComboBox = new JComboBox(getTrueFalseModel());
        secondaryTOComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryTOComboBox.setForeground(Color.black);
        secondaryTOComboBox.addActionListener(this);
        secondaryLandLabel = new JLabel("Sec. Landing");
        secondaryLandLabel.setFont(Utilities.LABEL_FONT);
        secondaryLandLabel.setForeground(Color.black);
        secondaryLandComboBox = new JComboBox(getTrueFalseModel());
        secondaryLandComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryLandComboBox.setForeground(Color.black);
        secondaryLandComboBox.addActionListener(this);
        secondaryPatLabel = new JLabel("Sec. Pattern");
        secondaryPatLabel.setFont(Utilities.LABEL_FONT);
        secondaryPatLabel.setForeground(Color.black);
        secondaryPatComboBox = new JComboBox(getLeftRightModel());
        secondaryPatComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryPatComboBox.setForeground(Color.black);
        secondaryPatComboBox.addActionListener(this);
        primaryMarkingLabel = new JLabel("Prim. Marking Bias");
        primaryMarkingLabel.setFont(Utilities.LABEL_FONT);
        primaryMarkingLabel.setForeground(Color.black);
        primaryMarkingTF = new PopupTextField(10);
        primaryMarkingTF.setFont(Utilities.TEXT_FIELD_FONT);
        primaryMarkingTF.setForeground(Color.black);
        primaryMarkingTF.addActionListener(this);
        primaryMarkingTF.addFocusListener(this);
        primaryMarkingComboBox = new JComboBox(new String[] {
            "M", "F", "N"
        });
        primaryMarkingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        primaryMarkingComboBox.setForeground(Color.black);
        primaryMarkingComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            primaryMarkingComboBox.setPrototypeDisplayValue("--------");
        secondaryMarkingLabel = new JLabel("Sec. Marking Bias");
        secondaryMarkingLabel.setFont(Utilities.LABEL_FONT);
        secondaryMarkingLabel.setForeground(Color.black);
        secondaryMarkingTF = new PopupTextField(10);
        secondaryMarkingTF.setFont(Utilities.TEXT_FIELD_FONT);
        secondaryMarkingTF.setForeground(Color.black);
        secondaryMarkingTF.addActionListener(this);
        secondaryMarkingTF.addFocusListener(this);
        secondaryMarkingComboBox = new JComboBox(new String[] {
            "M", "F", "N"
        });
        secondaryMarkingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        secondaryMarkingComboBox.setForeground(Color.black);
        secondaryMarkingComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            secondaryMarkingComboBox.setPrototypeDisplayValue("--------");
        JPanel generalPanel = new JPanel(new GridBagLayout());
        generalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(generalPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, latLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, latTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, lonLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, lonTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, headingLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, headingPanel, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, lengthLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, lengthSpinner, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, lengthComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, widthLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, widthSpinner, 1, 5, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, widthComboBox, 2, 5, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, altLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, altSpinner, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, altComboBox, 2, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, surfaceLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, surfaceComboBox, 1, 7, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, numberLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, numberComboBox, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, designatorLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, designatorComboBox, 1, 9, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, primaryDesigLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryDesigComboBox, 1, 10, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryDesigLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryDesigComboBox, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, patternAltLabel, 0, 12, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, patternAltSpinner, 1, 12, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, patternAltComboBox, 2, 12, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryTOLabel, 0, 13, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryTOComboBox, 1, 13, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, primaryLandLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryLandComboBox, 1, 14, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, primaryPatLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryPatComboBox, 1, 15, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryTOLabel, 0, 16, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryTOComboBox, 1, 16, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryLandLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryLandComboBox, 1, 17, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryPatLabel, 0, 18, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryPatComboBox, 1, 18, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, primaryMarkingLabel, 0, 19, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, primaryMarkingTF, 1, 19, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, primaryMarkingComboBox, 2, 19, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryMarkingLabel, 0, 20, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryMarkingTF, 1, 20, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(generalPanel, secondaryMarkingComboBox, 2, 20, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(generalPanel, Box.createGlue(), 0, 21, 3, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane generalSP = new JScrollPane(generalPanel);
        markingsPanel = new RunwayMarkingsData();
        markingsPanel.addPropertyChangeListener(this);
        JScrollPane markingsSP = new JScrollPane(markingsPanel);
        detailsPanel = new RunwayDetailsData();
        detailsPanel.addPropertyChangeListener(this);
        JScrollPane detailsSP = new JScrollPane(detailsPanel);
        vasiPanel = new RunwayVasiData();
        vasiPanel.addPropertyChangeListener(this);
        JScrollPane vasiSP = new JScrollPane(vasiPanel);
        lightsPanel = new RunwayLightsData();
        lightsPanel.addPropertyChangeListener(this);
        JScrollPane lightsSP = new JScrollPane(lightsPanel);
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(Utilities.LABEL_FONT);
        tabbedPane.setForeground(Color.black);
        tabbedPane.add("General", generalSP);
        tabbedPane.add("Markings", markingsSP);
        tabbedPane.add("Details", detailsSP);
        tabbedPane.add("VASI", vasiSP);
        tabbedPane.add("Lights", lightsSP);
        add(tabbedPane, "Center");
        windowBorder = new WindowBorder("Runway Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
        updateDisplay(null);
    }

    private DefaultComboBoxModel getDesignatorModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("-----");
        model.addElement("NONE");
        model.addElement("C");
        model.addElement("CENTER");
        model.addElement("L");
        model.addElement("LEFT");
        model.addElement("R");
        model.addElement("RIGHT");
        model.addElement("W");
        model.addElement("WATER");
        model.addElement("A");
        model.addElement("B");
        return model;
    }

    private DefaultComboBoxModel getLeftRightModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("LEFT");
        model.addElement("RIGHT");
        return model;
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    public RunwayModel getRunwayModel()
    {
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        RunwayModel model = null;
        if(baseModel instanceof RunwayModel)
            model = (RunwayModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        headingSlider.removeChangeListener(this);
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        lengthComboBox.removeActionListener(this);
        lengthSpinner.removeChangeListener(this);
        widthComboBox.removeActionListener(this);
        widthSpinner.removeChangeListener(this);
        surfaceComboBox.removeActionListener(this);
        latTF.removeActionListener(this);
        lonTF.removeActionListener(this);
        headingTF.removeActionListener(this);
        patternAltComboBox.removeActionListener(this);
        numberComboBox.removeActionListener(this);
        designatorComboBox.removeActionListener(this);
        primaryDesigComboBox.removeActionListener(this);
        secondaryDesigComboBox.removeActionListener(this);
        primaryTOComboBox.removeActionListener(this);
        primaryLandComboBox.removeActionListener(this);
        primaryPatComboBox.removeActionListener(this);
        secondaryTOComboBox.removeActionListener(this);
        secondaryLandComboBox.removeActionListener(this);
        secondaryPatComboBox.removeActionListener(this);
        primaryMarkingTF.removeActionListener(this);
        primaryMarkingComboBox.removeActionListener(this);
        secondaryMarkingTF.removeActionListener(this);
        secondaryMarkingComboBox.removeActionListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            surfaceComboBox.setSelectedItem(model.getSurface());
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            lengthSpinner.setValue(new Double(model.getLength()));
            lengthComboBox.setSelectedItem(model.getLengthMeasure());
            widthSpinner.setValue(new Double(model.getWidth()));
            widthComboBox.setSelectedItem(model.getWidthMeasure());
            numberComboBox.setSelectedItem(model.getNumber());
            if(model.getDesignator().length() == 0)
                designatorComboBox.setSelectedIndex(0);
            else
                designatorComboBox.setSelectedItem(model.getDesignator());
            if(model.getPrimaryDesignator().length() == 0)
                primaryDesigComboBox.setSelectedIndex(0);
            else
                primaryDesigComboBox.setSelectedItem(model.getPrimaryDesignator());
            if(model.getSecondaryDesignator().length() == 0)
                secondaryDesigComboBox.setSelectedIndex(0);
            else
                secondaryDesigComboBox.setSelectedItem(model.getSecondaryDesignator());
            patternAltSpinner.setValue(new Double(model.getPatternAltitude()));
            patternAltComboBox.setSelectedItem(model.getPatternAltitudeMeasure());
            primaryTOComboBox.setSelectedIndex(model.isPrimaryTakeoff() ? 0 : 1);
            primaryLandComboBox.setSelectedIndex(model.isPrimaryLanding() ? 0 : 1);
            primaryPatComboBox.setSelectedItem(model.getPrimaryPattern());
            secondaryTOComboBox.setSelectedIndex(model.isSecondaryTakeoff() ? 0 : 1);
            secondaryLandComboBox.setSelectedIndex(model.isSecondaryLanding() ? 0 : 1);
            secondaryPatComboBox.setSelectedItem(model.getSecondaryPattern());
            primaryMarkingTF.setText((new StringBuilder()).append("").append(model.getPrimaryMarkingBias()).toString());
            primaryMarkingComboBox.setSelectedItem(model.getPrimaryMarkingBiasMeasure());
            secondaryMarkingTF.setText((new StringBuilder()).append("").append(model.getSecondaryMarkingBias()).toString());
            secondaryMarkingComboBox.setSelectedItem(model.getSecondaryMarkingBiasMeasure());
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            surfaceComboBox.setSelectedIndex(0);
            headingSlider.setValue(0);
            headingTF.setText("");
            lengthSpinner.setValue(new Double(0.0D));
            lengthComboBox.setSelectedIndex(0);
            widthSpinner.setValue(new Double(0.0D));
            widthComboBox.setSelectedIndex(0);
            numberComboBox.setSelectedIndex(0);
            designatorComboBox.setSelectedIndex(0);
            primaryDesigComboBox.setSelectedIndex(0);
            secondaryDesigComboBox.setSelectedIndex(0);
            patternAltSpinner.setValue(new Double(0.0D));
            patternAltComboBox.setSelectedIndex(0);
            primaryTOComboBox.setSelectedIndex(0);
            primaryLandComboBox.setSelectedIndex(0);
            primaryPatComboBox.setSelectedIndex(0);
            secondaryTOComboBox.setSelectedIndex(0);
            secondaryLandComboBox.setSelectedIndex(0);
            secondaryPatComboBox.setSelectedIndex(0);
            primaryMarkingTF.setText("");
            primaryMarkingComboBox.setSelectedIndex(0);
            secondaryMarkingTF.setText("");
            secondaryMarkingComboBox.setSelectedIndex(0);
            status = false;
        }
        markingsPanel.updateDisplay(model);
        detailsPanel.updateDisplay(model);
        vasiPanel.updateDisplay(model);
        lightsPanel.updateDisplay(model);
        headingSlider.addChangeListener(this);
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        lengthComboBox.addActionListener(this);
        lengthSpinner.addChangeListener(this);
        widthComboBox.addActionListener(this);
        widthSpinner.addChangeListener(this);
        surfaceComboBox.addActionListener(this);
        latTF.addActionListener(this);
        lonTF.addActionListener(this);
        headingTF.addActionListener(this);
        patternAltComboBox.addActionListener(this);
        numberComboBox.addActionListener(this);
        designatorComboBox.addActionListener(this);
        primaryDesigComboBox.addActionListener(this);
        secondaryDesigComboBox.addActionListener(this);
        primaryTOComboBox.addActionListener(this);
        primaryLandComboBox.addActionListener(this);
        primaryPatComboBox.addActionListener(this);
        secondaryTOComboBox.addActionListener(this);
        secondaryLandComboBox.addActionListener(this);
        secondaryPatComboBox.addActionListener(this);
        primaryMarkingTF.addActionListener(this);
        primaryMarkingComboBox.addActionListener(this);
        secondaryMarkingTF.addActionListener(this);
        secondaryMarkingComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        ilsPrimaryButton.setEnabled(status);
        ilsSecondaryButton.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        altLabel.setEnabled(status);
        altSpinner.setEnabled(status);
        altComboBox.setEnabled(status);
        surfaceLabel.setEnabled(status);
        surfaceComboBox.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        lengthLabel.setEnabled(status);
        lengthSpinner.setEnabled(status);
        lengthComboBox.setEnabled(status);
        widthLabel.setEnabled(status);
        widthSpinner.setEnabled(status);
        widthComboBox.setEnabled(status);
        numberLabel.setEnabled(status);
        numberComboBox.setEnabled(status);
        designatorLabel.setEnabled(status);
        designatorComboBox.setEnabled(status);
        primaryDesigLabel.setEnabled(status);
        primaryDesigComboBox.setEnabled(status);
        secondaryDesigLabel.setEnabled(status);
        secondaryDesigComboBox.setEnabled(status);
        patternAltLabel.setEnabled(status);
        patternAltSpinner.setEnabled(status);
        patternAltComboBox.setEnabled(status);
        primaryTOLabel.setEnabled(status);
        primaryTOComboBox.setEnabled(status);
        primaryLandLabel.setEnabled(status);
        primaryLandComboBox.setEnabled(status);
        primaryPatLabel.setEnabled(status);
        primaryPatComboBox.setEnabled(status);
        secondaryTOLabel.setEnabled(status);
        secondaryTOComboBox.setEnabled(status);
        secondaryLandLabel.setEnabled(status);
        secondaryLandComboBox.setEnabled(status);
        secondaryPatLabel.setEnabled(status);
        secondaryPatComboBox.setEnabled(status);
        primaryMarkingLabel.setEnabled(status);
        primaryMarkingTF.setEnabled(status);
        primaryMarkingComboBox.setEnabled(status);
        secondaryMarkingLabel.setEnabled(status);
        secondaryMarkingTF.setEnabled(status);
        secondaryMarkingComboBox.setEnabled(status);
        tabbedPane.setEnabled(status);
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
                lengthSpinner.commitEdit();
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
                patternAltSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
            model.setAltMeasure((String)altComboBox.getSelectedItem());
            model.setSurface((String)surfaceComboBox.getSelectedItem());
            model.setNumber((String)numberComboBox.getSelectedItem());
            if(designatorComboBox.getSelectedIndex() == 0)
                model.setDesignator("");
            else
                model.setDesignator((String)designatorComboBox.getSelectedItem());
            if(primaryDesigComboBox.getSelectedIndex() == 0)
                model.setPrimaryDesignator("");
            else
                model.setPrimaryDesignator((String)primaryDesigComboBox.getSelectedItem());
            if(secondaryDesigComboBox.getSelectedIndex() == 0)
                model.setSecondaryDesignator("");
            else
                model.setSecondaryDesignator((String)secondaryDesigComboBox.getSelectedItem());
            model.setPatternAltitude(((Double)patternAltSpinner.getValue()).floatValue());
            model.setPatternAltitudeMeasure((String)patternAltComboBox.getSelectedItem());
            model.setPrimaryTakeoff(primaryTOComboBox.getSelectedIndex() == 0);
            model.setPrimaryLanding(primaryLandComboBox.getSelectedIndex() == 0);
            model.setPrimaryPattern((String)primaryPatComboBox.getSelectedItem());
            model.setSecondaryTakeoff(secondaryTOComboBox.getSelectedIndex() == 0);
            model.setSecondaryLanding(secondaryLandComboBox.getSelectedIndex() == 0);
            model.setSecondaryPattern((String)secondaryPatComboBox.getSelectedItem());
            try
            {
                model.setPrimaryMarkingBias(Double.parseDouble(primaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
            model.setPrimaryMarkingBiasMeasure((String)primaryMarkingComboBox.getSelectedItem());
            try
            {
                model.setSecondaryMarkingBias(Double.parseDouble(secondaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
            model.setSecondaryMarkingBiasMeasure((String)secondaryMarkingComboBox.getSelectedItem());
            detailsPanel.updateModel();
        }
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

    private void updateLengthMeasure()
    {
        if(((String)lengthComboBox.getSelectedItem()).equals(model.getLengthMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getLengthMeasure().equals("M"))
                model.setLength(model.getLength() * 3.28F);
            else
                model.setLength(model.getLength() / 3.28F);
            lengthSpinner.setValue(new Double(model.getLength()));
        }
        firePropertyChange("update-LengthMeasure", model.getLengthMeasure(), (String)lengthComboBox.getSelectedItem());
        model.setLengthMeasure((String)lengthComboBox.getSelectedItem());
    }

    private void updateWidthMeasure()
    {
        if(((String)widthComboBox.getSelectedItem()).equals(model.getWidthMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getWidthMeasure().equals("M"))
                model.setWidth(model.getWidth() * 3.28F);
            else
                model.setWidth(model.getWidth() / 3.28F);
            widthSpinner.setValue(new Double(model.getWidth()));
        }
        firePropertyChange("update-widthMeasure", model.getWidthMeasure(), (String)widthComboBox.getSelectedItem());
        model.setWidthMeasure((String)widthComboBox.getSelectedItem());
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Runway?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    public void addILSBeam(boolean primaryEnd)
    {
        String text = (new StringBuilder()).append("Are you sure you want to add an ILS Beam to the ").append(primaryEnd ? "Primary" : "Secondary").append(" end of this runway?").toString();
        if(JOptionPane.showConfirmDialog(this, text, "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addILS", new Boolean(primaryEnd), new Boolean(primaryEnd));
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

    private void updatePatternAltMeasure()
    {
        if(((String)patternAltComboBox.getSelectedItem()).equals(model.getPatternAltitudeMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getPatternAltitudeMeasure().equals("M"))
                model.setPatternAltitude(model.getPatternAltitude() * 3.28F);
            else
                model.setPatternAltitude(model.getPatternAltitude() / 3.28F);
            patternAltSpinner.setValue(new Double(model.getPatternAltitude()));
        }
        model.setPatternAltitudeMeasure((String)patternAltComboBox.getSelectedItem());
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
        if(event.getSource() == lengthComboBox && model != null)
            updateLengthMeasure();
        else
        if(event.getSource() == widthComboBox && model != null)
            updateWidthMeasure();
        else
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setRunwaysLocked(!LockingEngine.getInstance().getRunwaysLocked());
        else
        if(event.getSource() == ilsPrimaryButton)
            addILSBeam(true);
        else
        if(event.getSource() == ilsSecondaryButton)
            addILSBeam(false);
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == surfaceComboBox)
        {
            firePropertyChange("update-surface", model.getSurface(), (String)surfaceComboBox.getSelectedItem());
            model.setSurface((String)surfaceComboBox.getSelectedItem());
        } else
        if(event.getSource() == patternAltComboBox)
            updatePatternAltMeasure();
        else
        if(event.getSource() == numberComboBox)
        {
            firePropertyChange("update-number", model.getNumber(), (String)numberComboBox.getSelectedItem());
            model.setNumber((String)numberComboBox.getSelectedItem());
        } else
        if(event.getSource() == designatorComboBox)
        {
            firePropertyChange("update-designator", model.getDesignator(), designatorComboBox.getSelectedItem());
            if(designatorComboBox.getSelectedIndex() == 0)
                model.setDesignator("");
            else
                model.setDesignator((String)designatorComboBox.getSelectedItem());
        } else
        if(event.getSource() == primaryDesigComboBox)
        {
            firePropertyChange("update-primaryDesignator", model.getPrimaryDesignator(), primaryDesigComboBox.getSelectedItem());
            if(primaryDesigComboBox.getSelectedIndex() == 0)
                model.setPrimaryDesignator("");
            else
                model.setPrimaryDesignator((String)primaryDesigComboBox.getSelectedItem());
        } else
        if(event.getSource() == secondaryDesigComboBox)
        {
            firePropertyChange("update-secondaryDesignator", model.getSecondaryDesignator(), secondaryDesigComboBox.getSelectedItem());
            if(secondaryDesigComboBox.getSelectedIndex() == 0)
                model.setSecondaryDesignator("");
            else
                model.setSecondaryDesignator((String)secondaryDesigComboBox.getSelectedItem());
        } else
        if(event.getSource() == primaryTOComboBox)
            model.setPrimaryTakeoff(primaryTOComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == primaryLandComboBox)
            model.setPrimaryLanding(primaryLandComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == primaryPatComboBox)
            model.setPrimaryPattern((String)primaryPatComboBox.getSelectedItem());
        else
        if(event.getSource() == secondaryTOComboBox)
            model.setSecondaryTakeoff(secondaryTOComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == secondaryLandComboBox)
            model.setSecondaryLanding(secondaryLandComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == secondaryPatComboBox)
            model.setSecondaryPattern((String)secondaryPatComboBox.getSelectedItem());
        else
        if(event.getSource() == primaryMarkingTF)
            try
            {
                model.setPrimaryMarkingBias(Double.parseDouble(primaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
        else
        if(event.getSource() == primaryMarkingComboBox)
            model.setPrimaryMarkingBiasMeasure((String)primaryMarkingComboBox.getSelectedItem());
        else
        if(event.getSource() == secondaryMarkingTF)
            try
            {
                model.setSecondaryMarkingBias(Double.parseDouble(secondaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
        else
        if(event.getSource() == secondaryMarkingComboBox)
            model.setSecondaryMarkingBiasMeasure((String)secondaryMarkingComboBox.getSelectedItem());
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == lengthSpinner && model != null)
        {
            firePropertyChange("update-length", new Double(model.getLength()), (Double)lengthSpinner.getValue());
            model.setLength(((Double)lengthSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == widthSpinner && model != null)
        {
            firePropertyChange("update-width", new Double(model.getWidth()), (Double)widthSpinner.getValue());
            model.setWidth(((Double)widthSpinner.getValue()).floatValue());
        } else
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
        else
        if(event.getSource() == patternAltSpinner && model != null)
            model.setPatternAltitude(((Double)patternAltSpinner.getValue()).floatValue());
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
        if(event.getSource() == primaryMarkingTF && !primaryMarkingTF.isPopupDisplayed())
            try
            {
                model.setPrimaryMarkingBias(Double.parseDouble(primaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
        else
        if(event.getSource() == secondaryMarkingTF && !secondaryMarkingTF.isPopupDisplayed())
            try
            {
                model.setSecondaryMarkingBias(Double.parseDouble(secondaryMarkingTF.getText()));
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
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
            if(event.getPropertyName().equals("alt"))
            {
                altSpinner.removeChangeListener(this);
                altSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                altSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("altMeasure"))
                altComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("surface"))
            {
                surfaceComboBox.removeActionListener(this);
                surfaceComboBox.setSelectedItem(event.getNewValue());
                surfaceComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("length"))
            {
                lengthSpinner.removeChangeListener(this);
                lengthSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                lengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("lengthMeasure"))
                lengthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("width"))
            {
                widthSpinner.removeChangeListener(this);
                widthSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                widthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("widthMeasure"))
                widthComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("number"))
            {
                numberComboBox.removeActionListener(this);
                numberComboBox.setSelectedItem(event.getNewValue());
                numberComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("designator"))
            {
                designatorComboBox.removeActionListener(this);
                designatorComboBox.setSelectedItem(event.getNewValue());
                designatorComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("primaryDesignator"))
            {
                primaryDesigComboBox.removeActionListener(this);
                if(((String)event.getNewValue()).length() == 0)
                    primaryDesigComboBox.setSelectedIndex(0);
                else
                    primaryDesigComboBox.setSelectedItem(event.getNewValue());
                primaryDesigComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryDesignator"))
            {
                secondaryDesigComboBox.removeActionListener(this);
                if(((String)event.getNewValue()).length() == 0)
                    secondaryDesigComboBox.setSelectedIndex(0);
                else
                    secondaryDesigComboBox.setSelectedItem(event.getNewValue());
                secondaryDesigComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("patternAltitude"))
            {
                patternAltSpinner.removeChangeListener(this);
                patternAltSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                patternAltSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("patternAltitudeMeasure"))
                patternAltComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("primaryPattern"))
            {
                primaryPatComboBox.removeActionListener(this);
                primaryPatComboBox.setSelectedItem(event.getNewValue());
                primaryPatComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryPattern"))
            {
                secondaryPatComboBox.removeActionListener(this);
                secondaryPatComboBox.setSelectedItem(event.getNewValue());
                secondaryPatComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("primaryMarkingBias"))
                primaryMarkingTF.setText((new StringBuilder()).append("").append(((Double)event.getNewValue()).floatValue()).toString());
            else
            if(event.getPropertyName().equals("primaryMarkingBiasMeasure"))
                primaryMarkingComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("secondaryMarkingBias"))
                secondaryMarkingTF.setText((new StringBuilder()).append("").append(((Double)event.getNewValue()).floatValue()).toString());
            else
            if(event.getPropertyName().equals("secondaryMarkingBiasMeasure"))
                secondaryMarkingComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("primaryTakeoff"))
            {
                primaryTOComboBox.removeActionListener(this);
                primaryTOComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                primaryTOComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("primaryLanding"))
            {
                primaryLandComboBox.removeActionListener(this);
                primaryLandComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                primaryLandComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryTakeoff"))
            {
                secondaryTOComboBox.removeActionListener(this);
                secondaryTOComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                secondaryTOComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("secondaryLanding"))
            {
                secondaryLandComboBox.removeActionListener(this);
                secondaryLandComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                secondaryLandComboBox.addActionListener(this);
            }
        } else
        if(event.getSource() == detailsPanel)
            firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
        else
        if(event.getSource() == vasiPanel)
            firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
        else
        if(event.getSource() == markingsPanel)
            firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
        else
        if(event.getSource() == lightsPanel)
            firePropertyChange(event.getPropertyName(), event.getOldValue(), event.getNewValue());
        else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("runwaysLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private RunwayModel model;
    private Vector listeners;
    private JButton deleteButton;
    private JButton lockingButton;
    private JButton ilsPrimaryButton;
    private JButton ilsSecondaryButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JComboBox surfaceComboBox;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JSpinner lengthSpinner;
    private JComboBox lengthComboBox;
    private JSpinner widthSpinner;
    private JComboBox widthComboBox;
    private JComboBox numberComboBox;
    private JComboBox designatorComboBox;
    private JComboBox primaryDesigComboBox;
    private JComboBox secondaryDesigComboBox;
    private JSpinner patternAltSpinner;
    private JComboBox patternAltComboBox;
    private JComboBox primaryTOComboBox;
    private JComboBox primaryLandComboBox;
    private JComboBox primaryPatComboBox;
    private JComboBox secondaryTOComboBox;
    private JComboBox secondaryLandComboBox;
    private JComboBox secondaryPatComboBox;
    private PopupTextField primaryMarkingTF;
    private JComboBox primaryMarkingComboBox;
    private PopupTextField secondaryMarkingTF;
    private JComboBox secondaryMarkingComboBox;
    private RunwayMarkingsData markingsPanel;
    private RunwayDetailsData detailsPanel;
    private RunwayVasiData vasiPanel;
    private RunwayLightsData lightsPanel;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel surfaceLabel;
    private JLabel headingLabel;
    private JLabel lengthLabel;
    private JLabel widthLabel;
    private JLabel numberLabel;
    private JLabel designatorLabel;
    private JLabel primaryDesigLabel;
    private JLabel secondaryDesigLabel;
    private JLabel patternAltLabel;
    private JLabel primaryTOLabel;
    private JLabel primaryLandLabel;
    private JLabel primaryPatLabel;
    private JLabel secondaryTOLabel;
    private JLabel secondaryLandLabel;
    private JLabel secondaryPatLabel;
    private JLabel primaryMarkingLabel;
    private JLabel secondaryMarkingLabel;
    private JTabbedPane tabbedPane;
}
