// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPRibbonBandUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.*;
import com.zbluesoftware.fsxp.ribbon.ZRibbonBand;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package ui:
//            ZRibbonBandUI, FSXPBandControlPanelUI

public class FSXPRibbonBandUI extends ZRibbonBandUI
{
    private class RibbonBandLayout
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
            int width = ribbonBand.getControlPanel().isVisible() ? collapsedButton.getPreferredSize().width : ribbonBand.getControlPanel().getPreferredSize().width;
            return new Dimension(width + 2, 86);
        }

        public Dimension minimumLayoutSize(Container c)
        {
            return preferredLayoutSize(c);
        }

        public void layoutContainer(Container c)
        {
            if(!c.isVisible())
                return;
            ZRibbonBand.RibbonBandCollapseKind bestFitCollapse = (ZRibbonBand.RibbonBandCollapseKind)ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().getLast();
            Iterator i$ = ZRibbonBand.RibbonBandCollapseKind.getSortedKinds().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                ZRibbonBand.RibbonBandCollapseKind collapseKind = (ZRibbonBand.RibbonBandCollapseKind)i$.next();
                int collapsedWidth = getPreferredWidth(collapseKind);
                if(collapsedWidth >= c.getWidth())
                    continue;
                bestFitCollapse = collapseKind;
                break;
            } while(true);
            currentFitCollapse = bestFitCollapse;
            if(bestFitCollapse == ZRibbonBand.RibbonBandCollapseKind.ICON)
            {
                collapsedButton.setVisible(true);
                int w = collapsedButton.getPreferredSize().width;
                collapsedButton.setBounds((c.getWidth() - w) / 2, 18, w, 68);
                Dimension size = new Dimension(4 + getPreferredWidth(ZRibbonBand.RibbonBandCollapseKind.NONE), 72);
                if(collapsedButton.getGallery() == null)
                {
                    collapsedButton.setGallery(new ZPopupGallery(ribbonBand.getControlPanel(), size));
                    ribbonBand.setControlPanel(null);
                }
                return;
            }
            if(collapsedButton.isVisible())
            {
                ZPopupGallery popupGallery = collapsedButton.getGallery();
                if(popupGallery != null)
                {
                    ZBandControlPanel panelFromPopup = (ZBandControlPanel)collapsedButton.getGallery().removeComponent();
                    ribbonBand.setControlPanel(panelFromPopup);
                    collapsedButton.setGallery(null);
                }
            }
            collapsedButton.setVisible(false);
            ZBandControlPanel controlPanel = ribbonBand.getControlPanel();
            controlPanel.setVisible(true);
            controlPanel.setBounds(1, 1, c.getWidth() - 2, 67);
            controlPanel.doLayout();
        }

        final FSXPRibbonBandUI this$0;
        private RibbonBandLayout()
        {
			super();
            this$0 = FSXPRibbonBandUI.this;
        }
    }

    public FSXPRibbonBandUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPRibbonBandUI();
    }

    public void installUI(JComponent c)
    {
        ribbonBand = (ZRibbonBand)c;
        installDefaults();
        installComponents();
        installListeners();
        c.setLayout(createLayoutManager());
    }

    public void uninstallUI(JComponent c)
    {
        c.setLayout(null);
        uninstallListeners();
        uninstallDefaults();
        uninstallComponents();
    }

    protected void installDefaults()
    {
        LookAndFeel.installColorsAndFont(ribbonBand, "ZRibbonBand.background", "ZRibbonBand.foreground", "ZRibbonBand.font");
        LookAndFeel.installBorder(ribbonBand, "ZRibbonBand.border");
    }

    protected void installComponents()
    {
        StackIcon icon = new StackIcon(ribbonBand.getIcon(), 0.25D);
        collapsedButton = new ZRibbonButton(null, ribbonBand.getTitle(), icon);
        collapsedButton.setState(GalleryElementState.BIG, true);
        ribbonBand.add(collapsedButton);
        if(ribbonBand.getExpandActionListener() != null)
        {
            expandButton = createExpandButton();
            ribbonBand.add(expandButton);
        }
    }

    protected AbstractButton createExpandButton()
    {
        JButton result = new JButton(UIManager.getIcon("ZRibbonBand.expandIcon"));
        result.setPreferredSize(new Dimension(result.getIcon().getIconWidth() + 4, result.getIcon().getIconHeight() + 4));
        return result;
    }

    protected void installListeners()
    {
        mouseListener = new MouseAdapter() {
            public void mouseEntered(MouseEvent event)
            {
                underMouse = true;
                ribbonBand.repaint();
            }
            public void mouseExited(MouseEvent event)
            {
                if(ribbonBand.contains(event.getPoint()))
                {
                    return;
                } else
                {
                    underMouse = false;
                    ribbonBand.repaint();
                    return;
                }
            }
            final FSXPRibbonBandUI this$0;
            {
				this$0 = FSXPRibbonBandUI.this;
            }
        };
        ribbonBand.addMouseListener(mouseListener);
        if(expandButton != null)
        {
            expandButton.addActionListener(ribbonBand.getExpandActionListener());
            expandButtonActionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e)
                {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run()
                        {
                            underMouse = false;
                            if(ribbonBand != null)
                                ribbonBand.repaint();
                        }
                    });
                }
                final FSXPRibbonBandUI this$0;
            {
				this$0 = FSXPRibbonBandUI.this;
            }
            };
            expandButton.addActionListener(expandButtonActionListener);
        }
    }

    protected void uninstallDefaults()
    {
        LookAndFeel.uninstallBorder(ribbonBand);
    }

    protected void uninstallComponents()
    {
        if(collapsedButton.isVisible())
        {
            ZPopupGallery popupGallery = collapsedButton.getGallery();
            if(popupGallery != null)
            {
                ZBandControlPanel panelFromPopup = (ZBandControlPanel)collapsedButton.getGallery().removeComponent();
                ribbonBand.setControlPanel(panelFromPopup);
                collapsedButton.setGallery(null);
            }
        }
        ribbonBand.remove(collapsedButton);
        collapsedButton = null;
        if(expandButton != null)
            ribbonBand.remove(expandButton);
        expandButton = null;
        ribbonBand = null;
    }

    protected void uninstallListeners()
    {
        ribbonBand.removeMouseListener(mouseListener);
        mouseListener = null;
        if(expandButton != null)
        {
            expandButton.removeActionListener(expandButtonActionListener);
            expandButtonActionListener = null;
            expandButton.removeActionListener(ribbonBand.getExpandActionListener());
        }
    }

    protected LayoutManager createLayoutManager()
    {
        return new RibbonBandLayout();
    }

    public boolean setUnderMouse(boolean underMouse)
    {
        if(this.underMouse != underMouse)
        {
            this.underMouse = underMouse;
            return true;
        } else
        {
            return false;
        }
    }

    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if(currentFitCollapse != ZRibbonBand.RibbonBandCollapseKind.ICON)
            paintBandTitle(g2, new Rectangle(0, 68, c.getWidth(), 18), ribbonBand.getTitle());
        else
            paintBandBackground(g2, new Rectangle(0, 0, c.getWidth(), c.getHeight()));
        g2.dispose();
    }

    protected void paintBandTitle(Graphics2D g2, Rectangle rect, String title)
    {
        if(underMouse)
            g2.setPaint(new Color(201, 225, 255));
        else
            g2.setPaint(new Color(193, 217, 240));
        g2.fillRect(rect.x + 3, rect.y, rect.width - 6, rect.height - 2);
        ((FSXPBandControlPanelUI)ribbonBand.getControlPanel().getUI()).setUnderMouse(underMouse);
        if(underMouse)
        {
            g2.setPaint(Color.white);
            g2.draw(new Line2D.Float(rect.x + 2, rect.y, rect.x + 2, (rect.y + rect.height) - 5));
            g2.draw(new Line2D.Float((rect.x + rect.width) - 4, rect.y, (rect.x + rect.width) - 4, (rect.y + rect.height) - 5));
            g2.draw(new Line2D.Float(rect.x + 4, (rect.y + rect.height) - 3, (rect.x + rect.width) - 6, (rect.y + rect.height) - 3));
            g2.draw(new Line2D.Float(rect.x + 2, (rect.y + rect.height) - 5, rect.x + 4, (rect.y + rect.height) - 3));
            g2.draw(new Line2D.Float((rect.x + rect.width) - 4, (rect.y + rect.height) - 5, (rect.x + rect.width) - 6, (rect.y + rect.height) - 3));
        } else
        {
            g2.setPaint(new Color(230, 238, 246));
            g2.draw(new Line2D.Float(rect.x + 2, rect.y, rect.x + 2, (rect.y + rect.height) - 3));
            g2.draw(new Line2D.Float((rect.x + rect.width) - 2, rect.y, (rect.x + rect.width) - 2, (rect.y + rect.height) - 3));
            g2.draw(new Line2D.Float(rect.x + 4, (rect.y + rect.height) - 1, (rect.x + rect.width) - 4, (rect.y + rect.height) - 1));
            g2.draw(new Line2D.Float(rect.x + 2, (rect.y + rect.height) - 3, rect.x + 4, (rect.y + rect.height) - 1));
            g2.draw(new Line2D.Float((rect.x + rect.width) - 2, (rect.y + rect.height) - 3, (rect.x + rect.width) - 4, (rect.y + rect.height) - 1));
        }
        g2.setPaint(new Color(162, 190, 212));
        g2.draw(new Line2D.Float(rect.x + 1, rect.y, rect.x + 1, (rect.y + rect.height) - 4));
        g2.draw(new Line2D.Float((rect.x + rect.width) - 3, rect.y, (rect.x + rect.width) - 3, (rect.y + rect.height) - 4));
        g2.draw(new Line2D.Float(rect.x + 3, (rect.y + rect.height) - 2, (rect.x + rect.width) - 5, (rect.y + rect.height) - 2));
        g2.draw(new Line2D.Float(rect.x + 1, (rect.y + rect.height) - 4, rect.x + 3, (rect.y + rect.height) - 2));
        g2.draw(new Line2D.Float((rect.x + rect.width) - 3, (rect.y + rect.height) - 4, (rect.x + rect.width) - 5, (rect.y + rect.height) - 2));
        g2.setFont(new Font("Tahoma", 0, 11));
        int y = (rect.y - 2) + (rect.height + g2.getFontMetrics().getAscent()) / 2;
        int x = (rect.width - g2.getFontMetrics().stringWidth(title)) / 2;
        g2.setColor(new Color(52, 91, 154));
        g2.drawString(title, x, y);
    }

    protected void paintBandBackground(Graphics2D g2, Rectangle rect)
    {
        g2.setPaint(new Color(193, 217, 240));
        g2.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public int getPreferredWidth(ZRibbonBand.RibbonBandCollapseKind collapseKind)
    {
        if(collapseKind == ZRibbonBand.RibbonBandCollapseKind.ICON)
            return collapsedButton.getPreferredSize().width + 2;
        ZBandControlPanel controlPanel = ribbonBand.getControlPanel();
        if(controlPanel == null)
        {
            ZPopupGallery gallery = collapsedButton.getGallery();
            controlPanel = (ZBandControlPanel)gallery.getComponent();
        }
        FSXPBandControlPanelUI baseUI = (FSXPBandControlPanelUI)controlPanel.getUI();
        return baseUI.getPreferredWidth(collapseKind);
    }

    public AbstractButton getExpandButton()
    {
        return expandButton;
    }

    protected ZRibbonBand ribbonBand;
    protected ZRibbonButton collapsedButton;
    protected ZRibbonBand.RibbonBandCollapseKind currentFitCollapse;
    protected AbstractButton expandButton;
    protected MouseListener mouseListener;
    protected ActionListener expandButtonActionListener;
    protected boolean underMouse;
    public static final int BAND_HEADER_HEIGHT = 18;
    public static final int BAND_CONTROL_PANEL_HEIGHT = 68;
}