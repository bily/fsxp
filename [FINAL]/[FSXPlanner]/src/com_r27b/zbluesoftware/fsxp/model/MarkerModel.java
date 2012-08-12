// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkerModel.java

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

public class MarkerModel extends BaseModel
{

    public MarkerModel()
    {
        modelName = "Marker";
        latLon = new LatLonPoint();
        altMeasure = "M";
        type = "INNER";
        region = "";
        ident = "";
        heading = 0.0F;
        alt = 0.0F;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            java.awt.geom.Point2D.Float startPoint = point;
            java.awt.geom.Point2D.Float endPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)((double)point.x - 50D * (double)scale), (float)((double)point.y - 200D * (double)scale)), heading);
            java.awt.geom.Point2D.Float endPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float((float)((double)point.x + 50D * (double)scale), (float)((double)point.y - 200D * (double)scale)), heading);
            modelPath = new GeneralPath();
            modelPath.moveTo(startPoint.x, startPoint.y);
            modelPath.lineTo(endPoint1.x, endPoint1.y);
            modelPath.lineTo(endPoint2.x, endPoint2.y);
            modelPath.lineTo(startPoint.x, startPoint.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        if(type.equals("INNER"))
            g2.setPaint(Color.gray);
        else
        if(type.equals("MIDDLE"))
            g2.setPaint(Color.orange);
        else
        if(type.equals("OUTER"))
            g2.setPaint(Color.blue);
        else
            g2.setPaint(Color.gray);
        g2.fill(modelPath);
        int fontSize = (int)(20F * scale);
        if(fontSize >= 6)
        {
            g2.setPaint(Color.black);
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

    public boolean isCopyable()
    {
        return true;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if(shouldNotify && !this.type.equals(type))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType", "Type", "", this, type, this.type));
        modelName = (new StringBuilder()).append(type).append(" Marker ").append(ident).toString();
        firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        if(shouldNotify && !this.region.equals(region))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRegion", "Region", "", this, region, this.region));
        firePropertyChange("region", this.region, region);
        this.region = region;
    }

    public String getIdent()
    {
        return ident;
    }

    public void setIdent(String ident)
    {
        if(shouldNotify && !this.ident.equals(ident))
            HistoryListModel.getInstance().addModel(new HistoryModel("setIdent", "Ident", "", this, ident, this.ident));
        modelName = (new StringBuilder()).append(type).append(" Marker ").append(ident).toString();
        firePropertyChange("ident", this.ident, ident);
        this.ident = ident;
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

    public Object clone()
    {
        MarkerModel model = new MarkerModel();
        model.setShouldNotify(false);
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAltMeasure(getAltMeasure());
        model.setType(getType());
        model.setRegion(getRegion());
        model.setIdent(getIdent());
        model.setHeading(getHeading());
        model.setAlt(getAlt());
        model.setShouldNotify(true);
        return model;
    }

    private java.awt.geom.Point2D.Float point;
    private LatLonPoint latLon;
    private String altMeasure;
    private String type;
    private String region;
    private String ident;
    private float heading;
    private float alt;
}
