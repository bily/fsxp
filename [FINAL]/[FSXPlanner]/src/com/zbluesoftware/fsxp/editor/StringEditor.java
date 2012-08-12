// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringEditor.java

package com.zbluesoftware.fsxp.editor;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class StringEditor extends JTextField
    implements TableCellEditor
{

    public StringEditor()
    {
        listeners = new Vector();
        originalValue = "";
        changeEvent = new ChangeEvent(this);
        setFont(Utilities.LABEL_FONT);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        setText(value.toString());
        setBackground(Utilities.DESIGN_EDIT_BG_COLOR);
        setForeground(Color.black);
        originalValue = getText();
        return this;
    }

    public void cancelCellEditing()
    {
        fireEditingCanceled();
    }

    public Object getCellEditorValue()
    {
        return getText();
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
        setText(originalValue);
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        for(int i = list.size() - 1; i >= 0; i--)
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
