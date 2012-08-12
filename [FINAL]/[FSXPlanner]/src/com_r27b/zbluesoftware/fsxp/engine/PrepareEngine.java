// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrepareEngine.java

package com.zbluesoftware.fsxp.engine;

import java.util.HashMap;
import java.util.prefs.Preferences;

public class PrepareEngine
{

    private PrepareEngine()
    {
        writeAprons = true;
        writeBlastFences = true;
        writeBoundaryFences = true;
        writeHelipads = true;
        writeJetways = true;
        writeRunways = true;
        writeStarts = true;
        writeTaxiways = true;
        writeTaxiwaySigns = true;
        writeTriggers = true;
        writeTowers = true;
        writeScenery = true;
        writeApproaches = false;
        writeBoundaries = true;
        writeCOMs = true;
        writeDMEs = true;
        writeGeopols = true;
        writeMarkers = true;
        writeNDBs = true;
        writeRoutes = true;
        writeVORs = true;
        writeWaypoints = true;
        deleteApproaches = false;
        deleteApronLights = true;
        deleteAprons = true;
        deleteFrequencies = false;
        deleteHelipads = true;
        deleteRunways = true;
        deleteStarts = true;
        deleteTaxiways = true;
        deleteBlastFences = true;
        deleteBoundaryFences = true;
        deleteTowers = false;
        deleteJetways = true;
        airportExRect = true;
        excludeAll = false;
        excludeBeacons = false;
        excludeEffect = false;
        excludeExtrusion = false;
        excludeBuilding = false;
        excludeLibrary = false;
        excludeSign = false;
        excludeTrigger = false;
        excludeWindsock = false;
        latitudeMinimum = -1D;
        latitudeMaximum = 0.0D;
        longitudeMinimum = -1D;
        longitudeMaximum = 0.0D;
        runwayHM = new HashMap();
        startHM = new HashMap();
        frequencyHM = new HashMap();
        readPreferences();
    }

    public static PrepareEngine getInstance()
    {
        if(engine == null)
            engine = new PrepareEngine();
        return engine;
    }

    public boolean getWriteAprons()
    {
        return writeAprons;
    }

    public void setWriteAprons(boolean writeAprons)
    {
        this.writeAprons = writeAprons;
    }

    public boolean getWriteBlastFences()
    {
        return writeBlastFences;
    }

    public void setWriteBlastFences(boolean writeBlastFences)
    {
        this.writeBlastFences = writeBlastFences;
    }

    public boolean getWriteBoundaryFences()
    {
        return writeBoundaryFences;
    }

    public void setWriteBoundaryFences(boolean writeBoundaryFences)
    {
        this.writeBoundaryFences = writeBoundaryFences;
    }

    public boolean getWriteHelipads()
    {
        return writeHelipads;
    }

    public void setWriteHelipads(boolean writeHelipads)
    {
        this.writeHelipads = writeHelipads;
    }

    public boolean getWriteJetways()
    {
        return writeJetways;
    }

    public void setWriteJetways(boolean writeJetways)
    {
        this.writeJetways = writeJetways;
    }

    public boolean getWriteRunways()
    {
        return writeRunways;
    }

    public void setWriteRunways(boolean writeRunways)
    {
        this.writeRunways = writeRunways;
    }

    public boolean getWriteStarts()
    {
        return writeStarts;
    }

    public void setWriteStarts(boolean writeStarts)
    {
        this.writeStarts = writeStarts;
    }

    public boolean getWriteTaxiways()
    {
        return writeTaxiways;
    }

    public void setWriteTaxiways(boolean writeTaxiways)
    {
        this.writeTaxiways = writeTaxiways;
    }

    public boolean getWriteTaxiwaySigns()
    {
        return writeTaxiwaySigns;
    }

    public void setWriteTaxiwaySigns(boolean writeTaxiwaySigns)
    {
        this.writeTaxiwaySigns = writeTaxiwaySigns;
    }

    public boolean getWriteTriggers()
    {
        return writeTriggers;
    }

    public void setWriteTriggers(boolean writeTriggers)
    {
        this.writeTriggers = writeTriggers;
    }

    public boolean getWriteTowers()
    {
        return writeTowers;
    }

    public void setWriteTowers(boolean writeTowers)
    {
        this.writeTowers = writeTowers;
    }

    public boolean getWriteScenery()
    {
        return writeScenery;
    }

    public void setWriteScenery(boolean writeScenery)
    {
        this.writeScenery = writeScenery;
    }

    public boolean getWriteApproaches()
    {
        return writeApproaches;
    }

    public void setWriteApproaches(boolean writeApproaches)
    {
        this.writeApproaches = writeApproaches;
    }

    public boolean getWriteBoundaries()
    {
        return writeBoundaries;
    }

    public void setWriteBoundaries(boolean writeBoundaries)
    {
        this.writeBoundaries = writeBoundaries;
    }

    public boolean getWriteCOMs()
    {
        return writeCOMs;
    }

    public void setWriteCOMs(boolean writeCOMs)
    {
        this.writeCOMs = writeCOMs;
    }

    public boolean getWriteDMEs()
    {
        return writeDMEs;
    }

    public void setWriteDMEs(boolean writeDMEs)
    {
        this.writeDMEs = writeDMEs;
    }

    public boolean getWriteGeopols()
    {
        return writeGeopols;
    }

    public void setWriteGeopols(boolean writeGeopols)
    {
        this.writeGeopols = writeGeopols;
    }

    public boolean getWriteMarkers()
    {
        return writeMarkers;
    }

    public void setWriteMarkers(boolean writeMarkers)
    {
        this.writeMarkers = writeMarkers;
    }

    public boolean getWriteNDBs()
    {
        return writeNDBs;
    }

    public void setWriteNDBs(boolean writeNDBs)
    {
        this.writeNDBs = writeNDBs;
    }

    public boolean getWriteRoutes()
    {
        return writeRoutes;
    }

    public void setWriteRoutes(boolean writeRoutes)
    {
        this.writeRoutes = writeRoutes;
    }

    public boolean getWriteVORs()
    {
        return writeVORs;
    }

    public void setWriteVORs(boolean writeVORs)
    {
        this.writeVORs = writeVORs;
    }

    public boolean getWriteWaypoints()
    {
        return writeWaypoints;
    }

    public void setWriteWaypoints(boolean writeWaypoints)
    {
        this.writeWaypoints = writeWaypoints;
    }

    public boolean getDeleteApproaches()
    {
        return deleteApproaches;
    }

    public void setDeleteApproaches(boolean deleteApproaches)
    {
        this.deleteApproaches = deleteApproaches;
    }

    public boolean getDeleteApronLights()
    {
        return deleteApronLights;
    }

    public void setDeleteApronLights(boolean deleteApronLights)
    {
        this.deleteApronLights = deleteApronLights;
    }

    public boolean getDeleteAprons()
    {
        return deleteAprons;
    }

    public void setDeleteAprons(boolean deleteAprons)
    {
        this.deleteAprons = deleteAprons;
    }

    public boolean getDeleteFrequencies()
    {
        return deleteFrequencies;
    }

    public void setDeleteFrequencies(boolean deleteFrequencies)
    {
        this.deleteFrequencies = deleteFrequencies;
    }

    public boolean getDeleteHelipads()
    {
        return deleteHelipads;
    }

    public void setDeleteHelipads(boolean deleteHelipads)
    {
        this.deleteHelipads = deleteHelipads;
    }

    public boolean getDeleteRunways()
    {
        return deleteRunways;
    }

    public void setDeleteRunways(boolean deleteRunways)
    {
        this.deleteRunways = deleteRunways;
    }

    public boolean getDeleteStarts()
    {
        return deleteStarts;
    }

    public void setDeleteStarts(boolean deleteStarts)
    {
        this.deleteStarts = deleteStarts;
    }

    public boolean getDeleteTaxiways()
    {
        return deleteTaxiways;
    }

    public void setDeleteTaxiways(boolean deleteTaxiways)
    {
        this.deleteTaxiways = deleteTaxiways;
    }

    public boolean getDeleteBlastFences()
    {
        return deleteBlastFences;
    }

    public void setDeleteBlastFences(boolean deleteBlastFences)
    {
        this.deleteBlastFences = deleteBlastFences;
    }

    public boolean getDeleteBoundaryFences()
    {
        return deleteBoundaryFences;
    }

    public void setDeleteBoundaryFences(boolean deleteBoundaryFences)
    {
        this.deleteBoundaryFences = deleteBoundaryFences;
    }

    public boolean getDeleteTowers()
    {
        return deleteTowers;
    }

    public void setDeleteTowers(boolean deleteTowers)
    {
        this.deleteTowers = deleteTowers;
    }

    public boolean getDeleteJetways()
    {
        return deleteJetways;
    }

    public void setDeleteJetways(boolean deleteJetways)
    {
        this.deleteJetways = deleteJetways;
    }

    public HashMap getRunwayHM()
    {
        return runwayHM;
    }

    public HashMap getStartHM()
    {
        return startHM;
    }

    public HashMap getFrequencyHM()
    {
        return frequencyHM;
    }

    public boolean getAirportExRect()
    {
        return airportExRect;
    }

    public void setAirportExRect(boolean airportExRect)
    {
        this.airportExRect = airportExRect;
    }

    public boolean getExcludeAll()
    {
        return excludeAll;
    }

    public void setExcludeAll(boolean excludeAll)
    {
        this.excludeAll = excludeAll;
    }

    public boolean getExcludeBeacons()
    {
        return excludeBeacons;
    }

    public void setExcludeBeacons(boolean excludeBeacons)
    {
        this.excludeBeacons = excludeBeacons;
    }

    public boolean getExcludeEffect()
    {
        return excludeEffect;
    }

    public void setExcludeEffect(boolean excludeEffect)
    {
        this.excludeEffect = excludeEffect;
    }

    public boolean getExcludeExtrusion()
    {
        return excludeExtrusion;
    }

    public void setExcludeExtrusion(boolean excludeExtrusion)
    {
        this.excludeExtrusion = excludeExtrusion;
    }

    public boolean getExcludeBuilding()
    {
        return excludeBuilding;
    }

    public void setExcludeBuilding(boolean excludeBuilding)
    {
        this.excludeBuilding = excludeBuilding;
    }

    public boolean getExcludeLibrary()
    {
        return excludeLibrary;
    }

    public void setExcludeLibrary(boolean excludeLibrary)
    {
        this.excludeLibrary = excludeLibrary;
    }

    public boolean getExcludeSign()
    {
        return excludeSign;
    }

    public void setExcludeSign(boolean excludeSign)
    {
        this.excludeSign = excludeSign;
    }

    public boolean getExcludeTrigger()
    {
        return excludeTrigger;
    }

    public void setExcludeTrigger(boolean excludeTrigger)
    {
        this.excludeTrigger = excludeTrigger;
    }

    public boolean getExcludeWindsock()
    {
        return excludeWindsock;
    }

    public void setExcludeWindsock(boolean excludeWindsock)
    {
        this.excludeWindsock = excludeWindsock;
    }

    public double getLatitudeMinimum()
    {
        return latitudeMinimum;
    }

    public void setLatitudeMinimum(double latitudeMinimum)
    {
        this.latitudeMinimum = latitudeMinimum;
    }

    public double getLatitudeMaximum()
    {
        return latitudeMaximum;
    }

    public void setLatitudeMaximum(double latitudeMaximum)
    {
        this.latitudeMaximum = latitudeMaximum;
    }

    public double getLongitudeMinimum()
    {
        return longitudeMinimum;
    }

    public void setLongitudeMinimum(double longitudeMinimum)
    {
        this.longitudeMinimum = longitudeMinimum;
    }

    public double getLongitudeMaximum()
    {
        return longitudeMaximum;
    }

    public void setLongitudeMaximum(double longitudeMaximum)
    {
        this.longitudeMaximum = longitudeMaximum;
    }

    public void writePreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putBoolean("writeAprons", writeAprons);
        userPrefs.putBoolean("writeBlastFences", writeBlastFences);
        userPrefs.putBoolean("writeBoundaryFences", writeBoundaryFences);
        userPrefs.putBoolean("writeHelipads", writeHelipads);
        userPrefs.putBoolean("writeJetways", writeJetways);
        userPrefs.putBoolean("writeRunways", writeRunways);
        userPrefs.putBoolean("writeStarts", writeStarts);
        userPrefs.putBoolean("writeTaxiways", writeTaxiways);
        userPrefs.putBoolean("writeTaxiwaySigns", writeTaxiwaySigns);
        userPrefs.putBoolean("writeTriggers", writeTriggers);
        userPrefs.putBoolean("writeTowers", writeTowers);
        userPrefs.putBoolean("writeScenery", writeScenery);
        userPrefs.putBoolean("writeApproaches", writeApproaches);
        userPrefs.putBoolean("writeBoundaries", writeBoundaries);
        userPrefs.putBoolean("writeCOMs", writeCOMs);
        userPrefs.putBoolean("writeDMEs", writeDMEs);
        userPrefs.putBoolean("writeGeopols", writeGeopols);
        userPrefs.putBoolean("writeMarkers", writeMarkers);
        userPrefs.putBoolean("writeNDBs", writeNDBs);
        userPrefs.putBoolean("writeRoutes", writeRoutes);
        userPrefs.putBoolean("writeVORs", writeVORs);
        userPrefs.putBoolean("writeWaypoints", writeWaypoints);
        userPrefs.putBoolean("deleteApproaches", deleteApproaches);
        userPrefs.putBoolean("deleteApronLights", deleteApronLights);
        userPrefs.putBoolean("deleteAprons", deleteAprons);
        userPrefs.putBoolean("deleteFrequencies", deleteFrequencies);
        userPrefs.putBoolean("deleteHelipads", deleteHelipads);
        userPrefs.putBoolean("deleteRunways", deleteRunways);
        userPrefs.putBoolean("deleteStarts", deleteStarts);
        userPrefs.putBoolean("deleteTaxiways", deleteTaxiways);
        userPrefs.putBoolean("deleteBlastFences", deleteBlastFences);
        userPrefs.putBoolean("deleteBoundaryFences", deleteBoundaryFences);
        userPrefs.putBoolean("deleteTowers", deleteTowers);
        userPrefs.putBoolean("deleteJetways", deleteJetways);
        userPrefs.putBoolean("airportExRect", airportExRect);
        userPrefs.putBoolean("excludeAll", excludeAll);
        userPrefs.putBoolean("excludeBeacons", excludeBeacons);
        userPrefs.putBoolean("excludeEffect", excludeEffect);
        userPrefs.putBoolean("excludeExtrusion", excludeExtrusion);
        userPrefs.putBoolean("excludeBuilding", excludeBuilding);
        userPrefs.putBoolean("excludeLibrary", excludeLibrary);
        userPrefs.putBoolean("excludeSign", excludeSign);
        userPrefs.putBoolean("excludeTrigger", excludeTrigger);
        userPrefs.putBoolean("excludeWindsock", excludeWindsock);
        userPrefs.putDouble("latitudeMinimum", latitudeMinimum);
        userPrefs.putDouble("latitudeMaximum", latitudeMaximum);
        userPrefs.putDouble("longitudeMinimum", longitudeMinimum);
        userPrefs.putDouble("longitudeMaximum", longitudeMaximum);
    }

    private void readPreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        writeAprons = userPrefs.getBoolean("writeAprons", writeAprons);
        writeBlastFences = userPrefs.getBoolean("writeBlastFences", writeBlastFences);
        writeBoundaryFences = userPrefs.getBoolean("writeBoundaryFences", writeBoundaryFences);
        writeHelipads = userPrefs.getBoolean("writeHelipads", writeHelipads);
        writeJetways = userPrefs.getBoolean("writeJetways", writeJetways);
        writeRunways = userPrefs.getBoolean("writeRunways", writeRunways);
        writeStarts = userPrefs.getBoolean("writeStarts", writeStarts);
        writeTaxiways = userPrefs.getBoolean("writeTaxiways", writeTaxiways);
        writeTaxiwaySigns = userPrefs.getBoolean("writeTaxiwaySigns", writeTaxiwaySigns);
        writeTriggers = userPrefs.getBoolean("writeTriggers", writeTriggers);
        writeTowers = userPrefs.getBoolean("writeTowers", writeTowers);
        writeScenery = userPrefs.getBoolean("writeScenery", writeScenery);
        writeApproaches = userPrefs.getBoolean("writeApproaches", writeApproaches);
        writeBoundaries = userPrefs.getBoolean("writeBoundaries", writeBoundaries);
        writeCOMs = userPrefs.getBoolean("writeCOMs", writeCOMs);
        writeDMEs = userPrefs.getBoolean("writeDMEs", writeDMEs);
        writeGeopols = userPrefs.getBoolean("writeGeopols", writeGeopols);
        writeMarkers = userPrefs.getBoolean("writeMarkers", writeMarkers);
        writeNDBs = userPrefs.getBoolean("writeNDBs", writeNDBs);
        writeRoutes = userPrefs.getBoolean("writeRoutes", writeRoutes);
        writeVORs = userPrefs.getBoolean("writeVORs", writeVORs);
        writeWaypoints = userPrefs.getBoolean("writeWaypoints", writeWaypoints);
        deleteApproaches = userPrefs.getBoolean("deleteApproaches", deleteApproaches);
        deleteApronLights = userPrefs.getBoolean("deleteApronLights", deleteApronLights);
        deleteAprons = userPrefs.getBoolean("deleteAprons", deleteAprons);
        deleteFrequencies = userPrefs.getBoolean("deleteFrequencies", deleteFrequencies);
        deleteHelipads = userPrefs.getBoolean("deleteHelipads", deleteHelipads);
        deleteRunways = userPrefs.getBoolean("deleteRunways", deleteRunways);
        deleteStarts = userPrefs.getBoolean("deleteStarts", deleteStarts);
        deleteTaxiways = userPrefs.getBoolean("deleteTaxiways", deleteTaxiways);
        deleteBlastFences = userPrefs.getBoolean("deleteBlastFences", deleteBlastFences);
        deleteBoundaryFences = userPrefs.getBoolean("deleteBoundaryFences", deleteBoundaryFences);
        deleteTowers = userPrefs.getBoolean("deleteTowers", deleteTowers);
        deleteJetways = userPrefs.getBoolean("deleteJetways", deleteJetways);
        airportExRect = userPrefs.getBoolean("airportExRect", airportExRect);
        excludeAll = userPrefs.getBoolean("excludeAll", excludeAll);
        excludeBeacons = userPrefs.getBoolean("excludeBeacons", excludeBeacons);
        excludeEffect = userPrefs.getBoolean("excludeEffect", excludeEffect);
        excludeExtrusion = userPrefs.getBoolean("excludeExtrusion", excludeExtrusion);
        excludeBuilding = userPrefs.getBoolean("excludeBuilding", excludeBuilding);
        excludeLibrary = userPrefs.getBoolean("excludeLibrary", excludeLibrary);
        excludeSign = userPrefs.getBoolean("excludeSign", excludeSign);
        excludeTrigger = userPrefs.getBoolean("excludeTrigger", excludeTrigger);
        excludeWindsock = userPrefs.getBoolean("excludeWindsock", excludeWindsock);
        latitudeMinimum = userPrefs.getDouble("latitudeMinimum", latitudeMinimum);
        latitudeMaximum = userPrefs.getDouble("latitudeMaximum", latitudeMaximum);
        longitudeMinimum = userPrefs.getDouble("longitudeMinimum", longitudeMinimum);
        longitudeMaximum = userPrefs.getDouble("longitudeMaximum", longitudeMaximum);
    }

    private boolean writeAprons;
    private boolean writeBlastFences;
    private boolean writeBoundaryFences;
    private boolean writeHelipads;
    private boolean writeJetways;
    private boolean writeRunways;
    private boolean writeScenery;
    private boolean writeStarts;
    private boolean writeTaxiways;
    private boolean writeTaxiwaySigns;
    private boolean writeTriggers;
    private boolean writeTowers;
    private boolean writeApproaches;
    private boolean writeBoundaries;
    private boolean writeCOMs;
    private boolean writeDMEs;
    private boolean writeGeopols;
    private boolean writeMarkers;
    private boolean writeNDBs;
    private boolean writeRoutes;
    private boolean writeVORs;
    private boolean writeWaypoints;
    private boolean deleteApproaches;
    private boolean deleteApronLights;
    private boolean deleteAprons;
    private boolean deleteFrequencies;
    private boolean deleteHelipads;
    private boolean deleteRunways;
    private boolean deleteStarts;
    private boolean deleteTaxiways;
    private boolean deleteBlastFences;
    private boolean deleteBoundaryFences;
    private boolean deleteTowers;
    private boolean deleteJetways;
    private boolean airportExRect;
    private boolean excludeAll;
    private boolean excludeBeacons;
    private boolean excludeEffect;
    private boolean excludeExtrusion;
    private boolean excludeBuilding;
    private boolean excludeLibrary;
    private boolean excludeSign;
    private boolean excludeTrigger;
    private boolean excludeWindsock;
    private double latitudeMinimum;
    private double latitudeMaximum;
    private double longitudeMinimum;
    private double longitudeMaximum;
    private HashMap runwayHM;
    private HashMap startHM;
    private HashMap frequencyHM;
    private static PrepareEngine engine = null;

}
