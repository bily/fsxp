// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NDBData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.NDBModel;
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

public class NDBData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public NDBData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        sceneryButton = new JButton(IconFactory.getInstance().getIcon("scenery"));
        sceneryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        sceneryButton.setToolTipText("Add NDB Antenna");
        sceneryButton.addActionListener(this);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock NDBs");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getNDBLocked())
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
        if(LockingEngine.getInstance().getNDBLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getNDBLocked())
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
        frequencyLabel = new JLabel("Frequency");
        frequencyLabel.setFont(Utilities.LABEL_FONT);
        frequencyLabel.setForeground(Color.black);
        frequencySpinner = new JSpinner(new SpinnerNumberModel(100D, 0.0D, 1737D, 0.10000000000000001D));
        frequencySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(frequencySpinner, "0.0"));
        frequencySpinner.addChangeListener(this);
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
        nameLabel = new JLabel("Name");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new PopupTextField(10);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.setDocument(new MaxLengthDocument(48));
        nameTF.addFocusListener(this);
        nameTF.addActionListener(this);
        typeLabel = new JLabel("Type");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("COMPASS_POINT");
        typeModel.addElement("H");
        typeModel.addElement("HH");
        typeModel.addElement("MH");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        typeComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            typeComboBox.setPrototypeDisplayValue("--------");
        regionLabel = new JLabel("Region");
        regionLabel.setFont(Utilities.LABEL_FONT);
        regionLabel.setForeground(Color.black);
        regionTF = new PopupTextField(10);
        regionTF.setFont(Utilities.TEXT_FIELD_FONT);
        regionTF.setForeground(Color.black);
        regionTF.setDocument(new MaxLengthDocument(2));
        regionTF.addFocusListener(this);
        regionTF.addActionListener(this);
        terminalCB = new JCheckBox("Terminal NDB", false);
        terminalCB.setFont(Utilities.LABEL_FONT);
        terminalCB.setForeground(Color.black);
        terminalCB.addActionListener(this);
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
        Utilities.addComponent(mainPanel, identLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, identTF, 1, 5, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, regionLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, regionTF, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, typeLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, frequencyLabel, 0, 9, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, frequencySpinner, 1, 9, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, rangeLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, rangePanel, 1, 10, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, magvarLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, magvarSpinner, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, terminalCB, 1, 12, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 13, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("NDB Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        NDBModel model = null;
        if(baseModel instanceof NDBModel)
            model = (NDBModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        frequencySpinner.removeChangeListener(this);
        rangeSpinner.removeChangeListener(this);
        rangeComboBox.removeActionListener(this);
        magvarSpinner.removeChangeListener(this);
        identTF.removeFocusListener(this);
        identTF.removeActionListener(this);
        nameTF.removeFocusListener(this);
        nameTF.removeActionListener(this);
        typeComboBox.removeActionListener(this);
        terminalCB.removeActionListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            frequencySpinner.setValue(new Double(model.getFrequency()));
            rangeSpinner.setValue(new Double(model.getRange()));
            rangeComboBox.setSelectedItem(model.getRangeMeasure());
            magvarSpinner.setValue(new Double(model.getMagvar()));
            identTF.setText(model.getIdent());
            nameTF.setText(model.getName());
            regionTF.setText(model.getRegion());
            typeComboBox.setSelectedItem(model.getType());
            terminalCB.setSelected(model.isTerminal());
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            frequencySpinner.setValue(new Double(108D));
            rangeSpinner.setValue(new Double(27D));
            rangeComboBox.setSelectedItem("N");
            magvarSpinner.setValue(new Double(0.0D));
            identTF.setText("");
            nameTF.setText("");
            regionTF.setText("");
            typeComboBox.setSelectedIndex(0);
            terminalCB.setSelected(false);
            status = false;
        }
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        frequencySpinner.addChangeListener(this);
        rangeSpinner.addChangeListener(this);
        rangeComboBox.addActionListener(this);
        magvarSpinner.addChangeListener(this);
        identTF.addFocusListener(this);
        identTF.addActionListener(this);
        nameTF.addFocusListener(this);
        nameTF.addActionListener(this);
        typeComboBox.addActionListener(this);
        terminalCB.addActionListener(this);
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
        frequencyLabel.setEnabled(status);
        frequencySpinner.setEnabled(status);
        rangeLabel.setEnabled(status);
        rangeSpinner.setEnabled(status);
        rangeComboBox.setEnabled(status);
        magvarLabel.setEnabled(status);
        magvarSpinner.setEnabled(status);
        identLabel.setEnabled(status);
        identTF.setEnabled(status);
        nameLabel.setEnabled(status);
        nameTF.setEnabled(status);
        typeLabel.setEnabled(status);
        typeComboBox.setEnabled(status);
        regionLabel.setEnabled(status);
        regionTF.setEnabled(status);
        terminalCB.setEnabled(status);
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
                rangeSpinner.commitEdit();
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
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
            model.setAltMeasure((String)altComboBox.getSelectedItem());
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

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this NDB?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    public void addNDBAntenna()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to add an NDB Antenna to this NDB?", "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addAntenna", model, model);
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

    public void actionPerformed(ActionEvent event)
    {
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
            LockingEngine.getInstance().setNDBLocked(!LockingEngine.getInstance().getNDBLocked());
        else
        if(event.getSource() == sceneryButton)
            addNDBAntenna();
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == rangeComboBox)
            updateRangeMeasure();
        else
        if(event.getSource() == identTF)
        {
            firePropertyChange("update-ident", model.getIdent(), identTF.getText());
            model.setIdent(identTF.getText());
        } else
        if(event.getSource() == nameTF)
            model.setName(nameTF.getText());
        else
        if(event.getSource() == regionTF)
            model.setRegion(regionTF.getText());
        else
        if(event.getSource() == typeComboBox)
            model.setType((String)typeComboBox.getSelectedItem());
        else
        if(event.getSource() == terminalCB)
            model.setTerminal(terminalCB.isSelected());
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
        else
        if(event.getSource() == rangeSpinner && model != null)
            model.setRange(((Double)rangeSpinner.getValue()).floatValue());
        else
        if(event.getSource() == frequencySpinner && model != null)
            model.setFrequency(((Double)frequencySpinner.getValue()).floatValue());
        else
        if(event.getSource() == magvarSpinner && model != null)
            model.setMagvar(((Double)magvarSpinner.getValue()).floatValue());
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
        if(event.getSource() == identTF && !identTF.isPopupDisplayed())
        {
            firePropertyChange("update-ident", model.getIdent(), identTF.getText());
            model.setIdent(identTF.getText());
        } else
        if(event.getSource() == nameTF && !nameTF.isPopupDisplayed())
            model.setName(nameTF.getText());
        else
        if(event.getSource() == regionTF && !regionTF.isPopupDisplayed())
            model.setRegion(regionTF.getText());
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
            if(event.getPropertyName().equals("altMeasure"))
                altComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("rangeMeasure"))
                rangeComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("ident"))
                identTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("name"))
                nameTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("region"))
                regionTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("type"))
            {
                typeComboBox.removeActionListener(this);
                typeComboBox.setSelectedItem((String)event.getNewValue());
                typeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("alt"))
            {
                altSpinner.removeChangeListener(this);
                altSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                altSpinner.addChangeListener(this);
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
            if(event.getPropertyName().equals("terminal"))
            {
                terminalCB.removeActionListener(this);
                terminalCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                terminalCB.addActionListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("ndbLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private NDBModel model;
    private JButton sceneryButton;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JSpinner frequencySpinner;
    private JSpinner rangeSpinner;
    private JComboBox rangeComboBox;
    private JSpinner magvarSpinner;
    private PopupTextField identTF;
    private PopupTextField nameTF;
    private JComboBox typeComboBox;
    private PopupTextField regionTF;
    private JCheckBox terminalCB;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel frequencyLabel;
    private JLabel rangeLabel;
    private JLabel magvarLabel;
    private JLabel identLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel regionLabel;
}
