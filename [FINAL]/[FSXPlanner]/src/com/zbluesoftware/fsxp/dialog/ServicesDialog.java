// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServicesDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.editor.ServicesAvailabilityEditor;
import com.zbluesoftware.fsxp.editor.ServicesTypeEditor;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.table.ServicesTableModel;
import com.zbluesoftware.fsxp.renderer.DataTableHeaderRenderer;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class ServicesDialog extends JDialog
    implements ActionListener, MouseListener
{

    private ServicesDialog(Frame parent)
    {
        super(parent, "Services", true);
        JTextArea instTA = new JTextArea(6, 50);
        instTA.setFont(Utilities.LABEL_FONT);
        instTA.setForeground(Color.black);
        instTA.setEditable(false);
        instTA.setOpaque(false);
        instTA.setLineWrap(true);
        instTA.setWrapStyleWord(true);
        StringBuffer buffer = new StringBuffer();
        buffer.append("This dialog lets you set the fuel services that are avilable at this airport.\n");
        buffer.append("To add a new service type just select the appropriate type from the last row of the table.\n");
        buffer.append("To delete an existing service, double click the X column for the fuel type.\n");
        buffer.append("\n");
        buffer.append("Note this does not add fuel locations or triggers to the airport.  It just adds the service");
        buffer.append(" type to the airport information that would appear in the GPS screens.");
        instTA.setText(buffer.toString());
        servicesTable = new JTable(new ServicesTableModel());
        servicesTable.setPreferredScrollableViewportSize(new Dimension(200, 100));
        servicesTable.getTableHeader().setDefaultRenderer(new DataTableHeaderRenderer());
        servicesTable.setSelectionMode(0);
        servicesTable.getColumnModel().getColumn(0).setCellEditor(new ServicesTypeEditor());
        servicesTable.getColumnModel().getColumn(1).setCellEditor(new ServicesAvailabilityEditor());
        servicesTable.addMouseListener(this);
        JScrollPane servicesSP = new JScrollPane(servicesTable);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, instTA, 0, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, servicesSP, 0, 1, 1, 1, 2, 17, new Insets(10, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        okButton = new JButton("OK");
        okButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        okButton.setForeground(Color.black);
        okButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(Utilities.DIALOG_BUTTON_FONT);
        cancelButton.setForeground(Color.black);
        cancelButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new ServicesDialog(parent);
        dialog.updateDialog(airportModel);
        dialog.setVisible(true);
    }

    private void updateDialog(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        ArrayList servicesAL = airportModel.getServicesAL();
        ArrayList data = new ArrayList();
        for(int i = 0; i < servicesAL.size(); i++)
            data.add((HashMap)((HashMap)servicesAL.get(i)).clone());

        ((ServicesTableModel)servicesTable.getModel()).setData(data);
    }

    public void deleteRow(Point point)
    {
        int row = servicesTable.rowAtPoint(point);
        if(row == -1 || row >= servicesTable.getRowCount() - 1)
            return;
        if(servicesTable.columnAtPoint(point) != 2)
            return;
        if(JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this fuel option?", "Confirm Delete...", 0) != 0)
            return;
        if(((ServicesTableModel)servicesTable.getModel()).deleteRow(row))
            JOptionPane.showMessageDialog(this, "The service has been deleted.", "Delete...", 1);
        else
            JOptionPane.showMessageDialog(this, "An error occured.  The services was to able to be deleted.", "Error...", 0);
    }

    private void endEditing()
    {
        servicesTable.getColumnModel().getColumn(0).getCellEditor().stopCellEditing();
        servicesTable.getColumnModel().getColumn(1).getCellEditor().stopCellEditing();
    }

    private void updateAirportServices()
    {
        ArrayList data = ((ServicesTableModel)servicesTable.getModel()).getData();
        ArrayList servicesAL = new ArrayList();
        for(int i = 0; i < data.size(); i++)
            servicesAL.add((HashMap)((HashMap)data.get(i)).clone());

        airportModel.setServicesAL(servicesAL);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            endEditing();
            updateAirportServices();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
        {
            endEditing();
            setVisible(false);
        }
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == servicesTable && event.getClickCount() == 2)
            deleteRow(event.getPoint());
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

    private AirportModel airportModel;
    private JTable servicesTable;
    private JButton okButton;
    private JButton cancelButton;
    private static ServicesDialog dialog = null;

}
