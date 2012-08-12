// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartupDialog.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            IntroPanel, DisplayPanel, CADPanel, AppPanel, 
//            DefaultsPanel, RunwayPanel, ColorPanel, WritePanel, 
//            DeletePanel, FinishPanel, StartupPanel

public class StartupDialog extends JDialog
    implements ActionListener
{

    public StartupDialog(Frame parent)
    {
        super(parent, "FSX Planner Settings", true);
        panelAL = new ArrayList();
        panelAL.add(new IntroPanel());
        panelAL.add(new DisplayPanel());
        panelAL.add(new CADPanel());
        panelAL.add(new AppPanel());
        panelAL.add(new DefaultsPanel());
        panelAL.add(new RunwayPanel());
        panelAL.add(new ColorPanel());
        panelAL.add(new WritePanel());
        panelAL.add(new DeletePanel());
        panelAL.add(new FinishPanel());
        getContentPane().setLayout(new GridBagLayout());
        for(int i = 0; i < panelAL.size(); i++)
        {
            Utilities.addComponent(getContentPane(), ((StartupPanel)panelAL.get(i)).getPanel(), 0, 0, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
            ((StartupPanel)panelAL.get(i)).setTotalCount(i + 1, panelAL.size());
            ((StartupPanel)panelAL.get(i)).getPanel().setVisible(i == 0);
        }

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        backButton.setForeground(Color.black);
        backButton.addActionListener(this);
        backButton.setEnabled(false);
        nextButton = new JButton("Next");
        nextButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        nextButton.setForeground(Color.black);
        nextButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        Utilities.addComponent(getContentPane(), buttonPanel, 0, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        setSize(500, 470);
        setLocationRelativeTo(parent);
    }

    public static boolean showDialog(Frame frame)
    {
        StartupDialog dialog = new StartupDialog(frame);
        dialog.setVisible(true);
        return dialog.getOpenAirport();
    }

    public boolean getOpenAirport()
    {
        if(panelAL.get(panelAL.size() - 1) instanceof FinishPanel)
            return ((FinishPanel)panelAL.get(panelAL.size() - 1)).getOpenCB().isSelected();
        else
            return false;
    }

    private void updateDisplay(int increment)
    {
        ((StartupPanel)panelAL.get(currentPanel)).recordSettings();
        ((StartupPanel)panelAL.get(currentPanel)).getPanel().setVisible(false);
        currentPanel += increment;
        if(currentPanel < 0)
            currentPanel = 0;
        else
        if(currentPanel >= panelAL.size())
            currentPanel = panelAL.size() - 1;
        ((StartupPanel)panelAL.get(currentPanel)).getPanel().setVisible(true);
        backButton.setEnabled(currentPanel > 0);
        nextButton.setEnabled(currentPanel < panelAL.size() - 1);
        cancelButton.setText(currentPanel != panelAL.size() - 1 ? "Cancel" : "Finish");
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == cancelButton)
        {
            if(cancelButton.getText().equals("Finish"))
            {
                SettingsEngine.getInstance().setFirstTime(false);
                SettingsEngine.getInstance().writePreferences();
            }
            setVisible(false);
        } else
        if(event.getSource() == backButton)
            updateDisplay(-1);
        else
        if(event.getSource() == nextButton)
            updateDisplay(1);
    }

    private ArrayList panelAL;
    private JButton cancelButton;
    private JButton backButton;
    private JButton nextButton;
    private int currentPanel;
}
