// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeletePanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.PrepareEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class DeletePanel extends JPanel
    implements StartupPanel
{

    public DeletePanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Data To Be Replaced Within Flight Simulator");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JTextArea deleteTA = new JTextArea(2, 5);
        deleteTA.setFont(Utilities.BOLD_LABEL_FONT);
        deleteTA.setForeground(Color.black);
        deleteTA.setOpaque(false);
        deleteTA.setEditable(false);
        deleteTA.setLineWrap(true);
        deleteTA.setWrapStyleWord(true);
        StringBuffer deleteBuffer = new StringBuffer();
        deleteBuffer.append("These options allow you to specify which default objects in FSX should be deleted.\n");
        deleteBuffer.append("(Exclusion rectangles can be used for additional delete options.)");
        deleteTA.setText(deleteBuffer.toString());
        deleteApproachCB = new JCheckBox("Delete All Approaches", PrepareEngine.getInstance().getDeleteApproaches());
        deleteApproachCB.setFont(Utilities.LABEL_FONT);
        deleteApproachCB.setForeground(Color.black);
        deleteApproachCB.setOpaque(false);
        deleteApronsCB = new JCheckBox("Delete All Aprons", PrepareEngine.getInstance().getDeleteAprons());
        deleteApronsCB.setFont(Utilities.LABEL_FONT);
        deleteApronsCB.setForeground(Color.black);
        deleteApronsCB.setOpaque(false);
        deleteApronLightsCB = new JCheckBox("Delete All Apron Lights", PrepareEngine.getInstance().getDeleteApronLights());
        deleteApronLightsCB.setFont(Utilities.LABEL_FONT);
        deleteApronLightsCB.setForeground(Color.black);
        deleteApronLightsCB.setOpaque(false);
        deleteBlastFencesCB = new JCheckBox("Delete All Blast Fences", PrepareEngine.getInstance().getDeleteBlastFences());
        deleteBlastFencesCB.setFont(Utilities.LABEL_FONT);
        deleteBlastFencesCB.setForeground(Color.black);
        deleteBlastFencesCB.setOpaque(false);
        deleteBoundaryFencesCB = new JCheckBox("Delete All Boundary Fences", PrepareEngine.getInstance().getDeleteBoundaryFences());
        deleteBoundaryFencesCB.setFont(Utilities.LABEL_FONT);
        deleteBoundaryFencesCB.setForeground(Color.black);
        deleteBoundaryFencesCB.setOpaque(false);
        deleteHelipadsCB = new JCheckBox("Delete All Helipads", PrepareEngine.getInstance().getDeleteHelipads());
        deleteHelipadsCB.setFont(Utilities.LABEL_FONT);
        deleteHelipadsCB.setForeground(Color.black);
        deleteHelipadsCB.setOpaque(false);
        deleteJetwaysCB = new JCheckBox("Delete All Jetways", PrepareEngine.getInstance().getDeleteJetways());
        deleteJetwaysCB.setFont(Utilities.LABEL_FONT);
        deleteJetwaysCB.setForeground(Color.black);
        deleteJetwaysCB.setOpaque(false);
        deleteTaxiwaysCB = new JCheckBox("Delete All Taxiways", PrepareEngine.getInstance().getDeleteTaxiways());
        deleteTaxiwaysCB.setFont(Utilities.LABEL_FONT);
        deleteTaxiwaysCB.setForeground(Color.black);
        deleteTaxiwaysCB.setOpaque(false);
        deleteTowersCB = new JCheckBox("Delete All Control Towers", PrepareEngine.getInstance().getDeleteTowers());
        deleteTowersCB.setFont(Utilities.LABEL_FONT);
        deleteTowersCB.setForeground(Color.black);
        deleteTowersCB.setOpaque(false);
        deleteRunwaysCB = new JCheckBox("Delete All Runways", PrepareEngine.getInstance().getDeleteRunways());
        deleteRunwaysCB.setFont(Utilities.LABEL_FONT);
        deleteRunwaysCB.setForeground(Color.black);
        deleteRunwaysCB.setOpaque(false);
        deleteStartsCB = new JCheckBox("Delete All Start Points", PrepareEngine.getInstance().getDeleteStarts());
        deleteStartsCB.setFont(Utilities.LABEL_FONT);
        deleteStartsCB.setForeground(Color.black);
        deleteStartsCB.setOpaque(false);
        deleteFrequenciesCB = new JCheckBox("Delete All Frequencies", PrepareEngine.getInstance().getDeleteFrequencies());
        deleteFrequenciesCB.setFont(Utilities.LABEL_FONT);
        deleteFrequenciesCB.setForeground(Color.black);
        deleteFrequenciesCB.setOpaque(false);
        JTextArea delete2TA = new JTextArea(2, 5);
        delete2TA.setFont(Utilities.LABEL_FONT);
        delete2TA.setForeground(Color.black);
        delete2TA.setOpaque(false);
        delete2TA.setEditable(false);
        delete2TA.setLineWrap(true);
        delete2TA.setWrapStyleWord(true);
        StringBuffer delete2Buffer = new StringBuffer();
        delete2Buffer.append("NOTE: FSX Planner does not actually delete any data from Flight Simulator.  These options simply");
        delete2Buffer.append(" prevent the default data within Flight Simulator from being displayed.  Not using the proper");
        delete2Buffer.append(" delete or exclusion rectangles can cause duplicatre objects to appear within the sim.\n");
        delete2Buffer.append("\n");
        delete2Buffer.append("ALSO NOTE: There are more detailed delete options that allow you to delete specific Runways,");
        delete2Buffer.append(" Starting Locations or Frequencies from within an airport located in the Prepare Options dialog.\n");
        delete2TA.setText(delete2Buffer.toString());
        TitledBorder deleteBorder = new TitledBorder(" General Delete Options ");
        deleteBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        deleteBorder.setTitleColor(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(deleteBorder);
        mainPanel.setOpaque(false);
        Utilities.addComponent(mainPanel, deleteApproachCB, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteApronsCB, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteApronLightsCB, 0, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteBlastFencesCB, 1, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteBoundaryFencesCB, 1, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteHelipadsCB, 1, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteJetwaysCB, 2, 0, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteTaxiwaysCB, 2, 1, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteTowersCB, 2, 2, 1, 1, 0, 17, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteRunwaysCB, 0, 3, 1, 1, 0, 17, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteStartsCB, 1, 3, 1, 1, 0, 17, new Insets(10, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteFrequenciesCB, 2, 3, 1, 1, 0, 17, new Insets(10, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        JLabel settingsLabel = new JLabel("(These can be set by using the Delete Options tab of the Prepare Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(Compile -> Prepare Options)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, deleteTA, 0, 1, 1, 1, 2, 17, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, mainPanel, 0, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, delete2TA, 0, 3, 1, 1, 2, 17, new Insets(5, 1, 5, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(this, settingsLabel, 0, 4, 1, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 5, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 6, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 7, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        PrepareEngine.getInstance().setDeleteApproaches(deleteApproachCB.isSelected());
        PrepareEngine.getInstance().setDeleteApronLights(deleteApronLightsCB.isSelected());
        PrepareEngine.getInstance().setDeleteAprons(deleteApronsCB.isSelected());
        PrepareEngine.getInstance().setDeleteFrequencies(deleteFrequenciesCB.isSelected());
        PrepareEngine.getInstance().setDeleteHelipads(deleteHelipadsCB.isSelected());
        PrepareEngine.getInstance().setDeleteRunways(deleteRunwaysCB.isSelected());
        PrepareEngine.getInstance().setDeleteStarts(deleteStartsCB.isSelected());
        PrepareEngine.getInstance().setDeleteTaxiways(deleteTaxiwaysCB.isSelected());
        PrepareEngine.getInstance().setDeleteBlastFences(deleteBlastFencesCB.isSelected());
        PrepareEngine.getInstance().setDeleteBoundaryFences(deleteBoundaryFencesCB.isSelected());
        PrepareEngine.getInstance().setDeleteTowers(deleteTowersCB.isSelected());
        PrepareEngine.getInstance().setDeleteJetways(deleteJetwaysCB.isSelected());
        PrepareEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JCheckBox deleteApproachCB;
    private JCheckBox deleteApronLightsCB;
    private JCheckBox deleteApronsCB;
    private JCheckBox deleteFrequenciesCB;
    private JCheckBox deleteHelipadsCB;
    private JCheckBox deleteRunwaysCB;
    private JCheckBox deleteStartsCB;
    private JCheckBox deleteTaxiwaysCB;
    private JCheckBox deleteBlastFencesCB;
    private JCheckBox deleteBoundaryFencesCB;
    private JCheckBox deleteTowersCB;
    private JCheckBox deleteJetwaysCB;
    private JCheckBox deleteDefaultCB;
}
