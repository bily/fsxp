package com.zbluesoftware.fsxp.ribbon;

import com.zbluesoftware.fsxp.graphics.RibbonIcon;
import com.zbluesoftware.fsxp.ribbon.ui.FSXPRibbonButtonUI;
import com.zbluesoftware.fsxp.ribbon.ui.ZRibbonButtonUI;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.DefaultButtonModel;
import javax.swing.JToggleButton;
import javax.swing.JToolTip;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ZRibbonButton extends AbstractButton implements ActionListener {

    public ZRibbonButton(ZPopupGallery gallery, String title, ResizableIcon icon)
    {
        this.gallery = gallery;
        this.title = title;
        this.icon = icon;
        popupDisplayed = false;
        displayX = false;
        grouped = false;
        buttonSelected = false;
        disabledIcon = icon;
        setModel( (ButtonModel) new DefaultButtonModel() );
        customDimension = -1;
        updateUI();
        addGalleryMouseListener();
        addActionListener( this );
        if( gallery != null )
            gallery.setActionListener( this );
    }

    protected ResizableIcon icon;
    protected ResizableIcon disabledIcon;
    protected String title;
    protected ZPopupGallery gallery;
    protected GalleryElementState state;
    protected int customDimension;
    protected boolean popupDisplayed;
    private boolean displayX;
    private boolean grouped;
    private boolean buttonSelected;
    private static final String uiClassID = "ZRibbonButtonUI";


    public ZRibbonButton(String title, Image icon)
    {
        this( null, title, (ResizableIcon) new RibbonIcon( icon ) );
    }

    public ZRibbonButton(String title, ResizableIcon icon)
    {
        this( null, title, icon );
    }

  protected void addGalleryMouseListener()
  {
    addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        final ZRibbonButton jgb = ZRibbonButton.this;
        if (!(jgb.isEnabled()))
          return;
        ZPopupGallery.hidePopups(jgb);
        if (jgb.actionListener != null)
          SwingUtilities.invokeLater(new Runnable()
          {
            public void run()
            {
              jgb.actionListener.actionPerformed(new ActionEvent(jgb, 1001, "pressed"));
            }
          });
        SwingUtilities.invokeLater(new Runnable()
        {
          public void run()
          {
            PopupFactory popupFactory = PopupFactory.getSharedInstance();
            if ((jgb == null) || (jgb.gallery == null) || (jgb.gallery.isEmpty()))
              return;
            int x = jgb.getLocationOnScreen().x;
            int y = jgb.getLocationOnScreen().y + jgb.getSize().height;
            Rectangle scrBounds = Utilities.MAIN_FRAME.getBounds();
            int pw = jgb.gallery.getPreferredSize().width;
            if (x + pw > scrBounds.x + scrBounds.width)
              x = scrBounds.x + scrBounds.width - pw;
            int ph = jgb.gallery.getPreferredSize().height;
            if (y + ph > scrBounds.y + scrBounds.height)
              y = scrBounds.y + scrBounds.height - ph;
            setPopupDisplayed(true);
            Popup popup = popupFactory.getPopup(jgb, jgb.gallery, x, y);
            popup.show();
            ZPopupGallery.addPopup(popup, jgb.gallery);
          }
        });
      }
    });
  }
  
    public boolean isGrouped()
    {
        return grouped;
    }

    public void setGrouped(boolean grouped)
    {
        this.grouped = grouped;
    }

    public boolean isButtonSelected()
    {
        return buttonSelected;
    }

    public void setButtonSelected(boolean buttonSelected)
    {
        this.buttonSelected = buttonSelected;
    }

    public void setDisplayX(boolean displayX)
    {
        this.displayX = displayX;
    }

    public void setPopupDisplayed(boolean popupDisplayed)
    {
        if( this.popupDisplayed != popupDisplayed )
        {
            this.popupDisplayed = popupDisplayed;
            if( !popupDisplayed )
                repaint();
        }
    }

    public boolean isPopupDisplayed()
    {
        return popupDisplayed;
    }



    public void setUI(ZRibbonButtonUI ui)
    {
        super.setUI( ui );
    }



    public void updateUI()
    {
        if( UIManager.get( getUIClassID() ) != null )
            setUI( (ZRibbonButtonUI) UIManager.getUI( this ) );
        else
            setUI( (ZRibbonButtonUI) new FSXPRibbonButtonUI() );
    }

    public ZRibbonButtonUI getUI()
    {
        return (ZRibbonButtonUI) ui;
    }

    public String getUIClassID()
    {
        return "ZRibbonButtonUI";
    }


    public void setState(GalleryElementState state, boolean toUpdateIcon)
    {
        this.state = state;
        getUI().updateState( state, toUpdateIcon );
    }



    public JToolTip createToolTip()
    {
        Object tip = new ZToolTip();

        ((ZToolTip) tip).setComponent( this );
        return (JToolTip) tip;
    }


    public Point getToolTipLocation(MouseEvent event)
    {
        int y = 88 - (getLocationOnScreen().y - getParent().getLocationOnScreen().y);

        return new Point( 0, y );
    }

    public void setToolTipTitle(String title)
    {
        putClientProperty( "ToolTipTitle", title );
    }

    public void setToolTipSize(Dimension size)
    {
        putClientProperty( "ToolTipSize", size );
    }

    public void addToolTipText(String text)
    {
        String newText = new StringBuilder().append( getClientProperty( "ToolTipText" ) ).append( text ).toString();

        putClientProperty( "ToolTipText", newText );
    }

    public ZPopupGallery getGallery()
    {
        return gallery;
    }

    public ResizableIcon getIcon()
    {
        return icon;
    }

    public ResizableIcon getDisabledIcon()
    {
        return disabledIcon;
    }

    public GalleryElementState getState()
    {
        return state;
    }

    public String getTitle()
    {
        return title;
    }

    public void setGallery(ZPopupGallery gallery)
    {
        getUI().updateGallery( gallery );
        if( gallery != null )
            gallery.setActionListener( null );
        this.gallery = gallery;
        if( gallery != null )
            gallery.setActionListener( this );
    }

    public void setActionListener(ActionListener actionListener)
    {
        this.actionListener = actionListener;
    }

    public void setEnabled(boolean isEnabled)
    {
        int i = 0;

        super.setEnabled( isEnabled );
        for( i = 0; i < getComponentCount(); ++i )
            getComponent( i ).setEnabled( isEnabled );
        model.setEnabled( isEnabled );
    }

    public boolean isToggle()
    {
        return getModel() instanceof JToggleButton.ToggleButtonModel;
    }


    public void setToggle(boolean isToggle)
    {
        if( isToggle )
            setModel( (ButtonModel) new JToggleButton.ToggleButtonModel() );
        else
            setModel( (ButtonModel) new DefaultButtonModel() );
        repaint();
    }

    public void updateCustomDimension(int dimension)
    {
        customDimension = dimension;
        getUI().updateCustomDimension();
    }

    public int getCustomDimension()
    {
        return customDimension;
    }


    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == gallery && gallery != null )
        {
            if( event.getActionCommand().equals( "Hidden" ) )
                setPopupDisplayed( false );
        }
        else if( event.getSource() == this )
        {
            setSelected( true );
            if( displayX )
            {
                icon.setDrawX( (!icon.getDrawX()) ? true : false );
                repaint();
            }
        }
    }
/*
    static ActionListener access$000(ZRibbonButton x0)
    {
        return x0.actionListener;
    }

    static ActionListener access$100(ZRibbonButton x0)
    {
        return x0.actionListener;
    }
*/
}