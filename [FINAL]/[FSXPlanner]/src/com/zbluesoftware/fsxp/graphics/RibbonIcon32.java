// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RibbonIcon32.java

package com.zbluesoftware.fsxp.graphics;

import com.zbluesoftware.fsxp.ribbon.ResizableIcon;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.ImageIcon;

// Referenced classes of package com.zbluesoftware.fsxp.graphics:
//            IconFactory

public class RibbonIcon32 extends ImageIcon
    implements ResizableIcon
{

    public RibbonIcon32(String imageName, int defaultSize)
    {
        this.imageName = imageName;
        this.defaultSize = defaultSize;
        imageIcon = IconFactory.getInstance().getIcon((new StringBuilder()).append(imageName).append(defaultSize).toString());
        setImage(imageIcon.getImage());
        drawX = false;
    }

    public String getImageName()
    {
        return imageName;
    }

    public int getDefaultSize()
    {
        return defaultSize;
    }

    public void setDimension(Dimension dimension)
    {
    }

    public void setHeight(int height)
    {
        if(height != getIconHeight() && (height == 16 || height == 32))
            imageIcon = IconFactory.getInstance().getIcon((new StringBuilder()).append(imageName).append(height).toString());
    }

    public void setWidth(int i)
    {
    }

    public void revertToOriginalDimension()
    {
        if(getIconHeight() != defaultSize)
            imageIcon = IconFactory.getInstance().getIcon((new StringBuilder()).append(imageName).append(defaultSize).toString());
    }

    public int getIconHeight()
    {
        if(imageIcon == null)
            return 0;
        else
            return imageIcon.getIconHeight();
    }

    public int getIconWidth()
    {
        if(imageIcon == null)
            return 0;
        else
            return imageIcon.getIconWidth();
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
        g.drawImage(imageIcon.getImage(), x, y, null);
        if(drawX)
        {
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(Color.red);
            g2.setStroke(new BasicStroke(2.0F));
            g2.draw(new java.awt.geom.Line2D.Float(x + 1, y + 1, (x + getIconWidth()) - 2, (y + getIconHeight()) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(x + 1, (y + getIconHeight()) - 2, (x + getIconWidth()) - 2, y + 1));
            g2.dispose();
        }
    }

    private String imageName;
    private ImageIcon imageIcon;
    private int defaultSize;
    private boolean drawX;
}
