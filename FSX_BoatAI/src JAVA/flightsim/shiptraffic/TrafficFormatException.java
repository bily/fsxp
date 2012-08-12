// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrafficFormatException.java

package flightsim.shiptraffic;

import java.io.File;
import java.text.MessageFormat;

// Referenced classes of package flightsim.shiptraffic:
//            BoatRecord, I18N, BoatRoute, PlanRecord

public class TrafficFormatException extends Exception
{

    public TrafficFormatException(String cause)
    {
        message = cause;
    }

    public TrafficFormatException(Object o, String cause)
    {
        if(o == null)
        {
            message = cause;
            return;
        }
        if(o instanceof BoatRecord)
        {
            BoatRecord br = (BoatRecord)o;
            message = MessageFormat.format(I18N._("TrafficFormatException.ErrForBoat"), new Object[] {
                Integer.valueOf(br.getIdentifier()), br.getName(), cause
            });
        } else
        if(o instanceof BoatRoute)
        {
            BoatRoute br = (BoatRoute)o;
            message = MessageFormat.format(I18N._("TrafficFormatException.ErrForRoute"), new Object[] {
                Integer.valueOf(br.getRouteId()), cause
            });
        } else
        if(o instanceof PlanRecord)
        {
            PlanRecord pl = (PlanRecord)o;
            message = MessageFormat.format(I18N._("TrafficFormatException.ErrForPlan"), new Object[] {
                pl.getName(), cause
            });
        } else
        {
            message = cause;
        }
    }

    public TrafficFormatException(File f, int line, String cause)
    {
        message = MessageFormat.format(I18N._("TrafficFormatException.ErrAtLine"), new Object[] {
            Integer.valueOf(line), f.getAbsolutePath(), cause
        });
    }

    public String getMessage()
    {
        return message;
    }

    public String getLocalizedMessage()
    {
        return getMessage();
    }

    private String message;
}
