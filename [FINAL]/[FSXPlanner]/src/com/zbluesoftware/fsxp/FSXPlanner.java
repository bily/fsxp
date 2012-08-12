package com.zbluesoftware.fsxp;

import com.zbluesoftware.fsxp.dialog.ApproachesDialog;
import com.zbluesoftware.fsxp.dialog.BackgroundImageDialog;
import com.zbluesoftware.fsxp.dialog.ComDialog;
import com.zbluesoftware.fsxp.dialog.CompileDialog;
import com.zbluesoftware.fsxp.dialog.ErrorCheckingDialog;
import com.zbluesoftware.fsxp.dialog.FSXConnectionDialog;
import com.zbluesoftware.fsxp.dialog.FindAirportDialog;
import com.zbluesoftware.fsxp.dialog.FindTaxiwayPointDialog;
import com.zbluesoftware.fsxp.dialog.LockingDialog;
import com.zbluesoftware.fsxp.dialog.MoveAirportDialog;
import com.zbluesoftware.fsxp.dialog.NewAirportDialog;
import com.zbluesoftware.fsxp.dialog.PreferencesDialog;
import com.zbluesoftware.fsxp.dialog.PrepareDialog;
import com.zbluesoftware.fsxp.dialog.ServicesDialog;
import com.zbluesoftware.fsxp.dialog.TaxiNameDialog;
import com.zbluesoftware.fsxp.engine.CursorEngine;
import com.zbluesoftware.fsxp.engine.FontEngine;
import com.zbluesoftware.fsxp.engine.PrintEngine;
import com.zbluesoftware.fsxp.engine.RecentFileEngine;
import com.zbluesoftware.fsxp.engine.SaveEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.engine.UpdateEngine;
import com.zbluesoftware.fsxp.event.MenuActionEvent;
import com.zbluesoftware.fsxp.event.MenuActionListener;
import com.zbluesoftware.fsxp.filechooser.BGLFileFilter;
import com.zbluesoftware.fsxp.filechooser.XMLFileFilter;
import com.zbluesoftware.fsxp.menu.FSXPMenuBar;
import com.zbluesoftware.fsxp.menu.MenuAction;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import com.zbluesoftware.fsxp.menu.RibbonMenu;
import com.zbluesoftware.fsxp.model.AirportListModel;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.ApronEdgeLightModel;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.BlastFenceModel;
import com.zbluesoftware.fsxp.model.BoundaryFenceModel;
import com.zbluesoftware.fsxp.model.CopiedItem;
import com.zbluesoftware.fsxp.model.HistoryListModel;
import com.zbluesoftware.fsxp.model.HistoryModel;
import com.zbluesoftware.fsxp.model.OpenAirportModel;
import com.zbluesoftware.fsxp.model.PlaneModel;
import com.zbluesoftware.fsxp.model.SelectedItem;
import com.zbluesoftware.fsxp.model.TaxiNameModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.startup.StartupDialog;
import com.zbluesoftware.fsxp.thread.ILSLineThread;
import com.zbluesoftware.fsxp.thread.ILSLineUpdates;
import com.zbluesoftware.fsxp.ui.AirportFrame;
import com.zbluesoftware.fsxp.ui.BottomBar;
import com.zbluesoftware.fsxp.ui.CountWindow;
import com.zbluesoftware.fsxp.ui.HistoryWindow;
import com.zbluesoftware.fsxp.ui.MainDisplay;
import com.zbluesoftware.fsxp.ui.MemoryWindow;
import com.zbluesoftware.fsxp.ui.NavToolBarWindow;
import com.zbluesoftware.fsxp.ui.TopBar;
import com.zbluesoftware.fsxp.ui.data.ApronEdgeLightData;
import com.zbluesoftware.fsxp.ui.data.BlastFenceData;
import com.zbluesoftware.fsxp.ui.data.FenceData;
import com.zbluesoftware.fsxp.ui.data.ILSData;
import com.zbluesoftware.fsxp.ui.data.NDBData;
import com.zbluesoftware.fsxp.ui.data.RunwayData;
import com.zbluesoftware.fsxp.ui.data.TaxiwayParkingData;
import com.zbluesoftware.fsxp.ui.data.VORData;
import com.zbluesoftware.fsxp.ui.display.AirportDisplay;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.SplashScreen;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.zbluesoftware.fsxp.engine.UpdateEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import com.zbluesoftware.fsxp.engine.OpenEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import com.zbluesoftware.fsxp.engine.OpenEngine;
import com.zbluesoftware.fsxp.event.MenuActionEvent;
import javax.swing.Action;
import com.zbluesoftware.fsxp.bgl.BGLDecompiler;
import java.io.File;
import org.w3c.dom.Document;
import com.zbluesoftware.fsxp.engine.OpenEngine;
import com.zbluesoftware.fsxp.util.Utilities;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import com.zbluesoftware.fsxp.model.OpenAirportModel;
import com.zbluesoftware.fsxp.ui.MainDisplay;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class FSXPlanner$7 implements ActionListener {
    FSXPlanner$7(FSXPlanner fsxplanner1)
    {
        this$0 = fsxplanner1;
    }
    FSXPlanner this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent event)
    {
        String ident = OpenAirportModel.getInstance().getAirportIdentAt( Integer.parseInt( event.getActionCommand() ) );
        MainDisplay.getInstance().showAirportFrame( ident );
    }
}

class FSXPlanner$6 extends Thread {
    FSXPlanner$6(FSXPlanner fsxplanner1)
    {
        this$0 = fsxplanner1;
    }
    FSXPlanner this$0;     // final access specifier removed
    public void run()
    {
        HashMap hashMap = new HashMap();
        ArrayList identAL = null;
        hashMap.put( "ident", "KLGA" );
        hashMap.put( "file", new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "KLGA - La Guardia.xml" ).toString() );
        identAL = OpenEngine.getInstance().openRecentFile( Utilities.MAIN_FRAME, hashMap );
        FSXPlanner.access$000( this$0, identAL );
    }
}

class FSXPlanner$5 extends Thread {
    FSXPlanner$5(FSXPlanner fsxplanner1, File file1, boolean bool, String s)
    {
        this$0 = fsxplanner1;
        val$bglFile = file1;
        val$readOBX = bool;
        val$icao = s;
    }
    File val$bglFile;     // final access specifier removed
    boolean val$readOBX;     // final access specifier removed
    String val$icao;     // final access specifier removed
    FSXPlanner this$0;     // final access specifier removed
    public void run()
    {
        Document xmlDoc = BGLDecompiler.getInstance().decompile( val$bglFile, Utilities.MAIN_FRAME, val$readOBX, false, val$icao );
        ArrayList identAL = new ArrayList();

        if( val$icao != null )
        {
            Object hashMap = new HashMap();

            ((HashMap) hashMap).put( "ident", val$icao );
            ((HashMap) hashMap).put( "fileName", "" );
            identAL.add( hashMap );
        }
        OpenEngine.getInstance().parseXMLDocument( xmlDoc, Utilities.MAIN_FRAME, identAL );
        FSXPlanner.access$000( this$0, identAL );
    }
}

class FSXPlanner$4 extends Thread {
    FSXPlanner$4(FSXPlanner fsxplanner1, MenuActionEvent menuactionevent1)
    {
        this$0 = fsxplanner1;
        val$event = menuactionevent1;
    }
    MenuActionEvent val$event;     // final access specifier removed
    FSXPlanner this$0;     // final access specifier removed
    public void run()
    {
        ArrayList identAL = OpenEngine.getInstance().openRecentFile( Utilities.MAIN_FRAME, (HashMap) ((Action) val$event.getSource()).getValue( "LongDescription" ) );
        FSXPlanner.access$000( this$0, identAL );
    }
}

class FSXPlanner$3 extends Thread {
    FSXPlanner$3(FSXPlanner fsxplanner1)
    {
        this$0 = fsxplanner1;
    }
    FSXPlanner this$0;     // final access specifier removed
    public void run()
    {
        ArrayList identAL = OpenEngine.getInstance().openAirport( Utilities.MAIN_FRAME );
        FSXPlanner.access$000( this$0, identAL );
    }
}

class FSXPlanner$2 extends Thread {
    FSXPlanner$2(FSXPlanner fsxplanner1)
    {
        this$0 = fsxplanner1;
    }
    FSXPlanner this$0;     // final access specifier removed
    public void run()
    {
        ArrayList updateAL = UpdateEngine.checkForUpdates();

        if( updateAL.size() > 0 )
        {
            UpdateEngine updateEngine = new UpdateEngine( Utilities.MAIN_FRAME, updateAL );

            updateEngine.setVisible( true );
        }
    }
}

class FSXPlanner$1 extends WindowAdapter {
    FSXPlanner$1(FSXPlanner fsxplanner1)
    {
        this$0 = fsxplanner1;
    }
    FSXPlanner this$0;     // final access specifier removed
    public void windowClosing(WindowEvent event)
    {
        this$0.shutDownProcedure();
    }
}

public class FSXPlanner extends JFrame implements ChangeListener, MenuActionListener, PropertyChangeListener {

    public FSXPlanner()
    {
        super( "FSX Planner [R 28] - DECOMPILED BY BAO HONG & LEE WEI, DSTA 2008 -" );
        Date date1 = null;
        Date today = null;
        int maxMemory = 0;

        addWindowListener( (WindowListener) new FSXPlanner$1( this ) );
        Utilities.MAIN_FRAME = (Frame) this;
        SplashScreen splashScreen = new SplashScreen();
        SettingsEngine.getInstance();
        FontEngine.getInstance();
        if( SettingsEngine.getInstance().getRestoreWindow() && SettingsEngine.getInstance().getScreenBounds().width > 0 )
            setBounds( SettingsEngine.getInstance().getScreenBounds() );
        else
            setBounds( Utilities.fullScreen() );
        getContentPane().setLayout( new BorderLayout( 0, 0 ) );
        if( SettingsEngine.getInstance().getRibbonMenus() )
        {
            MenuFactory.getInstance().initializeMenus( this );
            getContentPane().add( (Component) RibbonMenu.getInstance().initializeRibbon(), "North" );
            getContentPane().add( (Component) BottomBar.getInstance(), "South" );
        }
        else
        {
            setJMenuBar( (JMenuBar) MenuFactory.getInstance().initializeMenus( this ) );
            getContentPane().add( (Component) TopBar.getInstance(), "North" );
        }
        getContentPane().add( (Component) MainDisplay.getInstance(), "Center" );
        MainDisplay.getInstance().addPropertyChangeListener( this );
        FSXConnection.getInstance().addPropertyChangeListener( this );
        RecentFileEngine.getInstance().setMenuActionListener( this );
        OpenAirportModel.getInstance().addChangeListener( this );
        ILSLineThread ilsLineThread = new ILSLineThread( ILSLineUpdates.getInstance() );
        ilsLineThread.setPriority( 4 );
        ilsLineThread.start();
        setVisible( true );
        if( SettingsEngine.getInstance().getDisplayMemory() )
            MemoryWindow.showWindow( (Frame) this );
        splashScreen.setVisible( false );
        splashScreen.dispose();
        if( SettingsEngine.getInstance().isFirstTime() && StartupDialog.showDialog( (Frame) this ) )
            openExampleAirport();
        maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024L / 1024L);
        if( maxMemory < 200 )
        {
            StringBuffer buffer = new StringBuffer( "WARNING:\n" );
            buffer.append( "The maximum memory available to FSX Planner is currently only " ).append( maxMemory ).append( " MB.\n" );
            buffer.append( "This can cause issues with the opening of bgl files, resulting in crashes\n" );
            buffer.append( "or out of memory errors.\n" );
            buffer.append( "\n" );
            buffer.append( "Please make sure you started FSX Planner by using the .exe file\n" );
            buffer.append( "and not the .jar file.\n" );
            JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Memory Low Error...", 0 );
        }
        date1 = SettingsEngine.getInstance().getLastUpdate();
        today = new Date();
        if( today.getTime() - date1.getTime() > 2592000000L )
        {
            Thread thread = new FSXPlanner$2( this );
			thread.setPriority( 4 );
            thread.start();
        }
    }

    public static void main(String[] args)
    {
        FSXPlanner fsxPlanner = new FSXPlanner();
    }

    public void shutDownProcedure()
    {
        MainDisplay.getInstance().checkAllAirports();
        SettingsEngine.getInstance().setScreenBounds( getBounds() );
        SettingsEngine.getInstance().writePreferences();
        System.exit( 0 );
    }

    private void createAirport()
    {
        AirportModel airportModel = NewAirportDialog.showDialog( (Frame) this );

        if( airportModel != null )
        {
            checkForTaxiwayName( airportModel );
            AirportListModel.getInstance().addAirportModel( airportModel );
            MainDisplay.getInstance().addAirportFrame( airportModel.getIdent(), airportModel.getFileName() );
            enableMenuOptions( true );
            OpenAirportModel.getInstance().addAirport( "", airportModel.getIdent() );
        }
    }

    private void openAirport()
    {
        Thread thread = new FSXPlanner$3( this );
        thread.setPriority( 4 );
        thread.start();
    }

    private void openRecentFile(MenuActionEvent event)
    {
        Thread thread = new FSXPlanner$4( this, event );
        thread.setPriority( 4 );
        thread.start();
    }

    private void openBGLFile(String fileName, String icao, boolean readOBX)
    {
		Object fileChooser = null;
        if( fileName == null )
        {
            fileChooser = new JFileChooser( SettingsEngine.getInstance().getLastOpenedBGLDir() );
            ((JFileChooser) fileChooser).setDialogTitle( "Open Airport BGL File" );
            ((JFileChooser) fileChooser).setAcceptAllFileFilterUsed( false );
            ((JFileChooser) fileChooser).setFileFilter( (FileFilter) new BGLFileFilter() );
            if( ((JFileChooser) fileChooser).showOpenDialog( (Component) this ) == 0 )
            {
                fileName = ((JFileChooser) fileChooser).getSelectedFile().getAbsolutePath();
                SettingsEngine.getInstance().setLastOpenedBGLDir( ((JFileChooser) fileChooser).getSelectedFile().getParent() );
                SettingsEngine.getInstance().writePreferences();
            }
        }
		else
        {
            fileChooser = new File( fileName );
            thread = new FSXPlanner$5( this, (File) fileChooser, readOBX, icao );
            thread.setPriority( 4 );
            thread.start();
        }
    }

    private void displayAirport(ArrayList identAL)
    {
        for(int i = 0; i < identAL.size(); ++i )
        {
            HashMap hashMap = (HashMap) identAL.get( i );
            String ident = (String) hashMap.get( "ident" );
            String fileName = (String) hashMap.get( "fileName" );
            String fileName2 = null;

            if( AirportListModel.getInstance().getAirportFrame( ident, fileName ) == null )
                MainDisplay.getInstance().addAirportFrame( ident, fileName );
            else
                MainDisplay.getInstance().showAirportFrame( AirportListModel.getInstance().getAirportFrame( ident, fileName ) );
            enableMenuOptions( true );
            ILSLineUpdates.getInstance().addAirportModel( AirportListModel.getInstance().getAirportModel( ident, fileName ) );
            fileName2 = fileName;
            if( fileName2.lastIndexOf( "\\" ) > 0 )
                fileName2 = fileName2.substring( fileName2.lastIndexOf( "\\" ) + 1 );
            OpenAirportModel.getInstance().addAirport( fileName2, ident );
            checkForTaxiwayName( AirportListModel.getInstance().getAirportModel( ident, fileName ) );
        }
    }

    private void findAirport()
    {
        HashMap hashMap = FindAirportDialog.showDialog( (Frame) this );

        if( hashMap != null )
        {
            String icao = (String) hashMap.get( "icao" );
            String folder = (String) hashMap.get( "folder" );
            String file = (String) hashMap.get( "file" );
            String bglFile = new StringBuilder().append( SettingsEngine.getInstance().getSimFolder() ).append( File.separator ).append( "Scenery" ).append( File.separator ).append( folder ).append( File.separator ).append( "scenery" ).append( File.separator ).append( file ).toString();

            openBGLFile( bglFile, icao, true );
        }
    }

    private void openExampleAirport()
    {
        Thread thread = new FSXPlanner$6( this );
		thread.setPriority( 4 );
        thread.start();
    }

    private void checkForTaxiwayName(AirportModel airportModel)
    {
        ArrayList arrayList = airportModel.getTaxiNameAL();
        int blankExists = 0;
        int blankIndex = -1;
        int i = arrayList.size() - 1;

        while( i >= 0 )
        {
            TaxiNameModel taxiNameModel = (TaxiNameModel) arrayList.get( i );

            if( taxiNameModel.getName().length() == 0 )
            {
                blankExists = 1;
                airportModel.setBlankTaxiNameIndex( taxiNameModel.getIndex() );
                break;
            }
            else
            {
                blankIndex = Math.max( blankIndex, taxiNameModel.getIndex() );
                --i;
            }
        }
        if( blankExists == 0 && blankIndex < 255 )
        {
            TaxiNameModel taxinamemodel1 = new TaxiNameModel();

            taxinamemodel1.setName( "" );
            taxinamemodel1.setIndex( blankIndex + 1 );
            airportModel.addTaxiNameModel( taxinamemodel1 );
            airportModel.setBlankTaxiNameIndex( blankIndex + 1 );
        }
    }

    private void enableMenuOptions(boolean status)
    {
        boolean connected = false;
        MenuFactory.getInstance().getMenuAction( new Integer( 6 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 7 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 8 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 9 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 10 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 24 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 25 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 26 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 28 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 29 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 30 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 31 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 71 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 72 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 73 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 77 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 64 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 65 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 66 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 67 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 68 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 69 ) ).setEnabled( status );
        MenuFactory.getInstance().getMenuAction( new Integer( 70 ) ).setEnabled( status );
        connected = status & FSXConnection.getInstance().isConnectedToFSX();
        MenuFactory.getInstance().getMenuAction( new Integer( 75 ) ).setEnabled( connected );
        MenuFactory.getInstance().getMenuAction( new Integer( 76 ) ).setEnabled( connected );
    }

    private void enabledSimConnectMenuOptions(boolean connected)
    {
        connected = connected & MenuFactory.getInstance().getMenuAction( new Integer( 0x6 ) ).isEnabled();
        MenuFactory.getInstance().getMenuAction( new Integer( 75 ) ).setEnabled( connected );
        MenuFactory.getInstance().getMenuAction( new Integer( 76 ) ).setEnabled( connected );
    }

    private void compileAirport()
    {
        AirportModel airportModel = MainDisplay.getInstance().getCurrentAirportModel();

        if( airportModel != null )
            CompileDialog.showDialog( (Frame) this, airportModel );
        else
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
    }

    private void saveAirport()
    {
        AirportModel airportModel = MainDisplay.getInstance().getCurrentAirportModel();

        if( airportModel != null )
        {
            if( airportModel.getFileName().length() > 0 )
            {
                SaveEngine.getInstance().saveAirport( airportModel, false );
                JOptionPane.showMessageDialog( (Component) this, "Save Complete", "Save...", 1 );
            }
            else
                saveAirportAs();
        }
        else
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
    }

    private void saveAirport(String ident, String fileName)
    {
        AirportModel airportModel = AirportListModel.getInstance().getAirportModel( ident, fileName );

        if( airportModel != null )
        {
            if( airportModel.getFileName().length() > 0 )
            {
                SaveEngine.getInstance().saveAirport( airportModel, false );
                JOptionPane.showMessageDialog( (Component) this, "Save Complete", "Save...", 1 );
            }
            else
                saveAirportAs();
        }
        else
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
    }


    private void saveAirportAs()
    {
        AirportModel airportModel = MainDisplay.getInstance().getCurrentAirportModel();
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle( new StringBuilder().append( "Save Airport XML [" ).append( airportModel.getIdent() ).append( "]" ).toString() );
        fileChooser.setFileFilter( (FileFilter) new XMLFileFilter() );
        if( airportModel.getFileName().length() > 0 )
            fileChooser.setCurrentDirectory( new File( airportModel.getFileName() ) );
        if( fileChooser.showSaveDialog( (Component) this ) == 0 )
        {
            String fileName = fileChooser.getSelectedFile().getAbsolutePath();

            if( !fileName.endsWith( ".xml" ) )
                fileName = new StringBuilder().append( fileName ).append( ".xml" ).toString();
            if( fileChooser.getSelectedFile().exists() && JOptionPane.showConfirmDialog( (Component) this, new StringBuilder().append( "Are you sure you want to over-write the file: " ).append( fileChooser.getSelectedFile().getName() ).append( "?" ).toString(), "File Exists...", 0 ) != 0 )
                return;
            else
            {
                String oldFileName = airportModel.getFileName();
                String newFileName = null;
                ArrayList identAL = null;
                Object hashMap = null;

                if( oldFileName.lastIndexOf( "\\" ) > 0 )
                    oldFileName = oldFileName.substring( oldFileName.lastIndexOf( "\\" ) + 1 );
                newFileName = fileName;
                if( newFileName.lastIndexOf( "\\" ) > 0 )
                    newFileName = newFileName.substring( newFileName.lastIndexOf( "\\" ) + 1 );
                OpenAirportModel.getInstance().updateAirportName( oldFileName, airportModel.getIdent(), newFileName, airportModel.getIdent() );
                airportModel.setFileName( fileName );
                SaveEngine.getInstance().saveAirport( airportModel, true );
                JOptionPane.showMessageDialog( (Component) this, "Save Complete", "Save...", 1 );
                identAL = new ArrayList();
                hashMap = new HashMap();
                ((HashMap) hashMap).put( "ident", airportModel.getIdent() );
                ((HashMap) hashMap).put( "fileName", fileName );
                identAL.add( hashMap );
                RecentFileEngine.getInstance().newFileOpened( new File( fileName ), identAL );
            }
        }
    }

    private void closeAirport()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model != null )
        {
            int option = JOptionPane.showConfirmDialog( (Component) this, new StringBuilder().append( "Would you like to save the airport " ).append( model.getIdent() ).append( " before closing?" ).toString(), "Close Airport...", 1 );

            if( option != 2 )
            {
                if( option == 0 )
                    saveAirport();
                MainDisplay.getInstance().removeCurrentAirport();
                AirportListModel.getInstance().removeAirportModel( model );
                AirportListModel.getInstance().removeAirportFrame( model.getIdent(), model.getFileName() );
                removeFromView( model );
            }
        }
    }

    private void removeFromView(AirportModel model)
    {
        if( model != null )
        {
            String fileName = model.getFileName();

            if( fileName.lastIndexOf( "\\" ) > 0 )
                fileName = fileName.substring( fileName.lastIndexOf( "\\" ) + 1 );
            OpenAirportModel.getInstance().removeAirport( fileName, model.getIdent() );
        }
    }

    private void cleanAprons()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model == null )
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
        else
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append( "This option will scan the airport for Aprons that consist\n" );
            buffer.append( "of two or fewer vertices and Apron Edge Lights that consist\n" );
            buffer.append( " of only one vertex and delete them.\n" );
            buffer.append( "\n" );
            buffer.append( "Would you like to delete these items?" );
            if( JOptionPane.showConfirmDialog( (Component) this, buffer.toString(), "Clean Up Aprons...", 0 ) == 0 )
            {
                int totalDeletedAprons = model.cleanUpApronModels();
                int totalDeletedEdgeLights = model.cleanUpApronEdgeLightModels();

                buffer = new StringBuffer();
                buffer.append( totalDeletedAprons );
                buffer.append( " Aprons have been deleted.\n\n" );
                buffer.append( totalDeletedEdgeLights );
                buffer.append( " Apron Edge Lights have been  deleted." );
                JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Delete Complete...", 1 );
            }
        }
    }

    private void cleanFences()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model == null )
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
        else
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append( "This option will scan the airport for Boundary Fences and Blast Fances\n" );
            buffer.append( "that consist of only one vertex and delete them.\n" );
            buffer.append( "\n" );
            buffer.append( "Would you like to delete the Boundary Fences and Blast Fences with only one vertex?" );
            if( JOptionPane.showConfirmDialog( (Component) this, buffer.toString(), "Clean Up Fences...", 0 ) == 0 )
            {
                int totalBoundariesDeleted = model.cleanUpBoundaryFenceModels();
                int totalBlastFencesDeleted = model.cleanUpBlastFenceModels();

                buffer = new StringBuffer();
                buffer.append( totalBoundariesDeleted );
                buffer.append( " Boundary Fences have been deleted.\n\n" );
                buffer.append( totalBlastFencesDeleted );
                buffer.append( " Blast Fences have been  deleted." );
                JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Delete Complete...", 1 );
            }
        }
    }

    private void cleanTaxiways()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model == null )
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
        else
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append( "This option will scan the airport for Taxiways that either:\n" );
            buffer.append( " 1) Start and end at the same taxiway point or\n" );
            buffer.append( " 2) Have a non-existant start or end point\n" );
            buffer.append( "and delete them.\n" );
            buffer.append( "\n" );
            buffer.append( "Would you like to find and delete these Taxiways?" );
            if( JOptionPane.showConfirmDialog( (Component) this, buffer.toString(), "Clean Up Taxiways...", 0 ) == 0 )
            {
                int totalDeleted = model.cleanUpTaxiways();

                JOptionPane.showMessageDialog( (Component) this, new StringBuilder().append( totalDeleted ).append( " taxiways have been deleted." ).toString(), "Delete Complete...", 1 );
            }
        }
    }

    private void cleanTaxiwayPoints()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model == null )
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
        else
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append( "This option will scan the airport for Taxiway Points and Taxiway Parking Locations\n" );
            buffer.append( "that have no taxiways associated with them and delete them.\n" );
            buffer.append( "\n" );
            buffer.append( "Would you like to find and delete these Taxiway Points?" );
            if( JOptionPane.showConfirmDialog( (Component) this, buffer.toString(), "Clean Up Taxiway Points...", 0 ) == 0 )
            {
                int totalPointsDeleted = model.cleanUpTaxiwayPoints();
                int totalParkingDeleted = model.cleanUpTaxiwayParking();

                MainDisplay.getInstance().repaintAirport();
                buffer = new StringBuffer();
                buffer.append( totalPointsDeleted );
                buffer.append( " Taxiway Points have been deleted.\n\n" );
                buffer.append( totalParkingDeleted );
                buffer.append( " Taxiway Parking Locations have been  deleted." );
                JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Delete Complete...", 1 );
            }
        }
    }

    public void cleanUpAll()
    {
        AirportModel model = MainDisplay.getInstance().getCurrentAirportModel();

        if( model == null )
            JOptionPane.showMessageDialog( (Component) this, "No airport is currently selected", "No Selection...", 0 );
        else
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append( "This option will run the following clean up options:\n" );
            buffer.append( " 1) Clean Up Aprons & Edge Lights\n" );
            buffer.append( " 2) Clean Up Fences\n" );
            buffer.append( " 3) Clean Up Taxiways\n" );
            buffer.append( " 4) Clean Up Taxiway Points\n" );
            buffer.append( "\n" );
            buffer.append( "Would you like to run these options?" );
            if( JOptionPane.showConfirmDialog( (Component) this, buffer.toString(), "Clean Up All...", 0 ) == 0 )
            {
                int totalApronsDeleted = model.cleanUpApronModels();
                int totalApronEdgeLightsDeleted = model.cleanUpApronEdgeLightModels();
                int totalBoundariesDeleted = model.cleanUpBoundaryFenceModels();
                int totalBlastFencesDeleted = model.cleanUpBlastFenceModels();
                int totalTaxiwaysDeleted = model.cleanUpTaxiways();
                int totalPointsDeleted = model.cleanUpTaxiwayPoints();
                int totalParkingDeleted = model.cleanUpTaxiwayParking();

                MainDisplay.getInstance().repaintAirport();
                buffer = new StringBuffer();
                buffer.append( totalApronsDeleted );
                buffer.append( " Aprons have been deleted.\n\n" );
                buffer.append( totalApronEdgeLightsDeleted );
                buffer.append( " Apron Edge Lights have been deleted.\n\n" );
                buffer.append( totalBoundariesDeleted );
                buffer.append( " Boundary Fences have been deleted.\n\n" );
                buffer.append( totalBlastFencesDeleted );
                buffer.append( " Blast Fences have been deleted.\n\n" );
                buffer.append( totalTaxiwaysDeleted );
                buffer.append( " Taxiways have been deleted.\n\n" );
                buffer.append( totalPointsDeleted );
                buffer.append( " Taxiway Points have been deleted.\n\n" );
                buffer.append( totalParkingDeleted );
                buffer.append( " Taxiway Parking Locations have been  deleted." );
                JOptionPane.showMessageDialog( (Component) this, buffer.toString(), "Delete Complete...", 1 );
            }
        }
    }

    private void breakFence()
    {
        if( SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel )
            MainDisplay.getInstance().getCurrentAirportFrame().getFenceData().cutString();
        else if( SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel )
            MainDisplay.getInstance().getCurrentAirportFrame().getBlastFenceData().cutString();
        else if( SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel )
            MainDisplay.getInstance().getCurrentAirportFrame().getApronEdgeLightData().cutString();
    }

    private void updateViewMenus()
    {
        String[] openFiles = OpenAirportModel.getInstance().getAirportNames();
        JMenu viewMenu = FSXPMenuBar.getInstance().getMenu( "View" );

        if( viewMenu != null )
        {
            int i = 0;

            for( i = viewMenu.getItemCount() - 1; i > 3; --i )
                viewMenu.remove( i );
            for( i = 0; i < openFiles.length; ++i )
            {
                JMenuItem menuItem = new JMenuItem( openFiles[i], null );

                menuItem.setFont( Utilities.MENU_FONT );
                menuItem.setActionCommand( new StringBuilder().append( "" ).append( i ).toString() );
                menuItem.addActionListener( (ActionListener) new FSXPlanner$7( this ) );
                viewMenu.add( menuItem );
            }
        }
    }

    private void moveFSXPlaneToIcon()
    {
        AirportModel airportModel = MainDisplay.getInstance().getCurrentAirportModel();

        if( airportModel != null )
            FSXConnection.getInstance().movePlane( ((PlaneModel) airportModel.getPlaneAL().get( 0 )).getLatLon(), ((PlaneModel) airportModel.getPlaneAL().get( 0 )).getHeading() );
    }


    public void stateChanged(ChangeEvent event)
    {
        if( event.getSource() == OpenAirportModel.getInstance() )
            updateViewMenus();
    }


    public void menuItemSelected(MenuActionEvent event)
    {
        int actionCommand = Integer.parseInt( (String) event.getActionCommand() );

        switch( actionCommand )
        {
            case 1:
                createAirport();
                break;
            case 2:
                findAirport();
                break;
            case 3:
                openAirport();
                break;
            case 4:
                openBGLFile( null, null, false );
                break;
            case 5:
                openRecentFile( event );
                break;
            case 6:
                saveAirport();
                break;
            case 7:
                saveAirportAs();
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            case 8:
                closeAirport();
                break;
            case 9:
                break;
            case 10:
                PrintEngine.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                MainDisplay.getInstance().repaintAirport();
                break;
            case 15:
                PreferencesDialog.showDialog( (Frame) this );
                break;
            case 16:
                UpdateEngine updateEngine = new UpdateEngine( (Frame) this );
                updateEngine.setVisible( true );
                break;
            case 17:
                shutDownProcedure();
                break;
            case 18:
                HistoryListModel.getInstance().undoItem( HistoryListModel.getInstance().getModel( 0 ) );
                MainDisplay.getInstance().repaintSelectedAirport();
                break;
            case 19:
                HistoryListModel.getInstance().redoItem();
                MainDisplay.getInstance().repaintSelectedAirport();
                break;
            case 20:
                HistoryWindow.getInstance( (Frame) this ).setVisible( true );
                break;
            case 21:
                CopiedItem.getInstance().setCopiedItem( SelectedItem.getInstance().getBaseModel() );
                MainDisplay.getInstance().deleteSelectedItem();
                break;
            case 22:
                CopiedItem.getInstance().setCopiedItem( SelectedItem.getInstance().getBaseModel() );
                break;
            case 23:
                MainDisplay.getInstance().pasteItem( (BaseModel) CopiedItem.getInstance().getCopiedItem().clone() );
                break;
            case 24:
                MainDisplay.getInstance().resetAirport();
                break;
            case 25:
                FindTaxiwayPointDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel(), this );
                break;
            case 26:
                BackgroundImageDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                MainDisplay.getInstance().getCurrentAirportFrame().getAirportDisplay().resetBGImages();
                break;
            case 27:
                LockingDialog.showDialog( (Frame) this );
                break;
            case 28:
                CountWindow.getInstance( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() ).setVisible( true );
                break;
            case 29:
                TaxiNameDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 30:
                ServicesDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 31:
                MoveAirportDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 32:
                SettingsEngine.getInstance().setDisplayRunway( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayRunway()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 33:
                SettingsEngine.getInstance().setDisplayILS( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayILS()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 34:
                SettingsEngine.getInstance().setDisplayApron( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayApron()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 35:
                SettingsEngine.getInstance().setDisplayApronEL( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayApronEL()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 36:
                SettingsEngine.getInstance().setDisplayTWPoint( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWPoint()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 37:
                SettingsEngine.getInstance().setDisplayTWSign( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWSign()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 38:
                SettingsEngine.getInstance().setDisplayParking( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayParking()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 39:
                SettingsEngine.getInstance().setDisplayJetways( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayJetways()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 40:
                SettingsEngine.getInstance().setShowRunwayPaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowRunwayPaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 41:
                SettingsEngine.getInstance().setShowParkingPaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowParkingPaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 42:
                SettingsEngine.getInstance().setShowTaxiPaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowTaxiPaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 43:
                SettingsEngine.getInstance().setShowPathPaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowPathPaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 44:
                SettingsEngine.getInstance().setShowClosedPaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowClosedPaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 45:
                SettingsEngine.getInstance().setShowVehiclePaths( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowVehiclePaths()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 46:
                SettingsEngine.getInstance().setDisplayTower( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTower()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 47:
                SettingsEngine.getInstance().setDisplayBoundFence( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBoundFence()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 48:
                SettingsEngine.getInstance().setDisplayBlastFence( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBlastFence()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 49:
                SettingsEngine.getInstance().setDisplayHelipad( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayHelipad()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 50:
                SettingsEngine.getInstance().setDisplayStart( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayStart()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 51:
                SettingsEngine.getInstance().setDisplayLatLon( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayLatLon()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 52:
                SettingsEngine.getInstance().setDisplayExcludes( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayExcludes()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 53:
                SettingsEngine.getInstance().setDisplayAirportCtr( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayAirportCtr()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 54:
                SettingsEngine.getInstance().setDisplayTestRadius( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTestRadius()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 55:
                SettingsEngine.getInstance().setDisplayBGImage( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBGImage()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 56:
                SettingsEngine.getInstance().setDisplayMarkers( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayMarkers()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 57:
                SettingsEngine.getInstance().setDisplayNDBs( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayNDBs()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 58:
                SettingsEngine.getInstance().setDisplayVORs( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayVORs()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 59:
                SettingsEngine.getInstance().setDisplayWindsocks( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayWindsocks()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 60:
                SettingsEngine.getInstance().setDisplayTriggers( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTriggers()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 61:
                SettingsEngine.getInstance().setDisplayScenery( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayScenery()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 62:
                SettingsEngine.getInstance().setDisplayRotation( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayRotation()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 63:
                PrepareDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 64:
                ErrorCheckingDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel(), this );
                break;
            case 65:
                cleanAprons();
                break;
            case 66:
                cleanFences();
                break;
            case 67:
                cleanTaxiways();
                break;
            case 68:
                cleanTaxiwayPoints();
                break;
            case 69:
                cleanUpAll();
                break;
            case 70:
                compileAirport();
                break;
            case 71:
                ComDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 72:
                ApproachesDialog.showDialog( (Frame) this, MainDisplay.getInstance().getCurrentAirportModel() );
                break;
            case 73:
                SettingsEngine.getInstance().setdisplayLights( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayLights()) ? true : false );
                SettingsEngine.getInstance().writePreferences();
                repaint();
                break;
            case 74:
                FSXConnectionDialog.showDialog( (Frame) this );
                break;
            case 75:
                MainDisplay.getInstance().getCurrentAirportFrame().getAirportDisplay().movePlaneToFSX( true, true );
                break;
            case 76:
                moveFSXPlaneToIcon();
                break;
            case 77:
                MainDisplay.getInstance().getCurrentAirportFrame().getAirportDisplay().movePlaneToCursor();
                break;
            case 78:
                FSXConnection.getInstance().setShowPlane( (!com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().getShowPlane()) ? true : false );
                FSXConnection.getInstance().writePreferences();
                MainDisplay.getInstance().setRecreate( true );
                repaint();
                break;
            case 79:
                FSXConnection.getInstance().setCursorAutoFollow( FSXConnection.getInstance().getFSXAutoFollow() );
                FSXConnection.getInstance().setFSXAutoFollow( (!com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().getFSXAutoFollow()) ? true : false );
                FSXConnection.getInstance().writePreferences();
                break;
            case 80:
                FSXConnection.getInstance().setFSXAutoFollow( FSXConnection.getInstance().getCursorAutoFollow() );
                FSXConnection.getInstance().setCursorAutoFollow( (!com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().getCursorAutoFollow()) ? true : false );
                FSXConnection.getInstance().writePreferences();
                break;
            case 81:
                MainDisplay.getInstance().updatePlaneMake( "Airbus A321" );
                break;
            case 82:
                MainDisplay.getInstance().updatePlaneMake( "Boeing 737" );
                break;
            case 83:
                MainDisplay.getInstance().updatePlaneMake( "Boeing 747" );
                break;
            case 84:
                MainDisplay.getInstance().updatePlaneMake( "Bombardier CRJ700" );
                break;
            case 85:
                MainDisplay.getInstance().updatePlaneMake( "Bombardier LearJet" );
                break;
            case 86:
                MainDisplay.getInstance().updatePlaneMake( "Beechcraft Baron 58" );
                break;
            case 87:
                MainDisplay.getInstance().updatePlaneMake( "Beechcraft Kingair 350" );
                break;
            case 88:
                MainDisplay.getInstance().updatePlaneMake( "Cessna 172" );
                break;
            case 89:
                MainDisplay.getInstance().updatePlaneMake( "Cessna C208B" );
                break;
            case 90:
                MainDisplay.getInstance().updatePlaneMake( "de Havilland Beaver" );
                break;
            case 91:
                MainDisplay.getInstance().updatePlaneMake( "Douglas DC-3" );
                break;
            case 92:
                MainDisplay.getInstance().updatePlaneMake( "Extra 300S" );
                break;
            case 93:
                MainDisplay.getInstance().updatePlaneMake( "Grumman Goose" );
                break;
            case 94:
                MainDisplay.getInstance().updatePlaneMake( "Maule Orion" );
                break;
            case 95:
                MainDisplay.getInstance().updatePlaneMake( "Mooney Bravo" );
                break;
            case 96:
                MainDisplay.getInstance().updatePlaneMake( "Piper Cub" );
                break;
            case 97:
                MainDisplay.getInstance().updatePlaneMake( "Bell Jet Ranger" );
                break;
            case 98:
                MainDisplay.getInstance().updatePlaneMake( "Robinson R22" );
                break;
            case 99:
                MainDisplay.getInstance().updatePlaneMake( "AirCreation Trike" );
                break;
            case 100:
                MainDisplay.getInstance().updatePlaneMake( "DG Flugzeubau" );
                break;
            case 101:
                MainDisplay.getInstance().updateAirportDataDisplay( ((JCheckBoxMenuItem) FSXPMenuBar.getInstance().getMenu( "View" ).getItem( 0 )).getState() );
                break;
            case 102:
                MainDisplay.getInstance().updateObjectDataDisplay( ((JCheckBoxMenuItem) FSXPMenuBar.getInstance().getMenu( "View" ).getItem( 1 )).getState() );
                break;
            case 103:
                if( ((JCheckBoxMenuItem) FSXPMenuBar.getInstance().getMenu( "View" ).getItem( 2 )).getState() )
                    NavToolBarWindow.showWindow( (Frame) this );
                else
                    NavToolBarWindow.hideWindow();
                break;
            case 104:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/donate.cfm" );
                break;
            case 105:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/basics.cfm" );
                break;
            case 106:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/bgl.cfm" );
                break;
            case 107:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/compiling.cfm" );
                break;
            case 108:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/simconnect.cfm" );
                break;
            case 109:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/aprons.cfm" );
                break;
            case 110:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/taxiways.cfm" );
                break;
            case 111:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/taxiwaySigns.cfm" );
                break;
            case 112:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/fuelTriggers.cfm" );
                break;
            case 113:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/backgroundImages.cfm" );
                break;
            case 114:
                Utilities.openURL( "http://www.zbluesoftware.com/fsxplanner/features.cfm" );
                break;
            case 115:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getDefaultCursor() );
                break;
            case 116:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getScrollCursor() );
                break;
            case 117:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getZoomInCursor() );
                break;
            case 118:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getZoomOutCursor() );
                break;
            case 119:
                CursorEngine.getInstance().setParkingType( "DOCK_GA" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 120:
                CursorEngine.getInstance().setParkingType( "FUEL" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 121:
                CursorEngine.getInstance().setParkingType( "GATE_HEAVY" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 122:
                CursorEngine.getInstance().setParkingType( "GATE_MEDIUM" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 123:
                CursorEngine.getInstance().setParkingType( "GATE_SMALL" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 124:
                CursorEngine.getInstance().setParkingType( "RAMP_CARGO" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 125:
                CursorEngine.getInstance().setParkingType( "RAMP_GA" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 126:
                CursorEngine.getInstance().setParkingType( "RAMP_GA_LARGE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 127:
                CursorEngine.getInstance().setParkingType( "RAMP_GA_MEDIUM" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 128:
                CursorEngine.getInstance().setParkingType( "RAMP_GA_SMALL" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 129:
                CursorEngine.getInstance().setParkingType( "RAMP_MIL_CARGO" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 130:
                CursorEngine.getInstance().setParkingType( "RAMP_MIL_COMBAT" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 131:
                CursorEngine.getInstance().setParkingType( "VEHICLE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getParkingCursor() );
                break;
            case 132:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getStartCursor() );
                break;
            case 133:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTowerCursor() );
                break;
            case 134:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiPointCursor() );
                break;
            case 135:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getILSTaxiPointCursor() );
                break;
            case 136:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getHSTaxiPointCursor() );
                break;
            case 137:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwaySignCursor() );
                break;
            case 138:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getApronEdgeLightCursor() );
                break;
            case 139:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getHelipadCursor() );
                break;
            case 140:
                CursorEngine.getInstance().setTaxiwayType( "RUNWAY" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 141:
                CursorEngine.getInstance().setTaxiwayType( "PARKING" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 142:
                CursorEngine.getInstance().setTaxiwayType( "TAXI" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 143:
                CursorEngine.getInstance().setTaxiwayType( "PATH" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 144:
                CursorEngine.getInstance().setTaxiwayType( "CLOSED" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 145:
                CursorEngine.getInstance().setTaxiwayType( "VEHICLE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getTaxiwayCursor() );
                break;
            case 146:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getApronCursor() );
                break;
            case 147:
                CursorEngine.getInstance().setRunwaySurface( "ASPHALT" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 148:
                CursorEngine.getInstance().setRunwaySurface( "BITUMINOUS" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 149:
                CursorEngine.getInstance().setRunwaySurface( "BRICK" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 150:
                CursorEngine.getInstance().setRunwaySurface( "CLAY" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 151:
                CursorEngine.getInstance().setRunwaySurface( "CEMENT" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 152:
                CursorEngine.getInstance().setRunwaySurface( "CONCRETE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 153:
                CursorEngine.getInstance().setRunwaySurface( "CORAL" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 154:
                CursorEngine.getInstance().setRunwaySurface( "DIRT" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 155:
                CursorEngine.getInstance().setRunwaySurface( "GRASS" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 156:
                CursorEngine.getInstance().setRunwaySurface( "GRAVEL" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 157:
                CursorEngine.getInstance().setRunwaySurface( "ICE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 158:
                CursorEngine.getInstance().setRunwaySurface( "MACADAM" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 159:
                CursorEngine.getInstance().setRunwaySurface( "OIL_TREATED, PLANKS" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 160:
                CursorEngine.getInstance().setRunwaySurface( "SAND" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 161:
                CursorEngine.getInstance().setRunwaySurface( "SHALE" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 162:
                CursorEngine.getInstance().setRunwaySurface( "SNOW" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 163:
                CursorEngine.getInstance().setRunwaySurface( "STEEL_MATS" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 164:
                CursorEngine.getInstance().setRunwaySurface( "TARMAC" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 165:
                CursorEngine.getInstance().setRunwaySurface( "UNKNOWN" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 166:
                CursorEngine.getInstance().setRunwaySurface( "WATER" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getRunwayCursor() );
                break;
            case 167:
                CursorEngine.getInstance().setFenceSize( "{f2c1f621-8089-42a4-8719-e77ec7139cc4}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFenceCursor() );
                break;
            case 168:
                CursorEngine.getInstance().setFenceSize( "{c0224543-5d3f-4063-8de4-cd6d0de92650}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFenceCursor() );
                break;
            case 169:
                CursorEngine.getInstance().setFenceSize( "{5acb60e6-992b-41c7-929b-e31d5343cd29}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFenceCursor() );
                break;
            case 170:
                CursorEngine.getInstance().setFenceSize( "{a3a491b1-ef49-47db-9c2b-080f48a5ea5d}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFenceCursor() );
                break;
            case 171:
                CursorEngine.getInstance().setFenceSize( "{f8c92217-857d-4dad-ab16-c6258fd76cdb}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFenceCursor() );
                break;
            case 172:
                CursorEngine.getInstance().setBlastFenceSize( "{91ce5d88-59b9-41e2-9776-b371490fc378}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getBlastFenceCursor() );
                break;
            case 173:
                CursorEngine.getInstance().setBlastFenceSize( "{28c633da-8926-48aa-b467-30799560462e}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getBlastFenceCursor() );
                break;
            case 174:
                CursorEngine.getInstance().setBlastFenceSize( "{a8beb882-f11f-4743-b09b-34f45f380802}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getBlastFenceCursor() );
                break;
            case 175:
                CursorEngine.getInstance().setBlastFenceSize( "{5b05e5fc-915b-4c87-a31d-615bc9dbf1ab}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getBlastFenceCursor() );
                break;
            case 176:
                CursorEngine.getInstance().setBlastFenceSize( "{bafc77d3-fa52-4cc2-b713-7609560bb026}" );
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getBlastFenceCursor() );
                break;
            case 177:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getJetwayCursor() );
                break;
            case 178:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getILSBeamCursor() );
                break;
            case 179:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getMarkerCursor() );
                break;
            case 180:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getVORCursor() );
                break;
            case 181:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getNDBCursor() );
                break;
            case 182:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getWindsockCursor() );
                break;
            case 183:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getExcludeCursor() );
                break;
            case 184:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getFuelCursor() );
                break;
            case 185:
                MainDisplay.getInstance().setAirportDisplayCursor( CursorEngine.getInstance().getSceneryCursor() );
                break;
            case 186:
                HistoryWindow.getInstance( (Frame) this ).displayDetails();
                break;
            case 187:
                HistoryWindow.getInstance( (Frame) this ).undoChange();
                break;
            case 188:
                breakFence();
                break;
            case 189:
                MainDisplay.getInstance().getCurrentAirportFrame().getRunwayData().addILSBeam( true );
                break;
            case 190:
                MainDisplay.getInstance().getCurrentAirportFrame().getRunwayData().addILSBeam( false );
                break;
            case 191:
                MainDisplay.getInstance().getCurrentAirportFrame().getTaxiwayParkingData().addFuelTrigger();
                break;
            case 192:
                MainDisplay.getInstance().getCurrentAirportFrame().getTaxiwayParkingData().addFuelStation();
                break;
            case 193:
                MainDisplay.getInstance().getCurrentAirportFrame().getILSData().addLocalizerArray();
                break;
            case 194:
                MainDisplay.getInstance().getCurrentAirportFrame().getVORData().addVORStation();
                break;
            case 195:
                MainDisplay.getInstance().getCurrentAirportFrame().getNDBData().addNDBAntenna();
                break;
            default:
                break;
            //case 11:
                //break;
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if( event.getSource() instanceof ErrorCheckingDialog )
        {
            if( event.getPropertyName().equals( "modelSelected" ) )
                MainDisplay.getInstance().focusOn( (BaseModel) event.getNewValue() );
        }
        else if( event.getSource() instanceof FindTaxiwayPointDialog )
        {
            if( event.getPropertyName().equals( "modelSelected" ) )
                MainDisplay.getInstance().focusOn( (BaseModel) event.getNewValue() );
        }
        else if( event.getSource() == MainDisplay.getInstance() )
        {
            if( event.getPropertyName().equals( "enableMenuOptions" ) )
                enableMenuOptions( true );
            else if( event.getPropertyName().equals( "disableMenuOptions" ) )
                enableMenuOptions( false );
            else if( event.getPropertyName().equals( "saveAndCloseAirport" ) )
            {
                saveAirport( (String) event.getNewValue(), (String) event.getOldValue() );
                AirportListModel.getInstance().removeAirportModel( (String) event.getNewValue(), (String) event.getNewValue() );
            }
            else if( event.getPropertyName().equals( "removeFromView" ) )
                removeFromView( AirportListModel.getInstance().getAirportModel( (String) event.getNewValue(), (String) event.getOldValue() ) );
        }
        else if( event.getSource() instanceof FSXConnection && event.getPropertyName().equals( "connectedToFSX" ) )
            enabledSimConnectMenuOptions( ((Boolean) event.getNewValue()).booleanValue() );
    }

    static void access$000(FSXPlanner x0, ArrayList x1)
    {
        x0.displayAirport( x1 );
    }
}