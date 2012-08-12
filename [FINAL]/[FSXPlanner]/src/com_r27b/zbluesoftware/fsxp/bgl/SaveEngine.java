// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:27:34 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SaveEngine.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.AirportObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachLegObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachLightObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachObject;
import com.zbluesoftware.fsxp.bgl.object.ApronEdgeLightObject;
import com.zbluesoftware.fsxp.bgl.object.ApronObject;
import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import com.zbluesoftware.fsxp.bgl.object.BlastFenceObject;
import com.zbluesoftware.fsxp.bgl.object.BoundaryFenceObject;
import com.zbluesoftware.fsxp.bgl.object.ComObject;
import com.zbluesoftware.fsxp.bgl.object.DMEArcObject;
import com.zbluesoftware.fsxp.bgl.object.DMEObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteFrequencyObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteRunwayObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteStartObject;
import com.zbluesoftware.fsxp.bgl.object.EdgeLightObject;
import com.zbluesoftware.fsxp.bgl.object.ExclusionObject;
import com.zbluesoftware.fsxp.bgl.object.FileObject;
import com.zbluesoftware.fsxp.bgl.object.FuelTriggerObject;
import com.zbluesoftware.fsxp.bgl.object.GlideSlopeObject;
import com.zbluesoftware.fsxp.bgl.object.HelipadObject;
import com.zbluesoftware.fsxp.bgl.object.JetwayObject;
import com.zbluesoftware.fsxp.bgl.object.LegObject;
import com.zbluesoftware.fsxp.bgl.object.LocalizerObject;
import com.zbluesoftware.fsxp.bgl.object.MarkerObject;
import com.zbluesoftware.fsxp.bgl.object.MissedApproachLegObject;
import com.zbluesoftware.fsxp.bgl.object.NDBObject;
import com.zbluesoftware.fsxp.bgl.object.NameObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayDetailObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayVasiObject;
import com.zbluesoftware.fsxp.bgl.object.SceneryObject;
import com.zbluesoftware.fsxp.bgl.object.ServicesObject;
import com.zbluesoftware.fsxp.bgl.object.StartObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiNameObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiNamesObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayParkingObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayParkingsObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPathObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPathsObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPointObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPointsObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwaySignObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwaySignsObject;
import com.zbluesoftware.fsxp.bgl.object.TowerSceneryObject;
import com.zbluesoftware.fsxp.bgl.object.TransitionLegObject;
import com.zbluesoftware.fsxp.bgl.object.TransitionObject;
import com.zbluesoftware.fsxp.bgl.object.TriggerObject;
import com.zbluesoftware.fsxp.bgl.object.VORILSObject;
import com.zbluesoftware.fsxp.bgl.object.VertexObject;
import com.zbluesoftware.fsxp.bgl.object.WindsockObject;
import com.zbluesoftware.fsxp.engine.LegTypeEngine;
import com.zbluesoftware.fsxp.engine.LogEngine;
import java.text.NumberFormat;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class SaveEngine
{

    private SaveEngine()
    {
        latLonFormat = NumberFormat.getInstance(Locale.US);
        latLonFormat.setMinimumIntegerDigits(0);
        latLonFormat.setMaximumFractionDigits(14);
    }

    public static SaveEngine getInstance()
    {
        if(engine == null)
            engine = new SaveEngine();
        return engine;
    }

    public Document createXML(FileObject fileObject, Document xmlDoc)
    {
        Element fsDataElement = null;
        if(xmlDoc == null)
        {
            try
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                xmlDoc = builder.newDocument();
            }
            catch(ParserConfigurationException pce)
            {
                LogEngine.getInstance().log(pce);
            }
            fsDataElement = xmlDoc.createElement("FSData");
            fsDataElement.setAttribute("version", "9.0");
            fsDataElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            fsDataElement.setAttribute("xsi:noNamespaceSchemaLocation", "bglcomp.xsd");
            xmlDoc.appendChild(fsDataElement);
        } else
        {
            NodeList nodeList = xmlDoc.getElementsByTagName("FSData");
            for(int i = nodeList.getLength() - 1; i >= 0; i--)
                fsDataElement = (Element)nodeList.item(i);

        }
        writeExclusions(fileObject, xmlDoc, fsDataElement);
        for(int i = 0; i < fileObject.getObjectAL().size(); i++)
            if(fileObject.getObjectAL().get(i) instanceof AirportObject)
                writeAirport((AirportObject)fileObject.getObjectAL().get(i), xmlDoc, fsDataElement);

        writeMarkers(fileObject, fsDataElement, xmlDoc);
        writeNDBs(fileObject, fsDataElement, xmlDoc);
        writeVORs(fileObject, fsDataElement, xmlDoc);
        writeWindsocks(fileObject, fsDataElement, xmlDoc);
        writeTriggers(fileObject, fsDataElement, xmlDoc);
        writeScenery(fileObject, fsDataElement, xmlDoc);
        return xmlDoc;
    }

    private void writeAirport(AirportObject airportObject, Document xmlDoc, Element fsDataElement)
    {
        Element airportElement = xmlDoc.createElement("Airport");
        fsDataElement.appendChild(airportElement);
        if(airportObject.getRegion().length() > 0)
            airportElement.setAttribute("region", airportObject.getRegion());
        if(airportObject.getCountry().length() > 0)
            airportElement.setAttribute("country", airportObject.getCountry());
        if(airportObject.getState().length() > 0)
            airportElement.setAttribute("state", airportObject.getState());
        if(airportObject.getCity().length() > 0)
            airportElement.setAttribute("city", airportObject.getCity());
        ArrayList arrayList = airportObject.getObjectAL();
        int i = 0;
        do
        {
            if(i >= arrayList.size())
                break;
            if(arrayList.get(i) instanceof NameObject)
            {
                airportElement.setAttribute("name", (String)((NameObject)arrayList.get(i)).getItem("name").getDecodedData());
                break;
            }
            i++;
        } while(true);
        airportElement.setAttribute("lat", latLonFormat.format((Double)airportObject.getItem("latitude").getDecodedData()));
        airportElement.setAttribute("lon", latLonFormat.format((Double)airportObject.getItem("longitude").getDecodedData()));
        airportElement.setAttribute("alt", (new StringBuilder()).append(airportObject.getItem("altitude").getDecodedData()).append("M").toString());
        airportElement.setAttribute("magvar", (new StringBuilder()).append("").append(airportObject.getItem("magVar").getDecodedData()).toString());
        airportElement.setAttribute("ident", (String)airportObject.getItem("icao").getDecodedData());
        airportElement.setAttribute("airportTestRadius", "5000M");
        airportElement.setAttribute("trafficScalar", "0.7");
        writeDeletes(airportObject, xmlDoc, airportElement);
        writeServices(airportObject, airportElement, xmlDoc);
        writeAprons(airportObject, airportElement, xmlDoc);
        writeApronEdgeLights(airportObject, airportElement, xmlDoc);
        writeBlastFences(airportObject, airportElement, xmlDoc);
        writeBoundaryFences(airportObject, airportElement, xmlDoc);
        writeHelipads(airportObject, airportElement, xmlDoc);
        writeJetways(airportObject, airportElement, xmlDoc);
        writeRunways(airportObject, airportElement, xmlDoc);
        writeStarts(airportObject, airportElement, xmlDoc);
        writeTaxiways(airportObject, airportElement, xmlDoc);
        writeTaxiwaySigns(airportObject, airportElement, xmlDoc);
        writeTowers(airportObject, airportElement, xmlDoc);
        writeApproaches(airportObject, airportElement, xmlDoc);
        writeCOMs(airportObject, airportElement, xmlDoc);
        writeNDBs(airportObject, airportElement, xmlDoc);
    }

    private void writeExclusions(FileObject fileObject, Document xmlDoc, Element fsDataElement)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(fileObject.getObjectAL().get(i) instanceof ExclusionObject))
                continue;
            ExclusionObject exclusionObject = (ExclusionObject)fileObject.getObjectAL().get(i);
            Element exclusionElement = xmlDoc.createElement("ExclusionRectangle");
            if(((String)exclusionObject.getItem("excludeAll").getDecodedData()).equals("TRUE"))
            {
                exclusionElement.setAttribute("excludeAllObjects", "TRUE");
            } else
            {
                if(((String)exclusionObject.getItem("beaconObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeBeaconObjects", "TRUE");
                if(((String)exclusionObject.getItem("effectObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeEffectObjects", "TRUE");
                if(((String)exclusionObject.getItem("extrusionBridges").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeExtrusionBridgeObjects", "TRUE");
                if(((String)exclusionObject.getItem("genericBuildingObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeGenericBuildingObjects", "TRUE");
                if(((String)exclusionObject.getItem("libraryObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeLibraryObjects", "TRUE");
                if(((String)exclusionObject.getItem("taxiwaySignObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeTaxiwaySignObjects", "TRUE");
                if(((String)exclusionObject.getItem("triggerObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeTriggerObjects", "TRUE");
                if(((String)exclusionObject.getItem("windsockObjects").getDecodedData()).equals("TRUE"))
                    exclusionElement.setAttribute("excludeWindsockObjects", "TRUE");
                if(exclusionElement.hasAttributes())
                    exclusionElement.setAttribute("excludeAllObjects", "FALSE");
            }
            if(exclusionElement.hasAttributes())
            {
                exclusionElement.setAttribute("latitudeMinimum", latLonFormat.format((Double)exclusionObject.getItem("bottomRightLat").getDecodedData()));
                exclusionElement.setAttribute("latitudeMaximum", latLonFormat.format((Double)exclusionObject.getItem("topLeftLat").getDecodedData()));
                exclusionElement.setAttribute("longitudeMinimum", latLonFormat.format((Double)exclusionObject.getItem("bottomRightLon").getDecodedData()));
                exclusionElement.setAttribute("longitudeMaximum", latLonFormat.format((Double)exclusionObject.getItem("topLeftLon").getDecodedData()));
                fsDataElement.appendChild(exclusionElement);
            }
        }

    }

    private void writeDeletes(AirportObject airportObject, Document xmlDoc, Element airportElement)
    {
        DeleteObject deleteObject = null;
        int i = 0;
        do
        {
            if(i >= airportObject.getObjectAL().size())
                break;
            if(airportObject.getObjectAL().get(i) instanceof DeleteObject)
            {
                deleteObject = (DeleteObject)airportObject.getObjectAL().get(i);
                break;
            }
            i++;
        } while(true);
        if(deleteObject == null)
            return;
        Element deleteElement = xmlDoc.createElement("DeleteAirport");
        if(((String)deleteObject.getItem("allApproaches").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllApproaches", "TRUE");
        if(((String)deleteObject.getItem("allApronLights").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllApronLights", "TRUE");
        if(((String)deleteObject.getItem("allAprons").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllAprons", "TRUE");
        if(((String)deleteObject.getItem("allFrequencies").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllFrequencies", "TRUE");
        if(((String)deleteObject.getItem("allHelipads").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllHelipads", "TRUE");
        if(((String)deleteObject.getItem("allRunways").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllRunways", "TRUE");
        if(((String)deleteObject.getItem("allStarts").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllStarts", "TRUE");
        if(((String)deleteObject.getItem("allTaxiways").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllTaxiways", "TRUE");
        if(((String)deleteObject.getItem("allBlastFences").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllBlastFences", "TRUE");
        if(((String)deleteObject.getItem("allBoundaryFences").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllBoundaryFences", "TRUE");
        if(((String)deleteObject.getItem("allControlTowers").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllControlTowers", "TRUE");
        if(((String)deleteObject.getItem("allJetways").getDecodedData()).equals("TRUE"))
            deleteElement.setAttribute("deleteAllJetways", "TRUE");
        for( i = 0; i < deleteObject.getObjectAL().size(); i++)
            if(deleteObject.getObjectAL().get(i) instanceof DeleteRunwayObject)
            {
                DeleteRunwayObject runwayObject = (DeleteRunwayObject)deleteObject.getObjectAL().get(i);
                Element deleteRunwayElement = xmlDoc.createElement("DeleteRunway");
                deleteRunwayElement.setAttribute("surface", (String)runwayObject.getItem("surface").getDecodedData());
                deleteRunwayElement.setAttribute("number", (String)runwayObject.getItem("primaryRunway").getDecodedData());
                deleteRunwayElement.setAttribute("designator", (String)runwayObject.getItem("primaryDesignator").getDecodedData());
                deleteElement.appendChild(deleteRunwayElement);
            }

        for( i = 0; i < deleteObject.getObjectAL().size(); i++)
            if(deleteObject.getObjectAL().get(i) instanceof DeleteStartObject)
            {
                DeleteStartObject startObject = (DeleteStartObject)deleteObject.getObjectAL().get(i);
                Element deleteStartElement = xmlDoc.createElement("DeleteStart");
                deleteStartElement.setAttribute("type", (String)startObject.getItem("startType").getDecodedData());
                deleteStartElement.setAttribute("number", (String)startObject.getItem("runwayNumber").getDecodedData());
                deleteStartElement.setAttribute("designator", (String)startObject.getItem("runwayDesignator").getDecodedData());
                deleteElement.appendChild(deleteStartElement);
            }

        for( i = 0; i < deleteObject.getObjectAL().size(); i++)
            if(deleteObject.getObjectAL().get(i) instanceof DeleteFrequencyObject)
            {
                DeleteFrequencyObject frequencyObject = (DeleteFrequencyObject)deleteObject.getObjectAL().get(i);
                Element deleteFrequencyElement = xmlDoc.createElement("DeleteFrequency");
                deleteFrequencyElement.setAttribute("frequency", (new StringBuilder()).append("").append(frequencyObject.getItem("frequency").getDecodedData()).toString());
                deleteFrequencyElement.setAttribute("type", (String)frequencyObject.getItem("type").getDecodedData());
                deleteElement.appendChild(deleteFrequencyElement);
            }

        if(deleteElement.hasAttributes() || deleteElement.hasChildNodes())
            airportElement.appendChild(deleteElement);
    }

    private void writeServices(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        int i = 0;
        do
        {
            if(i >= airportObject.getObjectAL().size())
                break;
            if(airportObject.getObjectAL().get(i) instanceof ServicesObject)
            {
                ServicesObject servicesObject = (ServicesObject)airportObject.getObjectAL().get(i);
                Element servicesElement = xmlDoc.createElement("Services");
                if(!((String)servicesObject.getItem("type73").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "73");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type73").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("type87").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "87");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type87").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("type100").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "100");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type100").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("type130").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "130");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type130").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("type145").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "145");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type145").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeMOGAS").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "MOGAS");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeMOGAS").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJET").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JET");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJET").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJETA").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JETA");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJETA").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJETA1").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JETA1");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJETA1").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJETAP").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JETAP");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJETAP").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJETB").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JETB");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJETB").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("type73").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JET4");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("type73").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJET4").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "JET5");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJET4").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(!((String)servicesObject.getItem("typeJET5").getDecodedData()).equals("NO"))
                {
                    Element fuelElement = xmlDoc.createElement("Fuel");
                    fuelElement.setAttribute("type", "UNKNOWN");
                    fuelElement.setAttribute("availability", (String)servicesObject.getItem("typeJET5").getDecodedData());
                    servicesElement.appendChild(fuelElement);
                }
                if(servicesElement.hasChildNodes())
                    airportElement.appendChild(servicesElement);
                break;
            }
            i++;
        } while(true);
    }

    private void writeAprons(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        Element apronsElement = xmlDoc.createElement("Aprons");
        int apronCount = 0;
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof ApronObject))
                continue;
            ApronObject apronObject = (ApronObject)arrayList.get(i);
            Element apronElement = xmlDoc.createElement("Apron");
            apronElement.setAttribute("surface", (String)apronObject.getItem("surface1").getDecodedData());
            apronElement.setAttribute("drawSurface", (String)apronObject.getItem("drawSurface").getDecodedData());
            apronElement.setAttribute("drawDetail", (String)apronObject.getItem("drawDetail").getDecodedData());
            apronsElement.appendChild(apronElement);
            ArrayList vertexAL = apronObject.getObjectAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexObject vertexObject = (VertexObject)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format((Double)vertexObject.getItem("longitude").getDecodedData()));
                vertexElement.setAttribute("lat", latLonFormat.format((Double)vertexObject.getItem("latitude").getDecodedData()));
                apronElement.appendChild(vertexElement);
            }

            apronCount++;
        }

        if(apronCount > 0)
            airportElement.appendChild(apronsElement);
    }

    private void writeApronEdgeLights(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        Element apronEdgeLightsElement = xmlDoc.createElement("ApronEdgeLights");
label0:
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof ApronEdgeLightObject))
                continue;
            ApronEdgeLightObject apronEdgeLightObject = (ApronEdgeLightObject)arrayList.get(i);
            ArrayList edgeLightAL = apronEdgeLightObject.getObjectAL();
            int j = 0;
            do
            {
                if(j >= edgeLightAL.size())
                    continue label0;
                Element edgeLightsElement = xmlDoc.createElement("EdgeLights");
                apronEdgeLightsElement.appendChild(edgeLightsElement);
                EdgeLightObject edgeLightObject = (EdgeLightObject)edgeLightAL.get(j);
                for(int k = 0; k < edgeLightObject.getObjectAL().size(); k++)
                {
                    VertexObject vertexObject = (VertexObject)edgeLightObject.getObjectAL().get(k);
                    Element vertexElement = xmlDoc.createElement("Vertex");
                    vertexElement.setAttribute("lon", latLonFormat.format((Double)vertexObject.getItem("longitude").getDecodedData()));
                    vertexElement.setAttribute("lat", latLonFormat.format((Double)vertexObject.getItem("latitude").getDecodedData()));
                    edgeLightsElement.appendChild(vertexElement);
                }

                j++;
            } while(true);
        }

        if(apronEdgeLightsElement.hasChildNodes())
            airportElement.appendChild(apronEdgeLightsElement);
    }

    private void writeBlastFences(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof BlastFenceObject))
                continue;
            BlastFenceObject fenceObject = (BlastFenceObject)arrayList.get(i);
            Element blastFenceElement = xmlDoc.createElement("BlastFence");
            blastFenceElement.setAttribute("profile", (new StringBuilder()).append("{").append((String)fenceObject.getItem("profile").getDecodedData()).append("}").toString());
            airportElement.appendChild(blastFenceElement);
            ArrayList vertexAL = fenceObject.getObjectAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexObject vertexObject = (VertexObject)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format((Double)vertexObject.getItem("longitude").getDecodedData()));
                vertexElement.setAttribute("lat", latLonFormat.format((Double)vertexObject.getItem("latitude").getDecodedData()));
                blastFenceElement.appendChild(vertexElement);
            }

        }

    }

    private void writeBoundaryFences(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof BoundaryFenceObject))
                continue;
            BoundaryFenceObject fenceObject = (BoundaryFenceObject)arrayList.get(i);
            Element boundaryFenceElement = xmlDoc.createElement("BoundaryFence");
            boundaryFenceElement.setAttribute("profile", (new StringBuilder()).append("{").append((String)fenceObject.getItem("profile").getDecodedData()).append("}").toString());
            airportElement.appendChild(boundaryFenceElement);
            ArrayList vertexAL = fenceObject.getObjectAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexObject vertexObject = (VertexObject)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format((Double)vertexObject.getItem("longitude").getDecodedData()));
                vertexElement.setAttribute("lat", latLonFormat.format((Double)vertexObject.getItem("latitude").getDecodedData()));
                boundaryFenceElement.appendChild(vertexElement);
            }

        }

    }

    private void writeHelipads(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
            if(arrayList.get(i) instanceof HelipadObject)
            {
                HelipadObject helipadObject = (HelipadObject)arrayList.get(i);
                Element helipadElement = xmlDoc.createElement("Helipad");
                helipadElement.setAttribute("lat", latLonFormat.format((Double)helipadObject.getItem("latitude").getDecodedData()));
                helipadElement.setAttribute("lon", latLonFormat.format((Double)helipadObject.getItem("longitude").getDecodedData()));
                helipadElement.setAttribute("alt", (new StringBuilder()).append(helipadObject.getItem("altitude").getDecodedData()).append("M").toString());
                helipadElement.setAttribute("surface", (String)helipadObject.getItem("surface").getDecodedData());
                helipadElement.setAttribute("heading", (new StringBuilder()).append("").append(helipadObject.getItem("heading").getDecodedData()).toString());
                helipadElement.setAttribute("length", (new StringBuilder()).append(helipadObject.getItem("length").getDecodedData()).append("M").toString());
                helipadElement.setAttribute("width", (new StringBuilder()).append(helipadObject.getItem("width").getDecodedData()).append("M").toString());
                helipadElement.setAttribute("type", (String)helipadObject.getItem("helipadType").getDecodedData());
                helipadElement.setAttribute("closed", (String)helipadObject.getItem("closed").getDecodedData());
                helipadElement.setAttribute("transparent", (String)helipadObject.getItem("transparent").getDecodedData());
                airportElement.appendChild(helipadElement);
            }

    }

    private void writeJetways(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof JetwayObject))
                continue;
            JetwayObject jetwayObject = (JetwayObject)arrayList.get(i);
            Element jetwayElement = xmlDoc.createElement("Jetway");
            airportElement.appendChild(jetwayElement);
            jetwayElement.setAttribute("gateName", (String)jetwayObject.getItem("gateName").getDecodedData());
            jetwayElement.setAttribute("parkingNumber", (new StringBuilder()).append("").append(jetwayObject.getItem("gateNumber").getDecodedData()).toString());
            for(int j = 0; j < jetwayObject.getObjectAL().size(); j++)
                if(jetwayObject.getObjectAL().get(j) instanceof SceneryObject)
                {
                    SceneryObject sceneryObject = (SceneryObject)jetwayObject.getObjectAL().get(j);
                    Element sceneryElement = xmlDoc.createElement("SceneryObject");
                    jetwayElement.appendChild(sceneryElement);
                    sceneryElement.setAttribute("lat", latLonFormat.format((Double)sceneryObject.getItem("latitude").getDecodedData()));
                    sceneryElement.setAttribute("lon", latLonFormat.format((Double)sceneryObject.getItem("longitude").getDecodedData()));
                    sceneryElement.setAttribute("alt", (new StringBuilder()).append(sceneryObject.getItem("altitude").getDecodedData()).append("M").toString());
                    sceneryElement.setAttribute("altitudeIsAgl", (String)sceneryObject.getItem("isAboveAGL").getDecodedData());
                    sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(sceneryObject.getItem("heading").getDecodedData()).toString());
                    sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(sceneryObject.getItem("pitch").getDecodedData()).toString());
                    sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(sceneryObject.getItem("bank").getDecodedData()).toString());
                    sceneryElement.setAttribute("imageComplexity", (String)sceneryObject.getItem("imageComplexity").getDecodedData());
                    Element libraryElement = xmlDoc.createElement("LibraryObject");
                    sceneryElement.appendChild(libraryElement);
                    libraryElement.setAttribute("name", (new StringBuilder()).append("{").append((String)sceneryObject.getItem("name").getDecodedData()).append("}").toString());
                    libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(sceneryObject.getItem("scale").getDecodedData()).toString());
                }

        }

    }

    private void writeRunways(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof RunwayObject))
                continue;
            RunwayObject runwayObject = (RunwayObject)arrayList.get(i);
            Element runwayElement = xmlDoc.createElement("Runway");
            runwayElement.setAttribute("lat", latLonFormat.format((Double)runwayObject.getItem("latitude").getDecodedData()));
            runwayElement.setAttribute("lon", latLonFormat.format((Double)runwayObject.getItem("longitude").getDecodedData()));
            runwayElement.setAttribute("alt", (new StringBuilder()).append(runwayObject.getItem("altitude").getDecodedData()).append("M").toString());
            runwayElement.setAttribute("surface", (String)runwayObject.getItem("surface").getDecodedData());
            runwayElement.setAttribute("heading", (new StringBuilder()).append("").append(runwayObject.getItem("heading").getDecodedData()).toString());
            runwayElement.setAttribute("length", (new StringBuilder()).append(runwayObject.getItem("length").getDecodedData()).append("M").toString());
            runwayElement.setAttribute("width", (new StringBuilder()).append(runwayObject.getItem("width").getDecodedData()).append("M").toString());
            runwayElement.setAttribute("number", (new StringBuilder()).append("").append(runwayObject.getItem("primaryRunwayNumber").getDecodedData()).toString());
            runwayElement.setAttribute("designator", (String)runwayObject.getItem("primaryRunwayDesig").getDecodedData());
            if(((Float)runwayObject.getItem("patternAltitude").getDecodedData()).floatValue() > 0.0F)
                runwayElement.setAttribute("patternAltitude", (new StringBuilder()).append(runwayObject.getItem("patternAltitude").getDecodedData()).append("M").toString());
            runwayElement.setAttribute("primaryTakeoff", (String)runwayObject.getItem("primaryTakeoff").getDecodedData());
            runwayElement.setAttribute("primaryLanding", (String)runwayObject.getItem("primaryLanding").getDecodedData());
            runwayElement.setAttribute("primaryPattern", (String)runwayObject.getItem("primaryPattern").getDecodedData());
            runwayElement.setAttribute("secondaryTakeoff", (String)runwayObject.getItem("secondaryTakeoff").getDecodedData());
            runwayElement.setAttribute("secondaryLanding", (String)runwayObject.getItem("secondaryLanding").getDecodedData());
            runwayElement.setAttribute("secondaryPattern", (String)runwayObject.getItem("secondaryPattern").getDecodedData());
            airportElement.appendChild(runwayElement);
            Element markingsElement = xmlDoc.createElement("Markings");
            markingsElement.setAttribute("alternateThreshold", (String)runwayObject.getItem("altThreshold").getDecodedData());
            markingsElement.setAttribute("alternateTouchdown", (String)runwayObject.getItem("altTouchdown").getDecodedData());
            markingsElement.setAttribute("alternateFixedDistance", (String)runwayObject.getItem("altFixedDistance").getDecodedData());
            markingsElement.setAttribute("alternatePrecision", (String)runwayObject.getItem("altPrecision").getDecodedData());
            markingsElement.setAttribute("leadingZeroIdent", (String)runwayObject.getItem("leadingZeroIdent").getDecodedData());
            markingsElement.setAttribute("noThresholdEndArrows", (String)runwayObject.getItem("noThesholdEndArrows").getDecodedData());
            markingsElement.setAttribute("edges", (String)runwayObject.getItem("edges").getDecodedData());
            markingsElement.setAttribute("threshold", (String)runwayObject.getItem("threshold").getDecodedData());
            markingsElement.setAttribute("fixedDistance", (String)runwayObject.getItem("fixedDistance").getDecodedData());
            markingsElement.setAttribute("touchdown", (String)runwayObject.getItem("touchdown").getDecodedData());
            markingsElement.setAttribute("dashes", (String)runwayObject.getItem("dashes").getDecodedData());
            markingsElement.setAttribute("ident", (String)runwayObject.getItem("identDisplayed").getDecodedData());
            markingsElement.setAttribute("precision", (String)runwayObject.getItem("precision").getDecodedData());
            markingsElement.setAttribute("edgePavement", (String)runwayObject.getItem("edgePavement").getDecodedData());
            markingsElement.setAttribute("singleEnd", (String)runwayObject.getItem("singleEnd").getDecodedData());
            markingsElement.setAttribute("primaryClosed", (String)runwayObject.getItem("primaryClosed").getDecodedData());
            markingsElement.setAttribute("secondaryClosed", (String)runwayObject.getItem("secondaryClosed").getDecodedData());
            markingsElement.setAttribute("primaryStol", (String)runwayObject.getItem("primaryStol").getDecodedData());
            markingsElement.setAttribute("secondaryStol", (String)runwayObject.getItem("secondaryStol").getDecodedData());
            runwayElement.appendChild(markingsElement);
            Element lightsElement = xmlDoc.createElement("Lights");
            lightsElement.setAttribute("center", (String)runwayObject.getItem("centerLights").getDecodedData());
            lightsElement.setAttribute("edge", (String)runwayObject.getItem("edgeLights").getDecodedData());
            lightsElement.setAttribute("centerRed", (String)runwayObject.getItem("centerRed").getDecodedData());
            runwayElement.appendChild(lightsElement);
            for(int j = 0; j < runwayObject.getObjectAL().size(); j++)
            {
                if(!(runwayObject.getObjectAL().get(j) instanceof RunwayDetailObject))
                    continue;
                RunwayDetailObject detailObject = (RunwayDetailObject)runwayObject.getObjectAL().get(j);
                Element detailElement = null;
                if(detailObject.getName().equals("primaryOffset"))
                {
                    detailElement = xmlDoc.createElement("OffsetThreshold");
                    detailElement.setAttribute("end", "PRIMARY");
                } else
                if(detailObject.getName().equals("secondaryOffset"))
                {
                    detailElement = xmlDoc.createElement("OffsetThreshold");
                    detailElement.setAttribute("end", "SECONDARY");
                } else
                if(detailObject.getName().equals("primaryBlastPad"))
                {
                    detailElement = xmlDoc.createElement("BlastPad");
                    detailElement.setAttribute("end", "PRIMARY");
                } else
                if(detailObject.getName().equals("secondaryBlastPad"))
                {
                    detailElement = xmlDoc.createElement("BlastPad");
                    detailElement.setAttribute("end", "SECONDARY");
                } else
                if(detailObject.getName().equals("primaryOverrun"))
                {
                    detailElement = xmlDoc.createElement("Overrun");
                    detailElement.setAttribute("end", "PRIMARY");
                } else
                if(detailObject.getName().equals("secondaryOverrun"))
                {
                    detailElement = xmlDoc.createElement("Overrun");
                    detailElement.setAttribute("end", "SECONDARY");
                }
                if(detailElement != null)
                {
                    detailElement.setAttribute("length", (new StringBuilder()).append(detailObject.getItem("length").getDecodedData()).append("M").toString());
                    detailElement.setAttribute("width", (new StringBuilder()).append(detailObject.getItem("width").getDecodedData()).append("M").toString());
                    detailElement.setAttribute("surface", (String)detailObject.getItem("surface").getDecodedData());
                    runwayElement.appendChild(detailElement);
                }
            }

            for(int j = 0; j < runwayObject.getObjectAL().size(); j++)
            {
                if(!(runwayObject.getObjectAL().get(j) instanceof ApproachLightObject))
                    continue;
                ApproachLightObject lightObject = (ApproachLightObject)runwayObject.getObjectAL().get(j);
                Element approachLightsElement = xmlDoc.createElement("ApproachLights");
                if(lightObject.getName().equals("primaryApproachLights"))
                    approachLightsElement.setAttribute("end", "PRIMARY");
                else
                if(lightObject.getName().equals("secondaryApproachLights"))
                    approachLightsElement.setAttribute("end", "SECONDARY");
                approachLightsElement.setAttribute("system", (String)lightObject.getItem("approachSystem").getDecodedData());
                approachLightsElement.setAttribute("strobes", (new StringBuilder()).append("").append(lightObject.getItem("strobes").getDecodedData()).toString());
                approachLightsElement.setAttribute("reil", (String)lightObject.getItem("reil").getDecodedData());
                approachLightsElement.setAttribute("touchdown", (String)lightObject.getItem("touchdown").getDecodedData());
                approachLightsElement.setAttribute("endLights", (String)lightObject.getItem("endLights").getDecodedData());
                runwayElement.appendChild(approachLightsElement);
            }

            for(int j = 0; j < runwayObject.getObjectAL().size(); j++)
            {
                if(!(runwayObject.getObjectAL().get(j) instanceof RunwayVasiObject))
                    continue;
                RunwayVasiObject vasiObject = (RunwayVasiObject)runwayObject.getObjectAL().get(j);
                Element vasiElement = xmlDoc.createElement("Vasi");
                if(vasiObject.getName().equals("primaryLeftVasi"))
                {
                    vasiElement.setAttribute("end", "PRIMARY");
                    vasiElement.setAttribute("side", "LEFT");
                }
                if(vasiObject.getName().equals("primaryRightVasi"))
                {
                    vasiElement.setAttribute("end", "PRIMARY");
                    vasiElement.setAttribute("side", "RIGHT");
                }
                if(vasiObject.getName().equals("secondaryLeftVasi"))
                {
                    vasiElement.setAttribute("end", "SECONDARY");
                    vasiElement.setAttribute("side", "LEFT");
                }
                if(vasiObject.getName().equals("secondaryRightVasi"))
                {
                    vasiElement.setAttribute("end", "SECONDARY");
                    vasiElement.setAttribute("side", "RIGHT");
                }
                vasiElement.setAttribute("type", (String)vasiObject.getItem("type").getDecodedData());
                vasiElement.setAttribute("biasX", (new StringBuilder()).append(vasiObject.getItem("biasX").getDecodedData()).append("M").toString());
                vasiElement.setAttribute("biasZ", (new StringBuilder()).append(vasiObject.getItem("biasZ").getDecodedData()).append("M").toString());
                vasiElement.setAttribute("spacing", (new StringBuilder()).append(vasiObject.getItem("spacing").getDecodedData()).append("M").toString());
                vasiElement.setAttribute("pitch", (new StringBuilder()).append("").append(vasiObject.getItem("pitch").getDecodedData()).toString());
                runwayElement.appendChild(vasiElement);
            }

            for(int j = runwayObject.getObjectAL().size() - 1; j >= 0; j--)
            {
                if(!(runwayObject.getObjectAL().get(j) instanceof VORILSObject))
                    continue;
                VORILSObject ilsObject = (VORILSObject)runwayObject.getObjectAL().get(j);
                Element ilsElement = xmlDoc.createElement("Ils");
                ilsElement.setAttribute("lat", latLonFormat.format((Double)ilsObject.getItem("latitude").getDecodedData()));
                ilsElement.setAttribute("lon", latLonFormat.format((Double)ilsObject.getItem("longitude").getDecodedData()));
                ilsElement.setAttribute("alt", (new StringBuilder()).append(ilsObject.getItem("altitude").getDecodedData()).append("M").toString());
                ilsElement.setAttribute("frequency", (new StringBuilder()).append("").append(ilsObject.getItem("frequency").getDecodedData()).toString());
                ilsElement.setAttribute("end", (String)ilsObject.getItem("end").getDecodedData());
                ilsElement.setAttribute("range", (new StringBuilder()).append(ilsObject.getItem("range").getDecodedData()).append("M").toString());
                ilsElement.setAttribute("magvar", (new StringBuilder()).append("").append(ilsObject.getItem("magVar").getDecodedData()).toString());
                ilsElement.setAttribute("ident", (String)ilsObject.getItem("icao").getDecodedData());
                ilsElement.setAttribute("backCourse", (String)ilsObject.getItem("backCourse").getDecodedData());
                for(int k = ilsObject.getObjectAL().size() - 1; k >= 0; k--)
                    if(ilsObject.getObjectAL().get(k) instanceof LocalizerObject)
                    {
                        LocalizerObject localizerObject = (LocalizerObject)ilsObject.getObjectAL().get(k);
                        ilsElement.setAttribute("heading", (new StringBuilder()).append("").append(localizerObject.getItem("heading").getDecodedData()).toString());
                        ilsElement.setAttribute("width", (new StringBuilder()).append("").append(localizerObject.getItem("width").getDecodedData()).toString());
                    }

                for(int k = ilsObject.getObjectAL().size() - 1; k >= 0; k--)
                {
                    if(!(ilsObject.getObjectAL().get(k) instanceof NameObject))
                        continue;
                    NameObject nameObject = (NameObject)ilsObject.getObjectAL().get(k);
                    if(((String)nameObject.getItem("name").getDecodedData()).length() > 0)
                        ilsElement.setAttribute("name", (String)nameObject.getItem("name").getDecodedData());
                }

                runwayElement.appendChild(ilsElement);
                for(int k = ilsObject.getObjectAL().size() - 1; k >= 0; k--)
                    if(ilsObject.getObjectAL().get(k) instanceof GlideSlopeObject)
                    {
                        GlideSlopeObject gsObject = (GlideSlopeObject)ilsObject.getObjectAL().get(k);
                        Element glideSlopeElement = xmlDoc.createElement("GlideSlope");
                        glideSlopeElement.setAttribute("lat", latLonFormat.format((Double)gsObject.getItem("latitude").getDecodedData()));
                        glideSlopeElement.setAttribute("lon", latLonFormat.format((Double)gsObject.getItem("longitude").getDecodedData()));
                        glideSlopeElement.setAttribute("alt", (new StringBuilder()).append(gsObject.getItem("altitude").getDecodedData()).append("M").toString());
                        glideSlopeElement.setAttribute("pitch", (new StringBuilder()).append("").append(gsObject.getItem("pitch").getDecodedData()).toString());
                        glideSlopeElement.setAttribute("range", (new StringBuilder()).append(gsObject.getItem("range").getDecodedData()).append("M").toString());
                        ilsElement.appendChild(glideSlopeElement);
                    }

                for(int k = ilsObject.getObjectAL().size() - 1; k >= 0; k--)
                    if(ilsObject.getObjectAL().get(k) instanceof DMEObject)
                    {
                        DMEObject dmeObject = (DMEObject)ilsObject.getObjectAL().get(k);
                        Element dmeElement = xmlDoc.createElement("Dme");
                        dmeElement.setAttribute("lat", latLonFormat.format((Double)dmeObject.getItem("latitude").getDecodedData()));
                        dmeElement.setAttribute("lon", latLonFormat.format((Double)dmeObject.getItem("longitude").getDecodedData()));
                        dmeElement.setAttribute("alt", (new StringBuilder()).append("").append(dmeObject.getItem("altitude").getDecodedData()).toString());
                        dmeElement.setAttribute("range", (new StringBuilder()).append("").append(dmeObject.getItem("range").getDecodedData()).toString());
                        ilsElement.appendChild(dmeElement);
                    }

            }

        }

    }

    private void writeStarts(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            if(!(arrayList.get(i) instanceof StartObject))
                continue;
            StartObject startObject = (StartObject)arrayList.get(i);
            Element startElement = xmlDoc.createElement("Start");
            startElement.setAttribute("lat", latLonFormat.format((Double)startObject.getItem("latitude").getDecodedData()));
            startElement.setAttribute("lon", latLonFormat.format((Double)startObject.getItem("longitude").getDecodedData()));
            if(((String)startObject.getItem("startType").getDecodedData()).length() > 0)
                startElement.setAttribute("type", (String)startObject.getItem("startType").getDecodedData());
            startElement.setAttribute("alt", (new StringBuilder()).append(startObject.getItem("altitude").getDecodedData()).append("M").toString());
            startElement.setAttribute("heading", (new StringBuilder()).append("").append(startObject.getItem("heading").getDecodedData()).toString());
            if(((String)startObject.getItem("runwayNumber").getDecodedData()).length() > 0)
                startElement.setAttribute("number", (String)startObject.getItem("runwayNumber").getDecodedData());
            if(((String)startObject.getItem("runwayDesignator").getDecodedData()).length() > 0)
                startElement.setAttribute("designator", (String)startObject.getItem("runwayDesignator").getDecodedData());
            airportElement.appendChild(startElement);
        }

    }

    private void writeTaxiways(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList arrayList = airportObject.getObjectAL();
        for(int i = 0; i < arrayList.size(); i++)
        {
            if(!(arrayList.get(i) instanceof TaxiwayPointsObject))
                continue;
            ArrayList taxiwayAL = ((TaxiwayPointsObject)arrayList.get(i)).getObjectAL();
            for(int j = 0; j < taxiwayAL.size(); j++)
            {
                TaxiwayPointObject tpObject = (TaxiwayPointObject)taxiwayAL.get(j);
                Element taxiwayPointElement = xmlDoc.createElement("TaxiwayPoint");
                taxiwayPointElement.setAttribute("lat", latLonFormat.format((Double)tpObject.getItem("latitude").getDecodedData()));
                taxiwayPointElement.setAttribute("lon", latLonFormat.format((Double)tpObject.getItem("longitude").getDecodedData()));
                taxiwayPointElement.setAttribute("index", (new StringBuilder()).append("").append(j).toString());
                taxiwayPointElement.setAttribute("type", (String)tpObject.getItem("taxiwayType").getDecodedData());
                taxiwayPointElement.setAttribute("orientation", (String)tpObject.getItem("taxiwayOrientation").getDecodedData());
                airportElement.appendChild(taxiwayPointElement);
            }

        }

label0:
        for(int i = 0; i < arrayList.size(); i++)
        {
            if(!(arrayList.get(i) instanceof TaxiwayParkingsObject))
                continue;
            ArrayList taxiwayAL = ((TaxiwayParkingsObject)arrayList.get(i)).getObjectAL();
            int j = 0;
            do
            {
                if(j >= taxiwayAL.size())
                    continue label0;
                TaxiwayParkingObject tpObject = (TaxiwayParkingObject)taxiwayAL.get(j);
                Element taxiwayParkingElement = xmlDoc.createElement("TaxiwayParking");
                taxiwayParkingElement.setAttribute("lat", latLonFormat.format((Double)tpObject.getItem("latitude").getDecodedData()));
                taxiwayParkingElement.setAttribute("lon", latLonFormat.format((Double)tpObject.getItem("longitude").getDecodedData()));
                taxiwayParkingElement.setAttribute("index", (new StringBuilder()).append("").append(j).toString());
                taxiwayParkingElement.setAttribute("type", (String)tpObject.getItem("parkingType").getDecodedData());
                taxiwayParkingElement.setAttribute("name", (String)tpObject.getItem("parkingName").getDecodedData());
                taxiwayParkingElement.setAttribute("heading", (new StringBuilder()).append("").append(tpObject.getItem("heading").getDecodedData()).toString());
                taxiwayParkingElement.setAttribute("radius", (new StringBuilder()).append(tpObject.getItem("radius").getDecodedData()).append("M").toString());
                taxiwayParkingElement.setAttribute("number", (new StringBuilder()).append("").append(tpObject.getItem("number").getDecodedData()).toString());
                if(((Integer)tpObject.getItem("airlineCodeCount").getDecodedData()).intValue() > 0)
                    taxiwayParkingElement.setAttribute("airlineCodes", (String)tpObject.getItem("airlineCodes").getDecodedData());
                taxiwayParkingElement.setAttribute("pushBack", (String)tpObject.getItem("pushBack").getDecodedData());
                taxiwayParkingElement.setAttribute("teeOffset1", (new StringBuilder()).append("").append(tpObject.getItem("teeOffset1").getDecodedData()).toString());
                taxiwayParkingElement.setAttribute("teeOffset2", (new StringBuilder()).append("").append(tpObject.getItem("teeOffset2").getDecodedData()).toString());
                taxiwayParkingElement.setAttribute("teeOffset3", (new StringBuilder()).append("").append(tpObject.getItem("teeOffset3").getDecodedData()).toString());
                taxiwayParkingElement.setAttribute("teeOffset4", (new StringBuilder()).append("").append(tpObject.getItem("teeOffset4").getDecodedData()).toString());
                airportElement.appendChild(taxiwayParkingElement);
                j++;
            } while(true);
        }

label1:
        for(int i = 0; i < arrayList.size(); i++)
        {
            if(!(arrayList.get(i) instanceof TaxiwayPathsObject))
                continue;
            ArrayList taxiwayAL = ((TaxiwayPathsObject)arrayList.get(i)).getObjectAL();
            int j = 0;
            do
            {
                if(j >= taxiwayAL.size())
                    continue label1;
                TaxiwayPathObject tpObject = (TaxiwayPathObject)taxiwayAL.get(j);
                Element taxiwayPathElement = xmlDoc.createElement("TaxiwayPath");
                taxiwayPathElement.setAttribute("type", (String)tpObject.getItem("type").getDecodedData());
                taxiwayPathElement.setAttribute("start", (new StringBuilder()).append("").append(tpObject.getItem("startIndex").getDecodedData()).toString());
                taxiwayPathElement.setAttribute("end", (new StringBuilder()).append("").append(tpObject.getItem("endIndex").getDecodedData()).toString());
                taxiwayPathElement.setAttribute("width", (new StringBuilder()).append(tpObject.getItem("width").getDecodedData()).append("M").toString());
                taxiwayPathElement.setAttribute("weightLimit", (new StringBuilder()).append("").append(tpObject.getItem("weightLimit").getDecodedData()).toString());
                taxiwayPathElement.setAttribute("surface", (String)tpObject.getItem("surface").getDecodedData());
                taxiwayPathElement.setAttribute("centerLine", (String)tpObject.getItem("centerLine").getDecodedData());
                taxiwayPathElement.setAttribute("centerLineLighted", (String)tpObject.getItem("centerLineLighted").getDecodedData());
                taxiwayPathElement.setAttribute("leftEdge", (String)tpObject.getItem("leftEdge").getDecodedData());
                taxiwayPathElement.setAttribute("leftEdgeLighted", (String)tpObject.getItem("leftEdgeLighted").getDecodedData());
                taxiwayPathElement.setAttribute("rightEdge", (String)tpObject.getItem("rightEdge").getDecodedData());
                taxiwayPathElement.setAttribute("rightEdgeLighted", (String)tpObject.getItem("rightEdgeLighted").getDecodedData());
                if(((String)tpObject.getItem("type").getDecodedData()).equals("RUNWAY"))
                {
                    taxiwayPathElement.setAttribute("designator", (String)tpObject.getItem("runwayDesignator").getDecodedData());
                    taxiwayPathElement.setAttribute("number", (new StringBuilder()).append("").append(tpObject.getItem("runwayNumber").getDecodedData()).toString());
                } else
                {
                    taxiwayPathElement.setAttribute("name", (new StringBuilder()).append("").append(tpObject.getItem("taxiName").getDecodedData()).toString());
                }
                airportElement.appendChild(taxiwayPathElement);
                j++;
            } while(true);
        }

        for(int i = 0; i < arrayList.size(); i++)
        {
            if(!(arrayList.get(i) instanceof TaxiNamesObject))
                continue;
            ArrayList taxiwayAL = ((TaxiNamesObject)arrayList.get(i)).getObjectAL();
            for(int j = 0; j < taxiwayAL.size(); j++)
            {
                TaxiNameObject taxiNameObject = (TaxiNameObject)taxiwayAL.get(j);
                Element taxiNameElement = xmlDoc.createElement("TaxiName");
                taxiNameElement.setAttribute("index", (new StringBuilder()).append("").append(j).toString());
                taxiNameElement.setAttribute("name", (String)taxiNameObject.getItem("taxiName").getDecodedData());
                airportElement.appendChild(taxiNameElement);
            }

        }

    }

    private void writeTaxiwaySigns(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
label0:
        for(int i = airportObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(airportObject.getObjectAL().get(i) instanceof TaxiwaySignsObject))
                continue;
            TaxiwaySignsObject taxiwaySignsObject = (TaxiwaySignsObject)airportObject.getObjectAL().get(i);
            double lat = ((Double)taxiwaySignsObject.getItem("latitude").getDecodedData()).doubleValue();
            double lon = ((Double)taxiwaySignsObject.getItem("longitude").getDecodedData()).doubleValue();
            int totalSigns = taxiwaySignsObject.getObjectAL().size();
            int j = 0;
            do
            {
                if(j >= totalSigns)
                    continue label0;
                TaxiwaySignObject taxiwaySignObject = (TaxiwaySignObject)taxiwaySignsObject.getObjectAL().get(j);
                Element taxiwaySignElement = xmlDoc.createElement("TaxiwaySign");
                float lonOffset = ((Float)taxiwaySignObject.getItem("lonOffset").getDecodedData()).floatValue();
                float latOffset = ((Float)taxiwaySignObject.getItem("latOffset").getDecodedData()).floatValue();
                double signLat = lat + (double)latOffset / 111130.55555555556D;
                double signLon = lon + (double)lonOffset / (111319.44444444444D * Math.cos(Math.toRadians(lat)));
                taxiwaySignElement.setAttribute("lat", latLonFormat.format(signLat));
                taxiwaySignElement.setAttribute("lon", latLonFormat.format(signLon));
                taxiwaySignElement.setAttribute("heading", (new StringBuilder()).append("").append(taxiwaySignObject.getItem("heading").getDecodedData()).toString());
                taxiwaySignElement.setAttribute("label", (String)taxiwaySignObject.getItem("label").getDecodedData());
                if(((String)taxiwaySignObject.getItem("justification").getDecodedData()).length() > 0)
                    taxiwaySignElement.setAttribute("justification", (String)taxiwaySignObject.getItem("justification").getDecodedData());
                taxiwaySignElement.setAttribute("size", (String)taxiwaySignObject.getItem("size").getDecodedData());
                airportElement.appendChild(taxiwaySignElement);
                j++;
            } while(true);
        }

    }

    private void writeTowers(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList towerAL = airportObject.getObjectAL();
        Element towerElement = xmlDoc.createElement("Tower");
        towerElement.setAttribute("lat", latLonFormat.format((Double)airportObject.getItem("towerLatitude").getDecodedData()));
        towerElement.setAttribute("lon", latLonFormat.format((Double)airportObject.getItem("towerLongitude").getDecodedData()));
        towerElement.setAttribute("alt", (new StringBuilder()).append(airportObject.getItem("towerAltitude").getDecodedData()).append("M").toString());
        airportElement.appendChild(towerElement);
        for(int i = towerAL.size() - 1; i >= 0; i--)
        {
            if(!(towerAL.get(i) instanceof TowerSceneryObject))
                continue;
            TowerSceneryObject towerObject = (TowerSceneryObject)towerAL.get(i);
            for(int j = 0; j < towerObject.getObjectAL().size(); j++)
                if(towerObject.getObjectAL().get(j) instanceof SceneryObject)
                {
                    SceneryObject sceneryObject = (SceneryObject)towerObject.getObjectAL().get(j);
                    Element sceneryElement = xmlDoc.createElement("SceneryObject");
                    towerElement.appendChild(sceneryElement);
                    sceneryElement.setAttribute("lat", latLonFormat.format((Double)sceneryObject.getItem("latitude").getDecodedData()));
                    sceneryElement.setAttribute("lon", latLonFormat.format((Double)sceneryObject.getItem("longitude").getDecodedData()));
                    sceneryElement.setAttribute("alt", (new StringBuilder()).append(sceneryObject.getItem("altitude").getDecodedData()).append("M").toString());
                    sceneryElement.setAttribute("altitudeIsAgl", (String)sceneryObject.getItem("isAboveAGL").getDecodedData());
                    sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(sceneryObject.getItem("heading").getDecodedData()).toString());
                    sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(sceneryObject.getItem("pitch").getDecodedData()).toString());
                    sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(sceneryObject.getItem("bank").getDecodedData()).toString());
                    sceneryElement.setAttribute("imageComplexity", (String)sceneryObject.getItem("imageComplexity").getDecodedData());
                    Element libraryElement = xmlDoc.createElement("LibraryObject");
                    sceneryElement.appendChild(libraryElement);
                    libraryElement.setAttribute("name", (new StringBuilder()).append("{").append((String)sceneryObject.getItem("name").getDecodedData()).append("}").toString());
                    libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(sceneryObject.getItem("scale").getDecodedData()).toString());
                }

        }

    }

    private void writeApproaches(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        for(int i = airportObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(airportObject.getObjectAL().get(i) instanceof ApproachObject))
                continue;
            ApproachObject approachObject = (ApproachObject)airportObject.getObjectAL().get(i);
            Element approachElement = xmlDoc.createElement("Approach");
            airportElement.appendChild(approachElement);
            approachElement.setAttribute("type", (String)approachObject.getItem("type").getDecodedData());
            approachElement.setAttribute("runway", (String)approachObject.getItem("runwayNumber").getDecodedData());
            approachElement.setAttribute("designator", (String)approachObject.getItem("runwayDesignator").getDecodedData());
            approachElement.setAttribute("suffix", (String)approachObject.getItem("suffix").getDecodedData());
            approachElement.setAttribute("gpsOverlay", (String)approachObject.getItem("gpsOverlay").getDecodedData());
            approachElement.setAttribute("fixType", (String)approachObject.getItem("fixType").getDecodedData());
            approachElement.setAttribute("fixRegion", (String)approachObject.getItem("fixRegion").getDecodedData());
            approachElement.setAttribute("fixIdent", (String)approachObject.getItem("fixIdent").getDecodedData());
            approachElement.setAttribute("altitude", (new StringBuilder()).append("").append(approachObject.getItem("altitude").getDecodedData()).toString());
            approachElement.setAttribute("heading", (new StringBuilder()).append("").append(approachObject.getItem("heading").getDecodedData()).toString());
            approachElement.setAttribute("missedAltitude", (new StringBuilder()).append("").append(approachObject.getItem("missedAltitude").getDecodedData()).toString());
            for(int j = 0; j < approachObject.getObjectAL().size(); j++)
            {
                if(!(approachObject.getObjectAL().get(j) instanceof ApproachLegObject))
                    continue;
                ApproachLegObject approachLegObject = (ApproachLegObject)approachObject.getObjectAL().get(j);
                Element approachLegsElement = xmlDoc.createElement("ApproachLegs");
                approachElement.appendChild(approachLegsElement);
                for(int k = 0; k < approachLegObject.getObjectAL().size(); k++)
                    if(approachLegObject.getObjectAL().get(k) instanceof LegObject)
                    {
                        LegObject legObject = (LegObject)approachLegObject.getObjectAL().get(k);
                        writeApproachLeg(legObject, xmlDoc, approachLegsElement);
                    }

            }

            for(int j = 0; j < approachObject.getObjectAL().size(); j++)
            {
                if(!(approachObject.getObjectAL().get(j) instanceof MissedApproachLegObject))
                    continue;
                MissedApproachLegObject missedLegObject = (MissedApproachLegObject)approachObject.getObjectAL().get(j);
                Element missedLegsElement = xmlDoc.createElement("MissedApproachLegs");
                approachElement.appendChild(missedLegsElement);
                for(int k = 0; k < missedLegObject.getObjectAL().size(); k++)
                    if(missedLegObject.getObjectAL().get(k) instanceof LegObject)
                    {
                        LegObject legObject = (LegObject)missedLegObject.getObjectAL().get(k);
                        writeApproachLeg(legObject, xmlDoc, missedLegsElement);
                    }

            }

            for(int j = 0; j < approachObject.getObjectAL().size(); j++)
            {
                if(!(approachObject.getObjectAL().get(j) instanceof TransitionObject))
                    continue;
                TransitionObject transitionObject = (TransitionObject)approachObject.getObjectAL().get(j);
                Element transitionElement = xmlDoc.createElement("Transition");
                approachElement.appendChild(transitionElement);
                transitionElement.setAttribute("transitionType", (String)transitionObject.getItem("transitionType").getDecodedData());
                transitionElement.setAttribute("fixType", (String)transitionObject.getItem("fixType").getDecodedData());
                transitionElement.setAttribute("fixRegion", (String)transitionObject.getItem("fixRegion").getDecodedData());
                transitionElement.setAttribute("fixIdent", (String)transitionObject.getItem("fixIdent").getDecodedData());
                transitionElement.setAttribute("altitude", (new StringBuilder()).append(transitionObject.getItem("altitude").getDecodedData()).append("M").toString());
                for(int k = 0; k < transitionObject.getObjectAL().size(); k++)
                    if(transitionObject.getObjectAL().get(k) instanceof DMEArcObject)
                    {
                        DMEArcObject dmeArcObject = (DMEArcObject)transitionObject.getObjectAL().get(k);
                        Element dmeArcElement = xmlDoc.createElement("DmeArc");
                        transitionElement.appendChild(dmeArcElement);
                        dmeArcElement.setAttribute("radial", (new StringBuilder()).append("").append(dmeArcObject.getItem("radial").getDecodedData()).toString());
                        dmeArcElement.setAttribute("distance", (new StringBuilder()).append(dmeArcObject.getItem("distance").getDecodedData()).append("M").toString());
                        dmeArcElement.setAttribute("dmeRegion", (String)dmeArcObject.getItem("dmeRegion").getDecodedData());
                        dmeArcElement.setAttribute("dmeIdent", (String)dmeArcObject.getItem("dmeIdent").getDecodedData());
                    }

                for(int k = 0; k < transitionObject.getObjectAL().size(); k++)
                {
                    if(!(transitionObject.getObjectAL().get(k) instanceof TransitionLegObject))
                        continue;
                    TransitionLegObject transitionLegObject = (TransitionLegObject)transitionObject.getObjectAL().get(k);
                    Element transitionLegsElement = xmlDoc.createElement("TransitionLegs");
                    transitionElement.appendChild(transitionLegsElement);
                    for(int h = 0; h < transitionLegObject.getObjectAL().size(); h++)
                        if(transitionLegObject.getObjectAL().get(h) instanceof LegObject)
                        {
                            LegObject legObject = (LegObject)transitionLegObject.getObjectAL().get(h);
                            writeApproachLeg(legObject, xmlDoc, transitionLegsElement);
                        }

                }

            }

        }

    }

    private void writeApproachLeg(LegObject legObject, Document xmlDoc, Element approachElement)
    {
        Element legElement = xmlDoc.createElement("Leg");
        approachElement.appendChild(legElement);
        String legType = (String)legObject.getItem("legID").getDecodedData();
        legElement.setAttribute("type", legType);
        if(LegTypeEngine.getInstance().getFixTypeHM().containsKey(legType))
            if(((String)LegTypeEngine.getInstance().getFixTypeHM().get(legType)).equals("R"))
                legElement.setAttribute("fixType", (String)legObject.getItem("fixType").getDecodedData());
            else
            if(((String)legObject.getItem("fixType").getDecodedData()).length() > 0)
                legElement.setAttribute("fixType", (String)legObject.getItem("fixType").getDecodedData());
        if(LegTypeEngine.getInstance().getFixRegionHM().containsKey(legType))
            if(((String)LegTypeEngine.getInstance().getFixRegionHM().get(legType)).equals("R"))
                legElement.setAttribute("fixRegion", (String)legObject.getItem("fixRegion").getDecodedData());
            else
            if(((String)legObject.getItem("fixRegion").getDecodedData()).length() > 0)
                legElement.setAttribute("fixRegion", (String)legObject.getItem("fixRegion").getDecodedData());
        if(LegTypeEngine.getInstance().getFixIdentHM().containsKey(legType))
            if(((String)LegTypeEngine.getInstance().getFixIdentHM().get(legType)).equals("R"))
                legElement.setAttribute("fixIdent", (String)legObject.getItem("fixIdent").getDecodedData());
            else
            if(((String)legObject.getItem("fixIdent").getDecodedData()).length() > 0)
                legElement.setAttribute("fixIdent", (String)legObject.getItem("fixIdent").getDecodedData());
        if(LegTypeEngine.getInstance().getFlyOverHM().containsKey(legType))
            legElement.setAttribute("flyOver", (String)legObject.getItem("flyOver").getDecodedData());
        if(LegTypeEngine.getInstance().getTurnDirectionHM().containsKey(legType))
        {
            boolean leftTurn = ((String)legObject.getItem("turnLeft").getDecodedData()).equals("TRUE");
            boolean rightTurn = ((String)legObject.getItem("turnRight").getDecodedData()).equals("TRUE");
            if(leftTurn && rightTurn)
                legElement.setAttribute("turnDirection", "E");
            else
            if(leftTurn && !rightTurn)
                legElement.setAttribute("turnDirection", "L");
            else
            if(!leftTurn && rightTurn)
                legElement.setAttribute("turnDirection", "R");
        }
        if(LegTypeEngine.getInstance().getRecommendedTypeHM().containsKey(legType) && ((String)legObject.getItem("recommendedType").getDecodedData()).length() > 0)
            legElement.setAttribute("recommendedType", (String)legObject.getItem("recommendedType").getDecodedData());
        if(LegTypeEngine.getInstance().getRecommendedRegionHM().containsKey(legType) && ((String)legObject.getItem("recommendedRegion").getDecodedData()).length() > 0)
            legElement.setAttribute("recommendedRegion", (String)legObject.getItem("recommendedRegion").getDecodedData());
        if(LegTypeEngine.getInstance().getRecommendedIdentHM().containsKey(legType) && ((String)legObject.getItem("recommendedIdent").getDecodedData()).length() > 0)
            legElement.setAttribute("recommendedIdent", (String)legObject.getItem("recommendedIdent").getDecodedData());
        if(LegTypeEngine.getInstance().getThetaHM().containsKey(legType))
            legElement.setAttribute("theta", (new StringBuilder()).append("").append(legObject.getItem("theta").getDecodedData()).toString());
        if(LegTypeEngine.getInstance().getRhoHM().containsKey(legType))
            legElement.setAttribute("rho", (new StringBuilder()).append(legObject.getItem("rho").getDecodedData()).append("M").toString());
        if(LegTypeEngine.getInstance().getTrueCourseHM().containsKey(legType) && ((String)legObject.getItem("courseType").getDecodedData()).equals("TRUE") && ((Float)legObject.getItem("course").getDecodedData()).floatValue() >= 0.0F)
            legElement.setAttribute("trueCourse", (new StringBuilder()).append("").append(legObject.getItem("course").getDecodedData()).toString());
        if(LegTypeEngine.getInstance().getMagneticCourseHM().containsKey(legType) && ((String)legObject.getItem("courseType").getDecodedData()).equals("MAGNETIC") && ((Float)legObject.getItem("course").getDecodedData()).floatValue() >= 0.0F)
            legElement.setAttribute("magneticCourse", (new StringBuilder()).append("").append(legObject.getItem("course").getDecodedData()).toString());
        if(LegTypeEngine.getInstance().getDistanceHM().containsKey(legType) && ((String)legObject.getItem("distanceTime").getDecodedData()).equals("DISTANCE"))
            legElement.setAttribute("distance", (new StringBuilder()).append(legObject.getItem("distanceTimeMeasure").getDecodedData()).append("M").toString());
        if(LegTypeEngine.getInstance().getTimeHM().containsKey(legType) && ((String)legObject.getItem("distanceTime").getDecodedData()).equals("TIME"))
            legElement.setAttribute("time", (new StringBuilder()).append("").append(legObject.getItem("distanceTimeMeasure").getDecodedData()).toString());
        if(LegTypeEngine.getInstance().getAltitudeDescriptorHM().containsKey(legType) && ((String)legObject.getItem("altDescriptor").getDecodedData()).length() > 0)
            legElement.setAttribute("altitudeDescriptor", (String)legObject.getItem("altDescriptor").getDecodedData());
        if(LegTypeEngine.getInstance().getAltitude1HM().containsKey(legType))
            legElement.setAttribute("altitude1", (new StringBuilder()).append(legObject.getItem("altitude1").getDecodedData()).append("M").toString());
        if(LegTypeEngine.getInstance().getAltitude2HM().containsKey(legType))
            legElement.setAttribute("altitude2", (new StringBuilder()).append(legObject.getItem("altitude2").getDecodedData()).append("M").toString());
    }

    private void writeCOMs(AirportObject airportObject, Element airportElement, Document xmlDoc)
    {
        ArrayList comAL = airportObject.getObjectAL();
        for(int i = comAL.size() - 1; i >= 0; i--)
            if(comAL.get(i) instanceof ComObject)
            {
                ComObject comObject = (ComObject)comAL.get(i);
                Element comElement = xmlDoc.createElement("Com");
                comElement.setAttribute("frequency", (new StringBuilder()).append("").append(comObject.getItem("comFrequency").getDecodedData()).toString());
                comElement.setAttribute("type", (String)comObject.getItem("comType").getDecodedData());
                comElement.setAttribute("name", (String)comObject.getItem("comName").getDecodedData());
                airportElement.appendChild(comElement);
            }

    }

    private void writeMarkers(FileObject fileObject, Element fsDataElement, Document xmlDoc)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(fileObject.getObjectAL().get(i) instanceof MarkerObject))
                continue;
            MarkerObject markerObject = (MarkerObject)fileObject.getObjectAL().get(i);
            Element markerElement = xmlDoc.createElement("Marker");
            markerElement.setAttribute("lat", latLonFormat.format((Double)markerObject.getItem("latitude").getDecodedData()));
            markerElement.setAttribute("lon", latLonFormat.format((Double)markerObject.getItem("longitude").getDecodedData()));
            markerElement.setAttribute("alt", (new StringBuilder()).append(markerObject.getItem("altitude").getDecodedData()).append("M").toString());
            markerElement.setAttribute("type", (String)markerObject.getItem("type").getDecodedData());
            markerElement.setAttribute("heading", (new StringBuilder()).append("").append(markerObject.getItem("heading").getDecodedData()).toString());
            if(((String)markerObject.getItem("region").getDecodedData()).length() > 0)
                markerElement.setAttribute("region", (String)markerObject.getItem("region").getDecodedData());
            if(((String)markerObject.getItem("icao").getDecodedData()).length() > 0)
                markerElement.setAttribute("ident", (String)markerObject.getItem("icao").getDecodedData());
            fsDataElement.appendChild(markerElement);
        }

    }

    private void writeNDBs(BaseObject baseObject, Element parentElement, Document xmlDoc)
    {
        for(int i = baseObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(baseObject.getObjectAL().get(i) instanceof NDBObject))
                continue;
            NDBObject ndbObject = (NDBObject)baseObject.getObjectAL().get(i);
            Element ndbElement = xmlDoc.createElement("Ndb");
            ndbElement.setAttribute("lat", latLonFormat.format((Double)ndbObject.getItem("latitude").getDecodedData()));
            ndbElement.setAttribute("lon", latLonFormat.format((Double)ndbObject.getItem("longitude").getDecodedData()));
            ndbElement.setAttribute("alt", (new StringBuilder()).append(ndbObject.getItem("altitude").getDecodedData()).append("M").toString());
            ndbElement.setAttribute("type", (String)ndbObject.getItem("type").getDecodedData());
            ndbElement.setAttribute("frequency", (new StringBuilder()).append("").append(ndbObject.getItem("frequency").getDecodedData()).toString());
            ndbElement.setAttribute("range", (new StringBuilder()).append(ndbObject.getItem("range").getDecodedData()).append("M").toString());
            ndbElement.setAttribute("magvar", (new StringBuilder()).append("").append(ndbObject.getItem("magVar").getDecodedData()).toString());
            if(((String)ndbObject.getItem("region").getDecodedData()).length() > 0)
                ndbElement.setAttribute("region", (String)ndbObject.getItem("region").getDecodedData());
            ndbElement.setAttribute("ident", (String)ndbObject.getItem("icao").getDecodedData());
            for(int j = ndbObject.getObjectAL().size() - 1; j >= 0; j--)
            {
                if(!(ndbObject.getObjectAL().get(j) instanceof NameObject))
                    continue;
                NameObject nameObject = (NameObject)ndbObject.getObjectAL().get(j);
                if(((String)nameObject.getItem("name").getDecodedData()).length() > 0)
                    ndbElement.setAttribute("name", (String)nameObject.getItem("name").getDecodedData());
            }

            parentElement.appendChild(ndbElement);
        }

    }

    private void writeVORs(FileObject fileObject, Element fsDataElement, Document xmlDoc)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(fileObject.getObjectAL().get(i) instanceof VORILSObject))
                continue;
            VORILSObject vorILSObject = (VORILSObject)fileObject.getObjectAL().get(i);
            if(((String)vorILSObject.getItem("type").getDecodedData()).equals("ILS"))
                continue;
            Element vorElement = xmlDoc.createElement("Vor");
            vorElement.setAttribute("dmeOnly", (String)vorILSObject.getItem("dmeOnly").getDecodedData());
            vorElement.setAttribute("dme", (String)vorILSObject.getItem("dme").getDecodedData());
            vorElement.setAttribute("lat", latLonFormat.format((Double)vorILSObject.getItem("latitude").getDecodedData()));
            vorElement.setAttribute("lon", latLonFormat.format((Double)vorILSObject.getItem("longitude").getDecodedData()));
            vorElement.setAttribute("alt", (new StringBuilder()).append(vorILSObject.getItem("altitude").getDecodedData()).append("M").toString());
            vorElement.setAttribute("type", (String)vorILSObject.getItem("type").getDecodedData());
            vorElement.setAttribute("frequency", (new StringBuilder()).append("").append(vorILSObject.getItem("frequency").getDecodedData()).toString());
            vorElement.setAttribute("range", (new StringBuilder()).append(vorILSObject.getItem("range").getDecodedData()).append("M").toString());
            vorElement.setAttribute("magvar", (new StringBuilder()).append("").append(vorILSObject.getItem("magVar").getDecodedData()).toString());
            if(((String)vorILSObject.getItem("region").getDecodedData()).length() > 0)
                vorElement.setAttribute("region", (String)vorILSObject.getItem("region").getDecodedData());
            vorElement.setAttribute("ident", (String)vorILSObject.getItem("icao").getDecodedData());
            for(int j = vorILSObject.getObjectAL().size() - 1; j >= 0; j--)
            {
                if(!(vorILSObject.getObjectAL().get(j) instanceof NameObject))
                    continue;
                NameObject nameObject = (NameObject)vorILSObject.getObjectAL().get(j);
                if(((String)nameObject.getItem("name").getDecodedData()).length() > 0)
                    vorElement.setAttribute("name", (String)nameObject.getItem("name").getDecodedData());
            }

            if(((String)vorILSObject.getItem("dme").getDecodedData()).equals("TRUE"))
            {
                for(int j = vorILSObject.getObjectAL().size() - 1; j >= 0; j--)
                    if(vorILSObject.getObjectAL().get(j) instanceof DMEObject)
                    {
                        DMEObject dmeObject = (DMEObject)vorILSObject.getObjectAL().get(j);
                        Element dmeElement = xmlDoc.createElement("Dme");
                        dmeElement.setAttribute("lat", latLonFormat.format((Double)dmeObject.getItem("latitude").getDecodedData()));
                        dmeElement.setAttribute("lon", latLonFormat.format((Double)dmeObject.getItem("longitude").getDecodedData()));
                        dmeElement.setAttribute("alt", (new StringBuilder()).append(dmeObject.getItem("altitude").getDecodedData()).append("M").toString());
                        dmeElement.setAttribute("range", (new StringBuilder()).append(dmeObject.getItem("range").getDecodedData()).append("M").toString());
                        vorElement.appendChild(dmeElement);
                    }

            }
            fsDataElement.appendChild(vorElement);
        }

    }

    private void writeWindsocks(FileObject fileObject, Element fsDataElement, Document xmlDoc)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
            if(fileObject.getObjectAL().get(i) instanceof WindsockObject)
            {
                WindsockObject windsockObject = (WindsockObject)fileObject.getObjectAL().get(i);
                Element sceneryElement = xmlDoc.createElement("SceneryObject");
                fsDataElement.appendChild(sceneryElement);
                sceneryElement.setAttribute("lat", latLonFormat.format((Double)windsockObject.getItem("latitude").getDecodedData()));
                sceneryElement.setAttribute("lon", latLonFormat.format((Double)windsockObject.getItem("longitude").getDecodedData()));
                sceneryElement.setAttribute("alt", (new StringBuilder()).append(windsockObject.getItem("altitude").getDecodedData()).append("M").toString());
                sceneryElement.setAttribute("altitudeIsAgl", "TRUE");
                sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(windsockObject.getItem("heading").getDecodedData()).toString());
                sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(windsockObject.getItem("pitch").getDecodedData()).toString());
                sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(windsockObject.getItem("bank").getDecodedData()).toString());
                sceneryElement.setAttribute("imageComplexity", (String)windsockObject.getItem("imageComplexity").getDecodedData());
                Element windsockElement = xmlDoc.createElement("Windsock");
                windsockElement.setAttribute("poleHeight", (new StringBuilder()).append("").append(windsockObject.getItem("poleHeight").getDecodedData()).toString());
                windsockElement.setAttribute("sockLength", (new StringBuilder()).append("").append(windsockObject.getItem("sockLength").getDecodedData()).toString());
                windsockElement.setAttribute("lighted", (String)windsockObject.getItem("lighted").getDecodedData());
                sceneryElement.appendChild(windsockElement);
                Element poleColorElement = xmlDoc.createElement("PoleColor");
                poleColorElement.setAttribute("red", (new StringBuilder()).append("").append(windsockObject.getItem("poleRed").getDecodedData()).toString());
                poleColorElement.setAttribute("blue", (new StringBuilder()).append("").append(windsockObject.getItem("poleBlue").getDecodedData()).toString());
                poleColorElement.setAttribute("green", (new StringBuilder()).append("").append(windsockObject.getItem("poleGreen").getDecodedData()).toString());
                windsockElement.appendChild(poleColorElement);
                Element sockColorElement = xmlDoc.createElement("SockColor");
                sockColorElement.setAttribute("red", (new StringBuilder()).append("").append(windsockObject.getItem("sockRed").getDecodedData()).toString());
                sockColorElement.setAttribute("blue", (new StringBuilder()).append("").append(windsockObject.getItem("sockBlue").getDecodedData()).toString());
                sockColorElement.setAttribute("green", (new StringBuilder()).append("").append(windsockObject.getItem("sockGreen").getDecodedData()).toString());
                windsockElement.appendChild(sockColorElement);
            }

    }

    private void writeTriggers(FileObject fileObject, Element fsDataElement, Document xmlDoc)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
        {
            if(!(fileObject.getObjectAL().get(i) instanceof TriggerObject))
                continue;
            TriggerObject triggerObject = (TriggerObject)fileObject.getObjectAL().get(i);
            if(!((String)triggerObject.getItem("type").getDecodedData()).equals("REFUEL_REPAIR"))
                continue;
            Element sceneryElement = xmlDoc.createElement("SceneryObject");
            fsDataElement.appendChild(sceneryElement);
            sceneryElement.setAttribute("lat", latLonFormat.format((Double)triggerObject.getItem("latitude").getDecodedData()));
            sceneryElement.setAttribute("lon", latLonFormat.format((Double)triggerObject.getItem("longitude").getDecodedData()));
            sceneryElement.setAttribute("alt", (new StringBuilder()).append(triggerObject.getItem("altitude").getDecodedData()).append("M").toString());
            sceneryElement.setAttribute("altitudeIsAgl", (String)triggerObject.getItem("isAboveAGL").getDecodedData());
            sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(triggerObject.getItem("heading").getDecodedData()).toString());
            sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(triggerObject.getItem("pitch").getDecodedData()).toString());
            sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(triggerObject.getItem("bank").getDecodedData()).toString());
            sceneryElement.setAttribute("imageComplexity", (String)triggerObject.getItem("imageComplexity").getDecodedData());
            FuelTriggerObject fuelTriggerObject = null;
            int j = 0;
            do
            {
                if(j >= triggerObject.getObjectAL().size())
                    break;
                if(triggerObject.getObjectAL().get(j) instanceof FuelTriggerObject)
                {
                    fuelTriggerObject = (FuelTriggerObject)triggerObject.getObjectAL().get(j);
                    break;
                }
                j++;
            } while(true);
            Element triggerElement = xmlDoc.createElement("Trigger");
            triggerElement.setAttribute("type", "REFUEL_REPAIR");
            sceneryElement.appendChild(triggerElement);
            if(!((String)fuelTriggerObject.getItem("type73").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "73");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type73").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("type87").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "87");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type87").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("type100").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "100");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type100").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("type130").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "130");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type130").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("type145").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "145");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type145").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeMOGAS").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "MOGAS");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeMOGAS").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJET").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJET").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJETA").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETA");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJETA").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJETA1").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETA1");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJETA1").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJETAP").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETAP");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJETAP").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJETB").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETB");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJETB").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("type73").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET4");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("type73").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJET4").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET5");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJET4").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            if(!((String)fuelTriggerObject.getItem("typeJET5").getDecodedData()).equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "UNKNOWN");
                fuelElement.setAttribute("availability", (String)fuelTriggerObject.getItem("typeJET5").getDecodedData());
                triggerElement.appendChild(fuelElement);
            }
            for( j = 0; j < fuelTriggerObject.getObjectAL().size(); j++)
            {
                VertexObject vertexObject = (VertexObject)fuelTriggerObject.getObjectAL().get(j);
                Element vertexElement = xmlDoc.createElement("Vertex");
                triggerElement.appendChild(vertexElement);
                vertexElement.setAttribute("biasX", (new StringBuilder()).append("").append(vertexObject.getItem("biasX").getDecodedData()).toString());
                vertexElement.setAttribute("biasZ", (new StringBuilder()).append("").append(vertexObject.getItem("biasZ").getDecodedData()).toString());
            }

        }

    }

    private void writeScenery(FileObject fileObject, Element fsDataElement, Document xmlDoc)
    {
        for(int i = fileObject.getObjectAL().size() - 1; i >= 0; i--)
            if(fileObject.getObjectAL().get(i) instanceof SceneryObject)
            {
                SceneryObject sceneryObject = (SceneryObject)fileObject.getObjectAL().get(i);
                Element sceneryElement = xmlDoc.createElement("SceneryObject");
                fsDataElement.appendChild(sceneryElement);
                sceneryElement.setAttribute("lat", latLonFormat.format((Double)sceneryObject.getItem("latitude").getDecodedData()));
                sceneryElement.setAttribute("lon", latLonFormat.format((Double)sceneryObject.getItem("longitude").getDecodedData()));
                sceneryElement.setAttribute("alt", (new StringBuilder()).append(sceneryObject.getItem("altitude").getDecodedData()).append("M").toString());
                sceneryElement.setAttribute("altitudeIsAgl", (String)sceneryObject.getItem("isAboveAGL").getDecodedData());
                sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(sceneryObject.getItem("heading").getDecodedData()).toString());
                sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(sceneryObject.getItem("pitch").getDecodedData()).toString());
                sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(sceneryObject.getItem("bank").getDecodedData()).toString());
                sceneryElement.setAttribute("imageComplexity", (String)sceneryObject.getItem("imageComplexity").getDecodedData());
                Element libraryElement = xmlDoc.createElement("LibraryObject");
                sceneryElement.appendChild(libraryElement);
                libraryElement.setAttribute("name", (new StringBuilder()).append("{").append((String)sceneryObject.getItem("name").getDecodedData()).append("}").toString());
                libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(sceneryObject.getItem("scale").getDecodedData()).toString());
            }

    }

    private NumberFormat latLonFormat;
    private static SaveEngine engine = null;

}