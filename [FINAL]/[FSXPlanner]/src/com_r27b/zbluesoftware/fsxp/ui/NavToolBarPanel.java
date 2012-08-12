// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NavToolBarPanel.java

package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

// Referenced classes of package com.zbluesoftware.fsxp.ui:
//            WindowBorder, ToolbarButtonGroup

public class NavToolBarPanel extends JPanel
{

    public NavToolBarPanel()
    {
        setLayout(new GridBagLayout());
        setBackground(Utilities.LIGHT_BG_COLOR);
        setBorder(new EtchedBorder());
        JToolBar toolbar = new JToolBar();
        toolbar.setOpaque(false);
        toolbar.setBorder(new EmptyBorder(0, 0, 0, 0));
        toolbar.setFloatable(false);
        JToggleButton ilsButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(111)));
        ilsButton.setOpaque(false);
        ilsButton.setText("");
        ilsButton.setToolTipText("Insert ILS");
        ilsButton.setRolloverIcon(IconFactory.getInstance().getIcon("ilsBeamOver"));
        ilsButton.setSelectedIcon(IconFactory.getInstance().getIcon("ilsBeamOn"));
        toolbar.add(ilsButton);
        JToggleButton markerButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(112)));
        markerButton.setOpaque(false);
        markerButton.setText("");
        markerButton.setToolTipText("Insert Marker");
        markerButton.setRolloverIcon(IconFactory.getInstance().getIcon("markerOver"));
        markerButton.setSelectedIcon(IconFactory.getInstance().getIcon("markerOn"));
        toolbar.add(markerButton);
        JToggleButton vorButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(113)));
        vorButton.setOpaque(false);
        vorButton.setText("");
        vorButton.setToolTipText("Insert VOR");
        vorButton.setRolloverIcon(IconFactory.getInstance().getIcon("vorOver"));
        vorButton.setSelectedIcon(IconFactory.getInstance().getIcon("vorOn"));
        toolbar.add(vorButton);
        JToggleButton ndbButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(114)));
        ndbButton.setOpaque(false);
        ndbButton.setText("");
        ndbButton.setToolTipText("Insert NDB");
        ndbButton.setRolloverIcon(IconFactory.getInstance().getIcon("ndbOver"));
        ndbButton.setSelectedIcon(IconFactory.getInstance().getIcon("ndbOn"));
        toolbar.add(ndbButton);
        JToggleButton windsockButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(115)));
        windsockButton.setOpaque(false);
        windsockButton.setText("");
        windsockButton.setToolTipText("Insert Windsock");
        windsockButton.setRolloverIcon(IconFactory.getInstance().getIcon("windsockOver"));
        windsockButton.setSelectedIcon(IconFactory.getInstance().getIcon("windsockOn"));
        toolbar.add(windsockButton);
        toolbar.addSeparator();
        JToggleButton fuelButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(117)));
        fuelButton.setOpaque(false);
        fuelButton.setText("");
        fuelButton.setToolTipText("Insert Fuel Trigger");
        fuelButton.setRolloverIcon(IconFactory.getInstance().getIcon("fuelOver"));
        fuelButton.setSelectedIcon(IconFactory.getInstance().getIcon("fuelOn"));
        toolbar.add(fuelButton);
        JToggleButton sceneryButton = new JToggleButton(MenuFactory.getInstance().getMenuAction(new Integer(118)));
        sceneryButton.setOpaque(false);
        sceneryButton.setText("");
        sceneryButton.setToolTipText("Insert Scenery");
        sceneryButton.setRolloverIcon(IconFactory.getInstance().getIcon("sceneryOver"));
        sceneryButton.setSelectedIcon(IconFactory.getInstance().getIcon("sceneryOn"));
        toolbar.add(sceneryButton);
        ToolbarButtonGroup.getInstance().add(ilsButton);
        ToolbarButtonGroup.getInstance().add(markerButton);
        ToolbarButtonGroup.getInstance().add(vorButton);
        ToolbarButtonGroup.getInstance().add(ndbButton);
        ToolbarButtonGroup.getInstance().add(windsockButton);
        ToolbarButtonGroup.getInstance().add(fuelButton);
        ToolbarButtonGroup.getInstance().add(sceneryButton);
        Utilities.addComponent(this, toolbar, 0, 0, 1, 1, 0, 17, new Insets(5, 5, 5, 1), 0, 0, 0.0D, 0.0D);
        windowBorder = new WindowBorder("Navigation Tools");
        windowBorder.setTitleBarColor(Utilities.LIGHT_BG_COLOR);
        windowBorder.setResizeBoxShown(false);
        setBorder(windowBorder);
    }

    public WindowBorder getWindowBorder()
    {
        return windowBorder;
    }

    private WindowBorder windowBorder;
}
