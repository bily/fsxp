// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageData.java

package imagetoolscommon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageData
{

    public ImageData()
    {
    }

    public static ImageData loadFile(File file)
        throws IOException
    {
        ImageData imageData = new ImageData();
        imageData.image = ImageIO.read(file);
        imageData.file = file;
        if(imageData.image == null)
        {
            return null;
        } else
        {
            imageData.filename = file.getName();
            imageData.createDir = file.getParent();
            return imageData;
        }
    }

    public BufferedImage image;
    public File file;
    public String filename;
    public String createDir;
}
