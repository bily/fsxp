// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExclusionEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import java.util.*;

public class ExclusionEngine
{

    private ExclusionEngine()
    {
        rectangleHM = null;
    }

    public static ExclusionEngine getInstance()
    {
        if(engine == null)
            engine = new ExclusionEngine();
        return engine;
    }

    public HashMap getRectangleHM()
    {
        return rectangleHM;
    }

    public HashMap getRectangle(AirportModel airportModel)
    {
        rectangleHM = new HashMap();
        rectangleHM.put("topLeft", (LatLonPoint)airportModel.getLatLon().clone());
        rectangleHM.put("bottomRight", (LatLonPoint)airportModel.getLatLon().clone());
        checkRunways(airportModel);
        checkTaxiwayPoints(airportModel);
        checkTaxiwaySigns(airportModel);
        checkJetways(airportModel);
        checkParkingLocations(airportModel);
        checkStartLocations(airportModel);
        checkHelipads(airportModel);
        checkTowers(airportModel);
        checkAprons(airportModel);
        checkApronEdgeLights(airportModel);
        checkBoundaryFences(airportModel);
        checkBlastFences(airportModel);
        checkILSBeams(airportModel);
        checkMarkers(airportModel);
        checkVORs(airportModel);
        checkNDBs(airportModel);
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        topLeft.adjustLat(0.00055555555555555556D);
        topLeft.adjustLon(-0.00055555555555555556D);
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        bottomRight.adjustLat(-0.00055555555555555556D);
        bottomRight.adjustLon(0.00055555555555555556D);
        return rectangleHM;
    }

    private void checkRunways(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getRunwayAL().size() - 1; i >= 0; i--)
        {
            RunwayModel model = (RunwayModel)airportModel.getRunwayAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkTaxiwayPoints(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        TaxiwayPointModel model;
        for(Iterator iterator = airportModel.getTaxiwayPointHM().keySet().iterator(); iterator.hasNext(); enclosePoint(model.getLatLon(), topLeft, bottomRight))
            model = (TaxiwayPointModel)airportModel.getTaxiwayPointHM().get(iterator.next());

    }

    private void checkTaxiwaySigns(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getTaxiwaySignAL().size() - 1; i >= 0; i--)
        {
            TaxiwaySignModel model = (TaxiwaySignModel)airportModel.getTaxiwaySignAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkJetways(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getJetwayAL().size() - 1; i >= 0; i--)
        {
            JetwayModel model = (JetwayModel)airportModel.getJetwayAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkParkingLocations(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        TaxiwayParkingModel model;
        for(Iterator iterator = airportModel.getTaxiwayParkingHM().keySet().iterator(); iterator.hasNext(); enclosePoint(model.getLatLon(), topLeft, bottomRight))
            model = (TaxiwayParkingModel)airportModel.getTaxiwayParkingHM().get(iterator.next());

    }

    private void checkStartLocations(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getStartAL().size() - 1; i >= 0; i--)
        {
            StartModel model = (StartModel)airportModel.getStartAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkHelipads(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getHelipadAL().size() - 1; i >= 0; i--)
        {
            HelipadModel model = (HelipadModel)airportModel.getHelipadAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkTowers(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getTowerAL().size() - 1; i >= 0; i--)
        {
            TowerModel model = (TowerModel)airportModel.getTowerAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkAprons(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getApronAL().size() - 1; i >= 0; i--)
        {
            ApronModel model = (ApronModel)airportModel.getApronAL().get(i);
            for(int j = model.getVertexAL().size() - 1; j >= 0; j--)
            {
                VertexModel vertexModel = (VertexModel)model.getVertexAL().get(j);
                enclosePoint(vertexModel.getLatLon(), topLeft, bottomRight);
            }

        }

    }

    private void checkApronEdgeLights(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getApronEdgeLightAL().size() - 1; i >= 0; i--)
        {
            ApronEdgeLightModel model = (ApronEdgeLightModel)airportModel.getApronEdgeLightAL().get(i);
            for(int j = model.getVertexAL().size() - 1; j >= 0; j--)
                enclosePoint(((VertexModel)model.getVertexAL().get(j)).getLatLon(), topLeft, bottomRight);

        }

    }

    private void checkBoundaryFences(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getBoundaryFenceAL().size() - 1; i >= 0; i--)
        {
            BoundaryFenceModel model = (BoundaryFenceModel)airportModel.getBoundaryFenceAL().get(i);
            for(int j = model.getVertexAL().size() - 1; j >= 0; j--)
            {
                VertexModel vertexModel = (VertexModel)model.getVertexAL().get(j);
                enclosePoint(vertexModel.getLatLon(), topLeft, bottomRight);
            }

        }

    }

    private void checkBlastFences(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getBlastFenceAL().size() - 1; i >= 0; i--)
        {
            BlastFenceModel model = (BlastFenceModel)airportModel.getBlastFenceAL().get(i);
            for(int j = model.getVertexAL().size() - 1; j >= 0; j--)
            {
                VertexModel vertexModel = (VertexModel)model.getVertexAL().get(j);
                enclosePoint(vertexModel.getLatLon(), topLeft, bottomRight);
            }

        }

    }

    private void checkILSBeams(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getILSModels().size() - 1; i >= 0; i--)
        {
            ILSModel model = (ILSModel)airportModel.getILSModels().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
            if(model.getDMEModel() != null)
                enclosePoint(model.getDMEModel().getLatLon(), topLeft, bottomRight);
            if(model.getGlideSlopeModel() != null)
                enclosePoint(model.getGlideSlopeModel().getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkMarkers(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getMarkerAL().size() - 1; i >= 0; i--)
        {
            MarkerModel model = (MarkerModel)airportModel.getMarkerAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkVORs(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getVORAL().size() - 1; i >= 0; i--)
        {
            VORModel model = (VORModel)airportModel.getVORAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
            if(model.getDme())
                enclosePoint(model.getDMEModel().getLatLon(), topLeft, bottomRight);
        }

    }

    private void checkNDBs(AirportModel airportModel)
    {
        LatLonPoint topLeft = (LatLonPoint)rectangleHM.get("topLeft");
        LatLonPoint bottomRight = (LatLonPoint)rectangleHM.get("bottomRight");
        for(int i = airportModel.getNDBAL().size() - 1; i >= 0; i--)
        {
            NDBModel model = (NDBModel)airportModel.getNDBAL().get(i);
            enclosePoint(model.getLatLon(), topLeft, bottomRight);
        }

    }

    private void enclosePoint(LatLonPoint modelLatLon, LatLonPoint topLeft, LatLonPoint bottomRight)
    {
        if(modelLatLon.getLon() >= topLeft.getLon() && modelLatLon.getLon() <= bottomRight.getLon() && modelLatLon.getLat() >= bottomRight.getLat() && modelLatLon.getLat() <= topLeft.getLat())
            return;
        if(modelLatLon.getLon() < topLeft.getLon())
            topLeft.adjustLon(modelLatLon.getLon() - topLeft.getLon());
        else
        if(modelLatLon.getLon() > bottomRight.getLon())
            bottomRight.adjustLon(modelLatLon.getLon() - bottomRight.getLon());
        if(modelLatLon.getLat() > topLeft.getLat())
            topLeft.adjustLat(modelLatLon.getLat() - topLeft.getLat());
        else
        if(modelLatLon.getLat() < bottomRight.getLat())
            bottomRight.adjustLat(modelLatLon.getLat() - bottomRight.getLat());
    }

    private HashMap rectangleHM;
    public static final String TOP_LEFT = "topLeft";
    public static final String BOTTOM_RIGHT = "bottomRight";
    private static ExclusionEngine engine = null;

}
