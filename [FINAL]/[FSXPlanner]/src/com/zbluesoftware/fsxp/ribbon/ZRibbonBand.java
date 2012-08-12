package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPRibbonBandUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZRibbonBandUI;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ZRibbonBand extends JComponent
{
  private ActionListener expandActionListener;
  private ZBandControlPanel controlPanel;
  private ResizableIcon icon;
  private String title;
  private static final String uiClassID = "ZRibbonBandUI";
  public ZRibbonBand(String title, ResizableIcon icon)
  {
    this(title, icon, null);
  }
  public ZRibbonBand(String title, ResizableIcon icon, ActionListener expandActionListener)
  {
    this.title = title;
    this.icon = icon;
    this.expandActionListener = expandActionListener;
    this.controlPanel = new ZBandControlPanel(this);
    add(this.controlPanel);
    updateUI();
  }
  public boolean getAllButtonsTopState()
  {
    if (this.controlPanel == null)
      return false;
    return this.controlPanel.getAllButtonsTopState();
  }
  public String getTitle()
  {
    return this.title;
  }
  public void setTitle(String title)
  {
    this.title = title;
  }
  public ResizableIcon getIcon()
  {
    return this.icon;
  }
  public void setUI(ZRibbonBandUI ui)
  {
    super.setUI(ui);
  }
  public void updateUI()
  {
    if (UIManager.get(getUIClassID()) != null)
      setUI((ZRibbonBandUI)UIManager.getUI(this));
    else
      setUI(new FSXPRibbonBandUI());
  }
  public ZRibbonBandUI getUI()
  {
    return ((ZRibbonBandUI)this.ui);
  }
  public String getUIClassID()
  {
    return "ZRibbonBandUI";
  }
  public void addGalleryButton(ZRibbonButton ribbonButton, RibbonElementPriority priority)
  {
    this.controlPanel.addGalleryButton(ribbonButton, priority);
  }
  public void addRibbonGallery(ZRibbonGallery ribbonGallery, RibbonElementPriority priority)
  {
    this.controlPanel.addRibbonGallery(ribbonGallery, priority);
  }
  public void addPanel(JPanel panel)
  {
    this.controlPanel.addPanel(panel);
  }
  public ActionListener getExpandActionListener()
  {
    return this.expandActionListener;
  }
  public ZBandControlPanel getControlPanel()
  {
    return this.controlPanel;
  }
  public void setControlPanel(ZBandControlPanel controlPanel)
  {
    if (controlPanel == null)
      remove(this.controlPanel);
    else
      add(controlPanel);
    this.controlPanel = controlPanel;
  }
  public static enum RibbonBandCollapseKind
  {
    NONE, LOW_TO_MID, MID_TO_MID, LOW_TO_LOW, MID_TO_LOW, HIGH_TO_MID, HIGH_TO_LOW, ICON;
    private int priority;
    private static LinkedList<RibbonBandCollapseKind> sortedKinds;
    public static LinkedList<RibbonBandCollapseKind> getSortedKinds()
    {
      return sortedKinds;
    }
    static
    {
      sortedKinds = new LinkedList();
      RibbonBandCollapseKind[] arr$ = values();
      int len$ = arr$.length;
      int i$ = 0;
      while (i$ < len$)
      {
        RibbonBandCollapseKind kind = arr$[i$];
        sortedKinds.add(kind);
        ++i$;
      }
      Collections.sort(sortedKinds, new Comparator()
      {
                public int compare(RibbonBandCollapseKind o1, RibbonBandCollapseKind o2)
                {
                    return o1.priority - o2.priority;
                }
                public int compare(Object x0, Object x1)
                {
                    return compare((RibbonBandCollapseKind)x0, (RibbonBandCollapseKind)x1);
                }
      });
	}
  }
  public static enum RibbonElementPriority
  {
    TOP, MEDIUM, LOW;
  }
}