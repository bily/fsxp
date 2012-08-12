package com.zbluesoftware.fsxp.engine;

import com.zbluesoftware.fsxp.event.MenuActionListener;
import com.zbluesoftware.fsxp.menu.MenuAction;
import com.zbluesoftware.fsxp.menu.MenuFactory;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.SAXException;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

class RecentFileEngine$1 extends Thread {

    RecentFileEngine$1(RecentFileEngine recentfileengine1)
    {
        this$0 = recentfileengine1;
    }

    RecentFileEngine this$0;     // final access specifier removed

    public void run()
    {
        try
        {
            Object os = System.getProperty( "os.name" );
            File file;
            Object chkDir;
            DocumentBuilder builder;
            Object xmlDoc;
            Object domSource;
            Object streamResult;
            TransformerFactory tf;
            Transformer serializer;

            if( ((String) os).equals( "Windows Vista" ) )
            {
                chkDir = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner" ).toString() );
                ((File) chkDir).mkdirs();
                file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\recentFiles.xml" ).toString() );
            }
            else if( ((String) os).equals( "Windows XP" ) )
            {
                chkDir = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\Application Data\\zBlueSoftware\\FSXPlanner" ).toString() );
                ((File) chkDir).mkdirs();
                file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\Application Data\\zBlueSoftware\\FSXPlanner\\recentFiles.xml" ).toString() );
            }
            else
                file = new File( new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "conf" ).append( File.separator ).append( "recentFiles.xml" ).toString() );
            chkDir = DocumentBuilderFactory.newInstance();
            builder = ((DocumentBuilderFactory) chkDir).newDocumentBuilder();
            xmlDoc = builder.newDocument();
            RecentFileEngine.access$000( this$0, (Document) xmlDoc );
            domSource = new DOMSource( (Node) xmlDoc );
            streamResult = new StreamResult( file );
            tf = TransformerFactory.newInstance();
            serializer = tf.newTransformer();
            serializer.setOutputProperty( "encoding", "ISO-8859-1" );
            serializer.setOutputProperty( "doctype-system", "recent-files.dtd" );
            serializer.setOutputProperty( "indent", "yes" );
            serializer.setOutputProperty( "{http://xml.apache.org/xslt}indent-amount", "4" );
            serializer.transform( (Source) domSource, (Result) streamResult );
        }
        catch( ParserConfigurationException parserconfigurationexception1 )
        {
            LogEngine.getInstance().log( (Throwable) parserconfigurationexception1 );
        }
        catch( TransformerException transformerexception1 )
        {
            LogEngine.getInstance().log( (Throwable) transformerexception1 );
        }
        catch( NullPointerException nullpointerexception1 )
        {
            LogEngine.getInstance().log( nullpointerexception1 );
        }
    }
}


public class RecentFileEngine implements PropertyChangeListener {

    private RecentFileEngine()
    {
        readRecentFiles();
    }

    private MenuActionListener menuActionListener;
    private static RecentFileEngine engine = null;
    private ArrayList recentFiles = new ArrayList();

    public void setMenuActionListener(MenuActionListener menuActionListener)
    {
        this.menuActionListener = menuActionListener;
        updateRecentFileMenu();
    }

    public static RecentFileEngine getInstance()
    {
        if( engine == null )
            engine = new RecentFileEngine();
        return engine;
    }

    public void newFileOpened(File file, ArrayList identAL)
    {
        String fullName = file.getAbsolutePath();
        int i;

        for( i = 0; i < identAL.size(); ++i )
        {
            String ident = (String) ((HashMap) identAL.get( i )).get( "ident" );
            int j = recentFiles.size() - 1;
            Object obj;

            while( j >= 0 )
            {
                if( ((String) ((HashMap) recentFiles.get( j )).get( "file" )).equals( fullName ) && ((String) ((HashMap) recentFiles.get( j )).get( "ident" )).equals( ident ) )
                {
                    recentFiles.remove( j );
                    break;
                }
                else
                    --j;
            }
            obj = new HashMap();
            ((HashMap) obj).put( "file", fullName );
            ((HashMap) obj).put( "name", file.getName() );
            ((HashMap) obj).put( "ident", ident );
            recentFiles.add( 0, obj );
            while( recentFiles.size() > 10 )
                recentFiles.remove( recentFiles.size() - 1 );
        }
        updateRecentFileMenu();
    }

    public void updateRecentFileMenu()
    {
        JMenu recentMenu = MenuFactory.getInstance().getMenu( new Integer( 5 ) );

        recentMenu.removeAll();
        if( recentFiles.size() == 0 )
        {
            JMenuItem menuItem = new JMenuItem( "No Recent Files" );

            menuItem.setFont( Utilities.MENU_FONT );
            menuItem.setForeground( Color.black );
            menuItem.setEnabled( false );
            recentMenu.add( menuItem );
        }
        else
        {
            int i;

            for( i = 0; i < recentFiles.size(); ++i )
            {
                Object hashMap = (HashMap) recentFiles.get( i );
                String fileName = (String) ((HashMap) hashMap).get( "name" );
                String ident = (String) ((HashMap) hashMap).get( "ident" );
                Object menuAction = new MenuAction( new StringBuilder().append( fileName ).append( " [" ).append( ident ).append( "]" ).toString(), null );
                JMenuItem menuItem;

                ((MenuAction) menuAction).putValue( "ActionCommandKey", "5" );
                ((MenuAction) menuAction).putValue( "LongDescription", hashMap );
                ((MenuAction) menuAction).addMenuListener( menuActionListener );
                menuItem = recentMenu.add( (Action) menuAction );
                menuItem.setFont( Utilities.MENU_FONT );
                menuItem.setForeground( Color.black );
            }
            saveRecentFiles();
        }
    }

    private void readRecentFiles()
    {
        String os = System.getProperty( "os.name" );
        File file;

        if( os.equals( "Windows Vista" ) )
            file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\AppData\\Local\\VirtualStore\\Program Files\\zBlueSoftware\\FSXPlanner\\recentFiles.xml" ).toString() );
        else if( os.equals( "Windows XP" ) )
            file = new File( new StringBuilder().append( System.getProperty( "user.home" ) ).append( "\\Application Data\\zBlueSoftware\\FSXPlanner\\recentFiles.xml" ).toString() );
        else
            file = new File( new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "conf" ).append( File.separator ).append( "recentFiles.xml" ).toString() );

        if( file.exists() )
        {
            try
            {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder;
                Document xmlDoc;
                NodeList plots;
                int i;
                factory.setValidating( true );
                builder = factory.newDocumentBuilder();
                builder.setEntityResolver( DTDResolver.getInstance() );
                xmlDoc = builder.parse( file );
                plots = xmlDoc.getChildNodes();
                for( i = 0; i < plots.getLength(); ++i )
                {
                    if( plots.item( i ).getNodeType() == 1 )
                        parseFiles( (Element) plots.item( i ) );
                }
            }
			catch ( java.net.MalformedURLException mue1 ) {
				LogEngine.getInstance().log( mue1 );
			}
            catch( IOException ioexception1 )
            {
                LogEngine.getInstance().log( ioexception1 );
            }
			catch( SAXException saxexception1 )
            {
                LogEngine.getInstance().log( saxexception1 );
            }
            catch( ParserConfigurationException parserconfigurationexception1 )
            {
                LogEngine.getInstance().log( parserconfigurationexception1 );
            }
        }
    }

    private void parseFiles(Element recentFilesElement)
    {
        NodeList nodeList = recentFilesElement.getElementsByTagName( "recent-file" );
        int i;

        for( i = 0; i < nodeList.getLength(); ++i )
        {
            Element recentFileElement = (Element) nodeList.item( i );
            String file = recentFileElement.getAttribute( "file" );
            String name = recentFileElement.getAttribute( "name" );
            String ident = recentFileElement.getAttribute( "ident" );
            File testFile = new File( file );

            if( testFile.exists() )
            {
                Object hashMap = new HashMap();

                ((HashMap) hashMap).put( "file", file );
                ((HashMap) hashMap).put( "name", name );
                ((HashMap) hashMap).put( "ident", ident );
                recentFiles.add( hashMap );
            }
        }
    }

    private void saveRecentFiles()
    {
        Object thread = new RecentFileEngine$1( this );

        ((Thread) thread).setPriority( Thread.currentThread().getPriority() - 1 );
        ((Thread) thread).start();
    }

    private void writeRecentFiles(Document xmlDoc)
    {
        Object recentFilesElement = xmlDoc.createElement( "recent-files" );
        int i;

        xmlDoc.appendChild( (Node) recentFilesElement );
        for( i = 0; i < recentFiles.size(); ++i )
        {
            Object recentFileElement = xmlDoc.createElement( "recent-file" );

            ((Element) recentFileElement).setAttribute( "file", (String) ((HashMap) recentFiles.get( i )).get( "file" ) );
            ((Element) recentFileElement).setAttribute( "name", (String) ((HashMap) recentFiles.get( i )).get( "name" ) );
            ((Element) recentFileElement).setAttribute( "ident", (String) ((HashMap) recentFiles.get( i )).get( "ident" ) );
            ((Element) recentFilesElement).appendChild( (Node) recentFileElement );
        }
    }

    private String getNodeValue(Element element)
    {
        NodeList children = element.getChildNodes();
        int j = 0;

        while( j < children.getLength() )
        {
            Node child = children.item( j );

            if( child.getNodeType() == 3 )
                return child.getNodeValue();
            else
                ++j;
        }
        return "";
    }

    public void propertyChange(PropertyChangeEvent event)
    {
    }

    static void access$000(RecentFileEngine x0, Document x1)
    {
        x0.writeRecentFiles( x1 );
    }
}