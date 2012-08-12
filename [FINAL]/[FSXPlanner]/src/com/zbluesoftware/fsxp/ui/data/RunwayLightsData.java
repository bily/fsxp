// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayLightsData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.renderer.IndexNameRenderer;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RunwayLightsData extends JPanel
    implements ActionListener, ChangeListener, PropertyChangeListener
{

    public RunwayLightsData()
    {
        listeners = new Vector();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(5, 5, 5, 5));
        lightsLabel = new JLabel("Lights");
        lightsLabel.setFont(Utilities.BOLD_LABEL_FONT);
        lightsLabel.setForeground(Color.black);
        centerLightsLabel = new JLabel("Center Lights");
        centerLightsLabel.setFont(Utilities.LABEL_FONT);
        centerLightsLabel.setForeground(Color.black);
        centerLightsComboBox = new JComboBox(getLightIntensityModel());
        centerLightsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        centerLightsComboBox.setForeground(Color.black);
        centerLightsComboBox.setRenderer(new IndexNameRenderer());
        centerLightsComboBox.addActionListener(this);
        edgeLightsLabel = new JLabel("Edge Lights");
        edgeLightsLabel.setFont(Utilities.LABEL_FONT);
        edgeLightsLabel.setForeground(Color.black);
        edgeLightsComboBox = new JComboBox(getLightIntensityModel());
        edgeLightsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        edgeLightsComboBox.setForeground(Color.black);
        edgeLightsComboBox.setRenderer(new IndexNameRenderer());
        edgeLightsComboBox.addActionListener(this);
        centerRedLabel = new JLabel("Center Red");
        centerRedLabel.setFont(Utilities.LABEL_FONT);
        centerRedLabel.setForeground(Color.black);
        centerRedComboBox = new JComboBox(getTrueFalseModel());
        centerRedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        centerRedComboBox.setForeground(Color.black);
        centerRedComboBox.addActionListener(this);
        primaryApproachCB = new JCheckBox("Primary Approach Lights");
        primaryApproachCB.setFont(Utilities.BOLD_LABEL_FONT);
        primaryApproachCB.setForeground(Color.black);
        primaryApproachCB.addActionListener(this);
        pSystemLabel = new JLabel("System");
        pSystemLabel.setFont(Utilities.LABEL_FONT);
        pSystemLabel.setForeground(Color.black);
        pSystemLabel.setEnabled(false);
        pSystemComboBox = new JComboBox(getSystemModel());
        pSystemComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pSystemComboBox.setForeground(Color.black);
        pSystemComboBox.setEnabled(false);
        pSystemComboBox.addActionListener(this);
        pStrobesLabel = new JLabel("Strobes");
        pStrobesLabel.setFont(Utilities.LABEL_FONT);
        pStrobesLabel.setForeground(Color.black);
        pStrobesLabel.setEnabled(false);
        pStrobesSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        pStrobesSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(pStrobesSpinner, "0"));
        pStrobesSpinner.addChangeListener(this);
        pStrobesSpinner.setEnabled(false);
        pReilLabel = new JLabel("REI Lights");
        pReilLabel.setFont(Utilities.LABEL_FONT);
        pReilLabel.setForeground(Color.black);
        pReilLabel.setEnabled(false);
        pReilComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        pReilComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pReilComboBox.setForeground(Color.black);
        pReilComboBox.setEnabled(false);
        pReilComboBox.addActionListener(this);
        pTouchdownLabel = new JLabel("Touchdown Lights");
        pTouchdownLabel.setFont(Utilities.LABEL_FONT);
        pTouchdownLabel.setForeground(Color.black);
        pTouchdownLabel.setEnabled(false);
        pTouchdownComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        pTouchdownComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pTouchdownComboBox.setForeground(Color.black);
        pTouchdownComboBox.setEnabled(false);
        pTouchdownComboBox.addActionListener(this);
        pEndLightsLabel = new JLabel("End Lights");
        pEndLightsLabel.setFont(Utilities.LABEL_FONT);
        pEndLightsLabel.setForeground(Color.black);
        pEndLightsLabel.setEnabled(false);
        pEndLightsComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        pEndLightsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        pEndLightsComboBox.setForeground(Color.black);
        pEndLightsComboBox.setEnabled(false);
        pEndLightsComboBox.addActionListener(this);
        secondaryApproachCB = new JCheckBox("Secondary Approach Lights");
        secondaryApproachCB.setFont(Utilities.BOLD_LABEL_FONT);
        secondaryApproachCB.setForeground(Color.black);
        secondaryApproachCB.addActionListener(this);
        sSystemLabel = new JLabel("System");
        sSystemLabel.setFont(Utilities.LABEL_FONT);
        sSystemLabel.setForeground(Color.black);
        sSystemLabel.setEnabled(false);
        sSystemComboBox = new JComboBox(getSystemModel());
        sSystemComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sSystemComboBox.setForeground(Color.black);
        sSystemComboBox.setEnabled(false);
        sSystemComboBox.addActionListener(this);
        sStrobesLabel = new JLabel("Strobes");
        sStrobesLabel.setFont(Utilities.LABEL_FONT);
        sStrobesLabel.setForeground(Color.black);
        sStrobesLabel.setEnabled(false);
        sStrobesSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
        sStrobesSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(sStrobesSpinner, "0"));
        sStrobesSpinner.addChangeListener(this);
        sStrobesSpinner.setEnabled(false);
        sReilLabel = new JLabel("REI Lights");
        sReilLabel.setFont(Utilities.LABEL_FONT);
        sReilLabel.setForeground(Color.black);
        sReilLabel.setEnabled(false);
        sReilComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        sReilComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sReilComboBox.setForeground(Color.black);
        sReilComboBox.setEnabled(false);
        sReilComboBox.addActionListener(this);
        sTouchdownLabel = new JLabel("Touchdown Lights");
        sTouchdownLabel.setFont(Utilities.LABEL_FONT);
        sTouchdownLabel.setForeground(Color.black);
        sTouchdownLabel.setEnabled(false);
        sTouchdownComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        sTouchdownComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sTouchdownComboBox.setForeground(Color.black);
        sTouchdownComboBox.setEnabled(false);
        sTouchdownComboBox.addActionListener(this);
        sEndLightsLabel = new JLabel("End Lights");
        sEndLightsLabel.setFont(Utilities.LABEL_FONT);
        sEndLightsLabel.setForeground(Color.black);
        sEndLightsLabel.setEnabled(false);
        sEndLightsComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        sEndLightsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        sEndLightsComboBox.setForeground(Color.black);
        sEndLightsComboBox.setEnabled(false);
        sEndLightsComboBox.addActionListener(this);
        Utilities.addComponent(this, lightsLabel, 0, 0, 3, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, centerLightsLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, centerLightsComboBox, 1, 1, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, edgeLightsLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, edgeLightsComboBox, 1, 2, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, centerRedLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, centerRedComboBox, 1, 3, 1, 1, 2, 13, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, primaryApproachCB, 0, 4, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pSystemLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pSystemComboBox, 1, 5, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pStrobesLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pStrobesSpinner, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pReilLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pReilComboBox, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pTouchdownLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pTouchdownComboBox, 1, 8, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, pEndLightsLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, pEndLightsComboBox, 1, 9, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, secondaryApproachCB, 0, 10, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sSystemLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sSystemComboBox, 1, 11, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sStrobesLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sStrobesSpinner, 1, 12, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sReilLabel, 0, 13, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sReilComboBox, 1, 13, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sTouchdownLabel, 0, 14, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sTouchdownComboBox, 1, 14, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, sEndLightsLabel, 0, 15, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, sEndLightsComboBox, 1, 15, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 1, 16, 2, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    private DefaultComboBoxModel getLightIntensityModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        HashMap hashMap = new HashMap();
        hashMap.put("index", "NONE");
        hashMap.put("name", "NONE");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "LOW");
        hashMap.put("name", "LOW INTENSITY");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "MEDIUM");
        hashMap.put("name", "MEDIUM INTENSITY");
        model.addElement(hashMap);
        hashMap = new HashMap();
        hashMap.put("index", "HIGH");
        hashMap.put("name", "HIGH INTENSITY");
        model.addElement(hashMap);
        return model;
    }

    private DefaultComboBoxModel getSystemModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("NONE");
        model.addElement("ALSF1");
        model.addElement("ALSF2");
        model.addElement("CALVERT");
        model.addElement("CALVERT2");
        model.addElement("MALS");
        model.addElement("MALSF");
        model.addElement("MALSR");
        model.addElement("ODALS");
        model.addElement("RAIL");
        model.addElement("SALS");
        model.addElement("SALSF");
        model.addElement("SSALF");
        model.addElement("SSALR");
        model.addElement("SSALS");
        return model;
    }

    public void updateDisplay(RunwayModel model)
    {
        updateModel();
        if(this.model != null)
        {
            this.model.getLightsModel().removePropertyChangeListener(this);
            if(this.model.getPrimaryApproachLightsModel() != null)
                this.model.getPrimaryApproachLightsModel().removePropertyChangeListener(this);
            if(this.model.getSecondaryApproachLightsModel() != null)
                this.model.getSecondaryApproachLightsModel().removePropertyChangeListener(this);
        }
        this.model = model;
        centerLightsComboBox.removeActionListener(this);
        edgeLightsComboBox.removeActionListener(this);
        centerRedComboBox.removeActionListener(this);
        primaryApproachCB.removeActionListener(this);
        pSystemComboBox.removeActionListener(this);
        pStrobesSpinner.removeChangeListener(this);
        pReilComboBox.removeActionListener(this);
        pTouchdownComboBox.removeActionListener(this);
        pEndLightsComboBox.removeActionListener(this);
        secondaryApproachCB.removeActionListener(this);
        sSystemComboBox.removeActionListener(this);
        sStrobesSpinner.removeChangeListener(this);
        sReilComboBox.removeActionListener(this);
        sTouchdownComboBox.removeActionListener(this);
        sEndLightsComboBox.removeActionListener(this);
        if(model != null)
        {
            centerRedComboBox.setSelectedIndex(model.getLightsModel().getCenterRed() ? 0 : 1);
            model.getLightsModel().addPropertyChangeListener(this);
            Utilities.setCodeDescComboBox(centerLightsComboBox, model.getLightsModel().getCenter(), "NONE");
            Utilities.setCodeDescComboBox(edgeLightsComboBox, model.getLightsModel().getEdge(), "NONE");
            if(model.getPrimaryApproachLightsModel() == null)
            {
                primaryApproachCB.setSelected(false);
                pSystemComboBox.setSelectedIndex(0);
                pStrobesSpinner.setValue(new Integer(0));
                pReilComboBox.setSelectedIndex(0);
                pTouchdownComboBox.setSelectedIndex(0);
                pEndLightsComboBox.setSelectedIndex(0);
                setPrimaryApproachEnabled(false);
            } else
            {
                primaryApproachCB.setSelected(true);
                pSystemComboBox.setSelectedItem(model.getPrimaryApproachLightsModel().getSystem());
                pStrobesSpinner.setValue(new Integer(model.getPrimaryApproachLightsModel().getStrobes()));
                pReilComboBox.setSelectedIndex(model.getPrimaryApproachLightsModel().getReil() ? 0 : 1);
                pTouchdownComboBox.setSelectedIndex(model.getPrimaryApproachLightsModel().getTouchdown() ? 0 : 1);
                pEndLightsComboBox.setSelectedIndex(model.getPrimaryApproachLightsModel().getEndLights() ? 0 : 1);
                setPrimaryApproachEnabled(true);
                model.getPrimaryApproachLightsModel().addPropertyChangeListener(this);
            }
            if(model.getSecondaryApproachLightsModel() == null)
            {
                secondaryApproachCB.setSelected(false);
                sSystemComboBox.setSelectedIndex(0);
                sStrobesSpinner.setValue(new Integer(0));
                sReilComboBox.setSelectedIndex(0);
                sTouchdownComboBox.setSelectedIndex(0);
                sEndLightsComboBox.setSelectedIndex(0);
                setSecondaryApproachEnabled(false);
            } else
            {
                secondaryApproachCB.setSelected(true);
                sSystemComboBox.setSelectedItem(model.getSecondaryApproachLightsModel().getSystem());
                sStrobesSpinner.setValue(new Integer(model.getSecondaryApproachLightsModel().getStrobes()));
                sReilComboBox.setSelectedIndex(model.getSecondaryApproachLightsModel().getReil() ? 0 : 1);
                sTouchdownComboBox.setSelectedIndex(model.getSecondaryApproachLightsModel().getTouchdown() ? 0 : 1);
                sEndLightsComboBox.setSelectedIndex(model.getSecondaryApproachLightsModel().getEndLights() ? 0 : 1);
                setSecondaryApproachEnabled(true);
                model.getSecondaryApproachLightsModel().addPropertyChangeListener(this);
            }
        } else
        {
            centerLightsComboBox.setSelectedItem("NONE");
            edgeLightsComboBox.setSelectedItem("NONE");
            centerRedComboBox.setSelectedItem("FALSE");
            primaryApproachCB.setSelected(false);
            pSystemComboBox.setSelectedIndex(0);
            pStrobesSpinner.setValue(new Integer(0));
            pReilComboBox.setSelectedIndex(0);
            pTouchdownComboBox.setSelectedIndex(0);
            pEndLightsComboBox.setSelectedIndex(0);
            setPrimaryApproachEnabled(false);
            secondaryApproachCB.setSelected(false);
            sSystemComboBox.setSelectedIndex(0);
            sStrobesSpinner.setValue(new Integer(0));
            sReilComboBox.setSelectedIndex(0);
            sTouchdownComboBox.setSelectedIndex(0);
            sEndLightsComboBox.setSelectedIndex(0);
            setSecondaryApproachEnabled(false);
        }
        centerLightsComboBox.addActionListener(this);
        edgeLightsComboBox.addActionListener(this);
        centerRedComboBox.addActionListener(this);
        primaryApproachCB.addActionListener(this);
        pSystemComboBox.addActionListener(this);
        pStrobesSpinner.addChangeListener(this);
        pReilComboBox.addActionListener(this);
        pTouchdownComboBox.addActionListener(this);
        pEndLightsComboBox.addActionListener(this);
        secondaryApproachCB.addActionListener(this);
        sSystemComboBox.addActionListener(this);
        sStrobesSpinner.addChangeListener(this);
        sReilComboBox.addActionListener(this);
        sTouchdownComboBox.addActionListener(this);
        sEndLightsComboBox.addActionListener(this);
        boolean status = model != null;
        lightsLabel.setEnabled(status);
        centerLightsLabel.setEnabled(status);
        centerLightsComboBox.setEnabled(status);
        edgeLightsLabel.setEnabled(status);
        edgeLightsComboBox.setEnabled(status);
        centerRedLabel.setEnabled(status);
        centerRedComboBox.setEnabled(status);
        primaryApproachCB.setEnabled(status);
        secondaryApproachCB.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            model.getLightsModel().setCenter((String)((HashMap)centerLightsComboBox.getSelectedItem()).get("index"));
            model.getLightsModel().setEdge((String)((HashMap)edgeLightsComboBox.getSelectedItem()).get("index"));
            model.getLightsModel().setCenterRed(centerRedComboBox.getSelectedIndex() == 0);
        }
    }

    private void setPrimaryApproachEnabled(boolean status)
    {
        pSystemLabel.setEnabled(status);
        pSystemComboBox.setEnabled(status);
        pStrobesLabel.setEnabled(status);
        pStrobesSpinner.setEnabled(status);
        pReilLabel.setEnabled(status);
        pReilComboBox.setEnabled(status);
        pTouchdownLabel.setEnabled(status);
        pTouchdownComboBox.setEnabled(status);
        pEndLightsLabel.setEnabled(status);
        pEndLightsComboBox.setEnabled(status);
        if(!containsActionListener(primaryApproachCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getPrimaryApproachLightsModel() != null)
                model.getPrimaryApproachLightsModel().removePropertyChangeListener(this);
            ApproachLightsModel approachLightsModel = new ApproachLightsModel();
            approachLightsModel.addPropertyChangeListener(this);
            approachLightsModel.setEnd("PRIMARY");
            model.setPrimaryApproachLightsModel(approachLightsModel);
        } else
        {
            if(model.getPrimaryApproachLightsModel() != null)
                model.getPrimaryApproachLightsModel().removePropertyChangeListener(this);
            model.setPrimaryApproachLightsModel(null);
        }
        firePropertyChange("update-primaryApproachLights", new Boolean(!status), new Boolean(status));
    }

    private void setSecondaryApproachEnabled(boolean status)
    {
        sSystemLabel.setEnabled(status);
        sSystemComboBox.setEnabled(status);
        sStrobesLabel.setEnabled(status);
        sStrobesSpinner.setEnabled(status);
        sReilLabel.setEnabled(status);
        sReilComboBox.setEnabled(status);
        sTouchdownLabel.setEnabled(status);
        sTouchdownComboBox.setEnabled(status);
        sEndLightsLabel.setEnabled(status);
        sEndLightsComboBox.setEnabled(status);
        if(!containsActionListener(secondaryApproachCB.getActionListeners()))
            return;
        if(status)
        {
            if(model.getSecondaryApproachLightsModel() != null)
                model.getSecondaryApproachLightsModel().removePropertyChangeListener(this);
            ApproachLightsModel approachLightsModel = new ApproachLightsModel();
            approachLightsModel.addPropertyChangeListener(this);
            approachLightsModel.setEnd("SECONDARY");
            model.setSecondaryApproachLightsModel(approachLightsModel);
        } else
        {
            if(model.getSecondaryApproachLightsModel() != null)
                model.getSecondaryApproachLightsModel().removePropertyChangeListener(this);
            model.setSecondaryApproachLightsModel(null);
        }
        firePropertyChange("update-primaryApproachLights", new Boolean(!status), new Boolean(status));
    }

    private boolean containsActionListener(ActionListener list[])
    {
        for(int i = list.length - 1; i >= 0; i--)
            if(list[i] == this)
                return true;

        return false;
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
        if(event.getSource() == centerLightsComboBox)
        {
            firePropertyChange("update-centerLights", model.getLightsModel().getCenter(), (String)((HashMap)centerLightsComboBox.getSelectedItem()).get("index"));
            model.getLightsModel().setCenter((String)((HashMap)centerLightsComboBox.getSelectedItem()).get("index"));
        } else
        if(event.getSource() == edgeLightsComboBox)
        {
            firePropertyChange("update-edgeLights", model.getLightsModel().getEdge(), (String)((HashMap)edgeLightsComboBox.getSelectedItem()).get("index"));
            model.getLightsModel().setEdge((String)((HashMap)edgeLightsComboBox.getSelectedItem()).get("index"));
        } else
        if(event.getSource() == centerRedComboBox)
        {
            firePropertyChange("update-centerRed", new Boolean(model.getLightsModel().getCenterRed()), new Boolean(centerRedComboBox.getSelectedIndex() == 0));
            model.getLightsModel().setCenterRed(centerRedComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == primaryApproachCB)
            setPrimaryApproachEnabled(primaryApproachCB.isSelected());
        else
        if(event.getSource() == secondaryApproachCB)
            setSecondaryApproachEnabled(secondaryApproachCB.isSelected());
        else
        if(event.getSource() == pSystemComboBox)
            model.getPrimaryApproachLightsModel().setSystem((String)pSystemComboBox.getSelectedItem());
        else
        if(event.getSource() == pReilComboBox)
        {
            firePropertyChange("update-rei", new Boolean(model.getPrimaryApproachLightsModel().getReil()), new Boolean(pReilComboBox.getSelectedIndex() == 0));
            model.getPrimaryApproachLightsModel().setReil(pReilComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == pTouchdownComboBox)
        {
            firePropertyChange("update-touchdown", new Boolean(model.getPrimaryApproachLightsModel().getTouchdown()), new Boolean(pTouchdownComboBox.getSelectedIndex() == 0));
            model.getPrimaryApproachLightsModel().setTouchdown(pTouchdownComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == pEndLightsComboBox)
        {
            firePropertyChange("update-endLights", new Boolean(model.getPrimaryApproachLightsModel().getEndLights()), new Boolean(pEndLightsComboBox.getSelectedIndex() == 0));
            model.getPrimaryApproachLightsModel().setEndLights(pEndLightsComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == sSystemComboBox)
            model.getSecondaryApproachLightsModel().setSystem((String)sSystemComboBox.getSelectedItem());
        else
        if(event.getSource() == sReilComboBox)
        {
            firePropertyChange("update-rei", new Boolean(model.getSecondaryApproachLightsModel().getReil()), new Boolean(sReilComboBox.getSelectedIndex() == 0));
            model.getSecondaryApproachLightsModel().setReil(sReilComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == sTouchdownComboBox)
        {
            firePropertyChange("update-touchdown", new Boolean(model.getSecondaryApproachLightsModel().getTouchdown()), new Boolean(sTouchdownComboBox.getSelectedIndex() == 0));
            model.getSecondaryApproachLightsModel().setTouchdown(sTouchdownComboBox.getSelectedIndex() == 0);
        } else
        if(event.getSource() == sEndLightsComboBox)
        {
            firePropertyChange("update-endLights", new Boolean(model.getSecondaryApproachLightsModel().getEndLights()), new Boolean(sEndLightsComboBox.getSelectedIndex() == 0));
            model.getSecondaryApproachLightsModel().setEndLights(sEndLightsComboBox.getSelectedIndex() == 0);
        }
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == pStrobesSpinner)
            model.getPrimaryApproachLightsModel().setStrobes(((Integer)pStrobesSpinner.getValue()).intValue());
        else
        if(event.getSource() == sStrobesSpinner)
            model.getSecondaryApproachLightsModel().setStrobes(((Integer)sStrobesSpinner.getValue()).intValue());
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == model.getLightsModel())
        {
            if(event.getPropertyName().equals("center"))
            {
                centerLightsComboBox.removeActionListener(this);
                centerLightsComboBox.setSelectedItem((String)event.getNewValue());
                centerLightsComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("edge"))
            {
                edgeLightsComboBox.removeActionListener(this);
                edgeLightsComboBox.setSelectedItem((String)event.getNewValue());
                edgeLightsComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("centerRed"))
            {
                centerRedComboBox.removeActionListener(this);
                centerRedComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                centerRedComboBox.addActionListener(this);
            }
        } else
        if(event.getSource() == model.getPrimaryApproachLightsModel())
        {
            if(event.getPropertyName().equals("system"))
            {
                pSystemComboBox.removeActionListener(this);
                pSystemComboBox.setSelectedItem((String)event.getNewValue());
                pSystemComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("strobes"))
            {
                pStrobesSpinner.removeChangeListener(this);
                pStrobesSpinner.setValue((Integer)event.getNewValue());
                pStrobesSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("reil"))
            {
                pReilComboBox.removeActionListener(this);
                pReilComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                pReilComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("touchdown"))
            {
                pTouchdownComboBox.removeActionListener(this);
                pTouchdownComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                pTouchdownComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("endLights"))
            {
                pEndLightsComboBox.removeActionListener(this);
                pEndLightsComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                pEndLightsComboBox.addActionListener(this);
            }
        } else
        if(event.getSource() == model.getSecondaryApproachLightsModel())
            if(event.getPropertyName().equals("system"))
            {
                sSystemComboBox.removeActionListener(this);
                sSystemComboBox.setSelectedItem((String)event.getNewValue());
                sSystemComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("strobes"))
            {
                sStrobesSpinner.removeChangeListener(this);
                sStrobesSpinner.setValue((Integer)event.getNewValue());
                sStrobesSpinner.addChangeListener(this);
            } else
            if(event.getPropertyName().equals("reil"))
            {
                sReilComboBox.removeActionListener(this);
                sReilComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                sReilComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("touchdown"))
            {
                sTouchdownComboBox.removeActionListener(this);
                sTouchdownComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                sTouchdownComboBox.addActionListener(this);
            } else
            if(event.getPropertyName().equals("endLights"))
            {
                sEndLightsComboBox.removeActionListener(this);
                sEndLightsComboBox.setSelectedIndex(((Boolean)event.getNewValue()).booleanValue() ? 0 : 1);
                sEndLightsComboBox.addActionListener(this);
            }
    }

    private RunwayModel model;
    private Vector listeners;
    private JComboBox centerLightsComboBox;
    private JComboBox edgeLightsComboBox;
    private JComboBox centerRedComboBox;
    private JCheckBox primaryApproachCB;
    private JLabel pSystemLabel;
    private JComboBox pSystemComboBox;
    private JLabel pStrobesLabel;
    private JSpinner pStrobesSpinner;
    private JLabel pReilLabel;
    private JComboBox pReilComboBox;
    private JLabel pTouchdownLabel;
    private JComboBox pTouchdownComboBox;
    private JLabel pEndLightsLabel;
    private JComboBox pEndLightsComboBox;
    private JCheckBox secondaryApproachCB;
    private JLabel sSystemLabel;
    private JComboBox sSystemComboBox;
    private JLabel sStrobesLabel;
    private JSpinner sStrobesSpinner;
    private JLabel sReilLabel;
    private JComboBox sReilComboBox;
    private JLabel sTouchdownLabel;
    private JComboBox sTouchdownComboBox;
    private JLabel sEndLightsLabel;
    private JComboBox sEndLightsComboBox;
    private JLabel lightsLabel;
    private JLabel centerLightsLabel;
    private JLabel edgeLightsLabel;
    private JLabel centerRedLabel;
}
