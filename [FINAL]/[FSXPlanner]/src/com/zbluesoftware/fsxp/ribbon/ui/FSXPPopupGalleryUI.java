// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPPopupGalleryUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.ZPopupGallery;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZPopupGalleryUI

public class FSXPPopupGalleryUI extends ZPopupGalleryUI
{

    public static ComponentUI createUI(JComponent b)
    {
        return INSTANCE;
    }

    public FSXPPopupGalleryUI()
    {
    }

    public void installUI(JComponent component)
    {
        ZPopupGallery gallery = (ZPopupGallery)component;
        super.installUI(gallery);
        installDefaults(gallery);
    }

    public void uninstallUI(JComponent component)
    {
        ZPopupGallery gallery = (ZPopupGallery)component;
        uninstallDefaults(gallery);
        super.uninstallUI(component);
    }

    protected void installDefaults(ZPopupGallery gallery)
    {
        LookAndFeel.installColorsAndFont(gallery, "ZPopupGallery.background", "ZPopupGallery.foreground", "ZPopupGallery.font");
        LookAndFeel.installBorder(gallery, "ZPopupGallery.border");
        LookAndFeel.installProperty(gallery, "opaque", Boolean.TRUE);
    }

    protected void uninstallDefaults(ZPopupGallery gallery)
    {
        LookAndFeel.uninstallBorder(gallery);
    }

    private static final FSXPPopupGalleryUI INSTANCE = new FSXPPopupGalleryUI();

}