// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class AppPanel extends JPanel
    implements StartupPanel
{

    public AppPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Application Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        restoreWindowCB = new JCheckBox("Restore last window location when starting.", SettingsEngine.getInstance().getRestoreWindow());
        restoreWindowCB.setFont(Utilities.LABEL_FONT);
        restoreWindowCB.setForeground(Color.black);
        restoreWindowCB.setOpaque(false);
        clearCompileCB = new JCheckBox("Clear Compiler Output when Compile Dialog is displayed.", SettingsEngine.getInstance().getClearCompile());
        clearCompileCB.setFont(Utilities.LABEL_FONT);
        clearCompileCB.setForeground(Color.black);
        clearCompileCB.setOpaque(false);
        xmlCB = new JCheckBox("Write each XML attribute on a separate line.", SettingsEngine.getInstance().getXMLPerLine());
        xmlCB.setFont(Utilities.LABEL_FONT);
        xmlCB.setForeground(Color.black);
        xmlCB.setOpaque(false);
        deletesCB = new JCheckBox("Read/write Deletes from/to airport XML files.", SettingsEngine.getInstance().getWriteDeletes());
        deletesCB.setFont(Utilities.LABEL_FONT);
        deletesCB.setForeground(Color.black);
        deletesCB.setOpaque(false);
        excludesCB = new JCheckBox("Read/write Exclusion Rectangles from/to airport XML files.", SettingsEngine.getInstance().getWriteExcludes());
        excludesCB.setFont(Utilities.LABEL_FONT);
        excludesCB.setForeground(Color.black);
        excludesCB.setOpaque(false);
        bgImageCB = new JCheckBox("Read/Write Background Images from/to airport XML files.", SettingsEngine.getInstance().getWriteBGImages());
        bgImageCB.setFont(Utilities.LABEL_FONT);
        bgImageCB.setForeground(Color.black);
        bgImageCB.setOpaque(false);
        memoryCB = new JCheckBox("Display memory window at startup", SettingsEngine.getInstance().getDisplayMemory());
        memoryCB.setFont(Utilities.LABEL_FONT);
        memoryCB.setForeground(Color.black);
        memoryCB.setOpaque(false);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(false);
        Utilities.addComponent(mainPanel, clearCompileCB, 0, 0, 1, 1, 0, 17, new Insets(5, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, xmlCB, 0, 1, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deletesCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, excludesCB, 0, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, bgImageCB, 0, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, restoreWindowCB, 0, 5, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, memoryCB, 0, 6, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the App tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, mainPanel, 0, 1, 1, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 2, 1, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 3, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 4, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 5, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        SettingsEngine.getInstance().setRestoreWindow(restoreWindowCB.isSelected());
        SettingsEngine.getInstance().setClearCompile(clearCompileCB.isSelected());
        SettingsEngine.getInstance().setXMLPerLine(xmlCB.isSelected());
        SettingsEngine.getInstance().setWriteDeletes(deletesCB.isSelected());
        SettingsEngine.getInstance().setWriteExcludes(excludesCB.isSelected());
        SettingsEngine.getInstance().setWriteBGImages(bgImageCB.isSelected());
        SettingsEngine.getInstance().setDisplayMemory(memoryCB.isSelected());
        SettingsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JCheckBox restoreWindowCB;
    private JCheckBox clearCompileCB;
    private JCheckBox xmlCB;
    private JCheckBox deletesCB;
    private JCheckBox excludesCB;
    private JCheckBox bgImageCB;
    private JCheckBox memoryCB;
}
