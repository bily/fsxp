// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IconFactory.java

package com.zbluesoftware.fsxp.graphics;

import java.util.HashMap;
import javax.swing.ImageIcon;

public class IconFactory
{

    private IconFactory()
    {
        iconHM = new HashMap();
        iconHM.put("delete", new ImageIcon(getClass().getResource("icons/delete.gif")));
        iconHM.put("cutLine", new ImageIcon(getClass().getResource("icons/CutLine.gif")));
        iconHM.put("ilsRunwayPrimary", new ImageIcon(getClass().getResource("icons/ILSRunwayPrimary.gif")));
        iconHM.put("ilsRunwaySecondary", new ImageIcon(getClass().getResource("icons/ILSRunwaySecondary.gif")));
        iconHM.put("fsxConnected", new ImageIcon(getClass().getResource("icons/FSXConnected.gif")));
        iconHM.put("fsxDisconnected", new ImageIcon(getClass().getResource("icons/FSXDisconnected.gif")));
        iconHM.put("padlockLocked", new ImageIcon(getClass().getResource("icons/PadlockLocked.gif")));
        iconHM.put("padlockUnlocked", new ImageIcon(getClass().getResource("icons/PadlockUnlocked.gif")));
        iconHM.put("highlightedTWPath", new ImageIcon(getClass().getResource("icons/HighlightedTWPath.gif")));
        iconHM.put("select", new ImageIcon(getClass().getResource("icons/Select.gif")));
        iconHM.put("selectOver", new ImageIcon(getClass().getResource("icons/SelectOver.gif")));
        iconHM.put("selectOn", new ImageIcon(getClass().getResource("icons/SelectOn.gif")));
        iconHM.put("parking", new ImageIcon(getClass().getResource("icons/Parking.gif")));
        iconHM.put("parkingOver", new ImageIcon(getClass().getResource("icons/ParkingOver.gif")));
        iconHM.put("parkingOn", new ImageIcon(getClass().getResource("icons/ParkingOn.gif")));
        iconHM.put("taxiPoint", new ImageIcon(getClass().getResource("icons/TaxiPoint.gif")));
        iconHM.put("taxiPointOver", new ImageIcon(getClass().getResource("icons/TaxiPointOver.gif")));
        iconHM.put("taxiPointOn", new ImageIcon(getClass().getResource("icons/TaxiPointOn.gif")));
        iconHM.put("taxiway", new ImageIcon(getClass().getResource("icons/Taxiway.gif")));
        iconHM.put("taxiwayOver", new ImageIcon(getClass().getResource("icons/TaxiwayOver.gif")));
        iconHM.put("taxiwayOn", new ImageIcon(getClass().getResource("icons/TaxiwayOn.gif")));
        iconHM.put("apron", new ImageIcon(getClass().getResource("icons/Apron.gif")));
        iconHM.put("apronOver", new ImageIcon(getClass().getResource("icons/ApronOver.gif")));
        iconHM.put("apronOn", new ImageIcon(getClass().getResource("icons/ApronOn.gif")));
        iconHM.put("ilsTaxiPoint", new ImageIcon(getClass().getResource("icons/ILSTaxiPoint.gif")));
        iconHM.put("ilsTaxiPointOver", new ImageIcon(getClass().getResource("icons/ILSTaxiPointOver.gif")));
        iconHM.put("ilsTaxiPointOn", new ImageIcon(getClass().getResource("icons/ILSTaxiPointOn.gif")));
        iconHM.put("hsTaxiPoint", new ImageIcon(getClass().getResource("icons/HSTaxiPoint.gif")));
        iconHM.put("hsTaxiPointOver", new ImageIcon(getClass().getResource("icons/HSTaxiPointOver.gif")));
        iconHM.put("hsTaxiPointOn", new ImageIcon(getClass().getResource("icons/HSTaxiPointOn.gif")));
        iconHM.put("startPoint", new ImageIcon(getClass().getResource("icons/StartPoint.gif")));
        iconHM.put("startPointOver", new ImageIcon(getClass().getResource("icons/StartPointOver.gif")));
        iconHM.put("startPointOn", new ImageIcon(getClass().getResource("icons/StartPointOn.gif")));
        iconHM.put("tower", new ImageIcon(getClass().getResource("icons/Tower.gif")));
        iconHM.put("towerOver", new ImageIcon(getClass().getResource("icons/TowerOver.gif")));
        iconHM.put("towerOn", new ImageIcon(getClass().getResource("icons/TowerOn.gif")));
        iconHM.put("taxiwaySign", new ImageIcon(getClass().getResource("icons/TaxiwaySign.gif")));
        iconHM.put("taxiwaySignOver", new ImageIcon(getClass().getResource("icons/TaxiwaySignOver.gif")));
        iconHM.put("taxiwaySignOn", new ImageIcon(getClass().getResource("icons/TaxiwaySignOn.gif")));
        iconHM.put("apronEdgeLight", new ImageIcon(getClass().getResource("icons/ApronEdgeLight.gif")));
        iconHM.put("apronEdgeLightOver", new ImageIcon(getClass().getResource("icons/ApronEdgeLightOver.gif")));
        iconHM.put("apronEdgeLightOn", new ImageIcon(getClass().getResource("icons/ApronEdgeLightOn.gif")));
        iconHM.put("helipad", new ImageIcon(getClass().getResource("icons/Helipad.gif")));
        iconHM.put("helipadOver", new ImageIcon(getClass().getResource("icons/HelipadOver.gif")));
        iconHM.put("helipadOn", new ImageIcon(getClass().getResource("icons/HelipadOn.gif")));
        iconHM.put("runway", new ImageIcon(getClass().getResource("icons/Runway.gif")));
        iconHM.put("runwayOver", new ImageIcon(getClass().getResource("icons/RunwayOver.gif")));
        iconHM.put("runwayOn", new ImageIcon(getClass().getResource("icons/RunwayOn.gif")));
        iconHM.put("fence", new ImageIcon(getClass().getResource("icons/Fence.gif")));
        iconHM.put("fenceOver", new ImageIcon(getClass().getResource("icons/FenceOver.gif")));
        iconHM.put("fenceOn", new ImageIcon(getClass().getResource("icons/FenceOn.gif")));
        iconHM.put("scroll", new ImageIcon(getClass().getResource("icons/Scroll.gif")));
        iconHM.put("scrollOver", new ImageIcon(getClass().getResource("icons/ScrollOver.gif")));
        iconHM.put("scrollOn", new ImageIcon(getClass().getResource("icons/ScrollOn.gif")));
        iconHM.put("zoomIn", new ImageIcon(getClass().getResource("icons/ZoomIn.gif")));
        iconHM.put("zoomInOver", new ImageIcon(getClass().getResource("icons/ZoomInOver.gif")));
        iconHM.put("zoomInOn", new ImageIcon(getClass().getResource("icons/ZoomInOn.gif")));
        iconHM.put("zoomOut", new ImageIcon(getClass().getResource("icons/ZoomOut.gif")));
        iconHM.put("zoomOutOver", new ImageIcon(getClass().getResource("icons/ZoomOutOver.gif")));
        iconHM.put("zoomOutOn", new ImageIcon(getClass().getResource("icons/ZoomOutOn.gif")));
        iconHM.put("blastFence", new ImageIcon(getClass().getResource("icons/BlastFence.gif")));
        iconHM.put("blastFenceOver", new ImageIcon(getClass().getResource("icons/BlastFenceOver.gif")));
        iconHM.put("blastFenceOn", new ImageIcon(getClass().getResource("icons/BlastFenceOn.gif")));
        iconHM.put("jetway", new ImageIcon(getClass().getResource("icons/Jetway.gif")));
        iconHM.put("jetwayOver", new ImageIcon(getClass().getResource("icons/JetwayOver.gif")));
        iconHM.put("jetwayOn", new ImageIcon(getClass().getResource("icons/JetwayOn.gif")));
        iconHM.put("ilsBeam", new ImageIcon(getClass().getResource("icons/ILSBeam.gif")));
        iconHM.put("ilsBeamOver", new ImageIcon(getClass().getResource("icons/ILSBeamOver.gif")));
        iconHM.put("ilsBeamOn", new ImageIcon(getClass().getResource("icons/ILSBeamOn.gif")));
        iconHM.put("marker", new ImageIcon(getClass().getResource("icons/Marker.gif")));
        iconHM.put("markerOver", new ImageIcon(getClass().getResource("icons/MarkerOver.gif")));
        iconHM.put("markerOn", new ImageIcon(getClass().getResource("icons/MarkerOn.gif")));
        iconHM.put("vor", new ImageIcon(getClass().getResource("icons/VOR.gif")));
        iconHM.put("vorOver", new ImageIcon(getClass().getResource("icons/VOROver.gif")));
        iconHM.put("vorOn", new ImageIcon(getClass().getResource("icons/VOROn.gif")));
        iconHM.put("ndb", new ImageIcon(getClass().getResource("icons/NDB.gif")));
        iconHM.put("ndbOver", new ImageIcon(getClass().getResource("icons/NDBOver.gif")));
        iconHM.put("ndbOn", new ImageIcon(getClass().getResource("icons/NDBOn.gif")));
        iconHM.put("windsock", new ImageIcon(getClass().getResource("icons/Windsock.gif")));
        iconHM.put("windsockOver", new ImageIcon(getClass().getResource("icons/WindsockOver.gif")));
        iconHM.put("windsockOn", new ImageIcon(getClass().getResource("icons/WindsockOn.gif")));
        iconHM.put("exclude", new ImageIcon(getClass().getResource("icons/Exclude.gif")));
        iconHM.put("excludeOver", new ImageIcon(getClass().getResource("icons/ExcludeOver.gif")));
        iconHM.put("excludeOn", new ImageIcon(getClass().getResource("icons/ExcludeOn.gif")));
        iconHM.put("fuel", new ImageIcon(getClass().getResource("icons/Fuel.gif")));
        iconHM.put("fuelOver", new ImageIcon(getClass().getResource("icons/FuelOver.gif")));
        iconHM.put("fuelOn", new ImageIcon(getClass().getResource("icons/FuelOn.gif")));
        iconHM.put("scenery", new ImageIcon(getClass().getResource("icons/Scenery.gif")));
        iconHM.put("sceneryOver", new ImageIcon(getClass().getResource("icons/SceneryOver.gif")));
        iconHM.put("sceneryOn", new ImageIcon(getClass().getResource("icons/SceneryOn.gif")));
        iconHM.put("parkingCursor", new ImageIcon(getClass().getResource("icons/ParkingCursor.gif")));
        iconHM.put("startCursor", new ImageIcon(getClass().getResource("icons/StartCursor.gif")));
        iconHM.put("towerCursor", new ImageIcon(getClass().getResource("icons/TowerCursor.gif")));
        iconHM.put("taxiPointCursor", new ImageIcon(getClass().getResource("icons/TaxiPointCursor.gif")));
        iconHM.put("ilsTaxiPointCursor", new ImageIcon(getClass().getResource("icons/ILSTaxiPointCursor.gif")));
        iconHM.put("hsTaxiPointCursor", new ImageIcon(getClass().getResource("icons/HSTaxiPointCursor.gif")));
        iconHM.put("taxiwaySignCursor", new ImageIcon(getClass().getResource("icons/TaxiwaySignCursor.gif")));
        iconHM.put("apronEdgeLightCursor", new ImageIcon(getClass().getResource("icons/ApronEdgeLightCursor.gif")));
        iconHM.put("helipadCursor", new ImageIcon(getClass().getResource("icons/HelipadCursor.gif")));
        iconHM.put("taxiwayCursor", new ImageIcon(getClass().getResource("icons/TaxiwayCursor.gif")));
        iconHM.put("apronCursor", new ImageIcon(getClass().getResource("icons/ApronCursor.gif")));
        iconHM.put("apronAddCursor", new ImageIcon(getClass().getResource("icons/ApronAddCursor.gif")));
        iconHM.put("apronDeleteCursor", new ImageIcon(getClass().getResource("icons/ApronDeleteCursor.gif")));
        iconHM.put("runwayCursor", new ImageIcon(getClass().getResource("icons/RunwayCursor.gif")));
        iconHM.put("fenceAddCursor", new ImageIcon(getClass().getResource("icons/FenceAddCursor.gif")));
        iconHM.put("fenceDeleteCursor", new ImageIcon(getClass().getResource("icons/FenceDeleteCursor.gif")));
        iconHM.put("fenceCursor", new ImageIcon(getClass().getResource("icons/FenceCursor.gif")));
        iconHM.put("blastFenceAddCursor", new ImageIcon(getClass().getResource("icons/BlastFenceAddCursor.gif")));
        iconHM.put("blastFenceDeleteCursor", new ImageIcon(getClass().getResource("icons/BlastFenceDeleteCursor.gif")));
        iconHM.put("blastFenceCursor", new ImageIcon(getClass().getResource("icons/BlastFenceCursor.gif")));
        iconHM.put("jetwayCursor", new ImageIcon(getClass().getResource("icons/JetwayCursor.gif")));
        iconHM.put("ilsBeamCursor", new ImageIcon(getClass().getResource("icons/ILSBeamCursor.gif")));
        iconHM.put("markerCursor", new ImageIcon(getClass().getResource("icons/MarkerCursor.gif")));
        iconHM.put("vorCursor", new ImageIcon(getClass().getResource("icons/VORCursor.gif")));
        iconHM.put("ndbCursor", new ImageIcon(getClass().getResource("icons/NDBCursor.gif")));
        iconHM.put("windsockCursor", new ImageIcon(getClass().getResource("icons/WindsockCursor.gif")));
        iconHM.put("excludeCursor", new ImageIcon(getClass().getResource("icons/ExcludeCursor.gif")));
        iconHM.put("fuelCursor", new ImageIcon(getClass().getResource("icons/FuelCursor.gif")));
        iconHM.put("sceneryCursor", new ImageIcon(getClass().getResource("icons/SceneryCursor.gif")));
        iconHM.put("scrollCursor", new ImageIcon(getClass().getResource("icons/ScrollCursor.gif")));
        iconHM.put("zoomInCursor", new ImageIcon(getClass().getResource("icons/ZoomInCursor.gif")));
        iconHM.put("zoomOutCursor", new ImageIcon(getClass().getResource("icons/ZoomOutCursor.gif")));
    }

    public static IconFactory getInstance()
    {
        if(factory == null)
            factory = new IconFactory();
        return factory;
    }

    public ImageIcon getIcon(String name)
    {
        if(iconHM.containsKey(name))
            return (ImageIcon)iconHM.get(name);
        else
            return null;
    }

    private HashMap iconHM;
    private static IconFactory factory = null;

}
