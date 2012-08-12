package gmapimagecutter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

public class MainFrame extends JFrame {


    public MainFrame()
    {
        try
        {
            setDefaultCloseOperation( 3 );
            jbInit();
        }
        catch( Exception exception )
        {
            exception.printStackTrace();
        }
    }

    private String imageFileExts;
    private String imageFilesDescription;
    ImageIcon mainIcon;
    JPanel contentPane;
    private DecimalFormat df = new DecimalFormat( "0.######" );
    private JFileChooser chooser = new JFileChooser();
    BorderLayout borderLayout1 = new BorderLayout();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenuFile = new JMenu();
    JMenuItem jMenuFileExit = new JMenuItem();
    JMenu jMenuHelp = new JMenu();
    JMenuItem jMenuHelpAbout = new JMenuItem();
    JLabel statusBar = new JLabel();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenu jMenu1 = new JMenu();
    JMenuItem createMenuItem = new JMenuItem();
    JPanel rightPanel = new JPanel();
    JSlider jSlider1 = new JSlider();
    JLabel jLabel1 = new JLabel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    JLabel tileCountLabel = new JLabel();
    JButton createButton = new JButton();
    ImagePanel centrePanel = new ImagePanel();
    JLabel jLabel2 = new JLabel();
    ButtonGroup qualityRadioGroup = new ButtonGroup();
    JRadioButtonMenuItem lowQualityMenuItem = new JRadioButtonMenuItem();
    JRadioButtonMenuItem highQualityMenuItem = new JRadioButtonMenuItem();


    private void jbInit()
        throws Exception
    {
        HashSet hash = null;
        String[] names = null;
        int i = 0;

        mainIcon = GMapImgCutApp.loadIcon( "images/mainicon.gif", "GMapImageCutter" );
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout( borderLayout1 );
        setIconImage( mainIcon.getImage() );
        setSize( new Dimension( 400, 300 ) );
        setTitle( "Google Maps Image Cutter" );
        statusBar.setText( " " );
        jMenuFile.setText( "File" );
        jMenuFileExit.setText( "Exit" );
        jMenuFileExit.addActionListener( (ActionListener) new MainFrame_jMenuFileExit_ActionAdapter( this ) );
        jMenuHelp.setText( "Help" );
        jMenuHelpAbout.setText( "About" );
        jMenuHelpAbout.addActionListener( (ActionListener) new MainFrame_jMenuHelpAbout_ActionAdapter( this ) );
        jMenuItem1.setText( "Open File..." );
        jMenuItem1.addActionListener( (ActionListener) new MainFrame_jMenuItem1_actionAdapter( this ) );
        jMenu1.setText( "Processing" );
        createMenuItem.setEnabled( false );
        createMenuItem.setText( "Create..." );
        createMenuItem.addActionListener( (ActionListener) new MainFrame_jMenuItem2_actionAdapter( this ) );
        jSlider1.setOrientation( 1 );
        jSlider1.setMajorTickSpacing( 5 );
        jSlider1.setMaximum( 17 );
        jSlider1.setMinorTickSpacing( 1 );
        jSlider1.setPaintLabels( true );
        jSlider1.setPaintTicks( true );
        jSlider1.setEnabled( false );
        jSlider1.addChangeListener( (ChangeListener) new MainFrame_jSlider1_changeAdapter( this ) );
        jLabel1.setEnabled( false );
        jLabel1.setText( "Tile Count:" );
        rightPanel.setLayout( gridBagLayout1 );
        tileCountLabel.setEnabled( false );
        tileCountLabel.setText( "0" );
        createButton.setEnabled( false );
        createButton.setSelectedIcon( null );
        createButton.setText( "Create..." );
        createButton.addActionListener( (ActionListener) new MainFrame_jButton1_actionAdapter( this ) );
        contentPane.setPreferredSize( new Dimension( 640, 400 ) );
        jLabel2.setEnabled( false );
        jLabel2.setText( "Max Zoom Level" );
        centrePanel.addMouseMotionListener( (MouseMotionListener) new MainFrame_centrePanel_mouseMotionAdapter( this ) );
        centrePanel.addMouseListener( (MouseListener) new MainFrame_centrePanel_mouseAdapter( this ) );
        qualityRadioGroup.add( (AbstractButton) lowQualityMenuItem );
        qualityRadioGroup.add( (AbstractButton) highQualityMenuItem );
        lowQualityMenuItem.setText( "Low Quality Images" );
        highQualityMenuItem.setSelected( true );
        highQualityMenuItem.setText( "High Quality Images" );
        jMenuBar1.add( jMenuFile );
        jMenuBar1.add( jMenu1 );
        jMenuFile.add( jMenuItem1 );
        jMenuFile.add( jMenuFileExit );
        jMenuBar1.add( jMenuHelp );
        jMenuHelp.add( jMenuHelpAbout );
        setJMenuBar( jMenuBar1 );
        contentPane.add( (Component) statusBar, "South" );
        contentPane.add( (Component) rightPanel, "East" );
        jMenu1.add( (JMenuItem) lowQualityMenuItem );
        jMenu1.add( (JMenuItem) highQualityMenuItem );
        jMenu1.addSeparator();
        jMenu1.add( createMenuItem );
        contentPane.add( (Component) centrePanel, "Center" );
        rightPanel.add( (Component) jLabel2, new GridBagConstraints( 0, 0, 2, 1, 0.0, 0.0, 10, 0, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        rightPanel.add( (Component) tileCountLabel, new GridBagConstraints( 1, 2, 1, 1, 0.0, 0.0, 10, 0, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        rightPanel.add( (Component) createButton, new GridBagConstraints( 0, 3, 2, 1, 0.0, 0.0, 10, 0, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        rightPanel.add( (Component) jLabel1, new GridBagConstraints( 0, 2, 1, 1, 0.0, 0.0, 17, 0, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        rightPanel.add( (Component) jSlider1, new GridBagConstraints( 0, 1, 2, 1, 0.0, 0.800000000000000044408921, 11, 3, new Insets( 0, 0, 0, 0 ), 0, 0 ) );
        hash = new HashSet();
        names = ImageIO.getReaderFormatNames();
        for( i = 0; i < names.length; ++i )
            hash.add( names[i] );
        imageFileExts = "";
        imageFilesDescription = " ";
        if( hash.contains( "jpeg" ) || hash.contains( "jpg" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".jpg.jpeg" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".jpg .jpeg " ).toString();
        }
        if( hash.contains( "gif" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".gif" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".gif " ).toString();
        }
        if( hash.contains( "png" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".png" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".png " ).toString();
        }
        if( hash.contains( "tif" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".tif.tiff" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".tif .tiff " ).toString();
        }
        if( hash.contains( "bmp" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".bmp" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".bmp " ).toString();
        }
        if( hash.contains( "raw" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".raw" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".raw " ).toString();
        }
        if( hash.contains( "pnm" ) )
        {
            imageFileExts = new StringBuilder().append( imageFileExts ).append( ".pnm" ).toString();
            imageFilesDescription = new StringBuilder().append( imageFilesDescription ).append( ".pnm " ).toString();
        }
    }


    void jMenuFileExit_actionPerformed(ActionEvent actionEvent)
    {
        System.exit( 0 );
    }

    void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent)
    {
        MainFrame_AboutBox dlg = new MainFrame_AboutBox( (Frame) this );
        Dimension dlgSize = dlg.getPreferredSize();
        Dimension frmSize = getSize();
        Point loc = getLocation();

        dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y );
        dlg.setModal( true );
        dlg.pack();
        dlg.show();
    }

  public void jMenuItem1_actionPerformed(ActionEvent e)
  {
    chooser.setAcceptAllFileFilterUsed(true);
    chooser.setFileFilter(new ImageFileFilter());
    if (chooser.showOpenDialog(this) == 0)
    {
      try
      {
        GMapImgCutApp.image = ImageIO.read(chooser.getSelectedFile());
        if (GMapImgCutApp.image == null)
        {
          Utils.showErrorDialogBox(this, "Error Loading Image", "The file " + chooser.getSelectedFile().getName() + " could not be loaded for some reason. Please ensure" + " that this file type can be handled by ImageIO and" + " install Java Advanced Imaging or any required image" + " reader extensions. See the readme file for more" + " information.", null);
          return;
        }
        GMapImgCutApp.filename = chooser.getSelectedFile().getName();
        GMapImgCutApp.createDir = chooser.getSelectedFile().getParent();
        GMapImgCutApp.maxZoomDepth = ImageTileCutterThread.calculateMaxZoomDepth(GMapImgCutApp.image.getWidth(), GMapImgCutApp.image.getHeight());
        jSlider1.setValue(GMapImgCutApp.maxZoomDepth);
        centrePanel.setImage(GMapImgCutApp.image);
        statusBar.setText(chooser.getSelectedFile().getName() + " " + GMapImgCutApp.image.getWidth() + " x " + GMapImgCutApp.image.getHeight() + " pixels");
      }
      catch (IOException ioe)
      {
      }
      jLabel2.setEnabled(true);
      createMenuItem.setEnabled(true);
      jSlider1.setEnabled(true);
      createButton.setEnabled(true);
      jLabel1.setEnabled(true);
      tileCountLabel.setEnabled(true);
      centrePanel.repaint();
    }
  }

    public void jSlider1_stateChanged(ChangeEvent e)
    {
        int count = ImageTileCutterThread.calculateTotalTileCount( jSlider1.getValue() );

        tileCountLabel.setText( Integer.toString( count ) );
    }

    public void jMenuItem2_actionPerformed(ActionEvent e)
    {
        createButtonClick();
    }

    public void jButton1_actionPerformed(ActionEvent e)
    {
        createButtonClick();
    }


    private void createButtonClick()
    {
        int depth = jSlider1.getValue();
        CreateDialog cd = new CreateDialog( (Frame) this, "Create Image Tiles", true );

        cd.setCreateDir( GMapImgCutApp.createDir );
        cd.setDepth( depth );
        cd.setHighQuality( highQualityMenuItem.isSelected() );
        cd.show();
    }

    public void centrePanel_mouseMoved(MouseEvent e)
    {
        Rectangle rect = centrePanel.getSquareImageRectangle();

        if( rect != null )
        {
            String text = new StringBuilder().append( GMapImgCutApp.filename ).append( " " ).append( GMapImgCutApp.image.getWidth() ).append( " x " ).append( GMapImgCutApp.image.getHeight() ).append( " pixels" ).toString();
            int x = e.getX() - rect.x;
            int y = e.getY() - rect.y;

            if( x >= 0 && x <= rect.width && y >= 0 && y <= rect.height )
            {
                double mx = ((double) x / (double) rect.width - 0.5) * 360.0;
                double my = (0.5 - (double) y / (double) rect.height) * 360.0;
                Point2D.Double lonlat = Utils.mercatorXYToLonLat( mx, my );

                text = new StringBuilder().append( text ).append( ", GLatLng(" ).append( df.format( lonlat.y ) ).append( "," ).append( df.format( lonlat.x ) ).append( ")" ).toString();
            }
            statusBar.setText( text );
        }
    }


    public void centrePanel_mouseClicked(MouseEvent e)
    {
        Rectangle rect = centrePanel.getSquareImageRectangle();
        int x = e.getX() - rect.x;
        int y = e.getY() - rect.y;

        if( x >= 0 && x <= rect.width && y >= 0 && y <= rect.height )
        {
            double mx = ((double) x / (double) rect.width - 0.5) * 360.0;
            double my = (0.5 - (double) y / (double) rect.height) * 360.0;
            Point2D.Double lonlat = Utils.mercatorXYToLonLat( mx, my );
            String text = new StringBuilder().append( "GLatLng(" ).append( df.format( lonlat.y ) ).append( "," ).append( df.format( lonlat.x ) ).append( ")" ).toString();
            TextTransfer textTransfer = new TextTransfer();

            textTransfer.setClipboardContents( text );
        }
    }

    static String access$000(MainFrame x0)
    {
        return x0.imageFileExts;
    }

    static String access$100(MainFrame x0)
    {
        return x0.imageFilesDescription;
    }
	
	public class ImageFileFilter extends FileFilter
    {

        public boolean accept(File f)
        {
            String ext = Utils.getFileExtension(f.getName());
            if(ext == null)
            {
                return f.isDirectory();
            } else
            {
                ext = ext.toLowerCase();
                return ((f.isDirectory()) || (MainFrame.access$000(this.this$0).indexOf(ext) >= 0));
            }
        }

        public String getDescription()
        {
            return "Image Files (" + MainFrame.access$100(this.this$0) + ")";
        }

        final MainFrame this$0;

        public ImageFileFilter()
        {
            super();
			this$0 = MainFrame.this;
        }
    }
}