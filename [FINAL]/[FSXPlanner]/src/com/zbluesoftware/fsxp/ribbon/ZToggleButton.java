// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZToggleButton.java

package com.zbluesoftware.fsxp.ribbon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JToggleButton;

public class ZToggleButton extends JToggleButton
    implements MouseListener
{

    public ZToggleButton(String text)
    {
        super(text);
        mouseOver = false;
        addMouseListener(this);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setPaint(new Color(191, 219, 255));
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, getWidth(), getHeight() - 1));
        if(isSelected())
        {
            g2.setPaint(new Color(245, 250, 255));
            g2.fill(new java.awt.geom.Rectangle2D.Float(2.0F, 2.0F, getWidth() - 5, getHeight() - 2));
            g2.fill(new java.awt.geom.Rectangle2D.Float(1.0F, getHeight() - 2, getWidth() - 3, getHeight() - 2));
            g2.setPaint(new Color(232, 240, 250));
            g2.fill(new java.awt.geom.Rectangle2D.Float(4F, 4F, getWidth() - 9, getHeight() - 4));
            g2.setPaint(new Color(174, 199, 231));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 2, 3F, getWidth() - 2, getHeight() - 3));
            g2.setPaint(new Color(184, 210, 245));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 1, 3F, getWidth() - 1, getHeight() - 3));
            g2.setPaint(new Color(141, 178, 227));
            g2.draw(new java.awt.geom.Line2D.Float(1.0F, 3F, 1.0F, getHeight() - 3));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 3, 3F, getWidth() - 3, getHeight() - 3));
            g2.draw(new java.awt.geom.Line2D.Float(3F, 1.0F, getWidth() - 5, 1.0F));
            g2.draw(new java.awt.geom.Line2D.Float(1.0F, 3F, 3F, 1.0F));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 5, 1.0F, getWidth() - 3, 3F));
            g2.draw(new java.awt.geom.Line2D.Float(0.0F, getHeight(), 1.0F, getHeight() - 3));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 1, getHeight(), getWidth() - 3, getHeight() - 3));
        } else
        if(mouseOver)
        {
            g2.setPaint(new Color(245, 250, 255));
            g2.fill(new java.awt.geom.Rectangle2D.Float(2.0F, 2.0F, getWidth() - 5, getHeight() - 3));
            GradientPaint gp = new GradientPaint(1.0F, getHeight() - 1, new Color(226, 209, 156), 1.0F, 3F, new Color(202, 225, 254));
            g2.setPaint(gp);
            g2.fill(new java.awt.geom.Rectangle2D.Float(4F, 4F, getWidth() - 9, getHeight() - 5));
            g2.setPaint(new Color(141, 178, 227));
            g2.draw(new java.awt.geom.Line2D.Float(1.0F, 3F, 1.0F, getHeight() - 2));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 3, 3F, getWidth() - 3, getHeight() - 2));
            g2.draw(new java.awt.geom.Line2D.Float(3F, 1.0F, getWidth() - 5, 1.0F));
            g2.draw(new java.awt.geom.Line2D.Float(1.0F, 3F, 3F, 1.0F));
            g2.draw(new java.awt.geom.Line2D.Float(getWidth() - 5, 1.0F, getWidth() - 3, 3F));
        }
        g2.setFont(new Font("Tahoma", 0, 11));
        g2.setPaint(new Color(21, 66, 159));
        g2.drawString(getText(), (float)(getWidth() - g2.getFontMetrics().stringWidth(getText())) / 2.0F, g2.getFontMetrics().getHeight() + 2);
    }

    public void mouseEntered(MouseEvent event)
    {
        if(event.getSource() == this)
            mouseOver = true;
    }

    public void mouseExited(MouseEvent event)
    {
        if(event.getSource() == this)
            mouseOver = false;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    private boolean mouseOver;
}