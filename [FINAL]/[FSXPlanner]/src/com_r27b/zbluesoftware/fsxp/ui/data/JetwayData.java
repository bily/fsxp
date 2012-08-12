// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JetwayData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.JetwayModel;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class JetwayData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public JetwayData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Jetways");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getJetwaysLocked())
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
        if(LockingEngine.getInstance().getJetwaysLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getJetwaysLocked())
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
        gateNameLabel = new JLabel("Gate Name");
        gateNameLabel.setFont(Utilities.LABEL_FONT);
        gateNameLabel.setForeground(Color.black);
        DefaultComboBoxModel gateNameModel = new DefaultComboBoxModel();
        gateNameModel.addElement("PARKING");
        gateNameModel.addElement("DOCK,");
        gateNameModel.addElement("GATE");
        for(int i = 65; i < 91; i++)
            gateNameModel.addElement((new StringBuilder()).append("GATE_").append(Character.toString((char)i)).toString());

        gateNameModel.addElement("NONE");
        gateNameModel.addElement("N_PARKING");
        gateNameModel.addElement("NE_PARKING");
        gateNameModel.addElement("NW_PARKING");
        gateNameModel.addElement("SE_PARKING");
        gateNameModel.addElement("S_PARKING");
        gateNameModel.addElement("SW_PARKING");
        gateNameModel.addElement("W_PARKING");
        gateNameModel.addElement("E_PARKING");
        gateNameComboBox = new JComboBox(gateNameModel);
        gateNameComboBox.setFont(Utilities.COMBO_BOX_FONT);
        gateNameComboBox.setForeground(Color.black);
        gateNameComboBox.addActionListener(this);
        numberLabel = new JLabel("Number");
        numberLabel.setFont(Utilities.LABEL_FONT);
        numberLabel.setForeground(Color.black);
        numberSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3999, 1));
        numberSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(numberSpinner, "0"));
        numberSpinner.addChangeListener(this);
        complexityLabel = new JLabel("Display At");
        complexityLabel.setFont(Utilities.LABEL_FONT);
        complexityLabel.setForeground(Color.black);
        complexityComboBox = new JComboBox(getComplexityModel());
        complexityComboBox.setFont(Utilities.COMBO_BOX_FONT);
        complexityComboBox.setForeground(Color.black);
        complexityComboBox.setRenderer(new IndexNameRenderer());
        complexityComboBox.addActionListener(this);
        nameLabel = new JLabel("Name (GUID)");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new PopupTextField(5);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.addActionListener(this);
        nameTF.addFocusListener(this);
        scaleLabel = new JLabel("Scale");
        scaleLabel.setFont(Utilities.LABEL_FONT);
        scaleLabel.setForeground(Color.black);
        scaleSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 0.10000000000000001D, 100D, 0.5D));
        scaleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(scaleSpinner, "0.00"));
        scaleSpinner.addChangeListener(this);
        instanceLabel = new JLabel("Instance ID");
        instanceLabel.setFont(Utilities.LABEL_FONT);
        instanceLabel.setForeground(Color.black);
        instanceTF = new PopupTextField(5);
        instanceTF.setFont(Utilities.TEXT_FIELD_FONT);
        instanceTF.setForeground(Color.black);
        instanceTF.addActionListener(this);
        instanceTF.addFocusListener(this);
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
        Utilities.addComponent(mainPanel, headingLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 5, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, pitchLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, pitchPanel, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, bankLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, bankPanel, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gateNameLabel, 0, 8, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gateNameComboBox, 1, 8, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, numberLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, numberSpinner, 1, 9, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, complexityLabel, 0, 10, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, complexityComboBox, 1, 10, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, instanceLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, instanceTF, 1, 12, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, scaleLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, scaleSpinner, 1, 13, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 14, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Jetway Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getComplexityModel()
    {
        DefaultComboBoxModel complexityModel = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "VERY_SPARSE");
        hashMap.put("name", "Very sparce scenery level");
        complexityModel.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "SPARSE");
        hashMap.put("name", "Sparce scenery level");
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
        JetwayModel model = null;
        if(baseModel instanceof JetwayModel)
            model = (JetwayModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        headingSlider.removeChangeListener(this);
        gateNameComboBox.removeActionListener(this);
        numberSpinner.removeChangeListener(this);
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
        nameTF.removeActionListener(this);
        nameTF.removeFocusListener(this);
        scaleSpinner.removeChangeListener(this);
        instanceTF.removeActionListener(this);
        instanceTF.removeFocusListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            gateNameComboBox.setSelectedItem(model.getGateName());
            numberSpinner.setValue(new Integer(model.getParkingNumber()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            altIsAglComboBox.setSelectedIndex(model.getAltitudeIsAgl() ? 0 : 1);
            pitchSlider.setValue((int)model.getPitch());
            pitchTF.setText((new StringBuilder()).append("").append(model.getPitch()).toString());
            bankSlider.setValue((int)model.getBank());
            bankTF.setText((new StringBuilder()).append("").append(model.getBank()).toString());
            nameTF.setText(model.getName());
            nameTF.setCaretPosition(0);
            scaleSpinner.setValue(new Double(model.getJetwayScale()));
            instanceTF.setText(model.getInstanceid());
            instanceTF.setCaretPosition(0);
            Utilities.setCodeDescComboBox(complexityComboBox, model.getImageComplexity(), "");
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            headingSlider.setValue(0);
            headingTF.setText("");
            gateNameComboBox.setSelectedIndex(0);
            numberSpinner.setValue(new Integer(0));
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedItem("M");
            altIsAglComboBox.setSelectedIndex(0);
            pitchSlider.setValue(0);
            pitchTF.setText("");
            bankSlider.setValue(0);
            bankTF.setText("");
            nameTF.setText("");
            scaleSpinner.setValue(new Double(0.0D));
            instanceTF.setText("");
            Utilities.setCodeDescComboBox(complexityComboBox, "NORMAL", "");
            status = false;
        }
        headingSlider.addChangeListener(this);
        gateNameComboBox.addActionListener(this);
        numberSpinner.addChangeListener(this);
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
        nameTF.addActionListener(this);
        nameTF.addFocusListener(this);
        scaleSpinner.addChangeListener(this);
        instanceTF.addActionListener(this);
        instanceTF.addFocusListener(this);
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
        gateNameLabel.setEnabled(status);
        gateNameComboBox.setEnabled(status);
        numberLabel.setEnabled(status);
        numberSpinner.setEnabled(status);
        complexityLabel.setEnabled(status);
        complexityComboBox.setEnabled(status);
        nameLabel.setEnabled(status);
        nameTF.setEnabled(status);
        scaleLabel.setEnabled(status);
        scaleSpinner.setEnabled(status);
        instanceLabel.setEnabled(status);
        instanceTF.setEnabled(status);
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
                numberSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            try
            {
                scaleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
            updateHeading();
            updatePitch();
            updateBank();
            model.setName(nameTF.getText());
            model.setInstanceid(instanceTF.getText());
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
                model.setAlt(model.getAlt() * 3.2799999713897705D);
            else
                model.setAlt(model.getAlt() / 3.2799999713897705D);
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
            LockingEngine.getInstance().setJetwaysLocked(!LockingEngine.getInstance().getJetwaysLocked());
        else
        if(event.getSource() == gateNameComboBox)
            model.setGateName((String)gateNameComboBox.getSelectedItem());
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
        if(event.getSource() == nameTF)
            model.setName(nameTF.getText());
        else
        if(event.getSource() == instanceTF)
            model.setInstanceid(instanceTF.getText());
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
        if(event.getSource() == numberSpinner && model != null)
            model.setParkingNumber(((Integer)numberSpinner.getValue()).intValue());
        else
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == scaleSpinner && model != null)
            model.setJetwayScale(((Double)scaleSpinner.getValue()).floatValue());
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
        else
        if(event.getSource() == nameTF && !nameTF.isPopupDisplayed())
            model.setName(nameTF.getText());
        else
        if(event.getSource() == instanceTF && !instanceTF.isPopupDisplayed())
            model.setInstanceid(instanceTF.getText());
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
                altSpinner.setValue(event.getNewValue());
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
            if(event.getPropertyName().equals("gateName"))
            {
                gateNameComboBox.removeActionListener(this);
                gateNameComboBox.setSelectedItem(event.getNewValue());
                gateNameComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("parkingNumber"))
            {
                numberSpinner.removeChangeListener(this);
                numberSpinner.setValue(event.getNewValue());
                numberSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("imageComplexity"))
            {
                complexityComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(complexityComboBox, (String)event.getNewValue(), "");
                complexityComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("name"))
                nameTF.setText((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("jetwayScale"))
            {
                scaleSpinner.removeChangeListener(this);
                scaleSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                scaleSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("instanceid"))
                instanceTF.setText((String)event.getNewValue());
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("jetwaysLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private JetwayModel model;
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
    private JComboBox gateNameComboBox;
    private JSpinner numberSpinner;
    private JComboBox complexityComboBox;
    private PopupTextField nameTF;
    private JSpinner scaleSpinner;
    private PopupTextField instanceTF;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel altIsAglLabel;
    private JLabel headingLabel;
    private JLabel pitchLabel;
    private JLabel bankLabel;
    private JLabel gateNameLabel;
    private JLabel numberLabel;
    private JLabel complexityLabel;
    private JLabel nameLabel;
    private JLabel scaleLabel;
    private JLabel instanceLabel;
}
