// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPButtonStripUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.ZButtonStrip;
import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZButtonStripUI

public class FSXPButtonStripUI extends ZButtonStripUI
{
    private class ButtonStripLayout
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
            int width = 0;
            int height = 0;
            if(buttonStrip.getOrientation() == com.zbluesoftware.fsxp.ribbon.ZButtonStrip.StripOrientation.HORIZONTAL)
            {
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    width += buttonStrip.getButton(i).getPreferredSize().width;
                    height = Math.max(height, buttonStrip.getButton(i).getPreferredSize().height);
                }

            } else
            {
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    height += buttonStrip.getButton(i).getPreferredSize().height;
                    width = Math.max(width, buttonStrip.getButton(i).getPreferredSize().width);
                }

            }
            Insets ins = c.getInsets();
            return new Dimension(width + ins.left + ins.right, height + ins.top + ins.bottom);
        }

        public Dimension minimumLayoutSize(Container c)
        {
            return preferredLayoutSize(c);
        }

        public void layoutContainer(Container c)
        {
            if(buttonStrip.getButtonCount() == 0)
                return;
            Insets ins = c.getInsets();
            int height = c.getHeight() - ins.top - ins.bottom;
            int width = c.getWidth() - ins.left - ins.right;
            if(buttonStrip.getOrientation() == com.zbluesoftware.fsxp.ribbon.ZButtonStrip.StripOrientation.HORIZONTAL)
            {
                int totalPreferredWidth = 0;
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    AbstractButton currButton = buttonStrip.getButton(i);
                    totalPreferredWidth += currButton.getPreferredSize().width;
                }

                int deltaX = (width - totalPreferredWidth) / buttonStrip.getButtonCount();
                int x = ins.left;
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    AbstractButton currButton = buttonStrip.getButton(i);
                    currButton.setBounds(x, ins.top, currButton.getPreferredSize().width + deltaX, height);
                    x += currButton.getPreferredSize().width + deltaX;
                }

            } else
            {
                int totalPreferredHeight = 0;
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    AbstractButton currButton = buttonStrip.getButton(i);
                    totalPreferredHeight += currButton.getPreferredSize().height;
                }

                int deltaY = (height - totalPreferredHeight) / buttonStrip.getButtonCount();
                int y = ins.top;
                for(int i = 0; i < buttonStrip.getButtonCount(); i++)
                {
                    AbstractButton currButton = buttonStrip.getButton(i);
                    currButton.setBounds(ins.left, y, width, currButton.getPreferredSize().height + deltaY);
                    y += currButton.getPreferredSize().height + deltaY;
                }
            }
        }
        final FSXPButtonStripUI this$0;
        private ButtonStripLayout()
        {
            this$0 = FSXPButtonStripUI.this;
        }

    }

    public FSXPButtonStripUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPButtonStripUI();
    }

    public void installUI(JComponent c)
    {
        buttonStrip = (ZButtonStrip)c;
        c.setLayout(createLayoutManager());
        c.setBorder(new EmptyBorder(1, 1, 1, 1));
    }

    public void uninstallUI(JComponent c)
    {
        c.setLayout(null);
        buttonStrip = null;
    }

    public void paint(Graphics g, JComponent c)
    {
        int buttonCount = buttonStrip.getButtonCount();
        Insets insets = c.getInsets();
        for(int i = 0; i < buttonCount; i++)
        {
            AbstractButton currButton = buttonStrip.getButton(i);
            boolean isFirst = buttonStrip.isFirst(currButton);
            boolean isLast = buttonStrip.isLast(currButton);
            Graphics2D g2 = (Graphics2D)g.create(currButton.getX(), currButton.getY(), currButton.getWidth(), currButton.getHeight());
            if(buttonStrip.getOrientation() == com.zbluesoftware.fsxp.ribbon.ZButtonStrip.StripOrientation.HORIZONTAL)
                paintStripButton(g2, currButton, isFirst, isLast, c.getWidth() - insets.left - insets.right, currButton.getX());
            else
                paintStripButton(g2, currButton, isFirst, isLast, c.getHeight() - insets.top - insets.bottom, currButton.getY());
            g2.dispose();
        }

    }

    protected void paintStripButton(Graphics g, AbstractButton button, boolean isFirst, boolean isLast, int totalStripDimension, int relativeOffset)
    {
        paintStripButtonBackground(g, button, isFirst, isLast, totalStripDimension, relativeOffset);
        paintButtonContents(g, button);
        paintStripButtonBorder(g, button, isFirst, isLast);
    }

    protected void paintButtonContents(Graphics g, AbstractButton button)
    {
        button.paint(g);
    }

    protected void paintStripButtonBackground(Graphics g, AbstractButton button, boolean isFirst, boolean isLast, int totalStripDimension, int relativeOffset)
    {
        if(button.isOpaque())
        {
            g.setColor(button.getBackground());
            g.fillRect(0, 0, button.getWidth(), button.getHeight());
        }
    }

    protected void paintStripButtonBorder(Graphics g, AbstractButton button, boolean isFirst, boolean isLast)
    {
        Border border = button.getBorder();
        if(border != null)
            border.paintBorder(button, g, 0, 0, button.getWidth(), button.getHeight());
    }

    protected LayoutManager createLayoutManager()
    {
        return new ButtonStripLayout();
    }

    protected ZButtonStrip buttonStrip;
}