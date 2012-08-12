// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPointModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class TaxiwayPointModel extends BaseModel
{

    public TaxiwayPointModel()
    {
        modelName = "Taxiway Point";
        type = "NORMAL";
        orientation = "";
        biasXMeasure = "M";
        biasZMeasure = "M";
        index = 0;
        biasX = 0.0D;
        biasZ = 0.0D;
        latLon = new LatLonPoint();
        taxiwayLineAL = new ArrayList();
        taxiwayWidth = 0.0F;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || pointCircle == null)
        {
            int size = (int)(SettingsEngine.getInstance().getTaxiwayPointDiameter() * scale);
            if(SettingsEngine.getInstance().getTaxiwayPointMeasure().equals("M"))
                size = (int)((float)size * 3.28F);
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            pointCircle = new java.awt.geom.Ellipse2D.Float(point.x - (float)size / 2.0F, point.y - (float)size / 2.0F, size, size);
            if(taxiwayWidth > 0.0F && type.equals("HOLD_SHORT"))
            {
                ilsLines = new GeneralPath();
                float twHeading = taxiwayHeading;
                if(orientation.equals("REVERSE"))
                {
                    twHeading += 180F;
                    if(twHeading > 360F)
                        twHeading -= 360F;
                }
                java.awt.geom.Point2D.Float topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y), twHeading);
                java.awt.geom.Point2D.Float topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y), twHeading);
                java.awt.geom.Point2D.Float topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y + 2.0F * scale), twHeading);
                java.awt.geom.Point2D.Float topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y + 2.0F * scale), twHeading);
                ilsLines.moveTo(topLeft1.x, topLeft1.y);
                ilsLines.lineTo(topRight1.x, topRight1.y);
                ilsLines.moveTo(topLeft2.x, topLeft2.y);
                ilsLines.lineTo(topRight2.x, topRight2.y);
                ilsDashes = new GeneralPath();
                topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y - 2.0F * scale), twHeading);
                topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y - 2.0F * scale), twHeading);
                topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y - 4F * scale), twHeading);
                topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y - 4F * scale), twHeading);
                ilsDashes.moveTo(topLeft1.x, topLeft1.y);
                ilsDashes.lineTo(topRight1.x, topRight1.y);
                ilsDashes.moveTo(topLeft2.x, topLeft2.y);
                ilsDashes.lineTo(topRight2.x, topRight2.y);
            } else
            if(taxiwayWidth > 0.0F && type.equals("ILS_HOLD_SHORT"))
            {
                ilsLines = new GeneralPath();
                java.awt.geom.Point2D.Float topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y + 2.0F * scale), taxiwayHeading);
                java.awt.geom.Point2D.Float topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y + 2.0F * scale), taxiwayHeading);
                java.awt.geom.Point2D.Float topLeft2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - taxiwayWidth * scale, point.y - 2.0F * scale), taxiwayHeading);
                java.awt.geom.Point2D.Float topRight2 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + taxiwayWidth * scale, point.y - 2.0F * scale), taxiwayHeading);
                ilsLines.moveTo(topLeft1.x, topLeft1.y);
                ilsLines.lineTo(topRight1.x, topRight1.y);
                ilsLines.moveTo(topLeft2.x, topLeft2.y);
                ilsLines.lineTo(topRight2.x, topRight2.y);
                for(float i = 0.0F; i < taxiwayWidth; i += 10F)
                {
                    topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - i * scale, point.y + 2.0F * scale), taxiwayHeading);
                    topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - i * scale, point.y - 2.0F * scale), taxiwayHeading);
                    ilsLines.moveTo(topLeft1.x, topLeft1.y);
                    ilsLines.lineTo(topRight1.x, topRight1.y);
                    topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (i - 2.0F) * scale, point.y + 2.0F * scale), taxiwayHeading);
                    topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x - (i - 2.0F) * scale, point.y - 2.0F * scale), taxiwayHeading);
                    ilsLines.moveTo(topLeft1.x, topLeft1.y);
                    ilsLines.lineTo(topRight1.x, topRight1.y);
                    topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + i * scale, point.y + 2.0F * scale), taxiwayHeading);
                    topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + i * scale, point.y - 2.0F * scale), taxiwayHeading);
                    ilsLines.moveTo(topLeft1.x, topLeft1.y);
                    ilsLines.lineTo(topRight1.x, topRight1.y);
                    topLeft1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (i - 2.0F) * scale, point.y + 2.0F * scale), taxiwayHeading);
                    topRight1 = Utilities.rotatePoint(point, new java.awt.geom.Point2D.Float(point.x + (i - 2.0F) * scale, point.y - 2.0F * scale), taxiwayHeading);
                    ilsLines.moveTo(topLeft1.x, topLeft1.y);
                    ilsLines.lineTo(topRight1.x, topRight1.y);
                }

                ilsDashes = null;
            } else
            {
                ilsLines = null;
            }
        }
        if(!pointCircle.intersects(g2.getClipBounds()))
            return;
        if(ilsLines != null && (double)scale >= 0.45000000000000001D)
        {
            g2.setStroke(new BasicStroke(1.0F * scale));
            g2.setPaint(Color.yellow);
            g2.draw(ilsLines);
            if(ilsDashes != null)
            {
                g2.setStroke(new BasicStroke(scale, 0, 2, 1.0F, new float[] {
                    scale * 4F, scale * 2.0F
                }, 0.0F));
                g2.draw(ilsDashes);
                g2.setStroke(Utilities.DEFAULT_STROKE);
            }
        }
        if(type.equals("NORMAL"))
            g2.setPaint(Color.blue);
        else
        if(type.equals("HOLD_SHORT"))
            g2.setPaint(Color.red);
        else
        if(type.equals("ILS_HOLD_SHORT"))
            g2.setPaint(Color.yellow);
        else
        if(type.equals("HOLD_SHORT_NO_DRAW"))
            g2.setPaint(Color.red);
        else
        if(type.equals("ILS_HOLD_SHORT_NO_DRAW"))
            g2.setPaint(Color.yellow);
        else
            g2.setPaint(Color.green);
        g2.fill(pointCircle);
        if(currentlySelected)
            if(SettingsEngine.getInstance().isSelectedItemOutlined())
            {
                g2.setStroke(new BasicStroke(4F));
                g2.setPaint(ColorsEngine.getInstance().getSelectedColor());
                g2.draw(pointCircle);
                g2.setStroke(Utilities.DEFAULT_STROKE);
            } else
            {
                Color selectedColor = ColorsEngine.getInstance().getSelectedColor();
                g2.setPaint(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), 196));
                g2.fill(pointCircle);
            }
    }

    public void addTaxiwayLines(HashMap hashMap)
    {
        int i = taxiwayLineAL.size() - 1;
        do
        {
            if(i < 0)
                break;
            if(((String)((HashMap)taxiwayLineAL.get(i)).get("name")).equals((String)hashMap.get("name")))
            {
                taxiwayLineAL.remove(i);
                break;
            }
            i--;
        } while(true);
        taxiwayLineAL.add(hashMap);
    }

    public void clearTaxiwayLines()
    {
        taxiwayLineAL.clear();
    }

    public boolean isWithinObject(int x, int y)
    {
        if(pointCircle == null)
            return false;
        else
            return pointCircle.contains(x, y);
    }

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(index).append("]").toString();
    }

    public void setTaxiwayWidth(float taxiwayWidth)
    {
        this.taxiwayWidth = taxiwayWidth;
    }

    public void setTaxiwayHeading(float taxiwayHeading)
    {
        this.taxiwayHeading = taxiwayHeading;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        if(shouldNotify && !this.type.equals(type))
            HistoryListModel.getInstance().addModel(new HistoryModel("setType", "Type", "", this, type, this.type));
        if(this.type.equals("NORMAL") && type.indexOf("HOLD_SHORT") >= 0)
            setOrientation("FORWARD");
        firePropertyChange("type", this.type, type);
        this.type = type;
    }

    public String getOrientation()
    {
        return orientation;
    }

    public void setOrientation(String orientation)
    {
        if(shouldNotify && !this.orientation.equals(orientation))
            HistoryListModel.getInstance().addModel(new HistoryModel("setOrientation", "Orientation", "", this, orientation, this.orientation));
        firePropertyChange("orientation", this.orientation, orientation);
        this.orientation = orientation;
    }

    public String getBiasXMeasure()
    {
        return biasXMeasure;
    }

    public void setBiasXMeasure(String biasXMeasure)
    {
        if(shouldNotify && !this.biasXMeasure.equals(biasXMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasXMeasure", "Bias X Measure", "", this, biasXMeasure, this.biasXMeasure));
        this.biasXMeasure = biasXMeasure;
    }

    public String getBiasZMeasure()
    {
        return biasZMeasure;
    }

    public void setBiasZMeasure(String biasZMeasure)
    {
        if(shouldNotify && !this.biasZMeasure.equals(biasZMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasZMeasure", "Bias Z Measure", "", this, biasZMeasure, this.biasZMeasure));
        this.biasZMeasure = biasZMeasure;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
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

    public double getBiasX()
    {
        return biasX;
    }

    public void setBiasX(double biasX)
    {
        if(shouldNotify && this.biasX != biasX)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasX", "Bias X", "", this, new Double(biasX), new Double(this.biasX)));
        this.biasX = biasX;
    }

    public double getBiasZ()
    {
        return biasZ;
    }

    public void setBiasZ(double biasZ)
    {
        if(shouldNotify && this.biasZ != biasZ)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasZ", "Bias Z", "", this, new Double(biasZ), new Double(this.biasZ)));
        this.biasZ = biasZ;
    }

    public Object clone()
    {
        TaxiwayPointModel model = new TaxiwayPointModel();
        model.setShouldNotify(false);
        model.setType(getType());
        model.setOrientation(getOrientation());
        model.setBiasXMeasure(getBiasXMeasure());
        model.setBiasZMeasure(getBiasZMeasure());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setIndex(getIndex());
        model.setBiasX(getBiasX());
        model.setBiasZ(getBiasZ());
        model.setShouldNotify(true);
        return model;
    }

    private ArrayList taxiwayLineAL;
    private Ellipse2D pointCircle;
    private GeneralPath ilsLines;
    private GeneralPath ilsDashes;
    private String type;
    private String orientation;
    private String biasXMeasure;
    private String biasZMeasure;
    private LatLonPoint latLon;
    private int index;
    private float taxiwayWidth;
    private float taxiwayHeading;
    private double biasX;
    private double biasZ;
}
