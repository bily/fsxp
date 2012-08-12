// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VertexModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.util.LatLonPoint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class VertexModel extends BaseModel
{

    public VertexModel()
    {
        biasXMeasure = "M";
        biasZMeasure = "M";
        biasX = 0.0F;
        biasZ = 0.0F;
        latLon = new LatLonPoint();
        currentPoint = new java.awt.geom.Point2D.Float(0.0F, 0.0F);
        modelName = "Vertex";
    }

    public void paint(Graphics2D graphics2d, boolean flag)
    {
    }

    public java.awt.geom.Point2D.Float getCurrentPoint()
    {
        return currentPoint;
    }

    public void setCurrentPoint(java.awt.geom.Point2D.Float currentPoint)
    {
        this.currentPoint = currentPoint;
    }

    public String getBiasXMeasure()
    {
        return biasXMeasure;
    }

    public void setBiasXMeasure(String biasXMeasure)
    {
        this.biasXMeasure = biasXMeasure;
    }

    public String getBiasZMeasure()
    {
        return biasZMeasure;
    }

    public void setBiasZMeasure(String biasZMeasure)
    {
        this.biasZMeasure = biasZMeasure;
    }

    public float getBiasX()
    {
        return biasX;
    }

    public void setBiasX(float biasX)
    {
        this.biasX = biasX;
    }

    public float getBiasZ()
    {
        return biasZ;
    }

    public void setBiasZ(float biasZ)
    {
        this.biasZ = biasZ;
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

    public void adjustLatLon(double latDifference, double lonDifference)
    {
        latLon.adjustLat(latDifference);
        latLon.adjustLon(lonDifference);
    }

    public void setCurrentlySelected(boolean currentlySelected)
    {
        this.currentlySelected = currentlySelected;
    }

    public Object clone()
    {
        VertexModel model = new VertexModel();
        model.setShouldNotify(false);
        model.setBiasXMeasure(getBiasXMeasure());
        model.setBiasZMeasure(getBiasZMeasure());
        model.setBiasX(getBiasX());
        model.setBiasZ(getBiasZ());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setCurrentPoint(new java.awt.geom.Point2D.Float(getCurrentPoint().x, getCurrentPoint().y));
        model.setShouldNotify(true);
        return model;
    }

    private String biasXMeasure;
    private String biasZMeasure;
    private LatLonPoint latLon;
    private java.awt.geom.Point2D.Float currentPoint;
    private float biasX;
    private float biasZ;
}
