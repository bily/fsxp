// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TowerData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.TowerModel;
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

public class TowerData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public TowerData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Towers");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getTowersLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        JLabel viewLabel = new JLabel("Tower Viewpoint");
        viewLabel.setFont(Utilities.LABEL_FONT);
        viewLabel.setForeground(Color.black);
        JLabel latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        if(LockingEngine.getInstance().getTowersLocked())
            latTF.setBackground(Color.red);
        JLabel lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        if(LockingEngine.getInstance().getTowersLocked())
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
        sceneryCB = new JCheckBox("Display Tower Scenery Object", false);
        sceneryCB.setFont(Utilities.LABEL_FONT);
        sceneryCB.setForeground(Color.black);
        sceneryCB.addActionListener(this);
        synchCB = new JCheckBox("Synchronize to Viewpoint", true);
        synchCB.setFont(Utilities.LABEL_FONT);
        synchCB.setForeground(Color.black);
        synchCB.addActionListener(this);
        synchCB.setEnabled(false);
        sceneryLatLabel = new JLabel("Latitude");
        sceneryLatLabel.setFont(Utilities.LABEL_FONT);
        sceneryLatLabel.setForeground(Color.black);
        sceneryLatLabel.setEnabled(false);
        sceneryLatTF = new PopupTextField(20);
        sceneryLatTF.setFont(Utilities.TEXT_FIELD_FONT);
        sceneryLatTF.setForeground(Color.black);
        sceneryLatTF.addActionListener(this);
        sceneryLatTF.addFocusListener(this);
        sceneryLatTF.setEnabled(false);
        sceneryLonLabel = new JLabel("Longitude");
        sceneryLonLabel.setFont(Utilities.LABEL_FONT);
        sceneryLonLabel.setForeground(Color.black);
        sceneryLonLabel.setEnabled(false);
        sceneryLonTF = new PopupTextField(20);
        sceneryLonTF.setFont(Utilities.TEXT_FIELD_FONT);
        sceneryLonTF.setForeground(Color.black);
        sceneryLonTF.addActionListener(this);
        sceneryLonTF.addFocusListener(this);
        sceneryLonTF.setEnabled(false);
        sceneryAltLabel = new JLabel("Altitude");
        sceneryAltLabel.setFont(Utilities.LABEL_FONT);
        sceneryAltLabel.setForeground(Color.black);
        sceneryAltLabel.setEnabled(false);
        sceneryAltSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -5000D, 100000D, 1.0D));
        sceneryAltSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sceneryAltSpinner, "0.000"));
        sceneryAltSpinner.addChangeListener(this);
        sceneryAltSpinner.setEnabled(false);
        sceneryAltComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        sceneryAltComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sceneryAltComboBox.setForeground(Color.black);
        sceneryAltComboBox.addActionListener(this);
        sceneryAltComboBox.setEnabled(false);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            sceneryAltComboBox.setPrototypeDisplayValue("--------");
        altIsAglLabel = new JLabel("Altitude is AGL");
        altIsAglLabel.setFont(Utilities.LABEL_FONT);
        altIsAglLabel.setForeground(Color.black);
        altIsAglLabel.setEnabled(false);
        altIsAglComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        altIsAglComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altIsAglComboBox.setForeground(Color.black);
        altIsAglComboBox.addActionListener(this);
        altIsAglComboBox.setEnabled(false);
        headingLabel = new JLabel("Heading");
        headingLabel.setFont(Utilities.LABEL_FONT);
        headingLabel.setForeground(Color.black);
        headingLabel.setEnabled(false);
        headingTF = new PopupTextField(5);
        headingTF.setFont(Utilities.TEXT_FIELD_FONT);
        headingTF.setForeground(Color.black);
        headingTF.addActionListener(this);
        headingTF.addFocusListener(this);
        headingTF.setEnabled(false);
        headingSlider = new JSlider(0);
        headingSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        headingSlider.setPreferredSize(new Dimension(100, headingSlider.getPreferredSize().height));
        headingSlider.addChangeListener(this);
        headingSlider.setEnabled(false);
        JPanel headingPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(headingPanel, headingTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(headingPanel, headingSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        pitchLabel = new JLabel("Pitch");
        pitchLabel.setFont(Utilities.LABEL_FONT);
        pitchLabel.setForeground(Color.black);
        pitchLabel.setEnabled(false);
        pitchTF = new PopupTextField(5);
        pitchTF.setFont(Utilities.TEXT_FIELD_FONT);
        pitchTF.setForeground(Color.black);
        pitchTF.addActionListener(this);
        pitchTF.addFocusListener(this);
        pitchTF.setEnabled(false);
        pitchSlider = new JSlider(0);
        pitchSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        pitchSlider.setPreferredSize(new Dimension(100, pitchSlider.getPreferredSize().height));
        pitchSlider.addChangeListener(this);
        pitchSlider.setEnabled(false);
        JPanel pitchPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(pitchPanel, pitchTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(pitchPanel, pitchSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        bankLabel = new JLabel("Bank");
        bankLabel.setFont(Utilities.LABEL_FONT);
        bankLabel.setForeground(Color.black);
        bankLabel.setEnabled(false);
        bankTF = new PopupTextField(5);
        bankTF.setFont(Utilities.TEXT_FIELD_FONT);
        bankTF.setForeground(Color.black);
        bankTF.addActionListener(this);
        bankTF.addFocusListener(this);
        bankTF.setEnabled(false);
        bankSlider = new JSlider(0);
        bankSlider.setModel(new DefaultBoundedRangeModel(0, 1, 0, 360));
        bankSlider.setPreferredSize(new Dimension(100, bankSlider.getPreferredSize().height));
        bankSlider.addChangeListener(this);
        bankSlider.setEnabled(false);
        JPanel bankPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(bankPanel, bankTF, 0, 0, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(bankPanel, bankSlider, 1, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        complexityLabel = new JLabel("Display At");
        complexityLabel.setFont(Utilities.LABEL_FONT);
        complexityLabel.setForeground(Color.black);
        complexityLabel.setEnabled(false);
        complexityComboBox = new JComboBox(getComplexityModel());
        complexityComboBox.setFont(Utilities.COMBO_BOX_FONT);
        complexityComboBox.setForeground(Color.black);
        complexityComboBox.setRenderer(new IndexNameRenderer());
        complexityComboBox.addActionListener(this);
        complexityComboBox.setEnabled(false);
        towerTypeLabel = new JLabel("Tower Type");
        towerTypeLabel.setFont(Utilities.LABEL_FONT);
        towerTypeLabel.setForeground(Color.black);
        towerTypeLabel.setEnabled(false);
        towerTypeComboBox = new JComboBox(getTowerTypes());
        towerTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        towerTypeComboBox.setForeground(Color.black);
        towerTypeComboBox.setRenderer(new IndexNameRenderer());
        towerTypeComboBox.addActionListener(this);
        towerTypeComboBox.setEnabled(false);
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
        scaleLabel.setEnabled(false);
        scaleSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 0.10000000000000001D, 100D, 0.5D));
        scaleSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(scaleSpinner, "0.00"));
        scaleSpinner.addChangeListener(this);
        scaleSpinner.setEnabled(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 3, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, viewLabel, 0, 1, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryCB, 0, 5, 3, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryLatLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryLatTF, 1, 6, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryLonLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryLonTF, 1, 7, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryAltLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryAltSpinner, 1, 8, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, sceneryAltComboBox, 2, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altIsAglComboBox, 1, 9, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, synchCB, 1, 10, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 11, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 11, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, pitchLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, pitchPanel, 1, 12, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, bankLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, bankPanel, 1, 13, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, complexityLabel, 0, 14, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, complexityComboBox, 1, 14, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, towerTypeLabel, 0, 15, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, towerTypeComboBox, 1, 15, 2, 1, 2, 13, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 16, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 16, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, scaleLabel, 0, 17, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, scaleSpinner, 1, 17, 2, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 18, 3, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Tower Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        TowerModel model = null;
        if(baseModel instanceof TowerModel)
            model = (TowerModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        sceneryCB.removeActionListener(this);
        sceneryAltSpinner.removeChangeListener(this);
        sceneryAltComboBox.removeActionListener(this);
        altIsAglComboBox.removeActionListener(this);
        headingSlider.removeChangeListener(this);
        pitchSlider.removeChangeListener(this);
        bankSlider.removeChangeListener(this);
        complexityComboBox.removeActionListener(this);
        towerTypeComboBox.removeActionListener(this);
        scaleSpinner.removeChangeListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            sceneryCB.setSelected(model.getIncludesScenery());
            if(model.getIncludesScenery())
            {
                sceneryLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getSceneryLatLon().getLat()));
                sceneryLonTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getSceneryLatLon().getLon()));
                sceneryAltSpinner.setValue(new Double(model.getSceneryAlt()));
                sceneryAltComboBox.setSelectedItem(model.getSceneryAltMeasure());
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
                Utilities.setCodeDescComboBox(towerTypeComboBox, model.getName(), "");
            } else
            {
                sceneryLatTF.setText("");
                sceneryLonTF.setText("");
                sceneryAltSpinner.setValue(new Double(0.0D));
                sceneryAltComboBox.setSelectedItem("M");
                altIsAglComboBox.setSelectedIndex(0);
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
                towerTypeComboBox.setSelectedIndex(1);
            }
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            sceneryLatTF.setText("");
            sceneryLonTF.setText("");
            sceneryAltSpinner.setValue(new Double(0.0D));
            sceneryAltComboBox.setSelectedItem("M");
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
            towerTypeComboBox.setSelectedIndex(1);
            status = false;
        }
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        sceneryCB.addActionListener(this);
        sceneryAltSpinner.addChangeListener(this);
        sceneryAltComboBox.addActionListener(this);
        altIsAglComboBox.addActionListener(this);
        headingSlider.addChangeListener(this);
        pitchSlider.addChangeListener(this);
        bankSlider.addChangeListener(this);
        complexityComboBox.addActionListener(this);
        towerTypeComboBox.addActionListener(this);
        scaleSpinner.addChangeListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        setSceneryEnabled();
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
                sceneryAltSpinner.commitEdit();
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
            updateSceneryLatitude();
            updateSceneryLongitude();
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

    private DefaultComboBoxModel getTowerTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "");
        hashMap.put("name", "Custom Tower ");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{9d4a2f38-bcb7-4124-8de2-b2811d962858}");
        hashMap.put("name", "Large Tower 1A");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c1b936ad-a25e-42c3-ad48-2dbcb1a3b501}");
        hashMap.put("name", "Large Tower 1B");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{87d440f0-dcfb-42a3-9504-61cee8607fcd}");
        hashMap.put("name", "Large Tower 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{4a841b6d-4e56-412c-8b70-aa4867c72c21}");
        hashMap.put("name", "Large Tower 3");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{38d99426-a14e-4974-93be-59436c10e8b1}");
        hashMap.put("name", "Medium Tower 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{604b317e-3938-4a34-b50a-dc16324baf0f}");
        hashMap.put("name", "Medium Tower 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{f3509ea4-5817-46a5-9cd3-eda3c449f25c}");
        hashMap.put("name", "Medium Tower 3");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{dc6a2ada-3104-4fbc-9030-5b58906c3834}");
        hashMap.put("name", "Medium Tower 4");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{7e6ceef0-7ff4-4738-898f-293e4a5fedb6}");
        hashMap.put("name", "Small Tower 1");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{c29b0f6f-9365-4448-b049-4b3d78abe798}");
        hashMap.put("name", "Small Tower 2");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "{6c07357f-286b-417e-894e-764ba7b82a9b}");
        hashMap.put("name", "Temporary Tower");
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
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Tower?", "Confirm Delete...", 0) != 0)
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

    private void updateSceneryLatitude()
    {
        if(sceneryLatTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(sceneryLatTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = model.getSceneryLatLon().getLat();
        sceneryLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        model.setSceneryLatLon(new LatLonPoint(lat, model.getSceneryLatLon().getLon()));
    }

    private void updateSceneryLongitude()
    {
        if(sceneryLonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(sceneryLonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = model.getSceneryLatLon().getLon();
        sceneryLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        model.setSceneryLatLon(new LatLonPoint(model.getSceneryLatLon().getLat(), lon));
    }

    private void updateSceneryAltMeasure()
    {
        if(((String)sceneryAltComboBox.getSelectedItem()).equals(model.getSceneryAltMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(model.getSceneryAltMeasure().equals("M"))
                model.setSceneryAlt(model.getSceneryAlt() * 3.2799999713897705D);
            else
                model.setSceneryAlt(model.getSceneryAlt() / 3.2799999713897705D);
            sceneryAltSpinner.setValue(new Double(model.getSceneryAlt()));
        }
        model.setSceneryAltMeasure((String)sceneryAltComboBox.getSelectedItem());
    }

    private void setSceneryEnabled()
    {
        boolean status = sceneryCB.isSelected();
        synchCB.setEnabled(status);
        sceneryLatLabel.setEnabled(status && !synchCB.isSelected());
        sceneryLatTF.setEnabled(status && !synchCB.isSelected());
        sceneryLonLabel.setEnabled(status && !synchCB.isSelected());
        sceneryLonTF.setEnabled(status && !synchCB.isSelected());
        sceneryAltLabel.setEnabled(status);
        sceneryAltSpinner.setEnabled(status);
        sceneryAltComboBox.setEnabled(status);
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
        towerTypeLabel.setEnabled(status);
        towerTypeComboBox.setEnabled(status);
        nameLabel.setEnabled(status && towerTypeComboBox.getSelectedIndex() == 0);
        nameTF.setEnabled(status && towerTypeComboBox.getSelectedIndex() == 0);
        scaleLabel.setEnabled(status);
        scaleSpinner.setEnabled(status);
        if(model != null)
        {
            model.setIncludesScenery(status);
            if(synchCB.isSelected())
                synchTower();
        }
    }

    private void setTowerType()
    {
        nameLabel.setEnabled(towerTypeComboBox.getSelectedIndex() == 0);
        nameTF.setEnabled(towerTypeComboBox.getSelectedIndex() == 0);
        nameTF.setText((String)((HashMap)towerTypeComboBox.getSelectedItem()).get("index"));
        nameTF.setCaretPosition(0);
        model.setName(nameTF.getText());
        if(towerTypeComboBox.getSelectedIndex() == 0)
            nameTF.requestFocus();
    }

    private void synchTower()
    {
        model.setSceneryLatLon((LatLonPoint)model.getLatLon().clone());
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
            LockingEngine.getInstance().setTowersLocked(!LockingEngine.getInstance().getTowersLocked());
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == sceneryCB)
            setSceneryEnabled();
        else
        if(event.getSource() == towerTypeComboBox)
            setTowerType();
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
        if(event.getSource() == sceneryLatTF)
            updateSceneryLatitude();
        else
        if(event.getSource() == sceneryLonTF)
            updateSceneryLongitude();
        else
        if(event.getSource() == sceneryAltComboBox)
            updateSceneryAltMeasure();
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
        if(event.getSource() == synchCB)
        {
            setSceneryEnabled();
            if(synchCB.isSelected())
                synchTower();
        }
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
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
        if(event.getSource() == sceneryAltSpinner && model != null)
            model.setSceneryAlt(((Double)sceneryAltSpinner.getValue()).doubleValue());
        else
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
        if(event.getSource() == sceneryLatTF)
            updateSceneryLatitude();
        else
        if(event.getSource() == sceneryLonTF)
            updateSceneryLongitude();
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
        if(event.getSource() == nameTF)
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
                if(synchCB.isSelected())
                {
                    sceneryLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                    sceneryLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
                }
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
            if(event.getPropertyName().equals("sceneryLatLon"))
            {
                sceneryLatTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                sceneryLonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            } else
            if(event.getPropertyName().equals("sceneryAlt"))
            {
                sceneryAltSpinner.removeChangeListener(this);
                sceneryAltSpinner.setValue(event.getNewValue());
                sceneryAltSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("sceneryAltMeasure"))
                sceneryAltComboBox.setSelectedItem(event.getNewValue());
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
                towerTypeComboBox.removeActionListener(this);
                Utilities.setCodeDescComboBox(towerTypeComboBox, (String)event.getNewValue(), "");
                towerTypeComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("sceneryScale"))
            {
                scaleSpinner.removeChangeListener(this);
                scaleSpinner.setValue(new Double(((Float)event.getNewValue()).doubleValue()));
                scaleSpinner.addChangeListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("towersLocked"))
        {
            latTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lonTF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private TowerModel model;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JCheckBox sceneryCB;
    private JCheckBox synchCB;
    private JLabel sceneryLatLabel;
    private PopupTextField sceneryLatTF;
    private JLabel sceneryLonLabel;
    private PopupTextField sceneryLonTF;
    private JLabel sceneryAltLabel;
    private JSpinner sceneryAltSpinner;
    private JComboBox sceneryAltComboBox;
    private JLabel altIsAglLabel;
    private JComboBox altIsAglComboBox;
    private JLabel headingLabel;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JLabel pitchLabel;
    private PopupTextField pitchTF;
    private JSlider pitchSlider;
    private JLabel bankLabel;
    private PopupTextField bankTF;
    private JSlider bankSlider;
    private JLabel complexityLabel;
    private JComboBox complexityComboBox;
    private JLabel towerTypeLabel;
    private JComboBox towerTypeComboBox;
    private JLabel nameLabel;
    private PopupTextField nameTF;
    private JLabel scaleLabel;
    private JSpinner scaleSpinner;
}
