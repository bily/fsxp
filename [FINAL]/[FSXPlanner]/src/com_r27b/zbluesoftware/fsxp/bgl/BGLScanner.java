// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 3/2/2008 9:51:50 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   BGLScanner.java

package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import com.zbluesoftware.fsxp.bgl.object.FileObject;
import com.zbluesoftware.fsxp.bgl.object.ICAONameListObject;
import com.zbluesoftware.fsxp.bgl.object.NameListNameObject;
import com.zbluesoftware.fsxp.bgl.object.NameListObject;
import com.zbluesoftware.fsxp.bgl.object.NameListsObject;
import com.zbluesoftware.fsxp.bgl.object.SectionHeaderObject;
import com.zbluesoftware.fsxp.bgl.object.SectionPointerObject;
import com.zbluesoftware.fsxp.engine.LogEngine;
import java.awt.Frame;
import java.io.*;
import java.util.*;

// Referenced classes of package com.zbluesoftware.fsxp.bgl:
//            BaseObjectSort

public class BGLScanner
{

    private BGLScanner()
    {
    }

    public static BGLScanner getInstance()
    {
        if(scanner == null)
            scanner = new BGLScanner();
        return scanner;
    }

    public ArrayList scan(File bglFile, Frame parent, boolean showResults)
    {
        ArrayList arrayList = new ArrayList();
        try
        {
            RandomAccessFile raFile = new RandomAccessFile(bglFile, "r");
            parseData(bglFile.getName(), raFile, arrayList);
        }
        catch(FileNotFoundException fnfe)
        {
            LogEngine.getInstance().log(fnfe);
        }
        return arrayList;
    }

    private void parseData(String fileName, RandomAccessFile raFile, ArrayList arrayList)
    {
        int offset;
        byte byteData[];
        int sectionCount;
        int i;
        offset = 0;
        byteData = new byte[1024];
        try
        {
            raFile.seek(0L);
            raFile.readFully(byteData, 0, 54);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        fileObject = new FileObject();
        fileObject.setItemProperty("id", 5, displayHex(getWord(byteData, 0), true));
        fileObject.setItemProperty("id", 3, new Integer(0));
        fileObject.setItemProperty("version", 5, displayHex(getWord(byteData, 2), true));
        fileObject.setItemProperty("version", 3, new Integer(2));
        fileObject.setItemProperty("size", 5, displayHex(getDWord(byteData, 4), true));
        fileObject.setItemProperty("size", 3, new Integer(4));
        fileObject.setItemProperty("sectionCount", 5, displayHex(getDWord(byteData, 20), true));
        fileObject.setItemProperty("sectionCount", 3, new Integer(20));
        int length = ((Integer)fileObject.getItem("size").getDecodedData()).intValue();
        fileObject.setLength(length);
        offset += length;
        sectionCount = ((Integer)fileObject.getItem("sectionCount").getDecodedData()).intValue();
		SectionPointerObject pointerObject = null;
	for(i=0;i<sectionCount;++i) 
	{
        try {if((long)(offset + 20) > raFile.length())
				return;
			} catch (IOException ioe1) {}
        try
        {
            raFile.seek(offset);
            raFile.readFully(byteData, 0, 20);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        int j = 0;
        do
        {
            if(j >= 15)
                break;
            if(convertByteToHex(byteData[0]).equals(pointers[j]))
            {
                pointerObject = new SectionPointerObject(sections[j], offset);
                break;
            }
            j++;
        } while(true);
        if(pointerObject == null)
            pointerObject = new SectionPointerObject("unknown", offset);
        pointerObject.setItemProperty("id", 5, displayHex(getDWord(byteData, 0), true));
        pointerObject.setItemProperty("id", 3, new Integer(0));
        pointerObject.setItemProperty("id", 4, new Integer(4));
        pointerObject.setItemProperty("subsectionPointers", 5, displayHex(getDWord(byteData, 8), true));
        pointerObject.setItemProperty("subsectionPointers", 3, new Integer(8));
        pointerObject.setItemProperty("offset", 5, displayHex(getDWord(byteData, 12), true));
        pointerObject.setItemProperty("offset", 3, new Integer(12));
        pointerObject.setItemProperty("size", 5, displayHex(getDWord(byteData, 16), true));
        pointerObject.setItemProperty("size", 3, new Integer(16));
        fileObject.addBaseObject(pointerObject);
        pointerObject.setLength(20);
        offset += 20;
	}

        for(i = 0; i < fileObject.getObjectAL().size(); i++)
        {
            pointerObject = (SectionPointerObject)fileObject.getObjectAL().get(i);
            int tempOffset = ((Integer)pointerObject.getItem("offset").getDecodedData()).intValue();
            int subsectionPointers = ((Integer)pointerObject.getItem("subsectionPointers").getDecodedData()).intValue();
            try
            {
                raFile.seek(tempOffset);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            for(int j = 0; j < subsectionPointers; j++)
            {
                SectionHeaderObject headerObject = new SectionHeaderObject(tempOffset);
                try
                {
                    raFile.seek(tempOffset);
                    raFile.readFully(byteData, 0, 16);
                }
                catch(IOException ioe)
                {
                    LogEngine.getInstance().log(ioe);
                }
                headerObject.setItemProperty("id", 5, displayHex(getDWord(byteData, 0), true));
                headerObject.setItemProperty("id", 3, new Integer(0));
                headerObject.setItemProperty("numberRecords", 5, displayHex(getDWord(byteData, 4), true));
                headerObject.setItemProperty("numberRecords", 3, new Integer(4));
                headerObject.setItemProperty("offset", 5, displayHex(getDWord(byteData, 8), true));
                headerObject.setItemProperty("offset", 3, new Integer(8));
                headerObject.setItemProperty("subsectionSize", 5, displayHex(getDWord(byteData, 12), true));
                headerObject.setItemProperty("subsectionSize", 3, new Integer(12));
                pointerObject.addBaseObject(headerObject);
                offset += 16;
                tempOffset += 16;
            }

        }

        ArrayList pointerAL = fileObject.getObjectAL();
        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
			pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("nameList"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int nameListOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int nameListCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseNameLists(raFile, byteData, nameListOffset, nameListCount, arrayList);
            }
        }
        return;
    }

    private void parseNameLists(RandomAccessFile raFile, byte byteData[], int nameListOffset, int nameListCount, ArrayList arrayList)
    {
        decompileNameList(raFile, byteData, fileObject, nameListOffset, 0, arrayList);
    }

    public int decompileNameList(RandomAccessFile raFile, byte byteData[], BaseObject baseObject, int airportOffset, int currentOffset, ArrayList arrayList)
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
            nameListsObject.setItemProperty("id", 5, displayHex(getWord(byteData, 0), true));
            nameListsObject.setItemProperty("size", 5, displayHex(getDWord(byteData, 2), true));
            nameListsObject.setItemProperty("size", 3, new Integer(2));
            nameListsObject.setItemProperty("regionCount", 5, displayHex(getWord(byteData, 6), true));
            nameListsObject.setItemProperty("regionCount", 3, new Integer(6));
            nameListsObject.setItemProperty("countryCount", 5, displayHex(getWord(byteData, 8), true));
            nameListsObject.setItemProperty("countryCount", 3, new Integer(8));
            nameListsObject.setItemProperty("stateCount", 5, displayHex(getWord(byteData, 10), true));
            nameListsObject.setItemProperty("stateCount", 3, new Integer(10));
            nameListsObject.setItemProperty("cityCount", 5, displayHex(getWord(byteData, 12), true));
            nameListsObject.setItemProperty("cityCount", 3, new Integer(12));
            nameListsObject.setItemProperty("airportNameCount", 5, displayHex(getWord(byteData, 14), true));
            nameListsObject.setItemProperty("airportNameCount", 3, new Integer(14));
            nameListsObject.setItemProperty("icaoCount", 5, displayHex(getWord(byteData, 16), true));
            nameListsObject.setItemProperty("icaoCount", 3, new Integer(16));
            nameListsObject.setItemProperty("regionOffset", 5, displayHex(getDWord(byteData, 18), true));
            nameListsObject.setItemProperty("regionOffset", 3, new Integer(18));
            nameListsObject.setItemProperty("countryOffset", 5, displayHex(getDWord(byteData, 22), true));
            nameListsObject.setItemProperty("countryOffset", 3, new Integer(22));
            nameListsObject.setItemProperty("stateOffset", 5, displayHex(getDWord(byteData, 26), true));
            nameListsObject.setItemProperty("stateOffset", 3, new Integer(26));
            nameListsObject.setItemProperty("cityOffset", 5, displayHex(getDWord(byteData, 30), true));
            nameListsObject.setItemProperty("cityOffset", 3, new Integer(30));
            nameListsObject.setItemProperty("airportNameOffset", 5, displayHex(getDWord(byteData, 34), true));
            nameListsObject.setItemProperty("airportNameOffset", 3, new Integer(34));
            nameListsObject.setItemProperty("icaoOffset", 5, displayHex(getDWord(byteData, 38), true));
            nameListsObject.setItemProperty("icaoOffset", 3, new Integer(38));
            length += decompileSpecificNameList(raFile, byteData, nameListsObject, "Regions", airportOffset, ((Integer)nameListsObject.getItem("regionCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("regionOffset").getDecodedData()).intValue());
            length += decompileSpecificNameList(raFile, byteData, nameListsObject, "Countries", airportOffset, ((Integer)nameListsObject.getItem("countryCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("countryOffset").getDecodedData()).intValue());
            length += decompileSpecificNameList(raFile, byteData, nameListsObject, "States", airportOffset, ((Integer)nameListsObject.getItem("stateCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("stateOffset").getDecodedData()).intValue());
            length += decompileSpecificNameList(raFile, byteData, nameListsObject, "Cities", airportOffset, ((Integer)nameListsObject.getItem("cityCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("cityOffset").getDecodedData()).intValue());
            length += decompileSpecificNameList(raFile, byteData, nameListsObject, "AirportNames", airportOffset, ((Integer)nameListsObject.getItem("airportNameCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("airportNameOffset").getDecodedData()).intValue());
            length += decompileIcaoList(raFile, byteData, nameListsObject, airportOffset, ((Integer)nameListsObject.getItem("icaoCount").getDecodedData()).intValue(), ((Integer)nameListsObject.getItem("icaoOffset").getDecodedData()).intValue());
            updateAirportInformation(nameListsObject, arrayList);
            nameListsObject.setLength(length);
            return length;
        } else
        {
            return 0;
        }
    }

    private int decompileSpecificNameList(RandomAccessFile raFile, byte byteData[], BaseObject baseObject, String name, int airportOffset, int count, int offset)
    {
        int i = airportOffset + offset;
        int length = 0;
        try
        {
            raFile.seek(i);
            raFile.readFully(byteData, 0, 4);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        NameListObject nameListObject = new NameListObject(name, i, 4);
        baseObject.addBaseObject(nameListObject);
        nameListObject.setItemProperty("index", 5, displayHex(getDWord(byteData, 0), true));
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
            int nameOffset = (int)Long.parseLong(getDWord(byteData, 0), 16);
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

    private int decompileIcaoList(RandomAccessFile raFile, byte byteData[], BaseObject baseObject, int airportOffset, int count, int offset)
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
            icaoObject.setItemProperty("stateIndex", 5, displayHex(getWord(byteData, 2), true));
            icaoObject.setItemProperty("stateIndex", 3, new Integer(2 + j * 20));
            icaoObject.setItemProperty("cityIndex", 5, convertByteToHex(byteData[4]));
            icaoObject.setItemProperty("cityIndex", 3, new Integer(4 + j * 20));
            icaoObject.setItemProperty("airportNameIndex", 5, convertByteToHex(byteData[6]));
            icaoObject.setItemProperty("airportNameIndex", 3, new Integer(6 + j * 20));
            icaoObject.setItemProperty("icao", 5, displayHex(getDWord(byteData, 8), true));
            icaoObject.setItemProperty("icao", 3, new Integer(8 + j * 20));
            icaoObject.setName((new StringBuilder()).append("Icao [").append(icaoObject.getItem("icao").getDecodedData()).append("]").toString());
        }

        Collections.sort(arrayList, new BaseObjectSort());
        baseObject.getObjectAL().addAll(arrayList);
        return count * 20;
    }

    private void updateAirportInformation(NameListsObject nameListsObject, ArrayList arrayList)
    {
        for(int i = nameListsObject.getObjectAL().size() - 1; i > 4; i--)
            if(nameListsObject.getObjectAL().get(i) instanceof ICAONameListObject)
            {
                HashMap hashMap = new HashMap();
                arrayList.add(hashMap);
                ICAONameListObject icaoObject = (ICAONameListObject)nameListsObject.getObjectAL().get(i);
                String icao = (String)icaoObject.getItem("icao").getDecodedData();
                hashMap.put("icao", icao);
                int regionIndex = ((Integer)icaoObject.getItem("regionIndex").getDecodedData()).intValue();
                hashMap.put("region", (String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(0)).getObjectAL().get(regionIndex)).getItem("name").getDecodedData());
                int countryIndex = ((Integer)icaoObject.getItem("countryIndex").getDecodedData()).intValue();
                hashMap.put("country", (String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(1)).getObjectAL().get(countryIndex)).getItem("name").getDecodedData());
                int stateIndex = ((Integer)icaoObject.getItem("stateIndex").getDecodedData()).intValue();
                hashMap.put("state", (String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(2)).getObjectAL().get(stateIndex)).getItem("name").getDecodedData());
                int cityIndex = ((Integer)icaoObject.getItem("cityIndex").getDecodedData()).intValue();
                hashMap.put("city", (String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(3)).getObjectAL().get(cityIndex)).getItem("name").getDecodedData());
                int nameIndex = ((Integer)icaoObject.getItem("airportNameIndex").getDecodedData()).intValue();
                hashMap.put("name", (String)((BaseObject)((BaseObject)nameListsObject.getObjectAL().get(4)).getObjectAL().get(nameIndex)).getItem("name").getDecodedData());
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

    private String getDWord(byte byteData[], int index)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(convertByteToHex(byteData[index + 3]));
        buffer.append(convertByteToHex(byteData[index + 2]));
        buffer.append(convertByteToHex(byteData[index + 1]));
        buffer.append(convertByteToHex(byteData[index]));
        return buffer.toString();
    }

    private String getWord(byte byteData[], int index)
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(convertByteToHex(byteData[index + 1]));
        buffer.append(convertByteToHex(byteData[index]));
        return buffer.toString();
    }

    private int convertDWordToInt(String dWord)
    {
        return Long.valueOf(dWord, 16).intValue();
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

    private FileObject fileObject;
    private static BGLScanner scanner = null;
    private static String pointers[];
    private static String sections[];

    static 
    {
        pointers = new String[15];
        pointers[0] = "03";
        pointers[1] = "13";
        pointers[2] = "17";
        pointers[3] = "18";
        pointers[4] = "20";
        pointers[5] = "22";
        pointers[6] = "23";
        pointers[7] = "25";
        pointers[8] = "27";
        pointers[9] = "2B";
        pointers[10] = "2C";
        pointers[11] = "2E";
        pointers[12] = "28";
        pointers[13] = "2A";
        pointers[14] = "29";
        sections = new String[15];
        sections[0] = "airportData";
        sections[1] = "VOR-ILS-Data";
        sections[2] = "NDBData";
        sections[3] = "markers";
        sections[4] = "boundaryData";
        sections[5] = "waypointData";
        sections[6] = "geopolData";
        sections[7] = "sceneryObjects";
        sections[8] = "nameList";
        sections[9] = "mdlData";
        sections[10] = "additionalAirportData";
        sections[11] = "exclusionRectangle";
        sections[12] = "VOR-ICAO-Index";
        sections[13] = "waypointIcaoIndex";
        sections[14] = "ndbIcaoIndex";
    }
}