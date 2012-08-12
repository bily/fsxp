// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Utils.java

package gmapimagecutter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.swing.*;

// Referenced classes of package gmapimagecutter:
//            GMapImgCutApp

public class Utils
{

    public Utils()
    {
    }

    public static String changeFileExt(String filename, String newExtension)
    {
        int pos = filename.lastIndexOf(".");
        if(pos >= 0)
            return (new StringBuilder()).append(filename.substring(0, pos)).append(newExtension).toString();
        else
            return (new StringBuilder()).append(filename).append(newExtension).toString();
    }

    public static String getFileExtension(String filename)
    {
        int pos = filename.lastIndexOf(".");
        if(pos >= 0)
            return filename.substring(pos);
        else
            return null;
    }

    public static String extractFilename(String filename)
    {
        int pos = filename.lastIndexOf('\\');
        if(pos >= 0)
            return filename.substring(pos + 1);
        else
            return filename;
    }

    public static void generateHTML(String templateHTML, String mainHTMLName, String createDir, String tileDir, int maxZoom)
        throws Exception
    {
        String apiKeyTag = "<%APIKEY>";
        String apiVersionTag = "<%APIVERSION>";
        String mapTitleTag = "<%MAPTITLE>";
        String maxZoomTag = "<%MAXZOOM>";
        String maxZoomP1Tag = "<%MAXZOOMP1>";
        String initialZoomTag = "<%INITIALZOOM>";
        String centreLatTag = "<%CENTRELAT>";
        String centreLonTag = "<%CENTRELON>";
        String tileDirTag = "<%TILEDIR>";
        double centreLat = 0.0D;
        double centreLon = 0.0D;
        int initialZoom = maxZoom >> 1;
        URL templateURL = gmapimagecutter.GMapImgCutApp.class.getResource(templateHTML);
        BufferedReader reader = new BufferedReader(new InputStreamReader(templateURL.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter((new StringBuilder()).append(createDir).append("/").append(mainHTMLName).toString()));
        String line;
        while((line = reader.readLine()) != null) 
        {
            if(line.indexOf("<%") >= 0)
            {
                line = line.replaceAll("<%APIKEY>", "PUTAPIKEYHERE");
                line = line.replaceAll("<%APIVERSION>", "2.x");
                line = line.replaceAll("<%MAPTITLE>", "Picture Viewer");
                line = line.replaceAll("<%MAXZOOM>", Integer.toString(maxZoom));
                line = line.replaceAll("<%MAXZOOMP1>", Integer.toString(maxZoom + 1));
                line = line.replaceAll("<%INITIALZOOM>", Integer.toString(initialZoom));
                line = line.replaceAll("<%CENTRELAT>", java.lang.Double.toString(centreLat));
                line = line.replaceAll("<%CENTRELON>", java.lang.Double.toString(centreLon));
                line = line.replaceAll("<%TILEDIR>", tileDir);
            }
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        reader.close();
    }

    public static void showErrorDialogBox(Component parent, String title, String message, Exception ex)
    {
        JTextArea textArea = new JTextArea(10, 80);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setText("");
        if(message != null)
            textArea.append((new StringBuilder()).append(message).append("\n\n").toString());
        if(ex != null)
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            textArea.append((new StringBuilder()).append("Exception:\n").append(sw.getBuffer().toString()).toString());
        }
        textArea.setCaretPosition(0);
        JOptionPane.showMessageDialog(parent, scrollPane, title, 0);
    }

    public static java.awt.geom.Point2D.Double mercatorXYToLonLat(double x, double y)
    {
        double lon = Math.toRadians(x);
        double lat = 2D * Math.atan(Math.exp(Math.toRadians(y))) - 1.5707963267948966D;
        return new java.awt.geom.Point2D.Double(Math.toDegrees(lon), Math.toDegrees(lat));
    }

    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight)
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

    public static void drawImage(Graphics2D g, BufferedImage image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, 
            int sx2, int sy2, Color col)
    {
        int sw = image.getWidth();
        int sh = image.getHeight();
        if(sx1 < 0 && sx2 < 0 || sx1 >= sw && sx2 >= sw || sy1 < 0 && sy2 < 0 || sy1 >= sh && sy2 >= sh)
        {
            g.setPaint(col);
            g.fillRect(dx1, dy1, dx2 - dx1, dy2 - dy1);
            return;
        }
        double scaleX = (double)(dx2 - dx1) / (double)(sx2 - sx1);
        double scaleY = (double)(dy2 - dy1) / (double)(sy2 - sy1);
        if(sx1 < 0)
        {
            dx1 = (int)Math.floor((double)(-sx1) * scaleX);
            sx1 = 0;
        }
        if(sx2 > sw)
        {
            dx2 = (int)Math.floor((double)dx2 - (double)(sx2 - sw) * scaleX);
            sx2 = sw;
        }
        if(sy1 < 0)
        {
            dy1 = (int)Math.floor((double)(-sy1) * scaleY);
            sy1 = 0;
        }
        if(sy2 > sh)
        {
            dy2 = (int)Math.floor((double)dy2 - (double)(sy2 - sh) * scaleY);
            sy2 = sh;
        }
        g.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, col, null);
    }

    public static BufferedImage get256pxScaledInstance(BufferedImage image, int x, int y, int x2, int y2)
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
                drawImage(gChunk, image, 0, 0, chunkWidth, chunkHeight, imageX, imageY, imageX + chunkWidth, imageY + chunkHeight, Color.black);
                int w = (int)Math.ceil(((float)chunkWidth * 256F) / (float)width);
                int h = (int)Math.ceil(((float)chunkHeight * 256F) / (float)height);
                g.drawImage(getScaledInstance(chunk, w, h), (int)Math.floor(tileX), (int)Math.floor(tileY), null);
            }

        }

        g.dispose();
        return tile;
    }

    public static BufferedImage makeSingleTile(BufferedImage originalImage, boolean highQuality, String tileString, int zoomLevel, double x, double y, 
            double width, double height)
    {
        if(zoomLevel >= tileString.length())
        {
            BufferedImage tileImg = new BufferedImage(256, 256, 1);
            Graphics2D g = tileImg.createGraphics();
            g.setPaint(Color.BLACK);
            g.fillRect(0, 0, 256, 256);
            if(highQuality)
            {
                BufferedImage tmp = get256pxScaledInstance(originalImage, (int)x, (int)y, (int)(x + width), (int)(y + height));
                g.drawImage(tmp, 0, 0, null);
            } else
            {
                g.drawImage(originalImage, 0, 0, 256, 256, (int)x, (int)y, (int)(x + width), (int)(y + height), Color.black, null);
            }
            return tileImg;
        }
        double centreX = x + width / 2D;
        double centreY = y + height / 2D;
        switch(tileString.charAt(zoomLevel))
        {
        case 113: // 'q'
            return makeSingleTile(originalImage, highQuality, tileString, zoomLevel + 1, x, y, width / 2D, height / 2D);

        case 114: // 'r'
            return makeSingleTile(originalImage, highQuality, tileString, zoomLevel + 1, centreX, y, width / 2D, height / 2D);

        case 115: // 's'
            return makeSingleTile(originalImage, highQuality, tileString, zoomLevel + 1, centreX, centreY, width / 2D, height / 2D);

        case 116: // 't'
            return makeSingleTile(originalImage, highQuality, tileString, zoomLevel + 1, x, centreY, width / 2D, height / 2D);
        }
        return null;
    }
}
