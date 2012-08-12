// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrafficSector.java

package flightsim.shiptraffic;

import java.io.*;
import java.util.Iterator;
import org.lc0277lib.geography.FormatLatLon;
import org.lc0277lib.geography.LatLon;

// Referenced classes of package flightsim.shiptraffic:
//            I18N, SortableVector, SectorCrossing

public class TrafficSector
{

    TrafficSector(DataInput bf)
        throws IOException
    {
        int recordlen = bf.readInt();
        ewcoord = bf.readShort();
        nscoord = bf.readShort();
        int nCross = (recordlen - 8) / 12;
        if((recordlen - 8) % 12 != 0)
            throw new IllegalArgumentException(I18N._("TrafficSector.InvalidSectorTable"));
        crossings = new SortableVector(nCross);
        for(int j = 0; j < nCross; j++)
        {
            SectorCrossing scr = new SectorCrossing(bf);
            crossings.add(scr);
        }

    }

    TrafficSector(short ewcoord, short nscoord)
    {
        this.ewcoord = ewcoord;
        this.nscoord = nscoord;
        crossings = new SortableVector();
    }

    void write(DataOutput bf)
        throws IOException
    {
        int recordlen = 8 + 12 * crossings.size();
        bf.writeInt(recordlen);
        bf.writeShort(ewcoord);
        bf.writeShort(nscoord);
        SectorCrossing sc;
        for(Iterator iterator = crossings.iterator(); iterator.hasNext(); sc.write(bf))
            sc = (SectorCrossing)iterator.next();

    }

    public double getLongitude()
    {
        return (((double)ewcoord - 192D) / 384D) * 360D;
    }

    public double getLatitude()
    {
        return (((double)nscoord - 128D) / 256D) * -180D;
    }

    public String getLongitudeString()
    {
        return FormatLatLon.formatLon(getLongitude());
    }

    public String getLatitudeString()
    {
        return FormatLatLon.formatLat(getLatitude());
    }

    public LatLon coordinates()
    {
        return new LatLon(getLatitude(), getLongitude());
    }

    public String toString()
    {
        return (new StringBuilder("NS/EW: ")).append(nscoord).append(" ").append(ewcoord).toString();
    }

    public final short ewcoord;
    public final short nscoord;
    public final SortableVector crossings;
}
