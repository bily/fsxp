// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultsPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class DefaultsPanel extends JPanel
    implements StartupPanel
{

    public DefaultsPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Default Measurement Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JLabel parkingLabel = new JLabel("Parking Spot Size:");
        parkingLabel.setFont(Utilities.LABEL_FONT);
        parkingLabel.setForeground(Color.black);
        parkingSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        parkingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(parkingSpinner, "0.0"));
        parkingSpinner.setValue(new Double(SettingsEngine.getInstance().getParkingSize()));
        parkingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        parkingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        parkingComboBox.setForeground(Color.black);
        parkingComboBox.setSelectedItem(SettingsEngine.getInstance().getParkingMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            parkingComboBox.setPrototypeDisplayValue("--------");
        JLabel tee1Label = new JLabel("Tee 1 Offset:");
        tee1Label.setFont(Utilities.LABEL_FONT);
        tee1Label.setForeground(Color.black);
        tee1Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee1Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee1Spinner, "00.00000"));
        tee1Spinner.setValue(new Double(SettingsEngine.getInstance().getTee1Offset()));
        JLabel tee2Label = new JLabel("Tee 2 Offset:");
        tee2Label.setFont(Utilities.LABEL_FONT);
        tee2Label.setForeground(Color.black);
        tee2Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee2Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee2Spinner, "00.00000"));
        tee2Spinner.setValue(new Double(SettingsEngine.getInstance().getTee2Offset()));
        JLabel tee3Label = new JLabel("Tee 3 Offset:");
        tee3Label.setFont(Utilities.LABEL_FONT);
        tee3Label.setForeground(Color.black);
        tee3Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee3Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee3Spinner, "00.00000"));
        tee3Spinner.setValue(new Double(SettingsEngine.getInstance().getTee3Offset()));
        JLabel tee4Label = new JLabel("Tee 4 Offset:");
        tee4Label.setFont(Utilities.LABEL_FONT);
        tee4Label.setForeground(Color.black);
        tee4Spinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 50D, 0.01D));
        tee4Spinner.setEditor(new javax.swing.JSpinner.NumberEditor(tee4Spinner, "00.00000"));
        tee4Spinner.setValue(new Double(SettingsEngine.getInstance().getTee4Offset()));
        JLabel taxiwayLabel = new JLabel("Taxiway Width:");
        taxiwayLabel.setFont(Utilities.LABEL_FONT);
        taxiwayLabel.setForeground(Color.black);
        taxiwaySpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        taxiwaySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiwaySpinner, "0.0"));
        taxiwaySpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayWidth()));
        taxiwayComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        taxiwayComboBox.setFont(Utilities.COMBO_BOX_FONT);
        taxiwayComboBox.setForeground(Color.black);
        taxiwayComboBox.setSelectedItem(SettingsEngine.getInstance().getTaxiwayMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            taxiwayComboBox.setPrototypeDisplayValue("--------");
        JLabel taxiWeightLabel = new JLabel("Taxiway Weight:");
        taxiWeightLabel.setFont(Utilities.LABEL_FONT);
        taxiWeightLabel.setForeground(Color.black);
        taxiWeightSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 10000000D, 10D));
        taxiWeightSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiWeightSpinner, "0.0"));
        taxiWeightSpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayWeight()));
        JLabel helipadLengthLabel = new JLabel("Helipad Length:");
        helipadLengthLabel.setFont(Utilities.LABEL_FONT);
        helipadLengthLabel.setForeground(Color.black);
        helipadLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        helipadLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(helipadLengthSpinner, "0.0"));
        helipadLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getHelipadLength()));
        helipadLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        helipadLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        helipadLengthComboBox.setForeground(Color.black);
        helipadLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getHelipadLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            helipadLengthComboBox.setPrototypeDisplayValue("--------");
        JLabel helipadWidthLabel = new JLabel("Helipad Width:");
        helipadWidthLabel.setFont(Utilities.LABEL_FONT);
        helipadWidthLabel.setForeground(Color.black);
        helipadWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        helipadWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(helipadWidthSpinner, "0.0"));
        helipadWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getHelipadWidth()));
        helipadWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        helipadWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        helipadWidthComboBox.setForeground(Color.black);
        helipadWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getHelipadWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            helipadWidthComboBox.setPrototypeDisplayValue("--------");
        JLabel taxiwayPointLabel = new JLabel("Taxiway Point Diameter:");
        taxiwayPointLabel.setFont(Utilities.LABEL_FONT);
        taxiwayPointLabel.setForeground(Color.black);
        taxiwayPointSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        taxiwayPointSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(taxiwayPointSpinner, "0.0"));
        taxiwayPointSpinner.setValue(new Double(SettingsEngine.getInstance().getTaxiwayPointDiameter()));
        taxiwayPointComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        taxiwayPointComboBox.setFont(Utilities.COMBO_BOX_FONT);
        taxiwayPointComboBox.setForeground(Color.black);
        taxiwayPointComboBox.setSelectedItem(SettingsEngine.getInstance().getTaxiwayPointMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            taxiwayPointComboBox.setPrototypeDisplayValue("--------");
        JLabel vertexPointLabel = new JLabel("Vertex Point Diameter:");
        vertexPointLabel.setFont(Utilities.LABEL_FONT);
        vertexPointLabel.setForeground(Color.black);
        vertexPointSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        vertexPointSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vertexPointSpinner, "0.0"));
        vertexPointSpinner.setValue(new Double(SettingsEngine.getInstance().getVertexPointDiameter()));
        vertexPointComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vertexPointComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vertexPointComboBox.setForeground(Color.black);
        vertexPointComboBox.setSelectedItem(SettingsEngine.getInstance().getVertexPointMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vertexPointComboBox.setPrototypeDisplayValue("--------");
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        Utilities.addComponent(mainPanel, parkingLabel, 0, 0, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, parkingSpinner, 1, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, parkingComboBox, 2, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee1Label, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee1Spinner, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee2Label, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee2Spinner, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee3Label, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee3Spinner, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee4Label, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, tee4Spinner, 1, 4, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayLabel, 0, 5, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwaySpinner, 1, 5, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayComboBox, 2, 5, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiWeightLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiWeightSpinner, 1, 6, 2, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayPointLabel, 0, 7, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayPointSpinner, 1, 7, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, taxiwayPointComboBox, 2, 7, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vertexPointLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vertexPointSpinner, 1, 8, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vertexPointComboBox, 2, 8, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadLengthLabel, 0, 9, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadLengthSpinner, 1, 9, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadLengthComboBox, 2, 9, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadWidthLabel, 0, 10, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadWidthSpinner, 1, 10, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, helipadWidthComboBox, 2, 10, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the Defaults tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, mainPanel, 0, 1, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 2, 1, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 4, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 5, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        SettingsEngine.getInstance().setParkingSize(((Double)parkingSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setParkingMeasure((String)parkingComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTee1Offset(((Double)tee1Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee2Offset(((Double)tee2Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee3Offset(((Double)tee3Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTee4Offset(((Double)tee4Spinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayWidth(((Double)taxiwaySpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayMeasure((String)taxiwayComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTaxiwayWeight(((Double)taxiWeightSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadLength(((Double)helipadLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadLengthMeasure((String)helipadLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setHelipadWidth(((Double)helipadWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setHelipadWidthMeasure((String)helipadWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setTaxiwayPointDiameter(((Double)taxiwayPointSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setTaxiwayPointMeasure((String)taxiwayPointComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVertexPointDiameter(((Double)vertexPointSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVertexPointMeasure((String)vertexPointComboBox.getSelectedItem());
        SettingsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JSpinner parkingSpinner;
    private JComboBox parkingComboBox;
    private JSpinner tee1Spinner;
    private JSpinner tee2Spinner;
    private JSpinner tee3Spinner;
    private JSpinner tee4Spinner;
    private JSpinner taxiwaySpinner;
    private JComboBox taxiwayComboBox;
    private JSpinner taxiWeightSpinner;
    private JSpinner helipadLengthSpinner;
    private JComboBox helipadLengthComboBox;
    private JSpinner helipadWidthSpinner;
    private JComboBox helipadWidthComboBox;
    private JSpinner taxiwayPointSpinner;
    private JComboBox taxiwayPointComboBox;
    private JSpinner vertexPointSpinner;
    private JComboBox vertexPointComboBox;
}
