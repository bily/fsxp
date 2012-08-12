// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhotoCutterThread.java

package photooverlaycreator;

import imagetoolscommon.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.IIOImage;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

// Referenced classes of package photooverlaycreator:
//            PhotoOverlayData

public class PhotoCutterThread extends ImageTileCutterThread
    implements Cloneable
{

    public PhotoCutterThread()
    {
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        PhotoCutterThread thread = (PhotoCutterThread)super.clone();
        thread.photoOverlayData = photoOverlayData;
        return thread;
    }

    public void setPhotoOverlayData(PhotoOverlayData photoOverlayData)
    {
        this.photoOverlayData = photoOverlayData;
    }

    public void setParams(ImageData imageData, PhotoOverlayData photoOverlayData)
    {
        int width = imageData.image.getWidth();
        int height = imageData.image.getHeight();
        tileObserver = null;
        createDepth = calculateMaxZoomDepth(width, height);
        totalTileCount = calculateTotalTileCount(createDepth, width, height);
        originalImage = imageData.image;
        imageFilename = imageData.filename;
        strCreateDir = imageData.file.getParent();
        File tileDir = new File(imageData.file.getParentFile(), Utils.changeFileExt(imageFilename, "") + "-tiles");
        strTileDir = tileDir.getAbsolutePath() + File.separator;
        this.photoOverlayData = photoOverlayData;
    }

    public void setImageData(ImageData imageData)
    {
        super.setImageData(imageData);
        int width = imageData.image.getWidth();
        int height = imageData.image.getHeight();
        totalTileCount = calculateTotalTileCount(createDepth, width, height);
        if(photoOverlayData != null)
            photoOverlayData.calculateDefaultFOV(width, height);
    }

    public static int calculateTotalTileCount(int depth, int width, int height)
    {
        float fwidth = width;
        float fheight = height;
        float size = Math.max(width, height);
        float d = (float)Math.ceil(Math.log(size) / Math.log(2D));
        size = (float)Math.pow(2D, d);
        int tilecount = 0;
        float tilesize = size;
        float axiscount = 1.0F;
        for(int i = 0; i < depth; i++)
        {
            int countx = (int)Math.ceil(fwidth / tilesize);
            int county = (int)Math.ceil(fheight / tilesize);
            tilecount += countx * county;
            tilesize /= 2.0F;
        }

        return tilecount;
    }

    public static void generatePhotoOverlayKML(String templateKML, String mainKMLName, String createDir, String tileDir, String imageName, int width, int height, PhotoOverlayData photoOverlayData)
        throws Exception
    {
        String nameTag = "<%NAME>";
        String descriptionTag = "<%DESCRIPTION>";
        String imageURLTag = "<%IMAGEURL>";
        String shapeTag = "<%SHAPE>";
        String maxWidthTag = "<%MAXWIDTH>";
        String maxHeightTag = "<%MAXHEIGHT>";
        String leftFOVTag = "<%LEFTFOV>";
        String rightFOVTag = "<%RIGHTFOV>";
        String topFOVTag = "<%TOPFOV>";
        String bottomFOVTag = "<%BOTTOMFOV>";
        String lonTag = "<%LON>";
        String latTag = "<%LAT>";
        String altTag = "<%ALT>";
        String nearTag = "<%NEAR>";
        URL templateURL = (photooverlaycreator.PhotoOverlayApp.class).getResource(templateKML);
        BufferedReader reader = new BufferedReader(new InputStreamReader(templateURL.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(createDir + "/" + mainKMLName));
        String line;
        while((line = reader.readLine()) != null) 
        {
            if(line.indexOf("<%") >= 0)
            {
                line = line.replaceAll("<%NAME>", imageName);
                line = line.replaceAll("<%DESCRIPTION>", photoOverlayData.description);
                line = line.replaceAll("<%IMAGEURL>", tileDir + "/" + imageName + "_\\$\\[level\\]_\\$\\[x\\]_\\$\\[y\\].jpg");
                line = line.replaceAll("<%SHAPE>", photoOverlayData.getStringShapeType());
                line = line.replaceAll("<%MAXWIDTH>", Integer.toString(width));
                line = line.replaceAll("<%MAXHEIGHT>", Integer.toString(height));
                line = line.replaceAll("<%LEFTFOV>", Float.toString(photoOverlayData.fovLeft));
                line = line.replaceAll("<%RIGHTFOV>", Float.toString(photoOverlayData.fovRight));
                line = line.replaceAll("<%BOTTOMFOV>", Float.toString(photoOverlayData.fovBottom));
                line = line.replaceAll("<%TOPFOV>", Float.toString(photoOverlayData.fovTop));
                line = line.replaceAll("<%LON>", Float.toString(photoOverlayData.lon));
                line = line.replaceAll("<%LAT>", Float.toString(photoOverlayData.lat));
                line = line.replaceAll("<%ALT>", Float.toString(photoOverlayData.alt));
                line = line.replaceAll("<%NEAR>", Float.toString(photoOverlayData.near));
            }
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        reader.close();
    }

    private void makePhotoOverlayTiles(int depth, int size, int tileX, int tileY)
    {
        if(abortCreate)
            return;
        String filenameStub = Utils.changeFileExt(imageFilename, "");
        BufferedImage tileImg = new BufferedImage(256, 256, 1);
        Graphics2D g = tileImg.createGraphics();
label0:
        for(int z = 0; z < depth; z++)
        {
            int count = (int)Math.ceil(Math.pow(2D, z));
            double px = (double)size / (double)count;
            double y = 0.0D;
            int gy = 0;
            do
            {
                if(gy >= count)
                    continue label0;
                double x = 0.0D;
                int gx = 0;
                do
                {
                    if(gx >= count)
                        break;
                    BufferedImage tmp = Utils.get256pxScaledInstance(originalImage, (int)x, (int)y, (int)(x + px), (int)(y + px));
                    g.drawImage(tmp, 0, 0, null);
                    try
                    {
                        currentTileCount++;
                        if(tileObserver != null)
                            tileObserver.processingTile(filenameStub + "_" + z + "_" + gx + "_" + gy + ".jpg", currentTileCount, totalTileCount);
                        imageWriter.setOutput(new FileImageOutputStream(new File(strTileDir + filenameStub + "_" + z + "_" + gx + "_" + gy + ".jpg")));
                        IIOImage iioimage = new IIOImage(tileImg, null, null);
                        imageWriter.write(null, iioimage, iwp);
                    }
                    catch(IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    x += px;
                    if(x >= (double)originalImage.getWidth())
                        break;
                    gx++;
                } while(true);
                y += px;
                if(y >= (double)originalImage.getHeight())
                    continue label0;
                gy++;
            } while(true);
        }

        g.dispose();
    }

    public void beginCreate(int depth)
    {
        abortCreate = false;
        currentTileCount = 0;
        File tileDir = new File(strTileDir);
        tileDir.mkdirs();
        try
        {
            String kmlName = Utils.changeFileExt(imageFilename, ".kml");
            String imageName = Utils.changeFileExt(imageFilename, "");
            generatePhotoOverlayKML("templatefiles/photooverlay-template.kml", kmlName, strCreateDir, tileDir.getName(), imageName, originalImage.getWidth(), originalImage.getHeight(), photoOverlayData);
        }
        catch(Exception ex)
        {
            Utils.showErrorDialogBox(null, "Error Generating KML File", null, ex);
            return;
        }
        setupImageWriter();
        int size = originalImage.getWidth();
        int height = originalImage.getHeight();
        if(height > size)
            size = height;
        float d = (float)Math.ceil(Math.log(size) / Math.log(2D));
        size = (int)Math.pow(2D, d);
        makePhotoOverlayTiles(depth, size, 0, 0);
    }

    private PhotoOverlayData photoOverlayData;
}
