// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayParkingData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.dialog.AirlineCodeDialog;
import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
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
//            ObjectData

public class TaxiwayParkingData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public TaxiwayParkingData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        sceneryButton = new JButton(IconFactory.getInstance().getIcon("scenery"));
        sceneryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        sceneryButton.setToolTipText("Add Fuel Station");
        sceneryButton.addActionListener(this);
        triggerButton = new JButton(IconFactory.getInstance().getIcon("fuel"));
        triggerButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        triggerButton.setToolTipText("Add Fuel Trigger");
        triggerButton.addActionListener(this);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Parking Locations");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getParkingLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(sceneryButton);
        buttonPanel.add(triggerButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        indexLabel = new JLabel("Index");
        indexLabel.setFont(Utilities.LABEL_FONT);
        indexLabel.setForeground(Color.black);
        indexTF = new JTextField(20);
        indexTF.setFont(Utilities.TEXT_FIELD_FONT);
        indexTF.setForeground(Color.gray);
        indexTF.setEditable(false);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getParkingLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getParkingLocked())
            lonTF.setBackground(Color.red);
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
        radiusLabel = new JLabel("Radius");
        radiusLabel.setFont(Utilities.LABEL_FONT);
        radiusLabel.setForeground(Color.black);
        radiusSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        radiusSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(radiusSpinner, "0.000"));
        radiusSpinner.addChangeListener(this);
        radiusComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        radiusComboBox.setFont(Utilities.COMBO_BOX_FONT);
        radiusComboBox.setForeground(Color.black);
        radiusComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            radiusComboBox.setPrototypeDisplayValue("--------");
        JPanel radiusPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(radiusPanel, radiusSpinner, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(radiusPanel, radiusComboBox, 1, 0, 1, 1, 0, 13, new Insets(1, 5, 1, 1), 0, 0, 0.0D, 0.0D);
        typeLabel = new JLabel("Type");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("NONE");
        typeModel.addElement("DOCK_GA");
        typeModel.addElement("FUEL");
        typeModel.addElement("GATE_HEAVY");
        typeModel.addElement("GATE_MEDIUM");
        typeModel.addElement("GATE_SMALL");
        typeModel.addElement("RAMP_CARGO");
        typeModel.addElement("RAMP_GA");
        typeModel.addElement("RAMP_GA_LARGE");
        typeModel.addElement("RAMP_GA_MEDIUM");
        typeModel.addElement("RAMP_GA_SMALL");
        typeModel.addElement("RAMP_MIL_CARGO");
        typeModel.addElement("RAMP_MIL_COMBAT");
        typeModel.addElement("VEHICLE");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        typeComboBox.addActionListener(this);
        nameLabel = new JLabel("Name");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        DefaultComboBoxModel nameModel = new DefaultComboBoxModel();
        nameModel.addElement("PARKING");
        nameModel.addElement("DOCK");
        nameModel.addElement("GATE");
        for(int i = 65; i < 91; i++)
            nameModel.addElement((new StringBuilder()).append("GATE_").append(Character.toString((char)i)).toString());

        nameModel.addElement("NONE");
        nameModel.addElement("N_PARKING");
        nameModel.addElement("NE_PARKING");
        nameModel.addElement("NW_PARKING");
        nameModel.addElement("SE_PARKING");
        nameModel.addElement("S_PARKING");
        nameModel.addElement("SW_PARKING");
        nameModel.addElement("W_PARKING");
        nameModel.addElement("E_PARKING");
        nameComboBox = new JComboBox(nameModel);
        nameComboBox.setFont(Utilities.COMBO_BOX_FONT);
        nameComboBox.setForeground(Color.black);
        nameComboBox.addActionListener(this);
        numberLabel = new JLabel("Number");
        numberLabel.setFont(Utilities.LABEL_FONT);
        numberLabel.setForeground(Color.black);
        numberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3999, 1));
        numberSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(numberSpinner, "0"));
        numberSpinner.addChangeListener(this);
        codeLabel = new JLabel("Airline Code");
        codeLabel.setFont(Utilities.LABEL_FONT);
        codeLabel.setForeground(Color.black);
        codeTF = new PopupTextField(10);
        codeTF.setFont(Utilities.TEXT_FIELD_FONT);
        codeTF.setForeground(Color.black);
        codeTF.addActionListener(this);
        codeTF.addFocusListener(this);
        codeButton = new JButton("Select");
        codeButton.setFont(Utilities.BUTTON_FONT);
        codeButton.setForeground(Color.black);
        codeButton.addActionListener(this);
        JPanel codePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(codePanel, codeTF, 0, 0, 1, 1, 2, 17, new Insets(0, 0, 0, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(codePanel, codeButton, 1, 0, 1, 1, 0, 17, new Insets(0, 1, 0, 0), 0, 0, 0.0D, 0.0D);
        pushLabel = new JLabel("Push Back");
        pushLabel.setFont(Utilities.LABEL_FONT);
        pushLabel.setForeground(Color.black);
        DefaultComboBoxModel pushModel = new DefaultComboBoxModel();
        pushModel.addElement("NONE");
        pushModel.addElement("LEFT");
        pushModel.addElement("RIGHT");
        pushModel.addElement("BOTH");
        pushComboBox = new JComboBox(pushModel);
        pushComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pushComboBox.setForeground(Color.black);
        pushComboBox.addActionListener(this);
        tee1Label = new JLabel("Tee Offset 1");
        tee1Label.setFont(Utilities.LABEL_FONT);
        tee1Label.setForeground(Color.black);
        tee1Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee1Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee1Spinner, "00.00000"));
        tee1Spinner.addChangeListener(this);
        tee2Label = new JLabel("Tee Offset 2");
        tee2Label.setFont(Utilities.LABEL_FONT);
        tee2Label.setForeground(Color.black);
        tee2Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee2Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee2Spinner, "00.00000"));
        tee2Spinner.addChangeListener(this);
        tee3Label = new JLabel("Tee Offset 3");
        tee3Label.setFont(Utilities.LABEL_FONT);
        tee3Label.setForeground(Color.black);
        tee3Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee3Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee3Spinner, "00.00000"));
        tee3Spinner.addChangeListener(this);
        tee4Label = new JLabel("Tee Offset 4");
        tee4Label.setFont(Utilities.LABEL_FONT);
        tee4Label.setForeground(Color.black);
        tee4Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee4Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee4Spinner, "00.00000"));
        tee4Spinner.addChangeListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, indexTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, radiusLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, radiusPanel, 1, 5, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, typeLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameComboBox, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, numberLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, numberSpinner, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, codeLabel, 0, 9, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, codePanel, 1, 9, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, pushLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, pushComboBox, 1, 10, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, tee1Label, 0, 11, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee1Spinner, 1, 11, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, tee2Label, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee2Spinner, 1, 12, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, tee3Label, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee3Spinner, 1, 13, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, tee4Label, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee4Spinner, 1, 14, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 15, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Taxiway Parking Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        TaxiwayParkingModel model = null;
        if(baseModel instanceof TaxiwayParkingModel)
            model = (TaxiwayParkingModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        radiusSpinner.removeChangeListener(this);
        radiusComboBox.removeActionListener(this);
        headingSlider.removeChangeListener(this);
        typeComboBox.removeActionListener(this);
        nameComboBox.removeActionListener(this);
        numberSpinner.removeChangeListener(this);
        codeTF.removeActionListener(this);
        pushComboBox.removeActionListener(this);
        tee1Spinner.removeChangeListener(this);
        tee2Spinner.removeChangeListener(this);
        tee3Spinner.removeChangeListener(this);
        tee4Spinner.removeChangeListener(this);
        if(model != null)
        {
            indexTF.setText((new StringBuilder()).append("").append(model.getIndex()).toString());
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            radiusSpinner.setValue(new Double(model.getRadius()));
            radiusComboBox.setSelectedItem(model.getRadiusMeasure());
            typeComboBox.setSelectedItem(model.getType());
            nameComboBox.setSelectedItem(model.getName());
            numberSpinner.setValue(new Integer(model.getNumber()));
            codeTF.setText(model.getAirlineCodes());
            codeTF.setCaretPosition(0);
            pushComboBox.setSelectedItem(model.getPushBack());
            tee1Spinner.setValue(new Double(model.getTeeOffset1()));
            tee2Spinner.setValue(new Double(model.getTeeOffset2()));
            tee3Spinner.setValue(new Double(model.getTeeOffset3()));
            tee4Spinner.setValue(new Double(model.getTeeOffset4()));
            model.addPropertyChangeListener(this);
        } else
        {
            indexTF.setText("");
            latTF.setText("");
            lonTF.setText("");
            headingSlider.setValue(0);
            headingTF.setText("");
            radiusSpinner.setValue(new Double(2D));
            radiusComboBox.setSelectedIndex(0);
            typeComboBox.setSelectedIndex(0);
            nameComboBox.setSelectedIndex(0);
            numberSpinner.setValue(new Integer(0));
            codeTF.setText("");
            pushComboBox.setSelectedIndex(0);
            tee1Spinner.setValue(new Double(0.0D));
            tee2Spinner.setValue(new Double(0.0D));
            tee3Spinner.setValue(new Double(0.0D));
            tee4Spinner.setValue(new Double(0.0D));
            status = false;
        }
        radiusSpinner.addChangeListener(this);
        radiusComboBox.addActionListener(this);
        headingSlider.addChangeListener(this);
        typeComboBox.addActionListener(this);
        nameComboBox.addActionListener(this);
        numberSpinner.addChangeListener(this);
        codeTF.addActionListener(this);
        pushComboBox.addActionListener(this);
        tee1Spinner.addChangeListener(this);
        tee2Spinner.addChangeListener(this);
        tee3Spinner.addChangeListener(this);
        tee4Spinner.addChangeListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        sceneryButton.setEnabled(status);
        triggerButton.setEnabled(status);
        indexLabel.setEnabled(status);
        indexTF.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        radiusLabel.setEnabled(status);
        radiusSpinner.setEnabled(status);
        radiusComboBox.setEnabled(status);
        typeLabel.setEnabled(status);
        typeComboBox.setEnabled(status);
        nameLabel.setEnabled(status);
        nameComboBox.setEnabled(status);
        numberLabel.setEnabled(status);
        numberSpinner.setEnabled(status);
        codeLabel.setEnabled(status);
        codeTF.setEnabled(status);
        pushLabel.setEnabled(status);
        pushComboBox.setEnabled(status);
        tee1Label.setEnabled(status);
        tee1Spinner.setEnabled(status);
        tee2Label.setEnabled(status);
        tee2Spinner.setEnabled(status);
        tee3Label.setEnabled(status);
        tee3Spinner.setEnabled(status);
        tee4Label.setEnabled(status);
        tee4Spinner.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            try
            {
                radiusSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                numberSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                tee1Spinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                tee2Spinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                tee3Spinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                tee4Spinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            model.setType((String)typeComboBox.getSelectedItem());
            model.setName((String)nameComboBox.getSelectedItem());
            model.setNumber(((Integer)numberSpinner.getValue()).intValue());
            model.setAirlineCodes(codeTF.getText());
            model.setPushBack((String)pushComboBox.getSelectedItem());
            model.setTeeOffset1(((Double)tee1Spinner.getValue()).floatValue());
            model.setTeeOffset2(((Double)tee2Spinner.getValue()).floatValue());
            model.setTeeOffset3(((Double)tee3Spinner.getValue()).floatValue());
            model.setTeeOffset4(((Double)tee4Spinner.getValue()).floatValue());
        }
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

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Taxiway Parking Space and its associated Taxi Paths?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    public void addFuelTrigger()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to add a Fuel Trigger to this parking location?", "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addTrigger", model, model);
            return;
        }
    }

    public void addFuelStation()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to add a Fuel Station to this parking location?", "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addStation", model, model);
            return;
        }
    }

    private void updateRadiusMeasure()
    {
        if(((String)radiusComboBox.getSelectedItem()).equals(model.getRadiusMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getRadiusMeasure().equals("M"))
                model.setRadius(model.getRadius() * 3.28F);
            else
                model.setRadius(model.getRadius() / 3.28F);
            radiusSpinner.setValue(new Double(model.getRadius()));
        }
        model.setRadiusMeasure((String)radiusComboBox.getSelectedItem());
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
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == radiusComboBox && model != null)
            updateRadiusMeasure();
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
        else
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setParkingLocked(!LockingEngine.getInstance().getParkingLocked());
        else
        if(event.getSource() == triggerButton)
            addFuelTrigger();
        else
        if(event.getSource() == sceneryButton)
            addFuelStation();
        else
        if(event.getSource() == typeComboBox)
        {
            firePropertyChange("update-type", model.getType(), (String)typeComboBox.getSelectedItem());
            model.setType((String)typeComboBox.getSelectedItem());
        } else
        if(event.getSource() == nameComboBox)
        {
            firePropertyChange("update-name", model.getName(), (String)nameComboBox.getSelectedItem());
            model.setName((String)nameComboBox.getSelectedItem());
        } else
        if(event.getSource() == codeTF)
        {
            firePropertyChange("update-airlineCode", model.getAirlineCodes(), codeTF.getText());
            model.setAirlineCodes(codeTF.getText());
        } else
        if(event.getSource() == pushComboBox)
            model.setPushBack((String)pushComboBox.getSelectedItem());
        else
        if(event.getSource() == codeButton)
        {
            String code = AirlineCodeDialog.showDialog(Utilities.MAIN_FRAME, codeTF.getText().trim());
            if(code != null)
            {
                codeTF.setText(code);
                firePropertyChange("update-airlineCode", model.getAirlineCodes(), codeTF.getText());
                model.setAirlineCodes(codeTF.getText());
            }
        }
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == radiusSpinner && model != null)
        {
            firePropertyChange("update-radius", new Float(model.getRadius()), new Float(((Double)radiusSpinner.getValue()).doubleValue()));
            model.setRadius((float)((Double)radiusSpinner.getValue()).doubleValue());
        } else
        if(event.getSource() == numberSpinner && model != null)
        {
            firePropertyChange("update-number", new Integer(model.getNumber()), (Integer)numberSpinner.getValue());
            model.setNumber(((Integer)numberSpinner.getValue()).intValue());
        } else
        if(event.getSource() == tee1Spinner && model != null)
            model.setTeeOffset1(((Double)tee1Spinner.getValue()).floatValue());
        else
        if(event.getSource() == tee2Spinner && model != null)
            model.setTeeOffset2(((Double)tee2Spinner.getValue()).floatValue());
        else
        if(event.getSource() == tee3Spinner && model != null)
            model.setTeeOffset3(((Double)tee3Spinner.getValue()).floatValue());
        else
        if(event.getSource() == tee4Spinner && model != null)
            model.setTeeOffset4(((Double)tee4Spinner.getValue()).floatValue());
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
        if(event.getSource() == codeTF && !codeTF.isPopupDisplayed())
            model.setAirlineCodes(codeTF.getText());
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
            if(event.getPropertyName().equals("type"))
            {
                typeComboBox.removeActionListener(this);
                typeComboBox.setSelectedItem(event.getNewValue());
                typeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("name"))
            {
                nameComboBox.removeActionListener(this);
                nameComboBox.setSelectedItem(event.getNewValue());
                nameComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("airlineCodes"))
                codeTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("pushBack"))
            {
                pushComboBox.removeActionListener(this);
                pushComboBox.setSelectedItem(event.getNewValue());
                pushComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("number"))
            {
                numberSpinner.removeChangeListener(this);
                numberSpinner.setValue(event.getNewValue());
                numberSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("radius"))
                radiusSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
            else
            if(event.getPropertyName().equals("radiusMeasure"))
                radiusComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("teeOffset1"))
            {
                tee1Spinner.removeChangeListener(this);
                tee1Spinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                tee1Spinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("teeOffset2"))
            {
                tee2Spinner.removeChangeListener(this);
                tee2Spinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                tee2Spinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("teeOffset3"))
            {
                tee3Spinner.removeChangeListener(this);
                tee3Spinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                tee3Spinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("teeOffset4"))
            {
                tee4Spinner.removeChangeListener(this);
                tee4Spinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                tee4Spinner.addChangeListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("parkingLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private TaxiwayParkingModel model;
    private Vector listeners;
    private JButton sceneryButton;
    private JButton triggerButton;
    private JButton lockingButton;
    private JButton deleteButton;
    private JTextField indexTF;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JSpinner radiusSpinner;
    private JComboBox radiusComboBox;
    private JComboBox typeComboBox;
    private JComboBox nameComboBox;
    private JSpinner numberSpinner;
    private PopupTextField codeTF;
    private JButton codeButton;
    private JComboBox pushComboBox;
    private JSpinner tee1Spinner;
    private JSpinner tee2Spinner;
    private JSpinner tee3Spinner;
    private JSpinner tee4Spinner;
    private JLabel indexLabel;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel headingLabel;
    private JLabel radiusLabel;
    private JLabel typeLabel;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JLabel codeLabel;
    private JLabel pushLabel;
    private JLabel tee1Label;
    private JLabel tee2Label;
    private JLabel tee3Label;
    private JLabel tee4Label;
}
