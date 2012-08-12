// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransitionModel.java

package com.zbluesoftware.fsxp.model;

import java.util.ArrayList;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            DmeArcModel, LegModel

public class TransitionModel
{

    public TransitionModel()
    {
        legAL = new ArrayList();
        dmeArcModel = null;
        transitionType = "FULL";
        fixType = "NDB";
        fixRegion = "";
        fixIdent = "";
        altMeasure = "M";
        alt = 0.0F;
    }

    public ArrayList getLegAL()
    {
        return legAL;
    }

    public void addLegModel(LegModel legModel)
    {
        if(!legAL.contains(legModel))
            legAL.add(legModel);
    }

    public void removeLegModel(LegModel legModel)
    {
        legAL.remove(legModel);
    }

    public DmeArcModel getDmeArcModel()
    {
        return dmeArcModel;
    }

    public void setDmeArcModel(DmeArcModel dmeArcModel)
    {
        this.dmeArcModel = dmeArcModel;
    }

    public String getTransitionType()
    {
        return transitionType;
    }

    public void setTransitionType(String transitionType)
    {
        this.transitionType = transitionType;
    }

    public String getFixType()
    {
        return fixType;
    }

    public void setFixType(String fixType)
    {
        this.fixType = fixType;
    }

    public String getFixRegion()
    {
        return fixRegion;
    }

    public void setFixRegion(String fixRegion)
    {
        this.fixRegion = fixRegion;
    }

    public String getFixIdent()
    {
        return fixIdent;
    }

    public void setFixIdent(String fixIdent)
    {
        this.fixIdent = fixIdent;
    }

    public String getAltMeasure()
    {
        return altMeasure;
    }

    public void setAltMeasure(String altMeasure)
    {
        this.altMeasure = altMeasure;
    }

    public float getAlt()
    {
        return alt;
    }

    public void setAlt(float alt)
    {
        this.alt = alt;
    }

    private ArrayList legAL;
    private DmeArcModel dmeArcModel;
    private String transitionType;
    private String fixType;
    private String fixRegion;
    private String fixIdent;
    private String altMeasure;
    private float alt;
}
