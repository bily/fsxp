// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorsEngine.java

package com.zbluesoftware.fsxp.engine;

import java.awt.Color;
import java.util.HashMap;
import java.util.prefs.Preferences;

public class ColorsEngine
{

    private ColorsEngine()
    {
        colorHM = new HashMap();
        colorHM.put("ASPHALT", new Color(89, 93, 94));
        colorHM.put("BITUMINOUS", new Color(71, 68, 61));
        colorHM.put("BRICK", new Color(116, 66, 41));
        colorHM.put("CLAY", new Color(191, 113, 65));
        colorHM.put("CEMENT", new Color(120, 136, 136));
        colorHM.put("CONCRETE", new Color(135, 135, 127));
        colorHM.put("CORAL", new Color(178, 168, 159));
        colorHM.put("DIRT", new Color(185, 161, 135));
        colorHM.put("GRASS", new Color(101, 174, 0));
        colorHM.put("GRAVEL", new Color(152, 153, 147));
        colorHM.put("ICE", new Color(176, 218, 255));
        colorHM.put("MACADAM", new Color(111, 113, 108));
        colorHM.put("OIL_TREATED, PLANKS", new Color(170, 111, 95));
        colorHM.put("SAND", new Color(179, 157, 116));
        colorHM.put("SHALE", new Color(147, 149, 138));
        colorHM.put("SNOW", new Color(221, 225, 224));
        colorHM.put("STEEL_MATS", new Color(149, 145, 146));
        colorHM.put("TARMAC", new Color(180, 179, 177));
        colorHM.put("UNKNOWN", new Color(128, 128, 128));
        colorHM.put("WATER", new Color(100, 143, 196));
        latLonColor = Color.lightGray;
        backgroundColor = Color.white;
        nightColor = Color.black;
        taxiwayLineColor = Color.yellow;
        ilsColor = Color.green;
        dmeColor = Color.green;
        glideSlopeColor = Color.blue;
        selectedColor = new Color(210, 171, 13);
        triggerColor = new Color(255, 255, 0);
        exclusionColor = new Color(255, 0, 0);
        sceneryColor = new Color(0, 0, 0);
        readPreferences();
    }

    public static ColorsEngine getInstance()
    {
        if(engine == null)
            engine = new ColorsEngine();
        return engine;
    }

    public Color getSurfaceColor(String surface)
    {
        if(colorHM.containsKey(surface))
            return (Color)colorHM.get(surface);
        else
            return Color.gray;
    }

    public void setSurfaceColor(String surface, Color color)
    {
        colorHM.put(surface, color);
    }

    public Color getLatLonColor()
    {
        return latLonColor;
    }

    public void setLatLonColor(Color latLonColor)
    {
        this.latLonColor = latLonColor;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public Color getNightColor()
    {
        return nightColor;
    }

    public void setNightColor(Color nightColor)
    {
        this.nightColor = nightColor;
    }

    public Color getTaxiwayLineColor()
    {
        return taxiwayLineColor;
    }

    public void setTaxiwayLineColor(Color taxiwayLineColor)
    {
        this.taxiwayLineColor = taxiwayLineColor;
    }

    public Color getILSColor()
    {
        return ilsColor;
    }

    public void setILSColor(Color ilsColor)
    {
        this.ilsColor = ilsColor;
    }

    public Color getDMEColor()
    {
        return dmeColor;
    }

    public void setDMEColor(Color dmeColor)
    {
        this.dmeColor = dmeColor;
    }

    public Color getGlideSlopeColor()
    {
        return glideSlopeColor;
    }

    public void setGlideSlopeColor(Color glideSlopeColor)
    {
        this.glideSlopeColor = glideSlopeColor;
    }

    public Color getSelectedColor()
    {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor)
    {
        this.selectedColor = selectedColor;
    }

    public Color getTriggerColor()
    {
        return triggerColor;
    }

    public void setTriggerColor(Color triggerColor)
    {
        this.triggerColor = triggerColor;
    }

    public Color getExclusionColor()
    {
        return exclusionColor;
    }

    public void setExclusionColor(Color exclusionColor)
    {
        this.exclusionColor = exclusionColor;
    }

    public Color getSceneryColor()
    {
        return sceneryColor;
    }

    public void setSceneryColor(Color sceneryColor)
    {
        this.sceneryColor = sceneryColor;
    }

    public void writePreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        userPrefs.putInt("ASPHALT", ((Color)colorHM.get("ASPHALT")).getRGB());
        userPrefs.putInt("BITUMINOUS", ((Color)colorHM.get("BITUMINOUS")).getRGB());
        userPrefs.putInt("BRICK", ((Color)colorHM.get("BRICK")).getRGB());
        userPrefs.putInt("CLAY", ((Color)colorHM.get("CLAY")).getRGB());
        userPrefs.putInt("CEMENT", ((Color)colorHM.get("CEMENT")).getRGB());
        userPrefs.putInt("CONCRETE", ((Color)colorHM.get("CONCRETE")).getRGB());
        userPrefs.putInt("CORAL", ((Color)colorHM.get("CORAL")).getRGB());
        userPrefs.putInt("DIRT", ((Color)colorHM.get("DIRT")).getRGB());
        userPrefs.putInt("GRASS", ((Color)colorHM.get("GRASS")).getRGB());
        userPrefs.putInt("GRAVEL", ((Color)colorHM.get("GRAVEL")).getRGB());
        userPrefs.putInt("ICE", ((Color)colorHM.get("ICE")).getRGB());
        userPrefs.putInt("MACADAM", ((Color)colorHM.get("MACADAM")).getRGB());
        userPrefs.putInt("OIL_TREATED, PLANKS", ((Color)colorHM.get("OIL_TREATED, PLANKS")).getRGB());
        userPrefs.putInt("SAND", ((Color)colorHM.get("SAND")).getRGB());
        userPrefs.putInt("SHALE", ((Color)colorHM.get("SHALE")).getRGB());
        userPrefs.putInt("SNOW", ((Color)colorHM.get("SNOW")).getRGB());
        userPrefs.putInt("STEEL_MATS", ((Color)colorHM.get("STEEL_MATS")).getRGB());
        userPrefs.putInt("TARMAC", ((Color)colorHM.get("TARMAC")).getRGB());
        userPrefs.putInt("UNKNOWN", ((Color)colorHM.get("UNKNOWN")).getRGB());
        userPrefs.putInt("WATER", ((Color)colorHM.get("WATER")).getRGB());
        userPrefs.putInt("latLonColor", latLonColor.getRGB());
        userPrefs.putInt("backgroundColor", backgroundColor.getRGB());
        userPrefs.putInt("nightColor", nightColor.getRGB());
        userPrefs.putInt("taxiwayLineColor", taxiwayLineColor.getRGB());
        userPrefs.putInt("ilsColor", ilsColor.getRGB());
        userPrefs.putInt("dmeColor", dmeColor.getRGB());
        userPrefs.putInt("glideSlopeColor", glideSlopeColor.getRGB());
        userPrefs.putInt("selectedColor", selectedColor.getRGB());
        userPrefs.putInt("triggerColor", triggerColor.getRGB());
        userPrefs.putInt("exclusionColor", exclusionColor.getRGB());
        userPrefs.putInt("sceneryColor", sceneryColor.getRGB());
    }

    private void readPreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage(getClass());
        colorHM.put("ASPHALT", new Color(userPrefs.getInt("ASPHALT", ((Color)colorHM.get("ASPHALT")).getRGB())));
        colorHM.put("BITUMINOUS", new Color(userPrefs.getInt("BITUMINOUS", ((Color)colorHM.get("BITUMINOUS")).getRGB())));
        colorHM.put("BRICK", new Color(userPrefs.getInt("BRICK", ((Color)colorHM.get("BRICK")).getRGB())));
        colorHM.put("CLAY", new Color(userPrefs.getInt("CLAY", ((Color)colorHM.get("CLAY")).getRGB())));
        colorHM.put("CEMENT", new Color(userPrefs.getInt("CEMENT", ((Color)colorHM.get("CEMENT")).getRGB())));
        colorHM.put("CONCRETE", new Color(userPrefs.getInt("CONCRETE", ((Color)colorHM.get("CONCRETE")).getRGB())));
        colorHM.put("CORAL", new Color(userPrefs.getInt("CORAL", ((Color)colorHM.get("CORAL")).getRGB())));
        colorHM.put("DIRT", new Color(userPrefs.getInt("DIRT", ((Color)colorHM.get("DIRT")).getRGB())));
        colorHM.put("GRASS", new Color(userPrefs.getInt("GRASS", ((Color)colorHM.get("GRASS")).getRGB())));
        colorHM.put("GRAVEL", new Color(userPrefs.getInt("GRAVEL", ((Color)colorHM.get("GRAVEL")).getRGB())));
        colorHM.put("ICE", new Color(userPrefs.getInt("ICE", ((Color)colorHM.get("ICE")).getRGB())));
        colorHM.put("MACADAM", new Color(userPrefs.getInt("MACADAM", ((Color)colorHM.get("MACADAM")).getRGB())));
        colorHM.put("OIL_TREATED, PLANKS", new Color(userPrefs.getInt("OIL_TREATED, PLANKS", ((Color)colorHM.get("OIL_TREATED, PLANKS")).getRGB())));
        colorHM.put("SAND", new Color(userPrefs.getInt("SAND", ((Color)colorHM.get("SAND")).getRGB())));
        colorHM.put("SHALE", new Color(userPrefs.getInt("SHALE", ((Color)colorHM.get("SHALE")).getRGB())));
        colorHM.put("SNOW", new Color(userPrefs.getInt("SNOW", ((Color)colorHM.get("SNOW")).getRGB())));
        colorHM.put("STEEL_MATS", new Color(userPrefs.getInt("STEEL_MATS", ((Color)colorHM.get("STEEL_MATS")).getRGB())));
        colorHM.put("TARMAC", new Color(userPrefs.getInt("TARMAC", ((Color)colorHM.get("TARMAC")).getRGB())));
        colorHM.put("UNKNOWN", new Color(userPrefs.getInt("UNKNOWN", ((Color)colorHM.get("UNKNOWN")).getRGB())));
        colorHM.put("WATER", new Color(userPrefs.getInt("WATER", ((Color)colorHM.get("WATER")).getRGB())));
        latLonColor = new Color(userPrefs.getInt("latLonColor", latLonColor.getRGB()));
        backgroundColor = new Color(userPrefs.getInt("backgroundColor", backgroundColor.getRGB()));
        nightColor = new Color(userPrefs.getInt("nightColor", nightColor.getRGB()));
        taxiwayLineColor = new Color(userPrefs.getInt("taxiwayLineColor", taxiwayLineColor.getRGB()));
        ilsColor = new Color(userPrefs.getInt("ilsColor", ilsColor.getRGB()));
        dmeColor = new Color(userPrefs.getInt("dmeColor", dmeColor.getRGB()));
        glideSlopeColor = new Color(userPrefs.getInt("glideSlopeColor", glideSlopeColor.getRGB()));
        selectedColor = new Color(userPrefs.getInt("selectedColor", selectedColor.getRGB()));
        triggerColor = new Color(userPrefs.getInt("triggerColor", triggerColor.getRGB()));
        exclusionColor = new Color(userPrefs.getInt("exclusionColor", exclusionColor.getRGB()));
        sceneryColor = new Color(userPrefs.getInt("sceneryColor", sceneryColor.getRGB()));
    }

    private HashMap colorHM;
    private Color latLonColor;
    private Color backgroundColor;
    private Color nightColor;
    private Color taxiwayLineColor;
    private Color ilsColor;
    private Color dmeColor;
    private Color glideSlopeColor;
    private Color selectedColor;
    private Color triggerColor;
    private Color exclusionColor;
    private Color sceneryColor;
    private static ColorsEngine engine = null;

}
