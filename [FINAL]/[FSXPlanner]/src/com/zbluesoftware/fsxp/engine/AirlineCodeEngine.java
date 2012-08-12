// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AirlineCodeEngine.java

package com.zbluesoftware.fsxp.engine;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            LogEngine

public class AirlineCodeEngine
{

    private AirlineCodeEngine()
    {
        airlineAL = new ArrayList();
        readAirlineCodes();
    }

    public static AirlineCodeEngine getInstance()
    {
        if(engine == null)
            engine = new AirlineCodeEngine();
        return engine;
    }

    public ArrayList getAirlineAL()
    {
        return airlineAL;
    }

    private void readAirlineCodes()
    {
        File file;
        String os = System.getProperty("os.name");
        if(os.equals("Windows Vista"))
            file = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\AirlineCodes.xml").toString());
        else
        if(os.equals("Windows XP"))
            file = new File((new StringBuilder()).append(System.getProperty("user.home")).append("\\Application Data\\zBlueSoftware\\FSXPlanner\\AirlineCodes.xml").toString());
        else
            file = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("conf").append(File.separator).append("AirlineCodes.xml").toString());
        if(!file.exists())
            return;
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document xmlDoc = builder.parse(file);
            NodeList airlines = xmlDoc.getElementsByTagName("Airline");
            int total = airlines.getLength();
            for(int i = 0; i < total; i++)
            {
                Element airlineElement = (Element)airlines.item(i);
                HashMap hashMap = new HashMap();
                hashMap.put("N", airlineElement.getAttribute("N"));
                hashMap.put("C", airlineElement.getAttribute("C"));
                try
                {
                    int red = Integer.parseInt(airlineElement.getAttribute("R"));
                    int green = Integer.parseInt(airlineElement.getAttribute("G"));
                    int blue = Integer.parseInt(airlineElement.getAttribute("B"));
                    Color color = new Color(red, green, blue);
                    hashMap.put("color", new Integer(color.getRGB()));
                }
                catch(NumberFormatException nfe)
                {
                    hashMap.put("color", Integer.valueOf(Color.white.getRGB()));
                }
                airlineAL.add(hashMap);
            }

        }
        catch(SAXException saxe)
        {
            LogEngine.getInstance().log(saxe);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
        }
        catch(ParserConfigurationException pce)
        {
            LogEngine.getInstance().log(pce);
        }
        return;
    }

    private Pattern latPattern1;
    private Pattern lonPattern1;
    private JDialog progressDialog;
    private JLabel airportDataLabel;
    private JLabel sectionDataLabel;
    private ArrayList airlineAL;
    private static AirlineCodeEngine engine = null;

}
