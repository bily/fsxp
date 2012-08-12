// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMLWriter.java

package kml;

import java.io.File;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.lc0277lib.geography.LatLon;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class KMLWriter
{

    protected Element createElement(String name)
    {
        return doc.createElement(name);
    }

    public KMLWriter()
        throws ParserConfigurationException
    {
        doc = null;
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = builder.newDocument();
        rootElement = doc.createElement("kml");
        rootElement.setAttribute("xmlns", "http://earth.google.com/kml/2.0");
        doc.appendChild(rootElement);
        currentElement = rootElement;
        createElementAndChangeTo("Document", null);
        createElementAndAdd("name", (new StringBuilder("File created with ")).append(getClass().getName()).toString());
    }

    public Element createElementAndChangeTo(String name, String text)
    {
        Element e = createElementAndAdd(name, text);
        currentElement = e;
        return e;
    }

    public Element createElementAndAdd(String name, String text)
    {
        Element e = createElement(name);
        if(text != null)
            e.setTextContent(text);
        currentElement.appendChild(e);
        return e;
    }

    public void endCurrentElement()
    {
        currentElement = (Element)currentElement.getParentNode();
    }

    public void createFolder(String name)
    {
        createElementAndChangeTo("Folder", null);
        createElementAndAdd("name", name);
    }

    public void addPlacemark(double lon, double lat, String name)
    {
        addPlacemark(lon, lat, name, null);
    }

    public void addPlacemark(double lon, double lat, String name, String description)
    {
        createElementAndChangeTo("Placemark", null);
        createElementAndAdd("name", name);
        if(description != null)
            createElementAndAdd("description", (new StringBuilder("![CDATA[")).append(description).append("]]").toString());
        createElementAndChangeTo("LookAt", null);
        createElementAndAdd("longitude", Double.toString(lon));
        createElementAndAdd("latitude", Double.toString(lon));
        createElementAndAdd("altitude", "7000000");
        createElementAndAdd("range", "0");
        createElementAndAdd("tilt", "0");
        createElementAndAdd("heading", "0");
        endCurrentElement();
        createElementAndAdd("styleUrl", "root://styles#default+icon=0x307</styleUrl");
        createElementAndChangeTo("Point", null);
        createElementAndAdd("coordinates", (new StringBuilder(String.valueOf(lon))).append(",").append(lat).toString());
        endCurrentElement();
        endCurrentElement();
    }

    public void addPath(String name, double lon[], double lat[], double alt[])
    {
        createElementAndChangeTo("Placemark", null);
        createElementAndAdd("name", name);
        createElementAndChangeTo("Style", null);
        createElementAndChangeTo("LineStyle", null);
        createElementAndAdd("color", "ff0000ff");
        createElementAndAdd("width", "2");
        endCurrentElement();
        endCurrentElement();
        createElementAndChangeTo("LineString", null);
        createElementAndAdd("tessellate", "1");
        StringBuffer sgb = new StringBuffer("");
        for(int i = 0; i < lon.length; i++)
        {
            sgb.append(lon[i]);
            sgb.append(",");
            sgb.append(lat[i]);
            sgb.append(",");
            if(alt != null)
                sgb.append(alt[i]);
            else
                sgb.append("0.0");
            sgb.append("\n");
        }

        createElementAndAdd("coordinates", sgb.toString());
        endCurrentElement();
        endCurrentElement();
    }

    public void addPath(Collection p, String name)
    {
        addPath(p, name, "ff0000ff");
    }

    public void addPath(Collection p, String name, String color)
    {
        createElementAndChangeTo("Placemark", null);
        createElementAndAdd("name", name);
        createElementAndChangeTo("Style", null);
        createElementAndChangeTo("LineStyle", null);
        createElementAndAdd("color", color);
        createElementAndAdd("width", "2");
        endCurrentElement();
        endCurrentElement();
        createElementAndChangeTo("LineString", null);
        createElementAndAdd("tessellate", "1");
        StringBuffer sgb = new StringBuffer("");
        for(Iterator iterator = p.iterator(); iterator.hasNext(); sgb.append(",0.0\n"))
        {
            LatLon pt = (LatLon)iterator.next();
            sgb.append(pt.getLongitude());
            sgb.append(",");
            sgb.append(pt.getLatitude());
        }

        createElementAndAdd("coordinates", sgb.toString());
        endCurrentElement();
        endCurrentElement();
    }

    public void write(File out)
        throws TransformerFactoryConfigurationError, TransformerException
    {
        write(new StreamResult(out));
    }

    public void write(Writer wr)
        throws TransformerFactoryConfigurationError, TransformerException
    {
        write(new StreamResult(wr));
    }

    public void write(StreamResult sr)
        throws TransformerFactoryConfigurationError, TransformerException
    {
        Transformer tr = TransformerFactory.newInstance().newTransformer();
        tr.setOutputProperty("indent", "yes");
        DOMSource ds = new DOMSource(doc);
        tr.transform(ds, sr);
    }

    protected Document doc;
    private Element rootElement;
    protected Element currentElement;
}
