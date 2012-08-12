// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirportListModel.java

package com.zbluesoftware.fsxp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.JInternalFrame;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            AirportModel

public class AirportListModel
{

    private AirportListModel()
    {
        airportModelAL = new ArrayList();
        airportHT = new Hashtable();
        listeners = new Vector();
    }

    public static synchronized AirportListModel getInstance()
    {
        if(model == null)
            model = new AirportListModel();
        return model;
    }

    public synchronized void addAirportModel(AirportModel airportModel)
    {
        if(!airportModelAL.contains(airportModel))
            airportModelAL.add(airportModel);
    }

    public synchronized void removeAirportModel(AirportModel airportModel)
    {
        airportModelAL.remove(airportModel);
    }

    public synchronized void removeAirportModel(String ident, String fileName)
    {
        for(int i = airportModelAL.size() - 1; i >= 0; i--)
        {
            AirportModel airportModel = (AirportModel)airportModelAL.get(i);
            if(airportModel.getIdent().toLowerCase().equals(ident.toLowerCase()) && airportModel.getFileName().equals(fileName))
            {
                airportModelAL.remove(i);
                return;
            }
        }

    }

    public AirportModel getCurrentAirport()
    {
        return currentAirport;
    }

    public synchronized void setCurrentAirport(AirportModel currentAirport)
    {
        this.currentAirport = currentAirport;
        firePropertyChange("currentAirport", "", "");
    }

    public ArrayList getAirportModelAL()
    {
        return airportModelAL;
    }

    public AirportModel getAirportModel(String ident, String fileName)
    {
        for(int i = airportModelAL.size() - 1; i >= 0; i--)
        {
            AirportModel airportModel = (AirportModel)airportModelAL.get(i);
            if(airportModel.getIdent().equals(ident) && airportModel.getFileName().equals(fileName))
                return airportModel;
        }

        return null;
    }

    public synchronized void setAirportFrame(String ident, String fileName, JInternalFrame frame)
    {
        airportHT.put(getAirportName(ident, fileName), frame);
    }

    public JInternalFrame getAirportFrame(String ident, String fileName)
    {
        if(airportHT.containsKey(getAirportName(ident, fileName)))
            return (JInternalFrame)airportHT.get(getAirportName(ident, fileName));
        else
            return null;
    }

    public void removeAirportFrame(String ident, String fileName)
    {
        airportHT.remove(getAirportName(ident, fileName));
    }

    private String getAirportName(String ident, String fileName)
    {
        StringBuffer buffer = new StringBuffer(ident);
        if(fileName.length() > 0)
            buffer.append("[").append(fileName).append("]");
        return buffer.toString();
    }

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

    private ArrayList airportModelAL;
    private Hashtable airportHT;
    private AirportModel currentAirport;
    private Vector listeners;
    private static AirportListModel model = null;

}
