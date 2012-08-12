// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExclusionData.java

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
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class ExclusionData extends ObjectData
    implements ActionListener, FocusListener, PropertyChangeListener
{

    public ExclusionData()
    {
        listeners = new Vector();
        setLayout(new BorderLayout(2, 2));
        lockingButton = new JButton(IconFactory.getInstance().getIcon("padlockUnlocked"));
        lockingButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        lockingButton.setToolTipText("Lock/Unlock Exclusion Rectangles");
        lockingButton.addActionListener(this);
        if(LockingEngine.getInstance().getExclusionLocked())
            lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
        deleteButton = new JButton(IconFactory.getInstance().getIcon("delete"));
        deleteButton.setBorder(new EmptyBorder(5, 5, 5, 5));
        deleteButton.setToolTipText("Delete");
        deleteButton.addActionListener(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(2));
        buttonPanel.add(lockingButton);
        buttonPanel.add(deleteButton);
        lat1Label = new JLabel("1st Vertex Latitude");
        lat1Label.setFont(Utilities.LABEL_FONT);
        lat1Label.setForeground(Color.black);
        lat1TF = new PopupTextField(20);
        lat1TF.setFont(Utilities.TEXT_FIELD_FONT);
        lat1TF.setForeground(Color.black);
        lat1TF.addActionListener(this);
        lat1TF.addFocusListener(this);
        if(LockingEngine.getInstance().getExclusionLocked())
            lat1TF.setBackground(Color.red);
        lon1Label = new JLabel("1st Vertex Longitude");
        lon1Label.setFont(Utilities.LABEL_FONT);
        lon1Label.setForeground(Color.black);
        lon1TF = new PopupTextField(20);
        lon1TF.setFont(Utilities.TEXT_FIELD_FONT);
        lon1TF.setForeground(Color.black);
        lon1TF.addActionListener(this);
        lon1TF.addFocusListener(this);
        if(LockingEngine.getInstance().getExclusionLocked())
            lon1TF.setBackground(Color.red);
        lat2Label = new JLabel("2nd Vertex Latitude");
        lat2Label.setFont(Utilities.LABEL_FONT);
        lat2Label.setForeground(Color.black);
        lat2TF = new PopupTextField(20);
        lat2TF.setFont(Utilities.TEXT_FIELD_FONT);
        lat2TF.setForeground(Color.black);
        lat2TF.addActionListener(this);
        lat2TF.addFocusListener(this);
        if(LockingEngine.getInstance().getExclusionLocked())
            lat2TF.setBackground(Color.red);
        lon2Label = new JLabel("2nd Vertex Longitude");
        lon2Label.setFont(Utilities.LABEL_FONT);
        lon2Label.setForeground(Color.black);
        lon2TF = new PopupTextField(20);
        lon2TF.setFont(Utilities.TEXT_FIELD_FONT);
        lon2TF.setForeground(Color.black);
        lon2TF.addActionListener(this);
        lon2TF.addFocusListener(this);
        if(LockingEngine.getInstance().getExclusionLocked())
            lat2TF.setBackground(Color.red);
        excludeAllCB = new JCheckBox("Exclude All Objects", true);
        excludeAllCB.setFont(Utilities.LABEL_FONT);
        excludeAllCB.setForeground(Color.black);
        excludeAllCB.addActionListener(this);
        excludeBeaconCB = new JCheckBox("Exclude All Beacon Objects", false);
        excludeBeaconCB.setFont(Utilities.LABEL_FONT);
        excludeBeaconCB.setForeground(Color.black);
        excludeBeaconCB.setEnabled(false);
        excludeBeaconCB.addActionListener(this);
        excludeEffectCB = new JCheckBox("Exclude All Effects Objects", false);
        excludeEffectCB.setFont(Utilities.LABEL_FONT);
        excludeEffectCB.setForeground(Color.black);
        excludeEffectCB.setEnabled(false);
        excludeEffectCB.addActionListener(this);
        excludeBridgeCB = new JCheckBox("Exclude All Extrusion Bridges", false);
        excludeBridgeCB.setFont(Utilities.LABEL_FONT);
        excludeBridgeCB.setForeground(Color.black);
        excludeBridgeCB.setEnabled(false);
        excludeBridgeCB.addActionListener(this);
        excludeBuildingCB = new JCheckBox("Exclude All Generic Buildings", false);
        excludeBuildingCB.setFont(Utilities.LABEL_FONT);
        excludeBuildingCB.setForeground(Color.black);
        excludeBuildingCB.setEnabled(false);
        excludeBuildingCB.addActionListener(this);
        excludeLibraryCB = new JCheckBox("Exclude All Library Objects", false);
        excludeLibraryCB.setFont(Utilities.LABEL_FONT);
        excludeLibraryCB.setForeground(Color.black);
        excludeLibraryCB.setEnabled(false);
        excludeLibraryCB.addActionListener(this);
        excludeSignCB = new JCheckBox("Exclude All Taxiway Signs", false);
        excludeSignCB.setFont(Utilities.LABEL_FONT);
        excludeSignCB.setForeground(Color.black);
        excludeSignCB.setEnabled(false);
        excludeSignCB.addActionListener(this);
        excludeTriggerCB = new JCheckBox("Exclude All Triggers Objects", false);
        excludeTriggerCB.setFont(Utilities.LABEL_FONT);
        excludeTriggerCB.setForeground(Color.black);
        excludeTriggerCB.setEnabled(false);
        excludeTriggerCB.addActionListener(this);
        excludeWindsockCB = new JCheckBox("Exclude All Windsocks", false);
        excludeWindsockCB.setFont(Utilities.LABEL_FONT);
        excludeWindsockCB.setForeground(Color.black);
        excludeWindsockCB.setEnabled(false);
        excludeWindsockCB.addActionListener(this);
        borderCB = new JCheckBox("Display Border Only", false);
        borderCB.setFont(Utilities.LABEL_FONT);
        borderCB.setForeground(Color.black);
        borderCB.setEnabled(false);
        borderCB.addActionListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, buttonPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lat1Label, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lat1TF, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lon1Label, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lon1TF, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lat2Label, 0, 3, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lat2TF, 1, 3, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lon2Label, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lon2TF, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, borderCB, 0, 5, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeAllCB, 0, 6, 2, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeBeaconCB, 0, 7, 2, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeEffectCB, 0, 8, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeBridgeCB, 0, 9, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeBuildingCB, 0, 10, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeLibraryCB, 0, 11, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeSignCB, 0, 12, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeTriggerCB, 0, 13, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludeWindsockCB, 0, 14, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 15, 2, 1, 3, 11, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Exclusion Rectangle Data");
        setBorder(windowBorder);
        LockingEngine.getInstance().addPropertyChangeListener(this);
    }

    private DefaultComboBoxModel getTrueFalseModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("TRUE");
        model.addElement("FALSE");
        return model;
    }

    public void updateDisplay(BaseModel baseModel)
    {
        ExclusionModel model = null;
        if(baseModel instanceof ExclusionModel)
            model = (ExclusionModel)baseModel;
        if(this.model != null)
        {
            this.model.removePropertyChangeListener(this);
            this.model.getVertex1().removePropertyChangeListener(this);
            this.model.getVertex2().removePropertyChangeListener(this);
        }
        updateModel();
        this.model = model;
        boolean status = true;
        borderCB.removeActionListener(this);
        excludeAllCB.removeActionListener(this);
        excludeBeaconCB.removeActionListener(this);
        excludeEffectCB.removeActionListener(this);
        excludeBridgeCB.removeActionListener(this);
        excludeBuildingCB.removeActionListener(this);
        excludeLibraryCB.removeActionListener(this);
        excludeSignCB.removeActionListener(this);
        excludeTriggerCB.removeActionListener(this);
        excludeWindsockCB.removeActionListener(this);
        if(model != null)
        {
            lat1TF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getVertex1().getLatLon().getLat()));
            lon1TF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getVertex1().getLatLon().getLon()));
            lat2TF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getVertex2().getLatLon().getLat()));
            lon2TF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getVertex2().getLatLon().getLon()));
            borderCB.setSelected(model.getDisplayBorderOnly());
            excludeAllCB.setSelected(model.getExcludeAllObjects());
            excludeBeaconCB.setSelected(model.getExcludeBeaconObjects());
            excludeEffectCB.setSelected(model.getExcludeEffectObjects());
            excludeBridgeCB.setSelected(model.getExcludeExtrusionBridgeObjects());
            excludeBuildingCB.setSelected(model.getExcludeGenericBuildingObjects());
            excludeLibraryCB.setSelected(model.getExcludeLibraryObjects());
            excludeSignCB.setSelected(model.getExcludeTaxiwaySignObjects());
            excludeTriggerCB.setSelected(model.getExcludeTriggerObjects());
            excludeWindsockCB.setSelected(model.getExcludeWindsockObjects());
            model.addPropertyChangeListener(this);
            model.getVertex1().addPropertyChangeListener(this);
            model.getVertex2().addPropertyChangeListener(this);
        } else
        {
            lat1TF.setText("");
            lon1TF.setText("");
            lat2TF.setText("");
            lon2TF.setText("");
            borderCB.setSelected(false);
            excludeAllCB.setSelected(true);
            excludeBeaconCB.setSelected(false);
            excludeEffectCB.setSelected(false);
            excludeBridgeCB.setSelected(false);
            excludeBuildingCB.setSelected(false);
            excludeLibraryCB.setSelected(false);
            excludeSignCB.setSelected(false);
            excludeTriggerCB.setSelected(false);
            excludeWindsockCB.setSelected(false);
            status = false;
        }
        setCheckboxesEnabled(!excludeAllCB.isSelected());
        borderCB.addActionListener(this);
        excludeAllCB.addActionListener(this);
        excludeBeaconCB.addActionListener(this);
        excludeEffectCB.addActionListener(this);
        excludeBridgeCB.addActionListener(this);
        excludeBuildingCB.addActionListener(this);
        excludeLibraryCB.addActionListener(this);
        excludeSignCB.addActionListener(this);
        excludeTriggerCB.addActionListener(this);
        excludeWindsockCB.addActionListener(this);
        deleteButton.setEnabled(status);
        lockingButton.setEnabled(status);
        lat1Label.setEnabled(status);
        lat1TF.setEnabled(status);
        lon1Label.setEnabled(status);
        lon1TF.setEnabled(status);
        lat2Label.setEnabled(status);
        lat2TF.setEnabled(status);
        lon2Label.setEnabled(status);
        lon2TF.setEnabled(status);
        excludeAllCB.setEnabled(status);
        excludeBeaconCB.setEnabled(status);
        excludeEffectCB.setEnabled(status);
        excludeBridgeCB.setEnabled(status);
        excludeBuildingCB.setEnabled(status);
        excludeLibraryCB.setEnabled(status);
        excludeSignCB.setEnabled(status);
        excludeTriggerCB.setEnabled(status);
        excludeWindsockCB.setEnabled(status);
        borderCB.setEnabled(status);
    }

    public void updateModel()
    {
    }

    private void setCheckboxesEnabled(boolean status)
    {
        excludeBeaconCB.setEnabled(status);
        excludeEffectCB.setEnabled(status);
        excludeBridgeCB.setEnabled(status);
        excludeBuildingCB.setEnabled(status);
        excludeLibraryCB.setEnabled(status);
        excludeSignCB.setEnabled(status);
        excludeTriggerCB.setEnabled(status);
        excludeWindsockCB.setEnabled(status);
    }

    private void delete()
    {
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Apron?", "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            firePropertyChange("delete", model, model);
            updateDisplay(null);
            return;
        }
    }

    private void updateLatitude(VertexModel vertexModel, JTextField textField)
    {
        if(((PopupTextField)textField).isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(textField.getText());
        if(lat == (1.0D / 0.0D))
            lat = vertexModel.getLatLon().getLat();
        textField.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        firePropertyChange("update-lat", new Double(vertexModel.getLatLon().getLat()), new Double(lat));
        vertexModel.setLatLon(new LatLonPoint(lat, vertexModel.getLatLon().getLon()));
    }

    private void updateLongitude(VertexModel vertexModel, JTextField textField)
    {
        if(((PopupTextField)textField).isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(textField.getText());
        if(lon == (1.0D / 0.0D))
            lon = vertexModel.getLatLon().getLon();
        textField.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        firePropertyChange("update-lon", new Double(vertexModel.getLatLon().getLon()), new Double(lon));
        vertexModel.setLatLon(new LatLonPoint(vertexModel.getLatLon().getLat(), lon));
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
        if(event.getSource() == deleteButton)
            delete();
        else
        if(event.getSource() == lockingButton)
            LockingEngine.getInstance().setExclusionLocked(!LockingEngine.getInstance().getExclusionLocked());
        else
        if(event.getSource() == lat1TF && model != null)
            updateLatitude(model.getVertex1(), lat1TF);
        else
        if(event.getSource() == lon1TF && model != null)
            updateLongitude(model.getVertex1(), lon1TF);
        else
        if(event.getSource() == lat2TF && model != null)
            updateLatitude(model.getVertex2(), lat2TF);
        else
        if(event.getSource() == lon2TF && model != null)
            updateLongitude(model.getVertex2(), lon2TF);
        else
        if(event.getSource() == excludeAllCB)
        {
            setCheckboxesEnabled(!excludeAllCB.isSelected());
            model.setExcludeAllObjects(excludeAllCB.isSelected());
        } else
        if(event.getSource() == excludeBeaconCB)
            model.setExcludeBeaconObjects(excludeBeaconCB.isSelected());
        else
        if(event.getSource() == excludeEffectCB)
            model.setExcludeEffectObjects(excludeEffectCB.isSelected());
        else
        if(event.getSource() == excludeBridgeCB)
            model.setExcludeExtrusionBridgeObjects(excludeBridgeCB.isSelected());
        else
        if(event.getSource() == excludeBuildingCB)
            model.setExcludeGenericBuildingObjects(excludeBuildingCB.isSelected());
        else
        if(event.getSource() == excludeLibraryCB)
            model.setExcludeLibraryObjects(excludeLibraryCB.isSelected());
        else
        if(event.getSource() == excludeSignCB)
            model.setExcludeTaxiwaySignObjects(excludeSignCB.isSelected());
        else
        if(event.getSource() == excludeTriggerCB)
            model.setExcludeTriggerObjects(excludeTriggerCB.isSelected());
        else
        if(event.getSource() == excludeWindsockCB)
            model.setExcludeWindsockObjects(excludeWindsockCB.isSelected());
        else
        if(event.getSource() == borderCB)
        {
            firePropertyChange("update-border", new Boolean(model.getDisplayBorderOnly()), new Boolean(borderCB.isSelected()));
            model.setDisplayBorderOnly(borderCB.isSelected());
        }
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == lat1TF && model != null)
            updateLatitude(model.getVertex1(), lat1TF);
        else
        if(event.getSource() == lon1TF && model != null)
            updateLongitude(model.getVertex1(), lon1TF);
        else
        if(event.getSource() == lat2TF && model != null)
            updateLatitude(model.getVertex2(), lat2TF);
        else
        if(event.getSource() == lon2TF && model != null)
            updateLongitude(model.getVertex2(), lon2TF);
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(model != null && event.getSource() == model.getVertex1())
        {
            if(event.getPropertyName().equals("latLon"))
            {
                lat1TF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lon1TF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            }
        } else
        if(model != null && event.getSource() == model.getVertex2())
        {
            if(event.getPropertyName().equals("latLon"))
            {
                lat2TF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
                lon2TF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
            }
        } else
        if(event.getSource() == model)
        {
            if(event.getPropertyName().equals("excludeAllObjects"))
            {
                excludeAllCB.removeActionListener(this);
                excludeAllCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                setCheckboxesEnabled(!excludeAllCB.isSelected());
                excludeAllCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeBeaconObjects"))
            {
                excludeBeaconCB.removeActionListener(this);
                excludeBeaconCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeBeaconCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeEffectObjects"))
            {
                excludeEffectCB.removeActionListener(this);
                excludeEffectCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeEffectCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeExtrusionBridgeObjects"))
            {
                excludeBridgeCB.removeActionListener(this);
                excludeBridgeCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeBridgeCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeGenericBuildingObjects"))
            {
                excludeBuildingCB.removeActionListener(this);
                excludeBuildingCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeBuildingCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeLibraryObjects"))
            {
                excludeLibraryCB.removeActionListener(this);
                excludeLibraryCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeLibraryCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeTaxiwaySignObjects"))
            {
                excludeSignCB.removeActionListener(this);
                excludeSignCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeSignCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeTriggerObjects"))
            {
                excludeTriggerCB.removeActionListener(this);
                excludeTriggerCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeTriggerCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("excludeWindsockObjects"))
            {
                excludeWindsockCB.removeActionListener(this);
                excludeWindsockCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                excludeWindsockCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("displayBorderOnly"))
            {
                borderCB.removeActionListener(this);
                borderCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                borderCB.addActionListener(this);
            }
        } else
        if((event.getSource() instanceof LockingEngine) && event.getPropertyName().equals("exclusionLocked"))
        {
            lat1TF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lon1TF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lat2TF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            lon2TF.setBackground(((Boolean)event.getNewValue()).booleanValue() ? Color.red : Color.white);
            if(((Boolean)event.getNewValue()).booleanValue())
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockLocked"));
            else
                lockingButton.setIcon(IconFactory.getInstance().getIcon("padlockUnlocked"));
        }
    }

    private ExclusionModel model;
    private Vector listeners;
    private JButton lockingButton;
    private JButton deleteButton;
    private PopupTextField lat1TF;
    private PopupTextField lon1TF;
    private PopupTextField lat2TF;
    private PopupTextField lon2TF;
    private JCheckBox excludeAllCB;
    private JCheckBox excludeBeaconCB;
    private JCheckBox excludeEffectCB;
    private JCheckBox excludeBridgeCB;
    private JCheckBox excludeBuildingCB;
    private JCheckBox excludeLibraryCB;
    private JCheckBox excludeSignCB;
    private JCheckBox excludeTriggerCB;
    private JCheckBox excludeWindsockCB;
    private JCheckBox borderCB;
    private JLabel lat1Label;
    private JLabel lon1Label;
    private JLabel lat2Label;
    private JLabel lon2Label;
}
