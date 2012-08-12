// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MarkingsModel.java

package com.zbluesoftware.fsxp.model;

import java.awt.Graphics2D;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class MarkingsModel extends BaseModel
{

    public MarkingsModel()
    {
        modelName = "Runway";
        number = "";
        alternateThreshold = false;
        alternateTouchdown = false;
        alternateFixedDistance = false;
        alternatePrecision = false;
        leadingZeroIdent = false;
        noThresholdEndArrows = false;
        edges = true;
        threshold = true;
        fixed = true;
        touchdown = true;
        dashes = true;
        ident = true;
        precision = true;
        edgePavement = true;
        singleEnd = false;
        primaryClosed = false;
        secondaryClosed = false;
        primaryStol = false;
        secondaryStol = false;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getModelName()
    {
        return (new StringBuilder()).append(modelName).append(" [").append(number).append("]").toString();
    }

    public void paint(Graphics2D graphics2d, boolean flag)
    {
    }

    public boolean getAlternateThreshold()
    {
        return alternateThreshold;
    }

    public void setAlternateThreshold(boolean alternateThreshold)
    {
        if(shouldNotify && this.alternateThreshold != alternateThreshold)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlternateThreshold", "Alternate Threshold", "", this, new Boolean(alternateThreshold), new Boolean(this.alternateThreshold)));
        firePropertyChange("alternateThreshold", new Boolean(this.alternateThreshold), new Boolean(alternateThreshold));
        this.alternateThreshold = alternateThreshold;
    }

    public boolean getAlternateTouchdown()
    {
        return alternateTouchdown;
    }

    public void setAlternateTouchdown(boolean alternateTouchdown)
    {
        if(shouldNotify && this.alternateTouchdown != alternateTouchdown)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlternateTouchdown", "Alternate Touchdown", "", this, new Boolean(alternateTouchdown), new Boolean(this.alternateTouchdown)));
        firePropertyChange("alternateTouchdown", new Boolean(this.alternateTouchdown), new Boolean(alternateTouchdown));
        this.alternateTouchdown = alternateTouchdown;
    }

    public boolean getAlternateFixedDistance()
    {
        return alternateFixedDistance;
    }

    public void setAlternateFixedDistance(boolean alternateFixedDistance)
    {
        if(shouldNotify && this.alternateFixedDistance != alternateFixedDistance)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlternateFixedDistance", "Alternate Fixed Distance", "", this, new Boolean(alternateFixedDistance), new Boolean(this.alternateFixedDistance)));
        firePropertyChange("alternateFixedDistance", new Boolean(this.alternateFixedDistance), new Boolean(alternateFixedDistance));
        this.alternateFixedDistance = alternateFixedDistance;
    }

    public boolean getAlternatePrecision()
    {
        return alternatePrecision;
    }

    public void setAlternatePrecision(boolean alternatePrecision)
    {
        if(shouldNotify && this.alternatePrecision != alternatePrecision)
            HistoryListModel.getInstance().addModel(new HistoryModel("setAlternatePrecision", "Alternate Precision", "", this, new Boolean(alternatePrecision), new Boolean(this.alternatePrecision)));
        firePropertyChange("alternatePrecision", new Boolean(this.alternatePrecision), new Boolean(alternatePrecision));
        this.alternatePrecision = alternatePrecision;
    }

    public boolean getLeadingZeroIdent()
    {
        return leadingZeroIdent;
    }

    public void setLeadingZeroIdent(boolean leadingZeroIdent)
    {
        if(shouldNotify && this.leadingZeroIdent != leadingZeroIdent)
            HistoryListModel.getInstance().addModel(new HistoryModel("setLeadingZeroIndent", "Leading Zero Indent", "", this, new Boolean(leadingZeroIdent), new Boolean(this.leadingZeroIdent)));
        firePropertyChange("leadingZeroIdent", new Boolean(this.leadingZeroIdent), new Boolean(leadingZeroIdent));
        this.leadingZeroIdent = leadingZeroIdent;
    }

    public boolean getNoThresholdEndArrows()
    {
        return noThresholdEndArrows;
    }

    public void setNoThresholdEndArrows(boolean noThresholdEndArrows)
    {
        if(shouldNotify && this.noThresholdEndArrows != noThresholdEndArrows)
            HistoryListModel.getInstance().addModel(new HistoryModel("setNoThresholdEndArrows", "No Threshold End Arrows", "", this, new Boolean(noThresholdEndArrows), new Boolean(this.noThresholdEndArrows)));
        firePropertyChange("noThresholdEndArrows", new Boolean(this.noThresholdEndArrows), new Boolean(noThresholdEndArrows));
        this.noThresholdEndArrows = noThresholdEndArrows;
    }

    public boolean getEdges()
    {
        return edges;
    }

    public void setEdges(boolean edges)
    {
        if(shouldNotify && this.edges != edges)
            HistoryListModel.getInstance().addModel(new HistoryModel("setEdges", "Edge Line", "", this, new Boolean(edges), new Boolean(this.edges)));
        firePropertyChange("edges", new Boolean(this.edges), new Boolean(edges));
        this.edges = edges;
    }

    public boolean getThreshold()
    {
        return threshold;
    }

    public void setThreshold(boolean threshold)
    {
        if(shouldNotify && this.threshold != threshold)
            HistoryListModel.getInstance().addModel(new HistoryModel("setThreshold", "Threshold Makrs", "", this, new Boolean(threshold), new Boolean(this.threshold)));
        firePropertyChange("threshold", new Boolean(this.threshold), new Boolean(threshold));
        this.threshold = threshold;
    }

    public boolean getFixed()
    {
        return fixed;
    }

    public void setFixed(boolean fixed)
    {
        if(shouldNotify && this.fixed != fixed)
            HistoryListModel.getInstance().addModel(new HistoryModel("setFixed", "Fixed Distance Marks", "", this, new Boolean(fixed), new Boolean(this.fixed)));
        firePropertyChange("fixed", new Boolean(this.fixed), new Boolean(fixed));
        this.fixed = fixed;
    }

    public boolean getTouchdown()
    {
        return touchdown;
    }

    public void setTouchdown(boolean touchdown)
    {
        if(shouldNotify && this.touchdown != touchdown)
            HistoryListModel.getInstance().addModel(new HistoryModel("setTouchdown", "Touchdown Marks", "", this, new Boolean(touchdown), new Boolean(this.touchdown)));
        firePropertyChange("touchdown", new Boolean(this.touchdown), new Boolean(touchdown));
        this.touchdown = touchdown;
    }

    public boolean getDashes()
    {
        return dashes;
    }

    public void setDashes(boolean dashes)
    {
        if(shouldNotify && this.dashes != dashes)
            HistoryListModel.getInstance().addModel(new HistoryModel("setDashes", "Dashed Center Line", "", this, new Boolean(dashes), new Boolean(this.dashes)));
        firePropertyChange("dashes", new Boolean(this.dashes), new Boolean(dashes));
        this.dashes = dashes;
    }

    public boolean getIdent()
    {
        return ident;
    }

    public void setIdent(boolean ident)
    {
        if(shouldNotify && this.ident != ident)
            HistoryListModel.getInstance().addModel(new HistoryModel("setIdent", "Display Ident", "", this, new Boolean(ident), new Boolean(this.ident)));
        firePropertyChange("ident", new Boolean(this.ident), new Boolean(ident));
        this.ident = ident;
    }

    public boolean getPrecision()
    {
        return precision;
    }

    public void setPrecision(boolean precision)
    {
        if(shouldNotify && this.precision != precision)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrecision", "Precision Markings", "", this, new Boolean(precision), new Boolean(this.precision)));
        firePropertyChange("precision", new Boolean(this.precision), new Boolean(precision));
        this.precision = precision;
    }

    public boolean getEdgePavement()
    {
        return edgePavement;
    }

    public void setEdgePavement(boolean edgePavement)
    {
        if(shouldNotify && this.edgePavement != edgePavement)
            HistoryListModel.getInstance().addModel(new HistoryModel("setEdgePavement", "Pavement Edges", "", this, new Boolean(edgePavement), new Boolean(this.edgePavement)));
        firePropertyChange("edgePavement", new Boolean(this.edgePavement), new Boolean(edgePavement));
        this.edgePavement = edgePavement;
    }

    public boolean getSingleEnd()
    {
        return singleEnd;
    }

    public void setSingleEnd(boolean singleEnd)
    {
        if(shouldNotify && this.singleEnd != singleEnd)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSingleEnd", "Single Ended", "", this, new Boolean(singleEnd), new Boolean(this.singleEnd)));
        firePropertyChange("singleEnd", new Boolean(this.singleEnd), new Boolean(singleEnd));
        this.singleEnd = singleEnd;
    }

    public boolean getPrimaryClosed()
    {
        return primaryClosed;
    }

    public void setPrimaryClosed(boolean primaryClosed)
    {
        if(shouldNotify && this.primaryClosed != primaryClosed)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryClosed", "Primary End Closed", "", this, new Boolean(primaryClosed), new Boolean(this.primaryClosed)));
        firePropertyChange("primaryClosed", new Boolean(this.primaryClosed), new Boolean(primaryClosed));
        this.primaryClosed = primaryClosed;
    }

    public boolean getSecondaryClosed()
    {
        return secondaryClosed;
    }

    public void setSecondaryClosed(boolean secondaryClosed)
    {
        if(shouldNotify && this.secondaryClosed != secondaryClosed)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryClosed", "Secondary End Closed", "", this, new Boolean(secondaryClosed), new Boolean(this.secondaryClosed)));
        firePropertyChange("secondaryClosed", new Boolean(this.secondaryClosed), new Boolean(secondaryClosed));
        this.secondaryClosed = secondaryClosed;
    }

    public boolean getPrimaryStol()
    {
        return primaryStol;
    }

    public void setPrimaryStol(boolean primaryStol)
    {
        if(shouldNotify && this.primaryStol != primaryStol)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPrimaryStol", "Primary End STOL", "", this, new Boolean(primaryStol), new Boolean(this.primaryStol)));
        firePropertyChange("primaryStol", new Boolean(this.primaryStol), new Boolean(primaryStol));
        this.primaryStol = primaryStol;
    }

    public boolean getSecondaryStol()
    {
        return secondaryStol;
    }

    public void setSecondaryStol(boolean secondaryStol)
    {
        if(shouldNotify && this.secondaryStol != secondaryStol)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSecondaryStol", "Secondary End STOL", "", this, new Boolean(secondaryStol), new Boolean(this.secondaryStol)));
        firePropertyChange("secondaryStol", new Boolean(this.secondaryStol), new Boolean(secondaryStol));
        this.secondaryStol = secondaryStol;
    }

    public Object clone()
    {
        MarkingsModel model = new MarkingsModel();
        model.setShouldNotify(false);
        model.setNumber(number);
        model.setAlternateThreshold(getAlternateThreshold());
        model.setAlternateTouchdown(getAlternateTouchdown());
        model.setAlternateFixedDistance(getAlternateFixedDistance());
        model.setAlternatePrecision(getAlternatePrecision());
        model.setLeadingZeroIdent(getLeadingZeroIdent());
        model.setNoThresholdEndArrows(getNoThresholdEndArrows());
        model.setEdges(getEdges());
        model.setThreshold(getThreshold());
        model.setFixed(getFixed());
        model.setTouchdown(getTouchdown());
        model.setDashes(getDashes());
        model.setIdent(getIdent());
        model.setPrecision(getPrecision());
        model.setEdgePavement(getEdgePavement());
        model.setSingleEnd(getSingleEnd());
        model.setPrimaryClosed(getPrimaryClosed());
        model.setSecondaryClosed(getSecondaryClosed());
        model.setPrimaryStol(getPrimaryStol());
        model.setSecondaryStol(getSecondaryStol());
        model.setShouldNotify(true);
        return model;
    }

    private String number;
    private boolean alternateThreshold;
    private boolean alternateTouchdown;
    private boolean alternateFixedDistance;
    private boolean alternatePrecision;
    private boolean leadingZeroIdent;
    private boolean noThresholdEndArrows;
    private boolean edges;
    private boolean threshold;
    private boolean fixed;
    private boolean touchdown;
    private boolean dashes;
    private boolean ident;
    private boolean precision;
    private boolean edgePavement;
    private boolean singleEnd;
    private boolean primaryClosed;
    private boolean secondaryClosed;
    private boolean primaryStol;
    private boolean secondaryStol;
}
