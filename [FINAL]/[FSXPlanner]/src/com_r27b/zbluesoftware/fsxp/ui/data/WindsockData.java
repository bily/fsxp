// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WindsockData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.WindsockModel;
import com.zbluesoftware.fsxp.renderer.IndexNameRenderer;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class WindsockData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, MouseListener, PropertyChangeListener
{

    public WindsockData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Windsocks");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getWindsocksLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
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
        if(LockingEngine.getInstance().getWindsocksLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getWindsocksLocked())
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
        altIsAglLabel = new JLabel("Altitude is AGL");
        altIsAglLabel.setFont(Utilities.LABEL_FONT);
        altIsAglLabel.setForeground(Color.black);
        altIsAglComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        altIsAglComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altIsAglComboBox.setForeground(Color.black);
        altIsAglComboBox.addActionListener(this);
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
        pitchLabel = new JLabel("Pitch");
        pitchLabel.setFont(Utilities.LABEL_FONT);
        pitchLabel.setForeground(Color.black);
        pitchTF = new PopupTextField(5);
        pitchTF.setFont(Utilities.TEXT_FIELD_FONT);
        pitchTF.setForeground(Color.black);
        pitchTF.addActionListener(this);
        pitchTF.addFocusListener(this);
        pitchSlider = new JSlider(0);
        pitchSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        pitchSlider.setPreferredSize(new Dimension(100, pitchSlider.getPreferredSize().height));
        pitchSlider.addChangeListener(this);
        JPanel pitchPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(pitchPanel, pitchTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(pitchPanel, pitchSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        bankLabel = new JLabel("Bank");
        bankLabel.setFont(Utilities.LABEL_FONT);
        bankLabel.setForeground(Color.black);
        bankTF = new PopupTextField(5);
        bankTF.setFont(Utilities.TEXT_FIELD_FONT);
        bankTF.setForeground(Color.black);
        bankTF.addActionListener(this);
        bankTF.addFocusListener(this);
        bankSlider = new JSlider(0);
        bankSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        bankSlider.setPreferredSize(new Dimension(100, bankSlider.getPreferredSize().height));
        bankSlider.addChangeListener(this);
        JPanel bankPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(bankPanel, bankTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(bankPanel, bankSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        complexityLabel = new JLabel("Display At");
        complexityLabel.setFont(Utilities.LABEL_FONT);
        complexityLabel.setForeground(Color.black);
        complexityComboBox = new JComboBox(getComplexityModel());
        complexityComboBox.setFont(Utilities.COMBO_BOX_FONT);
        complexityComboBox.setForeground(Color.black);
        complexityComboBox.setRenderer(new IndexNameRenderer());
        complexityComboBox.addActionListener(this);
        poleHeightLabel = new JLabel("Pole Height");
        poleHeightLabel.setFont(Utilities.LABEL_FONT);
        poleHeightLabel.setForeground(Color.black);
        poleHeightSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 0.0D, 100D, 0.5D));
        poleHeightSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(poleHeightSpinner, "0.00"));
        poleHeightSpinner.addChangeListener(this);
        sockLengthLabel = new JLabel("Sock Length");
        sockLengthLabel.setFont(Utilities.LABEL_FONT);
        sockLengthLabel.setForeground(Color.black);
        sockLengthSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 0.0D, 100D, 0.5D));
        sockLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sockLengthSpinner, "0.00"));
        sockLengthSpinner.addChangeListener(this);
        lightedLabel = new JLabel("Windsock Lighted");
        lightedLabel.setFont(Utilities.LABEL_FONT);
        lightedLabel.setForeground(Color.black);
        lightedComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        lightedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        lightedComboBox.setForeground(Color.black);
        lightedComboBox.addActionListener(this);
        poleColorLabel = new JLabel("Pole Color");
        poleColorLabel.setFont(Utilities.LABEL_FONT);
        poleColorLabel.setForeground(Color.black);
        poleColorPanel = new JPanel();
        poleColorPanel.setBackground(Color.white);
        poleColorPanel.setMinimumSize(new Dimension(25, 25));
        poleColorPanel.setPreferredSize(poleColorPanel.getMinimumSize());
        poleColorPanel.setMaximumSize(poleColorPanel.getMinimumSize());
        poleColorPanel.setBorder(new EtchedBorder());
        poleColorPanel.addMouseListener(this);
        poleColorPanel.setToolTipText("Double click to change");
        poleColorPanel.setCursor(new Cursor(12));
        sockColorLabel = new JLabel("Sock Color");
        sockColorLabel.setFont(Utilities.LABEL_FONT);
        sockColorLabel.setForeground(Color.black);
        sockColorPanel = new JPanel();
        sockColorPanel.setBackground(Color.red);
        sockColorPanel.setMinimumSize(new Dimension(25, 25));
        sockColorPanel.setPreferredSize(sockColorPanel.getMinimumSize());
        sockColorPanel.setMaximumSize(sockColorPanel.getMinimumSize());
        sockColorPanel.setBorder(new EtchedBorder());
        sockColorPanel.addMouseListener(this);
        sockColorPanel.setToolTipText("Double click to change");
        sockColorPanel.setCursor(new Cursor(12));
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglComboBox, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, poleHeightLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, poleHeightSpinner, 1, 5, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sockLengthLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sockLengthSpinner, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lightedLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lightedComboBox, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, poleColorLabel, 0, 8, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, poleColorPanel, 1, 8, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sockColorLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sockColorPanel, 1, 9, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 10, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 10, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, pitchLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, pitchPanel, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, bankLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, bankPanel, 1, 12, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, complexityLabel, 0, 13, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, complexityComboBox, 1, 13, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 14, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Windsock Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getComplexityModel()
    {
        DefaultComboBoxModel complexityModel = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "VERY_SPARSE");
        hashMap.put("name", "Very sparse scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "SPARSE");
        hashMap.put("name", "Sparse scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "NORMAL");
        hashMap.put("name", "Normal scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "DENSE");
        hashMap.put("name", "Dense scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "VERY_DENSE");
        hashMap.put("name", "Very dense scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "EXTREMELY_DENSE");
        hashMap.put("name", "Extremely dense scenery level");
        complexityModel.addElement(hashMap);
        return complexityModel;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        WindsockModel model = null;
        if(baseModel instanceof WindsockModel)
            model = (WindsockModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        headingSlider.removeChangeListener(this);
        altSpinner.removeChangeListener(this);
        altComboBox.removeActionListener(this);
        altIsAglComboBox.removeActionListener(this);
        pitchTF.removeActionListener(this);
        pitchTF.removeFocusListener(this);
        pitchSlider.removeChangeListener(this);
        bankTF.removeActionListener(this);
        bankTF.removeFocusListener(this);
        bankSlider.removeChangeListener(this);
        complexityComboBox.removeActionListener(this);
        poleHeightSpinner.removeChangeListener(this);
        sockLengthSpinner.removeChangeListener(this);
        lightedComboBox.removeActionListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            altIsAglComboBox.setSelectedIndex(model.getAltitudeIsAgl() ? 0 : 1);
            pitchSlider.setValue((int)model.getPitch());
            pitchTF.setText((new StringBuilder()).append("").append(model.getPitch()).toString());
            bankSlider.setValue((int)model.getBank());
            bankTF.setText((new StringBuilder()).append("").append(model.getBank()).toString());
            poleHeightSpinner.setValue(new Double(model.getPoleHeight()));
            sockLengthSpinner.setValue(new Double(model.getSockLength()));
            lightedComboBox.setSelectedIndex(model.getLighted() ? 0 : 1);
            poleColorPanel.setBackground(model.getPoleColor());
            sockColorPanel.setBackground(model.getSockColor());
            Utilities.setCodeDescComboBox(complexityComboBox, model.getImageComplexity(), "");
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            headingSlider.setValue(0);
            headingTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedItem("M");
            altIsAglComboBox.setSelectedIndex(0);
            pitchSlider.setValue(0);
            pitchTF.setText("");
            bankSlider.setValue(0);
            bankTF.setText("");
            poleHeightSpinner.setValue(new Double(5.5D));
            sockLengthSpinner.setValue(new Double(3.5D));
            lightedComboBox.setSelectedIndex(0);
            poleColorPanel.setBackground(new Color(128, 128, 128));
            sockColorPanel.setBackground(new Color(255, 128, 128));
            Utilities.setCodeDescComboBox(complexityComboBox, "NORMAL", "");
            status = false;
        }
        headingSlider.addChangeListener(this);
        altSpinner.addChangeListener(this);
        altComboBox.addActionListener(this);
        altIsAglComboBox.addActionListener(this);
        pitchTF.addActionListener(this);
        pitchTF.addFocusListener(this);
        pitchSlider.addChangeListener(this);
        bankTF.addActionListener(this);
        bankTF.addFocusListener(this);
        bankSlider.addChangeListener(this);
        complexityComboBox.addActionListener(this);
        poleHeightSpinner.addChangeListener(this);
        sockLengthSpinner.addChangeListener(this);
        lightedComboBox.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        altLabel.setEnabled(status);
        altSpinner.setEnabled(status);
        altComboBox.setEnabled(status);
        altIsAglLabel.setEnabled(status);
        altIsAglComboBox.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        pitchLabel.setEnabled(status);
        pitchTF.setEnabled(status);
        pitchSlider.setEnabled(status);
        bankLabel.setEnabled(status);
        bankTF.setEnabled(status);
        bankSlider.setEnabled(status);
        complexityLabel.setEnabled(status);
        complexityComboBox.setEnabled(status);
        poleHeightLabel.setEnabled(status);
        poleHeightSpinner.setEnabled(status);
        sockLengthLabel.setEnabled(status);
        sockLengthSpinner.setEnabled(status);
        lightedLabel.setEnabled(status);
        lightedComboBox.setEnabled(status);
        poleColorLabel.setEnabled(status);
        poleColorPanel.setEnabled(status);
        sockColorLabel.setEnabled(status);
        sockColorPanel.setEnabled(status);
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
                poleHeightSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                sockLengthSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            updateHeading();
            updatePitch();
            updateBank();
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

    private void updatePitch()
    {
        if(pitchTF.isPopupDisplayed())
            return;
        float pitch = model.getPitch();
        try
        {
            pitch = Float.parseFloat(pitchTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            pitch = model.getPitch();
        }
        if(pitch < 0.0F)
            pitch = 0.0F;
        else
        if(pitch > 359F)
            pitch = 359F;
        pitchSlider.setValue((int)pitch);
        pitchTF.setText((new StringBuilder()).append("").append(pitch).toString());
        firePropertyChange("update-pitch", new Float(model.getPitch()), new Float(pitchSlider.getValue()));
        model.setPitch(pitch);
    }

    private void updateBank()
    {
        if(bankTF.isPopupDisplayed())
            return;
        float bank = model.getBank();
        try
        {
            bank = Float.parseFloat(bankTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            bank = model.getBank();
        }
        if(bank < 0.0F)
            bank = 0.0F;
        else
        if(bank > 359F)
            bank = 359F;
        bankSlider.setValue((int)bank);
        bankTF.setText((new StringBuilder()).append("").append(bank).toString());
        firePropertyChange("update-bank", new Float(model.getBank()), new Float(bankSlider.getValue()));
        model.setBank(bank);
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
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Jetway?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
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
        if(event.getSource() == pitchTF)
            updatePitch();
        else
        if(event.getSource() == bankTF)
            updateBank();
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
            LockingEngine.getInstance().setWindsocksLocked(!LockingEngine.getInstance().getWindsocksLocked());
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == altIsAglComboBox)
            model.setAltitudeIsAgl(altIsAglComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == complexityComboBox)
            model.setImageComplexity((String)((HashMap)complexityComboBox.getSelectedItem()).get("index"));
        else
        if(event.getSource() == lightedComboBox)
            model.setLighted(lightedComboBox.getSelectedIndex() == 0);
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == pitchSlider && model != null)
        {
            pitchTF.setText((new StringBuilder()).append("").append(pitchSlider.getValue()).toString());
            firePropertyChange("update-pitch", new Float(model.getPitch()), new Float(pitchSlider.getValue()));
            model.setPitch(pitchSlider.getValue());
        } else
        if(event.getSource() == bankSlider && model != null)
        {
            bankTF.setText((new StringBuilder()).append("").append(bankSlider.getValue()).toString());
            firePropertyChange("update-bank", new Float(model.getBank()), new Float(bankSlider.getValue()));
            model.setBank(bankSlider.getValue());
        } else
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).floatValue());
        else
        if(event.getSource() == poleHeightSpinner && model != null)
            model.setPoleHeight(((Double)poleHeightSpinner.getValue()).floatValue());
        else
        if(event.getSource() == sockLengthSpinner && model != null)
            model.setSockLength(((Double)sockLengthSpinner.getValue()).floatValue());
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
        if(event.getSource() == pitchTF)
            updatePitch();
        else
        if(event.getSource() == bankTF)
            updateBank();
    }

    public void mouseClicked(MouseEvent event)
    {
        if((event.getSource() instanceof JPanel) && event.getClickCount() == 2)
        {
            Color color = JColorChooser.showDialog(this, "Select Color...", ((JPanel)event.getSource()).getBackground());
            if(color != null)
            {
                ((JPanel)event.getSource()).setBackground(color);
                if(event.getSource() == poleColorPanel)
                    model.setPoleColor(color);
                else
                if(event.getSource() == sockColorPanel)
                    model.setSockColor(color);
            }
        }
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
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
                altComboBox.setSelectedItem(event.getNewValue());
            else
            if(event.getPropertyName().equals("altitudeIsAgl"))
            {
                altIsAglComboBox.removeActionListener(this);
                altIsAglComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                altIsAglComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("pitch"))
            {
                pitchTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                pitchSlider.removeChangeListener(this);
                pitchSlider.setValue(((Float)event.getNewValue()).intValue());
                pitchSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("bank"))
            {
                bankTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                bankSlider.removeChangeListener(this);
                bankSlider.setValue(((Float)event.getNewValue()).intValue());
                bankSlider.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("imageComplexity"))
            {
                complexityComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(complexityComboBox, (String)event.getNewValue(), "");
                complexityComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("lighted"))
            {
                lightedComboBox.removeActionListener(this);
                lightedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                lightedComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("poleHeight"))
            {
                poleHeightSpinner.removeChangeListener(this);
                poleHeightSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                poleHeightSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("sockLength"))
            {
                sockLengthSpinner.removeChangeListener(this);
                sockLengthSpinner.setValue(new Double(((Float)event.getNewValue()).floatValue()));
                sockLengthSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("poleColor"))
                poleColorPanel.setBackground((Color)event.getNewValue());
            else
            if(event.getPropertyName().equals("sockColor"))
                sockColorPanel.setBackground((Color)event.getNewValue());
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("windsocksLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private WindsockModel model;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JComboBox altIsAglComboBox;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private PopupTextField pitchTF;
    private JSlider pitchSlider;
    private PopupTextField bankTF;
    private JSlider bankSlider;
    private JComboBox complexityComboBox;
    private JSpinner poleHeightSpinner;
    private JSpinner sockLengthSpinner;
    private JComboBox lightedComboBox;
    private JPanel poleColorPanel;
    private JPanel sockColorPanel;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel altIsAglLabel;
    private JLabel headingLabel;
    private JLabel pitchLabel;
    private JLabel bankLabel;
    private JLabel complexityLabel;
    private JLabel poleHeightLabel;
    private JLabel sockLengthLabel;
    private JLabel lightedLabel;
    private JLabel poleColorLabel;
    private JLabel sockColorLabel;
}
