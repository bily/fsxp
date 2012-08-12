// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlideSlopeModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, SelectedItem, HistoryListModel

public class GlideSlopeModel extends BaseModel
{

    public GlideSlopeModel()
    {
        altMeasure = "M";
        rangeMeasure = "N";
        alt = 0.0F;
        range = 27F;
        pitch = 3F;
        latLon = new LatLonPoint();
        heading = 0.0F;
        ident = "";
        modelName = "DME";
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            float displayHeading = heading;
            if(heading <= 180F)
                displayHeading += 180F;
            else
                displayHeading -= 180F;
            float beamWidth = (float)(Math.tan(Math.toRadians(20D)) * (double)(500F * scale));
            java.awt.geom.Point2D.Float startPoint = point;
            java.awt.geom.Point2D.Float endPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - beamWidth / 2.0F, (float)((double)point.y - 500D * (double)scale)), displayHeading);
            java.awt.geom.Point2D.Float endPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + beamWidth / 2.0F, (float)((double)point.y - 500D * (double)scale)), displayHeading);
            modelPath = new GeneralPath();
            modelPath.moveTo(startPoint.x, startPoint.y);
            modelPath.lineTo(endPoint1.x, endPoint1.y);
            modelPath.lineTo(endPoint2.x, endPoint2.y);
            modelPath.lineTo(startPoint.x, startPoint.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        Color glideSlopeColor = ColorsEngine.getInstance().getGlideSlopeColor();
        g2.setPaint(new Color(glideSlopeColor.getRed(), glideSlopeColor.getGreen(), glideSlopeColor.getBlue(), 128));
        g2.fill(modelPath);
        g2.setPaint(glideSlopeColor);
        g2.draw(modelPath);
        int fontSize = (int)(20F * scale);
        if(fontSize >= 6)
        {
            g2.setFont(new Font("Arial", 0, fontSize));
            g2.drawString(ident, point.x, point.y);
        }
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

    public void setParentModel(BaseModel parentModel)
    {
        this.parentModel = parentModel;
    }

    public BaseModel getParentModel()
    {
        return parentModel;
    }

    public void setHeading(float heading)
    {
        this.heading = heading;
    }

    public void setIdent(String ident)
    {
        this.ident = ident;
        modelName = (new StringBuilder()).append("DME ").append(ident).toString();
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

    public void setCurrentlySelected(boolean currentlySelected)
    {
        if(currentlySelected)
            SelectedItem.getInstance().selectBaseModel(this);
        firePropertyChange("currentlySelected", new Boolean(this.currentlySelected), new Boolean(currentlySelected));
        this.currentlySelected = currentlySelected;
    }

    public void setSelected(boolean currentlySelected)
    {
        this.currentlySelected = currentlySelected;
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

    public String getRangeMeasure()
    {
        return rangeMeasure;
    }

    public void setRangeMeasure(String rangeMeasure)
    {
        if(shouldNotify && !this.rangeMeasure.equals(rangeMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRangeMeasure", "Range Measure", "", this, rangeMeasure, this.rangeMeasure));
        firePropertyChange("rangeMeasure", this.rangeMeasure, rangeMeasure);
        this.rangeMeasure = rangeMeasure;
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

    public float getRange()
    {
        return range;
    }

    public void setRange(float range)
    {
        if(shouldNotify && this.range != range)
            HistoryListModel.getInstance().addModel(new HistoryModel("setRange", "Range", "", this, new Float(range), new Float(this.range)));
        firePropertyChange("range", new Float(this.range), new Float(range));
        this.range = range;
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

    public Object clone()
    {
        GlideSlopeModel model = new GlideSlopeModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setRangeMeasure(getRangeMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setIdent(ident);
        model.setAlt(getAlt());
        model.setRange(getRange());
        model.setPitch(getPitch());
        model.setHeading(heading);
        model.setShouldNotify(true);
        return model;
    }

    private BaseModel parentModel;
    private java.awt.geom.Point2D.Float point;
    private String altMeasure;
    private String rangeMeasure;
    private LatLonPoint latLon;
    private String ident;
    private float alt;
    private float range;
    private float pitch;
    private float heading;
}
