// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPToolTipUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.ZToolTip;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.StringTokenizer;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolTipUI;

public class FSXPToolTipUI extends BasicToolTipUI
{

    public FSXPToolTipUI()
    {
    }

    public void paint(Graphics g, JComponent c)
    {
        Dimension size = (Dimension)((ZToolTip)c).getComponent().getClientProperty("ToolTipSize");
        String title = (String)((ZToolTip)c).getComponent().getClientProperty("ToolTipTitle");
        String text = (String)((ZToolTip)c).getComponent().getClientProperty("ToolTipText");
        if(size == null)
            size = new Dimension(20, 20);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setPaint(Color.white);
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, c.getWidth(), c.getHeight()));
        GradientPaint gp = new GradientPaint(0.0F, 0.0F, new Color(254, 254, 254), 0.0F, c.getHeight() - 1, new Color(201, 217, 239));
        g2.setPaint(gp);
        g2.fill(new java.awt.geom.RoundRectangle2D.Float(0.0F, 0.0F, c.getWidth() - 1, c.getHeight() - 1, 5F, 5F));
        g2.setPaint(new Color(118, 118, 118));
        g2.draw(new java.awt.geom.RoundRectangle2D.Float(0.0F, 0.0F, c.getWidth() - 1, c.getHeight() - 1, 5F, 5F));
        g2.setFont(new Font("Tahoma", 0, 11));
        g2.setPaint(Color.black);
        FontMetrics fontMetrics = g2.getFontMetrics();
        int spaceWidth = fontMetrics.stringWidth(" ");
        int textHeight = fontMetrics.getHeight();
        int rightMargin = size.width - 5;
        if(title != null)
            g2.drawString(title, 5, 15);
        if(text != null)
        {
            g2.setPaint(Color.darkGray);
            int xOffset = 15;
            int yOffset = 40;
            StringTokenizer tokenizer = new StringTokenizer(text, " \n", true);
            do
            {
                if(!tokenizer.hasMoreTokens())
                    break;
                String token = tokenizer.nextToken();
                if(!token.equals(" "))
                    if(token.equals("\n"))
                    {
                        xOffset = 15;
                        yOffset += textHeight;
                    } else
                    {
                        if(fontMetrics.stringWidth(token) + xOffset > rightMargin && xOffset != 15)
                        {
                            xOffset = 15;
                            yOffset += textHeight;
                        }
                        g2.drawString(token, xOffset, yOffset);
                        xOffset += fontMetrics.stringWidth(token) + spaceWidth;
                        if(xOffset > rightMargin)
                        {
                            xOffset = 15;
                            yOffset += textHeight;
                        }
                    }
            } while(true);
        }
    }

    public Dimension getPreferredSize(JComponent c)
    {
        Dimension size = (Dimension)((ZToolTip)c).getComponent().getClientProperty("ToolTipSize");
        if(size == null)
            size = new Dimension(10, 10);
        return size;
    }

    public Dimension getMinimumSize(JComponent c)
    {
        return getPreferredSize(c);
    }

    public Dimension getMaximumSize(JComponent c)
    {
        return getPreferredSize(c);
    }

    public static final int TEXT_OFFSET = 15;
    public static final int RIGHT_BORDER = 5;
}