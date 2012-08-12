// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwayPathDisplayModel.java

package com.zbluesoftware.fsxp.model;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TaxiwayPathDisplayModel
    implements PropertyChangeListener
{

    public TaxiwayPathDisplayModel(Line2D leftEdgeLine, Line2D rightEdgeLine, String leftEdge, String rightEdge, String type, int start, int end, 
            float width, boolean leftEdgeLighted, boolean rightEdgeLighted, String widthMeasure)
    {
        this.leftEdgeLine = leftEdgeLine;
        this.rightEdgeLine = rightEdgeLine;
        this.start = start;
        this.end = end;
        this.leftEdgeLighted = leftEdgeLighted;
        this.rightEdgeLighted = rightEdgeLighted;
        if(widthMeasure.equals("F"))
            this.width = width;
        else
            this.width = width * 3.28F;
        if(type.equals("RUNWAY"))
        {
            this.leftEdge = "NONE";
            this.rightEdge = "NONE";
        } else
        {
            this.leftEdge = leftEdge;
            this.rightEdge = rightEdge;
        }
        displayLights = SettingsEngine.getInstance().getDisplayLights();
        SettingsEngine.getInstance().addPropertyChangeListener(this);
    }

    public void paint(Graphics2D g2, float scale)
    {
        float dashSize = 24F * scale;
        float lineWidth = Math.max(1.0F, 0.75F * scale);
        g2.setStroke(new BasicStroke(lineWidth));
        g2.setPaint(ColorsEngine.getInstance().getTaxiwayLineColor());
        if(leftEdge.equals("SOLID"))
            g2.draw(leftEdgeLine);
        else
        if(leftEdge.equals("DASHED"))
        {
            g2.setStroke(new BasicStroke(lineWidth, 0, 1, 0.0F, new float[] {
                dashSize, dashSize
            }, 0.0F));
            g2.draw(leftEdgeLine);
            g2.setStroke(new BasicStroke(lineWidth));
        }
        if(rightEdge.equals("SOLID"))
            g2.draw(rightEdgeLine);
        else
        if(rightEdge.equals("DASHED"))
        {
            g2.setStroke(new BasicStroke(lineWidth, 0, 1, 0.0F, new float[] {
                dashSize, dashSize
            }, 0.0F));
            g2.draw(rightEdgeLine);
            g2.setStroke(new BasicStroke(lineWidth));
        }
    }

    public void paintLights(Graphics2D g2, float scale)
    {
        if(displayLights && (double)scale >= 0.20000000000000001D)
        {
            double lightSize = 5D * (double)scale;
            double haloSize = 7D * (double)scale;
            g2.setPaint(Color.blue);
            if(leftEdgeLighted)
                drawEdgeLights(g2, scale, lightSize, haloSize, leftEdgeLine.getX1(), leftEdgeLine.getY1(), leftEdgeLine.getX2(), leftEdgeLine.getY2());
            if(rightEdgeLighted)
                drawEdgeLights(g2, scale, lightSize, haloSize, rightEdgeLine.getX1(), rightEdgeLine.getY1(), rightEdgeLine.getX2(), rightEdgeLine.getY2());
        }
    }

    private void drawEdgeLights(Graphics2D g2, float scale, double lightSize, double haloSize, double x1, double y1, double x2, double y2)
    {
        boolean yLonger = false;
        double shortLen = y2 - y1;
        double longLen = x2 - x1;
        if(Math.abs(shortLen) > Math.abs(longLen))
        {
            double swap = shortLen;
            shortLen = longLen;
            longLen = swap;
            yLonger = true;
        }
        double lightSpacing;
        if(longLen < 0.0D)
            lightSpacing = -50D * (double)scale;
        else
            lightSpacing = 50D * (double)scale;
        double divDiff;
        if(shortLen == 0.0D)
            divDiff = longLen;
        else
            divDiff = longLen / shortLen;
        if(yLonger)
        {
            if(longLen > 0.0D)
            {
                for(double i = 0.0D; i < longLen; i += lightSpacing)
                {
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i / divDiff) - haloSize / 2D, (y1 + i) - haloSize / 2D, haloSize, haloSize));
                    g2.setPaint(Color.blue);
                    g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i / divDiff) - lightSize / 2D, (y1 + i) - lightSize / 2D, lightSize, lightSize));
                }

            } else
            {
                for(double i = longLen; i <= 0.0D; i -= lightSpacing)
                {
                    g2.setPaint(Utilities.HALO_COLOR);
                    g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i / divDiff) - haloSize / 2D, (y1 + i) - haloSize / 2D, haloSize, haloSize));
                    g2.setPaint(Color.blue);
                    g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i / divDiff) - lightSize / 2D, (y1 + i) - lightSize / 2D, lightSize, lightSize));
                }

            }
        } else
        if(longLen > 0.0D)
        {
            for(double i = 0.0D; i < longLen; i += lightSpacing)
            {
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i) - haloSize / 2D, (y1 + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(Color.blue);
                g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i) - lightSize / 2D, (y1 + i / divDiff) - lightSize / 2D, lightSize, lightSize));
            }

        } else
        {
            for(double i = longLen; i <= 0.0D; i -= lightSpacing)
            {
                g2.setPaint(Utilities.HALO_COLOR);
                g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i) - haloSize / 2D, (y1 + i / divDiff) - haloSize / 2D, haloSize, haloSize));
                g2.setPaint(Color.blue);
                g2.fill(new java.awt.geom.Ellipse2D.Double((x1 + i) - lightSize / 2D, (y1 + i / divDiff) - lightSize / 2D, lightSize, lightSize));
            }

        }
    }

    public boolean isDisplayed()
    {
        return leftEdgeLine != null;
    }

    public Line2D getLeftEdgeLine(int index)
    {
        if(index == start)
            return rightEdgeLine;
        else
            return leftEdgeLine;
    }

    public Line2D getLeftEdgeLine()
    {
        return leftEdgeLine;
    }

    public Line2D getRightEdgeLine(int index)
    {
        if(index == end)
            return rightEdgeLine;
        else
            return leftEdgeLine;
    }

    public boolean isAtBeginning(int index)
    {
        return index == end;
    }

    public Line2D getRightEdgeLine()
    {
        return rightEdgeLine;
    }

    public String getLeftEdge()
    {
        return leftEdge;
    }

    public String getRightEdge()
    {
        return rightEdge;
    }

    public int getStart()
    {
        return start;
    }

    public int getEnd()
    {
        return end;
    }

    public float getWidth()
    {
        return width;
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if((event.getSource() instanceof SettingsEngine) && event.getPropertyName().equals("displayLights"))
            displayLights = ((Boolean)event.getNewValue()).booleanValue();
    }

    private Line2D leftEdgeLine;
    private Line2D rightEdgeLine;
    private String leftEdge;
    private String rightEdge;
    private int start;
    private int end;
    private float width;
    private boolean leftEdgeLighted;
    private boolean rightEdgeLighted;
    private boolean displayLights;
}
