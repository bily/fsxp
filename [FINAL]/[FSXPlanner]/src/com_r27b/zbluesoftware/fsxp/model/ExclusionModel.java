// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExclusionModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, VertexModel, HistoryModel, HistoryListModel

public class ExclusionModel extends BaseModel
{

    public ExclusionModel()
    {
        vertex1 = new VertexModel();
        vertex2 = new VertexModel();
        excludeAllObjects = true;
        excludeBeaconObjects = false;
        excludeEffectObjects = false;
        excludeExtrusionBridgeObjects = false;
        excludeGenericBuildingObjects = false;
        excludeLibraryObjects = false;
        excludeTaxiwaySignObjects = false;
        excludeTriggerObjects = false;
        excludeWindsockObjects = false;
        displayBorderOnly = false;
        vertexSelected = false;
        modelName = "Exclusion Rectangle";
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            java.awt.geom.Point2D.Float vertex1Point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), vertex1.getLatLon().getLat(), vertex1.getLatLon().getLon(), scale);
            vertex1.setCurrentPoint(vertex1Point);
            java.awt.geom.Point2D.Float vertex2Point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), vertex2.getLatLon().getLat(), vertex2.getLatLon().getLon(), scale);
            vertex2.setCurrentPoint(vertex2Point);
            java.awt.geom.Point2D.Float topLeft = new java.awt.geom.Point2D.Float(Math.min(vertex1Point.x, vertex2Point.x), Math.min(vertex1Point.y, vertex2Point.y));
            java.awt.geom.Point2D.Float bottomRight = new java.awt.geom.Point2D.Float(Math.max(vertex1Point.x, vertex2Point.x), Math.max(vertex1Point.y, vertex2Point.y));
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeft.x, topLeft.y);
            modelPath.lineTo(bottomRight.x, topLeft.y);
            modelPath.lineTo(bottomRight.x, bottomRight.y);
            modelPath.lineTo(topLeft.x, bottomRight.y);
            modelPath.lineTo(topLeft.x, topLeft.y);
            if(displayBorderOnly)
            {
                interiorPath = new GeneralPath();
                interiorPath.setWindingRule(0);
                interiorPath.moveTo(topLeft.x, topLeft.y);
                interiorPath.lineTo(bottomRight.x, topLeft.y);
                interiorPath.lineTo(bottomRight.x, bottomRight.y);
                interiorPath.lineTo(topLeft.x, bottomRight.y);
                interiorPath.lineTo(topLeft.x, topLeft.y);
                float interiorWidth = scale * 30F;
                interiorPath.moveTo(topLeft.x + interiorWidth, topLeft.y + interiorWidth);
                interiorPath.lineTo(bottomRight.x - interiorWidth, topLeft.y + interiorWidth);
                interiorPath.lineTo(bottomRight.x - interiorWidth, bottomRight.y - interiorWidth);
                interiorPath.lineTo(topLeft.x + interiorWidth, bottomRight.y - interiorWidth);
                interiorPath.lineTo(topLeft.x + interiorWidth, topLeft.y + interiorWidth);
            } else
            {
                interiorPath = modelPath;
            }
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        if(currentlySelected)
        {
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                Color exclusionColor = ColorsEngine.getInstance().getExclusionColor();
                g2.setPaint(new Color(exclusionColor.getRed(), exclusionColor.getGreen(), exclusionColor.getBlue(), 128));
                g2.fill(interiorPath);
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(modelPath);
                g2.setStroke(new BasicStroke());
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(interiorPath);
                g2.setPaint(ColorsEngine.getInstance().getExclusionColor());
                g2.draw(modelPath);
            }
            g2.setPaint(new Color(255, 0, 0));
            int fontSize = Math.max(6, (int)(10F * scale));
            g2.setFont(new Font("Arial", 0, fontSize));
            int size = (int)(35F * scale);
            g2.setPaint(new Color(255, 0, 0));
            g2.fill(new java.awt.geom.Ellipse2D.Double(vertex1.getCurrentPoint().x - (float)size / 2.0F, vertex1.getCurrentPoint().y - (float)size / 2.0F, size, size));
            g2.setPaint(new Color(0, 0, 204));
            g2.draw(new java.awt.geom.Ellipse2D.Double(vertex1.getCurrentPoint().x - (float)size / 2.0F, vertex1.getCurrentPoint().y - (float)size / 2.0F, size, size));
            g2.drawString("1", vertex1.getCurrentPoint().x, vertex1.getCurrentPoint().y + (float)fontSize);
            g2.setPaint(new Color(255, 0, 0));
            g2.fill(new java.awt.geom.Ellipse2D.Double(vertex2.getCurrentPoint().x - (float)size / 2.0F, vertex2.getCurrentPoint().y - (float)size / 2.0F, size, size));
            g2.setPaint(new Color(0, 0, 204));
            g2.draw(new java.awt.geom.Ellipse2D.Double(vertex2.getCurrentPoint().x - (float)size / 2.0F, vertex2.getCurrentPoint().y - (float)size / 2.0F, size, size));
            g2.drawString("2", vertex2.getCurrentPoint().x, vertex2.getCurrentPoint().y + (float)fontSize);
        } else
        {
            Color exclusionColor = ColorsEngine.getInstance().getExclusionColor();
            g2.setPaint(new Color(exclusionColor.getRed(), exclusionColor.getGreen(), exclusionColor.getBlue(), 128));
            g2.fill(interiorPath);
            g2.setPaint(ColorsEngine.getInstance().getExclusionColor());
            g2.draw(modelPath);
        }
    }

    public boolean moveTo(LatLonPoint latLonPoint, double oldX, double oldY)
    {
        if(vertexSelected)
            return false;
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), oldX, oldY, scale);
        LatLonPoint exclusionCenter = getCenterLatLon();
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        vertex1.adjustLatLon(latDifference, lonDifference);
        vertex2.adjustLatLon(latDifference, lonDifference);
        if(shouldNotify)
            HistoryListModel.getInstance().addModel(new HistoryModel("undoMoveTo", "getCenterLatLon", "Position", "", this, exclusionCenter, getCenterLatLon()));
        return true;
    }

    public void undoMoveTo(LatLonPoint oldLatLon)
    {
        LatLonPoint currentLatLon = getCenterLatLon();
        double latDifference = oldLatLon.getLat() - currentLatLon.getLat();
        double lonDifference = oldLatLon.getLon() - currentLatLon.getLon();
        vertex1.adjustLatLon(latDifference, lonDifference);
        vertex2.adjustLatLon(latDifference, lonDifference);
    }

    public void moveCenterTo(LatLonPoint latLonPoint)
    {
        double centerX = (double)(vertex1.getCurrentPoint().x + vertex2.getCurrentPoint().x) / 2D;
        double centerY = (double)(vertex1.getCurrentPoint().y + vertex2.getCurrentPoint().y) / 2D;
        LatLonPoint modelLatLonPoint = Utilities.getLatLonForPixel(centerPoint.getLat(), centerPoint.getLon(), centerX, centerY, scale);
        double latDifference = latLonPoint.getLat() - modelLatLonPoint.getLat();
        double lonDifference = latLonPoint.getLon() - modelLatLonPoint.getLon();
        vertex1.adjustLatLon(latDifference, lonDifference);
        vertex2.adjustLatLon(latDifference, lonDifference);
    }

    public LatLonPoint getCenterLatLon()
    {
        return new LatLonPoint((vertex1.getLatLon().getLat() + vertex2.getLatLon().getLat()) / 2D, (vertex1.getLatLon().getLon() + vertex2.getLatLon().getLon()) / 2D);
    }

    public VertexModel isWithinVertex(int x, int y)
    {
        float size = 17.5F * scale;
        if((float)x >= vertex1.getCurrentPoint().x - size && (float)x <= vertex1.getCurrentPoint().x + size && (float)y >= vertex1.getCurrentPoint().y - size && (float)y <= vertex1.getCurrentPoint().y + size)
            return vertex1;
        if((float)x >= vertex2.getCurrentPoint().x - size && (float)x <= vertex2.getCurrentPoint().x + size && (float)y >= vertex2.getCurrentPoint().y - size && (float)y <= vertex2.getCurrentPoint().y + size)
            return vertex2;
        else
            return null;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(interiorPath == null)
        {
            return false;
        } else
        {
            float size = 17.5F * scale;
            boolean within = interiorPath.contains(x, y);
            within = within || (float)x >= vertex1.getCurrentPoint().x - size && (float)x <= vertex1.getCurrentPoint().x + size && (float)y >= vertex1.getCurrentPoint().y - size && (float)y <= vertex1.getCurrentPoint().y + size;
            within = within || (float)x >= vertex2.getCurrentPoint().x - size && (float)x <= vertex2.getCurrentPoint().x + size && (float)y >= vertex2.getCurrentPoint().y - size && (float)y <= vertex2.getCurrentPoint().y + size;
            return within;
        }
    }

    public boolean isCopyable()
    {
        return true;
    }

    public void displayVertexModel(VertexModel vertexModel)
    {
        vertexSelected = vertexModel != null;
    }

    public VertexModel getVertex1()
    {
        return vertex1;
    }

    public void setVertex1(VertexModel vertex1)
    {
        this.vertex1 = vertex1;
    }

    public VertexModel getVertex2()
    {
        return vertex2;
    }

    public void setVertex2(VertexModel vertex2)
    {
        this.vertex2 = vertex2;
    }

    public boolean getExcludeAllObjects()
    {
        return excludeAllObjects;
    }

    public void setExcludeAllObjects(boolean excludeAllObjects)
    {
        if(shouldNotify && this.excludeAllObjects != excludeAllObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeAllObjects", "Exclude All Objects", "", this, new Boolean(excludeAllObjects), new Boolean(this.excludeAllObjects)));
        firePropertyChange("excludeAllObjects", new Boolean(this.excludeAllObjects), new Boolean(excludeAllObjects));
        this.excludeAllObjects = excludeAllObjects;
    }

    public boolean getExcludeBeaconObjects()
    {
        return excludeBeaconObjects;
    }

    public void setExcludeBeaconObjects(boolean excludeBeaconObjects)
    {
        if(shouldNotify && this.excludeBeaconObjects != excludeBeaconObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeBeaconObjects", "Exclude Beacon Objects", "", this, new Boolean(excludeBeaconObjects), new Boolean(this.excludeBeaconObjects)));
        firePropertyChange("excludeBeaconObjects", new Boolean(this.excludeBeaconObjects), new Boolean(excludeBeaconObjects));
        this.excludeBeaconObjects = excludeBeaconObjects;
    }

    public boolean getExcludeEffectObjects()
    {
        return excludeEffectObjects;
    }

    public void setExcludeEffectObjects(boolean excludeEffectObjects)
    {
        if(shouldNotify && this.excludeEffectObjects != excludeEffectObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeEffectObjects", "Exclude Effect Objects", "", this, new Boolean(excludeEffectObjects), new Boolean(this.excludeEffectObjects)));
        firePropertyChange("excludeEffectObjects", new Boolean(this.excludeEffectObjects), new Boolean(excludeEffectObjects));
        this.excludeEffectObjects = excludeEffectObjects;
    }

    public boolean getExcludeExtrusionBridgeObjects()
    {
        return excludeExtrusionBridgeObjects;
    }

    public void setExcludeExtrusionBridgeObjects(boolean excludeExtrusionBridgeObjects)
    {
        if(shouldNotify && this.excludeExtrusionBridgeObjects != excludeExtrusionBridgeObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeExtrusionBridgeObjects", "Exclude Bridge Objects", "", this, new Boolean(excludeExtrusionBridgeObjects), new Boolean(this.excludeExtrusionBridgeObjects)));
        firePropertyChange("excludeExtrusionBridgeObjects", new Boolean(this.excludeExtrusionBridgeObjects), new Boolean(excludeExtrusionBridgeObjects));
        this.excludeExtrusionBridgeObjects = excludeExtrusionBridgeObjects;
    }

    public boolean getExcludeGenericBuildingObjects()
    {
        return excludeGenericBuildingObjects;
    }

    public void setExcludeGenericBuildingObjects(boolean excludeGenericBuildingObjects)
    {
        if(shouldNotify && this.excludeGenericBuildingObjects != excludeGenericBuildingObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeGenericBuildingObjects", "Exclude Building Objects", "", this, new Boolean(excludeGenericBuildingObjects), new Boolean(this.excludeGenericBuildingObjects)));
        firePropertyChange("excludeGenericBuildingObjects", new Boolean(this.excludeGenericBuildingObjects), new Boolean(excludeGenericBuildingObjects));
        this.excludeGenericBuildingObjects = excludeGenericBuildingObjects;
    }

    public boolean getExcludeLibraryObjects()
    {
        return excludeLibraryObjects;
    }

    public void setExcludeLibraryObjects(boolean excludeLibraryObjects)
    {
        if(shouldNotify && this.excludeLibraryObjects != excludeLibraryObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeLibraryObjects", "Exclude Library Objects", "", this, new Boolean(excludeLibraryObjects), new Boolean(this.excludeLibraryObjects)));
        firePropertyChange("excludeLibraryObjects", new Boolean(this.excludeLibraryObjects), new Boolean(excludeLibraryObjects));
        this.excludeLibraryObjects = excludeLibraryObjects;
    }

    public boolean getExcludeTaxiwaySignObjects()
    {
        return excludeTaxiwaySignObjects;
    }

    public void setExcludeTaxiwaySignObjects(boolean excludeTaxiwaySignObjects)
    {
        if(shouldNotify && this.excludeTaxiwaySignObjects != excludeTaxiwaySignObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeTaxiwaySignObjects", "Exclude Taxiway Signs", "", this, new Boolean(excludeTaxiwaySignObjects), new Boolean(this.excludeTaxiwaySignObjects)));
        firePropertyChange("excludeTaxiwaySignObjects", new Boolean(this.excludeTaxiwaySignObjects), new Boolean(excludeTaxiwaySignObjects));
        this.excludeTaxiwaySignObjects = excludeTaxiwaySignObjects;
    }

    public boolean getExcludeTriggerObjects()
    {
        return excludeTriggerObjects;
    }

    public void setExcludeTriggerObjects(boolean excludeTriggerObjects)
    {
        if(shouldNotify && this.excludeTriggerObjects != excludeTriggerObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeTriggerObjects", "Exclude Trigger Objects", "", this, new Boolean(excludeTriggerObjects), new Boolean(this.excludeTriggerObjects)));
        firePropertyChange("excludeTriggerObjects", new Boolean(this.excludeTriggerObjects), new Boolean(excludeTriggerObjects));
        this.excludeTriggerObjects = excludeTriggerObjects;
    }

    public boolean getExcludeWindsockObjects()
    {
        return excludeWindsockObjects;
    }

    public void setExcludeWindsockObjects(boolean excludeWindsockObjects)
    {
        if(shouldNotify && this.excludeWindsockObjects != excludeWindsockObjects)
            HistoryListModel.getInstance().addModel(new HistoryModel("setExcludeWindsockObjects", "Exclude Windsocks", "", this, new Boolean(excludeWindsockObjects), new Boolean(this.excludeWindsockObjects)));
        firePropertyChange("excludeWindsockObjects", new Boolean(this.excludeWindsockObjects), new Boolean(excludeWindsockObjects));
        this.excludeWindsockObjects = excludeWindsockObjects;
    }

    public boolean getDisplayBorderOnly()
    {
        return displayBorderOnly;
    }

    public void setDisplayBorderOnly(boolean displayBorderOnly)
    {
        if(shouldNotify && this.displayBorderOnly != displayBorderOnly)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDisplayBorderOnly", "Display Border Only", "", this, new Boolean(displayBorderOnly), new Boolean(this.displayBorderOnly)));
        firePropertyChange("displayBorderOnly", new Boolean(this.displayBorderOnly), new Boolean(displayBorderOnly));
        this.displayBorderOnly = displayBorderOnly;
    }

    public Object clone()
    {
        ExclusionModel model = new ExclusionModel();
        model.setShouldNotify(false);
        model.setScale(scale);
        model.setCenterPoint((LatLonPoint)centerPoint.clone());
        model.setVertex1((VertexModel)getVertex1().clone());
        model.setVertex2((VertexModel)getVertex2().clone());
        model.setExcludeAllObjects(getExcludeAllObjects());
        model.setExcludeBeaconObjects(getExcludeBeaconObjects());
        model.setExcludeEffectObjects(getExcludeEffectObjects());
        model.setExcludeExtrusionBridgeObjects(getExcludeExtrusionBridgeObjects());
        model.setExcludeGenericBuildingObjects(getExcludeGenericBuildingObjects());
        model.setExcludeLibraryObjects(getExcludeLibraryObjects());
        model.setExcludeTaxiwaySignObjects(getExcludeTaxiwaySignObjects());
        model.setExcludeTriggerObjects(getExcludeTriggerObjects());
        model.setExcludeWindsockObjects(getExcludeWindsockObjects());
        model.setShouldNotify(true);
        return model;
    }

    private GeneralPath interiorPath;
    private VertexModel vertex1;
    private VertexModel vertex2;
    private boolean excludeAllObjects;
    private boolean excludeBeaconObjects;
    private boolean excludeEffectObjects;
    private boolean excludeExtrusionBridgeObjects;
    private boolean excludeGenericBuildingObjects;
    private boolean excludeLibraryObjects;
    private boolean excludeTaxiwaySignObjects;
    private boolean excludeTriggerObjects;
    private boolean excludeWindsockObjects;
    private boolean displayBorderOnly;
    private boolean vertexSelected;
}
