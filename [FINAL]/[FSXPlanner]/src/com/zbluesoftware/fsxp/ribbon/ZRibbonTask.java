// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   ZRibbonTask.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.ZRibbonBandUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ButtonGroup;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ZRibbonButton, ZRibbonBand, ZBandControlPanel

public class ZRibbonTask
    implements MouseListener
{

    public ZRibbonTask()
    {
        bandAL = new ArrayList();
        buttonGroup = new ButtonGroup();
        grouped = false;
    }

    public boolean isGrouped()
    {
        return grouped;
    }

    public void setGrouped(boolean grouped)
    {
        this.grouped = grouped;
    }

    public void addBand(ZRibbonBand band)
    {
        bandAL.add(band);
        band.addMouseListener(this);
        if(grouped)
        {
            ZRibbonBand.RibbonElementPriority arr$[] = ZRibbonBand.RibbonElementPriority.values();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                ZRibbonBand.RibbonElementPriority rep = arr$[i$];
                java.util.List list = band.getControlPanel().getRibbonButtons(rep);
                ZRibbonButton button;
                boolean selected;
                for(Iterator iterator = list.iterator(); iterator.hasNext(); button.setSelected(selected))
                {
                    button = (ZRibbonButton)iterator.next();
                    selected = button.isSelected();
                    button.setToggle(true);
                    buttonGroup.add(button);
                    button.setGrouped(true);
                }

            }

        }
    }

    public void removeBand(ZRibbonBand band)
    {
        bandAL.remove(band);
        band.removeMouseListener(this);
    }

    public void removeBand(int index)
    {
        ((ZRibbonBand)bandAL.remove(index)).removeMouseListener(this);
    }

    public int getBandCount()
    {
        return bandAL.size();
    }

    public ZRibbonBand getBand(int index)
    {
        return (ZRibbonBand)bandAL.get(index);
    }

    private void updateMouseUnder(ZRibbonBand band)
    {
        for(int i = getBandCount() - 1; i >= 0; i--)
            if(getBand(i) != band && getBand(i).getUI().setUnderMouse(false))
                getBand(i).repaint();

    }

    public void mouseEntered(MouseEvent event)
    {
        if(event.getSource() instanceof ZRibbonBand)
            updateMouseUnder((ZRibbonBand)event.getSource());
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    private ArrayList bandAL;
    private ButtonGroup buttonGroup;
    private boolean grouped;
}