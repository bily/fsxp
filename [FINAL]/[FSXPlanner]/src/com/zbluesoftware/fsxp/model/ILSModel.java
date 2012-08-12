// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ILSModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, GlideSlopeModel, DMEModel, 
//            SelectedItem, HistoryListModel

public class ILSModel extends BaseModel
    implements PropertyChangeListener
{

    public ILSModel()
    {
        glideSlopeModel = null;
        dmeModel = null;
        altMeasure = "M";
        rangeMeasure = "N";
        end = "PRIMARY";
        ident = "";
        name = "";
        alt = 0.0F;
        heading = 0.0F;
        frequency = 108F;
        range = 27F;
        magvar = 0.0F;
        width = 5F;
        backCourse = true;
        latLon = new LatLonPoint();
        runway = "";
        modelName = "ILS";
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(dmeModel != null)
        {
            dmeModel.setCenterPoint(centerPoint);
            dmeModel.setScale(scale);
            dmeModel.paint(g2, recreate);
        }
        if(glideSlopeModel != null)
        {
            glideSlopeModel.setCenterPoint(centerPoint);
            glideSlopeModel.setScale(scale);
            glideSlopeModel.paint(g2, recreate);
        }
        if(recreate || modelPath == null)
        {
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            float displayHeading = heading;
            if(heading <= 180F)
                displayHeading += 180F;
            else
                displayHeading -= 180F;
            float beamWidth = (float)(Math.tan(Math.toRadians(width)) * (double)(2000F * scale));
            java.awt.geom.Point2D.Float startPoint = point;
            java.awt.geom.Point2D.Float endPoint1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - beamWidth / 2.0F, (float)((double)point.y - 2000D * (double)scale)), displayHeading);
            java.awt.geom.Point2D.Float endPoint2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + beamWidth / 2.0F, (float)((double)point.y - 2000D * (double)scale)), displayHeading);
            java.awt.geom.Point2D.Float midPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x, (float)((double)point.y - 1800D * (double)scale)), displayHeading);
            modelPath = new GeneralPath();
            modelPath.moveTo(startPoint.x, startPoint.y);
            modelPath.lineTo(endPoint1.x, endPoint1.y);
            modelPath.lineTo(midPoint.x, midPoint.y);
            modelPath.lineTo(endPoint2.x, endPoint2.y);
            modelPath.lineTo(startPoint.x, startPoint.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        Color ilsColor = ColorsEngine.getInstance().getILSColor();
        g2.setPaint(new Color(ilsColor.getRed(), ilsColor.getGreen(), ilsColor.getBlue(), 128));
        g2.fill(modelPath);
        g2.setPaint(ilsColor);
        g2.draw(modelPath);
        int fontSize = (int)((float)Utilities.ILS_FONT.getSize() * scale);
        if(fontSize >= 6)
        {
            g2.setFont(new Font(Utilities.ILS_FONT.getFamily(), Utilities.ILS_FONT.getStyle(), fontSize));
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

    public void setCurrentlySelected(boolean currentlySelected)
    {
        if(currentlySelected)
            SelectedItem.getInstance().selectBaseModel(this);
        this.currentlySelected = currentlySelected;
        if(dmeModel != null)
            dmeModel.setSelected(currentlySelected);
        if(glideSlopeModel != null)
            glideSlopeModel.setSelected(currentlySelected);
    }

    public void setSelected(boolean currentlySelected)
    {
        this.currentlySelected = currentlySelected;
    }

    public GlideSlopeModel getGlideSlopeModel()
    {
        return glideSlopeModel;
    }

    public void setGlideSlopeModel(GlideSlopeModel glideSlopeModel)
    {
        if(this.glideSlopeModel != null)
            this.glideSlopeModel.removePropertyChangeListener(this);
        this.glideSlopeModel = glideSlopeModel;
        if(glideSlopeModel != null)
        {
            glideSlopeModel.setHeading(heading);
            glideSlopeModel.setIdent(ident);
            glideSlopeModel.setParentModel(this);
            glideSlopeModel.addPropertyChangeListener(this);
        }
    }

    public DMEModel getDMEModel()
    {
        return dmeModel;
    }

    public void setDMEModel(DMEModel dmeModel)
    {
        if(this.dmeModel != null)
            this.dmeModel.removePropertyChangeListener(this);
        this.dmeModel = dmeModel;
        if(dmeModel != null)
        {
            dmeModel.setDisplayHeading(heading);
            dmeModel.setIdent(ident);
            dmeModel.setParentModel(this);
            dmeModel.addPropertyChangeListener(this);
        }
    }

    public String getRunway()
    {
        return runway;
    }

    public void setRunway(String runway)
    {
        if(shouldNotify && !this.runway.equals(runway))
            HistoryListModel.getInstance().addModel(new HistoryModel("setRunway", "Runway", "", this, runway, this.runway));
        firePropertyChange("runway", this.runway, runway);
        this.runway = runway;
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

    public String getEnd()
    {
        return end;
    }

    public void setEnd(String end)
    {
        if(shouldNotify && !this.end.equals(end))
            HistoryListModel.getInstance().addModel(new HistoryModel("setEnd", "End", "", this, end, this.end));
        firePropertyChange("end", this.end, end);
        this.end = end;
    }

    public String getIdent()
    {
        return ident;
    }

    public void setIdent(String ident)
    {
        if(shouldNotify && !this.ident.equals(ident))
            HistoryListModel.getInstance().addModel(new HistoryModel("setIdent", "Ident", "", this, ident, this.ident));
        modelName = (new StringBuilder()).append("ILS ").append(ident).toString();
        firePropertyChange("ident", this.ident, ident);
        this.ident = ident;
        if(dmeModel != null)
            dmeModel.setIdent(ident);
        if(glideSlopeModel != null)
            glideSlopeModel.setIdent(ident);
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
        if(dmeModel != null)
            dmeModel.setDisplayHeading(heading);
        if(glideSlopeModel != null)
            glideSlopeModel.setHeading(heading);
    }

    public float getFrequency()
    {
        return frequency;
    }

    public void setFrequency(float frequency)
    {
        if(shouldNotify && this.frequency != frequency)
            HistoryListModel.getInstance().addModel(new HistoryModel("setFrequency", "Frequency", "", this, new Float(frequency), new Float(this.frequency)));
        firePropertyChange("frequency", new Float(this.frequency), new Float(frequency));
        this.frequency = frequency;
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

    public float getMagvar()
    {
        return magvar;
    }

    public void setMagvar(float magvar)
    {
        if(shouldNotify && this.magvar != magvar)
            HistoryListModel.getInstance().addModel(new HistoryModel("setMagvar", "Magvar", "", this, new Float(magvar), new Float(this.magvar)));
        firePropertyChange("magvar", new Float(this.magvar), new Float(magvar));
        this.magvar = magvar;
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

    public boolean getBackCourse()
    {
        return backCourse;
    }

    public void setBackCourse(boolean backCourse)
    {
        if(shouldNotify && this.backCourse != backCourse)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBackCourse", "Back Course", "", this, new Boolean(backCourse), new Boolean(this.backCourse)));
        firePropertyChange("backCourse", new Boolean(this.backCourse), new Boolean(backCourse));
        this.backCourse = backCourse;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == dmeModel)
        {
            if(event.getPropertyName().equals("currentlySelected"))
            {
                currentlySelected = ((Boolean)event.getNewValue()).booleanValue();
                if(glideSlopeModel != null)
                    glideSlopeModel.setSelected(currentlySelected);
            } else
            if(event.getPropertyName().equals("latLon"))
                firePropertyChange("dmeLatLon", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("alt"))
                firePropertyChange("dmeAlt", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("altMeasure"))
                firePropertyChange("dmeAltMeasure", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("range"))
                firePropertyChange("dmeRange", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("rangeMeasure"))
                firePropertyChange("dmeRangeMeasure", event.getOldValue(), event.getNewValue());
        } else
        if(event.getSource() == glideSlopeModel)
            if(event.getPropertyName().equals("currentlySelected"))
            {
                currentlySelected = ((Boolean)event.getNewValue()).booleanValue();
                if(dmeModel != null)
                    dmeModel.setSelected(currentlySelected);
            } else
            if(event.getPropertyName().equals("latLon"))
                firePropertyChange("gsLatLon", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("altMeasure"))
                firePropertyChange("gsAltMeasure", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("rangeMeasure"))
                firePropertyChange("gsRangeMeasure", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("alt"))
                firePropertyChange("gsAlt", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("range"))
                firePropertyChange("gsRange", event.getOldValue(), event.getNewValue());
            else
            if(event.getPropertyName().equals("pitch"))
                firePropertyChange("gsPitch", event.getOldValue(), event.getNewValue());
    }

    public Object clone()
    {
        ILSModel model = new ILSModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setRangeMeasure(getRangeMeasure());
        model.setEnd(getEnd());
        model.setIdent(getIdent());
        model.setName(getName());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setRunway(getRunway());
        model.setAlt(getAlt());
        model.setHeading(getHeading());
        model.setFrequency(getFrequency());
        model.setRange(getRange());
        model.setMagvar(getMagvar());
        model.setWidth(getWidth());
        model.setBackCourse(getBackCourse());
        if(getGlideSlopeModel() != null)
        {
            model.setGlideSlopeModel((GlideSlopeModel)getGlideSlopeModel().clone());
            model.getGlideSlopeModel().setParentModel(model);
        }
        if(getDMEModel() != null)
        {
            model.setDMEModel((DMEModel)getDMEModel().clone());
            model.getDMEModel().setParentModel(model);
        }
        model.setShouldNotify(true);
        return model;
    }

    private GlideSlopeModel glideSlopeModel;
    private java.awt.geom.Point2D.Float point;
    private DMEModel dmeModel;
    private String altMeasure;
    private String rangeMeasure;
    private String end;
    private String ident;
    private String name;
    private LatLonPoint latLon;
    private String runway;
    private float alt;
    private float heading;
    private float frequency;
    private float range;
    private float magvar;
    private float width;
    private boolean backCourse;
}
