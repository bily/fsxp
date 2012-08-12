package com.zbluesoftware.fsxp.ui.data;

import com.zbluesoftware.fsxp.engine.DisplayEngine;
import com.zbluesoftware.fsxp.engine.ParseEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.PlaneModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.thread.CursorFollowFSXTask;
import com.zbluesoftware.fsxp.ui.WindowBorder;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.PopupTextField;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
//import javax.swing.JSpinner$NumberEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlaneData extends ObjectData implements ActionListener, ChangeListener, FocusListener, PropertyChangeListener {

    public PlaneData()
    {
        Object latLabel;
        Object lonLabel;
        Object altLabel;
        Object headingLabel;
        Object makeLabel;
        Object connectPanel;
        Object headingPanel;
        Object mainPanel;
        Object scrollPane;

        setLayout( new BorderLayout( 2, 2 ) );
        connectLabel = new JLabel( "Not Connected To FSX" );
        connectLabel.setFont( Utilities.BOLD_LABEL_FONT );
        connectLabel.setForeground( Color.red );
        connectButton = new JButton( "Connect" );
        connectButton.setFont( Utilities.BUTTON_FONT );
        connectButton.setForeground( Color.black );
        connectButton.addActionListener( this );
        connectPanel = new JPanel( new FlowLayout( 2 ) );
        ((JPanel) connectPanel).add( (Component) connectLabel );
        ((JPanel) connectPanel).add( Box.createHorizontalStrut( 10 ) );
        ((JPanel) connectPanel).add( (Component) connectButton );
        latLabel = new JLabel( "Latitude" );
        ((JLabel) latLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) latLabel).setForeground( Color.black );
        latTF = new PopupTextField( 20 );
        latTF.setFont( Utilities.TEXT_FIELD_FONT );
        latTF.setForeground( Color.black );
        latTF.addActionListener( this );
        latTF.addFocusListener( this );
        lonLabel = new JLabel( "Longitude" );
        ((JLabel) lonLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) lonLabel).setForeground( Color.black );
        lonTF = new PopupTextField( 20 );
        lonTF.setFont( Utilities.TEXT_FIELD_FONT );
        lonTF.setForeground( Color.black );
        lonTF.addActionListener( this );
        lonTF.addFocusListener( this );
        altLabel = new JLabel( "Altitude" );
        ((JLabel) altLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) altLabel).setForeground( Color.black );
        altSpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( 0.0, -5000.0, 100000.0, 1.0 ) );
        altSpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( altSpinner, "0.000" ) );
        altSpinner.addChangeListener( this );
        altComboBox = new JComboBox( new String[] { "M", "F" } );
        altComboBox.setFont( Utilities.COMBO_BOX_FONT );
        altComboBox.setForeground( Color.black );
        altComboBox.addActionListener( this );
        if( SettingsEngine.getInstance().getLAF().equals( "Windows" ) )
            altComboBox.setPrototypeDisplayValue( "--------" );
        headingLabel = new JLabel( "Heading" );
        ((JLabel) headingLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) headingLabel).setForeground( Color.black );
        headingTF = new PopupTextField( 5 );
        headingTF.setFont( Utilities.TEXT_FIELD_FONT );
        headingTF.setForeground( Color.black );
        headingTF.addActionListener( this );
        headingTF.addFocusListener( this );
        headingSlider = new JSlider( 0 );
        headingSlider.setModel( (BoundedRangeModel) new DefaultBoundedRangeModel( 0, 1, 0, 360 ) );
        headingSlider.setPreferredSize( new Dimension( 100, headingSlider.getPreferredSize().height ) );
        headingSlider.addChangeListener( this );
        headingPanel = new JPanel( new GridBagLayout() );
        Utilities.addComponent( (Container) headingPanel, (Component) headingTF, 0, 0, 1, 1, 2, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) headingPanel, (Component) headingSlider, 1, 0, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        makeLabel = new JLabel( "Plane Make" );
        ((JLabel) makeLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) makeLabel).setForeground( Color.black );
        makeComboBox = new JComboBox( (ComboBoxModel) getPlaneMakes() );
        makeComboBox.setFont( Utilities.COMBO_BOX_FONT );
        makeComboBox.setForeground( Color.black );
        makeComboBox.addActionListener( this );
        typeLabel = new JLabel( "Plane Type" );
        typeLabel.setFont( Utilities.LABEL_FONT );
        typeLabel.setForeground( Color.black );
        typeLabel.setEnabled( false );
        typeComboBox = new JComboBox( (ComboBoxModel) getPlaneTypes() );
        typeComboBox.setFont( Utilities.COMBO_BOX_FONT );
        typeComboBox.setForeground( Color.black );
        typeComboBox.addActionListener( this );
        typeComboBox.setEnabled( false );
        widthLabel = new JLabel( "Plane Width" );
        widthLabel.setFont( Utilities.LABEL_FONT );
        widthLabel.setForeground( Color.black );
        widthLabel.setEnabled( false );
        widthSpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( 10.0, 10.0, 750.0, 1.0 ) );
        widthSpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( widthSpinner, "0.0" ) );
        widthSpinner.addChangeListener( this );
        widthSpinner.setEnabled( false );
        lengthLabel = new JLabel( "Plane Length" );
        lengthLabel.setFont( Utilities.LABEL_FONT );
        lengthLabel.setForeground( Color.black );
        lengthLabel.setEnabled( false );
        lengthSpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( 10.0, 10.0, 750.0, 1.0 ) );
        lengthSpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( lengthSpinner, "0.0" ) );
        lengthSpinner.addChangeListener( this );
        lengthSpinner.setEnabled( false );
        fsxAutoFollowCB = new JCheckBox( "FSX Plane auto follows cursor", FSXConnection.getInstance().getFSXAutoFollow() );
        fsxAutoFollowCB.setFont( Utilities.LABEL_FONT );
        fsxAutoFollowCB.setForeground( Color.black );
        fsxAutoFollowCB.addActionListener( this );
        cursorAutoFollowCB = new JCheckBox( "Cursor auto follows FSX plane", FSXConnection.getInstance().getCursorAutoFollow() );
        cursorAutoFollowCB.setFont( Utilities.LABEL_FONT );
        cursorAutoFollowCB.setForeground( Color.black );
        cursorAutoFollowCB.addActionListener( this );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) connectPanel, 0, 0, 2, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) latLabel, 0, 1, 1, 1, 0, 13, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) latTF, 1, 1, 2, 1, 2, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) lonLabel, 0, 2, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) lonTF, 1, 2, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) altLabel, 0, 3, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) altSpinner, 1, 3, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) altComboBox, 2, 3, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) headingLabel, 0, 4, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) headingPanel, 1, 4, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) makeLabel, 0, 5, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) makeComboBox, 1, 5, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) typeLabel, 0, 6, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) typeComboBox, 1, 6, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) widthLabel, 0, 7, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) widthSpinner, 1, 7, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) lengthLabel, 0, 8, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) lengthSpinner, 1, 8, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) fsxAutoFollowCB, 0, 9, 2, 1, 0, 13, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) cursorAutoFollowCB, 0, 10, 2, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, Box.createGlue(), 0, 11, 3, 1, 3, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.5 );
        scrollPane = new JScrollPane( (Component) mainPanel );
        add( (Component) scrollPane, "Center" );
        windowBorder = new WindowBorder( "Plane Data" );
        setBorder( (Border) windowBorder );
        definePlaneDimensions();
        FSXConnection.getInstance().addPropertyChangeListener( this );
    }

    private PlaneModel model;
    private JLabel connectLabel;
    private JButton connectButton;
    private PopupTextField latTF;
    private PopupTextField lonTF;
    private JSpinner altSpinner;
    private JComboBox altComboBox;
    private PopupTextField headingTF;
    private JSlider headingSlider;
    private JComboBox makeComboBox;
    private JLabel typeLabel;
    private JComboBox typeComboBox;
    private JLabel widthLabel;
    private JSpinner widthSpinner;
    private JLabel lengthLabel;
    private JSpinner lengthSpinner;
    private JCheckBox fsxAutoFollowCB;
    private JCheckBox cursorAutoFollowCB;
    private HashMap planeWidthHM;
    private HashMap planeLengthHM;
    private HashMap planeTypeHM;
    private Vector listeners = new Vector();
    private Timer cursorAutoFollowFSXTimer = null;

    public void updateDisplay(BaseModel baseModel)
    {
        PlaneModel model = null;

        if( baseModel instanceof PlaneModel )
            model = (PlaneModel) baseModel;
        if( model != null )
            model.removePropertyChangeListener( this );
        updateModel();
        this.model = model;
        altComboBox.removeActionListener( this );
        altSpinner.removeChangeListener( this );
        headingSlider.removeChangeListener( this );
        makeComboBox.removeActionListener( this );
        typeComboBox.removeActionListener( this );
        widthSpinner.removeChangeListener( this );
        lengthSpinner.removeChangeListener( this );
        if( model != null )
        {
            latTF.setText( DisplayEngine.getInstance().formatObjectLatitude( model.getLatLon().getLat() ) );
            lonTF.setText( DisplayEngine.getInstance().formatObjectLongitude( model.getLatLon().getLon() ) );
            altSpinner.setValue( new Double( model.getAlt() ) );
            altComboBox.setSelectedItem( model.getAltMeasure() );
            headingSlider.setValue( (int) model.getHeading() );
            headingTF.setText( new StringBuilder().append( "" ).append( model.getHeading() ).toString() );
            makeComboBox.setSelectedItem( model.getPlaneMake() );
            typeComboBox.setSelectedItem( model.getPlaneType() );
            widthSpinner.setValue( new Double( (double) model.getWidth() ) );
            lengthSpinner.setValue( new Double( (double) model.getLength() ) );
            model.addPropertyChangeListener( this );
        }
        else
        {
            latTF.setText( "" );
            lonTF.setText( "" );
            altSpinner.setValue( new Double( 0.0 ) );
            altComboBox.setSelectedIndex( 0 );
            headingSlider.setValue( 0 );
            headingTF.setText( "" );
            makeComboBox.setSelectedItem( "Cessna 172" );
            typeComboBox.setSelectedItem( "Airplane" );
            widthSpinner.setValue( new Double( 36.0 ) );
            lengthSpinner.setValue( new Double( 26.0 ) );
        }
        altComboBox.addActionListener( this );
        altSpinner.addChangeListener( this );
        headingSlider.addChangeListener( this );
        makeComboBox.addActionListener( this );
        typeComboBox.addActionListener( this );
        widthSpinner.addChangeListener( this );
        lengthSpinner.addChangeListener( this );
        updateConnectionLabel( FSXConnection.getInstance().isConnectedToFSX() );
    }

    public void updateModel()
    {
        if( model != null )
        {
            model.setAlt( ((Double) altSpinner.getValue()).doubleValue() );
            model.setAltMeasure( (String) altComboBox.getSelectedItem() );
        }
    }

    private DefaultComboBoxModel getPlaneMakes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model.addElement( "Airbus A321" );
        model.addElement( "AirCreation Trike" );
        model.addElement( "Beechcraft Baron 58" );
        model.addElement( "Beechcraft Kingair 350" );
        model.addElement( "Bell Jet Ranger" );
        model.addElement( "Boeing 737" );
        model.addElement( "Boeing 747" );
        model.addElement( "Bombardier CRJ700" );
        model.addElement( "Bombardier LearJet" );
        model.addElement( "Cessna 172" );
        model.addElement( "Cessna C208B" );
        model.addElement( "de Havilland Beaver" );
        model.addElement( "DG Flugzeubau" );
        model.addElement( "Douglas DC-3" );
        model.addElement( "Extra 300S" );
        model.addElement( "Grumman Goose" );
        model.addElement( "Maule Orion" );
        model.addElement( "Mooney Bravo" );
        model.addElement( "Piper Cub" );
        model.addElement( "Robinson R22" );
        model.addElement( "Custom" );
        return model;
    }

    private DefaultComboBoxModel getPlaneTypes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        model.addElement( "Airplane" );
        model.addElement( "Helicopter" );
        return model;
    }

    private void definePlaneDimensions()
    {
        planeWidthHM = new HashMap();
        planeLengthHM = new HashMap();
        planeTypeHM = new HashMap();
        planeWidthHM.put( "Airbus A321", new Double( 112.0 ) );
        planeLengthHM.put( "Airbus A321", new Double( 146.0 ) );
        planeTypeHM.put( "Airbus A321", "Airplane" );
        planeWidthHM.put( "AirCreation Trike", new Double( 33.0 ) );
        planeLengthHM.put( "AirCreation Trike", new Double( 12.0 ) );
        planeTypeHM.put( "AirCreation Trike", "Airplane" );
        planeWidthHM.put( "Beechcraft Baron 58", new Double( 38.0 ) );
        planeLengthHM.put( "Beechcraft Baron 58", new Double( 30.0 ) );
        planeTypeHM.put( "Beechcraft Baron 58", "Airplane" );
        planeWidthHM.put( "Beechcraft Kingair 350", new Double( 58.0 ) );
        planeLengthHM.put( "Beechcraft Kingair 350", new Double( 46.0 ) );
        planeTypeHM.put( "Beechcraft Kingair 350", "Airplane" );
        planeWidthHM.put( "Bell Jet Ranger", new Double( 33.0 ) );
        planeLengthHM.put( "Bell Jet Ranger", new Double( 31.0 ) );
        planeTypeHM.put( "Bell Jet Ranger", "Helicopter" );
        planeWidthHM.put( "Boeing 737", new Double( 113.0 ) );
        planeLengthHM.put( "Boeing 737", new Double( 129.0 ) );
        planeTypeHM.put( "Boeing 737", "Airplane" );
        planeWidthHM.put( "Boeing 747", new Double( 212.0 ) );
        planeLengthHM.put( "Boeing 747", new Double( 232.0 ) );
        planeTypeHM.put( "Boeing 747", "Airplane" );
        planeWidthHM.put( "Bombardier CRJ700", new Double( 76.0 ) );
        planeLengthHM.put( "Bombardier CRJ700", new Double( 107.0 ) );
        planeTypeHM.put( "Bombardier CRJ700", "Airplane" );
        planeWidthHM.put( "Bombardier LearJet", new Double( 48.0 ) );
        planeLengthHM.put( "Bombardier LearJet", new Double( 58.0 ) );
        planeTypeHM.put( "Bombardier LearJet", "Airplane" );
        planeWidthHM.put( "Cessna 172", new Double( 36.0 ) );
        planeLengthHM.put( "Cessna 172", new Double( 26.0 ) );
        planeTypeHM.put( "Cessna 172", "Airplane" );
        planeWidthHM.put( "Cessna C208B", new Double( 52.0 ) );
        planeLengthHM.put( "Cessna C208B", new Double( 42.0 ) );
        planeTypeHM.put( "Cessna C208B", "Airplane" );
        planeWidthHM.put( "de Havilland Beaver", new Double( 48.0 ) );
        planeLengthHM.put( "de Havilland Beaver", new Double( 30.0 ) );
        planeTypeHM.put( "de Havilland Beaver", "Airplane" );
        planeWidthHM.put( "DG Flugzeubau", new Double( 59.0 ) );
        planeLengthHM.put( "DG Flugzeubau", new Double( 22.0 ) );
        planeTypeHM.put( "DG Flugzeubau", "Airplane" );
        planeWidthHM.put( "Douglas DC-3", new Double( 95.0 ) );
        planeLengthHM.put( "Douglas DC-3", new Double( 65.0 ) );
        planeTypeHM.put( "Douglas DC-3", "Airplane" );
        planeWidthHM.put( "Extra 300S", new Double( 26.0 ) );
        planeLengthHM.put( "Extra 300S", new Double( 23.0 ) );
        planeTypeHM.put( "Extra 300S", "Airplane" );
        planeWidthHM.put( "Grumman Goose", new Double( 49.0 ) );
        planeLengthHM.put( "Grumman Goose", new Double( 38.0 ) );
        planeTypeHM.put( "Grumman Goose", "Airplane" );
        planeWidthHM.put( "Maule Orion", new Double( 33.0 ) );
        planeLengthHM.put( "Maule Orion", new Double( 23.0 ) );
        planeTypeHM.put( "Maule Orion", "Airplane" );
        planeWidthHM.put( "Mooney Bravo", new Double( 36.0 ) );
        planeLengthHM.put( "Mooney Bravo", new Double( 27.0 ) );
        planeTypeHM.put( "Mooney Bravo", "Airplane" );
        planeWidthHM.put( "Piper Cub", new Double( 35.0 ) );
        planeLengthHM.put( "Piper Cub", new Double( 22.0 ) );
        planeTypeHM.put( "Piper Cub", "Airplane" );
        planeWidthHM.put( "Robinson R22", new Double( 25.0 ) );
        planeLengthHM.put( "Robinson R22", new Double( 29.0 ) );
        planeTypeHM.put( "Robinson R22", "Helicopter" );
    }

    private void updateLatitude()
    {
        if( !latTF.isPopupDisplayed() )
        {
            double lat = ParseEngine.getInstance().parseLatitude( latTF.getText() );

            if( lat == Double.POSITIVE_INFINITY )
                lat = model.getLatLon().getLat();
            latTF.setText( DisplayEngine.getInstance().formatObjectLatitude( lat ) );
            firePropertyChange( "update-lat", new Double( model.getLatLon().getLat() ), new Double( lat ) );
            model.setLatLon( new LatLonPoint( lat, model.getLatLon().getLon() ) );
        }
    }

    private void updateLongitude()
    {
        if( !lonTF.isPopupDisplayed() )
        {
            double lon = ParseEngine.getInstance().parseLongitude( lonTF.getText() );

            if( lon == Double.POSITIVE_INFINITY )
                lon = model.getLatLon().getLon();
            lonTF.setText( DisplayEngine.getInstance().formatObjectLongitude( lon ) );
            firePropertyChange( "update-lon", new Double( model.getLatLon().getLon() ), new Double( lon ) );
            model.setLatLon( new LatLonPoint( model.getLatLon().getLat(), lon ) );
        }
    }

    private void updateHeading()
    {
        if( !headingTF.isPopupDisplayed() )
        {
            float heading = model.getHeading();

            try
            {
                heading = Float.parseFloat( headingTF.getText() );
            }
            catch( NumberFormatException nfe )
            {
                heading = model.getHeading();
            }
            if( heading < 0.0F )
                heading = 0.0F;
            else if( heading > 359.0F )
                heading = 359.0F;
            headingSlider.setValue( (int) heading );
            headingTF.setText( new StringBuilder().append( "" ).append( heading ).toString() );
            firePropertyChange( "update-heading", new Float( model.getHeading() ), new Float( (float) headingSlider.getValue() ) );
            model.setHeading( heading );
        }
    }

    private void updateAltMeasure()
    {
        if( SettingsEngine.getInstance().getAdjustMeasurements() )
        {
            if( model.getAltMeasure().equals( "M" ) )
                model.setAlt( model.getAlt() * 3.2799999713897705078125 );
            else
                model.setAlt( model.getAlt() / 3.2799999713897705078125 );
            altSpinner.setValue( new Double( model.getAlt() ) );
        }
        model.setAltMeasure( (String) altComboBox.getSelectedItem() );
    }

    private void updatePlaneMake()
    {
        boolean enabled = ((String) makeComboBox.getSelectedItem()).equals( "Custom" );
        String planeMake;

        typeLabel.setEnabled( enabled );
        typeComboBox.setEnabled( enabled );
        widthLabel.setEnabled( enabled );
        widthSpinner.setEnabled( enabled );
        lengthLabel.setEnabled( enabled );
        lengthSpinner.setEnabled( enabled );
        planeMake = (String) makeComboBox.getSelectedItem();
        model.setPlaneMake( planeMake );
        if( !planeMake.equals( "Custom" ) )
        {
            widthSpinner.setValue( (Double) planeWidthHM.get( planeMake ) );
            lengthSpinner.setValue( (Double) planeLengthHM.get( planeMake ) );
            typeComboBox.setSelectedItem( (String) planeTypeHM.get( planeMake ) );
        }
        FSXConnection.getInstance().setPlaneMake( (String) makeComboBox.getSelectedItem() );
        FSXConnection.getInstance().writePreferences();
    }

    private void updateConnectionLabel(boolean connected)
    {
        if( connected )
        {
            connectLabel.setText( "Connected To FSX" );
            connectLabel.setForeground( new Color( 0, 102, 0 ) );
            connectButton.setText( "Disconnect" );
            if( cursorAutoFollowCB.isSelected() )
            {
                Object task;

                if( cursorAutoFollowFSXTimer != null )
                    cursorAutoFollowFSXTimer.cancel();
                task = new CursorFollowFSXTask();
                ((CursorFollowFSXTask) task).addPropertyChangeListener( this );
                cursorAutoFollowFSXTimer = new Timer();
                cursorAutoFollowFSXTimer.scheduleAtFixedRate( (TimerTask) task, 0L, 1000L );
            }
        }
        else
        {
            connectLabel.setText( "Not Connected To FSX" );
            connectLabel.setForeground( Color.red );
            connectButton.setText( "Connect" );
            if( cursorAutoFollowFSXTimer != null )
            {
                cursorAutoFollowFSXTimer.cancel();
                cursorAutoFollowFSXTimer = null;
            }
        }
    }

    private void setCursorAutoFollowFSX()
    {
        FSXConnection.getInstance().setCursorAutoFollow( cursorAutoFollowCB.isSelected() );
        FSXConnection.getInstance().writePreferences();
        if( cursorAutoFollowCB.isSelected() && FSXConnection.getInstance().isConnectedToFSX() )
        {
            Object task;

            if( cursorAutoFollowFSXTimer != null )
                cursorAutoFollowFSXTimer.cancel();
            task = new CursorFollowFSXTask();
            ((CursorFollowFSXTask) task).addPropertyChangeListener( this );
            cursorAutoFollowFSXTimer = new Timer();
            cursorAutoFollowFSXTimer.scheduleAtFixedRate( (TimerTask) task, 0L, 1000L );
        }
        else if( cursorAutoFollowFSXTimer != null )
        {
            cursorAutoFollowFSXTimer.cancel();
            cursorAutoFollowFSXTimer = null;
        }
    }

    private void handleFSXConnection()
    {
        if( FSXConnection.getInstance().isConnectedToFSX() )
        {
            if( !com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().disconnectFromFSX() )
                JOptionPane.showMessageDialog( (Component) this, "There was a problem disconnecting from FSX.", "Error...", 0 );
        }
        else if( FSXConnection.getInstance().isLocalConnection() && !com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().getSpecifyPort() )
        {
            ArrayList portAL = FSXConnection.getInstance().runNetstat();

            if( portAL.size() == 0 )
            {
                StringBuffer buffer = new StringBuffer();

                buffer.append( "No open SimConnect ports were found.\n" );
                buffer.append( "Please make sure that FSX is running.\n" );
                buffer.append( "and that your connection properties are correct.\n" );
                buffer.append( "SimConnect -> FSX Connection Status." );
                JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "No Port Found Error...", 0 );
                return;
            }
            else
            {
                int i = 0;

                while( i < portAL.size() )
                {
                    int port = ((Integer) portAL.get( i )).intValue();

                    if( FSXConnection.getInstance().connectToFSX( "127.0.0.1", port, false ) )
                    {
                        i = 1;
                        FSXConnection.getInstance().setPort( port );
                        FSXConnection.getInstance().writePreferences();
                        break;
                    }
                    else
                        ++i;
                }
                if( i == 0 )
                {
                    StringBuffer stringbuffer1 = new StringBuffer();

                    stringbuffer1.append( "There was a problem connecting to Flight Simulator X.\n" );
                    stringbuffer1.append( "Please check to make sure your connection properties are correct.\n" );
                    stringbuffer1.append( "SimConnect -> FSX Connection Status." );
                    JOptionPane.showMessageDialog( (Component) this, stringbuffer1.toString(), "Connection Error...", 0 );
                }
            }
        }
        else if( !com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().connectToFSX() )
        {
            StringBuffer stringbuffer2 = new StringBuffer();

            stringbuffer2.append( "There was a problem connecting to Flight Simulator X.\n" );
            stringbuffer2.append( "Please check to make sure your connection properties are correct.\n" );
            stringbuffer2.append( "SimConnect -> FSX Connection Status." );
            JOptionPane.showMessageDialog( (Component) this, stringbuffer2.toString(), "Connection Error...", 0 );
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

    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == latTF )
            updateLatitude();
        else if( event.getSource() == lonTF )
            updateLongitude();
        else if( event.getSource() == headingTF )
            updateHeading();
        else if( event.getSource() == altComboBox )
            updateAltMeasure();
        else if( event.getSource() == makeComboBox )
            updatePlaneMake();
        else if( event.getSource() == typeComboBox )
        {
            model.setPlaneType( (String) typeComboBox.getSelectedItem() );
            FSXConnection.getInstance().setPlaneType( (String) typeComboBox.getSelectedItem() );
            FSXConnection.getInstance().writePreferences();
        }
        else if( event.getSource() == fsxAutoFollowCB )
        {
            FSXConnection.getInstance().setFSXAutoFollow( fsxAutoFollowCB.isSelected() );
            FSXConnection.getInstance().writePreferences();
        }
        else if( event.getSource() == cursorAutoFollowCB )
            setCursorAutoFollowFSX();
        else if( event.getSource() == connectButton )
            handleFSXConnection();
    }

    public void stateChanged(ChangeEvent event)
    {
        if( event.getSource() == headingSlider && model != null )
        {
            headingTF.setText( new StringBuilder().append( "" ).append( headingSlider.getValue() ).toString() );
            firePropertyChange( "update-heading", new Float( model.getHeading() ), new Float( (float) headingSlider.getValue() ) );
            model.setHeading( (float) headingSlider.getValue() );
        }
        else if( event.getSource() == altSpinner && model != null )
            model.setAlt( ((Double) altSpinner.getValue()).doubleValue() );
        else if( event.getSource() == widthSpinner && model != null )
        {
            model.setWidth( ((Double) widthSpinner.getValue()).floatValue() );
            FSXConnection.getInstance().setPlaneWidth( model.getWidth() );
            FSXConnection.getInstance().writePreferences();
            firePropertyChange( "update-width", new Float( model.getWidth() ), new Float( ((Double) widthSpinner.getValue()).floatValue() ) );
        }
        else if( event.getSource() == lengthSpinner && model != null )
        {
            model.setLength( ((Double) lengthSpinner.getValue()).floatValue() );
            FSXConnection.getInstance().setPlaneLength( model.getLength() );
            FSXConnection.getInstance().writePreferences();
            firePropertyChange( "update-length", new Float( model.getLength() ), new Float( ((Double) lengthSpinner.getValue()).floatValue() ) );
        }
    }

    public void focusGained(FocusEvent event)
    {
    }

    public void focusLost(FocusEvent event)
    {
        if( event.getSource() == latTF )
            updateLatitude();
        else if( event.getSource() == lonTF )
            updateLongitude();
        else if( event.getSource() == headingTF )
            updateHeading();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if( event.getSource() == model )
        {
            if( event.getPropertyName().equals( "latLon" ) )
            {
                latTF.setText( DisplayEngine.getInstance().formatObjectLatitude( ((LatLonPoint) event.getNewValue()).getLat() ) );
                lonTF.setText( DisplayEngine.getInstance().formatObjectLongitude( ((LatLonPoint) event.getNewValue()).getLon() ) );
            }
            else if( event.getPropertyName().equals( "alt" ) )
            {
                altSpinner.removeChangeListener( this );
                altSpinner.setValue( event.getNewValue() );
                altSpinner.addChangeListener( this );
            }
            else if( event.getPropertyName().equals( "altMeasure" ) )
                altComboBox.setSelectedItem( (String) event.getNewValue() );
            else if( event.getPropertyName().equals( "heading" ) )
            {
                headingTF.setText( new StringBuilder().append( "" ).append( ((Float) event.getNewValue()).floatValue() ).toString() );
                headingSlider.removeChangeListener( this );
                headingSlider.setValue( ((Float) event.getNewValue()).intValue() );
                headingSlider.addChangeListener( this );
            }
        }
        else if( event.getSource() instanceof FSXConnection )
        {
            if( event.getPropertyName().equals( "connectedToFSX" ) )
                updateConnectionLabel( ((Boolean) event.getNewValue()).booleanValue() );
        }
        else if( event.getSource() instanceof CursorFollowFSXTask && event.getPropertyName().equals( "CursorFollowFSXTask" ) )
            firePropertyChange( "update-position", "", "" );
    }
}