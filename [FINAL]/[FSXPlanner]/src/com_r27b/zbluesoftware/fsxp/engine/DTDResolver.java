// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTDResolver.java

package com.zbluesoftware.fsxp.engine;

import java.io.File;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class DTDResolver implements EntityResolver
{

    private DTDResolver()
    {
        baseDTD = "file:///"+(new StringBuilder()).append(System.getProperty("user.dir")).append(File.separator).append("dtd").append(File.separator).toString();
    }

    public static DTDResolver getInstance()
    {
        if(resolver == null)
            resolver = new DTDResolver();
        return resolver;
    }

    public InputSource resolveEntity(String publicID, String systemID)
    {
        if(systemID.endsWith("recent-files.dtd"))
            return new InputSource((new StringBuilder()).append(baseDTD).append("recent-files.dtd").toString());
        if(systemID.endsWith("background-images.dtd"))
            return new InputSource((new StringBuilder()).append(baseDTD).append("background-images.dtd").toString());
        if(systemID.endsWith("updates.dtd"))
            return new InputSource((new StringBuilder()).append(baseDTD).append("updates.dtd").toString());
        if(systemID.endsWith("update-item.dtd"))
            return new InputSource((new StringBuilder()).append(baseDTD).append("update-item.dtd").toString());
        else
            return null;
    }

    private String baseDTD;
    private static DTDResolver resolver = null;
}
