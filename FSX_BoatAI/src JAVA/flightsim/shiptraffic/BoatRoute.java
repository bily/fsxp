// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoatRoute.java

package flightsim.shiptraffic;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kml.KMLFormatException;
import kml.KMLReader;
import org.lc0277lib.geography.*;

public class BoatRoute
{
    class BoatRouteLatLon extends LatLon
    {

        void write(DataOutput bf)
            throws IOException
        {
            bf.writeInt(unk1);
            bf.writeInt(ConvertUnits.lat2fsLat(getLatitude()));
            bf.writeInt(unk2);
            bf.writeInt(ConvertUnits.lon2fsLon(getLongitude()));
        }

        public String toString()
        {
            return (new StringBuilder(String.valueOf(getLatitude()))).append(" ").append(getLongitude()).append(" (").append(Integer.toHexString(unk1)).append(" ").append(Integer.toHexString(unk2)).append(")").toString();
        }

        String pointId()
        {
            return Integer.toString(unk2 >> 16 & 0xffff);
        }

        int unk1;
        int unk2;
        final BoatRoute this$0;

        private BoatRouteLatLon(double lat, double lon, int unk1, int unk2)
        {
			super(lat, lon);
            this$0 = BoatRoute.this;
            this.unk1 = unk1;
            this.unk2 = unk2;
        }

        BoatRouteLatLon(double d, double d1, int i, int j, 
                BoatRouteLatLon boatroutelatlon)
        {
            this(d, d1, i, j);
        }
    }


    BoatRoute(DataInput bf)
        throws IOException
    {
        used = false;
        reverseRoute = null;
        int recordlen = bf.readInt();
        routeId = bf.readInt();
        int np = (recordlen - 8) / 16;
        route = new Vector(np);
        for(int j = 0; j < np; j++)
        {
            int blank = bf.readInt();
            int lat = bf.readInt();
            int unk = bf.readInt();
            int lon = bf.readInt();
            BoatRouteLatLon pt = new BoatRouteLatLon(ConvertUnits.fsLat2Lat(lat), ConvertUnits.fsLon2Lon(lon), blank, unk, null);
            route.add(pt);
        }

    }

    public BoatRoute(int identifier)
    {
        used = false;
        reverseRoute = null;
        routeId = identifier;
        route = new Vector();
    }

    BoatRoute(int identifier, List points)
    {
        used = false;
        reverseRoute = null;
        routeId = identifier;
        route = points;
    }

    void write(DataOutput bf)
        throws IOException
    {
        int recordlen = 8 + route.size() * 16;
        bf.writeInt(recordlen);
        bf.writeInt(routeId);
        for(Iterator iterator = route.iterator(); iterator.hasNext();)
        {
            LatLon pt = (LatLon)iterator.next();
            if(pt instanceof BoatRouteLatLon)
            {
                BoatRouteLatLon brpt = (BoatRouteLatLon)pt;
                brpt.write(bf);
            } else
            {
                bf.writeInt(0);
                bf.writeInt(ConvertUnits.lat2fsLat(pt.getLatitude()));
                bf.writeInt((int)(Math.random() * 65536D) << 16);
                bf.writeInt(ConvertUnits.lon2fsLon(pt.getLongitude()));
            }
        }

    }

    public double getDistance()
    {
        double dist = 0.0D;
        LatLon last = null;
        for(Iterator iterator = route.iterator(); iterator.hasNext();)
        {
            LatLon pt = (LatLon)iterator.next();
            if(last != null)
                dist += ConvertUnits.metersToNauticMiles(GeoCalc.distance(last, pt));
            last = pt;
        }

        return dist;
    }

    public List reverseRoute()
    {
        if(reverseRoute == null)
        {
            reverseRoute = new Vector(route.size());
            for(int i = route.size() - 1; i >= 0; i--)
                reverseRoute.add((LatLon)route.get(i));

        }
        return reverseRoute;
    }

    public boolean isUsed()
    {
        return used;
    }

    public void setUsed(boolean used)
    {
        this.used = used;
    }

    public List getRoute()
    {
        return route;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List getRoute(boolean reverse)
    {
        if(!reverse)
            return route;
        else
            return reverseRoute();
    }

    public int getRouteId()
    {
        return routeId;
    }

    public void setRouteId(int routeId)
    {
        this.routeId = routeId;
    }

    public String guessRouteName(boolean reverse)
    {
        if(name != null && name.length() > 0)
            return name;
        LatLon firstPt = (LatLon)route.get(0);
        LatLon lastPt = (LatLon)route.get(route.size() - 1);
        if((firstPt instanceof BoatRouteLatLon) && (lastPt instanceof BoatRouteLatLon))
        {
            if(reverse)
                return (new StringBuilder(String.valueOf(((BoatRouteLatLon)lastPt).pointId()))).append("-").append(((BoatRouteLatLon)firstPt).pointId()).toString();
            else
                return (new StringBuilder(String.valueOf(((BoatRouteLatLon)firstPt).pointId()))).append("-").append(((BoatRouteLatLon)lastPt).pointId()).toString();
        } else
        {
            return (new StringBuilder(String.valueOf(reverse ? "<-" : "->"))).append(Integer.toString(routeId)).toString();
        }
    }

    public String toString()
    {
        return (new StringBuilder("Route #")).append(routeId).append(" ").append(route.size()).append(" points").toString();
    }

    public void printCSV(PrintStream ps, String from, String to)
    {
        ps.println((new StringBuilder(String.valueOf(routeId))).append(",").append(from).append(",").append(to).toString());
        ps.println("{");
        LatLon lt;
        for(Iterator iterator = route.iterator(); iterator.hasNext(); ps.println((new StringBuilder(String.valueOf(lt.getLatitude()))).append(", ").append(lt.getLongitude()).toString()))
            lt = (LatLon)iterator.next();

        ps.println("}");
    }

    public void printCSV(PrintStream ps)
    {
        if(name != null)
        {
            String parts[] = name.split(",");
            if(parts.length == 2)
                printCSV(ps, parts[0].trim(), parts[1].trim());
        } else
        {
            printCSV(ps, "", "");
        }
    }

    public static Map readFS10CSVBoatRoutes(File file)
        throws IOException
    {
        Map mbr = new TreeMap();
        BufferedReader br = new BufferedReader(new FileReader(file));
        Pattern routeHdrPattern = Pattern.compile("(\\d+),\\s*(\\w+),\\s*(\\w+)");
        Pattern routePtPattern = Pattern.compile("(-?\\d*\\.?\\d+),\\s*(-?\\d*\\.?\\d+)");
        BoatRoute current = null;
        String line;
        while((line = br.readLine()) != null) 
        {
            int ip = line.indexOf('#');
            if(ip != -1)
                line = line.substring(0, ip);
            line = line.trim();
            Matcher m = routeHdrPattern.matcher(line);
            if(m.matches())
            {
                try
                {
                    String idString = m.group(1);
                    int id = Integer.parseInt(idString);
                    current = new BoatRoute(id);
                    current.setName((new StringBuilder(String.valueOf(m.group(2)))).append(",").append(m.group(3)).toString());
                    mbr.put(Integer.valueOf(id), current);
                }
                catch(NumberFormatException nfe)
                {
                    nfe.printStackTrace();
                }
            } else
            {
                m = routePtPattern.matcher(line);
                if(m.matches() && current != null)
                    try
                    {
                        double lat = Double.parseDouble(m.group(1));
                        double lon = Double.parseDouble(m.group(2));
                        current.route.add(new LatLon(lat, lon));
                    }
                    catch(NumberFormatException nfe)
                    {
                        nfe.printStackTrace();
                    }
                else
                if(line.startsWith("}"))
                    current = null;
            }
        }
        br.close();
        return mbr;
    }

    public static Map readKMLBoatRoutes(String fileName)
        throws IOException, KMLFormatException
    {
        return readKMLBoatRoutes(new File(fileName));
    }

    public static Map readKMLBoatRoutes(File file) throws IOException, KMLFormatException
    {
        final KMLReader kmr = new KMLReader(file);
        final Map mbr = new TreeMap();
        kmr.extractPaths(new kml.KMLReader.KMLPathHandler() {

            public void handleError(Exception exception)
            {
            }

            public void handlePath(String kmlPathName, String coordinates)
            {
                try
                {
                    int id = Integer.parseInt(kmlPathName);
                    BoatRoute br = new BoatRoute(id);
                    br.setName(kmlPathName);
                    br.route.addAll(kmr.parsePoints(coordinates));
                    mbr.put(Integer.valueOf(id), br);
                }
                catch(NumberFormatException numberformatexception) { }
            }
        });
        return mbr;
    }

    private final List route;
    private int routeId;
    private String name;
    private boolean used;
    private Vector reverseRoute;

}
