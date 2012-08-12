// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ScanProgressDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class ScanProgressDialog extends JDialog
    implements ActionListener
{

    private ScanProgressDialog(JDialog parent)
    {
        super(parent, "Scan Progress", false);
        JLabel folderLabel = new JLabel("Folder:");
        folderLabel.setFont(Utilities.BOLD_LABEL_FONT);
        folderLabel.setForeground(Color.black);
        folderDataLabel = new JLabel("---");
        folderDataLabel.setFont(Utilities.BOLD_LABEL_FONT);
        folderDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        JLabel fileLabel = new JLabel("File:");
        fileLabel.setFont(Utilities.BOLD_LABEL_FONT);
        fileLabel.setForeground(Color.black);
        fileDataLabel = new JLabel("---");
        fileDataLabel.setFont(Utilities.BOLD_LABEL_FONT);
        fileDataLabel.setForeground(Utilities.DATA_HIGHLIGHT_COLOR);
        cancelButton = new JButton("Cancel Scan");
        cancelButton.setFont(Utilities.BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(mainPanel, folderLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, folderDataLabel, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fileLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fileDataLabel, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, cancelButton, 0, 2, 2, 1, 0, 10, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        setResizable(false);
        setSize(new Dimension(300, 100));
        setLocationRelativeTo(parent);
    }

    public static void showDialog(JDialog parent, PropertyChangeListener listener)
    {
        if(dialog == null)
            dialog = new ScanProgressDialog(parent);
        dialog.updateDialog(listener);
        dialog.setVisible(true);
    }

    public static void hideDialog()
    {
        if(dialog != null)
            dialog.setVisible(false);
    }

    public void updateDialog(PropertyChangeListener listener)
    {
        this.listener = listener;
        cancelButton.setEnabled(true);
    }

    public static void updateFolderDisplay(String text)
    {
        if(dialog != null)
            dialog.getFolderDataLabel().setText(text);
    }

    public static void updateFileDisplay(String text)
    {
        if(dialog != null)
            dialog.getFileDataLabel().setText(text);
    }

    public JLabel getFolderDataLabel()
    {
        return folderDataLabel;
    }

    public JLabel getFileDataLabel()
    {
        return fileDataLabel;
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == cancelButton)
        {
            cancelButton.setEnabled(false);
            listener.propertyChange(new PropertyChangeEvent(this, "working", new Boolean(false), new Boolean(false)));
        }
    }

    private JLabel folderDataLabel;
    private JLabel fileDataLabel;
    private JButton cancelButton;
    private PropertyChangeListener listener;
    private static ScanProgressDialog dialog = null;

}
