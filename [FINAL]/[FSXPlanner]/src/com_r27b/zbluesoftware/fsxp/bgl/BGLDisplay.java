// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:24:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BGLDisplay.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import com.zbluesoftware.fsxp.bgl.object.FileObject;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl:
//            FileTableModel, FileTableRenderer, PropertiesTableModel

public class BGLDisplay extends JDialog
    implements ActionListener, ListSelectionListener, TreeSelectionListener
{

    private BGLDisplay(Frame parent)
    {
        super(parent, "BGL Data", true);
        setResizable(false);
        fileTree = new JTree();
        fileTree.addTreeSelectionListener(this);
        JScrollPane treeSP = new JScrollPane(fileTree);
        treeSP.setPreferredSize(new Dimension(220, 300));
        fileTable = new JTable(new FileTableModel());
        fileTable.setPreferredScrollableViewportSize(new Dimension(580, 300));
        fileTable.setAutoResizeMode(0);
        fileTable.setShowVerticalLines(false);
        fileTable.setDefaultRenderer(java.lang.String.class, new FileTableRenderer());
        fileTable.getColumnModel().getColumn(0).setPreferredWidth(75);
        fileTable.getColumnModel().getColumn(1).setPreferredWidth(75);
        fileTable.getColumnModel().getColumn(2).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(3).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(4).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(5).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(6).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(7).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(8).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(9).setPreferredWidth(35);
        fileTable.getColumnModel().getColumn(10).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(11).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(12).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(13).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(14).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(15).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(16).setPreferredWidth(25);
        fileTable.getColumnModel().getColumn(17).setPreferredWidth(25);
        tableSP = new JScrollPane(fileTable);
        propertiesTable = new JTable(new PropertiesTableModel());
        propertiesTable.setPreferredScrollableViewportSize(new Dimension(800, 150));
        propertiesTable.setAutoResizeMode(0);
        propertiesTable.setSelectionMode(0);
        propertiesTable.getColumnModel().getColumn(0).setPreferredWidth(125);
        propertiesTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        propertiesTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        propertiesTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        propertiesTable.getColumnModel().getColumn(4).setPreferredWidth(215);
        propertiesTable.getColumnModel().getColumn(5).setPreferredWidth(228);
        propertiesTable.getSelectionModel().addListSelectionListener(this);
        JScrollPane propertiesSP = new JScrollPane(propertiesTable);
        JPanel mainPanel = new JPanel(new GridBagLayout());
        Utilities.addComponent(mainPanel, treeSP, 0, 0, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(mainPanel, tableSP, 1, 0, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(mainPanel, propertiesSP, 0, 1, 2, 1, 2, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.0D);
        getContentPane().add(mainPanel, "Center");
        closeButton = new JButton("CLOSE");
        closeButton.setFont(Utilities.BUTTON_FONT);
        closeButton.setForeground(Color.black);
        closeButton.addActionListener(this);
        packButton = new JButton("Pack");
        packButton.setFont(Utilities.BUTTON_FONT);
        packButton.setForeground(Color.black);
        packButton.addActionListener(this);
        jumpCB = new JCheckBox("Jump To Object Location", true);
        jumpCB.setFont(Utilities.LABEL_FONT);
        jumpCB.setForeground(Color.black);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(packButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(jumpCB);
        getContentPane().add(buttonPanel, "South");
        pack();
        setLocationRelativeTo(parent);
    }

    public static void showDialog(Frame parent, FileObject fileObject, String fileName)
    {
        if(display == null)
            display = new BGLDisplay(parent);
        display.updateDisplay(fileObject, fileName);
        display.setVisible(true);
    }

    private void updateDisplay(FileObject fileObject, String fileName)
    {
        ((FileTableModel)fileTable.getModel()).setFileName(fileName);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileObject);
        fileTree.removeTreeSelectionListener(this);
        fileTree.setModel(new DefaultTreeModel(root));
        for(int i = 0; i < fileObject.getObjectAL().size(); i++)
            populateTree((BaseObject)fileObject.getObjectAL().get(i), root);

        fileTree.addTreeSelectionListener(this);
        pack();
    }

    private void populateTree(BaseObject object, DefaultMutableTreeNode parentNode)
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(object);
        parentNode.add(node);
        ArrayList arrayList = object.getObjectAL();
        for(int i = 0; i < arrayList.size(); i++)
            populateTree((BaseObject)arrayList.get(i), node);

    }

    private void itemPropertySelected()
    {
        int row = propertiesTable.getSelectedRow();
        if(row >= 0)
            try
            {
                int offset = Integer.parseInt(propertiesTable.getValueAt(row, 1).toString());
                int length = Integer.parseInt(propertiesTable.getValueAt(row, 2).toString());
                ((FileTableModel)fileTable.getModel()).setItemLocation(offset, length);
            }
            catch(NumberFormatException nfe)
            {
                LogEngine.getInstance().log(nfe);
            }
        else
            ((FileTableModel)fileTable.getModel()).setItemLocation(-1, -1);
        fileTable.repaint();
    }

    private void objectSelected(BaseObject baseObject)
    {
        if(baseObject != null)
            ((FileTableModel)fileTable.getModel()).setObjectLocation(baseObject.getOffset(), baseObject.getLength());
        else
            ((FileTableModel)fileTable.getModel()).setObjectLocation(-1, -1);
        if(jumpCB.isSelected() && baseObject != null)
            tableSP.getVerticalScrollBar().setValue(Math.max(0, baseObject.getOffset() - 16));
        fileTable.repaint();
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == closeButton)
            setVisible(false);
        else
        if(event.getSource() == packButton)
            pack();
    }

    public void valueChanged(ListSelectionEvent event)
    {
        if(event.getSource() == propertiesTable.getSelectionModel() && !event.getValueIsAdjusting())
            itemPropertySelected();
    }

    public void valueChanged(TreeSelectionEvent event)
    {
        if(event.getSource() == fileTree)
            if(!fileTree.isSelectionEmpty() && (((DefaultMutableTreeNode)event.getNewLeadSelectionPath().getLastPathComponent()).getUserObject() instanceof BaseObject))
            {
                ((PropertiesTableModel)propertiesTable.getModel()).setData((BaseObject)((DefaultMutableTreeNode)event.getNewLeadSelectionPath().getLastPathComponent()).getUserObject());
                objectSelected((BaseObject)((DefaultMutableTreeNode)event.getNewLeadSelectionPath().getLastPathComponent()).getUserObject());
            } else
            {
                objectSelected(null);
            }
    }

    private JTable fileTable;
    private JTree fileTree;
    private JTable propertiesTable;
    private JScrollPane tableSP;
    private JButton closeButton;
    private JButton packButton;
    private JCheckBox jumpCB;
    private static BGLDisplay display = null;

}