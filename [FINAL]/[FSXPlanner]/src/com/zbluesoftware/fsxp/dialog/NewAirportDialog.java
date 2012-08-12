// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewAirportDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewAirportDialog extends JDialog
    implements ActionListener
{

    private NewAirportDialog(Frame parent)
    {
        super(parent, "Create Airport", true);
        JLabel enterLabel = new JLabel("Enter New Airport Information:");
        enterLabel.setFont(Utilities.BOLD_LABEL_FONT);
        enterLabel.setForeground(Color.black);
        JLabel identLabel = new JLabel("ICAO code:");
        identLabel.setFont(Utilities.LABEL_FONT);
        identLabel.setForeground(Color.black);
        identTF = new JTextField(20);
        identTF.setFont(Utilities.TEXT_FIELD_FONT);
        identTF.setForeground(Color.black);
        identTF.setDocument(new MaxLengthDocument(4));
        JLabel nameLabel = new JLabel("Airport Name:");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameTF = new JTextField(20);
        nameTF.setFont(Utilities.TEXT_FIELD_FONT);
        nameTF.setForeground(Color.black);
        nameTF.setDocument(new MaxLengthDocument(48));
        JLabel regionLabel = new JLabel("Global Region:");
        regionLabel.setFont(Utilities.LABEL_FONT);
        regionLabel.setForeground(Color.black);
        regionTF = new JTextField(20);
        regionTF.setFont(Utilities.TEXT_FIELD_FONT);
        regionTF.setForeground(Color.black);
        regionTF.setDocument(new MaxLengthDocument(48));
        JLabel countryLabel = new JLabel("Country/Region:");
        countryLabel.setFont(Utilities.LABEL_FONT);
        countryLabel.setForeground(Color.black);
        countryTF = new JTextField(20);
        countryTF.setFont(Utilities.TEXT_FIELD_FONT);
        countryTF.setForeground(Color.black);
        countryTF.setDocument(new MaxLengthDocument(48));
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(Utilities.LABEL_FONT);
        stateLabel.setForeground(Color.black);
        stateTF = new JTextField(20);
        stateTF.setFont(Utilities.TEXT_FIELD_FONT);
        stateTF.setForeground(Color.black);
        stateTF.setDocument(new MaxLengthDocument(48));
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(Utilities.LABEL_FONT);
        cityLabel.setForeground(Color.black);
        cityTF = new JTextField(20);
        cityTF.setFont(Utilities.TEXT_FIELD_FONT);
        cityTF.setForeground(Color.black);
        cityTF.setDocument(new MaxLengthDocument(48));
        JLabel altLabel = new JLabel("Altitude:");
        altLabel.setFont(Utilities.LABEL_FONT);
        altLabel.setForeground(Color.black);
        altSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        altSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(altSpinner, "0.0"));
        altComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        altComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altComboBox.setForeground(Color.black);
        altComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            altComboBox.setPrototypeDisplayValue("--------");
        JLabel latLabel = new JLabel("Latitude:");
        latLabel.setFont(Utilities.LABEL_FONT);
        latLabel.setForeground(Color.black);
        latTF = new JTextField(20);
        latTF.setFont(Utilities.TEXT_FIELD_FONT);
        latTF.setForeground(Color.black);
        JLabel lonLabel = new JLabel("Longitude:");
        lonLabel.setFont(Utilities.LABEL_FONT);
        lonLabel.setForeground(Color.black);
        lonTF = new JTextField(20);
        lonTF.setFont(Utilities.TEXT_FIELD_FONT);
        lonTF.setForeground(Color.black);
        JLabel magvarLabel = new JLabel("Magnetic Variation:");
        magvarLabel.setFont(Utilities.LABEL_FONT);
        magvarLabel.setForeground(Color.black);
        magvarSpinner = new JSpinner(new SpinnerNumberModel(0.0D, -360D, 360D, 1.0D));
        magvarSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(magvarSpinner, "0.00"));
        JLabel airportTRLabel = new JLabel("Airport Test Radius:");
        airportTRLabel.setFont(Utilities.LABEL_FONT);
        airportTRLabel.setForeground(Color.black);
        airportTRSpinner = new JSpinner(new SpinnerNumberModel(5000D, 1.0D, 100000D, 1.0D));
        airportTRSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(airportTRSpinner, "0.0"));
        airportTRComboBox = new JComboBox(new String[] {
            "M", "F", "N"
        });
        airportTRComboBox.setFont(Utilities.COMBO_BOX_FONT);
        airportTRComboBox.setForeground(Color.black);
        airportTRComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            airportTRComboBox.setPrototypeDisplayValue("--------");
        JLabel trafficLabel = new JLabel("Traffic Scalar:");
        trafficLabel.setFont(Utilities.LABEL_FONT);
        trafficLabel.setForeground(Color.black);
        trafficSpinner = new JSpinner(new SpinnerNumberModel(0.69999999999999996D, 0.01D, 1.0D, 0.01D));
        trafficSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(trafficSpinner, "0.00"));
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, enterLabel, 0, 0, 3, 1, 0, 17, new Insets(1, 1, 5, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, identLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, identTF, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, nameLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, cityLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, cityTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, stateLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, stateTF, 1, 4, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, countryLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, countryTF, 1, 5, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, regionLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, regionTF, 1, 6, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 7, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 7, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, latTF, 1, 8, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, lonLabel, 0, 9, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, lonTF, 1, 9, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, magvarLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, magvarSpinner, 1, 10, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRLabel, 0, 11, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRSpinner, 1, 11, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, airportTRComboBox, 2, 11, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, trafficLabel, 0, 12, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, trafficSpinner, 1, 12, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        createButton = new JButton("Create");
        createButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        createButton.setForeground(Color.black);
        createButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static AirportModel showDialog(Frame parent)
    {
        if(dialog == null)
            dialog = new NewAirportDialog(parent);
        dialog.resetDialog();
        dialog.setVisible(true);
        return dialog.getAirportModel();
    }

    public AirportModel getAirportModel()
    {
        return airportModel;
    }

    private void resetDialog()
    {
        altComboBox.removeActionListener(this);
        airportTRComboBox.removeActionListener(this);
        identTF.setText("");
        nameTF.setText("");
        regionTF.setText("");
        countryTF.setText("");
        stateTF.setText("");
        cityTF.setText("");
        altSpinner.setValue(new Double(0.0D));
        altComboBox.setSelectedItem("M");
        latTF.setText("");
        lonTF.setText("");
        magvarSpinner.setValue(new Double(0.0D));
        airportTRSpinner.setValue(new Double(5000D));
        airportTRComboBox.setSelectedItem("M");
        trafficSpinner.setValue(new Double(0.69999999999999996D));
        airportModel = null;
        altComboBox.addActionListener(this);
        airportTRComboBox.addActionListener(this);
    }

    private void updateAltMeasure()
    {
        if(SettingsEngine.getInstance().getAdjustMeasurements())
            if(((String)altComboBox.getSelectedItem()).equals("F"))
                altSpinner.setValue(new Double(((Double)altSpinner.getValue()).floatValue() * 3.28F));
            else
                altSpinner.setValue(new Double(((Double)altSpinner.getValue()).floatValue() / 3.28F));
    }

    private void updateAirportTRMeasure()
    {
        if(SettingsEngine.getInstance().getAdjustMeasurements())
            if(((String)airportTRComboBox.getSelectedItem()).equals("F"))
                airportTRSpinner.setValue(new Double(((Double)airportTRSpinner.getValue()).floatValue() * 3.28F));
            else
                airportTRSpinner.setValue(new Double(((Double)airportTRSpinner.getValue()).floatValue() / 3.28F));
    }

    private boolean createAirport()
    {
        if(latTF.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "You have not entered the airport's latitude", "No Latitude...", 0);
            latTF.requestFocus();
            return false;
        }
        if(lonTF.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "You have not entered the airport's longitude", "No Longitude...", 0);
            lonTF.requestFocus();
            return false;
        }
        if(identTF.getText().trim().length() == 0)
        {
            JOptionPane.showMessageDialog(this, "You have not entered the airport's ICAO", "No ICAO...", 0);
            identTF.requestFocus();
            return false;
        } else
        {
            airportModel = new AirportModel(parseLatLon());
            airportModel.setIdent(identTF.getText().trim());
            airportModel.setName(nameTF.getText().trim());
            airportModel.setRegion(regionTF.getText().trim());
            airportModel.setCountry(countryTF.getText().trim());
            airportModel.setState(stateTF.getText().trim());
            airportModel.setCity(cityTF.getText().trim());
            airportModel.setAlt(((Double)altSpinner.getValue()).doubleValue());
            airportModel.setAltMeasure((String)altComboBox.getSelectedItem());
            airportModel.setMagvar(((Double)magvarSpinner.getValue()).floatValue());
            airportModel.setAirportTestRadius(((Double)airportTRSpinner.getValue()).doubleValue());
            airportModel.setAirportTestRadiusMeasure((String)airportTRComboBox.getSelectedItem());
            airportModel.setTrafficScalar(((Double)trafficSpinner.getValue()).floatValue());
            return true;
        }
    }

    private LatLonPoint parseLatLon()
    {
        double lat = 0.0D;
        if(airportModel != null)
            airportModel.getLatLon().getLat();
        try
        {
            lat = Double.parseDouble(latTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            lat = 0.0D;
            if(airportModel != null)
                lat = airportModel.getLatLon().getLat();
        }
        if(lat < -90D)
            lat = -90D;
        else
        if(lat > 90D)
            lat = 90D;
        double lon = 0.0D;
        if(airportModel != null)
            airportModel.getLatLon().getLon();
        try
        {
            lon = Double.parseDouble(lonTF.getText());
        }
        catch(NumberFormatException nfe)
        {
            lon = 0.0D;
            if(airportModel != null)
                lon = airportModel.getLatLon().getLon();
        }
        if(lon < -180D)
            lon = -180D;
        else
        if(lon > 180D)
            lon = 180D;
        return new LatLonPoint(lat, lon);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == airportTRComboBox)
            updateAirportTRMeasure();
        else
        if(event.getSource() == createButton)
        {
            if(createAirport())
                setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
    }

    private AirportModel airportModel;
    private JTextField identTF;
    private JTextField nameTF;
    private JTextField regionTF;
    private JTextField countryTF;
    private JTextField stateTF;
    private JTextField cityTF;
    private JSpinner altSpinner;
    private JTextField latTF;
    private JTextField lonTF;
    private JSpinner magvarSpinner;
    private JSpinner airportTRSpinner;
    private JSpinner trafficSpinner;
    private JComboBox altComboBox;
    private JComboBox airportTRComboBox;
    private JButton createButton;
    private JButton cancelButton;
    private static NewAirportDialog dialog = null;

}
