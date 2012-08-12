// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPRibbonGalleryUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZRibbonGalleryUI

public class FSXPRibbonGalleryUI extends ZRibbonGalleryUI
{
    private class RibbonGalleryLayout
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
            return new Dimension(ribbonGallery.getPreferredWidth(ribbonGallery.getState()), c.getHeight());
        }

        public Dimension minimumLayoutSize(Container c)
        {
            return preferredLayoutSize(c);
        }

        public void layoutContainer(Container c)
        {
            int width = c.getWidth();
            int height = c.getHeight();
            Insets borderInsets = ribbonGallery.getBorder().getBorderInsets(ribbonGallery);
            int totalButtonHeight = (int)(0.90000000000000002D * (double)height);
            int buttonY = (height - totalButtonHeight) / 2;
            int buttonHeight = totalButtonHeight / 3;
            int buttonWidth = 15;
            int buttonX = width - buttonWidth - borderInsets.right;
            scrollDownButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            scrollUpButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            expandActionButton.setPreferredSize(new Dimension(buttonWidth, totalButtonHeight - 2 * buttonHeight));
            buttonStrip.setBounds(buttonX, buttonY, buttonWidth, totalButtonHeight);
            for(int i = 0; i < ribbonGallery.getButtonCount(); i++)
            {
                ZRibbonButton currButton = ribbonGallery.getButtonAt(i);
                currButton.setVisible(false);
            }

            int currIndex = firstButtonIndex;
            int startX = borderInsets.left;
            if(currIndex < 0)
            {
                startX = buttonX;
                currIndex = lastButtonIndex;
                do
                {
                    if(currIndex < 0)
                        break;
                    firstButtonIndex = currIndex;
                    ZRibbonButton currButton = ribbonGallery.getButtonAt(currIndex);
                    int currButtonWidth = currButton.getPreferredSize().width;
                    int currStartX = startX - currButtonWidth - 4;
                    if(currStartX < 0)
                        break;
                    startX = currStartX;
                    currIndex--;
                } while(true);
            }
            currIndex = firstButtonIndex;
            visibleButtonsCount = 0;
            startX = borderInsets.left;
            do
            {
                if(currIndex >= ribbonGallery.getButtonCount())
                    break;
                ZRibbonButton currButton = ribbonGallery.getButtonAt(currIndex);
                int currButtonWidth = currButton.getPreferredSize().width;
                int currButtonHeight = currButton.getPreferredSize().height;
                int nextStartX = startX + currButtonWidth + 4;
                if(nextStartX >= buttonX)
                    break;
                int topY = (height - currButtonHeight) / 2;
                currButton.setVisible(true);
                currButton.setBounds(startX, topY, currButtonWidth, currButtonHeight);
                startX = nextStartX;
                visibleButtonsCount++;
                currIndex++;
            } while(true);
            scrollDownButton.setEnabled(currIndex < ribbonGallery.getButtonCount());
            scrollUpButton.setEnabled(firstButtonIndex > 0);
        }

        final FSXPRibbonGalleryUI this$0;

        private RibbonGalleryLayout()
        {
            this$0 = FSXPRibbonGalleryUI.this;
        }

    }

    protected static class ZButtonStripUIResource extends ZButtonStrip
        implements UIResource
    {

        public ZButtonStripUIResource()
        {
        }

        public ZButtonStripUIResource(com.zbluesoftware.fsxp.ribbon.ZButtonStrip.StripOrientation orientation)
        {
            super(orientation);
        }
    }


    public FSXPRibbonGalleryUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPRibbonGalleryUI();
    }

    public void installUI(JComponent c)
    {
        ribbonGallery = (ZRibbonGallery)c;
        firstButtonIndex = 0;
        visibleButtonsCount = 0;
        installDefaults();
        installComponents();
        installListeners();
        c.setLayout(createLayoutManager());
    }

    protected void installComponents()
    {
        buttonStrip = new ZButtonStripUIResource(com.zbluesoftware.fsxp.ribbon.ZButtonStrip.StripOrientation.VERTICAL);
        ribbonGallery.add(buttonStrip);
        scrollUpButton = createScrollUpButton();
        scrollDownButton = createScrollDownButton();
        expandActionButton = createExpandButton();
        buttonStrip.add(scrollUpButton);
        buttonStrip.add(scrollDownButton);
        buttonStrip.add(expandActionButton);
    }

    protected AbstractButton createScrollDownButton()
    {
        return new BasicArrowButton(5);
    }

    protected AbstractButton createScrollUpButton()
    {
        return new BasicArrowButton(1);
    }

    protected AbstractButton createExpandButton()
    {
        return new BasicArrowButton(5);
    }

    protected void uninstallComponents()
    {
        buttonStrip.remove(scrollUpButton);
        buttonStrip.remove(scrollDownButton);
        buttonStrip.remove(expandActionButton);
        ribbonGallery.remove(buttonStrip);
    }

    protected void installDefaults()
    {
        ribbonGallery.setBorder(new EmptyBorder(3, 3, 3, 3));
    }

    protected void uninstallDefaults()
    {
    }

    protected void installListeners()
    {
        scrollDownListener = new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                firstButtonIndex += visibleButtonsCount;
                if(visibleButtonsCount > 1)
                    firstButtonIndex--;
                if(firstButtonIndex >= ribbonGallery.getButtonCount())
                    firstButtonIndex = ribbonGallery.getButtonCount() - 1;
                ribbonGallery.revalidate();
            }

            final FSXPRibbonGalleryUI this$0;
            {
                this$0 = FSXPRibbonGalleryUI.this;
            }
        }
;
        scrollDownButton.addActionListener(scrollDownListener);
        scrollUpListener = new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                lastButtonIndex = firstButtonIndex + 1;
                firstButtonIndex = -1;
                if(visibleButtonsCount == 1)
                    lastButtonIndex--;
                ribbonGallery.revalidate();
            }

            final FSXPRibbonGalleryUI this$0;
            {
                this$0 = FSXPRibbonGalleryUI.this;
            }
        }
;
        scrollUpButton.addActionListener(scrollUpListener);
        expandListener = new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ZPopupGallery.hidePopups(ribbonGallery);
                SwingUtilities.invokeLater(new Runnable() {

                    public void run()
                    {
                        PopupFactory popupFactory = PopupFactory.getSharedInstance();
                        ZPopupGallery ribbonPopupGallery = ribbonGallery.getPopupGallery();
                        if(ribbonPopupGallery == null)
                            return;
                        int x = ribbonGallery.getLocationOnScreen().x;
                        int y = ribbonGallery.getLocationOnScreen().y;
                        Rectangle scrBounds = ribbonGallery.getGraphicsConfiguration().getBounds();
                        int pw = ribbonPopupGallery.getPreferredSize().width;
                        if(x + pw > scrBounds.x + scrBounds.width)
                            x = (scrBounds.x + scrBounds.width) - pw;
                        int ph = ribbonPopupGallery.getPreferredSize().height;
                        if(y + ph > scrBounds.y + scrBounds.height)
                            y = (scrBounds.y + scrBounds.height) - ph;
                        Popup popup = popupFactory.getPopup(ribbonGallery, ribbonPopupGallery, x, y);
                        popup.show();
                        ribbonGallery.repaint();
                        ZPopupGallery.addPopup(popup, ribbonPopupGallery);
                    }
                });
            }

            final FSXPRibbonGalleryUI this$0;
            {
                this$0 = FSXPRibbonGalleryUI.this;
            }
        }
;
        expandActionButton.addActionListener(expandListener);
    }

    protected void uninstallListeners()
    {
        scrollDownButton.removeActionListener(scrollDownListener);
        scrollDownListener = null;
        scrollUpButton.removeActionListener(scrollUpListener);
        scrollUpListener = null;
        expandActionButton.removeActionListener(expandListener);
        expandListener = null;
    }

    public void uninstallUI(JComponent c)
    {
        c.setLayout(null);
        uninstallListeners();
        uninstallDefaults();
        uninstallComponents();
        ribbonGallery = null;
    }

    protected LayoutManager createLayoutManager()
    {
        return new RibbonGalleryLayout();
    }

    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        paintRibbonGalleryBackground(g2, new Rectangle(0, 0, c.getWidth(), c.getHeight()));
        g2.dispose();
    }

    protected void paintRibbonGalleryBackground(Graphics graphics, Rectangle toFill)
    {
        graphics.setColor(ribbonGallery.getBackground());
        graphics.fillRect(toFill.x, toFill.y, toFill.width, toFill.height);
    }

    protected ZRibbonGallery ribbonGallery;
    protected AbstractButton scrollDownButton;
    protected AbstractButton scrollUpButton;
    protected AbstractButton expandActionButton;
    protected ZButtonStrip buttonStrip;
    protected ActionListener scrollDownListener;
    protected ActionListener scrollUpListener;
    protected ActionListener expandListener;
    protected int firstButtonIndex;
    protected int lastButtonIndex;
    protected int visibleButtonsCount;
}