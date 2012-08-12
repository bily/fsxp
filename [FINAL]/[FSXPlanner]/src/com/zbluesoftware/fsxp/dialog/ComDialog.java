// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.editor.*;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.table.ComTableModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

public class ComDialog extends JDialog
    implements ActionListener, ComponentListener, MouseListener
{

    private ComDialog(Frame parent)
    {
        super(parent, "Communication Frequencies", true);
        JTextArea infoTA = new JTextArea(5, 5);
        infoTA.setFont(Utilities.LABEL_FONT);
        infoTA.setForeground(Color.black);
        infoTA.setLineWrap(true);
        infoTA.setWrapStyleWord(true);
        infoTA.setEditable(false);
        infoTA.setOpaque(false);
        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(textPanel, infoTA, 0, 0, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(textPanel, "North");
        StringBuffer buffer = new StringBuffer();
        buffer.append("This dialog displays the communication frequencies for the airport.\n");
        buffer.append(" To modify an existing frequency simple type into the appropriate cell in the table.\n");
        buffer.append(" To delete a frequency double click on the X column of the frequency you wish to delete.\n");
        buffer.append(" To add a frequency enter it into the empty row at the bottom of the table.");
        infoTA.setText(buffer.toString());
        comTable = new JTable(new ComTableModel());
        comTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        comTable.getTableHeader().setReorderingAllowed(false);
        comTable.setAutoResizeMode(0);
        comTable.setSelectionMode(0);
        comTable.getColumnModel().getColumn(0).setCellEditor(new ComFrequencyEditor());
        comTable.getColumnModel().getColumn(1).setCellEditor(new ComTypeEditor());
        comTable.getColumnModel().getColumn(2).setCellEditor(new StringEditor());
        comTable.addMouseListener(this);
        setColumnSizes();
        comSP = new JScrollPane(comTable);
        JPanel comPanel = new JPanel(new GridBagLayout());
        comPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(comPanel, comSP, 0, 0, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(comPanel, "Center");
        closeButton = new JButton("Close");
        closeButton.setFont(Utilities.BUTTON_FONT);
        closeButton.setForeground(Color.black);
        closeButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, "South");
        addComponentListener(this);
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, AirportModel airportModel)
    {
        if(dialog == null)
            dialog = new ComDialog(parent);
        dialog.setAirportModel(airportModel);
        dialog.setVisible(true);
    }

    public void setColumnSizes()
    {
        if(comSP != null)
        {
            int paneWidth = comSP.getViewport().getSize().width;
            if(paneWidth > 0 && comTable.getColumnCount() == 4)
            {
                comTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.14999999999999999D));
                comTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.34999999999999998D));
                comTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.40000000000000002D));
                comTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
    }

    public void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        ((ComTableModel)comTable.getModel()).setData(airportModel.getComAL());
        setTitle((new StringBuilder()).append("Communication Frequencies [").append(airportModel.getIdent()).append("]").toString());
    }

    private void deleteCom(Point point)
    {
        int row = comTable.rowAtPoint(point);
        int column = comTable.columnAtPoint(point);
        if(row < 0 || row >= comTable.getRowCount() - 1)
            return;
        if(column != 3)
            return;
        if(JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Are you sure you want to delete the frequency: ").append(comTable.getValueAt(row, 0)).append(" [").append(comTable.getValueAt(row, 1)).append("]").toString(), "Confirm Delete...", 0) == 0)
        {
            ((ComTableModel)comTable.getModel()).deleteRow(row);
            JOptionPane.showMessageDialog(this, "The frequency has been deleted", "Delete...", 1);
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == closeButton)
            setVisible(false);
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

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == comTable && event.getClickCount() == 2)
            deleteCom(event.getPoint());
    }

    private AirportModel airportModel;
    private JTable comTable;
    private JScrollPane comSP;
    private JButton closeButton;
    private static ComDialog dialog = null;

}
