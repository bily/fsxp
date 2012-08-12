// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FinishPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class FinishPanel extends JPanel
    implements StartupPanel
{

    public FinishPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Setup Complete");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JTextArea infoTA = new JTextArea(5, 5);
        infoTA.setFont(Utilities.TEXT_FIELD_FONT);
        infoTA.setForeground(Color.black);
        infoTA.setOpaque(false);
        infoTA.setEditable(false);
        infoTA.setLineWrap(true);
        infoTA.setWrapStyleWord(true);
        StringBuffer buffer = new StringBuffer();
        buffer.append("Your preferences have been set.  You may modify these preferences, as well as several others, at any time by");
        buffer.append(" using the Preference Dialog, located under the File -> Preferences menu or the Prepare Options dialog,");
        buffer.append(" located undert the Compile -> Prepare Options menu.\n");
        buffer.append("\n");
        buffer.append("Other items that may be of interest are:\n");
        buffer.append("* Object Locking: located under the Edit menu\n");
        buffer.append("* Background Images: located under the Edit menu\n");
        buffer.append("* Connecting to FSX: located under the SimConnect menu\n");
        buffer.append("* Controlling what object are displayed using the Display menu\n");
        buffer.append("* Displaying the Navigation toolbar located under the View menu\n");
        buffer.append("\n");
        buffer.append("Be sure to visit the FSX Planner website located at www.zbluesoftware.com/fsxplanner");
        buffer.append(" for updates, tutorials and more!\n");
        buffer.append("\n");
        buffer.append("Thank you for using FSX Planner!\n");
        infoTA.setText(buffer.toString());
        openCB = new JCheckBox("Open La Guardia sample airport", true);
        openCB.setFont(Utilities.BOLD_LABEL_FONT);
        openCB.setForeground(Color.black);
        openCB.setOpaque(false);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, infoTA, 0, 1, 1, 1, 1, 10, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, openCB, 0, 2, 1, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, totalLabel, 0, 3, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public JCheckBox getOpenCB()
    {
        return openCB;
    }

    public void recordSettings()
    {
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
    private JCheckBox openCB;
}
