// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   I18N.java

package flightsim.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18N
{

    private I18N()
    {
    }

    public static String _(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch(MissingResourceException e)
        {
            return (new StringBuilder(String.valueOf('!'))).append(key).append('!').toString();
        }
    }

    private static final String BUNDLE_NAME = "flightsim.utils.messages";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("flightsim.utils.messages");

}
