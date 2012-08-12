// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StartModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class StartModel extends BaseModel
{

    public StartModel()
    {
        modelName = "Start Position";
        type = "";
        altMeasure = "M";
        number = "";
        designator = "";
        alt = 0.0D;
        heading = 0.0F;
        latLon = new LatLonPoint();
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || startCircle == null)
        {
            int size = (int)(50F * scale);
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            startCircle = new java.awt.geom.Ellipse2D.Float(point.x - (float)size / 2.0F, point.y - (float)size / 2.0F, size, size);
            planeCenter = new java.awt.geom.Point2D.Float(point.x, point.y - ((float)size * 3F) / 16F);
            planeLeft = new java.awt.geom.Point2D.Float(point.x - (float)size / 4F, point.y + (float)size / 16F);
            planeRight = new java.awt.geom.Point2D.Float(point.x + (float)size / 4F, point.y + (float)size / 16F);
            planeCenter = Utilities.rotatePoint(point, planeCenter, heading);
            planeLeft = Utilities.rotatePoint(point, planeLeft, heading);
            planeRight = Utilities.rotatePoint(point, planeRight, heading);
        }
        if(!startCircle.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(new Color(193, 30, 193));
        g2.fill(startCircle);
        g2.setPaint(new Color(102, 0, 102));
        g2.draw(startCircle);
        g2.draw(new java.awt.geom.Line2D.Float(planeCenter, planeLeft));
        g2.draw(new java.awt.geom.Line2D.Float(planeCenter, planeRight));
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(startCircle);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(startCircle);
            }
    }

    public Shape getClip()
    {
        return startCircle;
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(startCircle == null)
            return false;
        else
            return startCircle.contains(x, y);
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(type).append(" ").append(number).append("]").toString();
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if(shouldNotify && !this.type.equals(type))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType", "Type", "", this, type, this.type));
        firePropertyChange("type", this.type, type);
        this.type = type;
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

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        if(shouldNotify && !this.number.equals(number))
            HistoryListModel.getInstance().addModel(new HistoryModel("setNumber", "Number", "", this, number, this.number));
        firePropertyChange("number", this.number, number);
        this.number = number;
    }

    public String getDesignator()
    {
        return designator;
    }

    public void setDesignator(String designator)
    {
        if(shouldNotify && !this.designator.equals(designator))
            HistoryListModel.getInstance().addModel(new HistoryModel("setDesignator", "Designator", "", this, designator, this.designator));
        firePropertyChange("designator", this.designator, designator);
        this.designator = designator;
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

    public Object clone()
    {
        StartModel model = new StartModel();
        model.setShouldNotify(false);
        model.setType(getType());
        model.setAltMeasure(getAltMeasure());
        model.setNumber(getNumber());
        model.setDesignator(getDesignator());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAlt(getAlt());
        model.setHeading(getHeading());
        model.setShouldNotify(true);
        return model;
    }

    private Ellipse2D startCircle;
    private java.awt.geom.Point2D.Float planeCenter;
    private java.awt.geom.Point2D.Float planeLeft;
    private java.awt.geom.Point2D.Float planeRight;
    private String type;
    private String altMeasure;
    private String number;
    private String designator;
    private LatLonPoint latLon;
    private double alt;
    private float heading;
}
