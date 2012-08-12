// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 3/2/2008 10:16:46 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   XMLEngine.java

package com.zbluesoftware.fsxp.engine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.*;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            SettingsEngine, LogEngine

public class XMLEngine
{

    private XMLEngine()
    {
        init();
    }

    public static XMLEngine getInstance()
    {
        if(engine == null)
            engine = new XMLEngine();
        engine.setXMLPerLine();
        return engine;
    }

    public void setXMLPerLine()
    {
        xmlPerLine = SettingsEngine.getInstance().getXMLPerLine();
    }

    public void writeDocument(Document xmlDoc, File file)
    {
        Element rootElement;
        BufferedWriter writer;
        rootElement = xmlDoc.getDocumentElement();
        writer = null;
try {
        writer = new BufferedWriter(new FileWriter(file));
        writer.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        writer.newLine();
        writer.write("<!-- Created with FSX Planner [R 27b] -->");
        writer.newLine();
        writer.write("<!-- FSX Planner is freely available here: www.zbluesoftware.com/fsxplanner -->");
        writer.newLine();
        writeElement(rootElement, writer, 0);
} catch (IOException ioe) { LogEngine.getInstance().log(ioe); }
finally {
        try
        {
            if(writer != null)
                writer.close();
        }
        catch(IOException ignored) { }
		}
    }

    private void writeElement(Element element, BufferedWriter writer, int indent)
        throws IOException
    {
        if(element.getTagName().equals("BackgroundImage") || element.getTagName().equals("BorderOnly"))
        {
            for(int i = 0; i < indent; i++)
                writer.write("\t");

            writer.write("<!--");
            writer.newLine();
        }
        for(int i = 0; i < indent; i++)
            writer.write("\t");

        writer.write("<");
        writer.write(element.getTagName());
        if(attributeHM.containsKey(element.getTagName()))
        {
            ArrayList arrayList = (ArrayList)attributeHM.get(element.getTagName());
            for(int i = 0; i < arrayList.size(); i++)
            {
                String attributeName = (String)arrayList.get(i);
                if(element.hasAttribute(attributeName))
                {
                    if(xmlPerLine)
                    {
                        writer.newLine();
                        for(int j = 0; j < indent + 1; j++)
                            writer.write("\t");

                    }
                    writer.write(writeAttribute(attributeName, element.getAttribute(attributeName)));
                }
            }

        } else
        {
            NamedNodeMap attributes = element.getAttributes();
            for(int i = 0; i < attributes.getLength(); i++)
            {
                Node attribute = attributes.item(i);
                if(xmlPerLine)
                {
                    writer.newLine();
                    for(int j = 0; j < indent + 1; j++)
                        writer.write("\t");

                }
                writer.write(writeAttribute(attribute.getNodeName(), element.getAttribute(attribute.getNodeName())));
            }

        }
        boolean hasChildren = false;
        NodeList children = element.getChildNodes();
        int i = children.getLength() - 1;
        do
        {
            if(i < 0)
                break;
            if(children.item(i) instanceof Element)
            {
                hasChildren = true;
                writer.write(">");
                writer.newLine();
                break;
            }
            i--;
        } while(true);
        int totalChildren = children.getLength();
        for(i = 0; i < totalChildren; i++)
            if(children.item(i) instanceof Element)
            {
                hasChildren = true;
                writeElement((Element)children.item(i), writer, indent + 1);
            }

        if(hasChildren)
        {
            for(i = 0; i < indent; i++)
                writer.write("\t");

            writer.write("</");
            writer.write(element.getTagName());
            writer.write(">");
            writer.newLine();
        } else
        {
            writer.write("/>");
            writer.newLine();
        }
        if(element.getTagName().equals("BackgroundImage") || element.getTagName().equals("BorderOnly"))
        {
            for(i = 0; i < indent; i++)
                writer.write("\t");

            writer.write("-->");
            writer.newLine();
        }
    }

    private String writeAttribute(String name, String value)
    {
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll(" & ", "&amp;");
        StringBuffer buffer = new StringBuffer();
        if(!xmlPerLine)
            buffer.append(" ");
        buffer.append(name);
        buffer.append("=\"");
        buffer.append(value);
        buffer.append("\"");
        return buffer.toString();
    }

    private void init()
    {
        attributeHM = new HashMap();
        ArrayList airportAL = new ArrayList();
        airportAL.add("region");
        airportAL.add("country");
        airportAL.add("state");
        airportAL.add("city");
        airportAL.add("name");
        airportAL.add("lat");
        airportAL.add("lon");
        airportAL.add("alt");
        airportAL.add("magvar");
        airportAL.add("ident");
        airportAL.add("trafficScalar");
        airportAL.add("airportTestRadius");
        attributeHM.put("Airport", airportAL);
        ArrayList approachAL = new ArrayList();
        approachAL.add("type");
        approachAL.add("runway");
        approachAL.add("designator");
        approachAL.add("suffix");
        approachAL.add("gpsOverlay");
        approachAL.add("fixType");
        approachAL.add("fixRegion");
        approachAL.add("fixIdent");
        approachAL.add("altitude");
        approachAL.add("heading");
        approachAL.add("missedAltitude");
        attributeHM.put("Approach", approachAL);
        ArrayList legAL = new ArrayList();
        legAL.add("type");
        legAL.add("fixType");
        legAL.add("fixRegion");
        legAL.add("fixIdent");
        legAL.add("flyOver");
        legAL.add("turnDirection");
        legAL.add("recommendedType");
        legAL.add("recommendedRegion");
        legAL.add("recommendedIdent");
        legAL.add("theta");
        legAL.add("rho");
        legAL.add("trueCourse");
        legAL.add("magneticCourse");
        legAL.add("distance");
        legAL.add("time");
        legAL.add("altitudeDescriptor");
        legAL.add("altitude1");
        legAL.add("altitude2");
        attributeHM.put("Leg", legAL);
        ArrayList transitionAL = new ArrayList();
        transitionAL.add("transitionType");
        transitionAL.add("fixType");
        transitionAL.add("fixRegion");
        transitionAL.add("fixIdent");
        transitionAL.add("Altitude");
        attributeHM.put("Transition", transitionAL);
        ArrayList dmeArcAL = new ArrayList();
        dmeArcAL.add("radial");
        dmeArcAL.add("distance");
        dmeArcAL.add("dmeRegion");
        dmeArcAL.add("dmeIdent");
        attributeHM.put("DmeArc", dmeArcAL);
        ArrayList waypointAL = new ArrayList();
        waypointAL.add("lat");
        waypointAL.add("lon");
        waypointAL.add("waypointType");
        waypointAL.add("magvar");
        waypointAL.add("waypointRegion");
        waypointAL.add("waypointIdent");
        attributeHM.put("Waypoint", waypointAL);
        ArrayList apronAL = new ArrayList();
        apronAL.add("surface");
        apronAL.add("drawSurface");
        apronAL.add("drawDetail");
        attributeHM.put("Apron", apronAL);
        ArrayList vertexAL = new ArrayList();
        vertexAL.add("biasX");
        vertexAL.add("biasZ");
        vertexAL.add("lat");
        vertexAL.add("lon");
        attributeHM.put("Vertex", vertexAL);
        ArrayList fenceAL = new ArrayList();
        fenceAL.add("instanceid");
        fenceAL.add("profile");
        attributeHM.put("BlastFence", fenceAL);
        attributeHM.put("BoundaryFence", fenceAL);
        ArrayList runwayAL = new ArrayList();
        runwayAL.add("lat");
        runwayAL.add("lon");
        runwayAL.add("alt");
        runwayAL.add("surface");
        runwayAL.add("heading");
        runwayAL.add("length");
        runwayAL.add("width");
        runwayAL.add("number");
        runwayAL.add("designator");
        runwayAL.add("primaryDesignator");
        runwayAL.add("secondaryDesignatior");
        runwayAL.add("patternAltitude");
        runwayAL.add("primaryTakeoff");
        runwayAL.add("primaryLanding");
        runwayAL.add("primaryPattern");
        runwayAL.add("secondaryTakeoff");
        runwayAL.add("secondaryLanding");
        runwayAL.add("secondaryPattern");
        runwayAL.add("primaryMarkingBias");
        runwayAL.add("secondaryMarkingBias");
        attributeHM.put("Runway", runwayAL);
        ArrayList markingsAL = new ArrayList();
        markingsAL.add("alternateThreshold");
        markingsAL.add("alternateTouchdown");
        markingsAL.add("alternateFixedDistance");
        markingsAL.add("alternatePrecision");
        markingsAL.add("leadingZeroIdent");
        markingsAL.add("noThresholdEndArrows");
        markingsAL.add("edges");
        markingsAL.add("threshold");
        markingsAL.add("fixedDistance");
        markingsAL.add("touchdown");
        markingsAL.add("dashes");
        markingsAL.add("ident");
        markingsAL.add("precision");
        markingsAL.add("edgePavement");
        markingsAL.add("singleEnd");
        markingsAL.add("primaryClosed");
        markingsAL.add("secondaryClosed");
        markingsAL.add("primaryStol");
        markingsAL.add("secondaryStol");
        attributeHM.put("Markings", markingsAL);
        ArrayList lightsAL = new ArrayList();
        lightsAL.add("center");
        lightsAL.add("edge");
        lightsAL.add("centerRed");
        attributeHM.put("Lights", lightsAL);
        ArrayList offsetAL = new ArrayList();
        offsetAL.add("end");
        offsetAL.add("length");
        offsetAL.add("width");
        offsetAL.add("surface");
        attributeHM.put("OffsetThreshold", offsetAL);
        attributeHM.put("BlastPad", offsetAL);
        attributeHM.put("Overrun", offsetAL);
        ArrayList approachLightsAL = new ArrayList();
        approachLightsAL.add("end");
        approachLightsAL.add("system");
        approachLightsAL.add("strobes");
        approachLightsAL.add("reil");
        approachLightsAL.add("touchdown");
        approachLightsAL.add("endLights");
        attributeHM.put("ApproachLights", approachLightsAL);
        ArrayList vasiAL = new ArrayList();
        vasiAL.add("end");
        vasiAL.add("type");
        vasiAL.add("side");
        vasiAL.add("biasX");
        vasiAL.add("biasZ");
        vasiAL.add("spacing");
        vasiAL.add("pitch");
        attributeHM.put("Vasi", vasiAL);
        ArrayList ilsAL = new ArrayList();
        ilsAL.add("lat");
        ilsAL.add("lon");
        ilsAL.add("alt");
        ilsAL.add("heading");
        ilsAL.add("frequency");
        ilsAL.add("end");
        ilsAL.add("range");
        ilsAL.add("magvar");
        ilsAL.add("ident");
        ilsAL.add("width");
        ilsAL.add("name");
        ilsAL.add("backCourse");
        attributeHM.put("Ils", ilsAL);
        ArrayList glideSlopeAL = new ArrayList();
        glideSlopeAL.add("lat");
        glideSlopeAL.add("lon");
        glideSlopeAL.add("alt");
        glideSlopeAL.add("pitch");
        glideSlopeAL.add("range");
        attributeHM.put("GlideSlope", glideSlopeAL);
        ArrayList visualModelAL = new ArrayList();
        visualModelAL.add("heading");
        visualModelAL.add("imageComplexity");
        visualModelAL.add("name");
        visualModelAL.add("instanceid");
        attributeHM.put("VisualModel", visualModelAL);
        ArrayList biasAL = new ArrayList();
        biasAL.add("biasX");
        biasAL.add("biasY");
        biasAL.add("biasZ");
        attributeHM.put("BiasXYZ", biasAL);
        ArrayList dmeAL = new ArrayList();
        dmeAL.add("lat");
        dmeAL.add("lon");
        dmeAL.add("alt");
        dmeAL.add("range");
        attributeHM.put("Dme", dmeAL);
        ArrayList startAL = new ArrayList();
        startAL.add("type");
        startAL.add("lat");
        startAL.add("lon");
        startAL.add("alt");
        startAL.add("heading");
        startAL.add("number");
        startAL.add("designator");
        attributeHM.put("Start", startAL);
        ArrayList taxiwayPointAL = new ArrayList();
        taxiwayPointAL.add("index");
        taxiwayPointAL.add("type");
        taxiwayPointAL.add("orientation");
        taxiwayPointAL.add("lat");
        taxiwayPointAL.add("lon");
        taxiwayPointAL.add("biasX");
        taxiwayPointAL.add("biasZ");
        attributeHM.put("TaxiwayPoint", taxiwayPointAL);
        ArrayList taxiwayParkingAL = new ArrayList();
        taxiwayParkingAL.add("index");
        taxiwayParkingAL.add("lat");
        taxiwayParkingAL.add("lon");
        taxiwayParkingAL.add("biasX");
        taxiwayParkingAL.add("biasZ");
        taxiwayParkingAL.add("heading");
        taxiwayParkingAL.add("radius");
        taxiwayParkingAL.add("type");
        taxiwayParkingAL.add("name");
        taxiwayParkingAL.add("number");
        taxiwayParkingAL.add("airlineCodes");
        taxiwayParkingAL.add("pushBack");
        taxiwayParkingAL.add("teeOffset1");
        taxiwayParkingAL.add("teeOffset2");
        taxiwayParkingAL.add("teeOffset3");
        taxiwayParkingAL.add("teeOffset4");
        attributeHM.put("TaxiwayParking", taxiwayParkingAL);
        ArrayList taxiwayPathAL = new ArrayList();
        taxiwayPathAL.add("type");
        taxiwayPathAL.add("start");
        taxiwayPathAL.add("end");
        taxiwayPathAL.add("width");
        taxiwayPathAL.add("weightLimit");
        taxiwayPathAL.add("surface");
        taxiwayPathAL.add("drawSurface");
        taxiwayPathAL.add("drawDetail");
        taxiwayPathAL.add("centerLine");
        taxiwayPathAL.add("centerLineLighted");
        taxiwayPathAL.add("leftEdge");
        taxiwayPathAL.add("leftEdgeLighted");
        taxiwayPathAL.add("rightEdge");
        taxiwayPathAL.add("rightEdgeLighted");
        taxiwayPathAL.add("number");
        taxiwayPathAL.add("designator");
        taxiwayPathAL.add("name");
        attributeHM.put("TaxiwayPath", taxiwayPathAL);
        ArrayList taxiNameAL = new ArrayList();
        taxiNameAL.add("index");
        taxiNameAL.add("name");
        attributeHM.put("TaxiName", taxiNameAL);
        ArrayList taxiwaySignAL = new ArrayList();
        taxiwaySignAL.add("lat");
        taxiwaySignAL.add("lon");
        taxiwaySignAL.add("heading");
        taxiwaySignAL.add("label");
        taxiwaySignAL.add("justification");
        taxiwaySignAL.add("size");
        attributeHM.put("TaxiwaySign", taxiwaySignAL);
        ArrayList helipadAL = new ArrayList();
        helipadAL.add("lat");
        helipadAL.add("lon");
        helipadAL.add("alt");
        helipadAL.add("surface");
        helipadAL.add("heading");
        helipadAL.add("length");
        helipadAL.add("width");
        helipadAL.add("type");
        helipadAL.add("closed");
        helipadAL.add("transparent");
        helipadAL.add("red");
        helipadAL.add("green");
        helipadAL.add("blue");
        attributeHM.put("Helipad", helipadAL);
        ArrayList fuelAL = new ArrayList();
        fuelAL.add("type");
        fuelAL.add("availability");
        attributeHM.put("Fuel", fuelAL);
        ArrayList towerAL = new ArrayList();
        towerAL.add("lat");
        towerAL.add("lon");
        towerAL.add("alt");
        attributeHM.put("Tower", towerAL);
        ArrayList comAL = new ArrayList();
        comAL.add("frequency");
        comAL.add("type");
        comAL.add("name");
        attributeHM.put("Com", comAL);
        ArrayList deleteAirportAL = new ArrayList();
        deleteAirportAL.add("deleteAllApproaches");
        deleteAirportAL.add("deleteAllApronLights");
        deleteAirportAL.add("deleteAllAprons");
        deleteAirportAL.add("deleteAllFrequencies");
        deleteAirportAL.add("deleteAllHelipads");
        deleteAirportAL.add("deleteAllRunways");
        deleteAirportAL.add("deleteAllStarts");
        deleteAirportAL.add("deleteAllTaxiways");
        deleteAirportAL.add("deleteAllBlastFences");
        deleteAirportAL.add("deleteAllBoundaryFences");
        deleteAirportAL.add("deleteAllControlTowers");
        deleteAirportAL.add("deleteAllJetways");
        attributeHM.put("DeleteAirport", deleteAirportAL);
        ArrayList deleteRunwayAL = new ArrayList();
        deleteRunwayAL.add("surface");
        deleteRunwayAL.add("number");
        deleteRunwayAL.add("designator");
        attributeHM.put("DeleteRunway", deleteRunwayAL);
        ArrayList deleteStartAL = new ArrayList();
        deleteStartAL.add("type");
        deleteStartAL.add("number");
        deleteStartAL.add("designator");
        attributeHM.put("DeleteStart", deleteStartAL);
        ArrayList deleteFreqAL = new ArrayList();
        deleteFreqAL.add("frequency");
        deleteFreqAL.add("type");
        attributeHM.put("DeleteFrequency", deleteFreqAL);
        ArrayList exclusionRectangleAL = new ArrayList();
        exclusionRectangleAL.add("latitudeMinimum");
        exclusionRectangleAL.add("latitudeMaximum");
        exclusionRectangleAL.add("longitudeMinimum");
        exclusionRectangleAL.add("longitudeMaximum");
        exclusionRectangleAL.add("excludeAllObjects");
        exclusionRectangleAL.add("excludeBeaconObjects");
        exclusionRectangleAL.add("excludeEffectObjects");
        exclusionRectangleAL.add("excludeExtrusionBridgeObjects");
        exclusionRectangleAL.add("excludeGenericBuildingObjects");
        exclusionRectangleAL.add("excludeLibraryObjects");
        exclusionRectangleAL.add("excludeTaxiwaySignObjects");
        exclusionRectangleAL.add("excludeTriggerObjects");
        exclusionRectangleAL.add("excludeWindsockObjects");
        attributeHM.put("ExclusionRectangle", exclusionRectangleAL);
        ArrayList vorAL = new ArrayList();
        vorAL.add("nav");
        vorAL.add("dmeOnly");
        vorAL.add("dme");
        vorAL.add("lat");
        vorAL.add("lon");
        vorAL.add("alt");
        vorAL.add("type");
        vorAL.add("frequency");
        vorAL.add("range");
        vorAL.add("magvar");
        vorAL.add("region");
        vorAL.add("ident");
        vorAL.add("name");
        attributeHM.put("Vor", vorAL);
        ArrayList markerAL = new ArrayList();
        markerAL.add("lat");
        markerAL.add("lon");
        markerAL.add("alt");
        markerAL.add("type");
        markerAL.add("heading");
        markerAL.add("region");
        markerAL.add("ident");
        attributeHM.put("Marker", markerAL);
        ArrayList jetwayAL = new ArrayList();
        jetwayAL.add("gateName");
        jetwayAL.add("parkingNumber");
        attributeHM.put("Jetway", jetwayAL);
        ArrayList sceneryAL = new ArrayList();
        sceneryAL.add("instanceid");
        sceneryAL.add("lat");
        sceneryAL.add("lon");
        sceneryAL.add("alt");
        sceneryAL.add("altitudeIsAgl");
        sceneryAL.add("pitch");
        sceneryAL.add("bank");
        sceneryAL.add("heading");
        sceneryAL.add("imageComplexity");
        attributeHM.put("SceneryObject", sceneryAL);
        ArrayList libraryAL = new ArrayList();
        libraryAL.add("name");
        libraryAL.add("scale");
        attributeHM.put("LibraryObject", libraryAL);
        ArrayList backgroundImageAL = new ArrayList();
        backgroundImageAL.add("path");
        backgroundImageAL.add("visible");
        backgroundImageAL.add("topLat");
        backgroundImageAL.add("topLon");
        backgroundImageAL.add("bottomLat");
        backgroundImageAL.add("bottomLon");
        attributeHM.put("BackgroundImage", backgroundImageAL);
    }

    private HashMap attributeHM;
    private boolean xmlPerLine;
    private static XMLEngine engine = null;

}