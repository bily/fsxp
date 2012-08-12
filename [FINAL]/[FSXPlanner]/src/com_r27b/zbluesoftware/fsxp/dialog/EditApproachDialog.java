// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EditApproachDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.model.table.ApproachLegTableModel;
import com.zbluesoftware.fsxp.model.table.TransitionTableModel;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

// Referenced classes of package com.zbluesoftware.fsxp.dialog:
//            LegPanel, EditTransitionDialog

public class EditApproachDialog extends JDialog
    implements ActionListener, ComponentListener, MouseListener
{

    private EditApproachDialog(JDialog parent)
    {
        super(parent, "Edit Approach", true);
        getContentPane().setLayout(new BorderLayout(5, 5));
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("GPS");
        typeModel.addElement("ILS");
        typeModel.addElement("LDA");
        typeModel.addElement("LOCALIZER");
        typeModel.addElement("LOCALIZER_BACKCOURSE");
        typeModel.addElement("NDB");
        typeModel.addElement("NDBDME");
        typeModel.addElement("RNAV");
        typeModel.addElement("SDF");
        typeModel.addElement("VOR");
        typeModel.addElement("VORDME");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
        JLabel runwayLabel = new JLabel("Runway:");
        runwayLabel.setFont(Utilities.LABEL_FONT);
        runwayLabel.setForeground(Color.black);
        DefaultComboBoxModel runwayModel = new DefaultComboBoxModel();
        for(int i = 0; i < 10; i++)
            runwayModel.addElement((new StringBuilder()).append("0").append(i).toString());

        for(int i = 10; i < 37; i++)
            runwayModel.addElement((new StringBuilder()).append("").append(i).toString());

        runwayModel.addElement("EAST");
        runwayModel.addElement("NORTH");
        runwayModel.addElement("NORTHEAST");
        runwayModel.addElement("NORTHWEST");
        runwayModel.addElement("SOUTH");
        runwayModel.addElement("SOUTHEAST");
        runwayModel.addElement("SOUTHWEST");
        runwayModel.addElement("WEST");
        runwayComboBox = new JComboBox(runwayModel);
        runwayComboBox.setFont(Utilities.COMBO_BOX_FONT);
        runwayComboBox.setForeground(Color.black);
        JLabel designatorLabel = new JLabel("Designator:");
        designatorLabel.setFont(Utilities.LABEL_FONT);
        designatorLabel.setForeground(Color.black);
        DefaultComboBoxModel designatorModel = new DefaultComboBoxModel();
        designatorModel.addElement("NONE");
        designatorModel.addElement("C");
        designatorModel.addElement("CENTER");
        designatorModel.addElement("L");
        designatorModel.addElement("LEFT");
        designatorModel.addElement("R");
        designatorModel.addElement("RIGHT");
        designatorModel.addElement("W");
        designatorModel.addElement("WATER");
        designatorModel.addElement("A");
        designatorModel.addElement("B");
        designatorComboBox = new JComboBox(designatorModel);
        designatorComboBox.setFont(Utilities.COMBO_BOX_FONT);
        designatorComboBox.setForeground(Color.black);
        JLabel suffixLabel = new JLabel("Suffix:");
        suffixLabel.setFont(Utilities.LABEL_FONT);
        suffixLabel.setForeground(Color.black);
        suffixTF = new PopupTextField(10);
        suffixTF.setFont(Utilities.TEXT_FIELD_FONT);
        suffixTF.setForeground(Color.black);
        suffixTF.setDocument(new MaxLengthDocument(1));
        JLabel gpsLabel = new JLabel("GPS Overlay:");
        gpsLabel.setFont(Utilities.LABEL_FONT);
        gpsLabel.setForeground(Color.black);
        gpsComboBox = new JComboBox(new String[] {
            "TRUE", "FALSE"
        });
        gpsComboBox.setFont(Utilities.COMBO_BOX_FONT);
        gpsComboBox.setForeground(Color.black);
        JLabel fixTypeLabel = new JLabel("Fix Type:");
        fixTypeLabel.setFont(Utilities.LABEL_FONT);
        fixTypeLabel.setForeground(Color.black);
        DefaultComboBoxModel fixTypeModel = new DefaultComboBoxModel();
        fixTypeModel.addElement("NDB");
        fixTypeModel.addElement("TERMINAL_NDB");
        fixTypeModel.addElement("TERMINAL_WAYPOINT");
        fixTypeModel.addElement("VOR");
        fixTypeModel.addElement("WAYPOINT");
        fixTypeComboBox = new JComboBox(fixTypeModel);
        fixTypeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        fixTypeComboBox.setForeground(Color.black);
        JLabel fixRegionLabel = new JLabel("Fix Region:");
        fixRegionLabel.setFont(Utilities.LABEL_FONT);
        fixRegionLabel.setForeground(Color.black);
        fixRegionTF = new PopupTextField(10);
        fixRegionTF.setFont(Utilities.TEXT_FIELD_FONT);
        fixRegionTF.setForeground(Color.black);
        fixRegionTF.setDocument(new MaxLengthDocument(2));
        JLabel fixIdentLabel = new JLabel("Fix Ident:");
        fixIdentLabel.setFont(Utilities.LABEL_FONT);
        fixIdentLabel.setForeground(Color.black);
        fixIdentTF = new PopupTextField(10);
        fixIdentTF.setFont(Utilities.TEXT_FIELD_FONT);
        fixIdentTF.setForeground(Color.black);
        fixIdentTF.setDocument(new MaxLengthDocument(5));
        JLabel altLabel = new JLabel("Altitude:");
        altLabel.setFont(Utilities.LABEL_FONT);
        altLabel.setForeground(Color.black);
        altSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        altSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(altSpinner, "0.0"));
        altComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        altComboBox.setFont(Utilities.COMBO_BOX_FONT);
        altComboBox.setForeground(Color.black);
        altComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            altComboBox.setPrototypeDisplayValue("--------");
        JLabel missedAltLabel = new JLabel("Missed Alt:");
        missedAltLabel.setFont(Utilities.LABEL_FONT);
        missedAltLabel.setForeground(Color.black);
        missedAltSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 100000D, 1.0D));
        missedAltSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(missedAltSpinner, "0.0"));
        missedAltComboBox = new JComboBox(new String[] {
            "M", "F"
        });
        missedAltComboBox.setFont(Utilities.COMBO_BOX_FONT);
        missedAltComboBox.setForeground(Color.black);
        missedAltComboBox.addActionListener(this);
        if(SettingsEngine.getInstance().getLAF().equals("Windows"))
            missedAltComboBox.setPrototypeDisplayValue("--------");
        JLabel headingLabel = new JLabel("Heading:");
        headingLabel.setFont(Utilities.LABEL_FONT);
        headingLabel.setForeground(Color.black);
        headingSpinner = new JSpinner(new SpinnerNumberModel(0.0D, 0.0D, 360D, 1.0D));
        headingSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(headingSpinner, "0.00"));
        TitledBorder mainBorder = new TitledBorder(" Main Approach Information ");
        mainBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        mainBorder.setTitleColor(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new CompoundBorder(mainBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(mainPanel, typeLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, runwayLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, runwayComboBox, 1, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, designatorLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, designatorComboBox, 1, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixTypeLabel, 2, 0, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixTypeComboBox, 3, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixRegionLabel, 2, 1, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixRegionTF, 3, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixIdentLabel, 2, 2, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixIdentTF, 3, 2, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, suffixLabel, 4, 0, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, suffixTF, 5, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, gpsLabel, 4, 1, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, gpsComboBox, 5, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 6, 0, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 7, 0, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 8, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, missedAltLabel, 6, 1, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, missedAltSpinner, 7, 1, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, missedAltComboBox, 8, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingLabel, 6, 2, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, headingSpinner, 7, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "North");
        approachLegTable = new JTable(new ApproachLegTableModel());
        approachLegTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        approachLegTable.getTableHeader().setReorderingAllowed(false);
        approachLegTable.setAutoResizeMode(0);
        approachLegTable.setSelectionMode(0);
        approachLegTable.addMouseListener(this);
        approachLegSP = new JScrollPane(approachLegTable);
        JLabel approachLabel = new JLabel("single click to view");
        approachLabel.setFont(Utilities.LABEL_FONT);
        approachLabel.setForeground(new Color(204, 0, 51));
        newApproachButton = new JButton("new approach leg");
        newApproachButton.setFont(Utilities.BUTTON_FONT);
        newApproachButton.setForeground(Color.black);
        newApproachButton.addActionListener(this);
        TitledBorder approachBorder = new TitledBorder(" Approach Legs ");
        approachBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        approachBorder.setTitleColor(Color.black);
        JPanel approachPanel = new JPanel(new GridBagLayout());
        approachPanel.setBorder(new CompoundBorder(approachBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(approachPanel, approachLegSP, 0, 0, 2, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(approachPanel, approachLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(approachPanel, newApproachButton, 1, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        missedLegTable = new JTable(new ApproachLegTableModel());
        missedLegTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        missedLegTable.getTableHeader().setReorderingAllowed(false);
        missedLegTable.setAutoResizeMode(0);
        missedLegTable.setSelectionMode(0);
        missedLegTable.addMouseListener(this);
        missedLegSP = new JScrollPane(missedLegTable);
        newMissedButton = new JButton("new missed approach leg");
        newMissedButton.setFont(Utilities.BUTTON_FONT);
        newMissedButton.setForeground(Color.black);
        newMissedButton.addActionListener(this);
        JLabel missedLabel = new JLabel("single click to view");
        missedLabel.setFont(Utilities.LABEL_FONT);
        missedLabel.setForeground(new Color(204, 0, 51));
        TitledBorder missedBorder = new TitledBorder(" Missed Approach Legs ");
        missedBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        missedBorder.setTitleColor(Color.black);
        JPanel missedPanel = new JPanel(new GridBagLayout());
        missedPanel.setBorder(new CompoundBorder(missedBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(missedPanel, missedLegSP, 0, 0, 2, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(missedPanel, missedLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(missedPanel, newMissedButton, 1, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        transitionTable = new JTable(new TransitionTableModel());
        transitionTable.setPreferredScrollableViewportSize(new Dimension(300, 50));
        transitionTable.getTableHeader().setReorderingAllowed(false);
        transitionTable.setAutoResizeMode(0);
        transitionTable.setSelectionMode(0);
        transitionTable.addMouseListener(this);
        transitionSP = new JScrollPane(transitionTable);
        JLabel transitionLabel = new JLabel("double click to open");
        transitionLabel.setFont(Utilities.LABEL_FONT);
        transitionLabel.setForeground(new Color(204, 0, 51));
        newTransitionButton = new JButton("new transition");
        newTransitionButton.setFont(Utilities.BUTTON_FONT);
        newTransitionButton.setForeground(Color.black);
        newTransitionButton.addActionListener(this);
        TitledBorder transitionBorder = new TitledBorder(" Transitions ");
        transitionBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        transitionBorder.setTitleColor(Color.black);
        JPanel transitionPanel = new JPanel(new GridBagLayout());
        transitionPanel.setBorder(new CompoundBorder(transitionBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(transitionPanel, transitionSP, 0, 0, 2, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(transitionPanel, transitionLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(transitionPanel, newTransitionButton, 1, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        legPanel = new LegPanel(this);
        JPanel tablePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(tablePanel, approachPanel, 0, 0, 1, 1, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(tablePanel, missedPanel, 0, 1, 1, 1, 1, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(tablePanel, transitionPanel, 0, 2, 1, 1, 1, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(tablePanel, legPanel, 1, 0, 1, 3, 1, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(tablePanel, "Center");
        updateButton = new JButton("Update");
        updateButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        updateButton.setForeground(Color.black);
        updateButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        addComponentListener(this);
        pack();
        setLocationRelativeTo(parent);
    }

    public static boolean showDialog(JDialog parent, ApproachModel approachModel)
    {
        if(dialog == null)
            dialog = new EditApproachDialog(parent);
        dialog.setApproachModel(approachModel);
        dialog.getLegPanel().setLegEnabled(false);
        dialog.getLegPanel().resetLeg();
        dialog.setApproachUpdated(false);
        dialog.setVisible(true);
        return dialog.isApproachUpdated();
    }

    public LegPanel getLegPanel()
    {
        return legPanel;
    }

    public void setColumnSizes()
    {
        if(approachLegSP != null)
        {
            int paneWidth = approachLegSP.getViewport().getSize().width;
            if(paneWidth > 0 && approachLegTable.getColumnCount() == 6)
            {
                approachLegTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.125D));
                approachLegTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.375D));
                approachLegTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.17499999999999999D));
                approachLegTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.125D));
                approachLegTable.getColumnModel().getColumn(4).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
                approachLegTable.getColumnModel().getColumn(5).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
        if(missedLegSP != null)
        {
            int paneWidth = missedLegSP.getViewport().getSize().width;
            if(paneWidth > 0 && missedLegTable.getColumnCount() == 6)
            {
                missedLegTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.125D));
                missedLegTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.375D));
                missedLegTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.17499999999999999D));
                missedLegTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.125D));
                missedLegTable.getColumnModel().getColumn(4).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
                missedLegTable.getColumnModel().getColumn(5).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
        if(transitionSP != null)
        {
            int paneWidth = transitionSP.getViewport().getSize().width;
            if(paneWidth > 0 && transitionTable.getColumnCount() == 5)
            {
                transitionTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.125D));
                transitionTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.375D));
                transitionTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.17499999999999999D));
                transitionTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.22500000000000001D));
                transitionTable.getColumnModel().getColumn(4).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
    }

    private void setApproachModel(ApproachModel approachModel)
    {
        this.approachModel = approachModel;
        ((ApproachLegTableModel)approachLegTable.getModel()).setData(approachModel.getApproachAL());
        ((ApproachLegTableModel)missedLegTable.getModel()).setData(approachModel.getMissedAL());
        ((TransitionTableModel)transitionTable.getModel()).setData(approachModel.getTransitionAL());
        altComboBox.removeActionListener(this);
        missedAltComboBox.removeActionListener(this);
        typeComboBox.setSelectedItem(approachModel.getType());
        runwayComboBox.setSelectedItem(approachModel.getRunway());
        designatorComboBox.setSelectedItem(approachModel.getDesignator());
        suffixTF.setText(approachModel.getSuffix());
        gpsComboBox.setSelectedIndex(approachModel.getGPSOverlay() ? 0 : 1);
        fixTypeComboBox.setSelectedItem(approachModel.getFixType());
        fixRegionTF.setText(approachModel.getFixRegion());
        fixIdentTF.setText(approachModel.getFixIdent());
        altSpinner.setValue(new Double(approachModel.getAltitude()));
        altComboBox.setSelectedItem(approachModel.getAltitudeMeasure());
        missedAltSpinner.setValue(new Double(approachModel.getMissedAltitude()));
        missedAltComboBox.setSelectedItem(approachModel.getMissedAltitudeMeasure());
        headingSpinner.setValue(new Double(approachModel.getHeading()));
        origAltMeasure = approachModel.getAltitudeMeasure();
        origMissedAltMeasure = approachModel.getMissedAltitudeMeasure();
        altComboBox.addActionListener(this);
        missedAltComboBox.addActionListener(this);
    }

    private void displayLeg(JTable table, Point point)
    {
        int row = table.rowAtPoint(point);
        int column = table.columnAtPoint(point);
        if(row < 0 || row >= table.getRowCount())
            return;
        if(column < 0 || column > 4)
            return;
        if(table == approachLegTable)
            missedLegTable.clearSelection();
        else
            approachLegTable.clearSelection();
        if(table.getSelectedRow() == -1)
            legPanel.setLegEnabled(false);
        else
            legPanel.displayLeg(((ApproachLegTableModel)table.getModel()).getLegModel(row));
    }

    public void setApproachUpdated(boolean approachUpdated)
    {
        this.approachUpdated = approachUpdated;
    }

    public boolean isApproachUpdated()
    {
        return approachUpdated;
    }

    private void deleteLeg(JTable table, Point point)
    {
        int row = table.rowAtPoint(point);
        if(row == -1)
            return;
        if(table.columnAtPoint(point) != 5)
            return;
        LegModel legModel = ((ApproachLegTableModel)table.getModel()).getLegModel(row);
        StringBuffer buffer = new StringBuffer();
        buffer.append("Are you sure you want to delete the ");
        if(table == approachLegTable)
            buffer.append(" approach leg\n");
        else
            buffer.append(" missed approach leg\n");
        buffer.append("Type: ").append(legModel.getType()).append("\n");
        buffer.append("Fix Type: ").append(legModel.getFixType()).append("\n");
        buffer.append("Fix Ident: ").append(legModel.getFixIdent()).append("\n");
        if(JOptionPane.showConfirmDialog(this, buffer.toString(), "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            ((ApproachLegTableModel)table.getModel()).deleteRow(row);
            JOptionPane.showMessageDialog(this, "Leg Deleted.", "Deleted...", 1);
            return;
        }
    }

    private void updateAddLeg()
    {
        LegModel legModel = legPanel.updateAddLeg();
        if(legPanel.getAddButton().getText().equals("Update Leg"))
        {
            ((ApproachLegTableModel)approachLegTable.getModel()).fireTableDataChanged();
            ((ApproachLegTableModel)missedLegTable.getModel()).fireTableDataChanged();
        } else
        if(legPanel.getAddButton().getText().equals("Add Approach Leg"))
            ((ApproachLegTableModel)approachLegTable.getModel()).addLeg(legModel);
        else
        if(legPanel.getAddButton().getText().equals("Add Missed Approach Leg"))
            ((ApproachLegTableModel)missedLegTable.getModel()).addLeg(legModel);
        JOptionPane.showMessageDialog(this, "The Leg has been recorded.", "Leg Recorded", 1);
    }

    private void updateApproach()
    {
        approachModel.setType((String)typeComboBox.getSelectedItem());
        approachModel.setRunway((String)runwayComboBox.getSelectedItem());
        if(designatorComboBox.getSelectedIndex() == 0)
            approachModel.setDesignator("");
        else
            approachModel.setDesignator((String)designatorComboBox.getSelectedItem());
        approachModel.setSuffix(suffixTF.getText());
        approachModel.setGPSOverlay(gpsComboBox.getSelectedIndex() == 0);
        approachModel.setFixType((String)fixTypeComboBox.getSelectedItem());
        approachModel.setFixRegion(fixRegionTF.getText());
        approachModel.setFixIdent(fixIdentTF.getText());
        approachModel.setAltitude(((Double)altSpinner.getValue()).floatValue());
        approachModel.setAltitudeMeasure((String)altComboBox.getSelectedItem());
        approachModel.setMissedAltitude(((Double)missedAltSpinner.getValue()).floatValue());
        approachModel.setMissedAltitudeMeasure((String)missedAltComboBox.getSelectedItem());
        approachModel.setHeading(((Double)headingSpinner.getValue()).floatValue());
        approachUpdated = true;
    }

    private void updateAltMeasure(JSpinner spinner, JComboBox combobox)
    {
        if(combobox == altComboBox && ((String)combobox.getSelectedItem()).equals(origAltMeasure))
            return;
        if(combobox == missedAltComboBox && ((String)combobox.getSelectedItem()).equals(origMissedAltMeasure))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            float altitude = ((Double)spinner.getValue()).floatValue();
            if(combobox.getSelectedItem().equals("M"))
                altitude /= 3.28F;
            else
                altitude *= 3.28F;
            spinner.setValue(new Double(altitude));
            if(combobox == altComboBox)
                origAltMeasure = (String)combobox.getSelectedItem();
            else
            if(combobox == missedAltComboBox)
                origMissedAltMeasure = (String)combobox.getSelectedItem();
        }
    }

    private void displayNewTransition()
    {
        TransitionModel transitionModel = new TransitionModel();
        if(EditTransitionDialog.showDialog(this, transitionModel))
            ((TransitionTableModel)transitionTable.getModel()).addTransition(transitionModel);
    }

    private void displayTransition(Point point)
    {
        int row = transitionTable.rowAtPoint(point);
        int column = transitionTable.columnAtPoint(point);
        if(row < 0 || row >= transitionTable.getRowCount())
            return;
        if(column < 0 || column > 4)
        {
            return;
        } else
        {
            EditTransitionDialog.showDialog(this, ((TransitionTableModel)transitionTable.getModel()).getTransitionModel(row));
            return;
        }
    }

    private void deleteTransition(Point point)
    {
        int row = transitionTable.rowAtPoint(point);
        if(row == -1)
            return;
        TransitionModel transitionModel = ((TransitionTableModel)transitionTable.getModel()).getTransitionModel(row);
        StringBuffer buffer = new StringBuffer();
        buffer.append("Are you sure you want to delete the transition\n");
        buffer.append("Type: ").append(transitionModel.getTransitionType()).append("\n");
        buffer.append("Fix Type: ").append(transitionModel.getFixType()).append("\n");
        buffer.append("Fix Ident: ").append(transitionModel.getFixIdent()).append("\n");
        if(JOptionPane.showConfirmDialog(this, buffer.toString(), "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            ((TransitionTableModel)transitionTable.getModel()).deleteRow(row);
            JOptionPane.showMessageDialog(this, "Transition Deleted.", "Deleted...", 1);
            return;
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == updateButton)
        {
            updateApproach();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure(altSpinner, altComboBox);
        else
        if(event.getSource() == missedAltComboBox)
            updateAltMeasure(missedAltSpinner, missedAltComboBox);
        else
        if(event.getSource() == newApproachButton)
        {
            legPanel.resetLeg();
            legPanel.setLegEnabled(true);
            legPanel.getAddButton().setText("Add Approach Leg");
        } else
        if(event.getSource() == newMissedButton)
        {
            legPanel.resetLeg();
            legPanel.setLegEnabled(true);
            legPanel.getAddButton().setText("Add Missed Approach Leg");
        } else
        if(event.getSource() == newTransitionButton)
            displayNewTransition();
        else
        if(event.getSource() == legPanel.getAddButton())
            updateAddLeg();
    }

    public void componentHidden(ComponentEvent componentevent)
    {
    }

    public void componentMoved(ComponentEvent componentevent)
    {
    }

    public void componentResized(ComponentEvent event)
    {
        if(event.getSource() == this)
            setColumnSizes();
    }

    public void componentShown(ComponentEvent event)
    {
        if(event.getSource() == this)
            setColumnSizes();
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == approachLegTable)
        {
            if(event.getClickCount() == 1)
                displayLeg(approachLegTable, event.getPoint());
            else
            if(event.getClickCount() == 2)
                deleteLeg(approachLegTable, event.getPoint());
        } else
        if(event.getSource() == missedLegTable)
        {
            if(event.getClickCount() == 1)
                displayLeg(missedLegTable, event.getPoint());
            else
            if(event.getClickCount() == 2)
                deleteLeg(missedLegTable, event.getPoint());
        } else
        if(event.getSource() == transitionTable && event.getClickCount() == 2)
            if(transitionTable.columnAtPoint(event.getPoint()) != 4)
                displayTransition(event.getPoint());
            else
                deleteTransition(event.getPoint());
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    private ApproachModel approachModel;
    private JTable approachLegTable;
    private JTable missedLegTable;
    private JTable transitionTable;
    private JScrollPane approachLegSP;
    private JScrollPane missedLegSP;
    private JScrollPane transitionSP;
    private JComboBox typeComboBox;
    private JComboBox runwayComboBox;
    private JComboBox designatorComboBox;
    private PopupTextField suffixTF;
    private JComboBox gpsComboBox;
    private JComboBox fixTypeComboBox;
    private PopupTextField fixRegionTF;
    private PopupTextField fixIdentTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JSpinner missedAltSpinner;
    private JComboBox missedAltComboBox;
    private JSpinner headingSpinner;
    private JButton updateButton;
    private JButton cancelButton;
    private JButton newApproachButton;
    private JButton newMissedButton;
    private JButton newTransitionButton;
    private LegPanel legPanel;
    private String origAltMeasure;
    private String origMissedAltMeasure;
    private boolean approachUpdated;
    private static EditApproachDialog dialog = null;

}
