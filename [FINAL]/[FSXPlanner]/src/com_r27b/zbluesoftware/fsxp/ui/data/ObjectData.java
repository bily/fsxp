// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ObjectData.java

package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JPanel;

public abstract class ObjectData extends JPanel
{

    public ObjectData()
    {
        listeners = new Vector();
    }

    public WindowBorder getWindowBorder()
    {
        return windowBorder;
    }

    public abstract void updateDisplay(BaseModel basemodel);

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        if(!listeners.contains(listener))
            listeners.add(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.remove(listener);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        super.firePropertyChange(propertyName, oldValue, newValue);
        if(listeners == null)
            return;
        Vector list;
        synchronized(this)
        {
            list = (Vector)listeners.clone();
        }
        if(list.size() == 0)
            return;
        PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
        for(int i = list.size() - 1; i >= 0; i--)
            ((PropertyChangeListener)list.elementAt(i)).propertyChange(event);

    }

    protected WindowBorder windowBorder;
    protected Vector listeners;
}
