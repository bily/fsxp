// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoatTrafficCompiler.java

package flightsim.shiptraffic;

import flightsim.utils.TimeUtils;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import org.lc0277lib.geography.*;

// Referenced classes of package flightsim.shiptraffic:
//            I18N, ProgressAdapter, TrafficBGLFile, PlanRecord, 
//            BoatRecord, PlanLeg, BoatPlanLeg, BoatRoute, 
//            TrafficSector, SortableVector, SectorCrossing

public class BoatTrafficCompiler
{

    public BoatTrafficCompiler(Collection boats, Collection routes, List plans, Map acTable, Map routeTable, ProgressAdapter pm)
    {
        if(boats == null)
            boats = acTable.values();
        if(routes == null)
            routes = routeTable.values();
        pm.setSection(I18N._("BoatTrafficCompiler.CreateBGL"), 1);
        bglFile = new TrafficBGLFile();
        trafficSectors = bglFile.getTrafficSectors();
        pm.increment();
        pm.setSection(I18N._("BoatTrafficCompiler.AddBoats"), 2);
        bglFile.getBoatRecords().addAll(boats);
        pm.increment();
        bglFile.getBoatRoutes().addAll(routes);
        pm.increment();
        this.acTable = acTable;
        this.routeTable = routeTable;
        pm.setSection(I18N._("BoatTrafficCompiler.AddPlans"), plans.size());
        int number = -plans.size() - 1;
        List bglPlans = bglFile.getPlanRecords();
        for(Iterator iterator = plans.iterator(); iterator.hasNext(); pm.increment())
        {
            PlanRecord pl = (PlanRecord)iterator.next();
            pl.setNumber(number++);
            bglPlans.add(pl);
        }

        markRoutes(pm);
        insertArrivalTimes(pm);
        calculateSectorCrossings(pm);
    }

    private void insertArrivalTimes(ProgressAdapter pa)
    {
        List plans = bglFile.getPlanRecords();
        pa.setSection(I18N._("BoatTrafficCompiler.Arrivals"), plans.size() / 10);
        int np = 0;
        for(Iterator iterator = plans.iterator(); iterator.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator.next();
            BoatRecord ac = (BoatRecord)acTable.get(Integer.valueOf(pl.getObjid()));
            if(ac == null)
                throw new IllegalArgumentException(MessageFormat.format(I18N._("BoatTrafficCompiler.UnknownAircraft"), new Object[] {
                    Integer.valueOf(pl.getObjid())
                }));
            for(Iterator iterator1 = pl.getLegs().iterator(); iterator1.hasNext();)
            {
                PlanLeg leg = (PlanLeg)iterator1.next();
                BoatPlanLeg bpl = (BoatPlanLeg)leg;
                BoatRoute br = (BoatRoute)routeTable.get(Integer.valueOf(bpl.getRoute()));
                if(br == null)
                    throw new IllegalArgumentException(MessageFormat.format(I18N._("BoatTrafficCompiler.UnknownRoute"), new Object[] {
                        Integer.valueOf(bpl.getRoute())
                    }));
                double time = br.getDistance() / (double)ac.getSpeed();
                bpl.arrival = bpl.departure + time;
                if(bpl.arrival > (double)bpl.getPeriodHours())
                    bpl.arrival = bpl.arrival % (double)bpl.getPeriodHours();
            }

            if(np % 10 == 0)
                pa.increment();
            np++;
        }

    }

    private void markRoutes(ProgressAdapter pa)
    {
        List plans = bglFile.getPlanRecords();
        int np = 0;
        pa.setSection(I18N._("BoatTrafficCompiler.MarkingRoutes"), plans.size());
        for(Iterator iterator = plans.iterator(); iterator.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator.next();
            BoatRoute br;
            for(Iterator iterator2 = pl.getLegs().iterator(); iterator2.hasNext(); br.setUsed(true))
            {
                PlanLeg leg = (PlanLeg)iterator2.next();
                BoatPlanLeg bpl = (BoatPlanLeg)leg;
                br = (BoatRoute)routeTable.get(Integer.valueOf(bpl.getRoute()));
            }

            if(np % 10 == 0)
                pa.increment();
            np++;
        }

        for(Iterator iterator1 = routeTable.values().iterator(); iterator1.hasNext();)
        {
            BoatRoute br = (BoatRoute)iterator1.next();
            if(br.isUsed())
                bglFile.getBoatRoutes().add(br);
        }

        Collections.sort(bglFile.getBoatRoutes(), new Comparator() {

            public int compare(BoatRoute o1, BoatRoute o2)
            {
                return o1.getRouteId() - o2.getRouteId();
            }

            public int compare(Object obj, Object obj1)
            {
                return compare((BoatRoute)obj, (BoatRoute)obj1);
            }

            final BoatTrafficCompiler this$0;
            {
                this$0 = BoatTrafficCompiler.this;
            }
        }
);
    }

    public void calculateSectorCrossings(ProgressAdapter pa)
    {
        List plans = bglFile.getPlanRecords();
        pa.setSection(I18N._("BoatTrafficCompiler.CalculateCrossings"), plans.size() / 10);
        int np = 0;
        for(Iterator iterator = plans.iterator(); iterator.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator.next();
            BoatRecord ac = (BoatRecord)acTable.get(Integer.valueOf(pl.getObjid()));
            int speedKts = ac.getSpeed();
            calculateSectorCrossings(speedKts, pl);
            if(np % 10 == 0)
                pa.increment();
            np++;
        }

        pa.setSection(I18N._("BoatTrafficCompiler.SortingSector"), 256);
        for(int i = 0; i < 256; i++)
        {
            if(trafficSectors[i] != null)
            {
                for(int j = 0; j < 384; j++)
                    if(trafficSectors[i][j] != null)
                    {
                        for(Iterator itsc = trafficSectors[i][j].crossings.iterator(); itsc.hasNext();)
                        {
                            SectorCrossing sc = (SectorCrossing)itsc.next();
                            if(sc.entryTime == sc.exitTime && (sc.entryTime & 0xffffff) != 0)
                                itsc.remove();
                        }

                        trafficSectors[i][j].crossings.sort();
                    }

            }
            pa.increment();
        }

    }

    private void calculateSectorCrossings(double speedKts, PlanRecord pl)
    {
        LinkedList li = new LinkedList();
        PlanLeg leg;
        for(Iterator iterator = pl.getLegs().iterator(); iterator.hasNext(); calculateSectorCrossings(speedKts, (BoatPlanLeg)leg, li, pl))
            leg = (PlanLeg)iterator.next();

        if(li.size() == 1)
        {
            TrafficSector ts = (TrafficSector)li.getFirst();
            ts.crossings.add(new SectorCrossing(pl.getNumber(), TimeUtils.makeAITime(0.0D, pl.getPeriod()), TimeUtils.makeAITime(0.0D, pl.getPeriod())));
        }
    }

    private void calculateSectorCrossings(double speedKts, BoatPlanLeg leg, LinkedList sectorsList, PlanRecord pl)
    {
        LatLon last = null;
        TrafficSector lastSector = sectorsList.size() == 0 ? null : (TrafficSector)sectorsList.getLast();
        double timeAtPoint = leg.departure;
        BoatRoute br = (BoatRoute)routeTable.get(Integer.valueOf(leg.getRoute()));
        br.setUsed(true);
        List route = br.getRoute(leg.isReverse());
        for(Iterator iterator = route.iterator(); iterator.hasNext();)
        {
            LatLon pt = (LatLon)iterator.next();
            if(lastSector == null)
            {
                lastSector = sector(pt);
                sectorsList.add(lastSector);
            }
            if(last != null)
            {
                TrafficSector fromSector = sector(last);
                TrafficSector toSector = sector(pt);
                if(fromSector != sectorsList.getLast())
                    sectorsList.add(fromSector);
                calculateSectorCrossingsLine(last, pt, sectorsList, timeAtPoint, speedKts, pl);
                if(toSector != sectorsList.getLast())
                    sectorsList.add(toSector);
                timeAtPoint += ConvertUnits.metersToNauticMiles(GeoCalc.distance(last, pt)) / speedKts;
            }
            last = pt;
        }

    }

    private SectorCrossing lastPlaneCrossing(TrafficSector tsc, int id)
    {
        for(int i = tsc.crossings.size() - 1; i >= 0; i--)
        {
            SectorCrossing sc = (SectorCrossing)tsc.crossings.get(i);
            if(sc.plan == id)
                return sc;
        }

        return null;
    }

    private void calculateSectorCrossingsLine(LatLon from, LatLon to, LinkedList sectorsList, double timeAtFrom, double speedKts, 
            PlanRecord pl)
    {
        TrafficSector fromSector = sector(from);
        TrafficSector toSector = sector(to);
        if(GeoCalc.distance(from, to) < 0.01D)
        {
            if(fromSector != toSector)
            {
                SectorCrossing sc = lastPlaneCrossing(fromSector, pl.getNumber());
                if(sc == null || sc != null && sc.exitTime != 0)
                {
                    sc = new SectorCrossing(pl.getNumber());
                    fromSector.crossings.add(sc);
                }
                sc.exitTime = TimeUtils.makeAITime(timeAtFrom, pl.getPeriod());
                sc = lastPlaneCrossing(toSector, pl.getNumber());
                if(sc == null || sc != null && sc.entryTime != 0)
                {
                    sc = new SectorCrossing(pl.getNumber());
                    toSector.crossings.add(sc);
                }
                sc.entryTime = TimeUtils.makeAITime(timeAtFrom, pl.getPeriod());
            }
            return;
        }
        if(fromSector != toSector)
        {
            LatLon midPoint = null;
            if(from.getLongitude() * to.getLongitude() < 0.0D && Math.abs(from.getLongitude()) + Math.abs(to.getLongitude()) > 180D)
            {
                double midLat = from.getLatitude() + (to.getLatitude() - from.getLatitude()) / 2D;
                double toLon = to.getLongitude();
                if(toLon < 0.0D)
                    toLon += 360D;
                if(toLon > 0.0D)
                    toLon += -360D;
                double midLon = from.getLongitude() + (toLon - from.getLongitude()) / 2D;
                if(midLon > 180D)
                    midLon += -360D;
                if(midLon < -180D)
                    midLon += 360D;
                midPoint = new LatLon(midLat, midLon);
            } else
            {
                double midLat = from.getLatitude() + (to.getLatitude() - from.getLatitude()) / 2D;
                double midLon = from.getLongitude() + (to.getLongitude() - from.getLongitude()) / 2D;
                if(midLat > 90D)
                    midLat %= 90D;
                if(midLon > 180D)
                    midLon %= 180D;
                midPoint = new LatLon(midLat, midLon);
            }
            TrafficSector midSector = sector(midPoint);
            calculateSectorCrossingsLine(from, midPoint, sectorsList, timeAtFrom, speedKts, pl);
            if(midSector != sectorsList.getLast())
                sectorsList.add(midSector);
            timeAtFrom += ConvertUnits.metersToNauticMiles(GeoCalc.distance(from, midPoint)) / speedKts;
            calculateSectorCrossingsLine(midPoint, to, sectorsList, timeAtFrom, speedKts, pl);
        }
    }

    private TrafficSector sector(LatLon point)
    {
        short ewcoord = (short)(int)((point.getLongitude() / 360D) * 384D + 192D);
        short nscoord = (short)(int)((point.getLatitude() / -180D) * 256D + 128D);
        TrafficSector ewBand[] = trafficSectors[nscoord];
        if(ewBand == null)
        {
            ewBand = new TrafficSector[384];
            trafficSectors[nscoord] = ewBand;
        }
        TrafficSector sector = ewBand[ewcoord];
        if(sector == null)
        {
            sector = new TrafficSector(ewcoord, nscoord);
            ewBand[ewcoord] = sector;
        }
        return sector;
    }

    public void writeBGLFile(ProgressAdapter pa, File file)
        throws IOException
    {
        pa.setSection(I18N._("BoatTrafficCompiler.WritingBGL"), 1);
        bglFile.write(file);
        pa.increment();
    }

    private Map acTable;
    private Map routeTable;
    private TrafficSector trafficSectors[][];
    private TrafficBGLFile bglFile;
}
