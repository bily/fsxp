package com.zbluesoftware.fsxp.simconnect;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import flightsim.simconnect.SimConnect;
import flightsim.simconnect.SimConnectDataType;
import flightsim.simconnect.SimObjectType;
import flightsim.simconnect.recv.DispatcherTask;
import flightsim.simconnect.recv.EventHandler;
import flightsim.simconnect.recv.OpenHandler;
import flightsim.simconnect.recv.QuitHandler;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvOpen;
import flightsim.simconnect.recv.RecvQuit;
import flightsim.simconnect.recv.RecvSimObjectDataByType;
import flightsim.simconnect.recv.SimObjectDataTypeHandler;
import flightsim.simconnect.wrappers.DataWrapper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zbluesoftware.fsxp.engine.LogEngine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

class FSXConnection$1ProcessOutput implements Runnable {

    public FSXConnection$1ProcessOutput(FSXConnection fsxconnection1, ArrayList arrayList, InputStream is)
    {
        this$0 = fsxconnection1;
        this.is = is;
        this.arrayList = arrayList;
    }

    private InputStream is;
    private ArrayList arrayList;
    FSXConnection this$0;     // final access specifier removed

    public void run()
    {
        BufferedReader br = null;

        try
        {
            Object line;

            br = new BufferedReader( (Reader) new InputStreamReader( is ) );
            line = null;
            while( (line = br.readLine()) != null )
                arrayList.add( line );
        }
        catch( IOException ioexception1 )
        {
            try
            {
                LogEngine.getInstance().log( 0, ioexception1 );
            }
            finally
            {
                    try
                    {
                        if( br != null )
                            br.close();
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
                        if( br != null )
                            br.close();
                    }
                    catch( IOException ignored )
                    {
                    }
        }
    }
}


public class FSXConnection implements EventHandler, OpenHandler, QuitHandler, SimObjectDataTypeHandler {
public enum DATA_REQUEST_ID { REQUEST_1 };
public enum DATA_DEFINE_ID { DEFINITION_1, DEFINITION_2 };
public enum EVENT_ID { EVENT_SIM_START };

    private FSXConnection()
    {
        readPreferences();
    }

    private LatLonPoint planeLatLon;
    private double planeAlt;
    private double planeHeading;
    private static FSXConnection fsxConnection = null;
    private Vector listeners = new Vector();
    private String planeMake = "Cessna 172";
    private String planeType = "Airplane";
    private float planeLength = 26.0F;
    private float planeWidth = 36.0F;
    private SimConnect simConnect = null;
    private boolean connectedToFSX = false;
    private boolean showPlane = false;
    private boolean fsxAutoFollow = false;
    private boolean cursorAutoFollow = false;
    private boolean localConnection = true;
    private boolean specifyPort = false;
    private int ip1 = 127;
    private int ip2 = 0;
    private int ip3 = 0;
    private int ip4 = 1;
    private int port = 48447;

    public static FSXConnection getInstance()
    {
        if( fsxConnection == null )
            fsxConnection = new FSXConnection();
        return fsxConnection;
    }

    public int getIP1()
    {
        return ip1;
    }

    public void setIP1(int ip1)
    {
        this.ip1 = ip1;
    }

    public int getIP2()
    {
        return ip2;
    }

    public void setIP2(int ip2)
    {
        this.ip2 = ip2;
    }

    public int getIP3()
    {
        return ip3;
    }

    public void setIP3(int ip3)
    {
        this.ip3 = ip3;
    }

    public int getIP4()
    {
        return ip4;
    }

    public void setIP4(int ip4)
    {
        this.ip4 = ip4;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public boolean getShowPlane()
    {
        return showPlane;
    }

    public void setShowPlane(boolean showPlane)
    {
        this.showPlane = showPlane;
    }

    public boolean getFSXAutoFollow()
    {
        return fsxAutoFollow;
    }

    public void setFSXAutoFollow(boolean fsxAutoFollow)
    {
        this.fsxAutoFollow = fsxAutoFollow;
    }

    public boolean getCursorAutoFollow()
    {
        return cursorAutoFollow;
    }

    public void setCursorAutoFollow(boolean cursorAutoFollow)
    {
        this.cursorAutoFollow = cursorAutoFollow;
    }

    public String getPlaneMake()
    {
        return planeMake;
    }

    public void setPlaneMake(String planeMake)
    {
        this.planeMake = planeMake;
    }

    public String getPlaneType()
    {
        return planeType;
    }

    public void setPlaneType(String planeType)
    {
        this.planeType = planeType;
    }

    public float getPlaneLength()
    {
        return planeLength;
    }

    public void setPlaneLength(float planeLength)
    {
        this.planeLength = planeLength;
    }

    public float getPlaneWidth()
    {
        return planeWidth;
    }

    public void setPlaneWidth(float planeWidth)
    {
        this.planeWidth = planeWidth;
    }

    public boolean isLocalConnection()
    {
        return localConnection;
    }

    public void setLocalConnection(boolean localConnection)
    {
        this.localConnection = localConnection;
    }

    public boolean getSpecifyPort()
    {
        return specifyPort;
    }

    public void setSpecifyPort(boolean specifyPort)
    {
        this.specifyPort = specifyPort;
    }

    public boolean connectToFSX()
    {
        String ipAddress = new StringBuilder().append( ip1 ).append( "." ).append( ip2 ).append( "." ).append( ip3 ).append( "." ).append( ip4 ).toString();

        return connectToFSX( ipAddress, port, true );
    }

    public boolean connectToFSX(String ipAddress, int port, boolean displayError)
    {
        if( !connectedToFSX )
        {
            try
            {
                Object dispatcherTask;

                simConnect = new SimConnect( "FSX Planner", ipAddress, port );
                simConnect.addToDataDefinition( DATA_DEFINE_ID.DEFINITION_1, "Title", null, SimConnectDataType.STRING256 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_1, "Plane Latitude", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_1, "Plane Longitude", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_1, "Plane Altitude", "feet", SimConnectDataType.FLOAT64 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_1, "Plane Heading Degrees True", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.subscribeToSystemEvent( 1, "1sec" );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_2, "Plane Latitude", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_2, "Plane Longitude", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.addToDataDefinition(DATA_DEFINE_ID.DEFINITION_2, "Plane Heading Degrees True", "degrees", SimConnectDataType.FLOAT64 );
                simConnect.subscribeToSystemEvent(EVENT_ID.EVENT_SIM_START, "SimStart" );
                dispatcherTask = new DispatcherTask( simConnect );
                ((DispatcherTask) dispatcherTask).addOpenHandler( this );
                ((DispatcherTask) dispatcherTask).addQuitHandler( this );
                ((DispatcherTask) dispatcherTask).addEventHandler( this );
                ((DispatcherTask) dispatcherTask).addSimObjectDataTypeHandler( this );
                ((DispatcherTask) dispatcherTask).createThread().start();
            }
            catch( IOException ioexception1 )
            {
                if( displayError )
                    LogEngine.getInstance().log( ioexception1 );
                return false;
            }
        }
        return true;
    }

    public boolean disconnectFromFSX()
    {
        if( simConnect == null || simConnect.isClosed() )
            return false;
        try
        {
            simConnect.close();
            setConnectedToFSX( false );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
            return false;
        }
        return true;
    }

    public boolean isConnectedToFSX()
    {
        return connectedToFSX;
    }

    private void setConnectedToFSX(boolean connectedToFSX)
    {
        if( this.connectedToFSX != connectedToFSX )
            firePropertyChange( "connectedToFSX", new Boolean( (!connectedToFSX) ? true : false ), new Boolean( connectedToFSX ) );
        this.connectedToFSX = connectedToFSX;
    }

    public LatLonPoint getPlaneLatLon()
    {
        return planeLatLon;
    }

    public void setPlaneLatLon(LatLonPoint planeLatLon)
    {
        this.planeLatLon = planeLatLon;
    }

    public double getPlaneHeading()
    {
        return planeHeading;
    }

    public double getPlaneAlt()
    {
        return planeAlt;
    }

    public void movePlane(LatLonPoint latLon, float heading)
    {
        DataWrapper dataWrapper = new DataWrapper( 24 );

        dataWrapper.putFloat64( latLon.getLat() );
        dataWrapper.putFloat64( latLon.getLon() );
        dataWrapper.putFloat64( (double) heading );
        try
        {
            simConnect.setDataOnSimObject(DATA_DEFINE_ID.DEFINITION_2, 0, false, 1, dataWrapper );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
    }

    public void handleOpen(SimConnect sender, RecvOpen e)
    {
        setConnectedToFSX( true );
    }

    public void handleQuit(SimConnect sender, RecvQuit event)
    {
        setConnectedToFSX( false );
    }

    public void handleEvent(SimConnect sender, RecvEvent e)
    {
        try
        {
            sender.requestDataOnSimObjectType(DATA_REQUEST_ID.REQUEST_1,DATA_DEFINE_ID.DEFINITION_1, 0, SimObjectType.USER );
        }
        catch( IOException ioe )
        {
            LogEngine.getInstance().log( ioe );
        }
    }

    public void handleSimObjectType(SimConnect sender, RecvSimObjectDataByType event)
    {
        if( event.getRequestID() == DATA_REQUEST_ID.REQUEST_1.ordinal() )
        {
            int objectID = event.getObjectID();
            String title = event.getDataString256();
            double lat = event.getDataFloat64();
            double lon = event.getDataFloat64();
            double alt = event.getDataFloat64();
            double heading = event.getDataFloat64();

            planeLatLon = new LatLonPoint( lat, lon );
            planeAlt = alt;
            planeHeading = heading;
        }
    }

    public ArrayList runNetstat()
    {
        ArrayList netstatAL = new ArrayList();
        ArrayList portAL = new ArrayList();
        Object obj;
        int i;

        try
        {
            Object runtime = Runtime.getRuntime();
            Process netstat = ((Runtime) runtime).exec( "netstat -b" );
            Thread processOutput = new Thread( (Runnable) new FSXConnection$1ProcessOutput( this, netstatAL, netstat.getInputStream() ), "NetstatOutput" );

            processOutput.setPriority( 5 );
            processOutput.start();
            try
            {
                netstat.waitFor();
            }
            catch( InterruptedException ignored )
            {
            }
        }
        catch( IOException ioexception1 )
        {
            LogEngine.getInstance().log( 0, ioexception1 );
        }
        obj = Pattern.compile( ":\\d+\\s" );
        System.out.println( new StringBuilder().append( "size: " ).append( netstatAL.size() ).toString() );
        for( i = 0; i < netstatAL.size(); ++i )
        {
            String s = (String) netstatAL.get( i );

            if( s.toLowerCase().trim().indexOf( "[fsx.exe]" ) >= 0 && i > 0 )
            {
                String str = ((String) netstatAL.get( i - 1 )).toLowerCase();
                Matcher matcher = ((Pattern) obj).matcher( (CharSequence) str );

                System.out.println( new StringBuilder().append( "lastLine: " ).append( str ).toString() );
                if( matcher.find() )
                {
                    int start = matcher.start() + 1;
                    int end = matcher.end();
                    String port = str.substring( start, end );

                    System.out.println( new StringBuilder().append( "port: " ).append( port ).toString() );
                    portAL.add( new Integer( port.trim() ) );
                }
            }
        }
        return portAL;
    }

    private void readPreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage( getClass() );

        ip1 = userPrefs.getInt( "ip1", ip1 );
        ip2 = userPrefs.getInt( "ip2", ip2 );
        ip3 = userPrefs.getInt( "ip3", ip3 );
        ip4 = userPrefs.getInt( "ip4", ip4 );
        port = userPrefs.getInt( "port", port );
        showPlane = userPrefs.getBoolean( "showPlane", showPlane );
        fsxAutoFollow = userPrefs.getBoolean( "fsxAutoFollow", fsxAutoFollow );
        cursorAutoFollow = userPrefs.getBoolean( "cursorAutoFollow", cursorAutoFollow );
        localConnection = userPrefs.getBoolean( "localConnection", localConnection );
        specifyPort = userPrefs.getBoolean( "specifyPort", specifyPort );
        planeLength = userPrefs.getFloat( "planeLength", planeLength );
        planeWidth = userPrefs.getFloat( "planeWidth", planeWidth );
        planeMake = userPrefs.get( "planeMake", planeMake );
        planeType = userPrefs.get( "planeType", planeType );
    }

    public void writePreferences()
    {
        Preferences userPrefs = Preferences.userNodeForPackage( getClass() );

        userPrefs.putInt( "ip1", ip1 );
        userPrefs.putInt( "ip2", ip2 );
        userPrefs.putInt( "ip3", ip3 );
        userPrefs.putInt( "ip4", ip4 );
        userPrefs.putInt( "port", port );
        userPrefs.putBoolean( "showPlane", showPlane );
        userPrefs.putBoolean( "fsxAutoFollow", fsxAutoFollow );
        userPrefs.putBoolean( "cursorAutoFollow", cursorAutoFollow );
        userPrefs.putBoolean( "localConnection", localConnection );
        userPrefs.putBoolean( "specifyPort", specifyPort );
        userPrefs.putFloat( "planeLength", planeLength );
        userPrefs.putFloat( "planeWidth", planeWidth );
        userPrefs.put( "planeMake", planeMake );
        userPrefs.put( "planeType", planeType );
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