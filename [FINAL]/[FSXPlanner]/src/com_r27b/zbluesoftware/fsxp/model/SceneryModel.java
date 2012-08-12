// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SceneryModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.HashMap;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class SceneryModel extends BaseModel
{

    public SceneryModel()
    {
        modelName = "Scenery";
        altMeasure = "M";
        alt = 0.0D;
        latLon = new LatLonPoint();
        imageComplexity = "NORMAL";
        name = "";
        displayName = "";
        altitudeIsAgl = true;
        pitch = 0.0F;
        bank = 0.0F;
        heading = 0.0F;
        sceneryScale = 1.0F;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            float size = 20F * scale;
            float smallSize = 15F * scale;
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            java.awt.geom.Point2D.Float topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size, point.y - size), heading);
            java.awt.geom.Point2D.Float topRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size, point.y - size), heading);
            java.awt.geom.Point2D.Float bottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size, point.y + size), heading);
            java.awt.geom.Point2D.Float bottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size, point.y + size), heading);
            java.awt.geom.Point2D.Float topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - smallSize, point.y - smallSize), heading);
            java.awt.geom.Point2D.Float topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + smallSize, point.y - smallSize), heading);
            java.awt.geom.Point2D.Float bottomLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - smallSize, point.y + smallSize), heading);
            java.awt.geom.Point2D.Float bottomRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + smallSize, point.y + smallSize), heading);
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeft.x, topLeft.y);
            modelPath.lineTo(topRight.x, topRight.y);
            modelPath.lineTo(bottomRight.x, bottomRight.y);
            modelPath.lineTo(bottomLeft.x, bottomLeft.y);
            modelPath.lineTo(topLeft.x, topLeft.y);
            modelPath.moveTo(topLeft2.x, topLeft2.y);
            modelPath.lineTo(topRight2.x, topRight2.y);
            modelPath.lineTo(bottomRight2.x, bottomRight2.y);
            modelPath.lineTo(bottomLeft2.x, bottomLeft2.y);
            modelPath.lineTo(topLeft2.x, topLeft2.y);
            modelPath.lineTo(bottomRight2.x, bottomRight2.y);
            modelPath.moveTo(bottomLeft2.x, bottomLeft2.y);
            modelPath.lineTo(topRight2.x, topRight2.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        Color sceneryColor = ColorsEngine.getInstance().getSceneryColor();
        g2.setPaint(new Color(sceneryColor.getRed(), sceneryColor.getGreen(), sceneryColor.getBlue(), 96));
        g2.fill(modelPath);
        g2.setPaint(ColorsEngine.getInstance().getSceneryColor());
        g2.draw(modelPath);
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(modelPath);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(modelPath);
            }
        int fontSize = (int)((double)((float)Utilities.SCENERY_FONT.getSize() * scale) * 0.5D);
        if(fontSize >= 6)
        {
            float size = 20F * scale;
            g2.setPaint(Color.black);
            g2.setFont(new Font(Utilities.SCENERY_FONT.getFamily(), Utilities.SCENERY_FONT.getStyle(), fontSize));
            int fontHeight = g2.getFontMetrics().getHeight();
            g2.drawString(displayName, (point.x - size) + 2.0F, (point.y - size) + (float)fontHeight);
            g2.drawString(imageComplexity, (point.x - size) + 2.0F, (point.y - size) + (float)(fontHeight * 2));
        }
    }

    public Shape getClip()
    {
        return modelPath;
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getAltMeasure()
    {
        return altMeasure;
    }

    public void setAltMeasure(String altMeasure)
    {
        if(shouldNotify && !this.altMeasure.equals(altMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setAltMeasure", "Altitude Measure", "", this, altMeasure, this.altMeasure));
        firePropertyChange("altMeasure", this.altMeasure, altMeasure);
        this.altMeasure = altMeasure;
    }

    public LatLonPoint getLatLon()
    {
        return latLon;
    }

    public void setLatLon(LatLonPoint latLon)
    {
        if(shouldNotify && !this.latLon.equals(latLon))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLatLon", "Lat/Lon", "", this, latLon, this.latLon));
        firePropertyChange("latLon", this.latLon, latLon);
        this.latLon = latLon;
    }

    public double getAlt()
    {
        return alt;
    }

    public void setAlt(double alt)
    {
        if(shouldNotify && this.alt != alt)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlt", "Altitude", "", this, new Double(alt), new Double(this.alt)));
        firePropertyChange("alt", new Double(this.alt), new Double(alt));
        this.alt = alt;
    }

    public String getImageComplexity()
    {
        return imageComplexity;
    }

    public void setImageComplexity(String imageComplexity)
    {
        if(shouldNotify && !this.imageComplexity.equals(imageComplexity))
            HistoryListModel.getInstance().addModel(new HistoryModel("setImageComplexity", "Image Complexity", "", this, imageComplexity, this.imageComplexity));
        firePropertyChange("imageComplexity", this.imageComplexity, imageComplexity);
        this.imageComplexity = imageComplexity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if(shouldNotify && !this.name.equals(name))
            HistoryListModel.getInstance().addModel(new HistoryModel("setName", "Name", "", this, name, this.name));
        firePropertyChange("name", this.name, name);
        this.name = name;
    }

    public boolean getAltitudeIsAgl()
    {
        return altitudeIsAgl;
    }

    public void setAltitudeIsAgl(boolean altitudeIsAgl)
    {
        if(shouldNotify && this.altitudeIsAgl != altitudeIsAgl)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAltitudeIsAgl", "Altitude Is AGL", "", this, new Boolean(altitudeIsAgl), new Boolean(this.altitudeIsAgl)));
        firePropertyChange("altitudeIsAgl", new Boolean(this.altitudeIsAgl), new Boolean(altitudeIsAgl));
        this.altitudeIsAgl = altitudeIsAgl;
    }

    public float getPitch()
    {
        return pitch;
    }

    public void setPitch(float pitch)
    {
        if(shouldNotify && this.pitch != pitch)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPitch", "Pitch", "", this, new Float(pitch), new Float(this.pitch)));
        firePropertyChange("pitch", new Float(this.pitch), new Float(pitch));
        this.pitch = pitch;
    }

    public float getBank()
    {
        return bank;
    }

    public void setBank(float bank)
    {
        if(shouldNotify && this.bank != bank)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBank", "Bank", "", this, new Float(bank), new Float(this.bank)));
        firePropertyChange("bank", new Float(this.bank), new Float(bank));
        this.bank = bank;
    }

    public float getHeading()
    {
        return heading;
    }

    public void setHeading(float heading)
    {
        if(shouldNotify && this.heading != heading)
            HistoryListModel.getInstance().addModel(new HistoryModel("setHeading", "Heading", "", this, new Float(heading), new Float(this.heading)));
        firePropertyChange("heading", new Float(this.heading), new Float(heading));
        this.heading = heading;
    }

    public float getSceneryScale()
    {
        return sceneryScale;
    }

    public void setSceneryScale(float sceneryScale)
    {
        if(shouldNotify && this.sceneryScale != sceneryScale)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSceneryScale", "Scenery Scale", "", this, new Float(sceneryScale), new Float(this.sceneryScale)));
        firePropertyChange("sceneryScale", new Float(this.sceneryScale), new Float(sceneryScale));
        this.sceneryScale = sceneryScale;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public void setDisplayName()
    {
        if(displayNameHM.containsKey(name))
            displayName = (String)displayNameHM.get(name);
        else
            displayName = "Custom Scenery Object";
    }

    public Object clone()
    {
        SceneryModel model = new SceneryModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAlt(getAlt());
        model.setImageComplexity(getImageComplexity());
        model.setName(getName());
        model.setAltitudeIsAgl(getAltitudeIsAgl());
        model.setPitch(getPitch());
        model.setBank(getBank());
        model.setHeading(getHeading());
        model.setSceneryScale(getSceneryScale());
        model.setDisplayName(getDisplayName());
        model.setShouldNotify(true);
        return model;
    }

    private java.awt.geom.Point2D.Float point;
    private String altMeasure;
    private LatLonPoint latLon;
    private String imageComplexity;
    private String name;
    private String displayName;
    private boolean altitudeIsAgl;
    private double alt;
    private float pitch;
    private float bank;
    private float heading;
    private float sceneryScale;
    private static HashMap displayNameHM;

    static 
    {
        displayNameHM = new HashMap();
        displayNameHM.put("", "Custom Scenery Object");
        displayNameHM.put("{1ffdec49-78fa-4b0c-8d9c-b56e91b8fbe8}", "Small Fuel Station 1");
        displayNameHM.put("{781c4106-8ca9-4925-af80-d075b2f713e9}", "Small Fuel Station 2");
        displayNameHM.put("{88d34df2-94ba-4068-a5a0-59609aeecade}", "Medium Fuel Station");
        displayNameHM.put("{0da416ee-c523-4d81-a562-9ce503f28468}", "Medium Fuel Station w/Overhang");
        displayNameHM.put("{85ca15ba-4809-4e2f-91e8-cb289f16710a}", "Kenmore Fuel Station 1");
        displayNameHM.put("{218b3cf4-b37b-4a84-8ef8-4d7db7ed1979}", "Kenmore Fuel Station 2");
        displayNameHM.put("{f51fb5e4-b23b-4a8e-a515-4c92176cc179}", "Large Fuel Tank");
        displayNameHM.put("{23ee29b9-2f04-47e8-abec-58877ef1fee8}", "Fuel Dock");
        displayNameHM.put("{0627877e-8643-4436-8f91-b271f9b11b82}", "Fuel Truck 1");
        displayNameHM.put("{c545a2a3-e2ec-11d2-9c84-00105a0ce62a}", "Fuel Truck 2");
        displayNameHM.put("{cd69fc27-8cf3-4fa0-aafd-67be3d24e9ef}", "Fuel Truck 3");
        displayNameHM.put("{6de93ad2-31f3-4044-9ac5-74071ed4386b}", "Fuel Truck 4");
        displayNameHM.put("{15265fec-26b5-4a44-8915-aee2a293afa3}", "Military Fuel Truck");
        displayNameHM.put("{fe978b1b-6b2f-4898-9e5d-a008e8675ed4}", "Localizer Antenna Array");
        displayNameHM.put("{5911c52a-dd21-4673-a3c7-c95c922e5aaf}", "NDB DME Antenna");
        displayNameHM.put("{401cb2c5-1791-4757-81ea-163fcb6693e8}", "NDB DME Tower");
        displayNameHM.put("{d5ee4aaa-9fe7-43d6-b0e4-195860aae99f}", "NDB DME Shack 1");
        displayNameHM.put("{5a003f36-68dd-4f97-b0b3-616863779a85}", "NDB DME Shack 2");
        displayNameHM.put("{127ae284-25ae-4ec3-bccf-e372bf7df5a3}", "NDB Water");
        displayNameHM.put("{2fb72c66-290c-4727-b970-da20984cee83}", "NDB Antenna");
        displayNameHM.put("{4808a0b0-fc56-4d43-917d-8620b36e8cfc}", "NDB High Antenna");
        displayNameHM.put("{ffbf01ac-2d33-4444-869a-0e1e48cdf2c3}", "General VOR");
        displayNameHM.put("{3a5affe1-5cb6-43e9-b5d3-00dfc3b86e78}", "General VOR DME");
        displayNameHM.put("{c545a272-e2ec-11d2-9c84-00105a0ce62a}", "Small VOR 1");
        displayNameHM.put("{3893d694-f519-4314-acb6-fe0b3b0d283a}", "Small VOR 2");
        displayNameHM.put("{c0b537c3-808a-48ba-8eff-e1ca1a872e84}", "Small VOR DME 1");
        displayNameHM.put("{047814f7-e832-45fc-b5a0-4f047f8234b0}", "Small VOR DME 2");
        displayNameHM.put("{73286269-0380-11d3-9c85-00105a0ce62a}", "ILS Transmitter");
        displayNameHM.put("{c545a270-e2ec-11d2-9c84-00105a0ce62a}", "Checkered Shed");
    }
}
