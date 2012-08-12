// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachModel.java

package com.zbluesoftware.fsxp.model;

import java.util.ArrayList;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            LegModel, TransitionModel

public class ApproachModel
{

    public ApproachModel()
    {
        approachAL = new ArrayList();
        missedAL = new ArrayList();
        transitionAL = new ArrayList();
        type = "GPS";
        runway = "";
        designator = "";
        fixType = "NDB";
        fixRegion = "";
        fixIdent = "";
        suffix = "";
        altitude = 1000F;
        heading = 0.0F;
        missedAltitude = 1000F;
        gpsOverlay = false;
        altitudeMeasure = "M";
        missedAltitudeMeasure = "M";
    }

    public ArrayList getApproachAL()
    {
        return approachAL;
    }

    public void addApproachLegModel(LegModel legModel)
    {
        if(!approachAL.contains(legModel))
            approachAL.add(legModel);
    }

    public void removeApproachLegModel(LegModel legModel)
    {
        approachAL.remove(legModel);
    }

    public ArrayList getMissedAL()
    {
        return missedAL;
    }

    public void addMissedLegModel(LegModel legModel)
    {
        if(!missedAL.contains(legModel))
            missedAL.add(legModel);
    }

    public void removeMissedLegModel(LegModel legModel)
    {
        missedAL.remove(legModel);
    }

    public ArrayList getTransitionAL()
    {
        return transitionAL;
    }

    public void addTransitionModel(TransitionModel transitionModel)
    {
        if(!transitionAL.contains(transitionModel))
            transitionAL.add(transitionModel);
    }

    public void removeTransitionModel(TransitionModel transitionModel)
    {
        transitionAL.remove(transitionModel);
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getRunway()
    {
        return runway;
    }

    public void setRunway(String runway)
    {
        this.runway = runway;
    }

    public String getDesignator()
    {
        return designator;
    }

    public void setDesignator(String designator)
    {
        this.designator = designator;
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

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public float getAltitude()
    {
        return altitude;
    }

    public void setAltitude(float altitude)
    {
        this.altitude = altitude;
    }

    public float getHeading()
    {
        return heading;
    }

    public void setHeading(float heading)
    {
        this.heading = heading;
    }

    public float getMissedAltitude()
    {
        return missedAltitude;
    }

    public void setMissedAltitude(float missedAltitude)
    {
        this.missedAltitude = missedAltitude;
    }

    public boolean getGPSOverlay()
    {
        return gpsOverlay;
    }

    public void setGPSOverlay(boolean gpsOverlay)
    {
        this.gpsOverlay = gpsOverlay;
    }

    public String getAltitudeMeasure()
    {
        return altitudeMeasure;
    }

    public void setAltitudeMeasure(String altitudeMeasure)
    {
        this.altitudeMeasure = altitudeMeasure;
    }

    public String getMissedAltitudeMeasure()
    {
        return missedAltitudeMeasure;
    }

    public void setMissedAltitudeMeasure(String missedAltitudeMeasure)
    {
        this.missedAltitudeMeasure = missedAltitudeMeasure;
    }

    private ArrayList approachAL;
    private ArrayList missedAL;
    private ArrayList transitionAL;
    private String type;
    private String runway;
    private String designator;
    private String fixType;
    private String fixRegion;
    private String fixIdent;
    private String suffix;
    private String altitudeMeasure;
    private String missedAltitudeMeasure;
    private float altitude;
    private float heading;
    private float missedAltitude;
    private boolean gpsOverlay;
}
