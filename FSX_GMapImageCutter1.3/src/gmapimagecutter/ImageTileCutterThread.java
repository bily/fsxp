// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageTileCutterThread.java

package gmapimagecutter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.FileImageOutputStream;

// Referenced classes of package gmapimagecutter:
//            Utils, TileObserver

public class ImageTileCutterThread extends Thread
{

    ImageTileCutterThread()
    {
        currentTileCount = 0;
        totalTileCount = 0;
        highQuality = true;
        running = false;
        abortCreate = false;
    }

    public void setParams(TileObserver tileObserver, BufferedImage image, String createDir, String imageFilename, int depth, boolean highQuality)
    {
        this.tileObserver = tileObserver;
        createDepth = depth;
        this.highQuality = highQuality;
        totalTileCount = calculateTotalTileCount(depth);
        originalImage = image;
        this.imageFilename = imageFilename;
        strCreateDir = createDir;
        File tileDir = new File(createDir, (new StringBuilder()).append(Utils.changeFileExt(imageFilename, "")).append("-tiles").toString());
        strTileDir = (new StringBuilder()).append(tileDir.getAbsolutePath()).append(File.separator).toString();
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

    private BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight)
    {
        BufferedImage ret = img;
        boolean higherQuality = targetWidth <= img.getWidth() || targetHeight <= img.getHeight();
        int w;
        int h;
        if(higherQuality)
        {
            w = img.getWidth();
            h = img.getHeight();
        } else
        {
            w = targetWidth;
            h = targetHeight;
        }
        do
        {
            if(higherQuality && w > targetWidth)
            {
                w /= 2;
                if(w < targetWidth)
                    w = targetWidth;
            }
            if(higherQuality && h > targetHeight)
            {
                h /= 2;
                if(h < targetHeight)
                    h = targetHeight;
            }
            BufferedImage tmp = new BufferedImage(w, h, 1);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();
            ret = tmp;
        } while(w != targetWidth || h != targetHeight);
        return ret;
    }

    private BufferedImage get256pxScaledInstance(BufferedImage image, int x, int y, int x2, int y2)
    {
        int width = x2 - x;
        int height = y2 - y;
        int chunkPX = 1024;
        BufferedImage tile = new BufferedImage(256, 256, 1);
        Graphics2D g = tile.createGraphics();
        for(int imageY = y; imageY < y + height; imageY += 1024)
        {
            for(int imageX = x; imageX < x + width; imageX += 1024)
            {
                float tileX = (float)Math.floor(((float)(imageX - x) * 256F) / (float)width);
                float tileY = (float)Math.floor(((float)(imageY - y) * 256F) / (float)height);
                int chunkWidth = 1024;
                int chunkHeight = 1024;
                if(imageX + chunkWidth > x2)
                    chunkWidth = x2 - imageX;
                if(imageY + chunkHeight > y2)
                    chunkHeight = y2 - imageY;
                BufferedImage chunk = new BufferedImage(chunkWidth, chunkHeight, 1);
                Graphics2D gChunk = chunk.createGraphics();
                gChunk.drawImage(image, 0, 0, chunkWidth, chunkHeight, imageX, imageY, imageX + chunkWidth, imageY + chunkHeight, Color.black, null);
                int w = (int)Math.ceil(((float)chunkWidth * 256F) / (float)width);
                int h = (int)Math.ceil(((float)chunkHeight * 256F) / (float)height);
                g.drawImage(getScaledInstance(chunk, w, h), (int)Math.floor(tileX), (int)Math.floor(tileY), null);
            }

        }

        g.dispose();
        return tile;
    }

    private void makeTiles(int depth, double x, double y, double width, 
            double height, String tileName)
    {
        if(abortCreate)
            return;
        if(depth <= 0)
            return;
        BufferedImage tileImg = new BufferedImage(256, 256, 1);
        Graphics2D g = tileImg.createGraphics();
        if(highQuality)
        {
            BufferedImage tmp = Utils.get256pxScaledInstance(originalImage, (int)x, (int)y, (int)(x + width), (int)(y + height));
            g.drawImage(tmp, 0, 0, null);
        } else
        {
            g.drawImage(originalImage, 0, 0, 256, 256, (int)x, (int)y, (int)(x + width), (int)(y + height), Color.black, null);
        }
        g.dispose();
        try
        {
            currentTileCount++;
            if(tileObserver != null)
                tileObserver.processingTile((new StringBuilder()).append(tileName).append(".jpg").toString(), currentTileCount, totalTileCount);
            imageWriter.setOutput(new FileImageOutputStream(new File((new StringBuilder()).append(strTileDir).append(tileName).append(".jpg").toString())));
            IIOImage iioimage = new IIOImage(tileImg, null, null);
            imageWriter.write(null, iioimage, iwp);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        double centreX = x + width / 2D;
        double centreY = y + height / 2D;
        makeTiles(depth - 1, x, y, width / 2D, height / 2D, (new StringBuilder()).append(tileName).append("q").toString());
        makeTiles(depth - 1, centreX, y, width / 2D, height / 2D, (new StringBuilder()).append(tileName).append("r").toString());
        makeTiles(depth - 1, centreX, centreY, width / 2D, height / 2D, (new StringBuilder()).append(tileName).append("s").toString());
        makeTiles(depth - 1, x, centreY, width / 2D, height / 2D, (new StringBuilder()).append(tileName).append("t").toString());
    }

    private void beginCreate(int depth)
    {
        abortCreate = false;
        currentTileCount = 0;
        File tileDir = new File(strTileDir);
        tileDir.mkdirs();
        try
        {
            String htmlName = Utils.changeFileExt(imageFilename, ".html");
            Utils.generateHTML("templatefiles/template.html", htmlName, strCreateDir, tileDir.getName(), depth - 1);
        }
        catch(Exception ex)
        {
            Utils.showErrorDialogBox(null, "Error Generating HTML Page", null, ex);
            return;
        }
        Iterator iter = ImageIO.getImageWritersByFormatName("JPG");
        if(iter.hasNext())
        {
            imageWriter = (ImageWriter)iter.next();
            iwp = imageWriter.getDefaultWriteParam();
            iwp.setCompressionMode(2);
        }
        double x = 0.0D;
        double y = 0.0D;
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        if(width < height)
        {
            x = -(height - width) / 2;
            makeTiles(depth, x, y, height, height, "t");
        } else
        if(height < width)
        {
            y = -(width - height) / 2;
            makeTiles(depth, x, y, width, width, "t");
        } else
        {
            makeTiles(depth, x, y, width, height, "t");
        }
        if(tileObserver != null)
            tileObserver.finishedProcessingTiles(currentTileCount);
    }

    private TileObserver tileObserver;
    private int currentTileCount;
    private int totalTileCount;
    private String currentFilename;
    private ImageWriter imageWriter;
    private ImageWriteParam iwp;
    private BufferedImage originalImage;
    private int createDepth;
    private boolean highQuality;
    private String imageFilename;
    private String strCreateDir;
    private String strTileDir;
    private boolean running;
    private boolean abortCreate;
}
