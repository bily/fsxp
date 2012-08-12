// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlaneDimensions.java

package com.zbluesoftware.fsxp.simconnect;

import java.util.HashMap;

public class PlaneDimensions
{

    private PlaneDimensions()
    {
        definePlaneDimensions();
    }

    public static PlaneDimensions getInstance()
    {
        if(planeDimensions == null)
            planeDimensions = new PlaneDimensions();
        return planeDimensions;
    }

    public double getPlaneWidth(String planeType)
    {
        if(planeWidthHM.containsKey(planeType))
            return ((Double)planeWidthHM.get(planeType)).doubleValue();
        else
            return 0.0D;
    }

    public double getPlaneLength(String planeType)
    {
        if(planeLengthHM.containsKey(planeType))
            return ((Double)planeLengthHM.get(planeType)).doubleValue();
        else
            return 0.0D;
    }

    public String getPlaneType(String planeType)
    {
        if(planeTypeHM.containsKey(planeType))
            return (String)planeTypeHM.get(planeType);
        else
            return "Airplane";
    }

    private void definePlaneDimensions()
    {
        planeWidthHM = new HashMap();
        planeLengthHM = new HashMap();
        planeTypeHM = new HashMap();
        planeWidthHM.put("Airbus A321", new Double(112D));
        planeLengthHM.put("Airbus A321", new Double(146D));
        planeTypeHM.put("Airbus A321", "Airplane");
        planeWidthHM.put("AirCreation Trike", new Double(33D));
        planeLengthHM.put("AirCreation Trike", new Double(12D));
        planeTypeHM.put("AirCreation Trike", "Airplane");
        planeWidthHM.put("Beechcraft Baron 58", new Double(38D));
        planeLengthHM.put("Beechcraft Baron 58", new Double(30D));
        planeTypeHM.put("Beechcraft Baron 58", "Airplane");
        planeWidthHM.put("Beechcraft Kingair 350", new Double(58D));
        planeLengthHM.put("Beechcraft Kingair 350", new Double(46D));
        planeTypeHM.put("Beechcraft Kingair 350", "Airplane");
        planeWidthHM.put("Bell Jet Ranger", new Double(33D));
        planeLengthHM.put("Bell Jet Ranger", new Double(31D));
        planeTypeHM.put("Bell Jet Ranger", "Helicopter");
        planeWidthHM.put("Boeing 737", new Double(113D));
        planeLengthHM.put("Boeing 737", new Double(129D));
        planeTypeHM.put("Boeing 737", "Airplane");
        planeWidthHM.put("Boeing 747", new Double(212D));
        planeLengthHM.put("Boeing 747", new Double(232D));
        planeTypeHM.put("Boeing 747", "Airplane");
        planeWidthHM.put("Bombardier CRJ700", new Double(76D));
        planeLengthHM.put("Bombardier CRJ700", new Double(107D));
        planeTypeHM.put("Bombardier CRJ700", "Airplane");
        planeWidthHM.put("Bombardier LearJet", new Double(48D));
        planeLengthHM.put("Bombardier LearJet", new Double(58D));
        planeTypeHM.put("Bombardier LearJet", "Airplane");
        planeWidthHM.put("Cessna 172", new Double(36D));
        planeLengthHM.put("Cessna 172", new Double(26D));
        planeTypeHM.put("Cessna 172", "Airplane");
        planeWidthHM.put("Cessna C208B", new Double(52D));
        planeLengthHM.put("Cessna C208B", new Double(42D));
        planeTypeHM.put("Cessna C208B", "Airplane");
        planeWidthHM.put("de Havilland Beaver", new Double(48D));
        planeLengthHM.put("de Havilland Beaver", new Double(30D));
        planeTypeHM.put("de Havilland Beaver", "Airplane");
        planeWidthHM.put("DG Flugzeubau", new Double(59D));
        planeLengthHM.put("DG Flugzeubau", new Double(22D));
        planeTypeHM.put("DG Flugzeubau", "Airplane");
        planeWidthHM.put("Douglas DC-3", new Double(95D));
        planeLengthHM.put("Douglas DC-3", new Double(65D));
        planeTypeHM.put("Douglas DC-3", "Airplane");
        planeWidthHM.put("Extra 300S", new Double(26D));
        planeLengthHM.put("Extra 300S", new Double(23D));
        planeTypeHM.put("Extra 300S", "Airplane");
        planeWidthHM.put("Grumman Goose", new Double(49D));
        planeLengthHM.put("Grumman Goose", new Double(38D));
        planeTypeHM.put("Grumman Goose", "Airplane");
        planeWidthHM.put("Maule Orion", new Double(33D));
        planeLengthHM.put("Maule Orion", new Double(23D));
        planeTypeHM.put("Maule Orion", "Airplane");
        planeWidthHM.put("Mooney Bravo", new Double(36D));
        planeLengthHM.put("Mooney Bravo", new Double(27D));
        planeTypeHM.put("Mooney Bravo", "Airplane");
        planeWidthHM.put("Piper Cub", new Double(35D));
        planeLengthHM.put("Piper Cub", new Double(22D));
        planeTypeHM.put("Piper Cub", "Airplane");
        planeWidthHM.put("Robinson R22", new Double(25D));
        planeLengthHM.put("Robinson R22", new Double(29D));
        planeTypeHM.put("Robinson R22", "Helicopter");
    }

    private HashMap planeWidthHM;
    private HashMap planeLengthHM;
    private HashMap planeTypeHM;
    private static PlaneDimensions planeDimensions = null;

}
