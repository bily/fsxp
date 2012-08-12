// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SettingsEngine.java

package com.zbluesoftware.fsxp.engine;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Vector;
import java.util.prefs.Preferences;
import javax.swing.*;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            LogEngine

public class SettingsEngine
{

    private SettingsEngine()
    {
        listeners = new Vector();
        version = "28";
        laf = "Windows";
        airportsDatPath = "C:\\Program Files\\Microsoft Games\\Microsoft Flight Simulator X SDK\\SDK\\Environment Kit\\Traffic Toolbox SDK\\fs10.Airports.dat";
        compDestFolder = "C:\\Program Files\\Microsoft Games\\Microsoft Flight Simulator X\\Addon Scenery";
        simFolder = "C:\\Program Files\\Microsoft Games\\Microsoft Flight Simulator X";
        bglCompFolder = "C:\\Program Files\\Microsoft Games\\Microsoft Flight Simulator X SDK\\SDK\\Environment Kit\\BGL Compiler SDK";
        elevationFolder = "C:\\Program Files\\Microsoft Games\\Microsoft Flight Simulator X\\Scenery\\World\\scenery";
        lastUpdate = new Date(0L);
        if(System.getProperty("os.name").equals("Windows Vista"))
            airportScanFolder = (new StringBuilder()).append(System.getProperty("user.home")).append("\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner").toString();
        else
            airportScanFolder = (new StringBuilder()).append(System.getProperty("user.home")).append("\\Application Data\\zBlueSoftware\\FSXPlanner").toString();
        latLonDisplay = 0;
        latLonGridDisplay = 1;
        latLonObjectDisplay = 0;
        secondsIncrement = 10;
        maxHistoryItems = 30;
        screenBounds = new Rectangle(0, 0, 0, 0);
        lastOpenedDir = "";
        lastOpenedBGLDir = "";
        bgImagesBelow = true;
        bgImageOpacity = 100;
        parkingSize = 10F;
        tee1Offset = 0.0F;
        tee2Offset = 0.0F;
        tee3Offset = 0.0F;
        tee4Offset = 0.0F;
        taxiwayWidth = 15F;
        taxiwayWeight = 500000F;
        helipadLength = 15F;
        helipadWidth = 15F;
        runwayLength = 1000F;
        runwayWidth = 50F;
        taxiwayPointDiameter = 25F;
        vertexPointDiameter = 5F;
        vasiX = 30F;
        vasiZ = 100F;
        vasiSpacing = 2.0F;
        offsetLength = 50F;
        offsetWidth = 50F;
        parkingMeasure = "M";
        taxiwayMeasure = "M";
        helipadLengthMeasure = "M";
        helipadWidthMeasure = "M";
        runwayLengthMeasure = "M";
        runwayWidthMeasure = "M";
        taxiwayPointMeasure = "F";
        vertexPointMeasure = "F";
        vasiXMeasure = "M";
        vasiZMeasure = "M";
        vasiZEnd = "from end";
        vasiSpacingMeasure = "M";
        offsetLengthMeasure = "M";
        offsetWidthMeasure = "M";
        offsetSurface = "ASPHALT";
        useAirportAlt = true;
        adjustMeasurements = false;
        drawTaxiwayPaths = false;
        updateJetways = true;
        taxiwayProperties = true;
        taxiwayRunways = true;
        restoreWindow = true;
        clearCompile = true;
        xmlPerLine = true;
        writeDeletes = true;
        writeExcludes = true;
        writeBGImages = false;
        edgeLightLines = false;
        offsetWidthRunway = true;
        offsetSurfaceRunway = true;
        selectedItemOutlined = true;
        useSceneryTexture = true;
        readNav = true;
        readScenery = true;
        doubleBuffer = true;
        ribbonMenus = true;
        displayMemory = false;
        displayLatLon = true;
        displayExcludes = true;
        displayAirportCtr = true;
        displayTestRadius = true;
        displayBGImage = false;
        displayMarkers = false;
        displayNDBs = false;
        displayVORs = false;
        displayWindsocks = false;
        displayRunway = true;
        displayILS = false;
        displayApron = true;
        displayApronEL = false;
        displayTWPoint = true;
        displayTWSign = true;
        displayParking = true;
        displayJetways = true;
        showRunwayPaths = true;
        showParkingPaths = true;
        showTaxiPaths = true;
        showPathPaths = true;
        showClosedPaths = true;
        showVehiclePaths = true;
        displayTower = true;
        displayBoundFence = true;
        displayBlastFence = true;
        displayHelipad = true;
        displayStart = false;
        displayTriggers = false;
        displayScenery = false;
        displayRotation = true;
        firstTime = true;
        displayLights = false;
        readPreferences();
    }

    public static SettingsEngine getInstance()
    {
        if(engine == null)
            engine = new SettingsEngine();
        return engine;
    }

    public boolean getDisplayFPS()
    {
        return displayFPS;
    }

    public void setDisplayFPS(boolean displayFPS)
    {
        if(this.displayFPS != displayFPS)
            firePropertyChange("displayFPS", new Boolean(this.displayFPS), new Boolean(displayFPS));
        this.displayFPS = displayFPS;
    }

    public boolean getDoubleBuffer()
    {
        return doubleBuffer;
    }

    public void setDoubleBuffer(boolean doubleBuffer)
    {
        if(this.doubleBuffer != doubleBuffer)
            firePropertyChange("doubleBuffer", new Boolean(this.doubleBuffer), new Boolean(doubleBuffer));
        this.doubleBuffer = doubleBuffer;
    }

    public boolean getRibbonMenus()
    {
        return ribbonMenus;
    }

    public void setRibbonMenus(boolean ribbonMenus)
    {
        this.ribbonMenus = ribbonMenus;
    }

    public boolean getReadNav()
    {
        return readNav;
    }

    public void setReadNav(boolean readNav)
    {
        this.readNav = readNav;
    }

    public boolean getReadScenery()
    {
        return readScenery;
    }

    public void setReadScenery(boolean readScenery)
    {
        this.readScenery = readScenery;
    }

    public boolean getDisplayLights()
    {
        return displayLights;
    }

    public void setdisplayLights(boolean displayLights)
    {
        if(this.displayLights != displayLights)
            firePropertyChange("displayLights", new Boolean(this.displayLights), new Boolean(displayLights));
        this.displayLights = displayLights;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public String getSimFolder()
    {
        return simFolder;
    }

    public void setSimFolder(String simFolder)
    {
        this.simFolder = simFolder;
    }

    public String getAirportScanFolder()
    {
        return airportScanFolder;
    }

    public void setAirportScanFolder(String airportScanFolder)
    {
        this.airportScanFolder = airportScanFolder;
    }

    public boolean isFirstTime()
    {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime)
    {
        this.firstTime = firstTime;
    }

    public boolean getBGImagesBelow()
    {
        return bgImagesBelow;
    }

    public void setBGImagesBelow(boolean bgImagesBelow)
    {
        if(this.bgImagesBelow != bgImagesBelow)
            firePropertyChange("bgImagesBelow", new Boolean(this.bgImagesBelow), new Boolean(bgImagesBelow));
        this.bgImagesBelow = bgImagesBelow;
    }

    public int getBGImageOpacity()
    {
        return bgImageOpacity;
    }

    public void setBGImageOpacity(int bgImageOpacity)
    {
        if(this.bgImageOpacity != bgImageOpacity)
            firePropertyChange("bgImageOpacity", new Integer(this.bgImageOpacity), new Integer(bgImageOpacity));
        this.bgImageOpacity = bgImageOpacity;
    }

    public boolean getEdgeLightLines()
    {
        return edgeLightLines;
    }

    public void setEdgeLightLines(boolean edgeLightLines)
    {
        if(this.edgeLightLines != edgeLightLines)
            firePropertyChange("edgeLightLines", new Boolean(this.edgeLightLines), new Boolean(edgeLightLines));
        this.edgeLightLines = edgeLightLines;
    }

    public boolean getWriteDeletes()
    {
        return writeDeletes;
    }

    public void setWriteDeletes(boolean writeDeletes)
    {
        this.writeDeletes = writeDeletes;
    }

    public boolean getWriteExcludes()
    {
        return writeExcludes;
    }

    public void setWriteExcludes(boolean writeExcludes)
    {
        this.writeExcludes = writeExcludes;
    }

    public boolean getWriteBGImages()
    {
        return writeBGImages;
    }

    public void setWriteBGImages(boolean writeBGImages)
    {
        this.writeBGImages = writeBGImages;
    }

    public boolean getUseSceneryTexture()
    {
        return useSceneryTexture;
    }

    public void setUseSceneryTexture(boolean useSceneryTexture)
    {
        this.useSceneryTexture = useSceneryTexture;
    }

    public boolean isSelectedItemOutlined()
    {
        return selectedItemOutlined;
    }

    public void setSelectedItemOutlined(boolean selectedItemOutlined)
    {
        this.selectedItemOutlined = selectedItemOutlined;
    }

    public String getLastOpenedDir()
    {
        return lastOpenedDir;
    }

    public void setLastOpenedDir(String lastOpenedDir)
    {
        this.lastOpenedDir = lastOpenedDir;
    }

    public String getLastOpenedBGLDir()
    {
        return lastOpenedBGLDir;
    }

    public void setLastOpenedBGLDir(String lastOpenedBGLDir)
    {
        this.lastOpenedBGLDir = lastOpenedBGLDir;
    }

    public int getMaxHistoryItems()
    {
        return maxHistoryItems;
    }

    public void setMaxHistoryItems(int maxHistoryItems)
    {
        if(this.maxHistoryItems != maxHistoryItems)
            firePropertyChange("maxHistoryItems", new Integer(this.maxHistoryItems), new Integer(maxHistoryItems));
        this.maxHistoryItems = maxHistoryItems;
    }

    public float getParkingSize()
    {
        return parkingSize;
    }

    public void setParkingSize(float parkingSize)
    {
        this.parkingSize = parkingSize;
    }

    public String getParkingMeasure()
    {
        return parkingMeasure;
    }

    public void setParkingMeasure(String parkingMeasure)
    {
        this.parkingMeasure = parkingMeasure;
    }

    public float getTee1Offset()
    {
        return tee1Offset;
    }

    public void setTee1Offset(float tee1Offset)
    {
        this.tee1Offset = tee1Offset;
    }

    public float getTee2Offset()
    {
        return tee2Offset;
    }

    public void setTee2Offset(float tee2Offset)
    {
        this.tee2Offset = tee2Offset;
    }

    public float getTee3Offset()
    {
        return tee3Offset;
    }

    public void setTee3Offset(float tee3Offset)
    {
        this.tee3Offset = tee3Offset;
    }

    public float getTee4Offset()
    {
        return tee4Offset;
    }

    public void setTee4Offset(float tee4Offset)
    {
        this.tee4Offset = tee4Offset;
    }

    public float getTaxiwayWidth()
    {
        return taxiwayWidth;
    }

    public void setTaxiwayWidth(float taxiwayWidth)
    {
        this.taxiwayWidth = taxiwayWidth;
    }

    public String getTaxiwayMeasure()
    {
        return taxiwayMeasure;
    }

    public void setTaxiwayMeasure(String taxiwayMeasure)
    {
        this.taxiwayMeasure = taxiwayMeasure;
    }

    public float getTaxiwayWeight()
    {
        return taxiwayWeight;
    }

    public void setTaxiwayWeight(float taxiwayWeight)
    {
        this.taxiwayWeight = taxiwayWeight;
    }

    public float getHelipadLength()
    {
        return helipadLength;
    }

    public void setHelipadLength(float helipadLength)
    {
        this.helipadLength = helipadLength;
    }

    public String getHelipadLengthMeasure()
    {
        return helipadLengthMeasure;
    }

    public void setHelipadLengthMeasure(String helipadLengthMeasure)
    {
        this.helipadLengthMeasure = helipadLengthMeasure;
    }

    public float getHelipadWidth()
    {
        return helipadWidth;
    }

    public void setHelipadWidth(float helipadWidth)
    {
        this.helipadWidth = helipadWidth;
    }

    public String getHelipadWidthMeasure()
    {
        return helipadWidthMeasure;
    }

    public void setHelipadWidthMeasure(String helipadWidthMeasure)
    {
        this.helipadWidthMeasure = helipadWidthMeasure;
    }

    public float getRunwayLength()
    {
        return runwayLength;
    }

    public void setRunwayLength(float runwayLength)
    {
        this.runwayLength = runwayLength;
    }

    public String getRunwayLengthMeasure()
    {
        return runwayLengthMeasure;
    }

    public void setRunwayLengthMeasure(String runwayLengthMeasure)
    {
        this.runwayLengthMeasure = runwayLengthMeasure;
    }

    public float getRunwayWidth()
    {
        return runwayWidth;
    }

    public void setRunwayWidth(float runwayWidth)
    {
        this.runwayWidth = runwayWidth;
    }

    public String getRunwayWidthMeasure()
    {
        return runwayWidthMeasure;
    }

    public void setRunwayWidthMeasure(String runwayWidthMeasure)
    {
        this.runwayWidthMeasure = runwayWidthMeasure;
    }

    public float getTaxiwayPointDiameter()
    {
        return taxiwayPointDiameter;
    }

    public void setTaxiwayPointDiameter(float taxiwayPointDiameter)
    {
        this.taxiwayPointDiameter = taxiwayPointDiameter;
    }

    public String getTaxiwayPointMeasure()
    {
        return taxiwayPointMeasure;
    }

    public void setTaxiwayPointMeasure(String taxiwayPointMeasure)
    {
        this.taxiwayPointMeasure = taxiwayPointMeasure;
    }

    public float getVertexPointDiameter()
    {
        return vertexPointDiameter;
    }

    public void setVertexPointDiameter(float vertexPointDiameter)
    {
        if(this.vertexPointDiameter != vertexPointDiameter)
            firePropertyChange("vertexPointDiameter", new Float(this.vertexPointDiameter), new Float(vertexPointDiameter));
        this.vertexPointDiameter = vertexPointDiameter;
    }

    public String getVertexPointMeasure()
    {
        return vertexPointMeasure;
    }

    public void setVertexPointMeasure(String vertexPointMeasure)
    {
        if(!this.vertexPointMeasure.equals(vertexPointMeasure))
            firePropertyChange("vertexPointMeasure", this.vertexPointMeasure, vertexPointMeasure);
        this.vertexPointMeasure = vertexPointMeasure;
    }

    public boolean getUseAirportAlt()
    {
        return useAirportAlt;
    }

    public void setUseAirportAlt(boolean useAirportAlt)
    {
        this.useAirportAlt = useAirportAlt;
    }

    public boolean getAdjustMeasurements()
    {
        return adjustMeasurements;
    }

    public void setAdjustMeasurements(boolean adjustMeasurements)
    {
        this.adjustMeasurements = adjustMeasurements;
    }

    public int getLatLonDisplay()
    {
        return latLonDisplay;
    }

    public void setLatLonDisplay(int latLonDisplay)
    {
        firePropertyChange("latLonDisplay", new Integer(this.latLonDisplay), new Integer(latLonDisplay));
        this.latLonDisplay = latLonDisplay;
    }

    public int getLatLonGridDisplay()
    {
        return latLonGridDisplay;
    }

    public void setLatLonGridDisplay(int latLonGridDisplay)
    {
        firePropertyChange("latLonGridDisplay", new Integer(this.latLonGridDisplay), new Integer(latLonGridDisplay));
        this.latLonGridDisplay = latLonGridDisplay;
    }

    public int getLatLonObjectDisplay()
    {
        return latLonObjectDisplay;
    }

    public void setLatLonObjectDisplay(int latLonObjectDisplay)
    {
        firePropertyChange("latLonObjectDisplay", new Integer(this.latLonObjectDisplay), new Integer(latLonObjectDisplay));
        this.latLonObjectDisplay = latLonObjectDisplay;
    }

    public int getSecondsIncrement()
    {
        return secondsIncrement;
    }

    public void setSecondsIncrement(int secondsIncrement)
    {
        this.secondsIncrement = secondsIncrement;
    }

    public boolean getDisplayLatLon()
    {
        return displayLatLon;
    }

    public void setDisplayLatLon(boolean displayLatLon)
    {
        this.displayLatLon = displayLatLon;
    }

    public boolean getDisplayExcludes()
    {
        return displayExcludes;
    }

    public void setDisplayExcludes(boolean displayExcludes)
    {
        this.displayExcludes = displayExcludes;
    }

    public boolean getDisplayAirportCtr()
    {
        return displayAirportCtr;
    }

    public void setDisplayAirportCtr(boolean displayAirportCtr)
    {
        this.displayAirportCtr = displayAirportCtr;
    }

    public boolean getDisplayTestRadius()
    {
        return displayTestRadius;
    }

    public void setDisplayTestRadius(boolean displayTestRadius)
    {
        this.displayTestRadius = displayTestRadius;
    }

    public boolean getDisplayBGImage()
    {
        return displayBGImage;
    }

    public void setDisplayBGImage(boolean displayBGImage)
    {
        this.displayBGImage = displayBGImage;
    }

    public boolean getDisplayMarkers()
    {
        return displayMarkers;
    }

    public void setDisplayMarkers(boolean displayMarkers)
    {
        this.displayMarkers = displayMarkers;
    }

    public boolean getDisplayNDBs()
    {
        return displayNDBs;
    }

    public void setDisplayNDBs(boolean displayNDBs)
    {
        this.displayNDBs = displayNDBs;
    }

    public boolean getDisplayVORs()
    {
        return displayVORs;
    }

    public void setDisplayVORs(boolean displayVORs)
    {
        this.displayVORs = displayVORs;
    }

    public boolean getDisplayWindsocks()
    {
        return displayWindsocks;
    }

    public void setDisplayWindsocks(boolean displayWindsocks)
    {
        this.displayWindsocks = displayWindsocks;
    }

    public String getBglCompFolder()
    {
        return bglCompFolder;
    }

    public void setBglCompFolder(String bglCompFolder)
    {
        this.bglCompFolder = bglCompFolder;
    }

    public String getElevationFolder()
    {
        return elevationFolder;
    }

    public void setElevationFolder(String elevationFolder)
    {
        this.elevationFolder = elevationFolder;
    }

    public String getCompDestFolder()
    {
        return compDestFolder;
    }

    public void setCompDestFolder(String compDestFolder)
    {
        this.compDestFolder = compDestFolder;
    }

    public void setAirportsDatPath(String airportsDatPath)
    {
        this.airportsDatPath = airportsDatPath;
    }

    public String getAirportsDatPath()
    {
        return airportsDatPath;
    }

    public boolean getDisplayRunway()
    {
        return displayRunway;
    }

    public void setDisplayRunway(boolean displayRunway)
    {
        this.displayRunway = displayRunway;
    }

    public boolean getDisplayILS()
    {
        return displayILS;
    }

    public void setDisplayILS(boolean displayILS)
    {
        this.displayILS = displayILS;
    }

    public boolean getDisplayApron()
    {
        return displayApron;
    }

    public void setDisplayApron(boolean displayApron)
    {
        this.displayApron = displayApron;
    }

    public boolean getDisplayApronEL()
    {
        return displayApronEL;
    }

    public void setDisplayApronEL(boolean displayApronEL)
    {
        this.displayApronEL = displayApronEL;
    }

    public boolean getDisplayTWPoint()
    {
        return displayTWPoint;
    }

    public void setDisplayTWPoint(boolean displayTWPoint)
    {
        this.displayTWPoint = displayTWPoint;
    }

    public boolean getDisplayTWSign()
    {
        return displayTWSign;
    }

    public void setDisplayTWSign(boolean displayTWSign)
    {
        this.displayTWSign = displayTWSign;
    }

    public boolean getDisplayParking()
    {
        return displayParking;
    }

    public void setDisplayParking(boolean displayParking)
    {
        this.displayParking = displayParking;
    }

    public boolean getDisplayJetways()
    {
        return displayJetways;
    }

    public void setDisplayJetways(boolean displayJetways)
    {
        this.displayJetways = displayJetways;
    }

    public void setShowRunwayPaths(boolean showRunwayPaths)
    {
        this.showRunwayPaths = showRunwayPaths;
    }

    public boolean getShowRunwayPaths()
    {
        return showRunwayPaths;
    }

    public void setShowParkingPaths(boolean showParkingPaths)
    {
        this.showParkingPaths = showParkingPaths;
    }

    public boolean getShowParkingPaths()
    {
        return showParkingPaths;
    }

    public void setShowTaxiPaths(boolean showTaxiPaths)
    {
        this.showTaxiPaths = showTaxiPaths;
    }

    public boolean getShowTaxiPaths()
    {
        return showTaxiPaths;
    }

    public void setShowPathPaths(boolean showPathPaths)
    {
        this.showPathPaths = showPathPaths;
    }

    public boolean getShowPathPaths()
    {
        return showPathPaths;
    }

    public void setShowClosedPaths(boolean showClosedPaths)
    {
        this.showClosedPaths = showClosedPaths;
    }

    public boolean getShowClosedPaths()
    {
        return showClosedPaths;
    }

    public void setShowVehiclePaths(boolean showVehiclePaths)
    {
        this.showVehiclePaths = showVehiclePaths;
    }

    public boolean getShowVehiclePaths()
    {
        return showVehiclePaths;
    }

    public boolean getDisplayTower()
    {
        return displayTower;
    }

    public void setDisplayTower(boolean displayTower)
    {
        this.displayTower = displayTower;
    }

    public boolean getDisplayBoundFence()
    {
        return displayBoundFence;
    }

    public void setDisplayBoundFence(boolean displayBoundFence)
    {
        this.displayBoundFence = displayBoundFence;
    }

    public boolean getDisplayBlastFence()
    {
        return displayBlastFence;
    }

    public void setDisplayBlastFence(boolean displayBlastFence)
    {
        this.displayBlastFence = displayBlastFence;
    }

    public boolean getDisplayHelipad()
    {
        return displayHelipad;
    }

    public void setDisplayHelipad(boolean displayHelipad)
    {
        this.displayHelipad = displayHelipad;
    }

    public boolean getDisplayStart()
    {
        return displayStart;
    }

    public void setDisplayStart(boolean displayStart)
    {
        this.displayStart = displayStart;
    }

    public boolean getDisplayTriggers()
    {
        return displayTriggers;
    }

    public void setDisplayTriggers(boolean displayTriggers)
    {
        this.displayTriggers = displayTriggers;
    }

    public boolean getDisplayScenery()
    {
        return displayScenery;
    }

    public void setDisplayScenery(boolean displayScenery)
    {
        this.displayScenery = displayScenery;
    }

    public boolean getDisplayRotation()
    {
        return displayRotation;
    }

    public void setDisplayRotation(boolean displayRotation)
    {
        this.displayRotation = displayRotation;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion()
    {
        return version;
    }

    public String getLAF()
    {
        return laf;
    }

    public void setLAF(String laf)
    {
        this.laf = laf;
    }

    public boolean getDisplayMemory()
    {
        return displayMemory;
    }

    public void setDisplayMemory(boolean displayMemory)
    {
        this.displayMemory = displayMemory;
    }

    public boolean getDrawTaxiwayPaths()
    {
        return drawTaxiwayPaths;
    }

    public void setDrawTaxiwayPaths(boolean drawTaxiwayPaths)
    {
        if(this.drawTaxiwayPaths != drawTaxiwayPaths)
            firePropertyChange("drawTaxiwayPaths", new Boolean(this.drawTaxiwayPaths), new Boolean(drawTaxiwayPaths));
        this.drawTaxiwayPaths = drawTaxiwayPaths;
    }

    public boolean getUpdateJetways()
    {
        return updateJetways;
    }

    public void setUpdateJetways(boolean updateJetways)
    {
        this.updateJetways = updateJetways;
    }

    public boolean getTaxiwayProperties()
    {
        return taxiwayProperties;
    }

    public void setTaxiwayProperties(boolean taxiwayProperties)
    {
        this.taxiwayProperties = taxiwayProperties;
    }

    public boolean getTaxiwayRunways()
    {
        return taxiwayRunways;
    }

    public void setTaxiwayRunways(boolean taxiwayRunways)
    {
        this.taxiwayRunways = taxiwayRunways;
    }

    public Rectangle getScreenBounds()
    {
        return screenBounds;
    }

    public void setScreenBounds(Rectangle screenBounds)
    {
        this.screenBounds = screenBounds;
    }

    public boolean getRestoreWindow()
    {
        return restoreWindow;
    }

    public void setRestoreWindow(boolean restoreWindow)
    {
        this.restoreWindow = restoreWindow;
    }

    public boolean getClearCompile()
    {
        return clearCompile;
    }

    public void setClearCompile(boolean clearCompile)
    {
        this.clearCompile = clearCompile;
    }

    public boolean getXMLPerLine()
    {
        return xmlPerLine;
    }

    public void setXMLPerLine(boolean xmlPerLine)
    {
        this.xmlPerLine = xmlPerLine;
    }

    public float getVasiX()
    {
        return vasiX;
    }

    public void setVasiX(float vasiX)
    {
        this.vasiX = vasiX;
    }

    public String getVasiXMeasure()
    {
        return vasiXMeasure;
    }

    public void setVasiXMeasure(String vasiXMeasure)
    {
        this.vasiXMeasure = vasiXMeasure;
    }

    public float getVasiZ()
    {
        return vasiZ;
    }

    public void setVasiZ(float vasiZ)
    {
        this.vasiZ = vasiZ;
    }

    public String getVasiZMeasure()
    {
        return vasiZMeasure;
    }

    public void setVasiZMeasure(String vasiZMeasure)
    {
        this.vasiZMeasure = vasiZMeasure;
    }

    public String getVasiZEnd()
    {
        return vasiZEnd;
    }

    public void setVasiZEnd(String vasiZEnd)
    {
        this.vasiZEnd = vasiZEnd;
    }

    public float getVasiSpacing()
    {
        return vasiSpacing;
    }

    public void setVasiSpacing(float vasiSpacing)
    {
        this.vasiSpacing = vasiSpacing;
    }

    public String getVasiSpacingMeasure()
    {
        return vasiSpacingMeasure;
    }

    public void setVasiSpacingMeasure(String vasiSpacingMeasure)
    {
        this.vasiSpacingMeasure = vasiSpacingMeasure;
    }

    public float getOffsetLength()
    {
        return offsetLength;
    }

    public void setOffsetLength(float offsetLength)
    {
        this.offsetLength = offsetLength;
    }

    public String getOffsetLengthMeasure()
    {
        return offsetLengthMeasure;
    }

    public void setOffsetLengthMeasure(String offsetLengthMeasure)
    {
        this.offsetLengthMeasure = offsetLengthMeasure;
    }

    public boolean getOffsetWidthRunway()
    {
        return offsetWidthRunway;
    }

    public void setOffsetWidthRunway(boolean offsetWidthRunway)
    {
        this.offsetWidthRunway = offsetWidthRunway;
    }

    public float getOffsetWidth()
    {
        return offsetWidth;
    }

    public void setOffsetWidth(float offsetWidth)
    {
        this.offsetWidth = offsetWidth;
    }

    public String getOffsetWidthMeasure()
    {
        return offsetWidthMeasure;
    }

    public void setOffsetWidthMeasure(String offsetWidthMeasure)
    {
        this.offsetWidthMeasure = offsetWidthMeasure;
    }

    public boolean getOffsetSurfaceRunway()
    {
        return offsetSurfaceRunway;
    }

    public void setOffsetSurfaceRunway(boolean offsetSurfaceRunway)
    {
        this.offsetSurfaceRunway = offsetSurfaceRunway;
    }

    public String getOffsetSurface()
    {
        return offsetSurface;
    }

    public void setOffsetSurface(String offsetSurface)
    {
        this.offsetSurface = offsetSurface;
    }

    public void writePreferences()
    {
        Preferences systemPrefs = Preferences.systemNodeForPackage(getClass());
        systemPrefs.put("airportsDatPath", airportsDatPath);
        systemPrefs.put("compDestFolder", compDestFolder);
        systemPrefs.put("bglCompFolder", bglCompFolder);
        systemPrefs.put("elevationFolder", elevationFolder);
        systemPrefs.put("simFolder", simFolder);
        systemPrefs.putLong("lastUpdate", lastUpdate.getTime());
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putBoolean("displayMemory", displayMemory);
        userPrefs.putBoolean("displayRunway", displayRunway);
        userPrefs.putBoolean("displayILS", displayILS);
        userPrefs.putBoolean("displayApron", displayApron);
        userPrefs.putBoolean("displayApronEL", displayApronEL);
        userPrefs.putBoolean("displayTWPoint", displayTWPoint);
        userPrefs.putBoolean("displayTWSign", displayTWSign);
        userPrefs.putBoolean("displayParking", displayParking);
        userPrefs.putBoolean("displayJetways", displayJetways);
        userPrefs.putBoolean("showRunwayPaths", showRunwayPaths);
        userPrefs.putBoolean("showParkingPaths", showParkingPaths);
        userPrefs.putBoolean("showTaxiPaths", showTaxiPaths);
        userPrefs.putBoolean("showPathPaths", showPathPaths);
        userPrefs.putBoolean("showClosedPaths", showClosedPaths);
        userPrefs.putBoolean("showVehiclePaths", showVehiclePaths);
        userPrefs.putBoolean("displayTower", displayTower);
        userPrefs.putBoolean("displayBoundFence", displayBoundFence);
        userPrefs.putBoolean("displayBlastFence", displayBlastFence);
        userPrefs.putBoolean("displayHelipad", displayHelipad);
        userPrefs.putBoolean("displayStart", displayStart);
        userPrefs.putBoolean("displayLatLon", displayLatLon);
        userPrefs.putBoolean("displayExcludes", displayExcludes);
        userPrefs.putBoolean("displayAirportCtr", displayAirportCtr);
        userPrefs.putBoolean("displayTestRadius", displayTestRadius);
        userPrefs.putBoolean("displayBGImage", displayBGImage);
        userPrefs.putBoolean("displayMarkers", displayMarkers);
        userPrefs.putBoolean("displayNDBs", displayNDBs);
        userPrefs.putBoolean("displayVORs", displayVORs);
        userPrefs.putBoolean("displayWindsocks", displayWindsocks);
        userPrefs.putBoolean("displayTriggers", displayTriggers);
        userPrefs.putBoolean("displayScenery", displayScenery);
        userPrefs.putBoolean("displayRotation", displayRotation);
        userPrefs.putBoolean("useAirportAlt", useAirportAlt);
        userPrefs.putBoolean("adjustMeasurements", adjustMeasurements);
        userPrefs.putBoolean("drawTaxiwayPaths", drawTaxiwayPaths);
        userPrefs.putBoolean("updateJetways", updateJetways);
        userPrefs.putBoolean("taxiwayProperties", taxiwayProperties);
        userPrefs.putBoolean("taxiwayRunways", taxiwayRunways);
        userPrefs.putBoolean("restoreWindow", restoreWindow);
        userPrefs.putBoolean("clearCompile", clearCompile);
        userPrefs.putBoolean("xmlPerLine", xmlPerLine);
        userPrefs.putBoolean("writeDeletes", writeDeletes);
        userPrefs.putBoolean("writeExcludes", writeExcludes);
        userPrefs.putBoolean("writeBGImages", writeBGImages);
        userPrefs.putBoolean("edgeLightLines", edgeLightLines);
        userPrefs.putBoolean("selectedItemOutlined", selectedItemOutlined);
        userPrefs.putBoolean("useSceneryTexture", useSceneryTexture);
        userPrefs.putBoolean("bgImagesBelow", bgImagesBelow);
        userPrefs.putBoolean("firstTime", firstTime);
        userPrefs.putBoolean("displayLights", displayLights);
        userPrefs.putBoolean("readNav", readNav);
        userPrefs.putBoolean("readScenery", readScenery);
        userPrefs.putBoolean("displayFPS", displayFPS);
        userPrefs.putBoolean("doubleBuffer", doubleBuffer);
        userPrefs.putBoolean("ribbonMenus", ribbonMenus);
        userPrefs.putInt("secondsIncrement", secondsIncrement);
        userPrefs.putInt("latLonDisplay", latLonDisplay);
        userPrefs.putInt("latLonGridDisplay", latLonGridDisplay);
        userPrefs.putInt("latLonObjectDisplay", latLonObjectDisplay);
        userPrefs.putInt("maxHistoryItems", maxHistoryItems);
        userPrefs.putInt("bgImageOpacity", bgImageOpacity);
        userPrefs.put("lastOpenedDir", lastOpenedDir);
        userPrefs.put("lastOpenedBGLDir", lastOpenedBGLDir);
        userPrefs.putFloat("parkingSize", parkingSize);
        userPrefs.putFloat("tee1Offset", tee1Offset);
        userPrefs.putFloat("tee2Offset", tee2Offset);
        userPrefs.putFloat("tee3Offset", tee3Offset);
        userPrefs.putFloat("tee4Offset", tee4Offset);
        userPrefs.putFloat("taxiwayWidth", taxiwayWidth);
        userPrefs.putFloat("taxiwayWeight", taxiwayWeight);
        userPrefs.putFloat("helipadLength", helipadLength);
        userPrefs.putFloat("helipadWidth", helipadWidth);
        userPrefs.putFloat("runwayLength", runwayLength);
        userPrefs.putFloat("runwayWidth", runwayWidth);
        userPrefs.putFloat("taxiwayPointDiameter", taxiwayPointDiameter);
        userPrefs.putFloat("vertexPointDiameter", vertexPointDiameter);
        userPrefs.putFloat("vasiX", vasiX);
        userPrefs.putFloat("vasiZ", vasiZ);
        userPrefs.putFloat("vasiSpacing", vasiSpacing);
        userPrefs.putFloat("offsetLength", offsetLength);
        userPrefs.putFloat("offsetWidth", offsetWidth);
        userPrefs.putBoolean("offsetWidthRunway", offsetWidthRunway);
        userPrefs.putBoolean("offsetSurfaceRunway", offsetSurfaceRunway);
        userPrefs.put("parkingMeasure", parkingMeasure);
        userPrefs.put("taxiwayMeasure", taxiwayMeasure);
        userPrefs.put("helipadLengthMeasure", helipadLengthMeasure);
        userPrefs.put("helipadWidthMeasure", helipadWidthMeasure);
        userPrefs.put("runwayLengthMeasure", runwayLengthMeasure);
        userPrefs.put("runwayWidthMeasure", runwayWidthMeasure);
        userPrefs.put("taxiwayPointMeasure", taxiwayPointMeasure);
        userPrefs.put("vertexPointMeasure", vertexPointMeasure);
        userPrefs.put("vasiXMeasure", vasiXMeasure);
        userPrefs.put("vasiZMeasure", vasiZMeasure);
        userPrefs.put("vasiZEnd", vasiZEnd);
        userPrefs.put("vasiSpacingMeasure", vasiSpacingMeasure);
        userPrefs.put("offsetLengthMeasure", offsetLengthMeasure);
        userPrefs.put("offsetWidthMeasure", offsetWidthMeasure);
        userPrefs.put("offsetSurface", offsetSurface);
        userPrefs.put("airportScanFolder", airportScanFolder);
        userPrefs.putInt("screenBoundsX", screenBounds.x);
        userPrefs.putInt("screenBoundsY", screenBounds.y);
        userPrefs.putInt("screenBoundsWidth", screenBounds.width);
        userPrefs.putInt("screenBoundsHeight", screenBounds.height);
        userPrefs.put("laf", laf);
    }

    private void readPreferences()
    {
        Preferences systemPrefs = Preferences.systemNodeForPackage(getClass());
        airportsDatPath = systemPrefs.get("airportsDatPath", airportsDatPath);
        compDestFolder = systemPrefs.get("compDestFolder", compDestFolder);
        bglCompFolder = systemPrefs.get("bglCompFolder", bglCompFolder);
        elevationFolder = systemPrefs.get("elevationFolder", elevationFolder);
        simFolder = systemPrefs.get("simFolder", simFolder);
        lastUpdate = new Date(systemPrefs.getLong("lastUpdate", 0L));
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        displayMemory = userPrefs.getBoolean("displayMemory", displayMemory);
        displayRunway = userPrefs.getBoolean("displayRunway", displayRunway);
        displayILS = userPrefs.getBoolean("displayILS", displayILS);
        displayApron = userPrefs.getBoolean("displayApron", displayApron);
        displayApronEL = userPrefs.getBoolean("displayApronEL", displayApronEL);
        displayTWPoint = userPrefs.getBoolean("displayTWPoint", displayTWPoint);
        displayTWSign = userPrefs.getBoolean("displayTWSign", displayTWSign);
        displayParking = userPrefs.getBoolean("displayParking", displayParking);
        displayJetways = userPrefs.getBoolean("displayJetways", displayJetways);
        showRunwayPaths = userPrefs.getBoolean("showRunwayPaths", showRunwayPaths);
        showParkingPaths = userPrefs.getBoolean("showParkingPaths", showParkingPaths);
        showTaxiPaths = userPrefs.getBoolean("showTaxiPaths", showTaxiPaths);
        showPathPaths = userPrefs.getBoolean("showPathPaths", showPathPaths);
        showClosedPaths = userPrefs.getBoolean("showClosedPaths", showClosedPaths);
        showVehiclePaths = userPrefs.getBoolean("showVehiclePaths", showVehiclePaths);
        displayTower = userPrefs.getBoolean("displayTower", displayTower);
        displayBoundFence = userPrefs.getBoolean("displayBoundFence", displayBoundFence);
        displayBlastFence = userPrefs.getBoolean("displayBlastFence", displayBlastFence);
        displayHelipad = userPrefs.getBoolean("displayHelipad", displayHelipad);
        displayStart = userPrefs.getBoolean("displayStart", displayStart);
        displayLatLon = userPrefs.getBoolean("displayLatLon", displayLatLon);
        displayExcludes = userPrefs.getBoolean("displayExcludes", displayExcludes);
        displayAirportCtr = userPrefs.getBoolean("displayAirportCtr", displayAirportCtr);
        displayTestRadius = userPrefs.getBoolean("displayTestRadius", displayTestRadius);
        displayBGImage = userPrefs.getBoolean("displayBGImage", displayBGImage);
        displayMarkers = userPrefs.getBoolean("displayMarkers", displayMarkers);
        displayNDBs = userPrefs.getBoolean("displayNDBs", displayNDBs);
        displayVORs = userPrefs.getBoolean("displayVORs", displayVORs);
        displayWindsocks = userPrefs.getBoolean("displayWindsocks", displayWindsocks);
        displayTriggers = userPrefs.getBoolean("displayTriggers", displayTriggers);
        displayScenery = userPrefs.getBoolean("displayScenery", displayScenery);
        displayRotation = userPrefs.getBoolean("displayRotation", displayRotation);
        useAirportAlt = userPrefs.getBoolean("useAirportAlt", useAirportAlt);
        adjustMeasurements = userPrefs.getBoolean("adjustMeasurements", adjustMeasurements);
        drawTaxiwayPaths = userPrefs.getBoolean("drawTaxiwayPaths", drawTaxiwayPaths);
        updateJetways = userPrefs.getBoolean("updateJetways", updateJetways);
        taxiwayProperties = userPrefs.getBoolean("taxiwayProperties", taxiwayProperties);
        taxiwayRunways = userPrefs.getBoolean("taxiwayRunways", taxiwayRunways);
        restoreWindow = userPrefs.getBoolean("restoreWindow", restoreWindow);
        clearCompile = userPrefs.getBoolean("clearCompile", clearCompile);
        xmlPerLine = userPrefs.getBoolean("xmlPerLine", xmlPerLine);
        writeDeletes = userPrefs.getBoolean("writeDeletes", writeDeletes);
        writeExcludes = userPrefs.getBoolean("writeExcludes", writeExcludes);
        writeBGImages = userPrefs.getBoolean("writeBGImages", writeBGImages);
        edgeLightLines = userPrefs.getBoolean("edgeLightLines", edgeLightLines);
        selectedItemOutlined = userPrefs.getBoolean("selectedItemOutlined", selectedItemOutlined);
        useSceneryTexture = userPrefs.getBoolean("useSceneryTexture", useSceneryTexture);
        bgImagesBelow = userPrefs.getBoolean("bgImagesBelow", bgImagesBelow);
        firstTime = userPrefs.getBoolean("firstTime", firstTime);
        displayLights = userPrefs.getBoolean("displayLights", displayLights);
        readNav = userPrefs.getBoolean("readNav", readNav);
        readScenery = userPrefs.getBoolean("readScenery", readScenery);
        displayFPS = userPrefs.getBoolean("displayFPS", displayFPS);
        doubleBuffer = userPrefs.getBoolean("doubleBuffer", doubleBuffer);
        ribbonMenus = userPrefs.getBoolean("ribbonMenus", ribbonMenus);
        secondsIncrement = userPrefs.getInt("secondsIncrement", secondsIncrement);
        latLonDisplay = userPrefs.getInt("latLonDisplay", latLonDisplay);
        latLonGridDisplay = userPrefs.getInt("latLonGridDisplay", latLonGridDisplay);
        latLonObjectDisplay = userPrefs.getInt("latLonObjectDisplay", latLonObjectDisplay);
        maxHistoryItems = userPrefs.getInt("maxHistoryItems", maxHistoryItems);
        lastOpenedDir = userPrefs.get("lastOpenedDir", lastOpenedDir);
        lastOpenedBGLDir = userPrefs.get("lastOpenedBGLDir", lastOpenedBGLDir);
        bgImageOpacity = userPrefs.getInt("bgImageOpacity", bgImageOpacity);
        parkingSize = userPrefs.getFloat("parkingSize", parkingSize);
        tee1Offset = userPrefs.getFloat("tee1Offset", tee1Offset);
        tee2Offset = userPrefs.getFloat("tee2Offset", tee2Offset);
        tee3Offset = userPrefs.getFloat("tee3Offset", tee3Offset);
        tee4Offset = userPrefs.getFloat("tee4Offset", tee4Offset);
        taxiwayWidth = userPrefs.getFloat("taxiwayWidth", taxiwayWidth);
        taxiwayWeight = userPrefs.getFloat("taxiwayWeight", taxiwayWeight);
        helipadLength = userPrefs.getFloat("helipadLength", helipadLength);
        helipadWidth = userPrefs.getFloat("helipadWidth", helipadWidth);
        runwayLength = userPrefs.getFloat("runwayLength", runwayLength);
        runwayWidth = userPrefs.getFloat("runwayWidth", runwayWidth);
        taxiwayPointDiameter = userPrefs.getFloat("taxiwayPointDiameter", taxiwayPointDiameter);
        vertexPointDiameter = userPrefs.getFloat("vertexPointDiameter", vertexPointDiameter);
        vasiX = userPrefs.getFloat("vasiX", vasiX);
        vasiZ = userPrefs.getFloat("vasiZ", vasiZ);
        vasiSpacing = userPrefs.getFloat("vasiSpacing", vasiSpacing);
        offsetLength = userPrefs.getFloat("offsetLength", offsetLength);
        offsetWidth = userPrefs.getFloat("offsetWidth", offsetWidth);
        offsetWidthRunway = userPrefs.getBoolean("offsetWidthRunway", offsetWidthRunway);
        offsetSurfaceRunway = userPrefs.getBoolean("offsetSurfaceRunway", offsetSurfaceRunway);
        parkingMeasure = userPrefs.get("parkingMeasure", parkingMeasure);
        taxiwayMeasure = userPrefs.get("taxiwayMeasure", taxiwayMeasure);
        helipadLengthMeasure = userPrefs.get("helipadLengthMeasure", helipadLengthMeasure);
        helipadWidthMeasure = userPrefs.get("helipadWidthMeasure", helipadWidthMeasure);
        runwayLengthMeasure = userPrefs.get("runwayLengthMeasure", runwayLengthMeasure);
        runwayWidthMeasure = userPrefs.get("runwayWidthMeasure", runwayWidthMeasure);
        taxiwayPointMeasure = userPrefs.get("taxiwayPointMeasure", taxiwayPointMeasure);
        vertexPointMeasure = userPrefs.get("vertexPointMeasure", vertexPointMeasure);
        vasiXMeasure = userPrefs.get("vasiXMeasure", vasiXMeasure);
        vasiZMeasure = userPrefs.get("vasiZMeasure", vasiZMeasure);
        vasiZEnd = userPrefs.get("vasiZEnd", vasiZEnd);
        vasiSpacingMeasure = userPrefs.get("vasiSpacingMeasure", vasiSpacingMeasure);
        offsetLengthMeasure = userPrefs.get("offsetLengthMeasure", offsetLengthMeasure);
        offsetWidthMeasure = userPrefs.get("offsetWidthMeasure", offsetWidthMeasure);
        offsetSurface = userPrefs.get("offsetSurface", offsetSurface);
        airportScanFolder = userPrefs.get("airportScanFolder", airportScanFolder);
        screenBounds = new Rectangle(userPrefs.getInt("screenBoundsX", 0), userPrefs.getInt("screenBoundsY", 0), userPrefs.getInt("screenBoundsWidth", 0), userPrefs.getInt("screenBoundsHeight", 0));
        laf = userPrefs.get("laf", laf);
        if(laf == null)
        {
            laf = "";
            LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
            if(lookAndFeel != null)
                laf = lookAndFeel.getName();
        }
        javax.swing.UIManager.LookAndFeelInfo lafs[] = UIManager.getInstalledLookAndFeels();
        int i = 0;
        do
        {
            if(i >= lafs.length)
                break;
            if(lafs[i].getName().equals(laf))
            {
                try
                {
                    UIManager.setLookAndFeel(lafs[i].getClassName());
                }
                catch(ClassNotFoundException cnfe)
                {
                    LogEngine.getInstance().log(cnfe);
                }
                catch(InstantiationException ie)
                {
                    LogEngine.getInstance().log(ie);
                }
                catch(IllegalAccessException iae)
                {
                    LogEngine.getInstance().log(iae);
                }
                catch(UnsupportedLookAndFeelException ulafe)
                {
                    LogEngine.getInstance().log(ulafe);
                }
                break;
            }
            i++;
        } while(true);
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

    private Vector listeners;
    private String version;
    private String laf;
    private String airportsDatPath;
    private String compDestFolder;
    private String bglCompFolder;
    private String elevationFolder;
    private String parkingMeasure;
    private String taxiwayMeasure;
    private String helipadLengthMeasure;
    private String helipadWidthMeasure;
    private String runwayLengthMeasure;
    private String runwayWidthMeasure;
    private String taxiwayPointMeasure;
    private String vertexPointMeasure;
    private String vasiXMeasure;
    private String vasiZMeasure;
    private String vasiZEnd;
    private String vasiSpacingMeasure;
    private String offsetLengthMeasure;
    private String offsetWidthMeasure;
    private String offsetSurface;
    private String lastOpenedDir;
    private String lastOpenedBGLDir;
    private String airportScanFolder;
    private String simFolder;
    private Rectangle screenBounds;
    private Date lastUpdate;
    private int latLonDisplay;
    private int latLonGridDisplay;
    private int latLonObjectDisplay;
    private int secondsIncrement;
    private int maxHistoryItems;
    private int bgImageOpacity;
    private float parkingSize;
    private float tee1Offset;
    private float tee2Offset;
    private float tee3Offset;
    private float tee4Offset;
    private float taxiwayWidth;
    private float taxiwayWeight;
    private float helipadLength;
    private float helipadWidth;
    private float runwayLength;
    private float runwayWidth;
    private float taxiwayPointDiameter;
    private float vertexPointDiameter;
    private float vasiX;
    private float vasiZ;
    private float vasiSpacing;
    private float offsetLength;
    private float offsetWidth;
    private boolean useSceneryTexture;
    private boolean offsetWidthRunway;
    private boolean offsetSurfaceRunway;
    private boolean displayMemory;
    private boolean displayFPS;
    private boolean doubleBuffer;
    private boolean displayRunway;
    private boolean displayILS;
    private boolean displayApron;
    private boolean displayApronEL;
    private boolean displayTWPoint;
    private boolean displayTWSign;
    private boolean displayParking;
    private boolean displayJetways;
    private boolean showRunwayPaths;
    private boolean showParkingPaths;
    private boolean showTaxiPaths;
    private boolean showPathPaths;
    private boolean showClosedPaths;
    private boolean showVehiclePaths;
    private boolean displayTower;
    private boolean displayBoundFence;
    private boolean displayBlastFence;
    private boolean displayHelipad;
    private boolean displayStart;
    private boolean displayLatLon;
    private boolean displayExcludes;
    private boolean displayAirportCtr;
    private boolean displayTestRadius;
    private boolean displayBGImage;
    private boolean displayMarkers;
    private boolean displayNDBs;
    private boolean displayVORs;
    private boolean displayWindsocks;
    private boolean displayTriggers;
    private boolean displayScenery;
    private boolean displayRotation;
    private boolean useAirportAlt;
    private boolean adjustMeasurements;
    private boolean drawTaxiwayPaths;
    private boolean updateJetways;
    private boolean taxiwayProperties;
    private boolean taxiwayRunways;
    private boolean restoreWindow;
    private boolean clearCompile;
    private boolean xmlPerLine;
    private boolean writeDeletes;
    private boolean writeExcludes;
    private boolean writeBGImages;
    private boolean edgeLightLines;
    private boolean selectedItemOutlined;
    private boolean bgImagesBelow;
    private boolean firstTime;
    private boolean displayLights;
    private boolean readNav;
    private boolean readScenery;
    private boolean ribbonMenus;
    public static final int DECIMAL = 0;
    public static final int FULL_DEGREES = 1;
    public static final int DECIMAL_DEGREES = 2;
    public static final int FULL_DEGREES_EUROPE = 3;
    public static final int DECIMAL_DEGREES_EUROPE = 4;
    private static SettingsEngine engine = null;

}
