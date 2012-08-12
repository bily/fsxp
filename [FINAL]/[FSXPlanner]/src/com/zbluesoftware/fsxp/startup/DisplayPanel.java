// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DisplayPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class DisplayPanel extends JPanel
    implements StartupPanel
{

    public DisplayPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Display Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JLabel latLonLabel = new JLabel("Latitude/Longitude pointer format:");
        latLonLabel.setFont(Utilities.LABEL_FONT);
        latLonLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonModel = new DefaultComboBoxModel();
        latLonModel.addElement("40.7772444561124");
        latLonModel.addElement("40\260 46' 38.07\"N");
        latLonModel.addElement("40\260 46.6345'N");
        latLonModel.addElement("N40\260 46' 38.07\"");
        latLonModel.addElement("N40\260 46.6345'");
        latLonComboBox = new JComboBox(latLonModel);
        latLonComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonComboBox.setForeground(Color.black);
        latLonComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonDisplay());
        JLabel latLonGridLabel = new JLabel("Latitude/Longitude grid format:");
        latLonGridLabel.setFont(Utilities.LABEL_FONT);
        latLonGridLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonGridModel = new DefaultComboBoxModel();
        latLonGridModel.addElement("40.777244");
        latLonGridModel.addElement("40\260 46' 38\"");
        latLonGridModel.addElement("40\260 46.6345'");
        latLonGridComboBox = new JComboBox(latLonGridModel);
        latLonGridComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonGridComboBox.setForeground(Color.black);
        latLonGridComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonGridDisplay());
        JLabel latLonObjectLabel = new JLabel("Latitude/Longitude object format:");
        latLonObjectLabel.setFont(Utilities.LABEL_FONT);
        latLonObjectLabel.setForeground(Color.black);
        DefaultComboBoxModel latLonObjectModel = new DefaultComboBoxModel();
        latLonObjectModel.addElement("40.7772444561124");
        latLonObjectModel.addElement("40 46 38.07N");
        latLonObjectModel.addElement("40 46.6345N");
        latLonObjectModel.addElement("N40 46 38.07");
        latLonObjectModel.addElement("N40 46.6345");
        latLonObjectComboBox = new JComboBox(latLonObjectModel);
        latLonObjectComboBox.setFont(Utilities.COMBO_BOX_FONT);
        latLonObjectComboBox.setForeground(Color.black);
        latLonObjectComboBox.setSelectedIndex(SettingsEngine.getInstance().getLatLonObjectDisplay());
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            latLonObjectComboBox.setPrototypeDisplayValue("--------------------------------------");
        JLabel coordLabel = new JLabel("Display lat/lon lines every:");
        coordLabel.setFont(Utilities.LABEL_FONT);
        coordLabel.setForeground(Color.black);
        DefaultComboBoxModel coordModel = new DefaultComboBoxModel();
        for(int i = 5; i < 35; i += 5)
            coordModel.addElement((new StringBuilder()).append(i).append(" seconds").toString());

        coordComboBox = new JComboBox(coordModel);
        coordComboBox.setFont(Utilities.COMBO_BOX_FONT);
        coordComboBox.setForeground(Color.black);
        coordComboBox.setSelectedIndex(SettingsEngine.getInstance().getSecondsIncrement() / 5 - 1);
        JLabel historyLabel = new JLabel("Max items to store in history:");
        historyLabel.setFont(Utilities.LABEL_FONT);
        historyLabel.setForeground(Color.black);
        historySpinner = new JSpinner(new SpinnerNumberModel(30, 1, 10000, 1));
        historySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(historySpinner, "0"));
        historySpinner.setValue(new Integer(SettingsEngine.getInstance().getMaxHistoryItems()));
        JLabel selectedLabel = new JLabel("Selected items should be:");
        selectedLabel.setFont(Utilities.LABEL_FONT);
        selectedLabel.setForeground(Color.black);
        selectedComboBox = new JComboBox(new String[] {
            "Outlined", "Highlighted"
        });
        selectedComboBox.setFont(Utilities.COMBO_BOX_FONT);
        selectedComboBox.setForeground(Color.black);
        selectedComboBox.setSelectedIndex(SettingsEngine.getInstance().isSelectedItemOutlined() ? 0 : 1);
        JLabel backgroundLabel = new JLabel("Display background images:");
        backgroundLabel.setFont(Utilities.LABEL_FONT);
        backgroundLabel.setForeground(Color.black);
        backgroundComboBox = new JComboBox(new String[] {
            "Below Airport", "Above Airport"
        });
        backgroundComboBox.setFont(Utilities.COMBO_BOX_FONT);
        backgroundComboBox.setForeground(Color.black);
        backgroundComboBox.setSelectedIndex(SettingsEngine.getInstance().getBGImagesBelow() ? 0 : 1);
        JLabel bgOpacityLabel = new JLabel("Background image opacity:");
        bgOpacityLabel.setFont(Utilities.LABEL_FONT);
        bgOpacityLabel.setForeground(Color.black);
        bgOpacitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        bgOpacitySpinner.setEditor(new javax.swing.JSpinner.NumberEditor(bgOpacitySpinner, "0"));
        bgOpacitySpinner.setValue(new Integer(SettingsEngine.getInstance().getBGImageOpacity()));
        JLabel lafLabel = new JLabel("Look & Feel:");
        lafLabel.setFont(Utilities.LABEL_FONT);
        lafLabel.setForeground(Color.black);
        DefaultComboBoxModel lafModel = new DefaultComboBoxModel();
        javax.swing.UIManager.LookAndFeelInfo lafs[] = UIManager.getInstalledLookAndFeels();
        for(int i = 0; i < lafs.length; i++)
            lafModel.addElement(lafs[i].getName());

        lafComboBox = new JComboBox(lafModel);
        lafComboBox.setFont(Utilities.COMBO_BOX_FONT);
        lafComboBox.setForeground(Color.black);
        lafComboBox.setSelectedItem(SettingsEngine.getInstance().getLAF());
        JLabel restartLabel = new JLabel("(requires restart)");
        restartLabel.setFont(Utilities.LABEL_FONT);
        restartLabel.setForeground(Color.black);
        JLabel settingsLabel = new JLabel("(These can be set by using the Display tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 3, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latLonLabel, 0, 1, 1, 1, 0, 13, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latLonComboBox, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, latLonGridLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latLonGridComboBox, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, latLonObjectLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, latLonObjectComboBox, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, coordLabel, 0, 4, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, coordComboBox, 1, 4, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, historyLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, historySpinner, 1, 5, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, selectedLabel, 0, 6, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, selectedComboBox, 1, 6, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, backgroundLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, backgroundComboBox, 1, 7, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, bgOpacityLabel, 0, 8, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, bgOpacitySpinner, 1, 8, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, lafLabel, 0, 9, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, lafComboBox, 1, 9, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, restartLabel, 2, 9, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 10, 3, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 11, 3, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 12, 3, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 13, 3, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        SettingsEngine.getInstance().setLatLonDisplay(latLonComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setLatLonGridDisplay(latLonGridComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setLatLonObjectDisplay(latLonObjectComboBox.getSelectedIndex());
        SettingsEngine.getInstance().setSecondsIncrement((coordComboBox.getSelectedIndex() + 1) * 5);
        SettingsEngine.getInstance().setLAF((String)lafComboBox.getSelectedItem());
        SettingsEngine.getInstance().setMaxHistoryItems(((Integer)historySpinner.getValue()).intValue());
        SettingsEngine.getInstance().setSelectedItemOutlined(selectedComboBox.getSelectedIndex() == 0);
        SettingsEngine.getInstance().setBGImagesBelow(backgroundComboBox.getSelectedIndex() == 0);
        SettingsEngine.getInstance().setBGImageOpacity(((Integer)bgOpacitySpinner.getValue()).intValue());
        SettingsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JComboBox latLonComboBox;
    private JComboBox latLonGridComboBox;
    private JComboBox latLonObjectComboBox;
    private JComboBox coordComboBox;
    private JSpinner historySpinner;
    private JComboBox selectedComboBox;
    private JComboBox backgroundComboBox;
    private JSpinner bgOpacitySpinner;
    private JComboBox lafComboBox;
}
