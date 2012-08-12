package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
//import java.awt.RenderingHints$Key;
import java.awt.Shape;
import java.awt.geom.Line2D;
//import java.awt.geom.Line2D$Double;
//import java.awt.geom.Line2D$Float;
import java.awt.geom.Rectangle2D;
//import java.awt.geom.Rectangle2D$Float;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.util.ArrayList;

class MemoryPanel$1 extends Thread {

    MemoryPanel$1(MemoryPanel memorypanel1)
    {
        this$0 = memorypanel1;
    }

    MemoryPanel this$0;     // final access specifier removed

    public void run()
    {
        while( MemoryPanel.access$000( this$0 ) )
        {
            Runtime runtime1;

            try
            {
                Thread.sleep( 2500L );
            }
            catch( InterruptedException ignored )
            {
            }
            runtime1 = Runtime.getRuntime();
            MemoryPanel.access$400( this$0 ).add( new Long( MemoryPanel.access$100( this$0 ) ) );
            while( MemoryPanel.access$400( this$0 ).size() > MemoryPanel.access$500( this$0 ) )
                MemoryPanel.access$400( this$0 ).remove( 0 );
            MemoryPanel.access$600( this$0 ).add( new Long( MemoryPanel.access$300( this$0 ) ) );
            while( MemoryPanel.access$600( this$0 ).size() > MemoryPanel.access$500( this$0 ) )
                MemoryPanel.access$600( this$0 ).remove( 0 );
            this$0.repaint();
        }
    }
}


public class MemoryPanel extends JPanel {

    public MemoryPanel()
    {
        Runtime runtime = Runtime.getRuntime();

        freeMemory = runtime.freeMemory();
        maxMemory = runtime.maxMemory();
        totalMemory = runtime.totalMemory();
        memoryFormat = NumberFormat.getInstance();
        memoryFormat.setMaximumFractionDigits( 2 );
        freeMemoryAL = new ArrayList();
        totalMemoryAL = new ArrayList();
        maxRecordedMemory = 10;
        pollThread = null;
        working = true;
        windowBorder = new WindowBorder( "Memory" );
        windowBorder.setTitleBarColor( Utilities.LIGHT_BG_COLOR );
        setBorder( (Border) windowBorder );
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
    public static final Color GRAPH_BG_COLOR = new Color( 137, 202, 157 );
    public static final Color TOTAL_MEMORY_COLOR = new Color( 0, 0, 179 );
    public static final Color USED_MEMORY_COLOR = new Color( 173, 1, 1 );

    public WindowBorder getWindowBorder()
    {
        return windowBorder;
    }

    public Dimension getMinimumSize()
    {
        return new Dimension( 100, 125 );
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
        if( pollThread == null || !pollThread.isAlive() )
        {
            pollThread = (Thread) new MemoryPanel$1( this );
            pollThread.setPriority( 4 );
            pollThread.start();
        }
    }

    public void setWorking(boolean working)
    {
        this.working = working;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2;
        Object hints;
        FontMetrics fontMetrics;
        int fontHeight;
        Insets insets;
        int x;
        int y;
        int width;
        int height;
        int currentYPos;
        int graphHeight;
        int freeMemorySize;
        int i;

        super.paint( g );
        g2 = (Graphics2D) g;
        hints = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setRenderingHints( (Map) hints );
        g2.setFont( new Font( "Verdana", 0, 10 ) );
        fontMetrics = g2.getFontMetrics();
        fontHeight = fontMetrics.getHeight();
        insets = getInsets();
        x = insets.left;
        y = insets.top;
        width = getSize().width - x - insets.right;
        height = getSize().height - y - insets.bottom;
        currentYPos = y + 1 + fontHeight;
        g2.setPaint( (Paint) Color.white );
        g2.fill( (Shape) new java.awt.geom.Rectangle2D.Float( (float) x, (float) y, (float) width, (float) height ) );
        g2.setPaint( (Paint) Color.black );
        g2.drawString( "Free:", 5, currentYPos );
        g2.setPaint( (Paint) Utilities.DATA_HIGHLIGHT_COLOR );
        g2.drawString( new StringBuilder().append( memoryFormat.format( (double) freeMemory / 1024.0 / 1024.0 ) ).append( " MB" ).toString(), 5 + fontMetrics.stringWidth( "Free: " ), currentYPos );
        currentYPos += fontHeight;
        g2.setPaint( (Paint) Color.black );
        g2.drawString( "Total:", 5, currentYPos );
        g2.setPaint( (Paint) Utilities.DATA_HIGHLIGHT_COLOR );
        g2.drawString( new StringBuilder().append( memoryFormat.format( (double) totalMemory / 1024.0 / 1024.0 ) ).append( " MB" ).toString(), 5 + fontMetrics.stringWidth( "Free: " ), currentYPos );
        currentYPos += fontHeight;
        g2.setPaint( (Paint) Color.black );
        g2.drawString( "Max:", 5, currentYPos );
        g2.setPaint( (Paint) Utilities.DATA_HIGHLIGHT_COLOR );
        g2.drawString( new StringBuilder().append( memoryFormat.format( (double) maxMemory / 1024.0 / 1024.0 ) ).append( " MB" ).toString(), 5 + fontMetrics.stringWidth( "Free: " ), currentYPos );
        currentYPos += fontHeight / 2;
        graphHeight = getSize().height - 75;
        maxRecordedMemory = width - 3;
        g2.setPaint( (Paint) new Color( 128, 128, 128 ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) x, (float) currentYPos, (float) x, (float) (currentYPos + graphHeight) ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) x, (float) currentYPos, (float) (x + width - 1), (float) currentYPos ) );
        g2.setPaint( (Paint) new Color( 64, 64, 64 ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) (x + 1), (float) (currentYPos + 1), (float) (x + 1), (float) (currentYPos + graphHeight - 1) ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) (x + 1), (float) (currentYPos + 1), (float) (x + width - 2), (float) (currentYPos + 1) ) );
        g2.setPaint( (Paint) new Color( 200, 200, 200 ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) x, (float) (currentYPos + graphHeight + 1), (float) (x + width - 1), (float) (currentYPos + graphHeight + 1) ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) (x + width), (float) currentYPos, (float) (x + width), (float) (currentYPos + graphHeight + 1) ) );
        g2.setPaint( (Paint) new Color( 160, 160, 160 ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) (x + 1), (float) (currentYPos + graphHeight), (float) (x + width - 2), (float) (currentYPos + graphHeight) ) );
        g2.draw( (Shape) new java.awt.geom.Line2D.Float( (float) (x + width - 1), (float) (currentYPos + 1), (float) (x + width - 1), (float) (currentYPos + graphHeight) ) );
        g2.setPaint( (Paint) GRAPH_BG_COLOR );
        g2.fill( (Shape) new java.awt.geom.Rectangle2D.Float( (float) (x + 2), (float) (currentYPos + 2), (float) (width - 3), (float) (graphHeight - 2) ) );
        freeMemorySize = freeMemoryAL.size();
        for( i = 0; i < totalMemoryAL.size(); ++i )
        {
            double totalStickHeight;

            g2.setPaint( (Paint) TOTAL_MEMORY_COLOR );
            totalStickHeight = (double) ((Long) totalMemoryAL.get( i )).longValue() / (double) maxMemory * ((double) graphHeight - 3.0);
            g2.draw( (Shape) new java.awt.geom.Line2D.Double( (double) x + 2.0 + (double) i, (double) currentYPos + (double) graphHeight - 1.0, (double) x + 2.0 + (double) i, (double) currentYPos + (double) graphHeight - totalStickHeight - 1.0 ) );
            if( i < freeMemorySize )
            {
                double usedStickHeight;

                g2.setPaint( (Paint) USED_MEMORY_COLOR );
                usedStickHeight = ((double) ((Long) totalMemoryAL.get( i )).longValue() - (double) ((Long) freeMemoryAL.get( i )).longValue()) / (double) ((Long) totalMemoryAL.get( i )).longValue() * (totalStickHeight - 3.0);
                g2.draw( (Shape) new java.awt.geom.Line2D.Double( (double) x + 2.0 + (double) i, (double) currentYPos + (double) graphHeight - 1.0, (double) x + 2.0 + (double) i, (double) currentYPos + (double) graphHeight - usedStickHeight - 1.0 ) );
            }
        }
    }

    static boolean access$000(MemoryPanel x0)
    {
        return x0.working;
    }

    static long access$102(MemoryPanel x0, long x1)
    {
        return x0.freeMemory = x1;
    }

    static long access$202(MemoryPanel x0, long x1)
    {
        return x0.maxMemory = x1;
    }

    static long access$302(MemoryPanel x0, long x1)
    {
        return x0.totalMemory = x1;
    }

    static long access$100(MemoryPanel x0)
    {
        return x0.freeMemory;
    }

    static ArrayList access$400(MemoryPanel x0)
    {
        return x0.freeMemoryAL;
    }

    static int access$500(MemoryPanel x0)
    {
        return x0.maxRecordedMemory;
    }

    static long access$300(MemoryPanel x0)
    {
        return x0.totalMemory;
    }

    static ArrayList access$600(MemoryPanel x0)
    {
        return x0.totalMemoryAL;
    }
}