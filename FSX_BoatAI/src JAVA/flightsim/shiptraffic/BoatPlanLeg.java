// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoatPlanLeg.java

package flightsim.shiptraffic;

import java.io.*;

// Referenced classes of package flightsim.shiptraffic:
//            PlanLeg

public class BoatPlanLeg extends PlanLeg
{

    BoatPlanLeg(DataInput bf)
        throws IOException
    {
        super(bf);
        route = bf.readInt();
        flags = bf.readInt();
    }

    public BoatPlanLeg(int period)
    {
        super(period);
    }

    public boolean isReverse()
    {
        return (flags & 0x4000000) != 0;
    }

    public boolean isFerry()
    {
        return (flags & 0x8000000) != 0;
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(super.toString()))).append(" route ").append(route).append(" flags ").append(Integer.toHexString(flags)).toString();
    }

    void write(DataOutput bf)
        throws IOException
    {
        super.write(bf);
        bf.writeInt(route);
        bf.writeInt(flags);
    }

    public void appendTrafficString(StringBuilder sb)
    {
        super.appendTrafficString(sb);
        sb.append(',');
        sb.append(route);
        sb.append(',');
        if(isReverse())
            sb.append("reverse,");
        else
            sb.append("normal,");
        if(isFerry())
            sb.append("ferry");
        else
            sb.append("cargo");
    }

    public int getRoute()
    {
        return route;
    }

    public int getFlags()
    {
        return flags;
    }

    public int getPeriod()
    {
        return period;
    }

    public void setReverse(boolean b)
    {
        if(b)
            flags |= 0x4000000;
        else
            flags &= 0xfbffffff;
    }

    public void setFerry(boolean b)
    {
        if(b)
            flags |= 0x8000000;
        else
            flags &= 0xf7ffffff;
    }

    public void setRoute(int routeId)
    {
        route = routeId;
    }

    private int route;
    private int flags;
}
