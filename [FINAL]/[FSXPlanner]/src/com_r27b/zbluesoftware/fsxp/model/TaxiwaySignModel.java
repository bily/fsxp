// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;
import java.io.PrintStream;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class TaxiwaySignModel extends BaseModel
{

    public TaxiwaySignModel()
    {
        modelName = "Taxiway Sign";
        label = "";
        justification = "LEFT";
        size = "SIZE1";
        heading = 0.0F;
        latLon = new LatLonPoint();
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            java.awt.geom.Point2D.Float point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            float height = 10F * scale;
            float width = 5F * scale;
            java.awt.geom.Point2D.Float topLeft = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)width / 2D), (float)(point.getY() - (double)height / 2D));
            java.awt.geom.Point2D.Float topRight = new java.awt.geom.Point2D.Float((float)(point.getX() + (double)width / 2D), (float)(point.getY() - (double)height / 2D));
            java.awt.geom.Point2D.Float bottomLeft = new java.awt.geom.Point2D.Float((float)(point.getX() - (double)width / 2D), (float)(point.getY() + (double)height / 2D));
            java.awt.geom.Point2D.Float bottomRight = new java.awt.geom.Point2D.Float((float)(point.getX() + (double)width / 2D), (float)(point.getY() + (double)height / 2D));
            if(justification.equals("LEFT"))
            {
                lineTop = new java.awt.geom.Point2D.Float(topRight.x - scale, topRight.y);
                lineBottom = new java.awt.geom.Point2D.Float(bottomRight.x - scale, bottomRight.y);
            } else
            {
                lineTop = new java.awt.geom.Point2D.Float(topLeft.x + scale, topLeft.y);
                lineBottom = new java.awt.geom.Point2D.Float(bottomLeft.x + scale, bottomLeft.y);
            }
            topLeft = Utilities.rotatePoint(point, topLeft, heading);
            bottomLeft = Utilities.rotatePoint(point, bottomLeft, heading);
            topRight = Utilities.rotatePoint(point, topRight, heading);
            bottomRight = Utilities.rotatePoint(point, bottomRight, heading);
            lineTop = Utilities.rotatePoint(point, lineTop, heading);
            lineBottom = Utilities.rotatePoint(point, lineBottom, heading);
            modelPath = new GeneralPath();
            modelPath.moveTo(topLeft.x, topLeft.y);
            modelPath.lineTo(topRight.x, topRight.y);
            modelPath.lineTo(bottomRight.x, bottomRight.y);
            modelPath.lineTo(bottomLeft.x, bottomLeft.y);
            modelPath.lineTo(topLeft.x, topLeft.y);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(Color.yellow);
        g2.fill(modelPath);
        g2.setPaint(Color.black);
        g2.draw(modelPath);
        try
        {
            g2.draw(new java.awt.geom.Line2D.Double(lineTop, lineBottom));
        }
        catch(Exception e)
        {
            System.out.println("----------------");
            System.out.println((new StringBuilder()).append("lineTop: ").append(lineTop).toString());
            System.out.println((new StringBuilder()).append("lineBottom: ").append(lineBottom).toString());
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

    public boolean moveTo(LatLonPoint latLonPoint, double centerX, double centerY)
    {
        setLatLon(latLonPoint);
        return true;
    }

    public boolean isCopyable()
    {
        return true;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        if(shouldNotify && !this.label.equals(label))
            HistoryListModel.getInstance().addModel(new HistoryModel("setLabel", "Label", "", this, label, this.label));
        firePropertyChange("label", this.label, label);
        this.label = label;
    }

    public String getJustification()
    {
        return justification;
    }

    public void setJustification(String justification)
    {
        if(shouldNotify && !this.justification.equals(justification))
            HistoryListModel.getInstance().addModel(new HistoryModel("setJustification", "Justification", "", this, justification, this.justification));
        firePropertyChange("justification", this.justification, justification);
        this.justification = justification;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        if(shouldNotify && !this.size.equals(size))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSize", "Size", "", this, size, this.size));
        firePropertyChange("size", this.size, size);
        this.size = size;
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
        TaxiwaySignModel model = new TaxiwaySignModel();
        model.setShouldNotify(false);
        model.setLabel(getLabel());
        model.setJustification(getJustification());
        model.setSize(getSize());
        model.setLatLon((LatLonPoint)getLatLon().clone());
        model.setHeading(getHeading());
        model.setShouldNotify(true);
        return model;
    }

    private java.awt.geom.Point2D.Float lineTop;
    private java.awt.geom.Point2D.Float lineBottom;
    private String label;
    private String justification;
    private String size;
    private LatLonPoint latLon;
    private float heading;
}
