// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RotationModel.java

package com.zbluesoftware.fsxp.model;

import java.awt.*;
import java.awt.geom.*;

// Referenced classes of package com.zbluesoftware.fsxp.model:
//            BaseModel, AlertModel

public class RotationModel extends BaseModel
{

    public RotationModel()
    {
        rotation = 0.0D;
        offset = new java.awt.geom.Point2D.Float(0.0F, 0.0F);
        AlertModel.getInstance().firePropertyChange("rotation", new Double(0.0D), new Double(0.0D));
    }

    public void paint(Graphics2D g2, boolean recreate)
    {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        float xOffset = offset.x + 10F;
        float yOffset = offset.y + 20F;
        modelPath = new GeneralPath();
        modelPath.append(new java.awt.geom.Ellipse2D.Float(xOffset - 15F, yOffset - 15F, 30F, 30F), false);
        modelPath.append(new java.awt.geom.Ellipse2D.Float(xOffset - 20F, yOffset - 20F, 40F, 40F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset + 15F, yOffset - 3F, xOffset + 25F, yOffset - 3F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset + 15F, yOffset + 3F, xOffset + 25F, yOffset + 3F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset - 25F, yOffset - 3F, xOffset - 15F, yOffset - 3F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset - 25F, yOffset + 3F, xOffset - 15F, yOffset + 3F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset - 3F, yOffset + 15F, xOffset - 3F, yOffset + 25F), false);
        modelPath.append(new java.awt.geom.Line2D.Float(xOffset + 3F, yOffset + 15F, xOffset + 3F, yOffset + 25F), false);
        northArrow = new GeneralPath();
        northArrow.moveTo(xOffset, yOffset - 26F);
        northArrow.lineTo(xOffset - 5F, yOffset - 16F);
        northArrow.lineTo(xOffset + 5F, yOffset - 16F);
        northArrow.lineTo(xOffset, yOffset - 26F);
        java.awt.geom.AffineTransform currentTransform = g2.getTransform();
        if(rotation > 0.0D)
            g2.rotate(Math.toRadians(rotation), xOffset, yOffset);
        g2.setPaint(Color.lightGray);
        g2.setStroke(new BasicStroke(2.0F));
        g2.draw(modelPath);
        g2.fill(northArrow);
        if(currentlySelected)
            g2.setPaint(Color.black);
        g2.setFont(new Font("Tahoma", 1, 10));
        g2.drawString("N", xOffset - 3F, yOffset - 5F);
        g2.setStroke(new BasicStroke());
        if(currentlySelected)
        {
            g2.setPaint(new Color(0, 0, 0, 48));
            g2.fill(modelPath);
        }
        if(rotation > 0.0D)
            g2.setTransform(currentTransform);
    }

    public Shape getClip()
    {
        return modelPath;
    }

    public boolean isWithinObject(int x, int y)
    {
        if(modelPath == null)
            return false;
        else
            return modelPath.contains(x, y);
    }

    public double getRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        AlertModel.getInstance().firePropertyChange("rotation", new Double(this.rotation), new Double(rotation));
        firePropertyChange("rotation", new Double(this.rotation), new Double(rotation));
        this.rotation = rotation;
    }

    public void setOffset(java.awt.geom.Point2D.Float offset)
    {
        this.offset = offset;
    }

    private GeneralPath northArrow;
    private java.awt.geom.Point2D.Float offset;
    private double rotation;
}
