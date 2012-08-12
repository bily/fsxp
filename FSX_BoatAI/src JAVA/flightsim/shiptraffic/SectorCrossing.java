// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SectorCrossing.java

package flightsim.shiptraffic;

import flightsim.utils.TimeUtils;
import java.io.*;

public class SectorCrossing
    implements Comparable
{

    SectorCrossing(DataInput bf)
        throws IOException
    {
        plan = bf.readInt();
        entryTime = bf.readInt();
        exitTime = bf.readInt();
    }

    SectorCrossing(int plan, int entryTime, int exitTime)
    {
        this.plan = plan;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public SectorCrossing(int plan)
    {
        this.plan = plan;
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(Integer.toHexString(plan).toUpperCase()))).append(" ").append(TimeUtils.formatAITime(entryTime, true)).append(" ").append(TimeUtils.formatAITime(exitTime, true)).toString();
    }

    void write(DataOutput bf)
        throws IOException
    {
        bf.writeInt(plan);
        bf.writeInt(entryTime);
        bf.writeInt(exitTime);
    }

    public int compareTo(SectorCrossing o)
    {
        return entryTime - o.entryTime;
    }

    public int compareTo(Object obj)
    {
        return compareTo((SectorCrossing)obj);
    }

    public final int plan;
    public int entryTime;
    public int exitTime;
}
