// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ColorPanel.java

package com.zbluesoftware.fsxp.startup;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.model.table.ColorPrefsTableModel;
import com.zbluesoftware.fsxp.renderer.ColorPrefsTableRenderer;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.zbluesoftware.fsxp.startup:
//            StartupPanel

public class ColorPanel extends JPanel
    implements MouseListener, StartupPanel
{

    public ColorPanel()
    {
        setBackground(Utilities.LIGHT_BG_COLOR);
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new LineBorder(Color.black), new EmptyBorder(5, 5, 5, 5)));
        JLabel welcomeLabel = new JLabel("Color Preferences");
        welcomeLabel.setFont(Utilities.LARGE_LABEL_FONT);
        welcomeLabel.setForeground(Color.black);
        JLabel clickLabel = new JLabel("Double click a color to change it.");
        clickLabel.setFont(Utilities.LABEL_FONT);
        clickLabel.setForeground(Color.black);
        colorTable = new JTable(new ColorPrefsTableModel());
        colorTable.setPreferredScrollableViewportSize(new Dimension(200, 200));
        colorTable.setDefaultRenderer(java.awt.Color.class, new ColorPrefsTableRenderer());
        colorTable.setRowHeight(30);
        colorTable.setIntercellSpacing(new Dimension(5, 5));
        colorTable.addMouseListener(this);
        JScrollPane colorSP = new JScrollPane(colorTable);
        JLabel settingsLabel = new JLabel("(These can be set by using the Color tab of the Preferences Dialog.)");
        settingsLabel.setFont(Utilities.LABEL_FONT);
        settingsLabel.setForeground(Color.darkGray);
        JLabel settings2Label = new JLabel("(File -> Preferences)");
        settings2Label.setFont(Utilities.LABEL_FONT);
        settings2Label.setForeground(Color.darkGray);
        totalLabel = new JLabel("1 of 1");
        totalLabel.setFont(Utilities.LABEL_FONT);
        totalLabel.setForeground(Color.gray);
        Utilities.addComponent(this, welcomeLabel, 0, 0, 1, 1, 0, 10, new Insets(1, 1, 20, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, clickLabel, 0, 1, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, colorSP, 0, 2, 1, 1, 1, 10, new Insets(5, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, settingsLabel, 0, 3, 1, 1, 0, 10, new Insets(20, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, settings2Label, 0, 4, 1, 1, 0, 10, new Insets(1, 1, 1, 1), 0, 0, 0.0D, 0.0D);
        Utilities.addComponent(this, Box.createGlue(), 0, 5, 1, 1, 1, 10, new Insets(1, 1, 1, 1), 0, 0, 0.5D, 0.5D);
        Utilities.addComponent(this, totalLabel, 0, 6, 1, 1, 0, 13, new Insets(10, 1, 1, 1), 0, 0, 0.0D, 0.0D);
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void recordSettings()
    {
        ColorsEngine.getInstance().setSurfaceColor("ASPHALT", (Color)colorTable.getValueAt(0, 1));
        ColorsEngine.getInstance().setSurfaceColor("BITUMINOUS", (Color)colorTable.getValueAt(1, 1));
        ColorsEngine.getInstance().setSurfaceColor("BRICK", (Color)colorTable.getValueAt(2, 1));
        ColorsEngine.getInstance().setSurfaceColor("CLAY", (Color)colorTable.getValueAt(3, 1));
        ColorsEngine.getInstance().setSurfaceColor("CEMENT", (Color)colorTable.getValueAt(4, 1));
        ColorsEngine.getInstance().setSurfaceColor("CONCRETE", (Color)colorTable.getValueAt(5, 1));
        ColorsEngine.getInstance().setSurfaceColor("CORAL", (Color)colorTable.getValueAt(6, 1));
        ColorsEngine.getInstance().setSurfaceColor("DIRT", (Color)colorTable.getValueAt(7, 1));
        ColorsEngine.getInstance().setSurfaceColor("GRASS", (Color)colorTable.getValueAt(8, 1));
        ColorsEngine.getInstance().setSurfaceColor("GRAVEL", (Color)colorTable.getValueAt(9, 1));
        ColorsEngine.getInstance().setSurfaceColor("ICE", (Color)colorTable.getValueAt(10, 1));
        ColorsEngine.getInstance().setSurfaceColor("MACADAM", (Color)colorTable.getValueAt(11, 1));
        ColorsEngine.getInstance().setSurfaceColor("OIL_TREATED, PLANKS", (Color)colorTable.getValueAt(12, 1));
        ColorsEngine.getInstance().setSurfaceColor("SAND", (Color)colorTable.getValueAt(13, 1));
        ColorsEngine.getInstance().setSurfaceColor("SHALE", (Color)colorTable.getValueAt(14, 1));
        ColorsEngine.getInstance().setSurfaceColor("SNOW", (Color)colorTable.getValueAt(15, 1));
        ColorsEngine.getInstance().setSurfaceColor("STEEL_MATS", (Color)colorTable.getValueAt(16, 1));
        ColorsEngine.getInstance().setSurfaceColor("TARMAC", (Color)colorTable.getValueAt(17, 1));
        ColorsEngine.getInstance().setSurfaceColor("UNKNOWN", (Color)colorTable.getValueAt(18, 1));
        ColorsEngine.getInstance().setSurfaceColor("WATER", (Color)colorTable.getValueAt(19, 1));
        ColorsEngine.getInstance().setLatLonColor((Color)colorTable.getValueAt(20, 1));
        ColorsEngine.getInstance().setBackgroundColor((Color)colorTable.getValueAt(21, 1));
        ColorsEngine.getInstance().setTaxiwayLineColor((Color)colorTable.getValueAt(22, 1));
        ColorsEngine.getInstance().setILSColor((Color)colorTable.getValueAt(23, 1));
        ColorsEngine.getInstance().setDMEColor((Color)colorTable.getValueAt(24, 1));
        ColorsEngine.getInstance().setGlideSlopeColor((Color)colorTable.getValueAt(25, 1));
        ColorsEngine.getInstance().setExclusionColor((Color)colorTable.getValueAt(26, 1));
        ColorsEngine.getInstance().setTriggerColor((Color)colorTable.getValueAt(27, 1));
        ColorsEngine.getInstance().setSceneryColor((Color)colorTable.getValueAt(28, 1));
        ColorsEngine.getInstance().setSelectedColor((Color)colorTable.getValueAt(29, 1));
        ColorsEngine.getInstance().writePreferences();
    }

    public void setTotalCount(int panelNumber, int totalPanels)
    {
        totalLabel.setText((new StringBuilder()).append(panelNumber).append(" of ").append(totalPanels).toString());
    }

    private void selectColor(Point point)
    {
        int row = colorTable.rowAtPoint(point);
        int column = colorTable.columnAtPoint(point);
        if(row == -1)
            return;
        if(column != 1)
            return;
        Color color = JColorChooser.showDialog(this, "Select Color:", (Color)colorTable.getValueAt(row, column));
        if(color != null)
            ((ColorPrefsTableModel)colorTable.getModel()).setColor((String)colorTable.getValueAt(row, 0), color);
    }

    public void mouseClicked(MouseEvent event)
    {
        if(event.getSource() == colorTable && event.getClickCount() == 2)
            selectColor(event.getPoint());
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    private JLabel totalLabel;
    private JTable colorTable;
}
