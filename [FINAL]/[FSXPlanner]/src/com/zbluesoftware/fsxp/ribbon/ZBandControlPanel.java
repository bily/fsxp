// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZBandControlPanel.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPBandControlPanelUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZBandControlPanelUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.UIResource;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ZRibbonButton, GalleryElementState, ZRibbonGallery, ZRibbonBand

public class ZBandControlPanel extends JPanel
    implements UIResource
{

    public ZBandControlPanel(ZRibbonBand ribbonBand)
    {
        ribbonButtons = new HashMap();
        ribbonButtonsPriorities = new HashMap();
        ribbonGalleries = new HashMap();
        ribbonGalleriesPriorities = new HashMap();
        galleries = false;
        galleryCount = 0;
        panels = new LinkedList();
    }

    public void setUI(ZBandControlPanelUI ui)
    {
        super.setUI(ui);
    }

    public void updateUI()
    {
        if(UIManager.get(getUIClassID()) != null)
            setUI((ZBandControlPanelUI)UIManager.getUI(this));
        else
            setUI(new FSXPBandControlPanelUI());
    }

    public ZBandControlPanelUI getUI()
    {
        return (ZBandControlPanelUI)ui;
    }

    public String getUIClassID()
    {
        return "ZBandControlPanelUI";
    }

    public String toString()
    {
        return (new StringBuilder()).append("@").append(hashCode()).append(": ").append(panels.size()).append(" panels, ").append(galleryCount).append(" galleries, ").append(ribbonButtons.size()).append(" buttons").toString();
    }

    public java.awt.Component add(java.awt.Component comp, int index)
    {
        throw new UnsupportedOperationException();
    }

    public void add(java.awt.Component comp, Object constraints, int index)
    {
        throw new UnsupportedOperationException();
    }

    public void add(java.awt.Component comp, Object constraints)
    {
        throw new UnsupportedOperationException();
    }

    public java.awt.Component add(java.awt.Component comp)
    {
        throw new UnsupportedOperationException();
    }

    public java.awt.Component add(String name, java.awt.Component comp)
    {
        throw new UnsupportedOperationException();
    }

    public void remove(java.awt.Component comp)
    {
        throw new UnsupportedOperationException();
    }

    public void remove(int index)
    {
        throw new UnsupportedOperationException();
    }

    public void removeAll()
    {
        throw new UnsupportedOperationException();
    }

    public synchronized void addGalleryButton(ZRibbonButton ribbonButton, ZRibbonBand.RibbonElementPriority priority)
    {
        if(!ribbonButtons.containsKey(priority))
            ribbonButtons.put(priority, new LinkedList());
        List al = (List)ribbonButtons.get(priority);
        al.add(ribbonButton);
        ribbonButtonsPriorities.put(ribbonButton, priority);
        ribbonButton.setState(GalleryElementState.BIG, true);
        super.add(ribbonButton);
    }

    public synchronized void addRibbonGallery(ZRibbonGallery ribbonGallery, ZRibbonBand.RibbonElementPriority priority)
    {
        if(!ribbonGalleries.containsKey(priority))
            ribbonGalleries.put(priority, new LinkedList());
        List al = (List)ribbonGalleries.get(priority);
        al.add(ribbonGallery);
        ribbonGalleriesPriorities.put(ribbonGallery, priority);
        ribbonGallery.setState(GalleryElementState.BIG);
        galleries = true;
        galleryCount++;
        super.add(ribbonGallery);
    }

    public synchronized void setPriority(ZRibbonButton ribbonButton, ZRibbonBand.RibbonElementPriority newPriority)
    {
        ZRibbonBand.RibbonElementPriority oldPriority = (ZRibbonBand.RibbonElementPriority)ribbonButtonsPriorities.get(ribbonButton);
        if(newPriority == oldPriority)
            return;
        ((List)ribbonButtons.get(oldPriority)).remove(ribbonButton);
        if(!ribbonButtons.containsKey(newPriority))
            ribbonButtons.put(newPriority, new ArrayList());
        ((List)ribbonButtons.get(newPriority)).add(ribbonButton);
    }

    public synchronized void setPriority(ZRibbonGallery ribbonGallery, ZRibbonBand.RibbonElementPriority newPriority)
    {
        ZRibbonBand.RibbonElementPriority oldPriority = (ZRibbonBand.RibbonElementPriority)ribbonGalleriesPriorities.get(ribbonGallery);
        if(newPriority == oldPriority)
            return;
        ((List)ribbonGalleries.get(oldPriority)).remove(ribbonGallery);
        if(!ribbonGalleries.containsKey(newPriority))
            ribbonGalleries.put(newPriority, new ArrayList());
        ((List)ribbonGalleries.get(newPriority)).add(ribbonGallery);
    }

    public void addPanel(JPanel panel)
    {
        panels.add(panel);
        super.add(panel);
    }

    public List getRibbonButtons(ZRibbonBand.RibbonElementPriority priority)
    {
        List result = (List)ribbonButtons.get(priority);
        if(result == null)
            return EMPTY_GALLERY_BUTTONS_LIST;
        else
            return result;
    }

    public List getRibbonGalleries(ZRibbonBand.RibbonElementPriority priority)
    {
        List result = (List)ribbonGalleries.get(priority);
        if(result == null)
            return EMPTY_RIBBON_GALLERIES_LIST;
        else
            return result;
    }

    public boolean getAllButtonsTopState()
    {
        ZRibbonBand.RibbonElementPriority arr$[] = ZRibbonBand.RibbonElementPriority.values();
        int len$ = arr$.length;
label0:
        for(int i$ = 0; i$ < len$; i$++)
        {
            ZRibbonBand.RibbonElementPriority priority = arr$[i$];
            if(!ribbonButtons.containsKey(priority))
                continue;
            List list = (List)ribbonButtons.get(priority);
            Iterator iterator = list.iterator();
            do
                if(!iterator.hasNext())
                    continue label0;
            while(((ZRibbonButton)iterator.next()).getState() == GalleryElementState.BIG);
            return false;
        }

        return true;
    }

	public boolean hasRibbonGalleries() {
		return hasGalleries;
	}

	public int getRibbonGalleriesCount() {
		return galleryCount;
	}

    public boolean hasGalleries()
    {
        return galleries;
    }

    public int getGalleryCount()
    {
        return galleryCount;
    }

	public List<JPanel> getPanels() {
		return panels;
	}

    private HashMap ribbonGalleries;
    private Map ribbonGalleriesPriorities;
    private Map ribbonButtons;
    private Map ribbonButtonsPriorities;
    private List panels;
    private boolean galleries;
	private boolean hasGalleries;
    private int galleryCount;
    public static final List EMPTY_GALLERY_BUTTONS_LIST = new LinkedList();
    public static final List EMPTY_RIBBON_GALLERIES_LIST = new LinkedList();
    private static final String uiClassID = "ZBandControlPanelUI";
}