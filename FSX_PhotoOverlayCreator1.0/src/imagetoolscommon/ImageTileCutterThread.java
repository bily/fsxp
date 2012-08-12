// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageTileCutterThread.java

package imagetoolscommon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import javax.imageio.*;

// Referenced classes of package imagetoolscommon:
//            ImageData, Utils, TileObserver

public abstract class ImageTileCutterThread extends Thread
{

    public ImageTileCutterThread()
    {
        currentTileCount = 0;
        totalTileCount = 0;
        running = false;
        abortCreate = false;
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        ImageTileCutterThread result = (ImageTileCutterThread)super.clone();
        return result;
    }

    public int getCurrentTileCount()
    {
        return currentTileCount;
    }

    public int getTotalTileCount()
    {
        return totalTileCount;
    }

    public void setTileObserver(TileObserver tileObserver)
    {
        this.tileObserver = tileObserver;
    }

    public void setImageData(ImageData imageData)
    {
        originalImage = imageData.image;
        imageFilename = imageData.filename;
        strCreateDir = imageData.file.getParent();
        File tileDir = new File(strCreateDir, Utils.changeFileExt(imageFilename, "") + "-tiles");
        strTileDir = tileDir.getAbsolutePath() + File.separator;
        ImageTileCutterThread _tmp = this;
        int depth = calculateMaxZoomDepth(originalImage.getWidth(), originalImage.getHeight());
        createDepth = depth;
    }

    public boolean isRunning()
    {
        return running;
    }

    public void run()
    {
        running = true;
        currentTileCount = 0;
        currentFilename = "Initialising...";
        beginCreate(createDepth);
        if(!abortCreate)
            totalTileCount = currentTileCount;
        if(tileObserver != null)
            tileObserver.finishedProcessingTiles(totalTileCount);
        running = false;
    }

    public static int calculateMaxZoomDepth(int width, int height)
    {
        double d = Math.max(width, height);
        double z = Math.log(d / 256D) / Math.log(2D);
        int depth = (int)Math.ceil(z + 1.0D);
        return depth;
    }

    public static int calculateTotalTileCount(int depth)
    {
        int count = 0;
        for(int i = 0; i < depth; i++)
            count = (int)((double)count + Math.pow(4D, i));

        return count;
    }

    public void abortCreate()
    {
        abortCreate = true;
    }

    protected void setupImageWriter()
    {
        Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
        if(iter.hasNext())
        {
            imageWriter = (ImageWriter)iter.next();
            iwp = imageWriter.getDefaultWriteParam();
            iwp.setCompressionMode(2);
        }
    }

    public abstract void beginCreate(int i);

    protected TileObserver tileObserver;
    protected int currentTileCount;
    protected int totalTileCount;
    protected String currentFilename;
    protected ImageWriter imageWriter;
    protected ImageWriteParam iwp;
    protected BufferedImage originalImage;
    protected int createDepth;
    protected String imageFilename;
    protected String strCreateDir;
    protected String strTileDir;
    protected boolean running;
    protected boolean abortCreate;
}
