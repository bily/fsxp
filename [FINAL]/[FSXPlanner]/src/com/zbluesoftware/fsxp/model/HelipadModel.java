// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HelipadModel.java

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

public class HelipadModel extends BaseModel
{

    public HelipadModel()
    {
        modelName = "Helipad";
        lengthMeasure = SettingsEngine.getInstance().getHelipadLengthMeasure();
        widthMeasure = SettingsEngine.getInstance().getHelipadWidthMeasure();
        type = "H";
        altMeasure = "M";
        surface = "ASPHALT";
        alt = 0.0D;
        heading = 0.0F;
        length = SettingsEngine.getInstance().getHelipadLength();
        width = SettingsEngine.getInstance().getHelipadWidth();
        closed = false;
        transparent = false;
        latLon = new LatLonPoint();
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || helipadShape == null)
        {
            int sizeLength = 0;
            if(lengthMeasure.equals("M"))
                sizeLength = (int)(length * 3.28F * scale);
            else
                sizeLength = (int)(length * scale);
            int sizeWidth = 0;
            if(widthMeasure.equals("M"))
                sizeWidth = (int)(width * 3.28F * scale);
            else
                sizeWidth = (int)(width * scale);
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            java.awt.geom.Point2D.Float topLeft = new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F);
            java.awt.geom.Point2D.Float topRight = new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F);
            java.awt.geom.Point2D.Float bottomLeft = new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F);
            java.awt.geom.Point2D.Float bottomRight = new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F);
            topLeft = Utilities.rotatePoint(point, topLeft, heading);
            topRight = Utilities.rotatePoint(point, topRight, heading);
            bottomLeft = Utilities.rotatePoint(point, bottomLeft, heading);
            bottomRight = Utilities.rotatePoint(point, bottomRight, heading);
            helipadShape = new GeneralPath();
            ((GeneralPath)helipadShape).moveTo(topLeft.x, topLeft.y);
            ((GeneralPath)helipadShape).lineTo(topRight.x, topRight.y);
            ((GeneralPath)helipadShape).lineTo(bottomRight.x, bottomRight.y);
            ((GeneralPath)helipadShape).lineTo(bottomLeft.x, bottomLeft.y);
            ((GeneralPath)helipadShape).lineTo(topLeft.x, topLeft.y);
            if(closed)
            {
                helipadClosed = new GeneralPath();
                ((GeneralPath)helipadClosed).moveTo(topLeft.x, topLeft.y);
                ((GeneralPath)helipadClosed).lineTo(bottomRight.x, bottomRight.y);
                ((GeneralPath)helipadClosed).moveTo(topRight.x, topRight.y);
                ((GeneralPath)helipadClosed).lineTo(bottomLeft.x, bottomLeft.y);
            } else
            {
                helipadClosed = null;
            }
            sizeWidth = (int)((float)sizeWidth - scale * 3F);
            sizeLength = (int)((float)sizeLength - scale * 3F);
            if(type.equals("CIRCLE"))
            {
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                topRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                bottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                bottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                sizeWidth = (int)((float)sizeWidth - scale * 3F);
                sizeLength = (int)((float)sizeLength - scale * 3F);
                java.awt.geom.Point2D.Float topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                java.awt.geom.Point2D.Float topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                java.awt.geom.Point2D.Float bottomLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                java.awt.geom.Point2D.Float bottomRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                helipadOutline = new GeneralPath();
                float midPointX = (topLeft.x + bottomLeft.x) / 2.0F;
                float midPointY = (topLeft.y + bottomLeft.y) / 2.0F;
                helipadOutline.moveTo(midPointX, midPointY);
                midPointX = (topLeft.x + topRight.x) / 2.0F;
                midPointY = (topLeft.y + topRight.y) / 2.0F;
                helipadOutline.quadTo(topLeft2.x, topLeft2.y, midPointX, midPointY);
                midPointX = (topRight.x + bottomRight.x) / 2.0F;
                midPointY = (topRight.y + bottomRight.y) / 2.0F;
                helipadOutline.quadTo(topRight2.x, topRight2.y, midPointX, midPointY);
                midPointX = (bottomRight.x + bottomLeft.x) / 2.0F;
                midPointY = (bottomRight.y + bottomLeft.y) / 2.0F;
                helipadOutline.quadTo(bottomRight2.x, bottomRight2.y, midPointX, midPointY);
                midPointX = (bottomLeft.x + topLeft.x) / 2.0F;
                midPointY = (bottomLeft.y + topLeft.y) / 2.0F;
                helipadOutline.quadTo(bottomLeft2.x, bottomLeft2.y, midPointX, midPointY);
            } else
            if(type.equals("MEDICAL"))
            {
                helipadOutline = new GeneralPath();
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 4F, point.y - (float)sizeLength / 2.0F), heading);
                helipadOutline.moveTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 4F, point.y - (float)sizeLength / 2.0F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 4F, point.y - (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 4F, point.y + (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 4F, point.y + (float)sizeLength / 2.0F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 4F, point.y + (float)sizeLength / 2.0F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 4F, point.y + (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 4F, point.y - (float)sizeLength / 4F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 4F, point.y - (float)sizeLength / 2.0F), heading);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
            } else
            {
                topLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                topRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y - (float)sizeLength / 2.0F), heading);
                bottomLeft = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                bottomRight = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (float)sizeWidth / 2.0F, point.y + (float)sizeLength / 2.0F), heading);
                helipadOutline = new GeneralPath();
                helipadOutline.moveTo(topLeft.x, topLeft.y);
                helipadOutline.lineTo(topRight.x, topRight.y);
                helipadOutline.lineTo(bottomRight.x, bottomRight.y);
                helipadOutline.lineTo(bottomLeft.x, bottomLeft.y);
                helipadOutline.lineTo(topLeft.x, topLeft.y);
            }
            java.awt.geom.Point2D.Float startPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - 10F * scale, point.y - 15F * scale), heading);
            java.awt.geom.Point2D.Float endPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - 10F * scale, point.y + 15F * scale), heading);
            helipadLetter = new GeneralPath();
            ((GeneralPath)helipadLetter).moveTo(startPoint.x, startPoint.y);
            ((GeneralPath)helipadLetter).lineTo(endPoint.x, endPoint.y);
            startPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + 10F * scale, point.y - 15F * scale), heading);
            endPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + 10F * scale, point.y + 15F * scale), heading);
            ((GeneralPath)helipadLetter).moveTo(startPoint.x, startPoint.y);
            ((GeneralPath)helipadLetter).lineTo(endPoint.x, endPoint.y);
            startPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - 10F * scale, point.y), heading);
            endPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + 10F * scale, point.y), heading);
            ((GeneralPath)helipadLetter).moveTo(startPoint.x, startPoint.y);
            ((GeneralPath)helipadLetter).lineTo(endPoint.x, endPoint.y);
        }
        if(!helipadShape.intersects(g2.getClipBounds()))
            return;
        if(transparent)
        {
            Color helipadColor = ColorsEngine.getInstance().getSurfaceColor(surface);
            g2.setPaint(new Color(helipadColor.getRed(), helipadColor.getGreen(), helipadColor.getBlue(), 25));
        } else
        {
            g2.setPaint(ColorsEngine.getInstance().getSurfaceColor(surface));
        }
        g2.fill(helipadShape);
        if(helipadOutline != null)
        {
            g2.setPaint(Color.white);
            if(!type.equals("H"))
                g2.setStroke(new BasicStroke(Math.max(1.0F, scale * 2.0F)));
            if(type.equals("MEDICAL"))
                g2.fill(helipadOutline);
            else
                g2.draw(helipadOutline);
            g2.setStroke(new BasicStroke());
        }
        if(type.equals("MEDICAL"))
            g2.setPaint(Color.red);
        else
            g2.setPaint(Color.white);
        g2.setStroke(new BasicStroke(Math.max(1.0F, scale * 3F)));
        g2.draw(helipadLetter);
        g2.setStroke(new BasicStroke());
        if(helipadClosed != null)
        {
            g2.setStroke(new BasicStroke(3F * scale));
            g2.setPaint(new Color(212, 167, 49, 198));
            g2.draw(helipadClosed);
            g2.setStroke(new BasicStroke());
        }
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(helipadShape);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(helipadShape);
            }
    }

    public Shape getClip()
    {
        return helipadShape;
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(helipadShape == null)
            return false;
        else
            return helipadShape.contains(x, y);
    }

    public boolean isCopyable()
    {
        return true;
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

    public String getSurface()
    {
        return surface;
    }

    public void setSurface(String surface)
    {
        if(shouldNotify && !this.surface.equals(surface))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSurface", "Surface", "", this, surface, this.surface));
        firePropertyChange("surface", this.surface, surface);
        this.surface = surface;
    }

    public float getLength()
    {
        return length;
    }

    public void setLength(float length)
    {
        if(shouldNotify && this.length != length)
            HistoryListModel.getInstance().addModel(new HistoryModel("setLength", "Length", "", this, new Float(length), new Float(this.length)));
        firePropertyChange("length", new Float(this.length), new Float(length));
        this.length = length;
    }

    public String getLengthMeasure()
    {
        return lengthMeasure;
    }

    public void setLengthMeasure(String lengthMeasure)
    {
        if(shouldNotify && !this.lengthMeasure.equals(lengthMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLengthMeasure", "Length Measure", "", this, lengthMeasure, this.lengthMeasure));
        firePropertyChange("lengthMeasure", this.lengthMeasure, lengthMeasure);
        this.lengthMeasure = lengthMeasure;
    }

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        if(shouldNotify && this.width != width)
            HistoryListModel.getInstance().addModel(new HistoryModel("setWidth", "Width", "", this, new Float(width), new Float(this.width)));
        firePropertyChange("width", new Float(this.width), new Float(width));
        this.width = width;
    }

    public String getWidthMeasure()
    {
        return widthMeasure;
    }

    public void setWidthMeasure(String widthMeasure)
    {
        if(shouldNotify && !this.widthMeasure.equals(widthMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setWidthMeasure", "Width Measure", "", this, widthMeasure, this.widthMeasure));
        firePropertyChange("widthMeasure", this.widthMeasure, widthMeasure);
        this.widthMeasure = widthMeasure;
    }

    public boolean getClosed()
    {
        return closed;
    }

    public void setClosed(boolean closed)
    {
        if(shouldNotify && this.closed != closed)
            HistoryListModel.getInstance().addModel(new HistoryModel("setClosed", "Closed", "", this, new Boolean(closed), new Boolean(this.closed)));
        firePropertyChange("closed", new Boolean(this.closed), new Boolean(closed));
        this.closed = closed;
    }

    public boolean getTransparent()
    {
        return transparent;
    }

    public void setTransparent(boolean transparent)
    {
        if(shouldNotify && this.transparent != transparent)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTransparent", "Transparent", "", this, new Boolean(transparent), new Boolean(this.transparent)));
        firePropertyChange("transparent", new Boolean(this.transparent), new Boolean(transparent));
        this.transparent = transparent;
    }

    public Object clone()
    {
        HelipadModel model = new HelipadModel();
        model.setShouldNotify(false);
        model.setLengthMeasure(getLengthMeasure());
        model.setWidthMeasure(getWidthMeasure());
        model.setType(getType());
        model.setAltMeasure(getAltMeasure());
        model.setSurface(getSurface());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setHeading(getHeading());
        model.setLength(getLength());
        model.setWidth(getWidth());
        model.setAlt(getAlt());
        model.setClosed(getClosed());
        model.setTransparent(getTransparent());
        model.setShouldNotify(true);
        return model;
    }

    private Shape helipadShape;
    private GeneralPath helipadOutline;
    private Shape helipadLetter;
    private Shape helipadClosed;
    private String lengthMeasure;
    private String widthMeasure;
    private String type;
    private String altMeasure;
    private String surface;
    private LatLonPoint latLon;
    private float heading;
    private float length;
    private float width;
    private double alt;
    private boolean closed;
    private boolean transparent;
}
