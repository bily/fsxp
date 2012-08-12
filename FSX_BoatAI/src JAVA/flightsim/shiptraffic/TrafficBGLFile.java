// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrafficBGLFile.java

package flightsim.shiptraffic;

import java.io.*;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.lc0277lib.geography.LatLon;
import org.lc0277lib.leio.LERandomAccessFile;

// Referenced classes of package flightsim.shiptraffic:
//            BoatRecord, PlanRecord, NotBoatException, TrafficSector, 
//            BoatRoute, PlanLeg, BoatPlanLeg, SortableVector, 
//            SectorCrossing

public class TrafficBGLFile
{
    private class DatabaseDescriptor
    {

        void write(LERandomAccessFile bf, int dbPos)
            throws IOException
        {
            long oldpos = bf.getFilePointer();
            bf.seek(172 + dbPos * 20);
            bf.writeInt(indexlen);
            bf.writeInt(recordsize);
            bf.writeInt(unknown);
            bf.writeInt(id);
            bf.writeInt(filepos);
            bf.seek(oldpos);
        }

        public String toString()
        {
            return (new StringBuilder("DBS: ")).append(indexlen).append(" ").append(recordsize).append(" ").append(unknown).append(" ").append(id).append(" ").append(filepos).toString();
        }

        int indexlen;
        int recordsize;
        int unknown;
        int id;
        int filepos;
        final TrafficBGLFile this$0;

        DatabaseDescriptor(DataInput bf)
            throws IOException
        {
			super();
            this$0 = TrafficBGLFile.this;
            indexlen = bf.readInt();
            recordsize = bf.readInt();
            unknown = bf.readInt();
            id = bf.readInt();
            filepos = bf.readInt();
        }

        DatabaseDescriptor(int indexlen, int id, int filepos)
        {
            super();
			this$0 = TrafficBGLFile.this;
            this.indexlen = indexlen;
            recordsize = 0;
            unknown = 1;
            this.id = id;
            this.filepos = filepos;
        }
    }


    public TrafficBGLFile(File file)
        throws IOException
    {
        this(file, 255);
    }

    public TrafficBGLFile(File file, int parseMask)
        throws IOException
    {
        boatRecords = new LinkedList();
        planRecords = new LinkedList();
        trafficSectors = new TrafficSector[256][];
        boatRoutes = new LinkedList();
        LERandomAccessFile bf = new LERandomAccessFile(file, "r");
        readBGL(bf, parseMask);
        bf.close();
    }

    public TrafficBGLFile(LERandomAccessFile bf, int parseMask)
        throws IOException
    {
        boatRecords = new LinkedList();
        planRecords = new LinkedList();
        trafficSectors = new TrafficSector[256][];
        boatRoutes = new LinkedList();
        readBGL(bf, parseMask);
        bf.close();
    }

    public TrafficBGLFile()
    {
        boatRecords = new LinkedList();
        planRecords = new LinkedList();
        trafficSectors = new TrafficSector[256][];
        boatRoutes = new LinkedList();
        northBound = 0x989d56;
        southBound = 0xff6762aa;
        eastBound = -1;
        westBound = 0;
    }

    private void readBGL(LERandomAccessFile bf, int parseMask)
        throws IOException
    {
        readHeader(bf);
        DatabaseDescriptor adatabasedescriptor[] = dbDescriptors;
        int i = 0;
        for(int j = adatabasedescriptor.length; i < j; i++)
        {
            DatabaseDescriptor db = adatabasedescriptor[i];
            switch(db.id)
            {
            case 2: // '\002'
                if((parseMask & 2) != 0)
                    readAircraftDb(db, bf);
                break;

            case 5: // '\005'
                if((parseMask & 0x10) != 0)
                    readPlanDb(db, bf);
                break;

            case 6: // '\006'
                if((parseMask & 0x20) != 0)
                    readSectorCrossingTable(db, bf);
                break;

            case 7: // '\007'
                if((parseMask & 0x40) != 0)
                    readBoatRoutes(db, bf);
                break;

            case 1: // '\001'
            case 3: // '\003'
            case 4: // '\004'
                System.err.println((new StringBuilder("Unsupported DB (")).append(db.id).append(") in traffic file").toString());
                break;

            default:
                System.err.println((new StringBuilder("Unknown DB (")).append(db.id).append(") starting at ").append(db.filepos).toString());
                break;
            }
        }

    }

    private void readHeader(LERandomAccessFile bf)
        throws IOException
    {
        short s = bf.readShort();
        if(s != 1)
            throw new IllegalArgumentException((new StringBuilder("Invalid world set number (got ")).append(s).append(")").toString());
        northBound = bf.readInt();
        southBound = bf.readInt();
        eastBound = bf.readInt();
        westBound = bf.readInt();
        bf.seek(118L);
        int magic = bf.readInt();
        if(magic != 0x87654321)
            throw new IllegalArgumentException("Invalid BGL Magic");
        bf.seek(168L);
        int ndb = bf.readInt();
        dbDescriptors = new DatabaseDescriptor[ndb];
        for(int i = 0; i < ndb; i++)
            dbDescriptors[i] = new DatabaseDescriptor(bf);

    }

    private void readAircraftDb(DatabaseDescriptor db, LERandomAccessFile bf)
        throws IOException
    {
        bf.seek(db.filepos);
        int size = db.indexlen;
        for(int i = 0; i < size; i++)
        {
            bf.readInt();
            int offset = bf.readInt();
            long curpos = bf.getFilePointer();
            bf.seek(offset);
            boatRecords.add(new BoatRecord(bf));
            bf.seek(curpos);
        }

    }

    private void readPlanDb(DatabaseDescriptor db, LERandomAccessFile bf)
        throws IOException
    {
        bf.seek(db.filepos);
        int size = db.indexlen;
        for(int i = 0; i < size; i++)
        {
            bf.readInt();
            int offset = bf.readInt();
            long curpos = bf.getFilePointer();
            bf.seek(offset);
            try
            {
                PlanRecord plrec = new PlanRecord(bf);
                planRecords.add(plrec);
            }
            catch(NotBoatException notboatexception) { }
            bf.seek(curpos);
        }

    }

    private void readSectorCrossingTable(DatabaseDescriptor db, LERandomAccessFile bf)
        throws IOException
    {
        bf.seek(db.filepos);
        for(int i = 0; i < db.indexlen; i++)
        {
            int ewcoord = bf.readShort() & 0xffff;
            int nscoord = bf.readShort() & 0xffff;
            int sectorPos = bf.readInt();
            long origpos = bf.getFilePointer();
            bf.seek(sectorPos);
            TrafficSector ts = new TrafficSector(bf);
            TrafficSector band[] = trafficSectors[nscoord];
            if(band == null)
            {
                band = new TrafficSector[384];
                trafficSectors[nscoord] = band;
            }
            band[ewcoord] = ts;
            bf.seek(origpos);
        }

    }

    private void readBoatRoutes(DatabaseDescriptor db, LERandomAccessFile bf)
        throws IOException
    {
        bf.seek(db.filepos);
        for(int i = 0; i < db.indexlen; i++)
        {
            bf.readInt();
            int routePos = bf.readInt();
            long origpos = bf.getFilePointer();
            bf.seek(routePos);
            BoatRoute br = new BoatRoute(bf);
            boatRoutes.add(br);
            bf.seek(origpos);
        }

    }

    public List getBoatRecords()
    {
        return boatRecords;
    }

    public List getPlanRecords()
    {
        return planRecords;
    }

    public TrafficSector[][] getTrafficSectors()
    {
        return trafficSectors;
    }

    public List getTrafficSectorsCollection()
    {
        List sectorsList = new LinkedList();
        for(int i = 0; i < 256; i++)
            if(trafficSectors[i] != null)
            {
                for(int j = 0; j < 384; j++)
                    if(trafficSectors[i][j] != null)
                        sectorsList.add(trafficSectors[i][j]);

            }

        return sectorsList;
    }

    public List getBoatRoutes()
    {
        return boatRoutes;
    }

    BoatRoute getBoatRoute(int route)
    {
        for(Iterator iterator = boatRoutes.iterator(); iterator.hasNext();)
        {
            BoatRoute br = (BoatRoute)iterator.next();
            if(br.getRouteId() == route)
                return br;
        }

        return null;
    }

    public void write(File file)
        throws IOException
    {
        LERandomAccessFile bf = new LERandomAccessFile(file, "rw");
        writeHeader(bf);
        bf.writeInt(4);
        bf.seek(252L);
        writeAircraftDb(bf, 0);
        writePlanDb(bf, 1);
        writeSectorCrossingTable(bf, 2);
        writeBoatRoutes(bf, 3);
        bf.close();
    }

    private void writeHeader(DataOutput bf)
        throws IOException
    {
        bf.writeShort(1);
        bf.writeInt(northBound);
        bf.writeInt(southBound);
        bf.writeInt(eastBound);
        bf.writeInt(westBound);
        for(int i = 0; i < 28; i++)
            bf.write(0);

        bf.writeInt(168);
        for(int i = 0; i < 68; i++)
            bf.write(0);

        bf.writeInt(0x87654321);
        for(int i = 0; i < 46; i++)
            bf.write(0);

    }

    private void writeAircraftDb(LERandomAccessFile bf, int dbPos)
        throws IOException
    {
        long startpos = bf.getFilePointer();
        bf.seek(startpos + (long)(8 * boatRecords.size()));
        int nr = 0;
        for(Iterator iterator = boatRecords.iterator(); iterator.hasNext();)
        {
            BoatRecord ac = (BoatRecord)iterator.next();
            long recordPos = bf.getFilePointer();
            bf.seek(startpos + (long)(8 * nr));
            bf.writeInt(ac.getIdentifier());
            bf.writeInt((int)recordPos);
            bf.seek(recordPos);
            ac.write(bf);
            nr++;
        }

        (new DatabaseDescriptor(boatRecords.size(), 2, (int)startpos)).write(bf, dbPos);
    }

    private void writePlanDb(LERandomAccessFile bf, int dbPos)
        throws IOException
    {
        long startpos = bf.getFilePointer();
        bf.seek(startpos + (long)(8 * planRecords.size()));
        int nr = 0;
        for(Iterator iterator = planRecords.iterator(); iterator.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator.next();
            long recordPos = bf.getFilePointer();
            bf.seek(startpos + (long)(8 * nr));
            bf.writeInt(pl.getNumber());
            bf.writeInt((int)recordPos);
            bf.seek(recordPos);
            pl.write(bf);
            nr++;
        }

        (new DatabaseDescriptor(planRecords.size(), 5, (int)startpos)).write(bf, dbPos);
    }

    private void writeSectorCrossingTable(LERandomAccessFile bf, int dbPos)
        throws IOException
    {
        long startpos = bf.getFilePointer();
        List sectorsList = getTrafficSectorsCollection();
        int nsectors = sectorsList.size();
        bf.seek(startpos + (long)(8 * nsectors));
        int i = 0;
        for(Iterator iterator = sectorsList.iterator(); iterator.hasNext();)
        {
            TrafficSector ts = (TrafficSector)iterator.next();
            long recordPos = bf.getFilePointer();
            bf.seek(startpos + (long)(8 * i));
            bf.writeShort(ts.ewcoord);
            bf.writeShort(ts.nscoord);
            bf.writeInt((int)recordPos);
            bf.seek(recordPos);
            ts.write(bf);
            i++;
        }

        (new DatabaseDescriptor(nsectors, 6, (int)startpos)).write(bf, dbPos);
    }

    private void writeBoatRoutes(LERandomAccessFile bf, int dbPos)
        throws IOException
    {
        long startpos = bf.getFilePointer();
        int nroutes = boatRoutes.size();
        bf.seek(startpos + (long)(8 * nroutes));
        for(int i = 0; i < nroutes; i++)
        {
            BoatRoute br = (BoatRoute)boatRoutes.get(i);
            long recordPos = bf.getFilePointer();
            bf.seek(startpos + (long)(8 * i));
            bf.writeInt(br.getRouteId());
            bf.writeInt((int)recordPos);
            bf.seek(recordPos);
            br.write(bf);
        }

        (new DatabaseDescriptor(boatRoutes.size(), 7, (int)startpos)).write(bf, dbPos);
    }

    public void printCSVRoutes(String csvFile)
        throws IOException
    {
        FileOutputStream fos = new FileOutputStream(csvFile);
        PrintStream ps = new PrintStream(fos);
        ps.println("#ID, from, to");
        ps.println("#from and to are not defined");
        int i = 0;
        for(Iterator iterator = boatRoutes.iterator(); iterator.hasNext();)
        {
            BoatRoute br = (BoatRoute)iterator.next();
            if(!br.isUsed())
                br.printCSV(ps, (new StringBuilder(String.valueOf(++i))).toString(), (new StringBuilder(String.valueOf(++i))).toString());
        }

        ps.flush();
        fos.close();
    }

    public static void main(String args[])
        throws IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
    {
        TrafficBGLFile tfbg = new TrafficBGLFile(new File(args[0]));
        System.out.println("\nAircrafts/boats: ");
        BoatRecord ar;
        for(Iterator iterator = tfbg.getBoatRecords().iterator(); iterator.hasNext(); System.out.println(ar))
            ar = (BoatRecord)iterator.next();

        System.out.println("\nSchedules: ");
        for(Iterator iterator1 = tfbg.getPlanRecords().iterator(); iterator1.hasNext();)
        {
            PlanRecord pl = (PlanRecord)iterator1.next();
            System.out.println(pl.toString());
            for(Iterator iterator4 = pl.getLegs().iterator(); iterator4.hasNext();)
            {
                PlanLeg leg = (PlanLeg)iterator4.next();
                BoatPlanLeg bpl = (BoatPlanLeg)leg;
                System.out.println((new StringBuilder("\t")).append(leg).toString());
                BoatRoute br = tfbg.getBoatRoute(((BoatPlanLeg)leg).getRoute());
                if((bpl.getFlags() & 0x8000000) != 0)
                    br.setUsed(true);
                else
                    br.setUsed(true);
            }

        }

        System.out.println("\nSectors: ");
        for(Iterator iterator2 = tfbg.getTrafficSectorsCollection().iterator(); iterator2.hasNext();)
        {
            TrafficSector ts = (TrafficSector)iterator2.next();
            System.out.println((new StringBuilder(String.valueOf(ts.toString()))).append(" ").append(ts.getLatitudeString()).append(" ").append(ts.getLongitudeString()).toString());
            SectorCrossing sc;
            for(Iterator iterator5 = ts.crossings.iterator(); iterator5.hasNext(); System.out.println((new StringBuilder("\t")).append(sc).toString()))
                sc = (SectorCrossing)iterator5.next();

        }

        System.out.println("\nRoutes: ");
        for(Iterator iterator3 = tfbg.getBoatRoutes().iterator(); iterator3.hasNext();)
        {
            BoatRoute br = (BoatRoute)iterator3.next();
            System.out.println(br.toString());
            LatLon routePt;
            for(Iterator iterator6 = br.getRoute().iterator(); iterator6.hasNext(); System.out.println((new StringBuilder("\t")).append(routePt.toString()).toString()))
                routePt = (LatLon)iterator6.next();

        }

    }

    private int northBound;
    private int southBound;
    private int eastBound;
    private int westBound;
    private DatabaseDescriptor dbDescriptors[];
    private List boatRecords;
    private List planRecords;
    private TrafficSector trafficSectors[][];
    private List boatRoutes;
    private static final int BGL_MAGIC = 0x87654321;
    public static final int AIRPORT_DB_ID = 1;
    public static final int AIRCRAFT_DB_ID = 2;
    public static final int FLIGHTPLAN_DB_ID = 3;
    public static final int TRAFFIC_SECTORS_DB_ID = 4;
    public static final int FLIGHTPLAN_X_DB_ID = 5;
    public static final int TRAFFIC_SECTORS_X_DB_ID = 6;
    public static final int BOAT_ROUTES_DB_ID = 7;
    public static final int PARSE_AIRPORTS = 1;
    public static final int PARSE_OBJECTS = 2;
    public static final int PARSE_PLANS = 4;
    public static final int PARSE_SECTORS = 8;
    public static final int PARSE_PLANS_X = 16;
    public static final int PARSE_SECTORS_X = 32;
    public static final int PARSE_BOAT_ROUTES = 64;
}
