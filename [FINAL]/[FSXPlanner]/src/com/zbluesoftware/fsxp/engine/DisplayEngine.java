// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DisplayEngine.java

package com.zbluesoftware.fsxp.engine;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Locale;

// Referenced classes of package com.zbluesoftware.fsxp.engine:
//            SettingsEngine

public class DisplayEngine
    implements PropertyChangeListener
{

    private DisplayEngine()
    {
        latLonDisplay = SettingsEngine.getInstance().getLatLonDisplay();
        latLonGridDisplay = SettingsEngine.getInstance().getLatLonGridDisplay();
        latLonObjectDisplay = SettingsEngine.getInstance().getLatLonObjectDisplay();
        decimalFormat = NumberFormat.getInstance(Locale.US);
        decimalFormat.setMinimumFractionDigits(10);
        decimalFormat.setMaximumFractionDigits(10);
        decimalGridFormat = NumberFormat.getInstance(Locale.US);
        decimalGridFormat.setMinimumFractionDigits(6);
        decimalGridFormat.setMaximumFractionDigits(6);
        degreeFormat = NumberFormat.getInstance(Locale.US);
        degreeFormat.setMinimumFractionDigits(2);
        degreeFormat.setMaximumFractionDigits(2);
        degreeFormat.setMinimumIntegerDigits(2);
        degreeFormat.setMaximumIntegerDigits(2);
        minutesFormat = NumberFormat.getInstance(Locale.US);
        minutesFormat.setMinimumFractionDigits(4);
        minutesFormat.setMaximumFractionDigits(4);
        minutesFormat.setMinimumIntegerDigits(2);
        minutesFormat.setMaximumIntegerDigits(2);
        integerFormat = NumberFormat.getInstance(Locale.US);
        integerFormat.setMinimumIntegerDigits(2);
        integerFormat.setMaximumIntegerDigits(2);
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public static DisplayEngine getInstance()
    {
        if(engine == null)
            engine = new DisplayEngine();
        return engine;
    }

    public String formatLongitude(double lon)
    {
        switch(latLonDisplay)
        {
        case 0: // '\0'
            return formatDecimal(lon);

        case 1: // '\001'
            return formatFullDegrees(lon, " W", " E");

        case 2: // '\002'
            return formatDecimalDegrees(lon, " W", " E");

        case 3: // '\003'
            return formatFullDegreesEurope(lon, "W ", "E ");

        case 4: // '\004'
            return formatDecimalDegreesEurope(lon, "W ", "E ");
        }
        return formatDecimal(lon);
    }

    public String formatLatitude(double lat)
    {
        switch(latLonDisplay)
        {
        case 0: // '\0'
            return formatDecimal(lat);

        case 1: // '\001'
            return formatFullDegrees(lat, " S", " N");

        case 2: // '\002'
            return formatDecimalDegrees(lat, " S", " N");

        case 3: // '\003'
            return formatFullDegreesEurope(lat, "S ", "N ");

        case 4: // '\004'
            return formatDecimalDegreesEurope(lat, "S ", "N ");
        }
        return formatDecimal(lat);
    }

    public String formatGridLongitude(double lon)
    {
        switch(latLonGridDisplay)
        {
        case 0: // '\0'
            return formatDecimalGrid(lon);

        case 1: // '\001'
            return formatFullDegreesGrid(lon, "", "");

        case 2: // '\002'
            return formatDecimalDegrees(lon, "", "");
        }
        return formatDecimal(lon);
    }

    public String formatGridLatitude(double lat)
    {
        switch(latLonGridDisplay)
        {
        case 0: // '\0'
            return formatDecimalGrid(lat);

        case 1: // '\001'
            return formatFullDegreesGrid(lat, "", "");

        case 2: // '\002'
            return formatDecimalDegrees(lat, "", "");
        }
        return formatDecimal(lat);
    }

    public String formatObjectLongitude(double lon)
    {
        switch(latLonObjectDisplay)
        {
        case 0: // '\0'
            return formatDecimal(lon);

        case 1: // '\001'
            return formatFullDegreesObject(lon, " W", " E");

        case 2: // '\002'
            return formatDecimalDegreesObject(lon, " W", " E");

        case 3: // '\003'
            return formatFullDegreesEuropeObject(lon, "W ", "E ");

        case 4: // '\004'
            return formatDecimalDegreesEuropeObject(lon, "W ", "E ");
        }
        return formatDecimal(lon);
    }

    public String formatObjectLatitude(double lat)
    {
        switch(latLonObjectDisplay)
        {
        case 0: // '\0'
            return formatDecimal(lat);

        case 1: // '\001'
            return formatFullDegreesObject(lat, " S", " N");

        case 2: // '\002'
            return formatDecimalDegreesObject(lat, " S", " N");

        case 3: // '\003'
            return formatFullDegreesEuropeObject(lat, "S ", "N ");

        case 4: // '\004'
            return formatDecimalDegreesEuropeObject(lat, "S ", "N ");
        }
        return formatDecimal(lat);
    }

    private String formatDecimal(double point)
    {
        return decimalFormat.format(point);
    }

    private String formatDecimalGrid(double point)
    {
        return decimalGridFormat.format(point);
    }

    private String formatFullDegrees(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        int minutes = (int)((point - (double)degrees) * 60D);
        float seconds = (float)(((point - (double)degrees) * 60D - (double)minutes) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(Math.abs(degrees)).append("\260 ").append(integerFormat.format(Math.abs(minutes))).append("' ").append(degreeFormat.format(Math.abs(seconds))).append("\"").append(lessThan).toString();
        else
            return (new StringBuilder()).append(degrees).append("\260 ").append(integerFormat.format(minutes)).append("' ").append(degreeFormat.format(seconds)).append("\"").append(greaterThan).toString();
    }

    private String formatFullDegreesGrid(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        int minutes = (int)((point - (double)degrees) * 60D);
        float seconds = (float)(((point - (double)degrees) * 60D - (double)minutes) * 60D);
        if(Math.abs(seconds) == 60F)
        {
            seconds = 0.0F;
            if(minutes < 0)
                minutes--;
            else
                minutes++;
        }
        if(point < 0.0D)
            return (new StringBuilder()).append(Math.abs(degrees)).append("\260 ").append(integerFormat.format(Math.abs(minutes))).append("' ").append(integerFormat.format(Math.abs(seconds))).append("\"").append(lessThan).toString();
        else
            return (new StringBuilder()).append(degrees).append("\260 ").append(integerFormat.format(minutes)).append("' ").append(integerFormat.format(seconds)).append("\"").append(greaterThan).toString();
    }

    private String formatFullDegreesObject(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        int minutes = (int)((point - (double)degrees) * 60D);
        float seconds = (float)(((point - (double)degrees) * 60D - (double)minutes) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(Math.abs(degrees)).append(" ").append(integerFormat.format(Math.abs(minutes))).append(" ").append(integerFormat.format(Math.abs(seconds))).append(lessThan).toString();
        else
            return (new StringBuilder()).append(degrees).append(" ").append(integerFormat.format(minutes)).append(" ").append(integerFormat.format(seconds)).append(greaterThan).toString();
    }

    private String formatFullDegreesEurope(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        int minutes = (int)((point - (double)degrees) * 60D);
        float seconds = (float)(((point - (double)degrees) * 60D - (double)minutes) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(lessThan).append(Math.abs(degrees)).append("\260 ").append(integerFormat.format(Math.abs(minutes))).append("' ").append(degreeFormat.format(Math.abs(seconds))).append("\"").toString();
        else
            return (new StringBuilder()).append(greaterThan).append(degrees).append("\260 ").append(integerFormat.format(minutes)).append("' ").append(degreeFormat.format(seconds)).append("\"").toString();
    }

    private String formatFullDegreesEuropeObject(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        int minutes = (int)((point - (double)degrees) * 60D);
        float seconds = (float)(((point - (double)degrees) * 60D - (double)minutes) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(lessThan).append(Math.abs(degrees)).append(" ").append(integerFormat.format(Math.abs(minutes))).append(" ").append(degreeFormat.format(Math.abs(seconds))).toString();
        else
            return (new StringBuilder()).append(greaterThan).append(degrees).append(" ").append(integerFormat.format(minutes)).append(" ").append(degreeFormat.format(seconds)).toString();
    }

    private String formatDecimalDegrees(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        float minutes = (float)((point - (double)degrees) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(Math.abs(degrees)).append("\260 ").append(minutesFormat.format(Math.abs(minutes))).append("'").append(lessThan).toString();
        else
            return (new StringBuilder()).append(degrees).append("\260 ").append(minutesFormat.format(minutes)).append("'").append(greaterThan).toString();
    }

    private String formatDecimalDegreesObject(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        float minutes = (float)((point - (double)degrees) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(Math.abs(degrees)).append(" ").append(minutesFormat.format(Math.abs(minutes))).append(lessThan).toString();
        else
            return (new StringBuilder()).append(degrees).append(" ").append(minutesFormat.format(minutes)).append(greaterThan).toString();
    }

    private String formatDecimalDegreesEurope(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        float minutes = (float)((point - (double)degrees) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(lessThan).append(Math.abs(degrees)).append("\260 ").append(minutesFormat.format(Math.abs(minutes))).append("'").toString();
        else
            return (new StringBuilder()).append(greaterThan).append(degrees).append("\260 ").append(minutesFormat.format(minutes)).append("'").toString();
    }

    private String formatDecimalDegreesEuropeObject(double point, String lessThan, String greaterThan)
    {
        int degrees = (int)point;
        float minutes = (float)((point - (double)degrees) * 60D);
        if(point < 0.0D)
            return (new StringBuilder()).append(lessThan).append(Math.abs(degrees)).append(" ").append(minutesFormat.format(Math.abs(minutes))).toString();
        else
            return (new StringBuilder()).append(greaterThan).append(degrees).append(" ").append(minutesFormat.format(minutes)).toString();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() instanceof SettingsEngine)
            if(event.getPropertyName().equals("latLonDisplay"))
                latLonDisplay = ((Integer)event.getNewValue()).intValue();
            else
            if(event.getPropertyName().equals("latLonGridDisplay"))
                latLonGridDisplay = ((Integer)event.getNewValue()).intValue();
            else
            if(event.getPropertyName().equals("latLonObjectDisplay"))
                latLonObjectDisplay = ((Integer)event.getNewValue()).intValue();
    }

    private NumberFormat decimalFormat;
    private NumberFormat decimalGridFormat;
    private NumberFormat degreeFormat;
    private NumberFormat minutesFormat;
    private NumberFormat integerFormat;
    private int latLonDisplay;
    private int latLonGridDisplay;
    private int latLonObjectDisplay;
    private static DisplayEngine engine = null;

}
