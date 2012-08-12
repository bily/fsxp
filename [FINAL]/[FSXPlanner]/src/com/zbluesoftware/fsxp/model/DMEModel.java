// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DMEModel.java

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

public class DMEModel extends BaseModel
{

    public DMEModel()
    {
        altMeasure = "M";
        rangeMeasure = "N";
        alt = 0.0F;
        range = 27F;
        latLon = new LatLonPoint();
        displayHeading = 0.0F;
        modelName = "DME";
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            float size = 20F * scale;
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            java.awt.geom.Point2D.Float topLeftPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size, point.y - size), displayHeading);
            java.awt.geom.Point2D.Float topRightPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size, point.y - size), displayHeading);
            java.awt.geom.Point2D.Float bottomLeftPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - size, point.y + size), displayHeading);
            java.awt.geom.Point2D.Float bottomRightPoint = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + size, point.y + size), displayHeading);
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeftPoint.x, topLeftPoint.y);
            modelPath.lineTo(topRightPoint.x, topRightPoint.y);
            modelPath.lineTo(bottomRightPoint.x, bottomRightPoint.y);
            modelPath.lineTo(bottomLeftPoint.x, bottomLeftPoint.y);
            modelPath.lineTo(topLeftPoint.x, topLeftPoint.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(ColorsEngine.getInstance().getDMEColor());
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

    public void setIdent(String ident)
    {
        modelName = (new StringBuilder()).append("DME ").append(ident).toString();
    }

    public void setParentModel(BaseModel parentModel)
    {
        this.parentModel = parentModel;
    }

    public BaseModel getParentModel()
    {
        return parentModel;
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
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

    public void setDisplayHeading(float displayHeading)
    {
        this.displayHeading = displayHeading;
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

    public Object clone()
    {
        DMEModel model = new DMEModel();
        model.setShouldNotify(false);
        model.setAltMeasure(getAltMeasure());
        model.setRangeMeasure(getRangeMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setAlt(getAlt());
        model.setRange(getRange());
        model.setDisplayHeading(displayHeading);
        model.setShouldNotify(true);
        return model;
    }

    private BaseModel parentModel;
    private String altMeasure;
    private String rangeMeasure;
    private LatLonPoint latLon;
    private float alt;
    private float range;
    private float displayHeading;
}
