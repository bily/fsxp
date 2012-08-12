// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageFileFilter.java

package imagetoolscommon;

import java.io.File;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileFilter;

// Referenced classes of package imagetoolscommon:
//            Utils

public class ImageFileFilter extends FileFilter
{

    public ImageFileFilter()
    {
        initFileFormats();
    }

    public static void initFileFormats()
    {
        HashSet hash = new HashSet();
        String names[] = ImageIO.getReaderFormatNames();
        for(int i = 0; i < names.length; i++)
            hash.add(names[i]);

        imageFileExts = "";
        imageFilesDescription = " ";
        if(hash.contains("jpeg") || hash.contains("jpg"))
        {
            imageFileExts = imageFileExts + ".jpg.jpeg";
            imageFilesDescription = imageFilesDescription + ".jpg .jpeg ";
        }
        if(hash.contains("gif"))
        {
            imageFileExts = imageFileExts + ".gif";
            imageFilesDescription = imageFilesDescription + ".gif ";
        }
        if(hash.contains("png"))
        {
            imageFileExts = imageFileExts + ".png";
            imageFilesDescription = imageFilesDescription + ".png ";
        }
        if(hash.contains("tif") || hash.contains("tiff"))
        {
            imageFileExts = imageFileExts + ".tif.tiff";
            imageFilesDescription = imageFilesDescription + ".tif .tiff ";
        }
        if(hash.contains("bmp"))
        {
            imageFileExts = imageFileExts + ".bmp";
            imageFilesDescription = imageFilesDescription + ".bmp ";
        }
        if(hash.contains("raw"))
        {
            imageFileExts = imageFileExts + ".raw";
            imageFilesDescription = imageFilesDescription + ".raw ";
        }
        if(hash.contains("pnm"))
        {
            imageFileExts = imageFileExts + ".pnm";
            imageFilesDescription = imageFilesDescription + ".pnm ";
        }
    }

    public boolean accept(File f)
    {
        String ext = Utils.getFileExtension(f.getName());
        if(ext == null)
        {
            return f.isDirectory();
        } else
        {
            ext = ext.toLowerCase();
            return f.isDirectory() || imageFileExts.indexOf(ext) >= 0;
        }
    }

    public String getDescription()
    {
        return "Image Files (" + imageFilesDescription + ")";
    }

    public static String imageFileExts;
    public static String imageFilesDescription;
}
