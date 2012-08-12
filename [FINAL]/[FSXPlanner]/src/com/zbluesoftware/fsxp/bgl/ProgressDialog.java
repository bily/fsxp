// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProgressDialog.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import javax.swing.*;

public class ProgressDialog extends JDialog
{

    private ProgressDialog(Frame parent)
    {
        super(parent, "Progress", false);
        JLabel decompileLabel = new JLabel("Decompiling BGL File:");
        decompileLabel.setFont(Utilities.BOLD_LABEL_FONT);
        decompileLabel.setForeground(Color.black);
        dataLabel = new JLabel("---");
        dataLabel.setFont(Utilities.BOLD_LABEL_FONT);
        dataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(mainPanel, decompileLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, dataLabel, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        setResizable(false);
        setSize(new Dimension(300, 100));
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent)
    {
        if(dialog == null)
            dialog = new ProgressDialog(parent);
        dialog.setVisible(true);
    }

    public static void hideDialog()
    {
        if(dialog != null)
            dialog.setVisible(false);
    }

    public static void updateDisplay(String text)
    {
        if(dialog != null)
            dialog.getDataLabel().setText(text);
    }

    public JLabel getDataLabel()
    {
        return dataLabel;
    }

    private JLabel dataLabel;
    private static ProgressDialog dialog = null;

}
