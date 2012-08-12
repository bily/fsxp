// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachesDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.ApproachModel;
import com.zbluesoftware.fsxp.model.table.ApproachTableModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

// Referenced classes of package com.zbluesoftware.fsxp.dialog:
//            EditApproachDialog

public class ApproachesDialog extends JDialog
    implements ActionListener, ComponentListener, MouseListener
{

    private ApproachesDialog(Frame parent)
    {
        super(parent, "Airport Approaches", true);
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
        buffer.append("This dialog displays the airport approaches.\n");
        buffer.append(" To modify an existing approach, double click on its row in the table.\n");
        buffer.append(" To delete an approach double click on the X column of the approach you wish to delete.\n");
        buffer.append(" To add an approach, click the Add Approach button.");
        infoTA.setText(buffer.toString());
        addButton = new JButton("add approach");
        addButton.setFont(Utilities.BUTTON_FONT);
        addButton.setForeground(Color.black);
        addButton.addActionListener(this);
        approachTable = new JTable(new ApproachTableModel());
        approachTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        approachTable.getTableHeader().setReorderingAllowed(false);
        approachTable.setAutoResizeMode(0);
        approachTable.setSelectionMode(0);
        approachTable.addMouseListener(this);
        setColumnSizes();
        approachSP = new JScrollPane(approachTable);
        JPanel approachPanel = new JPanel(new GridBagLayout());
        approachPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(approachPanel, addButton, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(approachPanel, approachSP, 0, 1, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        getContentPane().add(approachPanel, "Center");
        closeButton = new JButton("Close");
        closeButton.setFont(Utilities.DIALOG_BUTTON_FONT);
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
            dialog = new ApproachesDialog(parent);
        dialog.setAirportModel(airportModel);
        dialog.setVisible(true);
    }

    public void setColumnSizes()
    {
        if(approachSP != null)
        {
            int paneWidth = approachSP.getViewport().getSize().width;
            if(paneWidth > 0 && approachTable.getColumnCount() == 5)
            {
                approachTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.29999999999999999D));
                approachTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.14999999999999999D));
                approachTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.25D));
                approachTable.getColumnModel().getColumn(3).setPreferredWidth((int)((double)paneWidth * 0.20000000000000001D));
                approachTable.getColumnModel().getColumn(4).setPreferredWidth((int)((double)paneWidth * 0.10000000000000001D));
            }
        }
    }

    public void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        ((ApproachTableModel)approachTable.getModel()).setData(airportModel.getApproachAL());
        setTitle((new StringBuilder()).append("Airport Approaches [").append(airportModel.getIdent()).append("]").toString());
    }

    private void deleteApproach(Point point)
    {
        int row = approachTable.rowAtPoint(point);
        int column = approachTable.columnAtPoint(point);
        if(row < 0 || row >= approachTable.getRowCount())
            return;
        if(column != 4)
            return;
        if(JOptionPane.showConfirmDialog(this, (new StringBuilder()).append("Are you sure you want to delete the approach: ").append(approachTable.getValueAt(row, 0)).append(" [").append(approachTable.getValueAt(row, 1)).append("]").toString(), "Confirm Delete...", 0) == 0)
        {
            ((ApproachTableModel)approachTable.getModel()).deleteRow(row);
            JOptionPane.showMessageDialog(this, "The approach has been deleted", "Delete...", 1);
        }
    }

    private void editApproach(Point point)
    {
        int row = approachTable.rowAtPoint(point);
        int column = approachTable.columnAtPoint(point);
        if(row < 0 || row >= approachTable.getRowCount())
            return;
        if(column == 4)
        {
            return;
        } else
        {
            EditApproachDialog.showDialog(this, ((ApproachTableModel)approachTable.getModel()).getApproach(row));
            ((ApproachTableModel)approachTable.getModel()).fireTableDataChanged();
            return;
        }
    }

    private void addApproach()
    {
        ApproachModel approachModel = new ApproachModel();
        if(EditApproachDialog.showDialog(this, approachModel))
            ((ApproachTableModel)approachTable.getModel()).addApproach(approachModel);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == closeButton)
            setVisible(false);
        else
        if(event.getSource() == addButton)
            addApproach();
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
        if(event.getSource() == approachTable && event.getClickCount() == 2)
        {
            deleteApproach(event.getPoint());
            editApproach(event.getPoint());
        }
    }

    private AirportModel airportModel;
    private JTable approachTable;
    private JScrollPane approachSP;
    private JButton addButton;
    private JButton closeButton;
    private static ApproachesDialog dialog = null;

}
