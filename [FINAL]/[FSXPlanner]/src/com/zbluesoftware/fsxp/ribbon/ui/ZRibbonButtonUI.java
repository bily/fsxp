// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZRibbonButtonUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.GalleryElementState;
import com.zbluesoftware.fsxp.ribbon.ZPopupGallery;
import javax.swing.plaf.ButtonUI;

public abstract class ZRibbonButtonUI extends ButtonUI
{

    public ZRibbonButtonUI()
    {
    }

    public abstract void updateState(GalleryElementState galleryelementstate, boolean flag);

    public abstract void updateCustomDimension();

    public abstract void updateGallery(ZPopupGallery zpopupgallery);
}