package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.HistoryListModel;
import com.zbluesoftware.fsxp.model.HistoryModel;
import com.zbluesoftware.fsxp.model.table.HistoryTableModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

public class HistoryDetailsDialog extends JDialog implements ActionListener, ListSelectionListener {

    private HistoryDetailsDialog(Frame frame, HistoryModel historyModel)
    {
        super( frame, "History Details", (System.getProperty( "os.name" ).indexOf( "Mac" ) == -1) ? true : false );
        StringBuffer descBuffer;
        Object mainPanel;
        Object buttonPanel;
        Object topPanel;
        Object detailsSP;
        Object timeLabel;
        Object airportLabel;
        Object descTA;

        this.historyModel = historyModel;
        listeners = new Vector();
        timeFormat = DateFormat.getTimeInstance( 3 );
        descTA = new JTextArea( 5, 5 );
        ((JTextArea) descTA).setFont( Utilities.LABEL_FONT );
        ((JTextArea) descTA).setForeground( Color.black );
        ((JTextArea) descTA).setOpaque( false );
        ((JTextArea) descTA).setEditable( false );
        ((JTextArea) descTA).setLineWrap( true );
        ((JTextArea) descTA).setWrapStyleWord( true );
        descBuffer = new StringBuffer();
        descBuffer.append( "This dialog displays the full details for a change that can be undone. This includes the" );
        descBuffer.append( " type of object affected by the change; the item that changed; its value before the change;" );
        descBuffer.append( " its value after the change; and its current value." );
        ((JTextArea) descTA).setText( descBuffer.toString() );
        airportLabel = new JLabel( "Airport:" );
        ((JLabel) airportLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) airportLabel).setForeground( Color.black );
        airportDataLabel = new JLabel( "" );
        airportDataLabel.setFont( Utilities.LABEL_FONT );
        airportDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        timeLabel = new JLabel( "Time of change:" );
        ((JLabel) timeLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) timeLabel).setForeground( Color.black );
        timeDataLabel = new JLabel( timeFormat.format( historyModel.getDate() ) );
        timeDataLabel.setFont( Utilities.LABEL_FONT );
        timeDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        historyTableModel = new HistoryTableModel();
        historyTableModel.setModelData( historyModel );
        detailsTable = new JTable( (TableModel) historyTableModel );
        detailsTable.setPreferredScrollableViewportSize( new Dimension( 600, 200 ) );
        detailsTable.setGridColor( Color.lightGray );
        detailsTable.getSelectionModel().addListSelectionListener( this );
        detailsSP = new JScrollPane( (Component) detailsTable );
        ((JScrollPane) detailsSP).getViewport().setBackground( Color.white );
        topPanel = new JPanel( new GridBagLayout() );
        Utilities.addComponent( (Container) topPanel, (Component) descTA, 0, 0, 5, 1, 1, 10, new Insets( 1, 1, 10, 1 ), 0, 0, 0.5, 0.5 );
        Utilities.addComponent( (Container) topPanel, (Component) airportLabel, 0, 1, 1, 1, 0, 13, new Insets( 1, 10, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) topPanel, (Component) airportDataLabel, 1, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) topPanel, Box.createHorizontalGlue(), 2, 1, 1, 1, 2, 10, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) topPanel, (Component) timeLabel, 3, 1, 1, 1, 0, 13, new Insets( 1, 10, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) topPanel, (Component) timeDataLabel, 4, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) topPanel, (Component) detailsSP, 0, 2, 5, 1, 1, 10, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.5 );
        undoButton = new JButton( "Undo this change" );
        undoButton.setFont( Utilities.DIALOG_BUTTON_FONT );
        undoButton.setForeground( Color.black );
        undoButton.addActionListener( this );
        undoSelectedButton = new JButton( "Undo selected units" );
        undoSelectedButton.setFont( Utilities.DIALOG_BUTTON_FONT );
        undoSelectedButton.setForeground( Color.black );
        undoSelectedButton.setEnabled( false );
        undoSelectedButton.addActionListener( this );
        closeButton = new JButton( "Close" );
        closeButton.setFont( Utilities.DIALOG_BUTTON_FONT );
        closeButton.setForeground( Color.black );
        closeButton.addActionListener( this );
        getRootPane().setDefaultButton( closeButton );
        buttonPanel = new JPanel( new GridBagLayout() );
        Utilities.addComponent( (Container) buttonPanel, (Component) undoButton, 0, 0, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) buttonPanel, Box.createHorizontalGlue(), 2, 0, 1, 1, 2, 10, new Insets( 10, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) buttonPanel, (Component) closeButton, 3, 0, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        mainPanel = new JPanel( new BorderLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        ((JPanel) mainPanel).add( (Component) topPanel, "Center" );
        ((JPanel) mainPanel).add( (Component) buttonPanel, "South" );
        getContentPane().add( (Component) mainPanel, "Center" );
        pack();
        setLocationRelativeTo( frame );
    }

    private JButton closeButton;
    private JButton undoButton;
    private JButton undoSelectedButton;
    private JLabel airportDataLabel;
    private JLabel timeDataLabel;
    private DateFormat timeFormat;
    private HistoryTableModel historyTableModel;
    private HistoryModel historyModel;
    private JTable detailsTable;
    private Vector listeners;
    private static HistoryDetailsDialog dialog = null;

    public static void showDialog(Frame frame, HistoryModel historyModel, PropertyChangeListener propertyChangeListener, int originalX, int originalY)
    {
        if( dialog == null )
        {
            dialog = new HistoryDetailsDialog( frame, historyModel );
            dialog.addPropertyChangeListener( propertyChangeListener );
        }
        else
            dialog.updateDisplay( historyModel );
        dialog.setVisible( true );
    }

    private void updateDisplay(HistoryModel historyModel)
    {
        this.historyModel = historyModel;
        airportDataLabel.setText( historyModel.getAirportName() );
        timeDataLabel.setText( timeFormat.format( historyModel.getDate() ) );
        historyTableModel.setModelData( historyModel );
    }

    private void undoItems()
    {
        HistoryListModel.getInstance().undoItem( historyModel );
        JOptionPane.showMessageDialog( (Component) this, "Item undone", "Undo", 1 );
        setVisible( false );
        Utilities.MAIN_FRAME.requestFocus();
    }

    private void undoSelectedItems()
    {
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

    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == closeButton )
            setVisible( false );
        else if( event.getSource() == undoButton )
            undoItems();
        else if( event.getSource() == undoSelectedButton )
            undoSelectedItems();
    }

    public void valueChanged(ListSelectionEvent event)
    {
        if( event.getSource() == detailsTable.getSelectionModel() && !event.getValueIsAdjusting() )
            undoSelectedButton.setEnabled( (!detailsTable.getSelectionModel().isSelectionEmpty()) ? true : false );
    }
}