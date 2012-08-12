// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ILSLineUpdates.java

package com.zbluesoftware.fsxp.thread;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.model.AirportModel;
import java.util.Vector;

public class ILSLineUpdates
{

    private ILSLineUpdates()
    {
        airportModels = new Vector();
    }

    public static ILSLineUpdates getInstance()
    {
        if(ilsLineUpdates == null)
            ilsLineUpdates = new ILSLineUpdates();
        return ilsLineUpdates;
    }

    public synchronized void addAirportModel(AirportModel airportModel)
    {
        airportModels.add(airportModel);
        notifyAll();
    }

    public synchronized AirportModel getNextAirportModel()
    {
        while(airportModels.size() == 0) 
            try
            {
                wait();
            }
            catch(InterruptedException ie)
            {
                LogEngine.getInstance().log(ie);
            }
        return (AirportModel)airportModels.remove(0);
    }

    private Vector airportModels;
    private static ILSLineUpdates ilsLineUpdates = null;

}
