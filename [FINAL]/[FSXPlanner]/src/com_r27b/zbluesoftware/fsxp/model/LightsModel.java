// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LightsModel.java

package com.zbluesoftware.fsxp.model;

import java.awt.Graphics2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class LightsModel extends BaseModel
{

    public LightsModel()
    {
        center = "NONE";
        edge = "NONE";
        centerRed = false;
        modelName = "Runway Lights";
    }

    public void paint(Graphics2D graphics2d, boolean flag)
    {
    }

    public String getCenter()
    {
        return center;
    }

    public void setCenter(String center)
    {
        if(shouldNotify && !this.center.equals(center))
            HistoryListModel.getInstance().addModel(new HistoryModel("setCenter", "Center", "", this, center, this.center));
        firePropertyChange("center", this.center, center);
        this.center = center;
    }

    public String getEdge()
    {
        return edge;
    }

    public void setEdge(String edge)
    {
        if(shouldNotify && !this.edge.equals(edge))
            HistoryListModel.getInstance().addModel(new HistoryModel("setEdge", "Edge", "", this, edge, this.edge));
        firePropertyChange("edge", this.edge, edge);
        this.edge = edge;
    }

    public boolean getCenterRed()
    {
        return centerRed;
    }

    public void setCenterRed(boolean centerRed)
    {
        if(shouldNotify && this.centerRed != centerRed)
            HistoryListModel.getInstance().addModel(new HistoryModel("setCenterRed", "Center Red", "", this, new Boolean(centerRed), new Boolean(this.centerRed)));
        firePropertyChange("centerRed", new Boolean(this.centerRed), new Boolean(centerRed));
        this.centerRed = centerRed;
    }

    public Object clone()
    {
        LightsModel model = new LightsModel();
        model.setShouldNotify(false);
        model.setCenter(getCenter());
        model.setEdge(getEdge());
        model.setCenterRed(getCenterRed());
        model.setShouldNotify(true);
        return model;
    }

    private String center;
    private String edge;
    private boolean centerRed;
}
