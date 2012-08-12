// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EditTransitionDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.LegModel;
import com.zbluesoftware.fsxp.model.TransitionModel;
import com.zbluesoftware.fsxp.model.table.ApproachLegTableModel;
import com.zbluesoftware.fsxp.model.table.TransitionTableModel;
import com.zbluesoftware.fsxp.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

// Referenced classes of package com.zbluesoftware.fsxp.dialog:
//            LegPanel

public class EditTransitionDialog extends JDialog
    implements ActionListener, ComponentListener, MouseListener
{

    private EditTransitionDialog(JDialog parent)
    {
        super(parent, "Edit Transition", true);
        getContentPane().setLayout(new BorderLayout(5, 5));
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setFont(Utilities.LABEL_FONT);
        typeLabel.setForeground(Color.black);
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel();
        typeModel.addElement("FULL");
        typeModel.addElement("DME");
        typeComboBox = new JComboBox(typeModel);
        typeComboBox.setFont(Utilities.COMBO_BOX_FONT);
        typeComboBox.setForeground(Color.black);
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
        TitledBorder mainBorder = new TitledBorder(" Main Transition Information ");
        mainBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        mainBorder.setTitleColor(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new CompoundBorder(mainBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(mainPanel, typeLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, typeComboBox, 1, 0, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixTypeLabel, 0, 1, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixTypeComboBox, 1, 1, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixRegionLabel, 0, 2, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixRegionTF, 1, 2, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, fixIdentLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, fixIdentTF, 1, 3, 2, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altLabel, 0, 4, 1, 1, 0, 13, new Insets(1, 10, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, altSpinner, 1, 4, 1, 1, 2, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(mainPanel, altComboBox, 2, 4, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        transitionTable = new JTable(new ApproachLegTableModel());
        transitionTable.setPreferredScrollableViewportSize(new Dimension(350, 50));
        transitionTable.getTableHeader().setReorderingAllowed(false);
        transitionTable.setAutoResizeMode(0);
        transitionTable.setSelectionMode(0);
        transitionTable.addMouseListener(this);
        transitionSP = new JScrollPane(transitionTable);
        JLabel transitionLabel = new JLabel("single click to view");
        transitionLabel.setFont(Utilities.LABEL_FONT);
        transitionLabel.setForeground(new Color(204, 0, 51));
        newTransitionButton = new JButton("new transition leg");
        newTransitionButton.setFont(Utilities.BUTTON_FONT);
        newTransitionButton.setForeground(Color.black);
        newTransitionButton.addActionListener(this);
        TitledBorder transitionBorder = new TitledBorder(" Transition Legs ");
        transitionBorder.setTitleFont(Utilities.TITLED_BORDER_FONT);
        transitionBorder.setTitleColor(Color.black);
        JPanel transitionPanel = new JPanel(new GridBagLayout());
        transitionPanel.setBorder(new CompoundBorder(transitionBorder, new EmptyBorder(5, 5, 5, 5)));
        Utilities.addComponent(transitionPanel, transitionSP, 0, 0, 2, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(transitionPanel, transitionLabel, 0, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(transitionPanel, newTransitionButton, 1, 1, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        legPanel = new LegPanel(this);
        JPanel tablePanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(tablePanel, mainPanel, 0, 0, 1, 1, 2, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.0D);
        Utilities.addComponent(tablePanel, transitionPanel, 0, 1, 1, 1, 1, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(tablePanel, legPanel, 1, 0, 1, 2, 1, 17, new Insets(1, 5, 1, 1), 0, 0, 0.5D, 0.5D);
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

    public static boolean showDialog(JDialog parent, TransitionModel transitionModel)
    {
        if(dialog == null)
            dialog = new EditTransitionDialog(parent);
        dialog.setTransitionModel(transitionModel);
        dialog.getLegPanel().setLegEnabled(false);
        dialog.getLegPanel().resetLeg();
        dialog.setTransitionUpdated(false);
        dialog.setVisible(true);
        return dialog.isTransitionUpdated();
    }

    public LegPanel getLegPanel()
    {
        return legPanel;
    }

    public void setColumnSizes()
    {
        if(transitionSP != null)
        {
            int paneWidth = transitionSP.getViewport().getSize().width;
            if(paneWidth > 0 && transitionTable.getColumnCount() == 6)
            {
                transitionTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.125D));
                transitionTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.375D));
                transitionTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.17499999999999999D));
                transitionTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.125D));
                transitionTable.getColumnModel().getColumn(4).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
                transitionTable.getColumnModel().getColumn(5).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
    }

    private void setTransitionModel(TransitionModel transitionModel)
    {
        this.transitionModel = transitionModel;
        ((ApproachLegTableModel)transitionTable.getModel()).setData(transitionModel.getLegAL());
        altComboBox.removeActionListener(this);
        typeComboBox.setSelectedItem(transitionModel.getTransitionType());
        fixTypeComboBox.setSelectedItem(transitionModel.getFixType());
        fixRegionTF.setText(transitionModel.getFixRegion());
        fixIdentTF.setText(transitionModel.getFixIdent());
        altSpinner.setValue(new Double(transitionModel.getAlt()));
        altComboBox.setSelectedItem(transitionModel.getAltMeasure());
        origAltMeasure = transitionModel.getAltMeasure();
        altComboBox.addActionListener(this);
    }

    private void displayLeg(Point point)
    {
        int row = transitionTable.rowAtPoint(point);
        int column = transitionTable.columnAtPoint(point);
        if(row < 0 || row >= transitionTable.getRowCount())
            return;
        if(column < 0 || column > 4)
            return;
        if(transitionTable.getSelectedRow() == -1)
            legPanel.setLegEnabled(false);
        else
            legPanel.displayLeg(((ApproachLegTableModel)transitionTable.getModel()).getLegModel(row));
    }

    public void setTransitionUpdated(boolean transitionUpdated)
    {
        this.transitionUpdated = transitionUpdated;
    }

    public boolean isTransitionUpdated()
    {
        return transitionUpdated;
    }

    private void deleteLeg(Point point)
    {
        int row = transitionTable.rowAtPoint(point);
        if(row == -1)
            return;
        if(transitionTable.columnAtPoint(point) != 5)
            return;
        LegModel legModel = ((ApproachLegTableModel)transitionTable.getModel()).getLegModel(row);
        StringBuffer buffer = new StringBuffer();
        buffer.append("Are you sure you want to delete the ");
        buffer.append(" transition leg\n");
        buffer.append("Type: ").append(legModel.getType()).append("\n");
        buffer.append("Fix Type: ").append(legModel.getFixType()).append("\n");
        buffer.append("Fix Ident: ").append(legModel.getFixIdent()).append("\n");
        if(JOptionPane.showConfirmDialog(this, buffer.toString(), "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            ((ApproachLegTableModel)transitionTable.getModel()).deleteRow(row);
            JOptionPane.showMessageDialog(this, "Leg Deleted.", "Deleted...", 1);
            return;
        }
    }

    private void updateAddLeg()
    {
        LegModel legModel = legPanel.updateAddLeg();
        if(legPanel.getAddButton().getText().equals("Update Leg"))
            ((ApproachLegTableModel)transitionTable.getModel()).fireTableDataChanged();
        else
        if(legPanel.getAddButton().getText().equals("Add Transition Leg"))
            ((ApproachLegTableModel)transitionTable.getModel()).addLeg(legModel);
        JOptionPane.showMessageDialog(this, "The Leg has been recorded.", "Leg Recorded", 1);
    }

    private void updateTransition()
    {
        transitionModel.setTransitionType((String)typeComboBox.getSelectedItem());
        transitionModel.setFixType((String)fixTypeComboBox.getSelectedItem());
        transitionModel.setFixRegion(fixRegionTF.getText());
        transitionModel.setFixIdent(fixIdentTF.getText());
        transitionModel.setAlt(((Double)altSpinner.getValue()).floatValue());
        transitionModel.setAltMeasure((String)altComboBox.getSelectedItem());
        transitionUpdated = true;
    }

    private void updateAltMeasure()
    {
        if(altComboBox.getSelectedItem().equals(origAltMeasure))
            return;
        if(SettingsEngine.getInstance().getAdjustMeasurements())
        {
            float altitude = ((Double)altSpinner.getValue()).floatValue();
            if(altComboBox.getSelectedItem().equals("M"))
                altitude /= 3.28F;
            else
                altitude *= 3.28F;
            altSpinner.setValue(new Double(altitude));
            origAltMeasure = (String)altComboBox.getSelectedItem();
        }
    }

    private void displayTransition(Point point)
    {
        int row = transitionTable.rowAtPoint(point);
        int column = transitionTable.columnAtPoint(point);
        if(row < 0 || row >= transitionTable.getRowCount())
            return;
        if(column < 0 || column > 4)
            return;
        else
            return;
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
            updateTransition();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
            setVisible(false);
        else
        if(event.getSource() == altComboBox)
            updateAltMeasure();
        else
        if(event.getSource() == newTransitionButton)
        {
            legPanel.resetLeg();
            legPanel.setLegEnabled(true);
            legPanel.getAddButton().setText("Add Transition Leg");
        } else
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
        if(event.getSource() == transitionTable)
            if(event.getClickCount() == 1)
                displayLeg(event.getPoint());
            else
            if(event.getClickCount() == 2)
                deleteLeg(event.getPoint());
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

    private TransitionModel transitionModel;
    private JTable transitionTable;
    private JScrollPane transitionSP;
    private JComboBox typeComboBox;
    private JComboBox fixTypeComboBox;
    private PopupTextField fixRegionTF;
    private PopupTextField fixIdentTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private JButton updateButton;
    private JButton cancelButton;
    private JButton newTransitionButton;
    private LegPanel legPanel;
    private String origAltMeasure;
    private boolean transitionUpdated;
    private static EditTransitionDialog dialog = null;

}
