// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WindowBorder.java

package com.zbluesoftware.fsxp.ui;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import javax.swing.border.AbstractBorder;

public class WindowBorder extends AbstractBorder
{

    public WindowBorder(String title)
    {
        this.title = title;
        font = new Font("Arial", 0, 10);
        titleBarColor = new Color(186, 201, 217);
        titleBarColor2 = new Color(186, 201, 217);
        width = 0;
        height = 0;
        borderOpaque = true;
        resizeBoxShown = true;
        titleGradient = false;
    }

    protected Insets getBorderInsets()
    {
        int ascent = (int)font.getLineMetrics("H", new FontRenderContext(new AffineTransform(), false, false)).getAscent();
        return new Insets(ascent + 7, 6, 6, 6);
    }

    public Insets getBorderInsets(Component c)
    {
        return getBorderInsets();
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Insets getBorderInsets(Component c, Insets insets)
    {
        Insets i2 = getBorderInsets(c);
        insets.top = i2.top;
        insets.bottom = i2.bottom;
        insets.left = i2.left;
        insets.right = i2.right;
        return insets;
    }

    public void setBorderOpaque(boolean borderOpaque)
    {
        this.borderOpaque = borderOpaque;
    }

    public boolean isBorderOpaque()
    {
        return borderOpaque;
    }

    public void setResizeBoxShown(boolean resizeBoxShown)
    {
        this.resizeBoxShown = resizeBoxShown;
    }

    public boolean isResizeBoxShown()
    {
        return resizeBoxShown;
    }

    public void setFont(Font font)
    {
        this.font = font;
    }

    public boolean isInHeader(Point point)
    {
        return point.y <= getBorderInsets().top;
    }

    public boolean isInCloseBox(Point point)
    {
        int top = getBorderInsets().top;
        return point.x >= width - top - 6 && point.x <= (width - top - 6) + (top - 5) && point.y >= 3 && point.y <= 3 + (top - 5);
    }

    public boolean isInResizeBox(Point point)
    {
        return point.x >= width - 9 && point.y >= height - 9;
    }

    public boolean isInBottomLine(Point point)
    {
        return point.y >= height - 5;
    }

    public boolean isInRightLine(Point point)
    {
        return point.x >= width - 5;
    }

    public boolean isInTitleBar(Point point)
    {
        int top = getBorderInsets().top;
        return point.y >= 0 && point.y <= top;
    }

    public void setTitleBarColor(Color titleBarColor)
    {
        this.titleBarColor = titleBarColor;
    }

    public void setTitleBarColor2(Color titleBarColor2)
    {
        this.titleBarColor2 = titleBarColor2;
    }

    public void setTitleGradient(boolean titleGradient)
    {
        this.titleGradient = titleGradient;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        int top = getBorderInsets().top;
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
        g2.setFont(font);
        FontMetrics fontMetrics = g.getFontMetrics();
        int ascent = fontMetrics.getAscent();
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, width, height);
        g2.setPaint(Color.black);
        g2.drawRect(2, 2, width - 5, height - 5);
        g2.setPaint(Color.gray);
        g2.drawRect(1, 1, width - 3, height - 3);
        g2.setPaint(Color.lightGray);
        g2.drawLine(0, 0, width, 0);
        g2.drawLine(0, 0, 0, height);
        g2.setPaint(Color.darkGray);
        g2.drawLine(width - 1, 0, width - 1, height);
        g2.drawLine(0, height - 1, width, height - 1);
        g2.drawLine(3, top - 1, width - 4, top - 1);
        if(titleGradient)
            g2.setPaint(new GradientPaint(3F, 3 + (top - 4) / 2, titleBarColor, width - 6, 3 + (top - 4) / 2, titleBarColor2));
        else
            g2.setPaint(titleBarColor);
        g2.fillRect(3, 3, width - 6, top - 4);
        g2.setPaint(Color.black);
        g2.drawString(title, 5, top - 3);
        if(resizeBoxShown)
        {
            g2.setColor(Color.black);
            g2.drawLine(width - 9, height - 3, width - 3, height - 9);
            g2.setColor(Color.gray);
            g2.drawLine(width - 9, height - 2, width - 2, height - 9);
            g2.drawLine(width - 5, height - 4, width - 4, height - 4);
            g2.drawLine(width - 4, height - 5, width - 4, height - 5);
            g2.setColor(Color.darkGray);
            g2.drawLine(width - 8, height - 2, width - 2, height - 8);
        }
        int boxLeft = width - 22;
        int boxTop = 4;
        g2.setPaint(Color.lightGray);
        g2.fill3DRect(boxLeft, boxTop, 10, 10, true);
        g2.setPaint(Color.black);
        g2.drawLine(boxLeft + 2, boxTop + 2, boxLeft + 3, boxTop + 2);
        g2.drawLine(boxLeft + 6, boxTop + 2, boxLeft + 7, boxTop + 2);
        g2.drawLine(boxLeft + 3, boxTop + 3, boxLeft + 6, boxTop + 3);
        g2.drawRect(boxLeft + 4, boxTop + 4, 1, 1);
        g2.drawLine(boxLeft + 3, boxTop + 6, boxLeft + 6, boxTop + 6);
        g2.drawLine(boxLeft + 6, boxTop + 7, boxLeft + 7, boxTop + 7);
        g2.drawLine(boxLeft + 2, boxTop + 7, boxLeft + 3, boxTop + 7);
        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setRenderingHints(hints);
    }

    private String title;
    private Font font;
    private Color titleBarColor;
    private Color titleBarColor2;
    private int width;
    private int height;
    private boolean borderOpaque;
    private boolean resizeBoxShown;
    private boolean titleGradient;
}
