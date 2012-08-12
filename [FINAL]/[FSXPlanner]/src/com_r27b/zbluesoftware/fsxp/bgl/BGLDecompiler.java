package com.zbluesoftware.fsxp.bgl;

import com.zbluesoftware.fsxp.bgl.item.Item;
import com.zbluesoftware.fsxp.bgl.object.AirportObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachLegObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachLightObject;
import com.zbluesoftware.fsxp.bgl.object.ApproachObject;
import com.zbluesoftware.fsxp.bgl.object.ApronEdgeLightObject;
import com.zbluesoftware.fsxp.bgl.object.ApronObject;
import com.zbluesoftware.fsxp.bgl.object.BaseObject;
import com.zbluesoftware.fsxp.bgl.object.BlastFenceObject;
import com.zbluesoftware.fsxp.bgl.object.BoundaryFenceObject;
import com.zbluesoftware.fsxp.bgl.object.ComObject;
import com.zbluesoftware.fsxp.bgl.object.DMEArcObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteFrequencyObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteRunwayObject;
import com.zbluesoftware.fsxp.bgl.object.DeleteStartObject;
import com.zbluesoftware.fsxp.bgl.object.EdgeLightObject;
import com.zbluesoftware.fsxp.bgl.object.FileObject;
import com.zbluesoftware.fsxp.bgl.object.HelipadObject;
import com.zbluesoftware.fsxp.bgl.object.JetwayObject;
import com.zbluesoftware.fsxp.bgl.object.LegObject;
import com.zbluesoftware.fsxp.bgl.object.MissedApproachLegObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayDetailObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayObject;
import com.zbluesoftware.fsxp.bgl.object.RunwayVasiObject;
import com.zbluesoftware.fsxp.bgl.object.SectionHeaderObject;
import com.zbluesoftware.fsxp.bgl.object.SectionPointerObject;
import com.zbluesoftware.fsxp.bgl.object.ServicesObject;
import com.zbluesoftware.fsxp.bgl.object.StartObject;
import com.zbluesoftware.fsxp.bgl.object.TowerSceneryObject;
import com.zbluesoftware.fsxp.bgl.object.TransitionLegObject;
import com.zbluesoftware.fsxp.bgl.object.TransitionObject;
import com.zbluesoftware.fsxp.bgl.object.UnknownObject;
import com.zbluesoftware.fsxp.bgl.object.VertexObject;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import java.awt.Component;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;

public class BGLDecompiler {

    private FileObject fileObject;
    private String airportICAO;
    private static String[] sections;
    private static BGLDecompiler decompiler = null;
    private static String[] pointers = new String[15];
    private ArrayList airportAL = new ArrayList();
    private byte[] byteData = new byte[1024];

    public static BGLDecompiler getInstance()
    {
        if( decompiler == null )
            decompiler = new BGLDecompiler();
        return decompiler;
    }

    public Document decompile(File bglFile, Frame parent, boolean readOBX, boolean showResults, String airportICAO)
    {
        Document xmlDoc;
        RandomAccessFile raFile;

        airportAL = new ArrayList();
        this.airportICAO = airportICAO;
        ProgressDialog.showDialog( parent );
        ProgressDialog.updateDisplay( "Reading file" );
        xmlDoc = null;
        raFile = null;
        try
        {
            raFile = new RandomAccessFile( bglFile, "r" );
            xmlDoc = parseData( bglFile.getName(), xmlDoc, raFile );
        }
        catch( FileNotFoundException ignored )
        {
                JOptionPane.showMessageDialog( parent, new StringBuilder().append( "Unable to find the file:\n" ).append( bglFile.getAbsolutePath() ).toString(), "File Not Found Error", 0 );
                ProgressDialog.hideDialog();
                LogEngine.getInstance().log( ignored );
        }
        finally
        {
        try
        {
            if( raFile != null )
                raFile.close();
        }
        catch( IOException ioexception1 )
        {
				raFile = null;
				System.gc();
        }
		}
		
        raFile = null;
        System.gc();
        if( !readOBX )
            ProgressDialog.hideDialog();
        if( showResults )
            BGLDisplay.showDialog( parent, fileObject, bglFile.getAbsolutePath() );
        if( readOBX )
        {
            int i;
            File file1;

            if( SettingsEngine.getInstance().getReadScenery() )
            {
                airportAL.clear();
                airportAL = null;
                System.gc();
                airportAL = new ArrayList();
                System.gc();
                i = 0;
                file1 = null;
                if( bglFile.getName().startsWith( "APX" ) )
                {
                    file1 = new File( new StringBuilder().append( bglFile.getParent() ).append( File.separator ).append( "OBX" ).append( bglFile.getName().substring( 3 ) ).toString() );
                    if( file1.exists() )
                    {
                        i = 1;
                        try
                        {
                            raFile = new RandomAccessFile( file1, "r" );
                            xmlDoc = parseData( file1.getName(), xmlDoc, raFile );
                        }
                        catch( FileNotFoundException filenotfoundexception1 )
                        {
                        }
                        finally
                        {
                            try
                            {
                                if( raFile != null )
                                    raFile.close();
                            }
                            catch( IOException ioexception2 )
                            {
                                raFile = null;
                                System.gc();
                            }
                        }
                    }
                }
                if( showResults && i != 0 )
                    BGLDisplay.showDialog( parent, fileObject, file1.getAbsolutePath() );
                file1 = null;
            }
            if( SettingsEngine.getInstance().getReadNav() )
            {
                airportAL.clear();
                airportAL = null;
                System.gc();
                airportAL = new ArrayList();
                System.gc();
                i = 0;
                file1 = null;
                if( bglFile.getName().startsWith( "APX" ) )
                {
                    Object obj1 = bglFile.getParentFile().getParentFile().getName();

                    file1 = new File( new StringBuilder().append( bglFile.getParent() ).append( File.separator ).append( "NVX" ).append( (String) obj1 ).append( "0.bgl" ).toString() );
                    if( file1.exists() )
                    {
                        i = 1;
                        try
                        {
                            raFile = new RandomAccessFile( file1, "r" );
                            xmlDoc = parseData( file1.getName(), xmlDoc, raFile );
                        }
                        catch( FileNotFoundException ignored )
                        {
                        }
                        finally
                        {
                            try
                            {
                                if( raFile != null )
                                    raFile.close();
                            }
                            catch( IOException ioexception3 )
                            {
                                raFile = null;
                                System.gc();
                            }
                        }
                    }
                }
                ProgressDialog.hideDialog();
                if( showResults && i != 0 )
                    BGLDisplay.showDialog( parent, fileObject, file1.getAbsolutePath() );
                file1 = null;
            }
            ProgressDialog.hideDialog();
        }
        airportAL.clear();
        airportAL = null;
        fileObject = null;
        System.gc();
        return xmlDoc;
    }

    private Document parseData(String fileName, Document xmlDoc, RandomAccessFile raFile)
    {
        int offset;
        int sectionCount;
        int i;
        offset = 0;
        try
        {
            raFile.seek(0L);
            raFile.readFully(byteData, 0, 54);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        ProgressDialog.updateDisplay("Decompiling Headers");
        fileObject = new FileObject();
        fileObject.setName(fileName);
        fileObject.setItemProperty("id", 5, displayHex(getWord(0), true));
        fileObject.setItemProperty("id", 3, new Integer(0));
        fileObject.setItemProperty("version", 5, displayHex(getWord(2), true));
        fileObject.setItemProperty("version", 3, new Integer(2));
        fileObject.setItemProperty("size", 5, displayHex(getDWord(4), true));
        fileObject.setItemProperty("size", 3, new Integer(4));
        fileObject.setItemProperty("sectionCount", 5, displayHex(getDWord(20), true));
        fileObject.setItemProperty("sectionCount", 3, new Integer(20));
        int length = ((Integer)fileObject.getItem("size").getDecodedData()).intValue();
        fileObject.setLength(length);
        offset += length;
        sectionCount = ((Integer)fileObject.getItem("sectionCount").getDecodedData()).intValue();
		SectionPointerObject pointerObject = null;
	for (i=0;i<sectionCount;++i)
	{
        try {if((long)(offset + 20) > raFile.length())
				break;//return xmlDoc;
			} catch (IOException ioe1) {}
        try
        {
            raFile.seek(offset);
            raFile.readFully(byteData, 0, 20);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        int j = 0;
        do
        {
            if(j >= 15)
                break;
            if(convertByteToHex(byteData[0]).equals(pointers[j]))
            {
                pointerObject = new SectionPointerObject(sections[j], offset);
                break;
            }
            j++;
        } while(true);
        if(pointerObject == null)
            pointerObject = new SectionPointerObject("unknown", offset);
        pointerObject.setItemProperty("id", 5, displayHex(getDWord(0), true));
        pointerObject.setItemProperty("id", 3, new Integer(0));
        pointerObject.setItemProperty("id", 4, new Integer(4));
        pointerObject.setItemProperty("subsectionPointers", 5, displayHex(getDWord(8), true));
        pointerObject.setItemProperty("subsectionPointers", 3, new Integer(8));
        pointerObject.setItemProperty("offset", 5, displayHex(getDWord(12), true));
        pointerObject.setItemProperty("offset", 3, new Integer(12));
        pointerObject.setItemProperty("size", 5, displayHex(getDWord(16), true));
        pointerObject.setItemProperty("size", 3, new Integer(16));
		fileObject.addBaseObject(pointerObject);
        pointerObject.setLength(20);
        offset += 20;
	}

        for(i = 0; i < fileObject.getObjectAL().size(); i++)
        {
            pointerObject = (SectionPointerObject)fileObject.getObjectAL().get(i);
            int tempOffset = ((Integer)pointerObject.getItem("offset").getDecodedData()).intValue();
            int subsectionPointers = ((Integer)pointerObject.getItem("subsectionPointers").getDecodedData()).intValue();
            try
            {
                raFile.seek(tempOffset);
            }
            catch(IOException ioe)
            {
                LogEngine.getInstance().log(ioe);
            }
            for(int j = 0; j < subsectionPointers; j++)
            {
                SectionHeaderObject headerObject = new SectionHeaderObject(tempOffset);
                try
                {
                    raFile.seek(tempOffset);
                    raFile.readFully(byteData, 0, 16);
                }
                catch(IOException ioe)
                {
                    LogEngine.getInstance().log(ioe);
                }
                headerObject.setItemProperty("id", 5, displayHex(getDWord(0), true));
                headerObject.setItemProperty("id", 3, new Integer(0));
                headerObject.setItemProperty("numberRecords", 5, displayHex(getDWord(4), true));
                headerObject.setItemProperty("numberRecords", 3, new Integer(4));
                headerObject.setItemProperty("offset", 5, displayHex(getDWord(8), true));
                headerObject.setItemProperty("offset", 3, new Integer(8));
                headerObject.setItemProperty("subsectionSize", 5, displayHex(getDWord(12), true));
                headerObject.setItemProperty("subsectionSize", 3, new Integer(12));
                pointerObject.addBaseObject(headerObject);
                offset += 16;
                tempOffset += 16;
            }

        }

        ArrayList pointerAL = fileObject.getObjectAL();
        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("airportData"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int airportOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int airportCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseAirports(raFile, airportOffset, airportCount);
            }

        }

        if(airportAL.size() > 0)
        {
            Collections.sort(airportAL, new BaseObjectSort());
            fileObject.getObjectAL().addAll(airportAL);
        }
        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("VOR-ILS-Data"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int vorILSoffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int numberRecords = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseVORILS(raFile, vorILSoffset, numberRecords);
            }

        }

        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("NDBData"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int ndbOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int ndbCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseNDBs(raFile, ndbOffset, ndbCount);
            }

        }

        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("markers"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int markerOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int markerCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseMarkers(raFile, markerOffset, markerCount);
            }

        }

        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("sceneryObjects"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int sceneryOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int sceneryCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseScenery(raFile, sceneryOffset, sceneryCount);
            }

        }

        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("nameList"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int nameListOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int nameListCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseNameLists(raFile, nameListOffset, nameListCount);
            }

        }

        for(i = 0; i < pointerAL.size(); i++)
        {
            if(!(pointerAL.get(i) instanceof SectionPointerObject))
                continue;
            pointerObject = (SectionPointerObject)pointerAL.get(i);
            if(!pointerObject.getName().equals("exclusionRectangle"))
                continue;
            for(int j = 0; j < pointerObject.getObjectAL().size(); j++)
            {
                SectionHeaderObject headerObject = (SectionHeaderObject)pointerObject.getObjectAL().get(j);
                int exclusionOffset = ((Integer)headerObject.getItem("offset").getDecodedData()).intValue();
                int exclusionCount = ((Integer)headerObject.getItem("numberRecords").getDecodedData()).intValue();
                parseExclusion(raFile, exclusionOffset, exclusionCount);
            }

        }

        ProgressDialog.updateDisplay("Creating XML");
        return SaveEngine.getInstance().createXML(fileObject, xmlDoc);
    }

    private void parseVORILS(RandomAccessFile raFile, int vorILSOffset, int vorILSCount)
    {
        int currentOffset = 0;
        int i;

        for( i = 0; i < vorILSCount; ++i )
        {
            ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling VOR/ILS " ).append( i + 1 ).append( " of " ).append( vorILSCount ).toString() );
            currentOffset += SceneryDecompiler.getInstance().decompileVORILSObject( (BaseObject) fileObject, vorILSOffset, currentOffset, raFile );
        }
    }

    private void parseNDBs(RandomAccessFile raFile, int ndbOffset, int ndbCount)
    {
        int currentOffset = 0;
        int i;

        for( i = 0; i < ndbCount; ++i )
        {
            ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling NDB " ).append( i + 1 ).append( " of " ).append( ndbCount ).toString() );
            currentOffset += SceneryDecompiler.getInstance().decompileNDBObject( (BaseObject) fileObject, ndbOffset, currentOffset, raFile );
        }
    }

    private void parseMarkers(RandomAccessFile raFile, int markerOffset, int markerCount)
    {
        int currentOffset = 0;
        int i;

        for( i = 0; i < markerCount; ++i )
        {
            ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling Marker " ).append( i + 1 ).append( " of " ).append( markerCount ).toString() );
            currentOffset += SceneryDecompiler.getInstance().decompileMarkerObject( (BaseObject) fileObject, markerOffset, currentOffset, raFile );
        }
    }

    private void parseScenery(RandomAccessFile raFile, int sceneryOffset, int sceneryCount)
    {
        int currentOffset = 0;
        int i;

        for( i = 0; i < sceneryCount; ++i )
        {
            ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling Scenery " ).append( i + 1 ).append( " of " ).append( sceneryCount ).toString() );
            currentOffset += SceneryDecompiler.getInstance().decompileSceneryObject( (BaseObject) fileObject, sceneryOffset, currentOffset, raFile );
            currentOffset += SceneryDecompiler.getInstance().decompileTriggerObject( (BaseObject) fileObject, sceneryOffset, currentOffset, raFile );
            currentOffset += SceneryDecompiler.getInstance().decompileTaxiwaySignsObject( (BaseObject) fileObject, sceneryOffset, currentOffset, raFile );
            currentOffset += SceneryDecompiler.getInstance().decompileWindsockObject( (BaseObject) fileObject, sceneryOffset, currentOffset, raFile );
        }
    }

    private void parseNameLists(RandomAccessFile raFile, int nameListOffset, int nameListCount)
    {
        ProgressDialog.updateDisplay( "Decompiling Name List" );
        SceneryDecompiler.getInstance().decompileNameList( (BaseObject) fileObject, nameListOffset, 0, raFile );
    }

    private void parseExclusion(RandomAccessFile raFile, int exclusionOffset, int exclusionCount)
    {
        int currentOffset = 0;
        int i;

        for( i = 0; i < exclusionCount; ++i )
        {
            ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling Exclusion " ).append( i + 1 ).append( " of " ).append( exclusionCount ).toString() );
            currentOffset += SceneryDecompiler.getInstance().decompileExclusionRectangle( (BaseObject) fileObject, exclusionOffset, currentOffset, raFile );
        }
    }

    private void parseAirports(RandomAccessFile raFile, int airportOffset, int airportCount)
    {
        int i;

        for( i = 0; i < airportCount; ++i )
        {
            try
            {
                raFile.seek( (long) airportOffset );
                raFile.readFully( byteData, 0, 56 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            if( convertByteToHex( (long) byteData[0] ).equals( "3C" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            {
                String s = getDWord( 2 );
                int length = Long.valueOf( s, 16 ).intValue();
                Object airportObject = new AirportObject( airportOffset, length );

                airportAL.add( airportObject );
                ((AirportObject) airportObject).setItemProperty( "id", 5, "3C 00" );
                ((AirportObject) airportObject).setItemProperty( "size", 5, displayHex( s, true ) );
                ((AirportObject) airportObject).setItemProperty( "size", 3, new Integer( 2 ) );
                ((AirportObject) airportObject).setItemProperty( "runwayCount", 3, new Integer( 6 ) );
                ((AirportObject) airportObject).setItemProperty( "runwayCount", 5, convertByteToHex( (long) byteData[6] ) );
                ((AirportObject) airportObject).setItemProperty( "comCount", 3, new Integer( 7 ) );
                ((AirportObject) airportObject).setItemProperty( "comCount", 5, convertByteToHex( (long) byteData[7] ) );
                ((AirportObject) airportObject).setItemProperty( "startCount", 3, new Integer( 8 ) );
                ((AirportObject) airportObject).setItemProperty( "startCount", 5, convertByteToHex( (long) byteData[8] ) );
                ((AirportObject) airportObject).setItemProperty( "approachCount", 3, new Integer( 9 ) );
                ((AirportObject) airportObject).setItemProperty( "approachCount", 5, convertByteToHex( (long) byteData[9] ) );
                ((AirportObject) airportObject).setItemProperty( "apronCount", 3, new Integer( 10 ) );
                ((AirportObject) airportObject).setItemProperty( "apronCount", 5, convertByteToHex( (long) byteData[10] ) );
                ((AirportObject) airportObject).setItemProperty( "helipadCount", 3, new Integer( 11 ) );
                ((AirportObject) airportObject).setItemProperty( "helipadCount", 5, convertByteToHex( (long) byteData[11] ) );
                ((AirportObject) airportObject).setItemProperty( "deleteFlag", 3, new Integer( 11 ) );
                ((AirportObject) airportObject).setItemProperty( "deleteFlag", 5, convertByteToHex( (long) byteData[11] ) );
                ((AirportObject) airportObject).setItemProperty( "longitude", 5, displayHex( getDWord( 12 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "longitude", 3, new Integer( 12 ) );
                ((AirportObject) airportObject).setItemProperty( "latitude", 5, displayHex( getDWord( 16 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "latitude", 3, new Integer( 16 ) );
                ((AirportObject) airportObject).setItemProperty( "altitude", 5, displayHex( getDWord( 20 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "altitude", 3, new Integer( 20 ) );
                ((AirportObject) airportObject).setItemProperty( "towerLongitude", 5, displayHex( getDWord( 24 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "towerLongitude", 3, new Integer( 24 ) );
                ((AirportObject) airportObject).setItemProperty( "towerLatitude", 5, displayHex( getDWord( 28 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "towerLatitude", 3, new Integer( 28 ) );
                ((AirportObject) airportObject).setItemProperty( "towerAltitude", 5, displayHex( getDWord( 32 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "towerAltitude", 3, new Integer( 32 ) );
                ((AirportObject) airportObject).setItemProperty( "magVar", 5, displayHex( getDWord( 36 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "magVar", 3, new Integer( 36 ) );
                ((AirportObject) airportObject).setItemProperty( "icao", 5, displayHex( getDWord( 40 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "icao", 3, new Integer( 40 ) );
                ((AirportObject) airportObject).setItemProperty( "unknown", 5, displayHex( getDWord( 44 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "unknown", 3, new Integer( 44 ) );
                ((AirportObject) airportObject).setItemProperty( "services", 5, displayHex( getDWord( 48 ), true ) );
                ((AirportObject) airportObject).setItemProperty( "services", 3, new Integer( 48 ) );
                ((AirportObject) airportObject).setItemProperty( "trafficScalar", 5, convertByteToHex( (long) byteData[53] ) );
                ((AirportObject) airportObject).setItemProperty( "trafficScalar", 3, new Integer( 53 ) );
                ProgressDialog.updateDisplay( new StringBuilder().append( "Decompiling Airport (" ).append( ((AirportObject) airportObject).getItem( "icao" ).getDecodedData() ).append( ")" ).toString() );
                if( airportICAO == null || ((AirportObject) airportObject).getItem( "icao" ).getDecodedData().equals( airportICAO ) )
                {
                    int currentOffset = 56;
                    int runwayCount;
                    int j;

                    decompileServicesObject( (BaseObject) airportObject, airportOffset, 48, raFile );
                    currentOffset += decompileDeletes( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += SceneryDecompiler.getInstance().decompileNameObject( (BaseObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileTowerScenery( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    runwayCount = ((Integer) ((AirportObject) airportObject).getItem( "runwayCount" ).getDecodedData()).intValue();
                    for( j = 0; j < runwayCount; ++j )
                        currentOffset += decompileRunway( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    j = ((Integer) ((AirportObject) airportObject).getItem( "helipadCount" ).getDecodedData()).intValue();
                    for( j = 0; j < j; ++j )
                        currentOffset += decompileHelipad( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    j = ((Integer) ((AirportObject) airportObject).getItem( "startCount" ).getDecodedData()).intValue();
                    for( j = 0; j < j; ++j )
                        currentOffset += decompileStart( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    j = ((Integer) ((AirportObject) airportObject).getItem( "comCount" ).getDecodedData()).intValue();
                    for( j = 0; j < j; ++j )
                        currentOffset += decompileCom( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += TWNetworkDecompiler.getInstance().decompileTaxiwayPoints( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += TWNetworkDecompiler.getInstance().decompileTaxiwayParkings( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += TWNetworkDecompiler.getInstance().decompileTaxiwayPaths( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += TWNetworkDecompiler.getInstance().decompileTaxiNames( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    j = ((Integer) ((AirportObject) airportObject).getItem( "apronCount" ).getDecodedData()).intValue();
                    for( j = 0; j < j; ++j )
                        currentOffset += decompileApron( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileUnknownRecords( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileBoundaryFences( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileBlastFences( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileJetways( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileApproaches( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                    currentOffset += decompileApronEdgeLights( (AirportObject) airportObject, airportOffset, currentOffset, raFile );
                }
                airportOffset += length;
            }
        }
    }

    private int decompileServicesObject(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        ServicesObject servicesobject1;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 4 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        servicesobject1 = new ServicesObject( i, 4 );
        baseObject.addBaseObject( (BaseObject) servicesobject1 );
        servicesobject1.setItemProperty( "type73", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "type73", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "type87", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "type87", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "type100", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "type100", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "type130", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "type130", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "type145", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "type145", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeMOGAS", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeMOGAS", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJET", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJET", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJETA", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJETA", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJETA1", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJETA1", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJETAP", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJETAP", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJETB", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJETB", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJET4", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJET4", 3, new Integer( 0 ) );
        servicesobject1.setItemProperty( "typeJET5", 5, displayHex( getDWord( 0 ), true ) );
        servicesobject1.setItemProperty( "typeJET5", 3, new Integer( 0 ) );
        return 4;
    }

    private int decompileDeletes(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int j;
        int length;
        Object deleteObject;
        int deleteOffset;
        int runwayCount;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 12 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "33" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        deleteObject = new DeleteObject( i, length );
        airportObject.addBaseObject( (BaseObject) deleteObject );
        ((DeleteObject) deleteObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((DeleteObject) deleteObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allApproaches", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allApproaches", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allApronLights", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allApronLights", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allAprons", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allAprons", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allFrequencies", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allFrequencies", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allHelipads", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allHelipads", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allRunways", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allRunways", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allStarts", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allStarts", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allTaxiways", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allTaxiways", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allBlastFences", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allBlastFences", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allBoundaryFences", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allBoundaryFences", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allJetways", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allJetways", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "allControlTowers", 5, displayHex( getWord( 6 ), true ) );
        ((DeleteObject) deleteObject).setItemProperty( "allControlTowers", 3, new Integer( 6 ) );
        ((DeleteObject) deleteObject).setItemProperty( "runwayCount", 5, convertByteToHex( (long) byteData[8] ) );
        ((DeleteObject) deleteObject).setItemProperty( "runwayCount", 3, new Integer( 8 ) );
        ((DeleteObject) deleteObject).setItemProperty( "startCount", 5, convertByteToHex( (long) byteData[9] ) );
        ((DeleteObject) deleteObject).setItemProperty( "startCount", 3, new Integer( 9 ) );
        ((DeleteObject) deleteObject).setItemProperty( "frequencyCount", 5, convertByteToHex( (long) byteData[10] ) );
        ((DeleteObject) deleteObject).setItemProperty( "frequencyCount", 3, new Integer( 10 ) );
        deleteOffset = i + 12;
        runwayCount = ((Integer) ((DeleteObject) deleteObject).getItem( "runwayCount" ).getDecodedData()).intValue();
        for( j = 0; j < runwayCount; ++j )
        {
            DeleteRunwayObject deleterunwayobject1;

            try
            {
                raFile.seek( (long) deleteOffset );
                raFile.readFully( byteData, 0, 4 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            deleterunwayobject1 = new DeleteRunwayObject( deleteOffset, 4 );
            ((DeleteObject) deleteObject).addBaseObject( (BaseObject) deleterunwayobject1 );
            deleterunwayobject1.setItemProperty( "surface", 5, convertByteToHex( (long) byteData[0] ) );
            deleterunwayobject1.setItemProperty( "surface", 3, new Integer( 0 ) );
            deleterunwayobject1.setItemProperty( "primaryRunway", 5, convertByteToHex( (long) byteData[1] ) );
            deleterunwayobject1.setItemProperty( "primaryRunway", 3, new Integer( 1 ) );
            deleterunwayobject1.setItemProperty( "secondaryRunway", 5, convertByteToHex( (long) byteData[2] ) );
            deleterunwayobject1.setItemProperty( "secondaryRunway", 3, new Integer( 2 ) );
            deleterunwayobject1.setItemProperty( "primaryDesignator", 5, convertByteToHex( (long) byteData[3] ) );
            deleterunwayobject1.setItemProperty( "primaryDesignator", 3, new Integer( 3 ) );
            deleterunwayobject1.setItemProperty( "secondaryDesignator", 5, convertByteToHex( (long) byteData[4] ) );
            deleterunwayobject1.setItemProperty( "secondaryDesignator", 3, new Integer( 3 ) );
            deleteOffset += 4;
        }
        j = ((Integer) ((DeleteObject) deleteObject).getItem( "startCount" ).getDecodedData()).intValue();
        for( i = 0; i < j; ++i )
        {
            DeleteStartObject deletestartobject1;

            try
            {
                raFile.seek( (long) deleteOffset );
                raFile.readFully( byteData, 0, 4 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            deletestartobject1 = new DeleteStartObject( deleteOffset, 4 );
            ((DeleteObject) deleteObject).addBaseObject( (BaseObject) deletestartobject1 );
            deletestartobject1.setItemProperty( "runwayNumber", 5, convertByteToHex( (long) byteData[0] ) );
            deletestartobject1.setItemProperty( "runwayNumber", 3, new Integer( 0 ) );
            deletestartobject1.setItemProperty( "runwayDesignator", 5, convertByteToHex( (long) byteData[1] ) );
            deletestartobject1.setItemProperty( "runwayDesignator", 3, new Integer( 1 ) );
            deletestartobject1.setItemProperty( "startType", 5, convertByteToHex( (long) byteData[2] ) );
            deletestartobject1.setItemProperty( "startType", 3, new Integer( 2 ) );
            deleteOffset += 4;
        }
        i = ((Integer) ((DeleteObject) deleteObject).getItem( "frequencyCount" ).getDecodedData()).intValue();
        for( j = 0; j < i; ++j )
        {
            DeleteFrequencyObject deletefrequencyobject1;

            try
            {
                raFile.seek( (long) deleteOffset );
                raFile.readFully( byteData, 0, 4 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            deletefrequencyobject1 = new DeleteFrequencyObject( deleteOffset, 4 );
            ((DeleteObject) deleteObject).addBaseObject( (BaseObject) deletefrequencyobject1 );
            deletefrequencyobject1.setItemProperty( "type", 5, displayHex( getDWord( 0 ), true ) );
            deletefrequencyobject1.setItemProperty( "type", 3, new Integer( 0 ) );
            deletefrequencyobject1.setItemProperty( "frequency", 5, displayHex( getDWord( 0 ), true ) );
            deletefrequencyobject1.setItemProperty( "frequency", 3, new Integer( 0 ) );
            deleteOffset += 4;
        }
        return length;
    }

    private int decompileTowerScenery(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 10 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "66" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object towerSceneryObject = new TowerSceneryObject( i, length );

            airportObject.addBaseObject( (BaseObject) towerSceneryObject );
            ((TowerSceneryObject) towerSceneryObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((TowerSceneryObject) towerSceneryObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((TowerSceneryObject) towerSceneryObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((TowerSceneryObject) towerSceneryObject).setItemProperty( "scenerySize", 5, displayHex( getDWord( 6 ), true ) );
            ((TowerSceneryObject) towerSceneryObject).setItemProperty( "scenerySize", 3, new Integer( 6 ) );
            SceneryDecompiler.getInstance().decompileSceneryObject( (BaseObject) towerSceneryObject, airportOffset, currentOffset + 10 + totalLength, raFile );
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 10 );
                continue;
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileRunway(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object runwayObject;
        StringBuffer buffer;
        int detailOffset;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 52 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "04" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        runwayObject = new RunwayObject( i, length );
        airportObject.addBaseObject( (BaseObject) runwayObject );
        ((RunwayObject) runwayObject).setItemProperty( "id", 5, "04 00" );
        ((RunwayObject) runwayObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((RunwayObject) runwayObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((RunwayObject) runwayObject).setItemProperty( "surface", 5, displayHex( getWord( 6 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "surface", 3, new Integer( 6 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryRunwayNumber", 5, convertByteToHex( (long) byteData[8] ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryRunwayNumber", 3, new Integer( 8 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryRunwayDesig", 5, convertByteToHex( (long) byteData[9] ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryRunwayDesig", 3, new Integer( 9 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryRunwayNumber", 5, convertByteToHex( (long) byteData[10] ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryRunwayNumber", 3, new Integer( 10 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryRunwayDesig", 5, convertByteToHex( (long) byteData[11] ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryRunwayDesig", 3, new Integer( 11 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryILSIcao", 5, displayHex( getDWord( 12 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryILSIcao", 3, new Integer( 12 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryILSIcao", 5, displayHex( getDWord( 16 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryILSIcao", 3, new Integer( 16 ) );
        ((RunwayObject) runwayObject).setItemProperty( "longitude", 5, displayHex( getDWord( 20 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "longitude", 3, new Integer( 20 ) );
        ((RunwayObject) runwayObject).setItemProperty( "latitude", 5, displayHex( getDWord( 24 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "latitude", 3, new Integer( 24 ) );
        ((RunwayObject) runwayObject).setItemProperty( "altitude", 5, displayHex( getDWord( 28 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "altitude", 3, new Integer( 28 ) );
        ((RunwayObject) runwayObject).setItemProperty( "length", 5, displayHex( getDWord( 32 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "length", 3, new Integer( 32 ) );
        ((RunwayObject) runwayObject).setItemProperty( "width", 5, displayHex( getDWord( 36 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "width", 3, new Integer( 36 ) );
        ((RunwayObject) runwayObject).setItemProperty( "heading", 5, displayHex( getDWord( 40 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "heading", 3, new Integer( 40 ) );
        ((RunwayObject) runwayObject).setItemProperty( "patternAltitude", 5, displayHex( getDWord( 44 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "patternAltitude", 3, new Integer( 44 ) );
        ((RunwayObject) runwayObject).setItemProperty( "edges", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "edges", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "threshold", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "threshold", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "fixedDistance", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "fixedDistance", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "touchdown", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "touchdown", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "dashes", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "dashes", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "identDisplayed", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "identDisplayed", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "precision", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "precision", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "edgePavement", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "edgePavement", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "singleEnd", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "singleEnd", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryClosed", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryClosed", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryClosed", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryClosed", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryStol", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryStol", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryStol", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryStol", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "altThreshold", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "altThreshold", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "altFixedDistance", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "altFixedDistance", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "altTouchdown", 5, displayHex( getWord( 48 ), true ) );
        ((RunwayObject) runwayObject).setItemProperty( "altTouchdown", 3, new Integer( 48 ) );
        ((RunwayObject) runwayObject).setItemProperty( "edgeLights", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "edgeLights", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "centerLights", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "centerLights", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "centerRed", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "centerRed", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "altPrecision", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "altPrecision", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "leadingZeroIdent", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "leadingZeroIdent", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "noThesholdEndArrows", 5, convertByteToHex( (long) byteData[50] ) );
        ((RunwayObject) runwayObject).setItemProperty( "noThesholdEndArrows", 3, new Integer( 50 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryTakeoff", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryTakeoff", 3, new Integer( 51 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryLanding", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryLanding", 3, new Integer( 51 ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryPattern", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "primaryPattern", 3, new Integer( 51 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryTakeoff", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryTakeoff", 3, new Integer( 51 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryLanding", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryLanding", 3, new Integer( 51 ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryPattern", 5, convertByteToHex( (long) byteData[51] ) );
        ((RunwayObject) runwayObject).setItemProperty( "secondaryPattern", 3, new Integer( 51 ) );
        buffer = new StringBuffer( "Runway" );
        buffer.append( " " ).append( (String) ((RunwayObject) runwayObject).getItem( "primaryRunwayNumber" ).getDecodedData() );
        if( !((String) ((RunwayObject) runwayObject).getItem( "primaryRunwayDesig" ).getDecodedData()).equals( "NONE" ) )
            buffer.append( " " ).append( (String) ((RunwayObject) runwayObject).getItem( "primaryRunwayDesig" ).getDecodedData() );
        ((RunwayObject) runwayObject).setName( buffer.toString() );
        detailOffset = 52;
        while( detailOffset < length )
        {
            try
            {
                raFile.seek( (long) (i + detailOffset) );
                raFile.readFully( byteData, 0, 2 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            switch( Long.valueOf( convertByteToHex( (long) byteData[0] ), 16 ).intValue() )
            {
                case 5:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "05", "00", "primaryOffset", raFile );
                    continue;
                case 6:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "06", "00", "secondaryOffset", raFile );
                    continue;
                case 7:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "07", "00", "primaryBlastPad", raFile );
                    continue;
                case 8:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "08", "00", "secondaryBlastPad", raFile );
                    continue;
                case 9:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "09", "00", "primaryOverrun", raFile );
                    continue;
                case 10:
                    detailOffset += decompileRunwayDetail( (RunwayObject) runwayObject, i, detailOffset, "0A", "00", "secondaryOverrun", raFile );
                    continue;
                case 11:
                    detailOffset += decompileRunwayVasi( (RunwayObject) runwayObject, i, detailOffset, "0B", "00", "primaryLeftVasi", raFile );
                    continue;
                case 12:
                    detailOffset += decompileRunwayVasi( (RunwayObject) runwayObject, i, detailOffset, "0C", "00", "primaryRightVasi", raFile );
                    continue;
                case 13:
                    detailOffset += decompileRunwayVasi( (RunwayObject) runwayObject, i, detailOffset, "0D", "00", "secondaryLeftVasi", raFile );
                    continue;
                case 14:
                    detailOffset += decompileRunwayVasi( (RunwayObject) runwayObject, i, detailOffset, "0E", "00", "secondaryRightVasi", raFile );
                    continue;
                case 15:
                    detailOffset += decompileApproachLight( (RunwayObject) runwayObject, i, detailOffset, "0F", "00", "primaryApproachLights", raFile );
                    continue;
                case 16:
                    detailOffset += decompileApproachLight( (RunwayObject) runwayObject, i, detailOffset, "10", "00", "secondaryApproachLights", raFile );
                    continue;
                default:
                    detailOffset += decompileUnknownRunwayRecords( (RunwayObject) runwayObject, i, detailOffset, raFile );
                    continue;
            }
        }
        return length;
    }

    private int decompileRunwayDetail(RunwayObject runwayObject, int runwayOffset, int currentOffset, String id1, String id2, String name, RandomAccessFile raFile)
    {
        int i = runwayOffset + currentOffset;
        int length;
        Object detailObject;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 16 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( id1 ) || !convertByteToHex( (long) byteData[1] ).equals( id2 ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        detailObject = new RunwayDetailObject( name, i, length );
        runwayObject.addBaseObject( (BaseObject) detailObject );
        ((RunwayDetailObject) detailObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "surface", 5, displayHex( getWord( 6 ), true ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "surface", 3, new Integer( 6 ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "length", 5, displayHex( getDWord( 8 ), true ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "length", 3, new Integer( 8 ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "width", 5, displayHex( getDWord( 12 ), true ) );
        ((RunwayDetailObject) detailObject).setItemProperty( "width", 3, new Integer( 12 ) );
        return length;
    }

    private int decompileRunwayVasi(RunwayObject runwayObject, int runwayOffset, int currentOffset, String id1, String id2, String name, RandomAccessFile raFile)
    {
        int i = runwayOffset + currentOffset;
        int length;
        Object detailObject;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 24 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( id1 ) || !convertByteToHex( (long) byteData[1] ).equals( id2 ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        detailObject = new RunwayVasiObject( name, i, length );
        runwayObject.addBaseObject( (BaseObject) detailObject );
        ((RunwayVasiObject) detailObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "type", 5, displayHex( getWord( 6 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "type", 3, new Integer( 6 ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "biasX", 5, displayHex( getDWord( 8 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "biasX", 3, new Integer( 8 ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "biasZ", 5, displayHex( getDWord( 12 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "biasZ", 3, new Integer( 12 ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "spacing", 5, displayHex( getDWord( 16 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "spacing", 3, new Integer( 16 ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "pitch", 5, displayHex( getDWord( 20 ), true ) );
        ((RunwayVasiObject) detailObject).setItemProperty( "pitch", 3, new Integer( 20 ) );
        return length;
    }

    private int decompileUnknownRunwayRecords(RunwayObject runwayObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;
        int length;
        Object unknownObject;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 6 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        unknownObject = new UnknownObject( i, length );
        runwayObject.addBaseObject( (BaseObject) unknownObject );
        ((UnknownObject) unknownObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((UnknownObject) unknownObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((UnknownObject) unknownObject).setItemProperty( "size", 3, new Integer( 2 ) );
        totalLength += length;
        i += length;
        return totalLength;
    }

    private int decompileApproachLight(RunwayObject runwayObject, int runwayOffset, int currentOffset, String id1, String id2, String name, RandomAccessFile raFile)
    {
        int i = runwayOffset + currentOffset;
        int length;
        Object detailObject;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 8 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( id1 ) || !convertByteToHex( (long) byteData[1] ).equals( id2 ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        detailObject = new ApproachLightObject( name, i, length );
        runwayObject.addBaseObject( (BaseObject) detailObject );
        ((ApproachLightObject) detailObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((ApproachLightObject) detailObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((ApproachLightObject) detailObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((ApproachLightObject) detailObject).setItemProperty( "approachSystem", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApproachLightObject) detailObject).setItemProperty( "approachSystem", 3, new Integer( 6 ) );
        ((ApproachLightObject) detailObject).setItemProperty( "endLights", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApproachLightObject) detailObject).setItemProperty( "endLights", 3, new Integer( 6 ) );
        ((ApproachLightObject) detailObject).setItemProperty( "reil", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApproachLightObject) detailObject).setItemProperty( "reil", 3, new Integer( 6 ) );
        ((ApproachLightObject) detailObject).setItemProperty( "touchdown", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApproachLightObject) detailObject).setItemProperty( "touchdown", 3, new Integer( 6 ) );
        ((ApproachLightObject) detailObject).setItemProperty( "strobes", 5, convertByteToHex( (long) byteData[7] ) );
        ((ApproachLightObject) detailObject).setItemProperty( "strobes", 3, new Integer( 7 ) );
        return length;
    }

    private int decompileHelipad(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object helipadObject;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 36 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "26" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        helipadObject = new HelipadObject( i, length );
        airportObject.addBaseObject( (BaseObject) helipadObject );
        ((HelipadObject) helipadObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((HelipadObject) helipadObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((HelipadObject) helipadObject).setItemProperty( "surface", 5, convertByteToHex( (long) byteData[6] ) );
        ((HelipadObject) helipadObject).setItemProperty( "surface", 3, new Integer( 6 ) );
        ((HelipadObject) helipadObject).setItemProperty( "helipadType", 5, convertByteToHex( (long) byteData[7] ) );
        ((HelipadObject) helipadObject).setItemProperty( "helipadType", 3, new Integer( 7 ) );
        ((HelipadObject) helipadObject).setItemProperty( "transparent", 5, convertByteToHex( (long) byteData[7] ) );
        ((HelipadObject) helipadObject).setItemProperty( "transparent", 3, new Integer( 7 ) );
        ((HelipadObject) helipadObject).setItemProperty( "closed", 5, convertByteToHex( (long) byteData[7] ) );
        ((HelipadObject) helipadObject).setItemProperty( "closed", 3, new Integer( 7 ) );
        ((HelipadObject) helipadObject).setItemProperty( "longitude", 5, displayHex( getDWord( 12 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "longitude", 3, new Integer( 12 ) );
        ((HelipadObject) helipadObject).setItemProperty( "latitude", 5, displayHex( getDWord( 16 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "latitude", 3, new Integer( 16 ) );
        ((HelipadObject) helipadObject).setItemProperty( "altitude", 5, displayHex( getDWord( 20 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "altitude", 3, new Integer( 20 ) );
        ((HelipadObject) helipadObject).setItemProperty( "length", 5, displayHex( getDWord( 24 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "length", 3, new Integer( 24 ) );
        ((HelipadObject) helipadObject).setItemProperty( "width", 5, displayHex( getDWord( 28 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "width", 3, new Integer( 28 ) );
        ((HelipadObject) helipadObject).setItemProperty( "heading", 5, displayHex( getDWord( 32 ), true ) );
        ((HelipadObject) helipadObject).setItemProperty( "heading", 3, new Integer( 32 ) );
        return length;
    }

    private int decompileStart(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object startObject;
        StringBuffer buffer;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 24 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "11" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        startObject = new StartObject( i, length );
        airportObject.addBaseObject( (BaseObject) startObject );
        ((StartObject) startObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((StartObject) startObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((StartObject) startObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((StartObject) startObject).setItemProperty( "runwayNumber", 5, convertByteToHex( (long) byteData[6] ) );
        ((StartObject) startObject).setItemProperty( "runwayNumber", 3, new Integer( 6 ) );
        ((StartObject) startObject).setItemProperty( "runwayDesignator", 5, convertByteToHex( (long) byteData[7] ) );
        ((StartObject) startObject).setItemProperty( "runwayDesignator", 3, new Integer( 7 ) );
        ((StartObject) startObject).setItemProperty( "startType", 5, convertByteToHex( (long) byteData[7] ) );
        ((StartObject) startObject).setItemProperty( "startType", 3, new Integer( 7 ) );
        ((StartObject) startObject).setItemProperty( "longitude", 5, displayHex( getDWord( 8 ), true ) );
        ((StartObject) startObject).setItemProperty( "longitude", 3, new Integer( 8 ) );
        ((StartObject) startObject).setItemProperty( "latitude", 5, displayHex( getDWord( 12 ), true ) );
        ((StartObject) startObject).setItemProperty( "latitude", 3, new Integer( 12 ) );
        ((StartObject) startObject).setItemProperty( "altitude", 5, displayHex( getDWord( 16 ), true ) );
        ((StartObject) startObject).setItemProperty( "altitude", 3, new Integer( 16 ) );
        ((StartObject) startObject).setItemProperty( "heading", 5, displayHex( getDWord( 20 ), true ) );
        ((StartObject) startObject).setItemProperty( "heading", 3, new Integer( 20 ) );
        buffer = new StringBuffer( "Start Location" );
        buffer.append( " " ).append( ((String) ((StartObject) startObject).getItem( "startType" ).getDecodedData()).substring( 0, 1 ) );
        buffer.append( (String) ((StartObject) startObject).getItem( "runwayNumber" ).getDecodedData() );
        if( !((String) ((StartObject) startObject).getItem( "runwayDesignator" ).getDecodedData()).equals( "NONE" ) )
            buffer.append( " " ).append( (String) ((StartObject) startObject).getItem( "runwayDesignator" ).getDecodedData() );
        ((StartObject) startObject).setName( buffer.toString() );
        return length;
    }

    private int decompileCom(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object comObject;
        StringBuffer buffer;
        int j;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 100 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "12" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        comObject = new ComObject( i, length );
        airportObject.addBaseObject( (BaseObject) comObject );
        ((ComObject) comObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((ComObject) comObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((ComObject) comObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((ComObject) comObject).setItemProperty( "comType", 5, displayHex( getWord( 6 ), true ) );
        ((ComObject) comObject).setItemProperty( "comType", 3, new Integer( 6 ) );
        ((ComObject) comObject).setItemProperty( "comFrequency", 5, displayHex( getDWord( 8 ), true ) );
        ((ComObject) comObject).setItemProperty( "comFrequency", 3, new Integer( 8 ) );
        buffer = new StringBuffer();
        for( j = 12; j < length; ++j )
        {
            buffer.append( convertByteToHex( (long) byteData[j] ) );
            if( j < length - 1 )
                buffer.append( " " );
        }
        ((ComObject) comObject).setItemProperty( "comName", 5, buffer.toString() );
        ((ComObject) comObject).setItemProperty( "comName", 3, new Integer( 12 ) );
        ((ComObject) comObject).setItemProperty( "comName", 4, new Integer( length - 12 ) );
        buffer = new StringBuffer( "Com" );
        buffer.append( " " ).append( (String) ((ComObject) comObject).getItem( "comType" ).getDecodedData() );
        buffer.append( " " ).append( ((ComObject) comObject).getItem( "comFrequency" ).getDecodedData().toString() );
        ((ComObject) comObject).setName( buffer.toString() );
        return length;
    }

    private int decompileApron(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object apronObject;
        int vertexCount;
        int j;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 12 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "37" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        apronObject = new ApronObject( i, length );
        airportObject.addBaseObject( (BaseObject) apronObject );
        ((ApronObject) apronObject).setItemProperty( "id1", 5, displayHex( getWord( 0 ), true ) );
        ((ApronObject) apronObject).setItemProperty( "size1", 5, displayHex( s, true ) );
        ((ApronObject) apronObject).setItemProperty( "size1", 3, new Integer( 2 ) );
        ((ApronObject) apronObject).setItemProperty( "surface1", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApronObject) apronObject).setItemProperty( "surface1", 3, new Integer( 6 ) );
        ((ApronObject) apronObject).setItemProperty( "vertexCount1", 5, displayHex( getWord( 7 ), true ) );
        ((ApronObject) apronObject).setItemProperty( "vertexCount1", 3, new Integer( 7 ) );
        vertexCount = ((Integer) ((ApronObject) apronObject).getItem( "vertexCount1" ).getDecodedData()).intValue();
        for( j = 0; j < vertexCount; ++j )
        {
            Object vertexObject = new VertexObject( i + 9 + j * 8 );

            ((ApronObject) apronObject).addBaseObject( (BaseObject) vertexObject );
            try
            {
                raFile.seek( (long) (i + 9 + j * 8) );
                raFile.readFully( byteData, 0, 8 );
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
            }
            ((VertexObject) vertexObject).setItemProperty( "longitude", 5, displayHex( getDWord( 0 ), true ) );
            ((VertexObject) vertexObject).setItemProperty( "longitude", 3, new Integer( 0 ) );
            ((VertexObject) vertexObject).setItemProperty( "latitude", 5, displayHex( getDWord( 4 ), true ) );
            ((VertexObject) vertexObject).setItemProperty( "latitude", 3, new Integer( 4 ) );
        }
        j = i + 12 + vertexCount * 8;
        try
        {
            raFile.seek( (long) j );
            raFile.readFully( byteData, 0, 10 );
        }
        catch( IOException ioexception1 )
        {
            LogEngine.getInstance().log( ioexception1 );
        }
        ((ApronObject) apronObject).setItemProperty( "id2", 5, displayHex( getWord( 0 ), true ) );
        ((ApronObject) apronObject).setItemProperty( "id2", 3, new Integer( j - i ) );
        ((ApronObject) apronObject).setItemProperty( "size2", 5, displayHex( getDWord( 2 ), true ) );
        ((ApronObject) apronObject).setItemProperty( "size2", 3, new Integer( j - i + 2 ) );
        ((ApronObject) apronObject).setItemProperty( "surface2", 5, convertByteToHex( (long) byteData[6] ) );
        ((ApronObject) apronObject).setItemProperty( "surface2", 3, new Integer( j - i + 6 ) );
        ((ApronObject) apronObject).setItemProperty( "drawSurface", 5, convertByteToHex( (long) byteData[7] ) );
        ((ApronObject) apronObject).setItemProperty( "drawSurface", 3, new Integer( j - i + 7 ) );
        ((ApronObject) apronObject).setItemProperty( "drawDetail", 5, convertByteToHex( (long) byteData[7] ) );
        ((ApronObject) apronObject).setItemProperty( "drawDetail", 3, new Integer( j - i + 7 ) );
        ((ApronObject) apronObject).setItemProperty( "vertexCount2", 5, convertByteToHex( (long) byteData[8] ) );
        ((ApronObject) apronObject).setItemProperty( "vertexCount2", 3, new Integer( j - i + 8 ) );
        ((ApronObject) apronObject).setItemProperty( "triangleCount", 5, convertByteToHex( (long) byteData[10] ) );
        ((ApronObject) apronObject).setItemProperty( "triangleCount", 3, new Integer( j - i + 10 ) );
        length += ((Integer) ((ApronObject) apronObject).getItem( "size2" ).getDecodedData()).intValue();
        ((ApronObject) apronObject).setLength( length );
        return length;
    }

    private int decompileUnknownRecords(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 6 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "3B" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object unknownObject = new UnknownObject( i, length );

            airportObject.addBaseObject( (BaseObject) unknownObject );
            ((UnknownObject) unknownObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((UnknownObject) unknownObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((UnknownObject) unknownObject).setItemProperty( "size", 3, new Integer( 2 ) );
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 6 );
                continue;
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileBoundaryFences(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 40 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "39" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object fenceObject = new BoundaryFenceObject( i, length );
            int vertexCount;
            int j;

            airportObject.addBaseObject( (BaseObject) fenceObject );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "vertexCount", 5, displayHex( getWord( 6 ), true ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "vertexCount", 3, new Integer( 6 ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "instanceId", 5, displayHex( getGUID( 8 ), true ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "instanceId", 3, new Integer( 8 ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "profile", 5, displayHex( getGUID( 24 ), true ) );
            ((BoundaryFenceObject) fenceObject).setItemProperty( "profile", 3, new Integer( 24 ) );
            vertexCount = ((Integer) ((BoundaryFenceObject) fenceObject).getItem( "vertexCount" ).getDecodedData()).intValue();
            for( j = 0; j < vertexCount; ++j )
            {
                Object vertexObject = new VertexObject( i + 40 + j * 8 );

                ((BoundaryFenceObject) fenceObject).addBaseObject( (BaseObject) vertexObject );
                try
                {
                    raFile.seek( (long) (i + 40 + j * 8) );
                    raFile.readFully( byteData, 0, 8 );
                }
                catch( IOException ioe )
                {
                    LogEngine.getInstance().log( ioe );
                }
                ((VertexObject) vertexObject).setItemProperty( "longitude", 5, displayHex( getDWord( 0 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "longitude", 3, new Integer( 0 ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 5, displayHex( getDWord( 4 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 3, new Integer( 4 ) );
            }
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 40 );
                continue;
            }
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileBlastFences(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 40 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "38" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object fenceObject = new BlastFenceObject( i, length );
            int vertexCount;
            int j;

            airportObject.addBaseObject( (BaseObject) fenceObject );
            ((BlastFenceObject) fenceObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "vertexCount", 5, displayHex( getWord( 6 ), true ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "vertexCount", 3, new Integer( 6 ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "instanceId", 5, displayHex( getGUID( 8 ), true ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "instanceId", 3, new Integer( 8 ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "profile", 5, displayHex( getGUID( 24 ), true ) );
            ((BlastFenceObject) fenceObject).setItemProperty( "profile", 3, new Integer( 24 ) );
            vertexCount = ((Integer) ((BlastFenceObject) fenceObject).getItem( "vertexCount" ).getDecodedData()).intValue();
            for( j = 0; j < vertexCount; ++j )
            {
                Object vertexObject = new VertexObject( i + 40 + j * 8 );

                ((BlastFenceObject) fenceObject).addBaseObject( (BaseObject) vertexObject );
                try
                {
                    raFile.seek( (long) (i + 40 + j * 8) );
                    raFile.readFully( byteData, 0, 8 );
                }
                catch( IOException ioe )
                {
                    LogEngine.getInstance().log( ioe );
                }
                ((VertexObject) vertexObject).setItemProperty( "longitude", 5, displayHex( getDWord( 0 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "longitude", 3, new Integer( 0 ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 5, displayHex( getDWord( 4 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 3, new Integer( 4 ) );
            }
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 40 );
                continue;
            }
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileJetways(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 10 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "3A" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object jetwayObject = new JetwayObject( i, length );

            airportObject.addBaseObject( (BaseObject) jetwayObject );
            ((JetwayObject) jetwayObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((JetwayObject) jetwayObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((JetwayObject) jetwayObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((JetwayObject) jetwayObject).setItemProperty( "gateNumber", 5, displayHex( getWord( 6 ), true ) );
            ((JetwayObject) jetwayObject).setItemProperty( "gateNumber", 3, new Integer( 6 ) );
            ((JetwayObject) jetwayObject).setItemProperty( "gateName", 5, displayHex( getWord( 8 ), true ) );
            ((JetwayObject) jetwayObject).setItemProperty( "gateName", 3, new Integer( 8 ) );
            ((JetwayObject) jetwayObject).setName( new StringBuilder().append( "Jetway " ).append( ((JetwayObject) jetwayObject).getItem( "gateName" ).getDecodedData() ).append( " " ).append( ((JetwayObject) jetwayObject).getItem( "gateNumber" ).getDecodedData() ).toString() );
            SceneryDecompiler.getInstance().decompileSceneryObject( (BaseObject) jetwayObject, airportOffset, currentOffset + totalLength + 14, raFile );
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 10 );
                continue;
            }
            catch( IOException ioe )
            {
                LogEngine.getInstance().log( ioe );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileApproaches(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 32 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "24" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object approachObject = new ApproachObject( i, length );
            int legOffset;
            int transitionCount;
            int j;

            airportObject.addBaseObject( (BaseObject) approachObject );
            ((ApproachObject) approachObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((ApproachObject) approachObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((ApproachObject) approachObject).setItemProperty( "suffix", 5, convertByteToHex( (long) byteData[6] ) );
            ((ApproachObject) approachObject).setItemProperty( "suffix", 3, new Integer( 6 ) );
            ((ApproachObject) approachObject).setItemProperty( "runwayNumber", 5, convertByteToHex( (long) byteData[7] ) );
            ((ApproachObject) approachObject).setItemProperty( "runwayNumber", 3, new Integer( 7 ) );
            ((ApproachObject) approachObject).setItemProperty( "type", 5, convertByteToHex( (long) byteData[8] ) );
            ((ApproachObject) approachObject).setItemProperty( "type", 3, new Integer( 8 ) );
            ((ApproachObject) approachObject).setItemProperty( "runwayDesignator", 5, convertByteToHex( (long) byteData[8] ) );
            ((ApproachObject) approachObject).setItemProperty( "runwayDesignator", 3, new Integer( 8 ) );
            ((ApproachObject) approachObject).setItemProperty( "gpsOverlay", 5, convertByteToHex( (long) byteData[8] ) );
            ((ApproachObject) approachObject).setItemProperty( "gpsOverlay", 3, new Integer( 8 ) );
            ((ApproachObject) approachObject).setItemProperty( "transitionCount", 5, convertByteToHex( (long) byteData[9] ) );
            ((ApproachObject) approachObject).setItemProperty( "transitionCount", 3, new Integer( 9 ) );
            ((ApproachObject) approachObject).setItemProperty( "approachLegCount", 5, convertByteToHex( (long) byteData[10] ) );
            ((ApproachObject) approachObject).setItemProperty( "approachLegCount", 3, new Integer( 10 ) );
            ((ApproachObject) approachObject).setItemProperty( "missedApproachLegCount", 5, convertByteToHex( (long) byteData[11] ) );
            ((ApproachObject) approachObject).setItemProperty( "missedApproachLegCount", 3, new Integer( 11 ) );
            ((ApproachObject) approachObject).setItemProperty( "fixType", 5, displayHex( getDWord( 12 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "fixType", 3, new Integer( 12 ) );
            ((ApproachObject) approachObject).setItemProperty( "fixIdent", 5, displayHex( getDWord( 12 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "fixIdent", 3, new Integer( 12 ) );
            ((ApproachObject) approachObject).setItemProperty( "fixRegion", 5, displayHex( getDWord( 16 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "fixRegion", 3, new Integer( 16 ) );
            ((ApproachObject) approachObject).setItemProperty( "airportIcao", 5, displayHex( getDWord( 16 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "airportIcao", 3, new Integer( 16 ) );
            ((ApproachObject) approachObject).setItemProperty( "altitude", 5, displayHex( getDWord( 20 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "altitude", 3, new Integer( 20 ) );
            ((ApproachObject) approachObject).setItemProperty( "heading", 5, displayHex( getDWord( 24 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "heading", 3, new Integer( 24 ) );
            ((ApproachObject) approachObject).setItemProperty( "missedAltitude", 5, displayHex( getDWord( 28 ), true ) );
            ((ApproachObject) approachObject).setItemProperty( "missedAltitude", 3, new Integer( 28 ) );
            legOffset = currentOffset + totalLength + 32;
            if( ((Integer) ((ApproachObject) approachObject).getItem( "approachLegCount" ).getDecodedData()).intValue() > 0 )
                legOffset += decompileApproachLeg( (BaseObject) approachObject, airportOffset, legOffset, raFile );
            if( ((Integer) ((ApproachObject) approachObject).getItem( "missedApproachLegCount" ).getDecodedData()).intValue() > 0 )
                legOffset += decompileMissedApproachLeg( (BaseObject) approachObject, airportOffset, legOffset, raFile );
            transitionCount = ((Integer) ((ApproachObject) approachObject).getItem( "transitionCount" ).getDecodedData()).intValue();
            for( j = 0; j < transitionCount; ++j )
                legOffset += decompileTransitions( (BaseObject) approachObject, airportOffset, legOffset, raFile );
            ((ApproachObject) approachObject).setName( new StringBuilder().append( "Approach R" ).append( ((ApproachObject) approachObject).getItem( "runwayNumber" ).getDecodedData() ).append( " " ).append( ((ApproachObject) approachObject).getItem( "type" ).getDecodedData() ).toString() );
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 32 );
                continue;
            }
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
                continue;
            }
        }
        return totalLength;
    }

    private int decompileApproachLeg(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object approachLegObject;
        int legOffset;
        int legCount;
        int j;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 8 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "2D" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        approachLegObject = new ApproachLegObject( i, length );
        baseObject.addBaseObject( (BaseObject) approachLegObject );
        ((ApproachLegObject) approachLegObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((ApproachLegObject) approachLegObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((ApproachLegObject) approachLegObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((ApproachLegObject) approachLegObject).setItemProperty( "legCount", 5, displayHex( getWord( 6 ), true ) );
        ((ApproachLegObject) approachLegObject).setItemProperty( "legCount", 3, new Integer( 6 ) );
        legOffset = currentOffset + 8;
        legCount = ((Integer) ((ApproachLegObject) approachLegObject).getItem( "legCount" ).getDecodedData()).intValue();
        for( j = 0; j < legCount; ++j )
            legOffset += decompileLeg( (BaseObject) approachLegObject, airportOffset, legOffset, raFile );
        return length;
    }

    private int decompileMissedApproachLeg(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object approachLegObject;
        int legOffset;
        int legCount;
        int j;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 8 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "2E" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        approachLegObject = new MissedApproachLegObject( i, length );
        baseObject.addBaseObject( (BaseObject) approachLegObject );
        ((MissedApproachLegObject) approachLegObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((MissedApproachLegObject) approachLegObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((MissedApproachLegObject) approachLegObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((MissedApproachLegObject) approachLegObject).setItemProperty( "legCount", 5, displayHex( getWord( 6 ), true ) );
        ((MissedApproachLegObject) approachLegObject).setItemProperty( "legCount", 3, new Integer( 6 ) );
        legOffset = currentOffset + 8;
        legCount = ((Integer) ((MissedApproachLegObject) approachLegObject).getItem( "legCount" ).getDecodedData()).intValue();
        for( j = 0; j < legCount; ++j )
            legOffset += decompileLeg( (BaseObject) approachLegObject, airportOffset, legOffset, raFile );
        return length;
    }

    private int decompileTransitions(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object transitionObject;
        int dmeOffset;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 36 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "2C" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getDWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        transitionObject = new TransitionObject( i, length );
        baseObject.addBaseObject( (BaseObject) transitionObject );
        ((TransitionObject) transitionObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((TransitionObject) transitionObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((TransitionObject) transitionObject).setItemProperty( "transitionType", 5, convertByteToHex( (long) byteData[6] ) );
        ((TransitionObject) transitionObject).setItemProperty( "transitionType", 3, new Integer( 6 ) );
        ((TransitionObject) transitionObject).setItemProperty( "legCount", 5, convertByteToHex( (long) byteData[7] ) );
        ((TransitionObject) transitionObject).setItemProperty( "legCount", 3, new Integer( 7 ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixType", 5, displayHex( getDWord( 8 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixType", 3, new Integer( 8 ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixIdent", 5, displayHex( getDWord( 8 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixIdent", 3, new Integer( 8 ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixRegion", 5, displayHex( getDWord( 12 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "fixRegion", 3, new Integer( 12 ) );
        ((TransitionObject) transitionObject).setItemProperty( "airportIcao", 5, displayHex( getDWord( 12 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "airportIcao", 3, new Integer( 12 ) );
        ((TransitionObject) transitionObject).setItemProperty( "altitude", 5, displayHex( getDWord( 16 ), true ) );
        ((TransitionObject) transitionObject).setItemProperty( "altitude", 3, new Integer( 16 ) );
        dmeOffset = 0;
        if( ((String) ((TransitionObject) transitionObject).getItem( "transitionType" ).getDecodedData()).equals( "DME" ) )
        {
            Object dmeArcObject = new DMEArcObject( i + 20, 16 );

            ((TransitionObject) transitionObject).addBaseObject( (BaseObject) dmeArcObject );
            ((DMEArcObject) dmeArcObject).setItemProperty( "dmeIdent", 5, displayHex( getDWord( 20 ), true ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "dmeIdent", 3, new Integer( 0 ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "dmeRegion", 5, displayHex( getDWord( 24 ), true ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "dmeRegion", 3, new Integer( 4 ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "airportIcao", 5, displayHex( getDWord( 24 ), true ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "airportIcao", 3, new Integer( 4 ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "radial", 5, displayHex( getDWord( 28 ), true ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "radial", 3, new Integer( 8 ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "distance", 5, displayHex( getDWord( 32 ), true ) );
            ((DMEArcObject) dmeArcObject).setItemProperty( "distance", 3, new Integer( 12 ) );
            dmeOffset = 16;
        }
        decompileTransitionLeg( (BaseObject) transitionObject, airportOffset, currentOffset + dmeOffset + 20, raFile );
        return length;
    }

    private int decompileTransitionLeg(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int length;
        Object transitionLegObject;
        int legOffset;
        int legCount;
        int j;
        String s;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 8 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        if( !convertByteToHex( (long) byteData[0] ).equals( "2F" ) || !convertByteToHex( (long) byteData[1] ).equals( "00" ) )
            return 0;
        s = getWord( 2 );
        length = Long.valueOf( s, 16 ).intValue();
        transitionLegObject = new TransitionLegObject( i, length );
        baseObject.addBaseObject( (BaseObject) transitionLegObject );
        ((TransitionLegObject) transitionLegObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
        ((TransitionLegObject) transitionLegObject).setItemProperty( "size", 5, displayHex( s, true ) );
        ((TransitionLegObject) transitionLegObject).setItemProperty( "size", 3, new Integer( 2 ) );
        ((TransitionLegObject) transitionLegObject).setItemProperty( "legCount", 5, displayHex( getWord( 6 ), true ) );
        ((TransitionLegObject) transitionLegObject).setItemProperty( "legCount", 3, new Integer( 6 ) );
        legOffset = currentOffset + 8;
        legCount = ((Integer) ((TransitionLegObject) transitionLegObject).getItem( "legCount" ).getDecodedData()).intValue();
        for( j = 0; j < legCount; ++j )
            legOffset += decompileLeg( (BaseObject) transitionLegObject, airportOffset, legOffset, raFile );
        return length;
    }

    private int decompileLeg(BaseObject baseObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        LegObject legobject1;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 44 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        legobject1 = new LegObject( i, 44 );
        baseObject.addBaseObject( (BaseObject) legobject1 );
        legobject1.setItemProperty( "legID", 5, convertByteToHex( (long) byteData[0] ) );
        legobject1.setItemProperty( "legID", 3, new Integer( 0 ) );
        legobject1.setItemProperty( "altDescriptor", 5, convertByteToHex( (long) byteData[1] ) );
        legobject1.setItemProperty( "altDescriptor", 3, new Integer( 1 ) );
        legobject1.setItemProperty( "turnLeft", 5, displayHex( getWord( 2 ), true ) );
        legobject1.setItemProperty( "turnLeft", 3, new Integer( 2 ) );
        legobject1.setItemProperty( "turnRight", 5, displayHex( getWord( 2 ), true ) );
        legobject1.setItemProperty( "turnRight", 3, new Integer( 2 ) );
        legobject1.setItemProperty( "courseType", 5, displayHex( getWord( 2 ), true ) );
        legobject1.setItemProperty( "courseType", 3, new Integer( 2 ) );
        legobject1.setItemProperty( "distanceTime", 5, displayHex( getWord( 2 ), true ) );
        legobject1.setItemProperty( "distanceTime", 3, new Integer( 2 ) );
        legobject1.setItemProperty( "flyOver", 5, displayHex( getWord( 2 ), true ) );
        legobject1.setItemProperty( "flyOver", 3, new Integer( 2 ) );
        legobject1.setItemProperty( "fixIdent", 5, displayHex( getDWord( 4 ), true ) );
        legobject1.setItemProperty( "fixIdent", 3, new Integer( 4 ) );
        legobject1.setItemProperty( "fixType", 5, displayHex( getDWord( 4 ), true ) );
        legobject1.setItemProperty( "fixType", 3, new Integer( 4 ) );
        legobject1.setItemProperty( "fixRegion", 5, displayHex( getDWord( 8 ), true ) );
        legobject1.setItemProperty( "fixRegion", 3, new Integer( 8 ) );
        legobject1.setItemProperty( "airportIcao", 5, displayHex( getDWord( 8 ), true ) );
        legobject1.setItemProperty( "airportIcao", 3, new Integer( 8 ) );
        legobject1.setItemProperty( "recommendedIdent", 5, displayHex( getDWord( 12 ), true ) );
        legobject1.setItemProperty( "recommendedIdent", 3, new Integer( 12 ) );
        legobject1.setItemProperty( "recommendedType", 5, displayHex( getDWord( 12 ), true ) );
        legobject1.setItemProperty( "recommendedType", 3, new Integer( 12 ) );
        legobject1.setItemProperty( "recommendedRegion", 5, displayHex( getDWord( 16 ), true ) );
        legobject1.setItemProperty( "recommendedRegion", 3, new Integer( 16 ) );
        legobject1.setItemProperty( "recommendedIcao", 5, displayHex( getDWord( 16 ), true ) );
        legobject1.setItemProperty( "recommendedIcao", 3, new Integer( 16 ) );
        legobject1.setItemProperty( "theta", 5, displayHex( getDWord( 20 ), true ) );
        legobject1.setItemProperty( "theta", 3, new Integer( 20 ) );
        legobject1.setItemProperty( "rho", 5, displayHex( getDWord( 24 ), true ) );
        legobject1.setItemProperty( "rho", 3, new Integer( 24 ) );
        legobject1.setItemProperty( "course", 5, displayHex( getDWord( 28 ), true ) );
        legobject1.setItemProperty( "course", 3, new Integer( 28 ) );
        legobject1.setItemProperty( "distanceTimeMeasure", 5, displayHex( getDWord( 32 ), true ) );
        legobject1.setItemProperty( "distanceTimeMeasure", 3, new Integer( 32 ) );
        legobject1.setItemProperty( "altitude1", 5, displayHex( getDWord( 36 ), true ) );
        legobject1.setItemProperty( "altitude1", 3, new Integer( 36 ) );
        legobject1.setItemProperty( "altitude2", 5, displayHex( getDWord( 40 ), true ) );
        legobject1.setItemProperty( "altitude2", 3, new Integer( 40 ) );
        legobject1.setName( new StringBuilder().append( "Leg " ).append( legobject1.getItem( "legID" ).getDecodedData() ).toString() );
        return 44;
    }

    private int decompileApronEdgeLights(AirportObject airportObject, int airportOffset, int currentOffset, RandomAccessFile raFile)
    {
        int i = airportOffset + currentOffset;
        int totalLength = 0;

        try
        {
            raFile.seek( (long) i );
            raFile.readFully( byteData, 0, 12 );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
        while( convertByteToHex( (long) byteData[0] ).equals( "31" ) && convertByteToHex( (long) byteData[1] ).equals( "00" ) )
        {
            String s = getDWord( 2 );
            int length = Long.valueOf( s, 16 ).intValue();
            Object apronEdgeLightObject = new ApronEdgeLightObject( i, length );
            int vertexCount;
            ArrayList vertexAL;
            Object edgeLightObject;
			int j;

            airportObject.addBaseObject( (BaseObject) apronEdgeLightObject );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "id", 5, displayHex( getWord( 0 ), true ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "size", 5, displayHex( s, true ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "size", 3, new Integer( 2 ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "vertexCount", 5, displayHex( getWord( 8 ), true ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "vertexCount", 3, new Integer( 8 ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "edgeCount", 5, displayHex( getWord( 10 ), true ) );
            ((ApronEdgeLightObject) apronEdgeLightObject).setItemProperty( "edgeCount", 3, new Integer( 10 ) );
            vertexCount = ((Integer) ((ApronEdgeLightObject) apronEdgeLightObject).getItem( "vertexCount" ).getDecodedData()).intValue();
            vertexAL = new ArrayList();
            for( j = 0; j < vertexCount; ++j )
            {
                Object vertexObject = new VertexObject( i + 24 + j * 8 );

                vertexAL.add( vertexObject );
                try
                {
                    raFile.seek( (long) (i + 24 + j * 8) );
                    raFile.readFully( byteData, 0, 8 );
                }
                catch( IOException ioe )
                {
                    LogEngine.getInstance().log( ioe );
                }
                ((VertexObject) vertexObject).setItemProperty( "longitude", 5, displayHex( getDWord( 0 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "longitude", 3, new Integer( 0 ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 5, displayHex( getDWord( 4 ), true ) );
                ((VertexObject) vertexObject).setItemProperty( "latitude", 3, new Integer( 4 ) );
                ((VertexObject) vertexObject).setName( new StringBuilder().append( "Vertex " ).append( j ).toString() );
            }
            j = -1;
            j = i + 24 + vertexCount * 8;
            i = ((Integer) ((ApronEdgeLightObject) apronEdgeLightObject).getItem( "edgeCount" ).getDecodedData()).intValue();
            edgeLightObject = null;
            for( j = 0; j < i; ++j )
            {
                Object testObject = new EdgeLightObject( j, 8 );
                int endIndex;
                int k;

                try
                {
                    raFile.seek( (long) j );
                    raFile.readFully( byteData, 0, 12 );
                }
                catch( IOException ioe )
                {
                    LogEngine.getInstance().log( ioe );
                }
                ((EdgeLightObject) testObject).setItemProperty( "startIndex", 5, displayHex( getWord( 4 ), true ) );
                ((EdgeLightObject) testObject).setItemProperty( "startIndex", 3, new Integer( 4 ) );
                ((EdgeLightObject) testObject).setItemProperty( "endIndex", 5, displayHex( getWord( 6 ), true ) );
                ((EdgeLightObject) testObject).setItemProperty( "endIndex", 3, new Integer( 6 ) );
                k = ((Integer) ((EdgeLightObject) testObject).getItem( "startIndex" ).getDecodedData()).intValue();
                endIndex = ((Integer) ((EdgeLightObject) testObject).getItem( "endIndex" ).getDecodedData()).intValue();
                if( j != k )
                {
                    edgeLightObject = testObject;
                    ((ApronEdgeLightObject) apronEdgeLightObject).addBaseObject( (BaseObject) edgeLightObject );
                    ((EdgeLightObject) edgeLightObject).addBaseObject( (BaseObject) vertexAL.get( k ) );
                }
                ((EdgeLightObject) edgeLightObject).addBaseObject( (BaseObject) vertexAL.get( endIndex ) );
                j = endIndex;
                j += 8;
            }
            totalLength += length;
            i += length;
            try
            {
                raFile.seek( (long) i );
                raFile.readFully( byteData, 0, 12 );
                continue;
            }
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
                continue;
            }
        }
        return totalLength;
    }

    private String displayHex(String hex, boolean reverse)
    {
        StringBuffer buffer = new StringBuffer();
        int i;

        if( reverse )
        {
            for( i = hex.length() - 1; i >= 0; i -= 2 )
            {
                buffer.append( hex.charAt( i - 1 ) ).append( hex.charAt( i ) );
                buffer.append( " " );
            }
        }
        else
        {
            for( i = 0; i < hex.length(); i += 2 )
            {
                buffer.append( hex.charAt( i ) ).append( hex.charAt( i + 1 ) );
                buffer.append( " " );
            }
        }
        return buffer.toString().trim();
    }

    private int convertDWordToInt(String dWord)
    {
        return Long.valueOf( dWord, 16 ).intValue();
    }

    private String getDWord(int index)
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( convertByteToHex( (long) byteData[index + 3] ) );
        buffer.append( convertByteToHex( (long) byteData[index + 2] ) );
        buffer.append( convertByteToHex( (long) byteData[index + 1] ) );
        buffer.append( convertByteToHex( (long) byteData[index] ) );
        return buffer.toString();
    }

    private String getWord(int index)
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( convertByteToHex( (long) byteData[index + 1] ) );
        buffer.append( convertByteToHex( (long) byteData[index] ) );
        return buffer.toString();
    }

    private String getGUID(int index)
    {
        StringBuffer buffer = new StringBuffer();
        int i;

        for( i = 15; i >= 0; --i )
            buffer.append( convertByteToHex( (long) byteData[index + i] ) );
        return buffer.toString();
    }

    private String convertByteToHex(long byteNumber)
    {
        String hexValue = Long.toHexString( byteNumber );

        if( hexValue.length() > 2 )
            hexValue = hexValue.substring( hexValue.length() - 2 );
        else if( hexValue.length() == 1 )
            hexValue = new StringBuilder().append( "0" ).append( hexValue ).toString();
        return hexValue.toUpperCase();
    }

    static 
    {
        pointers[0] = "03";
        pointers[1] = "13";
        pointers[2] = "17";
        pointers[3] = "18";
        pointers[4] = "20";
        pointers[5] = "22";
        pointers[6] = "23";
        pointers[7] = "25";
        pointers[8] = "27";
        pointers[9] = "2B";
        pointers[10] = "2C";
        pointers[11] = "2E";
        pointers[12] = "28";
        pointers[13] = "2A";
        pointers[14] = "29";
        sections = new String[15];
        sections[0] = "airportData";
        sections[1] = "VOR-ILS-Data";
        sections[2] = "NDBData";
        sections[3] = "markers";
        sections[4] = "boundaryData";
        sections[5] = "waypointData";
        sections[6] = "geopolData";
        sections[7] = "sceneryObjects";
        sections[8] = "nameList";
        sections[9] = "mdlData";
        sections[10] = "additionalAirportData";
        sections[11] = "exclusionRectangle";
        sections[12] = "VOR-ICAO-Index";
        sections[13] = "waypointIcaoIndex";
        sections[14] = "ndbIcaoIndex";
    }
}