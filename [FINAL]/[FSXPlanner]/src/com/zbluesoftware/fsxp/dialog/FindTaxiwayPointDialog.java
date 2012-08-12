package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
import com.zbluesoftware.fsxp.model.TaxiwayPointModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
//import javax.swing.JSpinner$NumberEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

class FindTaxiwayPointDialog$1 extends Thread {

    FindTaxiwayPointDialog$1(FindTaxiwayPointDialog findtaxiwaypointdialog1)
    {
        this$0 = findtaxiwaypointdialog1;
    }

    FindTaxiwayPointDialog this$0;     // final access specifier removed

    public void run()
    {
        if( FindTaxiwayPointDialog.access$000( this$0 ) )
            FindTaxiwayPointDialog.access$100( this$0 );
        FindTaxiwayPointDialog.access$200( this$0 );
    }
}


public class FindTaxiwayPointDialog extends JDialog implements ActionListener {

    private FindTaxiwayPointDialog(Frame parent)
    {
        super( parent, "Find Taxiway Point", false );
        StringBuffer text;
        Object buttonPanel;
        Object mainPanel;
        ButtonGroup buttonGroup;
        Object searchLabel;
        Object numberLabel;
        Object infoTA;

        infoTA = new JTextArea( 3, 30 );
        ((JTextArea) infoTA).setFont( Utilities.LABEL_FONT );
        ((JTextArea) infoTA).setForeground( Color.black );
        ((JTextArea) infoTA).setEditable( false );
        ((JTextArea) infoTA).setLineWrap( true );
        ((JTextArea) infoTA).setWrapStyleWord( true );
        ((JTextArea) infoTA).setOpaque( false );
        text = new StringBuffer();
        text.append( "This dialog allows you to find specific taxiway points or taxiway parking locations." );
        text.append( " It is intended to help you debug any XML errors resulting from taxiway paths or points." );
        ((JTextArea) infoTA).setText( text.toString() );
        numberLabel = new JLabel( "Taxiway Point/Parking Index Number:" );
        ((JLabel) numberLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) numberLabel).setForeground( Color.black );
        numberSpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( 0, 0, 3999, 1 ) );
        numberSpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( numberSpinner, "0" ) );
        searchLabel = new JLabel( "Search the following type of points:" );
        ((JLabel) searchLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) searchLabel).setForeground( Color.black );
        pointRB = new JRadioButton( "Taxiway Point", true );
        pointRB.setFont( Utilities.LABEL_FONT );
        pointRB.setForeground( Color.black );
        parkingRB = new JRadioButton( "Taxiway Parking Location", false );
        parkingRB.setFont( Utilities.LABEL_FONT );
        parkingRB.setForeground( Color.black );
        buttonGroup = new ButtonGroup();
        buttonGroup.add( (AbstractButton) pointRB );
        buttonGroup.add( (AbstractButton) parkingRB );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) infoTA, 0, 0, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) numberLabel, 0, 1, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) numberSpinner, 0, 2, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) searchLabel, 0, 3, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) pointRB, 0, 4, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) parkingRB, 0, 5, 1, 1, 0, 17, new Insets( 1, 1, 10, 1 ), 0, 0, 0.0, 0.0 );
        getContentPane().add( (Component) mainPanel, "Center" );
        findButton = new JButton( "Find Point" );
        findButton.setFont( Utilities.BUTTON_FONT );
        findButton.setForeground( Color.black );
        findButton.addActionListener( this );
        closeButton = new JButton( "Close" );
        closeButton.setFont( Utilities.BUTTON_FONT );
        closeButton.setForeground( Color.black );
        closeButton.addActionListener( this );
        buttonPanel = new JPanel();
        ((JPanel) buttonPanel).add( (Component) findButton );
        ((JPanel) buttonPanel).add( (Component) closeButton );
        getContentPane().add( (Component) buttonPanel, "South" );
        pack();
        setLocationRelativeTo( parent );
    }

    private AirportModel airportModel;
    private BaseModel baseModel;
    private JSpinner numberSpinner;
    private JRadioButton pointRB;
    private JRadioButton parkingRB;
    private JButton findButton;
    private JButton closeButton;
    private static FindTaxiwayPointDialog dialog = null;
    private Vector listeners = new Vector();

    public static void showDialog(Frame parent, AirportModel airportModel, PropertyChangeListener listener)
    {
        if( dialog == null )
        {
            dialog = new FindTaxiwayPointDialog( parent );
            dialog.addPropertyChangeListener( listener );
        }
        dialog.setAirportModel( airportModel );
        dialog.setVisible( true );
    }

    private void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        findButton.setEnabled( (airportModel != null) ? true : false );
        setTitle( new StringBuilder().append( "Find Taxiway Point [" ).append( airportModel.getIdent() ).append( "]" ).toString() );
    }

    private void runTests()
    {
        Object thread = new FindTaxiwayPointDialog$1( this );

        ((Thread) thread).setPriority( 4 );
        ((Thread) thread).start();
    }

    private void displayCompletedMessage()
    {
        StringBuffer buffer = new StringBuffer();

        if( baseModel == null )
            buffer.append( "No match was found." );
        else if( baseModel instanceof TaxiwayPointModel )
            buffer.append( "The Taxiway Point was found." );
        else
            buffer.append( "The Taxiway Parking Spot was found." );
        JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Complete...", 1 );
    }

    private boolean findPoint()
    {
        HashMap taxiwayPointHM;

        if( pointRB.isSelected() )
        {
            taxiwayPointHM = airportModel.getTaxiwayPointHM();
            if( taxiwayPointHM.containsKey( (Integer) numberSpinner.getValue() ) )
            {
                baseModel = (BaseModel) (TaxiwayPointModel) taxiwayPointHM.get( (Integer) numberSpinner.getValue() );
                return true;
            }
            else
            {
                baseModel = null;
                return false;
            }
        }
        else
        {
            taxiwayPointHM = airportModel.getTaxiwayParkingHM();
            if( taxiwayPointHM.containsKey( (Integer) numberSpinner.getValue() ) )
            {
                baseModel = (BaseModel) (TaxiwayParkingModel) taxiwayPointHM.get( (Integer) numberSpinner.getValue() );
                return true;
            }
            else
            {
                baseModel = null;
                return false;
            }
        }
    }

    private void selectItem()
    {
        firePropertyChange( "modelSelected", baseModel, baseModel );
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        super.addPropertyChangeListener( listener );
        if( listeners != null && !listeners.contains( listener ) )
            listeners.add( listener );
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        super.removePropertyChangeListener( listener );
        if( listeners != null )
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

    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == closeButton )
            setVisible( false );
        else if( event.getSource() == findButton )
            runTests();
    }

    static boolean access$000(FindTaxiwayPointDialog x0)
    {
        return x0.findPoint();
    }

    static void access$100(FindTaxiwayPointDialog x0)
    {
        x0.selectItem();
    }

    static void access$200(FindTaxiwayPointDialog x0)
    {
        x0.displayCompletedMessage();
    }
}