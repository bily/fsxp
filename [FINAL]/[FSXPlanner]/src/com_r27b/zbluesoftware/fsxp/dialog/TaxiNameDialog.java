// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiNameDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TaxiNameDialog extends JDialog
    implements ActionListener, ListSelectionListener, MouseListener
{

    private TaxiNameDialog(Frame parent)
    {
        super(parent, "Taxi Names", true);
        JLabel nameLabel = new JLabel("Taxi Names:");
        nameLabel.setFont(Utilities.LABEL_FONT);
        nameLabel.setForeground(Color.black);
        nameList = new JList();
        nameList.setVisibleRowCount(8);
        nameList.setPrototypeCellValue("------------------------------------------");
        nameList.setSelectionMode(0);
        nameList.addListSelectionListener(this);
        nameList.addMouseListener(this);
        JScrollPane nameSP = new JScrollPane(nameList);
        addButton = new JButton("Add");
        addButton.setFont(Utilities.BUTTON_FONT);
        addButton.setForeground(Color.black);
        addButton.addActionListener(this);
        editButton = new JButton("Edit");
        editButton.setFont(Utilities.BUTTON_FONT);
        editButton.setForeground(Color.black);
        editButton.setEnabled(false);
        editButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.setFont(Utilities.BUTTON_FONT);
        deleteButton.setForeground(Color.black);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(this);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(mainPanel, nameLabel, 0, 0, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, nameSP, 0, 1, 1, 3, 1, 17, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(mainPanel, addButton, 1, 1, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, editButton, 1, 2, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, deleteButton, 1, 3, 1, 1, 0, 18, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        closeButton = new JButton("Close");
        closeButton.setFont(Utilities.BUTTON_FONT);
        closeButton.setForeground(Color.black);
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new TaxiNameDialog(parent);
        dialog.updateDialog(airportModel);
        dialog.setVisible(true);
    }

    private void updateDialog(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        updateNameList();
        setTitle((new StringBuilder()).append("Taxi Names [").append(airportModel.getIdent()).append("]").toString());
    }

    private void updateNameList()
    {
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < airportModel.getTaxiNameAL().size(); i++)
            arrayList.add(((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).getName());

        Collections.sort(arrayList);
        DefaultListModel model = new DefaultListModel();
        for(int i = 0; i < arrayList.size(); i++)
        {
            String name = (String)arrayList.get(i);
            if(name.length() == 0)
                name = "[none]";
            model.addElement(name);
        }

        nameList.setModel(model);
    }

    private void addName()
    {
        String newName = JOptionPane.showInputDialog(this, "Enter the new Taxi Name:", "");
        if(newName == null || newName.length() == 0)
            return;
        for(int i = airportModel.getTaxiNameAL().size() - 1; i >= 0; i--)
            if(((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).getName().equalsIgnoreCase(newName))
            {
                JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The Taxi Name '").append(newName).append("' already exists.").toString(), "Name Exists...", 0);
                return;
            }

        TaxiNameModel model = new TaxiNameModel();
        model.setName(newName);
        model.setIndex(airportModel.incrementTaxiNameIndex());
        airportModel.addTaxiNameModel(model);
        updateNameList();
        JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The Taxi Name '").append(newName).append("' has been added to the airport.").toString(), "Taxi Name Added...", 1);
    }

    private void editName()
    {
        if(nameList.getSelectedValue() == null)
            return;
        String taxiName = (String)nameList.getSelectedValue();
        String newName = JOptionPane.showInputDialog(this, "Enter the new Taxi Name:", taxiName);
        if(newName == null || newName.equalsIgnoreCase(taxiName))
            return;
        int i;
        for(i = airportModel.getTaxiNameAL().size() - 1; i >= 0; i--)
            if(((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).getName().equalsIgnoreCase(newName))
            {
                JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The Taxi Name '").append(newName).append("' already exists.").toString(), "Name Exists...", 0);
                return;
            }

        i = airportModel.getTaxiNameAL().size() - 1;
        do
        {
            if(i < 0)
                break;
            if(((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).getName().equals(taxiName))
            {
                ((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).setName(newName);
                break;
            }
            i--;
        } while(true);
        updateNameList();
        airportModel.firePropertyChange("taxiNames", "", airportModel.getTaxiNameAL());
        JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The Taxi Name '").append(taxiName).append("' has been changed to '").append(newName).append("'.").toString(), "Taxi Name Changed...", 1);
    }

    private void deleteName()
    {
        if(nameList.getSelectedValue() == null)
            return;
        String taxiName = (String)nameList.getSelectedValue();
        if(taxiName.equals("[none]"))
            taxiName = "";
        TaxiNameModel model = null;
        int i = airportModel.getTaxiNameAL().size() - 1;
        do
        {
            if(i < 0)
                break;
            if(((TaxiNameModel)airportModel.getTaxiNameAL().get(i)).getName().equals(taxiName))
            {
                model = (TaxiNameModel)airportModel.getTaxiNameAL().get(i);
                break;
            }
            i--;
        } while(true);
        int modelIndex = model.getIndex();
        int totalPaths = 0;
        for(i = airportModel.getTaxiwayPathAL().size() - 1; i >= 0; i--)
        {
            TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel)airportModel.getTaxiwayPathAL().get(i);
            if(!taxiwayPathModel.getType().equals("RUNWAY") && taxiwayPathModel.getName() == modelIndex)
                totalPaths++;
        }

        if(totalPaths > 0)
        {
            JOptionPane.showMessageDialog(this, (new StringBuilder()).append("The taxi name '").append(taxiName).append("' is used by ").append(totalPaths).append(" taxiways.\n\nIt cannot be deleted.").toString(), "Taxi Name In Use...", 0);
            return;
        }
        if(JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Are you sure you want to delete taxi name '").append(taxiName).append("'?").toString(), "Confirm Delete...", 0) != 0)
        {
            return;
        } else
        {
            airportModel.removeTaxiNameModel(model);
            ((DefaultListModel)nameList.getModel()).remove(nameList.getSelectedIndex());
            JOptionPane.showMessageDialog(this, (new StringBuilder()).append("Taxi name '").append(taxiName).append("' has been deleted.").toString(), "Taxi Name Deleted...", 1);
            return;
        }
    }

    private void enableOptions()
    {
        boolean enabled = !nameList.isSelectionEmpty();
        if(enabled && ((String)nameList.getSelectedValue()).equals("[none]"))
            enabled = false;
        editButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == addButton)
            addName();
        else
        if(event.getSource() == editButton)
            editName();
        else
        if(event.getSource() == deleteButton)
            deleteName();
        else
        if(event.getSource() == closeButton)
            setVisible(false);
    }

    public void valueChanged(ListSelectionEvent event)
    {
        if(event.getSource() == nameList)
            enableOptions();
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == nameList && event.getClickCount() == 2 && nameList.locationToIndex(event.getPoint()) >= 0)
        {
            nameList.setSelectedIndex(nameList.locationToIndex(event.getPoint()));
            editName();
        }
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    private AirportModel airportModel;
    private JList nameList;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton closeButton;
    private static TaxiNameDialog dialog = null;

}
