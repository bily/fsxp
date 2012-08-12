// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TowerModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class TowerModel extends BaseModel
{

    public TowerModel()
    {
        modelName = "Tower";
        altMeasure = "M";
        alt = 0.0D;
        latLon = new LatLonPoint();
        sceneryAltMeasure = "M";
        imageComplexity = "VERY_SPARSE";
        name = "{604b317e-3938-4a34-b50a-dc16324baf0f}";
        sceneryLatLon = new LatLonPoint();
        includesScenery = true;
        altitudeIsAgl = true;
        sceneryAlt = 0.0D;
        pitch = 0.0F;
        bank = 0.0F;
        heading = 0.0F;
        sceneryScale = 1.0F;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            modelPath = new GeneralPath();
            modelPath.moveTo(point.x - 10F * scale, point.y - 20F * scale);
            modelPath.lineTo(point.x + 10F * scale, point.y - 20F * scale);
            modelPath.lineTo(point.x + 20F * scale, point.y - 10F * scale);
            modelPath.lineTo(point.x + 20F * scale, point.y + 10F * scale);
            modelPath.lineTo(point.x + 10F * scale, point.y + 20F * scale);
            modelPath.lineTo(point.x - 10F * scale, point.y + 20F * scale);
            modelPath.lineTo(point.x - 20F * scale, point.y + 10F * scale);
            modelPath.lineTo(point.x - 20F * scale, point.y - 10F * scale);
            modelPath.lineTo(point.x - 10F * scale, point.y - 20F * scale);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(Color.red);
        g2.fill(modelPath);
        g2.setPaint(new Color(153, 0, 0));
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

    public String getSceneryAltMeasure()
    {
        return sceneryAltMeasure;
    }

    public void setSceneryAltMeasure(String sceneryAltMeasure)
    {
        if(shouldNotify && !this.sceneryAltMeasure.equals(sceneryAltMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSceneryAltMeasure", "Scenery Altitude Measure", "", this, sceneryAltMeasure, this.sceneryAltMeasure));
        firePropertyChange("sceneryAltMeasure", this.sceneryAltMeasure, sceneryAltMeasure);
        this.sceneryAltMeasure = sceneryAltMeasure;
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

    public LatLonPoint getSceneryLatLon()
    {
        return sceneryLatLon;
    }

    public void setSceneryLatLon(LatLonPoint sceneryLatLon)
    {
        if(shouldNotify && !this.sceneryLatLon.equals(sceneryLatLon))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSceneryLatLon", "Scenery Lat/Lon", "", this, sceneryLatLon, this.sceneryLatLon));
        firePropertyChange("sceneryLatLon", this.sceneryLatLon, sceneryLatLon);
        this.sceneryLatLon = sceneryLatLon;
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

    public double getSceneryAlt()
    {
        return sceneryAlt;
    }

    public void setSceneryAlt(double sceneryAlt)
    {
        if(shouldNotify && this.sceneryAlt != sceneryAlt)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSceneryAlt", "Scenery Altitude", "", this, new Double(sceneryAlt), new Double(this.sceneryAlt)));
        firePropertyChange("sceneryAlt", new Double(this.sceneryAlt), new Double(sceneryAlt));
        this.sceneryAlt = sceneryAlt;
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

    public boolean getIncludesScenery()
    {
        return includesScenery;
    }

    public void setIncludesScenery(boolean includesScenery)
    {
        this.includesScenery = includesScenery;
    }

    public Object clone()
    {
        TowerModel model = new TowerModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAlt(getAlt());
        model.setSceneryAltMeasure(getSceneryAltMeasure());
        model.setImageComplexity(getImageComplexity());
        model.setName(getName());
        model.setSceneryLatLon((LatLonPoint)getSceneryLatLon().clone());
        model.setIncludesScenery(getIncludesScenery());
        model.setAltitudeIsAgl(getAltitudeIsAgl());
        model.setSceneryAlt(getSceneryAlt());
        model.setPitch(getPitch());
        model.setBank(getBank());
        model.setHeading(getHeading());
        model.setSceneryScale(getSceneryScale());
        model.setShouldNotify(true);
        return model;
    }

    private String altMeasure;
    private LatLonPoint latLon;
    private String sceneryAltMeasure;
    private String imageComplexity;
    private String name;
    private LatLonPoint sceneryLatLon;
    private boolean includesScenery;
    private boolean altitudeIsAgl;
    private double sceneryAlt;
    private double alt;
    private float pitch;
    private float bank;
    private float heading;
    private float sceneryScale;
}
