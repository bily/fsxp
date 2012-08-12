// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PhotoOverlayApp.java

package photooverlaycreator;

import imagetoolscommon.ImageData;
import imagetoolscommon.Utils;
import java.awt.EventQueue;
import javax.swing.UIManager;

// Referenced classes of package photooverlaycreator:
//            PhotoOverlayData, PhotoMainFrame

public class PhotoOverlayApp
{

    public PhotoOverlayApp()
    {
    }

    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable() {

            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception ex)
                {
                    Utils.showErrorDialogBox(null, "Exception setting look and feel", "", ex);
                }
                (new PhotoMainFrame()).setVisible(true);
            }

        }
);
    }

    public static final String appName = "Photo Overlay Creator";
    public static final String appVersion = "1.0";
    public static ImageData imageData;
    public static PhotoOverlayData photoOverlayData = new PhotoOverlayData();
    public static final String templateKML = "templatefiles/photooverlay-template.kml";
    public static final String placesXML = "templatefiles/places.xml";

}
