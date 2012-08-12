// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SaveEngine.java

package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.comparator.TaxiwayPointElementSort;
import com.zbluesoftware.fsxp.model.*;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            LogEngine, SettingsEngine, XMLEngine, BackgroundImageEngine, 
//            LegTypeEngine

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

    public void saveAirport(AirportModel airportModel, boolean performingSaveAs)
    {
        File file = new File(airportModel.getFileName());
        Document xmlDoc = null;
        Element fsDataElement = null;
        Element airportElement = null;
        if(file.exists() && !performingSaveAs)
            try
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                xmlDoc = builder.parse(file);
                NodeList fsDataElements = xmlDoc.getElementsByTagName("FSData");
                for(int i = 0; i < fsDataElements.getLength(); i++)
                    fsDataElement = (Element)fsDataElements.item(i);

                if(fsDataElement != null)
                {
                    NodeList airportElements = fsDataElement.getElementsByTagName("Airport");
                    int i = 0;
                    do
                    {
                        if(i >= airportElements.getLength())
                            break;
                        if(((Element)airportElements.item(i)).getAttribute("ident").equals(airportModel.getIdent()))
                        {
                            airportElement = (Element)airportElements.item(i);
                            break;
                        }
                        i++;
                    } while(true);
                }
            }
            catch(SAXException saxe)
            {
                LogEngine.getInstance().log(saxe);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            catch(ParserConfigurationException pce)
            {
                LogEngine.getInstance().log(pce);
            }
        else
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
        if(fsDataElement == null)
        {
            fsDataElement = xmlDoc.createElement("FSData");
            fsDataElement.setAttribute("version", "9.0");
            fsDataElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            fsDataElement.setAttribute("xsi:noNamespaceSchemaLocation", "bglcomp.xsd");
            xmlDoc.appendChild(fsDataElement);
            airportElement = null;
        }
        if(SettingsEngine.getInstance().getWriteExcludes())
            writeExclusions(airportModel, xmlDoc, fsDataElement);
        if(airportElement == null)
        {
            airportElement = xmlDoc.createElement("Airport");
            fsDataElement.appendChild(airportElement);
        }
        writeAirport(airportModel, xmlDoc, airportElement, performingSaveAs);
        writeMarkers(airportModel, fsDataElement, xmlDoc, performingSaveAs);
        writeNDBs(airportModel, fsDataElement, xmlDoc, false, performingSaveAs);
        writeVORs(airportModel, fsDataElement, xmlDoc, performingSaveAs);
        writeWindsocks(airportModel, fsDataElement, xmlDoc, performingSaveAs);
        writeTriggers(airportModel, fsDataElement, xmlDoc, performingSaveAs);
        writeScenery(airportModel, fsDataElement, xmlDoc, performingSaveAs);
        XMLEngine.getInstance().writeDocument(xmlDoc, file);
        BackgroundImageEngine.getInstance().saveBGImages(airportModel.getName(), airportModel.getIdent(), airportModel.getBGImageAL());
    }

    private void writeAirport(AirportModel airportModel, Document xmlDoc, Element airportElement, boolean performingSaveAs)
    {
        if(airportModel.getRegion().length() > 0)
            airportElement.setAttribute("region", airportModel.getRegion());
        else
            airportElement.removeAttribute("region");
        if(airportModel.getCountry().length() > 0)
            airportElement.setAttribute("country", airportModel.getCountry());
        else
            airportElement.removeAttribute("country");
        if(airportModel.getState().length() > 0)
            airportElement.setAttribute("state", airportModel.getState());
        else
            airportElement.removeAttribute("state");
        if(airportModel.getCity().length() > 0)
            airportElement.setAttribute("city", airportModel.getCity());
        else
            airportElement.removeAttribute("city");
        if(airportModel.getName().length() > 0)
            airportElement.setAttribute("name", airportModel.getName());
        else
            airportElement.removeAttribute("name");
        airportElement.setAttribute("lat", latLonFormat.format(airportModel.getLatLon().getLat()));
        airportElement.setAttribute("lon", latLonFormat.format(airportModel.getLatLon().getLon()));
        airportElement.setAttribute("alt", (new StringBuilder()).append(airportModel.getAlt()).append(airportModel.getAltMeasure()).toString());
        if(airportModel.getMagvar() != 0.0F)
            airportElement.setAttribute("magvar", (new StringBuilder()).append("").append(airportModel.getMagvar()).toString());
        else
            airportElement.removeAttribute("magvar");
        airportElement.setAttribute("ident", airportModel.getIdent());
        airportElement.setAttribute("airportTestRadius", (new StringBuilder()).append(airportModel.getAirportTestRadius()).append(airportModel.getAirportTestRadiusMeasure()).toString());
        airportElement.setAttribute("trafficScalar", (new StringBuilder()).append("").append(airportModel.getTrafficScalar()).toString());
        if(SettingsEngine.getInstance().getWriteDeletes())
            writeDeletes(airportModel, xmlDoc, airportElement);
        writeServices(airportModel, airportElement, xmlDoc);
        writeAprons(airportModel, airportElement, xmlDoc);
        writeApronEdgeLights(airportModel, airportElement, xmlDoc);
        writeBlastFences(airportModel, airportElement, xmlDoc);
        writeBoundaryFences(airportModel, airportElement, xmlDoc);
        writeHelipads(airportModel, airportElement, xmlDoc);
        writeJetways(airportModel, airportElement, xmlDoc);
        writeRunways(airportModel, airportElement, xmlDoc);
        writeStarts(airportModel, airportElement, xmlDoc);
        writeTaxiways(airportModel, airportElement, xmlDoc);
        writeTaxiwaySigns(airportModel, airportElement, xmlDoc);
        writeTowers(airportModel, airportElement, xmlDoc);
        writeApproaches(airportModel, airportElement, xmlDoc);
        writeCOMs(airportModel, airportElement, xmlDoc);
        writeNDBs(airportModel, airportElement, xmlDoc, true, performingSaveAs);
        writeBGImages(airportModel, airportElement, xmlDoc);
    }

    private void writeExclusions(AirportModel airportModel, Document xmlDoc, Element fsDataElement)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("ExclusionRectangle");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == fsDataElement)
                fsDataElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getExclusionAL();
        arrayList.addAll(airportModel.getUnusedExclusionAL());
        for(int i = 0; i < arrayList.size(); i++)
        {
            ExclusionModel exclusionModel = (ExclusionModel)arrayList.get(i);
            Element exclusionElement = xmlDoc.createElement("ExclusionRectangle");
            if(exclusionModel.getExcludeAllObjects())
            {
                exclusionElement.setAttribute("excludeAllObjects", "TRUE");
            } else
            {
                if(exclusionModel.getExcludeBeaconObjects())
                    exclusionElement.setAttribute("excludeBeaconObjects", "TRUE");
                if(exclusionModel.getExcludeEffectObjects())
                    exclusionElement.setAttribute("excludeEffectObjects", "TRUE");
                if(exclusionModel.getExcludeExtrusionBridgeObjects())
                    exclusionElement.setAttribute("excludeExtrusionBridgeObjects", "TRUE");
                if(exclusionModel.getExcludeGenericBuildingObjects())
                    exclusionElement.setAttribute("excludeGenericBuildingObjects", "TRUE");
                if(exclusionModel.getExcludeLibraryObjects())
                    exclusionElement.setAttribute("excludeLibraryObjects", "TRUE");
                if(exclusionModel.getExcludeTaxiwaySignObjects())
                    exclusionElement.setAttribute("excludeTaxiwaySignObjects", "TRUE");
                if(exclusionModel.getExcludeTriggerObjects())
                    exclusionElement.setAttribute("excludeTriggerObjects", "TRUE");
                if(exclusionModel.getExcludeWindsockObjects())
                    exclusionElement.setAttribute("excludeWindsockObjects", "TRUE");
                if(exclusionElement.hasAttributes())
                    exclusionElement.setAttribute("excludeAllObjects", "FALSE");
            }
            Element borderOnlyElement = xmlDoc.createElement("BorderOnly");
            borderOnlyElement.setAttribute("display", exclusionModel.getDisplayBorderOnly() ? "TRUE" : "FALSE");
            exclusionElement.appendChild(borderOnlyElement);
            if(exclusionElement.hasAttributes())
            {
                exclusionElement.setAttribute("latitudeMinimum", latLonFormat.format(Math.min(exclusionModel.getVertex1().getLatLon().getLat(), exclusionModel.getVertex2().getLatLon().getLat())));
                exclusionElement.setAttribute("latitudeMaximum", latLonFormat.format(Math.max(exclusionModel.getVertex1().getLatLon().getLat(), exclusionModel.getVertex2().getLatLon().getLat())));
                exclusionElement.setAttribute("longitudeMinimum", latLonFormat.format(Math.min(exclusionModel.getVertex1().getLatLon().getLon(), exclusionModel.getVertex2().getLatLon().getLon())));
                exclusionElement.setAttribute("longitudeMaximum", latLonFormat.format(Math.max(exclusionModel.getVertex1().getLatLon().getLon(), exclusionModel.getVertex2().getLatLon().getLon())));
                fsDataElement.appendChild(exclusionElement);
            }
        }

    }

    private void writeDeletes(AirportModel airportModel, Document xmlDoc, Element airportElement)
    {
        NodeList nodeList = airportElement.getElementsByTagName("DeleteAirport");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        Element deleteElement = xmlDoc.createElement("DeleteAirport");
        DeleteModel deleteModel = airportModel.getDeleteModel();
        if(deleteModel.getDeleteAllApproaches())
            deleteElement.setAttribute("deleteAllApproaches", "TRUE");
        if(deleteModel.getDeleteAllApronLights())
            deleteElement.setAttribute("deleteAllApronLights", "TRUE");
        if(deleteModel.getDeleteAllAprons())
            deleteElement.setAttribute("deleteAllAprons", "TRUE");
        if(deleteModel.getDeleteAllFrequencies())
            deleteElement.setAttribute("deleteAllFrequencies", "TRUE");
        if(deleteModel.getDeleteAllHelipads())
            deleteElement.setAttribute("deleteAllHelipads", "TRUE");
        if(deleteModel.getDeleteAllRunways())
            deleteElement.setAttribute("deleteAllRunways", "TRUE");
        if(deleteModel.getDeleteAllStarts())
            deleteElement.setAttribute("deleteAllStarts", "TRUE");
        if(deleteModel.getDeleteAllTaxiways())
            deleteElement.setAttribute("deleteAllTaxiways", "TRUE");
        if(deleteModel.getDeleteAllBlastFences())
            deleteElement.setAttribute("deleteAllBlastFences", "TRUE");
        if(deleteModel.getDeleteAllBoundaryFences())
            deleteElement.setAttribute("deleteAllBoundaryFences", "TRUE");
        if(deleteModel.getDeleteAllControlTowers())
            deleteElement.setAttribute("deleteAllControlTowers", "TRUE");
        if(deleteModel.getDeleteAllJetways())
            deleteElement.setAttribute("deleteAllJetways", "TRUE");
        if(!deleteModel.getDeleteAllRunways())
        {
            ArrayList arrayList = deleteModel.getRunwayAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                Element deleteRunwayElement = xmlDoc.createElement("DeleteRunway");
                deleteRunwayElement.setAttribute("surface", (String)hashMap.get("surface"));
                deleteRunwayElement.setAttribute("number", (String)hashMap.get("number"));
                deleteRunwayElement.setAttribute("designator", (String)hashMap.get("designator"));
                deleteElement.appendChild(deleteRunwayElement);
            }

        }
        if(!deleteModel.getDeleteAllStarts())
        {
            ArrayList arrayList = deleteModel.getStartAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                Element deleteStartElement = xmlDoc.createElement("DeleteStart");
                deleteStartElement.setAttribute("type", (String)hashMap.get("type"));
                deleteStartElement.setAttribute("number", (String)hashMap.get("number"));
                deleteStartElement.setAttribute("designator", (String)hashMap.get("designator"));
                deleteElement.appendChild(deleteStartElement);
            }

        }
        if(!deleteModel.getDeleteAllFrequencies())
        {
            ArrayList arrayList = deleteModel.getFrequencyAL();
            for(int i = arrayList.size() - 1; i >= 0; i--)
            {
                HashMap hashMap = (HashMap)arrayList.get(i);
                Element deleteFrequencyElement = xmlDoc.createElement("DeleteFrequency");
                deleteFrequencyElement.setAttribute("frequency", (String)hashMap.get("frequency"));
                deleteFrequencyElement.setAttribute("type", (String)hashMap.get("type"));
                deleteElement.appendChild(deleteFrequencyElement);
            }

        }
        if(deleteElement.hasAttributes() || deleteElement.hasChildNodes())
            airportElement.appendChild(deleteElement);
    }

    private void writeServices(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Services");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList servicesAL = airportModel.getServicesAL();
        Element servicesElement = xmlDoc.createElement("Services");
        for(int i = 0; i < servicesAL.size(); i++)
        {
            Element fuelElement = xmlDoc.createElement("Fuel");
            servicesElement.appendChild(fuelElement);
            HashMap hashMap = (HashMap)servicesAL.get(i);
            fuelElement.setAttribute("type", (String)hashMap.get("type"));
            fuelElement.setAttribute("availability", (String)hashMap.get("availability"));
        }

        if(servicesAL.size() > 0)
            airportElement.appendChild(servicesElement);
    }

    private void writeAprons(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Aprons");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getApronAL();
        Element apronsElement = xmlDoc.createElement("Aprons");
        if(arrayList.size() > 0)
            airportElement.appendChild(apronsElement);
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            ApronModel apronModel = (ApronModel)arrayList.get(i);
            Element apronElement = xmlDoc.createElement("Apron");
            apronElement.setAttribute("surface", apronModel.getSurface());
            apronElement.setAttribute("drawSurface", apronModel.getDrawSurface() ? "TRUE" : "FALSE");
            apronElement.setAttribute("drawDetail", apronModel.getDrawDetail() ? "TRUE" : "FALSE");
            apronsElement.appendChild(apronElement);
            ArrayList vertexAL = apronModel.getVertexAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexModel vertexModel = (VertexModel)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format(vertexModel.getLatLon().getLon()));
                vertexElement.setAttribute("lat", latLonFormat.format(vertexModel.getLatLon().getLat()));
                apronElement.appendChild(vertexElement);
            }

        }

    }

    private void writeApronEdgeLights(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("ApronEdgeLights");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getApronEdgeLightAL();
        Element apronEdgeLightsElement = xmlDoc.createElement("ApronEdgeLights");
        if(arrayList.size() > 0)
            airportElement.appendChild(apronEdgeLightsElement);
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            Element edgeLightsElement = xmlDoc.createElement("EdgeLights");
            apronEdgeLightsElement.appendChild(edgeLightsElement);
            ApronEdgeLightModel apronEdgeLightModel = (ApronEdgeLightModel)arrayList.get(i);
            for(int j = 0; j < apronEdgeLightModel.getVertexAL().size(); j++)
            {
                VertexModel vertexModel = (VertexModel)apronEdgeLightModel.getVertexAL().get(j);
                Element vertexElement = xmlDoc.createElement("Vertex");
                vertexElement.setAttribute("lon", latLonFormat.format(vertexModel.getLatLon().getLon()));
                vertexElement.setAttribute("lat", latLonFormat.format(vertexModel.getLatLon().getLat()));
                edgeLightsElement.appendChild(vertexElement);
            }

        }

    }

    private void writeBlastFences(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("BlastFence");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getBlastFenceAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            BlastFenceModel blastFenceModel = (BlastFenceModel)arrayList.get(i);
            Element blastFenceElement = xmlDoc.createElement("BlastFence");
            blastFenceElement.setAttribute("profile", blastFenceModel.getProfile());
            if(blastFenceModel.getInstanceID().length() > 0)
                blastFenceElement.setAttribute("instanceid", blastFenceModel.getInstanceID());
            airportElement.appendChild(blastFenceElement);
            ArrayList vertexAL = blastFenceModel.getVertexAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexModel vertexModel = (VertexModel)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format(vertexModel.getLatLon().getLon()));
                vertexElement.setAttribute("lat", latLonFormat.format(vertexModel.getLatLon().getLat()));
                blastFenceElement.appendChild(vertexElement);
            }

        }

    }

    private void writeBoundaryFences(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("BoundaryFence");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getBoundaryFenceAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            BoundaryFenceModel boundaryFenceModel = (BoundaryFenceModel)arrayList.get(i);
            Element boundaryFenceElement = xmlDoc.createElement("BoundaryFence");
            boundaryFenceElement.setAttribute("profile", boundaryFenceModel.getProfile());
            if(boundaryFenceModel.getInstanceID().length() > 0)
                boundaryFenceElement.setAttribute("instanceid", boundaryFenceModel.getInstanceID());
            airportElement.appendChild(boundaryFenceElement);
            ArrayList vertexAL = boundaryFenceModel.getVertexAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                Element vertexElement = xmlDoc.createElement("Vertex");
                VertexModel vertexModel = (VertexModel)vertexAL.get(j);
                vertexElement.setAttribute("lon", latLonFormat.format(vertexModel.getLatLon().getLon()));
                vertexElement.setAttribute("lat", latLonFormat.format(vertexModel.getLatLon().getLat()));
                boundaryFenceElement.appendChild(vertexElement);
            }

        }

    }

    private void writeHelipads(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Helipad");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getHelipadAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            HelipadModel helipadModel = (HelipadModel)arrayList.get(i);
            Element helipadElement = xmlDoc.createElement("Helipad");
            helipadElement.setAttribute("lat", latLonFormat.format(helipadModel.getLatLon().getLat()));
            helipadElement.setAttribute("lon", latLonFormat.format(helipadModel.getLatLon().getLon()));
            helipadElement.setAttribute("alt", (new StringBuilder()).append(helipadModel.getAlt()).append(helipadModel.getAltMeasure()).toString());
            helipadElement.setAttribute("surface", helipadModel.getSurface());
            helipadElement.setAttribute("heading", (new StringBuilder()).append("").append(helipadModel.getHeading()).toString());
            helipadElement.setAttribute("length", (new StringBuilder()).append(helipadModel.getLength()).append(helipadModel.getLengthMeasure()).toString());
            helipadElement.setAttribute("width", (new StringBuilder()).append(helipadModel.getWidth()).append(helipadModel.getWidthMeasure()).toString());
            helipadElement.setAttribute("type", helipadModel.getType());
            helipadElement.setAttribute("closed", helipadModel.getClosed() ? "TRUE" : "FALSE");
            helipadElement.setAttribute("transparent", helipadModel.getTransparent() ? "TRUE" : "FALSE");
            airportElement.appendChild(helipadElement);
        }

    }

    private void writeJetways(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Jetway");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getJetwayAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            JetwayModel jetwayModel = (JetwayModel)arrayList.get(i);
            Element jetwayElement = xmlDoc.createElement("Jetway");
            airportElement.appendChild(jetwayElement);
            jetwayElement.setAttribute("gateName", jetwayModel.getGateName());
            jetwayElement.setAttribute("parkingNumber", (new StringBuilder()).append("").append(jetwayModel.getParkingNumber()).toString());
            Element sceneryElement = xmlDoc.createElement("SceneryObject");
            jetwayElement.appendChild(sceneryElement);
            sceneryElement.setAttribute("lat", latLonFormat.format(jetwayModel.getLatLon().getLat()));
            sceneryElement.setAttribute("lon", latLonFormat.format(jetwayModel.getLatLon().getLon()));
            sceneryElement.setAttribute("alt", (new StringBuilder()).append(jetwayModel.getAlt()).append(jetwayModel.getAltMeasure()).toString());
            sceneryElement.setAttribute("altitudeIsAgl", jetwayModel.getAltitudeIsAgl() ? "TRUE" : "FALSE");
            sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(jetwayModel.getHeading()).toString());
            sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(jetwayModel.getPitch()).toString());
            sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(jetwayModel.getBank()).toString());
            sceneryElement.setAttribute("imageComplexity", (new StringBuilder()).append("").append(jetwayModel.getImageComplexity()).toString());
            if(jetwayModel.getInstanceid().length() > 0)
                sceneryElement.setAttribute("instanceid", jetwayModel.getInstanceid());
            Element libraryElement = xmlDoc.createElement("LibraryObject");
            sceneryElement.appendChild(libraryElement);
            libraryElement.setAttribute("name", jetwayModel.getName());
            libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(jetwayModel.getJetwayScale()).toString());
        }

    }

    private void writeRunways(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Runway");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getRunwayAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            RunwayModel runwayModel = (RunwayModel)arrayList.get(i);
            Element runwayElement = xmlDoc.createElement("Runway");
            runwayElement.setAttribute("lat", latLonFormat.format(runwayModel.getLatLon().getLat()));
            runwayElement.setAttribute("lon", latLonFormat.format(runwayModel.getLatLon().getLon()));
            runwayElement.setAttribute("alt", (new StringBuilder()).append(runwayModel.getAlt()).append(runwayModel.getAltMeasure()).toString());
            runwayElement.setAttribute("surface", runwayModel.getSurface());
            runwayElement.setAttribute("heading", (new StringBuilder()).append("").append(runwayModel.getHeading()).toString());
            runwayElement.setAttribute("length", (new StringBuilder()).append(runwayModel.getLength()).append(runwayModel.getLengthMeasure()).toString());
            runwayElement.setAttribute("width", (new StringBuilder()).append(runwayModel.getWidth()).append(runwayModel.getWidthMeasure()).toString());
            runwayElement.setAttribute("number", runwayModel.getNumber());
            if(runwayModel.getDesignator().length() > 0)
            {
                runwayElement.setAttribute("designator", runwayModel.getDesignator());
            } else
            {
                runwayElement.setAttribute("primaryDesignator", runwayModel.getPrimaryDesignator());
                runwayElement.setAttribute("secondaryDesignator", runwayModel.getSecondaryDesignator());
            }
            if(runwayModel.getPatternAltitude() > 0.0F)
                runwayElement.setAttribute("patternAltitude", (new StringBuilder()).append(runwayModel.getPatternAltitude()).append(runwayModel.getPatternAltitudeMeasure()).toString());
            runwayElement.setAttribute("primaryTakeoff", runwayModel.isPrimaryTakeoff() ? "TRUE" : "FALSE");
            runwayElement.setAttribute("primaryLanding", runwayModel.isPrimaryLanding() ? "TRUE" : "FALSE");
            runwayElement.setAttribute("primaryPattern", runwayModel.getPrimaryPattern());
            runwayElement.setAttribute("secondaryTakeoff", runwayModel.isSecondaryTakeoff() ? "TRUE" : "FALSE");
            runwayElement.setAttribute("secondaryLanding", runwayModel.isSecondaryLanding() ? "TRUE" : "FALSE");
            runwayElement.setAttribute("secondaryPattern", runwayModel.getSecondaryPattern());
            runwayElement.setAttribute("primaryMarkingBias", (new StringBuilder()).append(runwayModel.getPrimaryMarkingBias()).append(runwayModel.getPrimaryMarkingBiasMeasure()).toString());
            runwayElement.setAttribute("secondaryMarkingBias", (new StringBuilder()).append(runwayModel.getSecondaryMarkingBias()).append(runwayModel.getSecondaryMarkingBiasMeasure()).toString());
            airportElement.appendChild(runwayElement);
            MarkingsModel markingsModel = runwayModel.getMarkingsModel();
            Element markingsElement = xmlDoc.createElement("Markings");
            markingsElement.setAttribute("alternateThreshold", markingsModel.getAlternateThreshold() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("alternateTouchdown", markingsModel.getAlternateTouchdown() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("alternateFixedDistance", markingsModel.getAlternateFixedDistance() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("alternatePrecision", markingsModel.getAlternatePrecision() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("leadingZeroIdent", markingsModel.getLeadingZeroIdent() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("noThresholdEndArrows", markingsModel.getNoThresholdEndArrows() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("edges", markingsModel.getEdges() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("threshold", markingsModel.getThreshold() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("fixedDistance", markingsModel.getFixed() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("touchdown", markingsModel.getTouchdown() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("dashes", markingsModel.getDashes() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("ident", markingsModel.getIdent() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("precision", markingsModel.getPrecision() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("edgePavement", markingsModel.getEdgePavement() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("singleEnd", markingsModel.getSingleEnd() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("primaryClosed", markingsModel.getPrimaryClosed() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("secondaryClosed", markingsModel.getSecondaryClosed() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("primaryStol", markingsModel.getPrimaryStol() ? "TRUE" : "FALSE");
            markingsElement.setAttribute("secondaryStol", markingsModel.getSecondaryStol() ? "TRUE" : "FALSE");
            runwayElement.appendChild(markingsElement);
            LightsModel lightsModel = runwayModel.getLightsModel();
            Element lightsElement = xmlDoc.createElement("Lights");
            lightsElement.setAttribute("center", lightsModel.getCenter());
            lightsElement.setAttribute("edge", lightsModel.getEdge());
            lightsElement.setAttribute("centerRed", lightsModel.getCenterRed() ? "TRUE" : "FALSE");
            runwayElement.appendChild(lightsElement);
            if(runwayModel.getPrimaryOffsetModel() != null)
            {
                RunwayDetailModel offsetThresholdModel = runwayModel.getPrimaryOffsetModel();
                Element offsetThresholdElement = xmlDoc.createElement("OffsetThreshold");
                offsetThresholdElement.setAttribute("end", "PRIMARY");
                offsetThresholdElement.setAttribute("length", (new StringBuilder()).append(offsetThresholdModel.getLength()).append(offsetThresholdModel.getLengthMeasure()).toString());
                if(offsetThresholdModel.getWidth() > 0.0F)
                    offsetThresholdElement.setAttribute("width", (new StringBuilder()).append(offsetThresholdModel.getWidth()).append(offsetThresholdModel.getWidthMeasure()).toString());
                if(offsetThresholdModel.getSurface().length() > 0)
                    offsetThresholdElement.setAttribute("surface", offsetThresholdModel.getSurface());
                runwayElement.appendChild(offsetThresholdElement);
            }
            if(runwayModel.getSecondaryOffsetModel() != null)
            {
                RunwayDetailModel offsetThresholdModel = runwayModel.getSecondaryOffsetModel();
                Element offsetThresholdElement = xmlDoc.createElement("OffsetThreshold");
                offsetThresholdElement.setAttribute("end", "SECONDARY");
                offsetThresholdElement.setAttribute("length", (new StringBuilder()).append(offsetThresholdModel.getLength()).append(offsetThresholdModel.getLengthMeasure()).toString());
                if(offsetThresholdModel.getWidth() > 0.0F)
                    offsetThresholdElement.setAttribute("width", (new StringBuilder()).append(offsetThresholdModel.getWidth()).append(offsetThresholdModel.getWidthMeasure()).toString());
                if(offsetThresholdModel.getSurface().length() > 0)
                    offsetThresholdElement.setAttribute("surface", offsetThresholdModel.getSurface());
                runwayElement.appendChild(offsetThresholdElement);
            }
            if(runwayModel.getPrimaryBlastPadModel() != null)
            {
                RunwayDetailModel blastPadModel = runwayModel.getPrimaryBlastPadModel();
                Element blastPadElement = xmlDoc.createElement("BlastPad");
                blastPadElement.setAttribute("end", "PRIMARY");
                blastPadElement.setAttribute("length", (new StringBuilder()).append(blastPadModel.getLength()).append(blastPadModel.getLengthMeasure()).toString());
                if(blastPadModel.getWidth() > 0.0F)
                    blastPadElement.setAttribute("width", (new StringBuilder()).append(blastPadModel.getWidth()).append(blastPadModel.getWidthMeasure()).toString());
                if(blastPadModel.getSurface().length() > 0)
                    blastPadElement.setAttribute("surface", blastPadModel.getSurface());
                runwayElement.appendChild(blastPadElement);
            }
            if(runwayModel.getSecondaryBlastPadModel() != null)
            {
                RunwayDetailModel blastPadModel = runwayModel.getSecondaryBlastPadModel();
                Element blastPadElement = xmlDoc.createElement("BlastPad");
                blastPadElement.setAttribute("end", "SECONDARY");
                blastPadElement.setAttribute("length", (new StringBuilder()).append(blastPadModel.getLength()).append(blastPadModel.getLengthMeasure()).toString());
                if(blastPadModel.getWidth() > 0.0F)
                    blastPadElement.setAttribute("width", (new StringBuilder()).append(blastPadModel.getWidth()).append(blastPadModel.getWidthMeasure()).toString());
                if(blastPadModel.getSurface().length() > 0)
                    blastPadElement.setAttribute("surface", blastPadModel.getSurface());
                runwayElement.appendChild(blastPadElement);
            }
            if(runwayModel.getPrimaryOverrunModel() != null)
            {
                RunwayDetailModel overrunModel = runwayModel.getPrimaryOverrunModel();
                Element overrunElement = xmlDoc.createElement("Overrun");
                overrunElement.setAttribute("end", "PRIMARY");
                overrunElement.setAttribute("length", (new StringBuilder()).append(overrunModel.getLength()).append(overrunModel.getLengthMeasure()).toString());
                if(overrunModel.getWidth() > 0.0F)
                    overrunElement.setAttribute("width", (new StringBuilder()).append(overrunModel.getWidth()).append(overrunModel.getWidthMeasure()).toString());
                if(overrunModel.getSurface().length() > 0)
                    overrunElement.setAttribute("surface", overrunModel.getSurface());
                runwayElement.appendChild(overrunElement);
            }
            if(runwayModel.getSecondaryOverrunModel() != null)
            {
                RunwayDetailModel overrunModel = runwayModel.getSecondaryOverrunModel();
                Element overrunElement = xmlDoc.createElement("Overrun");
                overrunElement.setAttribute("end", "SECONDARY");
                overrunElement.setAttribute("length", (new StringBuilder()).append(overrunModel.getLength()).append(overrunModel.getLengthMeasure()).toString());
                if(overrunModel.getWidth() > 0.0F)
                    overrunElement.setAttribute("width", (new StringBuilder()).append(overrunModel.getWidth()).append(overrunModel.getWidthMeasure()).toString());
                if(overrunModel.getSurface().length() > 0)
                    overrunElement.setAttribute("surface", overrunModel.getSurface());
                runwayElement.appendChild(overrunElement);
            }
            if(runwayModel.getPrimaryApproachLightsModel() != null)
            {
                ApproachLightsModel approachLightsModel = runwayModel.getPrimaryApproachLightsModel();
                Element approachLightsElement = xmlDoc.createElement("ApproachLights");
                approachLightsElement.setAttribute("end", "PRIMARY");
                if(approachLightsModel.getSystem().length() > 0)
                    approachLightsElement.setAttribute("system", approachLightsModel.getSystem());
                if(approachLightsModel.getStrobes() > 0)
                    approachLightsElement.setAttribute("strobes", (new StringBuilder()).append("").append(approachLightsModel.getStrobes()).toString());
                approachLightsElement.setAttribute("reil", approachLightsModel.getReil() ? "TRUE" : "FALSE");
                approachLightsElement.setAttribute("touchdown", approachLightsModel.getTouchdown() ? "TRUE" : "FALSE");
                approachLightsElement.setAttribute("endLights", approachLightsModel.getEndLights() ? "TRUE" : "FALSE");
                runwayElement.appendChild(approachLightsElement);
            }
            if(runwayModel.getSecondaryApproachLightsModel() != null)
            {
                ApproachLightsModel approachLightsModel = runwayModel.getSecondaryApproachLightsModel();
                Element approachLightsElement = xmlDoc.createElement("ApproachLights");
                approachLightsElement.setAttribute("end", "SECONDARY");
                if(approachLightsModel.getSystem().length() > 0)
                    approachLightsElement.setAttribute("system", approachLightsModel.getSystem());
                if(approachLightsModel.getStrobes() > 0)
                    approachLightsElement.setAttribute("strobes", (new StringBuilder()).append("").append(approachLightsModel.getStrobes()).toString());
                approachLightsElement.setAttribute("reil", approachLightsModel.getReil() ? "TRUE" : "FALSE");
                approachLightsElement.setAttribute("touchdown", approachLightsModel.getTouchdown() ? "TRUE" : "FALSE");
                approachLightsElement.setAttribute("endLights", approachLightsModel.getEndLights() ? "TRUE" : "FALSE");
                runwayElement.appendChild(approachLightsElement);
            }
            if(runwayModel.getPrimaryLeftVasiModel() != null)
            {
                VasiModel vasiModel = runwayModel.getPrimaryLeftVasiModel();
                Element vasiElement = xmlDoc.createElement("Vasi");
                vasiElement.setAttribute("end", "PRIMARY");
                vasiElement.setAttribute("type", vasiModel.getType());
                vasiElement.setAttribute("side", vasiModel.getSide());
                vasiElement.setAttribute("biasX", (new StringBuilder()).append(vasiModel.getBiasX()).append(vasiModel.getBiasXMeasure()).toString());
                vasiElement.setAttribute("biasZ", (new StringBuilder()).append(vasiModel.getBiasZ()).append(vasiModel.getBiasZMeasure()).toString());
                vasiElement.setAttribute("spacing", (new StringBuilder()).append(vasiModel.getSpacing()).append(vasiModel.getSpacingMeasure()).toString());
                vasiElement.setAttribute("pitch", (new StringBuilder()).append("").append(vasiModel.getPitch()).toString());
                runwayElement.appendChild(vasiElement);
            }
            if(runwayModel.getPrimaryRightVasiModel() != null)
            {
                VasiModel vasiModel = runwayModel.getPrimaryRightVasiModel();
                Element vasiElement = xmlDoc.createElement("Vasi");
                vasiElement.setAttribute("end", "PRIMARY");
                vasiElement.setAttribute("type", vasiModel.getType());
                vasiElement.setAttribute("side", vasiModel.getSide());
                vasiElement.setAttribute("biasX", (new StringBuilder()).append(vasiModel.getBiasX()).append(vasiModel.getBiasXMeasure()).toString());
                vasiElement.setAttribute("biasZ", (new StringBuilder()).append(vasiModel.getBiasZ()).append(vasiModel.getBiasZMeasure()).toString());
                vasiElement.setAttribute("spacing", (new StringBuilder()).append(vasiModel.getSpacing()).append(vasiModel.getSpacingMeasure()).toString());
                vasiElement.setAttribute("pitch", (new StringBuilder()).append("").append(vasiModel.getPitch()).toString());
                runwayElement.appendChild(vasiElement);
            }
            if(runwayModel.getSecondaryLeftVasiModel() != null)
            {
                VasiModel vasiModel = runwayModel.getSecondaryLeftVasiModel();
                Element vasiElement = xmlDoc.createElement("Vasi");
                vasiElement.setAttribute("end", "SECONDARY");
                vasiElement.setAttribute("type", vasiModel.getType());
                vasiElement.setAttribute("side", vasiModel.getSide());
                vasiElement.setAttribute("biasX", (new StringBuilder()).append(vasiModel.getBiasX()).append(vasiModel.getBiasXMeasure()).toString());
                vasiElement.setAttribute("biasZ", (new StringBuilder()).append(vasiModel.getBiasZ()).append(vasiModel.getBiasZMeasure()).toString());
                vasiElement.setAttribute("spacing", (new StringBuilder()).append(vasiModel.getSpacing()).append(vasiModel.getSpacingMeasure()).toString());
                vasiElement.setAttribute("pitch", (new StringBuilder()).append("").append(vasiModel.getPitch()).toString());
                runwayElement.appendChild(vasiElement);
            }
            if(runwayModel.getSecondaryRightVasiModel() != null)
            {
                VasiModel vasiModel = runwayModel.getSecondaryRightVasiModel();
                Element vasiElement = xmlDoc.createElement("Vasi");
                vasiElement.setAttribute("end", "SECONDARY");
                vasiElement.setAttribute("type", vasiModel.getType());
                vasiElement.setAttribute("side", vasiModel.getSide());
                vasiElement.setAttribute("biasX", (new StringBuilder()).append(vasiModel.getBiasX()).append(vasiModel.getBiasXMeasure()).toString());
                vasiElement.setAttribute("biasZ", (new StringBuilder()).append(vasiModel.getBiasZ()).append(vasiModel.getBiasZMeasure()).toString());
                vasiElement.setAttribute("spacing", (new StringBuilder()).append(vasiModel.getSpacing()).append(vasiModel.getSpacingMeasure()).toString());
                vasiElement.setAttribute("pitch", (new StringBuilder()).append("").append(vasiModel.getPitch()).toString());
                runwayElement.appendChild(vasiElement);
            }
            ArrayList ilsAL = runwayModel.getILSAL();
            for(int j = 0; j < ilsAL.size(); j++)
            {
                ILSModel ilsModel = (ILSModel)ilsAL.get(j);
                Element ilsElement = xmlDoc.createElement("Ils");
                ilsElement.setAttribute("lat", latLonFormat.format(ilsModel.getLatLon().getLat()));
                ilsElement.setAttribute("lon", latLonFormat.format(ilsModel.getLatLon().getLon()));
                ilsElement.setAttribute("alt", (new StringBuilder()).append(ilsModel.getAlt()).append(ilsModel.getAltMeasure()).toString());
                ilsElement.setAttribute("heading", (new StringBuilder()).append("").append(ilsModel.getHeading()).toString());
                ilsElement.setAttribute("frequency", (new StringBuilder()).append("").append(ilsModel.getFrequency()).toString());
                ilsElement.setAttribute("end", ilsModel.getEnd());
                if(ilsModel.getRange() != 27F)
                    ilsElement.setAttribute("range", (new StringBuilder()).append(ilsModel.getRange()).append(ilsModel.getRangeMeasure()).toString());
                ilsElement.setAttribute("magvar", (new StringBuilder()).append("").append(ilsModel.getMagvar()).toString());
                ilsElement.setAttribute("ident", ilsModel.getIdent());
                if(ilsModel.getWidth() != 5F)
                    ilsElement.setAttribute("width", (new StringBuilder()).append("").append(ilsModel.getWidth()).toString());
                if(ilsModel.getName().length() > 0)
                    ilsElement.setAttribute("name", ilsModel.getName());
                ilsElement.setAttribute("backCourse", ilsModel.getBackCourse() ? "TRUE" : "FALSE");
                runwayElement.appendChild(ilsElement);
                GlideSlopeModel glideSlopeModel = ilsModel.getGlideSlopeModel();
                if(glideSlopeModel != null)
                {
                    Element glideSlopeElement = xmlDoc.createElement("GlideSlope");
                    glideSlopeElement.setAttribute("lat", latLonFormat.format(glideSlopeModel.getLatLon().getLat()));
                    glideSlopeElement.setAttribute("lon", latLonFormat.format(glideSlopeModel.getLatLon().getLon()));
                    glideSlopeElement.setAttribute("alt", (new StringBuilder()).append(glideSlopeModel.getAlt()).append(glideSlopeModel.getAltMeasure()).toString());
                    glideSlopeElement.setAttribute("pitch", (new StringBuilder()).append("").append(glideSlopeModel.getPitch()).toString());
                    glideSlopeElement.setAttribute("range", (new StringBuilder()).append(glideSlopeModel.getRange()).append(glideSlopeModel.getRangeMeasure()).toString());
                    ilsElement.appendChild(glideSlopeElement);
                }
                DMEModel dmeModel = ilsModel.getDMEModel();
                if(dmeModel != null)
                {
                    Element dmeElement = xmlDoc.createElement("Dme");
                    dmeElement.setAttribute("lat", latLonFormat.format(dmeModel.getLatLon().getLat()));
                    dmeElement.setAttribute("lon", latLonFormat.format(dmeModel.getLatLon().getLon()));
                    dmeElement.setAttribute("alt", (new StringBuilder()).append("").append(dmeModel.getAlt()).toString());
                    dmeElement.setAttribute("range", (new StringBuilder()).append("").append(dmeModel.getRange()).toString());
                    ilsElement.appendChild(dmeElement);
                }
            }

        }

    }

    private void writeStarts(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Start");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getStartAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            StartModel startModel = (StartModel)arrayList.get(i);
            Element startElement = xmlDoc.createElement("Start");
            startElement.setAttribute("lat", latLonFormat.format(startModel.getLatLon().getLat()));
            startElement.setAttribute("lon", latLonFormat.format(startModel.getLatLon().getLon()));
            if(startModel.getType().length() > 0)
                startElement.setAttribute("type", startModel.getType());
            startElement.setAttribute("alt", (new StringBuilder()).append(startModel.getAlt()).append(startModel.getAltMeasure()).toString());
            startElement.setAttribute("heading", (new StringBuilder()).append("").append(startModel.getHeading()).toString());
            if(startModel.getNumber().length() > 0)
                startElement.setAttribute("number", startModel.getNumber());
            if(startModel.getDesignator().length() > 0)
                startElement.setAttribute("designator", startModel.getDesignator());
            airportElement.appendChild(startElement);
        }

    }

    private void writeTaxiways(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("TaxiwayPoint");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        HashMap hashMap = airportModel.getTaxiwayPointHM();
        ArrayList tempAL = new ArrayList();
        Element taxiwayPointElement;
        for(Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext(); tempAL.add(taxiwayPointElement))
        {
            TaxiwayPointModel taxiwayPointModel = (TaxiwayPointModel)hashMap.get(iterator.next());
            taxiwayPointElement = xmlDoc.createElement("TaxiwayPoint");
            taxiwayPointElement.setAttribute("lat", latLonFormat.format(taxiwayPointModel.getLatLon().getLat()));
            taxiwayPointElement.setAttribute("lon", latLonFormat.format(taxiwayPointModel.getLatLon().getLon()));
            taxiwayPointElement.setAttribute("index", (new StringBuilder()).append("").append(taxiwayPointModel.getIndex()).toString());
            taxiwayPointElement.setAttribute("type", taxiwayPointModel.getType());
            if(taxiwayPointModel.getType().indexOf("HOLD_SHORT") >= 0)
                taxiwayPointElement.setAttribute("orientation", taxiwayPointModel.getOrientation());
        }

        Collections.sort(tempAL, new TaxiwayPointElementSort());
        for(int i = tempAL.size() - 1; i >= 0; i--)
            airportElement.appendChild((Element)tempAL.get(i));

        nodeList = airportElement.getElementsByTagName("TaxiwayParking");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            airportElement.removeChild(nodeList.item(i));

        hashMap = airportModel.getTaxiwayParkingHM();
        tempAL.clear();
        Element taxiwayParkingElement;
        for(Iterator iterator = hashMap.keySet().iterator(); iterator.hasNext(); tempAL.add(taxiwayParkingElement))
        {
            TaxiwayParkingModel taxiwayParkingModel = (TaxiwayParkingModel)hashMap.get(iterator.next());
            taxiwayParkingElement = xmlDoc.createElement("TaxiwayParking");
            taxiwayParkingElement.setAttribute("lat", latLonFormat.format(taxiwayParkingModel.getLatLon().getLat()));
            taxiwayParkingElement.setAttribute("lon", latLonFormat.format(taxiwayParkingModel.getLatLon().getLon()));
            taxiwayParkingElement.setAttribute("index", (new StringBuilder()).append("").append(taxiwayParkingModel.getIndex()).toString());
            taxiwayParkingElement.setAttribute("type", taxiwayParkingModel.getType());
            taxiwayParkingElement.setAttribute("name", taxiwayParkingModel.getName());
            taxiwayParkingElement.setAttribute("heading", (new StringBuilder()).append("").append(taxiwayParkingModel.getHeading()).toString());
            taxiwayParkingElement.setAttribute("radius", (new StringBuilder()).append(taxiwayParkingModel.getRadius()).append(taxiwayParkingModel.getRadiusMeasure()).toString());
            taxiwayParkingElement.setAttribute("number", (new StringBuilder()).append("").append(taxiwayParkingModel.getNumber()).toString());
            if(taxiwayParkingModel.getAirlineCodes().length() > 0)
                taxiwayParkingElement.setAttribute("airlineCodes", taxiwayParkingModel.getAirlineCodes());
            taxiwayParkingElement.setAttribute("pushBack", taxiwayParkingModel.getPushBack());
            if((double)taxiwayParkingModel.getTeeOffset1() >= 0.10000000000000001D)
                taxiwayParkingElement.setAttribute("teeOffset1", (new StringBuilder()).append("").append(taxiwayParkingModel.getTeeOffset1()).toString());
            if((double)taxiwayParkingModel.getTeeOffset2() >= 0.10000000000000001D)
                taxiwayParkingElement.setAttribute("teeOffset2", (new StringBuilder()).append("").append(taxiwayParkingModel.getTeeOffset2()).toString());
            if((double)taxiwayParkingModel.getTeeOffset3() >= 0.10000000000000001D)
                taxiwayParkingElement.setAttribute("teeOffset3", (new StringBuilder()).append("").append(taxiwayParkingModel.getTeeOffset3()).toString());
            if((double)taxiwayParkingModel.getTeeOffset4() >= 0.10000000000000001D)
                taxiwayParkingElement.setAttribute("teeOffset4", (new StringBuilder()).append("").append(taxiwayParkingModel.getTeeOffset4()).toString());
        }

        Collections.sort(tempAL, new TaxiwayPointElementSort());
        for(int i = tempAL.size() - 1; i >= 0; i--)
            airportElement.appendChild((Element)tempAL.get(i));

        nodeList = airportElement.getElementsByTagName("TaxiwayPath");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getTaxiwayPathAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel)arrayList.get(i);
            Element taxiwayPathElement = xmlDoc.createElement("TaxiwayPath");
            taxiwayPathElement.setAttribute("type", taxiwayPathModel.getType());
            taxiwayPathElement.setAttribute("start", (new StringBuilder()).append("").append(taxiwayPathModel.getStart()).toString());
            taxiwayPathElement.setAttribute("end", (new StringBuilder()).append("").append(taxiwayPathModel.getEnd()).toString());
            taxiwayPathElement.setAttribute("width", (new StringBuilder()).append(taxiwayPathModel.getWidth()).append(taxiwayPathModel.getWidthMeasure()).toString());
            taxiwayPathElement.setAttribute("weightLimit", (new StringBuilder()).append("").append((int)taxiwayPathModel.getWeightLimit()).toString());
            taxiwayPathElement.setAttribute("surface", taxiwayPathModel.getSurface());
            taxiwayPathElement.setAttribute("drawSurface", taxiwayPathModel.getDrawSurface() ? "TRUE" : "FALSE");
            taxiwayPathElement.setAttribute("drawDetail", taxiwayPathModel.getDrawDetail() ? "TRUE" : "FALSE");
            taxiwayPathElement.setAttribute("centerLine", taxiwayPathModel.getCenterLine() ? "TRUE" : "FALSE");
            taxiwayPathElement.setAttribute("centerLineLighted", taxiwayPathModel.getCenterLineLighted() ? "TRUE" : "FALSE");
            if(taxiwayPathModel.getLeftEdge().length() > 0)
                taxiwayPathElement.setAttribute("leftEdge", taxiwayPathModel.getLeftEdge());
            taxiwayPathElement.setAttribute("leftEdgeLighted", taxiwayPathModel.getLeftEdgeLighted() ? "TRUE" : "FALSE");
            if(taxiwayPathModel.getRightEdge().length() > 0)
                taxiwayPathElement.setAttribute("rightEdge", taxiwayPathModel.getRightEdge());
            taxiwayPathElement.setAttribute("rightEdgeLighted", taxiwayPathModel.getRightEdgeLighted() ? "TRUE" : "FALSE");
            if(taxiwayPathModel.getType().equals("RUNWAY"))
            {
                taxiwayPathElement.setAttribute("designator", taxiwayPathModel.getDesignator());
                taxiwayPathElement.setAttribute("number", taxiwayPathModel.getNumber());
            } else
            {
                taxiwayPathElement.setAttribute("name", (new StringBuilder()).append("").append(taxiwayPathModel.getName()).toString());
            }
            airportElement.appendChild(taxiwayPathElement);
        }

        nodeList = airportElement.getElementsByTagName("TaxiName");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            airportElement.removeChild(nodeList.item(i));

        arrayList = airportModel.getTaxiNameAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            TaxiNameModel taxiNameModel = (TaxiNameModel)arrayList.get(i);
            Element taxiNameElement = xmlDoc.createElement("TaxiName");
            taxiNameElement.setAttribute("index", (new StringBuilder()).append("").append(taxiNameModel.getIndex()).toString());
            taxiNameElement.setAttribute("name", taxiNameModel.getName());
            airportElement.appendChild(taxiNameElement);
        }

    }

    private void writeTaxiwaySigns(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("TaxiwaySign");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList arrayList = airportModel.getTaxiwaySignAL();
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            TaxiwaySignModel taxiwaySignModel = (TaxiwaySignModel)arrayList.get(i);
            Element taxiwaySignElement = xmlDoc.createElement("TaxiwaySign");
            taxiwaySignElement.setAttribute("lat", latLonFormat.format(taxiwaySignModel.getLatLon().getLat()));
            taxiwaySignElement.setAttribute("lon", latLonFormat.format(taxiwaySignModel.getLatLon().getLon()));
            taxiwaySignElement.setAttribute("heading", (new StringBuilder()).append("").append(taxiwaySignModel.getHeading()).toString());
            taxiwaySignElement.setAttribute("label", taxiwaySignModel.getLabel());
            if(taxiwaySignModel.getJustification().length() > 0)
                taxiwaySignElement.setAttribute("justification", taxiwaySignModel.getJustification());
            taxiwaySignElement.setAttribute("size", taxiwaySignModel.getSize());
            airportElement.appendChild(taxiwaySignElement);
        }

    }

    private void writeTowers(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Tower");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList towerAL = airportModel.getTowerAL();
        for(int i = towerAL.size() - 1; i >= 0; i--)
        {
            TowerModel towerModel = (TowerModel)towerAL.get(i);
            Element towerElement = xmlDoc.createElement("Tower");
            towerElement.setAttribute("lat", latLonFormat.format(towerModel.getLatLon().getLat()));
            towerElement.setAttribute("lon", latLonFormat.format(towerModel.getLatLon().getLon()));
            towerElement.setAttribute("alt", (new StringBuilder()).append(towerModel.getAlt()).append(towerModel.getAltMeasure()).toString());
            if(towerModel.getIncludesScenery())
            {
                Element sceneryElement = xmlDoc.createElement("SceneryObject");
                towerElement.appendChild(sceneryElement);
                sceneryElement.setAttribute("lat", latLonFormat.format(towerModel.getSceneryLatLon().getLat()));
                sceneryElement.setAttribute("lon", latLonFormat.format(towerModel.getSceneryLatLon().getLon()));
                sceneryElement.setAttribute("alt", (new StringBuilder()).append(towerModel.getSceneryAlt()).append(towerModel.getSceneryAltMeasure()).toString());
                sceneryElement.setAttribute("altitudeIsAgl", towerModel.getAltitudeIsAgl() ? "TRUE" : "FALSE");
                sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(towerModel.getHeading()).toString());
                sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(towerModel.getPitch()).toString());
                sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(towerModel.getBank()).toString());
                sceneryElement.setAttribute("imageComplexity", towerModel.getImageComplexity());
                Element libraryElement = xmlDoc.createElement("LibraryObject");
                sceneryElement.appendChild(libraryElement);
                libraryElement.setAttribute("name", towerModel.getName());
                libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(towerModel.getSceneryScale()).toString());
            }
            airportElement.appendChild(towerElement);
        }

    }

    private void writeApproaches(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Approach");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList approachAL = airportModel.getApproachAL();
        for(int i = approachAL.size() - 1; i >= 0; i--)
        {
            ApproachModel approachModel = (ApproachModel)approachAL.get(i);
            Element approachElement = xmlDoc.createElement("Approach");
            airportElement.appendChild(approachElement);
            approachElement.setAttribute("type", approachModel.getType());
            if(approachModel.getRunway().length() > 0)
                approachElement.setAttribute("runway", approachModel.getRunway());
            if(approachModel.getDesignator().length() > 0)
                approachElement.setAttribute("designator", approachModel.getDesignator());
            if(approachModel.getSuffix().length() > 0)
                approachElement.setAttribute("suffix", approachModel.getSuffix());
            approachElement.setAttribute("gpsOverlay", approachModel.getGPSOverlay() ? "TRUE" : "FALSE");
            approachElement.setAttribute("fixType", approachModel.getFixType());
            approachElement.setAttribute("fixRegion", approachModel.getFixRegion());
            approachElement.setAttribute("fixIdent", approachModel.getFixIdent());
            approachElement.setAttribute("altitude", (new StringBuilder()).append("").append(approachModel.getAltitude()).toString());
            approachElement.setAttribute("heading", (new StringBuilder()).append("").append(approachModel.getHeading()).toString());
            approachElement.setAttribute("missedAltitude", (new StringBuilder()).append("").append(approachModel.getMissedAltitude()).toString());
            ArrayList approachLegAL = approachModel.getApproachAL();
            if(approachLegAL.size() > 0)
            {
                Element approachLegsElement = xmlDoc.createElement("ApproachLegs");
                approachElement.appendChild(approachLegsElement);
                for(int j = 0; j < approachLegAL.size(); j++)
                    writeApproachLeg((LegModel)approachLegAL.get(j), xmlDoc, approachLegsElement);

            }
            ArrayList missedLegAL = approachModel.getMissedAL();
            if(missedLegAL.size() > 0)
            {
                Element missedLegsElement = xmlDoc.createElement("MissedApproachLegs");
                approachElement.appendChild(missedLegsElement);
                for(int j = 0; j < missedLegAL.size(); j++)
                    writeApproachLeg((LegModel)missedLegAL.get(j), xmlDoc, missedLegsElement);

            }
            ArrayList transitionAL = approachModel.getTransitionAL();
            for(int j = 0; j < transitionAL.size(); j++)
            {
                TransitionModel transitionModel = (TransitionModel)transitionAL.get(j);
                Element transitionElement = xmlDoc.createElement("Transition");
                approachElement.appendChild(transitionElement);
                transitionElement.setAttribute("transitionType", transitionModel.getTransitionType());
                transitionElement.setAttribute("fixType", transitionModel.getFixType());
                transitionElement.setAttribute("fixRegion", transitionModel.getFixRegion());
                transitionElement.setAttribute("fixIdent", transitionModel.getFixIdent());
                transitionElement.setAttribute("altitude", (new StringBuilder()).append(transitionModel.getAlt()).append(transitionModel.getAltMeasure()).toString());
                if(transitionModel.getDmeArcModel() != null)
                {
                    DmeArcModel dmeArcModel = transitionModel.getDmeArcModel();
                    Element dmeArcElement = xmlDoc.createElement("DmeArc");
                    transitionElement.appendChild(dmeArcElement);
                    dmeArcElement.setAttribute("radial", (new StringBuilder()).append("").append(dmeArcModel.getRadial()).toString());
                    dmeArcElement.setAttribute("distance", (new StringBuilder()).append(dmeArcModel.getDistance()).append(dmeArcModel.getDistanceMeasure()).toString());
                    dmeArcElement.setAttribute("dmeRegion", dmeArcModel.getDmeRegion());
                    dmeArcElement.setAttribute("dmeIdent", dmeArcModel.getDmeIdent());
                }
                ArrayList legAL = transitionModel.getLegAL();
                if(legAL.size() <= 0)
                    continue;
                Element transitionLegsElement = xmlDoc.createElement("TransitionLegs");
                transitionElement.appendChild(transitionLegsElement);
                for(int k = 0; k < legAL.size(); k++)
                    writeApproachLeg((LegModel)legAL.get(k), xmlDoc, transitionLegsElement);

            }

        }

    }

    private void writeApproachLeg(LegModel legModel, Document xmlDoc, Element approachElement)
    {
        Element legElement = xmlDoc.createElement("Leg");
        approachElement.appendChild(legElement);
        legElement.setAttribute("type", legModel.getType());
        if(LegTypeEngine.getInstance().getFixTypeHM().containsKey(legModel.getType()))
            if(((String)LegTypeEngine.getInstance().getFixTypeHM().get(legModel.getType())).equals("R"))
                legElement.setAttribute("fixType", legModel.getFixType());
            else
            if(legModel.getFixType().length() > 0)
                legElement.setAttribute("fixType", legModel.getFixType());
        if(LegTypeEngine.getInstance().getFixRegionHM().containsKey(legModel.getType()))
            if(((String)LegTypeEngine.getInstance().getFixRegionHM().get(legModel.getType())).equals("R"))
                legElement.setAttribute("fixRegion", legModel.getFixRegion());
            else
            if(legModel.getFixRegion().length() > 0)
                legElement.setAttribute("fixRegion", legModel.getFixRegion());
        if(LegTypeEngine.getInstance().getFixIdentHM().containsKey(legModel.getType()))
            if(((String)LegTypeEngine.getInstance().getFixIdentHM().get(legModel.getType())).equals("R"))
                legElement.setAttribute("fixIdent", legModel.getFixIdent());
            else
            if(legModel.getFixIdent().length() > 0)
                legElement.setAttribute("fixIdent", legModel.getFixIdent());
        if(LegTypeEngine.getInstance().getFlyOverHM().containsKey(legModel.getType()))
            legElement.setAttribute("flyOver", legModel.getFlyOver() ? "TRUE" : "FALSE");
        if(LegTypeEngine.getInstance().getTurnDirectionHM().containsKey(legModel.getType()) && legModel.getTurnDirection().length() > 0)
            legElement.setAttribute("turnDirection", legModel.getTurnDirection());
        if(LegTypeEngine.getInstance().getRecommendedTypeHM().containsKey(legModel.getType()) && legModel.getRecommendedType().length() > 0)
            legElement.setAttribute("recommendedType", legModel.getRecommendedType());
        if(LegTypeEngine.getInstance().getRecommendedRegionHM().containsKey(legModel.getType()) && legModel.getRecommendedRegion().length() > 0)
            legElement.setAttribute("recommendedRegion", legModel.getRecommendedRegion());
        if(LegTypeEngine.getInstance().getRecommendedIdentHM().containsKey(legModel.getType()) && legModel.getRecommendedIdent().length() > 0)
            legElement.setAttribute("recommendedIdent", legModel.getRecommendedIdent());
        if(LegTypeEngine.getInstance().getThetaHM().containsKey(legModel.getType()))
            legElement.setAttribute("theta", (new StringBuilder()).append("").append(legModel.getTheta()).toString());
        if(LegTypeEngine.getInstance().getRhoHM().containsKey(legModel.getType()))
            legElement.setAttribute("rho", (new StringBuilder()).append(legModel.getRho()).append(legModel.getRhoMeasure()).toString());
        if(LegTypeEngine.getInstance().getTrueCourseHM().containsKey(legModel.getType()) && legModel.getTrueCourse() >= 0.0F)
            legElement.setAttribute("trueCourse", (new StringBuilder()).append("").append(legModel.getTrueCourse()).toString());
        if(LegTypeEngine.getInstance().getMagneticCourseHM().containsKey(legModel.getType()) && legModel.getMagneticCourse() >= 0.0F)
            legElement.setAttribute("magneticCourse", (new StringBuilder()).append("").append(legModel.getMagneticCourse()).toString());
        if(LegTypeEngine.getInstance().getDistanceHM().containsKey(legModel.getType()))
            if(LegTypeEngine.getInstance().getTimeHM().containsKey(legModel.getType()))
            {
                if(legModel.getDistance() > 0.0F)
                    legElement.setAttribute("distance", (new StringBuilder()).append(legModel.getDistance()).append(legModel.getDistanceMeasure()).toString());
            } else
            {
                legElement.setAttribute("distance", (new StringBuilder()).append(legModel.getDistance()).append(legModel.getDistanceMeasure()).toString());
            }
        if(LegTypeEngine.getInstance().getTimeHM().containsKey(legModel.getType()) && legModel.getTime() > 0.0F)
            legElement.setAttribute("time", (new StringBuilder()).append("").append(legModel.getTime()).toString());
        if(LegTypeEngine.getInstance().getAltitudeDescriptorHM().containsKey(legModel.getType()) && legModel.getAltitudeDescriptor().length() > 0)
            legElement.setAttribute("altitudeDescriptor", legModel.getAltitudeDescriptor());
        if(LegTypeEngine.getInstance().getAltitude1HM().containsKey(legModel.getType()))
            legElement.setAttribute("altitude1", (new StringBuilder()).append(legModel.getAltitude1()).append(legModel.getAltitude1Measure()).toString());
        if(LegTypeEngine.getInstance().getAltitude2HM().containsKey(legModel.getType()))
            legElement.setAttribute("altitude2", (new StringBuilder()).append(legModel.getAltitude2()).append(legModel.getAltitude2Measure()).toString());
    }

    private void writeCOMs(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getElementsByTagName("Com");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == airportElement)
                airportElement.removeChild(nodeList.item(i));

        ArrayList comAL = airportModel.getComAL();
        for(int i = comAL.size() - 1; i >= 0; i--)
        {
            ComModel comModel = (ComModel)comAL.get(i);
            Element comElement = xmlDoc.createElement("Com");
            comElement.setAttribute("frequency", (new StringBuilder()).append("").append(comModel.getFrequency()).toString());
            comElement.setAttribute("type", comModel.getType());
            comElement.setAttribute("name", comModel.getName());
            airportElement.appendChild(comElement);
        }

    }

    private void writeMarkers(AirportModel airportModel, Element fsDataElement, Document xmlDoc, boolean performingSaveAs)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("Marker");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == fsDataElement)
                fsDataElement.removeChild(nodeList.item(i));

        ArrayList markerAL = (ArrayList)airportModel.getMarkerAL().clone();
        if(!performingSaveAs)
            markerAL.addAll(airportModel.getUnusedMarkerAL());
        else
            airportModel.getUnusedMarkerAL().clear();
        for(int i = markerAL.size() - 1; i >= 0; i--)
        {
            MarkerModel markerModel = (MarkerModel)markerAL.get(i);
            Element markerElement = xmlDoc.createElement("Marker");
            markerElement.setAttribute("lat", latLonFormat.format(markerModel.getLatLon().getLat()));
            markerElement.setAttribute("lon", latLonFormat.format(markerModel.getLatLon().getLon()));
            markerElement.setAttribute("alt", (new StringBuilder()).append(markerModel.getAlt()).append(markerModel.getAltMeasure()).toString());
            markerElement.setAttribute("type", markerModel.getType());
            markerElement.setAttribute("heading", (new StringBuilder()).append("").append(markerModel.getHeading()).toString());
            if(markerModel.getRegion().length() > 0)
                markerElement.setAttribute("region", markerModel.getRegion());
            if(markerModel.getIdent().length() > 0)
                markerElement.setAttribute("ident", markerModel.getIdent());
            fsDataElement.appendChild(markerElement);
        }

    }

    private void writeNDBs(AirportModel airportModel, Element parentElement, Document xmlDoc, boolean terminal, boolean performingSaveAs)
    {
        NodeList nodeList = parentElement.getElementsByTagName("Ndb");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == parentElement)
                parentElement.removeChild(nodeList.item(i));

        ArrayList ndbAL = (ArrayList)airportModel.getNDBAL().clone();
        if(!performingSaveAs)
            ndbAL.addAll(airportModel.getUnusedNDBAL());
        else
            airportModel.getUnusedNDBAL().clear();
        for(int i = ndbAL.size() - 1; i >= 0; i--)
        {
            NDBModel ndbModel = (NDBModel)ndbAL.get(i);
            if(ndbModel.isTerminal() != terminal)
                continue;
            Element ndbElement = xmlDoc.createElement("Ndb");
            ndbElement.setAttribute("lat", latLonFormat.format(ndbModel.getLatLon().getLat()));
            ndbElement.setAttribute("lon", latLonFormat.format(ndbModel.getLatLon().getLon()));
            ndbElement.setAttribute("alt", (new StringBuilder()).append(ndbModel.getAlt()).append(ndbModel.getAltMeasure()).toString());
            ndbElement.setAttribute("type", ndbModel.getType());
            ndbElement.setAttribute("frequency", (new StringBuilder()).append("").append(ndbModel.getFrequency()).toString());
            ndbElement.setAttribute("range", (new StringBuilder()).append(ndbModel.getRange()).append(ndbModel.getRangeMeasure()).toString());
            ndbElement.setAttribute("magvar", (new StringBuilder()).append("").append(ndbModel.getMagvar()).toString());
            if(ndbModel.getRegion().length() > 0)
                ndbElement.setAttribute("region", ndbModel.getRegion());
            ndbElement.setAttribute("ident", ndbModel.getIdent());
            if(ndbModel.getName().length() > 0)
                ndbElement.setAttribute("name", ndbModel.getName());
            parentElement.appendChild(ndbElement);
        }

    }

    private void writeVORs(AirportModel airportModel, Element fsDataElement, Document xmlDoc, boolean performingSaveAs)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("Vor");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i).getParentNode() == fsDataElement)
                fsDataElement.removeChild(nodeList.item(i));

        ArrayList vorAL = (ArrayList)airportModel.getVORAL().clone();
        if(!performingSaveAs)
            vorAL.addAll(airportModel.getUnusedVORAL());
        else
            airportModel.getUnusedVORAL().clear();
        for(int i = vorAL.size() - 1; i >= 0; i--)
        {
            VORModel vorModel = (VORModel)vorAL.get(i);
            Element vorElement = xmlDoc.createElement("Vor");
            vorElement.setAttribute("dmeOnly", vorModel.getDmeOnly() ? "TRUE" : "FALSE");
            vorElement.setAttribute("dme", vorModel.getDme() ? "TRUE" : "FALSE");
            vorElement.setAttribute("lat", latLonFormat.format(vorModel.getLatLon().getLat()));
            vorElement.setAttribute("lon", latLonFormat.format(vorModel.getLatLon().getLon()));
            vorElement.setAttribute("alt", (new StringBuilder()).append(vorModel.getAlt()).append(vorModel.getAltMeasure()).toString());
            vorElement.setAttribute("type", vorModel.getType());
            vorElement.setAttribute("frequency", (new StringBuilder()).append("").append(vorModel.getFrequency()).toString());
            vorElement.setAttribute("range", (new StringBuilder()).append(vorModel.getRange()).append(vorModel.getRangeMeasure()).toString());
            vorElement.setAttribute("magvar", (new StringBuilder()).append("").append(vorModel.getMagvar()).toString());
            if(vorModel.getRegion().length() > 0)
                vorElement.setAttribute("region", vorModel.getRegion());
            vorElement.setAttribute("ident", vorModel.getIdent());
            if(vorModel.getName().length() > 0)
                vorElement.setAttribute("name", vorModel.getName());
            if(vorModel.getDme())
            {
                DMEModel dmeModel = vorModel.getDMEModel();
                Element dmeElement = xmlDoc.createElement("Dme");
                dmeElement.setAttribute("lat", latLonFormat.format(dmeModel.getLatLon().getLat()));
                dmeElement.setAttribute("lon", latLonFormat.format(dmeModel.getLatLon().getLon()));
                dmeElement.setAttribute("alt", (new StringBuilder()).append(dmeModel.getAlt()).append(dmeModel.getAltMeasure()).toString());
                vorElement.appendChild(dmeElement);
            }
            fsDataElement.appendChild(vorElement);
        }

    }

    private void writeWindsocks(AirportModel airportModel, Element fsDataElement, Document xmlDoc, boolean performingSaveAs)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("Windsock");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
        {
            Element sceneryElement = (Element)nodeList.item(i).getParentNode();
            if(sceneryElement.getParentNode() == fsDataElement)
                fsDataElement.removeChild(sceneryElement);
        }

        ArrayList windsockAL = airportModel.getWindsockAL();
        if(!performingSaveAs)
            windsockAL.addAll(airportModel.getUnusedWindsockAL());
        else
            airportModel.getUnusedWindsockAL().clear();
        for(int i = windsockAL.size() - 1; i >= 0; i--)
        {
            WindsockModel windsockModel = (WindsockModel)windsockAL.get(i);
            Element sceneryElement = xmlDoc.createElement("SceneryObject");
            fsDataElement.appendChild(sceneryElement);
            sceneryElement.setAttribute("lat", latLonFormat.format(windsockModel.getLatLon().getLat()));
            sceneryElement.setAttribute("lon", latLonFormat.format(windsockModel.getLatLon().getLon()));
            sceneryElement.setAttribute("alt", (new StringBuilder()).append(windsockModel.getAlt()).append(windsockModel.getAltMeasure()).toString());
            sceneryElement.setAttribute("altitudeIsAgl", windsockModel.getAltitudeIsAgl() ? "TRUE" : "FALSE");
            sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(windsockModel.getHeading()).toString());
            sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(windsockModel.getPitch()).toString());
            sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(windsockModel.getBank()).toString());
            sceneryElement.setAttribute("imageComplexity", (new StringBuilder()).append("").append(windsockModel.getImageComplexity()).toString());
            Element windsockElement = xmlDoc.createElement("Windsock");
            windsockElement.setAttribute("poleHeight", (new StringBuilder()).append("").append(windsockModel.getPoleHeight()).toString());
            windsockElement.setAttribute("sockLength", (new StringBuilder()).append("").append(windsockModel.getSockLength()).toString());
            windsockElement.setAttribute("lighted", windsockModel.getLighted() ? "TRUE" : "FALSE");
            sceneryElement.appendChild(windsockElement);
            Element poleColorElement = xmlDoc.createElement("PoleColor");
            poleColorElement.setAttribute("red", (new StringBuilder()).append("").append(windsockModel.getPoleColor().getRed()).toString());
            poleColorElement.setAttribute("blue", (new StringBuilder()).append("").append(windsockModel.getPoleColor().getBlue()).toString());
            poleColorElement.setAttribute("green", (new StringBuilder()).append("").append(windsockModel.getPoleColor().getGreen()).toString());
            windsockElement.appendChild(poleColorElement);
            Element sockColorElement = xmlDoc.createElement("SockColor");
            sockColorElement.setAttribute("red", (new StringBuilder()).append("").append(windsockModel.getSockColor().getRed()).toString());
            sockColorElement.setAttribute("blue", (new StringBuilder()).append("").append(windsockModel.getSockColor().getBlue()).toString());
            sockColorElement.setAttribute("green", (new StringBuilder()).append("").append(windsockModel.getSockColor().getGreen()).toString());
            windsockElement.appendChild(sockColorElement);
        }

    }

    private void writeTriggers(AirportModel airportModel, Element fsDataElement, Document xmlDoc, boolean performingSaveAs)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("Trigger");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
        {
            Element sceneryElement = (Element)nodeList.item(i).getParentNode();
            if(sceneryElement.getParentNode() == fsDataElement)
                fsDataElement.removeChild(sceneryElement);
        }

        ArrayList triggerAL = airportModel.getTriggerAL();
        if(!performingSaveAs)
            triggerAL.addAll(airportModel.getUnusedTriggerAL());
        else
            airportModel.getUnusedTriggerAL().clear();
        for(int i = triggerAL.size() - 1; i >= 0; i--)
        {
            TriggerModel triggerModel = (TriggerModel)triggerAL.get(i);
            Element sceneryElement = xmlDoc.createElement("SceneryObject");
            fsDataElement.appendChild(sceneryElement);
            LatLonPoint baseLatLon = ((VertexModel)triggerModel.getVertexAL().get(0)).getLatLon();
            sceneryElement.setAttribute("lat", latLonFormat.format(baseLatLon.getLat()));
            sceneryElement.setAttribute("lon", latLonFormat.format(baseLatLon.getLon()));
            sceneryElement.setAttribute("alt", "0");
            sceneryElement.setAttribute("altitudeIsAgl", "TRUE");
            sceneryElement.setAttribute("heading", "0");
            sceneryElement.setAttribute("pitch", "0");
            sceneryElement.setAttribute("bank", "0");
            sceneryElement.setAttribute("imageComplexity", "VERY_SPARSE");
            Element triggerElement = xmlDoc.createElement("Trigger");
            triggerElement.setAttribute("type", "REFUEL_REPAIR");
            sceneryElement.appendChild(triggerElement);
            if(!triggerModel.getType73().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "73");
                fuelElement.setAttribute("availability", triggerModel.getType73());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getType87().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "87");
                fuelElement.setAttribute("availability", triggerModel.getType87());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getType100().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "100");
                fuelElement.setAttribute("availability", triggerModel.getType100());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getType130().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "130");
                fuelElement.setAttribute("availability", triggerModel.getType130());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getType145().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "145");
                fuelElement.setAttribute("availability", triggerModel.getType145());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeMogas().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "MOGAS");
                fuelElement.setAttribute("availability", triggerModel.getTypeMogas());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJet().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET");
                fuelElement.setAttribute("availability", triggerModel.getTypeJet());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJetA().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETA");
                fuelElement.setAttribute("availability", triggerModel.getTypeJetA());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJetA1().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETA1");
                fuelElement.setAttribute("availability", triggerModel.getTypeJetA1());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJetAP().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETAP");
                fuelElement.setAttribute("availability", triggerModel.getTypeJetAP());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJetB().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JETB");
                fuelElement.setAttribute("availability", triggerModel.getTypeJetB());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJet4().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET4");
                fuelElement.setAttribute("availability", triggerModel.getTypeJet4());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeJet5().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "JET5");
                fuelElement.setAttribute("availability", triggerModel.getTypeJet5());
                triggerElement.appendChild(fuelElement);
            }
            if(!triggerModel.getTypeUnknown().equals("NO"))
            {
                Element fuelElement = xmlDoc.createElement("Fuel");
                fuelElement.setAttribute("type", "UNKNOWN");
                fuelElement.setAttribute("availability", triggerModel.getTypeUnknown());
                triggerElement.appendChild(fuelElement);
            }
            ArrayList vertexAL = triggerModel.getVertexAL();
            for(int j = 0; j < vertexAL.size(); j++)
            {
                VertexModel vertexModel = (VertexModel)vertexAL.get(j);
                Element vertexElement = xmlDoc.createElement("Vertex");
                triggerElement.appendChild(vertexElement);
                if(j == 0)
                {
                    vertexElement.setAttribute("biasX", "0");
                    vertexElement.setAttribute("biasZ", "0");
                    continue;
                }
                double biasX = 1000D * Utilities.getDistanceBetweenLatLons(baseLatLon.getLat(), baseLatLon.getLon(), baseLatLon.getLat(), vertexModel.getLatLon().getLon());
                double biasZ = 1000D * Utilities.getDistanceBetweenLatLons(baseLatLon.getLat(), baseLatLon.getLon(), vertexModel.getLatLon().getLat(), baseLatLon.getLon());
                if(vertexModel.getLatLon().getLon() < baseLatLon.getLon())
                    biasX *= -1D;
                if(vertexModel.getLatLon().getLat() < baseLatLon.getLat())
                    biasZ *= -1D;
                vertexElement.setAttribute("biasX", (new StringBuilder()).append("").append(biasX).toString());
                vertexElement.setAttribute("biasZ", (new StringBuilder()).append("").append(biasZ).toString());
            }

        }

    }

    private void writeScenery(AirportModel airportModel, Element fsDataElement, Document xmlDoc, boolean performingSaveAs)
    {
        NodeList nodeList = fsDataElement.getElementsByTagName("SceneryObject");
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
        {
            Element sceneryElement = (Element)nodeList.item(i);
            if(sceneryElement.getParentNode() != fsDataElement)
                continue;
            boolean shouldDelete = true;
            NodeList children = sceneryElement.getChildNodes();
            int j = 0;
            do
            {
                if(j >= children.getLength())
                    break;
                if((children.item(j) instanceof Element) && !((Element)children.item(j)).getTagName().equals("LibraryObject"))
                {
                    shouldDelete = false;
                    break;
                }
                j++;
            } while(true);
            if(shouldDelete)
                fsDataElement.removeChild(sceneryElement);
        }

        ArrayList sceneryAL = airportModel.getSceneryAL();
        if(!performingSaveAs)
            sceneryAL.addAll(airportModel.getUnusedSceneryAL());
        else
            airportModel.getUnusedSceneryAL().clear();
        for(int i = sceneryAL.size() - 1; i >= 0; i--)
        {
            SceneryModel sceneryModel = (SceneryModel)sceneryAL.get(i);
            Element sceneryElement = xmlDoc.createElement("SceneryObject");
            fsDataElement.appendChild(sceneryElement);
            sceneryElement.setAttribute("lat", latLonFormat.format(sceneryModel.getLatLon().getLat()));
            sceneryElement.setAttribute("lon", latLonFormat.format(sceneryModel.getLatLon().getLon()));
            sceneryElement.setAttribute("alt", (new StringBuilder()).append(sceneryModel.getAlt()).append(sceneryModel.getAltMeasure()).toString());
            sceneryElement.setAttribute("altitudeIsAgl", sceneryModel.getAltitudeIsAgl() ? "TRUE" : "FALSE");
            sceneryElement.setAttribute("heading", (new StringBuilder()).append("").append(sceneryModel.getHeading()).toString());
            sceneryElement.setAttribute("pitch", (new StringBuilder()).append("").append(sceneryModel.getPitch()).toString());
            sceneryElement.setAttribute("bank", (new StringBuilder()).append("").append(sceneryModel.getBank()).toString());
            sceneryElement.setAttribute("imageComplexity", sceneryModel.getImageComplexity());
            Element libraryElement = xmlDoc.createElement("LibraryObject");
            sceneryElement.appendChild(libraryElement);
            libraryElement.setAttribute("name", sceneryModel.getName());
            libraryElement.setAttribute("scale", (new StringBuilder()).append("").append(sceneryModel.getSceneryScale()).toString());
        }

    }

    private void writeBGImages(AirportModel airportModel, Element airportElement, Document xmlDoc)
    {
        NodeList nodeList = airportElement.getChildNodes();
        for(int i = nodeList.getLength() - 1; i >= 0; i--)
            if(nodeList.item(i) instanceof Comment)
                airportElement.removeChild(nodeList.item(i));

        if(SettingsEngine.getInstance().getWriteBGImages())
        {
            ArrayList bgImageAL = airportModel.getBGImageAL();
            StringBuffer buffer = new StringBuffer();
            for(int i = bgImageAL.size() - 1; i >= 0; i--)
            {
                BackgroundImageModel bgImageModel = (BackgroundImageModel)bgImageAL.get(i);
                Element backgroundImageElement = xmlDoc.createElement("BackgroundImage");
                backgroundImageElement.setAttribute("path", bgImageModel.getPath());
                backgroundImageElement.setAttribute("visible", bgImageModel.isImageVisible() ? "TRUE" : "FALSE");
                backgroundImageElement.setAttribute("topLat", latLonFormat.format(bgImageModel.getTopLeft().getLat()));
                backgroundImageElement.setAttribute("topLon", latLonFormat.format(bgImageModel.getTopLeft().getLon()));
                backgroundImageElement.setAttribute("bottomLat", latLonFormat.format(bgImageModel.getBottomRight().getLat()));
                backgroundImageElement.setAttribute("bottomLon", latLonFormat.format(bgImageModel.getBottomRight().getLon()));
                airportElement.appendChild(backgroundImageElement);
            }

        }
    }

    private NumberFormat latLonFormat;
    private static SaveEngine engine = null;

}
