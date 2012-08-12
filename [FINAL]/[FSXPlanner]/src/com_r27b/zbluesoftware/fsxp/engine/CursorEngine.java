// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CursorEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.graphics.IconFactory;
import java.awt.*;
import javax.swing.ImageIcon;

public class CursorEngine
{

    private CursorEngine()
    {
        parkingCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("parkingCursor").getImage(), new Point(15, 15), "Parking");
        startCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("startCursor").getImage(), new Point(15, 15), "Start");
        towerCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("towerCursor").getImage(), new Point(15, 15), "Tower");
        taxiPointCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("taxiPointCursor").getImage(), new Point(15, 14), "TaxiPoint");
        ilsTaxiPointCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("ilsTaxiPointCursor").getImage(), new Point(15, 14), "ILSTaxiPoint");
        hsTaxiPointCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("hsTaxiPointCursor").getImage(), new Point(15, 14), "HSTaxiPoint");
        taxiwaySignCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("taxiwaySignCursor").getImage(), new Point(15, 14), "TaxiwaySign");
        apronEdgeLightCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("apronEdgeLightCursor").getImage(), new Point(17, 17), "ApronEdgeLight");
        helipadCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("helipadCursor").getImage(), new Point(15, 14), "Helipad");
        taxiwayCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("taxiwayCursor").getImage(), new Point(16, 17), "Taxiway");
        apronCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("apronCursor").getImage(), new Point(16, 17), "Apron");
        apronAddCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("apronAddCursor").getImage(), new Point(15, 17), "Apron Add");
        apronDeleteCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("apronDeleteCursor").getImage(), new Point(15, 17), "Apron Delete");
        runwayCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("runwayCursor").getImage(), new Point(15, 17), "Runway");
        fenceCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("fenceCursor").getImage(), new Point(15, 16), "Boundary Fence");
        fenceAddCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("fenceAddCursor").getImage(), new Point(14, 15), "Boundary Fence Add");
        fenceDeleteCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("fenceDeleteCursor").getImage(), new Point(14, 15), "Boundary Fence Delete");
        blastFenceCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("blastFenceCursor").getImage(), new Point(15, 16), "Blast Fence");
        blastFenceAddCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("blastFenceAddCursor").getImage(), new Point(14, 15), "Blast Fence Add");
        blastFenceDeleteCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("blastFenceDeleteCursor").getImage(), new Point(14, 15), "Blast Fence Delete");
        jetwayCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("jetwayCursor").getImage(), new Point(14, 15), "Jetway");
        ilsBeamCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("ilsBeamCursor").getImage(), new Point(14, 15), "ILS Beam");
        markerCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("markerCursor").getImage(), new Point(14, 15), "Marker");
        vorCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("vorCursor").getImage(), new Point(14, 15), "VOR");
        ndbCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("ndbCursor").getImage(), new Point(14, 15), "NDB");
        windsockCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("windsockCursor").getImage(), new Point(14, 15), "Windsock");
        excludeCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("excludeCursor").getImage(), new Point(15, 16), "Exclusion Rectangle");
        fuelCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("fuelCursor").getImage(), new Point(15, 16), "Fuel Trigger");
        sceneryCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("sceneryCursor").getImage(), new Point(15, 16), "Scenery");
        scrollCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("scrollCursor").getImage(), new Point(14, 15), "Scroll");
        zoomInCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("zoomInCursor").getImage(), new Point(14, 15), "Zoom In");
        zoomOutCursor = Toolkit.getDefaultToolkit().createCustomCursor(IconFactory.getInstance().getIcon("zoomOutCursor").getImage(), new Point(14, 15), "Zoom Out");
        defaultCursor = new Cursor(0);
        currentCursor = defaultCursor;
    }

    public static CursorEngine getInstance()
    {
        if(engine == null)
            engine = new CursorEngine();
        return engine;
    }

    public void setCurrentCursor(Cursor currentCursor)
    {
        this.currentCursor = currentCursor;
    }

    public Cursor getCurrentCursor()
    {
        return currentCursor;
    }

    public Cursor getDefaultCursor()
    {
        return defaultCursor;
    }

    public Cursor getParkingCursor()
    {
        return parkingCursor;
    }

    public Cursor getStartCursor()
    {
        return startCursor;
    }

    public Cursor getTowerCursor()
    {
        return towerCursor;
    }

    public Cursor getTaxiPointCursor()
    {
        return taxiPointCursor;
    }

    public Cursor getILSTaxiPointCursor()
    {
        return ilsTaxiPointCursor;
    }

    public Cursor getHSTaxiPointCursor()
    {
        return hsTaxiPointCursor;
    }

    public Cursor getTaxiwaySignCursor()
    {
        return taxiwaySignCursor;
    }

    public Cursor getApronEdgeLightCursor()
    {
        return apronEdgeLightCursor;
    }

    public Cursor getHelipadCursor()
    {
        return helipadCursor;
    }

    public Cursor getTaxiwayCursor()
    {
        return taxiwayCursor;
    }

    public Cursor getApronCursor()
    {
        return apronCursor;
    }

    public Cursor getApronAddCursor()
    {
        return apronAddCursor;
    }

    public Cursor getApronDeleteCursor()
    {
        return apronDeleteCursor;
    }

    public Cursor getRunwayCursor()
    {
        return runwayCursor;
    }

    public Cursor getFenceCursor()
    {
        return fenceCursor;
    }

    public Cursor getFenceAddCursor()
    {
        return fenceAddCursor;
    }

    public Cursor getFenceDeleteCursor()
    {
        return fenceDeleteCursor;
    }

    public Cursor getBlastFenceCursor()
    {
        return blastFenceCursor;
    }

    public Cursor getBlastFenceAddCursor()
    {
        return blastFenceAddCursor;
    }

    public Cursor getBlastFenceDeleteCursor()
    {
        return blastFenceDeleteCursor;
    }

    public Cursor getScrollCursor()
    {
        return scrollCursor;
    }

    public Cursor getZoomInCursor()
    {
        return zoomInCursor;
    }

    public Cursor getZoomOutCursor()
    {
        return zoomOutCursor;
    }

    public Cursor getJetwayCursor()
    {
        return jetwayCursor;
    }

    public Cursor getILSBeamCursor()
    {
        return ilsBeamCursor;
    }

    public Cursor getMarkerCursor()
    {
        return markerCursor;
    }

    public Cursor getVORCursor()
    {
        return vorCursor;
    }

    public Cursor getNDBCursor()
    {
        return ndbCursor;
    }

    public Cursor getWindsockCursor()
    {
        return windsockCursor;
    }

    public Cursor getExcludeCursor()
    {
        return excludeCursor;
    }

    public Cursor getFuelCursor()
    {
        return fuelCursor;
    }

    public Cursor getSceneryCursor()
    {
        return sceneryCursor;
    }

    private Cursor parkingCursor;
    private Cursor startCursor;
    private Cursor towerCursor;
    private Cursor taxiPointCursor;
    private Cursor ilsTaxiPointCursor;
    private Cursor hsTaxiPointCursor;
    private Cursor taxiwaySignCursor;
    private Cursor apronEdgeLightCursor;
    private Cursor helipadCursor;
    private Cursor taxiwayCursor;
    private Cursor apronCursor;
    private Cursor apronAddCursor;
    private Cursor apronDeleteCursor;
    private Cursor runwayCursor;
    private Cursor fenceCursor;
    private Cursor fenceAddCursor;
    private Cursor fenceDeleteCursor;
    private Cursor blastFenceCursor;
    private Cursor blastFenceAddCursor;
    private Cursor blastFenceDeleteCursor;
    private Cursor jetwayCursor;
    private Cursor ilsBeamCursor;
    private Cursor markerCursor;
    private Cursor vorCursor;
    private Cursor ndbCursor;
    private Cursor windsockCursor;
    private Cursor excludeCursor;
    private Cursor fuelCursor;
    private Cursor sceneryCursor;
    private Cursor defaultCursor;
    private Cursor scrollCursor;
    private Cursor zoomInCursor;
    private Cursor zoomOutCursor;
    private Cursor currentCursor;
    private static CursorEngine engine = null;

}
