// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.*;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AirportData extends JPanel
    implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener
{

    public AirportData(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        airportModel.addPropertyChangeListener(this);
        setLayout(new BorderLayout(2, 2));
        JLabel identLabel = new JLabel("ICAO code:");
        identLabel.setFont(Utilities.LABEL_FONT);
        identLabel.setForeground(Color.black);
        identTF = new PopupTextField(18);
        identTF.setFont(Utilities.TEXT_FIELD_FONT);
        identTF.setForeground(Color.black);
        identTF.setDocument(new MaxLengthDocument(4));
        identTF.setText(airportModel.getIdent());
        identTF.addActionListener(this);
        identTF.addFocusListener(this);
        JLabel nameLabel = new JLabel("Airport Name:");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new PopupTextField(18);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.setDocument(new MaxLengthDocument(48));
        nameTF.setText(airportModel.getName());
        nameTF.addActionListener(this);
        nameTF.addFocusListener(this);
        JLabel regionLabel = new JLabel("Global Region:");
        regionLabel.setFont(Utilities.LABEL_FONT);
        regionLabel.setForeground(Color.black);
        regionTF = new PopupTextField(18);
        regionTF.setFont(Utilities.TEXT_FIELD_FONT);
        regionTF.setForeground(Color.black);
        regionTF.setDocument(new MaxLengthDocument(48));
        regionTF.setText(airportModel.getRegion());
        regionTF.addActionListener(this);
        regionTF.addFocusListener(this);
        JLabel countryLabel = new JLabel("Country/Region:");
        countryLabel.setFont(Utilities.LABEL_FONT);
        countryLabel.setForeground(Color.black);
        countryTF = new PopupTextField(18);
        countryTF.setFont(Utilities.TEXT_FIELD_FONT);
        countryTF.setForeground(Color.black);
        countryTF.setDocument(new MaxLengthDocument(48));
        countryTF.setText(airportModel.getCountry());
        countryTF.addActionListener(this);
        countryTF.addFocusListener(this);
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(Utilities.LABEL_FONT);
        stateLabel.setForeground(Color.black);
        stateTF = new PopupTextField(18);
        stateTF.setFont(Utilities.TEXT_FIELD_FONT);
        stateTF.setForeground(Color.black);
        stateTF.setDocument(new MaxLengthDocument(48));
        stateTF.setText(airportModel.getState());
        stateTF.addActionListener(this);
        stateTF.addFocusListener(this);
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(Utilities.LABEL_FONT);
        cityLabel.setForeground(Color.black);
        cityTF = new PopupTextField(18);
        cityTF.setFont(Utilities.TEXT_FIELD_FONT);
        cityTF.setForeground(Color.black);
        cityTF.setDocument(new MaxLengthDocument(48));
        cityTF.setText(airportModel.getCity());
        cityTF.addActionListener(this);
        cityTF.addFocusListener(this);
        JLabel altLabel = new JLabel("Altitude:");
        altLabel.setFont(Utilities.LABEL_FONT);
        altLabel.setForeground(Color.black);
        altSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -5000D, 100000D, 1.0D));
        altSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(altSpinner, "0.000"));
        altSpinner.addChangeListener(this);
        altSpinner.setValue(new Double(airportModel.getAlt()));
        altComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        altComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altComboBox.setForeground(Color.black);
        altComboBox.setSelectedItem(airportModel.getAltMeasure());
        altComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            altComboBox.setPrototypeDisplayValue("--------");
        JLabel latLabel = new JLabel("Latitude:");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new PopupTextField(DisplayEngine.getInstance().formatObjectLatitude(airportModel.getLatLon().getLat()), 18);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        latTF.addActionListener(this);
        latTF.addFocusListener(this);
        JLabel lonLabel = new JLabel("Longitude:");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new PopupTextField(DisplayEngine.getInstance().formatObjectLongitude(airportModel.getLatLon().getLon()), 18);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        lonTF.addActionListener(this);
        lonTF.addFocusListener(this);
        JLabel magvarLabel = new JLabel("Magnetic Variation:");
        magvarLabel.setFont(Utilities.LABEL_FONT);
        magvarLabel.setForeground(Color.black);
        magvarSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -360D, 360D, 1.0D));
        magvarSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(magvarSpinner, "0.000"));
        magvarSpinner.addChangeListener(this);
        magvarSpinner.setValue(new Double(airportModel.getMagvar()));
        JLabel airportTRLabel = new JLabel("Airport Test Radius:");
        airportTRLabel.setFont(Utilities.LABEL_FONT);
        airportTRLabel.setForeground(Color.black);
        airportTRSpinner = new JSpinner(new SpinnerNumberModel(1.0D, 1.0D, 100000D, 1.0D));
        airportTRSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(airportTRSpinner, "0.000"));
        airportTRSpinner.addChangeListener(this);
        airportTRSpinner.setValue(new Double(airportModel.getAirportTestRadius()));
        airportTRComboBox = new JComboBox(new String[] {
            "M", "F", "N"
        });
        airportTRComboBox.setFont(Utilities.COMBO_BOX_FONT);
        airportTRComboBox.setForeground(Color.black);
        airportTRComboBox.setSelectedItem(airportModel.getAirportTestRadiusMeasure());
        airportTRComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            airportTRComboBox.setPrototypeDisplayValue("--------");
        JLabel trafficLabel = new JLabel("Traffic Scalar:");
        trafficLabel.setFont(Utilities.LABEL_FONT);
        trafficLabel.setForeground(Color.black);
        trafficSpinner = new JSpinner(new SpinnerNumberModel(0.01D, 0.01D, 1.0D, 0.01D));
        trafficSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(trafficSpinner, "0.00"));
        trafficSpinner.addChangeListener(this);
        trafficSpinner.setValue(new Double(airportModel.getTrafficScalar()));
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, identLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, identTF, 1, 0, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, cityLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, cityTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, stateLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, stateTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, countryLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, countryTF, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, regionLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, regionTF, 1, 5, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 6, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 7, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, magvarLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, magvarSpinner, 1, 9, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRSpinner, 1, 10, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRComboBox, 2, 10, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, trafficLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, trafficSpinner, 1, 11, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, Box.createGlue(), 0, 12, 3, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        add(mainPanel, "Center");
        windowBorder = new WindowBorder("Airport Data");
        setBorder(windowBorder);
    }

    public WindowBorder getWindowBorder()
    {
        return windowBorder;
    }

    private void updateLatitude()
    {
        if(latTF.isPopupDisplayed())
            return;
        double lat = ParseEngine.getInstance().parseLatitude(latTF.getText());
        if(lat == (1.0D / 0.0D))
            lat = airportModel.getLatLon().getLat();
        latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(lat));
        airportModel.setLatLon(new LatLonPoint(lat, airportModel.getLatLon().getLon()));
        ParseEngine.getInstance().parseLatitude(latTF.getText());
    }

    private void updateLongitude()
    {
        if(lonTF.isPopupDisplayed())
            return;
        double lon = ParseEngine.getInstance().parseLongitude(lonTF.getText());
        if(lon == (1.0D / 0.0D))
            lon = airportModel.getLatLon().getLon();
        lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(lon));
        airportModel.setLatLon(new LatLonPoint(airportModel.getLatLon().getLat(), lon));
    }

    private void updateAltMeasure()
    {
        if(((String)altComboBox.getSelectedItem()).equals(airportModel.getAltMeasure()))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(airportModel.getAltMeasure().equals("M"))
                airportModel.setAlt(airportModel.getAlt() * 3.2799999713897705D);
            else
                airportModel.setAlt(airportModel.getAlt() / 3.2799999713897705D);
            altSpinner.setValue(new Double(airportModel.getAlt()));
        }
        airportModel.setAltMeasure((String)altComboBox.getSelectedItem());
    }

    private void updateTestRadiusMeasure()
    {
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            if(airportModel.getAirportTestRadiusMeasure().equals("M"))
            {
                if(((String)airportTRComboBox.getSelectedItem()).equals("N"))
                    airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() / 1852D);
                else
                if(((String)airportTRComboBox.getSelectedItem()).equals("F"))
                    airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() * 3.2799999713897705D);
            } else
            if(airportModel.getAirportTestRadiusMeasure().equals("N"))
            {
                if(((String)airportTRComboBox.getSelectedItem()).equals("F"))
                    airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() * 3.2799999713897705D * 1852D);
                else
                if(((String)airportTRComboBox.getSelectedItem()).equals("M"))
                    airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() * 1852D);
            } else
            if(((String)airportTRComboBox.getSelectedItem()).equals("M"))
                airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() / 3.2799999713897705D);
            else
            if(((String)airportTRComboBox.getSelectedItem()).equals("N"))
                airportModel.setAirportTestRadius(airportModel.getAirportTestRadius() / 6074.56005859375D);
            airportTRSpinner.setValue(new Double(airportModel.getAirportTestRadius()));
        }
        airportModel.setAirportTestRadiusMeasure((String)airportTRComboBox.getSelectedItem());
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == identTF)
            airportModel.setIdent(identTF.getText());
        else
        if(event.getSource() == nameTF)
            airportModel.setName(nameTF.getText());
        else
        if(event.getSource() == regionTF)
            airportModel.setRegion(regionTF.getText());
        else
        if(event.getSource() == countryTF)
            airportModel.setCountry(countryTF.getText());
        else
        if(event.getSource() == stateTF)
            airportModel.setState(stateTF.getText());
        else
        if(event.getSource() == cityTF)
            airportModel.setCity(cityTF.getText());
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == airportTRComboBox)
            updateTestRadiusMeasure();
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
    }

    public void stateChanged(ChangeEvent event)
    {
        if(event.getSource() == altSpinner && airportModel != null)
            airportModel.setAlt(((Double)altSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == airportTRSpinner && airportModel != null)
            airportModel.setAirportTestRadius(((Double)airportTRSpinner.getValue()).doubleValue());
        else
        if(event.getSource() == trafficSpinner && airportModel != null)
            airportModel.setTrafficScalar(((Double)trafficSpinner.getValue()).floatValue());
        else
        if(event.getSource() == magvarSpinner && airportModel != null)
            airportModel.setMagvar(((Double)magvarSpinner.getValue()).floatValue());
    }

    public void focusGained(FocusEvent focusevent)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if(event.getSource() == identTF)
            airportModel.setIdent(identTF.getText());
        else
        if(event.getSource() == nameTF)
            airportModel.setName(nameTF.getText());
        else
        if(event.getSource() == regionTF)
            airportModel.setRegion(regionTF.getText());
        else
        if(event.getSource() == countryTF)
            airportModel.setCountry(countryTF.getText());
        else
        if(event.getSource() == stateTF)
            airportModel.setState(stateTF.getText());
        else
        if(event.getSource() == cityTF)
            airportModel.setCity(cityTF.getText());
        else
        if(event.getSource() == latTF)
            updateLatitude();
        else
        if(event.getSource() == lonTF)
            updateLongitude();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == airportModel && event.getPropertyName().equals("latLon"))
        {
            latTF.setText(DisplayEngine.getInstance().formatObjectLatitude(((LatLonPoint)event.getNewValue()).getLat()));
            lonTF.setText(DisplayEngine.getInstance().formatObjectLongitude(((LatLonPoint)event.getNewValue()).getLon()));
        }
    }

    private AirportModel airportModel;
    private PopupTextField identTF;
    private PopupTextField nameTF;
    private PopupTextField regionTF;
    private PopupTextField countryTF;
    private PopupTextField stateTF;
    private PopupTextField cityTF;
    private JSpinner altSpinner;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner magvarSpinner;
    private JSpinner airportTRSpinner;
    private JSpinner trafficSpinner;
    private JComboBox altComboBox;
    private JComboBox airportTRComboBox;
    private WindowBorder windowBorder;
}
