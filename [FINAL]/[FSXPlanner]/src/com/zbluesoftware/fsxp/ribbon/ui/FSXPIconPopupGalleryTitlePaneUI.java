// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   FSXPIconPopupGalleryTitlePaneUI.java

package com.zbluesoftware.fsxp.ribbon.ui;

import com.zbluesoftware.fsxp.ribbon.IconPopupGalleryTitlePane;
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

// Referenced classes of package com.zbluesoftware.fsxp.ribbon.ui:
//            ZIconPopupGalleryTitlePaneUI

public class FSXPIconPopupGalleryTitlePaneUI extends ZIconPopupGalleryTitlePaneUI
{

    public FSXPIconPopupGalleryTitlePaneUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new FSXPIconPopupGalleryTitlePaneUI();
    }

    public void installUI(JComponent c)
    {
        iconPopupGalleryTitlePane = (IconPopupGalleryTitlePane)c;
        installDefaults();
        installComponents();
        installListeners();
        c.setLayout(createLayoutManager());
    }

    public void uninstallUI(JComponent c)
    {
        uninstallListeners();
        uninstallComponents();
        uninstallDefaults();
        c.setLayout(null);
        iconPopupGalleryTitlePane = null;
    }

    protected void installListeners()
    {
    }

    protected void uninstallListeners()
    {
    }

    protected void installComponents()
    {
        titleLabel = new JLabel(iconPopupGalleryTitlePane.getTitle());
        titleLabel.setFont(new Font("Tahoma", 1, 11));
        titleLabel.setForeground(new Color(0, 21, 110));
        iconPopupGalleryTitlePane.add(titleLabel);
    }

    protected void uninstallComponents()
    {
        iconPopupGalleryTitlePane.remove(titleLabel);
    }

    protected void installDefaults()
    {
        iconPopupGalleryTitlePane.setBackground(UIManager.getColor("ZRibbonBand.background").darker());
        iconPopupGalleryTitlePane.setBorder(null);
    }

    protected void uninstallDefaults()
    {
    }

    protected LayoutManager createLayoutManager()
    {
        return new FlowLayout(0);
    }

    public void paint(Graphics g, JComponent c)
    {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        paintPanelBackground(g2, new Rectangle(0, 0, c.getWidth(), c.getHeight()));
        g2.dispose();
    }

    protected void paintPanelBackground(Graphics2D g2, Rectangle toFill)
    {
        g2.setPaint(new Color(221, 231, 238));
        g2.fillRect(toFill.x, toFill.y, toFill.width, toFill.height);
        g2.setPaint(new Color(197, 197, 197));
        g2.draw(new java.awt.geom.Line2D.Float(toFill.x, (toFill.y + toFill.height) - 2, toFill.x + toFill.width, (toFill.y + toFill.height) - 2));
        g2.setPaint(new Color(245, 245, 245));
        g2.draw(new java.awt.geom.Line2D.Float(toFill.x, (toFill.y + toFill.height) - 1, toFill.x + toFill.width, (toFill.y + toFill.height) - 1));
    }

    protected IconPopupGalleryTitlePane iconPopupGalleryTitlePane;
    protected JLabel titleLabel;
}