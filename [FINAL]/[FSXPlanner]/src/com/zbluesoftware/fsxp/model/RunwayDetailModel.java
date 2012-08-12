// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RunwayDetailModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import java.awt.Graphics2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class RunwayDetailModel extends BaseModel
{

    public RunwayDetailModel()
    {
        end = "PRIMARY";
        surface = SettingsEngine.getInstance().getOffsetSurface();
        lengthMeasure = SettingsEngine.getInstance().getOffsetLengthMeasure();
        widthMeasure = SettingsEngine.getInstance().getOffsetWidthMeasure();
        length = SettingsEngine.getInstance().getOffsetLength();
        width = SettingsEngine.getInstance().getOffsetWidth();
        modelName = "Runway Details";
    }

    public void paint(Graphics2D graphics2d, boolean flag)
    {
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

    public Object clone()
    {
        RunwayDetailModel model = new RunwayDetailModel();
        model.setShouldNotify(false);
        model.setEnd(getEnd());
        model.setSurface(getSurface());
        model.setLengthMeasure(getLengthMeasure());
        model.setWidthMeasure(getWidthMeasure());
        model.setLength(getLength());
        model.setWidth(getWidth());
        model.setShouldNotify(true);
        return model;
    }

    private String end;
    private String surface;
    private String lengthMeasure;
    private String widthMeasure;
    private float length;
    private float width;
}
