// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoatRecord.java

package flightsim.shiptraffic;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package flightsim.shiptraffic:
//            TrafficFormatException, I18N

public class BoatRecord
{

    BoatRecord(DataInput bf)
        throws IOException
    {
        int recordLen = bf.readShort() & 0xffff;
        blank1 = bf.readShort() & 0xffff;
        identifier = bf.readShort() & 0xffff;
        blank2 = bf.readShort() & 0xffff;
        speed = bf.readShort() & 0xffff;
        name = makeString(bf, recordLen - 10);
    }

    public BoatRecord(int id, int speed, String name)
    {
        identifier = id;
        blank1 = 0;
        blank2 = 0;
        this.speed = speed;
        this.name = name;
    }

    public BoatRecord(int speed, String name)
    {
        this(0, speed, name);
    }

    private String makeString(DataInput bf, int len)
        throws IOException
    {
        byte tmp[] = new byte[len];
        bf.readFully(tmp);
        int fZeroPos;
        for(fZeroPos = 0; tmp[fZeroPos] != 0 && fZeroPos < len; fZeroPos++);
        return new String(tmp, 0, fZeroPos);
    }

    void write(DataOutput bf)
        throws IOException
    {
        byte nameBs[] = name.getBytes();
        int size = 10 + nameBs.length + 1;
        bf.writeShort((short)size);
        bf.writeShort((short)blank1);
        bf.writeShort((short)identifier);
        bf.writeShort((short)blank2);
        bf.writeShort((short)speed);
        bf.write(nameBs);
        bf.write(0);
    }

    public String toString()
    {
        return (new StringBuilder("#")).append(identifier).append(" \"").append(name).append("\" ").append(speed).append(" kts ").append(blank1).append(" ").append(blank2).toString();
    }

    public String toTrafficString()
    {
        return (new StringBuilder("AC#")).append(identifier + 1).append(",").append(speed).append(",\"").append(name).append("\"").toString();
    }

    public int getIdentifier()
    {
        return identifier;
    }

    public String getName()
    {
        return name;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public void setIdentifier(int identifier)
    {
        this.identifier = identifier;
    }

    public static Map readTToolsAircrafts(File file)
        throws IOException
    {
        Map acTable = new TreeMap();
        BufferedReader br = new BufferedReader(new FileReader(file));
        Pattern acp = Pattern.compile("[aAbB][cCTt]?#(\\d+),(\\d+),\"(.*)\"");
        int id = 0;
        String line;
        while((line = br.readLine()) != null) 
            if(!line.trim().startsWith("//"))
            {
                Matcher m = acp.matcher(line);
                if(m.matches())
                    try
                    {
                        int no = Integer.parseInt(m.group(1)) - 1;
                        int speed = Integer.parseInt(m.group(2));
                        String title = m.group(3);
                        BoatRecord acr = new BoatRecord(id++, speed, title);
                        acTable.put(Integer.valueOf(no), acr);
                    }
                    catch(NumberFormatException nfe)
                    {
                        nfe.printStackTrace();
                    }
            }
        return acTable;
    }

    public static Map readFS10CSVBoats(File file)
        throws IOException, TrafficFormatException
    {
        Map mbr = new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(file));
        int lineno = 0;
        String line;
        while((line = br.readLine()) != null) 
        {
            lineno++;
            line = line.trim();
            if(!line.startsWith("#"))
            {
                String parts[] = line.split(",");
                if(line.length() >= 2)
                {
                    if(parts.length != 5)
                        throw new TrafficFormatException(file, lineno, I18N._("BoatRecord.InvalidLine"));
                    String key = parts[0];
                    String name = parts[1];
                    int speed;
                    try
                    {
                        speed = Integer.parseInt(parts[2]);
                    }
                    catch(NumberFormatException nfe)
                    {
                        speed = 25;
                    }
                    BoatRecord boat = new BoatRecord(0, speed, name);
                    mbr.put(key, boat);
                }
            }
        }
        br.close();
        return mbr;
    }

    private int blank1;
    private int identifier;
    private int blank2;
    private int speed;
    private String name;
}
