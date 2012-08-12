package collada2x.gui;

import collada.COLLADA;
import collada2x.Collada2X;
import collada2x.XFrame;
import collada2x.XMaterial;
import collada2x.XMesh;
import collada2x.XOutput;
import com.sun.imageio.plugins.bmp.BMPImageWriter;
import com.sun.xml.bind.IDResolver;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.lc0277lib.GUID;
import org.lc0277lib.gui.ExtensionFileFilter;
import org.lc0277lib.gui.JTextAreaStreamAdapter;
import org.lc0277lib.gui.NameFileFilter;
import org.lc0277lib.gui.ProgressDialogInputTask;
import org.lc0277lib.gui.ProgressDialogTask;


class Collada2XUI$1 extends ProgressDialogTask {
    Collada2XUI$1(Collada2XUI collada2xui1, Frame x0)
    {
        super( x0 );
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public boolean doWork()
    {
        try
        {
            Collada2XUI.access$002( this$0, JAXBContext.newInstance( "collada" ) );
        }
        catch( JAXBException e )
        {
            setError( "Error", null, (Throwable) e );
            return false;
        }
        return true;
    }
}

class Collada2XUI$2 implements ActionListener {
    Collada2XUI$2(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        this$0.openFile();
    }
}

class Collada2XUI$3 implements ActionListener {
    Collada2XUI$3(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        this$0.saveFile();
    }
}

class Collada2XUI$4 implements ActionListener {
    Collada2XUI$4(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        this$0.compile();
    }
}

class Collada2XUI$5 implements ActionListener {
    Collada2XUI$5(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        System.exit( 0 );
    }
}

class Collada2XUI$6 implements ActionListener {
    Collada2XUI$6(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        Collada2XUI.access$100( this$0 );
    }
}

class Collada2XUI$7 extends ProgressDialogTask {
    Collada2XUI$7(Collada2XUI collada2xui1, Frame x0, File file1)
    {
        super( x0 );
        this$0 = collada2xui1;
        val$file = file1;
    }
    File val$file;     // final access specifier removed
    Collada2XUI this$0;     // final access specifier removed
    public boolean doWork()
    {
        setMessage( "Extracting KMZ File contents" );
        Collada2XUI.access$200( this$0 ).clear();
        Collada2XUI.access$302( this$0, null );
        try
        {
            Object zf = new ZipFile( val$file );
            Enumeration entries = ((ZipFile) zf).entries();

            while( entries.hasMoreElements() )
            {
                ZipEntry ze = (ZipEntry) entries.nextElement();
                String name = ze.getName();
                InputStream is = null;
                File outFile = null;
                FileOutputStream fos = null;
                byte[] buffer = null;
                int r = 0;
                name = name.replace( '/', File.separatorChar );
                name = name.replace( '\\', File.separatorChar );
                appendDetails( new StringBuilder().append( "Extracting " ).append( name ).toString() );
                is = ((ZipFile) zf).getInputStream( ze );
                outFile = new File( Collada2XUI.access$400( this$0 ), name );
                outFile.getParentFile().mkdirs();
                fos = new FileOutputStream( outFile );
                buffer = new byte[1024];
                while( (r = is.read( buffer )) > 0 )
                    fos.write( buffer, 0, r );
                fos.close();
                is.close();
                if( name.toLowerCase().startsWith( "images" ) )
                    Collada2XUI.access$200( this$0 ).add( outFile );
                else
                {
                    if( !name.toLowerCase().startsWith( "models" ) || !name.toLowerCase().endsWith( ".dae" ) )
                        continue;
                    Collada2XUI.access$302( this$0, outFile );
                }
            }
            ((ZipFile) zf).close();
        }
        catch( Exception exception1 )
        {
            exception1.printStackTrace();
            setError( "Cannot extract KMZ contents", null, exception1 );
            return false;
        }
        return true;
    }
    public void finishedOK()
    {
        super.finishedOK();
        if( Collada2XUI.access$300( this$0 ) == null )
            this$0.showError( "No DAE model file in KMZ" );
        else
            Collada2XUI.access$500( this$0, Collada2XUI.access$300( this$0 ) );
    }
}

class Collada2XUI$8 extends ProgressDialogInputTask
{
	DefaultMutableTreeNode rootNode;
    File val$file;     // final access specifier removed
    FileInputStream val$fis;     // final access specifier removed
    Collada2XUI this$0;     // final access specifier removed
	
	Collada2XUI$8(Collada2XUI collada2xui1, Frame x0, InputStream x1 , File file1, FileInputStream fileinputstream1)
    {
        super( x0, x1 );
        this$0 = collada2xui1;
        val$file = file1;
        val$fis = fileinputstream1;
        rootNode = null;
    }

	public boolean doWork(InputStream is)
    {
		COLLADA col;
		IDResolver idr;
		XFrame rootFrame;
		
        try
        {
      Unmarshaller um = Collada2XUI.access$000( this.this$0 ).createUnmarshaller(  );
      col = ((COLLADA) um.unmarshal( is ) );
      idr = ((IDResolver) um.getProperty( com.sun.xml.bind.IDResolver.class.getName(  )) );
      Collada2XUI.access$602( this.this$0, (new Collada2X( col, idr )) );
      Collada2XUI.access$600( this.this$0 ).setWarnings( Collada2XUI.access$700( this.this$0 ) );
      rootFrame = Collada2XUI.access$600( this.this$0 ).getRootFrame(  );
      this.rootNode = (new javax.swing.tree.DefaultMutableTreeNode( this.val$file.getAbsolutePath(  ) ));
      Collada2XUI.access$800( this.this$0, this.rootNode, rootFrame );
      return true;
        }
        catch(Exception ex)
        {
            setError("Cannot parse document", null, ex);
            ex.printStackTrace();
            return false;
        }
    }

    public void finishedOK()
    {
        super.finishedOK();
        Collada2XUI.access$302(this$0, val$file);
        if(rootNode != null)
        {
            Collada2XUI.access$900(this$0).setRoot(rootNode);
            Collada2XUI.access$1000(this$0).setEnabled(true);
            Collada2XUI.access$1100(this$0).setEnabled(true);
            Collada2XUI.access$1200(this$0).setEnabled(true);
        } else
        {
            Collada2XUI.access$1000(this$0).setEditable(false);
        }
        try
        {
            val$fis.close();
        }
        catch(IOException e) { }
    }

    public void finishedError()
    {
        super.finishedError();
        setMessage("Error occured");
        try
        {
            val$fis.close();
        }
        catch(IOException e) { }
    }
}

class Collada2XUI$9 extends ProgressDialogTask {
    Collada2XUI$9(Collada2XUI collada2xui1, Frame x0, File file1, String s, GUID guid1)
    {
        super( x0 );
        this$0 = collada2xui1;
        val$file = file1;
        val$modelName = s;
        val$guid = guid1;
    }
    File val$file;     // final access specifier removed
    String val$modelName;     // final access specifier removed
    GUID val$guid;     // final access specifier removed
    Collada2XUI this$0;     // final access specifier removed
    public boolean doWork()
    {
        try
        {
            Collada2XUI.access$1300( this$0, val$file, val$modelName, val$guid );
        }
        catch( IOException e )
        {
            setError( "Save failed", null, e );
            return false;
        }
        setMessage( "Saving file successful" );
        return false;
    }
}

class Collada2XUI$10 extends ProgressDialogTask {
    Collada2XUI$10(Collada2XUI collada2xui1, Frame x0, File file1, String s, GUID guid1, File file2)
    {
        super( x0 );
        this$0 = collada2xui1;
        val$expXFile = file1;
        val$exportModelName = s;
        val$exportGUID = guid1;
        val$selFile = file2;
    }
    File val$expXFile;     // final access specifier removed
    String val$exportModelName;     // final access specifier removed
    GUID val$exportGUID;     // final access specifier removed
    File val$selFile;     // final access specifier removed
    Collada2XUI this$0;     // final access specifier removed
    public boolean doWork()
    {
        int fails = 0;
        Object outFileName = null;
        Iterator iterator1 = null;

        setMessage( "Saving file" );
        try
        {
            Collada2XUI.access$1300( this$0, val$expXFile, val$exportModelName, val$exportGUID );
        }
        catch( IOException e )
        {
            appendError( "Save failed", null, e );
            return false;
        }
        appendDetails( "Exported .X file" );
        setMessage( "Exporting textures" );
        setProgressBarParameters( (Collada2XUI.access$200( this$0 ).size() > 0) ? true : false, 0, Collada2XUI.access$200( this$0 ).size() );
        iterator1 = Collada2XUI.access$200( this$0 ).iterator();
        while( iterator1.hasNext() )
        {
            int ip = 0;
            File outFileBMP = null;
            File outFileDDS = null;

            File txt = (File) iterator1.next();
            outFileName = txt.getName();
            ip = ((String) outFileName).lastIndexOf( 46 );
            if( ip != -1 )
                outFileName = ((String) outFileName).substring( 0, ip );
            outFileBMP = new File( val$selFile.getParent(), new StringBuilder().append( (String) outFileName ).append( ".BMP" ).toString() );
            outFileDDS = new File( val$selFile.getParent(), new StringBuilder().append( (String) outFileName ).append( ".DDS" ).toString() );
            try
            {
                Collada2XUI.convertImageToBMP( (File) txt, outFileBMP );
                Collada2XUI.access$1400( this$0, outFileBMP );
                outFileBMP.delete();
                appendDetails( new StringBuilder().append( "Converted texture to " ).append( outFileDDS ).toString() );
                continue;
            }
            catch( IOException e )
            {
                appendError( "Error converting texture", null, e );
                fails = 1;
                continue;
            }
        }
        setMessage( "Compiling to MDL" );
        setProgressBarParameters( true, 0, 0 );
        if( val$selFile.exists() )
            val$selFile.delete();
        try
        {
            Process p = Runtime.getRuntime().exec( new String[] { Collada2XUI.access$1500( this$0 ), val$expXFile.getName() }, null, val$expXFile.getParentFile() );
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int i = 0;
			String line;
			StringWriter outS = new StringWriter();
			while((line = br.readLine()) != null) 
            {
                outS.append( line );
                outS.append( '\n' );
            }
            i = p.waitFor();
            appendDetails( outS.toString() );
            appendDetails( new StringBuilder().append( "XToMDL exited with code " ).append( i ).toString() );
        }
        catch( Exception exception1 )
        {
            appendError( "Cannot run XToMDL", null, exception1 );
            return false;
        }
        if( !val$selFile.exists() || val$selFile.length() == 0L )
            appendError( "MDL File creation failed", "File does not exists", null );
        else if( fails == 0 )
            setMessage( "MDL File creation successful" );
        else
            appendError( "MDL File creation successful (with warnings)", null, null );
        return false;
    }
}

class Collada2XUI$11 implements ActionListener {
    Collada2XUI$11(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        Collada2XUI.access$1600( this$0 );
    }
}

class Collada2XUI$12 implements ActionListener {
    Collada2XUI$12(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        Collada2XUI.access$1700( this$0 );
    }
}

class Collada2XUI$13 implements ActionListener {
    Collada2XUI$13(Collada2XUI collada2xui1)
    {
        this$0 = collada2xui1;
    }
    Collada2XUI this$0;     // final access specifier removed
    public void actionPerformed(ActionEvent e)
    {
        Collada2XUI.access$1800( this$0 ).setVisible( false );
    }
}

class Collada2XUI$14 implements Runnable {
    Collada2XUI$14(String[] as)
    {
        val$args = as;
    }
    String[] val$args;     // final access specifier removed
    public void run()
    {
        Collada2XUI mainUi = new Collada2XUI();

        mainUi.show();
        if( val$args.length > 0 )
            mainUi.openFile( val$args[0] );
    }
}


public class Collada2XUI {
    private Preferences prefs;
    private File tempDir;
    private JAXBContext jaxbContext;
    private File openedFile;
    private ArrayList textureFiles;
    private Collada2X converter;
    private JFrame frame;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem compileItem;
    private JMenuItem exitItem;
    private JTree infoTree;
    private DefaultTreeModel infoTreeModel;
    private JTextArea logArea;
    private JPanel propertyPanel;
    private JTextField propertyNameField;
    private JFormattedTextField propertyGUIDField;
    private JFileChooser openFileChooser;
    private JFileChooser saveFileChooser;
    private JFileChooser compileFileChooser;
    private PrintStream logAreaErrorStream;
    private JDialog configDialog;
    private JTextField xToMdlPathLabel;
    private JTextField imageToolPathLabel;
    private JFileChooser fileChooser;
    private NameFileFilter fileFilter;
/*
    public Collada2XUI() {
        prefs = Preferences.userNodeForPackage(collada2x.Collada2X.class);
        tempDir = new File(System.getProperty("java.io.tmpdir"));
        textureFiles = new ArrayList();
        frame = new JFrame("Collada2X");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(3);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                openFile();
            }
        });
        fileMenu.add(openItem);
        saveItem = new JMenuItem("Save .x");
        saveItem.setEnabled(false);
        saveItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                saveFile();
            }
        });
        fileMenu.add(saveItem);
        compileItem = new JMenuItem("Compile ");
        compileItem.setEnabled(false);
        compileItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                compile();
            }
        });
        fileMenu.add(compileItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        JMenuItem configMenu = new JMenu("Configuration");
        menuBar.add(configMenu);
        JMenuItem configItem = new JMenuItem("Configuration");
        configItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                showConfigDialog();
            }
        });
        configMenu.add(configItem);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(0);
        mainPanel.add(splitPane, "Center");
        frame.setContentPane(mainPanel);
        infoTreeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Document tree"));
        infoTree = new JTree(infoTreeModel);
        infoTree.setEnabled(false);
        splitPane.setLeftComponent(new JScrollPane(infoTree));
        logArea = new JTextArea();
        logArea.setEditable(false);
        initLogAreaStream();
        splitPane.setRightComponent(new JScrollPane(logArea));
    }
*/

    private void initJAXBContext()
    {
        if( jaxbContext == null )
        {
            Object pd = new Collada2XUI$1( this, (Frame) frame );

            ((ProgressDialogTask) pd).setInterruptPossible( false );
            ((ProgressDialogTask) pd).setTitle( "Initializing" );
            ((ProgressDialogTask) pd).setMessage( "Initializing JAXB Parser, please wait" );
            ((ProgressDialogTask) pd).start();
        }
    }

    private void makeTreeNodes(DefaultMutableTreeNode parent, XFrame frame)
    {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode();
        parent.add(node);
        node.setUserObject((new StringBuilder()).append("Frame ").append(frame.getName()).toString());
        XMesh mesh = frame.getMesh();
        if(mesh != null)
        {
            DefaultMutableTreeNode meshNode = new DefaultMutableTreeNode();
            node.add(meshNode);
            meshNode.setUserObject((new StringBuilder()).append("Mesh: ").append(mesh.getVertices().size()).append(" vertices, ").append(mesh.getFaces().size()).append(" faces, ").append(mesh.getNormals().size()).append(" normals, ").append(mesh.getFacesNormals().size()).append(" faces normals").toString());
        }
        if(mesh != null && mesh.getMaterials().size() > 0)
        {
            DefaultMutableTreeNode matsNode = new DefaultMutableTreeNode();
            node.add(matsNode);
            matsNode.setUserObject((new StringBuilder()).append(mesh.getMaterials().size()).append(" materials").toString());
            XMaterial xm;
            DefaultMutableTreeNode matNode;
            for(Iterator i$ = mesh.getMaterials().iterator(); i$.hasNext(); matNode.setUserObject((new StringBuilder()).append(xm.getName()).append(": texture '").append(xm.getTextureFileName()).append("', ").append(" ( r=").append(xm.getFaceColor()[0]).append(",g=").append(xm.getFaceColor()[1]).append(",b=").append(xm.getFaceColor()[2]).append(")").toString()))
            {
                xm = (XMaterial)i$.next();
                matNode = new DefaultMutableTreeNode();
                matsNode.add(matNode);
            }

        }
        XFrame sonFrame;
        for(Iterator i$ = frame.getSubFrames().iterator(); i$.hasNext(); makeTreeNodes(node, sonFrame))
            sonFrame = (XFrame)i$.next();

    }

public Collada2XUI( )
  {
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem configMenu;
    JMenuItem configItem;
    JPanel mainPanel;
    JSplitPane splitPane;

    this.prefs = Preferences.userNodeForPackage( Collada2X.class );
    this.tempDir = (new File( System.getProperty( "java.io.tmpdir" ) ));
    this.textureFiles = (new ArrayList(  ));
    this.frame = (new JFrame( "Collada2X" ));
    this.frame.setSize( 800, 600 );
    this.frame.setDefaultCloseOperation( 3 );
    menuBar = (new JMenuBar(  ));
    this.frame.setJMenuBar( menuBar );
    fileMenu = (new JMenu( "File" ));
    menuBar.add( fileMenu );
    this.openItem = (new JMenuItem( "Open" ));
    this.openItem.addActionListener( (new Collada2XUI$2( this )) );
    fileMenu.add( this.openItem );
    this.saveItem = (new JMenuItem( "Save .x" ));
    this.saveItem.setEnabled( false );
    this.saveItem.addActionListener( (new Collada2XUI$3( this )) );
    fileMenu.add( this.saveItem );
    this.compileItem = (new JMenuItem( "Compile " ));
    this.compileItem.setEnabled( false );
    this.compileItem.addActionListener( (new Collada2XUI$4( this )) );
    fileMenu.add( this.compileItem );
    this.exitItem = (new JMenuItem( "Exit" ));
    this.exitItem.addActionListener( (new Collada2XUI$5( this )) );
    fileMenu.addSeparator(  );
    fileMenu.add( this.exitItem );
    configMenu = (new JMenu( "Configuration" ));
    menuBar.add( configMenu );
    configItem = (new JMenuItem( "Configuration" ));
    configItem.addActionListener( (new Collada2XUI$6( this )) );
    configMenu.add( configItem );
    mainPanel = (new JPanel( (new java.awt.BorderLayout(  )) ));
    splitPane = (new JSplitPane( 0 ));
    mainPanel.add( splitPane, "Center" );
    this.frame.setContentPane( mainPanel );
    this.infoTreeModel = (new javax.swing.tree.DefaultTreeModel( (new DefaultMutableTreeNode( "Document tree" )) ));
    this.infoTree = (new JTree( this.infoTreeModel ));
    this.infoTree.setEnabled( false );
    splitPane.setLeftComponent( (new javax.swing.JScrollPane( this.infoTree )) );
    this.logArea = (new JTextArea(  ));
    this.logArea.setEditable( false );
    this.initLogAreaStream(  );
    splitPane.setRightComponent( (new javax.swing.JScrollPane( this.logArea )) );
  }

    public void show()
    {
        frame.setVisible( true );
        checkFileError( "xtomdl" );
        checkFileError( "imagetool" );
    }

    private void addComponent(JPanel main, Component c, int x, int y, int w, int h, int anchor, int fill, int ipadx, int ipady, double weightx, double weighty, int insettop, int insetleft, int insetbottom, int insetright)
    {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.insets = new Insets( insettop, insetleft, insetbottom, insetright );
        main.add( c, gbc );
    }

    private Object[] fillModelInformations(String name)
    {
        if(propertyPanel == null)
        {
            propertyPanel = new JPanel(new GridBagLayout());
            JLabel lbl = new JLabel("Model name");
            addComponent(propertyPanel, lbl, 0, 0, 1, 1, 11, 0, 0, 0, 0.0D, 0.0D, 20, 20, 5, 5);
            propertyNameField = new JTextField();
            propertyNameField.setColumns(40);
            addComponent(propertyPanel, propertyNameField, 1, 0, 1, 1, 11, 2, 0, 0, 0.0D, 0.0D, 20, 5, 5, 5);
            lbl = new JLabel("Model GUID");
            addComponent(propertyPanel, lbl, 0, 1, 1, 1, 11, 0, 0, 0, 0.0D, 0.0D, 5, 20, 5, 5);
            propertyGUIDField = new JFormattedTextField();
            propertyGUIDField.setColumns(40);
            try
            {
                MaskFormatter guidFmter = new MaskFormatter("{HHHHHHHH-HHHH-HHHH-HHHH-HHHHHHHHHHHH}");
                guidFmter.setPlaceholderCharacter('_');
                propertyGUIDField.setFormatterFactory(new DefaultFormatterFactory(guidFmter));
            }
            catch(ParseException e) { }
            addComponent(propertyPanel, propertyGUIDField, 1, 1, 1, 1, 11, 2, 0, 0, 0.0D, 0.0D, 5, 5, 5, 5);
        }
        propertyNameField.setText(name);
        propertyGUIDField.setValue(GUID.randomGUID().toRegistryString());
        int res = JOptionPane.showConfirmDialog(frame, propertyPanel, "Export properties", 2, 3);
        if(res == 0)
        {
            Object results[] = new Object[2];
            results[0] = propertyNameField.getText();
            results[1] = new GUID(GUID.decodeRegistry(propertyGUIDField.getValue().toString()));
            return results;
        } else
        {
            return null;
        }
    }

    private void initFileChooser()
    {
        if(openFileChooser == null)
        {
            openFileChooser = new JFileChooser();
            openFileChooser.setAcceptAllFileFilterUsed(false);
            openFileChooser.setFileFilter(new ExtensionFileFilter("dae", "Collada DAE file"));
            openFileChooser.setFileFilter(new ExtensionFileFilter("kmz", "Google earth 4.0 KMZ file"));
        }
        if(saveFileChooser == null)
        {
            saveFileChooser = new JFileChooser();
            saveFileChooser.setAcceptAllFileFilterUsed(false);
            saveFileChooser.setFileFilter(new ExtensionFileFilter("x", "DirectX .x definition file"));
        }
        if(compileFileChooser == null)
        {
            compileFileChooser = new JFileChooser();
            compileFileChooser.setAcceptAllFileFilterUsed(false);
            compileFileChooser.setFileFilter(new ExtensionFileFilter("mdl", "MDL file"));
        }
        String lastDir = prefs.get("last_dir", null);
        if(lastDir != null)
            openFileChooser.setCurrentDirectory(new File(lastDir));
        lastDir = prefs.get("last_dir_save", null);
        if(lastDir != null)
        {
            saveFileChooser.setCurrentDirectory(new File(lastDir));
            compileFileChooser.setCurrentDirectory(new File(lastDir));
        }
    }

    public void openFile()
    {
        int ret = 0;

        initFileChooser();
        ret = openFileChooser.showOpenDialog( (Component) frame );
        if( ret == 0 )
        {
            File selFile = openFileChooser.getSelectedFile();

            if( selFile != null )
            {
                prefs.put( "last_dir", selFile.getParent() );
                openFile( selFile );
            }
        }
    }

    public void openFile(String fileName)
    {
        openFile( new File( fileName ) );
    }

    public void openFile(File file)
    {
        String ext = file.getName().toLowerCase();
        int ip = ext.lastIndexOf( 46 );

        if( ip != -1 )
            ext = ext.substring( ip + 1 );
        if( "kmz".equals( ext ) )
            openFileKMZ( file );
        else if( "dae".equals( ext ) )
            openFileCollada( file );
        else
            showError( new StringBuilder().append( "Cannot determinate file type of " ).append( file ).toString() );
    }

    private void openFileKMZ(File file)
    {
        Object pd = new Collada2XUI$7( this, (Frame) frame, file );

        ((ProgressDialogTask) pd).setTitle( "Extracting KMZ contents" );
        ((ProgressDialogTask) pd).setInterruptPossible( false );
        ((ProgressDialogTask) pd).start();
    }

    private void openFileCollada(File file)
    {
        initJAXBContext();
        saveItem.setEnabled( false );
        compileItem.setEnabled( false );
        infoTree.setEditable( false );
        converter = null;
        logArea.setText( "" );
		FileInputStream fis;
        Collada2XUI$8 collada2xui$8_1 = null;
        try
        {
            fis = new FileInputStream( file );
        }
        catch( Exception e )
        {
            showError( new StringBuilder().append( "Cannot open file '" ).append( file ).append( "'" ).toString(), e );
            return;
        }
        collada2xui$8_1 = new Collada2XUI$8( this, (Frame) frame, fis, file, fis );
        collada2xui$8_1.setInterruptPossible( false );
	    collada2xui$8_1.setMessage( "Parsing Collada document and extracting model informations" );
        collada2xui$8_1.start();
    }

    public void saveFile()
    {
        if( converter != null && converter.getRootFrame() != null )
        {
            int ret = 0;

            initFileChooser();
            ret = saveFileChooser.showSaveDialog( (Component) frame );
            if( ret == 0 )
            {
                File selFile = saveFileChooser.getSelectedFile();

                if( selFile != null )
                {
                    String modelName = null;
                    int ip = 0;
                    Object[] properties = null;

                    prefs.put( "last_dir_save", selFile.getParent() );
                    modelName = openedFile.getName();
                    ip = modelName.lastIndexOf( 46 );
                    if( ip != -1 )
                        modelName = modelName.substring( 0, ip );
                    properties = fillModelInformations( modelName );
                    if( properties == null )
                        return;
                    else
                    {
                        String exportModelName = (String) properties[0];
                        GUID exportGUID = (GUID) properties[1];

                        saveFile( selFile, exportModelName, exportGUID );
                    }
                }
            }
        }
    }

    private void saveFile(File file, String modelName, GUID guid)
    {
        Object pd = new Collada2XUI$9( this, (Frame) frame, file, modelName, guid );

        ((ProgressDialogTask) pd).setInterruptPossible( false );
        ((ProgressDialogTask) pd).setMessage( "Saving file" );
        ((ProgressDialogTask) pd).setTitle( "Saving file" );
        ((ProgressDialogTask) pd).start();
    }

    private void saveXFile(File file, String modelName, GUID guid)
        throws IOException
    {
        FileOutputStream fos = null;

        try
        {
            Object pos = null;
            Object xo = null;

            fos = new FileOutputStream( file );
            pos = new PrintStream( fos );
            xo = new XOutput( (PrintStream) pos, modelName );
            ((XOutput) xo).setModelGUID( guid );
            ((XOutput) xo).writeXData( converter.getRootFrame() );
            fos.close();
        }
        catch( IOException ioexception1 )
        {
            try
            {
                fos.close();
            }
            catch( IOException ioexception2 )
            {
            }
            throw ioexception1;
        }
    }

    public void compile()
    {
        if( converter != null && converter.getRootFrame() != null )
        {
            if( checkFileError( "xtomdl" ) )
            {
                if( checkFileError( "imagetool" ) )
                {
                    int ret = 0;

                    initFileChooser();
                    ret = compileFileChooser.showSaveDialog( (Component) frame );
                    if( ret == 0 )
                    {
                        File selFile = compileFileChooser.getSelectedFile();
                        String modelName = null;
                        int ip = 0;
                        Object[] properties = null;

                        if( selFile == null )
                            return;
                        prefs.put( "last_dir_save", selFile.getParent() );
                        modelName = openedFile.getName();
                        ip = modelName.lastIndexOf( 46 );
                        if( ip != -1 )
                            modelName = modelName.substring( 0, ip );
                        properties = fillModelInformations( modelName );
                        if( properties != null )
                        {
                            String exportModelName = (String) properties[0];
                            GUID exportGUID = (GUID) properties[1];
                            String expFileName = selFile.getName();
                            File expXFile = null;
                            Object pd = null;

                            ip = expFileName.lastIndexOf( 46 );
                            if( ip != -1 )
                                expFileName = expFileName.substring( 0, ip );
                            expXFile = new File( selFile.getParent(), new StringBuilder().append( expFileName ).append( ".x" ).toString() );
                            pd = new Collada2XUI$10( this, (Frame) frame, expXFile, exportModelName, exportGUID, selFile );
                            ((ProgressDialogTask) pd).setInterruptPossible( false );
                            ((ProgressDialogTask) pd).setTitle( "Compiling" );
                            ((ProgressDialogTask) pd).start();
                        }
                    }
                }
            }
        }
    }

    void showError(String error)
    {
        JOptionPane.showMessageDialog( (Component) frame, error, "Error", 0 );
    }

    void showError(String error, Throwable cause)
    {
        showError( new StringBuilder().append( error ).append( "\n" ).append( cause.getMessage() ).toString() );
    }

    private void initLogAreaStream()
    {
        JTextAreaStreamAdapter jtsa = new JTextAreaStreamAdapter(logArea);
        logAreaErrorStream = new PrintStream(jtsa);
    }

    void printError(String error)
    {
        logAreaErrorStream.println( error );
    }

    void printErrorStackTrace(Exception e)
    {
        e.printStackTrace( logAreaErrorStream );
    }

    private boolean checkFileNoError(String application)
    {
        String f = prefs.get( application, null );

        if( f != null )
        {
            File ff = new File( f );

            if( ff.exists() )
                return true;
        }
        return false;
    }

    private boolean checkFileError(String application)
    {
        boolean b = checkFileNoError( application );

        if( !b )
            showError( new StringBuilder().append( "Cannot find " ).append( application ).append( " executable. Check configuration" ).toString() );
        return b;
    }

    static void convertImageToBMP(File inFile, File outFile)
        throws IOException
    {
        BufferedImage bi = ImageIO.read(inFile);
        int w = bi.getWidth();
        int h = bi.getHeight();
        int fw;
        for(fw = 1; fw < w; fw *= 2);
        int fh;
        for(fh = 1; fh < h; fh *= 2);
        if(fw - w > w - fw / 2)
            fw /= 2;
        if(fh - h > h - fh / 2)
            fh /= 2;
        BufferedImage nImage = new BufferedImage(fw, fh, 5);
        nImage.createGraphics().drawImage(bi, 0, 0, fw, fh, null);
        Iterator iter = ImageIO.getImageWritersByFormatName("BMP");
        BMPImageWriter biw = null;
        do
        {
            if(!iter.hasNext())
                break;
            ImageWriter iw = (ImageWriter)iter.next();
            if(iw instanceof BMPImageWriter)
                biw = (BMPImageWriter)iw;
        } while(true);
        ImageWriteParam iwp = biw.getDefaultWriteParam();
        iwp.setCompressionMode(2);
        iwp.setCompressionType("BI_RGB");
        FileImageOutputStream fios = new FileImageOutputStream(outFile);
        biw.setOutput(fios);
        IIOImage image = new IIOImage(nImage, null, null);
        biw.write(null, image, iwp);
        fios.close();
        bi = null;
    }

    private void convertImageToDDS(File bmpFile)
        throws IOException
    {
        Process p = Runtime.getRuntime().exec(new String[] {
            getImageToolPath(), "-nogui", "-dds", "-nomip", "-DXT5", "-vflip", bmpFile.getAbsolutePath()
        }, null, bmpFile.getParentFile());
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while((line = br.readLine()) != null) 
            System.out.println(line);
        int code;
        try
        {
            code = p.waitFor();
        }
        catch(InterruptedException e)
        {
            throw new IOException(e.getMessage());
        }
    }

    private String getXToMdlPath()
    {
        return prefs.get( "xtomdl", "xtomdl.exe" );
    }

    private String getImageToolPath()
    {
        return prefs.get( "imagetool", "ImageTool.exe" );
    }

    private void showConfigDialog()
    {
        if( configDialog == null )
        {
            JPanel mainPanel = new JPanel( new GridBagLayout() );
            JPanel pathsPanel = new JPanel( new GridBagLayout() );
            Font f = null;
            JButton jb = null;
            JButton closeButton = null;

            ((JPanel) pathsPanel).setBorder( BorderFactory.createTitledBorder( "Paths" ) );
            addComponent( (JPanel) mainPanel, (Component) pathsPanel, 0, 0, 1, 1, 11, 1, 0, 0, 1.0, 1.0, 5, 5, 5, 5 );
            xToMdlPathLabel = new JTextField( 40 );
            xToMdlPathLabel.setOpaque( false );
            xToMdlPathLabel.setEditable( false );
            imageToolPathLabel = new JTextField( 40 );
            imageToolPathLabel.setOpaque( false );
            imageToolPathLabel.setEditable( false );
            f = xToMdlPathLabel.getFont();
            f = f.deriveFont( 2 );
            xToMdlPathLabel.setFont( f );
            imageToolPathLabel.setFont( f );
            addComponent( (JPanel) pathsPanel, (Component) new JLabel( "XToMDL: " ), 0, 0, 1, 1, 17, 0, 0, 0, 0.0, 0.0, 5, 5, 5, 5 );
            addComponent( (JPanel) pathsPanel, (Component) xToMdlPathLabel, 1, 0, 1, 1, 17, 2, 0, 0, 1.0, 0.0, 5, 5, 5, 5 );
            jb = new JButton( "Browse" );
            ((JButton) jb).addActionListener( (ActionListener) new Collada2XUI$11( this ) );
            addComponent( (JPanel) pathsPanel, (Component) jb, 2, 0, 1, 1, 13, 2, 0, 0, 0.0, 0.0, 5, 5, 5, 5 );
            addComponent( (JPanel) pathsPanel, (Component) new JLabel( "ImageTool: " ), 0, 1, 1, 1, 17, 0, 0, 0, 0.0, 0.0, 5, 5, 5, 5 );
            addComponent( (JPanel) pathsPanel, (Component) imageToolPathLabel, 1, 1, 1, 1, 17, 2, 0, 0, 1.0, 0.0, 5, 5, 5, 5 );
            jb = new JButton( "Browse" );
            ((JButton) jb).addActionListener( (ActionListener) new Collada2XUI$12( this ) );
            addComponent( (JPanel) pathsPanel, (Component) jb, 2, 1, 1, 1, 11, 2, 0, 0, 0.0, 0.0, 5, 5, 5, 5 );
            closeButton = new JButton( "Close" );
            ((JButton) closeButton).addActionListener( (ActionListener) new Collada2XUI$13( this ) );
            addComponent( (JPanel) mainPanel, (Component) closeButton, 0, 1, 1, 1, 10, 0, 0, 0, 0.0, 0.0, 5, 0, 5, 0 );
            configDialog = new JDialog( (Frame) frame, "Configuration" );
            configDialog.setContentPane( mainPanel );
            configDialog.pack();
        }
        xToMdlPathLabel.setText( getXToMdlPath() );
        imageToolPathLabel.setText( getImageToolPath() );
        configDialog.setLocationRelativeTo( (Component) frame );
        configDialog.setVisible( true );
    }

    private String openFile(String exeName, String desc, String current)
    {
        String lastDir = null;
        int ret = 0;
        File selFile = null;

        if( fileChooser == null )
        {
            fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed( false );
            fileFilter = new NameFileFilter( exeName, desc );
            fileChooser.setFileFilter( fileFilter );
        }
        fileFilter.setName( exeName );
        fileFilter.setDescription( desc );
        if( current != null )
            fileChooser.setSelectedFile( new File( current ) );
        lastDir = prefs.get( "lastdir", null );
        if( lastDir != null )
            fileChooser.setCurrentDirectory( new File( lastDir ) );
        ret = fileChooser.showOpenDialog( (Component) frame );
        if( ret != 0 )
            return null;
        selFile = fileChooser.getSelectedFile();
        if( selFile != null )
            prefs.put( "lastdir", selFile.getParent() );
        return selFile.getAbsolutePath();
    }

    private void browseXtoMdlPath()
    {
        String xTMdl = openFile( "xtomdl.exe", "XToMdl executable", getXToMdlPath() );

        if( xTMdl != null )
        {
            prefs.put( "xtomdl", xTMdl );
            xToMdlPathLabel.setText( xTMdl );
        }
    }

    private void browseImageToolPath()
    {
        String imgTool = openFile( "imagetool.exe", "ImageTool executable", getImageToolPath() );

        if( imgTool != null )
        {
            prefs.put( "imagetool", imgTool );
            imageToolPathLabel.setText( imgTool );
        }
    }

    public static void main(String[] args)
        throws InterruptedException, InvocationTargetException
    {
        SwingUtilities.invokeAndWait( (Runnable) new Collada2XUI$14( args ) );
    }

    static JAXBContext access$002(Collada2XUI x0, JAXBContext x1)
    {
        return x0.jaxbContext = x1;
    }

    static void access$100(Collada2XUI x0)
    {
        x0.showConfigDialog();
    }

    static ArrayList access$200(Collada2XUI x0)
    {
        return x0.textureFiles;
    }

    static File access$302(Collada2XUI x0, File x1)
    {
        return x0.openedFile = x1;
    }

    static File access$400(Collada2XUI x0)
    {
        return x0.tempDir;
    }

    static File access$300(Collada2XUI x0)
    {
        return x0.openedFile;
    }

    static void access$500(Collada2XUI x0, File x1)
    {
        x0.openFileCollada( x1 );
    }

    static JAXBContext access$000(Collada2XUI x0)
    {
        return x0.jaxbContext;
    }

    static Collada2X access$602(Collada2XUI x0, Collada2X x1)
    {
        return x0.converter = x1;
    }

    static PrintStream access$700(Collada2XUI x0)
    {
        return x0.logAreaErrorStream;
    }

    static Collada2X access$600(Collada2XUI x0)
    {
        return x0.converter;
    }

    static void access$800(Collada2XUI x0, DefaultMutableTreeNode x1, XFrame x2)
    {
        x0.makeTreeNodes( x1, x2 );
    }

    static DefaultTreeModel access$900(Collada2XUI x0)
    {
        return x0.infoTreeModel;
    }

    static JTree access$1000(Collada2XUI x0)
    {
        return x0.infoTree;
    }

    static JMenuItem access$1100(Collada2XUI x0)
    {
        return x0.saveItem;
    }

    static JMenuItem access$1200(Collada2XUI x0)
    {
        return x0.compileItem;
    }

    static void access$1300(Collada2XUI x0, File x1, String x2, GUID x3)
        throws IOException
    {
        x0.saveXFile( x1, x2, x3 );
    }

    static void access$1400(Collada2XUI x0, File x1)
        throws IOException
    {
        x0.convertImageToDDS( x1 );
    }

    static String access$1500(Collada2XUI x0)
    {
        return x0.getXToMdlPath();
    }

    static void access$1600(Collada2XUI x0)
    {
        x0.browseXtoMdlPath();
    }

    static void access$1700(Collada2XUI x0)
    {
        x0.browseImageToolPath();
    }

    static JDialog access$1800(Collada2XUI x0)
    {
        return x0.configDialog;
    }
}