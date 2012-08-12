// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SceneryData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.SceneryModel;
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

public class SceneryData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public SceneryData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Scenery Objects");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getSceneryLocked())
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
        if(LockingEngine.getInstance().getSceneryLocked())
            latTF.setBackground(Color.red);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getSceneryLocked())
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
        sceneryTypeLabel = new JLabel("Scenery Type");
        sceneryTypeLabel.setFont(Utilities.LABEL_FONT);
        sceneryTypeLabel.setForeground(Color.black);
        sceneryTypeComboBox = new JComboBox(getSceneryTypes());
        sceneryTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sceneryTypeComboBox.setForeground(Color.black);
        sceneryTypeComboBox.setRenderer(new IndexNameRenderer());
        sceneryTypeComboBox.addActionListener(this);
        nameLabel = new JLabel("Name (GUID)");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameLabel.setEnabled(false);
        nameTF = new PopupTextField(5);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.addActionListener(this);
        nameTF.addFocusListener(this);
        nameTF.setEnabled(false);
        scaleLabel = new JLabel("Scale");
        scaleLabel.setFont(Utilities.LABEL_FONT);
        scaleLabel.setForeground(Color.black);
        scaleSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 0.10000000000000001D, 100D, 0.5D));
        scaleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(scaleSpinner, "0.00"));
        scaleSpinner.addChangeListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglComboBox, 1, 9, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 11, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 11, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, pitchLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, pitchPanel, 1, 12, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, bankLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, bankPanel, 1, 13, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, complexityLabel, 0, 14, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, complexityComboBox, 1, 14, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryTypeLabel, 0, 15, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryTypeComboBox, 1, 15, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 16, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, scaleLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, scaleSpinner, 1, 17, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 18, 3, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Scenery Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        SceneryModel model = null;
        if(baseModel instanceof SceneryModel)
            model = (SceneryModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        altIsAglComboBox.removeActionListener(this);
        headingSlider.removeChangeListener(this);
        pitchSlider.removeChangeListener(this);
        bankSlider.removeChangeListener(this);
        complexityComboBox.removeActionListener(this);
        sceneryTypeComboBox.removeActionListener(this);
        scaleSpinner.removeChangeListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            altIsAglComboBox.setSelectedIndex(model.getAltitudeIsAgl() ? 0 : 1);
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            headingSlider.setValue((int)model.getHeading());
            pitchTF.setText((new StringBuilder()).append("").append(model.getPitch()).toString());
            pitchSlider.setValue((int)model.getPitch());
            bankTF.setText((new StringBuilder()).append("").append(model.getBank()).toString());
            bankSlider.setValue((int)model.getBank());
            nameTF.setText(model.getName());
            nameTF.setCaretPosition(0);
            scaleSpinner.setValue(new Double(model.getSceneryScale()));
            Utilities.setCodeDescComboBox(complexityComboBox, model.getImageComplexity(), "");
            Utilities.setCodeDescComboBox(sceneryTypeComboBox, model.getName(), "");
            model.addPropertyChangeListener(this);
            model.setDisplayName((String)((HashMap)sceneryTypeComboBox.getSelectedItem()).get("name"));
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            altIsAglComboBox.setSelectedIndex(1);
            headingTF.setText("0");
            headingSlider.setValue(0);
            pitchTF.setText("0");
            pitchSlider.setValue(0);
            bankTF.setText("0");
            bankSlider.setValue(0);
            nameTF.setText("{38d99426-a14e-4974-93be-59436c10e8b1}");
            nameTF.setCaretPosition(0);
            scaleSpinner.setValue(new Double(1.0D));
            complexityComboBox.setSelectedIndex(0);
            sceneryTypeComboBox.setSelectedIndex(1);
            status = false;
        }
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        altIsAglComboBox.addActionListener(this);
        headingSlider.addChangeListener(this);
        pitchSlider.addChangeListener(this);
        bankSlider.addChangeListener(this);
        complexityComboBox.addActionListener(this);
        sceneryTypeComboBox.addActionListener(this);
        scaleSpinner.addChangeListener(this);
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
        sceneryTypeLabel.setEnabled(status);
        sceneryTypeComboBox.setEnabled(status);
        scaleLabel.setEnabled(status);
        scaleSpinner.setEnabled(status);
        nameLabel.setEnabled(status && sceneryTypeComboBox.getSelectedIndex() == 0);
        nameTF.setEnabled(status && sceneryTypeComboBox.getSelectedIndex() == 0);
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
                scaleSpinner.commitEdit();
            }
            catch(ParseException pe)
            {
                LogEngine.getInstance().log(pe);
            }
        }
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

    private DefaultComboBoxModel getSceneryTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "");
        hashMap.put("name", "Custom Scenery Object");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{1ffdec49-78fa-4b0c-8d9c-b56e91b8fbe8}");
        hashMap.put("name", "Small Fuel Station 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{781c4106-8ca9-4925-af80-d075b2f713e9}");
        hashMap.put("name", "Small Fuel Station 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{88d34df2-94ba-4068-a5a0-59609aeecade}");
        hashMap.put("name", "Medium Fuel Station");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{0da416ee-c523-4d81-a562-9ce503f28468}");
        hashMap.put("name", "Medium Fuel Station w/Overhang");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{85ca15ba-4809-4e2f-91e8-cb289f16710a}");
        hashMap.put("name", "Kenmore Fuel Station 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{218b3cf4-b37b-4a84-8ef8-4d7db7ed1979}");
        hashMap.put("name", "Kenmore Fuel Station 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{f51fb5e4-b23b-4a8e-a515-4c92176cc179}");
        hashMap.put("name", "Large Fuel Tank");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{23ee29b9-2f04-47e8-abec-58877ef1fee8}");
        hashMap.put("name", "Fuel Dock");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{0627877e-8643-4436-8f91-b271f9b11b82}");
        hashMap.put("name", "Fuel Truck 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c545a2a3-e2ec-11d2-9c84-00105a0ce62a}");
        hashMap.put("name", "Fuel Truck 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{cd69fc27-8cf3-4fa0-aafd-67be3d24e9ef}");
        hashMap.put("name", "Fuel Truck 3");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{6de93ad2-31f3-4044-9ac5-74071ed4386b}");
        hashMap.put("name", "Fuel Truck 4");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{15265fec-26b5-4a44-8915-aee2a293afa3}");
        hashMap.put("name", "Military Fuel Truck");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{fe978b1b-6b2f-4898-9e5d-a008e8675ed4}");
        hashMap.put("name", "Localizer Antenna Array");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{5911c52a-dd21-4673-a3c7-c95c922e5aaf}");
        hashMap.put("name", "NDB DME Antenna");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{401cb2c5-1791-4757-81ea-163fcb6693e8}");
        hashMap.put("name", "NDB DME Tower");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{d5ee4aaa-9fe7-43d6-b0e4-195860aae99f}");
        hashMap.put("name", "NDB DME Shack 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{5a003f36-68dd-4f97-b0b3-616863779a85}");
        hashMap.put("name", "NDB DME Shack 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{127ae284-25ae-4ec3-bccf-e372bf7df5a3}");
        hashMap.put("name", "NDB Water");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{2fb72c66-290c-4727-b970-da20984cee83}");
        hashMap.put("name", "NDB Antenna");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{4808a0b0-fc56-4d43-917d-8620b36e8cfc}");
        hashMap.put("name", "NDB High Antenna");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{ffbf01ac-2d33-4444-869a-0e1e48cdf2c3}");
        hashMap.put("name", "General VOR");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{3a5affe1-5cb6-43e9-b5d3-00dfc3b86e78}");
        hashMap.put("name", "General VOR DME");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c545a272-e2ec-11d2-9c84-00105a0ce62a}");
        hashMap.put("name", "Small VOR 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{3893d694-f519-4314-acb6-fe0b3b0d283a}");
        hashMap.put("name", "Small VOR 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c0b537c3-808a-48ba-8eff-e1ca1a872e84}");
        hashMap.put("name", "Small VOR DME 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{047814f7-e832-45fc-b5a0-4f047f8234b0}");
        hashMap.put("name", "Small VOR DME 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{73286269-0380-11d3-9c85-00105a0ce62a}");
        hashMap.put("name", "ILS Transmitter");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c545a270-e2ec-11d2-9c84-00105a0ce62a}");
        hashMap.put("name", "Checkered Shed");
        model.addElement(hashMap);
        return model;
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
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Scenery Object?", "Confirm Delete...", 0) != 0)
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
        model.setBank(bank);
    }

    private void setSceneryType()
    {
        nameLabel.setEnabled(sceneryTypeComboBox.getSelectedIndex() == 0);
        nameTF.setEnabled(sceneryTypeComboBox.getSelectedIndex() == 0);
        nameTF.setText((String)((HashMap)sceneryTypeComboBox.getSelectedItem()).get("index"));
        nameTF.setCaretPosition(0);
        model.setName(nameTF.getText());
        model.setDisplayName((String)((HashMap)sceneryTypeComboBox.getSelectedItem()).get("name"));
        if(sceneryTypeComboBox.getSelectedIndex() == 0)
            nameTF.requestFocus();
        firePropertyChange("update-sceneryType", model.getDisplayName(), (String)((HashMap)sceneryTypeComboBox.getSelectedItem()).get("name"));
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
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setSceneryLocked(!LockingEngine.getInstance().getSceneryLocked());
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == sceneryTypeComboBox)
            setSceneryType();
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
        if(event.getSource() == altIsAglComboBox)
            model.setAltitudeIsAgl(altIsAglComboBox.getSelectedIndex() == 0);
        else
        if(event.getSource() == complexityComboBox)
        {
            firePropertyChange("update-complexity", model.getImageComplexity(), (String)((HashMap)complexityComboBox.getSelectedItem()).get("index"));
            model.setImageComplexity((String)((HashMap)complexityComboBox.getSelectedItem()).get("index"));
        } else
        if(event.getSource() == nameTF)
            model.setName(nameTF.getText());
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == pitchSlider && model != null)
        {
            pitchTF.setText((new StringBuilder()).append("").append(pitchSlider.getValue()).toString());
            model.setPitch(pitchSlider.getValue());
        } else
        if(event.getSource() == bankSlider && model != null)
        {
            bankTF.setText((new StringBuilder()).append("").append(bankSlider.getValue()).toString());
            model.setBank(bankSlider.getValue());
        } else
        if(event.getSource() == scaleSpinner && model != null)
            model.setSceneryScale(((Double)scaleSpinner.getValue()).floatValue());
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
            if(event.getPropertyName().equals("imageComplexity"))
            {
                complexityComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(complexityComboBox, (String)event.getNewValue(), "");
                complexityComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("name"))
            {
                nameTF.setText((String)event.getNewValue());
                sceneryTypeComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(sceneryTypeComboBox, (String)event.getNewValue(), "");
                sceneryTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("sceneryScale"))
            {
                scaleSpinner.removeChangeListener(this);
                scaleSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                scaleSpinner.addChangeListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("sceneryLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private SceneryModel model;
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
    private JComboBox sceneryTypeComboBox;
    private JLabel nameLabel;
    private PopupTextField nameTF;
    private JSpinner scaleSpinner;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel altIsAglLabel;
    private JLabel headingLabel;
    private JLabel pitchLabel;
    private JLabel bankLabel;
    private JLabel complexityLabel;
    private JLabel sceneryTypeLabel;
    private JLabel scaleLabel;
}
