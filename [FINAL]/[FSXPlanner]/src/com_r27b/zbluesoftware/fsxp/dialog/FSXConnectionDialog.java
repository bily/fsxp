package com.zbluesoftware.fsxp.dialog;

import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.util.MaxLengthDocument;
import com.zbluesoftware.fsxp.util.PopupTextField;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
//import javax.swing.javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;

import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

class FSXConnectionDialog$1 extends Thread {

    FSXConnectionDialog$1(FSXConnectionDialog fsxconnectiondialog1, ArrayList arraylist1)
    {
        this$0 = fsxconnectiondialog1;
        val$portAL = arraylist1;
    }

    ArrayList val$portAL;     // final access specifier removed
    FSXConnectionDialog this$0;     // final access specifier removed

    public void run()
    {
        boolean connected;
        int i;

        FSXConnection.getInstance().setIP1( 127 );
        FSXConnection.getInstance().setIP2( 0 );
        FSXConnection.getInstance().setIP3( 0 );
        FSXConnection.getInstance().setIP4( 1 );
        FSXConnection.getInstance().setLocalConnection( true );
        FSXConnection.getInstance().writePreferences();
        FSXConnectionDialog.access$000( this$0 ).setVisible( false );
        FSXConnectionDialog.access$100( this$0 ).setVisible( true );
        FSXConnectionDialog.access$200( this$0 ).setEnabled( false );
        FSXConnectionDialog.access$300( this$0 ).setEnabled( false );
        this$0.pack();
        connected = false;
        i = 0;
        while( i < val$portAL.size() )
        {
            int port = ((Integer) val$portAL.get( i )).intValue();

            FSXConnectionDialog.access$400( this$0 ).setValue( Integer.valueOf( port ) );
            if( FSXConnection.getInstance().connectToFSX( "127.0.0.1", port, false ) )
            {
                connected = true;
                FSXConnection.getInstance().setPort( port );
                FSXConnection.getInstance().writePreferences();
                break;
            }
            else
                ++i;
        }
        FSXConnectionDialog.access$500( this$0, connected );
        if( connected )
        {
            FSXConnectionDialog.access$000( this$0 ).setText( "Connected" );
            FSXConnectionDialog.access$000( this$0 ).setForeground( FSXConnectionDialog.access$600() );
            FSXConnectionDialog.access$200( this$0 ).setText( "Disconnect" );
        }
        FSXConnectionDialog.access$200( this$0 ).setEnabled( true );
        FSXConnectionDialog.access$300( this$0 ).setEnabled( true );
        FSXConnectionDialog.access$100( this$0 ).setVisible( false );
        FSXConnectionDialog.access$000( this$0 ).setVisible( true );
        this$0.pack();
    }
}

class FSXConnectionDialog$2 extends Thread {

    FSXConnectionDialog$2(FSXConnectionDialog fsxconnectiondialog1, StringBuffer stringbuffer1, int i)
    {
        this$0 = fsxconnectiondialog1;
        val$ipBuffer = stringbuffer1;
        val$port = i;
    }

    StringBuffer val$ipBuffer;     // final access specifier removed
    int val$port;     // final access specifier removed
    FSXConnectionDialog this$0;     // final access specifier removed

    public void run()
    {
        FSXConnectionDialog.access$000( this$0 ).setVisible( false );
        FSXConnectionDialog.access$100( this$0 ).setVisible( true );
        FSXConnectionDialog.access$200( this$0 ).setEnabled( false );
        FSXConnectionDialog.access$300( this$0 ).setEnabled( false );
        this$0.pack();
        if( FSXConnection.getInstance().connectToFSX( val$ipBuffer.toString(), val$port, true ) )
        {
            FSXConnectionDialog.access$500( this$0, true );
            FSXConnectionDialog.access$000( this$0 ).setText( "Connected" );
            FSXConnectionDialog.access$000( this$0 ).setForeground( FSXConnectionDialog.access$600() );
            FSXConnectionDialog.access$200( this$0 ).setText( "Disconnect" );
        }
        else
            FSXConnectionDialog.access$500( this$0, false );
        FSXConnectionDialog.access$200( this$0 ).setEnabled( true );
        FSXConnectionDialog.access$300( this$0 ).setEnabled( true );
        FSXConnectionDialog.access$100( this$0 ).setVisible( false );
        FSXConnectionDialog.access$000( this$0 ).setVisible( true );
        this$0.pack();
    }
}


public class FSXConnectionDialog extends JDialog implements ActionListener {

    private FSXConnectionDialog(Frame parent)
    {
        super( parent, "FSX Connection Status", true );
        Object buttonPanel;
        Object mainPanel;
        Object localPanel;
        ButtonGroup buttonGroup;
        Object connectionLabel;

        connectionLabel = new JLabel( "Connection Status:" );
        ((JLabel) connectionLabel).setFont( Utilities.BOLD_LABEL_FONT );
        ((JLabel) connectionLabel).setForeground( Color.black );
        statusLabel = new JLabel( "Disconnected" );
        statusLabel.setFont( Utilities.BOLD_LABEL_FONT );
        statusLabel.setForeground( DISCONNECTED_COLOR );
        progressBar = new JProgressBar();
        progressBar.setIndeterminate( true );
        progressBar.setVisible( false );
        localRB = new JRadioButton( "Local Connection", FSXConnection.getInstance().isLocalConnection() );
        localRB.setFont( Utilities.LABEL_FONT );
        localRB.setForeground( Color.black );
        localRB.addActionListener( this );
        specifyCB = new JCheckBox( "Specify port:", FSXConnection.getInstance().getSpecifyPort() );
        specifyCB.setFont( Utilities.TEXT_FIELD_FONT );
        specifyCB.setForeground( Color.black );
        specifyCB.addActionListener( this );
        specifySpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( FSXConnection.getInstance().getPort(), 0, 65536, 1 ) );
        specifySpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( specifySpinner, "0" ) );
        localPanel = new JPanel( new FlowLayout( 0 ) );
        ((JPanel) localPanel).add( (Component) specifyCB );
        ((JPanel) localPanel).add( (Component) specifySpinner );
        remoteRB = new JRadioButton( "Remote Connection", (!com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().isLocalConnection()) ? true : false );
        remoteRB.setFont( Utilities.LABEL_FONT );
        remoteRB.setForeground( Color.black );
        remoteRB.addActionListener( this );
        buttonGroup = new ButtonGroup();
        buttonGroup.add( (AbstractButton) localRB );
        buttonGroup.add( (AbstractButton) remoteRB );
        ipLabel = new JLabel( "IP Address:" );
        ipLabel.setFont( Utilities.LABEL_FONT );
        ipLabel.setForeground( Color.black );
        ip1TF = new PopupTextField( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP1() ).toString(), 4 );
        ip1TF.setFont( Utilities.TEXT_FIELD_FONT );
        ip1TF.setForeground( Color.black );
        ip1TF.setDocument( (Document) new MaxLengthDocument( 3 ) );
        ip1TF.setText( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP1() ).toString() );
        ip2TF = new PopupTextField( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP2() ).toString(), 4 );
        ip2TF.setFont( Utilities.TEXT_FIELD_FONT );
        ip2TF.setForeground( Color.black );
        ip2TF.setDocument( (Document) new MaxLengthDocument( 3 ) );
        ip2TF.setText( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP2() ).toString() );
        ip3TF = new PopupTextField( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP3() ).toString(), 4 );
        ip3TF.setFont( Utilities.TEXT_FIELD_FONT );
        ip3TF.setForeground( Color.black );
        ip3TF.setDocument( (Document) new MaxLengthDocument( 3 ) );
        ip3TF.setText( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP3() ).toString() );
        ip4TF = new PopupTextField( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP4() ).toString(), 4 );
        ip4TF.setFont( Utilities.TEXT_FIELD_FONT );
        ip4TF.setForeground( Color.black );
        ip4TF.setDocument( (Document) new MaxLengthDocument( 3 ) );
        ip4TF.setText( new StringBuilder().append( "" ).append( FSXConnection.getInstance().getIP4() ).toString() );
        portLabel = new JLabel( "Port:" );
        portLabel.setFont( Utilities.LABEL_FONT );
        portLabel.setForeground( Color.black );
        portSpinner = new JSpinner( (SpinnerModel) new SpinnerNumberModel( FSXConnection.getInstance().getPort(), 0, 65536, 1 ) );
        portSpinner.setEditor( (JComponent) new javax.swing.JSpinner.NumberEditor( portSpinner, "0" ) );
        connectButton = new JButton( "Connect" );
        connectButton.setFont( Utilities.DIALOG_BUTTON_FONT );
        connectButton.setForeground( Color.black );
        connectButton.addActionListener( this );
        mainPanel = new JPanel( new GridBagLayout() );
        ((JPanel) mainPanel).setBorder( (Border) new EmptyBorder( 5, 5, 5, 5 ) );
        Utilities.addComponent( (Container) mainPanel, (Component) connectionLabel, 0, 0, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) statusLabel, 1, 0, 4, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) progressBar, 1, 0, 4, 1, 2, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) localRB, 0, 1, 5, 1, 0, 17, new Insets( 10, 1, 0, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) localPanel, 0, 2, 5, 1, 0, 17, new Insets( 0, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) remoteRB, 0, 3, 5, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) ipLabel, 0, 4, 1, 1, 0, 13, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) ip1TF, 1, 4, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) ip2TF, 2, 4, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) ip3TF, 3, 4, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) ip4TF, 4, 4, 1, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) portLabel, 0, 5, 1, 1, 0, 13, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) portSpinner, 1, 5, 4, 1, 0, 17, new Insets( 1, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) connectButton, 1, 6, 4, 1, 0, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.0, 0.0 );
        Utilities.addComponent( (Container) mainPanel, (Component) new JSeparator(), 0, 7, 5, 1, 2, 17, new Insets( 10, 1, 1, 1 ), 0, 0, 0.5, 0.0 );
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

    private JLabel statusLabel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private PopupTextField ip1TF;
    private PopupTextField ip2TF;
    private PopupTextField ip3TF;
    private PopupTextField ip4TF;
    private JSpinner portSpinner;
    private JProgressBar progressBar;
    private JRadioButton localRB;
    private JRadioButton remoteRB;
    private JSpinner specifySpinner;
    private JCheckBox specifyCB;
    private JButton connectButton;
    private JButton closeButton;
    private static final Color CONNECTED_COLOR = new Color( 0, 102, 0 );
    private static final Color DISCONNECTED_COLOR = Color.red;
    private static FSXConnectionDialog dialog = null;

    public static void showDialog(Frame parent)
    {
        if( dialog == null )
            dialog = new FSXConnectionDialog( parent );
        dialog.updateDialog();
        dialog.setVisible( true );
    }

    private void updateDialog()
    {
        if( FSXConnection.getInstance().isConnectedToFSX() )
        {
            statusLabel.setText( "Connected" );
            statusLabel.setForeground( CONNECTED_COLOR );
            connectButton.setText( "Disconnect" );
        }
        else
        {
            statusLabel.setText( "Disconnected" );
            statusLabel.setForeground( DISCONNECTED_COLOR );
            connectButton.setText( "Connect" );
        }
        updateLocalRemote();
    }

    private void updateLocalRemote()
    {
        boolean enabled = !FSXConnection.getInstance().isLocalConnection();
        specifyCB.setEnabled(!enabled);
        specifySpinner.setEnabled(!enabled && specifyCB.isSelected());
        ipLabel.setEnabled( enabled );
        ip1TF.setEnabled( enabled );
        ip2TF.setEnabled( enabled );
        ip3TF.setEnabled( enabled );
        ip4TF.setEnabled( enabled );
        portLabel.setEnabled( enabled );
        portSpinner.setEnabled( enabled );
    }

    private void connectToFSX()
    {
        if( connectButton.getText().equals( "Connect" ) )
        {
            int ip1 = 127;
            int ip2 = 0;
            int ip3 = 0;
            int ip4 = 1;

            if( localRB.isSelected() )
            {
                ArrayList portAL = FSXConnection.getInstance().runNetstat();

                if( specifyCB.isSelected() )
                {
                    portAL.clear();
                    portAL.add( (Integer) specifySpinner.getValue() );
                }
                if( portAL.size() == 0 )
                {
                    JOptionPane.showMessageDialog( (Component) this, "No open SimConnect ports were found.\n\nPlease make sure that FSX is running.", "No Port Found Error...", 0 );
                    return;
                }
                else
                {
                    Object thread = new FSXConnectionDialog$1( this, portAL );

                    ((Thread) thread).setPriority( 4 );
                    ((Thread) thread).start();
                }
            }
            else
            {
                Object thread;
                Object obj;
                int i;

                try
                {
                    ip1 = Integer.parseInt( ip1TF.getText() );
                }
                catch( NumberFormatException numberformatexception1 )
                {
                    ip1 = 127;
                }
                try
                {
                    ip2 = Integer.parseInt( ip2TF.getText() );
                }
                catch( NumberFormatException numberformatexception2 )
                {
                    ip2 = 0;
                }
                try
                {
                    ip3 = Integer.parseInt( ip3TF.getText() );
                }
                catch( NumberFormatException numberformatexception3 )
                {
                    ip3 = 0;
                }
                try
                {
                    ip4 = Integer.parseInt( ip4TF.getText() );
                }
                catch( NumberFormatException numberformatexception4 )
                {
                    ip4 = 1;
                }
                ip1TF.setText( new StringBuilder().append( "" ).append( ip1 ).toString() );
                ip2TF.setText( new StringBuilder().append( "" ).append( ip2 ).toString() );
                ip3TF.setText( new StringBuilder().append( "" ).append( ip3 ).toString() );
                ip4TF.setText( new StringBuilder().append( "" ).append( ip4 ).toString() );
                obj = new StringBuffer();
                ((StringBuffer) obj).append( ip1 ).append( "." );
                ((StringBuffer) obj).append( ip2 ).append( "." );
                ((StringBuffer) obj).append( ip3 ).append( "." );
                ((StringBuffer) obj).append( ip4 );
                i = ((Integer) portSpinner.getValue()).intValue();
                FSXConnection.getInstance().setIP1( ip1 );
                FSXConnection.getInstance().setIP2( ip2 );
                FSXConnection.getInstance().setIP3( ip3 );
                FSXConnection.getInstance().setIP4( ip4 );
                FSXConnection.getInstance().setPort( i );
                FSXConnection.getInstance().writePreferences();
                thread = new FSXConnectionDialog$2( this, (StringBuffer) obj, i );
                ((Thread) thread).setPriority( 4 );
                ((Thread) thread).start();
            }
        }
        else if( FSXConnection.getInstance().disconnectFromFSX() )
        {
            JOptionPane.showMessageDialog( (Component) this, "Successfully disconnected from FSX.", "Disconnected", 1 );
            statusLabel.setText( "Disconnetced" );
            statusLabel.setForeground( DISCONNECTED_COLOR );
            connectButton.setText( "Connect" );
        }
        else
            JOptionPane.showMessageDialog( (Component) this, "Unable to disconnect from FSX", "Disocnnection Error...", 0 );
    }

    private void displayConnectionMessage(boolean connected)
    {
        if( connected )
            JOptionPane.showMessageDialog( (Component) this, "Connection to FSX successfully established.", "Connected", 1 );
        else
            JOptionPane.showMessageDialog( (Component) this, "Unable to connect to FSX", "Connection Error...", 0 );
    }

    public void actionPerformed(ActionEvent event)
    {
        if( event.getSource() == connectButton )
            connectToFSX();
        else if( event.getSource() == closeButton )
            setVisible( false );
        else if( event.getSource() == localRB )
        {
            FSXConnection.getInstance().setLocalConnection( localRB.isSelected() );
            FSXConnection.getInstance().writePreferences();
            updateLocalRemote();
        }
        else if( event.getSource() == remoteRB )
        {
            FSXConnection.getInstance().setLocalConnection( localRB.isSelected() );
            FSXConnection.getInstance().writePreferences();
            updateLocalRemote();
        }
        else if( event.getSource() == specifyCB )
        {
            FSXConnection.getInstance().setSpecifyPort( specifyCB.isSelected() );
            FSXConnection.getInstance().writePreferences();
            updateLocalRemote();
        }
    }

    static JLabel access$000(FSXConnectionDialog x0)
    {
        return x0.statusLabel;
    }

    static JProgressBar access$100(FSXConnectionDialog x0)
    {
        return x0.progressBar;
    }

    static JButton access$200(FSXConnectionDialog x0)
    {
        return x0.connectButton;
    }

    static JButton access$300(FSXConnectionDialog x0)
    {
        return x0.closeButton;
    }

    static JSpinner access$400(FSXConnectionDialog x0)
    {
        return x0.specifySpinner;
    }

    static void access$500(FSXConnectionDialog x0, boolean x1)
    {
        x0.displayConnectionMessage( x1 );
    }

    static Color access$600()
    {
        return CONNECTED_COLOR;
    }
}