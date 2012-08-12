// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PlaneModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.io.PrintStream;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel

public class PlaneModel extends BaseModel
{

    public PlaneModel()
    {
        modelName = "Plane";
        altMeasure = "M";
        alt = 0.0D;
        heading = 0.0F;
        latLon = new LatLonPoint();
        planeMake = FSXConnection.getInstance().getPlaneMake();
        planeType = FSXConnection.getInstance().getPlaneType();
        length = FSXConnection.getInstance().getPlaneLength();
        width = FSXConnection.getInstance().getPlaneWidth();
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            float planeWidth = width * scale;
            float planeLength = length * scale;
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            modelPath = new GeneralPath();
            java.awt.geom.Point2D.Float planePoint;
            if(planeType.equals("Airplane"))
            {
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - planeLength * 0.5F), heading);
                modelPath.moveTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y - planeLength * 0.4F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y - planeLength * 0.25F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.5F, point.y + planeLength * 0.062F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.5F, point.y + planeLength * 0.125F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y - planeLength * 0.03F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y + planeLength * 0.25F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.25F, point.y + planeLength * 0.4F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.25F, point.y + planeLength * 0.45F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + planeLength * 0.4F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.25F, point.y + planeLength * 0.45F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.25F, point.y + planeLength * 0.4F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y + planeLength * 0.25F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y - planeLength * 0.03F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.5F, point.y + planeLength * 0.125F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.5F, point.y + planeLength * 0.062F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y - planeLength * 0.25F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y - planeLength * 0.4F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - planeLength * 0.5F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
            } else
            {
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - planeLength * 0.33F), heading);
                modelPath.moveTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y - planeLength * 0.3F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.0625F, point.y + planeLength * 0.1F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.03F, point.y + planeLength * 0.2F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - planeWidth * 0.03F, point.y + planeLength * 0.66F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + planeLength * 0.7F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.03F, point.y + planeLength * 0.66F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.03F, point.y + planeLength * 0.58F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.04F, point.y + planeLength * 0.58F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.04F, point.y + planeLength * 0.66F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.055F, point.y + planeLength * 0.66F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.055F, point.y + planeLength * 0.46F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.04F, point.y + planeLength * 0.46F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.04F, point.y + planeLength * 0.54F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.03F, point.y + planeLength * 0.54F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.03F, point.y + planeLength * 0.2F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y + planeLength * 0.1F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + planeWidth * 0.0625F, point.y - planeLength * 0.3F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - planeLength * 0.33F), heading);
                modelPath.lineTo(planePoint.x, planePoint.y);
                modelPath.append(new java.awt.geom.Ellipse2D.Float(point.x - planeWidth * 0.4F, point.y - planeWidth * 0.4F, planeWidth * 0.8F, planeWidth * 0.8F), false);
            }
            planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - 10F, point.y), heading);
            modelPath.moveTo(planePoint.x, planePoint.y);
            planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + 10F, point.y), heading);
            modelPath.lineTo(planePoint.x, planePoint.y);
            planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y - 10F), heading);
            modelPath.moveTo(planePoint.x, planePoint.y);
            planePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + 10F), heading);
            modelPath.lineTo(planePoint.x, planePoint.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(new Color(153, 153, 153, 128));
        g2.fill(modelPath);
        g2.setPaint(new Color(153, 153, 153));
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

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        else
            return modelPath.contains(x, y);
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
        firePropertyChange("altMeasure", this.altMeasure, altMeasure);
        this.altMeasure = altMeasure;
    }

    public LatLonPoint getLatLon()
    {
        return latLon;
    }

    public void setLatLon(LatLonPoint latLon)
    {
        firePropertyChange("latLon", this.latLon, latLon);
        this.latLon = latLon;
    }

    public double getAlt()
    {
        return alt;
    }

    public void setAlt(double alt)
    {
        firePropertyChange("alt", new Double(this.alt), new Double(alt));
        this.alt = alt;
    }

    public float getHeading()
    {
        return heading;
    }

    public void setHeading(float heading)
    {
        firePropertyChange("heading", new Float(this.heading), new Float(heading));
        this.heading = heading;
    }

    public String getPlaneMake()
    {
        return planeMake;
    }

    public void setPlaneMake(String planeMake)
    {
        System.out.println((new StringBuilder()).append("planeMake: ").append(planeMake).toString());
        firePropertyChange("planeMake", this.planeMake, planeMake);
        this.planeMake = planeMake;
    }

    public String getPlaneType()
    {
        return planeType;
    }

    public void setPlaneType(String planeType)
    {
        firePropertyChange("planeType", this.planeType, planeType);
        this.planeType = planeType;
    }

    public float getLength()
    {
        return length;
    }

    public void setLength(float length)
    {
        firePropertyChange("length", new Float(this.length), new Float(length));
        this.length = length;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        firePropertyChange("width", new Float(this.width), new Float(width));
        this.width = width;
    }

    private String altMeasure;
    private String planeMake;
    private String planeType;
    private LatLonPoint latLon;
    private double alt;
    private float heading;
    private float length;
    private float width;
}
