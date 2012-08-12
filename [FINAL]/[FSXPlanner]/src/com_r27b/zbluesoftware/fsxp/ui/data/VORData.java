// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VORData.java

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
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class VORData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public VORData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        sceneryButton = new JButton(IconFactory.getInstance().getIcon("scenery"));
        sceneryButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        sceneryButton.setToolTipText("Add VOR Station");
        sceneryButton.addActionListener(this);
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock VORs");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getVORLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(sceneryButton);
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        JLabel latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getVORLocked())
            latTF.setBackground(Color.red);
        JLabel lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getVORLocked())
            lonTF.setBackground(Color.red);
        JLabel altLabel = new JLabel("Altitude");
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
        JLabel frequencyLabel = new JLabel("Frequency");
        frequencyLabel.setFont(Utilities.LABEL_FONT);
        frequencyLabel.setForeground(Color.black);
        frequencySpinner = new JSpinner(new SpinnerNumberModel(108D, 108D, 136.99199999999999D, 0.001D));
        frequencySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(frequencySpinner, "0.000"));
        frequencySpinner.addChangeListener(this);
        JLabel rangeLabel = new JLabel("Range");
        rangeLabel.setFont(Utilities.LABEL_FONT);
        rangeLabel.setForeground(Color.black);
        rangeSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 1000000D, 10D));
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
        JLabel magvarLabel = new JLabel("Magvar");
        magvarLabel.setFont(Utilities.LABEL_FONT);
        magvarLabel.setForeground(Color.black);
        magvarSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -360D, 360D, 1.0D));
        magvarSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(magvarSpinner, "0.000"));
        magvarSpinner.addChangeListener(this);
        JLabel identLabel = new JLabel("Ident");
        identLabel.setFont(Utilities.LABEL_FONT);
        identLabel.setForeground(Color.black);
        identTF = new PopupTextField(10);
        identTF.setFont(Utilities.TEXT_FIELD_FONT);
        identTF.setForeground(Color.black);
        identTF.setDocument(new MaxLengthDocument(5));
        identTF.addFocusListener(this);
        identTF.addActionListener(this);
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new PopupTextField(10);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.setDocument(new MaxLengthDocument(48));
        nameTF.addFocusListener(this);
        nameTF.addActionListener(this);
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("LOW");
        typeModel.addElement("HIGH");
        typeModel.addElement("TERMINAL");
        typeModel.addElement("VOT");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        typeComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            typeComboBox.setPrototypeDisplayValue("--------");
        JLabel regionLabel = new JLabel("Region");
        regionLabel.setFont(Utilities.LABEL_FONT);
        regionLabel.setForeground(Color.black);
        regionTF = new PopupTextField(10);
        regionTF.setFont(Utilities.TEXT_FIELD_FONT);
        regionTF.setForeground(Color.black);
        regionTF.setDocument(new MaxLengthDocument(2));
        regionTF.addFocusListener(this);
        regionTF.addActionListener(this);
        dmeCB = new JCheckBox("DME");
        dmeCB.setFont(Utilities.LABEL_FONT);
        dmeCB.setForeground(Color.black);
        dmeCB.addActionListener(this);
        dmeOnlyCB = new JCheckBox("DME Only");
        dmeOnlyCB.setFont(Utilities.LABEL_FONT);
        dmeOnlyCB.setForeground(Color.black);
        dmeOnlyCB.addActionListener(this);
        dmeOnlyCB.setEnabled(false);
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
        Utilities.addComponent(mainPanel, dmeCB, 0, 13, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeOnlyCB, 1, 13, 2, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLatLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLatTF, 1, 14, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLonLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeLonTF, 1, 15, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltSpinner, 1, 16, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, dmeAltComboBox, 2, 16, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 18, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("VOR Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        VORModel model = null;
        if(baseModel instanceof VORModel)
            model = (VORModel)baseModel;
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
        dmeCB.removeActionListener(this);
        typeComboBox.removeActionListener(this);
        dmeOnlyCB.removeActionListener(this);
        dmeLatTF.removeActionListener(this);
        dmeLatTF.removeFocusListener(this);
        dmeLonTF.removeActionListener(this);
        dmeLonTF.removeFocusListener(this);
        dmeAltSpinner.removeChangeListener(this);
        dmeAltComboBox.removeActionListener(this);
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
            if(model.getDme())
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
                dmeOnlyCB.setSelected(false);
                dmeLatTF.setText("");
                dmeLonTF.setText("");
                dmeAltSpinner.setValue(new Double(0.0D));
                dmeAltComboBox.setSelectedIndex(0);
                setDMEEnabled(false);
            }
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
            dmeCB.setSelected(false);
            dmeOnlyCB.setSelected(false);
            dmeLatTF.setText("");
            dmeLonTF.setText("");
            dmeAltSpinner.setValue(new Double(0.0D));
            dmeAltComboBox.setSelectedIndex(0);
            setDMEEnabled(false);
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
        dmeOnlyCB.addActionListener(this);
        dmeCB.addActionListener(this);
        dmeLatTF.addActionListener(this);
        dmeLatTF.addFocusListener(this);
        dmeLonTF.addActionListener(this);
        dmeLonTF.addFocusListener(this);
        dmeAltSpinner.addChangeListener(this);
        dmeAltComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
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
            try
            {
                dmeAltSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
            model.setAltMeasure((String)altComboBox.getSelectedItem());
        }
    }

    private void setDMEEnabled(boolean enabled)
    {
        dmeOnlyCB.setEnabled(enabled);
        dmeLatLabel.setEnabled(enabled);
        dmeLatTF.setEnabled(enabled);
        dmeLonLabel.setEnabled(enabled);
        dmeLonTF.setEnabled(enabled);
        dmeAltLabel.setEnabled(enabled);
        dmeAltSpinner.setEnabled(enabled);
        dmeAltComboBox.setEnabled(enabled);
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

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this VOR?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    public void addVORStation()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to add a VOR Station to this VOR?", "Confirm Add...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("addStation", model, model);
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
            model.setDme(true);
            dmeLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getDMEModel().getLatLon().getLat()));
            dmeLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getDMEModel().getLatLon().getLon()));
            dmeAltSpinner.setValue(new Double(model.getDMEModel().getAlt()));
            dmeAltComboBox.setSelectedItem(model.getDMEModel().getAltMeasure());
        } else
        {
            model.setDMEModel(null);
            model.setDme(false);
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
            LockingEngine.getInstance().setVORLocked(!LockingEngine.getInstance().getVORLocked());
        else
        if(event.getSource() == sceneryButton)
            addVORStation();
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
        if(event.getSource() == dmeCB)
        {
            setDMEEnabled(dmeCB.isSelected());
            updateDMEModel();
        } else
        if(event.getSource() == dmeOnlyCB)
        {
            firePropertyChange("update-dmeOnly", new Boolean(model.getDmeOnly()), new Boolean(dmeOnlyCB.isSelected()));
            model.setDmeOnly(dmeOnlyCB.isSelected());
        } else
        if(event.getSource() == dmeLatTF)
            updateDMELatitude();
        else
        if(event.getSource() == dmeLonTF)
            updateDMELongitude();
        else
        if(event.getSource() == dmeAltComboBox)
            updateDMEAltMeasure();
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
        else
        if(event.getSource() == dmeAltSpinner && model != null)
            model.getDMEModel().setAlt(((Double)dmeAltSpinner.getValue()).floatValue());
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
        else
        if(event.getSource() == dmeLatTF)
            updateDMELatitude();
        else
        if(event.getSource() == dmeLonTF)
            updateDMELongitude();
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
            if(event.getPropertyName().equals("dmeOnly"))
            {
                dmeOnlyCB.removeActionListener(this);
                dmeOnlyCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                dmeOnlyCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("dmeAlt"))
            {
                dmeAltSpinner.removeChangeListener(this);
                dmeAltSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                dmeAltSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("dmeAltMeasure"))
                dmeAltComboBox.setSelectedItem(event.getNewValue());
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("vorLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private VORModel model;
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
    private JCheckBox dmeOnlyCB;
    private JComboBox typeComboBox;
    private PopupTextField regionTF;
    private JCheckBox dmeCB;
    private JLabel dmeLatLabel;
    private PopupTextField dmeLatTF;
    private JLabel dmeLonLabel;
    private PopupTextField dmeLonTF;
    private JLabel dmeAltLabel;
    private JSpinner dmeAltSpinner;
    private JComboBox dmeAltComboBox;
}
