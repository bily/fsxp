package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.model.BackgroundImageModel;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

import com.zbluesoftware.fsxp.model.BackgroundImageModel;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

class BackgroundImageEngine$1 extends Thread {

    BackgroundImageEngine$1(BackgroundImageEngine backgroundimageengine1, ArrayList arraylist1, String s, String str)
    {
        this$0 = backgroundimageengine1;
        val$bgImagesAL = arraylist1;
        val$name = s;
        val$ident = str;
    }

    ArrayList val$bgImagesAL;     // final access specifier removed
    String val$name;     // final access specifier removed
    String val$ident;     // final access specifier removed
    BackgroundImageEngine this$0;     // final access specifier removed

    public void run()
    {
        try
        {
            Object arrayList = new ArrayList();
            int i;
            Object h2;
            File file;
            Object chkDir;
            DocumentBuilder builder;
            Object xmlDoc;
            Object airports;
            Object streamResult;
            TransformerFactory tf;
            Transformer serializer;
            HashMap hashmap1;
            Object obj;

            for( i = 0; i < val$bgImagesAL.size(); ++i )
            {
                h2 = new HashMap();
                ((HashMap) h2).put( "name", val$name );
                ((HashMap) h2).put( "ident", val$ident );
                ((HashMap) h2).put( "path", ((BackgroundImageModel) val$bgImagesAL.get( i )).getPath() );
                ((HashMap) h2).put( "visible", new Boolean( ((BackgroundImageModel) val$bgImagesAL.get( i )).isImageVisible() ) );
                ((HashMap) h2).put( "topLeft", (LatLonPoint) ((BackgroundImageModel) val$bgImagesAL.get( i )).getTopLeft().clone() );
                ((HashMap) h2).put( "bottomRight", (LatLonPoint) ((BackgroundImageModel) val$bgImagesAL.get( i )).getBottomRight().clone() );
                ((ArrayList) arrayList).add( h2 );
            }
            hashmap1 = new HashMap();
            hashmap1.put( new StringBuilder().append( val$name ).append( "-" ).append( val$ident ).toString(), arrayList );
            h2 = System.getProperty( "os.name" );
            if( ((String) h2).equals( "Windows Vista" ) )
            {
                chkDir = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner" ).toString() );
                ((File) chkDir).mkdirs();
                file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\backgroundImages.xml" ).toString() );
            }
            else if( ((String) h2).equals( "Windows XP" ) )
            {
                chkDir = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\Application Data\\zBlueSoftware\\FSXPlanner" ).toString() );
                ((File) chkDir).mkdirs();
                file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\Application Data\\zBlueSoftware\\FSXPlanner\\backgroundImages.xml" ).toString() );
            }
            else
                file = new File( new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "conf" ).append( File.separator ).append( "backgroundImages.xml" ).toString() );
            chkDir = DocumentBuilderFactory.newInstance();
            ((DocumentBuilderFactory) chkDir).setValidating( true );
            builder = ((DocumentBuilderFactory) chkDir).newDocumentBuilder();
            builder.setEntityResolver( (EntityResolver) DTDResolver.getInstance() );
            if( file.exists() )
            {
                xmlDoc = builder.parse( file );
                airports = ((Document) xmlDoc).getElementsByTagName( "Airport" );
                for( i = 0; i < ((NodeList) airports).getLength(); ++i )
                    BackgroundImageEngine.access$000( this$0, (Element) ((NodeList) airports).item( i ), hashmap1, val$name, val$ident );
            }
            xmlDoc = builder.newDocument();
            airports = ((Document) xmlDoc).createElement( "Airports" );
            ((Document) xmlDoc).appendChild( (Node) airports );
            BackgroundImageEngine.access$100( this$0, (Document) xmlDoc, (Element) airports, hashmap1 );
            obj = new DOMSource( (Node) xmlDoc );
            streamResult = new StreamResult( file );
            tf = TransformerFactory.newInstance();
            serializer = tf.newTransformer();
            serializer.setOutputProperty( "encoding", "ISO-8859-1" );
            serializer.setOutputProperty( "doctype-system", "background-images.dtd" );
            serializer.setOutputProperty( "indent", "yes" );
            serializer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4" );
            serializer.transform( (Source) obj, (Result) streamResult );
        }
        catch( SAXException saxexception1 )
        {
	    LogEngine.getInstance().log( (Throwable) saxexception1 );
        }
        catch( IOException ioexception1 )
        {
            LogEngine.getInstance().log( ioexception1 );
        }
        catch( ParserConfigurationException parserconfigurationexception1 )
        {
            LogEngine.getInstance().log( (Throwable) parserconfigurationexception1 );
        }
        catch( TransformerException transformerexception1 )
        {
            LogEngine.getInstance().log( (Throwable) transformerexception1 );
        }
        catch( NullPointerException nullpointerexception1 )
        {
            LogEngine.getInstance().log( nullpointerexception1 );
        }
    }
}



public class BackgroundImageEngine {

    private BackgroundImageEngine()
    {
        latLonFormat.setMinimumIntegerDigits( 0 );
        latLonFormat.setMaximumFractionDigits( 14 );
    }

    private static BackgroundImageEngine engine = null;
    private NumberFormat latLonFormat = NumberFormat.getInstance( Locale.US );

    public static BackgroundImageEngine getInstance()
    {
        if( engine == null )
            engine = new BackgroundImageEngine();
        return engine;
    }

// Error: Internal #901: 
// The following method may not be correct.

    public ArrayList getBGImages(String name, String ident)
    {
        File file;
        ArrayList arrayList;
        String os = System.getProperty("os.name");
        if(os.equals("Windows Vista"))
            file = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\backgroundImages.xml").toString());
        else
        if(os.equals("Windows XP"))
            file = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\Application Data\\zBlueSoftware\\FSXPlanner\\backgroundImages.xml").toString());
        else
            file = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("conf").append(File.separator).append("backgroundImages.xml").toString());

        if( !file.exists() )
            return new ArrayList();
        else
        {
            arrayList = new ArrayList();
            try
            {

	NodeList airports;
        int i;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setEntityResolver(DTDResolver.getInstance());
        Document xmlDoc = builder.parse(file);
        airports = xmlDoc.getElementsByTagName("Airport");
        i = 0;
                while( i < airports.getLength() )
                {
                    if( checkAirport( (Element) airports.item( i ), arrayList, name, ident ) )
                     return arrayList;
                }
            }
            catch( SAXException saxexception1 )
            {
                LogEngine.getInstance().log( (Throwable) saxexception1 );
            }
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
            }
            catch( ParserConfigurationException parserconfigurationexception1 )
            {
                LogEngine.getInstance().log( (Throwable) parserconfigurationexception1 );
            }
            return arrayList;
        }
    }

    private boolean checkAirport(Element airportElement, ArrayList arrayList, String name, String ident)
    {
        if( !airportElement.getAttribute( "name" ).equals( name ) || !airportElement.getAttribute( "ident" ).equals( ident ) )
            return false;
        else
        {
            NodeList nodeList = airportElement.getElementsByTagName( "BackgroundImage" );
            int i;

label_171:
            for( i = 0; i < nodeList.getLength(); ++i )
            {
                Element backgroundImageElement = (Element) nodeList.item( i );
                String path = backgroundImageElement.getAttribute( "path" );
                boolean visibility = backgroundImageElement.getAttribute( "visible" ).equals( "TRUE" );
                LatLonPoint topLeft = null;
                LatLonPoint bottomRight = null;
                NodeList vertices = backgroundImageElement.getElementsByTagName( "Vertex" );
                int j;
                Object vertexElement;
                Object backgroundImageModel;
                File file1;
                BufferedImage bufferedimage1;

                for( j = 0; j < vertices.getLength(); ++j )
                {
                    vertexElement = (Element) vertices.item( j );
                    try
                    {
                        if( j == 0 )
                            topLeft = new LatLonPoint( Double.parseDouble( ((Element) vertexElement).getAttribute( "lat" ) ), Double.parseDouble( ((Element) vertexElement).getAttribute( "lon" ) ) );
                        else
                            bottomRight = new LatLonPoint( Double.parseDouble( ((Element) vertexElement).getAttribute( "lat" ) ), Double.parseDouble( ((Element) vertexElement).getAttribute( "lon" ) ) );
                    }
                    catch( NumberFormatException nfe )
                    {
                        LogEngine.getInstance().log( nfe );
                    }
                }
                file1 = new File( path );
                if( !file1.exists() )
                {
                    file1 = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( File.separator ).append( "My Documents" ).append( File.separator ).append( "My Pictures" ).append( File.separator ).append( "FSXPlanner" ).append( File.separator ).append( file1.getName() ).toString() );
                    if( !file1.exists() )
                        continue label_171;
                }
                vertexElement = new ImageIcon( file1.getAbsolutePath() );
                bufferedimage1 = new BufferedImage( ((ImageIcon) vertexElement).getIconWidth(), ((ImageIcon) vertexElement).getIconHeight(), 5 );
                bufferedimage1.getGraphics().drawImage( ((ImageIcon) vertexElement).getImage(), 0, 0, null );
                backgroundImageModel = new BackgroundImageModel( bufferedimage1, path, topLeft, bottomRight );
                ((BackgroundImageModel) backgroundImageModel).setImageVisible( visibility );
                arrayList.add( backgroundImageModel );
            }
            return true;
        }
    }

    public void saveBGImages(String name, String ident, ArrayList bgImagesAL)
    {
        Object thread = new BackgroundImageEngine$1( this, bgImagesAL, name, ident );

        ((Thread) thread).setPriority( Thread.currentThread().getPriority() - 1 );
        ((Thread) thread).start();
    }

    private void parseAirport(Element airportElement, HashMap hashMap, String name, String ident)
    {
        if( !airportElement.getAttribute( "name" ).equals( name ) || !airportElement.getAttribute( "ident" ).equals( ident ) )
        {
            Object arrayList = new ArrayList();
            String airportName = airportElement.getAttribute( "name" );
            String airportIdent = airportElement.getAttribute( "ident" );
            NodeList nodeList;
            int i;

            hashMap.put( new StringBuilder().append( airportName ).append( "-" ).append( airportIdent ).toString(), arrayList );
            nodeList = airportElement.getElementsByTagName( "BackgroundImage" );
            for( i = 0; i < nodeList.getLength(); ++i )
            {
                Element backgroundImageElement = (Element) nodeList.item( i );
                String path = backgroundImageElement.getAttribute( "path" );
                boolean visibility = backgroundImageElement.getAttribute( "visibile" ).equals( "TRUE" );
                LatLonPoint topLeft = null;
                LatLonPoint bottomRight = null;
                NodeList vertices = backgroundImageElement.getElementsByTagName( "Vertex" );
                int j;
                Object obj;

                for( j = 0; j < vertices.getLength(); ++j )
                {
                    Element vertexElement = (Element) vertices.item( j );

                    try
                    {
                        if( j == 0 )
                            topLeft = new LatLonPoint( Double.parseDouble( vertexElement.getAttribute( "lat" ) ), Double.parseDouble( vertexElement.getAttribute( "lon" ) ) );
                        else
                            bottomRight = new LatLonPoint( Double.parseDouble( vertexElement.getAttribute( "lat" ) ), Double.parseDouble( vertexElement.getAttribute( "lon" ) ) );
                    }
                    catch( NumberFormatException nfe )
                    {
                        LogEngine.getInstance().log( nfe );
                    }
                }
                obj = new HashMap();
                ((HashMap) obj).put( "path", path );
                ((HashMap) obj).put( "visible", new Boolean( visibility ) );
                ((HashMap) obj).put( "topLeft", topLeft );
                ((HashMap) obj).put( "bottomRight", bottomRight );
                ((HashMap) obj).put( "name", airportName );
                ((HashMap) obj).put( "ident", airportIdent );
                ((ArrayList) arrayList).add( obj );
            }
        }
    }

    private void writeAirports(Document xmlDoc, Element airportsElement, HashMap hashMap)
    {
        Iterator iterator = hashMap.keySet().iterator();

        while( iterator.hasNext() )
        {
            ArrayList arrayList = (ArrayList) hashMap.get( iterator.next() );
            Object airportElement = xmlDoc.createElement( "Airport" );
            int i;

            if( arrayList.size() > 0 )
                airportsElement.appendChild( (Node) airportElement );
            for( i = 0; i < arrayList.size(); ++i )
            {
                HashMap h2 = (HashMap) arrayList.get( i );
                Object backgroundImageElement = xmlDoc.createElement( "BackgroundImage" );
                Object vertex1Element;
                Object vertex2Element;

                ((Element) backgroundImageElement).setAttribute( "path", (String) h2.get( "path" ) );
                ((Element) backgroundImageElement).setAttribute( "visible", (((Boolean) h2.get( "visible" )).booleanValue()) ? "TRUE" : "FALSE" );
                ((Element) airportElement).appendChild( (Node) backgroundImageElement );
                ((Element) airportElement).setAttribute( "name", (String) h2.get( "name" ) );
                ((Element) airportElement).setAttribute( "ident", (String) h2.get( "ident" ) );
                vertex1Element = xmlDoc.createElement( "Vertex" );
                ((Element) vertex1Element).setAttribute( "lat", latLonFormat.format( ((LatLonPoint) h2.get( "topLeft" )).getLat() ) );
                ((Element) vertex1Element).setAttribute( "lon", latLonFormat.format( ((LatLonPoint) h2.get( "topLeft" )).getLon() ) );
                ((Element) backgroundImageElement).appendChild( (Node) vertex1Element );
                vertex2Element = xmlDoc.createElement( "Vertex" );
                ((Element) vertex2Element).setAttribute( "lat", latLonFormat.format( ((LatLonPoint) h2.get( "bottomRight" )).getLat() ) );
                ((Element) vertex2Element).setAttribute( "lon", latLonFormat.format( ((LatLonPoint) h2.get( "bottomRight" )).getLon() ) );
                ((Element) backgroundImageElement).appendChild( (Node) vertex2Element );
            }
        }
    }

    static void access$000(BackgroundImageEngine x0, Element x1, HashMap x2, String x3, String x4)
    {
        x0.parseAirport( x1, x2, x3, x4 );
    }

    static void access$100(BackgroundImageEngine x0, Document x1, Element x2, HashMap x3)
    {
        x0.writeAirports( x1, x2, x3 );
    }
}