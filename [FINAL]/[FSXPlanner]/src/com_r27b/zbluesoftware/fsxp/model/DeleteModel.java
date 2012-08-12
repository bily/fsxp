// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DeleteModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.PrepareEngine;
import java.util.ArrayList;

public class DeleteModel
{

    public DeleteModel()
    {
        deleteAllApproaches = PrepareEngine.getInstance().getDeleteApproaches();
        deleteAllApronLights = PrepareEngine.getInstance().getDeleteApronLights();
        deleteAllAprons = PrepareEngine.getInstance().getDeleteAprons();
        deleteAllFrequencies = PrepareEngine.getInstance().getDeleteFrequencies();
        deleteAllHelipads = PrepareEngine.getInstance().getDeleteHelipads();
        deleteAllRunways = PrepareEngine.getInstance().getDeleteRunways();
        deleteAllStarts = PrepareEngine.getInstance().getDeleteStarts();
        deleteAllTaxiways = PrepareEngine.getInstance().getDeleteTaxiways();
        deleteAllBlastFences = PrepareEngine.getInstance().getDeleteBlastFences();
        deleteAllBoundaryFences = PrepareEngine.getInstance().getDeleteBoundaryFences();
        deleteAllControlTowers = PrepareEngine.getInstance().getDeleteTowers();
        deleteAllJetways = PrepareEngine.getInstance().getDeleteJetways();
        runwayAL = new ArrayList();
        startAL = new ArrayList();
        frequencyAL = new ArrayList();
    }

    public boolean getDeleteAllApproaches()
    {
        return deleteAllApproaches;
    }

    public void setDeleteAllApproaches(boolean deleteAllApproaches)
    {
        this.deleteAllApproaches = deleteAllApproaches;
    }

    public boolean getDeleteAllApronLights()
    {
        return deleteAllApronLights;
    }

    public void setDeleteAllApronLights(boolean deleteAllApronLights)
    {
        this.deleteAllApronLights = deleteAllApronLights;
    }

    public boolean getDeleteAllAprons()
    {
        return deleteAllAprons;
    }

    public void setDeleteAllAprons(boolean deleteAllAprons)
    {
        this.deleteAllAprons = deleteAllAprons;
    }

    public boolean getDeleteAllFrequencies()
    {
        return deleteAllFrequencies;
    }

    public void setDeleteAllFrequencies(boolean deleteAllFrequencies)
    {
        this.deleteAllFrequencies = deleteAllFrequencies;
    }

    public boolean getDeleteAllHelipads()
    {
        return deleteAllHelipads;
    }

    public void setDeleteAllHelipads(boolean deleteAllHelipads)
    {
        this.deleteAllHelipads = deleteAllHelipads;
    }

    public boolean getDeleteAllRunways()
    {
        return deleteAllRunways;
    }

    public void setDeleteAllRunways(boolean deleteAllRunways)
    {
        this.deleteAllRunways = deleteAllRunways;
    }

    public boolean getDeleteAllStarts()
    {
        return deleteAllStarts;
    }

    public void setDeleteAllStarts(boolean deleteAllStarts)
    {
        this.deleteAllStarts = deleteAllStarts;
    }

    public boolean getDeleteAllTaxiways()
    {
        return deleteAllTaxiways;
    }

    public void setDeleteAllTaxiways(boolean deleteAllTaxiways)
    {
        this.deleteAllTaxiways = deleteAllTaxiways;
    }

    public boolean getDeleteAllBlastFences()
    {
        return deleteAllBlastFences;
    }

    public void setDeleteAllBlastFences(boolean deleteAllBlastFences)
    {
        this.deleteAllBlastFences = deleteAllBlastFences;
    }

    public boolean getDeleteAllBoundaryFences()
    {
        return deleteAllBoundaryFences;
    }

    public void setDeleteAllBoundaryFences(boolean deleteAllBoundaryFences)
    {
        this.deleteAllBoundaryFences = deleteAllBoundaryFences;
    }

    public boolean getDeleteAllControlTowers()
    {
        return deleteAllControlTowers;
    }

    public void setDeleteAllControlTowers(boolean deleteAllControlTowers)
    {
        this.deleteAllControlTowers = deleteAllControlTowers;
    }

    public boolean getDeleteAllJetways()
    {
        return deleteAllJetways;
    }

    public void setDeleteAllJetways(boolean deleteAllJetways)
    {
        this.deleteAllJetways = deleteAllJetways;
    }

    public ArrayList getRunwayAL()
    {
        return runwayAL;
    }

    public void setRunwayAL(ArrayList runwayAL)
    {
        this.runwayAL = runwayAL;
    }

    public ArrayList getStartAL()
    {
        return startAL;
    }

    public void setStartAL(ArrayList startAL)
    {
        this.startAL = startAL;
    }

    public ArrayList getFrequencyAL()
    {
        return frequencyAL;
    }

    public void setFrequencyAL(ArrayList frequencyAL)
    {
        this.frequencyAL = frequencyAL;
    }

    private ArrayList runwayAL;
    private ArrayList startAL;
    private ArrayList frequencyAL;
    private boolean deleteAllApproaches;
    private boolean deleteAllApronLights;
    private boolean deleteAllAprons;
    private boolean deleteAllFrequencies;
    private boolean deleteAllHelipads;
    private boolean deleteAllRunways;
    private boolean deleteAllStarts;
    private boolean deleteAllTaxiways;
    private boolean deleteAllBlastFences;
    private boolean deleteAllBoundaryFences;
    private boolean deleteAllControlTowers;
    private boolean deleteAllJetways;
}
