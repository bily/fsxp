// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirlineCodeDialog.java

package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.table.AirlineCodeTableModel;
import com.zbluesoftware.fsxp.renderer.ColorPrefsTableRenderer;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Document;

public class AirlineCodeDialog extends JDialog
    implements ActionListener, ComponentListener, DocumentListener, MouseListener
{

    private AirlineCodeDialog(Frame parent)
    {
        super(parent, "Airline Codes", true);
        JLabel findLabel = new JLabel("Find airline:");
        findLabel.setFont(Utilities.LABEL_FONT);
        findLabel.setForeground(Color.black);
        findTF = new JTextField(25);
        findTF.setFont(Utilities.TEXT_FIELD_FONT);
        findTF.setForeground(Color.black);
        findTF.getDocument().addDocumentListener(this);
        codeTable = new JTable(new AirlineCodeTableModel());
        codeTable.setPreferredScrollableViewportSize(new Dimension(400, 300));
        codeTable.setDefaultRenderer(java.awt.Color.class, new ColorPrefsTableRenderer());
        codeTable.setRowHeight(30);
        codeTable.setIntercellSpacing(new Dimension(5, 5));
        codeTable.addMouseListener(this);
        codeSP = new JScrollPane(codeTable);
        JLabel selectLabel = new JLabel("double click to select");
        selectLabel.setFont(Utilities.LABEL_FONT);
        selectLabel.setForeground(Color.red);
        JLabel codeLabel = new JLabel("Airline Codes for parking space:");
        codeLabel.setFont(Utilities.LABEL_FONT);
        codeLabel.setForeground(Color.black);
        codeTF = new JTextField(20);
        codeTF.setFont(Utilities.TEXT_FIELD_FONT);
        codeTF.setForeground(Color.black);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        Utilities.addComponent(mainPanel, findLabel, 0, 0, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, findTF, 1, 0, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, codeSP, 0, 1, 2, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, selectLabel, 0, 2, 1, 1, 0, 10, new Insets(1, 1, 10, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, codeLabel, 0, 3, 1, 1, 0, 13, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, codeTF, 1, 3, 1, 1, 0, 17, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(mainPanel, new JSeparator(), 0, 4, 2, 1, 2, 17, new Insets(10, 1, 10, 1), 0, 0, 0.5D, 0.0D);
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
        addComponentListener(this);
        pack();
        setLocationRelativeTo(parent);
    }

    public static String showDialog(Frame parent, String currentCode)
    {
        if(dialog == null)
            dialog = new AirlineCodeDialog(parent);
        dialog.setCurrentCode(currentCode);
        dialog.setVisible(true);
        return dialog.getCurrentCode();
    }

    public void setCurrentCode(String currentCode)
    {
        this.currentCode = currentCode;
        codeTF.setText(currentCode);
    }

    public String getCurrentCode()
    {
        return currentCode;
    }

    public void setColumnSizes()
    {
        if(codeSP != null)
        {
            int paneWidth = codeSP.getViewport().getSize().width;
            if(paneWidth > 0 && codeTable.getColumnCount() == 3)
            {
                codeTable.getColumnModel().getColumn(0).setPreferredWidth((int)((double)paneWidth * 0.5D));
                codeTable.getColumnModel().getColumn(1).setPreferredWidth((int)((double)paneWidth * 0.14999999999999999D));
                codeTable.getColumnModel().getColumn(2).setPreferredWidth((int)((double)paneWidth * 0.34999999999999998D));
            }
        }
    }

    private void searchCodes()
    {
        String text = findTF.getText().trim();
        int total = codeTable.getRowCount() - 1;
        int lowPos = 0;
        int highPos = total;
        int currentPos = total / 2;
        boolean found = false;
        if(text.length() == 0)
            return;
        do
        {
            String testString = (String)codeTable.getValueAt(currentPos, 0);
            if(testString.startsWith(text))
                found = true;
            else
            if(text.compareToIgnoreCase(testString) < 0)
            {
                highPos = currentPos;
                currentPos = (lowPos + highPos) / 2;
            } else
            {
                lowPos = currentPos;
                currentPos = (lowPos + highPos) / 2;
            }
            if(Math.abs(lowPos - highPos) <= 1)
                found = true;
        } while(!found);
        codeTable.setRowSelectionInterval(currentPos + 1, currentPos + 1);
        codeSP.getVerticalScrollBar().setValue((codeSP.getVerticalScrollBar().getMaximum() / total) * (currentPos - 4));
    }

    private void selectCode(Point point)
    {
        int row = codeTable.rowAtPoint(point);
        if(row == -1)
            return;
        String code = (String)codeTable.getValueAt(row, 1);
        StringBuffer buffer = new StringBuffer(codeTF.getText().trim());
        if(buffer.length() > 0)
            buffer.append(",");
        buffer.append(code);
        codeTF.setText(buffer.toString());
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == okButton)
        {
            currentCode = codeTF.getText().trim();
            setVisible(false);
        } else
        if(event.getSource() == cancelButton)
        {
            currentCode = null;
            setVisible(false);
        }
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

    public void changedUpdate(DocumentEvent documentevent)
    {
    }

    public void insertUpdate(DocumentEvent event)
    {
        if(event.getDocument() == findTF.getDocument())
            searchCodes();
    }

    public void removeUpdate(DocumentEvent event)
    {
        if(event.getDocument() == findTF.getDocument())
            searchCodes();
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == codeTable && event.getClickCount() == 2)
            selectCode(event.getPoint());
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

    private JTextField codeTF;
    private JTextField findTF;
    private JTable codeTable;
    private JScrollPane codeSP;
    private JButton okButton;
    private JButton cancelButton;
    private String currentCode;
    private static AirlineCodeDialog dialog = null;

}
