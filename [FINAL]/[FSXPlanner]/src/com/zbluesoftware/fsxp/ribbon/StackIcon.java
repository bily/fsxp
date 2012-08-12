// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   StackIcon.java

package com.zbluesoftware.fsxp.ribbon;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ResizableIcon

public class StackIcon
    implements ResizableIcon
{

    public StackIcon(ResizableIcon stackIcon, double marginThickness)
    {
        this.stackIcon = stackIcon;
        this.marginThickness = marginThickness;
        setHeight(stackIcon.getIconHeight());
        setWidth(stackIcon.getIconWidth());
        drawX = false;
    }

    public int getIconHeight()
    {
        return (int)((1.0D + 2D * marginThickness) * (double)stackIcon.getIconHeight());
    }

    public int getIconWidth()
    {
        return (int)((1.0D + 2D * marginThickness) * (double)stackIcon.getIconWidth());
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
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle rect = new Rectangle(x + marginX / 2, y + marginY / 2, stackIcon.getIconWidth() + marginX, stackIcon.getIconHeight() + marginY);
        int rx = x + marginX + stackIcon.getIconWidth() / 2;
        int ry = y + marginY + stackIcon.getIconHeight() / 2;
        paintFrame(g2, rotateRect(rect, rx - 2, ry, -8D));
        paintFrame(g2, rotateRect(rect, rx, ry + 2, 5D));
        paintFrame(g2, rect);
        g2.dispose();
        stackIcon.paintIcon(c, g, 1 + x + marginX, 1 + y + marginY);
    }

    protected Point2D rotatePoint(int x, int y, int rx, int ry, double theta)
    {
        double st = Math.sin((3.1415926535897931D * theta) / 180D);
        double ct = Math.cos((3.1415926535897931D * theta) / 180D);
        return new java.awt.geom.Point2D.Double(((double)rx + (double)(x - rx) * ct) - (double)(y - ry) * st, (double)ry + (double)(y - ry) * ct + (double)(x - rx) * st);
    }

    protected GeneralPath rotateRect(Rectangle rect, int rx, int ry, double theta)
    {
        GeneralPath res = new GeneralPath();
        Point2D p1 = rotatePoint(rect.x, rect.y, rx, ry, theta);
        Point2D p2 = rotatePoint(rect.x + rect.width, rect.y, rx, ry, theta);
        Point2D p3 = rotatePoint(rect.x + rect.width, rect.y + rect.height, rx, ry, theta);
        Point2D p4 = rotatePoint(rect.x, rect.y + rect.height, rx, ry, theta);
        res.moveTo((float)p1.getX(), (float)p1.getY());
        res.lineTo((float)p2.getX(), (float)p2.getY());
        res.lineTo((float)p3.getX(), (float)p3.getY());
        res.lineTo((float)p4.getX(), (float)p4.getY());
        res.lineTo((float)p1.getX(), (float)p1.getY());
        return res;
    }

    protected void paintFrame(Graphics2D g2, Shape shape)
    {
        g2.setStroke(new BasicStroke(0.8F));
        g2.setColor(Color.white);
        g2.fill(shape);
        g2.setColor(Color.darkGray);
        g2.draw(shape);
    }

    public void setDimension(Dimension newDimension)
    {
        stackIcon.setWidth(newDimension.width);
        stackIcon.setHeight(newDimension.height);
    }

    public void setHeight(int height)
    {
        int newHeight = (int)((double)height / (1.0D + 2D * marginThickness));
        if((height - newHeight) % 2 == 1)
            newHeight--;
        stackIcon.setHeight(newHeight);
        marginY = (height - stackIcon.getIconHeight()) / 2;
    }

    public void setWidth(int width)
    {
        int newWidth = (int)((double)width / (1.0D + 2D * marginThickness));
        if((width - newWidth) % 2 == 1)
            newWidth--;
        stackIcon.setWidth(newWidth);
        marginX = (width - stackIcon.getIconWidth()) / 2;
    }

    public void revertToOriginalDimension()
    {
        stackIcon.revertToOriginalDimension();
        setWidth(stackIcon.getIconWidth());
        setHeight(stackIcon.getIconHeight());
    }

    protected ResizableIcon stackIcon;
    protected int marginX;
    protected int marginY;
    protected double marginThickness;
    private boolean drawX;
}