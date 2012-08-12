// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   GMapImgCutApp.java

package gmapimagecutter;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import javax.swing.*;

// Referenced classes of package gmapimagecutter:
//            MainFrame, ImageTileCutterThread

public class GMapImgCutApp
{

    public GMapImgCutApp()
    {
        packFrame = false;
        MainFrame frame = new MainFrame();
        if(packFrame)
            frame.pack();
        else
            frame.validate();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
        try
        {
            jbInit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static ImageIcon loadIcon(String path, String description)
    {
        java.net.URL imgURL = gmapimagecutter.GMapImgCutApp.class.getResource(path);
        if(imgURL != null)
        {
            return new ImageIcon(imgURL, description);
        } else
        {
            System.err.println((new StringBuilder()).append("Couldn't find file: ").append(path).toString());
            return null;
        }
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable() {

            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception exception)
                {
                    exception.printStackTrace();
                }
                new GMapImgCutApp();
            }

        }
);
    }

    private void jbInit()
        throws Exception
    {
    }

    boolean packFrame;
    public static final String appName = "Google Maps Image Cutter";
    public static final String appVersion = "1.3";
    public static BufferedImage image;
    public static String filename;
    public static String createDir;
    public static int maxZoomDepth = 0;
    public static ImageTileCutterThread imgTileCutThread = new ImageTileCutterThread();
    static final String templateHTML = "templatefiles/template.html";

}