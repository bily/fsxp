// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplashScreen.java

package com.zbluesoftware.fsxp.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.util:
//            Utilities

public class SplashScreen extends JWindow
{

    public SplashScreen()
    {
        ImageIcon logo = new ImageIcon(com.zbluesoftware.fsxp.util.SplashScreen.class.getResource("LargeLogo.jpg"));
        JLabel logoLabel = new JLabel(logo);
		JLabel copyrightLabel = new JLabel("(c) copyright zBlueSoftware, 2008",JLabel.RIGHT);
	copyrightLabel.setFont(Utilities.LABEL_FONT);
        copyrightLabel.setForeground(Color.white);
		Font myFont = new Font("Verdana", Font.BOLD, 15);
		Color myColor = new Color(255,190,15);
		JLabel dstaLabel = new JLabel("[=DECOMPILED BY=]",JLabel.CENTER);
		JLabel dstaLabel2 = new JLabel("Bao Hong & Lee Wei",JLabel.CENTER);
		JLabel dstaLabel3 = new JLabel("DSTA, 2008",JLabel.CENTER);
		dstaLabel.setForeground(myColor);dstaLabel.setFont(myFont);
		dstaLabel2.setForeground(myColor);dstaLabel2.setFont(myFont);
		dstaLabel3.setForeground(myColor);dstaLabel3.setFont(myFont);
        JPanel mainPanel = new JPanel(new GridBagLayout());
	        mainPanel.setBackground(Color.black);
        mainPanel.setBorder(new CompoundBorder(new BevelBorder(0), new LineBorder(new Color(0, 99, 166), 2)));
/*
        Utilities.addComponent(mainPanel, copyrightLabel, 0, 0, 1, 1, 0, 12, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, logoLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
*/
int m=1;
Utilities.addComponent(mainPanel, copyrightLabel, 2, 0, 1, 1, 0, 12, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, new JLabel(" "), 2, 1, 1, m, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, new JLabel(" "), 2, (1+m), 1, m, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, new JLabel(" "), 2, (1+2*m), 1, m, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, dstaLabel, 2, (1+2*m+1), 1, 1, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, dstaLabel2, 2, (1+2*m+2), 1, 1, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, dstaLabel3, 2, (1+2*m+3), 1, 1, 0, 11, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);
Utilities.addComponent(mainPanel, logoLabel, 0, 0, 3, (1+2*m+4), 0, 12, new Insets(2, 1, 1, 2), 0, 0, 0.0D, 0.0D);

        getContentPane().add(mainPanel);
        setSize(logo.getIconWidth(), logo.getIconHeight());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getSize().width) / 2, (screenSize.height - getSize().height) / 2);
        setVisible(true);
    }
}
