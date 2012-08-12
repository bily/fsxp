package com.zbluesoftware.fsxp.engine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEngine {

    public static final int ERROR = 0;
    public static final int WARNING = 1;
    public static final int INFO = 2;
    public static final boolean DEBUG = true;
    private static LogEngine engine = null;

    public static LogEngine getInstance()
    {
        if( engine == null )
            engine = new LogEngine();
        return engine;
    }

    public void log(Throwable t)
    {
        log( 0, t );
    }

    public void log(int level, Throwable t)
    {
        t.printStackTrace();
        writeLog( level, t, null );
    }

    public void log(String message)
    {
        writeLog( 2, null, message );
    }

    public void writeLog(int level, Throwable t, String message)
    {
        BufferedWriter writer = null;

        try
        {
            Object errorBuffer = new StringBuffer();
            DateFormat dateFormat;
            String dateText;
            File file1;
            SimpleDateFormat simpledateformat1;

            switch( level )
            {
                case 0:
                    ((StringBuffer) errorBuffer).append( "ERROR: " );
                    break;
                case 1:
                    ((StringBuffer) errorBuffer).append( "WARNING: " );
                    break;
                default:
                    ((StringBuffer) errorBuffer).append( "INFO: " );
                    break;
            }
            dateFormat = DateFormat.getDateTimeInstance();
            ((StringBuffer) errorBuffer).append( "[" ).append( dateFormat.format( new Date() ) ).append( "]\n" );
            if( t != null )
            {
                StackTraceElement[] traces;
                int i;

                ((StringBuffer) errorBuffer).append( t.toString() ).append( "\n" );
                traces = t.getStackTrace();
                for( i = 0; i < traces.length; ++i )
                    ((StringBuffer) errorBuffer).append( "\t" ).append( traces[i].toString() ).append( "\n" );
            }
            if( message != null )
                ((StringBuffer) errorBuffer).append( message );
            file1 = new File( new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "logs" ).toString() );
            file1.mkdirs();
            simpledateformat1 = new SimpleDateFormat( "MMM-dd-yyyy" );
            dateText = simpledateformat1.format( new Date() );
            writer = new BufferedWriter( (Writer) new FileWriter( new File( new StringBuilder().append( System.getProperty( "user.dir" ) ).append( File.separator ).append( "logs" ).append( File.separator ).append( "log-" ).append( dateText ).append( ".txt" ).toString() ), true ) );
            writer.write( ((StringBuffer) errorBuffer).toString() );
            writer.newLine();
            writer.flush();
        }
        catch( IOException ioexception1 )
        {
            try
            {
                ioexception1.printStackTrace();
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
                    catch( IOException ignored2 )
                    {
                    }
        }
    }
}