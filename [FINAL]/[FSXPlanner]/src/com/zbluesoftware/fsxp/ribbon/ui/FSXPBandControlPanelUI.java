// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPBandControlPanelUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.GalleryElementState;
import com.zbluesoftware.fsxp.ribbon.ZBandControlPanel;
import com.zbluesoftware.fsxp.ribbon.ZRibbonBand;
import com.zbluesoftware.fsxp.ribbon.ZRibbonButton;
import com.zbluesoftware.fsxp.ribbon.ZRibbonGallery;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZBandControlPanelUI
/*
class FSXPBandControlPanelUI$1
            {
				static final int $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[];
				static
				{
					$SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind = new int[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.values().length];
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.NONE.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.LOW_TO_MID.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.MID_TO_MID.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.LOW_TO_LOW.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.MID_TO_LOW.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.HIGH_TO_MID.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.HIGH_TO_LOW.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError ex) { }
				}
		}
*/
public class FSXPBandControlPanelUI extends ZBandControlPanelUI
{
    protected ZBandControlPanel controlPanel;
    protected ZRibbonBand.RibbonBandCollapseKind currentFitCollapse;
    private HashMap sizeCacheHM = new HashMap();
	private boolean underMouse;
    public static final int STRUT_X = 4;
/*
	public FSXPBandControlPanelUI() {
		sizeCacheHM=new HashMap();
	}
*/	

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPBandControlPanelUI();
    }
	
	@Override
    public void installUI(JComponent c)
    {
        this.controlPanel = (ZBandControlPanel) c;
        installDefaults();
        installComponents();
        installListeners();
		
        c.setLayout( createLayoutManager() );
    }

	@Override
    public void uninstallUI(JComponent c)
    {
        uninstallListeners();
        uninstallComponents();
        uninstallDefaults();
        c.setLayout( null );
        this.controlPanel = null;
    }

    protected void installListeners()
    {
    }

    protected void uninstallListeners()
    {
    }

    protected void installComponents()
    {
    }

    protected void uninstallComponents()
    {
    }

    protected void installDefaults()
    {
        LookAndFeel.installColorsAndFont(this.controlPanel, "ZControlPanel.background", "ZControlPanel.foreground", "ZControlPanel.font");
        LookAndFeel.installBorder(this.controlPanel, "ZControlPanel.border");
    }

    protected void uninstallDefaults()
    {
        LookAndFeel.uninstallBorder(this.controlPanel);
    }

    protected LayoutManager createLayoutManager()
    {
        return new ControlPanelLayout();
    }

private class ControlPanelLayout implements LayoutManager {

        public void addLayoutComponent(String name, Component c)
        {
        }

        public void removeLayoutComponent(Component c)
        {
        }

        public Dimension preferredLayoutSize(Container c)
        {
            return c.getSize();
        }

        public Dimension minimumLayoutSize(Container c)
        {
            return this.preferredLayoutSize(c);
        }
		
        public void layoutContainer(Container c)
        {
            int x = 4;
            ZRibbonBand.RibbonBandCollapseKind bestFitCollapse = ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().getLast();
			for (ZRibbonBand.RibbonBandCollapseKind collapseKind : ZRibbonBand.RibbonBandCollapseKind
					.getSortedKinds()) {
				int collapsedWidth = getPreferredWidth(collapseKind);
				if (collapsedWidth <= c.getWidth()) {
					bestFitCollapse = collapseKind;
					break;
				}
			}
/*
			Iterator i1 = com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().iterator();
//WHILE -- ONE
      while (true)
      {
        if (!i1.hasNext())
          break;
        ZRibbonBand.RibbonBandCollapseKind collapseKind = (ZRibbonBand.RibbonBandCollapseKind)i1.next();
        int collapsedWidth = getPreferredWidth(collapseKind);
        if (collapsedWidth <= c.getWidth())
        {
          bestFitCollapse = collapseKind;
          break;
        }
      }
            currentFitCollapse = bestFitCollapse;
            if(bestFitCollapse == com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonBandCollapseKind.ICON)
                return;
*/
			currentFitCollapse = bestFitCollapse;
			if (bestFitCollapse == ZRibbonBand.RibbonBandCollapseKind.ICON) {
				return;
			}
			
			java.util.List<ZRibbonGallery> topGalleries = controlPanel
					.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.TOP);
			java.util.List<ZRibbonGallery> mediumGalleries = controlPanel
					.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.MEDIUM);
			java.util.List<ZRibbonGallery> lowGalleries = controlPanel
					.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.LOW);
/*
            java.util.List topGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.TOP);
            java.util.List mediumGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.MEDIUM);
            java.util.List lowGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.LOW);
*/
//                ZRibbonGallery top;                ZRibbonGallery med;                ZRibbonGallery low;
//            switch(FSXPBandControlPanelUI$1.$SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[bestFitCollapse.ordinal()])
			switch (bestFitCollapse)
            {
            default:
                break;
/*
            case 1: // '\001'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.BIG))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.BIG))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.BIG))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 2: // '\002'
                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.BIG))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.BIG))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.MEDIUM))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 3: // '\003'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.BIG))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.MEDIUM))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.MEDIUM))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 4: // '\004'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.BIG))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.MEDIUM))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.SMALL))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 5: // '\005'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.BIG))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.SMALL))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.SMALL))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 6: // '\006'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.MEDIUM))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.SMALL))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.SMALL))
                    low = (ZRibbonGallery)i$.next();

                break;

            case 7: // '\007'

                for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); top.setState(GalleryElementState.SMALL))
                    top = (ZRibbonGallery)i$.next();


                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); med.setState(GalleryElementState.SMALL))
                    med = (ZRibbonGallery)i$.next();


                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); low.setState(GalleryElementState.SMALL))
                    low = (ZRibbonGallery)i$.next();

                break;
*/
			case NONE:
				// put all galleries to top
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.BIG);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.BIG);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.BIG);
				break;

			case LOW_TO_MID:
				// put all low priority galleries to medium list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.BIG);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.BIG);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.MEDIUM);
				break;

			case MID_TO_MID:
				// put all low / medium priority galleries to medium list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.BIG);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.MEDIUM);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.MEDIUM);
				break;

			case LOW_TO_LOW:
				// put all low priority galleries to low list
				// put all medium priority buttons to medium list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.BIG);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.MEDIUM);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.SMALL);
				break;

			case MID_TO_LOW:
				// put all medium / low priority galleries to low list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.BIG);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.SMALL);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.SMALL);
				break;

			case HIGH_TO_MID:
				// put all medium / low priority galleries to low list
				// put all top priority galleries to medium list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.MEDIUM);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.SMALL);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.SMALL);
				break;

			case HIGH_TO_LOW:
				// put all galleries to low list
				for (ZRibbonGallery top : topGalleries)
					top.setState(GalleryElementState.SMALL);
				for (ZRibbonGallery med : mediumGalleries)
					med.setState(GalleryElementState.SMALL);
				for (ZRibbonGallery low : lowGalleries)
					low.setState(GalleryElementState.SMALL);
				break;
            }
			Map<GalleryElementState, java.util.List<ZRibbonGallery>> galleryStateMap = new HashMap<GalleryElementState, java.util.List<ZRibbonGallery>>();
			galleryStateMap.put(GalleryElementState.BIG,
					new LinkedList<ZRibbonGallery>());
			galleryStateMap.put(GalleryElementState.MEDIUM,
					new LinkedList<ZRibbonGallery>());
			galleryStateMap.put(GalleryElementState.SMALL,
					new LinkedList<ZRibbonGallery>());

			for (ZRibbonGallery top : topGalleries)
				galleryStateMap.get(top.getState()).add(top);
			for (ZRibbonGallery med : mediumGalleries)
				galleryStateMap.get(med.getState()).add(med);
			for (ZRibbonGallery low : lowGalleries)
				galleryStateMap.get(low.getState()).add(low);
/*
            HashMap galleryStateMap = new HashMap();
            galleryStateMap.put(GalleryElementState.BIG, new LinkedList());
            galleryStateMap.put(GalleryElementState.MEDIUM, new LinkedList());
            galleryStateMap.put(GalleryElementState.SMALL, new LinkedList());

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); ((java.util.List)galleryStateMap.get(top.getState())).add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); ((java.util.List)galleryStateMap.get(med.getState())).add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); ((java.util.List)galleryStateMap.get(low.getState())).add(low))
                low = (ZRibbonGallery)i$.next();
*/
			// bigs
			for (ZRibbonGallery big : galleryStateMap.get(GalleryElementState.BIG)) {
				int pw = big.getPreferredWidth(GalleryElementState.BIG);
				big.setBounds(x, 0, pw, 68);
				//System.out.println("BIG " + big.getBounds());
				x += pw;
				x += 4;
			}

			// mediums
			for (ZRibbonGallery med : galleryStateMap.get(GalleryElementState.MEDIUM)) {
				int pw = med.getPreferredWidth(GalleryElementState.MEDIUM);
				med.setBounds(x, 0, pw, 68);
				//System.out.println("BIG " + big.getBounds());
				x += pw;
				x += 4;
			}

			// smalls
			for (ZRibbonGallery small : galleryStateMap.get(GalleryElementState.SMALL)) {
				int pw = small.getPreferredWidth(GalleryElementState.SMALL);
				small.setBounds(x, 0, pw, 68);
				//System.out.println("SMALL " + small.getBounds());
				x += pw;
				x += 4;
			}

			// Regular panels
			for (JPanel panel : controlPanel.getPanels()) {
				int pw = panel.getPreferredSize().width;
				panel.setBounds(x, 0, pw, 68);
				x += pw;
				x += 4;
			}
/*
            for(Iterator i$ = ((java.util.List)galleryStateMap.get(GalleryElementState.BIG)).iterator(); i$.hasNext();)
            {
                ZRibbonGallery big = (ZRibbonGallery)i$.next();
                int pw = big.getPreferredWidth(GalleryElementState.BIG);
                big.setBounds(x, 0, pw, 68);
                x += pw;
                x += 4;
            }

            for(Iterator i$ = ((java.util.List)galleryStateMap.get(GalleryElementState.MEDIUM)).iterator(); i$.hasNext();)
            {
                med = (ZRibbonGallery)i$.next();
                int pw = med.getPreferredWidth(GalleryElementState.MEDIUM);
                med.setBounds(x, 0, pw, 68);
                x += pw;
                x += 4;
            }

            for(Iterator i$ = ((java.util.List)galleryStateMap.get(GalleryElementState.SMALL)).iterator(); i$.hasNext();)
            {
                ZRibbonGallery small = (ZRibbonGallery)i$.next();
                int pw = small.getPreferredWidth(GalleryElementState.SMALL);
                small.setBounds(x, 0, pw, 68);
                x += pw;
                x += 4;
            }

            for(Iterator i$ = controlPanel.getPanels().iterator(); i$.hasNext();)
            {
                JPanel panel = (JPanel)i$.next();
                int pw = panel.getPreferredSize().width;
                panel.setBounds(x, 0, pw, 68);
                x += pw;
                x += 4;
            }
*/
			java.util.List<ZRibbonButton> topButtons = controlPanel
					.getRibbonButtons(ZRibbonBand.RibbonElementPriority.TOP);
			java.util.List<ZRibbonButton> mediumButtons = controlPanel
					.getRibbonButtons(ZRibbonBand.RibbonElementPriority.MEDIUM);
			java.util.List<ZRibbonButton> lowButtons = controlPanel
					.getRibbonButtons(ZRibbonBand.RibbonElementPriority.LOW);

			switch (bestFitCollapse) {
			case NONE:
				// put all buttons to top
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.BIG, true);
				break;

			case LOW_TO_MID:
				// put all low priority buttons to medium list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.MEDIUM, true);
				break;

			case MID_TO_MID:
				// put all low / medium priority buttons to medium list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.MEDIUM, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.MEDIUM, true);
				break;

			case LOW_TO_LOW:
				// put all low priority buttons to low list
				// put all medium priority buttons to medium list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.MEDIUM, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.SMALL, true);
				break;

			case MID_TO_LOW:
				// put all medium / low priority buttons to low list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.BIG, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.SMALL, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.SMALL, true);
				break;

			case HIGH_TO_MID:
				// put all medium / low priority buttons to low list
				// put all top priority buttons to medium list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.MEDIUM, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.SMALL, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.SMALL, true);
				break;

			case HIGH_TO_LOW:
				// put all buttons to low list
				for (ZRibbonButton top : topButtons)
					top.setState(GalleryElementState.SMALL, true);
				for (ZRibbonButton med : mediumButtons)
					med.setState(GalleryElementState.SMALL, true);
				for (ZRibbonButton low : lowButtons)
					low.setState(GalleryElementState.SMALL, true);
				break;
			}

			Map<GalleryElementState, java.util.List<ZRibbonButton>> buttonStateMap = new HashMap<GalleryElementState, java.util.List<ZRibbonButton>>();
			buttonStateMap.put(GalleryElementState.BIG,
					new LinkedList<ZRibbonButton>());
			buttonStateMap.put(GalleryElementState.MEDIUM,
					new LinkedList<ZRibbonButton>());
			buttonStateMap.put(GalleryElementState.SMALL,
					new LinkedList<ZRibbonButton>());

			for (ZRibbonButton top : topButtons)
				buttonStateMap.get(top.getState()).add(top);
			for (ZRibbonButton med : mediumButtons)
				buttonStateMap.get(med.getState()).add(med);
			for (ZRibbonButton low : lowButtons)
				buttonStateMap.get(low.getState()).add(low);

			// start from BIGs
			for (ZRibbonButton big : buttonStateMap.get(GalleryElementState.BIG)) {
				big.setBounds(x, 0, big.getPreferredSize().width,68);
				x += big.getPreferredSize().width;
				x += 4;
			}

			int medSize = buttonStateMap.get(GalleryElementState.MEDIUM).size();
			if (medSize > 0) {
				// try to move buttons from small to medium to make
				// three-somes.
				while (((buttonStateMap.get(GalleryElementState.MEDIUM).size() % 3) != 0)
						&& (buttonStateMap.get(GalleryElementState.SMALL).size() > 0)) {
					ZRibbonButton low = buttonStateMap.get(GalleryElementState.SMALL)
							.get(0);
					buttonStateMap.get(GalleryElementState.SMALL).remove(low);
					low.setState(GalleryElementState.MEDIUM, true);
					buttonStateMap.get(GalleryElementState.MEDIUM).add(low);
				}
			}

			// at this point, mediumButtons list contains either
			// threesomes, or there are no buttons in lowButtons.
			int index3 = 0;
			int maxWidth3 = 0;
			Set<ZRibbonButton> threesome = new HashSet<ZRibbonButton>();
			for (ZRibbonButton medium : buttonStateMap
					.get(GalleryElementState.MEDIUM)) {
				int medWidth = medium.getPreferredSize().width;
				maxWidth3 = Math.max(maxWidth3, medWidth);
                medium.setBounds(x, (index3 * 68) / 3, medWidth, 22);
				threesome.add(medium);

				index3++;
				if (index3 == 3) {
					// last button in threesome
					for (ZRibbonButton button : threesome) {
						Rectangle bounds = button.getBounds();
						button.setBounds(bounds.x, bounds.y, maxWidth3,
								bounds.height);
					}

					index3 = 0;
					x += maxWidth3;
					x += 4;
					maxWidth3 = 0;
					threesome.clear();
				}
			}
			// at this point, maxWidth3 may be non-null. We can safely
			// add it, since in this case there will be no buttons
			// left in lowButtons
			x += maxWidth3;
			if (maxWidth3 > 0)
				x += 4;

			index3 = 0;
			maxWidth3 = 0;
			for (ZRibbonButton small : buttonStateMap.get(GalleryElementState.SMALL)) {
				int lowWidth = small.getPreferredSize().width;
				maxWidth3 = Math.max(maxWidth3, lowWidth);
                small.setBounds(x, (index3 * 68) / 3, lowWidth, 22);
				threesome.add(small);

				index3++;
				if (index3 == 3) {
					// last button in threesome
					for (ZRibbonButton button : threesome) {
						Rectangle bounds = button.getBounds();
						button.setBounds(bounds.x, bounds.y, maxWidth3,
								bounds.height);
					}

					index3 = 0;
					x += maxWidth3;
					x += 4;
					maxWidth3 = 0;
					threesome.clear();
				}
			}
			x += maxWidth3;
			if (maxWidth3 > 0)
				x += 4;

			// At this point there may be some pixels left to the right.
			// We need to shift everything to the right for
			// center-alignment.
			int shiftX = (c.getWidth() - x) / 2;
			if (controlPanel.hasRibbonGalleries()) {
				int galleryCount = controlPanel.getRibbonGalleriesCount();
				int delta = 0;
				if (shiftX < 0) {
					// need to remove some pixels from galleries
					delta = -2
							* (int) Math.ceil((double) (-shiftX)
									/ (double) galleryCount);
				} else {
					// need to add pixels to galleries
					delta = 2 * (int) Math.ceil((double) (shiftX)
							/ (double) galleryCount);
				}

				int totalDelta = 0;
				for (ZRibbonGallery top : topGalleries) {
					Rectangle bounds = top.getBounds();
					top.setBounds(bounds.x + totalDelta, bounds.y, bounds.width
							+ delta, bounds.height);
					totalDelta += delta;
				}
				for (ZRibbonGallery med : mediumGalleries) {
					Rectangle bounds = med.getBounds();
					med.setBounds(bounds.x + totalDelta, bounds.y, bounds.width
							+ delta, bounds.height);
					totalDelta += delta;
				}
				for (ZRibbonGallery low : lowGalleries) {
					Rectangle bounds = low.getBounds();
					low.setBounds(bounds.x + totalDelta, bounds.y, bounds.width
							+ delta, bounds.height);
					totalDelta += delta;
				}

				for (JPanel panel : controlPanel.getPanels()) {
					Rectangle bounds = panel.getBounds();
					panel.setBounds(bounds.x + 2 * shiftX, bounds.y,
							bounds.width, bounds.height);
				}

				for (ZRibbonButton top : topButtons) {
					Rectangle bounds = top.getBounds();
					top.setBounds(bounds.x + 2 * shiftX, bounds.y,
							bounds.width, bounds.height);
				}
				for (ZRibbonButton med : mediumButtons) {
					Rectangle bounds = med.getBounds();
					med.setBounds(bounds.x + 2 * shiftX, bounds.y,
							bounds.width, bounds.height);
				}
				for (ZRibbonButton low : lowButtons) {
					Rectangle bounds = low.getBounds();
					low.setBounds(bounds.x + 2 * shiftX, bounds.y,
							bounds.width, bounds.height);
				}
			} else {
				if (shiftX > 0) {
					// see if have galleries
					for (JPanel panel : controlPanel.getPanels()) {
						Rectangle bounds = panel.getBounds();
						panel.setBounds(bounds.x + shiftX, bounds.y,
								bounds.width, bounds.height);
					}

					for (ZRibbonButton top : topButtons) {
						Rectangle bounds = top.getBounds();
						top.setBounds(bounds.x + shiftX, bounds.y,
								bounds.width, bounds.height);
					}
					for (ZRibbonButton med : mediumButtons) {
						Rectangle bounds = med.getBounds();
						med.setBounds(bounds.x + shiftX, bounds.y,
								bounds.width, bounds.height);
					}
					for (ZRibbonButton low : lowButtons) {
						Rectangle bounds = low.getBounds();
						low.setBounds(bounds.x + shiftX, bounds.y,
								bounds.width, bounds.height);
					}
				}
			}
		}

/*
		ZRibbonGallery top;                ZRibbonGallery med;                ZRibbonGallery low;
            java.util.List topButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.TOP);
            java.util.List mediumButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.MEDIUM);
            java.util.List lowButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.LOW);
		ZRibbonButton topB; ZRibbonButton medB; ZRibbonButton lowB;
            switch(FSXPBandControlPanelUI$1.$SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[bestFitCollapse.ordinal()])
            {
            default:
                break;

            case 1: // '\001'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.BIG, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.BIG, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 2: // '\002'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.BIG, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.MEDIUM, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 3: // '\003'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.MEDIUM, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.MEDIUM, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 4: // '\004'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.MEDIUM, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.SMALL, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 5: // '\005'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.SMALL, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.SMALL, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 6: // '\006'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.MEDIUM, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.SMALL, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.SMALL, true))
                    lowB = (ZRibbonButton)i$.next();

                break;

            case 7: // '\007'

                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.SMALL, true))
                    topB = (ZRibbonButton)i$.next();


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.SMALL, true))
                    medB = (ZRibbonButton)i$.next();


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.SMALL, true))
                    lowB = (ZRibbonButton)i$.next();

                break;
            }
            HashMap buttonStateMap = new HashMap();
            buttonStateMap.put(GalleryElementState.BIG, new LinkedList());
            buttonStateMap.put(GalleryElementState.MEDIUM, new LinkedList());
            buttonStateMap.put(GalleryElementState.SMALL, new LinkedList());

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(topB.getState())).add(topB))
                topB = (ZRibbonButton)i$.next();

				
            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(medB.getState())).add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(lowB.getState())).add(lowB))
                lowB = (ZRibbonButton)i$.next();

            for(Iterator i$ = ((java.util.List)buttonStateMap.get(GalleryElementState.BIG)).iterator(); i$.hasNext();)
            {
                ZRibbonButton big = (ZRibbonButton)i$.next();
                big.setBounds(x, 0, big.getPreferredSize().width, 68);
                x += big.getPreferredSize().width;
                x += 4;
            }

            int medSize = ((java.util.List)buttonStateMap.get(GalleryElementState.MEDIUM)).size();
            if(medSize > 0)
            {
                for(; ((java.util.List)buttonStateMap.get(GalleryElementState.MEDIUM)).size() % 3 != 0 && ((java.util.List)buttonStateMap.get(GalleryElementState.SMALL)).size() > 0; ((java.util.List)buttonStateMap.get(GalleryElementState.MEDIUM)).add(lowB))
                {
                    lowB = (ZRibbonButton)((java.util.List)buttonStateMap.get(GalleryElementState.SMALL)).get(0);
                    ((java.util.List)buttonStateMap.get(GalleryElementState.SMALL)).remove(lowB);
                    lowB.setState(GalleryElementState.MEDIUM, true);
                }

            }
            int index3 = 0;
            int maxWidth3 = 0;
            HashSet threesome = new HashSet();
//WHILE -- TWO
        Iterator i1$ = ((java.util.List)buttonStateMap.get(GalleryElementState.MEDIUM)).iterator();
        while( i1$.hasNext() )
        {
                ZRibbonButton medium = (ZRibbonButton)i1$.next();
                int medWidth = medium.getPreferredSize().width;
                maxWidth3 = Math.max(maxWidth3, medWidth);
                medium.setBounds(x, (index3 * 68) / 3, medWidth, 22);
                threesome.add(medium);
            if( ++index3 != 3 )
                continue;
            /*Iterator i2$ = threesome.iterator();
            while( i2$.hasNext() )
            {
                        ZRibbonButton button = (ZRibbonButton)i2$.next();
                        Rectangle bounds = button.getBounds();
						button.setBounds(bounds.x, bounds.y, maxWidth3, bounds.height);
            }
                for(Iterator i$ = threesome.iterator(); i$.hasNext();)
                {
                    ZRibbonButton button = (ZRibbonButton)i$.next();
                    Rectangle bounds = button.getBounds();
					button.setBounds(bounds.x, bounds.y, maxWidth3, bounds.height);
                }
                    index3 = 0;
                    x += maxWidth3;
                    x += 4;
                    maxWidth3 = 0;
                    threesome.clear();
        }

            x += maxWidth3;
            if(maxWidth3 > 0)
                x += 4;
            index3 = 0;
            maxWidth3 = 0;
//WHILE -- THREE
        i1$ = ((java.util.List)buttonStateMap.get(GalleryElementState.SMALL)).iterator();
        while( i1$.hasNext() )
        {
                ZRibbonButton small = (ZRibbonButton)i1$.next();
                int lowWidth = small.getPreferredSize().width;
                maxWidth3 = Math.max(maxWidth3, lowWidth);
                small.setBounds(x, (index3 * 68) / 3, lowWidth, 22);
                threesome.add(small);
            if( ++index3 != 3 )
                continue;
            /*Iterator i2$ = threesome.iterator();
            while( i2$.hasNext() )
            {
                        ZRibbonButton button = (ZRibbonButton)i2$.next();
                        Rectangle bounds = button.getBounds();
						button.setBounds(bounds.x, bounds.y, maxWidth3, bounds.height);
            }
                for(Iterator i$ = threesome.iterator(); i$.hasNext();)
                {
                    ZRibbonButton button = (ZRibbonButton)i$.next();
                    Rectangle bounds = button.getBounds();
					button.setBounds(bounds.x, bounds.y, maxWidth3, bounds.height);
                }
                    index3 = 0;
                    x += maxWidth3;
                    x += 4;
                    maxWidth3 = 0;
                    threesome.clear();
        }

            x += maxWidth3;
            if(maxWidth3 > 0)
                x += 4;

            int shiftX = (c.getWidth() - x) / 2;
            if(controlPanel.hasGalleries())
            {
                int galleryCount = controlPanel.getGalleryCount();
                int delta = 0;
                if(shiftX < 0)
                    delta = -2 * (int)Math.ceil((double)(-shiftX) / (double)galleryCount);
                else
                    delta = 2 * (int)Math.ceil((double)shiftX / (double)galleryCount);
                int totalDelta = 0;
                for(Iterator i$ = topGalleries.iterator(); i$.hasNext();)
                {
                    top = (ZRibbonGallery)i$.next();
                    Rectangle bounds = top.getBounds();
                    top.setBounds(bounds.x + totalDelta, bounds.y, bounds.width + delta, bounds.height);
                    totalDelta += delta;
                }

                for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext();)
                {
                    med = (ZRibbonGallery)i$.next();
                    Rectangle bounds = med.getBounds();
                    med.setBounds(bounds.x + totalDelta, bounds.y, bounds.width + delta, bounds.height);
                    totalDelta += delta;
                }

                for(Iterator i$ = lowGalleries.iterator(); i$.hasNext();)
                {
                    low = (ZRibbonGallery)i$.next();
                    Rectangle bounds = low.getBounds();
                    low.setBounds(bounds.x + totalDelta, bounds.y, bounds.width + delta, bounds.height);
                    totalDelta += delta;
                }

                JPanel panel;
                Rectangle bounds;
                for(Iterator i$ = controlPanel.getPanels().iterator(); i$.hasNext(); panel.setBounds(bounds.x + 2 * shiftX, bounds.y, bounds.width, bounds.height))
                {
                    panel = (JPanel)i$.next();
                    bounds = panel.getBounds();
                }



                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setBounds(bounds.x + 2 * shiftX, bounds.y, bounds.width, bounds.height))
                {
                    topB = (ZRibbonButton)i$.next();
                    bounds = topB.getBounds();
                }



                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setBounds(bounds.x + 2 * shiftX, bounds.y, bounds.width, bounds.height))
                {
                    medB = (ZRibbonButton)i$.next();
                    bounds = medB.getBounds();
                }



                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setBounds(bounds.x + 2 * shiftX, bounds.y, bounds.width, bounds.height))
                {
                    lowB = (ZRibbonButton)i$.next();
                    bounds = lowB.getBounds();
                }

            } else
            if(shiftX > 0)
            {
                JPanel panel;
                Rectangle bounds;
                for(Iterator i$ = controlPanel.getPanels().iterator(); i$.hasNext(); panel.setBounds(bounds.x + shiftX, bounds.y, bounds.width, bounds.height))
                {
                    panel = (JPanel)i$.next();
                    bounds = panel.getBounds();
                }


                for(Iterator i$ = topButtons.iterator(); i$.hasNext(); topB.setBounds(bounds.x + shiftX, bounds.y, bounds.width, bounds.height))
                {
                    topB = (ZRibbonButton)i$.next();
                    bounds = topB.getBounds();
                }


                for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); medB.setBounds(bounds.x + shiftX, bounds.y, bounds.width, bounds.height))
                {
                    medB = (ZRibbonButton)i$.next();
                    bounds = medB.getBounds();
                }


                for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); lowB.setBounds(bounds.x + shiftX, bounds.y, bounds.width, bounds.height))
                {
                    lowB = (ZRibbonButton)i$.next();
                    bounds = lowB.getBounds();
                }

            }
        }
/*        final FSXPBandControlPanelUI this$0;
        private ControlPanelLayout()
        {
			super();
			this$0 = FSXPBandControlPanelUI.this;
        }
	*/
    }

    public void setUnderMouse(boolean underMouse)
    {
        this.underMouse = underMouse;
    }

    public boolean isUnderMouse()
    {
        return underMouse;
    }
	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
		this.paintBandBackground(graphics, new Rectangle(0, 0, c.getWidth(), c
				.getHeight()));
		graphics.dispose();
	}
/*
    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBandBackground(g2, new Rectangle(0, 0, c.getWidth(), c.getHeight()));
        g2.dispose();
    }
*/
    protected void paintBandBackground(Graphics2D g2, Rectangle toFill)
    {
        int y = (int)((double)toFill.height * 0.29999999999999999D);
        if(underMouse)
            g2.setPaint(new Color(230, 240, 252));
        else
            g2.setPaint(new Color(216, 228, 242));
        g2.fillRect(toFill.x + 2, toFill.y + 2, toFill.width - 4, (toFill.height + y) - 4);
        GradientPaint gp;
        if(underMouse)
            gp = new GradientPaint(toFill.x, toFill.y + y, new Color(219, 233, 251), toFill.x, toFill.height - y, new Color(220, 232, 248));
        else
            gp = new GradientPaint(toFill.x, toFill.y + y, new Color(200, 216, 237), toFill.x, toFill.height - y, new Color(216, 228, 242));
        g2.setPaint(gp);
        g2.fillRect(toFill.x, toFill.y + y, toFill.width, toFill.height - y);
        if(underMouse)
        {
            g2.setPaint(Color.white);
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 3, toFill.x + 1, toFill.y + toFill.height));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 3, toFill.y + 3, (toFill.x + toFill.width) - 3, toFill.y + toFill.height));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 3, toFill.y + 1, (toFill.x + toFill.width) - 5, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 3, toFill.x + 3, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 3, toFill.y + 3, (toFill.x + toFill.width) - 5, toFill.y + 1));
        } else
        {
            g2.setPaint(new Color(230, 238, 246));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 3, toFill.x + 1, toFill.y + toFill.height));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 1, toFill.y + 3, (toFill.x + toFill.width) - 1, toFill.y + toFill.height));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 3, toFill.y + 1, (toFill.x + toFill.width) - 3, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 3, toFill.x + 3, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 1, toFill.y + 3, (toFill.x + toFill.width) - 3, toFill.y + 1));
        }
        g2.setPaint(new Color(162, 190, 212));
        g2.draw(new java.awt.geom.Line2D.Float(toFill.x, toFill.y + 2, toFill.x, toFill.y + toFill.height));
        g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 2, toFill.y + 2, (toFill.x + toFill.width) - 2, toFill.y + toFill.height));
        g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 2, toFill.y, (toFill.x + toFill.width) - 4, toFill.y));
        g2.draw(new java.awt.geom.Line2D.Float(toFill.x, toFill.y + 2, toFill.x + 2, toFill.y));
        g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 2, toFill.y + 2, (toFill.x + toFill.width) - 4, toFill.y));
    }

    public int getPreferredWidth(ZRibbonBand.RibbonBandCollapseKind collapseKind)
    {
        if(sizeCacheHM.containsKey(collapseKind))
            return ((Integer) ( (sizeCacheHM).get(collapseKind) )).intValue();
        int panelWidth = 0;

		java.util.List<ZRibbonGallery> topGalleries = controlPanel
				.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.TOP);
		java.util.List<ZRibbonGallery> mediumGalleries = controlPanel
				.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.MEDIUM);
		java.util.List<ZRibbonGallery> lowGalleries = controlPanel
				.getRibbonGalleries(ZRibbonBand.RibbonElementPriority.LOW);

		java.util.List<ZRibbonGallery> tempTopGalleries = new LinkedList<ZRibbonGallery>();
		java.util.List<ZRibbonGallery> tempMediumGalleries = new LinkedList<ZRibbonGallery>();
		java.util.List<ZRibbonGallery> tempLowGalleries = new LinkedList<ZRibbonGallery>();

		switch (collapseKind) {
		case NONE:
			// put all galleries to top
			for (ZRibbonGallery top : topGalleries)
				tempTopGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempTopGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempTopGalleries.add(low);
			break;

		case LOW_TO_MID:
			// put all low priority galleries to medium list
			for (ZRibbonGallery top : topGalleries)
				tempTopGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempTopGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempMediumGalleries.add(low);
			break;

		case MID_TO_MID:
			// put all low / medium priority galleries to medium list
			for (ZRibbonGallery top : topGalleries)
				tempTopGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempMediumGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempMediumGalleries.add(low);
			break;

		case LOW_TO_LOW:
			// put all low priority galleries to low list
			// put all medium priority galleries to medium list
			for (ZRibbonGallery top : topGalleries)
				tempTopGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempMediumGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempLowGalleries.add(low);
			break;

		case MID_TO_LOW:
			// put all medium / low priority galleries to low list
			for (ZRibbonGallery top : topGalleries)
				tempTopGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempLowGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempLowGalleries.add(low);
			break;

		case HIGH_TO_MID:
			// put all medium / low priority galleries to low list
			// put all top priority galleries to medium list
			for (ZRibbonGallery top : topGalleries)
				tempMediumGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempLowGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempLowGalleries.add(low);
			break;

		case HIGH_TO_LOW:
			// put all galleries to low list
			for (ZRibbonGallery top : topGalleries)
				tempLowGalleries.add(top);
			for (ZRibbonGallery med : mediumGalleries)
				tempLowGalleries.add(med);
			for (ZRibbonGallery low : lowGalleries)
				tempLowGalleries.add(low);
			break;
		}

		for (ZRibbonGallery top : tempTopGalleries)
			panelWidth += (top.getPreferredWidth(GalleryElementState.BIG) + 4);
		for (ZRibbonGallery med : tempMediumGalleries)
			panelWidth += (med.getPreferredWidth(GalleryElementState.MEDIUM) + 4);
		for (ZRibbonGallery low : tempLowGalleries)
			panelWidth += (low.getPreferredWidth(GalleryElementState.SMALL) + 4);

		// panels
		for (JPanel panel : controlPanel.getPanels())
			panelWidth += panel.getPreferredSize().width;

		// ribbon buttons
		java.util.List<ZRibbonButton> topButtons = controlPanel
				.getRibbonButtons(ZRibbonBand.RibbonElementPriority.TOP);
		java.util.List<ZRibbonButton> mediumButtons = controlPanel
				.getRibbonButtons(ZRibbonBand.RibbonElementPriority.MEDIUM);
		java.util.List<ZRibbonButton> lowButtons = controlPanel
				.getRibbonButtons(ZRibbonBand.RibbonElementPriority.LOW);

		java.util.List<ZRibbonButton> tempTopButtons = new LinkedList<ZRibbonButton>();
		java.util.List<ZRibbonButton> tempMediumButtons = new LinkedList<ZRibbonButton>();
		java.util.List<ZRibbonButton> tempLowButtons = new LinkedList<ZRibbonButton>();

		Map<GalleryElementState, java.util.List<ZRibbonButton>> buttonStateMap = new HashMap<GalleryElementState, java.util.List<ZRibbonButton>>();
		buttonStateMap.put(GalleryElementState.BIG, new LinkedList<ZRibbonButton>());
		buttonStateMap.put(GalleryElementState.MEDIUM,
				new LinkedList<ZRibbonButton>());
		buttonStateMap
				.put(GalleryElementState.SMALL, new LinkedList<ZRibbonButton>());

		// store current button state
		for (ZRibbonButton top : topButtons)
			buttonStateMap.get(top.getState()).add(top);
		for (ZRibbonButton med : mediumButtons)
			buttonStateMap.get(med.getState()).add(med);
		for (ZRibbonButton low : lowButtons)
			buttonStateMap.get(low.getState()).add(low);

		switch (collapseKind) {
		case NONE:
			// put all buttons to top
			for (ZRibbonButton top : topButtons)
				tempTopButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempTopButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempTopButtons.add(low);
			break;

		case LOW_TO_MID:
			// put all low priority buttons to medium list
			for (ZRibbonButton top : topButtons)
				tempTopButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempTopButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempMediumButtons.add(low);
			break;

		case MID_TO_MID:
			// put all low / medium priority buttons to medium list
			for (ZRibbonButton top : topButtons)
				tempTopButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempMediumButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempMediumButtons.add(low);
			break;

		case LOW_TO_LOW:
			// put all low priority buttons to low list
			// put all medium priority buttons to medium list
			for (ZRibbonButton top : topButtons)
				tempTopButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempMediumButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempLowButtons.add(low);
			break;

		case MID_TO_LOW:
			// put all medium / low priority buttons to low list
			for (ZRibbonButton top : topButtons)
				tempTopButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempLowButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempLowButtons.add(low);
			break;

		case HIGH_TO_MID:
			// put all medium / low priority buttons to low list
			// put all top priority buttons to medium list
			for (ZRibbonButton top : topButtons)
				tempMediumButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempLowButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempLowButtons.add(low);
			break;

		case HIGH_TO_LOW:
			// put all buttons to low list
			for (ZRibbonButton top : topButtons)
				tempLowButtons.add(top);
			for (ZRibbonButton med : mediumButtons)
				tempLowButtons.add(med);
			for (ZRibbonButton low : lowButtons)
				tempLowButtons.add(low);
			break;
		}

		for (ZRibbonButton top : tempTopButtons)
			top.setState(GalleryElementState.BIG, false);
		for (ZRibbonButton med : tempMediumButtons)
			med.setState(GalleryElementState.MEDIUM, false);
		for (ZRibbonButton low : tempLowButtons)
			low.setState(GalleryElementState.SMALL, false);

		panelWidth += getWidth(tempTopButtons, tempMediumButtons,
				tempLowButtons);

		// restore buttons to their original state
		for (Map.Entry<GalleryElementState, java.util.List<ZRibbonButton>> stateEntry : buttonStateMap
				.entrySet()) {
			for (ZRibbonButton button : stateEntry.getValue())
				button.setState(stateEntry.getKey(), false);
		}

		// System.out.println("Collapse " + collapseKind.name() +
		// // " [" +
		// // tempTopButtons.size() + ", " + tempMediumButtons.size() + ", " +
		// // tempLowButtons.size() + "], " +
		// " width is "
		// + panelWidth);
        sizeCacheHM.put(collapseKind, new Integer(panelWidth));
		return panelWidth;
/*		
        java.util.List topGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.TOP);
        java.util.List mediumGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.MEDIUM);
        java.util.List lowGalleries = controlPanel.getRibbonGalleries(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.LOW);
        java.util.List tempTopGalleries = new LinkedList();
        java.util.List tempMediumGalleries = new LinkedList();
        java.util.List tempLowGalleries = new LinkedList();
		ZRibbonGallery top;            ZRibbonGallery med;            ZRibbonGallery low;
        switch(FSXPBandControlPanelUI$1.$SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[collapseKind.ordinal()])
        {
        default:
            break;

        case 1: // '\001'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 2: // '\002'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempMediumGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 3: // '\003'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempMediumGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempMediumGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 4: // '\004'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempMediumGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 5: // '\005'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempTopGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 6: // '\006'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempMediumGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;

        case 7: // '\007'

            for(Iterator i$ = topGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(top))
                top = (ZRibbonGallery)i$.next();


            for(Iterator i$ = mediumGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(med))
                med = (ZRibbonGallery)i$.next();


            for(Iterator i$ = lowGalleries.iterator(); i$.hasNext(); tempLowGalleries.add(low))
                low = (ZRibbonGallery)i$.next();

            break;
        }
        for(Iterator i$ = tempTopGalleries.iterator(); i$.hasNext();)
        {
            top = (ZRibbonGallery)i$.next();
            panelWidth += top.getPreferredWidth(GalleryElementState.BIG) + 4;
        }

        for(Iterator i$ = tempMediumGalleries.iterator(); i$.hasNext();)
        {
            med = (ZRibbonGallery)i$.next();
            panelWidth += med.getPreferredWidth(GalleryElementState.MEDIUM) + 4;
        }

        for(Iterator i$ = tempLowGalleries.iterator(); i$.hasNext();)
        {
            low = (ZRibbonGallery)i$.next();
            panelWidth += low.getPreferredWidth(GalleryElementState.SMALL) + 4;
        }

        for(Iterator i$ = controlPanel.getPanels().iterator(); i$.hasNext();)
        {
            JPanel panel = (JPanel)i$.next();
            panelWidth += panel.getPreferredSize().width;
        }

        java.util.List topButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.TOP);
        java.util.List mediumButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.MEDIUM);
        java.util.List lowButtons = controlPanel.getRibbonButtons(com.zbluesoftware.fsxp.ribbon.ZRibbonBand.RibbonElementPriority.LOW);
        java.util.List tempTopButtons = new LinkedList();
        java.util.List tempMediumButtons = new LinkedList();
        java.util.List tempLowButtons = new LinkedList();
        HashMap buttonStateMap = new HashMap();
        buttonStateMap.put(GalleryElementState.BIG, new LinkedList());
        buttonStateMap.put(GalleryElementState.MEDIUM, new LinkedList());
        buttonStateMap.put(GalleryElementState.SMALL, new LinkedList());
		ZRibbonButton topB;ZRibbonButton medB;ZRibbonButton lowB;
        for(Iterator i$ = topButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(topB.getState())).add(topB))
            topB = (ZRibbonButton)i$.next();


        for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(medB.getState())).add(medB))
            medB = (ZRibbonButton)i$.next();


        for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); ((java.util.List)buttonStateMap.get(lowB.getState())).add(lowB))
            lowB = (ZRibbonButton)i$.next();

        switch(FSXPBandControlPanelUI$1.$SwitchMap$com$zbluesoftware$fsxp$ribbon$ZRibbonBand$RibbonBandCollapseKind[collapseKind.ordinal()])
        {
        default:
            break;

        case 1: // '\001'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempTopButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempTopButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempTopButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 2: // '\002'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempTopButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempTopButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempMediumButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 3: // '\003'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempTopButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempMediumButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempMediumButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 4: // '\004'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempTopButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempMediumButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempLowButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 5: // '\005'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempTopButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempLowButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempLowButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 6: // '\006'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempMediumButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempLowButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempLowButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;

        case 7: // '\007'

            for(Iterator i$ = topButtons.iterator(); i$.hasNext(); tempLowButtons.add(topB))
                topB = (ZRibbonButton)i$.next();


            for(Iterator i$ = mediumButtons.iterator(); i$.hasNext(); tempLowButtons.add(medB))
                medB = (ZRibbonButton)i$.next();


            for(Iterator i$ = lowButtons.iterator(); i$.hasNext(); tempLowButtons.add(lowB))
                lowB = (ZRibbonButton)i$.next();

            break;
        }

        for(Iterator i$ = tempTopButtons.iterator(); i$.hasNext(); topB.setState(GalleryElementState.BIG, false))
            topB = (ZRibbonButton)i$.next();


        for(Iterator i$ = tempMediumButtons.iterator(); i$.hasNext(); medB.setState(GalleryElementState.MEDIUM, false))
            medB = (ZRibbonButton)i$.next();


        for(Iterator i$ = tempLowButtons.iterator(); i$.hasNext(); lowB.setState(GalleryElementState.SMALL, false))
            lowB = (ZRibbonButton)i$.next();

        panelWidth += getWidth(tempTopButtons, tempMediumButtons, tempLowButtons);
        for(Iterator i$ = buttonStateMap.entrySet().iterator(); i$.hasNext();)
        {
            java.util.Map.Entry stateEntry = (java.util.Map.Entry)i$.next();
            i$ = ((java.util.List)stateEntry.getValue()).iterator();
            while(i$.hasNext()) 
            {
                ZRibbonButton button = (ZRibbonButton)i$.next();
                button.setState((GalleryElementState)stateEntry.getKey(), false);
            }
        }
        this.sizeCacheHM.put(collapseKind, new Integer(panelWidth));
        return panelWidth;
*/
    }

	protected int getWidth(java.util.List<ZRibbonButton> bigButtons,
			java.util.List<ZRibbonButton> mediumButtons,
			java.util.List<ZRibbonButton> smallButtons) {
        int result = 4;
		
		for (ZRibbonButton top : bigButtons) {
			result += top.getPreferredSize().width;
			result += 4;
		}

		int medSize = mediumButtons.size();
		if (medSize > 0) {
			// try to move buttons from low to med to make
			// three-somes.
			while (((mediumButtons.size() % 3) != 0)
					&& (smallButtons.size() > 0)) {
				ZRibbonButton low = smallButtons.remove(0);
				low.setState(GalleryElementState.MEDIUM, false);
				mediumButtons.add(low);
			}
		}

		// at this point, mediumButtons list contains either
		// threesomes, or there are no buttons in lowButtons.
		int index3 = 0;
		int maxWidth3 = 0;
		for (ZRibbonButton medium : mediumButtons) {
			int medWidth = medium.getPreferredSize().width;
			maxWidth3 = Math.max(maxWidth3, medWidth);
			index3++;

			if (index3 == 3) {
				// last button in threesome
				index3 = 0;
				result += maxWidth3;
				result += 4;
				maxWidth3 = 0;
			}
		}
		// at this point, maxWidth3 may be non-zero. We can safely
		// add it, since in this case there will be no buttons
		// left in lowButtons
		result += maxWidth3;
		if (maxWidth3 > 0)
			result += 4;

		index3 = 0;
		maxWidth3 = 0;
		for (ZRibbonButton low : smallButtons) {
			int lowWidth = low.getPreferredSize().width;
			maxWidth3 = Math.max(maxWidth3, lowWidth);
			index3++;

			if (index3 == 3) {
				// last button in threesome
				index3 = 0;
				result += maxWidth3;
				result += 4;
				maxWidth3 = 0;
			}
		}
		// at this point, maxWidth3 may be non-zero. We can safely
		// add it, since in this case there will be no buttons left
		result += maxWidth3;
		result += 4;

		return result;
/*
		for (Iterator i$ = bigButtons.iterator();i$.hasNext();)
        {
            ZRibbonButton top = (ZRibbonButton)i$.next();
            result = (int)((double)result + top.getPreferredSize().getWidth());
            result += 4;
        }

        int medSize = mediumButtons.size();
        if(medSize > 0)
        {
            while( (mediumButtons.size() % 3) != 0 && (smallButtons.size() > 0) )
            {
                ZRibbonButton low = (ZRibbonButton)smallButtons.remove(0);
                low.setState(GalleryElementState.MEDIUM, false);
				mediumButtons.add(low);
            }
        }
        int index3 = 0;
        int maxWidth3 = 0;
//WHILE == FOUR
		for (Iterator i$ = mediumButtons.iterator();i$.hasNext();)
			{
            ZRibbonButton medium = (ZRibbonButton)i$.next();
            int medWidth = medium.getPreferredSize().width;
            maxWidth3 = Math.max(maxWidth3, medWidth);
			index3++;
			
            if(index3 == 3)
			{
			index3 = 0;
            result += maxWidth3;
            result += 4;
            maxWidth3 = 0;
			}
		}
        result += maxWidth3;
        if(maxWidth3 > 0)
            result += 4;
        index3 = 0;
        maxWidth3 = 0;
//WHILE == FIVE
		for (Iterator i$ = smallButtons.iterator();i$.hasNext();)
		{
            ZRibbonButton low = (ZRibbonButton)i$.next();
            int lowWidth = low.getPreferredSize().width;
            maxWidth3 = Math.max(maxWidth3, lowWidth);
			index3++;
            if(index3 == 3)
			{
            index3 = 0;
            result += maxWidth3;
            result += 4;
            maxWidth3 = 0;
			}
		}
        result += maxWidth3;
		result += 4;
        return result;
*/
    }
}