// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:27:43 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SceneryDecompiler.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.AirportObject;
import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import com.zbluesoftware.fsxp.bgl.object.DMEObject;
import com.zbluesoftware.fsxp.bgl.object.ExclusionObject;
import com.zbluesoftware.fsxp.bgl.object.FuelTriggerObject;
import com.zbluesoftware.fsxp.bgl.object.GlideSlopeObject;
import com.zbluesoftware.fsxp.bgl.object.ICAONameListObject;
import com.zbluesoftware.fsxp.bgl.object.LocalizerObject;
import com.zbluesoftware.fsxp.bgl.object.MarkerObject;
import com.zbluesoftware.fsxp.bgl.object.NDBObject;
import com.zbluesoftware.fsxp.bgl.object.NameListNameObject;
import com.zbluesoftware.fsxp.bgl.object.NameListObject;
import com.zbluesoftware.fsxp.bgl.object.NameListsObject;
import com.zbluesoftware.fsxp.bgl.object.NameObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayObject;
import com.zbluesoftware.fsxp.bgl.object.SceneryObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwaySignObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwaySignsObject;
import com.zbluesoftware.fsxp.bgl.object.TriggerObject;
import com.zbluesoftware.fsxp.bgl.object.VORILSObject;
import com.zbluesoftware.fsxp.bgl.object.VertexObject;
import com.zbluesoftware.fsxp.bgl.object.WeatherTriggerObject;
import com.zbluesoftware.fsxp.bgl.object.WindsockObject;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

// Referenced classes of package com.zbluesoftware.fsxp.bgl:
//            BaseObjectSort

public class SceneryDecompiler
{

    private SceneryDecompiler()
    {
        byteData = new byte[256];
    }

    public static SceneryDecompiler getInstance()
    {
        if(decompiler == null)
            decompiler = new SceneryDecompiler();
        return decompiler;
    }

    public int decompileSceneryObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 64);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("0B") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            SceneryObject sceneryObject = new SceneryObject(i, length);
            boolean added = false;
            int j = baseObject.getObjectAL().size() - 1;
            do
            {
                if(j < 0)
                    break;
                if(((BaseObject)baseObject.getObjectAL().get(j)).getName().equals("SceneryObject"))
                {
                    baseObject.getObjectAL().add(j, sceneryObject);
                    added = true;
                    break;
                }
                j--;
            } while(true);
            if(!added)
                baseObject.addBaseObject(sceneryObject);
            sceneryObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            sceneryObject.setItemProperty("size", 5, displayHex(hexLength, true));
            sceneryObject.setItemProperty("size", 3, new Integer(2));
            sceneryObject.setItemProperty("longitude", 5, displayHex(getDWord(4), true));
            sceneryObject.setItemProperty("longitude", 3, new Integer(4));
            sceneryObject.setItemProperty("latitude", 5, displayHex(getDWord(8), true));
            sceneryObject.setItemProperty("latitude", 3, new Integer(8));
            sceneryObject.setItemProperty("altitude", 5, displayHex(getDWord(12), true));
            sceneryObject.setItemProperty("altitude", 3, new Integer(12));
            sceneryObject.setItemProperty("isAboveAGL", 5, displayHex(getWord(16), true));
            sceneryObject.setItemProperty("isAboveAGL", 3, new Integer(16));
            sceneryObject.setItemProperty("pitch", 5, displayHex(getWord(18), true));
            sceneryObject.setItemProperty("pitch", 3, new Integer(18));
            sceneryObject.setItemProperty("bank", 5, displayHex(getWord(20), true));
            sceneryObject.setItemProperty("bank", 3, new Integer(20));
            sceneryObject.setItemProperty("heading", 5, displayHex(getWord(22), true));
            sceneryObject.setItemProperty("heading", 3, new Integer(22));
            sceneryObject.setItemProperty("imageComplexity", 5, displayHex(getWord(24), true));
            sceneryObject.setItemProperty("imageComplexity", 3, new Integer(24));
            sceneryObject.setItemProperty("instanceId", 5, displayHex(getGUID(28), true));
            sceneryObject.setItemProperty("instanceId", 3, new Integer(28));
            sceneryObject.setItemProperty("name", 5, displayHex(getGUID(44), true));
            sceneryObject.setItemProperty("name", 3, new Integer(44));
            sceneryObject.setItemProperty("scale", 5, displayHex(getDWord(60), true));
            sceneryObject.setItemProperty("scale", 3, new Integer(60));
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileTriggerObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 50);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("10") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TriggerObject triggerObject = new TriggerObject(i, length);
            boolean added = false;
            int j = baseObject.getObjectAL().size() - 1;
            do
            {
                if(j < 0)
                    break;
                if(((BaseObject)baseObject.getObjectAL().get(j)).getName().equals("TriggerObject"))
                {
                    baseObject.getObjectAL().add(j, triggerObject);
                    added = true;
                    break;
                }
                j--;
            } while(true);
            if(!added)
                baseObject.addBaseObject(triggerObject);
            triggerObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            triggerObject.setItemProperty("size", 5, displayHex(hexLength, true));
            triggerObject.setItemProperty("size", 3, new Integer(2));
            triggerObject.setItemProperty("longitude", 5, displayHex(getDWord(4), true));
            triggerObject.setItemProperty("longitude", 3, new Integer(4));
            triggerObject.setItemProperty("latitude", 5, displayHex(getDWord(8), true));
            triggerObject.setItemProperty("latitude", 3, new Integer(8));
            triggerObject.setItemProperty("altitude", 5, displayHex(getDWord(12), true));
            triggerObject.setItemProperty("altitude", 3, new Integer(12));
            triggerObject.setItemProperty("isAboveAGL", 5, displayHex(getWord(16), true));
            triggerObject.setItemProperty("isAboveAGL", 3, new Integer(16));
            triggerObject.setItemProperty("pitch", 5, displayHex(getWord(18), true));
            triggerObject.setItemProperty("pitch", 3, new Integer(18));
            triggerObject.setItemProperty("bank", 5, displayHex(getWord(20), true));
            triggerObject.setItemProperty("bank", 3, new Integer(20));
            triggerObject.setItemProperty("heading", 5, displayHex(getWord(22), true));
            triggerObject.setItemProperty("heading", 3, new Integer(22));
            triggerObject.setItemProperty("imageComplexity", 5, displayHex(getWord(24), true));
            triggerObject.setItemProperty("imageComplexity", 3, new Integer(24));
            triggerObject.setItemProperty("instanceId", 5, displayHex(getGUID(28), true));
            triggerObject.setItemProperty("instanceId", 3, new Integer(28));
            triggerObject.setItemProperty("type", 5, displayHex(getWord(44), true));
            triggerObject.setItemProperty("type", 3, new Integer(44));
            triggerObject.setItemProperty("triggerHeight", 5, displayHex(getDWord(46), true));
            triggerObject.setItemProperty("triggerHeight", 3, new Integer(46));
            if(triggerObject.getItem("type").getDecodedData().equals("REFUEL_REPAIR"))
                decompileFuelTriggerObject(triggerObject, airportOffset, currentOffset + 50, raFile);
            if(triggerObject.getItem("type").getDecodedData().equals("WEATHER"))
                decompileWeatherTriggerObject(triggerObject, airportOffset, currentOffset + 50, raFile);
            return length;
        } else
        {
            return 0;
        }
    }

    private int decompileFuelTriggerObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 8);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        FuelTriggerObject fuelTriggerObject = new FuelTriggerObject(i, 8);
        baseObject.addBaseObject(fuelTriggerObject);
        fuelTriggerObject.setItemProperty("type73", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("type73", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("type87", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("type87", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("type100", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("type100", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("type130", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("type130", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("type145", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("type145", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeMOGAS", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeMOGAS", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJET", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJET", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJETA", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJETA", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJETA1", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJETA1", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJETAP", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJETAP", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJETB", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJETB", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJET4", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJET4", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("typeJET5", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("typeJET5", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("pistonType", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("pistonType", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("jetType", 5, displayHex(getDWord(0), true));
        fuelTriggerObject.setItemProperty("jetType", 3, new Integer(0));
        fuelTriggerObject.setItemProperty("vertexCount", 5, displayHex(getDWord(4), true));
        fuelTriggerObject.setItemProperty("vertexCount", 3, new Integer(4));
        int vertexCount = ((Integer)fuelTriggerObject.getItem("vertexCount").getDecodedData()).intValue();
        for(int j = 0; j < vertexCount; j++)
        {
            VertexObject vertexObject = new VertexObject(i + 8 + j * 8);
            fuelTriggerObject.addBaseObject(vertexObject);
            try
            {
                raFile.seek(i + 8 + j * 8);
                raFile.readFully(byteData, 0, 8);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            vertexObject.setItemProperty("biasX", 5, displayHex(getDWord(0), true));
            vertexObject.setItemProperty("biasX", 3, new Integer(0));
            vertexObject.setItemProperty("biasZ", 5, displayHex(getDWord(4), true));
            vertexObject.setItemProperty("biasZ", 3, new Integer(4));
            vertexObject.setName((new StringBuilder()).append("Vertex ").append(j).toString());
        }

        return 8 + vertexCount * 8;
    }

    private int decompileWeatherTriggerObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 14);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        WeatherTriggerObject weatherTriggerObject = new WeatherTriggerObject(i, 14);
        baseObject.addBaseObject(weatherTriggerObject);
        weatherTriggerObject.setItemProperty("type", 5, displayHex(getWord(0), true));
        weatherTriggerObject.setItemProperty("type", 3, new Integer(0));
        weatherTriggerObject.setItemProperty("heading", 5, displayHex(getDWord(2), true));
        weatherTriggerObject.setItemProperty("heading", 3, new Integer(2));
        weatherTriggerObject.setItemProperty("scalar", 5, displayHex(getDWord(6), true));
        weatherTriggerObject.setItemProperty("scalar", 3, new Integer(6));
        weatherTriggerObject.setItemProperty("vertexCount", 5, displayHex(getDWord(10), true));
        weatherTriggerObject.setItemProperty("vertexCount", 3, new Integer(10));
        int vertexCount = ((Integer)weatherTriggerObject.getItem("vertexCount").getDecodedData()).intValue();
        for(int j = 0; j < vertexCount; j++)
        {
            VertexObject vertexObject = new VertexObject(i + 8 + j * 8);
            weatherTriggerObject.addBaseObject(vertexObject);
            try
            {
                raFile.seek(i + 8 + j * 8);
                raFile.readFully(byteData, 0, 8);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            vertexObject.setItemProperty("biasX", 5, displayHex(getDWord(0), true));
            vertexObject.setItemProperty("biasX", 3, new Integer(0));
            vertexObject.setItemProperty("biasZ", 5, displayHex(getDWord(4), true));
            vertexObject.setItemProperty("biasZ", 3, new Integer(4));
            vertexObject.setName((new StringBuilder()).append("Vertex ").append(j).toString());
        }

        return 14 + vertexCount * 8;
    }

    public int decompileTaxiwaySignsObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 48);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("0E") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TaxiwaySignsObject taxiwaySignsObject = new TaxiwaySignsObject(i, length);
            taxiwaySignsObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            taxiwaySignsObject.setItemProperty("size", 5, displayHex(hexLength, true));
            taxiwaySignsObject.setItemProperty("size", 3, new Integer(2));
            taxiwaySignsObject.setItemProperty("longitude", 5, displayHex(getDWord(4), true));
            taxiwaySignsObject.setItemProperty("longitude", 3, new Integer(4));
            taxiwaySignsObject.setItemProperty("latitude", 5, displayHex(getDWord(8), true));
            taxiwaySignsObject.setItemProperty("latitude", 3, new Integer(8));
            taxiwaySignsObject.setItemProperty("altitude", 5, displayHex(getDWord(12), true));
            taxiwaySignsObject.setItemProperty("altitude", 3, new Integer(12));
            taxiwaySignsObject.setItemProperty("isAboveAGL", 5, displayHex(getWord(16), true));
            taxiwaySignsObject.setItemProperty("isAboveAGL", 3, new Integer(16));
            taxiwaySignsObject.setItemProperty("pitch", 5, displayHex(getWord(18), true));
            taxiwaySignsObject.setItemProperty("pitch", 3, new Integer(18));
            taxiwaySignsObject.setItemProperty("bank", 5, displayHex(getWord(20), true));
            taxiwaySignsObject.setItemProperty("bank", 3, new Integer(20));
            taxiwaySignsObject.setItemProperty("heading", 5, displayHex(getWord(22), true));
            taxiwaySignsObject.setItemProperty("heading", 3, new Integer(22));
            taxiwaySignsObject.setItemProperty("imageComplexity", 5, displayHex(getWord(24), true));
            taxiwaySignsObject.setItemProperty("imageComplexity", 3, new Integer(24));
            taxiwaySignsObject.setItemProperty("instanceId", 5, displayHex(getGUID(28), true));
            taxiwaySignsObject.setItemProperty("instanceId", 3, new Integer(28));
            taxiwaySignsObject.setItemProperty("signCount", 5, displayHex(getWord(44), true));
            taxiwaySignsObject.setItemProperty("signCount", 3, new Integer(44));
            int signCount = ((Integer)taxiwaySignsObject.getItem("signCount").getDecodedData()).intValue();
            int signOffset = currentOffset + 48;
            for(int j = 0; j < signCount; j++)
                signOffset += decompileTaxiwaySignObject(taxiwaySignsObject, airportOffset, signOffset, raFile);

            double taxiwayLat = ((Double)taxiwaySignsObject.getItem("latitude").getDecodedData()).doubleValue();
            double taxiwayLon = ((Double)taxiwaySignsObject.getItem("longitude").getDecodedData()).doubleValue();
            AirportObject currentAirport = null;
            double currentDistance = 1.7976931348623157E+308D;
            for(int j = baseObject.getObjectAL().size() - 1; j >= 0; j--)
            {
                if(!(baseObject.getObjectAL().get(j) instanceof AirportObject))
                    continue;
                AirportObject airportObject = (AirportObject)baseObject.getObjectAL().get(j);
                double airportDistance = Utilities.getDistanceBetweenLatLons(taxiwayLat, taxiwayLon, ((Double)airportObject.getItem("latitude").getDecodedData()).doubleValue(), ((Double)airportObject.getItem("longitude").getDecodedData()).doubleValue());
                if(airportDistance < currentDistance)
                {
                    currentDistance = airportDistance;
                    currentAirport = airportObject;
                }
            }

            if(currentAirport != null)
                currentAirport.addBaseObject(taxiwaySignsObject);
            else
                baseObject.addBaseObject(taxiwaySignsObject);
            return length;
        } else
        {
            return 0;
        }
    }

    private int decompileTaxiwaySignObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 100);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        TaxiwaySignObject taxiwaySignObject = new TaxiwaySignObject(i, 12);
        baseObject.addBaseObject(taxiwaySignObject);
        taxiwaySignObject.setItemProperty("lonOffset", 5, displayHex(getDWord(0), true));
        taxiwaySignObject.setItemProperty("lonOffset", 3, new Integer(0));
        taxiwaySignObject.setItemProperty("latOffset", 5, displayHex(getDWord(4), true));
        taxiwaySignObject.setItemProperty("latOffset", 3, new Integer(4));
        taxiwaySignObject.setItemProperty("heading", 5, displayHex(getWord(8), true));
        taxiwaySignObject.setItemProperty("heading", 3, new Integer(8));
        taxiwaySignObject.setItemProperty("size", 5, convertByteToHex(byteData[10]));
        taxiwaySignObject.setItemProperty("size", 3, new Integer(10));
        taxiwaySignObject.setItemProperty("justification", 5, convertByteToHex(byteData[11]));
        taxiwaySignObject.setItemProperty("justification", 3, new Integer(11));
        int labelLength;
        for(labelLength = 2; !convertByteToHex(byteData[11 + labelLength]).equals("00"); labelLength += 2);
        StringBuffer buffer = new StringBuffer();
        for(int j = 12; j < 12 + labelLength; j++)
        {
            if(buffer.length() > 0)
                buffer.append(" ");
            buffer.append(convertByteToHex(byteData[j]));
        }

        taxiwaySignObject.setItemProperty("label", 5, buffer.toString());
        taxiwaySignObject.setItemProperty("label", 3, new Integer(12));
        taxiwaySignObject.setItemProperty("label", 4, new Integer(labelLength));
        taxiwaySignObject.setLength(12 + labelLength);
        return 12 + labelLength;
    }

    public int decompileMarkerObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 40);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("18") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            MarkerObject markerObject = new MarkerObject(i, length);
            baseObject.addBaseObject(markerObject);
            markerObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            markerObject.setItemProperty("size", 5, displayHex(hexLength, true));
            markerObject.setItemProperty("size", 3, new Integer(2));
            markerObject.setItemProperty("heading", 5, displayHex(getWord(5), true));
            markerObject.setItemProperty("heading", 3, new Integer(5));
            markerObject.setItemProperty("type", 5, convertByteToHex(byteData[7]));
            markerObject.setItemProperty("type", 3, new Integer(7));
            markerObject.setItemProperty("longitude", 5, displayHex(getDWord(8), true));
            markerObject.setItemProperty("longitude", 3, new Integer(8));
            markerObject.setItemProperty("latitude", 5, displayHex(getDWord(12), true));
            markerObject.setItemProperty("latitude", 3, new Integer(12));
            markerObject.setItemProperty("altitude", 5, displayHex(getDWord(16), true));
            markerObject.setItemProperty("altitude", 3, new Integer(16));
            markerObject.setItemProperty("icao", 5, displayHex(getDWord(20), true));
            markerObject.setItemProperty("icao", 3, new Integer(20));
            markerObject.setItemProperty("region", 5, displayHex(getWord(24), true));
            markerObject.setItemProperty("region", 3, new Integer(24));
            markerObject.setName((new StringBuilder()).append("Marker ").append((String)markerObject.getItem("type").getDecodedData()).toString());
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileNDBObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 40);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("17") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            NDBObject ndbObject = new NDBObject(i, length);
            ndbObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            ndbObject.setItemProperty("size", 5, displayHex(hexLength, true));
            ndbObject.setItemProperty("size", 3, new Integer(2));
            ndbObject.setItemProperty("type", 5, displayHex(getWord(6), true));
            ndbObject.setItemProperty("type", 3, new Integer(6));
            ndbObject.setItemProperty("frequency", 5, displayHex(getDWord(8), true));
            ndbObject.setItemProperty("frequency", 3, new Integer(8));
            ndbObject.setItemProperty("longitude", 5, displayHex(getDWord(12), true));
            ndbObject.setItemProperty("longitude", 3, new Integer(12));
            ndbObject.setItemProperty("latitude", 5, displayHex(getDWord(16), true));
            ndbObject.setItemProperty("latitude", 3, new Integer(16));
            ndbObject.setItemProperty("altitude", 5, displayHex(getDWord(20), true));
            ndbObject.setItemProperty("altitude", 3, new Integer(20));
            ndbObject.setItemProperty("range", 5, displayHex(getDWord(24), true));
            ndbObject.setItemProperty("range", 3, new Integer(24));
            ndbObject.setItemProperty("magVar", 5, displayHex(getDWord(28), true));
            ndbObject.setItemProperty("magVar", 3, new Integer(28));
            ndbObject.setItemProperty("icao", 5, displayHex(getDWord(32), true));
            ndbObject.setItemProperty("icao", 3, new Integer(32));
            ndbObject.setItemProperty("region", 5, displayHex(getDWord(36), true));
            ndbObject.setItemProperty("region", 3, new Integer(36));
            ndbObject.setItemProperty("airportIcao", 5, displayHex(getDWord(36), true));
            ndbObject.setItemProperty("airportIcao", 3, new Integer(36));
            decompileNameObject(ndbObject, airportOffset, currentOffset + 40, raFile);
            if(((String)ndbObject.getItem("airportIcao").getDecodedData()).length() > 0)
            {
                String airportIcao = (String)ndbObject.getItem("airportIcao").getDecodedData();
                int j = baseObject.getObjectAL().size() - 1;
                do
                {
                    if(j < 0)
                        break;
                    if(baseObject.getObjectAL().get(j) instanceof AirportObject)
                    {
                        AirportObject airportObject = (AirportObject)baseObject.getObjectAL().get(j);
                        if(airportObject.getItem("icao").getDecodedData().equals(airportIcao))
                        {
                            airportObject.addBaseObject(ndbObject);
                            break;
                        }
                    }
                    j--;
                } while(true);
            } else
            {
                baseObject.addBaseObject(ndbObject);
            }
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileVORILSObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 40);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("13") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            VORILSObject vorILSObject = new VORILSObject(i, length);
            vorILSObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            vorILSObject.setItemProperty("size", 5, displayHex(hexLength, true));
            vorILSObject.setItemProperty("size", 3, new Integer(2));
            vorILSObject.setItemProperty("type", 5, convertByteToHex(byteData[6]));
            vorILSObject.setItemProperty("type", 3, new Integer(6));
            vorILSObject.setItemProperty("dmeOnly", 5, convertByteToHex(byteData[7]));
            vorILSObject.setItemProperty("dmeOnly", 3, new Integer(7));
            vorILSObject.setItemProperty("backCourse", 5, convertByteToHex(byteData[7]));
            vorILSObject.setItemProperty("backCourse", 3, new Integer(7));
            vorILSObject.setItemProperty("glideSlope", 5, convertByteToHex(byteData[7]));
            vorILSObject.setItemProperty("glideSlope", 3, new Integer(7));
            vorILSObject.setItemProperty("dme", 5, convertByteToHex(byteData[7]));
            vorILSObject.setItemProperty("dme", 3, new Integer(7));
            vorILSObject.setItemProperty("navTrue", 5, convertByteToHex(byteData[7]));
            vorILSObject.setItemProperty("navTrue", 3, new Integer(7));
            vorILSObject.setItemProperty("longitude", 5, displayHex(getDWord(8), true));
            vorILSObject.setItemProperty("longitude", 3, new Integer(8));
            vorILSObject.setItemProperty("latitude", 5, displayHex(getDWord(12), true));
            vorILSObject.setItemProperty("latitude", 3, new Integer(12));
            vorILSObject.setItemProperty("altitude", 5, displayHex(getDWord(16), true));
            vorILSObject.setItemProperty("altitude", 3, new Integer(16));
            vorILSObject.setItemProperty("frequency", 5, displayHex(getDWord(20), true));
            vorILSObject.setItemProperty("frequency", 3, new Integer(20));
            vorILSObject.setItemProperty("range", 5, displayHex(getDWord(24), true));
            vorILSObject.setItemProperty("range", 3, new Integer(24));
            vorILSObject.setItemProperty("magVar", 5, displayHex(getDWord(28), true));
            vorILSObject.setItemProperty("magVar", 3, new Integer(28));
            vorILSObject.setItemProperty("icao", 5, displayHex(getDWord(32), true));
            vorILSObject.setItemProperty("icao", 3, new Integer(32));
            vorILSObject.setItemProperty("region", 5, displayHex(getDWord(36), true));
            vorILSObject.setItemProperty("region", 3, new Integer(36));
            vorILSObject.setItemProperty("airportIcao", 5, displayHex(getDWord(36), true));
            vorILSObject.setItemProperty("airportIcao", 3, new Integer(36));
            int vorILSOffset = currentOffset + 40;
            if(((String)vorILSObject.getItem("type").getDecodedData()).equals("ILS"))
            {
                vorILSOffset += decompileLocalizerObject(vorILSObject, airportOffset, vorILSOffset, raFile);
                if(((String)vorILSObject.getItem("glideSlope").getDecodedData()).equals("TRUE"))
                    vorILSOffset += decompileGlideSlopeObject(vorILSObject, airportOffset, vorILSOffset, raFile);
            }
            if(((String)vorILSObject.getItem("dme").getDecodedData()).equals("TRUE"))
                vorILSOffset += decompileDMEObject(vorILSObject, airportOffset, vorILSOffset, raFile);
            decompileNameObject(vorILSObject, airportOffset, vorILSOffset, raFile);
            if(((String)vorILSObject.getItem("type").getDecodedData()).equals("ILS"))
                vorILSObject.setName((new StringBuilder()).append("ILS: ").append((String)vorILSObject.getItem("icao").getDecodedData()).toString());
            else
                vorILSObject.setName((new StringBuilder()).append("VOR: ").append((String)vorILSObject.getItem("icao").getDecodedData()).toString());
            if(((String)vorILSObject.getItem("type").getDecodedData()).equals("ILS") && ((String)vorILSObject.getItem("airportIcao").getDecodedData()).length() > 0)
            {
                String airportIcao = (String)vorILSObject.getItem("airportIcao").getDecodedData();
                String ilsIcao = (String)vorILSObject.getItem("icao").getDecodedData();
                int j = baseObject.getObjectAL().size() - 1;
                do
                {
                    if(j < 0)
                        break;
                    if(baseObject.getObjectAL().get(j) instanceof AirportObject)
                    {
                        AirportObject airportObject = (AirportObject)baseObject.getObjectAL().get(j);
                        if(airportObject.getItem("icao").getDecodedData().equals(airportIcao))
                        {
                            for(int k = airportObject.getObjectAL().size() - 1; k >= 0; k--)
                                if(airportObject.getObjectAL().get(k) instanceof RunwayObject)
                                {
                                    RunwayObject runwayObject = (RunwayObject)airportObject.getObjectAL().get(k);
                                    if(runwayObject.getItem("primaryILSIcao").getDecodedData().equals(ilsIcao))
                                    {
                                        vorILSObject.setItemProperty("end", 5, "PRIMARY");
                                        runwayObject.addBaseObject(vorILSObject);
                                    } else
                                    if(runwayObject.getItem("secondaryILSIcao").getDecodedData().equals(ilsIcao))
                                    {
                                        vorILSObject.setItemProperty("end", 5, "SECONDARY");
                                        runwayObject.addBaseObject(vorILSObject);
                                    } else
                                    {
                                        for(int ii = vorILSObject.getObjectAL().size() - 1; ii >= 0; ii--)
                                        {
                                            if(!(vorILSObject.getObjectAL().get(ii) instanceof NameObject))
                                                continue;
                                            NameObject nameObject = (NameObject)vorILSObject.getObjectAL().get(ii);
                                            String name = (String)nameObject.getItem("name").getDecodedData();
                                            if(name.endsWith((String)runwayObject.getItem("primaryRunwayNumber").getDecodedData()))
                                            {
                                                vorILSObject.setItemProperty("end", 5, "PRIMARY");
                                                runwayObject.addBaseObject(vorILSObject);
                                                continue;
                                            }
                                            if(name.endsWith((String)runwayObject.getItem("secondaryRunwayNumber").getDecodedData()))
                                            {
                                                vorILSObject.setItemProperty("end", 5, "SECONDARY");
                                                runwayObject.addBaseObject(vorILSObject);
                                            }
                                        }

                                    }
                                }

                            break;
                        }
                    }
                    j--;
                } while(true);
            } else
            {
                baseObject.addBaseObject(vorILSObject);
            }
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileLocalizerObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 16);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("14") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            LocalizerObject localizerObject = new LocalizerObject(i, length);
            baseObject.addBaseObject(localizerObject);
            localizerObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            localizerObject.setItemProperty("size", 5, displayHex(hexLength, true));
            localizerObject.setItemProperty("size", 3, new Integer(2));
            localizerObject.setItemProperty("heading", 5, displayHex(getDWord(8), true));
            localizerObject.setItemProperty("heading", 3, new Integer(8));
            localizerObject.setItemProperty("width", 5, displayHex(getDWord(12), true));
            localizerObject.setItemProperty("width", 3, new Integer(12));
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileGlideSlopeObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 28);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("15") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            GlideSlopeObject glideSlopeObject = new GlideSlopeObject(i, length);
            baseObject.addBaseObject(glideSlopeObject);
            glideSlopeObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            glideSlopeObject.setItemProperty("size", 5, displayHex(hexLength, true));
            glideSlopeObject.setItemProperty("size", 3, new Integer(2));
            glideSlopeObject.setItemProperty("longitude", 5, displayHex(getDWord(8), true));
            glideSlopeObject.setItemProperty("longitude", 3, new Integer(8));
            glideSlopeObject.setItemProperty("latitude", 5, displayHex(getDWord(12), true));
            glideSlopeObject.setItemProperty("latitude", 3, new Integer(12));
            glideSlopeObject.setItemProperty("altitude", 5, displayHex(getDWord(16), true));
            glideSlopeObject.setItemProperty("altitude", 3, new Integer(16));
            glideSlopeObject.setItemProperty("range", 5, displayHex(getDWord(20), true));
            glideSlopeObject.setItemProperty("range", 3, new Integer(20));
            glideSlopeObject.setItemProperty("pitch", 5, displayHex(getDWord(24), true));
            glideSlopeObject.setItemProperty("pitch", 3, new Integer(24));
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileDMEObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 24);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("16") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            DMEObject dmeObject = new DMEObject(i, length);
            baseObject.addBaseObject(dmeObject);
            dmeObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            dmeObject.setItemProperty("size", 5, displayHex(hexLength, true));
            dmeObject.setItemProperty("size", 3, new Integer(2));
            dmeObject.setItemProperty("longitude", 5, displayHex(getDWord(8), true));
            dmeObject.setItemProperty("longitude", 3, new Integer(8));
            dmeObject.setItemProperty("latitude", 5, displayHex(getDWord(12), true));
            dmeObject.setItemProperty("latitude", 3, new Integer(12));
            dmeObject.setItemProperty("altitude", 5, displayHex(getDWord(16), true));
            dmeObject.setItemProperty("altitude", 3, new Integer(16));
            dmeObject.setItemProperty("range", 5, displayHex(getDWord(20), true));
            dmeObject.setItemProperty("range", 3, new Integer(20));
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileWindsockObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 62);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("0C") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            WindsockObject windsockObject = new WindsockObject(i, length);
            boolean added = false;
            int j = baseObject.getObjectAL().size() - 1;
            do
            {
                if(j < 0)
                    break;
                if(((BaseObject)baseObject.getObjectAL().get(j)).getName().equals("WindsockObject"))
                {
                    baseObject.getObjectAL().add(j, windsockObject);
                    added = true;
                    break;
                }
                j--;
            } while(true);
            if(!added)
                baseObject.addBaseObject(windsockObject);
            windsockObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            windsockObject.setItemProperty("size", 5, displayHex(hexLength, true));
            windsockObject.setItemProperty("size", 3, new Integer(2));
            windsockObject.setItemProperty("longitude", 5, displayHex(getDWord(4), true));
            windsockObject.setItemProperty("longitude", 3, new Integer(4));
            windsockObject.setItemProperty("latitude", 5, displayHex(getDWord(8), true));
            windsockObject.setItemProperty("latitude", 3, new Integer(8));
            windsockObject.setItemProperty("altitude", 5, displayHex(getDWord(12), true));
            windsockObject.setItemProperty("altitude", 3, new Integer(12));
            windsockObject.setItemProperty("pitch", 5, displayHex(getWord(18), true));
            windsockObject.setItemProperty("pitch", 3, new Integer(18));
            windsockObject.setItemProperty("bank", 5, displayHex(getWord(20), true));
            windsockObject.setItemProperty("bank", 3, new Integer(20));
            windsockObject.setItemProperty("heading", 5, displayHex(getWord(22), true));
            windsockObject.setItemProperty("heading", 3, new Integer(22));
            windsockObject.setItemProperty("imageComplexity", 5, displayHex(getWord(24), true));
            windsockObject.setItemProperty("imageComplexity", 3, new Integer(24));
            windsockObject.setItemProperty("instanceId", 5, displayHex(getGUID(28), true));
            windsockObject.setItemProperty("instanceId", 3, new Integer(28));
            windsockObject.setItemProperty("poleHeight", 5, displayHex(getDWord(44), true));
            windsockObject.setItemProperty("poleHeight", 3, new Integer(44));
            windsockObject.setItemProperty("sockLength", 5, displayHex(getDWord(48), true));
            windsockObject.setItemProperty("sockLength", 3, new Integer(48));
            windsockObject.setItemProperty("poleBlue", 5, convertByteToHex(byteData[52]));
            windsockObject.setItemProperty("poleBlue", 3, new Integer(52));
            windsockObject.setItemProperty("poleGreen", 5, convertByteToHex(byteData[53]));
            windsockObject.setItemProperty("poleGreen", 3, new Integer(53));
            windsockObject.setItemProperty("poleRed", 5, convertByteToHex(byteData[54]));
            windsockObject.setItemProperty("poleRed", 3, new Integer(54));
            windsockObject.setItemProperty("poleAlpha", 5, convertByteToHex(byteData[55]));
            windsockObject.setItemProperty("poleAlpha", 3, new Integer(55));
            windsockObject.setItemProperty("sockBlue", 5, convertByteToHex(byteData[56]));
            windsockObject.setItemProperty("sockBlue", 3, new Integer(56));
            windsockObject.setItemProperty("sockGreen", 5, convertByteToHex(byteData[57]));
            windsockObject.setItemProperty("sockGreen", 3, new Integer(57));
            windsockObject.setItemProperty("sockRed", 5, convertByteToHex(byteData[58]));
            windsockObject.setItemProperty("sockRed", 3, new Integer(58));
            windsockObject.setItemProperty("sockAlpha", 5, convertByteToHex(byteData[59]));
            windsockObject.setItemProperty("sockAlpha", 3, new Integer(59));
            windsockObject.setItemProperty("lighted", 5, displayHex(getWord(60), true));
            windsockObject.setItemProperty("lighted", 3, new Integer(60));
            return length;
        } else
        {
            return 0;
        }
    }

    public int decompileExclusionRectangle(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 20);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        ExclusionObject exclusionObject = new ExclusionObject(i, 20);
        baseObject.addBaseObject(exclusionObject);
        exclusionObject.setItemProperty("excludeAll", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("excludeAll", 3, new Integer(0));
        exclusionObject.setItemProperty("beaconObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("beaconObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("effectObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("effectObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("genericBuildingObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("genericBuildingObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("libraryObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("libraryObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("taxiwaySignObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("taxiwaySignObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("triggerObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("triggerObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("windsockObjects", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("windsockObjects", 3, new Integer(0));
        exclusionObject.setItemProperty("extrusionBridges", 5, displayHex(getWord(0), true));
        exclusionObject.setItemProperty("extrusionBridges", 3, new Integer(0));
        exclusionObject.setItemProperty("size", 5, displayHex(getWord(2), true));
        exclusionObject.setItemProperty("size", 3, new Integer(2));
        exclusionObject.setItemProperty("topLeftLon", 5, displayHex(getDWord(4), true));
        exclusionObject.setItemProperty("topLeftLon", 3, new Integer(4));
        exclusionObject.setItemProperty("topLeftLat", 5, displayHex(getDWord(8), true));
        exclusionObject.setItemProperty("topLeftLat", 3, new Integer(8));
        exclusionObject.setItemProperty("bottomRightLon", 5, displayHex(getDWord(12), true));
        exclusionObject.setItemProperty("bottomRightLon", 3, new Integer(12));
        exclusionObject.setItemProperty("bottomRightLat", 5, displayHex(getDWord(16), true));
        exclusionObject.setItemProperty("bottomRightLat", 3, new Integer(16));
        return 20;
    }

    public int decompileNameList(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 42);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("27") && convertByteToHex(byteData[1]).equals("00"))
        {
            int length = 42;
            NameListsObject nameListsObject = new NameListsObject(i, length);
            baseObject.addBaseObject(nameListsObject);
            nameListsObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            nameListsObject.setItemProperty("size", 5, displayHex(getDWord(2), true));
            nameListsObject.setItemProperty("size", 3, new Integer(2));
            nameListsObject.setItemProperty("regionCount", 5, displayHex(getWord(6), true));
            nameListsObject.setItemProperty("regionCount", 3, new Integer(6));
            nameListsObject.setItemProperty("countryCount", 5, displayHex(getWord(8), true));
            nameListsObject.setItemProperty("countryCount", 3, new Integer(8));
            nameListsObject.setItemProperty("stateCount", 5, displayHex(getWord(10), true));
            nameListsObject.setItemProperty("stateCount", 3, new Integer(10));
            nameListsObject.setItemProperty("cityCount", 5, displayHex(getWord(12), true));
            nameListsObject.setItemProperty("cityCount", 3, new Integer(12));
            nameListsObject.setItemProperty("airportNameCount", 5, displayHex(getWord(14), true));
            nameListsObject.setItemProperty("airportNameCount", 3, new Integer(14));
            nameListsObject.setItemProperty("icaoCount", 5, displayHex(getWord(16), true));
            nameListsObject.setItemProperty("icaoCount", 3, new Integer(16));
            nameListsObject.setItemProperty("regionOffset", 5, displayHex(getDWord(18), true));
            nameListsObject.setItemProperty("regionOffset", 3, new Integer(18));
            nameListsObject.setItemProperty("countryOffset", 5, displayHex(getDWord(22), true));
            nameListsObject.setItemProperty("countryOffset", 3, new Integer(22));
            nameListsObject.setItemProperty("stateOffset", 5, displayHex(getDWord(26), true));
            nameListsObject.setItemProperty("stateOffset", 3, new Integer(26));
            nameListsObject.setItemProperty("cityOffset", 5, displayHex(getDWord(30), true));
            nameListsObject.setItemProperty("cityOffset", 3, new Integer(30));
            nameListsObject.setItemProperty("airportNameOffset", 5, displayHex(getDWord(34), true));
            nameListsObject.setItemProperty("airportNameOffset", 3, new Integer(34));
            nameListsObject.setItemProperty("icaoOffset", 5, displayHex(getDWord(38), true));
            nameListsObject.setItemProperty("icaoOffset", 3, new Integer(38));
            length += decompileSpecificNameList(nameListsObject, "Regions", airportOffset, ((Integer)nameListsObject.getItem("regionCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("regionOffset").getDecodedData()).intValue(), raFile);
            length += decompileSpecificNameList(nameListsObject, "Countries", airportOffset, ((Integer)nameListsObject.getItem("countryCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("countryOffset").getDecodedData()).intValue(), raFile);
            length += decompileSpecificNameList(nameListsObject, "States", airportOffset, ((Integer)nameListsObject.getItem("stateCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("stateOffset").getDecodedData()).intValue(), raFile);
            length += decompileSpecificNameList(nameListsObject, "Cities", airportOffset, ((Integer)nameListsObject.getItem("cityCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("cityOffset").getDecodedData()).intValue(), raFile);
            length += decompileSpecificNameList(nameListsObject, "AirportNames", airportOffset, ((Integer)nameListsObject.getItem("airportNameCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("airportNameOffset").getDecodedData()).intValue(), raFile);
            length += decompileIcaoList(nameListsObject, airportOffset, ((Integer)nameListsObject.getItem("icaoCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("icaoOffset").getDecodedData()).intValue(), raFile);
            updateAirportInformation(baseObject, nameListsObject);
            nameListsObject.setLength(length);
            return length;
        } else
        {
            return 0;
        }
    }

    private int decompileSpecificNameList(BaseObject baseObject, String name, int airportOffset, int count, int offset, RandomAccessFile raFile)
    {
        int i = airportOffset + offset;
        int length = 0;
        NameListObject nameListObject = new NameListObject(name, i, 4);
        baseObject.addBaseObject(nameListObject);
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 4);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        nameListObject.setItemProperty("index", 5, displayHex(getDWord(0), true));
        nameListObject.setItemProperty("index", 3, new Integer(0));
        for(int j = 0; j < count; j++)
        {
            NameListNameObject nameObject = new NameListNameObject();
            nameListObject.addBaseObject(nameObject);
            try
            {
                raFile.seek(i + j * 4);
                raFile.readFully(byteData, 0, 4);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            int nameOffset = (int)Long.parseLong(getDWord(0), 16);
            nameOffset += count * 4;
            try
            {
                raFile.seek(i + nameOffset);
                raFile.readFully(byteData, 0, (int)Math.min(256L, raFile.length() - (long)(i + nameOffset)));
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            int tempOffset;
            for(tempOffset = 0; !convertByteToHex(byteData[tempOffset]).equals("00"); tempOffset++);
            tempOffset++;
            StringBuffer buffer = new StringBuffer();
            for(int k = 0; k < tempOffset; k++)
            {
                if(buffer.length() > 0)
                    buffer.append(" ");
                buffer.append(convertByteToHex(byteData[k]));
            }

            nameObject.setItemProperty("name", 5, buffer.toString());
            nameObject.setItemProperty("name", 4, new Integer(tempOffset - nameOffset));
            nameObject.setOffset(i + nameOffset);
            nameObject.setLength(tempOffset - nameOffset);
            length += (tempOffset - nameOffset) + 4;
        }

        nameListObject.setLength(length);
        return length;
    }

    private int decompileIcaoList(BaseObject baseObject, int airportOffset, int count, int offset, RandomAccessFile raFile)
    {
        int i = airportOffset + offset;
        ArrayList arrayList = new ArrayList();
        for(int j = 0; j < count; j++)
        {
            ICAONameListObject icaoObject = new ICAONameListObject(i + j * 20, 20);
            arrayList.add(icaoObject);
            try
            {
                raFile.seek(i + j * 20);
                raFile.readFully(byteData, 0, 20);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            icaoObject.setItemProperty("regionIndex", 5, convertByteToHex(byteData[0]));
            icaoObject.setItemProperty("regionIndex", 3, new Integer(j * 20));
            icaoObject.setItemProperty("countryIndex", 5, convertByteToHex(byteData[1]));
            icaoObject.setItemProperty("countryIndex", 3, new Integer(1 + j * 20));
            icaoObject.setItemProperty("stateIndex", 5, displayHex(getWord(2), true));
            icaoObject.setItemProperty("stateIndex", 3, new Integer(2 + j * 20));
            icaoObject.setItemProperty("cityIndex", 5, convertByteToHex(byteData[4]));
            icaoObject.setItemProperty("cityIndex", 3, new Integer(4 + j * 20));
            icaoObject.setItemProperty("airportNameIndex", 5, convertByteToHex(byteData[6]));
            icaoObject.setItemProperty("airportNameIndex", 3, new Integer(6 + j * 20));
            icaoObject.setItemProperty("icao", 5, displayHex(getDWord(8), true));
            icaoObject.setItemProperty("icao", 3, new Integer(8 + j * 20));
            icaoObject.setName((new StringBuilder()).append("Icao [").append(icaoObject.getItem("icao").getDecodedData()).append("]").toString());
        }

        Collections.sort(arrayList, new BaseObjectSort());
        baseObject.getObjectAL().addAll(arrayList);
        return count * 20;
    }

    private void updateAirportInformation(BaseObject baseObject, NameListsObject nameListsObject)
    {
        for(int i = nameListsObject.getObjectAL().size() - 1; i > 4; i--)
        {
            if(!(nameListsObject.getObjectAL().get(i) instanceof ICAONameListObject))
                continue;
            ICAONameListObject icaoObject = (ICAONameListObject)nameListsObject.getObjectAL().get(i);
            String icao = (String)icaoObject.getItem("icao").getDecodedData();
            for(int j = baseObject.getObjectAL().size() - 1; j >= 0; j--)
            {
                if(!(baseObject.getObjectAL().get(j) instanceof AirportObject))
                    continue;
                AirportObject airportObject = (AirportObject)baseObject.getObjectAL().get(j);
                if(((String)airportObject.getItem("icao").getDecodedData()).equals(icao))
                {
                    int regionIndex = ((Integer)icaoObject.getItem("regionIndex").getDecodedData()).intValue();
                    airportObject.setRegion((String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(0)).getObjectAL().get(regionIndex)).getItem("name").getDecodedData());
                    int countryIndex = ((Integer)icaoObject.getItem("countryIndex").getDecodedData()).intValue();
                    airportObject.setCountry((String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(1)).getObjectAL().get(countryIndex)).getItem("name").getDecodedData());
                    int stateIndex = ((Integer)icaoObject.getItem("stateIndex").getDecodedData()).intValue();
                    airportObject.setState((String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(2)).getObjectAL().get(stateIndex)).getItem("name").getDecodedData());
                    int cityIndex = ((Integer)icaoObject.getItem("cityIndex").getDecodedData()).intValue();
                    airportObject.setCity((String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(3)).getObjectAL().get(cityIndex)).getItem("name").getDecodedData());
                }
            }

        }

    }

    public int decompileNameObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 6);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        if(convertByteToHex(byteData[0]).equals("19") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            NameObject nameObject = new NameObject(i, length);
            baseObject.addBaseObject(nameObject);
            nameObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            nameObject.setItemProperty("size", 5, displayHex(hexLength, true));
            nameObject.setItemProperty("size", 3, new Integer(2));
            try
            {
                raFile.seek(i);
                raFile.readFully(byteData, 0, length);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            int namelength = ((Integer)nameObject.getItem("size").getDecodedData()).intValue() - 6;
            StringBuffer buffer = new StringBuffer();
            for(int j = 6; j < 6 + namelength; j++)
                buffer.append(convertByteToHex(byteData[j]));

            nameObject.setItemProperty("name", 3, new Integer(6));
            nameObject.setItemProperty("name", 4, Integer.valueOf(namelength));
            nameObject.setItemProperty("name", 5, displayHex(buffer.toString(), false));
            if(baseObject instanceof AirportObject)
                baseObject.setName((new StringBuilder()).append((String)baseObject.getItem("icao").getDecodedData()).append(": ").append((String)nameObject.getItem("name").getDecodedData()).toString());
            else
            if(baseObject instanceof NDBObject)
                baseObject.setName((new StringBuilder()).append("NDB ").append((String)baseObject.getItem("icao").getDecodedData()).append(": ").append((String)nameObject.getItem("name").getDecodedData()).toString());
            return length;
        } else
        {
            return 0;
        }
    }

    private String displayHex(String hex, boolean reverse)
    {
        StringBuffer buffer = new StringBuffer();
        if(reverse)
        {
            for(int i = hex.length() - 1; i >= 0; i -= 2)
            {
                buffer.append(hex.charAt(i - 1)).append(hex.charAt(i));
                buffer.append(" ");
            }

        } else
        {
            for(int i = 0; i < hex.length(); i += 2)
            {
                buffer.append(hex.charAt(i)).append(hex.charAt(i + 1));
                buffer.append(" ");
            }

        }
        return buffer.toString().trim();
    }

    private int convertDWordToInt(String dWord)
    {
        return Long.valueOf(dWord, 16).intValue();
    }

    private String getDWord(int index)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(convertByteToHex(byteData[index + 3]));
        buffer.append(convertByteToHex(byteData[index + 2]));
        buffer.append(convertByteToHex(byteData[index + 1]));
        buffer.append(convertByteToHex(byteData[index]));
        return buffer.toString();
    }

    private String getWord(int index)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(convertByteToHex(byteData[index + 1]));
        buffer.append(convertByteToHex(byteData[index]));
        return buffer.toString();
    }

    private String getGUID(int index)
    {
        StringBuffer buffer = new StringBuffer();
        for(int i = 15; i >= 0; i--)
            buffer.append(convertByteToHex(byteData[index + i]));

        return buffer.toString();
    }

    private String convertByteToHex(long byteNumber)
    {
        String hexValue = Long.toHexString(byteNumber);
        if(hexValue.length() > 2)
            hexValue = hexValue.substring(hexValue.length() - 2);
        else
        if(hexValue.length() == 1)
            hexValue = (new StringBuilder()).append("0").append(hexValue).toString();
        return hexValue.toUpperCase();
    }

    private byte byteData[];
    private static SceneryDecompiler decompiler = null;

}