// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaxiwaySignDisplay.java

package com.zbluesoftware.fsxp.ui.component;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComponent;

public class TaxiwaySignDisplay extends JComponent
{

    public TaxiwaySignDisplay()
    {
        labelText = "";
        typePattern = Pattern.compile("[ldmiru][^ldmiru]+");
        specialItems = "_>^'<v`/\\[]x#=.|";
        borderSize = 5;
        align = 2;
    }

    public void setAlign(int align)
    {
        this.align = align;
    }

    public void setLabelText(String labelText)
    {
        this.labelText = labelText;
        repaint();
    }

    public Dimension getMinimumSize()
    {
        return new Dimension(100, 40);
    }

    public void setBounds(int x, int y, int width, int height)
    {
        height = Math.max(height, getMinimumSize().height);
        super.setBounds(x, y, width, height);
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        ArrayList arrayList = new ArrayList();
        if(labelText.length() == 0)
            return;
        g2.setPaint(getBackground());
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, getSize().width, getSize().height));
        g2.setFont(new Font("Arial", 1, 24));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int textHeight = fontMetrics.getHeight();
        Matcher typeMatcher = typePattern.matcher(labelText);
        do
        {
            if(!typeMatcher.find())
                break;
            String label = typeMatcher.group();
            Color textColor = null;
            Color bgColor = null;
            if(label.startsWith("l"))
            {
                textColor = Color.yellow;
                bgColor = Color.black;
            } else
            if(label.startsWith("d"))
            {
                textColor = Color.black;
                bgColor = Color.yellow;
            } else
            if(label.startsWith("m"))
            {
                textColor = Color.white;
                bgColor = Color.red;
            } else
            if(label.startsWith("i"))
            {
                textColor = Color.black;
                bgColor = Color.white;
            } else
            if(label.startsWith("r"))
            {
                textColor = Color.white;
                bgColor = Color.red;
            } else
            if(label.startsWith("u"))
            {
                textColor = Color.black;
                bgColor = Color.white;
            }
            if(textColor != null && label.length() >= 2)
            {
                HashMap hashMap = new HashMap();
                hashMap.put("textColor", new Color(0, 0, 0, 0));
                hashMap.put("bgColor", bgColor);
                hashMap.put("data", new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, 3F, textHeight));
                hashMap.put("draw", "fill");
                hashMap.put("label", "");
                arrayList.add(hashMap);
                for(int i = 1; i < label.length(); i++)
                {
                    hashMap = new HashMap();
                    hashMap.put("textColor", textColor);
                    hashMap.put("bgColor", bgColor);
                    hashMap.put("label", label);
                    String item = label.substring(i, i + 1);
                    if(specialItems.indexOf(item) >= 0)
                    {
                        if(item.equals("_"))
                        {
                            hashMap.put("data", " ");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals(">"))
                        {
                            hashMap.put("data", createRightArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("^"))
                        {
                            hashMap.put("data", createUpArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("'"))
                        {
                            hashMap.put("data", createUpRightArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("<"))
                        {
                            hashMap.put("data", createLeftArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("v"))
                        {
                            hashMap.put("data", createBackArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("`"))
                        {
                            hashMap.put("data", createUpLeftArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("/"))
                        {
                            hashMap.put("data", createBackLeftArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("\\"))
                        {
                            hashMap.put("data", createBackRightArrow());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("["))
                        {
                            hashMap.put("data", createLeftBorder());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("]"))
                        {
                            hashMap.put("data", createRightBorder());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("x"))
                        {
                            hashMap.put("data", createDoNotEnter());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("#"))
                        {
                            hashMap.put("data", createILSSign());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("="))
                        {
                            hashMap.put("data", createRunway());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("."))
                        {
                            hashMap.put("data", createDot());
                            hashMap.put("draw", "fill");
                            hashMap.put("space", new Integer(1));
                        } else
                        if(item.equals("|"))
                        {
                            hashMap.put("data", createVerticalLine());
                            hashMap.put("draw", "draw");
                            hashMap.put("space", new Integer(5));
                        }
                    } else
                    {
                        hashMap.put("data", item);
                        hashMap.put("space", new Integer(1));
                    }
                    int space = ((Integer)hashMap.get("space")).intValue();
                    HashMap h2 = new HashMap();
                    h2.put("textColor", new Color(0, 0, 0, 0));
                    h2.put("bgColor", bgColor);
                    h2.put("data", new java.awt.geom.Rectangle2D.Float(0.0F, textHeight / 2, space, 2.0F));
                    h2.put("draw", "fill");
                    if((hashMap.get("data") instanceof String) || label.startsWith("l") && item.equals("]"))
                        h2.put("label", label);
                    else
                        h2.put("label", "");
                    arrayList.add(h2);
                    arrayList.add(hashMap);
                    if(i >= label.length() - 1)
                        continue;
                    h2 = new HashMap();
                    h2.put("textColor", new Color(0, 0, 0, 0));
                    h2.put("bgColor", bgColor);
                    h2.put("data", new java.awt.geom.Rectangle2D.Float(0.0F, textHeight / 2, space, 2.0F));
                    h2.put("draw", "fill");
                    if((hashMap.get("data") instanceof String) || label.startsWith("l") && item.equals("["))
                        h2.put("label", label);
                    else
                        h2.put("label", "");
                    arrayList.add(h2);
                }

                hashMap = new HashMap();
                hashMap.put("textColor", new Color(0, 0, 0, 0));
                hashMap.put("bgColor", bgColor);
                hashMap.put("data", new java.awt.geom.Rectangle2D.Float(0.0F, textHeight / 2, 3F, 2.0F));
                hashMap.put("draw", "fill");
                hashMap.put("label", "");
                arrayList.add(hashMap);
            }
        } while(true);
        int signWidth = 0;
        for(int i = arrayList.size() - 1; i >= 0; i--)
        {
            HashMap hashMap = (HashMap)arrayList.get(i);
            Object data = hashMap.get("data");
            if(data instanceof String)
            {
                signWidth += fontMetrics.stringWidth((String)data);
                continue;
            }
            if(data instanceof Shape)
                signWidth += Math.max(1, ((Shape)data).getBounds().width);
        }

        int x;
        switch(align)
        {
        case 1: // '\001'
            x = 0;
            break;

        default:
            x = (getSize().width - signWidth) / 2;
            break;
        }
        int y = textHeight - 2;
        g2.setPaint(new Color(180, 180, 180));
        g2.fill(new java.awt.geom.Rectangle2D.Float(x, 0.0F, signWidth + borderSize * 2, textHeight + borderSize * 2 + 3));
        x += borderSize;
        for(int i = 0; i < arrayList.size(); i++)
        {
            HashMap hashMap = (HashMap)arrayList.get(i);
            int objectWidth = 0;
            Object data = hashMap.get("data");
            if(data instanceof String)
            {
                String text = (String)data;
                objectWidth = fontMetrics.stringWidth(text);
                g2.setPaint((Color)hashMap.get("bgColor"));
                g2.fill(new java.awt.geom.Rectangle2D.Float(x, borderSize, objectWidth, (textHeight + borderSize) - 3));
                x += objectWidth;
            } else
            if(data instanceof Shape)
            {
                Shape shape = (Shape)data;
                objectWidth = Math.max(1, shape.getBounds().width);
                g2.setPaint((Color)hashMap.get("bgColor"));
                g2.fill(new java.awt.geom.Rectangle2D.Float(x, borderSize, objectWidth, (textHeight + borderSize) - 3));
                x += objectWidth;
            }
            if(((String)hashMap.get("label")).startsWith("l"))
            {
                g2.setPaint(Color.yellow);
                g2.draw(new java.awt.geom.Line2D.Float(x - objectWidth, borderSize + 2, x, borderSize + 2));
                g2.draw(new java.awt.geom.Line2D.Float(x - objectWidth, y + borderSize + 1, x, y + borderSize + 1));
            }
        }

        switch(align)
        {
        case 1: // '\001'
            x = borderSize;
            break;

        default:
            x = (getSize().width - signWidth) / 2 + borderSize;
            break;
        }
        for(int i = 0; i < arrayList.size(); i++)
        {
            HashMap hashMap = (HashMap)arrayList.get(i);
            Object data = hashMap.get("data");
            if(data instanceof String)
            {
                String text = (String)data;
                int textWidth = fontMetrics.stringWidth(text);
                g2.setPaint((Color)hashMap.get("textColor"));
                g2.drawString(text, (x + borderSize / 2) - 2, (y + borderSize) - 2);
                x += textWidth;
                continue;
            }
            if(!(data instanceof Shape))
                continue;
            Shape shape = (Shape)data;
            int objectWidth = Math.max(1, shape.getBounds().width);
            g2.translate(x, borderSize);
            g2.setPaint((Color)hashMap.get("textColor"));
            if(((String)hashMap.get("draw")).equals("draw"))
                g2.draw(shape);
            else
                g2.fill(shape);
            g2.translate(-x, -borderSize);
            x += objectWidth;
        }

    }

    public Shape createLeftArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(19F, 15F);
        path.lineTo(19F, 18F);
        path.lineTo(6F, 18F);
        path.lineTo(12F, 24F);
        path.lineTo(8F, 24F);
        path.lineTo(0.0F, 16F);
        path.lineTo(7F, 9F);
        path.lineTo(11F, 9F);
        path.lineTo(5F, 15F);
        path.lineTo(19F, 15F);
        return path;
    }

    public Shape createRightArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 15F);
        path.lineTo(0.0F, 18F);
        path.lineTo(13F, 18F);
        path.lineTo(7F, 24F);
        path.lineTo(11F, 24F);
        path.lineTo(19F, 16F);
        path.lineTo(12F, 9F);
        path.lineTo(8F, 9F);
        path.lineTo(13F, 15F);
        path.lineTo(0.0F, 15F);
        return path;
    }

    public Shape createUpArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(6F, 26F);
        path.lineTo(9F, 26F);
        path.lineTo(9F, 12F);
        path.lineTo(15F, 18F);
        path.lineTo(15F, 14F);
        path.lineTo(7F, 6F);
        path.lineTo(0.0F, 14F);
        path.lineTo(0.0F, 18F);
        path.lineTo(6F, 12F);
        path.lineTo(6F, 26F);
        return path;
    }

    public Shape createBackArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(6F, 7F);
        path.lineTo(9F, 7F);
        path.lineTo(9F, 20F);
        path.lineTo(15F, 14F);
        path.lineTo(15F, 18F);
        path.lineTo(7F, 26F);
        path.lineTo(0.0F, 18F);
        path.lineTo(0.0F, 14F);
        path.lineTo(6F, 20F);
        path.lineTo(6F, 7F);
        return path;
    }

    public Shape createUpRightArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(2.0F, 22F);
        path.lineTo(4F, 25F);
        path.lineTo(14F, 15F);
        path.lineTo(14F, 23F);
        path.lineTo(17F, 20F);
        path.lineTo(17F, 10F);
        path.lineTo(6F, 10F);
        path.lineTo(3F, 13F);
        path.lineTo(11F, 13F);
        path.lineTo(2.0F, 22F);
        return path;
    }

    public Shape createUpLeftArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(15F, 22F);
        path.lineTo(13F, 25F);
        path.lineTo(3F, 15F);
        path.lineTo(3F, 23F);
        path.lineTo(0.0F, 20F);
        path.lineTo(0.0F, 10F);
        path.lineTo(11F, 10F);
        path.lineTo(14F, 13F);
        path.lineTo(6F, 13F);
        path.lineTo(15F, 22F);
        return path;
    }

    public Shape createBackLeftArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(15F, 10F);
        path.lineTo(13F, 7F);
        path.lineTo(3F, 17F);
        path.lineTo(3F, 9F);
        path.lineTo(0.0F, 12F);
        path.lineTo(0.0F, 22F);
        path.lineTo(11F, 22F);
        path.lineTo(14F, 19F);
        path.lineTo(6F, 19F);
        path.lineTo(15F, 10F);
        return path;
    }

    public Shape createBackRightArrow()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(2.0F, 10F);
        path.lineTo(4F, 7F);
        path.lineTo(14F, 17F);
        path.lineTo(14F, 9F);
        path.lineTo(17F, 12F);
        path.lineTo(17F, 22F);
        path.lineTo(6F, 22F);
        path.lineTo(3F, 19F);
        path.lineTo(11F, 19F);
        path.lineTo(2.0F, 10F);
        return path;
    }

    public Shape createILSSign()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 9F);
        path.lineTo(16F, 9F);
        path.lineTo(16F, 10F);
        path.lineTo(0.0F, 10F);
        path.moveTo(0.0F, 22F);
        path.lineTo(16F, 22F);
        path.lineTo(16F, 23F);
        path.lineTo(0.0F, 23F);
        path.moveTo(5F, 9F);
        path.lineTo(5F, 22F);
        path.lineTo(6F, 22F);
        path.lineTo(6F, 9F);
        path.moveTo(10F, 9F);
        path.lineTo(10F, 22F);
        path.lineTo(11F, 22F);
        path.lineTo(11F, 9F);
        return path;
    }

    public Shape createDoNotEnter()
    {
        java.awt.geom.Ellipse2D.Float circle = new java.awt.geom.Ellipse2D.Float(0.0F, 7F, 16F, 16F);
        GeneralPath path = new GeneralPath(circle);
        path.moveTo(3F, 14F);
        path.lineTo(13F, 14F);
        path.lineTo(13F, 15F);
        path.lineTo(3F, 15F);
        return path;
    }

    public Shape createDot()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(2.0F, 15F);
        path.lineTo(5F, 15F);
        path.lineTo(5F, 18F);
        path.lineTo(2.0F, 18F);
        path.lineTo(2.0F, 15F);
        return path;
    }

    public Shape createVerticalLine()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 2.0F);
        path.lineTo(0.0F, 28F);
        path.lineTo(1.0F, 28F);
        path.lineTo(1.0F, 2.0F);
        return path;
    }

    public Shape createRunway()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 8F);
        path.lineTo(16F, 8F);
        path.moveTo(0.0F, 9F);
        path.lineTo(16F, 9F);
        path.moveTo(0.0F, 12F);
        path.lineTo(16F, 12F);
        path.moveTo(0.0F, 13F);
        path.lineTo(16F, 13F);
        path.moveTo(0.0F, 16F);
        path.lineTo(8F, 16F);
        path.moveTo(0.0F, 17F);
        path.lineTo(8F, 17F);
        path.moveTo(0.0F, 20F);
        path.lineTo(8F, 20F);
        path.moveTo(0.0F, 21F);
        path.lineTo(8F, 21F);
        return path;
    }

    public Shape createLeftBorder()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 2.0F);
        path.lineTo(0.0F, 27F);
        return path;
    }

    public Shape createRightBorder()
    {
        GeneralPath path = new GeneralPath();
        path.moveTo(0.0F, 2.0F);
        path.lineTo(0.0F, 27F);
        return path;
    }

    private String labelText;
    private String specialItems;
    private Pattern typePattern;
    private int borderSize;
    private int align;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_RIGHT = 3;
}
