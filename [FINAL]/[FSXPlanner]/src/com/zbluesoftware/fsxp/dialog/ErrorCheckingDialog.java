package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.DMEModel;
import com.zbluesoftware.fsxp.model.GlideSlopeModel;
import com.zbluesoftware.fsxp.model.HelipadModel;
import com.zbluesoftware.fsxp.model.ILSModel;
import com.zbluesoftware.fsxp.model.JetwayModel;
import com.zbluesoftware.fsxp.model.MarkerModel;
import com.zbluesoftware.fsxp.model.NDBModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.SceneryModel;
import com.zbluesoftware.fsxp.model.StartModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathModel;
import com.zbluesoftware.fsxp.model.TaxiwayPointModel;
import com.zbluesoftware.fsxp.model.TowerModel;
import com.zbluesoftware.fsxp.model.VORModel;
import com.zbluesoftware.fsxp.model.WindsockModel;
import com.zbluesoftware.fsxp.model.table.ErrorCheckingTableModel;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import com.zbluesoftware.fsxp.model.table.ErrorCheckingTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

class ErrorCheckingDialog$1 extends Thread {

    ErrorCheckingDialog$1(ErrorCheckingDialog errorcheckingdialog1)
    {
        this$0 = errorcheckingdialog1;
    }

    ErrorCheckingDialog this$0;     // final access specifier removed

    public void run()
    {
        ((ErrorCheckingTableModel) ErrorCheckingDialog.access$000( this$0 ).getModel()).clearData();
        ErrorCheckingDialog.access$100( this$0 );
        ErrorCheckingDialog.access$200( this$0 );
        ErrorCheckingDialog.access$300( this$0 );
        ErrorCheckingDialog.access$400( this$0 );
        ErrorCheckingDialog.access$500( this$0 );
        ErrorCheckingDialog.access$600( this$0 );
        ErrorCheckingDialog.access$700( this$0 );
        ErrorCheckingDialog.access$800( this$0 );
        ErrorCheckingDialog.access$900( this$0 );
        ErrorCheckingDialog.access$1000( this$0 );
        ErrorCheckingDialog.access$1100( this$0 );
    }
}

public class ErrorCheckingDialog extends JDialog implements ActionListener, MouseListener {

    private ErrorCheckingDialog(Frame parent)
    {
        super( parent, "Error Checking", false );
        StringBuffer text;
        Object buttonPanel;
        Object mainPanel;
        Object clickLabel;
        Object resultsSP;
        Object resultsLabel;
        Object infoTA;

        infoTA = new JTextArea( 5, 50 );
        ((JTextArea) infoTA).setFont( Utilities.LABEL_FONT );
        ((JTextArea) infoTA).setForeground( Color.black );
        ((JTextArea) infoTA).setEditable( false );
        ((JTextArea) infoTA).setLineWrap( true );
        ((JTextArea) infoTA).setWrapStyleWord( true );
        ((JTextArea) infoTA).setOpaque( false );
        text = new StringBuffer();
        text.append( "This dialog allows you to check for errors that may prevent your airport from compiling properly.\n\n" );
        text.append( "The following errors will be checked:\n" );
        text.append( "1) Duplicate parking spot names\n" );
        text.append( "2) Taxiways that have non-existant taxiway points associated with them\n" );
        text.append( "3) Taxiway Points that have incorrect Type/Orientation settings\n" );
        text.append( "4) Duplicate jetways\n" );
        text.append( "5) Jetways associated with non-existant gates\n" );
        text.append( "6) Runway Taxiway Paths that are assigned to non-existant runways\n" );
        text.append( "7) Runway Taxiway Paths that do not have a runway number or designator assigned to them\n" );
        text.append( "8) Runways that do not have designators properly assigned\n" );
        text.append( "9) NDBs, VORs or ILSs without Idents\n" );
        text.append( "10) Altitudes that are inconsistent with the airport altitude\n" );
        ((JTextArea) infoTA).setText( text.toString() );
        runButton = new JButton( "Run Error Checking" );
        runButton.setFont( Utilities.BUTTON_FONT );
        runButton.setForeground( Color.black );
        runButton.addActionListener( this );
        resultsLabel = new JLabel( "Results:" );
        ((JLabel) resultsLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) resultsLabel).setForeground( Color.black );
        resultsTable = new JTable( (TableModel) new ErrorCheckingTableModel() );
        resultsTable.setPreferredScrollableViewportSize( new Dimension( 400, 200 ) );
        resultsTable.addMouseListener( this );
        resultsSP = new JScrollPane( (Component) resultsTable );
        clickLabel = new JLabel( "double click on a row to view the object." );
        ((JLabel) clickLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) clickLabel).setForeground( Color.black );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) infoTA, 0, 0, 1, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) runButton, 0, 1, 1, 1, 0, 17, new Insets( 5, 1, 10, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) resultsLabel, 0, 2, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) resultsSP, 0, 3, 1, 1, 1, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.5 );
        Utilities.addComponent( (Container) mainPanel, (Component) clickLabel, 0, 4, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        getContentPane().add( (Component) mainPanel, "Center" );
        closeButton = new JButton( "Close" );
        closeButton.setFont( Utilities.BUTTON_FONT );
        closeButton.setForeground( Color.black );
        closeButton.addActionListener( this );
        buttonPanel = new JPanel();
        ((JPanel) buttonPanel).add( (Component) closeButton );
        getContentPane().add( (Component) buttonPanel, "South" );
        pack();
        setLocationRelativeTo( parent );
    }

    private AirportModel airportModel;
    private JTable resultsTable;
    private JButton runButton;
    private JButton closeButton;
    private static ErrorCheckingDialog dialog = null;
    private Vector listeners = new Vector();

    public static void showDialog(Frame parent, AirportModel airportModel, PropertyChangeListener listener)
    {
        if( dialog == null )
        {
            dialog = new ErrorCheckingDialog( parent );
            dialog.addPropertyChangeListener( listener );
        }
        dialog.setAirportModel( airportModel );
        dialog.setVisible( true );
    }

    private void setAirportModel(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        runButton.setEnabled( (airportModel != null) ? true : false );
        setTitle( new StringBuilder().append( "Error Checking [" ).append( airportModel.getIdent() ).append( "]" ).toString() );
    }

    private void runTests()
    {
        Object thread = new ErrorCheckingDialog$1( this );

        ((Thread) thread).setPriority( 4 );
        ((Thread) thread).start();
    }

    private void displayCompletedMessage()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( "Reports Complete\n\n" );
        if( resultsTable.getRowCount() > 0 )
            buffer.append( "Please see the table for any details." );
        else
            buffer.append( "No errors were found." );
        JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Complete...", 1 );
    }

    private void runDuplicateParkingSpotTest()
    {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap taxiwayParkingHM = airportModel.getTaxiwayParkingHM();
        Iterator iterator = taxiwayParkingHM.keySet().iterator();
        Object index;
        int i;

        while( iterator.hasNext() )
        {
            Object model;
            String gateName;

            index = (Integer) iterator.next();
            model = (TaxiwayParkingModel) taxiwayParkingHM.get( index );
            gateName = new StringBuilder().append( ((TaxiwayParkingModel) model).getName() ).append( "-" ).append( ((TaxiwayParkingModel) model).getNumber() ).toString();
            if( hashMap.containsKey( gateName ) )
            {
                arrayList.add( model );
                if( !arrayList.contains( hashMap.get( gateName ) ) )
                    arrayList.add( hashMap.get( gateName ) );
            }
            hashMap.put( gateName, model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            index = (TaxiwayParkingModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) index, "Duplicate Parking Location", new StringBuilder().append( ((TaxiwayParkingModel) index).getName() ).append( " Number " ).append( ((TaxiwayParkingModel) index).getNumber() ).toString() );
        }
    }

    private void runDuplicateJetwayTest()
    {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList jetwayAL = airportModel.getJetwayAL();
        int i;
        Object model;

        for( i = jetwayAL.size() - 1; i >= 0; --i )
        {
            String gateName;

            model = (JetwayModel) jetwayAL.get( i );
            gateName = new StringBuilder().append( ((JetwayModel) model).getGateName() ).append( "-" ).append( ((JetwayModel) model).getParkingNumber() ).toString();
            if( hashMap.containsKey( gateName ) )
            {
                arrayList.add( model );
                if( !arrayList.contains( hashMap.get( gateName ) ) )
                    arrayList.add( hashMap.get( gateName ) );
            }
            hashMap.put( gateName, model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (JetwayModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Duplicate Jetway", new StringBuilder().append( ((JetwayModel) model).getGateName() ).append( " Number " ).append( ((JetwayModel) model).getParkingNumber() ).toString() );
        }
    }

    private void runJetwayTest()
    {
        HashMap taxiwayParkingHM = airportModel.getTaxiwayParkingHM();
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;

        for( i = airportModel.getJetwayAL().size() - 1; i >= 0; --i )
        {
            String gateName;
            int found;
            Iterator iterator;

            model = (JetwayModel) airportModel.getJetwayAL().get( i );
            gateName = new StringBuilder().append( ((JetwayModel) model).getGateName() ).append( "-" ).append( ((JetwayModel) model).getParkingNumber() ).toString();
            found = 0;
            iterator = taxiwayParkingHM.keySet().iterator();
label_72:
            {
                TaxiwayParkingModel taxiwayParkingModel;

                do
                {
                    if( !iterator.hasNext() )
                        break label_72;
                    taxiwayParkingModel = (TaxiwayParkingModel) taxiwayParkingHM.get( (Integer) iterator.next() );
                }
                while( !gateName.equals( new StringBuilder().append( taxiwayParkingModel.getName() ).append( "-" ).append( taxiwayParkingModel.getNumber() ).toString() ) );
                found = 1;
            }
            if( found == 0 )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (JetwayModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Jetway Without Gate", new StringBuilder().append( ((JetwayModel) model).getGateName() ).append( " Number " ).append( ((JetwayModel) model).getParkingNumber() ).toString() );
        }
    }

    private void runTaxiwayTest1()
    {
        ArrayList runwayAL = airportModel.getRunwayAL();
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;

        for( i = airportModel.getTaxiwayPathAL().size() - 1; i >= 0; --i )
        {
            model = (TaxiwayPathModel) airportModel.getTaxiwayPathAL().get( i );
            if( ((TaxiwayPathModel) model).getType().equals( "RUNWAY" ) )
            {
                int found = 0;
                int j = runwayAL.size() - 1;

                while( j >= 0 )
                {
                    if( ((TaxiwayPathModel) model).getNumber().equals( ((RunwayModel) runwayAL.get( j )).getNumber() ) && ((TaxiwayPathModel) model).getDesignator().equals( ((RunwayModel) runwayAL.get( j )).getDesignator() ) )
                    {
                        found = 1;
                        break;
                    }
                    else
                        --j;
                }
                if( found == 0 )
                    arrayList.add( model );
            }
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (TaxiwayPathModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Runway", new StringBuilder().append( "Assigned to Runway " ).append( ((TaxiwayPathModel) model).getNumber() ).append( " [" ).append( ((TaxiwayPathModel) model).getDesignator() ).append( "]" ).toString() );
        }
    }

    private void runTaxiwayTest2()
    {
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;

        for( i = airportModel.getTaxiwayPathAL().size() - 1; i >= 0; --i )
        {
            model = (TaxiwayPathModel) airportModel.getTaxiwayPathAL().get( i );
            if( ((TaxiwayPathModel) model).getType().equals( "RUNWAY" ) && ((TaxiwayPathModel) model).getDesignator().length() == 0 )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (TaxiwayPathModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Designator", "You should assign a designator to this taxiway" );
        }
    }

    private void runRunwayTest()
    {
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;

        for( i = airportModel.getRunwayAL().size() - 1; i >= 0; --i )
        {
            model = (RunwayModel) airportModel.getRunwayAL().get( i );
            if( ((RunwayModel) model).getDesignator().length() == 0 && (((RunwayModel) model).getPrimaryDesignator().length() == 0 || ((RunwayModel) model).getSecondaryDesignator().length() == 0) )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (RunwayModel) arrayList.get( i );
            ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Runway With Incorrect Designator", "You must assign either a designator OR a primary AND secondary designator" );
        }
    }

    private void runIdentTest()
    {
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;
        ArrayList arraylist1;

        for( i = airportModel.getVORAL().size() - 1; i >= 0; --i )
        {
            model = (VORModel) airportModel.getVORAL().get( i );
            if( ((VORModel) model).getIdent().length() == 0 )
                arrayList.add( model );
        }
        for( i = airportModel.getNDBAL().size() - 1; i >= 0; --i )
        {
            model = (NDBModel) airportModel.getNDBAL().get( i );
            if( ((NDBModel) model).getIdent().length() == 0 )
                arrayList.add( model );
        }
        arraylist1 = airportModel.getILSModels();
        for( i = arraylist1.size() - 1; i >= 0; --i )
        {
            model = (ILSModel) arraylist1.get( i );
            if( ((ILSModel) model).getIdent().length() == 0 )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            if( arrayList.get( i ) instanceof VORModel )
            {
                model = (VORModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "VOR Without An Ident", "All VORS must have an Ident." );
            }
            else if( arrayList.get( i ) instanceof NDBModel )
            {
                model = (NDBModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "NDB Without An Ident", "All NDBs must have an ident." );
            }
            else if( arrayList.get( i ) instanceof ILSModel )
            {
                model = (ILSModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "ILS Without An Ident", "All ILSs must have an ident." );
            }
        }
    }

    private void runTaxiwayPointTest()
    {
        ArrayList arrayList = new ArrayList();
        int i;
        Object model;

        for( i = airportModel.getTaxiwayPathAL().size() - 1; i >= 0; --i )
        {
            model = (TaxiwayPathModel) airportModel.getTaxiwayPathAL().get( i );
            if( ((TaxiwayPathModel) model).getType().equals( "PARKING" ) )
            {
                if( !airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) && !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) && !airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) && !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) )
                    arrayList.add( model );
            }
            else if( !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) )
                arrayList.add( model );
            else if( !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            model = (TaxiwayPathModel) arrayList.get( i );
            if( ((TaxiwayPathModel) model).getType().equals( "PARKING" ) )
            {
                if( !airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) && airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The start taxiway parking point [" ).append( ((TaxiwayPathModel) model).getStart() ).append( "] does not exist." ).toString() );
                else if( !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) && airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The start taxiway point [" ).append( ((TaxiwayPathModel) model).getStart() ).append( "] does not exist." ).toString() );
                else if( airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) && !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The end taxiway parking point [" ).append( ((TaxiwayPathModel) model).getEnd() ).append( "] does not exist." ).toString() );
                else if( airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) && !airportModel.getTaxiwayParkingHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The end taxiway point [" ).append( ((TaxiwayPathModel) model).getEnd() ).append( "] does not exist." ).toString() );
                else
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "Either the start [" ).append( ((TaxiwayPathModel) model).getStart() ).append( "] or end [" ).append( ((TaxiwayPathModel) model).getEnd() ).append( "] taxiway point does not exist." ).toString() );
            }
            else
            {
                if( !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getStart() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The start taxiway point [" ).append( ((TaxiwayPathModel) model).getStart() ).append( "] does not exist." ).toString() );
                if( !airportModel.getTaxiwayPointHM().containsKey( new Integer( ((TaxiwayPathModel) model).getEnd() ) ) )
                    ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Taxiway Without Taxiway Point", new StringBuilder().append( "The end taxiway point [" ).append( ((TaxiwayPathModel) model).getEnd() ).append( "] does not exist." ).toString() );
            }
        }
    }

    private void runTaxiwayPointTest2()
    {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = airportModel.getTaxiwayPointHM().keySet().iterator();
        Object model;
        int i;

        while( iterator.hasNext() )
        {
            model = (TaxiwayPointModel) airportModel.getTaxiwayPointHM().get( iterator.next() );
            if( ((TaxiwayPointModel) model).getType().indexOf( "HOLD_SHORT" ) < 0 || ((TaxiwayPointModel) model).getOrientation().length() != 0 )
                continue;
            arrayList.add( model );
        }
        iterator = airportModel.getTaxiwayParkingHM().keySet().iterator();
        while( iterator.hasNext() )
        {
            model = (TaxiwayParkingModel) airportModel.getTaxiwayParkingHM().get( iterator.next() );
            if( !((TaxiwayParkingModel) model).getType().equals( "NONE" ) )
                continue;
            arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            if( arrayList.get( i ) instanceof TaxiwayPointModel )
            {
                model = (TaxiwayPointModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Incorrect Taxiway Point Orientation", "Should be either FORWARD or REVERSE." );
            }
            else if( arrayList.get( i ) instanceof TaxiwayParkingModel )
            {
                model = (TaxiwayParkingModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Incorrect Taxiway Parking Type", "Parking spaces should not be of type NONE." );
            }
        }
    }

    private void runAltitudeTest()
    {
        ArrayList arrayList = new ArrayList();
        ArrayList patternAL = new ArrayList();
        ArrayList towerAL = new ArrayList();
        float airportAltitude = (float) airportModel.getAlt();
        int i;
        Object model;
        float runwayAlt;
        float patternAlt;

        if( airportModel.getAltMeasure().equals( "M" ) )
            airportAltitude = airportAltitude * 3.2799999713897705078125F;
        for( i = airportModel.getRunwayAL().size() - 1; i >= 0; --i )
        {
            model = (RunwayModel) airportModel.getRunwayAL().get( i );
            runwayAlt = ((RunwayModel) model).getAlt();
            if( ((RunwayModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            patternAlt = ((RunwayModel) model).getPatternAltitude();
            if( ((RunwayModel) model).getPatternAltitudeMeasure().equals( "M" ) )
                patternAlt = patternAlt * 3.2799999713897705078125F;
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
            if( patternAlt > airportAltitude + 2000.0F )
                patternAL.add( model );
        }
        for( i = airportModel.getStartAL().size() - 1; i >= 0; --i )
        {
            model = (StartModel) airportModel.getStartAL().get( i );
            runwayAlt = (float) ((StartModel) model).getAlt();
            if( ((StartModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = airportModel.getTowerAL().size() - 1; i >= 0; --i )
        {
            model = (TowerModel) airportModel.getTowerAL().get( i );
            runwayAlt = (float) ((TowerModel) model).getAlt();
            if( ((TowerModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( runwayAlt < 0.0F || runwayAlt > 200.0F )
                arrayList.add( model );
            if( ((TowerModel) model).getIncludesScenery() )
            {
                patternAlt = (float) ((TowerModel) model).getSceneryAlt();
                if( ((TowerModel) model).getSceneryAltMeasure().equals( "M" ) )
                    patternAlt = patternAlt * 3.2799999713897705078125F;
                if( ((TowerModel) model).getAltitudeIsAgl() )
                    patternAlt += airportAltitude;
                System.out.println( new StringBuilder().append( "towerSceneryAlt: " ).append( patternAlt ).toString() );
                if( patternAlt != airportAltitude )
                    towerAL.add( model );
            }
        }
        for( i = airportModel.getHelipadAL().size() - 1; i >= 0; --i )
        {
            model = (HelipadModel) airportModel.getHelipadAL().get( i );
            runwayAlt = (float) ((HelipadModel) model).getAlt();
            if( ((HelipadModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = airportModel.getJetwayAL().size() - 1; i >= 0; --i )
        {
            model = (JetwayModel) airportModel.getJetwayAL().get( i );
            runwayAlt = (float) ((JetwayModel) model).getAlt();
            if( ((JetwayModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( ((JetwayModel) model).getAltitudeIsAgl() )
                runwayAlt += airportAltitude;
            if( runwayAlt != airportAltitude )
                arrayList.add( model );
        }
        for( i = airportModel.getSceneryAL().size() - 1; i >= 0; --i )
        {
            model = (SceneryModel) airportModel.getSceneryAL().get( i );
            runwayAlt = (float) ((SceneryModel) model).getAlt();
            if( ((SceneryModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( ((SceneryModel) model).getAltitudeIsAgl() )
                runwayAlt += airportAltitude;
            if( runwayAlt != airportAltitude )
                arrayList.add( model );
        }
        for( i = airportModel.getWindsockAL().size() - 1; i >= 0; --i )
        {
            model = (WindsockModel) airportModel.getWindsockAL().get( i );
            runwayAlt = ((WindsockModel) model).getAlt();
            if( ((WindsockModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( ((WindsockModel) model).getAltitudeIsAgl() )
                runwayAlt += airportAltitude;
            if( runwayAlt != airportAltitude )
                arrayList.add( model );
        }
        for( i = airportModel.getNDBAL().size() - 1; i >= 0; --i )
        {
            model = (NDBModel) airportModel.getNDBAL().get( i );
            runwayAlt = ((NDBModel) model).getAlt();
            if( ((NDBModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = airportModel.getMarkerAL().size() - 1; i >= 0; --i )
        {
            model = (MarkerModel) airportModel.getMarkerAL().get( i );
            runwayAlt = ((MarkerModel) model).getAlt();
            if( ((MarkerModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = airportModel.getVORAL().size() - 1; i >= 0; --i )
        {
            model = (VORModel) airportModel.getVORAL().get( i );
            runwayAlt = ((VORModel) model).getAlt();
            if( ((VORModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            patternAlt = airportAltitude;
            if( ((VORModel) model).getDme() )
            {
                patternAlt = ((VORModel) model).getDMEModel().getAlt();
                if( ((VORModel) model).getDMEModel().getAltMeasure().equals( "M" ) )
                    patternAlt = patternAlt * 3.2799999713897705078125F;
            }
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F || Math.abs( patternAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = airportModel.getILSModels().size() - 1; i >= 0; --i )
        {
            float gsAlt;

            model = (ILSModel) airportModel.getILSModels().get( i );
            runwayAlt = ((ILSModel) model).getAlt();
            if( ((ILSModel) model).getAltMeasure().equals( "M" ) )
                runwayAlt = runwayAlt * 3.2799999713897705078125F;
            patternAlt = airportAltitude;
            if( ((ILSModel) model).getDMEModel() != null )
            {
                patternAlt = ((ILSModel) model).getDMEModel().getAlt();
                if( ((ILSModel) model).getDMEModel().getAltMeasure().equals( "M" ) )
                    patternAlt = patternAlt * 3.2799999713897705078125F;
            }
            gsAlt = airportAltitude;
            if( ((ILSModel) model).getGlideSlopeModel() != null )
            {
                gsAlt = ((ILSModel) model).getGlideSlopeModel().getAlt();
                if( ((ILSModel) model).getGlideSlopeModel().getAltMeasure().equals( "M" ) )
                    gsAlt = gsAlt * 3.2799999713897705078125F;
            }
            if( Math.abs( runwayAlt - airportAltitude ) > 0.002000000094994902610778809F || Math.abs( patternAlt - airportAltitude ) > 0.002000000094994902610778809F || Math.abs( gsAlt - airportAltitude ) > 0.002000000094994902610778809F )
                arrayList.add( model );
        }
        for( i = 0; i < arrayList.size(); ++i )
        {
            if( arrayList.get( i ) instanceof RunwayModel )
            {
                model = (RunwayModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Runway Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof StartModel )
            {
                model = (StartModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Start Location Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof TowerModel )
            {
                model = (TowerModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Tower Viewpoint Altitude Is Either Below Ground Or 200 Feet Or More Above Ground", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof HelipadModel )
            {
                model = (HelipadModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Helipad Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof JetwayModel )
            {
                model = (JetwayModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Jetway Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof SceneryModel )
            {
                model = (SceneryModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Scenery Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof WindsockModel )
            {
                model = (WindsockModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Windsock Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof NDBModel )
            {
                model = (NDBModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "NDB Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof MarkerModel )
            {
                model = (MarkerModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Marker Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof VORModel )
            {
                model = (VORModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "VOR Altitude (or VOR's DME Altitude) Does Not Match Airport Altitude", "This may cause problems." );
            }
            else if( arrayList.get( i ) instanceof ILSModel )
            {
                model = (ILSModel) arrayList.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "ILS Altitude (or ILS's DME or Glide Slope Altitude) Does Not Match Airport Altitude", "This may cause problems." );
            }
        }
        for( i = 0; i < patternAL.size(); ++i )
        {
            if( patternAL.get( i ) instanceof RunwayModel )
            {
                model = (RunwayModel) patternAL.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Pattern Altitude Is More Than 2,000 Feet AGL", "This may cause problems." );
            }
        }
        for( i = 0; i < towerAL.size(); ++i )
        {
            if( towerAL.get( i ) instanceof TowerModel )
            {
                model = (TowerModel) towerAL.get( i );
                ((ErrorCheckingTableModel) resultsTable.getModel()).addRow( (BaseModel) model, "Tower Scenery Altitude Does Not Match Airport Altitude", "This may cause problems." );
            }
        }
    }

    private void selectItem(Point point)
    {
        int row = resultsTable.rowAtPoint( point );
        int column = resultsTable.columnAtPoint( point );

        if( row != -1 && column != -1 )
        {
            BaseModel model = ((ErrorCheckingTableModel) resultsTable.getModel()).getModel( row );

            firePropertyChange( "modelSelected", model, model );
        }
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
        else if( event.getSource() == runButton )
            runTests();
    }

    public void mouseEntered(MouseEvent event)
    {
    }

    public void mouseExited(MouseEvent event)
    {
    }

    public void mouseClicked(MouseEvent event)
    {
        if( event.getSource() == resultsTable && event.getClickCount() == 2 )
            selectItem( event.getPoint() );
    }

    public void mousePressed(MouseEvent event)
    {
    }

    public void mouseReleased(MouseEvent event)
    {
    }

    static JTable access$000(ErrorCheckingDialog x0)
    {
        return x0.resultsTable;
    }

    static void access$100(ErrorCheckingDialog x0)
    {
        x0.runDuplicateParkingSpotTest();
    }

    static void access$200(ErrorCheckingDialog x0)
    {
        x0.runDuplicateJetwayTest();
    }

    static void access$300(ErrorCheckingDialog x0)
    {
        x0.runJetwayTest();
    }

    static void access$400(ErrorCheckingDialog x0)
    {
        x0.runTaxiwayTest1();
    }

    static void access$500(ErrorCheckingDialog x0)
    {
        x0.runTaxiwayTest2();
    }

    static void access$600(ErrorCheckingDialog x0)
    {
        x0.runRunwayTest();
    }

    static void access$700(ErrorCheckingDialog x0)
    {
        x0.runIdentTest();
    }

    static void access$800(ErrorCheckingDialog x0)
    {
        x0.runTaxiwayPointTest();
    }

    static void access$900(ErrorCheckingDialog x0)
    {
        x0.runTaxiwayPointTest2();
    }

    static void access$1000(ErrorCheckingDialog x0)
    {
        x0.runAltitudeTest();
    }

    static void access$1100(ErrorCheckingDialog x0)
    {
        x0.displayCompletedMessage();
    }
}