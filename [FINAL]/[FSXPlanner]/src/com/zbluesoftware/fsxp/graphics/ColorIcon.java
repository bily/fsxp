// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorIcon.java

package com.zbluesoftware.fsxp.graphics;

import com.zbluesoftware.fsxp.ribbon.ResizableIcon;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class ColorIcon extends ImageIcon
    implements ResizableIcon
{

    public ColorIcon(Image image, Color baseColor)
    {
        super(image);
        highlightColor = new Color(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), 128);
        drawX = false;
    }

    public void setDimension(Dimension dimension)
    {
    }

    public void setHeight(int i)
    {
    }

    public void setWidth(int i)
    {
    }

    public void revertToOriginalDimension()
    {
    }

    public void setDrawX(boolean drawX)
    {
        if(this.drawX != drawX)
            this.drawX = drawX;
    }

    public boolean getDrawX()
    {
        return drawX;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        super.paintIcon(c, g, x, y);
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(highlightColor);
        g2.fill(new java.awt.geom.Rectangle2D.Float(x, y, getIconWidth(), getIconHeight()));
        if(drawX)
        {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(Color.red);
            g2.setStroke(new BasicStroke(2.0F));
            g2.draw(new java.awt.geom.Line2D.Float(x + 1, y + 1, (x + getIconWidth()) - 2, (y + getIconHeight()) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(x + 1, (y + getIconHeight()) - 2, (x + getIconWidth()) - 2, y + 1));
        }
        g2.dispose();
    }

    private Color highlightColor;
    private boolean drawX;
}
