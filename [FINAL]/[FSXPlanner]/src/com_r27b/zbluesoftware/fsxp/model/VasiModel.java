// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VasiModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, HistoryModel, HistoryListModel

public class VasiModel extends BaseModel
{

    public VasiModel()
    {
        end = "PRIMARY";
        type = "PAPI2";
        side = "LEFT";
        biasXMeasure = SettingsEngine.getInstance().getVasiXMeasure();
        biasZMeasure = SettingsEngine.getInstance().getVasiZMeasure();
        spacingMeasure = SettingsEngine.getInstance().getVasiSpacingMeasure();
        biasX = SettingsEngine.getInstance().getVasiX();
        biasZ = SettingsEngine.getInstance().getVasiZ();
        spacing = SettingsEngine.getInstance().getVasiSpacing();
        pitch = 3F;
        modelName = "Runway VASI";
        heading = 0.0F;
    }

    public void setRunwayCP(java.awt.geom.Point2D.Float runwayCP)
    {
        this.runwayCP = runwayCP;
    }

    public void setHeading(float heading)
    {
        this.heading = heading;
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        if(recreate || modelPath == null)
        {
            float xOffset = biasX;
            if(biasXMeasure.equals("M"))
                xOffset *= 3.28F;
            xOffset *= scale;
            float zOffset = biasZ;
            if(biasZMeasure.equals("M"))
                zOffset *= 3.28F;
            zOffset *= scale;
            float spacingDist = spacing;
            if(spacingMeasure.equals("M"))
                spacingDist *= 3.28F;
            spacingDist *= scale;
            if(spacingDist == 0.0F)
                spacingDist = 3F;
            java.awt.geom.Point2D.Float vasiCenter;
            if(end.equals("PRIMARY") && side.equals("LEFT"))
                vasiCenter = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)xOffset), (float)(runwayCP.getY() + (double)zOffset)), heading);
            else
            if(end.equals("PRIMARY") && side.equals("RIGHT"))
                vasiCenter = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)xOffset), (float)(runwayCP.getY() + (double)zOffset)), heading);
            else
            if(end.equals("SECONDARY") && side.equals("LEFT"))
                vasiCenter = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float((float)(runwayCP.getX() + (double)xOffset), (float)(runwayCP.getY() - (double)zOffset)), heading);
            else
                vasiCenter = Utilities.rotatePoint(runwayCP, new java.awt.geom.Point2D.Float((float)(runwayCP.getX() - (double)xOffset), (float)(runwayCP.getY() - (double)zOffset)), heading);
            if(xOffset == 0.0F && zOffset == 0.0F)
                vasiCenter = runwayCP;
            int lrDirection = 1;
            if(end.equals("PRIMARY") && side.equals("RIGHT"))
                lrDirection = -1;
            else
            if(end.equals("SECONDARY") && side.equals("LEFT"))
                lrDirection = -1;
            int fbDirection = 1;
            if(end.equals("SECONDARY"))
                fbDirection = -1;
            modelPath = new GeneralPath();
            if(type.equals("PAPI2") || type.equals("PANELS"))
            {
                for(int i = 0; i <= 30; i += 30)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)vasiCenter.getY()), heading);
                    if(i == 0)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            if(type.equals("PAPI4"))
            {
                for(int i = 0; i <= 90; i += 30)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)vasiCenter.getY()), heading);
                    if(i == 0)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            if(type.equals("PVASI") || type.equals("BALL"))
                modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiCenter.x - 3F * scale, vasiCenter.y - 3F * scale, 6F * scale, 6F * scale), false);
            else
            if(type.equals("TRICOLOR"))
                modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiCenter.x - 3F * scale, vasiCenter.y - 3F * scale, 6F * scale, 6F * scale), false);
            else
            if(type.equals("TVASI"))
            {
                for(int i = 0; i <= 15; i += 15)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)vasiCenter.getY()), heading);
                    if(i == 0)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

                for(int i = 40; i <= 55; i += 15)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)vasiCenter.getY()), heading);
                    if(i == 0)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

                for(float i = spacingDist; i <= spacingDist * 3F; i += spacingDist)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - 27.5D * (double)scale * (double)lrDirection), (float)(vasiCenter.getY() - (double)(i * (float)fbDirection))), heading);
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            if(type.equals("VASI21"))
            {
                for(float i = 0.0F; i <= spacingDist; i += spacingDist)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)vasiCenter.getX(), (float)(vasiCenter.getY() - (double)(i * (float)fbDirection))), heading);
                    if(i == 0.0F)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            if(type.equals("VASI22"))
            {
                for(int i = 0; i <= 30; i += 30)
                {
                    for(float j = 0.0F; j <= spacingDist; j += spacingDist)
                    {
                        java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)(vasiCenter.getY() - (double)(j * (float)fbDirection))), heading);
                        if(i == 0 && j == 0.0F)
                            vasiPoint = vasiCenter;
                        modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                    }

                }

            } else
            if(type.equals("VASI23"))
            {
                for(int i = 0; i <= 60; i += 30)
                {
                    for(float j = 0.0F; j <= spacingDist; j += spacingDist)
                    {
                        java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)(vasiCenter.getY() - (double)(j * (float)fbDirection))), heading);
                        if(i == 0 && j == 0.0F)
                            vasiPoint = vasiCenter;
                        modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                    }

                }

            } else
            if(type.equals("VASI31"))
            {
                for(float i = 0.0F; i <= spacingDist * 2.0F; i += spacingDist)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)vasiCenter.getX(), (float)(vasiCenter.getY() - (double)(i * (float)fbDirection))), heading);
                    if(i == 0.0F)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            if(type.equals("VASI32"))
            {
                for(int i = 0; i <= 30; i += 30)
                {
                    for(float j = 0.0F; j <= spacingDist * 2.0F; j += spacingDist)
                    {
                        java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)(vasiCenter.getY() - (double)(j * (float)fbDirection))), heading);
                        if(i == 0 && j == 0.0F)
                            vasiPoint = vasiCenter;
                        modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                    }

                }

            } else
            if(type.equals("VASI33"))
            {
                for(int i = 0; i <= 60; i += 30)
                {
                    for(float j = 0.0F; j <= spacingDist * 2.0F; j += spacingDist)
                    {
                        if(i == 60 && j == spacingDist * 2.0F)
                            continue;
                        java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)(vasiCenter.getY() - (double)(j * (float)fbDirection))), heading);
                        if(i == 0 && j == 0.0F)
                            vasiPoint = vasiCenter;
                        modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                    }

                }

            } else
            if(type.equals("APAP"))
            {
                for(int i = 0; i <= 20; i += 20)
                {
                    java.awt.geom.Point2D.Float vasiPoint = Utilities.rotatePoint(vasiCenter, new java.awt.geom.Point2D.Float((float)(vasiCenter.getX() - (double)((float)i * scale * (float)lrDirection)), (float)vasiCenter.getY()), heading);
                    if(i == 0)
                        vasiPoint = vasiCenter;
                    modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiPoint.x - 3F * scale, vasiPoint.y - 3F * scale, 6F * scale, 6F * scale), false);
                }

            } else
            {
                modelPath.append(new java.awt.geom.Ellipse2D.Float(vasiCenter.x - 3F * scale, vasiCenter.y - 3F * scale, 6F * scale, 6F * scale), false);
            }
        }
        if(!modelPath.intersects(g2.getClipBounds()))
            return;
        if(type.equals("TRICOLOR"))
            g2.setPaint(new Color(116, 255, 129));
        else
            g2.setPaint(new Color(232, 221, 171));
        g2.fill(modelPath);
        g2.setPaint(new Color(255, 73, 52));
        g2.draw(modelPath);
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

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        if(shouldNotify && !this.side.equals(side))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSide", "Side", "", this, side, this.side));
        firePropertyChange("side", this.side, side);
        this.side = side;
    }

    public String getBiasXMeasure()
    {
        return biasXMeasure;
    }

    public void setBiasXMeasure(String biasXMeasure)
    {
        if(shouldNotify && !this.biasXMeasure.equals(biasXMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasXMeasure", "Bias X Measure", "", this, biasXMeasure, this.biasXMeasure));
        firePropertyChange("biasXMeasure", this.biasXMeasure, biasXMeasure);
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
        firePropertyChange("biasZMeasure", this.biasZMeasure, biasZMeasure);
        this.biasZMeasure = biasZMeasure;
    }

    public String getSpacingMeasure()
    {
        return spacingMeasure;
    }

    public void setSpacingMeasure(String spacingMeasure)
    {
        if(shouldNotify && !this.spacingMeasure.equals(spacingMeasure))
            HistoryListModel.getInstance().addModel(new HistoryModel("setSpacingMeasure", "Spacing Measure", "", this, spacingMeasure, this.spacingMeasure));
        firePropertyChange("spacingMeasure", this.spacingMeasure, spacingMeasure);
        this.spacingMeasure = spacingMeasure;
    }

    public float getBiasX()
    {
        return biasX;
    }

    public void setBiasX(float biasX)
    {
        if(shouldNotify && this.biasX != biasX)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasX", "Bias X", "", this, new Float(biasX), new Float(this.biasX)));
        firePropertyChange("biasX", new Float(this.biasX), new Float(biasX));
        this.biasX = biasX;
    }

    public float getBiasZ()
    {
        return biasZ;
    }

    public void setBiasZ(float biasZ)
    {
        if(shouldNotify && this.biasZ != biasZ)
            HistoryListModel.getInstance().addModel(new HistoryModel("setBiasZ", "Bias Z", "", this, new Float(biasZ), new Float(this.biasZ)));
        firePropertyChange("biasZ", new Float(this.biasZ), new Float(biasZ));
        this.biasZ = biasZ;
    }

    public float getSpacing()
    {
        return spacing;
    }

    public void setSpacing(float spacing)
    {
        if(shouldNotify && this.spacing != spacing)
            HistoryListModel.getInstance().addModel(new HistoryModel("setSpacing", "Spacing", "", this, new Float(spacing), new Float(this.spacing)));
        firePropertyChange("spacing", new Float(this.spacing), new Float(spacing));
        this.spacing = spacing;
    }

    public float getPitch()
    {
        return pitch;
    }

    public void setPitch(float pitch)
    {
        if(shouldNotify && this.pitch != pitch)
            HistoryListModel.getInstance().addModel(new HistoryModel("setPitch", "Pitch", "", this, new Float(pitch), new Float(this.pitch)));
        firePropertyChange("pitch", new Float(this.pitch), new Float(pitch));
        this.pitch = pitch;
    }

    public Object clone()
    {
        VasiModel model = new VasiModel();
        model.setShouldNotify(false);
        model.setEnd(getEnd());
        model.setType(getType());
        model.setSide(getSide());
        model.setBiasXMeasure(getBiasXMeasure());
        model.setBiasZMeasure(getBiasZMeasure());
        model.setSpacingMeasure(getSpacingMeasure());
        model.setBiasX(getBiasX());
        model.setBiasZ(getBiasZ());
        model.setSpacing(getSpacing());
        model.setPitch(getPitch());
        model.setShouldNotify(true);
        return model;
    }

    private java.awt.geom.Point2D.Float runwayCP;
    private String end;
    private String type;
    private String side;
    private String biasXMeasure;
    private String biasZMeasure;
    private String spacingMeasure;
    private float biasX;
    private float biasZ;
    private float spacing;
    private float pitch;
    private float heading;
}
