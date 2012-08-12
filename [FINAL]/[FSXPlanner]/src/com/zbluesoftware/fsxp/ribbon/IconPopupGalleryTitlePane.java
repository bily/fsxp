package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.ribbon.ui.FSXPIconPopupGalleryTitlePaneUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZIconPopupGalleryTitlePaneUI;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class IconPopupGalleryTitlePane extends JPanel
{
  protected String title;
  private static final String uiClassID = "ZIconPopupGalleryTitlePaneUI";

  public IconPopupGalleryTitlePane(String title)
  {
    this.title = title;
    updateUI();
  }
  public String getTitle()
  {
    return this.title;
  }
  public void updateUI()
  {
    if (UIManager.get(getUIClassID()) != null)
      setUI((ZIconPopupGalleryTitlePaneUI)UIManager.getUI(this));
    else
      setUI(new FSXPIconPopupGalleryTitlePaneUI());
  }
  public ZIconPopupGalleryTitlePaneUI getUI()
  {
    return ((ZIconPopupGalleryTitlePaneUI)this.ui);
  }
  public void setUI(ZIconPopupGalleryTitlePaneUI ui)
  {
    super.setUI(ui);
  }
  public String getUIClassID()
  {
    return "ZIconPopupGalleryTitlePaneUI";
  }
}