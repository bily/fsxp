// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanLeg.java

package flightsim.shiptraffic;

import flightsim.utils.TimeUtils;
import java.io.*;
import java.util.Date;

public abstract class PlanLeg
{

    PlanLeg(DataInput bf)
        throws IOException
    {
        int dep0 = bf.readInt();
        int arr0 = bf.readInt();
        period = dep0 >> 24;
        periodHours = TimeUtils.PERIODS_HOUR[period];
        departure = ((double)(dep0 & 0xffffff) / 16777216D) * (double)periodHours;
        arrival = ((double)(arr0 & 0xffffff) / 16777216D) * (double)periodHours;
    }

    PlanLeg(int period)
    {
        setPeriod(period);
    }

    void write(DataOutput bf)
        throws IOException
    {
        int depFmt = TimeUtils.makeAITime(departure, period);
        int arrFmt = TimeUtils.makeAITime(arrival, period);
        bf.writeInt(depFmt);
        bf.writeInt(arrFmt);
    }

    public double getDeparture()
    {
        return departure;
    }

    public double getArrival()
    {
        return arrival;
    }

    public Date getDepartureDate()
    {
        return new Date((long)(departure * 3600D * 1000D));
    }

    public Date getArrivalDate()
    {
        return new Date((long)(arrival * 3600D * 1000D));
    }

    public String getDepartureString()
    {
        return TimeUtils.formatTime(departure, periodHours, true);
    }

    public String getArrivalString()
    {
        return TimeUtils.formatTime(arrival, periodHours, true);
    }

    protected String makeString(DataInput bf, int len)
        throws IOException
    {
        byte tmp[] = new byte[len];
        bf.readFully(tmp);
        int fZeroPos;
        for(fZeroPos = 0; fZeroPos < len && tmp[fZeroPos] != 0; fZeroPos++);
        return new String(tmp, 0, fZeroPos);
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(getDepartureString()))).append(" ").append(getArrivalString()).toString();
    }

    public void appendTrafficString(StringBuilder sb)
    {
        sb.append(',');
        sb.append(getDepartureString());
        sb.append(',');
        sb.append(getArrivalString());
    }

    public int getPeriodHours()
    {
        return periodHours;
    }

    public void setDeparture(double d)
    {
        departure = d;
    }

    public void setArrival(double d)
    {
        arrival = d;
    }

    public void setPeriod(int period)
    {
        this.period = period;
        periodHours = TimeUtils.PERIODS_HOUR[period];
    }

    protected double departure;
    protected double arrival;
    protected int period;
    protected int periodHours;
}
