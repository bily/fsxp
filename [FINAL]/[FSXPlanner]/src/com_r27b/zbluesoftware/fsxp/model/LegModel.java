// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegModel.java

package com.zbluesoftware.fsxp.model;


public class LegModel
{

    public LegModel()
    {
        type = "AF";
        fixType = "NDB";
        fixRegion = "";
        fixIdent = "";
        turnDirection = "E";
        recommendedType = "NDB";
        recommendedRegion = "";
        recommendedIdent = "";
        rhoMeasure = "M";
        distanceMeasure = "M";
        altitude1Measure = "M";
        altitude2Measure = "M";
        altitudeDescriptor = " ";
        theta = 0.0F;
        rho = 0.0F;
        trueCourse = -1F;
        magneticCourse = -1F;
        distance = 0.0F;
        time = 0.0F;
        altitude1 = 0.0F;
        altitude2 = 0.0F;
        flyOver = false;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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

    public String getTurnDirection()
    {
        return turnDirection;
    }

    public void setTurnDirection(String turnDirection)
    {
        this.turnDirection = turnDirection;
    }

    public String getRecommendedType()
    {
        return recommendedType;
    }

    public void setRecommendedType(String recommendedType)
    {
        this.recommendedType = recommendedType;
    }

    public String getRecommendedRegion()
    {
        return recommendedRegion;
    }

    public void setRecommendedRegion(String recommendedRegion)
    {
        this.recommendedRegion = recommendedRegion;
    }

    public String getRecommendedIdent()
    {
        return recommendedIdent;
    }

    public void setRecommendedIdent(String recommendedIdent)
    {
        this.recommendedIdent = recommendedIdent;
    }

    public String getRhoMeasure()
    {
        return rhoMeasure;
    }

    public void setRhoMeasure(String rhoMeasure)
    {
        this.rhoMeasure = rhoMeasure;
    }

    public String getDistanceMeasure()
    {
        return distanceMeasure;
    }

    public void setDistanceMeasure(String distanceMeasure)
    {
        this.distanceMeasure = distanceMeasure;
    }

    public String getAltitude1Measure()
    {
        return altitude1Measure;
    }

    public void setAltitude1Measure(String altitude1Meaasure)
    {
        altitude1Measure = altitude1Measure;
    }

    public String getAltitude2Measure()
    {
        return altitude2Measure;
    }

    public void setAltitude2Measure(String altitude2Meaasure)
    {
        altitude2Measure = altitude2Measure;
    }

    public String getAltitudeDescriptor()
    {
        return altitudeDescriptor;
    }

    public void setAltitudeDescriptor(String altitudeDescriptor)
    {
        this.altitudeDescriptor = altitudeDescriptor;
    }

    public float getTheta()
    {
        return theta;
    }

    public void setTheta(float theta)
    {
        this.theta = theta;
    }

    public float getRho()
    {
        return rho;
    }

    public void setRho(float rho)
    {
        this.rho = rho;
    }

    public float getTrueCourse()
    {
        return trueCourse;
    }

    public void setTrueCourse(float trueCourse)
    {
        this.trueCourse = trueCourse;
    }

    public float getMagneticCourse()
    {
        return magneticCourse;
    }

    public void setMagneticCourse(float magneticCourse)
    {
        this.magneticCourse = magneticCourse;
    }

    public float getDistance()
    {
        return distance;
    }

    public void setDistance(float distance)
    {
        this.distance = distance;
    }

    public float getTime()
    {
        return time;
    }

    public void setTime(float time)
    {
        this.time = time;
    }

    public float getAltitude1()
    {
        return altitude1;
    }

    public void setAltitude1(float altitude1)
    {
        this.altitude1 = altitude1;
    }

    public float getAltitude2()
    {
        return altitude2;
    }

    public void setAltitude2(float altitude2)
    {
        this.altitude2 = altitude2;
    }

    public boolean getFlyOver()
    {
        return flyOver;
    }

    public void setFlyOver(boolean flyOver)
    {
        this.flyOver = flyOver;
    }

    private String type;
    private String fixType;
    private String fixRegion;
    private String fixIdent;
    private String turnDirection;
    private String recommendedType;
    private String recommendedRegion;
    private String recommendedIdent;
    private String rhoMeasure;
    private String distanceMeasure;
    private String altitudeDescriptor;
    private String altitude1Measure;
    private String altitude2Measure;
    private float theta;
    private float rho;
    private float trueCourse;
    private float magneticCourse;
    private float distance;
    private float time;
    private float altitude1;
    private float altitude2;
    private boolean flyOver;
}
