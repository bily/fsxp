// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntroPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class IntroPanel extends JPanel
    implements StartupPanel
{

    public IntroPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.black));
        JLabel welcomeLabel = new JLabel("Welcome to FSX Planner!");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JTextArea infoTA = new JTextArea(5, 5);
        infoTA.setFont(Utilities.BOLD_LABEL_FONT);
        infoTA.setForeground(Color.black);
        infoTA.setOpaque(false);
        infoTA.setEditable(false);
        infoTA.setLineWrap(true);
        infoTA.setWrapStyleWord(true);
        StringBuffer buffer = new StringBuffer();
        buffer.append("This setup dialog is displayed the first time you run FSX Planner, and is intended to make you aware of the various");
        buffer.append(" options that can be customized within FSX Planner.\n");
        buffer.append("\n");
        buffer.append("These options may be customized now or at any time in the future through the Preferences dialog or the Prepare Options dialog.\n");
        buffer.append("\n");
        buffer.append("For the full list of customizable options within FSX planner, please see the Preferences dialog under the File menu");
        buffer.append(" or the Prepare Options dialog under the Compile menu.\n");
        buffer.append("\n");
        buffer.append("\n");
        buffer.append("Click Next to begin.\n");
        infoTA.setText(buffer.toString());
        JLabel logoLabel = new JLabel(new ImageIcon(com.zbluesoftware.fsxp.util.Utilities.class.getResource("LargeLogo2.jpg")));
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(6, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, infoTA, 0, 1, 1, 1, 1, 10, new Insets(10, 6, 1, 6), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 2, 1, 1, 0, 13, new Insets(10, 6, 1, 6), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, logoLabel, 0, 0, 1, 3, 1, 10, new Insets(0, 0, 0, 0), 0, 0, 0.5D, 0.5D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private JLabel totalLabel;
}
