// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LegTypeEngine.java

package com.zbluesoftware.fsxp.engine;

import java.util.HashMap;

public class LegTypeEngine
{

    private LegTypeEngine()
    {
        fixTypeHM = new HashMap();
        fixRegionHM = new HashMap();
        fixIdentHM = new HashMap();
        flyOverHM = new HashMap();
        turnDirectionHM = new HashMap();
        recommendedTypeHM = new HashMap();
        recommendedRegionHM = new HashMap();
        recommendedIdentHM = new HashMap();
        thetaHM = new HashMap();
        rhoHM = new HashMap();
        trueCourseHM = new HashMap();
        magneticCourseHM = new HashMap();
        distanceHM = new HashMap();
        timeHM = new HashMap();
        altitudeDescriptorHM = new HashMap();
        altitude1HM = new HashMap();
        altitude2HM = new HashMap();
        fixTypeHM.put("AF", "R");
        fixTypeHM.put("CF", "R");
        fixTypeHM.put("DF", "R");
        fixTypeHM.put("FA", "R");
        fixTypeHM.put("FC", "R");
        fixTypeHM.put("FD", "R");
        fixTypeHM.put("FM", "R");
        fixTypeHM.put("HA", "R");
        fixTypeHM.put("HF", "R");
        fixTypeHM.put("HM", "R");
        fixTypeHM.put("IF", "R");
        fixTypeHM.put("PI", "R");
        fixTypeHM.put("RF", "R");
        fixTypeHM.put("TF", "R");
        fixTypeHM.put("VM", "O");
        fixRegionHM.put("AF", "R");
        fixRegionHM.put("CF", "R");
        fixRegionHM.put("DF", "R");
        fixRegionHM.put("FA", "R");
        fixRegionHM.put("FC", "R");
        fixRegionHM.put("FD", "R");
        fixRegionHM.put("FM", "R");
        fixRegionHM.put("HA", "R");
        fixRegionHM.put("HF", "R");
        fixRegionHM.put("HM", "R");
        fixRegionHM.put("IF", "R");
        fixRegionHM.put("PI", "R");
        fixRegionHM.put("RF", "R");
        fixRegionHM.put("TF", "R");
        fixRegionHM.put("VM", "O");
        fixIdentHM.put("AF", "R");
        fixIdentHM.put("CF", "R");
        fixIdentHM.put("DF", "R");
        fixIdentHM.put("FA", "R");
        fixIdentHM.put("FC", "R");
        fixIdentHM.put("FD", "R");
        fixIdentHM.put("FM", "R");
        fixIdentHM.put("HA", "R");
        fixIdentHM.put("HF", "R");
        fixIdentHM.put("HM", "R");
        fixIdentHM.put("IF", "R");
        fixIdentHM.put("PI", "R");
        fixIdentHM.put("RF", "R");
        fixIdentHM.put("TF", "R");
        fixIdentHM.put("VM", "O");
        flyOverHM.put("AF", "O");
        flyOverHM.put("CF", "R");
        flyOverHM.put("CI", "O");
        flyOverHM.put("CR", "O");
        flyOverHM.put("DF", "R");
        flyOverHM.put("FC", "R");
        flyOverHM.put("FD", "O");
        flyOverHM.put("RF", "O");
        flyOverHM.put("TR", "O");
        flyOverHM.put("VI", "O");
        flyOverHM.put("VR", "O");
        turnDirectionHM.put("AF", "R");
        turnDirectionHM.put("CA", "O");
        turnDirectionHM.put("CD", "O");
        turnDirectionHM.put("CF", "O");
        turnDirectionHM.put("CI", "O");
        turnDirectionHM.put("CR", "O");
        turnDirectionHM.put("DF", "O");
        turnDirectionHM.put("FA", "O");
        turnDirectionHM.put("FC", "O");
        turnDirectionHM.put("FD", "O");
        turnDirectionHM.put("FM", "O");
        turnDirectionHM.put("HA", "O");
        turnDirectionHM.put("HF", "O");
        turnDirectionHM.put("HM", "O");
        turnDirectionHM.put("PI", "R");
        turnDirectionHM.put("RF", "R");
        turnDirectionHM.put("TF", "O");
        turnDirectionHM.put("VA", "O");
        turnDirectionHM.put("VD", "O");
        turnDirectionHM.put("VI", "O");
        turnDirectionHM.put("VM", "O");
        turnDirectionHM.put("VR", "O");
        recommendedTypeHM.put("AF", "R");
        recommendedTypeHM.put("CD", "R");
        recommendedTypeHM.put("CF", "R");
        recommendedTypeHM.put("CI", "O");
        recommendedTypeHM.put("CR", "R");
        recommendedTypeHM.put("DF", "O");
        recommendedTypeHM.put("FA", "R");
        recommendedTypeHM.put("FC", "R");
        recommendedTypeHM.put("FD", "R");
        recommendedTypeHM.put("FM", "R");
        recommendedTypeHM.put("HA", "O");
        recommendedTypeHM.put("HF", "O");
        recommendedTypeHM.put("HM", "O");
        recommendedTypeHM.put("IF", "O");
        recommendedTypeHM.put("PI", "R");
        recommendedTypeHM.put("RF", "O");
        recommendedTypeHM.put("TF", "O");
        recommendedTypeHM.put("VD", "R");
        recommendedTypeHM.put("VI", "O");
        recommendedTypeHM.put("VR", "R");
        recommendedRegionHM.put("AF", "R");
        recommendedRegionHM.put("CD", "R");
        recommendedRegionHM.put("CF", "R");
        recommendedRegionHM.put("CI", "O");
        recommendedRegionHM.put("CR", "R");
        recommendedRegionHM.put("DF", "O");
        recommendedRegionHM.put("FA", "R");
        recommendedRegionHM.put("FC", "R");
        recommendedRegionHM.put("FD", "R");
        recommendedRegionHM.put("FM", "R");
        recommendedRegionHM.put("HA", "O");
        recommendedRegionHM.put("HF", "O");
        recommendedRegionHM.put("HM", "O");
        recommendedRegionHM.put("IF", "O");
        recommendedRegionHM.put("PI", "R");
        recommendedRegionHM.put("RF", "O");
        recommendedRegionHM.put("TF", "O");
        recommendedRegionHM.put("VD", "R");
        recommendedRegionHM.put("VI", "O");
        recommendedRegionHM.put("VR", "R");
        recommendedIdentHM.put("AF", "R");
        recommendedIdentHM.put("CD", "R");
        recommendedIdentHM.put("CF", "R");
        recommendedIdentHM.put("CI", "O");
        recommendedIdentHM.put("CR", "R");
        recommendedIdentHM.put("DF", "O");
        recommendedIdentHM.put("FA", "R");
        recommendedIdentHM.put("FC", "R");
        recommendedIdentHM.put("FD", "R");
        recommendedIdentHM.put("FM", "R");
        recommendedIdentHM.put("HA", "O");
        recommendedIdentHM.put("HF", "O");
        recommendedIdentHM.put("HM", "O");
        recommendedIdentHM.put("IF", "O");
        recommendedIdentHM.put("PI", "R");
        recommendedIdentHM.put("RF", "O");
        recommendedIdentHM.put("TF", "O");
        recommendedIdentHM.put("VD", "R");
        recommendedIdentHM.put("VI", "O");
        recommendedIdentHM.put("VR", "R");
        thetaHM.put("AF", "R");
        thetaHM.put("CF", "R");
        thetaHM.put("DF", "O");
        thetaHM.put("FA", "R");
        thetaHM.put("FC", "R");
        thetaHM.put("FD", "R");
        thetaHM.put("FM", "R");
        thetaHM.put("HA", "O");
        thetaHM.put("HF", "O");
        thetaHM.put("HM", "O");
        thetaHM.put("IF", "O");
        thetaHM.put("PI", "R");
        thetaHM.put("RF", "O");
        thetaHM.put("TF", "O");
        rhoHM.put("AF", "R");
        rhoHM.put("CF", "R");
        rhoHM.put("DF", "O");
        rhoHM.put("FA", "R");
        rhoHM.put("FC", "R");
        rhoHM.put("FD", "R");
        rhoHM.put("FM", "R");
        rhoHM.put("HA", "O");
        rhoHM.put("HF", "O");
        rhoHM.put("HM", "O");
        rhoHM.put("IF", "O");
        rhoHM.put("PI", "R");
        rhoHM.put("TF", "O");
        rhoHM.put("VR", "R");
        trueCourseHM.put("AF", "R");
        trueCourseHM.put("CA", "R");
        trueCourseHM.put("CD", "R");
        trueCourseHM.put("CF", "R");
        trueCourseHM.put("CI", "R");
        trueCourseHM.put("CR", "R");
        trueCourseHM.put("FA", "R");
        trueCourseHM.put("FC", "R");
        trueCourseHM.put("FD", "R");
        trueCourseHM.put("FM", "R");
        trueCourseHM.put("HA", "R");
        trueCourseHM.put("HF", "R");
        trueCourseHM.put("HM", "R");
        trueCourseHM.put("PI", "R");
        trueCourseHM.put("RF", "R");
        trueCourseHM.put("TF", "R");
        trueCourseHM.put("VA", "O");
        trueCourseHM.put("VD", "R");
        trueCourseHM.put("VI", "R");
        trueCourseHM.put("VM", "R");
        trueCourseHM.put("VR", "R");
        magneticCourseHM.put("AF", "R");
        magneticCourseHM.put("CA", "R");
        magneticCourseHM.put("CD", "R");
        magneticCourseHM.put("CF", "R");
        magneticCourseHM.put("CI", "R");
        magneticCourseHM.put("CR", "R");
        magneticCourseHM.put("FA", "R");
        magneticCourseHM.put("FC", "R");
        magneticCourseHM.put("FD", "R");
        magneticCourseHM.put("FM", "R");
        magneticCourseHM.put("HA", "R");
        magneticCourseHM.put("HF", "R");
        magneticCourseHM.put("HM", "R");
        magneticCourseHM.put("PI", "R");
        magneticCourseHM.put("RF", "R");
        magneticCourseHM.put("TF", "R");
        magneticCourseHM.put("VA", "O");
        magneticCourseHM.put("VD", "R");
        magneticCourseHM.put("VI", "R");
        magneticCourseHM.put("VM", "R");
        magneticCourseHM.put("VR", "R");
        distanceHM.put("CD", "R");
        distanceHM.put("CF", "R");
        distanceHM.put("CI", "R");
        distanceHM.put("CR", "R");
        distanceHM.put("FC", "R");
        distanceHM.put("FD", "R");
        distanceHM.put("HA", "R");
        distanceHM.put("HF", "R");
        distanceHM.put("HM", "R");
        distanceHM.put("PI", "R");
        distanceHM.put("RF", "R");
        distanceHM.put("TF", "O");
        distanceHM.put("VD", "O");
        timeHM.put("HA", "R");
        timeHM.put("HF", "R");
        timeHM.put("HM", "R");
        timeHM.put("PI", "R");
        timeHM.put("TF", "R");
        timeHM.put("VD", "O");
        altitudeDescriptorHM.put("AF", "O");
        altitudeDescriptorHM.put("CA", "R");
        altitudeDescriptorHM.put("CD", "O");
        altitudeDescriptorHM.put("CF", "O");
        altitudeDescriptorHM.put("CI", "O");
        altitudeDescriptorHM.put("CR", "O");
        altitudeDescriptorHM.put("DF", "O");
        altitudeDescriptorHM.put("FA", "O");
        altitudeDescriptorHM.put("FC", "O");
        altitudeDescriptorHM.put("FD", "O");
        altitudeDescriptorHM.put("FM", "O");
        altitudeDescriptorHM.put("HA", "R");
        altitudeDescriptorHM.put("HF", "O");
        altitudeDescriptorHM.put("HM", "O");
        altitudeDescriptorHM.put("IF", "O");
        altitudeDescriptorHM.put("PI", "R");
        altitudeDescriptorHM.put("RF", "O");
        altitudeDescriptorHM.put("TF", "O");
        altitudeDescriptorHM.put("VA", "R");
        altitudeDescriptorHM.put("VD", "O");
        altitudeDescriptorHM.put("VI", "O");
        altitudeDescriptorHM.put("VM", "O");
        altitudeDescriptorHM.put("VR", "O");
        altitude1HM.put("AF", "O");
        altitude1HM.put("CA", "R");
        altitude1HM.put("CD", "O");
        altitude1HM.put("CF", "O");
        altitude1HM.put("CI", "O");
        altitude1HM.put("CR", "O");
        altitude1HM.put("DF", "O");
        altitude1HM.put("FA", "O");
        altitude1HM.put("FC", "O");
        altitude1HM.put("FD", "O");
        altitude1HM.put("FM", "O");
        altitude1HM.put("HA", "R");
        altitude1HM.put("HF", "O");
        altitude1HM.put("HM", "O");
        altitude1HM.put("IF", "O");
        altitude1HM.put("PI", "R");
        altitude1HM.put("RF", "O");
        altitude1HM.put("TF", "O");
        altitude1HM.put("VA", "R");
        altitude1HM.put("VD", "O");
        altitude1HM.put("VI", "O");
        altitude1HM.put("VM", "O");
        altitude1HM.put("VR", "O");
        altitude2HM.put("AF", "O");
        altitude2HM.put("CD", "O");
        altitude2HM.put("CF", "O");
        altitude2HM.put("CI", "O");
        altitude2HM.put("CR", "O");
        altitude2HM.put("DF", "O");
        altitude2HM.put("FC", "O");
        altitude2HM.put("FD", "O");
        altitude2HM.put("IF", "O");
        altitude2HM.put("RF", "O");
        altitude2HM.put("TF", "O");
        altitude2HM.put("VD", "O");
        altitude2HM.put("VI", "O");
        altitude2HM.put("VR", "O");
    }

    public static LegTypeEngine getInstance()
    {
        if(engine == null)
            engine = new LegTypeEngine();
        return engine;
    }

    public HashMap getFixTypeHM()
    {
        return fixTypeHM;
    }

    public HashMap getFixRegionHM()
    {
        return fixRegionHM;
    }

    public HashMap getFixIdentHM()
    {
        return fixIdentHM;
    }

    public HashMap getFlyOverHM()
    {
        return flyOverHM;
    }

    public HashMap getTurnDirectionHM()
    {
        return turnDirectionHM;
    }

    public HashMap getRecommendedTypeHM()
    {
        return recommendedTypeHM;
    }

    public HashMap getRecommendedRegionHM()
    {
        return recommendedRegionHM;
    }

    public HashMap getRecommendedIdentHM()
    {
        return recommendedIdentHM;
    }

    public HashMap getThetaHM()
    {
        return thetaHM;
    }

    public HashMap getRhoHM()
    {
        return rhoHM;
    }

    public HashMap getTrueCourseHM()
    {
        return trueCourseHM;
    }

    public HashMap getMagneticCourseHM()
    {
        return magneticCourseHM;
    }

    public HashMap getDistanceHM()
    {
        return distanceHM;
    }

    public HashMap getTimeHM()
    {
        return timeHM;
    }

    public HashMap getAltitudeDescriptorHM()
    {
        return altitudeDescriptorHM;
    }

    public HashMap getAltitude1HM()
    {
        return altitude1HM;
    }

    public HashMap getAltitude2HM()
    {
        return altitude2HM;
    }

    private HashMap fixTypeHM;
    private HashMap fixRegionHM;
    private HashMap fixIdentHM;
    private HashMap flyOverHM;
    private HashMap turnDirectionHM;
    private HashMap recommendedTypeHM;
    private HashMap recommendedRegionHM;
    private HashMap recommendedIdentHM;
    private HashMap thetaHM;
    private HashMap rhoHM;
    private HashMap trueCourseHM;
    private HashMap magneticCourseHM;
    private HashMap distanceHM;
    private HashMap timeHM;
    private HashMap altitudeDescriptorHM;
    private HashMap altitude1HM;
    private HashMap altitude2HM;
    private static LegTypeEngine engine = null;

}
