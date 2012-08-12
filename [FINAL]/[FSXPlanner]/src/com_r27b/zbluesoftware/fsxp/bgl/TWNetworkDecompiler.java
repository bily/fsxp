// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 2/28/2008 8:27:50 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TWNetworkDecompiler.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.AirportObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiNameObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiNamesObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayParkingObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayParkingsObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPathObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPathsObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPointObject;
import com.zbluesoftware.fsxp.bgl.object.TaxiwayPointsObject;
import com.zbluesoftware.fsxp.engine.LogEngine;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TWNetworkDecompiler
{

    private TWNetworkDecompiler()
    {
        byteData = new byte[256];
    }

    public static TWNetworkDecompiler getInstance()
    {
        if(decompiler == null)
            decompiler = new TWNetworkDecompiler();
        return decompiler;
    }

    public int decompileTaxiwayPoints(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
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
        if(convertByteToHex(byteData[0]).equals("1A") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TaxiwayPointsObject taxiwayPointsObject = new TaxiwayPointsObject(i, length);
            airportObject.addBaseObject(taxiwayPointsObject);
            taxiwayPointsObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            taxiwayPointsObject.setItemProperty("size", 5, displayHex(hexLength, true));
            taxiwayPointsObject.setItemProperty("size", 3, new Integer(2));
            taxiwayPointsObject.setItemProperty("pointCount", 5, displayHex(getWord(6), true));
            taxiwayPointsObject.setItemProperty("pointCount", 3, new Integer(6));
            decompileTaxiwayPoint(taxiwayPointsObject, i + 8, raFile);
            return length;
        } else
        {
            return 0;
        }
    }

    private void decompileTaxiwayPoint(TaxiwayPointsObject taxiwayPointsObject, int index, RandomAccessFile raFile)
    {
        int pointCount = ((Integer)taxiwayPointsObject.getItem("pointCount").getDecodedData()).intValue();
        for(int i = 0; i < pointCount; i++)
        {
            int offset = index + i * 12;
            try
            {
                raFile.seek(offset);
                raFile.readFully(byteData, 0, 12);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            TaxiwayPointObject tpObject = new TaxiwayPointObject(offset, 12);
            tpObject.setName((new StringBuilder()).append("Taxiway Point ").append(i).toString());
            taxiwayPointsObject.addBaseObject(tpObject);
            tpObject.setItemProperty("taxiwayType", 5, convertByteToHex(byteData[0]));
            tpObject.setItemProperty("taxiwayType", 3, new Integer(0));
            tpObject.setItemProperty("taxiwayOrientation", 5, convertByteToHex(byteData[1]));
            tpObject.setItemProperty("taxiwayOrientation", 3, new Integer(1));
            tpObject.setItemProperty("longitude", 5, displayHex(getDWord(4), true));
            tpObject.setItemProperty("longitude", 3, new Integer(4));
            tpObject.setItemProperty("latitude", 5, displayHex(getDWord(8), true));
            tpObject.setItemProperty("latitude", 3, new Integer(8));
        }

    }

    public int decompileTaxiwayParkings(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
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
        if(convertByteToHex(byteData[0]).equals("3D") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TaxiwayParkingsObject taxiwayParkingsObject = new TaxiwayParkingsObject(i, length);
            airportObject.addBaseObject(taxiwayParkingsObject);
            taxiwayParkingsObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            taxiwayParkingsObject.setItemProperty("size", 5, displayHex(hexLength, true));
            taxiwayParkingsObject.setItemProperty("size", 3, new Integer(2));
            taxiwayParkingsObject.setItemProperty("pointCount", 5, displayHex(getWord(6), true));
            taxiwayParkingsObject.setItemProperty("pointCount", 3, new Integer(6));
            decompileTaxiwayParking(taxiwayParkingsObject, i + 8, raFile);
            return length;
        } else
        {
            return 0;
        }
    }

    private void decompileTaxiwayParking(TaxiwayParkingsObject taxiwayParkingsObject, int index, RandomAccessFile raFile)
    {
        int pointCount = ((Integer)taxiwayParkingsObject.getItem("pointCount").getDecodedData()).intValue();
        int offset = index;
        for(int i = 0; i < pointCount; i++)
        {
            TaxiwayParkingObject tpObject = new TaxiwayParkingObject(offset, 12);
            taxiwayParkingsObject.addBaseObject(tpObject);
            try
            {
                raFile.seek(offset);
                raFile.readFully(byteData, 0, 36);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            tpObject.setItemProperty("parkingName", 5, displayHex(getDWord(0), true));
            tpObject.setItemProperty("parkingName", 3, new Integer(0));
            tpObject.setItemProperty("parkingType", 5, displayHex(getDWord(0), true));
            tpObject.setItemProperty("parkingType", 3, new Integer(0));
            tpObject.setItemProperty("number", 5, displayHex(getDWord(0), true));
            tpObject.setItemProperty("number", 3, new Integer(0));
            tpObject.setItemProperty("pushBack", 5, displayHex(getDWord(0), true));
            tpObject.setItemProperty("pushBack", 3, new Integer(0));
            tpObject.setItemProperty("airlineCodeCount", 5, displayHex(getDWord(0), true));
            tpObject.setItemProperty("airlineCodeCount", 3, new Integer(0));
            tpObject.setItemProperty("radius", 5, displayHex(getDWord(4), true));
            tpObject.setItemProperty("radius", 3, new Integer(4));
            tpObject.setItemProperty("heading", 5, displayHex(getDWord(8), true));
            tpObject.setItemProperty("heading", 3, new Integer(8));
            tpObject.setItemProperty("teeOffset1", 5, displayHex(getDWord(12), true));
            tpObject.setItemProperty("teeOffset1", 3, new Integer(12));
            tpObject.setItemProperty("teeOffset2", 5, displayHex(getDWord(16), true));
            tpObject.setItemProperty("teeOffset2", 3, new Integer(16));
            tpObject.setItemProperty("teeOffset3", 5, displayHex(getDWord(20), true));
            tpObject.setItemProperty("teeOffset3", 3, new Integer(20));
            tpObject.setItemProperty("teeOffset4", 5, displayHex(getDWord(24), true));
            tpObject.setItemProperty("teeOffset4", 3, new Integer(24));
            tpObject.setItemProperty("longitude", 5, displayHex(getDWord(28), true));
            tpObject.setItemProperty("longitude", 3, new Integer(28));
            tpObject.setItemProperty("latitude", 5, displayHex(getDWord(32), true));
            tpObject.setItemProperty("latitude", 3, new Integer(32));
            int totalCodes = 0;
            if((totalCodes = ((Integer)tpObject.getItem("airlineCodeCount").getDecodedData()).intValue()) > 0)
            {
                try
                {
                    raFile.seek(offset + 36);
                    raFile.readFully(byteData, 0, totalCodes * 4);
                }
                catch(IOException ioe)
                {
                    LogEngine.getInstance().log(ioe);
                }
                StringBuffer codeBuffer = new StringBuffer();
                for(int j = 0; j < totalCodes * 4; j++)
                {
                    codeBuffer.append(convertByteToHex(byteData[j]));
                    if(j < totalCodes * 4 - 1)
                        codeBuffer.append(" ");
                }

                tpObject.setItemProperty("airlineCodes", 5, codeBuffer.toString());
                tpObject.setItemProperty("airlineCodes", 3, new Integer(36));
                tpObject.setItemProperty("airlineCodes", 4, new Integer(4 * totalCodes));
            }
            tpObject.setName((new StringBuilder()).append("Parking ").append(tpObject.getItem("number").getDecodedData()).append(" (").append(tpObject.getItem("parkingType").getDecodedData()).append(")").toString());
            int airlineCodeCount = ((Integer)tpObject.getItem("airlineCodeCount").getDecodedData()).intValue();
            tpObject.setLength(36 + airlineCodeCount * 4);
            offset += 36 + airlineCodeCount * 4;
        }

    }

    public int decompileTaxiwayPaths(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
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
        if(convertByteToHex(byteData[0]).equals("1C") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TaxiwayPathsObject taxiwayPathsObject = new TaxiwayPathsObject(i, length);
            airportObject.addBaseObject(taxiwayPathsObject);
            taxiwayPathsObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            taxiwayPathsObject.setItemProperty("size", 5, displayHex(hexLength, true));
            taxiwayPathsObject.setItemProperty("size", 3, new Integer(2));
            taxiwayPathsObject.setItemProperty("pathCount", 5, displayHex(getWord(6), true));
            taxiwayPathsObject.setItemProperty("pathCount", 3, new Integer(6));
            decompileTaxiwayPath(taxiwayPathsObject, i + 8, raFile);
            return length;
        } else
        {
            return 0;
        }
    }

    private void decompileTaxiwayPath(TaxiwayPathsObject taxiwayPathsObject, int index, RandomAccessFile raFile)
    {
        int pathCount = ((Integer)taxiwayPathsObject.getItem("pathCount").getDecodedData()).intValue();
        int offset = index;
        for(int i = 0; i < pathCount; i++)
        {
            TaxiwayPathObject tpObject = new TaxiwayPathObject(offset, 20);
            taxiwayPathsObject.addBaseObject(tpObject);
            try
            {
                raFile.seek(offset);
                raFile.readFully(byteData, 0, 20);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            tpObject.setItemProperty("startIndex", 5, displayHex(getWord(0), true));
            tpObject.setItemProperty("startIndex", 3, new Integer(0));
            tpObject.setItemProperty("endIndex", 5, displayHex(getWord(2), true));
            tpObject.setItemProperty("endIndex", 3, new Integer(2));
            tpObject.setItemProperty("runwayDesignator", 5, displayHex(getWord(2), true));
            tpObject.setItemProperty("runwayDesignator", 3, new Integer(2));
            tpObject.setItemProperty("type", 5, convertByteToHex(byteData[4]));
            tpObject.setItemProperty("type", 3, new Integer(4));
            tpObject.setItemProperty("runwayNumber", 5, convertByteToHex(byteData[5]));
            tpObject.setItemProperty("runwayNumber", 3, new Integer(5));
            tpObject.setItemProperty("taxiName", 5, convertByteToHex(byteData[5]));
            tpObject.setItemProperty("taxiName", 3, new Integer(5));
            tpObject.setItemProperty("centerLine", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("centerLine", 3, new Integer(6));
            tpObject.setItemProperty("centerLineLighted", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("centerLineLighted", 3, new Integer(6));
            tpObject.setItemProperty("leftEdge", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("leftEdge", 3, new Integer(6));
            tpObject.setItemProperty("leftEdgeLighted", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("leftEdgeLighted", 3, new Integer(6));
            tpObject.setItemProperty("rightEdge", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("rightEdge", 3, new Integer(6));
            tpObject.setItemProperty("rightEdgeLighted", 5, convertByteToHex(byteData[6]));
            tpObject.setItemProperty("rightEdgeLighted", 3, new Integer(6));
            tpObject.setItemProperty("surface", 5, convertByteToHex(byteData[7]));
            tpObject.setItemProperty("surface", 3, new Integer(7));
            tpObject.setItemProperty("width", 5, displayHex(getDWord(8), true));
            tpObject.setItemProperty("width", 3, new Integer(8));
            tpObject.setItemProperty("weightLimit", 5, displayHex(getDWord(12), true));
            tpObject.setItemProperty("weightLimit", 3, new Integer(12));
            offset += 20;
        }

    }

    public int decompileTaxiNames(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
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
        if(convertByteToHex(byteData[0]).equals("1D") && convertByteToHex(byteData[1]).equals("00"))
        {
            String hexLength = getDWord(2);
            int length = Long.valueOf(hexLength, 16).intValue();
            TaxiNamesObject taxiNamesObject = new TaxiNamesObject(i, length);
            airportObject.addBaseObject(taxiNamesObject);
            taxiNamesObject.setItemProperty("id", 5, displayHex(getWord(0), true));
            taxiNamesObject.setItemProperty("size", 5, displayHex(hexLength, true));
            taxiNamesObject.setItemProperty("size", 3, new Integer(2));
            taxiNamesObject.setItemProperty("nameCount", 5, displayHex(getWord(6), true));
            taxiNamesObject.setItemProperty("nameCount", 3, new Integer(6));
            decompileTaxiName(taxiNamesObject, i + 8, raFile);
            return length;
        } else
        {
            return 0;
        }
    }

    private void decompileTaxiName(TaxiNamesObject taxiNamesObject, int index, RandomAccessFile raFile)
    {
        int nameCount = ((Integer)taxiNamesObject.getItem("nameCount").getDecodedData()).intValue();
        int offset = index;
        for(int i = 0; i < nameCount; i++)
        {
            TaxiNameObject taxiNameObject = new TaxiNameObject(offset, 8);
            taxiNamesObject.addBaseObject(taxiNameObject);
            try
            {
                raFile.seek(offset);
                raFile.readFully(byteData, 0, 8);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            StringBuffer buffer = new StringBuffer();
            for(int j = 0; j < 8; j++)
            {
                buffer.append(convertByteToHex(byteData[j]));
                if(j < 7)
                    buffer.append(" ");
            }

            taxiNameObject.setItemProperty("taxiName", 5, buffer.toString());
            taxiNameObject.setItemProperty("taxiName", 3, new Integer(0));
            taxiNameObject.setName((new StringBuilder()).append("TaxiName ").append((String)taxiNameObject.getItem("taxiName").getDecodedData()).toString());
            offset += 8;
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
    private static TWNetworkDecompiler decompiler = null;

}