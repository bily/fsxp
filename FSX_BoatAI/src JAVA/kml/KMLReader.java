// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMLReader.java

package kml;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.parsers.*;
import org.lc0277lib.geography.LatLon;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package kml:
//            KMLFormatException

public class KMLReader
{
    public static interface KMLPathHandler
    {

        public abstract void handlePath(String s, String s1);

        public abstract void handleError(Exception exception);
    }


    public KMLReader(String fileName)
        throws IOException, KMLFormatException
    {
        this(new File(fileName));
    }

    public KMLReader(File file)
        throws IOException, KMLFormatException
    {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        byte b[] = new byte[2];
        raf.read(b);
        raf.close();
        if(b[0] == 80 && b[1] == 75)
        {
            ZipFile zf = new ZipFile(file);
            for(Enumeration e = zf.entries(); e.hasMoreElements();)
            {
                ZipEntry ze = (ZipEntry)e.nextElement();
                if(!ze.isDirectory() && ze.getName().equalsIgnoreCase("doc.kml"))
                    parseXML(zf.getInputStream(ze));
            }

            zf.close();
        } else
        {
            FileInputStream fis = new FileInputStream(file);
            parseXML(fis);
            fis.close();
        }
    }

    private void parseXML(InputStream is)
        throws IOException, KMLFormatException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            root = document.getDocumentElement();
        }
        catch(ParserConfigurationException e)
        {
            throw new KMLFormatException(e.getLocalizedMessage());
        }
        catch(SAXException e)
        {
            throw new KMLFormatException(e.getLocalizedMessage());
        }
    }

    public void extractPaths(KMLPathHandler pathHandler)
    {
        extractPaths0(root, pathHandler);
    }

    private void extractPaths0(Node elt, KMLPathHandler pathHandler)
    {
        if(elt.getNodeType() != 1)
            return;
        String nodeName = elt.getNodeName();
        if("Placemark".equalsIgnoreCase(nodeName))
        {
            String name = null;
            String points = null;
            NodeList nl = elt.getChildNodes();
            for(int i = 0; i < nl.getLength(); i++)
            {
                Node son = nl.item(i);
                if(son.getNodeType() == 1)
                {
                    String sonName = son.getNodeName();
                    if("name".equalsIgnoreCase(sonName))
                    {
                        name = son.getFirstChild().getNodeValue();
                        if(name != null)
                            name = name.trim();
                    }
                    if("LineString".equalsIgnoreCase(sonName))
                    {
                        NodeList nll = son.getChildNodes();
                        for(int j = 0; j < nll.getLength(); j++)
                        {
                            Node son2 = nll.item(j);
                            if(son2.getNodeType() == 1)
                            {
                                String son2Name = son2.getNodeName();
                                if("coordinates".equalsIgnoreCase(son2Name))
                                    points = son2.getFirstChild().getNodeValue();
                            }
                        }

                    }
                }
            }

            if(name != null && points != null)
                pathHandler.handlePath(name, points);
        } else
        {
            NodeList nl = elt.getChildNodes();
            for(int i = 0; i < nl.getLength(); i++)
                extractPaths0(nl.item(i), pathHandler);

        }
    }

    public String extractCoordinates(final String name)
    {
        final ThreadLocal val = new ThreadLocal();
        val.set(null);
        extractPaths(new KMLPathHandler() {

            public void handleError(Exception exception)
            {
            }

            public void handlePath(String kmlPathName, String coordinates)
            {
                if(name.equalsIgnoreCase(kmlPathName))
                    val.set(coordinates);
            }

            final KMLReader this$0;
			
            {
				this$0 = KMLReader.this;
            }
        }
);
        return (String)val.get();
    }

    public List parsePoints(String s)
    {
        List ll = new LinkedList();
        parsePoints(s, ((Collection) (ll)));
        return ll;
    }

    public void parsePoints(String s, Collection points)
    {
        for(StringTokenizer toker = new StringTokenizer(s.toString(), "\t\n "); toker.hasMoreTokens();)
        {
            String token = toker.nextToken().trim();
            if(token.length() >= 2)
            {
                String parts[] = token.split(",");
                if(parts.length >= 2)
                    try
                    {
                        double lon = Double.parseDouble(parts[0]);
                        double lat = Double.parseDouble(parts[1]);
                        points.add(new LatLon(lat, lon));
                    }
                    catch(NumberFormatException numberformatexception) { }
            }
        }

    }

    private Element root;
}
