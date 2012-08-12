// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WindsockModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class WindsockModel extends BaseModel
{

    public WindsockModel()
    {
        modelName = "Windsock";
        altMeasure = "M";
        imageComplexity = "NORMAL";
        latLon = new LatLonPoint();
        altitudeIsAgl = false;
        alt = 0.0F;
        pitch = 0.0F;
        bank = 0.0F;
        heading = 0.0F;
        lighted = true;
        poleHeight = 5.5F;
        sockLength = 3.5F;
        poleColor = new Color(128, 128, 128);
        sockColor = new Color(255, 128, 128);
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        float size = 3F * scale;
        if(recreate || modelPath == null)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            modelPath = new GeneralPath();
            java.awt.geom.Ellipse2D.Float topCircle = new java.awt.geom.Ellipse2D.Float(point.x - size * 0.5F, point.y - size * 0.5F, size, size);
            modelPath.append(topCircle, false);
            attachmentLines = new GeneralPath();
            attachmentLines.moveTo(point.x + size, point.y - size);
            attachmentLines.lineTo(point.x, point.y);
            attachmentLines.lineTo(point.x + size, point.y + size);
            sockPath = new GeneralPath();
            sockPath.moveTo(point.x + size, point.y - size);
            sockPath.lineTo(point.x + size, point.y + size);
            sockPath.lineTo(point.x + size * 5F, point.y + size / 4F);
            sockPath.lineTo(point.x + size * 5F, point.y - size / 4F);
            sockPath.lineTo(point.x + size, point.y - size);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(poleColor);
        g2.fill(modelPath);
        g2.setPaint(Color.darkGray);
        g2.draw(modelPath);
        g2.setPaint(Color.darkGray);
        g2.draw(attachmentLines);
        g2.setPaint(sockColor);
        g2.fill(sockPath);
        g2.setPaint(new Color(102, 0, 0));
        g2.draw(sockPath);
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(modelPath);
                g2.draw(sockPath);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(modelPath);
                g2.fill(sockPath);
            }
    }

    public boolean moveTo(LatLonPoint latLonPoint, double oldX, double oldY)
    {
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), oldX, oldY, scale);
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        LatLonPoint latLon2 = new LatLonPoint(getLatLon().getLat(), getLatLon().getLon());
        latLon2.adjustLat(latDifference);
        latLon2.adjustLon(lonDifference);
        setLatLon(latLon2);
        return true;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        return modelPath.contains(x, y) || sockPath.contains(x, y);
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getModelName()
    {
        return modelName;
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

    public float getAlt()
    {
        return alt;
    }

    public void setAlt(float alt)
    {
        if(shouldNotify && this.alt != alt)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlt", "Altitude", "", this, new Float(alt), new Float(this.alt)));
        firePropertyChange("alt", new Float(this.alt), new Float(alt));
        this.alt = alt;
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

    public boolean getLighted()
    {
        return lighted;
    }

    public void setLighted(boolean lighted)
    {
        if(shouldNotify && this.lighted != lighted)
            HistoryListModel.getInstance().addModel(new HistoryModel("setLighted", "Lighted", "", this, new Boolean(lighted), new Boolean(this.lighted)));
        firePropertyChange("lighted", new Boolean(this.lighted), new Boolean(lighted));
        this.lighted = lighted;
    }

    public float getPoleHeight()
    {
        return poleHeight;
    }

    public void setPoleHeight(float poleHeight)
    {
        if(shouldNotify && this.poleHeight != poleHeight)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPoleHeight", "Pole Height", "", this, new Float(poleHeight), new Float(this.poleHeight)));
        firePropertyChange("poleHeight", new Float(this.poleHeight), new Float(poleHeight));
        this.poleHeight = poleHeight;
    }

    public float getSockLength()
    {
        return sockLength;
    }

    public void setSockLength(float sockLength)
    {
        if(shouldNotify && this.sockLength != sockLength)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSockLength", "Sock Length", "", this, new Float(sockLength), new Float(this.sockLength)));
        firePropertyChange("sockLength", new Float(this.sockLength), new Float(sockLength));
        this.sockLength = sockLength;
    }

    public Color getPoleColor()
    {
        return poleColor;
    }

    public void setPoleColor(Color poleColor)
    {
        if(shouldNotify && !this.poleColor.equals(poleColor))
            HistoryListModel.getInstance().addModel(new HistoryModel("setPoleColor", "Pole Color", "", this, poleColor, this.poleColor));
        firePropertyChange("poleColor", this.poleColor, poleColor);
        this.poleColor = poleColor;
    }

    public Color getSockColor()
    {
        return sockColor;
    }

    public void setSockColor(Color sockColor)
    {
        if(shouldNotify && !this.sockColor.equals(sockColor))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSockColor", "Sock Color", "", this, sockColor, this.sockColor));
        firePropertyChange("sockColor", this.sockColor, sockColor);
        this.sockColor = sockColor;
    }

    public Object clone()
    {
        WindsockModel model = new WindsockModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setImageComplexity(getImageComplexity());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAltitudeIsAgl(getAltitudeIsAgl());
        model.setAlt(getAlt());
        model.setPitch(getPitch());
        model.setBank(getBank());
        model.setHeading(getHeading());
        model.setLighted(getLighted());
        model.setPoleHeight(getPoleHeight());
        model.setSockLength(getSockLength());
        model.setPoleColor(getPoleColor());
        model.setSockColor(getSockColor());
        model.setShouldNotify(true);
        return model;
    }

    private GeneralPath sockPath;
    private GeneralPath attachmentLines;
    private String altMeasure;
    private String imageComplexity;
    private LatLonPoint latLon;
    private Color poleColor;
    private Color sockColor;
    private boolean altitudeIsAgl;
    private boolean lighted;
    private float alt;
    private float pitch;
    private float bank;
    private float heading;
    private float poleHeight;
    private float sockLength;
}
