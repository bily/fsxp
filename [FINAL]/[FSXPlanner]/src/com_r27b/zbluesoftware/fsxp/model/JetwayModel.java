// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JetwayModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class JetwayModel extends BaseModel
{

    public JetwayModel()
    {
        modelName = "Jetway";
        gateName = "PARKING";
        instanceid = "";
        altMeasure = "M";
        imageComplexity = "NORMAL";
        name = "{bfcdf52b-9142-415c-8318-03c1b92ca9d9}";
        latLon = new LatLonPoint();
        altitudeIsAgl = false;
        alt = 0.0D;
        pitch = 0.0F;
        bank = 0.0F;
        heading = 0.0F;
        jetwayScale = 1.0F;
        parkingNumber = 0;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        float size = 10F * scale;
        if(recreate || modelPath == null)
        {
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            float displayHeading = heading + 90F;
            if(displayHeading >= 360F)
                displayHeading -= 360F;
            java.awt.geom.Point2D.Float topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.3F, point.y - size), displayHeading);
            java.awt.geom.Point2D.Float topRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.3F, point.y - size), displayHeading);
            java.awt.geom.Point2D.Float bottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.3F, point.y + size), displayHeading);
            java.awt.geom.Point2D.Float bottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.3F, point.y + size), displayHeading);
            java.awt.geom.Point2D.Float topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.4F, point.y + size), displayHeading);
            java.awt.geom.Point2D.Float topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.4F, point.y + size), displayHeading);
            java.awt.geom.Point2D.Float bottomLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.4F, point.y + size * 1.2F), displayHeading);
            java.awt.geom.Point2D.Float bottomRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.4F, point.y + size * 1.2F), displayHeading);
            java.awt.geom.Point2D.Float topLeft3 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.5F, point.y + size * 1.2F), displayHeading);
            java.awt.geom.Point2D.Float topRight3 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.5F, point.y + size * 1.2F), displayHeading);
            java.awt.geom.Point2D.Float bottomLeft3 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.5F, point.y + size * 4.2F), displayHeading);
            java.awt.geom.Point2D.Float bottomRight3 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.5F, point.y + size * 4.2F), displayHeading);
            java.awt.geom.Point2D.Float bottomLeft4 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size * 0.6F, point.y + size * 4.5F), displayHeading);
            java.awt.geom.Point2D.Float bottomRight4 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size * 0.6F, point.y + size * 4.5F), displayHeading);
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeft.x, topLeft.y);
            modelPath.lineTo(topRight.x, topRight.y);
            modelPath.lineTo(bottomRight.x, bottomRight.y);
            modelPath.lineTo(topRight2.x, topRight2.y);
            modelPath.lineTo(bottomRight2.x, bottomRight2.y);
            modelPath.lineTo(topRight3.x, topRight3.y);
            modelPath.lineTo(bottomRight3.x, bottomRight3.y);
            modelPath.lineTo(bottomRight4.x, bottomRight4.y);
            modelPath.lineTo(bottomLeft4.x, bottomLeft4.y);
            modelPath.lineTo(bottomLeft3.x, bottomLeft3.y);
            modelPath.lineTo(topLeft3.x, topLeft3.y);
            modelPath.lineTo(bottomLeft2.x, bottomLeft2.y);
            modelPath.lineTo(topLeft2.x, topLeft2.y);
            modelPath.lineTo(bottomLeft.x, bottomLeft.y);
            modelPath.lineTo(topLeft.x, topLeft.y);
            java.awt.geom.Ellipse2D.Float topCircle = new java.awt.geom.Ellipse2D.Float(point.x - size * 0.5F, point.y - size * 0.5F, size, size);
            modelPath.append(topCircle, false);
            java.awt.geom.Point2D.Float bottomCirclePoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, point.y + size * 3.8F), displayHeading);
            java.awt.geom.Ellipse2D.Float bottomCircle = new java.awt.geom.Ellipse2D.Float(bottomCirclePoint.x - size * 0.6F, bottomCirclePoint.y - size * 0.6F, size * 1.2F, size * 1.2F);
            modelPath.append(bottomCircle, false);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(new Color(255, 255, 153));
        g2.fill(modelPath);
        g2.setPaint(Color.green);
        g2.draw(modelPath);
        int fontSize = (int)((double)((float)Utilities.JETWAY_FONT.getSize() * scale) * 0.5D);
        if(fontSize >= 6)
        {
            g2.setPaint(Color.black);
            g2.setFont(new Font(Utilities.JETWAY_FONT.getFamily(), Utilities.JETWAY_FONT.getStyle(), fontSize));
            int fontHeight = g2.getFontMetrics().getHeight();
            g2.drawString(gateName, (point.x - size) + 2.0F, (point.y - size) + (float)fontHeight);
            g2.drawString((new StringBuilder()).append(parkingNumber).append("").toString(), (point.x - size) + 2.0F, (point.y - size) + (float)(fontHeight * 2));
            g2.drawString(imageComplexity, (point.x - size) + 2.0F, (point.y - size) + (float)(fontHeight * 3));
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

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(gateName).append(" ").append(parkingNumber).append("]").toString();
    }

    public String getGateName()
    {
        return gateName;
    }

    public void setGateName(String gateName)
    {
        if(shouldNotify && !this.gateName.equals(gateName))
            HistoryListModel.getInstance().addModel(new HistoryModel("setGateName", "Gate Name", "", this, gateName, this.gateName));
        firePropertyChange("gateName", this.gateName, gateName);
        this.gateName = gateName;
    }

    public String getInstanceid()
    {
        return instanceid;
    }

    public void setInstanceid(String instanceid)
    {
        if(shouldNotify && !this.instanceid.equals(instanceid))
            HistoryListModel.getInstance().addModel(new HistoryModel("setInstanceid", "Instance ID", "", this, instanceid, this.instanceid));
        firePropertyChange("instanceid", this.instanceid, instanceid);
        this.instanceid = instanceid;
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

    public float getJetwayScale()
    {
        return jetwayScale;
    }

    public void setJetwayScale(float jetwayScale)
    {
        if(shouldNotify && this.jetwayScale != jetwayScale)
            HistoryListModel.getInstance().addModel(new HistoryModel("setJetwayScale", "Jetway Scale", "", this, new Float(jetwayScale), new Float(this.jetwayScale)));
        firePropertyChange("jetwayScale", new Float(this.jetwayScale), new Float(jetwayScale));
        this.jetwayScale = jetwayScale;
    }

    public int getParkingNumber()
    {
        return parkingNumber;
    }

    public void setParkingNumber(int parkingNumber)
    {
        if(shouldNotify && this.parkingNumber != parkingNumber)
            HistoryListModel.getInstance().addModel(new HistoryModel("setParkingNumber", "Parking Number", "", this, new Integer(parkingNumber), new Integer(this.parkingNumber)));
        firePropertyChange("parkingNumber", new Integer(this.parkingNumber), new Integer(parkingNumber));
        this.parkingNumber = parkingNumber;
    }

    public Object clone()
    {
        JetwayModel model = new JetwayModel();
        model.setShouldNotify(false);
        model.setGateName(getGateName());
        model.setInstanceid(getInstanceid());
        model.setAltMeasure(getAltMeasure());
        model.setImageComplexity(getImageComplexity());
        model.setName(getName());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAltitudeIsAgl(getAltitudeIsAgl());
        model.setAlt(getAlt());
        model.setPitch(getPitch());
        model.setBank(getBank());
        model.setHeading(getHeading());
        model.setJetwayScale(getJetwayScale());
        model.setParkingNumber(getParkingNumber());
        model.setShouldNotify(false);
        return model;
    }

    private java.awt.geom.Point2D.Float point;
    private String gateName;
    private String instanceid;
    private String altMeasure;
    private String imageComplexity;
    private String name;
    private LatLonPoint latLon;
    private boolean altitudeIsAgl;
    private double alt;
    private float pitch;
    private float bank;
    private float heading;
    private float jetwayScale;
    private int parkingNumber;
}
