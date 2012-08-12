// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MemoryPanel.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JPanel;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            WindowBorder

public class MemoryPanel extends JPanel
{

    public MemoryPanel()
    {
        Runtime runtime = Runtime.getRuntime();
        freeMemory = runtime.freeMemory();
        maxMemory = runtime.maxMemory();
        totalMemory = runtime.totalMemory();
        memoryFormat = NumberFormat.getInstance();
        memoryFormat.setMaximumFractionDigits(2);
        freeMemoryAL = new ArrayList();
        totalMemoryAL = new ArrayList();
        maxRecordedMemory = 10;
        pollThread = null;
        working = true;
        windowBorder = new WindowBorder("Memory");
        windowBorder.setTitleBarColor(Utilities.LIGHT_BG_COLOR);
        setBorder(windowBorder);
    }

    public WindowBorder getWindowBorder()
    {
        return windowBorder;
    }

    public Dimension getMinimumSize()
    {
        return new Dimension(100, 125);
    }

    public Dimension getPreferredSize()
    {
        return getMinimumSize();
    }

    public Dimension getMaximumSize()
    {
        return getMinimumSize();
    }

    public void startPoll()
    {
        if(pollThread != null && pollThread.isAlive())
        {
            return;
        } else
        {
            pollThread = new Thread() {

                public void run()
                {
                    while(working) 
                    {
                        try
                        {
                            Thread.sleep(2500L);
                        }
                        catch(InterruptedException ignored) { }
                        Runtime runtime = Runtime.getRuntime();
                        freeMemory = runtime.freeMemory();
                        maxMemory = runtime.maxMemory();
                        totalMemory = runtime.totalMemory();
                        freeMemoryAL.add(new Long(freeMemory));
                        for(; freeMemoryAL.size() > maxRecordedMemory; freeMemoryAL.remove(0));
                        totalMemoryAL.add(new Long(totalMemory));
                        for(; totalMemoryAL.size() > maxRecordedMemory; totalMemoryAL.remove(0));
                        repaint();
                    }
                }

                final MemoryPanel this$0;

            
            {
                this$0 = MemoryPanel.this;
            }
            }
;
            pollThread.setPriority(4);
            pollThread.start();
            return;
        }
    }

    public void setWorking(boolean working)
    {
        this.working = working;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(hints);
        g2.setFont(new Font("Verdana", 0, 10));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int fontHeight = fontMetrics.getHeight();
        Insets insets = getInsets();
        int x = insets.left;
        int y = insets.top;
        int width = getSize().width - x - insets.right;
        int height = getSize().height - y - insets.bottom;
        int currentYPos = y + 1 + fontHeight;
        g2.setPaint(Color.white);
        g2.fill(new java.awt.geom.Rectangle2D.Float(x, y, width, height));
        g2.setPaint(Color.black);
        g2.drawString("Free:", 5, currentYPos);
        g2.setPaint(Utilities.DATA_HIGHLIGHT_COLOR);
        g2.drawString((new StringBuilder()).append(memoryFormat.format((double)freeMemory / 1024D / 1024D)).append(" MB").toString(), 5 + fontMetrics.stringWidth("Free: "), currentYPos);
        currentYPos += fontHeight;
        g2.setPaint(Color.black);
        g2.drawString("Total:", 5, currentYPos);
        g2.setPaint(Utilities.DATA_HIGHLIGHT_COLOR);
        g2.drawString((new StringBuilder()).append(memoryFormat.format((double)totalMemory / 1024D / 1024D)).append(" MB").toString(), 5 + fontMetrics.stringWidth("Free: "), currentYPos);
        currentYPos += fontHeight;
        g2.setPaint(Color.black);
        g2.drawString("Max:", 5, currentYPos);
        g2.setPaint(Utilities.DATA_HIGHLIGHT_COLOR);
        g2.drawString((new StringBuilder()).append(memoryFormat.format((double)maxMemory / 1024D / 1024D)).append(" MB").toString(), 5 + fontMetrics.stringWidth("Free: "), currentYPos);
        currentYPos += fontHeight / 2;
        int graphHeight = getSize().height - 75;
        maxRecordedMemory = width - 3;
        g2.setPaint(new Color(128, 128, 128));
        g2.draw(new java.awt.geom.Line2D.Float(x, currentYPos, x, currentYPos + graphHeight));
        g2.draw(new java.awt.geom.Line2D.Float(x, currentYPos, (x + width) - 1, currentYPos));
        g2.setPaint(new Color(64, 64, 64));
        g2.draw(new java.awt.geom.Line2D.Float(x + 1, currentYPos + 1, x + 1, (currentYPos + graphHeight) - 1));
        g2.draw(new java.awt.geom.Line2D.Float(x + 1, currentYPos + 1, (x + width) - 2, currentYPos + 1));
        g2.setPaint(new Color(200, 200, 200));
        g2.draw(new java.awt.geom.Line2D.Float(x, currentYPos + graphHeight + 1, (x + width) - 1, currentYPos + graphHeight + 1));
        g2.draw(new java.awt.geom.Line2D.Float(x + width, currentYPos, x + width, currentYPos + graphHeight + 1));
        g2.setPaint(new Color(160, 160, 160));
        g2.draw(new java.awt.geom.Line2D.Float(x + 1, currentYPos + graphHeight, (x + width) - 2, currentYPos + graphHeight));
        g2.draw(new java.awt.geom.Line2D.Float((x + width) - 1, currentYPos + 1, (x + width) - 1, currentYPos + graphHeight));
        g2.setPaint(GRAPH_BG_COLOR);
        g2.fill(new java.awt.geom.Rectangle2D.Float(x + 2, currentYPos + 2, width - 3, graphHeight - 2));
        int freeMemorySize = freeMemoryAL.size();
        for(int i = 0; i < totalMemoryAL.size(); i++)
        {
            g2.setPaint(TOTAL_MEMORY_COLOR);
            double totalStickHeight = ((double)((Long)totalMemoryAL.get(i)).longValue() / (double)maxMemory) * ((double)graphHeight - 3D);
            g2.draw(new java.awt.geom.Line2D.Double((double)x + 2D + (double)i, ((double)currentYPos + (double)graphHeight) - 1.0D, (double)x + 2D + (double)i, ((double)currentYPos + (double)graphHeight) - totalStickHeight - 1.0D));
            if(i < freeMemorySize)
            {
                g2.setPaint(USED_MEMORY_COLOR);
                double usedStickHeight = (((double)((Long)totalMemoryAL.get(i)).longValue() - (double)((Long)freeMemoryAL.get(i)).longValue()) / (double)((Long)totalMemoryAL.get(i)).longValue()) * (totalStickHeight - 3D);
                g2.draw(new java.awt.geom.Line2D.Double((double)x + 2D + (double)i, ((double)currentYPos + (double)graphHeight) - 1.0D, (double)x + 2D + (double)i, ((double)currentYPos + (double)graphHeight) - usedStickHeight - 1.0D));
            }
        }

    }

    private NumberFormat memoryFormat;
    private Thread pollThread;
    private WindowBorder windowBorder;
    private ArrayList freeMemoryAL;
    private ArrayList totalMemoryAL;
    private int maxRecordedMemory;
    private long freeMemory;
    private long maxMemory;
    private long totalMemory;
    private boolean working;
    public static final Color GRAPH_BG_COLOR = new Color(137, 202, 157);
    public static final Color TOTAL_MEMORY_COLOR = new Color(0, 0, 179);
    public static final Color USED_MEMORY_COLOR = new Color(173, 1, 1);










}
