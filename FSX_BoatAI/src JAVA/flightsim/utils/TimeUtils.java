// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeUtils.java

package flightsim.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package flightsim.utils:
//            I18N

public class TimeUtils
{

    public TimeUtils()
    {
    }

    public static int periodType(int periodHours)
    {
        for(int i = 0; i < PERIODS_HOUR.length; i++)
            if(PERIODS_HOUR[i] == periodHours)
                return i;

        return -1;
    }

    public static String formatTime(double hours, int periodHours, boolean showSeconds)
    {
        double tsec = hours * 3600D;
        StringBuffer sgb = new StringBuffer("");
        int d = (int)Math.floor(tsec / 86400D);
        tsec -= d * 0x15180;
        int h = (int)Math.floor(tsec / 3600D);
        tsec -= h * 3600;
        int m = (int)Math.floor(tsec / 60D);
        tsec -= m * 60;
        int s = (int)tsec;
        if(periodHours > 24)
        {
            sgb.append(d);
            sgb.append("/");
        }
        if(h < 10)
            sgb.append('0');
        sgb.append(h);
        sgb.append(":");
        if(m < 10)
            sgb.append('0');
        sgb.append(m);
        if(showSeconds)
        {
            sgb.append(":");
            if(s < 10)
                sgb.append('0');
            sgb.append(s);
        }
        return sgb.toString();
    }

    public static String formatAITime(int aitime, boolean showSeconds)
    {
        double time = (double)(aitime & 0xffffff) / 16777216D;
        int period = aitime >> 24;
        int periodHours = PERIODS_HOUR[period];
        return formatTime(time * (double)periodHours, periodHours, showSeconds);
    }

    public static int makeAITime(double timeHours, int period)
    {
        int periodHours = PERIODS_HOUR[period];
        return (int)((timeHours / (double)periodHours) * 16777216D) & 0xffffff | period << 24;
    }

    public static double parseTime(String human)
    {
        return parseTime(human, 0);
    }

    public static double parseTime(String human, int period)
    {
        int seconds = 0;
        Matcher m = ADATE_PATTERN.matcher(human);
        if(!m.matches())
            throw new IllegalArgumentException(MessageFormat.format(I18N._("TimeUtils.InvalidDateFormat"), new Object[] {
                human
            }));
        if(m.group(4) != null)
        {
            String day = m.group(4).substring(0, m.group(4).length() - 1);
            seconds += 0x15180 * Integer.parseInt(day);
        }
        seconds += 3600 * Integer.parseInt(m.group(5));
        seconds += 60 * Integer.parseInt(m.group(6));
        if(m.group(7) != null)
            seconds += Integer.parseInt(m.group(7));
        if(period > 0)
        {
            int periodSeconds = period * 3600;
            seconds %= periodSeconds;
            if(seconds < 0)
                seconds = periodSeconds - seconds;
        }
        return (double)seconds / 3600D;
    }

    public static final int PERIODS_HOUR[] = {
        1, 2, 4, 6, 8, 12, 24, 168, 336, 840, 
        1344
    };
    public static final String PERIODS_HOUR_STRING[] = {
        "1Hr", "2Hr", "4Hr", "6Hr", "8Hr", "12Hr", "24Hr", "WEEK", "2WEEK", "5WEEK", 
        "8WEEK"
    };
    private static final Pattern ADATE_PATTERN = Pattern.compile("(@?)(TNG)?(@)?([0-9]+/)?([012]?[0-9]):([0-6]?[0-9]):?([0-6]?[0-9])?");

}
