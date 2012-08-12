// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrafficProject.java

package flightsim.shiptraffic;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import kml.KMLFormatException;
import org.lc0277lib.geography.GeoCalc;
import org.lc0277lib.geography.LatLon;

// Referenced classes of package flightsim.shiptraffic:
//            TrafficFormatException, I18N, ProgressAdapter, BoatRecord, 
//            BoatRoute, PlanRecord, PlanLeg, BoatPlanLeg, 
//            BoatTrafficCompiler

public class TrafficProject
{

    public TrafficProject(File acFile, File rtFile, File plFile, ProgressAdapter pma)
        throws IOException, KMLFormatException, TrafficFormatException
    {
        pma.setSection(I18N._("TrafficProject.ParsingTextFiles"), 3);
        boatTable = BoatRecord.readTToolsAircrafts(acFile);
        boatRecords = boatTable.values();
        pma.increment();
        String rtFileName = rtFile.getName().toLowerCase();
        if(rtFileName.endsWith(".csv"))
            routeTable = BoatRoute.readFS10CSVBoatRoutes(rtFile);
        else
        if(rtFileName.endsWith(".kml") || rtFileName.endsWith(".kmz"))
            routeTable = BoatRoute.readKMLBoatRoutes(rtFile);
        else
            throw new TrafficFormatException(I18N._("TrafficProject.UnknownRouteTypes"));
        boatRoutes = routeTable.values();
        pma.increment();
        planRecords = PlanRecord.readTToolsBoatPlans(plFile);
        pma.increment();
        Set ids = new HashSet();
        pma.setSection(I18N._("TrafficProject.VerifyingTraffic"), 3);
        ids.clear();
        if(boatRecords.size() == 0)
            throw new TrafficFormatException(I18N._("TrafficProject.Need1Boat"));
        BoatRecord br;
        for(Iterator iterator = boatRecords.iterator(); iterator.hasNext(); ids.add(Integer.valueOf(br.getIdentifier())))
        {
            br = (BoatRecord)iterator.next();
            if(ids.contains(Integer.valueOf(br.getIdentifier())))
                throw new TrafficFormatException(br, MessageFormat.format(I18N._("TrafficProject.DupBoat"), new Object[] {
                    Integer.valueOf(br.getIdentifier())
                }));
            if(br.getSpeed() <= 0 || br.getSpeed() > 65535)
                throw new TrafficFormatException(br, MessageFormat.format(I18N._("TrafficProject.InvalidSpeed"), new Object[] {
                    Integer.valueOf(br.getSpeed())
                }));
            if(br.getName() == null || br.getName().length() == 0)
                throw new TrafficFormatException(br, MessageFormat.format(I18N._("TrafficProject.InvalidBoatName"), new Object[] {
                    br.getName()
                }));
        }

        pma.increment();
        ids.clear();
        if(routeTable.size() == 0) throw new TrafficFormatException(I18N._("TrafficProject.Need1Route"));
		BoatRoute br1;
        for(Iterator iterator1 = boatRoutes.iterator(); iterator1.hasNext(); ids.add(Integer.valueOf(br1.getRouteId())))
        {
            br1 = (BoatRoute)iterator1.next();
            if(ids.contains(Integer.valueOf(br1.getRouteId())))
                throw new TrafficFormatException(br1, MessageFormat.format(I18N._("TrafficProject.DupRoute"), new Object[] {
                    Integer.valueOf(br1.getRouteId())
                }));
            List points = br1.getRoute();
            if(points == null || points.size() < 2)
                throw new TrafficFormatException(br1, I18N._("TrafficProject.Need2Points"));
        }

        pma.increment();
        if(planRecords.size() == 0)
            throw new TrafficFormatException(I18N._("TrafficProject.Need1Plan"));
        for(Iterator iterator2 = planRecords.iterator(); iterator2.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator2.next();
            BoatRecord plBoat = lookupBoat(pl);
            if(plBoat == null)
                throw new TrafficFormatException(pl, MessageFormat.format(I18N._("TrafficProject.UnkBoat"), new Object[] {
                    Integer.valueOf(pl.getObjid() + 1)
                }));
            if(pl.getName() == null || pl.getName().length() > 10)
                throw new TrafficFormatException(pl, MessageFormat.format(I18N._("TrafficProject.InvalidPlanName"), new Object[] {
                    pl.getName()
                }));
            if(pl.getPercent() < 0 || pl.getPercent() > 100)
                throw new TrafficFormatException(pl, MessageFormat.format(I18N._("TrafficProject.InvalidPercent"), new Object[] {
                    Integer.valueOf(pl.getPercent())
                }));
            List lpl = pl.getLegs();
            ids.clear();
            LatLon lastPt = null;
            int legidx = 0;
            for(Iterator iterator3 = lpl.iterator(); iterator3.hasNext();)
            {
                PlanLeg leg = (PlanLeg)iterator3.next();
                BoatPlanLeg bpl = (BoatPlanLeg)leg;
                BoatRoute legRoute = lookupRoute(bpl);
                if(legRoute == null)
                    throw new TrafficFormatException(bpl, MessageFormat.format(I18N._("TrafficProject.UnkRouteID"), new Object[] {
                        Integer.valueOf(bpl.getRoute())
                    }));
                ids.add(Integer.valueOf(legRoute.getRouteId()));
                List route = legRoute.getRoute(bpl.isReverse());
                LatLon legFirstPt = (LatLon)route.get(0);
                LatLon legLastPt = (LatLon)route.get(route.size() - 1);
                if(lastPt != null)
                {
                    double dist = GeoCalc.distance(lastPt, legFirstPt);
                    if(dist > 0.80000000000000004D)
                        throw new TrafficFormatException(bpl, MessageFormat.format(I18N._("TrafficProject.TooLong1"), new Object[] {
                            Integer.valueOf(legidx - 1), Integer.valueOf(legidx), Double.valueOf(dist)
                        }));
                }
                lastPt = legLastPt;
                legidx++;
            }

            legidx = 0;
            BoatPlanLeg bpl = (BoatPlanLeg)pl.getLeg(0);
            BoatRoute br2 = lookupRoute(bpl);
            LatLon legFirstPt = (LatLon)br2.getRoute(bpl.isReverse()).get(0);
            double dist = GeoCalc.distance(lastPt, legFirstPt);
            if(dist > 0.80000000000000004D)
                throw new TrafficFormatException(bpl, MessageFormat.format(I18N._("TrafficProject.TooLong2"), new Object[] {
                    Integer.valueOf(lpl.size() - 1), Integer.valueOf(0), Double.valueOf(dist)
                }));
            if(ids.size() > 2)
                throw new TrafficFormatException(bpl, I18N._("TrafficProject.Morethan2routes"));
        }

        pma.increment();
    }

    private BoatRoute lookupRoute(BoatPlanLeg bpl)
    {
        return (BoatRoute)routeTable.get(Integer.valueOf(bpl.getRoute()));
    }

    private BoatRecord lookupBoat(PlanRecord pl)
    {
        return (BoatRecord)boatTable.get(Integer.valueOf(pl.getObjid()));
    }

    public void compile(File out, ProgressAdapter pa)
        throws IOException
    {
        BoatTrafficCompiler compiler = new BoatTrafficCompiler(boatRecords, boatRoutes, planRecords, boatTable, routeTable, pa);
        compiler.writeBGLFile(pa, out);
    }

    public void inserArrivals(File plFile)
        throws IOException
    {
        File tempFile = new File(plFile.getParent(), (new StringBuilder("temp")).append(plFile.getName()).toString());
        PlanRecord.insertArrivals(plFile, tempFile, planRecords);
        plFile.delete();
        tempFile.renameTo(plFile);
    }

    private Collection boatRoutes;
    private Collection boatRecords;
    private List planRecords;
    private Map boatTable;
    private Map routeTable;
}
