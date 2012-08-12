package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.menu.HistoryPopupMenu;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HistoryItem extends JPanel implements MouseListener {

    public HistoryItem()
    {
        setLayout( new GridBagLayout() );
        setBackground( Color.lightGray );
        changeLabel = new JLabel( "Change:" );
        changeLabel.setFont( Utilities.LABEL_FONT );
        changeLabel.setForeground( Color.black );
        changeLabel.setToolTipText( "A description of the change" );
        changeLabel.setEnabled( false );
        changeLabel.addMouseListener( this );
        changeDataLabel = new JLabel( BLANK_ITEM );
        changeDataLabel.setFont( Utilities.LABEL_FONT );
        changeDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        changeDataLabel.setEnabled( false );
        changeDataLabel.addMouseListener( this );
        objectLabel = new JLabel( "Object:" );
        objectLabel.setFont( Utilities.LABEL_FONT );
        objectLabel.setForeground( Color.black );
        objectLabel.setToolTipText( "The object the change applies to" );
        objectLabel.setEnabled( false );
        objectLabel.addMouseListener( this );
        objectDataLabel = new JLabel( BLANK_ITEM );
        objectDataLabel.setFont( Utilities.LABEL_FONT );
        objectDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        objectDataLabel.setEnabled( false );
        objectDataLabel.addMouseListener( this );
        airportLabel = new JLabel( "Airport:" );
        airportLabel.setFont( Utilities.LABEL_FONT );
        airportLabel.setForeground( Color.black );
        airportLabel.setToolTipText( "The airport the change applies to" );
        airportLabel.setEnabled( false );
        airportLabel.addMouseListener( this );
        airportDataLabel = new JLabel( BLANK_ITEM );
        airportDataLabel.setFont( Utilities.LABEL_FONT );
        airportDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        airportDataLabel.setEnabled( false );
        airportDataLabel.addMouseListener( this );
        timeLabel = new JLabel( "Time:" );
        timeLabel.setFont( Utilities.LABEL_FONT );
        timeLabel.setForeground( Color.black );
        timeLabel.setToolTipText( "The time the change occured" );
        timeLabel.setEnabled( false );
        timeLabel.addMouseListener( this );
        timeDataLabel = new JLabel( BLANK_ITEM );
        timeDataLabel.setFont( Utilities.LABEL_FONT );
        timeDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        timeDataLabel.setEnabled( false );
        timeDataLabel.addMouseListener( this );
        Utilities.addComponent( (Container) this, (Component) changeLabel, 0, 0, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) this, (Component) changeDataLabel, 1, 0, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) this, (Component) objectLabel, 0, 1, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) this, (Component) objectDataLabel, 1, 1, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) this, (Component) airportLabel, 0, 2, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) this, (Component) airportDataLabel, 1, 2, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) this, (Component) timeLabel, 0, 3, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) this, (Component) timeDataLabel, 1, 3, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        addMouseListener( this );
    }

    private JLabel changeDataLabel;
    private JLabel objectDataLabel;
    private JLabel airportDataLabel;
    private JLabel timeDataLabel;
    private JLabel changeLabel;
    private JLabel objectLabel;
    private JLabel airportLabel;
    private JLabel timeLabel;
    public static String BLANK_ITEM = "----";
    public static Color HIGHLIGHT_COLOR = new Color( 255, 255, 204 );
    private Vector listeners = new Vector();
    private boolean colorChange = true;

    public void setChangeData(String text)
    {
        changeDataLabel.setText( text );
    }

    public void setObjectData(String text)
    {
        objectDataLabel.setText( text );
    }

    public void setAirportData(String text)
    {
        airportDataLabel.setText( text );
    }

    public void setTimeData(String text)
    {
        timeDataLabel.setText( text );
    }

    public void setLabelsEnabled(boolean state)
    {
        changeDataLabel.setEnabled( state );
        objectDataLabel.setEnabled( state );
        airportDataLabel.setEnabled( state );
        timeDataLabel.setEnabled( state );
        changeLabel.setEnabled( state );
        objectLabel.setEnabled( state );
        airportLabel.setEnabled( state );
        timeLabel.setEnabled( state );
        if( !state )
            setBackground( Color.lightGray );
    }

    private void displayPopup(Component component, Point point)
    {
        if( changeLabel.isEnabled() )
        {
            colorChange = false;
            firePropertyChange( "setTrigger", "", "" );
            HistoryPopupMenu.getInstance().show( component, point.x, point.y );
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

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue)
    {
        Vector list;
        Object event;
        int i;

        super.firePropertyChange( propertyName, oldValue, newValue );
        event = this;
label_15:
        {
            synchronized( event )
            {
                if( listeners != null )
                    break label_15;
            }
            return;
        }
        try
        {
            list = (Vector) listeners.clone();
        }
        finally
        {
        }
        if( list.size() != 0 )
        {
            event = new PropertyChangeEvent( this, propertyName, oldValue, newValue );
            i = list.size() - 1;
            while( i >= 0 )
            {
                ((PropertyChangeListener) list.elementAt( i )).propertyChange( (PropertyChangeEvent) event );
                i = i - 1;
            }
        }
    }

    public void mouseClicked(MouseEvent event)
    {
        if( event.getClickCount() == 2 )
            firePropertyChange( "displayDetails", "", "" );
    }

    public void mousePressed(MouseEvent event)
    {
        if( event.isPopupTrigger() )
            displayPopup( (Component) event.getSource(), event.getPoint() );
    }

    public void mouseReleased(MouseEvent event)
    {
        if( event.isPopupTrigger() )
            displayPopup( (Component) event.getSource(), event.getPoint() );
    }

    public void mouseEntered(MouseEvent event)
    {
        if( colorChange && changeLabel.isEnabled() )
            setBackground( HIGHLIGHT_COLOR );
    }

    public void mouseExited(MouseEvent event)
    {
        if( colorChange && changeLabel.isEnabled() )
            setBackground( Color.lightGray );
        else if( changeLabel.isEnabled() )
            colorChange = true;
    }
}