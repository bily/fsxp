// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class RunwayPanel extends JPanel
    implements ActionListener, StartupPanel
{

    public RunwayPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Default Runway Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JLabel runwayLengthLabel = new JLabel("Runway Length:");
        runwayLengthLabel.setFont(Utilities.LABEL_FONT);
        runwayLengthLabel.setForeground(Color.black);
        runwayLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        runwayLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(runwayLengthSpinner, "000.0"));
        runwayLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayLength()));
        runwayLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        runwayLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayLengthComboBox.setForeground(Color.black);
        runwayLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            runwayLengthComboBox.setPrototypeDisplayValue("--------");
        JLabel runwayWidthLabel = new JLabel("Runway Width:");
        runwayWidthLabel.setFont(Utilities.LABEL_FONT);
        runwayWidthLabel.setForeground(Color.black);
        runwayWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        runwayWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(runwayWidthSpinner, "0.0"));
        runwayWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getRunwayWidth()));
        runwayWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        runwayWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayWidthComboBox.setForeground(Color.black);
        runwayWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getRunwayWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            runwayWidthComboBox.setPrototypeDisplayValue("--------");
        JLabel vasiXLabel = new JLabel("VASI Bias X:");
        vasiXLabel.setFont(Utilities.LABEL_FONT);
        vasiXLabel.setForeground(Color.black);
        vasiXSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiXSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiXSpinner, "0.000"));
        vasiXSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiX()));
        vasiXComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiXComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiXComboBox.setForeground(Color.black);
        vasiXComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiXMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiXComboBox.setPrototypeDisplayValue("--------");
        JLabel vasiZLabel = new JLabel("VASI Bias Z:");
        vasiZLabel.setFont(Utilities.LABEL_FONT);
        vasiZLabel.setForeground(Color.black);
        vasiZSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiZSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiZSpinner, "0.000"));
        vasiZSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiZ()));
        vasiZComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiZComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiZComboBox.setForeground(Color.black);
        vasiZComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiZMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiZComboBox.setPrototypeDisplayValue("--------");
        vasiZEndComboBox = new JComboBox(new String[] {
            "from center", "from end"
        });
        vasiZEndComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiZEndComboBox.setForeground(Color.black);
        vasiZEndComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiZEnd());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiZEndComboBox.setPrototypeDisplayValue("------------------------");
        JLabel vasiSpacingLabel = new JLabel("VASI Spacing:");
        vasiSpacingLabel.setFont(Utilities.LABEL_FONT);
        vasiSpacingLabel.setForeground(Color.black);
        vasiSpacingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        vasiSpacingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(vasiSpacingSpinner, "0.000"));
        vasiSpacingSpinner.setValue(new Double(SettingsEngine.getInstance().getVasiSpacing()));
        vasiSpacingComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        vasiSpacingComboBox.setFont(Utilities.COMBO_BOX_FONT);
        vasiSpacingComboBox.setForeground(Color.black);
        vasiSpacingComboBox.setSelectedItem(SettingsEngine.getInstance().getVasiSpacingMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            vasiSpacingComboBox.setPrototypeDisplayValue("--------");
        JLabel offsetLabel = new JLabel("Offset Threshold / Blast Pad / Overrun Defaults:");
        offsetLabel.setFont(Utilities.BOLD_LABEL_FONT);
        offsetLabel.setForeground(Color.black);
        JLabel offsetLengthLabel = new JLabel("Length");
        offsetLengthLabel.setFont(Utilities.LABEL_FONT);
        offsetLengthLabel.setForeground(Color.black);
        offsetLengthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        offsetLengthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(offsetLengthSpinner, "0.000"));
        offsetLengthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetLength()));
        offsetLengthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        offsetLengthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetLengthComboBox.setForeground(Color.black);
        offsetLengthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetLengthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            offsetLengthComboBox.setPrototypeDisplayValue("--------");
        offsetWidthRunwayRB = new JRadioButton("Same Width As Runway", SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthRunwayRB.setFont(Utilities.LABEL_FONT);
        offsetWidthRunwayRB.setForeground(Color.black);
        offsetWidthRunwayRB.addActionListener(this);
        offsetWidthRunwayRB.setOpaque(false);
        offsetWidthSpecificRB = new JRadioButton("Specific Width", !SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthSpecificRB.setFont(Utilities.LABEL_FONT);
        offsetWidthSpecificRB.setForeground(Color.black);
        offsetWidthSpecificRB.addActionListener(this);
        offsetWidthSpecificRB.setOpaque(false);
        ButtonGroup widthBG = new ButtonGroup();
        widthBG.add(offsetWidthSpecificRB);
        widthBG.add(offsetWidthRunwayRB);
        offsetWidthSpinner = new JSpinner(new SpinnerNumberModel(2D, 2D, 10000D, 1.0D));
        offsetWidthSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(offsetWidthSpinner, "0.000"));
        offsetWidthSpinner.setValue(new Double(SettingsEngine.getInstance().getOffsetWidth()));
        offsetWidthSpinner.setEnabled(!SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        offsetWidthComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetWidthComboBox.setForeground(Color.black);
        offsetWidthComboBox.setEnabled(!SettingsEngine.getInstance().getOffsetWidthRunway());
        offsetWidthComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetWidthMeasure());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            offsetWidthComboBox.setPrototypeDisplayValue("--------");
        offsetSurfaceRunwayRB = new JRadioButton("Same Surface As Runway", SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceRunwayRB.setFont(Utilities.LABEL_FONT);
        offsetSurfaceRunwayRB.setForeground(Color.black);
        offsetSurfaceRunwayRB.addActionListener(this);
        offsetSurfaceRunwayRB.setOpaque(false);
        offsetSurfaceSpecificRB = new JRadioButton("Specific Surface", !SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceSpecificRB.setFont(Utilities.LABEL_FONT);
        offsetSurfaceSpecificRB.setForeground(Color.black);
        offsetSurfaceSpecificRB.addActionListener(this);
        offsetSurfaceSpecificRB.setOpaque(false);
        ButtonGroup surfaceBG = new ButtonGroup();
        surfaceBG.add(offsetSurfaceRunwayRB);
        surfaceBG.add(offsetSurfaceSpecificRB);
        offsetSurfaceComboBox = new JComboBox(getSurfaceModel());
        offsetSurfaceComboBox.setFont(Utilities.COMBO_BOX_FONT);
        offsetSurfaceComboBox.setForeground(Color.black);
        offsetSurfaceComboBox.setEnabled(!SettingsEngine.getInstance().getOffsetSurfaceRunway());
        offsetSurfaceComboBox.setSelectedItem(SettingsEngine.getInstance().getOffsetSurface());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        Utilities.addComponent(mainPanel, runwayLengthLabel, 0, 0, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayLengthSpinner, 1, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayLengthComboBox, 2, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayWidthLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayWidthSpinner, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayWidthComboBox, 2, 1, 1, 1, 0, 17, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiXLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiXSpinner, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiXComboBox, 2, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiZLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiZSpinner, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiZComboBox, 2, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiZEndComboBox, 3, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiSpacingLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiSpacingSpinner, 1, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, vasiSpacingComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetLabel, 0, 5, 4, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetLengthLabel, 0, 6, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetLengthSpinner, 1, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetLengthComboBox, 2, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetWidthSpecificRB, 0, 7, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetWidthSpinner, 1, 7, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetWidthComboBox, 2, 7, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetWidthRunwayRB, 0, 8, 4, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetSurfaceSpecificRB, 0, 9, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetSurfaceComboBox, 1, 9, 2, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, offsetSurfaceRunwayRB, 0, 10, 4, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the Runway tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, mainPanel, 0, 1, 1, 1, 0, 10, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 12, 1, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 13, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 14, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 15, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    private DefaultComboBoxModel getSurfaceModel()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("ASPHALT");
        model.addElement("BITUMINOUS");
        model.addElement("BRICK");
        model.addElement("CLAY");
        model.addElement("CEMENT");
        model.addElement("CONCRETE");
        model.addElement("CORAL");
        model.addElement("DIRT");
        model.addElement("GRASS");
        model.addElement("GRAVEL");
        model.addElement("ICE");
        model.addElement("MACADAM");
        model.addElement("OIL_TREATED, PLANKS");
        model.addElement("SAND");
        model.addElement("SHALE");
        model.addElement("SNOW");
        model.addElement("STEEL_MATS");
        model.addElement("TARMAC");
        model.addElement("UNKNOWN");
        model.addElement("WATER");
        return model;
    }

    public void recordSettings()
    {
        SettingsEngine.getInstance().setRunwayLength(((Double)runwayLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setRunwayLengthMeasure((String)runwayLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setRunwayWidth(((Double)runwayWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setRunwayWidthMeasure((String)runwayWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiX(((Double)vasiXSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiXMeasure((String)vasiXComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiZ(((Double)vasiZSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiZMeasure((String)vasiZComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiZEnd((String)vasiZEndComboBox.getSelectedItem());
        SettingsEngine.getInstance().setVasiSpacing(((Double)vasiSpacingSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setVasiSpacingMeasure((String)vasiSpacingComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetLength(((Double)offsetLengthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setOffsetLengthMeasure((String)offsetLengthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetWidthRunway(offsetWidthRunwayRB.isSelected());
        SettingsEngine.getInstance().setOffsetWidth(((Double)offsetWidthSpinner.getValue()).floatValue());
        SettingsEngine.getInstance().setOffsetWidthMeasure((String)offsetWidthComboBox.getSelectedItem());
        SettingsEngine.getInstance().setOffsetSurfaceRunway(offsetSurfaceRunwayRB.isSelected());
        SettingsEngine.getInstance().setOffsetSurface((String)offsetSurfaceComboBox.getSelectedItem());
        SettingsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == offsetWidthRunwayRB)
        {
            offsetWidthSpinner.setEnabled(offsetWidthSpecificRB.isSelected());
            offsetWidthComboBox.setEnabled(offsetWidthSpecificRB.isSelected());
        } else
        if(event.getSource() == offsetWidthSpecificRB)
        {
            offsetWidthSpinner.setEnabled(offsetWidthSpecificRB.isSelected());
            offsetWidthComboBox.setEnabled(offsetWidthSpecificRB.isSelected());
        } else
        if(event.getSource() == offsetSurfaceRunwayRB)
            offsetSurfaceComboBox.setEnabled(offsetSurfaceSpecificRB.isSelected());
        else
        if(event.getSource() == offsetSurfaceSpecificRB)
            offsetSurfaceComboBox.setEnabled(offsetSurfaceSpecificRB.isSelected());
    }

    private JLabel totalLabel;
    private JSpinner runwayLengthSpinner;
    private JComboBox runwayLengthComboBox;
    private JSpinner runwayWidthSpinner;
    private JComboBox runwayWidthComboBox;
    private JSpinner vasiXSpinner;
    private JComboBox vasiXComboBox;
    private JSpinner vasiZSpinner;
    private JComboBox vasiZComboBox;
    private JComboBox vasiZEndComboBox;
    private JSpinner vasiSpacingSpinner;
    private JComboBox vasiSpacingComboBox;
    private JSpinner offsetLengthSpinner;
    private JComboBox offsetLengthComboBox;
    private JSpinner offsetWidthSpinner;
    private JComboBox offsetWidthComboBox;
    private JComboBox offsetSurfaceComboBox;
    private JRadioButton offsetWidthRunwayRB;
    private JRadioButton offsetWidthSpecificRB;
    private JRadioButton offsetSurfaceRunwayRB;
    private JRadioButton offsetSurfaceSpecificRB;
}
