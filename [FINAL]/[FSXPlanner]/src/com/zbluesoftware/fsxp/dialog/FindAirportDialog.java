package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.bgl.BGLScanner;
import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.filechooser.AirportDatFileFilter;
import com.zbluesoftware.fsxp.model.table.FindAirportTableModel;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import com.zbluesoftware.fsxp.engine.LogEngine;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JTextField;

class FindAirportDialog$1 extends Thread {

    FindAirportDialog$1(FindAirportDialog findairportdialog1)
    {
        this$0 = findairportdialog1;
    }

    FindAirportDialog this$0;     // final access specifier removed

    public void run()
    {
        BufferedWriter writer = null;
        File scanFile = new File( new StringBuilder().append( FindAirportDialog.access$000( this$0 ).getText() ).append( File.separator ).append( "airportScan.txt" ).toString() );
        File[] files;
        File file1;

        try
        {
            writer = new BufferedWriter( (Writer) new FileWriter( scanFile ) );
            writer.write( "i=icao,n=name,c=city,s=state,cn=country,r=region,fo=folder,fi=file" );
            writer.newLine();
            writer.flush();
        }
        catch( IOException ignored )
        {
            try
            {
                LogEngine.getInstance().log( ignored );
            }
            finally
            {
                    try
                    {
                        if( writer != null )
                            writer.close();
                    }
                    catch( IOException ioe1)
                    {
                    }
            }
        }
        finally
        {
                    try
                    {
                        if( writer != null )
                            writer.close();
                    }
                    catch( IOException ioe2)
                    {
                    }
        }
        file1 = new File( new StringBuilder().append( FindAirportDialog.access$100( this$0 ).getText() ).append( File.separator ).append( "Scenery" ).toString() );
        if( !file1.exists() )
        {
            FindAirportDialog.access$200( this$0, "Unable to find path to flight simulator X install.\n\nPlease make sure it is properly set." );
            ScanProgressDialog.hideDialog();
        }
        else
        {
            int i;

            files = file1.listFiles();
            for( i = 0; i < files.length; ++i )
            {
                if( FindAirportDialog.access$300( this$0 ) && files[i].isDirectory() )
                {
                    ScanProgressDialog.updateFolderDisplay( new StringBuilder().append( files[i].getName() ).append( " [" ).append( i + 1 ).append( " of " ).append( files.length ).append( "]" ).toString() );
                    FindAirportDialog.access$400( this$0, files[i] );
                }
            }
            ScanProgressDialog.hideDialog();
            if( FindAirportDialog.access$300( this$0 ) )
                FindAirportDialog.access$200( this$0, "Airport Scan Completed" );
            else
                FindAirportDialog.access$200( this$0, "Scan Cancelled" );
        }
    }
}

public class FindAirportDialog extends JDialog implements ActionListener, MouseListener, PropertyChangeListener {

    private FindAirportDialog(Frame parent)
    {
        super( parent, "Find Airport", true );
        StringBuffer buffer;
        ButtonGroup searchBG;
        Object mainPanel;
        Object findPanel;
        Object findSP;
        Object clickLabel;
        Object airportLabel;
        Object simLabel;
        Object instPanel;
        Object instTA;

        setResizable( false );
        instTA = new JTextArea( 2, 5 );
        ((JTextArea) instTA).setFont( Utilities.LABEL_FONT );
        ((JTextArea) instTA).setForeground( Color.black );
        ((JTextArea) instTA).setOpaque( false );
        ((JTextArea) instTA).setEditable( false );
        buffer = new StringBuffer();
        buffer.append( "This dialog is intended to help you find the BGL file that contains the default FSX airport you\n" );
        buffer.append( "wish to modify. Any airports you have added will not be seached by this dialog." );
        ((JTextArea) instTA).setText( buffer.toString() );
        instPanel = new JPanel();
        ((JPanel) instPanel).add( (Component) instTA );
        simLabel = new JLabel( "Path to Flight Simulator X install:" );
        ((JLabel) simLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) simLabel).setForeground( Color.black );
        simTF = new JTextField( SettingsEngine.getInstance().getSimFolder(), 40 );
        simTF.setFont( Utilities.TEXT_FIELD_FONT );
        simTF.setForeground( Color.black );
        simTF.setCaretPosition( 0 );
        simButton = new JButton( "Browse" );
        simButton.setFont( Utilities.BUTTON_FONT );
        simButton.setForeground( Color.black );
        simButton.addActionListener( this );
        sceneryRB = new JRadioButton( "Search by scanning sim scenery files", true );
        sceneryRB.setFont( Utilities.COMBO_BOX_FONT );
        sceneryRB.setForeground( Color.black );
        sceneryRB.addActionListener( this );
        datRB = new JRadioButton( "Search using SDK fs10.Airports.dat file", false );
        datRB.setFont( Utilities.COMBO_BOX_FONT );
        datRB.setForeground( Color.black );
        datRB.addActionListener( this );
        searchBG = new ButtonGroup();
        searchBG.add( (AbstractButton) sceneryRB );
        searchBG.add( (AbstractButton) datRB );
        sdkLabel = new JLabel( "Save airport scan file in the following folder:" );
        sdkLabel.setFont( Utilities.LABEL_FONT );
        sdkLabel.setForeground( Color.black );
        sdkTF = new JTextField( SettingsEngine.getInstance().getAirportScanFolder(), 40 );
        sdkTF.setFont( Utilities.TEXT_FIELD_FONT );
        sdkTF.setForeground( Color.black );
        sdkButton = new JButton( "Browse" );
        sdkButton.setFont( Utilities.BUTTON_FONT );
        sdkButton.setForeground( Color.black );
        sdkButton.addActionListener( this );
        scanButton = new JButton( "Scan Airports" );
        scanButton.setFont( Utilities.BUTTON_FONT );
        scanButton.setForeground( Color.black );
        scanButton.addActionListener( this );
        airportLabel = new JLabel( "Find airport by ICAO code" );
        ((JLabel) airportLabel).setFont( Utilities.LABEL_FONT );
        ((JLabel) airportLabel).setForeground( Color.black );
        airportTF = new JTextField( 6 );
        airportTF.setFont( Utilities.TEXT_FIELD_FONT );
        airportTF.setForeground( Color.black );
        airportTF.addActionListener( this );
        findButton = new JButton( "Find" );
        findButton.setFont( Utilities.BUTTON_FONT );
        findButton.setForeground( Color.black );
        findButton.addActionListener( this );
        nameLabel = new JLabel( "Find airport by name" );
        nameLabel.setFont( Utilities.LABEL_FONT );
        nameLabel.setForeground( Color.black );
        nameTF = new JTextField( 20 );
        nameTF.setFont( Utilities.TEXT_FIELD_FONT );
        nameTF.setForeground( Color.black );
        nameTF.addActionListener( this );
        findNameButton = new JButton( "Find" );
        findNameButton.setFont( Utilities.BUTTON_FONT );
        findNameButton.setForeground( Color.black );
        findNameButton.addActionListener( this );
        findTable = new JTable( (TableModel) new FindAirportTableModel() );
        findTable.setPreferredScrollableViewportSize( new Dimension( 550, 100 ) );
        findTable.addMouseListener( this );
        findSP = new JScrollPane( (Component) findTable );
        clickLabel = new JLabel( "Double click a row to open the airport" );
        ((JLabel) clickLabel).setFont( Utilities.BOLD_LABEL_FONT );
        ((JLabel) clickLabel).setForeground( Color.black );
        closeButton = new JButton( "Close" );
        closeButton.setFont( Utilities.BUTTON_FONT );
        closeButton.setForeground( Color.black );
        closeButton.addActionListener( this );
        findPanel = new JPanel( new GridBagLayout() );
        Utilities.addComponent( (Container) findPanel, (Component) airportLabel, 0, 0, 2, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) airportTF, 0, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) findButton, 1, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) nameLabel, 2, 0, 2, 1, 0, 17, new Insets( 1, 40, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) nameTF, 2, 1, 1, 1, 0, 17, new Insets( 1, 40, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) findNameButton, 3, 1, 1, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) findPanel, (Component) findSP, 0, 3, 4, 1, 1, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.5 );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) instPanel, 0, 0, 3, 1, 2, 10, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) simLabel, 0, 1, 3, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) simTF, 0, 2, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) simButton, 2, 2, 1, 1, 0, 17, new Insets( 1, 1, 1, 10 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) new JSeparator(), 0, 3, 3, 1, 2, 10, new Insets( 10, 1, 10, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sceneryRB, 0, 4, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) scanButton, 1, 4, 1, 1, 0, 17, new Insets( 10, 10, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) datRB, 0, 5, 2, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sdkLabel, 0, 6, 2, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sdkTF, 0, 7, 2, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) sdkButton, 2, 7, 1, 1, 0, 17, new Insets( 1, 1, 1, 10 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) new JSeparator(), 0, 8, 3, 1, 2, 10, new Insets( 10, 1, 10, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) findPanel, 0, 9, 3, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) clickLabel, 0, 10, 3, 1, 0, 10, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) new JSeparator(), 0, 11, 3, 1, 2, 10, new Insets( 10, 1, 10, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) closeButton, 0, 12, 3, 1, 0, 10, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        getContentPane().add( (Component) mainPanel, "Center" );
        pack();
        setLocationRelativeTo( parent );
        updateOptionDisplay();
    }

    private JTextField sdkTF;
    private JTextField airportTF;
    private JTextField nameTF;
    private JTextField simTF;
    private JButton simButton;
    private JRadioButton sceneryRB;
    private JRadioButton datRB;
    private JTable findTable;
    private JLabel nameLabel;
    private JLabel sdkLabel;
    private JButton sdkButton;
    private JButton scanButton;
    private JButton findButton;
    private JButton findNameButton;
    private JButton closeButton;
    private HashMap bglHM;
    private boolean working;
    private static FindAirportDialog dialog = null;

    public static HashMap showDialog(Frame parent)
    {
        if( dialog == null )
            dialog = new FindAirportDialog( parent );
        dialog.updateDialog();
        dialog.setVisible( true );
        return dialog.getBGLHM();
    }

    public void updateDialog()
    {
        bglHM = null;
        pack();
    }

    public HashMap getBGLHM()
    {
        return bglHM;
    }

    private void browseSDK()
    {
        JFileChooser fileChooser = new JFileChooser( sdkTF.getText() );

        fileChooser.setDialogTitle( "Select fs10.Airports File" );
        fileChooser.setFileFilter( (FileFilter) new AirportDatFileFilter() );
        if( fileChooser.showOpenDialog( (Component) this ) == 0 )
        {
            sdkTF.setText( fileChooser.getSelectedFile().getAbsolutePath() );
            SettingsEngine.getInstance().setAirportsDatPath( sdkTF.getText() );
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void browseScanFolder()
    {
        JFileChooser fileChooser = new JFileChooser( sdkTF.getText() );

        fileChooser.setDialogTitle( "Select Folder For Airport Scan File" );
        fileChooser.setApproveButtonText( "Select" );
        fileChooser.setFileSelectionMode( 1 );
        if( fileChooser.showOpenDialog( (Component) this ) == 0 )
        {
            sdkTF.setText( fileChooser.getSelectedFile().getAbsolutePath() );
            SettingsEngine.getInstance().setAirportScanFolder( sdkTF.getText() );
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void browseSimFolder()
    {
        JFileChooser fileChooser = new JFileChooser( simTF.getText() );

        fileChooser.setDialogTitle( "Select Sim Install Folder" );
        fileChooser.setApproveButtonText( "Select" );
        fileChooser.setFileSelectionMode( 1 );
        if( fileChooser.showOpenDialog( (Component) this ) == 0 )
        {
            simTF.setText( fileChooser.getSelectedFile().getAbsolutePath() );
            SettingsEngine.getInstance().setSimFolder( simTF.getText() );
            SettingsEngine.getInstance().writePreferences();
        }
    }

    private void searchDatForAirport()
    {
        String icao = airportTF.getText().trim();
        BufferedReader reader;

        SettingsEngine.getInstance().setAirportsDatPath( sdkTF.getText() );
        SettingsEngine.getInstance().writePreferences();
label_104:
        {
label_97:
            {
                if( icao.length() < 3 || icao.length() > 4 )
                    JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( "The ICAO code '" ).append( icao ).append( "' is not valid." ).toString(), "Invalid IACO Code...", 0 );
                else
                {
                    File airportFile = new File( SettingsEngine.getInstance().getAirportsDatPath() );

                    if( !airportFile.exists() )
                        JOptionPane.showMessageDialog( (Component) this, "The fs10.Aiports.dat file was not found.\n\nPlease make sure the path to the file is correct.", "fs10.Aiports Not Found.", 0 );
                    else
                    {
                        ((FindAirportTableModel) findTable.getModel()).clearData();
                        reader = null;
                        try
                        {
                            Object line;

                            reader = new BufferedReader( (Reader) new FileReader( airportFile ) );
                            line = null;
                            while( (line = reader.readLine()) != null )
                            {
                                String[] tokens;

                                if( !((String) line).toLowerCase().startsWith( icao.toLowerCase() ) )
                                    continue;
                                tokens = ((String) line).split( "," );
                                parseFileFolder( icao.toUpperCase(), tokens[1], tokens[2] );
                                JOptionPane.showMessageDialog( (Component) this, "Airport file found.", "File Found", 1 );
                                break label_97;
                            }
                            break label_104;
                        }
                        catch( FileNotFoundException filenotfoundexception1 )
                        {
                            try
                            {
                                LogEngine.getInstance().log( filenotfoundexception1 );
                            }
                            finally
                            {
                                    if( reader != null )
                                    {
                                        try
                                        {
                                            reader.close();
                                        }
                                        catch( IOException ioe1 )
                                        {
                                        }
                                    }
                            }
                        }
                        catch( IOException ioexception2 )
                        {
                            try
                            {
                                LogEngine.getInstance().log( ioexception2 );
                            }
                            finally
                            {
								try{
                                if( reader != null )
                                    reader.close();
								}
								catch (IOException ioe2) {}
                            }
                        }
                        finally
                        {
							try{
                            if( reader != null )
                                reader.close();
							}
							catch (IOException ioe3) {}
                        }
                        JOptionPane.showMessageDialog( (Component) this, "The Airport was not found", "File Not Found", 0 );
                    }
                }
                return;
            }
            if( reader != null )
            {
                try
                {
                    reader.close();
                }
                catch( IOException ignored )
                {
                }
            }
            return;
        }
        if( reader != null )
        {
            try
            {
                reader.close();
            }
            catch( IOException ioexception4 )
            {
            }
        }
    }

    private void parseFileFolder(String icao, String latString, String lonString)
    {
        int folder1 = (int) ((180.0 + Double.parseDouble( lonString )) / 30.0);
        int folder2 = (int) ((90.0 + Double.parseDouble( latString ) * -1.0) / 22.5);
        int file1 = (int) ((180.0 + Double.parseDouble( lonString )) / 3.75);
        int file2 = (int) ((90.0 - Double.parseDouble( latString )) / 2.8125);
        StringBuffer fileName = new StringBuffer();
        StringBuffer folderName;

        fileName.append( "APX" );
        if( file1 < 10 )
            fileName.append( "0" );
        fileName.append( file1 );
        if( file2 < 10 )
            fileName.append( "0" );
        fileName.append( file2 );
        fileName.append( "0.bgl" );
        folderName = new StringBuffer();
        if( folder1 < 10 )
            folderName.append( "0" );
        folderName.append( folder1 );
        if( folder2 < 10 )
            folderName.append( "0" );
        folderName.append( folder2 );
        ((FindAirportTableModel) findTable.getModel()).addRow( "", icao, "", "", "", folderName.toString(), fileName.toString() );
    }

    private void searchScanForICAO()
    {
        String icao = airportTF.getText().trim();

        SettingsEngine.getInstance().setAirportScanFolder( sdkTF.getText() );
        SettingsEngine.getInstance().writePreferences();
        if( icao.length() < 3 || icao.length() > 4 )
            JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( "The ICAO code '" ).append( icao ).append( "' is not valid." ).toString(), "Invalid IACO Code...", 0 );
        else
        {
            File airportFile;

            ((FindAirportTableModel) findTable.getModel()).clearData();
            airportFile = new File( new StringBuilder().append( sdkTF.getText() ).append( File.separator ).append( "airportScan.txt" ).toString() );
            if( !airportFile.exists() )
                JOptionPane.showMessageDialog( (Component) this, "The airport scan file does not exist.\n\nPlease scan the airports before using this search option.", "Scan File Not Found.", 0 );
            else
            {
                BufferedReader reader = null;

                try
                {
                    Object line;

                    reader = new BufferedReader( (Reader) new FileReader( airportFile ) );
                    line = null;
                    while( (line = reader.readLine()) != null )
                    {
                        if( !((String) line).toLowerCase().startsWith( new StringBuilder().append( "i=\"" ).append( icao.toLowerCase() ).toString() ) )
                            continue;
                        parseScanFile( (String) line );
                    }
                }
                catch( FileNotFoundException filenotfoundexception1 )
                {
                    try
                    {
                        LogEngine.getInstance().log( filenotfoundexception1 );
                    }
                    finally
                    {
                            if( reader != null )
                            {
                                try
                                {
                                    reader.close();
                                }
                                catch( IOException ignored )
                                {
                                }
                            }
                    }
                }
                catch( IOException ioexception2 )
                {
                    try
                    {
                        LogEngine.getInstance().log( ioexception2 );
                    }
                    finally
                    {
                    if( reader != null )
                    {
                        try
                        {
                            reader.close();
                        }
                        catch( IOException ioexception3 )
                        {
                        }
                    }
                    }
                }
                finally
                {
					try {
                    if( reader != null )
                        reader.close();
					} catch (IOException ioe4) {}
                }
                if( findTable.getRowCount() > 0 )
                    JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( findTable.getRowCount() ).append( " Airport(s) were found" ).toString(), "Airport(s) Found", 1 );
                else
                    JOptionPane.showMessageDialog( (Component) this, "The Airport was not found", "File Not Found", 0 );
            }
        }
    }

    private void searchScanForName()
    {
        String name = nameTF.getText().trim();

        SettingsEngine.getInstance().setAirportScanFolder( sdkTF.getText() );
        SettingsEngine.getInstance().writePreferences();
        if( name.length() < 3 )
            JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( "The name '" ).append( name ).append( "' is less than 3 characters." ).toString(), "Invalid Name...", 0 );
        else
        {
            File airportFile;

            ((FindAirportTableModel) findTable.getModel()).clearData();
            airportFile = new File( new StringBuilder().append( sdkTF.getText() ).append( File.separator ).append( "airportScan.txt" ).toString() );
            if( !airportFile.exists() )
                JOptionPane.showMessageDialog( (Component) this, "The airport scan file does not exist.\n\nPlease scan the airports before using this search option.", "Scan File Not Found.", 0 );
            else
            {
                BufferedReader reader = null;

                try
                {
                    Object line;
                    Pattern pattern;

                    reader = new BufferedReader( (Reader) new FileReader( airportFile ) );
                    line = null;
                    pattern = Pattern.compile( new StringBuilder().append( "n=\"[ \\w]*(" ).append( name.toLowerCase() ).append( ")" ).toString() );
                    while( (line = reader.readLine()) != null )
                    {
                        Matcher matcher = pattern.matcher( (CharSequence) ((String) line).toLowerCase() );

                        if( !matcher.find() )
                            continue;
                        parseScanFile( (String) line );
                    }
                }
                catch( FileNotFoundException filenotfoundexception1 )
                {
                    try
                    {
                        LogEngine.getInstance().log( filenotfoundexception1 );
                    }
                    finally
                    {
                            if( reader != null )
                            {
                                try
                                {
                                    reader.close();
                                }
                                catch( IOException ignored )
                                {
                                }
                            }
                    }
                }
                catch( IOException ioexception2 )
                {
                    try
                    {
                        LogEngine.getInstance().log( ioexception2 );
                    }
                    finally
                    {
						try {
                        if( reader != null )
                            reader.close();
						} catch (IOException ioe3) {}
                    }
                }
                finally
                {
					try {
                    if( reader != null )
                        reader.close();
					} catch (IOException ioe4) {}
                }
                if( findTable.getRowCount() > 0 )
                    JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( findTable.getRowCount() ).append( " Airport(s) were found" ).toString(), "Airport(s) Found", 1 );
                else
                    JOptionPane.showMessageDialog( (Component) this, "The Airport was not found", "File Not Found", 0 );
            }
        }
    }

    private void parseScanFile(String line)
    {
        int startIndex = 3;
        int endIndex = 0;
        String icao;
        String name;
        String city;
        String state;
        String country;
        String folder;
        String file;

        endIndex = line.indexOf( "\"," );
        icao = line.substring( 3, endIndex );
        startIndex = endIndex + 5;
        endIndex = line.indexOf( "\",", startIndex );
        name = line.substring( startIndex, endIndex );
        startIndex = endIndex + 5;
        endIndex = line.indexOf( "\",", startIndex );
        city = line.substring( startIndex, endIndex );
        startIndex = endIndex + 5;
        endIndex = line.indexOf( "\",", startIndex );
        state = line.substring( startIndex, endIndex );
        startIndex = endIndex + 6;
        endIndex = line.indexOf( "\",", startIndex );
        country = line.substring( startIndex, endIndex );
        startIndex = line.indexOf( "fo=\"" ) + 4;
        endIndex = line.indexOf( "\",", startIndex );
        folder = line.substring( startIndex, endIndex );
        startIndex = endIndex + 6;
        endIndex = line.indexOf( "\",", startIndex );
        file = line.substring( startIndex, endIndex );
        ((FindAirportTableModel) findTable.getModel()).addRow( name, icao, city, state, country, folder, file );
    }

    private void scanAirports()
    {
        Object thread;

        setWorking( true );
        ScanProgressDialog.showDialog( this, this );
        SettingsEngine.getInstance().setSimFolder( simTF.getText() );
        SettingsEngine.getInstance().setAirportScanFolder( sdkTF.getText() );
        SettingsEngine.getInstance().writePreferences();
        thread = new FindAirportDialog$1( this );
        ((Thread) thread).setPriority( 4 );
        ((Thread) thread).start();
    }

    private void scanFolder(File folder)
    {
        File[] files = folder.listFiles();
        int i;

        for( i = 0; i < files.length; ++i )
        {
            if( working && files[i].isDirectory() && files[i].getName().equalsIgnoreCase( "Scenery" ) )
                scanFiles( files[i] );
        }
    }

    private void scanFiles(File folder)
    {
        File[] files = folder.listFiles();
        int i;

        for( i = 0; i < files.length; ++i )
        {
            if( working && !files[i].isDirectory() && files[i].getName().startsWith( "APX" ) && files[i].getName().endsWith( ".bgl" ) )
            {
                ArrayList airportAL;
                BufferedWriter writer;
                File scanFile;

                ScanProgressDialog.updateFileDisplay( new StringBuilder().append( files[i].getName() ).append( " [" ).append( i + 1 ).append( " of " ).append( files.length ).append( "]" ).toString() );
                airportAL = BGLScanner.getInstance().scan( files[i], Utilities.MAIN_FRAME, false );
                writer = null;
                scanFile = new File( new StringBuilder().append( sdkTF.getText() ).append( File.separator ).append( "airportScan.txt" ).toString() );
                try
                {
                    int j;

                    writer = new BufferedWriter( (Writer) new FileWriter( scanFile, true ) );
                    for( j = 0; j < airportAL.size(); ++j )
                    {
                        HashMap hashMap = (HashMap) airportAL.get( j );

                        writer.write( "i=\"" );
                        writer.write( (String) hashMap.get( "icao" ) );
                        writer.write( "\"," );
                        writer.write( "n=\"" );
                        writer.write( (String) hashMap.get( "name" ) );
                        writer.write( "\"," );
                        writer.write( "c=\"" );
                        writer.write( (String) hashMap.get( "city" ) );
                        writer.write( "\"," );
                        writer.write( "s=\"" );
                        writer.write( (String) hashMap.get( "state" ) );
                        writer.write( "\"," );
                        writer.write( "cn=\"" );
                        writer.write( (String) hashMap.get( "country" ) );
                        writer.write( "\"," );
                        writer.write( "r=\"" );
                        writer.write( (String) hashMap.get( "region" ) );
                        writer.write( "\"," );
                        writer.write( "fo=\"" );
                        writer.write( folder.getParentFile().getName() );
                        writer.write( "\"," );
                        writer.write( "fi=\"" );
                        writer.write( files[i].getName() );
                        writer.write( "\"," );
                        writer.newLine();
                    }
                    writer.flush();
                }
                catch( IOException ioexception1 )
                {
                    try
                    {
                        LogEngine.getInstance().log( ioexception1 );
                    }
                    finally
                    {
                            try
                            {
                                if( writer != null )
                                    writer.close();
                            }
                            catch( IOException ignored )
                            {
                            }
                    }
                }
                finally
                {
				    try
                    {
                        if( writer != null )
                            writer.close();
                    }
                    catch( IOException ioexception2 )
                    {
                    }
                }
            }
        }
    }

    private void setWorking(boolean working)
    {
        this.working = working;
    }

    private void displayMessage(String message)
    {
        JOptionPane.showMessageDialog( (Component) this, message, "Airport Scan...", 1 );
    }

    private void updateOptionDisplay()
    {
        scanButton.setEnabled( sceneryRB.isSelected() );
        nameLabel.setEnabled( sceneryRB.isSelected() );
        nameTF.setEnabled( sceneryRB.isSelected() );
        findNameButton.setEnabled( sceneryRB.isSelected() );
        if( sceneryRB.isSelected() )
        {
            sdkLabel.setText( "Save airport scan file in the following folder:" );
            sdkTF.setText( SettingsEngine.getInstance().getAirportScanFolder() );
            sdkTF.setCaretPosition( 0 );
        }
        else
        {
            sdkLabel.setText( "Path to Flight Simulator SDK's fs10.Airports.dat file:" );
            sdkTF.setText( SettingsEngine.getInstance().getAirportsDatPath() );
            sdkTF.setCaretPosition( 0 );
        }
    }

    private void openBGLFile(Point point)
    {
        int row = findTable.rowAtPoint( point );

        if( row != -1 )
        {
            bglHM = new HashMap();
            bglHM.put( "icao", (String) findTable.getValueAt( row, 1 ) );
            bglHM.put( "folder", (String) findTable.getValueAt( row, 5 ) );
            bglHM.put( "file", (String) findTable.getValueAt( row, 6 ) );
            SettingsEngine.getInstance().setSimFolder( simTF.getText() );
            SettingsEngine.getInstance().writePreferences();
            setVisible( false );
        }
    }

    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == sdkButton )
        {
            if( datRB.isSelected() )
                browseSDK();
            else
                browseScanFolder();
        }
        else if( event.getSource() == findButton || event.getSource() == airportTF )
        {
            if( datRB.isSelected() )
                searchDatForAirport();
            else
                searchScanForICAO();
        }
        else if( event.getSource() == findNameButton || event.getSource() == nameTF )
            searchScanForName();
        else if( event.getSource() == closeButton )
            setVisible( false );
        else if( event.getSource() == scanButton )
            scanAirports();
        else if( event.getSource() == sceneryRB )
            updateOptionDisplay();
        else if( event.getSource() == datRB )
            updateOptionDisplay();
        else if( event.getSource() == simButton )
            browseSimFolder();
    }

    public void mouseEntered(MouseEvent event)
    {
    }

    public void mouseExited(MouseEvent event)
    {
    }

    public void mousePressed(MouseEvent event)
    {
    }

    public void mouseReleased(MouseEvent event)
    {
    }

    public void mouseClicked(MouseEvent event)
    {
        if( event.getSource() == findTable && event.getClickCount() == 2 )
            openBGLFile( event.getPoint() );
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if( event.getSource() instanceof ScanProgressDialog && event.getPropertyName().equals( "working" ) )
            setWorking( ((Boolean) event.getNewValue()).booleanValue() );
    }

    static JTextField access$000(FindAirportDialog x0)
    {
        return x0.sdkTF;
    }

    static JTextField access$100(FindAirportDialog x0)
    {
        return x0.simTF;
    }

    static void access$200(FindAirportDialog x0, String x1)
    {
        x0.displayMessage( x1 );
    }

    static boolean access$300(FindAirportDialog x0)
    {
        return x0.working;
    }

    static void access$400(FindAirportDialog x0, File x1)
    {
        x0.scanFolder( x1 );
    }
}