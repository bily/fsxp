package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.filechooser.XMLFileFilter;
import com.zbluesoftware.fsxp.model.AirportListModel;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.ApproachLightsModel;
import com.zbluesoftware.fsxp.model.ApproachModel;
import com.zbluesoftware.fsxp.model.ApronEdgeLightModel;
import com.zbluesoftware.fsxp.model.ApronModel;
import com.zbluesoftware.fsxp.model.BlastFenceModel;
import com.zbluesoftware.fsxp.model.BoundaryFenceModel;
import com.zbluesoftware.fsxp.model.ComModel;
import com.zbluesoftware.fsxp.model.DMEModel;
import com.zbluesoftware.fsxp.model.DeleteModel;
import com.zbluesoftware.fsxp.model.DmeArcModel;
import com.zbluesoftware.fsxp.model.ExclusionModel;
import com.zbluesoftware.fsxp.model.GlideSlopeModel;
import com.zbluesoftware.fsxp.model.HelipadModel;
import com.zbluesoftware.fsxp.model.HistoryListModel;
import com.zbluesoftware.fsxp.model.ILSModel;
import com.zbluesoftware.fsxp.model.JetwayModel;
import com.zbluesoftware.fsxp.model.LegModel;
import com.zbluesoftware.fsxp.model.LightsModel;
import com.zbluesoftware.fsxp.model.MarkerModel;
import com.zbluesoftware.fsxp.model.MarkingsModel;
import com.zbluesoftware.fsxp.model.NDBModel;
import com.zbluesoftware.fsxp.model.RunwayDetailModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.SceneryModel;
import com.zbluesoftware.fsxp.model.StartModel;
import com.zbluesoftware.fsxp.model.TaxiNameModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathModel;
import com.zbluesoftware.fsxp.model.TaxiwayPointModel;
import com.zbluesoftware.fsxp.model.TaxiwaySignModel;
import com.zbluesoftware.fsxp.model.TowerModel;
import com.zbluesoftware.fsxp.model.TransitionModel;
import com.zbluesoftware.fsxp.model.TriggerModel;
import com.zbluesoftware.fsxp.model.VORModel;
import com.zbluesoftware.fsxp.model.VasiModel;
import com.zbluesoftware.fsxp.model.VertexModel;
import com.zbluesoftware.fsxp.model.WindsockModel;
import com.zbluesoftware.fsxp.model.table.AirportTableModel;
import com.zbluesoftware.fsxp.renderer.DataTableHeaderRenderer;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
//import java.awt.geom.java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zbluesoftware.fsxp.model.table.AirportTableModel;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.w3c.dom.NodeList;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JDialog;
import org.w3c.dom.NodeList;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JDialog;
import org.w3c.dom.NodeList;

class OpenEngine$1 extends MouseAdapter {

    OpenEngine$1(OpenEngine openengine1)
    {
        this$0 = openengine1;
    }

    OpenEngine this$0;     // final access specifier removed

    public void mouseClicked(MouseEvent event)
    {
        int column = ((JTableHeader) event.getSource()).columnAtPoint( event.getPoint() );

        ((AirportTableModel) ((JTableHeader) event.getSource()).getTable().getModel()).sortDataByColumn( column );
    }
}

class OpenEngine$2 implements ActionListener {

    OpenEngine$2(OpenEngine openengine1, JDialog jdialog1)
    {
        this$0 = openengine1;
        val$dialog = jdialog1;
    }

    JDialog val$dialog;     // final access specifier removed
    OpenEngine this$0;     // final access specifier removed

    public void actionPerformed(ActionEvent event)
    {
        val$dialog.setTitle( "Open" );
        val$dialog.setVisible( false );
    }
}

class OpenEngine$3 implements ActionListener {

    OpenEngine$3(OpenEngine openengine1, JDialog jdialog1)
    {
        this$0 = openengine1;
        val$dialog = jdialog1;
    }

    JDialog val$dialog;     // final access specifier removed
    OpenEngine this$0;     // final access specifier removed

    public void actionPerformed(ActionEvent event)
    {
        val$dialog.setTitle( "Cancel" );
        val$dialog.setVisible( false );
    }
}

public class OpenEngine {

    private JDialog progressDialog;
    private JLabel airportDataLabel;
    private JLabel sectionDataLabel;
    private static OpenEngine openEngine = null;
    private Pattern latPattern1 = Pattern.compile( "[n,s]\\d+ \\d+" );
    private Pattern lonPattern1 = Pattern.compile( "[e,w]\\d+ \\d+" );

    public static OpenEngine getInstance()
    {
        if( openEngine == null )
            openEngine = new OpenEngine();
        return openEngine;
    }

    private void createProgressDialog(Frame frame)
    {
        Object airportLabel = new JLabel( "Opening Airport:" );
        Object sectionLabel;
        Object mainPanel;

        ((JLabel) airportLabel).setFont( Utilities.BOLD_LABEL_FONT );
        ((JLabel) airportLabel).setForeground( Color.black );
        airportDataLabel = new JLabel( "---" );
        airportDataLabel.setFont( Utilities.BOLD_LABEL_FONT );
        airportDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        sectionLabel = new JLabel( "Section:" );
        ((JLabel) sectionLabel).setFont( Utilities.BOLD_LABEL_FONT );
        ((JLabel) sectionLabel).setForeground( Color.black );
        sectionDataLabel = new JLabel( "---" );
        sectionDataLabel.setFont( Utilities.BOLD_LABEL_FONT );
        sectionDataLabel.setForeground( Utilities.DATA_HIGHLIGHT_COLOR );
        mainPanel = new JPanel( new GridBagLayout() );
        Utilities.addComponent( (Container) mainPanel, (Component) airportLabel, 0, 0, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) airportDataLabel, 1, 0, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sectionLabel, 0, 1, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sectionDataLabel, 1, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        progressDialog = new JDialog( frame, "Progress", false );
        progressDialog.getContentPane().add( (Component) mainPanel, "Center" );
        progressDialog.setResizable( false );
        progressDialog.setSize( new Dimension( 300, 100 ) );
        progressDialog.setLocationRelativeTo( frame );
    }

    public ArrayList openAirport(Frame frame)
    {
        ArrayList identAL = new ArrayList();
        JFileChooser fileChooser;

        createProgressDialog( frame );
        fileChooser = new JFileChooser( SettingsEngine.getInstance().getLastOpenedDir() );
        fileChooser.setDialogTitle( "Open Airport XML" );
        fileChooser.setAcceptAllFileFilterUsed( false );
        fileChooser.setFileFilter( (FileFilter) new XMLFileFilter() );
        if( fileChooser.showOpenDialog( frame ) == 0 )
        {
            fileChooser.setCursor( new Cursor( 3 ) );
            frame.setCursor( new Cursor( 3 ) );
            parseXMLFile( fileChooser.getSelectedFile(), frame, identAL );
            frame.setCursor( new Cursor( 0 ) );
            SettingsEngine.getInstance().setLastOpenedDir( fileChooser.getSelectedFile().getParent() );
            SettingsEngine.getInstance().writePreferences();
            RecentFileEngine.getInstance().newFileOpened( fileChooser.getSelectedFile(), identAL );
        }
        return identAL;
    }

    public ArrayList openRecentFile(Frame frame, HashMap hashMap)
    {
        ArrayList identAL = new ArrayList();
        File file;

        identAL.add( hashMap );
        hashMap.put( "fileName", hashMap.get( "file" ) );
        createProgressDialog( frame );
        file = new File( (String) hashMap.get( "file" ) );
        frame.setCursor( new Cursor( 3 ) );
        parseXMLFile( file, frame, identAL );
        frame.setCursor( new Cursor( 0 ) );
        RecentFileEngine.getInstance().newFileOpened( file, identAL );
        return identAL;
    }
    private void parseXMLFile(File xmlFile, Frame frame, ArrayList identAL)
    {
	try {
        String fileName;
        Element fsDataElement;
        NodeList airports;
        fileName = xmlFile.getAbsolutePath().replaceAll("#", "%23");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(false);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xmlDoc = builder.parse(new File(fileName));
        NodeList fsDataElements = xmlDoc.getElementsByTagName("FSData");
        fsDataElement = (Element)fsDataElements.item(0);
        airports = xmlDoc.getElementsByTagName("Airport");
        if(airports.getLength() == 0)
        {
            JOptionPane.showMessageDialog(frame, "There are no airports in this XML file.", "No Airports...", 0);
            return;
        }
        try
        {
            if(identAL.size() == 0)
                if(airports.getLength() == 1)
                {
                    HashMap hashMap = new HashMap();
                    hashMap.put("ident", ((Element)airports.item(0)).getAttribute("ident"));
                    hashMap.put("fileName", xmlFile.getAbsolutePath());
                    identAL.add(hashMap);
                } else
                {
                    selectAirports(xmlFile, airports, frame, identAL);
                }
            setProgressDialogVisible(true);
            HistoryListModel.getInstance().setRecordChanges(false);
            if(identAL.size() > 0)
            {
                for(int i = 0; i < airports.getLength(); i++)
                    parseAirport(fsDataElement, (Element)airports.item(i), fileName, identAL);

            }
            HistoryListModel.getInstance().setRecordChanges(true);
            setProgressDialogVisible(false);
        }
		catch (Exception e) {}
	}
        catch(SAXException saxe)
        {
            LogEngine.getInstance().log(saxe);
            JOptionPane.showMessageDialog(frame, saxe.getMessage(), "Error...", 0);
        }
        catch(IOException ioe)
        {
            LogEngine.getInstance().log(ioe);
            JOptionPane.showMessageDialog(frame, ioe.getMessage(), "Error...", 0);
        }
        catch(ParserConfigurationException pce)
        {
            LogEngine.getInstance().log(pce);
            JOptionPane.showMessageDialog(frame, pce.getMessage(), "Error...", 0);
        }
        return;
    }

    public void parseXMLDocument(Document xmlDoc, Frame frame, ArrayList identAL)
    {
        NodeList fsDataElements;
        Element fsDataElement;
        NodeList airports;

        createProgressDialog( frame );
        fsDataElements = xmlDoc.getElementsByTagName( "FSData" );
        fsDataElement = (Element) fsDataElements.item( 0 );
        airports = xmlDoc.getElementsByTagName( "Airport" );
        if( airports.getLength() == 0 )
            JOptionPane.showMessageDialog( frame, "There are no airports in this XML file.", "No Airports...", 0 );
        else
        {
            if( identAL.size() == 0 )
            {
                if( airports.getLength() == 1 )
                {
                    Object hashMap = new HashMap();

                    ((HashMap) hashMap).put( "ident", ((Element) airports.item( 0 )).getAttribute( "ident" ) );
                    ((HashMap) hashMap).put( "fileName", "" );
                    identAL.add( hashMap );
                }
                else
                    selectAirports( null, airports, frame, identAL );
            }
            setProgressDialogVisible( true );
            HistoryListModel.getInstance().setRecordChanges( false );
            if( identAL.size() > 0 )
            {
                int i;

                for( i = 0; i < airports.getLength(); ++i )
                    parseAirport( fsDataElement, (Element) airports.item( i ), "", identAL );
            }
            HistoryListModel.getInstance().setRecordChanges( true );
            setProgressDialogVisible( false );
        }
    }

    private void setProgressDialogVisible(boolean status)
    {
        progressDialog.setVisible( status );
    }

    private void setAirportDataLabel(String text)
    {
        airportDataLabel.setText( text );
    }

    public void setSectionDataLabel(String text)
    {
        sectionDataLabel.setText( text );
    }

    private void selectAirports(File xmlFile, NodeList airports, Frame frame, ArrayList identAL)
    {
        JDialog dialog;
        Object multipleLabel;
        Object selectLabel;
        Object airportTable;
        Object airportSP;
        Object mainPanel;
        Object openButton;
        Object cancelButton;
        Object buttonPanel;

        setProgressDialogVisible( true );
        setAirportDataLabel( "Scanning File..." );
        dialog = new JDialog( frame, "Select Airport(s) From File", true );
        dialog.setResizable( false );
        multipleLabel = new JLabel( "This XML file contains multiple airports." );
        ((JLabel) multipleLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) multipleLabel).setForeground( Color.black );
        selectLabel = new JLabel( "Select the airport(s) you wish to open by ticking the appropriate checkbox(es)." );
        ((JLabel) selectLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) selectLabel).setForeground( Color.black );
        airportTable = new JTable( (TableModel) new AirportTableModel( airports ) );
        ((JTable) airportTable).setPreferredScrollableViewportSize( new Dimension( 400, 200 ) );
        ((JTable) airportTable).setAutoResizeMode( 0 );
        ((JTable) airportTable).getColumnModel().getColumn( 0 ).setPreferredWidth( 75 );
        ((JTable) airportTable).getColumnModel().getColumn( 1 ).setPreferredWidth( 275 );
        ((JTable) airportTable).getColumnModel().getColumn( 2 ).setPreferredWidth( 50 );
        ((JTable) airportTable).getTableHeader().setReorderingAllowed( false );
        ((JTable) airportTable).getTableHeader().setDefaultRenderer( (TableCellRenderer) new DataTableHeaderRenderer() );
        ((JTable) airportTable).getTableHeader().addMouseListener( (MouseListener) new OpenEngine$1( this ) );
        airportSP = new JScrollPane( (Component) airportTable );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) multipleLabel, 0, 0, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) selectLabel, 0, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) airportSP, 0, 2, 1, 1, 1, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.5 );
        openButton = new JButton( "Open" );
        ((JButton) openButton).setFont( Utilities.DIALOG_BUTTON_FONT );
        ((JButton) openButton).setForeground( Color.black );
        ((JButton) openButton).addActionListener( (ActionListener) new OpenEngine$2( this, dialog ) );
        cancelButton = new JButton( "Cancel" );
        ((JButton) cancelButton).setFont( Utilities.DIALOG_BUTTON_FONT );
        ((JButton) cancelButton).setForeground( Color.black );
        ((JButton) cancelButton).addActionListener( (ActionListener) new OpenEngine$3( this, dialog ) );
        buttonPanel = new JPanel();
        ((JPanel) buttonPanel).add( (Component) openButton );
        ((JPanel) buttonPanel).add( (Component) cancelButton );
        setProgressDialogVisible( false );
        dialog.getContentPane().add( (Component) mainPanel, "Center" );
        dialog.getContentPane().add( (Component) buttonPanel, "South" );
        dialog.pack();
        dialog.setLocationRelativeTo( frame );
        dialog.setVisible( true );
        if( dialog.getTitle().equals( "Open" ) )
        {
            ArrayList arrayList = ((AirportTableModel) ((JTable) airportTable).getModel()).getSelectedAirports();
            int i;

            for( i = 0; i < arrayList.size(); ++i )
            {
                Object hashMap = new HashMap();

                ((HashMap) hashMap).put( "ident", (String) arrayList.get( i ) );
                if( xmlFile != null )
                    ((HashMap) hashMap).put( "fileName", xmlFile.getAbsolutePath() );
                else
                    ((HashMap) hashMap).put( "fileName", "" );
                identAL.add( hashMap );
            }
        }
    }

    private void parseAirport(Element fsDataElement, Element airportElement, String fileName, ArrayList airportAL)
    {
        String ident = airportElement.getAttribute( "ident" );
        int openFile = 0;
        int i = airportAL.size() - 1;

        while( i >= 0 )
        {
            if( ((String) ((HashMap) airportAL.get( i )).get( "ident" )).equals( ident ) )
            {
                openFile = 1;
                break;
            }
            else
                --i;
        }
        if( openFile != 0 )
        {
            if( AirportListModel.getInstance().getAirportModel( ident, fileName ) == null )
            {
                AirportModel airportmodel1;
                Object obj1;
                Object obj;

                setAirportDataLabel( ident );
                airportmodel1 = new AirportModel( new LatLonPoint( getLat( airportElement.getAttribute( "lat" ) ), getLon( airportElement.getAttribute( "lon" ) ) ) );
                airportmodel1.setFileName( fileName );
                airportmodel1.setIdent( ident );
                if( airportElement.hasAttribute( "region" ) )
                    airportmodel1.setRegion( airportElement.getAttribute( "region" ) );
                if( airportElement.hasAttribute( "country" ) )
                    airportmodel1.setCountry( airportElement.getAttribute( "country" ) );
                if( airportElement.hasAttribute( "state" ) )
                    airportmodel1.setState( airportElement.getAttribute( "state" ) );
                if( airportElement.hasAttribute( "city" ) )
                    airportmodel1.setCity( airportElement.getAttribute( "city" ) );
                if( airportElement.hasAttribute( "name" ) )
                    airportmodel1.setName( airportElement.getAttribute( "name" ) );
                if( airportElement.hasAttribute( "magvar" ) )
                {
                    try
                    {
                        airportmodel1.setMagvar( Float.parseFloat( airportElement.getAttribute( "magvar" ) ) );
                    }
                    catch( NumberFormatException nfe )
                    {
                        LogEngine.getInstance().log( nfe );
                    }
                }
                if( airportElement.hasAttribute( "trafficScalar" ) )
                {
                    try
                    {
                        airportmodel1.setTrafficScalar( Float.parseFloat( airportElement.getAttribute( "trafficScalar" ) ) );
                    }
                    catch( NumberFormatException numberformatexception1 )
                    {
                        LogEngine.getInstance().log( numberformatexception1 );
                    }
                }
                obj1 = getDistanceMeasurement( airportElement.getAttribute( "alt" ), new String[] { "M", "F" } );
                try
                {
                    airportmodel1.setAlt( Double.parseDouble( (String) ((HashMap) obj1).get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
                airportmodel1.setAltMeasure( (String) ((HashMap) obj1).get( "measure" ) );
                if( airportElement.hasAttribute( "airportTestRadius" ) )
                {
                    obj1 = getDistanceMeasurement( airportElement.getAttribute( "airportTestRadius" ), new String[] { "M", "F", "N" } );
                    try
                    {
                        airportmodel1.setAirportTestRadius( Double.parseDouble( (String) ((HashMap) obj1).get( "distance" ) ) );
                    }
                    catch( NumberFormatException numberformatexception2 )
                    {
                        LogEngine.getInstance().log( numberformatexception2 );
                    }
                    airportmodel1.setAirportTestRadiusMeasure( (String) ((HashMap) obj1).get( "measure" ) );
                }
                AirportListModel.getInstance().addAirportModel( airportmodel1 );
                if( SettingsEngine.getInstance().getWriteDeletes() )
                {
                    setSectionDataLabel( "Deletes" );
                    obj = airportElement.getElementsByTagName( "DeleteAirport" );
                    for( i = 0; i < ((NodeList) obj).getLength(); ++i )
                        parseDeletes( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                }
                setSectionDataLabel( "Runways" );
                obj = airportElement.getElementsByTagName( "Runway" );
                for( i = 0; i < ((NodeList) obj).getLength(); ++i )
                    parseRunway( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Taxiway Points" );
                obj = airportElement.getElementsByTagName( "TaxiwayPoint" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTaxiwayPoint( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Taxiway Parking" );
                obj = airportElement.getElementsByTagName( "TaxiwayParking" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTaxiwayParking( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Aprons" );
                obj = airportElement.getElementsByTagName( "Apron" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseApron( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Taxiway Signs" );
                obj = airportElement.getElementsByTagName( "TaxiwaySign" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTaxiwaySign( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Towers" );
                obj = airportElement.getElementsByTagName( "Tower" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTower( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Boundary Fences" );
                obj = airportElement.getElementsByTagName( "BoundaryFence" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseBoundaryFence( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Blast Fences" );
                obj = airportElement.getElementsByTagName( "BlastFence" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseBlastFence( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Taxiway Paths" );
                obj = airportElement.getElementsByTagName( "TaxiwayPath" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTaxiwayPath( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Start Points" );
                obj = airportElement.getElementsByTagName( "Start" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseStartPoint( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Helipads" );
                obj = airportElement.getElementsByTagName( "Helipad" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseHelipad( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Apron Edge Lights" );
                obj = airportElement.getElementsByTagName( "ApronEdgeLights" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseApronEdgeLight( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Taxi names" );
                obj = airportElement.getElementsByTagName( "TaxiName" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTaxiName( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Jetways" );
                obj = airportElement.getElementsByTagName( "Jetway" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseJetway( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "COMs" );
                obj = airportElement.getElementsByTagName( "Com" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseCom( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Approaches" );
                obj = airportElement.getElementsByTagName( "Approach" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseApproach( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "VORs" );
                obj = fsDataElement.getElementsByTagName( "Vor" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseVOR( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "NDBs" );
                obj = fsDataElement.getElementsByTagName( "Ndb" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                {
                    if( ((Element) ((NodeList) obj).item( i )).getParentNode() == fsDataElement )
                        parseNDB( (Element) ((NodeList) obj).item( i ), airportmodel1, false );
                }
                setSectionDataLabel( "NDBs" );
                obj = airportElement.getElementsByTagName( "Ndb" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseNDB( (Element) ((NodeList) obj).item( i ), airportmodel1, true );
                setSectionDataLabel( "Markers" );
                obj = fsDataElement.getElementsByTagName( "Marker" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseMarker( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Windsocks" );
                obj = fsDataElement.getElementsByTagName( "Windsock" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseWindsock( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Triggers" );
                obj = fsDataElement.getElementsByTagName( "Trigger" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseTrigger( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                setSectionDataLabel( "Scenery Objects" );
                obj = fsDataElement.getElementsByTagName( "SceneryObject" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseScenery( (Element) ((NodeList) obj).item( i ), fsDataElement, airportmodel1 );
                setSectionDataLabel( "Services" );
                obj = airportElement.getElementsByTagName( "Services" );
                for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    parseServices( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                if( SettingsEngine.getInstance().getWriteExcludes() )
                {
                    setSectionDataLabel( "Exclusion Rectangles" );
                    obj = fsDataElement.getElementsByTagName( "ExclusionRectangle" );
                    for( i = 0; i < ((NodeList) obj).getLength(); ++i )
                        parseExcludes( (Element) ((NodeList) obj).item( i ), airportmodel1 );
                }
                if( SettingsEngine.getInstance().getWriteBGImages() )
                {
                    setSectionDataLabel( "Background Images" );
                    obj = airportElement.getChildNodes();
                    for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
                    {
                        if( ((NodeList) obj).item( i ).getNodeType() == 8 )
                            parseBackgroundImage( (Comment) ((NodeList) obj).item( i ), airportmodel1 );
                    }
                }
                else
                {
                    setSectionDataLabel( "Background Images" );
                    airportmodel1.setBGImageAL( BackgroundImageEngine.getInstance().getBGImages( airportmodel1.getName(), airportmodel1.getIdent() ) );
                }
            }
        }
    }

    private void parseDeletes(Element deleteElement, AirportModel airportModel)
    {
        DeleteModel deleteModel = airportModel.getDeleteModel();
        NodeList nodeList;
        int i;
        Element deleteRunwayElement;
        Object hashMap;

        if( deleteElement.hasAttribute( "deleteAllApproaches" ) )
            deleteModel.setDeleteAllApproaches( getBoolean( deleteElement.getAttribute( "deleteAllApproaches" ) ) );
        else
            deleteModel.setDeleteAllApproaches( false );
        if( deleteElement.hasAttribute( "deleteAllApronLights" ) )
            deleteModel.setDeleteAllApronLights( getBoolean( deleteElement.getAttribute( "deleteAllApronLights" ) ) );
        else
            deleteModel.setDeleteAllApronLights( false );
        if( deleteElement.hasAttribute( "deleteAllAprons" ) )
            deleteModel.setDeleteAllAprons( getBoolean( deleteElement.getAttribute( "deleteAllAprons" ) ) );
        else
            deleteModel.setDeleteAllAprons( false );
        if( deleteElement.hasAttribute( "deleteAllFrequencies" ) )
            deleteModel.setDeleteAllFrequencies( getBoolean( deleteElement.getAttribute( "deleteAllFrequencies" ) ) );
        else
            deleteModel.setDeleteAllFrequencies( false );
        if( deleteElement.hasAttribute( "deleteAllHelipads" ) )
            deleteModel.setDeleteAllHelipads( getBoolean( deleteElement.getAttribute( "deleteAllHelipads" ) ) );
        else
            deleteModel.setDeleteAllHelipads( false );
        if( deleteElement.hasAttribute( "deleteAllRunways" ) )
            deleteModel.setDeleteAllRunways( getBoolean( deleteElement.getAttribute( "deleteAllRunways" ) ) );
        else
            deleteModel.setDeleteAllRunways( false );
        if( deleteElement.hasAttribute( "deleteAllStarts" ) )
            deleteModel.setDeleteAllStarts( getBoolean( deleteElement.getAttribute( "deleteAllStarts" ) ) );
        else
            deleteModel.setDeleteAllStarts( false );
        if( deleteElement.hasAttribute( "deleteAllTaxiways" ) )
            deleteModel.setDeleteAllTaxiways( getBoolean( deleteElement.getAttribute( "deleteAllTaxiways" ) ) );
        else
            deleteModel.setDeleteAllTaxiways( false );
        if( deleteElement.hasAttribute( "deleteAllBlastFences" ) )
            deleteModel.setDeleteAllBlastFences( getBoolean( deleteElement.getAttribute( "deleteAllBlastFences" ) ) );
        else
            deleteModel.setDeleteAllBlastFences( false );
        if( deleteElement.hasAttribute( "deleteAllBoundaryFences" ) )
            deleteModel.setDeleteAllBoundaryFences( getBoolean( deleteElement.getAttribute( "deleteAllBoundaryFences" ) ) );
        else
            deleteModel.setDeleteAllBoundaryFences( false );
        if( deleteElement.hasAttribute( "deleteAllControlTowers" ) )
            deleteModel.setDeleteAllControlTowers( getBoolean( deleteElement.getAttribute( "deleteAllControlTowers" ) ) );
        else
            deleteModel.setDeleteAllControlTowers( false );
        if( deleteElement.hasAttribute( "deleteAllJetways" ) )
            deleteModel.setDeleteAllJetways( getBoolean( deleteElement.getAttribute( "deleteAllJetways" ) ) );
        else
            deleteModel.setDeleteAllJetways( false );
        nodeList = deleteElement.getElementsByTagName( "DeleteRunway" );
        for( i = 0; i < nodeList.getLength(); ++i )
        {
            deleteRunwayElement = (Element) nodeList.item( i );
            hashMap = new HashMap();
            ((HashMap) hashMap).put( "surface", deleteRunwayElement.getAttribute( "surface" ) );
            ((HashMap) hashMap).put( "number", deleteRunwayElement.getAttribute( "number" ) );
            ((HashMap) hashMap).put( "designator", deleteRunwayElement.getAttribute( "designator" ) );
            deleteModel.getRunwayAL().add( hashMap );
        }
        nodeList = deleteElement.getElementsByTagName( "DeleteStart" );
        for( i = 0; i < nodeList.getLength(); ++i )
        {
            deleteRunwayElement = (Element) nodeList.item( i );
            hashMap = new HashMap();
            ((HashMap) hashMap).put( "type", deleteRunwayElement.getAttribute( "type" ) );
            ((HashMap) hashMap).put( "number", deleteRunwayElement.getAttribute( "number" ) );
            ((HashMap) hashMap).put( "designator", deleteRunwayElement.getAttribute( "designator" ) );
            deleteModel.getStartAL().add( hashMap );
        }
        nodeList = deleteElement.getElementsByTagName( "DeleteFrequency" );
        for( i = 0; i < nodeList.getLength(); ++i )
        {
            deleteRunwayElement = (Element) nodeList.item( i );
            hashMap = new HashMap();
            ((HashMap) hashMap).put( "frequency", deleteRunwayElement.getAttribute( "frequency" ) );
            ((HashMap) hashMap).put( "type", deleteRunwayElement.getAttribute( "type" ) );
            deleteModel.getFrequencyAL().add( hashMap );
        }
    }

    private void parseRunway(Element runwayElement, AirportModel airportModel)
    {
        RunwayModel runwayModel = new RunwayModel();
        int i;
        HashMap hashmap1;
        Object obj1;
        Object obj;

        runwayModel.setLatLon( new LatLonPoint( getLat( runwayElement.getAttribute( "lat" ) ), getLon( runwayElement.getAttribute( "lon" ) ) ) );
        runwayModel.setSurface( runwayElement.getAttribute( "surface" ) );
        try
        {
            runwayModel.setHeading( Float.parseFloat( runwayElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        runwayModel.setNumber( runwayElement.getAttribute( "number" ) );
        if( runwayElement.hasAttribute( "designator" ) )
            runwayModel.setDesignator( runwayElement.getAttribute( "designator" ) );
        if( runwayElement.hasAttribute( "primaryDesignator" ) )
            runwayModel.setPrimaryDesignator( runwayElement.getAttribute( "primaryDesignator" ) );
        if( runwayElement.hasAttribute( "secondaryDesignator" ) )
            runwayModel.setSecondaryDesignator( runwayElement.getAttribute( "secondaryDesignator" ) );
        if( runwayElement.hasAttribute( "primaryTakeoff" ) )
            runwayModel.setPrimaryTakeoff( getBoolean( runwayElement.getAttribute( "primaryTakeoff" ) ) );
        if( runwayElement.hasAttribute( "primaryLanding" ) )
            runwayModel.setPrimaryLanding( getBoolean( runwayElement.getAttribute( "primaryLanding" ) ) );
        if( runwayElement.hasAttribute( "primaryPattern" ) )
            runwayModel.setPrimaryPattern( runwayElement.getAttribute( "primaryPattern" ) );
        if( runwayElement.hasAttribute( "secondaryTakeoff" ) )
            runwayModel.setSecondaryTakeoff( getBoolean( runwayElement.getAttribute( "secondaryTakeoff" ) ) );
        if( runwayElement.hasAttribute( "secondaryLanding" ) )
            runwayModel.setSecondaryLanding( getBoolean( runwayElement.getAttribute( "secondaryLanding" ) ) );
        if( runwayElement.hasAttribute( "secondaryPattern" ) )
            runwayModel.setSecondaryPattern( runwayElement.getAttribute( "secondaryPattern" ) );
        hashmap1 = getDistanceMeasurement( runwayElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        try
        {
            runwayModel.setAlt( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        runwayModel.setAltMeasure( (String) hashmap1.get( "measure" ) );
        obj1 = getDistanceMeasurement( runwayElement.getAttribute( "length" ), new String[] { "M", "F" } );
        try
        {
            runwayModel.setLength( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        runwayModel.setLengthMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        obj1 = getDistanceMeasurement( runwayElement.getAttribute( "width" ), new String[] { "M", "F" } );
        try
        {
            runwayModel.setWidth( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
        }
        catch( NumberFormatException numberformatexception2 )
        {
            LogEngine.getInstance().log( numberformatexception2 );
        }
        runwayModel.setWidthMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        if( runwayElement.hasAttribute( "patternAltitude" ) )
        {
            obj1 = getDistanceMeasurement( runwayElement.getAttribute( "patternAltitude" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
            {
                try
                {
                    runwayModel.setPatternAltitude( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception3 )
                {
                    LogEngine.getInstance().log( numberformatexception3 );
                }
            }
            runwayModel.setPatternAltitudeMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        }
        obj1 = getDistanceMeasurement( runwayElement.getAttribute( "primaryMarkingBias" ), new String[] { "M", "F", "N" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                runwayModel.setPrimaryMarkingBias( Double.parseDouble( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception4 )
            {
                LogEngine.getInstance().log( numberformatexception4 );
            }
        }
        runwayModel.setPrimaryMarkingBiasMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        obj1 = getDistanceMeasurement( runwayElement.getAttribute( "secondaryMarkingBias" ), new String[] { "M", "F", "N" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                runwayModel.setSecondaryMarkingBias( Double.parseDouble( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception5 )
            {
                LogEngine.getInstance().log( numberformatexception5 );
            }
        }
        runwayModel.setSecondaryMarkingBiasMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        airportModel.addRunwayModel( runwayModel );
        obj = runwayElement.getElementsByTagName( "Markings" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayMarkings( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "Lights" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayLights( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "OffsetThreshold" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayOffsetThreshold( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "BlastPad" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayBlastPad( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "ApproachLights" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayApproachLights( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "Vasi" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayVasi( (Element) ((NodeList) obj).item( i ), runwayModel );
        obj = runwayElement.getElementsByTagName( "Ils" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseRunwayILS( (Element) ((NodeList) obj).item( i ), runwayModel );
    }

    private void parseRunwayMarkings(Element markingsElement, RunwayModel runwayModel)
    {
        MarkingsModel markingsModel = new MarkingsModel();

        markingsModel.setAlternateThreshold( getBoolean( markingsElement.getAttribute( "alternateThreshold" ) ) );
        markingsModel.setAlternateTouchdown( getBoolean( markingsElement.getAttribute( "alternateTouchdown" ) ) );
        markingsModel.setAlternateFixedDistance( getBoolean( markingsElement.getAttribute( "alternateFixedDistance" ) ) );
        markingsModel.setAlternatePrecision( getBoolean( markingsElement.getAttribute( "alternatePrecision" ) ) );
        markingsModel.setLeadingZeroIdent( getBoolean( markingsElement.getAttribute( "leadingZeroIdent" ) ) );
        markingsModel.setNoThresholdEndArrows( getBoolean( markingsElement.getAttribute( "noThresholdEndArrows" ) ) );
        markingsModel.setEdges( getBoolean( markingsElement.getAttribute( "edges" ) ) );
        markingsModel.setThreshold( getBoolean( markingsElement.getAttribute( "threshold" ) ) );
        markingsModel.setFixed( getBoolean( markingsElement.getAttribute( "fixedDistance" ) ) );
        markingsModel.setTouchdown( getBoolean( markingsElement.getAttribute( "touchdown" ) ) );
        markingsModel.setDashes( getBoolean( markingsElement.getAttribute( "dashes" ) ) );
        markingsModel.setIdent( getBoolean( markingsElement.getAttribute( "ident" ) ) );
        markingsModel.setPrecision( getBoolean( markingsElement.getAttribute( "precision" ) ) );
        markingsModel.setEdgePavement( getBoolean( markingsElement.getAttribute( "edgePavement" ) ) );
        markingsModel.setSingleEnd( getBoolean( markingsElement.getAttribute( "singleEnd" ) ) );
        markingsModel.setPrimaryClosed( getBoolean( markingsElement.getAttribute( "primaryClosed" ) ) );
        markingsModel.setSecondaryClosed( getBoolean( markingsElement.getAttribute( "secondaryClosed" ) ) );
        markingsModel.setPrimaryStol( getBoolean( markingsElement.getAttribute( "primaryStol" ) ) );
        markingsModel.setSecondaryStol( getBoolean( markingsElement.getAttribute( "secondaryStol" ) ) );
        runwayModel.setMarkingsModel( markingsModel );
    }

    private void parseRunwayLights(Element lightsElement, RunwayModel runwayModel)
    {
        LightsModel lightsModel = new LightsModel();

        if( lightsElement.hasAttribute( "center" ) && lightsElement.getAttribute( "center" ).length() > 0 )
            lightsModel.setCenter( lightsElement.getAttribute( "center" ) );
        if( lightsElement.hasAttribute( "edge" ) && lightsElement.getAttribute( "edge" ).length() > 0 )
            lightsModel.setEdge( lightsElement.getAttribute( "edge" ) );
        if( lightsElement.hasAttribute( "centerRed" ) && lightsElement.getAttribute( "centerRed" ).length() > 0 )
            lightsModel.setCenterRed( getBoolean( lightsElement.getAttribute( "centerRed" ) ) );
        runwayModel.setLightsModel( lightsModel );
    }

    private void parseRunwayOffsetThreshold(Element offsetThresholdElement, RunwayModel runwayModel)
    {
        RunwayDetailModel offsetThresholdModel = new RunwayDetailModel();
        HashMap distanceHM;

        offsetThresholdModel.setEnd( offsetThresholdElement.getAttribute( "end" ) );
        if( offsetThresholdElement.hasAttribute( "surface" ) )
            offsetThresholdModel.setSurface( offsetThresholdElement.getAttribute( "surface" ) );
        distanceHM = getDistanceMeasurement( offsetThresholdElement.getAttribute( "length" ), new String[] { "M", "F" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                offsetThresholdModel.setLength( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        offsetThresholdModel.setLengthMeasure( (String) distanceHM.get( "measure" ) );
        if( offsetThresholdElement.hasAttribute( "width" ) )
        {
            distanceHM = getDistanceMeasurement( offsetThresholdElement.getAttribute( "width" ), new String[] { "M", "F" } );
            if( ((String) distanceHM.get( "distance" )).length() > 0 )
            {
                try
                {
                    offsetThresholdModel.setWidth( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
            }
            offsetThresholdModel.setWidthMeasure( (String) distanceHM.get( "measure" ) );
        }
        if( offsetThresholdModel.getEnd().equals( "PRIMARY" ) )
            runwayModel.setPrimaryOffsetModel( offsetThresholdModel );
        else if( offsetThresholdModel.getEnd().equals( "SECONDARY" ) )
            runwayModel.setSecondaryOffsetModel( offsetThresholdModel );
    }

    private void parseRunwayBlastPad(Element blastPadElement, RunwayModel runwayModel)
    {
        RunwayDetailModel blastPadModel = new RunwayDetailModel();
        HashMap distanceHM;

        blastPadModel.setEnd( blastPadElement.getAttribute( "end" ) );
        if( blastPadElement.hasAttribute( "surface" ) )
            blastPadModel.setSurface( blastPadElement.getAttribute( "surface" ) );
        distanceHM = getDistanceMeasurement( blastPadElement.getAttribute( "length" ), new String[] { "M", "F" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                blastPadModel.setLength( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        blastPadModel.setLengthMeasure( (String) distanceHM.get( "measure" ) );
        if( blastPadElement.hasAttribute( "width" ) )
        {
            distanceHM = getDistanceMeasurement( blastPadElement.getAttribute( "width" ), new String[] { "M", "F" } );
            if( ((String) distanceHM.get( "distance" )).length() > 0 )
            {
                try
                {
                    blastPadModel.setWidth( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
            }
            blastPadModel.setWidthMeasure( (String) distanceHM.get( "measure" ) );
        }
        if( blastPadModel.getEnd().equals( "PRIMARY" ) )
            runwayModel.setPrimaryBlastPadModel( blastPadModel );
        else if( blastPadModel.getEnd().equals( "SECONDARY" ) )
            runwayModel.setSecondaryBlastPadModel( blastPadModel );
    }

    private void parseRunwayOverrun(Element overrunElement, RunwayModel runwayModel)
    {
        RunwayDetailModel overrunModel = new RunwayDetailModel();
        HashMap distanceHM;

        overrunModel.setEnd( overrunElement.getAttribute( "end" ) );
        if( overrunElement.hasAttribute( "surface" ) )
            overrunModel.setSurface( overrunElement.getAttribute( "surface" ) );
        distanceHM = getDistanceMeasurement( overrunElement.getAttribute( "length" ), new String[] { "M", "F" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                overrunModel.setLength( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        overrunModel.setLengthMeasure( (String) distanceHM.get( "measure" ) );
        if( overrunElement.hasAttribute( "width" ) )
        {
            distanceHM = getDistanceMeasurement( overrunElement.getAttribute( "width" ), new String[] { "M", "F" } );
            if( ((String) distanceHM.get( "distance" )).length() > 0 )
            {
                try
                {
                    overrunModel.setWidth( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
            }
            overrunModel.setWidthMeasure( (String) distanceHM.get( "measure" ) );
        }
        if( overrunModel.getEnd().equals( "PRIMARY" ) )
            runwayModel.setPrimaryOverrunModel( overrunModel );
        else if( overrunModel.getEnd().equals( "SECONDARY" ) )
            runwayModel.setSecondaryOverrunModel( overrunModel );
    }

    private void parseRunwayApproachLights(Element approachLightsElement, RunwayModel runwayModel)
    {
        ApproachLightsModel approachLightsModel = new ApproachLightsModel();

        approachLightsModel.setEnd( approachLightsElement.getAttribute( "end" ) );
        if( approachLightsElement.hasAttribute( "system" ) )
            approachLightsModel.setSystem( approachLightsElement.getAttribute( "system" ) );
        if( approachLightsElement.hasAttribute( "strobes" ) )
        {
            try
            {
                approachLightsModel.setStrobes( Integer.parseInt( approachLightsElement.getAttribute( "strobes" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        if( approachLightsElement.hasAttribute( "reil" ) )
            approachLightsModel.setReil( getBoolean( approachLightsElement.getAttribute( "reil" ) ) );
        if( approachLightsElement.hasAttribute( "touchdown" ) )
            approachLightsModel.setTouchdown( getBoolean( approachLightsElement.getAttribute( "touchdown" ) ) );
        if( approachLightsElement.hasAttribute( "endLights" ) )
            approachLightsModel.setEndLights( getBoolean( approachLightsElement.getAttribute( "endLights" ) ) );
        if( approachLightsModel.getEnd().equals( "PRIMARY" ) )
            runwayModel.setPrimaryApproachLightsModel( approachLightsModel );
        else if( approachLightsModel.getEnd().equals( "SECONDARY" ) )
            runwayModel.setSecondaryApproachLightsModel( approachLightsModel );
    }

    private void parseRunwayVasi(Element vasiElement, RunwayModel runwayModel)
    {
        VasiModel vasiModel = new VasiModel();
        HashMap hashmap1;
        Object obj;

        vasiModel.setEnd( vasiElement.getAttribute( "end" ) );
        vasiModel.setType( vasiElement.getAttribute( "type" ) );
        vasiModel.setSide( vasiElement.getAttribute( "side" ) );
        try
        {
            vasiModel.setPitch( Float.parseFloat( vasiElement.getAttribute( "pitch" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        hashmap1 = getDistanceMeasurement( vasiElement.getAttribute( "biasX" ), new String[] { "M", "F" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                vasiModel.setBiasX( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        vasiModel.setBiasXMeasure( (String) hashmap1.get( "measure" ) );
        obj = getDistanceMeasurement( vasiElement.getAttribute( "biasZ" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                vasiModel.setBiasZ( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        vasiModel.setBiasZMeasure( (String) ((HashMap) obj).get( "measure" ) );
        obj = getDistanceMeasurement( vasiElement.getAttribute( "spacing" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                vasiModel.setSpacing( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
        }
        vasiModel.setSpacingMeasure( (String) ((HashMap) obj).get( "measure" ) );
        if( vasiModel.getEnd().equals( "PRIMARY" ) && vasiModel.getSide().equals( "LEFT" ) )
            runwayModel.setPrimaryLeftVasiModel( vasiModel );
        else if( vasiModel.getEnd().equals( "PRIMARY" ) && vasiModel.getSide().equals( "RIGHT" ) )
            runwayModel.setPrimaryRightVasiModel( vasiModel );
        else if( vasiModel.getEnd().equals( "SECONDARY" ) && vasiModel.getSide().equals( "LEFT" ) )
            runwayModel.setSecondaryLeftVasiModel( vasiModel );
        else if( vasiModel.getEnd().equals( "SECONDARY" ) && vasiModel.getSide().equals( "RIGHT" ) )
            runwayModel.setSecondaryRightVasiModel( vasiModel );
    }

    private void parseRunwayILS(Element ilsElement, RunwayModel runwayModel)
    {
        ILSModel ilsModel = new ILSModel();
        int i;
        Object obj1;
        Object obj;

        ilsModel.setLatLon( new LatLonPoint( getLat( ilsElement.getAttribute( "lat" ) ), getLon( ilsElement.getAttribute( "lon" ) ) ) );
        try
        {
            ilsModel.setHeading( Float.parseFloat( ilsElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        try
        {
            ilsModel.setFrequency( Float.parseFloat( ilsElement.getAttribute( "frequency" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        ilsModel.setEnd( ilsElement.getAttribute( "end" ) );
        try
        {
            ilsModel.setMagvar( Float.parseFloat( ilsElement.getAttribute( "magvar" ) ) );
        }
        catch( NumberFormatException numberformatexception2 )
        {
            LogEngine.getInstance().log( numberformatexception2 );
        }
        ilsModel.setIdent( ilsElement.getAttribute( "ident" ) );
        if( ilsElement.hasAttribute( "width" ) )
        {
            try
            {
                ilsModel.setWidth( Float.parseFloat( ilsElement.getAttribute( "width" ) ) );
            }
            catch( NumberFormatException numberformatexception3 )
            {
                LogEngine.getInstance().log( numberformatexception3 );
            }
        }
        if( ilsElement.hasAttribute( "name" ) )
            ilsModel.setName( ilsElement.getAttribute( "name" ) );
        if( ilsElement.hasAttribute( "backCourse" ) )
            ilsModel.setBackCourse( getBoolean( ilsElement.getAttribute( "backCourse" ) ) );
        obj1 = getDistanceMeasurement( ilsElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                ilsModel.setAlt( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        ilsModel.setAltMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        if( ilsElement.hasAttribute( "range" ) )
        {
            obj1 = getDistanceMeasurement( ilsElement.getAttribute( "range" ), new String[] { "N", "M", "F" } );
            if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
            {
                try
                {
                    ilsModel.setRange( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception4 )
                {
                    LogEngine.getInstance().log( numberformatexception4 );
                }
            }
            ilsModel.setRangeMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        }
        runwayModel.addILSModel( ilsModel );
        obj = ilsElement.getElementsByTagName( "GlideSlope" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            parseGlideSlope( (Element) ((NodeList) obj).item( i ), ilsModel );
        obj = ilsElement.getElementsByTagName( "Dme" );
        for( i = ((NodeList) obj).getLength() - 1; i >= 0; --i )
            ilsModel.setDMEModel( parseDME( (Element) ((NodeList) obj).item( i ) ) );
    }

    private void parseGlideSlope(Element glideSlopeElement, ILSModel ilsModel)
    {
        GlideSlopeModel glideSlopeModel = new GlideSlopeModel();
        HashMap hashmap1;
        Object obj;

        glideSlopeModel.setLatLon( new LatLonPoint( getLat( glideSlopeElement.getAttribute( "lat" ) ), getLon( glideSlopeElement.getAttribute( "lon" ) ) ) );
        try
        {
            glideSlopeModel.setPitch( Float.parseFloat( glideSlopeElement.getAttribute( "pitch" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        hashmap1 = getDistanceMeasurement( glideSlopeElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                glideSlopeModel.setAlt( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        glideSlopeModel.setAltMeasure( (String) hashmap1.get( "measure" ) );
        obj = getDistanceMeasurement( glideSlopeElement.getAttribute( "range" ), new String[] { "N", "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                glideSlopeModel.setRange( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        glideSlopeModel.setRangeMeasure( (String) ((HashMap) obj).get( "measure" ) );
        ilsModel.setGlideSlopeModel( glideSlopeModel );
    }

    private DMEModel parseDME(Element dmeElement)
    {
        DMEModel dmeModel = new DMEModel();
        HashMap distanceHM;

        dmeModel.setLatLon( new LatLonPoint( getLat( dmeElement.getAttribute( "lat" ) ), getLon( dmeElement.getAttribute( "lon" ) ) ) );
        distanceHM = getDistanceMeasurement( dmeElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                dmeModel.setAlt( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        dmeModel.setAltMeasure( (String) distanceHM.get( "measure" ) );
        distanceHM = getDistanceMeasurement( dmeElement.getAttribute( "range" ), new String[] { "N", "M", "F" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                dmeModel.setRange( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        dmeModel.setRangeMeasure( (String) distanceHM.get( "measure" ) );
        return dmeModel;
    }

    private void parseTaxiwayPoint(Element taxiwayPointElement, AirportModel airportModel)
    {
        TaxiwayPointModel taxiwayPointModel = new TaxiwayPointModel();

        try
        {
            taxiwayPointModel.setIndex( Integer.parseInt( taxiwayPointElement.getAttribute( "index" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        taxiwayPointModel.setType( taxiwayPointElement.getAttribute( "type" ) );
        if( taxiwayPointElement.hasAttribute( "orientation" ) )
            taxiwayPointModel.setOrientation( taxiwayPointElement.getAttribute( "orientation" ) );
        if( taxiwayPointElement.hasAttribute( "lat" ) && taxiwayPointElement.hasAttribute( "lon" ) )
            taxiwayPointModel.setLatLon( new LatLonPoint( getLat( taxiwayPointElement.getAttribute( "lat" ) ), getLon( taxiwayPointElement.getAttribute( "lon" ) ) ) );
        if( taxiwayPointElement.hasAttribute( "biasX" ) )
        {
            HashMap hashmap1 = getDistanceMeasurement( taxiwayPointElement.getAttribute( "biasX" ), new String[] { "M", "F" } );

            if( ((String) hashmap1.get( "distance" )).length() > 0 )
            {
                try
                {
                    taxiwayPointModel.setBiasX( Double.parseDouble( (String) hashmap1.get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            taxiwayPointModel.setBiasXMeasure( (String) hashmap1.get( "measure" ) );
        }
        if( taxiwayPointElement.hasAttribute( "biasZ" ) )
        {
            Object obj = getDistanceMeasurement( taxiwayPointElement.getAttribute( "biasZ" ), new String[] { "M", "F" } );

            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    taxiwayPointModel.setBiasZ( Double.parseDouble( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
            }
            taxiwayPointModel.setBiasZMeasure( (String) ((HashMap) obj).get( "measure" ) );
        }
        airportModel.addTaxiwayPointModel( taxiwayPointModel );
        airportModel.setMaxTaxiIndex( Math.max( airportModel.getMaxTaxiIndex(), taxiwayPointModel.getIndex() ) );
    }

    private void parseTaxiwayParking(Element taxiwayParkingElement, AirportModel airportModel)
    {
        TaxiwayParkingModel taxiwayParkingModel = new TaxiwayParkingModel();
        Object obj;

        try
        {
            taxiwayParkingModel.setIndex( Integer.parseInt( taxiwayParkingElement.getAttribute( "index" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        if( taxiwayParkingElement.hasAttribute( "lat" ) && taxiwayParkingElement.hasAttribute( "lon" ) )
            taxiwayParkingModel.setLatLon( new LatLonPoint( getLat( taxiwayParkingElement.getAttribute( "lat" ) ), getLon( taxiwayParkingElement.getAttribute( "lon" ) ) ) );
        try
        {
            taxiwayParkingModel.setHeading( Float.parseFloat( taxiwayParkingElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        taxiwayParkingModel.setType( taxiwayParkingElement.getAttribute( "type" ) );
        taxiwayParkingModel.setName( taxiwayParkingElement.getAttribute( "name" ) );
        try
        {
            taxiwayParkingModel.setNumber( Integer.parseInt( taxiwayParkingElement.getAttribute( "number" ) ) );
        }
        catch( NumberFormatException numberformatexception2 )
        {
            LogEngine.getInstance().log( numberformatexception2 );
        }
        if( taxiwayParkingElement.hasAttribute( "airlineCodes" ) )
            taxiwayParkingModel.setAirlineCodes( taxiwayParkingElement.getAttribute( "airlineCodes" ) );
        taxiwayParkingModel.setPushBack( taxiwayParkingElement.getAttribute( "pushBack" ) );
        if( taxiwayParkingElement.hasAttribute( "teeOffset1" ) )
        {
            try
            {
                taxiwayParkingModel.setTeeOffset1( Float.parseFloat( taxiwayParkingElement.getAttribute( "teeOffset1" ) ) );
            }
            catch( NumberFormatException numberformatexception3 )
            {
                LogEngine.getInstance().log( numberformatexception3 );
            }
        }
        if( taxiwayParkingElement.hasAttribute( "teeOffset2" ) )
        {
            try
            {
                taxiwayParkingModel.setTeeOffset2( Float.parseFloat( taxiwayParkingElement.getAttribute( "teeOffset2" ) ) );
            }
            catch( NumberFormatException numberformatexception4 )
            {
                LogEngine.getInstance().log( numberformatexception4 );
            }
        }
        if( taxiwayParkingElement.hasAttribute( "teeOffset3" ) )
        {
            try
            {
                taxiwayParkingModel.setTeeOffset3( Float.parseFloat( taxiwayParkingElement.getAttribute( "teeOffset3" ) ) );
            }
            catch( NumberFormatException numberformatexception5 )
            {
                LogEngine.getInstance().log( numberformatexception5 );
            }
        }
        if( taxiwayParkingElement.hasAttribute( "teeeOffset4" ) )
        {
            try
            {
                taxiwayParkingModel.setTeeOffset4( Float.parseFloat( taxiwayParkingElement.getAttribute( "teeOffset4" ) ) );
            }
            catch( NumberFormatException numberformatexception6 )
            {
                LogEngine.getInstance().log( numberformatexception6 );
            }
        }
        obj = getDistanceMeasurement( taxiwayParkingElement.getAttribute( "radius" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                taxiwayParkingModel.setRadius( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        taxiwayParkingModel.setRadiusMeasure( (String) ((HashMap) obj).get( "measure" ) );
        if( taxiwayParkingElement.hasAttribute( "biasX" ) )
        {
            obj = getDistanceMeasurement( taxiwayParkingElement.getAttribute( "biasX" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    taxiwayParkingModel.setBiasX( Double.parseDouble( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception7 )
                {
                    LogEngine.getInstance().log( numberformatexception7 );
                }
            }
            taxiwayParkingModel.setBiasXMeasure( (String) ((HashMap) obj).get( "measure" ) );
        }
        if( taxiwayParkingElement.hasAttribute( "biasZ" ) )
        {
            obj = getDistanceMeasurement( taxiwayParkingElement.getAttribute( "biasZ" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    taxiwayParkingModel.setBiasZ( Double.parseDouble( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception8 )
                {
                    LogEngine.getInstance().log( numberformatexception8 );
                }
            }
            taxiwayParkingModel.setBiasZMeasure( (String) ((HashMap) obj).get( "measure" ) );
        }
        airportModel.addTaxiwayParkingModel( taxiwayParkingModel );
        airportModel.setMaxTaxiIndex( Math.max( airportModel.getMaxTaxiIndex(), taxiwayParkingModel.getIndex() ) );
    }

    private void parseApron(Element apronElement, AirportModel airportModel)
    {
        ApronModel apronModel = new ApronModel();
        NodeList vertices;
        int i;

        apronModel.setSurface( apronElement.getAttribute( "surface" ) );
        if( apronElement.hasAttribute( "drawSurface" ) )
            apronModel.setDrawSurface( getBoolean( apronElement.getAttribute( "drawSurface" ) ) );
        if( apronElement.hasAttribute( "drawDetail" ) )
            apronModel.setDrawDetail( getBoolean( apronElement.getAttribute( "drawDetail" ) ) );
        vertices = apronElement.getElementsByTagName( "Vertex" );
        for( i = 0; i < vertices.getLength(); ++i )
            apronModel.addVertexModel( parseVertex( (Element) vertices.item( i ) ) );
        airportModel.addApronModel( apronModel );
    }

    private void parseTaxiwaySign(Element taxiwaySignElement, AirportModel airportModel)
    {
        TaxiwaySignModel taxiwaySignModel = new TaxiwaySignModel();

        try
        {
            taxiwaySignModel.setHeading( Float.parseFloat( taxiwaySignElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        taxiwaySignModel.setLabel( taxiwaySignElement.getAttribute( "label" ) );
        if( taxiwaySignElement.hasAttribute( "justification" ) )
            taxiwaySignModel.setJustification( taxiwaySignElement.getAttribute( "justification" ) );
        taxiwaySignModel.setSize( taxiwaySignElement.getAttribute( "size" ) );
        taxiwaySignModel.setLatLon( new LatLonPoint( getLat( taxiwaySignElement.getAttribute( "lat" ) ), getLon( taxiwaySignElement.getAttribute( "lon" ) ) ) );
        airportModel.addTaxiwaySignModel( taxiwaySignModel );
    }

    private void parseTower(Element towerElement, AirportModel airportModel)
    {
        TowerModel towerModel = new TowerModel();
        Object distanceHM;
        int i;

        towerModel.setIncludesScenery( false );
        if( towerElement.hasAttribute( "lat" ) && towerElement.hasAttribute( "lon" ) )
            towerModel.setLatLon( new LatLonPoint( getLat( towerElement.getAttribute( "lat" ) ), getLon( towerElement.getAttribute( "lon" ) ) ) );
        if( towerElement.hasAttribute( "alt" ) )
        {
            distanceHM = getDistanceMeasurement( towerElement.getAttribute( "alt" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) distanceHM).get( "distance" )).length() > 0 )
            {
                try
                {
                    towerModel.setAlt( Double.parseDouble( (String) ((HashMap) distanceHM).get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            towerModel.setAltMeasure( (String) ((HashMap) distanceHM).get( "measure" ) );
        }
        distanceHM = towerElement.getElementsByTagName( "SceneryObject" );
        for( i = 0; i < ((NodeList) distanceHM).getLength(); ++i )
        {
            Element sceneryElement = (Element) ((NodeList) distanceHM).item( i );
            int j;
            Object obj;
            NodeList nodelist1;

            towerModel.setIncludesScenery( true );
            towerModel.setSceneryLatLon( new LatLonPoint( getLat( sceneryElement.getAttribute( "lat" ) ), getLon( sceneryElement.getAttribute( "lon" ) ) ) );
            towerModel.setAltitudeIsAgl( getBoolean( sceneryElement.getAttribute( "altitudeIsAgl" ) ) );
            try
            {
                towerModel.setPitch( Float.parseFloat( sceneryElement.getAttribute( "pitch" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
            try
            {
                towerModel.setBank( Float.parseFloat( sceneryElement.getAttribute( "bank" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
            try
            {
                towerModel.setHeading( Float.parseFloat( sceneryElement.getAttribute( "heading" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
            towerModel.setImageComplexity( sceneryElement.getAttribute( "imageComplexity" ) );
            obj = getDistanceMeasurement( sceneryElement.getAttribute( "alt" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    towerModel.setSceneryAlt( (double) Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            towerModel.setSceneryAltMeasure( (String) ((HashMap) obj).get( "measure" ) );
            nodelist1 = sceneryElement.getElementsByTagName( "LibraryObject" );
            for( j = 0; j < nodelist1.getLength(); ++j )
            {
                Element libraryElement = (Element) nodelist1.item( j );

                towerModel.setName( libraryElement.getAttribute( "name" ) );
                try
                {
                    towerModel.setSceneryScale( Float.parseFloat( libraryElement.getAttribute( "scale" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
        }
        airportModel.addTowerModel( towerModel );
    }

    private void parseBoundaryFence(Element boundaryFenceElement, AirportModel airportModel)
    {
        BoundaryFenceModel boundaryFenceModel = new BoundaryFenceModel();
        NodeList vertices;
        int i;

        if( boundaryFenceElement.hasAttribute( "instanceid" ) )
            boundaryFenceModel.setInstanceID( boundaryFenceElement.getAttribute( "instanceid" ) );
        boundaryFenceModel.setProfile( boundaryFenceElement.getAttribute( "profile" ) );
        vertices = boundaryFenceElement.getElementsByTagName( "Vertex" );
        for( i = 0; i < vertices.getLength(); ++i )
            boundaryFenceModel.addVertexModel( parseVertex( (Element) vertices.item( i ) ) );
        airportModel.addBoundaryFenceModel( boundaryFenceModel );
    }

    private void parseBlastFence(Element blastFenceElement, AirportModel airportModel)
    {
        BlastFenceModel blastFenceModel = new BlastFenceModel();
        NodeList vertices;
        int i;

        if( blastFenceElement.hasAttribute( "instanceid" ) )
            blastFenceModel.setInstanceID( blastFenceElement.getAttribute( "instanceid" ) );
        blastFenceModel.setProfile( blastFenceElement.getAttribute( "profile" ) );
        vertices = blastFenceElement.getElementsByTagName( "Vertex" );
        for( i = 0; i < vertices.getLength(); ++i )
            blastFenceModel.addVertexModel( parseVertex( (Element) vertices.item( i ) ) );
        airportModel.addBlastFenceModel( blastFenceModel );
    }

    private void parseTaxiwayPath(Element taxiwayPathElement, AirportModel airportModel)
    {
        TaxiwayPathModel taxiwayPathModel = new TaxiwayPathModel();
        Object obj;

        taxiwayPathModel.setType( taxiwayPathElement.getAttribute( "type" ) );
        try
        {
            taxiwayPathModel.setStart( Integer.parseInt( taxiwayPathElement.getAttribute( "start" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        try
        {
            taxiwayPathModel.setEnd( Integer.parseInt( taxiwayPathElement.getAttribute( "end" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        try
        {
            taxiwayPathModel.setWeightLimit( Float.parseFloat( taxiwayPathElement.getAttribute( "weightLimit" ) ) );
        }
        catch( NumberFormatException numberformatexception2 )
        {
            LogEngine.getInstance().log( numberformatexception2 );
        }
        taxiwayPathModel.setSurface( taxiwayPathElement.getAttribute( "surface" ) );
        taxiwayPathModel.setDrawSurface( Boolean.valueOf( taxiwayPathElement.getAttribute( "drawSurface" ) ).booleanValue() );
        taxiwayPathModel.setDrawDetail( Boolean.valueOf( taxiwayPathElement.getAttribute( "drawDetail" ) ).booleanValue() );
        if( taxiwayPathElement.hasAttribute( "centerLine" ) )
            taxiwayPathModel.setCenterLine( Boolean.valueOf( taxiwayPathElement.getAttribute( "centerLine" ) ).booleanValue() );
        if( taxiwayPathElement.hasAttribute( "centerLineLighted" ) )
            taxiwayPathModel.setCenterLineLighted( Boolean.valueOf( taxiwayPathElement.getAttribute( "centerLineLighted" ) ).booleanValue() );
        if( taxiwayPathElement.hasAttribute( "leftEdge" ) )
            taxiwayPathModel.setLeftEdge( taxiwayPathElement.getAttribute( "leftEdge" ) );
        if( taxiwayPathElement.hasAttribute( "leftEdgeLighted" ) )
            taxiwayPathModel.setLeftEdgeLighted( Boolean.valueOf( taxiwayPathElement.getAttribute( "leftEdgeLighted" ) ).booleanValue() );
        if( taxiwayPathElement.hasAttribute( "rightEdge" ) )
            taxiwayPathModel.setRightEdge( taxiwayPathElement.getAttribute( "rightEdge" ) );
        if( taxiwayPathElement.hasAttribute( "rightEdgeLighted" ) )
            taxiwayPathModel.setRightEdgeLighted( Boolean.valueOf( taxiwayPathElement.getAttribute( "rightEdgeLighted" ) ).booleanValue() );
        if( taxiwayPathElement.hasAttribute( "number" ) )
            taxiwayPathModel.setNumber( taxiwayPathElement.getAttribute( "number" ) );
        if( taxiwayPathElement.hasAttribute( "designator" ) )
            taxiwayPathModel.setDesignator( taxiwayPathElement.getAttribute( "designator" ) );
        if( taxiwayPathElement.hasAttribute( "name" ) )
        {
            try
            {
                taxiwayPathModel.setName( Integer.parseInt( taxiwayPathElement.getAttribute( "name" ) ) );
            }
            catch( NumberFormatException numberformatexception3 )
            {
                LogEngine.getInstance().log( numberformatexception3 );
            }
        }
        obj = getDistanceMeasurement( taxiwayPathElement.getAttribute( "width" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                taxiwayPathModel.setWidth( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        taxiwayPathModel.setWidthMeasure( (String) ((HashMap) obj).get( "measure" ) );
        airportModel.addTaxiwayPathModel( taxiwayPathModel );
    }

    private void parseStartPoint(Element startElement, AirportModel airportModel)
    {
        StartModel startModel = new StartModel();
        HashMap hashmap1;

        if( startElement.hasAttribute( "type" ) )
            startModel.setType( startElement.getAttribute( "type" ) );
        startModel.setLatLon( new LatLonPoint( getLat( startElement.getAttribute( "lat" ) ), getLon( startElement.getAttribute( "lon" ) ) ) );
        try
        {
            startModel.setHeading( Float.parseFloat( startElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        if( startElement.hasAttribute( "number" ) )
            startModel.setNumber( startElement.getAttribute( "number" ) );
        if( startElement.hasAttribute( "designator" ) )
            startModel.setDesignator( startElement.getAttribute( "designator" ) );
        hashmap1 = getDistanceMeasurement( startElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                startModel.setAlt( Double.parseDouble( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        startModel.setAltMeasure( (String) hashmap1.get( "measure" ) );
        airportModel.addStartModel( startModel );
    }

    private void parseHelipad(Element helipadElement, AirportModel airportModel)
    {
        HelipadModel helipadModel = new HelipadModel();
        HashMap hashmap1;
        Object obj;

        helipadModel.setLatLon( new LatLonPoint( getLat( helipadElement.getAttribute( "lat" ) ), getLon( helipadElement.getAttribute( "lon" ) ) ) );
        helipadModel.setSurface( helipadElement.getAttribute( "surface" ) );
        try
        {
            helipadModel.setHeading( Float.parseFloat( helipadElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        helipadModel.setType( helipadElement.getAttribute( "type" ) );
        if( helipadElement.hasAttribute( "closed" ) )
            helipadModel.setClosed( getBoolean( helipadElement.getAttribute( "closed" ) ) );
        if( helipadElement.hasAttribute( "transparent" ) )
            helipadModel.setTransparent( getBoolean( helipadElement.getAttribute( "transparent" ) ) );
        hashmap1 = getDistanceMeasurement( helipadElement.getAttribute( "alt" ), new String[] { "M", "F" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                helipadModel.setAlt( (double) Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        helipadModel.setAltMeasure( (String) hashmap1.get( "measure" ) );
        obj = getDistanceMeasurement( helipadElement.getAttribute( "length" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                helipadModel.setLength( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        helipadModel.setLengthMeasure( (String) ((HashMap) obj).get( "measure" ) );
        obj = getDistanceMeasurement( helipadElement.getAttribute( "width" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                helipadModel.setWidth( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
        }
        helipadModel.setWidthMeasure( (String) ((HashMap) obj).get( "measure" ) );
        airportModel.addHelipadModel( helipadModel );
    }

    private void parseApronEdgeLight(Element apronEdgeLightElement, AirportModel airportModel)
    {
        NodeList edgeLights = apronEdgeLightElement.getElementsByTagName( "EdgeLights" );
        int i;

        for( i = 0; i < edgeLights.getLength(); ++i )
        {
            ApronEdgeLightModel apronEdgeLightModel = new ApronEdgeLightModel();
            Element edgeLightElement = (Element) edgeLights.item( i );
            NodeList vertices;
            int j;

            airportModel.addApronEdgeLightModel( apronEdgeLightModel );
            vertices = edgeLightElement.getElementsByTagName( "Vertex" );
            for( j = 0; j < vertices.getLength(); ++j )
                apronEdgeLightModel.addVertexModel( parseVertex( (Element) vertices.item( j ) ) );
        }
    }

    private void parseTaxiName(Element taxiNameElement, AirportModel airportModel)
    {
        TaxiNameModel taxiNameModel = new TaxiNameModel();

        try
        {
            taxiNameModel.setIndex( Integer.parseInt( taxiNameElement.getAttribute( "index" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        taxiNameModel.setName( taxiNameElement.getAttribute( "name" ) );
        airportModel.addTaxiNameModel( taxiNameModel );
        airportModel.setMaxTaxiNameIndex( Math.max( airportModel.getMaxTaxiNameIndex(), taxiNameModel.getIndex() ) );
    }

    private void parseJetway(Element jetwayElement, AirportModel airportModel)
    {
        JetwayModel jetwayModel = new JetwayModel();
        int i;
        NodeList nodelist1;

        jetwayModel.setGateName( jetwayElement.getAttribute( "gateName" ) );
        try
        {
            jetwayModel.setParkingNumber( Integer.parseInt( jetwayElement.getAttribute( "parkingNumber" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        nodelist1 = jetwayElement.getElementsByTagName( "SceneryObject" );
        for( i = 0; i < nodelist1.getLength(); ++i )
        {
            Element sceneryElement = (Element) nodelist1.item( i );
            int j;
            Object obj;
            NodeList nodelist2;

            jetwayModel.setLatLon( new LatLonPoint( getLat( sceneryElement.getAttribute( "lat" ) ), getLon( sceneryElement.getAttribute( "lon" ) ) ) );
            jetwayModel.setAltitudeIsAgl( getBoolean( sceneryElement.getAttribute( "altitudeIsAgl" ) ) );
            try
            {
                jetwayModel.setPitch( Float.parseFloat( sceneryElement.getAttribute( "pitch" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
            try
            {
                jetwayModel.setBank( Float.parseFloat( sceneryElement.getAttribute( "bank" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
            try
            {
                jetwayModel.setHeading( Float.parseFloat( sceneryElement.getAttribute( "heading" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
            jetwayModel.setImageComplexity( sceneryElement.getAttribute( "imageComplexity" ) );
            obj = getDistanceMeasurement( sceneryElement.getAttribute( "alt" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    jetwayModel.setAlt( (double) Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            jetwayModel.setAltMeasure( (String) ((HashMap) obj).get( "measure" ) );
            nodelist2 = sceneryElement.getElementsByTagName( "LibraryObject" );
            for( j = 0; j < nodelist2.getLength(); ++j )
            {
                Element libraryElement = (Element) nodelist2.item( j );

                jetwayModel.setName( libraryElement.getAttribute( "name" ) );
                try
                {
                    jetwayModel.setJetwayScale( Float.parseFloat( libraryElement.getAttribute( "scale" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
        }
        airportModel.addJetwayModel( jetwayModel );
    }

    private void parseCom(Element comElement, AirportModel airportModel)
    {
        ComModel comModel = new ComModel();

        comModel.setType( comElement.getAttribute( "type" ) );
        comModel.setName( comElement.getAttribute( "name" ) );
        try
        {
            comModel.setFrequency( Float.parseFloat( comElement.getAttribute( "frequency" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        airportModel.addComModel( comModel );
    }

    private void parseApproach(Element approachElement, AirportModel airportModel)
    {
        ApproachModel approachModel = new ApproachModel();
        int i;
        Element approachLegElement;
        NodeList n2;
        int j;
        HashMap hashmap1;
        Object obj1;
        Object obj;

        approachModel.setType( approachElement.getAttribute( "type" ) );
        if( approachElement.hasAttribute( "runway" ) )
            approachModel.setRunway( approachElement.getAttribute( "runway" ) );
        if( approachElement.hasAttribute( "designator" ) )
            approachModel.setDesignator( approachElement.getAttribute( "designator" ) );
        if( approachElement.hasAttribute( "suffix" ) )
            approachModel.setSuffix( approachElement.getAttribute( "suffix" ) );
        if( approachElement.hasAttribute( "gpsOverlay" ) )
            approachModel.setGPSOverlay( getBoolean( approachElement.getAttribute( "gpsOverlay" ) ) );
        approachModel.setFixType( approachElement.getAttribute( "fixType" ) );
        approachModel.setFixRegion( approachElement.getAttribute( "fixRegion" ) );
        approachModel.setFixIdent( approachElement.getAttribute( "fixIdent" ) );
        try
        {
            approachModel.setHeading( Float.parseFloat( approachElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        hashmap1 = getDistanceMeasurement( approachElement.getAttribute( "altitude" ), new String[] { "M", "F" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                approachModel.setAltitude( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        approachModel.setAltitudeMeasure( (String) hashmap1.get( "measure" ) );
        obj1 = getDistanceMeasurement( approachElement.getAttribute( "missedAltitude" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                approachModel.setMissedAltitude( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        approachModel.setMissedAltitudeMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        obj = approachElement.getElementsByTagName( "ApproachLegs" );
        for( i = 0; i < ((NodeList) obj).getLength(); ++i )
        {
            approachLegElement = (Element) ((NodeList) obj).item( i );
            n2 = approachLegElement.getElementsByTagName( "Leg" );
            for( j = 0; j < n2.getLength(); ++j )
                approachModel.addApproachLegModel( parseApproachLeg( (Element) n2.item( j ) ) );
        }
        obj = approachElement.getElementsByTagName( "MissedApproachLegs" );
        for( i = 0; i < ((NodeList) obj).getLength(); ++i )
        {
            approachLegElement = (Element) ((NodeList) obj).item( i );
            n2 = approachLegElement.getElementsByTagName( "Leg" );
            for( j = 0; j < n2.getLength(); ++j )
                approachModel.addMissedLegModel( parseApproachLeg( (Element) n2.item( j ) ) );
        }
        obj = approachElement.getElementsByTagName( "Transition" );
        for( i = 0; i < ((NodeList) obj).getLength(); ++i )
            approachModel.addTransitionModel( parseTransition( (Element) ((NodeList) obj).item( i ) ) );
        airportModel.addApproachModel( approachModel );
    }

    private TransitionModel parseTransition(Element transitionElement)
    {
        TransitionModel transitionModel = new TransitionModel();
        HashMap distanceHM;
        int i;
        NodeList nodelist1;
        Object obj;

        transitionModel.setTransitionType( transitionElement.getAttribute( "transitionType" ) );
        transitionModel.setFixType( transitionElement.getAttribute( "fixType" ) );
        transitionModel.setFixRegion( transitionElement.getAttribute( "fixRegion" ) );
        transitionModel.setFixIdent( transitionElement.getAttribute( "fixIdent" ) );
        distanceHM = getDistanceMeasurement( transitionElement.getAttribute( "altitude" ), new String[] { "N", "M" } );
        if( ((String) distanceHM.get( "distance" )).length() > 0 )
        {
            try
            {
                transitionModel.setAlt( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        transitionModel.setAltMeasure( (String) distanceHM.get( "measure" ) );
        nodelist1 = transitionElement.getElementsByTagName( "DmeArc" );
        for( i = 0; i < nodelist1.getLength(); ++i )
            transitionModel.setDmeArcModel( parseDmeArc( (Element) nodelist1.item( i ) ) );
        obj = transitionElement.getElementsByTagName( "TransitionLegs" );
        for( i = 0; i < ((NodeList) obj).getLength(); ++i )
        {
            Element transitionLegElement = (Element) ((NodeList) obj).item( i );
            NodeList n2 = transitionLegElement.getElementsByTagName( "Leg" );
            int j;

            for( j = 0; j < n2.getLength(); ++j )
                transitionModel.addLegModel( parseApproachLeg( (Element) n2.item( j ) ) );
        }
        return transitionModel;
    }

    private DmeArcModel parseDmeArc(Element dmeArcElement)
    {
        DmeArcModel dmeArcModel = new DmeArcModel();
        HashMap hashmap1;

        try
        {
            dmeArcModel.setRadial( Float.parseFloat( dmeArcElement.getAttribute( "radial" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        dmeArcModel.setDmeRegion( dmeArcElement.getAttribute( "dmeRegion" ) );
        dmeArcModel.setDmeIdent( dmeArcElement.getAttribute( "dmeIdent" ) );
        hashmap1 = getDistanceMeasurement( dmeArcElement.getAttribute( "distance" ), new String[] { "N", "M" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                dmeArcModel.setDistance( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        dmeArcModel.setDistanceMeasure( (String) hashmap1.get( "measure" ) );
        return dmeArcModel;
    }

    private LegModel parseApproachLeg(Element legElement)
    {
        LegModel legModel = new LegModel();
        Object obj;

        legModel.setType( legElement.getAttribute( "type" ) );
        legModel.setFixType( legElement.getAttribute( "fixType" ) );
        legModel.setFixRegion( legElement.getAttribute( "fixRegion" ) );
        legModel.setFixIdent( legElement.getAttribute( "fixIdent" ) );
        legModel.setFlyOver( getBoolean( legElement.getAttribute( "flyOver" ) ) );
        legModel.setTurnDirection( legElement.getAttribute( "turnDirection" ) );
        legModel.setRecommendedType( legElement.getAttribute( "recommendedType" ) );
        legModel.setRecommendedRegion( legElement.getAttribute( "recommendedRegion" ) );
        legModel.setRecommendedIdent( legElement.getAttribute( "recommendedIdent" ) );
        if( legElement.hasAttribute( "theta" ) )
        {
            try
            {
                legModel.setTheta( Float.parseFloat( legElement.getAttribute( "theta" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        if( legElement.hasAttribute( "trueCourse" ) )
        {
            try
            {
                legModel.setTrueCourse( Float.parseFloat( legElement.getAttribute( "trueCourse" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        if( legElement.hasAttribute( "magneticCourse" ) )
        {
            try
            {
                legModel.setMagneticCourse( Float.parseFloat( legElement.getAttribute( "magneticCourse" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
        }
        if( legElement.hasAttribute( "time" ) )
        {
            try
            {
                legModel.setTime( Float.parseFloat( legElement.getAttribute( "time" ) ) );
            }
            catch( NumberFormatException numberformatexception3 )
            {
                LogEngine.getInstance().log( numberformatexception3 );
            }
        }
        legModel.setAltitudeDescriptor( legElement.getAttribute( "altitudeDescriptor" ) );
        obj = getDistanceMeasurement( legElement.getAttribute( "altitude1" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                legModel.setAltitude1( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        legModel.setAltitude1Measure( (String) ((HashMap) obj).get( "measure" ) );
        obj = getDistanceMeasurement( legElement.getAttribute( "altitude2" ), new String[] { "M", "F" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                legModel.setAltitude2( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception4 )
            {
                LogEngine.getInstance().log( numberformatexception4 );
            }
        }
        legModel.setAltitude2Measure( (String) ((HashMap) obj).get( "measure" ) );
        obj = getDistanceMeasurement( legElement.getAttribute( "rho" ), new String[] { "M", "N" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                legModel.setRho( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception5 )
            {
                LogEngine.getInstance().log( numberformatexception5 );
            }
        }
        legModel.setRhoMeasure( (String) ((HashMap) obj).get( "measure" ) );
        if( legElement.hasAttribute( "distance" ) )
        {
            obj = getDistanceMeasurement( legElement.getAttribute( "distance" ), new String[] { "M", "N" } );
            if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
            {
                try
                {
                    legModel.setDistance( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception6 )
                {
                    LogEngine.getInstance().log( numberformatexception6 );
                }
            }
            legModel.setDistanceMeasure( (String) ((HashMap) obj).get( "measure" ) );
        }
        return legModel;
    }

    private void parseVOR(Element vorElement, AirportModel airportModel)
    {
        VORModel vorModel = new VORModel();
        int i;
        java.awt.geom.Point2D.Float distancePoint;
        float distance;
        Object obj1;
        Object obj;
        float f;

        if( vorElement.hasAttribute( "lat" ) && vorElement.hasAttribute( "lon" ) )
            vorModel.setLatLon( new LatLonPoint( getLat( vorElement.getAttribute( "lat" ) ), getLon( vorElement.getAttribute( "lon" ) ) ) );
        vorModel.setType( vorElement.getAttribute( "type" ) );
        vorModel.setRegion( vorElement.getAttribute( "region" ) );
        vorModel.setIdent( vorElement.getAttribute( "ident" ) );
        if( vorElement.hasAttribute( "nav" ) )
            vorModel.setDmeOnly( (!getBoolean( vorElement.getAttribute( "nav" ) )) ? true : false );
        if( vorElement.hasAttribute( "dmeOnly" ) )
            vorModel.setDmeOnly( getBoolean( vorElement.getAttribute( "dmeOnly" ) ) );
        if( vorElement.hasAttribute( "dme" ) )
            vorModel.setDme( getBoolean( vorElement.getAttribute( "dme" ) ) );
        try
        {
            vorModel.setFrequency( Float.parseFloat( vorElement.getAttribute( "frequency" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        try
        {
            vorModel.setMagvar( Float.parseFloat( vorElement.getAttribute( "magvar" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        vorModel.setName( vorElement.getAttribute( "name" ) );
        obj1 = getDistanceMeasurement( vorElement.getAttribute( "alt" ), new String[] { "F", "M" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                vorModel.setAlt( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        vorModel.setAltMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        obj1 = getDistanceMeasurement( vorElement.getAttribute( "range" ), new String[] { "M", "N" } );
        if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
        {
            try
            {
                vorModel.setRange( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
        }
        vorModel.setRangeMeasure( (String) ((HashMap) obj1).get( "measure" ) );
        obj = vorElement.getElementsByTagName( "Dme" );
        for( i = 0; i < ((NodeList) obj).getLength(); ++i )
            vorModel.setDMEModel( parseDME( (Element) ((NodeList) obj).item( i ) ) );
        f = (float) airportModel.getAirportTestRadius();
        if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
            f = f * 3.2799999713897705078125F;
        else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
            f = f * 6074.56005859375F;
        distancePoint = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), vorModel.getLatLon().getLat(), vorModel.getLatLon().getLon(), 1.0F );
        distance = (float) Math.sqrt( Math.pow( distancePoint.getX(), 2.0 ) + Math.pow( distancePoint.getY(), 2.0 ) );
        if( distance < f )
            airportModel.addVORModel( vorModel );
        else
            airportModel.addUnusedVORModel( vorModel );
    }

    private void parseNDB(Element ndbElement, AirportModel airportModel, boolean terminal)
    {
        NDBModel ndbModel = new NDBModel();
        java.awt.geom.Point2D.Float distancePoint;
        float distance;
        Object obj;
        float f;

        ndbModel.setTerminal( terminal );
        if( ndbElement.hasAttribute( "lat" ) && ndbElement.hasAttribute( "lon" ) )
            ndbModel.setLatLon( new LatLonPoint( getLat( ndbElement.getAttribute( "lat" ) ), getLon( ndbElement.getAttribute( "lon" ) ) ) );
        ndbModel.setType( ndbElement.getAttribute( "type" ) );
        ndbModel.setRegion( ndbElement.getAttribute( "region" ) );
        ndbModel.setIdent( ndbElement.getAttribute( "ident" ) );
        try
        {
            ndbModel.setFrequency( Float.parseFloat( ndbElement.getAttribute( "frequency" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        try
        {
            ndbModel.setMagvar( Float.parseFloat( ndbElement.getAttribute( "magvar" ) ) );
        }
        catch( NumberFormatException numberformatexception1 )
        {
            LogEngine.getInstance().log( numberformatexception1 );
        }
        ndbModel.setName( ndbElement.getAttribute( "name" ) );
        obj = getDistanceMeasurement( ndbElement.getAttribute( "alt" ), new String[] { "F", "M" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                ndbModel.setAlt( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        ndbModel.setAltMeasure( (String) ((HashMap) obj).get( "measure" ) );
        obj = getDistanceMeasurement( ndbElement.getAttribute( "range" ), new String[] { "M", "N" } );
        if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
        {
            try
            {
                ndbModel.setRange( Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
        }
        ndbModel.setRangeMeasure( (String) ((HashMap) obj).get( "measure" ) );
        f = (float) airportModel.getAirportTestRadius();
        if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
            f = f * 3.2799999713897705078125F;
        else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
            f = f * 6074.56005859375F;
        distancePoint = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), ndbModel.getLatLon().getLat(), ndbModel.getLatLon().getLon(), 1.0F );
        distance = (float) Math.sqrt( Math.pow( distancePoint.getX(), 2.0 ) + Math.pow( distancePoint.getY(), 2.0 ) );
        if( distance < f )
            airportModel.addNDBModel( ndbModel );
        else
            airportModel.addUnusedNDBModel( ndbModel );
    }

    private void parseMarker(Element markerElement, AirportModel airportModel)
    {
        MarkerModel markerModel = new MarkerModel();
        java.awt.geom.Point2D.Float distancePoint;
        float distance;
        HashMap hashmap1;
        float f;

        if( markerElement.hasAttribute( "lat" ) && markerElement.hasAttribute( "lon" ) )
            markerModel.setLatLon( new LatLonPoint( getLat( markerElement.getAttribute( "lat" ) ), getLon( markerElement.getAttribute( "lon" ) ) ) );
        markerModel.setType( markerElement.getAttribute( "type" ) );
        try
        {
            markerModel.setHeading( Float.parseFloat( markerElement.getAttribute( "heading" ) ) );
        }
        catch( NumberFormatException nfe )
        {
            LogEngine.getInstance().log( nfe );
        }
        markerModel.setRegion( markerElement.getAttribute( "region" ) );
        markerModel.setIdent( markerElement.getAttribute( "ident" ) );
        hashmap1 = getDistanceMeasurement( markerElement.getAttribute( "alt" ), new String[] { "F", "M" } );
        if( ((String) hashmap1.get( "distance" )).length() > 0 )
        {
            try
            {
                markerModel.setAlt( Float.parseFloat( (String) hashmap1.get( "distance" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
        }
        markerModel.setAltMeasure( (String) hashmap1.get( "measure" ) );
        f = (float) airportModel.getAirportTestRadius();
        if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
            f = f * 3.2799999713897705078125F;
        else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
            f = f * 6074.56005859375F;
        distancePoint = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), markerModel.getLatLon().getLat(), markerModel.getLatLon().getLon(), 1.0F );
        distance = (float) Math.sqrt( Math.pow( distancePoint.getX(), 2.0 ) + Math.pow( distancePoint.getY(), 2.0 ) );
        if( distance < f )
            airportModel.addMarkerModel( markerModel );
        else
            airportModel.addUnusedMarkerModel( markerModel );
    }

    private void parseWindsock(Element windsockElement, AirportModel airportModel)
    {
        if( windsockElement.getParentNode() != null )
        {
            WindsockModel windsockModel = new WindsockModel();
            Element sceneryElement = (Element) windsockElement.getParentNode();
            int i;
            Object poleColorElement;
            int blue;
            int green;
            Object obj1;
            Object obj;
            float f;
            float f1;

            windsockModel.setLatLon( new LatLonPoint( getLat( sceneryElement.getAttribute( "lat" ) ), getLon( sceneryElement.getAttribute( "lon" ) ) ) );
            windsockModel.setAltitudeIsAgl( getBoolean( sceneryElement.getAttribute( "altitudeIsAgl" ) ) );
            try
            {
                windsockModel.setPitch( Float.parseFloat( sceneryElement.getAttribute( "pitch" ) ) );
            }
            catch( NumberFormatException nfe )
            {
                LogEngine.getInstance().log( nfe );
            }
            try
            {
                windsockModel.setBank( Float.parseFloat( sceneryElement.getAttribute( "bank" ) ) );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
            try
            {
                windsockModel.setHeading( Float.parseFloat( sceneryElement.getAttribute( "heading" ) ) );
            }
            catch( NumberFormatException numberformatexception2 )
            {
                LogEngine.getInstance().log( numberformatexception2 );
            }
            windsockModel.setImageComplexity( sceneryElement.getAttribute( "imageComplexity" ) );
            obj1 = getDistanceMeasurement( sceneryElement.getAttribute( "alt" ), new String[] { "M", "F" } );
            if( ((String) ((HashMap) obj1).get( "distance" )).length() > 0 )
            {
                try
                {
                    windsockModel.setAlt( Float.parseFloat( (String) ((HashMap) obj1).get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            windsockModel.setAltMeasure( (String) ((HashMap) obj1).get( "measure" ) );
            try
            {
                windsockModel.setPoleHeight( Float.parseFloat( windsockElement.getAttribute( "poleHeight" ) ) );
            }
            catch( NumberFormatException numberformatexception3 )
            {
                LogEngine.getInstance().log( numberformatexception3 );
            }
            try
            {
                windsockModel.setSockLength( Float.parseFloat( windsockElement.getAttribute( "sockLength" ) ) );
            }
            catch( NumberFormatException numberformatexception4 )
            {
                LogEngine.getInstance().log( numberformatexception4 );
            }
            windsockModel.setLighted( getBoolean( windsockElement.getAttribute( "lighted" ) ) );
            obj = windsockElement.getElementsByTagName( "PoleColor" );
            for( i = 0; i < ((NodeList) obj).getLength(); ++i )
            {
                poleColorElement = (Element) ((NodeList) obj).item( i );
                try
                {
                    int red = Integer.parseInt( ((Element) poleColorElement).getAttribute( "red" ) );

                    blue = Integer.parseInt( ((Element) poleColorElement).getAttribute( "blue" ) );
                    green = Integer.parseInt( ((Element) poleColorElement).getAttribute( "green" ) );
                    windsockModel.setPoleColor( new Color( red, green, blue ) );
                }
                catch( NumberFormatException numberformatexception5 )
                {
                    LogEngine.getInstance().log( numberformatexception5 );
                }
            }
            obj = windsockElement.getElementsByTagName( "SockColor" );
            for( i = 0; i < ((NodeList) obj).getLength(); ++i )
            {
                poleColorElement = (Element) ((NodeList) obj).item( i );
                try
                {
                    i = Integer.parseInt( ((Element) poleColorElement).getAttribute( "red" ) );
                    blue = Integer.parseInt( ((Element) poleColorElement).getAttribute( "blue" ) );
                    green = Integer.parseInt( ((Element) poleColorElement).getAttribute( "green" ) );
                    windsockModel.setSockColor( new Color( i, green, blue ) );
                }
                catch( NumberFormatException numberformatexception6 )
                {
                    LogEngine.getInstance().log( numberformatexception6 );
                }
            }
            f = (float) airportModel.getAirportTestRadius();
            if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
                f = f * 3.2799999713897705078125F;
            else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
                f = f * 6074.56005859375F;
            poleColorElement = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), windsockModel.getLatLon().getLat(), windsockModel.getLatLon().getLon(), 1.0F );
            f1 = (float) Math.sqrt( Math.pow( ((java.awt.geom.Point2D.Float) poleColorElement).getX(), 2.0 ) + Math.pow( ((java.awt.geom.Point2D.Float) poleColorElement).getY(), 2.0 ) );
            if( f1 < f )
                airportModel.addWindsockModel( windsockModel );
            else
                airportModel.addUnusedWindsockModel( windsockModel );
        }
    }

    private void parseTrigger(Element triggerElement, AirportModel airportModel)
    {
        if( triggerElement.getParentNode() != null )
        {
            TriggerModel triggerModel = new TriggerModel();
            Element sceneryElement = (Element) triggerElement.getParentNode();
            LatLonPoint baseLatLon = new LatLonPoint( getLat( sceneryElement.getAttribute( "lat" ) ), getLon( sceneryElement.getAttribute( "lon" ) ) );
            NodeList vertices = triggerElement.getElementsByTagName( "Vertex" );
            int i;
            NodeList nodelist1;
            float f;
            java.awt.geom.Point2D.Float float1;
            float f1;

            for( i = 0; i < vertices.getLength(); ++i )
            {
                Element vertexElement = (Element) vertices.item( i );
                double biasX = 0.0;
                double biasZ = 0.0;
                LatLonPoint biasZLL;
                VertexModel vertexModel;
                Object obj;

                try
                {
                    biasX = Double.parseDouble( vertexElement.getAttribute( "biasX" ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
                try
                {
                    biasZ = Double.parseDouble( vertexElement.getAttribute( "biasZ" ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
                obj = null;
                biasZLL = null;
                if( biasX != 0.0 )
                    obj = Utilities.getLatLonForPixel( baseLatLon.getLat(), baseLatLon.getLon(), biasX * 3.2799999713897705078125, 0.0, 1.0F );
                else
                    obj = baseLatLon;
                if( biasZ != 0.0 )
                    biasZLL = Utilities.getLatLonForPixel( baseLatLon.getLat(), baseLatLon.getLon(), 0.0, biasZ * 3.2799999713897705078125 * -1.0, 1.0F );
                else
                    biasZLL = baseLatLon;
                vertexModel = new VertexModel();
                vertexModel.setLatLon( new LatLonPoint( biasZLL.getLat(), ((LatLonPoint) obj).getLon() ) );
                triggerModel.addVertexModel( vertexModel );
            }
            nodelist1 = triggerElement.getElementsByTagName( "Fuel" );
            for( i = 0; i < nodelist1.getLength(); ++i )
            {
                Element element1 = (Element) nodelist1.item( i );
                String type = element1.getAttribute( "type" );
                String s = element1.getAttribute( "availability" );

                if( type.equals( "73" ) )
                    triggerModel.setType73( s );
                else if( type.equals( "87" ) )
                    triggerModel.setType87( s );
                else if( type.equals( "100" ) )
                    triggerModel.setType100( s );
                else if( type.equals( "130" ) )
                    triggerModel.setType130( s );
                else if( type.equals( "145" ) )
                    triggerModel.setType145( s );
                else if( type.equals( "MOGAS" ) )
                    triggerModel.setTypeMogas( s );
                else if( type.equals( "JET" ) )
                    triggerModel.setTypeJet( s );
                else if( type.equals( "JETA" ) )
                    triggerModel.setTypeJetA( s );
                else if( type.equals( "JETA1" ) )
                    triggerModel.setTypeJetA1( s );
                else if( type.equals( "JETAP" ) )
                    triggerModel.setTypeJetAP( s );
                else if( type.equals( "JETB" ) )
                    triggerModel.setTypeJetB( s );
                else if( type.equals( "JET4" ) )
                    triggerModel.setTypeJet4( s );
                else if( type.equals( "JET5" ) )
                    triggerModel.setTypeJet5( s );
                else if( type.equals( "UNKNOWN" ) )
                    triggerModel.setTypeUnknown( s );
            }
            f = (float) airportModel.getAirportTestRadius();
            if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
                f = f * 3.2799999713897705078125F;
            else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
                f = f * 6074.56005859375F;
            float1 = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), baseLatLon.getLat(), baseLatLon.getLon(), 1.0F );
            f1 = (float) Math.sqrt( Math.pow( float1.getX(), 2.0 ) + Math.pow( float1.getY(), 2.0 ) );
            if( f1 < f )
                airportModel.addTriggerModel( triggerModel );
            else
                airportModel.addUnusedTriggerModel( triggerModel );
        }
    }

    private void parseScenery(Element sceneryElement, Element fsDataElement, AirportModel airportModel)
    {
        if( sceneryElement.getParentNode() == fsDataElement )
        {
            int shouldRead = 1;
            NodeList nodeList = sceneryElement.getChildNodes();
            int i = 0;

            while( i < nodeList.getLength() )
            {
                if( nodeList.item( i ) instanceof Element && !((Element) nodeList.item( i )).getTagName().equals( "LibraryObject" ) )
                {
                    shouldRead = 0;
                    break;
                }
                else
                    ++i;
            }
            if( shouldRead != 0 )
            {
                SceneryModel scenerymodel1 = new SceneryModel();
                int j;
                Object libraryElement;
                Object obj;
                NodeList nodelist1;
                float f;
                float f1;

                scenerymodel1.setLatLon( new LatLonPoint( getLat( sceneryElement.getAttribute( "lat" ) ), getLon( sceneryElement.getAttribute( "lon" ) ) ) );
                scenerymodel1.setAltitudeIsAgl( getBoolean( sceneryElement.getAttribute( "altitudeIsAgl" ) ) );
                try
                {
                    scenerymodel1.setPitch( Float.parseFloat( sceneryElement.getAttribute( "pitch" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
                try
                {
                    scenerymodel1.setBank( Float.parseFloat( sceneryElement.getAttribute( "bank" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
                try
                {
                    scenerymodel1.setHeading( Float.parseFloat( sceneryElement.getAttribute( "heading" ) ) );
                }
                catch( NumberFormatException numberformatexception2 )
                {
                    LogEngine.getInstance().log( numberformatexception2 );
                }
                scenerymodel1.setImageComplexity( sceneryElement.getAttribute( "imageComplexity" ) );
                obj = getDistanceMeasurement( sceneryElement.getAttribute( "alt" ), new String[] { "M", "F" } );
                if( ((String) ((HashMap) obj).get( "distance" )).length() > 0 )
                {
                    try
                    {
                        scenerymodel1.setAlt( (double) Float.parseFloat( (String) ((HashMap) obj).get( "distance" ) ) );
                    }
                    catch( NumberFormatException nfe )
                    {
                        LogEngine.getInstance().log( nfe );
                    }
                }
                scenerymodel1.setAltMeasure( (String) ((HashMap) obj).get( "measure" ) );
                nodelist1 = sceneryElement.getElementsByTagName( "LibraryObject" );
                for( j = 0; j < nodelist1.getLength(); ++j )
                {
                    libraryElement = (Element) nodelist1.item( j );
                    scenerymodel1.setName( ((Element) libraryElement).getAttribute( "name" ) );
                    scenerymodel1.setDisplayName();
                    try
                    {
                        scenerymodel1.setSceneryScale( Float.parseFloat( ((Element) libraryElement).getAttribute( "scale" ) ) );
                    }
                    catch( NumberFormatException nfe )
                    {
                        LogEngine.getInstance().log( nfe );
                    }
                }
                f = (float) airportModel.getAirportTestRadius();
                if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
                    f = f * 3.2799999713897705078125F;
                else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
                    f = f * 6074.56005859375F;
                libraryElement = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), scenerymodel1.getLatLon().getLat(), scenerymodel1.getLatLon().getLon(), 1.0F );
                f1 = (float) Math.sqrt( Math.pow( ((java.awt.geom.Point2D.Float) libraryElement).getX(), 2.0 ) + Math.pow( ((java.awt.geom.Point2D.Float) libraryElement).getY(), 2.0 ) );
                if( f1 < f )
                    airportModel.addSceneryModel( scenerymodel1 );
                else
                    airportModel.addUnusedSceneryModel( scenerymodel1 );
            }
        }
    }

    private void parseServices(Element servicesElement, AirportModel airportModel)
    {
        ArrayList arrayList = new ArrayList();
        NodeList fuelElements = servicesElement.getElementsByTagName( "Fuel" );
        int i;

        for( i = 0; i < fuelElements.getLength(); ++i )
        {
            Element fuelElement = (Element) fuelElements.item( i );
            Object hashMap = new HashMap();

            ((HashMap) hashMap).put( "type", fuelElement.getAttribute( "type" ) );
            ((HashMap) hashMap).put( "availability", fuelElement.getAttribute( "availability" ) );
            arrayList.add( hashMap );
        }
        airportModel.setServicesAL( arrayList );
    }

    private void parseExcludes(Element excludeElement, AirportModel airportModel)
    {
        ExclusionModel exclusionModel = new ExclusionModel();
        NodeList nodeList;
        int i;
        Object commentData;
        float distance2;
        float f;
        float f1;
        java.awt.geom.Point2D.Float float1;

        exclusionModel.getVertex1().setLatLon( new LatLonPoint( getLat( excludeElement.getAttribute( "latitudeMinimum" ) ), getLon( excludeElement.getAttribute( "longitudeMinimum" ) ) ) );
        exclusionModel.getVertex2().setLatLon( new LatLonPoint( getLat( excludeElement.getAttribute( "latitudeMaximum" ) ), getLon( excludeElement.getAttribute( "longitudeMaximum" ) ) ) );
        exclusionModel.setExcludeAllObjects( getBoolean( excludeElement.getAttribute( "excludeAllObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeBeaconObjects" ) )
            exclusionModel.setExcludeBeaconObjects( getBoolean( excludeElement.getAttribute( "excludeBeaconObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeEffectObjects" ) )
            exclusionModel.setExcludeEffectObjects( getBoolean( excludeElement.getAttribute( "excludeEffectObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeExtrusionBridgeObjects" ) )
            exclusionModel.setExcludeExtrusionBridgeObjects( getBoolean( excludeElement.getAttribute( "excludeExtrusionBridgeObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeGenericBuildingObjects" ) )
            exclusionModel.setExcludeGenericBuildingObjects( getBoolean( excludeElement.getAttribute( "excludeGenericBuildingObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeLibraryObjects" ) )
            exclusionModel.setExcludeLibraryObjects( getBoolean( excludeElement.getAttribute( "excludeLibraryObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeTaxiwaySignObjects" ) )
            exclusionModel.setExcludeTaxiwaySignObjects( getBoolean( excludeElement.getAttribute( "excludeTaxiwaySignObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeTriggerObjects" ) )
            exclusionModel.setExcludeTriggerObjects( getBoolean( excludeElement.getAttribute( "excludeTriggerObjects" ) ) );
        if( excludeElement.hasAttribute( "excludeWindsockObjects" ) )
            exclusionModel.setExcludeWindsockObjects( getBoolean( excludeElement.getAttribute( "excludeWindsockObjects" ) ) );
        nodeList = excludeElement.getChildNodes();
        for( i = nodeList.getLength() - 1; i >= 0; --i )
        {
            if( nodeList.item( i ).getNodeType() == 8 )
            {
                commentData = ((Comment) nodeList.item( i )).getData().trim();
                if( ((String) commentData).startsWith( "<BorderOnly" ) )
                {
                    int startIndex = ((String) commentData).indexOf( "display=\"" );
                    int endIndex = ((String) commentData).indexOf( "\"", startIndex + 9 );

                    exclusionModel.setDisplayBorderOnly( getBoolean( ((String) commentData).substring( startIndex + 9, endIndex ) ) );
                }
            }
        }
        f = (float) airportModel.getAirportTestRadius();
        if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
            f = f * 3.2799999713897705078125F;
        else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
            f = f * 6074.56005859375F;
        commentData = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), exclusionModel.getVertex1().getLatLon().getLat(), exclusionModel.getVertex1().getLatLon().getLon(), 1.0F );
        f1 = (float) Math.sqrt( Math.pow( ((java.awt.geom.Point2D.Float) commentData).getX(), 2.0 ) + Math.pow( ((java.awt.geom.Point2D.Float) commentData).getY(), 2.0 ) );
        float1 = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), exclusionModel.getVertex2().getLatLon().getLat(), exclusionModel.getVertex2().getLatLon().getLon(), 1.0F );
        distance2 = (float) Math.sqrt( Math.pow( float1.getX(), 2.0 ) + Math.pow( float1.getY(), 2.0 ) );
        if( f1 < f || distance2 < f )
            airportModel.addExclusionModel( exclusionModel );
        else
            airportModel.addUnusedExclusionModel( exclusionModel );
    }

    private void parseBackgroundImage(Comment comment, AirportModel airportModel)
    {
        String commentData = comment.getData().trim();

        if( commentData.startsWith( "<BackgroundImage" ) )
        {
            int startIndex = commentData.indexOf( "path=\"" );
            int endIndex = commentData.indexOf( "\"", startIndex + 6 );
            String path = commentData.substring( startIndex + 6, endIndex );
            boolean imageVisible;
            double topLat;
            double topLon;
            double bottomLat;
            double bottomLon;
            File file;
            ImageIcon previewIcon;
            BufferedImage bufferedImage;

            startIndex = commentData.indexOf( "visible=\"" );
            endIndex = commentData.indexOf( "\"", startIndex + 9 );
            imageVisible = getBoolean( commentData.substring( startIndex + 9, endIndex ) );
            startIndex = commentData.indexOf( "topLat=\"" );
            endIndex = commentData.indexOf( "\"", startIndex + 8 );
            topLat = getLat( commentData.substring( startIndex + 8, endIndex ) );
            startIndex = commentData.indexOf( "topLon=\"" );
            endIndex = commentData.indexOf( "\"", startIndex + 8 );
            topLon = getLon( commentData.substring( startIndex + 8, endIndex ) );
            startIndex = commentData.indexOf( "bottomLat=\"" );
            endIndex = commentData.indexOf( "\"", startIndex + 11 );
            bottomLat = getLat( commentData.substring( startIndex + 11, endIndex ) );
            startIndex = commentData.indexOf( "bottomLon=\"" );
            endIndex = commentData.indexOf( "\"", startIndex + 11 );
            bottomLon = getLon( commentData.substring( startIndex + 11, endIndex ) );
            file = new File( path );
            if( !file.exists() )
            {
                file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( File.separator ).append( "My Documents" ).append( File.separator ).append( "My Pictures" ).append( File.separator ).append( "FSXPlanner" ).append( File.separator ).append( file.getName() ).toString() );
                if( !file.exists() )
                    return;
            }
            previewIcon = new ImageIcon( file.getAbsolutePath() );
            bufferedImage = new BufferedImage( previewIcon.getIconWidth(), previewIcon.getIconHeight(), 5 );
            bufferedImage.getGraphics().drawImage( previewIcon.getImage(), 0, 0, null );
            airportModel.addBackgroundImage( bufferedImage, path, imageVisible, new LatLonPoint( topLat, topLon ), new LatLonPoint( bottomLat, bottomLon ) );
        }
    }

    private VertexModel parseVertex(Element vertexElement)
    {
        VertexModel vertexModel = new VertexModel();
        HashMap distanceHM;

        if( vertexElement.hasAttribute( "lat" ) && vertexElement.hasAttribute( "lon" ) )
            vertexModel.setLatLon( new LatLonPoint( getLat( vertexElement.getAttribute( "lat" ) ), getLon( vertexElement.getAttribute( "lon" ) ) ) );
        if( vertexElement.hasAttribute( "biasX" ) )
        {
            distanceHM = getDistanceMeasurement( vertexElement.getAttribute( "biasX" ), new String[] { "M", "F" } );
            if( ((String) distanceHM.get( "distance" )).length() > 0 )
            {
                try
                {
                    vertexModel.setBiasX( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
            vertexModel.setBiasXMeasure( (String) distanceHM.get( "measure" ) );
        }
        if( vertexElement.hasAttribute( "biasZ" ) )
        {
            distanceHM = getDistanceMeasurement( vertexElement.getAttribute( "biasZ" ), new String[] { "M", "F" } );
            if( ((String) distanceHM.get( "distance" )).length() > 0 )
            {
                try
                {
                    vertexModel.setBiasZ( Float.parseFloat( (String) distanceHM.get( "distance" ) ) );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    LogEngine.getInstance().log( numberformatexception1 );
                }
            }
            vertexModel.setBiasZMeasure( (String) distanceHM.get( "measure" ) );
        }
        return vertexModel;
    }

    private HashMap getDistanceMeasurement(String distance, String[] options)
    {
        String distanceMeasure = options[0];
        int i = 0;
        HashMap hashmap1;

        while( i < options.length )
        {
            if( distance.endsWith( options[i] ) )
            {
                distanceMeasure = options[i];
                distance = distance.substring( 0, distance.length() - 1 );
                break;
            }
            else
                ++i;
        }
        hashmap1 = new HashMap();
        hashmap1.put( "measure", distanceMeasure );
        hashmap1.put( "distance", distance );
        return hashmap1;
    }

    private boolean getBoolean(String value)
    {
        if( value.equals( "YES" ) )
            value = "TRUE";
        else if( value.equals( "NO" ) )
            value = "FALSE";
        return Boolean.valueOf( value ).booleanValue();
    }

    private double getLat(String lat)
    {
        Object obj = lat.toLowerCase();
        Matcher matcher1 = latPattern1.matcher( (CharSequence) obj );
        double latitude = 0.0;

        if( matcher1.find() )
        {
            int spaceIndex = ((String) obj).indexOf( " " );
            String startLat = ((String) obj).substring( 1, spaceIndex );
            String endLat = ((String) obj).substring( spaceIndex );

            latitude = Double.parseDouble( startLat );
            latitude += Double.parseDouble( endLat ) / 60.0;
            if( ((String) obj).startsWith( "s" ) )
                latitude = latitude * -1.0;
        }
        else
        {
            try
            {
                latitude = Double.parseDouble( (String) obj );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        return latitude;
    }

    private double getLon(String lon)
    {
        Object obj = lon.toLowerCase();
        Matcher matcher1 = lonPattern1.matcher( (CharSequence) obj );
        double longitude = 0.0;

        if( matcher1.find() )
        {
            int spaceIndex = ((String) obj).indexOf( " " );
            String startLon = ((String) obj).substring( 1, spaceIndex );
            String endLon = ((String) obj).substring( spaceIndex );

            longitude = Double.parseDouble( startLon );
            longitude += Double.parseDouble( endLon ) / 60.0;
            if( ((String) obj).startsWith( "w" ) )
                longitude = longitude * -1.0;
        }
        else
        {
            try
            {
                longitude = Double.parseDouble( (String) obj );
            }
            catch( NumberFormatException numberformatexception1 )
            {
                LogEngine.getInstance().log( numberformatexception1 );
            }
        }
        return longitude;
    }

    private String getNodeValue(NodeList nodeList)
    {
        int i;

        for( i = 0; i < nodeList.getLength(); ++i )
        {
            Object node = (Element) nodeList.item( i );
            NodeList children = ((Node) node).getChildNodes();
            int j = 0;

            while( j < children.getLength() )
            {
                Node child = children.item( j );

                if( child.getNodeType() == 3 )
                    return child.getNodeValue();
                else
                    ++j;
            }
        }
        return "";
    }
}