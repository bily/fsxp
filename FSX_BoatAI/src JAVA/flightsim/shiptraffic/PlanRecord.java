// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlanRecord.java

package flightsim.shiptraffic;

import flightsim.utils.TimeUtils;
import java.io.*;
import java.text.MessageFormat;
import java.util.*;

// Referenced classes of package flightsim.shiptraffic:
//            NotBoatException, I18N, BoatPlanLeg, PlanLeg, 
//            TrafficFormatException

public class PlanRecord
{

    PlanRecord(DataInput bf)
        throws NotBoatException, IOException
    {
        legs = new Vector();
        int recordlen = bf.readInt();
        number = bf.readInt();
        objid = bf.readInt();
        name = makeString(bf, 8);
        percent = bf.readUnsignedByte() & 0xff;
        flags = bf.readUnsignedByte() & 0xff;
        int nLegs = (recordlen - 22) / 16;
        if((recordlen - 22) % 16 != 0)
            throw new IllegalArgumentException(I18N._("PlanRecord.BadLegsStructure"));
        period = flags & 0xf;
        if(period > TimeUtils.PERIODS_HOUR.length)
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(I18N._("PlanRecord.UnsupportedPeriod")))).append(period).toString());
        boolean isboat = (flags & 0x20) != 0;
        if(!isboat)
            throw new NotBoatException();
        for(int i = 0; i < nLegs; i++)
            if(isboat)
                legs.add(new BoatPlanLeg(bf));

    }

    PlanRecord(int number, int objid, String name, int percent, int flags, int period)
    {
        legs = new Vector();
        this.number = number;
        this.objid = objid;
        this.name = name;
        this.percent = percent;
        this.period = period;
        this.flags = flags | period & 0xf | 0x20;
    }

    protected String makeString(DataInput bf, int len)
        throws IOException
    {
        byte tmp[] = new byte[len];
        bf.readFully(tmp);
        int fZeroPos;
        for(fZeroPos = 0; tmp[fZeroPos] != 0 && fZeroPos < len; fZeroPos++);
        return new String(tmp, 0, fZeroPos);
    }

    public PlanRecord()
    {
        legs = new Vector();
        name = "";
        percent = 50;
    }

    void write(DataOutput bf)
        throws IOException
    {
        int recordlen = 22 + 16 * legs.size();
        bf.writeInt(recordlen);
        bf.writeInt(number);
        bf.writeInt(objid);
        byte nameBs[] = name.getBytes();
        bf.write(nameBs, 0, Math.min(8, nameBs.length));
        for(int i = 0; i < 8 - nameBs.length; i++)
            bf.write(0);

        bf.write((byte)percent);
        bf.write((byte)flags);
        PlanLeg leg;
        for(Iterator iterator = legs.iterator(); iterator.hasNext(); leg.write(bf))
            leg = (PlanLeg)iterator.next();

    }

    public List getLegs()
    {
        return legs;
    }

    public String getName()
    {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public String toString()
    {
        return (new StringBuilder("\nPlan ")).append(Integer.toHexString(number).toUpperCase()).append(" AC#").append(objid).append(" '").append(name).append("'").append(" ").append(percent).append("% ").append(TimeUtils.PERIODS_HOUR_STRING[period]).append(" flags ").append(Integer.toHexString(flags)).toString();
    }

    public String toTrafficString()
    {
        StringBuilder sb = new StringBuilder("");
        sb.append("AC#");
        sb.append(objid + 1);
        sb.append(',');
        sb.append(name);
        sb.append(',');
        sb.append(percent);
        sb.append('%');
        sb.append(',');
        sb.append(TimeUtils.PERIODS_HOUR_STRING[period]);
        PlanLeg pl;
        for(Iterator iterator = legs.iterator(); iterator.hasNext(); pl.appendTrafficString(sb))
            pl = (PlanLeg)iterator.next();

        return sb.toString();
    }

    public int getObjid()
    {
        return objid;
    }

    public int getPercent()
    {
        return percent;
    }

    public int getPeriod()
    {
        return period;
    }

    public PlanLeg getLeg(int idx)
    {
        return (PlanLeg)legs.get(idx);
    }

    public void setObjId(int identifier)
    {
        objid = identifier;
    }

    public void setName(String text)
    {
        if(text.length() > 10)
            text = text.substring(0, 10);
        name = text;
    }

    public void setPercent(int value)
    {
        percent = value;
    }

    public void setPeriod(int period)
    {
        this.period = period;
    }

    public void addLeg(BoatPlanLeg bpl)
    {
        legs.add(bpl);
    }

    private static int parsePeriodHours(String s)
    {
        if(s.equalsIgnoreCase("1Hr"))
            return 1;
        if(s.equalsIgnoreCase("2Hr"))
            return 2;
        if(s.equalsIgnoreCase("4Hr"))
            return 4;
        if(s.equalsIgnoreCase("6Hr"))
            return 6;
        if(s.equalsIgnoreCase("8Hr"))
            return 8;
        if(s.equalsIgnoreCase("12Hr"))
            return 12;
        if(s.equalsIgnoreCase("24Hr"))
            return 24;
        if(s.equalsIgnoreCase("168Hr"))
            return 168;
        if(s.equalsIgnoreCase("336Hr"))
            return 336;
        if(s.equalsIgnoreCase("840Hr"))
            return 840;
        if(s.equalsIgnoreCase("1344Hr"))
            return 1344;
        if(s.equalsIgnoreCase("WEEK"))
            return 168;
        if(s.equalsIgnoreCase("1WEEK"))
            return 168;
        if(s.equalsIgnoreCase("2WEEK"))
            return 336;
        if(s.equalsIgnoreCase("5WEEK"))
            return 840;
        if(s.equalsIgnoreCase("8WEEK"))
            return 1344;
        if(s.equalsIgnoreCase("2WEEKS"))
            return 336;
        if(s.equalsIgnoreCase("5WEEKS"))
            return 840;
        return !s.equalsIgnoreCase("8WEEKS") ? -1 : 1344;
    }

    public static List readTToolsBoatPlans(File file)
        throws IOException, TrafficFormatException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List lpl = new ArrayList();
        int number = -2;
        int lineno = 0;
        String line;
        while((line = br.readLine()) != null) 
        {
            lineno++;
            line = line.trim();
            if(!line.startsWith("//"))
            {
                StringTokenizer toker = new StringTokenizer(line, ",");
                if(toker.countTokens() >= 5)
                {
                    String tmp = toker.nextToken().trim().toLowerCase();
                    if(!tmp.startsWith("ac#") && !tmp.startsWith("bt#") && !tmp.startsWith("b#"))
                        throw new TrafficFormatException(file, lineno, MessageFormat.format(I18N._("PlanRecord.BadBoatId"), new Object[] {
                            tmp
                        }));
                    int ip = tmp.lastIndexOf('#');
                    int acid;
                    try
                    {
                        acid = Integer.parseInt(tmp.substring(ip + 1)) - 1;
                    }
                    catch(NumberFormatException nfe)
                    {
                        throw new TrafficFormatException(file, lineno, I18N._("PlanRecord.InvalidBoatID"));
                    }
                    String name = toker.nextToken().trim();
                    tmp = toker.nextToken().trim();
                    if(tmp.endsWith("%"))
                        tmp = tmp.substring(0, tmp.length() - 1);
                    int perc;
                    try
                    {
                        perc = Integer.parseInt(tmp);
                    }
                    catch(NumberFormatException nfe)
                    {
                        throw new TrafficFormatException(file, lineno, I18N._("PlanRecord.InvalidPercentFmt"));
                    }
                    String periodString = toker.nextToken();
                    int periodHours = parsePeriodHours(periodString);
                    if(periodHours == -1)
                        throw new TrafficFormatException(file, lineno, MessageFormat.format(I18N._("PlanRecord.BadPeriod"), new Object[] {
                            periodString
                        }));
                    try
                    {
                        PlanRecord plr = new PlanRecord(number--, acid, name, perc, 0, TimeUtils.periodType(periodHours));
                        lpl.add(plr);
                        parseTTBoatPlanLegs(toker, plr);
                    }
                    catch(NumberFormatException nfe)
                    {
                        throw new TrafficFormatException(file, lineno, MessageFormat.format(I18N._("PlanRecord.CannotParseNumber"), new Object[] {
                            nfe.getMessage()
                        }));
                    }
                    catch(IllegalArgumentException ilae)
                    {
                        throw new TrafficFormatException(file, lineno, MessageFormat.format(I18N._("PlanRecord.CannotParseDate"), new Object[] {
                            ilae.getMessage()
                        }));
                    }
                }
            }
        }
        br.close();
        return lpl;
    }

    private static void parseTTBoatPlanLegs(StringTokenizer toker, PlanRecord pl)
    {
        BoatPlanLeg bpl;
        for(; toker.hasMoreElements(); pl.addLeg(bpl))
        {
            bpl = new BoatPlanLeg(pl.getPeriod());
            double depTime = TimeUtils.parseTime(toker.nextToken());
            double arrTime = TimeUtils.parseTime(toker.nextToken());
            int routeId = Integer.parseInt(toker.nextToken());
            String reverse = toker.nextToken();
            String ferry = toker.nextToken();
            bpl.setDeparture(depTime);
            bpl.setArrival(arrTime);
            bpl.setRoute(routeId);
            if(reverse.equalsIgnoreCase("reverse"))
                bpl.setReverse(true);
            if(ferry.equalsIgnoreCase("ferry"))
                bpl.setFerry(true);
        }

    }

    public static void insertArrivals(File file, File outFile, List plans)
        throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintStream out = new PrintStream(outFile);
        int lineno = 0;
        PlanRecord current = null;
        String line;
        while((line = br.readLine()) != null) 
        {
            lineno++;
            if(line.startsWith("//"))
            {
                out.println(line);
            } else
            {
                line = line.trim();
                StringTokenizer toker = new StringTokenizer(line, ",");
                if(toker.countTokens() >= 5)
                {
                    String tmp = toker.nextToken().trim().toLowerCase();
                    if(tmp.startsWith("ac#") || tmp.startsWith("bt#") || tmp.startsWith("b#"))
                    {
                        int ip = tmp.lastIndexOf('#');
                        int acid = Integer.parseInt(tmp.substring(ip + 1)) - 1;
                        String name = toker.nextToken().trim();
                        current = null;
                        for(Iterator iterator = plans.iterator(); iterator.hasNext();)
                        {
                            PlanRecord plr = (PlanRecord)iterator.next();
                            if(plr.getName().equals(name) && plr.getObjid() == acid)
                            {
                                current = plr;
                                break;
                            }
                        }

                        if(current == null)
                            out.println(line);
                        else
                            out.println(current.toTrafficString());
                    }
                }
            }
        }
        br.close();
        out.flush();
        out.close();
    }

    private int number;
    private int objid;
    private String name;
    private int percent;
    private int flags;
    private int period;
    private final List legs;
}
