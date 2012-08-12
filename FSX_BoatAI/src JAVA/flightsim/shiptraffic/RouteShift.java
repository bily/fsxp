// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RouteShift.java

package flightsim.shiptraffic;

import java.io.*;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import kml.KMLWriter;
import org.lc0277lib.geography.LatLon;

// Referenced classes of package flightsim.shiptraffic:
//            BoatRoute

public class RouteShift
{

    public RouteShift()
    {
    }

    public static void main(String args[])
    {
        if(args.length < 4)
        {
            System.err.println("Usage: RouteShift <routes file> <route id> <lat shift> <lon shift> [-out]");
            System.err.println("Route file could be CSV or KML (will check extension)");
            System.err.println("Lat/Lon shifts are in meter");
            System.exit(-1);
        }
        Map routes = null;
        String routeFileName = args[0];
        try
        {
            if(routeFileName.toLowerCase().endsWith(".csv"))
                routes = BoatRoute.readFS10CSVBoatRoutes(new File(routeFileName));
            else
            if(routeFileName.toLowerCase().endsWith(".kml"))
            {
                routes = BoatRoute.readKMLBoatRoutes(routeFileName);
            } else
            {
                System.err.println((new StringBuilder("Unknown route format: ")).append(routeFileName).toString());
                System.exit(-4);
            }
        }
        catch(Exception e)
        {
            System.err.println((new StringBuilder("Cannot read routes: ")).append(e.getMessage()).toString());
            System.exit(-3);
        }
        BoatRoute br = null;
        try
        {
            br = (BoatRoute)routes.get(Integer.valueOf(Integer.parseInt(args[1])));
            if(br == null)
            {
                System.err.println((new StringBuilder("Route not found: ")).append(args[1]).toString());
                System.exit(-2);
            }
        }
        catch(NumberFormatException nfe)
        {
            System.err.println((new StringBuilder("Bad route format: ")).append(nfe.getMessage()).toString());
            System.exit(-5);
        }
        double latShift = Double.parseDouble(args[2]) / 111195D;
        double lonShift = Double.parseDouble(args[3]) / 78275D;
        int newid;
        for(newid = br.getRouteId() + 1; routes.containsKey(Integer.valueOf(newid)); newid++);
        BoatRoute newRoute = new BoatRoute(newid);
        newRoute.setName(br.getName());
        routes.put(Integer.valueOf(newRoute.getRouteId()), newRoute);
        System.out.println((new StringBuilder("Created route: ")).append(newid).toString());
        LatLon shifted;
        for(Iterator iterator = br.getRoute().iterator(); iterator.hasNext(); newRoute.getRoute().add(shifted))
        {
            LatLon pt = (LatLon)iterator.next();
            shifted = new LatLon(pt.getLatitude() + latShift, pt.getLongitude() + lonShift);
        }

        if(args.length == 5)
            newRoute.printCSV(System.out);
        else
        if(routeFileName.toLowerCase().endsWith(".csv"))
        {
            PrintStream ps = null;
            try
            {
                ps = new PrintStream(new FileOutputStream(routeFileName));
            }
            catch(FileNotFoundException e)
            {
                System.err.println((new StringBuilder("Cannot write CML Document: ")).append(e.getMessage()).toString());
                System.exit(-1);
            }
            BoatRoute or;
            for(Iterator iterator1 = routes.values().iterator(); iterator1.hasNext(); or.printCSV(ps))
                or = (BoatRoute)iterator1.next();

            ps.flush();
            ps.close();
        } else
        if(routeFileName.toLowerCase().endsWith(".kml"))
        {
            KMLWriter kml = null;
            try
            {
                kml = new KMLWriter();
            }
            catch(ParserConfigurationException e)
            {
                System.err.println((new StringBuilder("Cannot write KML (")).append(e.getMessage()).append(")").toString());
                System.exit(-1);
            }
            kml.createFolder("Boat routes");
            BoatRoute or;
            for(Iterator iterator2 = routes.values().iterator(); iterator2.hasNext(); kml.addPath(or.getRoute(), Integer.toString(or.getRouteId())))
                or = (BoatRoute)iterator2.next();

            try
            {
                kml.write(new File(routeFileName));
                System.out.println((new StringBuilder("Wrote to ")).append(routeFileName).toString());
            }
            catch(Exception e)
            {
                System.err.println((new StringBuilder("Cannot write KML (")).append(e.getMessage()).append(")").toString());
                System.exit(-1);
            }
        } else
        {
            System.err.println((new StringBuilder("Unknown route format: ")).append(routeFileName).toString());
            System.exit(-4);
        }
    }
}
