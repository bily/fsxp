// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NDBModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class NDBModel extends BaseModel
{

    public NDBModel()
    {
        modelName = "NDB";
        type = "COMPASS_POINT";
        altMeasure = "M";
        alt = 0.0F;
        region = "";
        ident = "";
        name = "";
        rangeMeasure = "N";
        frequency = 108F;
        range = 27F;
        magvar = 0.0F;
        latLon = new LatLonPoint();
        terminal = false;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            int size1 = (int)(10F * scale);
            int size2 = (int)(15F * scale);
            int size3 = (int)(20F * scale);
            int size4 = (int)(25F * scale);
            point = Utilities.getPixelsBetweenPoints(centerPoint.getLat(), centerPoint.getLon(), latLon.getLat(), latLon.getLon(), scale);
            modelPath = new GeneralPath();
            modelPath.moveTo(point.x, point.y);
            modelPath.lineTo(point.x, point.y);
            modelPath.append(new java.awt.geom.Ellipse2D.Float(point.x - (float)size1 / 2.0F, point.y - (float)size1 / 2.0F, size1, size1), false);
            if((double)scale > 0.25D)
            {
                modelPath.moveTo(point.x - 1.0F, point.y - 1.0F);
                modelPath.lineTo(point.x + 1.0F, point.y - 1.0F);
                modelPath.lineTo(point.x + 1.0F, point.y + 1.0F);
                modelPath.lineTo(point.x - 1.0F, point.y + 1.0F);
                modelPath.lineTo(point.x - 1.0F, point.y - 1.0F);
            }
            outerCircle = new GeneralPath();
            outerCircle.append(new java.awt.geom.Ellipse2D.Float(point.x - (float)size2 / 2.0F, point.y - (float)size2 / 2.0F, size2, size2), false);
            outerCircle.append(new java.awt.geom.Ellipse2D.Float(point.x - (float)size3 / 2.0F, point.y - (float)size3 / 2.0F, size3, size3), false);
            outerCircle.append(new java.awt.geom.Ellipse2D.Float(point.x - (float)size4 / 2.0F, point.y - (float)size4 / 2.0F, size4, size4), false);
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        g2.setPaint(Color.black);
        g2.draw(modelPath);
        if(scale > 1.0F)
            g2.setStroke(new BasicStroke(1.0F, 0, 1, 0.0F, new float[] {
                2.0F, 4F
            }, 0.0F));
        else
            g2.setStroke(new BasicStroke(1.0F, 0, 1, 0.0F, new float[] {
                1.0F, 1.0F
            }, 0.0F));
        g2.draw(outerCircle);
        g2.setStroke(new BasicStroke());
        int fontSize = (int)((float)Utilities.NDB_FONT.getSize() * scale);
        if(fontSize >= 6)
        {
            g2.setPaint(Color.black);
            g2.setFont(new Font(Utilities.NDB_FONT.getFamily(), Utilities.NDB_FONT.getStyle(), fontSize));
            g2.drawString(ident, point.x, point.y + 25F * scale);
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

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        else
            return outerCircle.contains(x, y);
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
        return (new StringBuilder()).append(modelName).append(" [").append(type).append(" ").append(ident).append("]").toString();
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
        firePropertyChange("ident", this.ident, ident);
        this.ident = ident;
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

    public boolean isTerminal()
    {
        return terminal;
    }

    public void setTerminal(boolean terminal)
    {
        if(shouldNotify && this.terminal != terminal)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTerminal", "Terminal", "", this, new Boolean(terminal), new Boolean(this.terminal)));
        firePropertyChange("terminal", new Boolean(this.terminal), new Boolean(terminal));
        this.terminal = terminal;
    }

    public Object clone()
    {
        NDBModel model = new NDBModel();
        model.setShouldNotify(false);
        model.setType(getType());
        model.setAltMeasure(getAltMeasure());
        model.setLatLon(getLatLon());
        model.setRegion(getRegion());
        model.setIdent(getIdent());
        model.setName(getName());
        model.setRangeMeasure(getRangeMeasure());
        model.setAlt(getAlt());
        model.setFrequency(getFrequency());
        model.setRange(getRange());
        model.setMagvar(getMagvar());
        model.setTerminal(isTerminal());
        model.setShouldNotify(true);
        return model;
    }

    private GeneralPath outerCircle;
    private java.awt.geom.Point2D.Float point;
    private String type;
    private String altMeasure;
    private LatLonPoint latLon;
    private String region;
    private String ident;
    private String name;
    private String rangeMeasure;
    private float alt;
    private float frequency;
    private float range;
    private float magvar;
    private boolean terminal;
}
