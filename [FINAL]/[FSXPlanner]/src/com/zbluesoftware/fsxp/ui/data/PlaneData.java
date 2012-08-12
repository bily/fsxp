// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlaneData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.PlaneModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.simconnect.PlaneDimensions;
import com.zbluesoftware.fsxp.thread.CursorFollowFSXTask;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.ui.data:
//            ObjectData

public class PlaneData extends ObjectData
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public PlaneData()
    {
        listeners = new Vector();
        cursorAutoFollowFSXTimer = null;
        setLayout(new BorderLayout(2, 2));
        connectLabel = new JLabel("Not Connected To FSX");
        connectLabel.setFont(Utilities.BOLD_LABEL_FONT);
        connectLabel.setForeground(Color.red);
        connectButton = new JButton("Connect");
        connectButton.setFont(Utilities.BUTTON_FONT);
        connectButton.setForeground(Color.black);
        connectButton.addActionListener(this);
        JPanel connectPanel = new JPanel(new FlowLayout(2));
        connectPanel.add(connectLabel);
        connectPanel.add(Box.createHorizontalStrut(10));
        connectPanel.add(connectButton);
        latLabel = new JLabel("Latitude");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        lonLabel = new JLabel("Longitude");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
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
        makeLabel = new JLabel("Plane Make");
        makeLabel.setFont(Utilities.LABEL_FONT);
        makeLabel.setForeground(Color.black);
        makeComboBox = new JComboBox(getPlaneMakes());
        makeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        makeComboBox.setForeground(Color.black);
        makeComboBox.addActionListener(this);
        typeLabel = new JLabel("Plane Type");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        typeLabel.setEnabled(false);
        typeComboBox = new JComboBox(getPlaneTypes());
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        typeComboBox.addActionListener(this);
        typeComboBox.setEnabled(false);
        widthLabel = new JLabel("Plane Width");
        widthLabel.setFont(Utilities.LABEL_FONT);
        widthLabel.setForeground(Color.black);
        widthLabel.setEnabled(false);
        widthSpinner = new JSpinner(new SpinnerNumberModel(10D, 10D, 750D, 1.0D));
        widthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(widthSpinner, "0.0"));
        widthSpinner.addChangeListener(this);
        widthSpinner.setEnabled(false);
        lengthLabel = new JLabel("Plane Length");
        lengthLabel.setFont(Utilities.LABEL_FONT);
        lengthLabel.setForeground(Color.black);
        lengthLabel.setEnabled(false);
        lengthSpinner = new JSpinner(new SpinnerNumberModel(10D, 10D, 750D, 1.0D));
        lengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(lengthSpinner, "0.0"));
        lengthSpinner.addChangeListener(this);
        lengthSpinner.setEnabled(false);
        fsxAutoFollowCB = new JCheckBox("FSX Plane auto follows cursor", FSXConnection.getInstance().getFSXAutoFollow());
        fsxAutoFollowCB.setFont(Utilities.LABEL_FONT);
        fsxAutoFollowCB.setForeground(Color.black);
        fsxAutoFollowCB.addActionListener(this);
        cursorAutoFollowCB = new JCheckBox("Cursor auto follows FSX plane", FSXConnection.getInstance().getCursorAutoFollow());
        cursorAutoFollowCB.setFont(Utilities.LABEL_FONT);
        cursorAutoFollowCB.setForeground(Color.black);
        cursorAutoFollowCB.addActionListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, connectPanel, 0, 0, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 1, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 1, 2, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 3, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingPanel, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, makeLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, makeComboBox, 1, 5, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, typeLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, widthLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, widthSpinner, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lengthLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lengthSpinner, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fsxAutoFollowCB, 0, 9, 2, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, cursorAutoFollowCB, 0, 10, 2, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 11, 3, 1, 3, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.5D);
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, "Center");
        windowBorder = new WindowBorder("Plane Data");
        setBorder(windowBorder);
        FSXConnection.getInstance().addPropertyChangeListener(this);
    }

    public void updateDisplay(BaseModel baseModel)
    {
        PlaneModel model = null;
        if(baseModel instanceof PlaneModel)
            model = (PlaneModel)baseModel;
        if(this.model != null)
            this.model.removePropertyChangeListener(this);
        updateModel();
        this.model = model;
        boolean status = true;
        altComboBox.removeActionListener(this);
        altSpinner.removeChangeListener(this);
        headingSlider.removeChangeListener(this);
        makeComboBox.removeActionListener(this);
        typeComboBox.removeActionListener(this);
        widthSpinner.removeChangeListener(this);
        lengthSpinner.removeChangeListener(this);
        if(model != null)
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(model.getLatLon().getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(model.getLatLon().getLon()));
            altSpinner.setValue(new Double(model.getAlt()));
            altComboBox.setSelectedItem(model.getAltMeasure());
            headingSlider.setValue((int)model.getHeading());
            headingTF.setText((new StringBuilder()).append("").append(model.getHeading()).toString());
            makeComboBox.setSelectedItem(model.getPlaneMake());
            typeComboBox.setSelectedItem(model.getPlaneType());
            widthSpinner.setValue(new Double(model.getWidth()));
            lengthSpinner.setValue(new Double(model.getLength()));
            model.addPropertyChangeListener(this);
        } else
        {
            latTF.setText("");
            lonTF.setText("");
            altSpinner.setValue(new Double(0.0D));
            altComboBox.setSelectedIndex(0);
            headingSlider.setValue(0);
            headingTF.setText("");
            makeComboBox.setSelectedItem("Cessna 172");
            typeComboBox.setSelectedItem("Airplane");
            widthSpinner.setValue(new Double(36D));
            lengthSpinner.setValue(new Double(26D));
            status = false;
        }
        altComboBox.addActionListener(this);
        altSpinner.addChangeListener(this);
        headingSlider.addChangeListener(this);
        makeComboBox.addActionListener(this);
        typeComboBox.addActionListener(this);
        widthSpinner.addChangeListener(this);
        lengthSpinner.addChangeListener(this);
        updateConnectionLabel(FSXConnection.getInstance().isConnectedToFSX());
        latLabel.setEnabled(status);
        latTF.setEnabled(status);
        lonLabel.setEnabled(status);
        lonTF.setEnabled(status);
        altLabel.setEnabled(status);
        altSpinner.setEnabled(status);
        altComboBox.setEnabled(status);
        headingLabel.setEnabled(status);
        headingTF.setEnabled(status);
        headingSlider.setEnabled(status);
        makeLabel.setEnabled(status);
        makeComboBox.setEnabled(status);
        typeLabel.setEnabled(status);
        typeComboBox.setEnabled(status);
        widthLabel.setEnabled(status);
        widthSpinner.setEnabled(status);
        lengthLabel.setEnabled(status);
        lengthSpinner.setEnabled(status);
        fsxAutoFollowCB.setEnabled(status);
        cursorAutoFollowCB.setEnabled(status);
    }

    public void updateModel()
    {
        if(model != null)
        {
            model.setAlt(((Double)altSpinner.getValue()).doubleValue());
            model.setAltMeasure((String)altComboBox.getSelectedItem());
        }
    }

    private DefaultComboBoxModel getPlaneMakes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Airbus A321");
        model.addElement("AirCreation Trike");
        model.addElement("Beechcraft Baron 58");
        model.addElement("Beechcraft Kingair 350");
        model.addElement("Bell Jet Ranger");
        model.addElement("Boeing 737");
        model.addElement("Boeing 747");
        model.addElement("Bombardier CRJ700");
        model.addElement("Bombardier LearJet");
        model.addElement("Cessna 172");
        model.addElement("Cessna C208B");
        model.addElement("de Havilland Beaver");
        model.addElement("DG Flugzeubau");
        model.addElement("Douglas DC-3");
        model.addElement("Extra 300S");
        model.addElement("Grumman Goose");
        model.addElement("Maule Orion");
        model.addElement("Mooney Bravo");
        model.addElement("Piper Cub");
        model.addElement("Robinson R22");
        model.addElement("Custom");
        return model;
    }

    private DefaultComboBoxModel getPlaneTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Airplane");
        model.addElement("Helicopter");
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

    private void updateAltMeasure()
    {
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

    private void updatePlaneMake(String planeMake, boolean update)
    {
        if(model == null)
            return;
        boolean enabled = planeMake.equals("Custom");
        typeLabel.setEnabled(enabled);
        typeComboBox.setEnabled(enabled);
        widthLabel.setEnabled(enabled);
        widthSpinner.setEnabled(enabled);
        lengthLabel.setEnabled(enabled);
        lengthSpinner.setEnabled(enabled);
        model.setPlaneMake(planeMake);
        if(!planeMake.equals("Custom"))
        {
            widthSpinner.setValue(Double.valueOf(PlaneDimensions.getInstance().getPlaneWidth(planeMake)));
            lengthSpinner.setValue(Double.valueOf(PlaneDimensions.getInstance().getPlaneLength(planeMake)));
            typeComboBox.setSelectedItem(PlaneDimensions.getInstance().getPlaneType(planeMake));
        }
        if(update)
        {
            FSXConnection.getInstance().setPlaneMake((String)makeComboBox.getSelectedItem());
            FSXConnection.getInstance().writePreferences();
        }
    }

    private void updateConnectionLabel(boolean connected)
    {
        if(connected)
        {
            connectLabel.setText("Connected To FSX");
            connectLabel.setForeground(new Color(0, 102, 0));
            connectButton.setText("Disconnect");
            if(cursorAutoFollowCB.isSelected())
            {
                if(cursorAutoFollowFSXTimer != null)
                    cursorAutoFollowFSXTimer.cancel();
                CursorFollowFSXTask task = new CursorFollowFSXTask();
                task.addPropertyChangeListener(this);
                cursorAutoFollowFSXTimer = new java.util.Timer();
                cursorAutoFollowFSXTimer.scheduleAtFixedRate(task, 0L, 1000L);
            }
        } else
        {
            connectLabel.setText("Not Connected To FSX");
            connectLabel.setForeground(Color.red);
            connectButton.setText("Connect");
            if(cursorAutoFollowFSXTimer != null)
            {
                cursorAutoFollowFSXTimer.cancel();
                cursorAutoFollowFSXTimer = null;
            }
        }
    }

    private void setCursorAutoFollowFSX()
    {
        FSXConnection.getInstance().setCursorAutoFollow(cursorAutoFollowCB.isSelected());
        FSXConnection.getInstance().writePreferences();
        if(cursorAutoFollowCB.isSelected() && FSXConnection.getInstance().isConnectedToFSX())
        {
            if(cursorAutoFollowFSXTimer != null)
                cursorAutoFollowFSXTimer.cancel();
            CursorFollowFSXTask task = new CursorFollowFSXTask();
            task.addPropertyChangeListener(this);
            cursorAutoFollowFSXTimer = new java.util.Timer();
            cursorAutoFollowFSXTimer.scheduleAtFixedRate(task, 0L, 1000L);
        } else
        if(cursorAutoFollowFSXTimer != null)
        {
            cursorAutoFollowFSXTimer.cancel();
            cursorAutoFollowFSXTimer = null;
        }
    }

    private void handleFSXConnection()
    {
        if(FSXConnection.getInstance().isConnectedToFSX())
        {
            if(!FSXConnection.getInstance().disconnectFromFSX())
                JOptionPane.showMessageDialog(this, "There was a problem disconnecting from FSX.", "Error...", 0);
        } else
        if(FSXConnection.getInstance().isLocalConnection() && !FSXConnection.getInstance().getSpecifyPort())
        {
            ArrayList portAL = FSXConnection.getInstance().runNetstat();
            if(portAL.size() == 0)
            {
                StringBuffer buffer = new StringBuffer();
                buffer.append("No open SimConnect ports were found.\n");
                buffer.append("Please make sure that FSX is running.\n");
                buffer.append("and that your connection properties are correct.\n");
                buffer.append("SimConnect -> FSX Connection Status.");
                JOptionPane.showMessageDialog(this, buffer.toString(), "No Port Found Error...", 0);
                return;
            }
            boolean connected = false;
            int i = 0;
            do
            {
                if(i >= portAL.size())
                    break;
                int port = ((Integer)portAL.get(i)).intValue();
                if(FSXConnection.getInstance().connectToFSX("127.0.0.1", port, false))
                {
                    connected = true;
                    FSXConnection.getInstance().setPort(port);
                    FSXConnection.getInstance().writePreferences();
                    break;
                }
                i++;
            } while(true);
            if(!connected)
            {
                StringBuffer buffer = new StringBuffer();
                buffer.append("There was a problem connecting to Flight Simulator X.\n");
                buffer.append("Please check to make sure your connection properties are correct.\n");
                buffer.append("SimConnect -> FSX Connection Status.");
                JOptionPane.showMessageDialog(this, buffer.toString(), "Connection Error...", 0);
            }
        } else
        if(!FSXConnection.getInstance().connectToFSX())
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append("There was a problem connecting to Flight Simulator X.\n");
            buffer.append("Please check to make sure your connection properties are correct.\n");
            buffer.append("SimConnect -> FSX Connection Status.");
            JOptionPane.showMessageDialog(this, buffer.toString(), "Connection Error...", 0);
        }
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
        if(event.getSource() == headingTF)
            updateHeading();
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == makeComboBox)
            updatePlaneMake((String)makeComboBox.getSelectedItem(), true);
        else
        if(event.getSource() == typeComboBox)
        {
            model.setPlaneType((String)typeComboBox.getSelectedItem());
            FSXConnection.getInstance().setPlaneType((String)typeComboBox.getSelectedItem());
            FSXConnection.getInstance().writePreferences();
        } else
        if(event.getSource() == fsxAutoFollowCB)
        {
            FSXConnection.getInstance().setFSXAutoFollow(fsxAutoFollowCB.isSelected());
            FSXConnection.getInstance().writePreferences();
        } else
        if(event.getSource() == cursorAutoFollowCB)
            setCursorAutoFollowFSX();
        else
        if(event.getSource() == connectButton)
            handleFSXConnection();
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == headingSlider && model != null)
        {
            headingTF.setText((new StringBuilder()).append("").append(headingSlider.getValue()).toString());
            firePropertyChange("update-heading", new Float(model.getHeading()), new Float(headingSlider.getValue()));
            model.setHeading(headingSlider.getValue());
        } else
        if(event.getSource() == altSpinner && model != null)
            model.setAlt(((Double)altSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == widthSpinner && model != null)
        {
            model.setWidth(((Double)widthSpinner.getValue()).floatValue());
            FSXConnection.getInstance().setPlaneWidth(model.getWidth());
            FSXConnection.getInstance().writePreferences();
            firePropertyChange("update-width", new Float(model.getWidth()), new Float(((Double)widthSpinner.getValue()).floatValue()));
        } else
        if(event.getSource() == lengthSpinner && model != null)
        {
            model.setLength(((Double)lengthSpinner.getValue()).floatValue());
            FSXConnection.getInstance().setPlaneLength(model.getLength());
            FSXConnection.getInstance().writePreferences();
            firePropertyChange("update-length", new Float(model.getLength()), new Float(((Double)lengthSpinner.getValue()).floatValue()));
        }
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
                altComboBox.setSelectedItem((String)event.getNewValue());
            else
            if(event.getPropertyName().equals("heading"))
            {
                headingTF.setText((new StringBuilder()).append("").append(((Float)event.getNewValue()).floatValue()).toString());
                headingSlider.removeChangeListener(this);
                headingSlider.setValue(((Float)event.getNewValue()).intValue());
                headingSlider.addChangeListener(this);
            }
        } else
        if(event.getSource() instanceof FSXConnection)
        {
            if(event.getPropertyName().equals("connectedToFSX"))
                updateConnectionLabel(((Boolean)event.getNewValue()).booleanValue());
            else
            if(event.getPropertyName().equals("fsxAutoFollow"))
            {
                fsxAutoFollowCB.removeActionListener(this);
                fsxAutoFollowCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                fsxAutoFollowCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("cursorAutoFollow"))
            {
                cursorAutoFollowCB.removeActionListener(this);
                cursorAutoFollowCB.setSelected(((Boolean)event.getNewValue()).booleanValue());
                cursorAutoFollowCB.addActionListener(this);
            } else
            if(event.getPropertyName().equals("planeMake"))
            {
                if(makeComboBox.isEnabled())
                {
                    makeComboBox.removeActionListener(this);
                    makeComboBox.setSelectedItem((String)event.getNewValue());
                    makeComboBox.addActionListener(this);
                }
                updatePlaneMake((String)event.getNewValue(), false);
            }
        } else
        if((event.getSource() instanceof CursorFollowFSXTask) && event.getPropertyName().equals("CursorFollowFSXTask"))
            firePropertyChange("update-position", "", "");
    }

    private PlaneModel model;
    private Vector listeners;
    private JLabel connectLabel;
    private JButton connectButton;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel altLabel;
    private JLabel headingLabel;
    private JLabel makeLabel;
    private JLabel widthLabel;
    private JLabel typeLabel;
    private JLabel lengthLabel;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JComboBox makeComboBox;
    private JComboBox typeComboBox;
    private JSpinner widthSpinner;
    private JSpinner lengthSpinner;
    private JCheckBox fsxAutoFollowCB;
    private JCheckBox cursorAutoFollowCB;
    private java.util.Timer cursorAutoFollowFSXTimer;
}
