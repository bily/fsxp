// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPRibbonUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZRibbonUI, FSXPRibbonBandUI

public class FSXPRibbonUI extends ZRibbonUI
{
    private class RibbonLayout
        implements LayoutManager
    {

        public void addLayoutComponent(String s, Component component)
        {
        }

        public void removeLayoutComponent(Component component)
        {
        }

        public Dimension preferredLayoutSize(Container c)
        {
            return new Dimension(c.getWidth(), 108);
        }

        public Dimension minimumLayoutSize(Container c)
        {
            int width = 0;
            for(Iterator i$ = ribbon.getBandAL().iterator(); i$.hasNext();)
            {
                ZRibbonBand ribbonBand = (ZRibbonBand)i$.next();
                FSXPRibbonBandUI bandUI = (FSXPRibbonBandUI)ribbonBand.getUI();
                width += bandUI.getPreferredWidth(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.ICON);
                width += 4;
            }

            width -= 4;
            return new Dimension(width, 108);
        }

        public void layoutContainer(Container c)
        {
            int x = 2;
            for(Iterator i$ = ribbon.getRegularComponentAL().iterator(); i$.hasNext();)
            {
                Component regComp = (Component)i$.next();
                int pw = regComp.getPreferredSize().width;
                regComp.setBounds(x, 0, pw, 22);
                x += pw + 2;
            }

            int totalTaskButtonsWidth = 0;
            for(Iterator i$ = ribbon.getTaskToggleAL().iterator(); i$.hasNext();)
            {
                JToggleButton taskToggleButton = (JToggleButton)i$.next();
                int pw = taskToggleButton.getPreferredSize().width;
                totalTaskButtonsWidth += pw + 10;
            }

            switch(ribbon.getAlignment())
            {
            case 4: // '\004'
                x = Math.max(x, c.getWidth() - totalTaskButtonsWidth);
                break;

            case 0: // '\0'
                x += Math.max(x, (c.getWidth() - totalTaskButtonsWidth) / 2);
                break;
            }
            for(Iterator i$ = ribbon.getTaskToggleAL().iterator(); i$.hasNext();)
            {
                JToggleButton taskToggleButton = (JToggleButton)i$.next();
                int pw = taskToggleButton.getPreferredSize().width;
                taskToggleButton.setBounds(x, 0, pw, 22);
                x += pw + 10;
            }

            HashMap widths = new HashMap();
            com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind arr$[] = com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.values();
            int len$ = arr$.length;
            for(int i = 0; i < len$; i++)
            {
                com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind collapseKind = arr$[i];
                int totalWidth = 0;
                for(Iterator i$ = ribbon.getBandAL().iterator(); i$.hasNext();)
                {
                    ZRibbonBand panel = (ZRibbonBand)i$.next();
                    FSXPRibbonBandUI ui = (FSXPRibbonBandUI)panel.getUI();
                    totalWidth += ui.getPreferredWidth(collapseKind);
                    totalWidth += 2;
                }
                totalWidth -= 2;
                widths.put(collapseKind, Integer.valueOf(totalWidth));
            }

            com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind bestFitCollapse = (com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind)com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().getLast();
            Iterator i$ = com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind collapseKind = (com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind)i$.next();
                if(((Integer)widths.get(collapseKind)).intValue() >= c.getWidth())
                    continue;
                bestFitCollapse = collapseKind;
                break;
            } while(true);
            int bestFitWidth = ((Integer)widths.get(bestFitCollapse)).intValue();
            double coef = (double)c.getWidth() / (double)bestFitWidth;
            x = 0;
            for(i$ = ribbon.getBandAL().iterator(); i$.hasNext();)
            {
                ZRibbonBand panel = (ZRibbonBand)i$.next();
                FSXPRibbonBandUI ui = (FSXPRibbonBandUI)panel.getUI();
                int preferredWidth = Math.max(30, ui.getPreferredWidth(bestFitCollapse));
                int fullWidth = (int)(coef * (double)preferredWidth);
                if(panel.getAllButtonsTopState())
                    fullWidth = preferredWidth + 10;
                if(x + fullWidth > c.getWidth())
                    fullWidth = c.getWidth() - x;
                panel.setBounds(x, 22, fullWidth, 86);
                panel.doLayout();
                FSXPRibbonBandUI ribbonBandUI = (FSXPRibbonBandUI)panel.getUI();
                if(ribbonBandUI.getExpandButton() != null)
                {
                    int ebpw = ribbonBandUI.getExpandButton().getPreferredSize().width;
                    int ebph = ribbonBandUI.getExpandButton().getPreferredSize().height;
                    ribbonBandUI.getExpandButton().setBounds(fullWidth - 4 - ebpw, (18 - ebph) / 2, ebpw, ebph);
                }
                x += fullWidth + 2;
            }

        }

        public static final int TOTAL_HEIGHT = 108;
        final FSXPRibbonUI this$0;

        private RibbonLayout()
        {
            this$0 = FSXPRibbonUI.this;
        }

    }


    public FSXPRibbonUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPRibbonUI();
    }

    public void installUI(JComponent c)
    {
        ribbon = (ZRibbon)c;
        installDefaults();
        installComponents();
        installListeners();
    }

    public void uninstallUI(JComponent c)
    {
        uninstallListeners();
        uninstallComponents();
        uninstallDefaults();
        ribbon = null;
    }

    protected void installListeners()
    {
        currHierarchyTopLevelAncestor = null;
        currHierarchyTopLevelAncestorMouseListener = null;
        hierarchyListener = new HierarchyListener() {

            public void hierarchyChanged(HierarchyEvent event)
            {
                if(currHierarchyTopLevelAncestor != null)
                    currHierarchyTopLevelAncestor.removeMouseListener(currHierarchyTopLevelAncestorMouseListener);
                JComponent orig = (JComponent)event.getComponent();
                Container parent = orig.getTopLevelAncestor();
                currHierarchyTopLevelAncestor = parent;
                if(parent == null)
                {
                    return;
                } else
                {
                    currHierarchyTopLevelAncestorMouseListener = new MouseAdapter() {

                        public void mousePressed(MouseEvent e)
                        {
                            ZPopupGallery.hidePopups(null);
                        }
                    };
                    parent.addMouseListener(currHierarchyTopLevelAncestorMouseListener);
                    return;
                }
            }

            final FSXPRibbonUI this$0;
            {
                this$0 = FSXPRibbonUI.this;
            }
        }
;
        ribbon.addHierarchyListener(hierarchyListener);
        componentListener = new ComponentAdapter() {

            public void componentResized(ComponentEvent event)
            {
                ZPopupGallery.hidePopups(null);
            }

            public void componentHidden(ComponentEvent event)
            {
                ZPopupGallery.hidePopups(null);
            }

            public void componentMoved(ComponentEvent event)
            {
                ZPopupGallery.hidePopups(null);
            }

            final FSXPRibbonUI this$0;
            {
                this$0 = FSXPRibbonUI.this;
            }
        }
;
        ribbon.addComponentListener(componentListener);
        mouseWheelListener = new MouseWheelListener() {

            public void mouseWheelMoved(MouseWheelEvent event)
            {
                int delta = event.getWheelRotation();
                if(delta == 0)
                    return;
                int currSelectedTaskIndex = ribbon.getSelectedTaskIndex();
                int taskCount = ribbon.getTaskCount();
                int newSelectedTaskIndex = currSelectedTaskIndex + (delta <= 0 ? -1 : 1);
                if(newSelectedTaskIndex < 0)
                    newSelectedTaskIndex = taskCount - 1;
                if(newSelectedTaskIndex >= taskCount)
                    newSelectedTaskIndex = 0;
                final int indexToSet = newSelectedTaskIndex;
                SwingUtilities.invokeLater(new Runnable() {

                    public void run()
                    {
                        ribbon.setCursor(Cursor.getPredefinedCursor(3));
                        ribbon.setSelectedTask(indexToSet);
                        ribbon.setCursor(Cursor.getPredefinedCursor(0));
                    }
                });
            }

            final FSXPRibbonUI this$0;
            {
                this$0 = FSXPRibbonUI.this;
            }
        }
;
        ribbon.addMouseWheelListener(mouseWheelListener);
    }

    protected void uninstallListeners()
    {
        if(currHierarchyTopLevelAncestor != null)
            currHierarchyTopLevelAncestor.removeMouseListener(currHierarchyTopLevelAncestorMouseListener);
        ribbon.removeMouseWheelListener(mouseWheelListener);
        mouseWheelListener = null;
        ribbon.removeComponentListener(componentListener);
        componentListener = null;
        ribbon.removeHierarchyListener(hierarchyListener);
        hierarchyListener = null;
    }

    protected void installDefaults()
    {
    }

    protected void uninstallDefaults()
    {
    }

    protected void installComponents()
    {
        ribbon.setLayout(createLayoutManager());
    }

    protected void uninstallComponents()
    {
        ribbon.setLayout(null);
    }

    protected LayoutManager createLayoutManager()
    {
        return new RibbonLayout();
    }

    public void paint(Graphics g, JComponent c)
    {
        int y = (int)((double)(c.getHeight() - 22) * 0.29999999999999999D);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(new Color(191, 219, 255));
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, c.getWidth(), 22F));
        g2.setPaint(new Color(215, 227, 242));
        g2.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 23F, c.getWidth(), c.getHeight() - 22));
        GradientPaint gp = new GradientPaint(0.0F, 22 + y, new Color(200, 216, 237), 0.0F, (c.getHeight() - y) + 22, new Color(215, 227, 242));
        g2.setPaint(gp);
        g2.fillRect(0, 22 + y, c.getWidth(), (c.getHeight() - y) + 22);
        g2.setPaint(new Color(141, 178, 227));
        g2.draw(new java.awt.geom.Line2D.Float(0.0F, 21F, c.getWidth(), 21F));
        g2.dispose();
    }

    protected ZRibbon ribbon;
    protected MouseWheelListener mouseWheelListener;
    protected ComponentListener componentListener;
    protected HierarchyListener hierarchyListener;
    protected Component currHierarchyTopLevelAncestor;
    protected MouseListener currHierarchyTopLevelAncestorMouseListener;
    public static final int TASKBAR_HEIGHT = 22;
    public static final int BAND_MIN_WIDTH = 30;
}