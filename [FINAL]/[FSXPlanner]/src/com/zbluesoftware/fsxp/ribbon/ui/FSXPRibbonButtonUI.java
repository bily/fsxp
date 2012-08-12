// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPRibbonButtonUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.AsynchronousLoadListener;
import com.zbluesoftware.fsxp.ribbon.AsynchronousLoading;
import com.zbluesoftware.fsxp.ribbon.GalleryElementState;
import com.zbluesoftware.fsxp.ribbon.ResizableIcon;
import com.zbluesoftware.fsxp.ribbon.ZPopupGallery;
import com.zbluesoftware.fsxp.ribbon.ZRibbonButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZRibbonButtonUI

class FSXPRibbonButtonUI$4 {
    static final int[] $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState = new int[GalleryElementState.values().length];
    static 
    {
        try
        {
            $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[GalleryElementState.SMALL.ordinal()] = 1;
        }
        catch( NoSuchFieldError ex )
        {
        }
        try
        {
            $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[GalleryElementState.MEDIUM.ordinal()] = 2;
        }
        catch( NoSuchFieldError nosuchfielderror1 )
        {
        }
        try
        {
            $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[GalleryElementState.CUSTOM.ordinal()] = 3;
        }
        catch( NoSuchFieldError nosuchfielderror2 )
        {
        }
        try
        {
            $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[GalleryElementState.BIG.ordinal()] = 4;
        }
        catch( NoSuchFieldError nosuchfielderror3 )
        {
        }
        try
        {
            $SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[GalleryElementState.ORIG.ordinal()] = 5;
        }
        catch( NoSuchFieldError nosuchfielderror4 )
        {
        }
    }
}

public class FSXPRibbonButtonUI extends ZRibbonButtonUI
{
    private class RibbonButtonLayout
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
            Insets borderInsets = ribbonButton.getBorder().getBorderInsets(ribbonButton);
            int bx = borderInsets.left + borderInsets.right;
            int by = borderInsets.top + borderInsets.bottom;
            Dimension iconDim = toTakeSavedDimension ? savedDimension : new Dimension(ribbonButton.getIcon().getIconWidth(), ribbonButton.getIcon().getIconHeight());
            
			switch(FSXPRibbonButtonUI$4.$SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[ribbonButton.getState().ordinal()])
            {
            case 1: // '\001'
                String buttonTitle = ribbonButton.getTitle();
                if(buttonTitle.length() > 4)
                    buttonTitle = buttonTitle.substring(0, 4);
                return new Dimension(bx + iconDim.width + 4 + (int)fontMetrics.getStringBounds(buttonTitle, g2).getWidth() + 4 + (actionLabel == null ? 0 : actionLabel.getPreferredSize().width), by + iconDim.height);

            case 2: // '\002'
                return new Dimension(bx + iconDim.width + 4 + (int)fontMetrics.getStringBounds(ribbonButton.getTitle(), g2).getWidth() + 4 + (actionLabel == null ? 0 : actionLabel.getPreferredSize().width), by + iconDim.height);

            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
                Rectangle2D rect1 = fontMetrics.getStringBounds(titlePart1, g2);
                Rectangle2D rect2 = fontMetrics.getStringBounds(titlePart2, g2);
                int width = Math.max(iconDim.width, (int)Math.max(rect1.getWidth(), rect2.getWidth() + 4D + (double)(actionLabel == null ? 0 : actionLabel.getPreferredSize().width)));
                return new Dimension(bx + width, by + iconDim.height + 4 + 2 * fontMetrics.getHeight());
            }
            return null;
        }

        public Dimension minimumLayoutSize(Container c)
        {
            return preferredLayoutSize(c);
        }

        public void layoutContainer(Container c)
        {
            int width = c.getWidth();
            int height = c.getHeight();
            Icon actionIcon = actionLabel == null ? null : actionLabel.getIcon();
            int x = ribbonButton.getBorder().getBorderInsets(ribbonButton).left;
            int y = 3 + ribbonButton.getBorder().getBorderInsets(ribbonButton).top;
			switch(FSXPRibbonButtonUI$4.$SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[ribbonButton.getState().ordinal()])
            {
            default:
                break;

            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
                if(ribbonButton.getState() == GalleryElementState.BIG)
                {
                    ribbonButton.getIcon().setHeight(32);
                    ribbonButton.getDisabledIcon().setHeight(32);
                } else
                if(ribbonButton.getState() == GalleryElementState.ORIG)
                {
                    ribbonButton.getIcon().revertToOriginalDimension();
                    ribbonButton.getDisabledIcon().revertToOriginalDimension();
                }
                iconLabel.setBounds((width - ribbonButton.getIcon().getIconWidth()) / 2, y, ribbonButton.getIcon().getIconWidth(), ribbonButton.getIcon().getIconHeight());
                y += ribbonButton.getIcon().getIconHeight();
                int labelWidth = bigLabel1.getPreferredSize().width;
                int labelHeight = bigLabel1.getPreferredSize().height;
                bigLabel1.setBounds((width - labelWidth) / 2, y, labelWidth, labelHeight);
                y += labelHeight;
                labelWidth = bigLabel2.getPreferredSize().width + (actionLabel == null ? 4 : 4 + actionLabel.getPreferredSize().width);
                x = (width - labelWidth) / 2;
                bigLabel2.setBounds(x, y, bigLabel2.getPreferredSize().width, labelHeight);
                if(actionLabel != null)
                {
                    x = (x += 4) + bigLabel2.getPreferredSize().width;
                    actionLabel.setBounds(x, y + (labelHeight - actionIcon.getIconHeight()) / 2, actionIcon.getIconWidth(), actionIcon.getIconHeight());
                }
                break;

            case 2: // '\002'
                ribbonButton.getIcon().setHeight(16);
                ribbonButton.getDisabledIcon().setHeight(16);
                iconLabel.setBounds(x, (height - ribbonButton.getIcon().getIconHeight()) / 2, ribbonButton.getIcon().getIconWidth(), ribbonButton.getIcon().getIconHeight());
                x = (x += 4) + ribbonButton.getIcon().getIconWidth();
                midLabel.setBounds(x, (height - midLabel.getPreferredSize().height) / 2, midLabel.getPreferredSize().width, midLabel.getPreferredSize().height);
                x = (x += 4) + midLabel.getPreferredSize().width;
                if(actionLabel != null)
                    actionLabel.setBounds(x, (height - actionIcon.getIconHeight()) / 2, actionIcon.getIconWidth(), actionIcon.getIconHeight());
                break;

            case 1: // '\001'
                ribbonButton.getIcon().setHeight(16);
                ribbonButton.getDisabledIcon().setHeight(16);
                iconLabel.setBounds(x, (height - ribbonButton.getIcon().getIconHeight()) / 2, ribbonButton.getIcon().getIconWidth(), ribbonButton.getIcon().getIconHeight());
                x = (x += 4) + ribbonButton.getIcon().getIconWidth();
                midLabel.setBounds(x, (height - midLabel.getPreferredSize().height) / 2, midLabel.getPreferredSize().width, midLabel.getPreferredSize().height);
                x = (x += 4) + midLabel.getPreferredSize().width;
                if(actionLabel != null)
                    actionLabel.setBounds(x, (height - actionIcon.getIconHeight()) / 2, actionIcon.getIconWidth(), actionIcon.getIconHeight());
                break;
            }
            ribbonButton.putClientProperty("icon.bounds", iconLabel.getBounds());
        }

        private FontMetrics fontMetrics;
        private Graphics2D g2;
        final FSXPRibbonButtonUI this$0;

        public RibbonButtonLayout()
        {
            super();
			this$0 = FSXPRibbonButtonUI.this;
            g2 = (Graphics2D)(new BufferedImage(1, 1, 2)).getGraphics();
            g2.setFont(UIManager.getFont("ZRibbon.font"));
            fontMetrics = g2.getFontMetrics();
        }
    }


    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPRibbonButtonUI();
    }

    public FSXPRibbonButtonUI()
    {
        toTakeSavedDimension = false;
    }

    public void setUnderMouse(boolean underMouse)
    {
        this.underMouse = underMouse;
    }

    public void installUI(JComponent c)
    {
        ribbonButton = (ZRibbonButton)c;
        installDefaults();
        installComponents();
        installListeners();
        ribbonButton.setLayout(createLayoutManager());
        if(ribbonButton.getState() != null)
            updateState(ribbonButton.getState(), false);
        updateCustomDimension();
    }

    public void uninstallUI(JComponent c)
    {
        c.setLayout(null);
        uninstallListeners();
        uninstallComponents();
        uninstallDefaults();
        ribbonButton = null;
    }

    protected void installDefaults()
    {
        ribbonButton.setRolloverEnabled(true);
    }

    protected void installComponents()
    {
        ribbonButton.setBorder(new EmptyBorder(4, 4, 4, 4));
        BufferedImage tempImage = new BufferedImage(30, 30, 2);
        Graphics2D g = (Graphics2D)tempImage.getGraphics();
        g.setFont(UIManager.getFont("ZRibbon.font"));
        FontMetrics fm = g.getFontMetrics();
        String title = ribbonButton.getTitle();
        if(title != null)
        {
            int spaceIndex = title.indexOf(' ');
            if(spaceIndex < 0)
            {
                titlePart1 = title;
                titlePart2 = "";
            } else
            {
                int currMaxLength = (int)fm.getStringBounds(ribbonButton.getTitle(), g).getWidth();
                int actionLabelWidth = actionLabel != null ? 4 + actionLabel.getIcon().getIconWidth() : 0;
                for(; spaceIndex >= 0; spaceIndex = title.indexOf(' ', spaceIndex + 1))
                {
                    String part1 = title.substring(0, spaceIndex);
                    String part2 = title.substring(spaceIndex + 1, title.length());
                    int len1 = (int)fm.getStringBounds(part1, g).getWidth();
                    int len2 = (int)fm.getStringBounds(part2, g).getWidth() + actionLabelWidth;
                    int len = Math.max(len1, len2);
                    if(currMaxLength > len)
                    {
                        currMaxLength = len;
                        titlePart1 = part1;
                        titlePart2 = part2;
                    }
                }

            }
        } else
        {
            titlePart1 = "";
            titlePart2 = "";
        }
        midLabel = new JLabel();
        midLabel.setForeground(new Color(21, 66, 139));
        bigLabel1 = new JLabel();
        bigLabel1.setForeground(new Color(21, 66, 139));
        bigLabel2 = new JLabel();
        bigLabel2.setForeground(new Color(21, 66, 139));
        ribbonButton.add(midLabel);
        ribbonButton.add(bigLabel1);
        ribbonButton.add(bigLabel2);
        ResizableIcon buttonIcon = ribbonButton.getIcon();
        iconLabel = new JLabel(ribbonButton.getIcon());
        if(buttonIcon instanceof AsynchronousLoading)
            ((AsynchronousLoading) buttonIcon).addAsynchronousLoadListener( (AsynchronousLoadListener) new AsynchronousLoadListener() {
			public void completed(boolean success)
                {
                    if(success)
                        ribbonButton.repaint();
                }
            final FSXPRibbonButtonUI this$0;
            {
				this$0 = FSXPRibbonButtonUI.this;
            }
            });
        ribbonButton.add(iconLabel);
        if(ribbonButton.getGallery() != null)
        {
            actionLabel = new JLabel(UIManager.getIcon("GalleryButton.expandIcon"));
            ribbonButton.add(actionLabel);
        }
    }

    protected void installListeners()
    {
        rolloverListener = new MouseAdapter() {

            public void mouseEntered(MouseEvent e)
            {
                underMouse = true;
                ribbonButton.repaint();
            }

            public void mouseExited(MouseEvent e)
            {
                underMouse = false;
                ribbonButton.repaint();
            }

            final FSXPRibbonButtonUI this$0;
            {
                this$0 = FSXPRibbonButtonUI.this;
            }
        }
;
        ribbonButton.addMouseListener(rolloverListener);
        focusListener = new FocusAdapter() {

            public void focusGained(FocusEvent e)
            {
                ribbonButton.repaint();
            }

            public void focusLost(FocusEvent e)
            {
                ribbonButton.repaint();
            }

            final FSXPRibbonButtonUI this$0;
            {
                this$0 = FSXPRibbonButtonUI.this;
            }
        }
;
        ribbonButton.addFocusListener(focusListener);
    }

    protected void uninstallDefaults()
    {
    }

    protected void uninstallComponents()
    {
        ribbonButton.remove(midLabel);
        ribbonButton.remove(bigLabel1);
        ribbonButton.remove(bigLabel2);
        ribbonButton.remove(iconLabel);
        if(actionLabel != null)
            ribbonButton.remove(actionLabel);
    }

    protected void uninstallListeners()
    {
        ribbonButton.removeMouseListener(rolloverListener);
        ribbonButton.removeFocusListener(focusListener);
        rolloverListener = null;
        focusListener = null;
    }

    protected LayoutManager createLayoutManager()
    {
        return new RibbonButtonLayout();
    }

    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintButtonBackground(g2, new Rectangle(0, 1, c.getWidth(), c.getHeight() - 2));
        g2.dispose();
    }

    protected void paintButtonBackground(Graphics2D g2, Rectangle toFill)
    {
        boolean highlight = underMouse && ribbonButton.isEnabled() || ribbonButton.isPopupDisplayed();
        if(highlight)
        {
            int y = (int)((double)toFill.height * 0.40000000000000002D);
            g2.setPaint(new Color(255, 247, 199));
            g2.fillRect(toFill.x + 1, toFill.y, toFill.width - 2, toFill.y + y);
            GradientPaint gp = new GradientPaint(toFill.x, toFill.y + y, new Color(255, 213, 78), toFill.x, toFill.height - y, new Color(255, 231, 155));
            g2.setPaint(gp);
            g2.fillRect(toFill.x + 1, toFill.y + y, toFill.width - 2, toFill.height - y);
            g2.setPaint(new Color(192, 167, 118));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x, toFill.y + 1, toFill.x, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 1, toFill.y + 1, (toFill.x + toFill.width) - 1, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y, (toFill.x + toFill.width) - 2, toFill.y));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, (toFill.y + toFill.height) - 1, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 1));
            g2.setPaint(new Color(255, 249, 228));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 1, toFill.x + 1, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 2, toFill.y + 1, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 1, (toFill.x + toFill.width) - 2, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, (toFill.y + toFill.height) - 2, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 2));
        } else
        if(ribbonButton.isGrouped() && ribbonButton.isSelected())
        {
            int y = (int)((double)toFill.height * 0.40000000000000002D);
            g2.setPaint(new Color(228, 255, 234));
            g2.fillRect(toFill.x + 1, toFill.y, toFill.width - 2, toFill.y + y);
            GradientPaint gp = new GradientPaint(toFill.x, toFill.y + y, new Color(141, 255, 169), toFill.x, toFill.height - y, new Color(190, 255, 206));
            g2.setPaint(gp);
            g2.fillRect(toFill.x + 1, toFill.y + y, toFill.width - 2, toFill.height - y);
            g2.setPaint(new Color(123, 197, 141));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x, toFill.y + 1, toFill.x, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 1, toFill.y + 1, (toFill.x + toFill.width) - 1, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y, (toFill.x + toFill.width) - 2, toFill.y));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, (toFill.y + toFill.height) - 1, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 1));
            g2.setPaint(new Color(237, 255, 241));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 1, toFill.x + 1, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float((toFill.x + toFill.width) - 2, toFill.y + 1, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 2));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, toFill.y + 1, (toFill.x + toFill.width) - 2, toFill.y + 1));
            g2.draw(new java.awt.geom.Line2D.Float(toFill.x + 1, (toFill.y + toFill.height) - 2, (toFill.x + toFill.width) - 2, (toFill.y + toFill.height) - 2));
        }
    }

    public void updateState(GalleryElementState state, boolean toUpdateIcon)
    {
        if(state != GalleryElementState.CUSTOM)
        {
            midLabel.setVisible(false);
            bigLabel1.setVisible(false);
            bigLabel2.setVisible(false);
        }
        int maxHeight = 0;
        switch(FSXPRibbonButtonUI$4.$SwitchMap$com$zbluesoftware$fsxp$ribbon$GalleryElementState[state.ordinal()])
        {
        case 3: // '\003'
        default:
            break;

        case 1: // '\001'
            if(ribbonButton.getTitle().length() > 5)
                midLabel.setText((new StringBuilder()).append(ribbonButton.getTitle().substring(0, 4)).append("...").toString());
            else
                midLabel.setText(ribbonButton.getTitle());
            midLabel.setVisible(true);
            maxHeight = 16;
            break;

        case 2: // '\002'
            midLabel.setText(ribbonButton.getTitle());
            midLabel.setVisible(true);
            maxHeight = 16;
            break;

        case 4: // '\004'
            bigLabel1.setText(titlePart1);
            bigLabel1.setVisible(true);
            bigLabel2.setText(titlePart2);
            bigLabel2.setVisible(true);
            maxHeight = 32;
            break;

        case 5: // '\005'
            bigLabel1.setText(titlePart1);
            bigLabel1.setVisible(true);
            bigLabel2.setText(titlePart2);
            bigLabel2.setVisible(true);
            maxHeight = ribbonButton.getIcon().getIconHeight();
            break;
        }
        toTakeSavedDimension = !toUpdateIcon && state != GalleryElementState.CUSTOM;
        savedDimension = new Dimension(maxHeight, maxHeight);
        if(toUpdateIcon && state != GalleryElementState.CUSTOM)
        {
            ribbonButton.getIcon().setHeight(maxHeight);
            ribbonButton.getDisabledIcon().setHeight(maxHeight);
        }
    }

    public void updateCustomDimension()
    {
        int dimension = ribbonButton.getCustomDimension();
        if(dimension > 0)
        {
            midLabel.setVisible(false);
            bigLabel1.setVisible(true);
            bigLabel2.setVisible(true);
            bigLabel1.setText(titlePart1);
            bigLabel2.setText(titlePart2);
            ribbonButton.getIcon().setDimension(new Dimension(dimension, dimension));
            ribbonButton.setState(GalleryElementState.CUSTOM, true);
        }
    }

    public void updateGallery(ZPopupGallery gallery)
    {
        if(gallery != null && ribbonButton.getGallery() == null)
        {
            actionLabel = new JLabel(UIManager.getIcon("GalleryButton.expandIcon"));
            ribbonButton.add(actionLabel);
        }
        if(gallery == null && ribbonButton.getGallery() != null)
        {
            ribbonButton.remove(actionLabel);
            actionLabel = null;
        }
    }

    public void setExpandIcon(Icon icon)
    {
        if(actionLabel != null)
            actionLabel.setIcon(icon);
    }

    protected ZRibbonButton ribbonButton;
    protected MouseListener rolloverListener;
    protected FocusListener focusListener;
    protected String titlePart1;
    protected String titlePart2;
    protected JLabel bigLabel1;
    protected JLabel bigLabel2;
    protected JLabel midLabel;
    protected JLabel iconLabel;
    protected JLabel actionLabel;
    protected Dimension savedDimension;
    protected boolean underMouse;
    protected boolean toTakeSavedDimension;
}