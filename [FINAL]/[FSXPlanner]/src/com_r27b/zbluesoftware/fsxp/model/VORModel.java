// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VORModel.java

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
//            BaseModel, DMEModel, HistoryModel, SelectedItem, 
//            HistoryListModel

public class VORModel extends BaseModel
    implements PropertyChangeListener
{

    public VORModel()
    {
        modelName = "VOR";
        dmeModel = new DMEModel();
        latLon = new LatLonPoint();
        altMeasure = "M";
        type = "LOW";
        region = "";
        ident = "";
        alt = 0.0F;
        name = "";
        rangeMeasure = "N";
        frequency = 108F;
        range = 27F;
        magvar = 0.0F;
        dmeOnly = false;
        dme = false;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(dme)
        {
            dmeModel.setCenterPoint(centerPoint);
            dmeModel.setScale(scale);
            dmeModel.paint(g2, recreate);
        }
        if(recreate || modelPath == null)
        {
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            java.awt.geom.Point2D.Float startPoint = point;
            java.awt.geom.Point2D.Float topLeftPoint = new java.awt.geom.Point2D.Float((float)((double)point.x - 9D * (double)scale), (float)((double)point.y - 18D * (double)scale));
            java.awt.geom.Point2D.Float topRightPoint = new java.awt.geom.Point2D.Float((float)((double)point.x + 9D * (double)scale), (float)((double)point.y - 18D * (double)scale));
            java.awt.geom.Point2D.Float bottomLeftPoint = new java.awt.geom.Point2D.Float((float)((double)point.x - 9D * (double)scale), (float)((double)point.y + 18D * (double)scale));
            java.awt.geom.Point2D.Float bottomRightPoint = new java.awt.geom.Point2D.Float((float)((double)point.x + 9D * (double)scale), (float)((double)point.y + 18D * (double)scale));
            java.awt.geom.Point2D.Float leftPoint = new java.awt.geom.Point2D.Float((float)((double)point.x - 18D * (double)scale), point.y);
            java.awt.geom.Point2D.Float rightPoint = new java.awt.geom.Point2D.Float((float)((double)point.x + 18D * (double)scale), point.y);
            modelPath = new GeneralPath();
            if(!dmeOnly)
            {
                modelPath.moveTo(topLeftPoint.x, topLeftPoint.y);
                modelPath.lineTo(topRightPoint.x, topRightPoint.y);
                modelPath.lineTo(rightPoint.x, rightPoint.y);
                modelPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
                modelPath.lineTo(bottomLeftPoint.x, bottomLeftPoint.y);
                modelPath.lineTo(leftPoint.x, leftPoint.y);
                modelPath.lineTo(topLeftPoint.x, topLeftPoint.y);
            }
            if((double)scale > 0.25D)
            {
                modelPath.moveTo(startPoint.x - 1.0F, startPoint.y - 1.0F);
                modelPath.lineTo(startPoint.x + 1.0F, startPoint.y - 1.0F);
                modelPath.lineTo(startPoint.x + 1.0F, startPoint.y + 1.0F);
                modelPath.lineTo(startPoint.x - 1.0F, startPoint.y + 1.0F);
                modelPath.lineTo(startPoint.x - 1.0F, startPoint.y - 1.0F);
            } else
            {
                modelPath.moveTo(startPoint.x, startPoint.y);
                modelPath.lineTo(startPoint.x, startPoint.y);
            }
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(Color.black);
        g2.draw(modelPath);
        int fontSize = (int)((float)Utilities.VOR_FONT.getSize() * scale);
        if(fontSize >= 6)
        {
            g2.setPaint(Color.black);
            g2.setFont(new Font(Utilities.VOR_FONT.getFamily(), Utilities.VOR_FONT.getStyle(), fontSize));
            g2.drawString(ident, point.x, point.y + 18F * scale);
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
    }

    public void setSelected(boolean currentlySelected)
    {
        this.currentlySelected = currentlySelected;
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
        modelName = (new StringBuilder()).append(type).append(" VOR ").append(ident).toString();
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
        modelName = (new StringBuilder()).append(type).append(" VOR ").append(ident).toString();
        dmeModel.setIdent(ident);
        firePropertyChange("ident", this.ident, ident);
        this.ident = ident;
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

    public boolean getDmeOnly()
    {
        return dmeOnly;
    }

    public void setDmeOnly(boolean dmeOnly)
    {
        if(shouldNotify && this.dmeOnly != dmeOnly)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDmeOnly", "DME Only", "", this, new Boolean(dmeOnly), new Boolean(this.dmeOnly)));
        firePropertyChange("dmeOnly", new Boolean(this.dmeOnly), new Boolean(dmeOnly));
        this.dmeOnly = dmeOnly;
    }

    public boolean getDme()
    {
        return dme;
    }

    public void setDme(boolean dme)
    {
        if(shouldNotify && this.dme != dme)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDme", "DME", "", this, new Boolean(dme), new Boolean(this.dme)));
        firePropertyChange("dme", new Boolean(this.dme), new Boolean(dme));
        this.dme = dme;
    }

    public DMEModel getDMEModel()
    {
        return dmeModel;
    }

    public void setDMEModel(DMEModel dmeModel)
    {
        if(dmeModel != null)
        {
            dmeModel.setIdent(ident);
            dmeModel.setParentModel(this);
            dmeModel.addPropertyChangeListener(this);
        }
        this.dmeModel = dmeModel;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if(event.getSource() == dmeModel)
            if(event.getPropertyName().equals("currentlySelected"))
                currentlySelected = ((Boolean)event.getNewValue()).booleanValue();
            else
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
    }

    public Object clone()
    {
        VORModel model = new VORModel();
        model.setShouldNotify(false);
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAltMeasure(getAltMeasure());
        model.setType(getType());
        model.setRegion(getRegion());
        model.setIdent(getIdent());
        model.setName(getName());
        model.setRangeMeasure(getRangeMeasure());
        model.setAlt(getAlt());
        model.setFrequency(getFrequency());
        model.setRange(getRange());
        model.setMagvar(getMagvar());
        model.setDmeOnly(getDmeOnly());
        model.setDme(getDme());
        if(getDMEModel() != null)
        {
            model.setDMEModel((DMEModel)getDMEModel().clone());
            model.getDMEModel().setParentModel(model);
        }
        model.setShouldNotify(true);
        return model;
    }

    private DMEModel dmeModel;
    private java.awt.geom.Point2D.Float point;
    private LatLonPoint latLon;
    private String altMeasure;
    private String type;
    private String region;
    private String ident;
    private String name;
    private String rangeMeasure;
    private float alt;
    private float frequency;
    private float range;
    private float magvar;
    private boolean dmeOnly;
    private boolean dme;
}
