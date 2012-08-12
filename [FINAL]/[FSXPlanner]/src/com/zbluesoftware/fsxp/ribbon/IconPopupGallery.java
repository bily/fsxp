// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   IconPopupGallery.java

package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPRibbonButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon:
//            ZPopupGallery, ZRibbonButton, IconPopupGalleryTitlePane, GalleryElementState, 
//            ResizableIcon

public class IconPopupGallery extends ZPopupGallery
    implements ActionListener
{
    protected class IconGalleryLayout
        implements LayoutManager
    {

        public void addLayoutComponent(String s, Component component)
        {
        }

        public void removeLayoutComponent(Component component)
        {
        }

        public void layoutContainer(Container parent)
        {
            Insets bInsets = getBorder().getBorderInsets(IconPopupGallery.this);
            int left = bInsets.left + 2;
            int right = bInsets.right + 2;
            int top = bInsets.top + 2;
            int y = top;
            int maxButtonWidth = 0;
            for(Iterator i$ = iconGroupAL.iterator(); i$.hasNext();)
            {
                IconGroup iconGroup = (IconGroup)i$.next();
                Iterator i1$ = iconGroup.iconButtonAL.iterator();
                while(i1$.hasNext()) 
                {
                    ZRibbonButton gb = (ZRibbonButton)i1$.next();
                    maxButtonWidth = Math.max(maxButtonWidth, gb.getPreferredSize().width);
                }
            }

            for(Iterator i$ = iconGroupAL.iterator(); i$.hasNext();)
            {
                IconGroup iconGroup = (IconGroup)i$.next();
                int labelH = iconGroup.titlePanel.getPreferredSize().height;
                iconGroup.titlePanel.setBounds(left, y, maxWidth - left - right, labelH);
                y += labelH;
                int currX = left;
                for(Iterator i2$ = iconGroup.iconButtonAL.iterator(); i2$.hasNext();)		//iterator var i$ OVERLOAD!! due to nested Iterator loop- must manually rename...
                {
                    ZRibbonButton gb = (ZRibbonButton)i2$.next();
                    int endX = currX + maxButtonWidth;
                    if(endX > maxWidth - right)
                    {
                        currX = left;
                        y += rowHeightInPixels;
                    }
                    gb.setBounds(currX, y, maxButtonWidth, rowHeightInPixels);
                    currX += maxButtonWidth;
                }
                y += rowHeightInPixels;
            }
        }

        public Dimension minimumLayoutSize(Container parent)
        {
            return preferredLayoutSize(parent);
        }

        public Dimension preferredLayoutSize(Container parent)
        {
            Insets bInsets = getBorder().getBorderInsets(IconPopupGallery.this);
            int top = bInsets.top + 2;
            int bottom = bInsets.bottom + 2;
            int height = top + bottom;
            int maxButtonWidth = 0;
            for(Iterator i$ = iconGroupAL.iterator(); i$.hasNext();)
            {
                IconGroup iconGroup = (IconGroup)i$.next();
                i$ = iconGroup.iconButtonAL.iterator();
                while(i$.hasNext()) 
                {
                    ZRibbonButton gb = (ZRibbonButton)i$.next();
                    maxButtonWidth = Math.max(maxButtonWidth, gb.getPreferredSize().width);
                }
            }
            int buttonsInRow = maxWidth / maxButtonWidth;
            for(Iterator i$ = iconGroupAL.iterator(); i$.hasNext();)
            {
                IconGroup iconGroup = (IconGroup)i$.next();
                height += iconGroup.titlePanel.getPreferredSize().height;
                int iconRows = (int)Math.ceil((double)iconGroup.iconButtonAL.size() / (double)buttonsInRow);
                height += iconRows * rowHeightInPixels;
            }
            return new Dimension(maxWidth, height);
        }
        public static final int STRUT = 2;
        final IconPopupGallery this$0;
        protected IconGalleryLayout()
        {
            this$0 = IconPopupGallery.this;
        }
    }

    protected class IconPopupLayout
        implements LayoutManager
    {

        public void addLayoutComponent(String s, Component component)
        {
        }

        public void removeLayoutComponent(Component component)
        {
        }

        public void layoutContainer(Container parent)
        {
            Insets insets = getBorder().getBorderInsets(IconPopupGallery.this);
            int left = insets.left;
            int right = insets.right;
            int top = insets.top;
            int bottom = insets.bottom;
            iconSP.setBounds(left, top, parent.getWidth() - left - right, parent.getHeight() - top - bottom);
        }

        public Dimension minimumLayoutSize(Container parent)
        {
            return preferredLayoutSize(parent);
        }

        public Dimension preferredLayoutSize(Container parent)
        {
            Insets insets = getBorder().getBorderInsets(IconPopupGallery.this);
            int left = insets.left;
            int right = insets.right;
            int top = insets.top;
            int bottom = insets.bottom;
            Dimension controlPanelDim = iconPanel.getPreferredSize();
            int w = Math.min(controlPanelDim.width, maxWidth) + left + right;
            int h = Math.min(controlPanelDim.height, maxHeight) + top + bottom;
            if(h == maxHeight + top + bottom)
                w += UIManager.getInt("ScrollBar.width");
            return new Dimension(w, h);
        }
        final IconPopupGallery this$0;
        protected IconPopupLayout()
        {
            this$0 = IconPopupGallery.this;
        }
    }

    public static class IconGroup
    {
        public String getTitle()
        {
            return groupTitle;
        }

        public ZRibbonButton addIcon(ResizableIcon icon, String iconTitle)
        {
            return addIcon(icon, iconTitle, null, iconButtonAL.size());
        }

        public ZRibbonButton addIcon(ResizableIcon icon, String iconTitle, ActionListener actionListener)
        {
            return addIcon(icon, iconTitle, actionListener, iconButtonAL.size());
        }

        public ZRibbonButton addIcon(ResizableIcon icon, String iconTitle, final ActionListener actionListener, int index)
        {
        final ZRibbonButton iconRibbonButton = new ZRibbonButton( null, iconTitle, icon );
        iconRibbonButton.setToggle( true );
        iconRibbonButton.setState( GalleryElementState.ORIG, true );
        if( actionListener != null )
            ((ZRibbonButton) iconRibbonButton).addMouseListener( new MouseListener() {
			    public void mouseClicked(MouseEvent e)
				{
					actionListener.actionPerformed(new ActionEvent(iconRibbonButton,1001,"pressed"));
				}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
			});
        iconButtonAL.add( index, iconRibbonButton );
        gallery.iconPanel.add( (Component) iconRibbonButton );
        gallery.buttonGroup.add( (AbstractButton) iconRibbonButton );
        ((ZRibbonButton) iconRibbonButton).addActionListener( gallery );
        return (ZRibbonButton) iconRibbonButton;
        }

        public void removeIcon(int index)
        {
            ZRibbonButton gb = (ZRibbonButton)iconButtonAL.remove(index);
            gallery.iconPanel.remove(gb);
        }

        private IconPopupGallery gallery;
        private String groupTitle;
        private ArrayList iconButtonAL;
        private IconPopupGalleryTitlePane titlePanel;

        private IconGroup(IconPopupGallery gallery, String groupTitle)
        {
            this.gallery = gallery;
            this.groupTitle = groupTitle;
            iconButtonAL = new ArrayList();
            titlePanel = new IconPopupGalleryTitlePane(this.groupTitle);
        }
    }

    public IconPopupGallery(int maxWidth, int maxHeight, int rowHeightInPixels)
    {
        buttonGroup = new ButtonGroup();
        iconGroupAL = new ArrayList();
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.rowHeightInPixels = rowHeightInPixels;
        setBorder(new LineBorder(new Color(134, 134, 134)));
        setBackground(Color.white);
        iconPanel = new JPanel();
        iconPanel.setOpaque(false);
        iconPanel.setLayout(new IconGalleryLayout());
        iconSP = new JScrollPane(iconPanel, 20, 31);
        iconSP.setBorder(null);
        iconSP.setOpaque(false);
        iconSP.getViewport().setOpaque(false);
        setLayout(new IconPopupLayout());
        setMaximumSize(new Dimension(this.maxWidth, this.maxHeight));
        add(iconSP);
        invisible = new JToggleButton();
        invisible.setVisible(false);
        buttonGroup.add(invisible);
    }

    public IconGroup createNewGroup(String groupTitle, int index)
    {
        IconGroup group = new IconGroup(this, groupTitle);
        iconGroupAL.add(index, group);
        iconPanel.add(group.titlePanel);
        return group;
    }

    public void removeIconGroup(int index)
    {
        IconGroup group = (IconGroup)iconGroupAL.remove(index);
        remove(group.titlePanel);
        ZRibbonButton gb;
        for(Iterator i$ = group.iconButtonAL.iterator(); i$.hasNext(); remove(gb))
            gb = (ZRibbonButton)i$.next();
    }

    public boolean isEmpty()
    {
        return false;
    }

    public void setSelectedButton(ZRibbonButton selectedButton)
    {
        if(selectedButton != null)
            selectedButton.setSelected(true);
        else
            invisible.setSelected(true);
        this.selectedButton = selectedButton;
        repaint();
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() instanceof ZRibbonButton)
        {
            ((FSXPRibbonButtonUI)((ZRibbonButton)event.getSource()).getUI()).setUnderMouse(false);
            hidePopups(null);
        }
    }

    protected ArrayList iconGroupAL;
    protected JPanel iconPanel;
    protected JScrollPane iconSP;
    protected JToggleButton invisible;
    protected ButtonGroup buttonGroup;
    protected ZRibbonButton selectedButton;
    protected int maxWidth;
    protected int maxHeight;
    protected int rowHeightInPixels;
}