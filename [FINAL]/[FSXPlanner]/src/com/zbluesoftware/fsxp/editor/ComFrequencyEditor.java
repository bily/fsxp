// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComFrequencyEditor.java

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

public class ComFrequencyEditor extends JSpinner
    implements TableCellEditor
{

    public ComFrequencyEditor()
    {
        super(new SpinnerNumberModel(108D, 108D, 136.99199999999999D, 0.050000000000000003D));
        listeners = new Vector();
        originalValue = 108F;
        changeEvent = new ChangeEvent(this);
        setEditor(new javax.swing.JSpinner.NumberEditor(this, "000.000"));
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        if(value instanceof Float)
            setValue(value);
        else
            setValue(new Float(108F));
        setBackground(Utilities.DESIGN_EDIT_BG_COLOR);
        setForeground(Color.black);
        originalValue = ((Float)getValue()).floatValue();
        return this;
    }

    public void cancelCellEditing()
    {
        fireEditingCanceled();
    }

    public Object getCellEditorValue()
    {
        return getValue();
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
        setValue(new Double(originalValue));
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
    private ChangeEvent changeEvent;
    private float originalValue;
}
