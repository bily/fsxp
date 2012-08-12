// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServicesAvailabilityEditor.java

package com.zbluesoftware.fsxp.editor;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class ServicesAvailabilityEditor extends JComboBox
    implements TableCellEditor
{

    public ServicesAvailabilityEditor()
    {
        listeners = new Vector();
        originalValue = "";
        changeEvent = new ChangeEvent(this);
        setFont(Utilities.LABEL_FONT);
        setModel(getTypes());
    }

    private DefaultComboBoxModel getTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(" ");
        model.addElement("YES");
        model.addElement("NO");
        model.addElement("UNKNOWN");
        model.addElement("PRIOR_REQUEST");
        return model;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        if(value == null)
            value = "";
        boolean matchFound = false;
        int i = getModel().getSize() - 1;
        do
        {
            if(i < 0)
                break;
            if(((String)getItemAt(i)).equals(value.toString()))
            {
                setSelectedIndex(i);
                matchFound = true;
                break;
            }
            i--;
        } while(true);
        if(!matchFound)
            setSelectedIndex(0);
        setBackground(Utilities.DESIGN_EDIT_BG_COLOR);
        setForeground(Color.black);
        originalValue = (String)getSelectedItem();
        return this;
    }

    public void cancelCellEditing()
    {
        fireEditingCanceled();
    }

    public Object getCellEditorValue()
    {
        return getSelectedItem();
    }

    public boolean isCellEditable(EventObject event)
    {
        return true;
    }

    public boolean shouldSelectCell(EventObject event)
    {
        return true;
    }

    public boolean stopCellEditing()
    {
        fireEditingStopped();
        return true;
    }

    public void addCellEditorListener(CellEditorListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removeCellEditorListener(CellEditorListener listener)
    {
        listeners.remove(listener);
    }

    protected void fireEditingCanceled()
    {
        boolean matchFound = false;
        int i = getModel().getSize() - 1;
        do
        {
            if(i < 0)
                break;
            if(((String)getItemAt(i)).equals(originalValue))
            {
                setSelectedIndex(i);
                matchFound = true;
                break;
            }
            i--;
        } while(true);
        if(!matchFound)
            setSelectedIndex(0);
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        for(i = list.size() - 1; i >= 0; i--)
            ((CellEditorListener)list.elementAt(i)).editingCanceled(changeEvent);

    }

    protected void fireEditingStopped()
    {
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        for(int i = list.size() - 1; i >= 0; i--)
            ((CellEditorListener)list.elementAt(i)).editingStopped(changeEvent);

    }

    private Vector listeners;
    private String originalValue;
    private ChangeEvent changeEvent;
}
