package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.engine.CursorEngine;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.model.AirportListModel;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.SelectedItem;
import com.zbluesoftware.fsxp.ui.display.AirportDisplay;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.util.Vector;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class MainDisplay extends JPanel implements InternalFrameListener {

    private MainDisplay()
    {
        init();
    }

    private JDesktopPane desktop;
    private JLabel backgroundLabel;
    private Vector listeners;
    private static MainDisplay display = null;

    public static MainDisplay getInstance()
    {
        if( display == null )
            display = new MainDisplay();
        return display;
    }

    private void init()
    {
        listeners = new Vector();
        setLayout( new BorderLayout() );
        desktop = new JDesktopPane();
        desktop.setBackground( Color.white );
        add( (Component) desktop, "Center" );
    }

    public void addAirportFrame(String ident, String fileName)
    {
        AirportListModel.getInstance().setAirportFrame( ident, fileName, new AirportFrame( AirportListModel.getInstance().getAirportModel( ident, fileName ) ) );
        showLayoutFrame( AirportListModel.getInstance().getAirportFrame( ident, fileName ), true );
    }

    public void showAirportFrame(JInternalFrame frame)
    {
        showLayoutFrame( frame, true );
    }

    public void showAirportFrame(String ident)
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i = frames.length - 1;

        while( i >= 0 )
        {
            if( frames[i] instanceof AirportFrame && frames[i].getTitle().endsWith( new StringBuilder().append( "[" ).append( ident ).append( "]" ).toString() ) )
            {
                showLayoutFrame( frames[i], true );
                break;
            }
            else
                --i;
        }
    }

    private void showLayoutFrame(JInternalFrame frame, boolean maximized)
    {
        int found = 0;
        JInternalFrame[] frames = desktop.getAllFrames();
        int i = frames.length - 1;

        while( i >= 0 )
        {
            if( frames[i] == frame )
            {
                found = 1;
                break;
            }
            else
                --i;
        }
        if( found == 0 )
        {
            frame.setVisible( true );
            frame.setLocation( 0, 0 );
            desktop.add( (Component) frame );
            frame.addInternalFrameListener( this );
            if( frame.getSize().width < frame.getMinimumSize().width )
                frame.setSize( frame.getMinimumSize().width, frame.getSize().height );
            if( frame.getSize().height < frame.getMinimumSize().height )
                frame.setSize( frame.getSize().width, frame.getMinimumSize().height );
            if( desktop.getSize().width < frame.getSize().width )
            {
                frame.setSize( Math.max( desktop.getSize().width, frame.getMinimumSize().width ), frame.getSize().height );
                frame.setLocation( 0, frame.getLocation().y );
            }
            if( desktop.getSize().height < frame.getSize().height )
            {
                frame.setSize( frame.getSize().width, Math.max( desktop.getSize().height, frame.getMinimumSize().height ) );
                frame.setLocation( frame.getLocation().x, 0 );
            }
        }
        if( !frame.isVisible() )
            frame.setVisible( true );
        desktop.setSelectedFrame( frame );
        frame.moveToFront();
        try
        {
            frame.setSelected( true );
            if( maximized )
                frame.setMaximum( true );
        }
        catch( PropertyVetoException propertyvetoexception1 )
        {
            LogEngine.getInstance().log( (Throwable) propertyvetoexception1 );
        }
        for( i = frames.length - 1; i >= 0; --i )
        {
            if( frames[i] != frame )
            {
                try
                {
                    frames[i].setSelected( false );
                }
                catch( PropertyVetoException pve )
                {
                    LogEngine.getInstance().log( (Throwable) pve );
                }
            }
        }
        AirportListModel.getInstance().setCurrentAirport( ((AirportFrame) frame).getAirportModel() );
    }

    public void setAirportDisplayCursor(Cursor cursor)
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i;

        for( i = frames.length - 1; i >= 0; --i )
        {
            if( frames[i] instanceof AirportFrame )
            {
                ((AirportFrame) frames[i]).getAirportDisplay().setCursor( cursor );
                ((AirportFrame) frames[i]).getAirportDisplay().resetFlags();
            }
        }
        CursorEngine.getInstance().setCurrentCursor( cursor );
    }

    public void resetAirport()
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i = frames.length - 1;

        while( i >= 0 )
        {
            if( frames[i] instanceof AirportFrame )
            {
                ((AirportFrame) frames[i]).getAirportDisplay().recenter();
                return;
            }
            else
                --i;
        }
    }

    public void repaintSelectedAirport()
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
        {
            ((AirportFrame) frame).getAirportDisplay().setRecreate( true );
            ((AirportFrame) frame).getAirportDisplay().repaint();
        }
    }

    public AirportModel getCurrentAirportModel()
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            return ((AirportFrame) frame).getAirportModel();
        else
            return null;
    }

    public AirportFrame getCurrentAirportFrame()
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            return (AirportFrame) frame;
        else
            return null;
    }

    public void updateAirportDataDisplay(boolean displayed)
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            ((AirportFrame) frame).setAirportDataDisplay( displayed );
    }

    public void deleteSelectedItem()
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            ((AirportFrame) frame).deleteSelectedItem();
    }

    public void pasteItem(BaseModel baseModel)
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            ((AirportFrame) frame).pasteItem( baseModel );
    }

    public void updateObjectDataDisplay(boolean displayed)
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
            ((AirportFrame) frame).setObjectDataDisplay( displayed );
    }

    public void focusOn(BaseModel model)
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i = frames.length - 1;

        while( i >= 0 )
        {
            if( frames[i] instanceof AirportFrame )
            {
                ((AirportFrame) frames[i]).getAirportDisplay().focusOn( model );
                return;
            }
            else
                --i;
        }
    }

    public void setRecreate(boolean recreate)
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i;

        for( i = frames.length - 1; i >= 0; --i )
        {
            if( frames[i] instanceof AirportFrame )
                ((AirportFrame) frames[i]).getAirportDisplay().setRecreate( recreate );
        }
    }

    public void removeCurrentAirport()
    {
        Object frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
        {
            ((AirportFrame) frame).clearDataDisplays();
            desktop.setSelectedFrame( null );
            desktop.remove( (Component) frame );
            ((JInternalFrame) frame).dispose();
            ((JInternalFrame) frame).removeInternalFrameListener( this );
            repaint();
        }
    }

    public void repaintAirport()
    {
        JInternalFrame frame = desktop.getSelectedFrame();

        if( frame != null && frame instanceof AirportFrame )
        {
            ((AirportFrame) frame).getAirportDisplay().setRecreate( true );
            ((AirportFrame) frame).getAirportDisplay().repaint();
        }
    }

    private boolean checkBeforeClosing(JInternalFrame frame)
    {
        String ident;
        String fileName;
        int option;

        if( !( frame instanceof AirportFrame ) )
            return false;
        ident = ((AirportFrame) frame).getAirportModel().getIdent();
        fileName = ((AirportFrame) frame).getAirportModel().getFileName();
        option = JOptionPane.showConfirmDialog( (Component) this, new StringBuilder().append( "Would you like to save " ).append( ident ).append( " before closing?" ).toString(), "Save...", 1 );
        if( option == 0 )
        {
            firePropertyChange( "removeFromView", fileName, ident );
            firePropertyChange( "saveAndCloseAirport", fileName, ident );
            AirportListModel.getInstance().removeAirportFrame( ident, fileName );
            frame.removeInternalFrameListener( this );
            return true;
        }
        else if( option == 1 )
        {
            firePropertyChange( "removeFromView", ident, ident );
            AirportListModel.getInstance().removeAirportModel( ((AirportFrame) frame).getAirportModel() );
            AirportListModel.getInstance().removeAirportFrame( ident, fileName );
            frame.removeInternalFrameListener( this );
            return true;
        }
        else
        {
            AirportListModel.getInstance().removeAirportFrame( ident, fileName );
            addAirportFrame( ident, fileName );
            return false;
        }
    }

    public void checkAllAirports()
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        int i;

        for( i = frames.length - 1; i >= 0; --i )
        {
            if( frames[i] instanceof AirportFrame )
            {
                String fileName;
                int option;
                String s;

                frames[i].moveToFront();
                try
                {
                    frames[i].setSelected( true );
                    frames[i].setMaximum( true );
                }
                catch( PropertyVetoException pve )
                {
                    LogEngine.getInstance().log( (Throwable) pve );
                }
                s = ((AirportFrame) frames[i]).getAirportModel().getIdent();
                fileName = ((AirportFrame) frames[i]).getAirportModel().getFileName();
                option = JOptionPane.showConfirmDialog( (Component) this, new StringBuilder().append( "Would you like to save " ).append( s ).append( " before closing?" ).toString(), "Save...", 0 );
                if( option == 0 )
                {
                    firePropertyChange( "removeFromView", fileName, s );
                    firePropertyChange( "saveAndCloseAirport", fileName, s );
                    AirportListModel.getInstance().removeAirportFrame( s, fileName );
                    frames[i].removeInternalFrameListener( this );
                }
                else if( option == 1 )
                {
                    firePropertyChange( "removeFromView", s, s );
                    AirportListModel.getInstance().removeAirportModel( ((AirportFrame) frames[i]).getAirportModel() );
                    AirportListModel.getInstance().removeAirportFrame( s, fileName );
                    frames[i].removeInternalFrameListener( this );
                }
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        if( !listeners.contains( listener ) )
            listeners.add( listener );
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listeners.remove( listener );
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        super.firePropertyChange( propertyName, oldValue, newValue );
        if( listeners != null )
        {
            Object event = this;
            Vector list;

            synchronized( event )
            {
                list = (Vector) listeners.clone();
            }
            if( list.size() != 0 )
            {
                int i;

                event = new PropertyChangeEvent( this, propertyName, oldValue, newValue );
                for( i = list.size() - 1; i >= 0; --i )
                    ((PropertyChangeListener) list.elementAt( i )).propertyChange( (PropertyChangeEvent) event );
            }
        }
    }

    public void internalFrameActivated(InternalFrameEvent event)
    {
        if( event.getInternalFrame() instanceof AirportFrame )
        {
            SelectedItem.getInstance().setPropertyChangeListener( (AirportFrame) event.getInternalFrame() );
            AirportListModel.getInstance().setCurrentAirport( ((AirportFrame) event.getInternalFrame()).getAirportModel() );
        }
        if( desktop.getSelectedFrame() != null )
            firePropertyChange( "enableMenuOptions", "", "" );
    }

    public void internalFrameClosed(InternalFrameEvent event)
    {
        if( event.getInternalFrame() instanceof AirportFrame )
        {
            SelectedItem.getInstance().removePropertyChangeListener( (AirportFrame) event.getInternalFrame() );
            AirportListModel.getInstance().setCurrentAirport( null );
        }
        if( desktop.getSelectedFrame() == null )
            firePropertyChange( "disableMenuOptions", "", "" );
    }

    public void internalFrameClosing(InternalFrameEvent event)
    {
        if( event.getInternalFrame() instanceof AirportFrame )
        {
            SelectedItem.getInstance().removePropertyChangeListener( (AirportFrame) event.getInternalFrame() );
            AirportListModel.getInstance().setCurrentAirport( null );
        }
        if( checkBeforeClosing( event.getInternalFrame() ) )
            firePropertyChange( "disableMenuOptions", "", "" );
    }

    public void internalFrameDeactivated(InternalFrameEvent event)
    {
    }

    public void internalFrameDeiconified(InternalFrameEvent event)
    {
        if( event.getInternalFrame() instanceof AirportFrame )
        {
            SelectedItem.getInstance().setPropertyChangeListener( (AirportFrame) event.getInternalFrame() );
            AirportListModel.getInstance().setCurrentAirport( ((AirportFrame) event.getInternalFrame()).getAirportModel() );
        }
        if( desktop.getSelectedFrame() != null )
            firePropertyChange( "enableMenuOptions", "", "" );
    }

    public void internalFrameIconified(InternalFrameEvent event)
    {
        if( event.getInternalFrame() instanceof AirportFrame )
        {
            SelectedItem.getInstance().removePropertyChangeListener( (AirportFrame) event.getInternalFrame() );
            AirportListModel.getInstance().setCurrentAirport( null );
        }
        if( desktop.getSelectedFrame() == null )
            firePropertyChange( "disableMenuOptions", "", "" );
    }

    public void internalFrameOpened(InternalFrameEvent event)
    {
    }
}