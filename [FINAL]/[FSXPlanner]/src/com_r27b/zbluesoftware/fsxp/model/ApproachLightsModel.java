// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApproachLightsModel.java

package com.zbluesoftware.fsxp.model;

import java.awt.Graphics2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class ApproachLightsModel extends BaseModel
{

    public ApproachLightsModel()
    {
        end = "PRIMARY";
        system = "NONE";
        strobes = 0;
        reil = true;
        touchdown = true;
        endLights = true;
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

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        if(shouldNotify && !this.system.equals(system))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSystem", "System", "", this, system, this.system));
        firePropertyChange("system", this.system, system);
        this.system = system;
    }

    public int getStrobes()
    {
        return strobes;
    }

    public void setStrobes(int strobes)
    {
        if(shouldNotify && this.strobes != strobes)
            HistoryListModel.getInstance().addModel(new HistoryModel("setStrobes", "Strobes", "", this, new Integer(strobes), new Integer(this.strobes)));
        firePropertyChange("strobes", new Integer(this.strobes), new Integer(strobes));
        this.strobes = strobes;
    }

    public boolean getReil()
    {
        return reil;
    }

    public void setReil(boolean reil)
    {
        if(shouldNotify && this.reil != reil)
            HistoryListModel.getInstance().addModel(new HistoryModel("setReil", "REI Lights", "", this, new Boolean(reil), new Boolean(this.reil)));
        firePropertyChange("reil", new Boolean(this.reil), new Boolean(reil));
        this.reil = reil;
    }

    public boolean getTouchdown()
    {
        return touchdown;
    }

    public void setTouchdown(boolean touchdown)
    {
        if(shouldNotify && this.touchdown != touchdown)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTouchdown", "Touchdown Lights", "", this, new Boolean(touchdown), new Boolean(this.touchdown)));
        firePropertyChange("touchdown", new Boolean(this.touchdown), new Boolean(touchdown));
        this.touchdown = touchdown;
    }

    public boolean getEndLights()
    {
        return endLights;
    }

    public void setEndLights(boolean endLights)
    {
        if(shouldNotify && this.endLights != endLights)
            HistoryListModel.getInstance().addModel(new HistoryModel("setEndLights", "End Lights", "", this, new Boolean(endLights), new Boolean(this.endLights)));
        firePropertyChange("endLights", new Boolean(this.endLights), new Boolean(endLights));
        this.endLights = endLights;
    }

    public Object clone()
    {
        ApproachLightsModel model = new ApproachLightsModel();
        model.setShouldNotify(false);
        model.setEnd(getEnd());
        model.setSystem(getSystem());
        model.setStrobes(getStrobes());
        model.setReil(getReil());
        model.setTouchdown(getTouchdown());
        model.setEndLights(getEndLights());
        model.setShouldNotify(true);
        return model;
    }

    private String end;
    private String system;
    private int strobes;
    private boolean reil;
    private boolean touchdown;
    private boolean endLights;
}
