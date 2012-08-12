// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParseEngine.java

package com.zbluesoftware.fsxp.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseEngine
{

    private ParseEngine()
    {
        latPattern1 = Pattern.compile("[n,s]\\s*\\d+ \\d+\\.\\d+");
        lonPattern1 = Pattern.compile("[e,w]\\s*\\d+ \\d+\\.\\d+");
        latPattern2 = Pattern.compile("[n,s]\\s*\\d+ \\d+ \\d+\\.\\d+");
        lonPattern2 = Pattern.compile("[e,w]\\s*\\d+ \\d+ \\d+\\.\\d+");
        latPattern3 = Pattern.compile("\\d+ \\d+\\.\\d+\\s*[n,s]");
        lonPattern3 = Pattern.compile("\\d+ \\d+\\.\\d+\\s*[e,w]");
        latPattern4 = Pattern.compile("\\d+ \\d+ \\d+\\.\\d+\\s*[n,s]");
        lonPattern4 = Pattern.compile("\\d+ \\d+ \\d+\\.\\d+\\s*[e,w]");
    }

    public static ParseEngine getInstance()
    {
        if(engine == null)
            engine = new ParseEngine();
        return engine;
    }

    public double parseLatitude(String lat)
    {
        lat = lat.toLowerCase().trim();
        Matcher matcher = latPattern1.matcher(lat);
        if(matcher.matches())
        {
            int flip = lat.startsWith("s") ? -1 : 1;
            lat = lat.substring(1).trim();
            int spaceIndex = lat.indexOf(" ");
            String startLat = lat.substring(0, spaceIndex);
            String endLat = lat.substring(spaceIndex);
            double latitude;
            try
            {
                latitude = Double.parseDouble(startLat);
                latitude += Double.parseDouble(endLat) / 60D;
                latitude *= flip;
                if(latitude < -90D)
                    latitude = -90D;
                else
                if(latitude > 90D)
                    latitude = 90D;
            }
            catch(NumberFormatException nfe)
            {
                latitude = (1.0D / 0.0D);
            }
            return latitude;
        }
        matcher = latPattern2.matcher(lat);
        if(matcher.matches())
        {
            int flip = lat.startsWith("s") ? -1 : 1;
            lat = lat.substring(1).trim();
            int spaceIndex1 = lat.indexOf(" ");
            int spaceIndex2 = lat.lastIndexOf(" ");
            String startLat = lat.substring(0, spaceIndex1);
            String midLat = lat.substring(spaceIndex1, spaceIndex2);
            String endLat = lat.substring(spaceIndex2);
            double latitude;
            try
            {
                latitude = Double.parseDouble(startLat);
                latitude += Double.parseDouble(midLat) / 60D;
                latitude += Double.parseDouble(endLat) / 3600D;
                latitude *= flip;
                if(latitude < -90D)
                    latitude = -90D;
                else
                if(latitude > 90D)
                    latitude = 90D;
            }
            catch(NumberFormatException nfe)
            {
                latitude = (1.0D / 0.0D);
            }
            return latitude;
        }
        matcher = latPattern3.matcher(lat);
        if(matcher.matches())
        {
            int flip = lat.endsWith("s") ? -1 : 1;
            lat = lat.substring(0, lat.length() - 1).trim();
            int spaceIndex = lat.indexOf(" ");
            String startLat = lat.substring(0, spaceIndex);
            String endLat = lat.substring(spaceIndex);
            double latitude;
            try
            {
                latitude = Double.parseDouble(startLat);
                latitude += Double.parseDouble(endLat) / 60D;
                latitude *= flip;
                if(latitude < -90D)
                    latitude = -90D;
                else
                if(latitude > 90D)
                    latitude = 90D;
            }
            catch(NumberFormatException nfe)
            {
                latitude = (1.0D / 0.0D);
            }
            return latitude;
        }
        matcher = latPattern4.matcher(lat);
        if(matcher.matches())
        {
            int flip = lat.endsWith("s") ? -1 : 1;
            lat = lat.substring(0, lat.length() - 1).trim();
            int spaceIndex1 = lat.indexOf(" ");
            int spaceIndex2 = lat.lastIndexOf(" ");
            String startLat = lat.substring(0, spaceIndex1);
            String midLat = lat.substring(spaceIndex1, spaceIndex2);
            String endLat = lat.substring(spaceIndex2);
            double latitude;
            try
            {
                latitude = Double.parseDouble(startLat);
                latitude += Double.parseDouble(midLat) / 60D;
                latitude += Double.parseDouble(endLat) / 3600D;
                latitude *= flip;
                if(latitude < -90D)
                    latitude = -90D;
                else
                if(latitude > 90D)
                    latitude = 90D;
            }
            catch(NumberFormatException nfe)
            {
                latitude = (1.0D / 0.0D);
            }
            return latitude;
        }
        double latitude;
        try
        {
            latitude = Double.parseDouble(lat);
            if(latitude < -90D)
                latitude = -90D;
            else
            if(latitude > 90D)
                latitude = 90D;
        }
        catch(NumberFormatException nfe)
        {
            latitude = (1.0D / 0.0D);
        }
        return latitude;
    }

    public double parseLongitude(String lon)
    {
        lon = lon.toLowerCase().trim();
        Matcher matcher = lonPattern1.matcher(lon);
        if(matcher.matches())
        {
            int flip = lon.startsWith("w") ? -1 : 1;
            lon = lon.substring(1).trim();
            int spaceIndex = lon.indexOf(" ");
            String startLon = lon.substring(0, spaceIndex);
            String endLon = lon.substring(spaceIndex);
            double longitude;
            try
            {
                longitude = Double.parseDouble(startLon);
                longitude += Double.parseDouble(endLon) / 60D;
                longitude *= flip;
                if(longitude < -180D)
                    longitude = -180D;
                else
                if(longitude > 180D)
                    longitude = 180D;
            }
            catch(NumberFormatException nfe)
            {
                longitude = (1.0D / 0.0D);
            }
            return longitude;
        }
        matcher = lonPattern2.matcher(lon);
        if(matcher.matches())
        {
            int flip = lon.startsWith("w") ? -1 : 1;
            lon = lon.substring(1).trim();
            int spaceIndex1 = lon.indexOf(" ");
            int spaceIndex2 = lon.lastIndexOf(" ");
            String startLon = lon.substring(0, spaceIndex1);
            String midLon = lon.substring(spaceIndex1, spaceIndex2);
            String endLon = lon.substring(spaceIndex2);
            double longitude;
            try
            {
                longitude = Double.parseDouble(startLon);
                longitude += Double.parseDouble(midLon) / 60D;
                longitude += Double.parseDouble(endLon) / 3600D;
                longitude *= flip;
                if(longitude < -180D)
                    longitude = -180D;
                else
                if(longitude > 180D)
                    longitude = 180D;
            }
            catch(NumberFormatException nfe)
            {
                longitude = (1.0D / 0.0D);
            }
            return longitude;
        }
        matcher = lonPattern3.matcher(lon);
        if(matcher.matches())
        {
            int flip = lon.endsWith("w") ? -1 : 1;
            lon = lon.substring(0, lon.length() - 1).trim();
            int spaceIndex = lon.indexOf(" ");
            String startLon = lon.substring(0, spaceIndex);
            String endLon = lon.substring(spaceIndex);
            double longitude;
            try
            {
                longitude = Double.parseDouble(startLon);
                longitude += Double.parseDouble(endLon) / 60D;
                longitude *= flip;
                if(longitude < -180D)
                    longitude = -180D;
                else
                if(longitude > 180D)
                    longitude = 180D;
            }
            catch(NumberFormatException nfe)
            {
                longitude = (1.0D / 0.0D);
            }
            return longitude;
        }
        matcher = lonPattern4.matcher(lon);
        if(matcher.matches())
        {
            int flip = lon.endsWith("w") ? -1 : 1;
            lon = lon.substring(0, lon.length() - 1).trim();
            int spaceIndex1 = lon.indexOf(" ");
            int spaceIndex2 = lon.lastIndexOf(" ");
            String startLon = lon.substring(0, spaceIndex1);
            String midLon = lon.substring(spaceIndex1, spaceIndex2);
            String endLon = lon.substring(spaceIndex2);
            double longitude;
            try
            {
                longitude = Double.parseDouble(startLon);
                longitude += Double.parseDouble(midLon) / 60D;
                longitude += Double.parseDouble(endLon) / 3600D;
                longitude *= flip;
                if(longitude < -180D)
                    longitude = -180D;
                else
                if(longitude > 180D)
                    longitude = 180D;
            }
            catch(NumberFormatException nfe)
            {
                longitude = (1.0D / 0.0D);
            }
            return longitude;
        }
        double longitude;
        try
        {
            longitude = Double.parseDouble(lon);
            if(longitude < -180D)
                longitude = -180D;
            else
            if(longitude > 180D)
                longitude = 180D;
        }
        catch(NumberFormatException nfe)
        {
            longitude = (1.0D / 0.0D);
        }
        return longitude;
    }

    private Pattern latPattern1;
    private Pattern lonPattern1;
    private Pattern latPattern2;
    private Pattern lonPattern2;
    private Pattern latPattern3;
    private Pattern lonPattern3;
    private Pattern latPattern4;
    private Pattern lonPattern4;
    private static ParseEngine engine = null;

}
