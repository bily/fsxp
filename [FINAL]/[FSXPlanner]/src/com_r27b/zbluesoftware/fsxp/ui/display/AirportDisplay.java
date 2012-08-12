package com.zbluesoftware.fsxp.ui.display;

import com.zbluesoftware.fsxp.comparator.HeadingSort;
import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.CursorEngine;
import com.zbluesoftware.fsxp.engine.DisplayEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.menu.CursorPopupMenu;
import com.zbluesoftware.fsxp.menu.FSXPMenuBar;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.AlertModel;
import com.zbluesoftware.fsxp.model.ApronEdgeLightModel;
import com.zbluesoftware.fsxp.model.ApronModel;
import com.zbluesoftware.fsxp.model.BackgroundImageModel;
import com.zbluesoftware.fsxp.model.BaseModel;
import com.zbluesoftware.fsxp.model.BlastFenceModel;
import com.zbluesoftware.fsxp.model.BoundaryFenceModel;
import com.zbluesoftware.fsxp.model.DMEModel;
import com.zbluesoftware.fsxp.model.ExclusionModel;
import com.zbluesoftware.fsxp.model.GlideSlopeModel;
import com.zbluesoftware.fsxp.model.HelipadModel;
import com.zbluesoftware.fsxp.model.ILSModel;
import com.zbluesoftware.fsxp.model.JetwayModel;
import com.zbluesoftware.fsxp.model.MarkerModel;
import com.zbluesoftware.fsxp.model.NDBModel;
import com.zbluesoftware.fsxp.model.PlaneModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.SceneryModel;
import com.zbluesoftware.fsxp.model.SelectedItem;
import com.zbluesoftware.fsxp.model.StartModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathDisplayModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathModel;
import com.zbluesoftware.fsxp.model.TaxiwayPointModel;
import com.zbluesoftware.fsxp.model.TaxiwaySignModel;
import com.zbluesoftware.fsxp.model.TowerModel;
import com.zbluesoftware.fsxp.model.TriggerModel;
import com.zbluesoftware.fsxp.model.VORModel;
import com.zbluesoftware.fsxp.model.VertexModel;
import com.zbluesoftware.fsxp.model.WindsockModel;
import com.zbluesoftware.fsxp.simconnect.FSXConnection;
import com.zbluesoftware.fsxp.thread.ILSLineUpdates;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathModel;
import java.awt.Point;
import java.util.ArrayList;

class AirportDisplay$1 extends Thread {

    AirportDisplay$1(AirportDisplay airportdisplay1)
    {
        this$0 = airportdisplay1;
    }

    AirportDisplay this$0;     // final access specifier removed

    public void run()
    {
        int i;
        ArrayList arraylist1;

        while( AirportDisplay.access$000( this$0 ).getStartLat() == 0.0 && AirportDisplay.access$000( this$0 ).getEndLat() == 0.0 )
        {
            try
            {
                Thread.sleep( 100L );
                continue;
            }
            catch( InterruptedException ignored )
            {
                continue;
            }
        }
        arraylist1 = AirportDisplay.access$100( this$0 ).getRunwayAL();
        i = arraylist1.size() - 1;
        while( i >= 0 )
        {
            RunwayModel runwayModel = (RunwayModel) arraylist1.get( i );

            if( runwayModel.containsTaxiway( AirportDisplay.access$000( this$0 ) ) )
            {
                AirportDisplay.access$000( this$0 ).setShouldNotify( false );
                AirportDisplay.access$000( this$0 ).setType( "RUNWAY" );
                AirportDisplay.access$000( this$0 ).setWidth( runwayModel.getWidth() );
                AirportDisplay.access$000( this$0 ).setWidthMeasure( runwayModel.getWidthMeasure() );
                AirportDisplay.access$000( this$0 ).setLeftEdge( "SOLID" );
                AirportDisplay.access$000( this$0 ).setRightEdge( "SOLID" );
                AirportDisplay.access$000( this$0 ).setSurface( runwayModel.getSurface() );
                AirportDisplay.access$000( this$0 ).setNumber( runwayModel.getNumber() );
                if( runwayModel.getPrimaryDesignator().length() == 0 )
                    AirportDisplay.access$000( this$0 ).setDesignator( runwayModel.getDesignator() );
                else
                    AirportDisplay.access$000( this$0 ).setDesignator( runwayModel.getPrimaryDesignator() );
                AirportDisplay.access$000( this$0 ).setShouldNotify( true );
                break;
            }
            else
                --i;
        }
    }
}


public class AirportDisplay extends JComponent implements ComponentListener, MouseListener, MouseMotionListener, MouseWheelListener, PropertyChangeListener {

    public AirportDisplay(AirportModel airportModel)
    {
        this.airportModel = airportModel;
        scale = 0.2000000029802322387695313F;
        airportModel.setScale( scale );
        centerPoint = new LatLonPoint( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon() );
        mouseLatLon = new LatLonPoint( 0.0, 0.0 );
        centerSet = false;
        recreate = true;
        recreateBGImage = true;
        recreateBGAllImage = true;
        drawing = false;
        redrawScroll = true;
        secondsFormat = NumberFormat.getInstance();
        secondsFormat.setMinimumFractionDigits( 0 );
        secondsFormat.setMaximumFractionDigits( 0 );
        displayLights = SettingsEngine.getInstance().getDisplayLights();
        displayFPS = SettingsEngine.getInstance().getDisplayFPS();
        doubleBuffer = SettingsEngine.getInstance().getDoubleBuffer();
        fpsFormat = NumberFormat.getInstance();
        fpsFormat.setMaximumFractionDigits( 1 );
        fpsFormat.setMinimumFractionDigits( 1 );
        bgImagesBelow = SettingsEngine.getInstance().getBGImagesBelow();
        SettingsEngine.getInstance().addPropertyChangeListener( this );
        setCursor( CursorEngine.getInstance().getDefaultCursor() );
        addMouseListener( this );
        addMouseWheelListener( this );
        addMouseMotionListener( this );
        addComponentListener( this );
    }

    private BufferedImage currentFrameBI;
    private BufferedImage lastFrameBI;
    private BufferedImage displayImage;
    private AirportModel airportModel;
    private LatLonPoint centerPoint;
    private LatLonPoint mouseLatLon;
    private NumberFormat secondsFormat;
    private TaxiwayPathModel taxiwayPathModel;
    private ApronModel apronModel;
    private TriggerModel triggerModel;
    private ApronEdgeLightModel apronEdgeLightModel;
    private BoundaryFenceModel boundaryFenceModel;
    private BlastFenceModel blastFenceModel;
    private VertexModel vertexModel;
    private ExclusionModel exclusionModel;
    private Point startPoint;
    private Point endPoint;
    private Point mouseClickPoint;
    private NumberFormat fpsFormat;
    private int oldX;
    private int oldY;
    private float scale;
    private double centerX;
    private double centerY;
    private boolean centerSet;
    private boolean recreate;
    private boolean recreateBGImage;
    private boolean recreateBGAllImage;
    private boolean creatingApron;
    private boolean modifyingApron;
    private boolean creatingTrigger;
    private boolean modifyingTrigger;
    private boolean creatingApronEdgeLight;
    private boolean modifyingApronEdgeLight;
    private boolean creatingFence;
    private boolean modifyingFence;
    private boolean creatingBlastFence;
    private boolean modifyingBlastFence;
    private boolean creatingExclude;
    private boolean modifyingExclude;
    private boolean bgImagesBelow;
    private boolean displayLights;
    private boolean drawing;
    private boolean redrawScroll;
    private boolean displayFPS;
    private boolean doubleBuffer;

    public void setRecreate(boolean recreate)
    {
        this.recreate = recreate;
    }

    public void paint(Graphics g)
    {
        paint( g, null, false );
    }

    public void paint(Graphics g, Shape clip, boolean buffering)
    {
        long startTime = System.currentTimeMillis();
        Graphics2D g2;

        drawing = true;
        if( !centerSet )
        {
            centerX = (double) getSize().width / 2.0;
            centerY = (double) getSize().height / 2.0;
            oldX = getSize().width / 2;
            oldY = getSize().height / 2;
            centerSet = true;
            recreateBGImage = true;
            displayImage = null;
        }
        g2 = (Graphics2D) g;
        if( buffering )
        {
            currentFrameBI = new BufferedImage( getSize().width, getSize().height, 6 );
            g2 = currentFrameBI.createGraphics();
        }
        g2.translate( (int) centerX, (int) centerY );
        if( clip != null )
            g2.setClip( clip );
        g2.setColor( ColorsEngine.getInstance().getBackgroundColor() );
        g2.fillRect( (int) -(centerX), (int) -(centerY), getSize().width, getSize().height );
        if( SettingsEngine.getInstance().getDisplayLatLon() )
            paintLatLons( g2 );
        drawAirport( g2 );
        recreate = false;
        redrawScroll = true;
        if( displayFPS )
        {
            long endTime;

            if( clip != null )
            {
                g2.setClip( (Shape) new Rectangle( (int) -(centerX), (int) -(centerY), 90, 15 ) );
                g2.setColor( ColorsEngine.getInstance().getBackgroundColor() );
                g2.fillRect( (int) -(centerX), (int) -(centerY), getSize().width, getSize().height );
            }
            endTime = System.currentTimeMillis();
            g2.setFont( new Font( "Arial", 0, 10 ) );
            g2.setColor( Color.red );
            g2.drawString( new StringBuilder().append( "fps: " ).append( fpsFormat.format( (double) (1000.0F / (float) (endTime - startTime)) ) ).append( " [" ).append( endTime - startTime ).append( "ms]" ).toString(), (int) (10.0 - centerX), (int) (10.0 - centerY) );
        }
        if( buffering )
            g.drawImage( (Image) currentFrameBI, 0, 0, null );
        drawing = false;
    }

    public void scrollDisplay(Graphics g, float deltaX, float deltaY)
    {
        long startTime = System.currentTimeMillis();
        Graphics2D g2;
        Object clipPath;

        drawing = true;
        if( displayImage == null )
        {
            displayImage = new BufferedImage( getSize().width, getSize().height, 6 );
            System.gc();
        }
        currentFrameBI = new BufferedImage( getSize().width, getSize().height, 6 );
        g2 = currentFrameBI.createGraphics();
        g2.translate( (int) centerX, (int) centerY );
        if( !redrawScroll )
        {
            g2.drawImage( (Image) lastFrameBI, (int) ((double) deltaX - centerX), (int) ((double) deltaY - centerY), null );
            if( deltaX != 0.0F )
            {
                clipPath = new GeneralPath();
                if( deltaX > 0.0F )
                {
                    ((GeneralPath) clipPath).moveTo( (float) (int) -(centerX), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) deltaX - centerX), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) deltaX - centerX), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX), (float) (int) -(centerY) );
                }
                else
                {
                    ((GeneralPath) clipPath).moveTo( (float) (int) ((double) getSize().width - centerX), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) ((float) getSize().width + deltaX) - centerX), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) ((float) getSize().width + deltaX) - centerX), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX), (float) (int) -(centerY) );
                }
                g2.setClip( (Shape) clipPath );
                drawAirport( g2 );
            }
            if( deltaY != 0.0F )
            {
                clipPath = new GeneralPath();
                if( deltaY < 0.0F )
                {
                    ((GeneralPath) clipPath).moveTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) ((double) ((float) getSize().height + deltaY) - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX + (double) Math.min( deltaX, 0.0F )), (float) (int) ((double) ((float) getSize().height + deltaY) - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX + (double) Math.min( deltaX, 0.0F )), (float) (int) ((double) getSize().height - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) ((double) getSize().height - centerY) );
                }
                else
                {
                    ((GeneralPath) clipPath).moveTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX + (double) Math.min( deltaX, 0.0F )), (float) (int) -(centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) ((double) getSize().width - centerX + (double) Math.min( deltaX, 0.0F )), (float) (int) ((double) deltaY - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) ((double) deltaY - centerY) );
                    ((GeneralPath) clipPath).lineTo( (float) (int) -(centerX) + Math.max( deltaX, 0.0F ), (float) (int) -(centerY) );
                }
                g2.setClip( (Shape) clipPath );
                drawAirport( g2 );
            }
        }
        else
        {
            g2.setClip( (Shape) new Rectangle( (int) -(centerX), (int) -(centerY), getSize().width, getSize().height ) );
            drawAirport( g2 );
        }
        recreate = false;
        clipPath = displayImage.createGraphics();
        ((Graphics2D) clipPath).setColor( ColorsEngine.getInstance().getBackgroundColor() );
        ((Graphics2D) clipPath).fillRect( 0, 0, getSize().width, getSize().height );
        if( SettingsEngine.getInstance().getDisplayLatLon() )
        {
            ((Graphics2D) clipPath).translate( (int) centerX, (int) centerY );
            paintLatLons( (Graphics2D) clipPath );
            ((Graphics2D) clipPath).translate( (int) -(centerX), (int) -(centerY) );
        }
        ((Graphics2D) clipPath).drawImage( (Image) currentFrameBI, 0, 0, null );
        g.drawImage( (Image) displayImage, 0, 0, null );
        lastFrameBI = currentFrameBI;
        if( displayFPS )
        {
            long endTime = System.currentTimeMillis();

            g.setFont( new Font( "Arial", 0, 10 ) );
            g.setColor( Color.red );
            g.drawString( new StringBuilder().append( "fps: " ).append( fpsFormat.format( (double) (1000.0F / (float) (endTime - startTime)) ) ).append( " [" ).append( endTime - startTime ).append( "ms]" ).toString(), 10, 10 );
        }
        drawing = false;
        redrawScroll = false;
    }

    private void drawAirport(Graphics2D g2)
    {
        BaseModel baseModel = SelectedItem.getInstance().getBaseModel();

        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        if( bgImagesBelow && SettingsEngine.getInstance().getDisplayBGImage() )
            drawBGImage( g2 );
        airportModel.clearTaxiwayPathDisplayModels();
        if( SettingsEngine.getInstance().getDisplayApron() )
            paintItems( g2, airportModel.getApronAL(), (recreate || creatingApron || baseModel instanceof ApronModel) ? true : false );
        paintTaxiwayPaths( g2, (recreate || baseModel instanceof TaxiwayPointModel || baseModel instanceof TaxiwayParkingModel) ? true : false, true );
        if( SettingsEngine.getInstance().getDisplayRunway() )
            paintItems( g2, airportModel.getRunwayAL(), (recreate || baseModel instanceof RunwayModel) ? true : false );
        paintTaxiwayPaths( g2, (recreate || baseModel instanceof TaxiwayPointModel || baseModel instanceof TaxiwayParkingModel) ? true : false, false );
        if( (double) scale >= 0.1000000000000000055511151 && SettingsEngine.getInstance().getDisplayTWPoint() )
            paintItems( g2, airportModel.getTaxiwayPointHM(), (recreate || baseModel instanceof TaxiwayPointModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayParking() )
            paintItems( g2, airportModel.getTaxiwayParkingHM(), (recreate || baseModel instanceof TaxiwayParkingModel) ? true : false );
        if( (double) scale >= 0.1000000000000000055511151 )
            paintTaxiwayLines( g2 );
        if( SettingsEngine.getInstance().getDisplayJetways() )
            paintItems( g2, airportModel.getJetwayAL(), (recreate || baseModel instanceof JetwayModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayTWSign() )
            paintItems( g2, airportModel.getTaxiwaySignAL(), (recreate || baseModel instanceof TaxiwaySignModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayTower() )
            paintItems( g2, airportModel.getTowerAL(), (recreate || baseModel instanceof TowerModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayBoundFence() )
            paintItems( g2, airportModel.getBoundaryFenceAL(), (recreate || baseModel instanceof BoundaryFenceModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayBlastFence() )
            paintItems( g2, airportModel.getBlastFenceAL(), (recreate || baseModel instanceof BlastFenceModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayHelipad() )
            paintItems( g2, airportModel.getHelipadAL(), (recreate || baseModel instanceof HelipadModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayStart() )
            paintItems( g2, airportModel.getStartAL(), (recreate || baseModel instanceof StartModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayApronEL() && !modifyingApron )
            paintItems( g2, airportModel.getApronEdgeLightAL(), (recreate || baseModel instanceof ApronEdgeLightModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayILS() )
            paintItems( g2, airportModel.getILSModels(), (recreate || baseModel instanceof ILSModel || baseModel instanceof DMEModel || baseModel instanceof GlideSlopeModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayMarkers() )
            paintItems( g2, airportModel.getMarkerAL(), (recreate || baseModel instanceof MarkerModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayVORs() )
            paintItems( g2, airportModel.getVORAL(), (recreate || baseModel instanceof VORModel || baseModel instanceof DMEModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayNDBs() )
            paintItems( g2, airportModel.getNDBAL(), (recreate || baseModel instanceof NDBModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayWindsocks() )
            paintItems( g2, airportModel.getWindsockAL(), (recreate || baseModel instanceof WindsockModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayScenery() )
            paintItems( g2, airportModel.getSceneryAL(), (recreate || baseModel instanceof SceneryModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayExcludes() )
            paintItems( g2, airportModel.getExclusionAL(), (recreate || creatingExclude || baseModel instanceof ExclusionModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayTriggers() )
            paintItems( g2, airportModel.getTriggerAL(), (recreate || creatingTrigger || baseModel instanceof TriggerModel) ? true : false );
        if( FSXConnection.getInstance().getShowPlane() )
            paintItems( g2, airportModel.getPlaneAL(), (recreate || baseModel instanceof PlaneModel) ? true : false );
        if( SettingsEngine.getInstance().getDisplayAirportCtr() )
        {
            g2.setPaint( (Paint) new Color( 255, 0, 255 ) );
            g2.fill( (Shape) new java.awt.geom.Ellipse2D.Double( -8.0, -8.0, 16.0, 16.0 ) );
            g2.draw( (Shape) new java.awt.geom.Line2D.Double( 0.0, -15.0, 0.0, 15.0 ) );
            g2.draw( (Shape) new java.awt.geom.Line2D.Double( -15.0, 0.0, 15.0, 0.0 ) );
        }
        if( SettingsEngine.getInstance().getDisplayTestRadius() )
            paintTestRadius( g2 );
        if( !bgImagesBelow && SettingsEngine.getInstance().getDisplayBGImage() )
            drawBGImage( g2 );
        if( startPoint != null && endPoint != null )
        {
            g2.setPaint( (Paint) Color.black );
            g2.draw( (Shape) new java.awt.geom.Line2D.Double( (double) startPoint.x - centerX, (double) startPoint.y - centerY, (double) endPoint.x - centerX, (double) endPoint.y - centerY ) );
        }
        if( apronModel != null )
        {
            apronModel.setAlpha( 128 );
            apronModel.paint( g2, recreate );
            apronModel.setAlpha( 255 );
        }
        if( displayLights )
        {
            Color nightColor = ColorsEngine.getInstance().getNightColor();
            ArrayList arrayList;
            int i;
            ArrayList arraylist1;
            ArrayList arraylist2;

            g2.setPaint( (Paint) new Color( nightColor.getRed(), nightColor.getGreen(), nightColor.getBlue(), 200 ) );
            g2.fillRect( (int) -(centerX), (int) -(centerY), getSize().width, getSize().height );
            arrayList = airportModel.getTaxiwayPathDisplayAL();
            for( i = arrayList.size() - 1; i >= 0; --i )
                ((TaxiwayPathDisplayModel) arrayList.get( i )).paintLights( g2, scale );
            arraylist1 = airportModel.getTaxiwayPathAL();
            for( i = arraylist1.size() - 1; i >= 0; --i )
                ((TaxiwayPathModel) arraylist1.get( i )).paintLights( g2 );
            arraylist2 = airportModel.getRunwayAL();
            for( i = arraylist2.size() - 1; i >= 0; --i )
                ((RunwayModel) arraylist2.get( i )).paintLights( g2 );
        }
    }

    private void drawBGImage(Graphics2D g2)
    {
        ArrayList bgImageAL = airportModel.getBGImageAL();
        int i;

        for( i = 0; i < bgImageAL.size(); ++i )
        {
            BackgroundImageModel bgImageModel = (BackgroundImageModel) bgImageAL.get( i );

            bgImageModel.setRecreateBGImage( recreateBGImage );
            bgImageModel.setRecreateBGAllImage( recreateBGAllImage );
            bgImageModel.setDisplaySize( getSize() );
            bgImageModel.setCenterPoint( centerPoint );
            bgImageModel.setScale( scale );
            bgImageModel.paint( g2, recreate );
        }
        recreateBGImage = false;
        recreateBGAllImage = false;
    }

    private void paintLatLons(Graphics2D g2)
    {
        double rightLon = airportModel.getLatLon().getLon();
        int rightLonDegrees = (int) rightLon;
        int rightLonMinutes = (int) ((rightLon - (double) rightLonDegrees) * 60.0) + 20;
        float rightLonSeconds = 0.0F;
        double leftLon;
        int leftLonDegrees;
        int leftLonMinutes;
        float leftLonSeconds;
        double topLat;
        int topLatDegrees;
        int topLatMinutes;
        float topLatSeconds;
        double bottomLat;
        int bottomLatDegrees;
        int bottomLatMinutes;
        float bottomLatSeconds;
        double secondIncrement;
        double i;
        java.awt.geom.Point2D.Float topPoint;
        java.awt.geom.Point2D.Float bottomPoint;

        rightLon = (double) rightLonDegrees + (double) rightLonMinutes / 60.0 + (double) rightLonSeconds / 3600.0;
        leftLon = airportModel.getLatLon().getLon();
        leftLonDegrees = (int) leftLon;
        leftLonMinutes = (int) ((leftLon - (double) leftLonDegrees) * 60.0) - 20;
        leftLonSeconds = 0.0F;
        leftLon = (double) leftLonDegrees + (double) leftLonMinutes / 60.0 + (double) leftLonSeconds / 3600.0;
        topLat = airportModel.getLatLon().getLat();
        topLatDegrees = (int) topLat;
        topLatMinutes = (int) ((topLat - (double) topLatDegrees) * 60.0) + 20;
        topLatSeconds = 0.0F;
        topLat = (double) topLatDegrees + (double) topLatMinutes / 60.0 + (double) topLatSeconds / 3600.0;
        bottomLat = airportModel.getLatLon().getLat();
        bottomLatDegrees = (int) bottomLat;
        bottomLatMinutes = (int) ((bottomLat - (double) bottomLatDegrees) * 60.0) - 20;
        bottomLatSeconds = 0.0F;
        bottomLat = (double) bottomLatDegrees + (double) bottomLatMinutes / 60.0 + (double) bottomLatSeconds / 3600.0;
        g2.setPaint( (Paint) ColorsEngine.getInstance().getLatLonColor() );
        g2.setFont( Utilities.LAT_LON_FONT );
        secondIncrement = 0.000277777777777777777536844 * (double) SettingsEngine.getInstance().getSecondsIncrement();
        for( i = leftLon; i < rightLon; i += secondIncrement )
        {
            topPoint = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), topLat, i, scale );
            bottomPoint = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), bottomLat, i, scale );
            g2.draw( (Shape) new java.awt.geom.Line2D.Float( topPoint.x, topPoint.y, bottomPoint.x, bottomPoint.y ) );
            g2.drawString( DisplayEngine.getInstance().formatGridLongitude( i ), bottomPoint.x + 2.0F, (float) ((double) getSize().height - centerY) );
        }
        for( i = bottomLat; i < topLat; i += secondIncrement )
        {
            topPoint = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), i, leftLon, scale );
            bottomPoint = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), i, rightLon, scale );
            g2.draw( (Shape) new java.awt.geom.Line2D.Float( topPoint.x, topPoint.y, bottomPoint.x, bottomPoint.y ) );
            g2.drawString( DisplayEngine.getInstance().formatGridLatitude( i ), (float) (-(centerX) + 2.0), topPoint.y - 2.0F );
        }
    }

    private void paintTestRadius(Graphics2D g2)
    {
        double radius = airportModel.getAirportTestRadius();

        if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
            radius = radius * 3.2799999713897705078125;
        else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
            radius = radius * 6074.56005859375;
        radius = radius * (double) scale;
        g2.setPaint( (Paint) Color.red );
        g2.draw( (Shape) new java.awt.geom.Ellipse2D.Double( -radius, -radius, radius * 2.0, radius * 2.0 ) );
    }

    private void paintItems(Graphics2D g2, ArrayList itemAL, boolean recreate)
    {
        int i;

        for( i = itemAL.size() - 1; i >= 0; --i )
        {
            BaseModel baseModel = (BaseModel) itemAL.get( i );

            baseModel.setCenterPoint( centerPoint );
            baseModel.setScale( scale );
            baseModel.paint( g2, recreate );
        }
    }

    private void paintItems(Graphics2D g2, HashMap itemHM, boolean recreate)
    {
        Iterator iterator = itemHM.keySet().iterator();

        while( iterator.hasNext() )
        {
            BaseModel baseModel = (BaseModel) itemHM.get( (Integer) iterator.next() );

            baseModel.setCenterPoint( centerPoint );
            baseModel.setScale( scale );
            baseModel.paint( g2, recreate );
        }
    }

    private void paintTaxiwayPaths(Graphics2D g2, boolean recreate, boolean first)
    {
        ArrayList taxiwayPathAL = airportModel.getTaxiwayPathAL();
        HashMap taxiwayPointHM = airportModel.getTaxiwayPointHM();
        HashMap taxiwayParkingHM = airportModel.getTaxiwayParkingHM();
        int i;

        for( i = taxiwayPathAL.size() - 1; i >= 0; --i )
        {
            TaxiwayPathModel taxiwayPathModel = (TaxiwayPathModel) taxiwayPathAL.get( i );

            if( first )
            {
                int start;
                int end;

                taxiwayPathModel.setCenterPoint( centerPoint );
                taxiwayPathModel.setScale( scale );
                taxiwayPathModel.setHighlightedTWPath( airportModel.getHighlightedTWPath() );
                start = taxiwayPathModel.getStart();
                end = taxiwayPathModel.getEnd();
                if( taxiwayPathModel.getType().equals( "PARKING" ) )
                {
                    if( taxiwayPointHM.containsKey( new Integer( start ) ) )
                    {
                        taxiwayPathModel.setStartLon( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( start ) )).getLatLon().getLon() );
                        taxiwayPathModel.setStartLat( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( start ) )).getLatLon().getLat() );
                    }
                    if( taxiwayParkingHM.containsKey( new Integer( end ) ) )
                    {
                        taxiwayPathModel.setEndLon( ((TaxiwayParkingModel) taxiwayParkingHM.get( new Integer( end ) )).getLatLon().getLon() );
                        taxiwayPathModel.setEndLat( ((TaxiwayParkingModel) taxiwayParkingHM.get( new Integer( end ) )).getLatLon().getLat() );
                    }
                }
                else
                {
                    if( taxiwayPointHM.containsKey( new Integer( start ) ) )
                    {
                        taxiwayPathModel.setStartLon( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( start ) )).getLatLon().getLon() );
                        taxiwayPathModel.setStartLat( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( start ) )).getLatLon().getLat() );
                    }
                    if( taxiwayPointHM.containsKey( new Integer( end ) ) )
                    {
                        taxiwayPathModel.setEndLon( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( end ) )).getLatLon().getLon() );
                        taxiwayPathModel.setEndLat( ((TaxiwayPointModel) taxiwayPointHM.get( new Integer( end ) )).getLatLon().getLat() );
                    }
                }
            }
            taxiwayPathModel.paint( g2, recreate );
            if( first && taxiwayPathModel.getLeftEdgeLine() != null )
            {
                TaxiwayPathDisplayModel taxiwaypathdisplaymodel1 = new TaxiwayPathDisplayModel( taxiwayPathModel.getLeftEdgeLine(), taxiwayPathModel.getRightEdgeLine(), taxiwayPathModel.getLeftEdge(), taxiwayPathModel.getRightEdge(), taxiwayPathModel.getType(), taxiwayPathModel.getStart(), taxiwayPathModel.getEnd(), taxiwayPathModel.getWidth(), taxiwayPathModel.getLeftEdgeLighted(), taxiwayPathModel.getRightEdgeLighted(), taxiwayPathModel.getWidthMeasure() );

                if( taxiwayPathModel.getType().equals( "PARKING" ) )
                    airportModel.addTaxiwayPathDisplayModel( taxiwaypathdisplaymodel1, true );
                else
                    airportModel.addTaxiwayPathDisplayModel( taxiwaypathdisplaymodel1, false );
            }
        }
    }

    private void paintTaxiwayLines(Graphics2D g2)
    {
        HashMap hashMap = airportModel.getTaxiwayPathDisplayHM();
        Object iterator = hashMap.keySet().iterator();
        int i;

        while( ((Iterator) iterator).hasNext() )
        {
            Integer index = (Integer) ((Iterator) iterator).next();
            Object taxiwayPathDisplayModelAL = (ArrayList) hashMap.get( index );

            Collections.sort( (List) taxiwayPathDisplayModelAL, (Comparator) HeadingSort.getInstance( index.intValue() ) );
            if( ((ArrayList) taxiwayPathDisplayModelAL).size() <= 1 )
                continue;
            for( i = ((ArrayList) taxiwayPathDisplayModelAL).size() - 1; i >= 0; --i )
            {
                TaxiwayPathDisplayModel model1 = (TaxiwayPathDisplayModel) ((ArrayList) taxiwayPathDisplayModelAL).get( i );
                TaxiwayPathDisplayModel model2;
                Line2D line1;
                Line2D line2;
                float x11;
                float y11;
                float x12;
                float y12;
                float x21;
                float y21;
                float x22;
                float y22;
                float b1;
                float b2;
                float a1;
                float a2;
                float xi;
                float yi;
                double length1;
                double length2;
                double length3;
                double length4;

                if( i > 0 )
                    model2 = (TaxiwayPathDisplayModel) ((ArrayList) taxiwayPathDisplayModelAL).get( i - 1 );
                else
                    model2 = (TaxiwayPathDisplayModel) ((ArrayList) taxiwayPathDisplayModelAL).get( ((ArrayList) taxiwayPathDisplayModelAL).size() - 1 );
                line1 = model1.getLeftEdgeLine( index.intValue() );
                line2 = model2.getRightEdgeLine( index.intValue() );
                x11 = (float) line1.getX1();
                y11 = (float) line1.getY1();
                x12 = (float) line1.getX2();
                y12 = (float) line1.getY2();
                if( !model1.isAtBeginning( index.intValue() ) )
                {
                    x11 = (float) line1.getX2();
                    y11 = (float) line1.getY2();
                    x12 = (float) line1.getX1();
                    y12 = (float) line1.getY1();
                }
                x21 = (float) line2.getX1();
                y21 = (float) line2.getY1();
                x22 = (float) line2.getX2();
                y22 = (float) line2.getY2();
                if( !model2.isAtBeginning( index.intValue() ) )
                {
                    x21 = (float) line2.getX2();
                    y21 = (float) line2.getY2();
                    x22 = (float) line2.getX1();
                    y22 = (float) line2.getY1();
                }
                b1 = (y11 - y12) / (x11 - x12);
                b2 = (y21 - y22) / (x21 - x22);
                a1 = y11 - b1 * x11;
                a2 = y21 - b2 * x21;
                if( b1 == (1.0F / 0.0F) || b1 == (-1.0F / 0.0F) || a1 == (1.0F / 0.0F) || a1 == (-1.0F / 0.0F) )
                    xi = x11;
                else if( b2 == (1.0F / 0.0F) || b2 == (-1.0F / 0.0F) || a2 == (1.0F / 0.0F) || a2 == (-1.0F / 0.0F) )
                    xi = x21;
                else
                    xi = -(a1 - a2) / (b1 - b2);
                if( b1 == 0.0F || a1 == 0.0F )
                    yi = y11;
                else if( b2 == 0.0F || a2 == 0.0F )
                    yi = y21;
                else if( b1 == (1.0F / 0.0F) || b1 == (-1.0F / 0.0F) )
                {
                    if( a1 == (1.0F / 0.0F) || a1 == (-1.0F / 0.0F) )
                        yi = y22;
                    else
                        yi = a1 + xi;
                }
                else
                    yi = a1 + b1 * xi;
                length1 = Math.sqrt( Math.pow( (double) (x12 - x11), 2.0 ) + Math.pow( (double) (y12 - y11), 2.0 ) );
                length2 = Math.sqrt( Math.pow( (double) (x22 - x21), 2.0 ) + Math.pow( (double) (y22 - y21), 2.0 ) );
                if( model1.isAtBeginning( index.intValue() ) )
                    length3 = Math.sqrt( Math.pow( (double) xi - line1.getX1(), 2.0 ) + Math.pow( (double) yi - line1.getY1(), 2.0 ) );
                else
                    length3 = Math.sqrt( Math.pow( line1.getX2() - (double) xi, 2.0 ) + Math.pow( line1.getY2() - (double) yi, 2.0 ) );
                if( model2.isAtBeginning( index.intValue() ) )
                    length4 = Math.sqrt( Math.pow( (double) xi - line2.getX1(), 2.0 ) + Math.pow( (double) yi - line2.getY1(), 2.0 ) );
                else
                    length4 = Math.sqrt( Math.pow( line2.getX2() - (double) xi, 2.0 ) + Math.pow( line2.getY2() - (double) yi, 2.0 ) );
                if( model1.isAtBeginning( index.intValue() ) )
                {
                    if( length3 < length1 + length2 && length4 < length1 + length2 )
                        line1.setLine( line1.getX1(), line1.getY1(), (double) xi, (double) yi );
                }
                else if( length3 < length1 + length2 && length4 < length1 + length2 )
                    line1.setLine( (double) xi, (double) yi, line1.getX2(), line1.getY2() );
                if( model2.isAtBeginning( index.intValue() ) )
                {
                    if( length3 < length1 + length2 && length4 < length1 + length2 )
                        line2.setLine( line2.getX1(), line2.getY1(), (double) xi, (double) yi );
                }
                else if( length3 < length1 + length2 && length4 < length1 + length2 )
                    line2.setLine( (double) xi, (double) yi, line2.getX2(), line2.getY2() );
            }
        }
        iterator = airportModel.getTaxiwayPathDisplayAL();
        for( i = ((ArrayList) iterator).size() - 1; i >= 0; --i )
            ((TaxiwayPathDisplayModel) ((ArrayList) iterator).get( i )).paint( g2, scale );
        g2.setStroke( (Stroke) Utilities.DEFAULT_STROKE );
    }

    private void zoomFromWheel(Point point, int zoom)
    {
        float oldScale = scale;
        float zoomFactor = 0.009999999776482582092285156F;
        float adjust;

        if( (double) scale > 1.5 )
            zoomFactor = 0.5F;
        else if( (double) scale > 0.75 )
            zoomFactor = 0.25F;
        else if( (double) scale > 0.2000000000000000111022302 )
            zoomFactor = 0.1000000014901161193847656F;
        scale -= (float) zoom * zoomFactor;
        if( (double) scale < 0.005000000000000000104083409 )
            scale = 0.004999999888241291046142578F;
        else if( scale > 4.0F )
            scale = 4.0F;
        adjust = scale / oldScale;
        if( point == null )
        {
            centerX = (centerX - (double) getSize().width / 2.0) * (double) adjust + (double) getSize().width / 2.0;
            centerY = (centerY - (double) getSize().height / 2.0) * (double) adjust + (double) getSize().height / 2.0;
        }
        else
        {
            centerX += (double) getSize().width / 2.0 - (double) point.x;
            centerX = (centerX - (double) getSize().width / 2.0) * (double) adjust + (double) getSize().width / 2.0;
            centerY += (double) getSize().height / 2.0 - (double) point.y;
            centerY = (centerY - (double) getSize().height / 2.0) * (double) adjust + (double) getSize().height / 2.0;
        }
        airportModel.setScale( scale );
        recreate = true;
        recreateBGImage = true;
        recreateBGAllImage = true;
        repaint();
    }

    public void recenter()
    {
        scale = 0.2000000029802322387695313F;
        centerSet = false;
        recreate = true;
        repaint();
    }

    private synchronized void scrollAirport(int x, int y)
    {
        float deltaX;
        float deltaY;

        centerX += (double) (x - oldX);
        centerY += (double) (y - oldY);
        deltaX = (float) (x - oldX);
        deltaY = (float) (y - oldY);
        oldX = x;
        oldY = y;
        recreate = false;
        recreateBGImage = true;
        scrollDisplay( getGraphics(), deltaX, deltaY );
    }

    private boolean checkItems(Point point, ArrayList itemAL)
    {
        int i = itemAL.size() - 1;

        while( i >= 0 )
        {
            if( ((BaseModel) itemAL.get( i )).isWithinObject( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) ) )
            {
                ((BaseModel) itemAL.get( i )).setCurrentlySelected( true );
                repaint();
                requestFocus();
                return true;
            }
            else
                --i;
        }
        return false;
    }

    private boolean checkItems(Point point, HashMap itemHM)
    {
        Iterator iterator = itemHM.keySet().iterator();

        while( iterator.hasNext() )
        {
            BaseModel baseModel = (BaseModel) itemHM.get( (Integer) iterator.next() );

            if( !baseModel.isWithinObject( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) ) )
                continue;
            baseModel.setCurrentlySelected( true );
            repaint();
            requestFocus();
            return true;
        }
        return false;
    }

    public void movePlaneToCursor()
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), mouseClickPoint.getX() - centerX, mouseClickPoint.getY() - centerY, scale );

        ((PlaneModel) airportModel.getPlaneAL().get( 0 )).moveTo( latLonPoint, (double) oldX - centerX, (double) oldY - centerY );
        ((PlaneModel) airportModel.getPlaneAL().get( 0 )).setCurrentlySelected( true );
        if( FSXConnection.getInstance().isConnectedToFSX() )
        {
            float airportAltitude = (float) airportModel.getAlt();

            if( airportModel.getAltMeasure().equals( "M" ) )
                airportAltitude = airportAltitude * 3.2799999713897705078125F;
            FSXConnection.getInstance().movePlane( latLonPoint, ((PlaneModel) airportModel.getPlaneAL().get( 0 )).getHeading() );
        }
        selectShowPlane();
        recreate = false;
        repaint();
    }

    public void movePlaneToFSX(boolean shouldSelect, boolean displayMessages)
    {
        LatLonPoint fsxLatLon = FSXConnection.getInstance().getPlaneLatLon();

        if( fsxLatLon == null )
        {
            if( displayMessages )
                JOptionPane.showMessageDialog( (Component) this, "Location of the plane in FSX is unknown.", "Unknown Location...", 0 );
        }
        else
        {
            float testRadius = (float) airportModel.getAirportTestRadius();
            java.awt.geom.Point2D.Float distancePoint;
            float distance;

            if( airportModel.getAirportTestRadiusMeasure().equals( "M" ) )
                testRadius = testRadius * 3.2799999713897705078125F;
            else if( airportModel.getAirportTestRadiusMeasure().equals( "N" ) )
                testRadius = testRadius * 6074.56005859375F;
            distancePoint = Utilities.getPixelsBetweenPoints( airportModel.getLatLon().getLat(), airportModel.getLatLon().getLon(), fsxLatLon.getLat(), fsxLatLon.getLon(), 1.0F );
            distance = (float) Math.sqrt( Math.pow( distancePoint.getX(), 2.0 ) + Math.pow( distancePoint.getY(), 2.0 ) );
            if( distance < testRadius )
            {
                java.awt.geom.Point2D.Float distanceOffset;

                ((PlaneModel) airportModel.getPlaneAL().get( 0 )).moveTo( (LatLonPoint) fsxLatLon.clone(), 0.0, 0.0 );
                ((PlaneModel) airportModel.getPlaneAL().get( 0 )).setHeading( (float) FSXConnection.getInstance().getPlaneHeading() );
                if( shouldSelect )
                    ((PlaneModel) airportModel.getPlaneAL().get( 0 )).setCurrentlySelected( true );
                distanceOffset = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), fsxLatLon.getLat(), fsxLatLon.getLon(), scale );
                centerX = (double) (distanceOffset.x * -1.0F) + (double) getSize().width / 2.0;
                centerY = (double) (distanceOffset.y * -1.0F) + (double) getSize().height / 2.0;
                selectShowPlane();
                recreate = false;
                repaint();
            }
            else if( displayMessages )
                JOptionPane.showMessageDialog( (Component) this, "The plane's location is outside the airport test radius.", "Plane Too Far Away...", 0 );
        }
    }

    private void findItem(Point point)
    {
        if( !com.zbluesoftware.fsxp.simconnect.FSXConnection.getInstance().getShowPlane() || !checkItems( point, airportModel.getPlaneAL() ) )
        {
            if( SettingsEngine.getInstance().getDisplayExcludes() && checkItems( point, airportModel.getExclusionAL() ) )
            {
                vertexModel = ((ExclusionModel) SelectedItem.getInstance().getBaseModel()).isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
                ((ExclusionModel) SelectedItem.getInstance().getBaseModel()).displayVertexModel( vertexModel );
            }
            else if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTower() || !checkItems( point, airportModel.getTowerAL() ) )
            {
                if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayStart() || !checkItems( point, airportModel.getStartAL() ) )
                {
                    if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayHelipad() || !checkItems( point, airportModel.getHelipadAL() ) )
                    {
                        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWSign() || !checkItems( point, airportModel.getTaxiwaySignAL() ) )
                        {
                            if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayJetways() || !checkItems( point, airportModel.getJetwayAL() ) )
                            {
                                if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayParking() || !checkItems( point, airportModel.getTaxiwayParkingHM() ) )
                                {
                                    if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTriggers() || !checkItems( point, airportModel.getTriggerAL() ) )
                                    {
                                        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWPoint() || !checkItems( point, airportModel.getTaxiwayPointHM() ) )
                                        {
                                            if( !checkItems( point, airportModel.getTaxiwayPathAL() ) )
                                            {
                                                if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayRunway() || !checkItems( point, airportModel.getRunwayAL() ) )
                                                {
                                                    if( SettingsEngine.getInstance().getDisplayApronEL() && checkItems( point, airportModel.getApronEdgeLightAL() ) )
                                                    {
                                                        vertexModel = ((ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel()).isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
                                                        ((ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel()).displayVertexModel( vertexModel );
                                                    }
                                                    else if( SettingsEngine.getInstance().getDisplayBoundFence() && checkItems( point, airportModel.getBoundaryFenceAL() ) )
                                                    {
                                                        vertexModel = ((BoundaryFenceModel) SelectedItem.getInstance().getBaseModel()).isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
                                                        ((BoundaryFenceModel) SelectedItem.getInstance().getBaseModel()).displayVertexModel( vertexModel );
                                                    }
                                                    else if( SettingsEngine.getInstance().getDisplayBlastFence() && checkItems( point, airportModel.getBlastFenceAL() ) )
                                                    {
                                                        vertexModel = ((BlastFenceModel) SelectedItem.getInstance().getBaseModel()).isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
                                                        ((BlastFenceModel) SelectedItem.getInstance().getBaseModel()).displayVertexModel( vertexModel );
                                                    }
                                                    else if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayMarkers() || !checkItems( point, airportModel.getMarkerAL() ) )
                                                    {
                                                        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayVORs() || !checkItems( point, airportModel.getVORAL() ) )
                                                        {
                                                            if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayVORs() || !checkItems( point, airportModel.getVORdmeModels() ) )
                                                            {
                                                                if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayNDBs() || !checkItems( point, airportModel.getNDBAL() ) )
                                                                {
                                                                    if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayScenery() || !checkItems( point, airportModel.getSceneryAL() ) )
                                                                    {
                                                                        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayILS() || !checkItems( point, airportModel.getILSModels() ) )
                                                                        {
                                                                            if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayILS() || !checkItems( point, airportModel.getILSDMEModels() ) )
                                                                            {
                                                                                if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayILS() || !checkItems( point, airportModel.getILSGSModels() ) )
                                                                                {
                                                                                    if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayWindsocks() || !checkItems( point, airportModel.getWindsockAL() ) )
                                                                                    {
                                                                                        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayApron() || !checkItems( point, airportModel.getApronAL() ) )
                                                                                        {
                                                                                            SelectedItem.getInstance().selectBaseModel( null );
                                                                                            repaint();
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void dragItem(Point point)
    {
        if( SelectedItem.getInstance().getBaseModel() != null && SelectedItem.getInstance().isModelDraggable() )
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );

            if( SelectedItem.getInstance().getBaseModel().moveTo( latLonPoint, (double) oldX - centerX, (double) oldY - centerY ) )
            {
                Object clipR = null;

                if( doubleBuffer && SelectedItem.getInstance().getBaseModel().getClip() != null )
                {
                    clipR = SelectedItem.getInstance().getBaseModel().getClip().getBounds();
                    ((Rectangle) clipR).setLocation( ((Rectangle) clipR).x - 10, ((Rectangle) clipR).y - 10 );
                    ((Rectangle) clipR).setSize( ((Rectangle) clipR).width + 20, ((Rectangle) clipR).height + 20 );
                }
                recreate = false;
                if( clipR == null )
                    repaint();
                else if( !drawing )
                    paint( getGraphics(), (Shape) clipR, true );
            }
            oldX = point.x;
            oldY = point.y;
            if( SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel && vertexModel != null )
                dragApronEdgeLight( point );
            else if( SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel && vertexModel != null )
                dragFence( point );
            else if( SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel && vertexModel != null )
                dragBlastFence( point );
            else if( SelectedItem.getInstance().getBaseModel() instanceof ExclusionModel && vertexModel != null )
                dragExclude( point );
        }
    }

    private void displayLatLon(Point point)
    {
        mouseLatLon = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        AlertModel.getInstance().firePropertyChange( "LatLon", mouseLatLon, mouseLatLon );
    }

    public LatLonPoint getMouseLatLon()
    {
        return mouseLatLon;
    }

    private void checkTaxiwayPoint(Point point)
    {
        if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayParking() || !checkItems( point, airportModel.getTaxiwayParkingHM() ) )
        {
            if( !com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWPoint() || !checkItems( point, airportModel.getTaxiwayPointHM() ) )
            {
                if( SelectedItem.getInstance().getBaseModel() != null || startPoint != null )
                {
                    SelectedItem.getInstance().selectBaseModel( null );
                    repaint();
                }
            }
        }
    }

    private void selectDisplayMenu(int option)
    {
        JMenu menu = FSXPMenuBar.getInstance().getMenu( "Display" );
        int i = menu.getItemCount() - 1;

        while( i >= 0 )
        {
            if( menu.getItem( i ) != null && menu.getItem( i ) instanceof JCheckBoxMenuItem && menu.getItem( i ).getActionCommand().equals( new StringBuilder().append( "" ).append( option ).toString() ) )
            {
                if( !((JCheckBoxMenuItem) menu.getItem( i )).getState() )
                    menu.getItem( i ).doClick( 10 );
                return;
            }
            else
                --i;
        }
    }

    private void selectShowPlane()
    {
        JMenu menu = FSXPMenuBar.getInstance().getMenu( "SimConnect" );
        int i = menu.getItemCount() - 1;

        while( i >= 0 )
        {
            if( menu.getItem( i ) != null && menu.getItem( i ) instanceof JCheckBoxMenuItem && menu.getItem( i ).getActionCommand().equals( "77" ) )
            {
                if( !((JCheckBoxMenuItem) menu.getItem( i )).getState() )
                    menu.getItem( i ).doClick( 10 );
                return;
            }
            else
                --i;
        }
    }

    private void insertStartLocation(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        StartModel model = new StartModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        model.setShouldNotify( true );
        airportModel.addStartModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 50 );
        repaint();
    }

    private void insertTower(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        TowerModel model = new TowerModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        model.setSceneryLatLon( (LatLonPoint) latLonPoint.clone() );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
            model.setSceneryAlt( 0.0 );
            model.setSceneryAltMeasure( airportModel.getAltMeasure() );
            model.setAltitudeIsAgl( true );
        }
        model.setShouldNotify( true );
        airportModel.addTowerModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 46 );
        repaint();
    }

    private void insertParkingLocation(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        TaxiwayParkingModel model = new TaxiwayParkingModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        model.setIndex( airportModel.incrementTaxiIndex() );
        model.setShouldNotify( true );
        airportModel.addTaxiwayParkingModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 38 );
        repaint();
    }

    private TaxiwayPointModel insertTaxiwayPoint(Point point, boolean shiftDown, String type)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        TaxiwayPointModel model = new TaxiwayPointModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        model.setIndex( airportModel.incrementTaxiIndex() );
        model.setType( type );
        model.setShouldNotify( true );
        airportModel.addTaxiwayPointModel( model );
        model.setCurrentlySelected( true );
        if( shiftDown )
        {
            ArrayList itemAL = airportModel.getTaxiwayPathAL();
            int i = itemAL.size() - 1;

            while( i >= 0 )
            {
                if( ((TaxiwayPathModel) itemAL.get( i )).isWithinObject( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) ) )
                {
                    TaxiwayPathModel model2 = (TaxiwayPathModel) ((TaxiwayPathModel) itemAL.get( i )).clone();

                    model2.setStart( model.getIndex() );
                    airportModel.addTaxiwayPathModel( model2 );
                    ((TaxiwayPathModel) itemAL.get( i )).setEnd( model.getIndex() );
                    break;
                }
                else
                    --i;
            }
        }
        selectDisplayMenu( 36 );
        repaint();
        ILSLineUpdates.getInstance().addAirportModel( airportModel );
        return model;
    }

    private void insertTaxiwaySign(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        TaxiwaySignModel model = new TaxiwaySignModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        model.setShouldNotify( true );
        airportModel.addTaxiwaySignModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 37 );
        repaint();
    }

    private void insertHelipad(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        HelipadModel model = new HelipadModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        model.setShouldNotify( true );
        airportModel.addHelipadModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 49 );
        repaint();
    }

    private void insertJetway(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        JetwayModel model = new JetwayModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( 0.0 );
            model.setAltMeasure( airportModel.getAltMeasure() );
            model.setAltitudeIsAgl( true );
        }
        model.setShouldNotify( true );
        airportModel.addJetwayModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 39 );
        repaint();
    }

    private void insertILSBeam(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        ILSModel model = new ILSModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( (float) airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        airportModel.addILSModel( model );
        model.setCurrentlySelected( true );
        model.setShouldNotify( true );
        selectDisplayMenu( 33 );
        repaint();
    }

    private void insertMarker(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        MarkerModel model = new MarkerModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( (float) airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        airportModel.addMarkerModel( model );
        model.setShouldNotify( true );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 56 );
        repaint();
    }

    private void insertVOR(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        VORModel model = new VORModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( (float) airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        airportModel.addVORModel( model );
        model.setShouldNotify( true );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 58 );
        repaint();
    }

    private void insertNDB(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        NDBModel model = new NDBModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( (float) airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        airportModel.addNDBModel( model );
        model.setShouldNotify( true );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 57 );
        repaint();
    }

    private void insertWindsock(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        WindsockModel model = new WindsockModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( 0.0F );
            model.setAltMeasure( airportModel.getAltMeasure() );
            model.setAltitudeIsAgl( true );
        }
        airportModel.addWindsockModel( model );
        model.setShouldNotify( true );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 59 );
        repaint();
    }

    private void insertScenery(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        SceneryModel model = new SceneryModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        airportModel.addSceneryModel( model );
        model.setShouldNotify( true );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 61 );
        repaint();
    }

    private void createTaxiway(Point point, boolean shiftDown)
    {
        Object baseModel = SelectedItem.getInstance().getBaseModel();

        if( baseModel == null )
            baseModel = insertTaxiwayPoint( point, shiftDown, "NORMAL" );
        taxiwayPathModel = new TaxiwayPathModel();
        taxiwayPathModel.setShouldNotify( false );
        if( baseModel instanceof TaxiwayParkingModel )
        {
            taxiwayPathModel.setEnd( ((TaxiwayParkingModel) baseModel).getIndex() );
            taxiwayPathModel.setType( "PARKING" );
        }
        else if( baseModel instanceof TaxiwayPointModel )
            taxiwayPathModel.setStart( ((TaxiwayPointModel) baseModel).getIndex() );
        taxiwayPathModel.setName( airportModel.getBlankTaxiNameIndex() );
        if( SettingsEngine.getInstance().getTaxiwayProperties() && baseModel instanceof TaxiwayPointModel )
        {
            ArrayList itemAL = airportModel.getTaxiwayPathAL();
            ArrayList arrayList = new ArrayList();
            int i;

            for( i = itemAL.size() - 1; i >= 0; --i )
            {
                if( !((TaxiwayPathModel) itemAL.get( i )).getType().equals( "PARKING" ) && (((TaxiwayPathModel) itemAL.get( i )).getStart() == taxiwayPathModel.getStart() || ((TaxiwayPathModel) itemAL.get( i )).getEnd() == taxiwayPathModel.getStart()) )
                    arrayList.add( itemAL.get( i ) );
            }
            if( arrayList.size() == 1 )
            {
                TaxiwayPathModel taxiwaypathmodel1 = (TaxiwayPathModel) arrayList.get( 0 );

                taxiwayPathModel.setType( taxiwaypathmodel1.getType() );
                taxiwayPathModel.setSurface( taxiwaypathmodel1.getSurface() );
                taxiwayPathModel.setLeftEdge( taxiwaypathmodel1.getLeftEdge() );
                taxiwayPathModel.setRightEdge( taxiwaypathmodel1.getRightEdge() );
                taxiwayPathModel.setNumber( taxiwaypathmodel1.getNumber() );
                taxiwayPathModel.setDesignator( taxiwaypathmodel1.getDesignator() );
                taxiwayPathModel.setWidthMeasure( taxiwaypathmodel1.getWidthMeasure() );
                taxiwayPathModel.setName( taxiwaypathmodel1.getName() );
                taxiwayPathModel.setWidth( taxiwaypathmodel1.getWidth() );
                taxiwayPathModel.setWeightLimit( taxiwaypathmodel1.getWeightLimit() );
                taxiwayPathModel.setDrawSurface( taxiwaypathmodel1.getDrawSurface() );
                taxiwayPathModel.setDrawDetail( taxiwaypathmodel1.getDrawDetail() );
                taxiwayPathModel.setCenterLine( taxiwaypathmodel1.getCenterLine() );
                taxiwayPathModel.setCenterLineLighted( taxiwaypathmodel1.getCenterLineLighted() );
                taxiwayPathModel.setLeftEdgeLighted( taxiwaypathmodel1.getLeftEdgeLighted() );
                taxiwayPathModel.setRightEdgeLighted( taxiwaypathmodel1.getRightEdgeLighted() );
            }
        }
    }

    private void finishTaxiway(Point point, boolean shiftDown)
    {
        Object baseModel = SelectedItem.getInstance().getBaseModel();

        if( baseModel == null )
            baseModel = insertTaxiwayPoint( point, shiftDown, "NORMAL" );
        if( baseModel instanceof TaxiwayParkingModel )
        {
            taxiwayPathModel.setEnd( ((TaxiwayParkingModel) baseModel).getIndex() );
            taxiwayPathModel.setType( "PARKING" );
        }
        else if( baseModel instanceof TaxiwayPointModel )
        {
            if( taxiwayPathModel.getType().equals( "PARKING" ) )
                taxiwayPathModel.setStart( ((TaxiwayPointModel) baseModel).getIndex() );
            else
                taxiwayPathModel.setEnd( ((TaxiwayPointModel) baseModel).getIndex() );
        }
        if( taxiwayPathModel.getStart() != taxiwayPathModel.getEnd() )
            airportModel.addTaxiwayPathModel( taxiwayPathModel );
        startPoint = null;
        endPoint = null;
        taxiwayPathModel.setShouldNotify( true );
        taxiwayPathModel.setCurrentlySelected( true );
        selectDisplayMenu( 42 );
        selectDisplayMenu( 40 );
        repaint();
        if( taxiwayPathModel.getType().equals( "TAXI" ) && SettingsEngine.getInstance().getTaxiwayRunways() )
        {
            Object thread = new AirportDisplay$1( this );

            ((Thread) thread).setPriority( 4 );
            ((Thread) thread).start();
        }
    }

    private void createExclude(Point point)
    {
        if( modifyingExclude && vertexModel == null && exclusionModel != null )
            vertexModel = exclusionModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayExcludes() && checkItems( point, airportModel.getExclusionAL() ) )
        {
            exclusionModel = (ExclusionModel) SelectedItem.getInstance().getBaseModel();
            modifyingExclude = true;
        }
        else if( vertexModel == null )
        {
            exclusionModel = new ExclusionModel();
            exclusionModel.setShouldNotify( false );
            exclusionModel.getVertex1().setShouldNotify( false );
            exclusionModel.getVertex2().setShouldNotify( false );
            exclusionModel.getVertex1().setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            exclusionModel.getVertex2().setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            vertexModel = exclusionModel.getVertex2();
            airportModel.addExclusionModel( exclusionModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) exclusionModel );
            exclusionModel.setCurrentlySelected( true );
            creatingExclude = true;
        }
    }

    private void finishExclude(Point point)
    {
        exclusionModel.setShouldNotify( true );
        exclusionModel.getVertex1().setShouldNotify( true );
        exclusionModel.getVertex2().setShouldNotify( true );
        vertexModel = null;
        if( exclusionModel.getVertex1().getLatLon().equals( exclusionModel.getVertex2().getLatLon() ) )
            airportModel.removeExclusionModel( exclusionModel );
        repaint();
    }

    private void dragExclude(Point point)
    {
        if( vertexModel != null )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            selectDisplayMenu( 52 );
            repaint();
        }
        else if( exclusionModel != null )
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );

            if( SelectedItem.getInstance().getBaseModel().moveTo( latLonPoint, (double) oldX - centerX, (double) oldY - centerY ) )
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void createApron(Point point)
    {
        if( apronModel == null )
        {
            apronModel = new ApronModel();
            airportModel.addApronModel( apronModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) apronModel );
            apronModel.setCurrentlySelected( true );
            apronModel.setModifying( true );
            creatingApron = true;
            modifyingApron = true;
        }
        if( creatingApron )
        {
            LatLonPoint latLonPoint;
            VertexModel model;

            startPoint = point;
            latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
            model = new VertexModel();
            model.setShouldNotify( false );
            model.setLatLon( latLonPoint );
            model.setShouldNotify( true );
            apronModel.addVertexModel( model );
            selectDisplayMenu( 34 );
            repaint();
        }
    }

    private void startApronDrag(Point point)
    {
        if( modifyingApron && vertexModel == null && apronModel != null )
        {
            vertexModel = apronModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            apronModel.displayVertexModel( vertexModel );
            repaint();
        }
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayApron() && checkItems( point, airportModel.getApronAL() ) )
        {
            if( apronModel != null )
                apronModel.setModifying( false );
            apronModel = (ApronModel) SelectedItem.getInstance().getBaseModel();
            apronModel.setModifying( true );
            modifyingApron = true;
            repaint();
        }
        else if( vertexModel == null && !creatingApron && apronModel != null )
        {
            apronModel.setModifying( false );
            apronModel = null;
            modifyingApron = false;
            repaint();
        }
    }

    private void dragApron(Point point)
    {
        if( vertexModel != null && SelectedItem.getInstance().isModelDraggable() )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            repaint();
        }
        else if( apronModel != null && !creatingApron && SelectedItem.getInstance().isModelDraggable() )
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );

            if( SelectedItem.getInstance().getBaseModel().moveTo( latLonPoint, (double) oldX - centerX, (double) oldY - centerY ) )
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void finishApron(Point point)
    {
        startPoint = null;
        endPoint = null;
        creatingApron = false;
        repaint();
    }

    private void addApronVertex(Point point)
    {
        if( apronModel != null )
        {
            int index = apronModel.isBetweenVertices( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( index >= 0 )
            {
                VertexModel model = new VertexModel();

                model.setShouldNotify( false );
                model.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
                model.setShouldNotify( true );
                apronModel.insertVertexModel( model, index + 1 );
                apronModel.displayVertexModel( model );
                repaint();
            }
        }
    }

    private void deleteApronVertex(Point point)
    {
        if( apronModel != null )
        {
            VertexModel model = apronModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( model != null )
            {
                apronModel.removeVertexModel( model );
                apronModel.displayVertexModel( null );
                repaint();
            }
        }
    }

    private void createTrigger(Point point)
    {
        if( triggerModel == null )
        {
            triggerModel = new TriggerModel();
            airportModel.addTriggerModel( triggerModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) triggerModel );
            triggerModel.setCurrentlySelected( true );
            triggerModel.setModifying( true );
            creatingTrigger = true;
            modifyingTrigger = true;
        }
        if( creatingTrigger )
        {
            LatLonPoint latLonPoint;
            VertexModel model;

            startPoint = point;
            latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
            model = new VertexModel();
            model.setShouldNotify( false );
            model.setLatLon( latLonPoint );
            model.setShouldNotify( true );
            triggerModel.addVertexModel( model );
            selectDisplayMenu( 60 );
            repaint();
        }
    }

    private void startTriggerDrag(Point point)
    {
        if( modifyingTrigger && vertexModel == null && triggerModel != null )
        {
            vertexModel = triggerModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            triggerModel.displayVertexModel( vertexModel );
            repaint();
        }
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayTriggers() && checkItems( point, airportModel.getTriggerAL() ) )
        {
            if( triggerModel != null )
                triggerModel.setModifying( false );
            triggerModel = (TriggerModel) SelectedItem.getInstance().getBaseModel();
            triggerModel.setModifying( true );
            modifyingTrigger = true;
            repaint();
        }
        else if( vertexModel == null && !creatingTrigger && triggerModel != null )
        {
            triggerModel.setModifying( false );
            triggerModel = null;
            modifyingTrigger = false;
            repaint();
        }
    }

    private void dragTrigger(Point point)
    {
        if( vertexModel != null && SelectedItem.getInstance().isModelDraggable() )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            repaint();
        }
        else if( triggerModel != null && !creatingTrigger && SelectedItem.getInstance().isModelDraggable() )
        {
            LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );

            if( SelectedItem.getInstance().getBaseModel().moveTo( latLonPoint, (double) oldX - centerX, (double) oldY - centerY ) )
            {
                recreate = false;
                repaint();
            }
            oldX = point.x;
            oldY = point.y;
        }
    }

    private void finishTrigger(Point point)
    {
        startPoint = null;
        endPoint = null;
        creatingTrigger = false;
        repaint();
    }

    private void addTriggerVertex(Point point)
    {
        if( triggerModel != null )
        {
            int index = triggerModel.isBetweenVertices( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( index >= 0 )
            {
                VertexModel model = new VertexModel();

                model.setShouldNotify( false );
                model.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
                model.setShouldNotify( true );
                triggerModel.insertVertexModel( model, index + 1 );
                triggerModel.displayVertexModel( model );
                repaint();
            }
        }
    }

    private void deleteTriggerVertex(Point point)
    {
        if( triggerModel != null )
        {
            VertexModel model = triggerModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( model != null )
            {
                triggerModel.removeVertexModel( model );
                triggerModel.displayVertexModel( null );
                repaint();
            }
        }
    }

    private void insertRunway(Point point)
    {
        LatLonPoint latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
        RunwayModel model = new RunwayModel();

        model.setShouldNotify( false );
        model.setLatLon( latLonPoint );
        if( SettingsEngine.getInstance().getUseAirportAlt() )
        {
            model.setAlt( (float) airportModel.getAlt() );
            model.setAltMeasure( airportModel.getAltMeasure() );
        }
        model.setShouldNotify( true );
        airportModel.addRunwayModel( model );
        model.setCurrentlySelected( true );
        selectDisplayMenu( 32 );
        repaint();
    }

    private void createApronEdgeLight(Point point)
    {
        if( apronEdgeLightModel == null )
        {
            apronEdgeLightModel = new ApronEdgeLightModel();
            airportModel.addApronEdgeLightModel( apronEdgeLightModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) apronEdgeLightModel );
            apronEdgeLightModel.setCurrentlySelected( true );
            creatingApronEdgeLight = true;
            modifyingApronEdgeLight = true;
        }
        if( creatingApronEdgeLight )
        {
            LatLonPoint latLonPoint;
            VertexModel model;

            startPoint = point;
            latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
            model = new VertexModel();
            model.setShouldNotify( false );
            model.setLatLon( latLonPoint );
            model.setShouldNotify( true );
            apronEdgeLightModel.addVertexModel( model );
            selectDisplayMenu( 35 );
            repaint();
        }
    }

    private void startApronEdgeLightDrag(Point point)
    {
        if( modifyingApronEdgeLight && vertexModel == null && apronEdgeLightModel != null )
        {
            vertexModel = apronEdgeLightModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            apronEdgeLightModel.displayVertexModel( vertexModel );
            repaint();
        }
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayApronEL() && checkItems( point, airportModel.getApronEdgeLightAL() ) )
        {
            if( apronEdgeLightModel != null )
                apronEdgeLightModel.setModifying( false );
            apronEdgeLightModel = (ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel();
            apronEdgeLightModel.setModifying( true );
            modifyingApronEdgeLight = true;
            repaint();
            vertexModel = apronEdgeLightModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            apronEdgeLightModel.displayVertexModel( vertexModel );
        }
        else if( vertexModel == null && !creatingApronEdgeLight && apronEdgeLightModel != null )
        {
            apronEdgeLightModel.setModifying( false );
            apronEdgeLightModel = null;
            modifyingApronEdgeLight = false;
            repaint();
        }
    }

    private void dragApronEdgeLight(Point point)
    {
        if( vertexModel != null && SelectedItem.getInstance().isModelDraggable() )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            repaint();
        }
    }

    private void finishApronEdgeLight(Point point, boolean close)
    {
        if( close )
            apronEdgeLightModel.addVertexModel( (VertexModel) ((VertexModel) apronEdgeLightModel.getVertexAL().get( 0 )).clone() );
        apronEdgeLightModel.setModifying( false );
        startPoint = null;
        endPoint = null;
        creatingApronEdgeLight = false;
        repaint();
        repaint();
    }

    private void mergeApronEdgeLights(Point point, boolean shiftDown)
    {
        if( shiftDown && vertexModel != null )
        {
            if( SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel )
            {
                ArrayList vertexAL = ((ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel()).getVertexAL();

                if( vertexAL.size() != 0 )
                {
                    if( vertexAL.get( 0 ) == vertexModel || vertexAL.get( vertexAL.size() - 1 ) == vertexModel )
                    {
                        ArrayList arrayList = airportModel.getApronEdgeLightAL();
                        int i;

                        for( i = arrayList.size() - 1; i >= 0; --i )
                        {
                            if( arrayList.get( i ) != SelectedItem.getInstance().getBaseModel() )
                            {
                                VertexModel model1 = ((ApronEdgeLightModel) arrayList.get( i )).isWithinEndVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

                                if( model1 != null )
                                {
                                    airportModel.mergeApronEdgeLights( (ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel(), (ApronEdgeLightModel) arrayList.get( i ), vertexModel, model1 );
                                    repaint();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addApronEdgeLightVertex(Point point)
    {
        if( apronEdgeLightModel != null )
        {
            int index = apronEdgeLightModel.isBetweenVertices( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( index >= 0 )
            {
                VertexModel model = new VertexModel();

                model.setShouldNotify( false );
                model.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
                model.setShouldNotify( true );
                apronEdgeLightModel.insertVertexModel( model, index + 1 );
                apronEdgeLightModel.displayVertexModel( model );
                repaint();
            }
        }
    }

    private void deleteApronEdgeLightVertex(Point point)
    {
        if( apronEdgeLightModel != null )
        {
            VertexModel model = apronEdgeLightModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( model != null )
            {
                apronEdgeLightModel.removeVertexModel( model );
                apronEdgeLightModel.displayVertexModel( null );
                repaint();
            }
        }
    }

    private void createFence(Point point)
    {
        if( boundaryFenceModel == null )
        {
            boundaryFenceModel = new BoundaryFenceModel();
            airportModel.addBoundaryFenceModel( boundaryFenceModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) boundaryFenceModel );
            boundaryFenceModel.setCurrentlySelected( true );
            creatingFence = true;
            modifyingFence = true;
        }
        if( creatingFence )
        {
            LatLonPoint latLonPoint;
            VertexModel model;

            startPoint = point;
            latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
            model = new VertexModel();
            model.setShouldNotify( false );
            model.setLatLon( latLonPoint );
            model.setShouldNotify( true );
            boundaryFenceModel.addVertexModel( model );
            selectDisplayMenu( 47 );
            repaint();
        }
    }

    private void startFenceDrag(Point point)
    {
        if( modifyingFence && vertexModel == null && boundaryFenceModel != null )
        {
            vertexModel = boundaryFenceModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            boundaryFenceModel.displayVertexModel( vertexModel );
            repaint();
        }
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayBoundFence() && checkItems( point, airportModel.getBoundaryFenceAL() ) )
        {
            if( boundaryFenceModel != null )
                boundaryFenceModel.setModifying( false );
            boundaryFenceModel = (BoundaryFenceModel) SelectedItem.getInstance().getBaseModel();
            boundaryFenceModel.setModifying( true );
            modifyingFence = true;
            repaint();
        }
        else if( vertexModel == null && !creatingFence && boundaryFenceModel != null )
        {
            boundaryFenceModel.setModifying( false );
            boundaryFenceModel = null;
            modifyingFence = false;
            repaint();
        }
    }

    private void dragFence(Point point)
    {
        if( vertexModel != null && SelectedItem.getInstance().isModelDraggable() )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            repaint();
        }
    }

    private void finishFence(Point point, boolean close)
    {
        if( close )
            boundaryFenceModel.addVertexModel( (VertexModel) ((VertexModel) boundaryFenceModel.getVertexAL().get( 0 )).clone() );
        boundaryFenceModel.setModifying( false );
        startPoint = null;
        endPoint = null;
        creatingFence = false;
        repaint();
    }

    private void mergeFence(Point point, boolean shiftDown)
    {
        if( shiftDown && vertexModel != null )
        {
            if( SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel )
            {
                ArrayList vertexAL = ((BoundaryFenceModel) SelectedItem.getInstance().getBaseModel()).getVertexAL();

                if( vertexAL.size() != 0 )
                {
                    if( vertexAL.get( 0 ) == vertexModel || vertexAL.get( vertexAL.size() - 1 ) == vertexModel )
                    {
                        ArrayList arrayList = airportModel.getBoundaryFenceAL();
                        int i;

                        for( i = arrayList.size() - 1; i >= 0; --i )
                        {
                            if( arrayList.get( i ) != SelectedItem.getInstance().getBaseModel() )
                            {
                                VertexModel model1 = ((BoundaryFenceModel) arrayList.get( i )).isWithinEndVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

                                if( model1 != null )
                                {
                                    airportModel.mergeBoundaryFences( (BoundaryFenceModel) SelectedItem.getInstance().getBaseModel(), (BoundaryFenceModel) arrayList.get( i ), vertexModel, model1 );
                                    repaint();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addFenceVertex(Point point)
    {
        if( boundaryFenceModel != null )
        {
            int index = boundaryFenceModel.isBetweenVertices( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( index >= 0 )
            {
                VertexModel model = new VertexModel();

                model.setShouldNotify( false );
                model.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
                model.setShouldNotify( true );
                boundaryFenceModel.insertVertexModel( model, index + 1 );
                boundaryFenceModel.displayVertexModel( model );
                repaint();
            }
        }
    }

    private void deleteFenceVertex(Point point)
    {
        if( boundaryFenceModel != null )
        {
            VertexModel model = boundaryFenceModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( model != null )
            {
                boundaryFenceModel.removeVertexModel( model );
                boundaryFenceModel.displayVertexModel( null );
                repaint();
            }
        }
    }

    private void createBlastFence(Point point)
    {
        if( blastFenceModel == null )
        {
            blastFenceModel = new BlastFenceModel();
            airportModel.addBlastFenceModel( blastFenceModel );
            SelectedItem.getInstance().selectBaseModel( (BaseModel) blastFenceModel );
            blastFenceModel.setCurrentlySelected( true );
            creatingBlastFence = true;
            modifyingBlastFence = true;
        }
        if( creatingBlastFence )
        {
            LatLonPoint latLonPoint;
            VertexModel model;

            startPoint = point;
            latLonPoint = Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale );
            model = new VertexModel();
            model.setShouldNotify( false );
            model.setLatLon( latLonPoint );
            model.setShouldNotify( true );
            blastFenceModel.addVertexModel( model );
            selectDisplayMenu( 48 );
            repaint();
        }
    }

    private void startBlastFenceDrag(Point point)
    {
        if( modifyingBlastFence && vertexModel == null && blastFenceModel != null )
        {
            vertexModel = blastFenceModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );
            blastFenceModel.displayVertexModel( vertexModel );
            repaint();
        }
        if( vertexModel == null && SettingsEngine.getInstance().getDisplayBlastFence() && checkItems( point, airportModel.getBlastFenceAL() ) )
        {
            if( blastFenceModel != null )
                blastFenceModel.setModifying( false );
            blastFenceModel = (BlastFenceModel) SelectedItem.getInstance().getBaseModel();
            blastFenceModel.setModifying( true );
            modifyingBlastFence = true;
            repaint();
        }
        else
        {
            int ti;

            if( vertexModel == null )
                ti = 1;
            else
                ti = 0;
            if( (ti & ((!creatingBlastFence) ? 0x1 : 0x0)) != 0 && blastFenceModel != null )
            {
                blastFenceModel.setModifying( false );
                blastFenceModel = null;
                modifyingBlastFence = false;
                repaint();
            }
        }
    }

    private void dragBlastFence(Point point)
    {
        if( vertexModel != null && SelectedItem.getInstance().isModelDraggable() )
        {
            vertexModel.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
            recreate = false;
            repaint();
        }
    }

    private void finishBlastFence(Point point)
    {
        blastFenceModel.setModifying( false );
        startPoint = null;
        endPoint = null;
        creatingBlastFence = false;
        repaint();
    }

    private void mergeBlastFence(Point point, boolean shiftDown)
    {
        if( shiftDown && vertexModel != null )
        {
            if( SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel )
            {
                ArrayList vertexAL = ((BlastFenceModel) SelectedItem.getInstance().getBaseModel()).getVertexAL();

                if( vertexAL.size() != 0 )
                {
                    if( vertexAL.get( 0 ) == vertexModel || vertexAL.get( vertexAL.size() - 1 ) == vertexModel )
                    {
                        ArrayList arrayList = airportModel.getBlastFenceAL();
                        int i;

                        for( i = arrayList.size() - 1; i >= 0; --i )
                        {
                            if( arrayList.get( i ) != SelectedItem.getInstance().getBaseModel() )
                            {
                                VertexModel model1 = ((BlastFenceModel) arrayList.get( i )).isWithinEndVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

                                if( model1 != null )
                                {
                                    airportModel.mergeBlastFences( (BlastFenceModel) SelectedItem.getInstance().getBaseModel(), (BlastFenceModel) arrayList.get( i ), vertexModel, model1 );
                                    repaint();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void addBlastFenceVertex(Point point)
    {
        if( blastFenceModel != null )
        {
            int index = blastFenceModel.isBetweenVertices( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( index >= 0 )
            {
                VertexModel model = new VertexModel();

                model.setShouldNotify( false );
                model.setLatLon( Utilities.getLatLonForPixel( centerPoint.getLat(), centerPoint.getLon(), point.getX() - centerX, point.getY() - centerY, scale ) );
                model.setShouldNotify( true );
                blastFenceModel.insertVertexModel( model, index + 1 );
                blastFenceModel.displayVertexModel( model );
                repaint();
            }
        }
    }

    private void deleteBlastFenceVertex(Point point)
    {
        if( blastFenceModel != null )
        {
            VertexModel model = blastFenceModel.isWithinVertex( (int) ((double) point.x - centerX), (int) ((double) point.y - centerY) );

            if( model != null )
            {
                blastFenceModel.removeVertexModel( model );
                blastFenceModel.displayVertexModel( null );
                repaint();
            }
        }
    }

    public void resetBGImages()
    {
        recreateBGImage = true;
        recreateBGAllImage = true;
        selectDisplayMenu( 55 );
        repaint();
    }

    public void resetFlags()
    {
        if( apronModel != null )
            apronModel.setModifying( false );
        apronModel = null;
        creatingApron = false;
        modifyingApron = false;
        if( triggerModel != null )
            triggerModel.setModifying( false );
        triggerModel = null;
        creatingTrigger = false;
        modifyingTrigger = false;
        if( apronEdgeLightModel != null )
            apronEdgeLightModel.setModifying( false );
        apronEdgeLightModel = null;
        creatingApronEdgeLight = false;
        modifyingApronEdgeLight = false;
        if( boundaryFenceModel != null )
            boundaryFenceModel.setModifying( false );
        boundaryFenceModel = null;
        creatingFence = false;
        modifyingFence = false;
        if( blastFenceModel != null )
            blastFenceModel.setModifying( false );
        blastFenceModel = null;
        creatingBlastFence = false;
        modifyingBlastFence = false;
        exclusionModel = null;
        creatingExclude = false;
        modifyingExclude = false;
        startPoint = null;
        endPoint = null;
        vertexModel = null;
        if( SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel )
        {
            boundaryFenceModel = (BoundaryFenceModel) SelectedItem.getInstance().getBaseModel();
            modifyingFence = true;
        }
        else if( SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel )
        {
            blastFenceModel = (BlastFenceModel) SelectedItem.getInstance().getBaseModel();
            modifyingBlastFence = true;
        }
        else if( SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel )
        {
            apronEdgeLightModel = (ApronEdgeLightModel) SelectedItem.getInstance().getBaseModel();
            modifyingApronEdgeLight = true;
        }
        else if( SelectedItem.getInstance().getBaseModel() instanceof ExclusionModel )
        {
            exclusionModel = (ExclusionModel) SelectedItem.getInstance().getBaseModel();
            modifyingExclude = true;
        }
    }

    public void focusOn(BaseModel model)
    {
        java.awt.geom.Point2D.Float point;

        scale = 0.5F;
        SelectedItem.getInstance().selectBaseModel( model );
        model.setCurrentlySelected( true );
        if( model instanceof JetwayModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((JetwayModel) model).getLatLon().getLat(), ((JetwayModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof TaxiwayParkingModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayParkingModel) model).getLatLon().getLat(), ((TaxiwayParkingModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof TaxiwayPointModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayPointModel) model).getLatLon().getLat(), ((TaxiwayPointModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof TaxiwayPathModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((TaxiwayPathModel) model).getStartLat(), ((TaxiwayPathModel) model).getStartLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof VORModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((VORModel) model).getLatLon().getLat(), ((VORModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof NDBModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((NDBModel) model).getLatLon().getLat(), ((NDBModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof ILSModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((ILSModel) model).getLatLon().getLat(), ((ILSModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        else if( model instanceof RunwayModel )
        {
            point = Utilities.getPixelsBetweenPoints( centerPoint.getLat(), centerPoint.getLon(), ((RunwayModel) model).getLatLon().getLat(), ((RunwayModel) model).getLatLon().getLon(), scale );
            centerX = (double) getSize().width / 2.0 - (double) point.x;
            centerY = (double) getSize().height / 2.0 - (double) point.y;
        }
        recreate = true;
        repaint();
    }

    public void resetCenterPoint(LatLonPoint centerPoint)
    {
        this.centerPoint = centerPoint;
        centerSet = false;
        recreate = true;
        repaint();
    }

    public void componentHidden(ComponentEvent event)
    {
    }

    public void componentMoved(ComponentEvent event)
    {
    }

    public void componentResized(ComponentEvent event)
    {
        if( event.getSource() == this )
        {
            centerSet = false;
            repaint();
        }
    }

    public void componentShown(ComponentEvent event)
    {
    }

    public void mouseClicked(MouseEvent event)
    {
        if( event.getSource() == this && event.getButton() == 1 )
        {
            if( getCursor() == CursorEngine.getInstance().getApronCursor() )
            {
                if( event.getClickCount() == 1 )
                {
                    if( event.isShiftDown() )
                        addApronVertex( event.getPoint() );
                    else if( event.isControlDown() )
                        deleteApronVertex( event.getPoint() );
                    else
                        createApron( event.getPoint() );
                }
                else if( event.getClickCount() == 2 )
                    finishApron( event.getPoint() );
            }
            else if( getCursor() == CursorEngine.getInstance().getFuelCursor() )
            {
                if( event.getClickCount() == 1 )
                {
                    if( event.isShiftDown() )
                        addTriggerVertex( event.getPoint() );
                    else if( event.isControlDown() )
                        deleteTriggerVertex( event.getPoint() );
                    else
                        createTrigger( event.getPoint() );
                }
                else if( event.getClickCount() == 2 )
                    finishTrigger( event.getPoint() );
            }
            else if( getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor() )
            {
                if( event.getClickCount() == 1 )
                {
                    if( event.isShiftDown() )
                        addApronEdgeLightVertex( event.getPoint() );
                    else if( event.isControlDown() )
                        deleteApronEdgeLightVertex( event.getPoint() );
                    else
                        createApronEdgeLight( event.getPoint() );
                }
                else if( event.getClickCount() == 2 )
                    finishApronEdgeLight( event.getPoint(), event.isShiftDown() );
            }
            else if( getCursor() == CursorEngine.getInstance().getFenceCursor() )
            {
                if( event.getClickCount() == 1 )
                {
                    if( event.isShiftDown() )
                        addFenceVertex( event.getPoint() );
                    else if( event.isControlDown() )
                        deleteFenceVertex( event.getPoint() );
                    else
                        createFence( event.getPoint() );
                }
                else if( event.getClickCount() == 2 )
                    finishFence( event.getPoint(), event.isShiftDown() );
            }
            else if( getCursor() == CursorEngine.getInstance().getBlastFenceCursor() )
            {
                if( event.getClickCount() == 1 )
                {
                    if( event.isShiftDown() )
                        addBlastFenceVertex( event.getPoint() );
                    else if( event.isControlDown() )
                        deleteBlastFenceVertex( event.getPoint() );
                    else
                        createBlastFence( event.getPoint() );
                }
                else if( event.getClickCount() == 2 )
                    finishBlastFence( event.getPoint() );
            }
            else if( getCursor() == CursorEngine.getInstance().getZoomInCursor() )
                zoomFromWheel( event.getPoint(), -1 );
            else if( getCursor() == CursorEngine.getInstance().getZoomOutCursor() )
                zoomFromWheel( event.getPoint(), 1 );
        }
    }

    public void mousePressed(MouseEvent event)
    {
        if( event.getSource() == this )
        {
            oldX = event.getPoint().x;
            oldY = event.getPoint().y;
            if( event.getButton() == 2 )
                setCursor( CursorEngine.getInstance().getScrollCursor() );
            else if( event.getButton() == 1 )
            {
                if( getCursor() == CursorEngine.getInstance().getStartCursor() )
                    insertStartLocation( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getTowerCursor() )
                    insertTower( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getParkingCursor() )
                    insertParkingLocation( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getTaxiPointCursor() )
                    insertTaxiwayPoint( event.getPoint(), event.isShiftDown(), "NORMAL" );
                else if( getCursor() == CursorEngine.getInstance().getILSTaxiPointCursor() )
                    insertTaxiwayPoint( event.getPoint(), event.isShiftDown(), "ILS_HOLD_SHORT" );
                else if( getCursor() == CursorEngine.getInstance().getHSTaxiPointCursor() )
                    insertTaxiwayPoint( event.getPoint(), event.isShiftDown(), "HOLD_SHORT" );
                else if( getCursor() == CursorEngine.getInstance().getTaxiwaySignCursor() )
                    insertTaxiwaySign( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getHelipadCursor() )
                    insertHelipad( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getTaxiwayCursor() )
                {
                    startPoint = event.getPoint();
                    createTaxiway( event.getPoint(), event.isShiftDown() );
                }
                else if( getCursor() == CursorEngine.getInstance().getExcludeCursor() )
                    createExclude( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getRunwayCursor() )
                    insertRunway( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getApronCursor() )
                    startApronDrag( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getFuelCursor() )
                    startTriggerDrag( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor() )
                    startApronEdgeLightDrag( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getFenceCursor() )
                    startFenceDrag( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getBlastFenceCursor() )
                    startBlastFenceDrag( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getJetwayCursor() )
                    insertJetway( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getILSBeamCursor() )
                    insertILSBeam( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getMarkerCursor() )
                    insertMarker( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getVORCursor() )
                    insertVOR( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getNDBCursor() )
                    insertNDB( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getWindsockCursor() )
                    insertWindsock( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getSceneryCursor() )
                    insertScenery( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getDefaultCursor() )
                    findItem( event.getPoint() );
            }
            else if( event.isPopupTrigger() )
            {
                mouseClickPoint = event.getPoint();
                CursorPopupMenu.getInstance().updateMenu( (vertexModel != null) ? true : false );
                CursorPopupMenu.getInstance().show( (Component) this, event.getPoint().x, event.getPoint().y );
            }
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if( event.getSource() == this )
        {
            if( event.getButton() == 2 )
                setCursor( CursorEngine.getInstance().getCurrentCursor() );
            else if( event.getButton() == 1 )
            {
                if( getCursor() == CursorEngine.getInstance().getTaxiwayCursor() )
                    finishTaxiway( event.getPoint(), event.isShiftDown() );
                else if( getCursor() == CursorEngine.getInstance().getExcludeCursor() )
                    finishExclude( event.getPoint() );
                else if( getCursor() == CursorEngine.getInstance().getApronCursor() )
                    vertexModel = null;
                else if( getCursor() == CursorEngine.getInstance().getFuelCursor() )
                    vertexModel = null;
                else if( getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor() )
                {
                    mergeApronEdgeLights( event.getPoint(), event.isShiftDown() );
                    vertexModel = null;
                }
                else if( getCursor() == CursorEngine.getInstance().getFenceCursor() )
                {
                    mergeFence( event.getPoint(), event.isShiftDown() );
                    vertexModel = null;
                }
                else if( getCursor() == CursorEngine.getInstance().getBlastFenceCursor() )
                {
                    mergeBlastFence( event.getPoint(), event.isShiftDown() );
                    vertexModel = null;
                }
                else if( getCursor() == CursorEngine.getInstance().getDefaultCursor() && SelectedItem.getInstance().getBaseModel() instanceof TaxiwayPointModel )
                    ILSLineUpdates.getInstance().addAirportModel( airportModel );
                else if( SelectedItem.getInstance().getBaseModel() instanceof BoundaryFenceModel )
                    mergeFence( event.getPoint(), event.isShiftDown() );
                else if( SelectedItem.getInstance().getBaseModel() instanceof BlastFenceModel )
                    mergeBlastFence( event.getPoint(), event.isShiftDown() );
                else if( SelectedItem.getInstance().getBaseModel() instanceof ApronEdgeLightModel )
                    mergeApronEdgeLights( event.getPoint(), event.isShiftDown() );
                else if( SelectedItem.getInstance().getBaseModel() instanceof PlaneModel && FSXConnection.getInstance().isConnectedToFSX() && FSXConnection.getInstance().getFSXAutoFollow() )
                    FSXConnection.getInstance().movePlane( ((PlaneModel) airportModel.getPlaneAL().get( 0 )).getLatLon(), ((PlaneModel) airportModel.getPlaneAL().get( 0 )).getHeading() );
            }
            else if( event.isPopupTrigger() )
            {
                mouseClickPoint = event.getPoint();
                CursorPopupMenu.getInstance().updateMenu( (vertexModel != null) ? true : false );
                CursorPopupMenu.getInstance().show( (Component) this, event.getPoint().x, event.getPoint().y );
            }
        }
    }

    public void mouseEntered(MouseEvent event)
    {
    }

    public void mouseExited(MouseEvent event)
    {
    }

    public void mouseDragged(MouseEvent event)
    {
        if( event.getSource() == this )
        {
            if( getCursor() == CursorEngine.getInstance().getScrollCursor() )
                scrollAirport( event.getPoint().x, event.getPoint().y );
            else if( getCursor() == CursorEngine.getInstance().getTaxiwayCursor() )
            {
                endPoint = event.getPoint();
                checkTaxiwayPoint( event.getPoint() );
            }
            else if( getCursor() == CursorEngine.getInstance().getApronCursor() )
                dragApron( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getFuelCursor() )
                dragTrigger( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor() )
                dragApronEdgeLight( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getFenceCursor() )
                dragFence( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getBlastFenceCursor() )
                dragBlastFence( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getExcludeCursor() )
                dragExclude( event.getPoint() );
            else
                dragItem( event.getPoint() );
            displayLatLon( event.getPoint() );
        }
    }

    public void mouseMoved(MouseEvent event)
    {
        if( event.getSource() == this )
        {
            displayLatLon( event.getPoint() );
            if( getCursor() == CursorEngine.getInstance().getTaxiwayCursor() )
                checkTaxiwayPoint( event.getPoint() );
            else if( getCursor() == CursorEngine.getInstance().getApronCursor() )
            {
                if( startPoint != null )
                {
                    endPoint = event.getPoint();
                    repaint();
                }
            }
            else if( getCursor() == CursorEngine.getInstance().getFuelCursor() )
            {
                if( startPoint != null )
                {
                    endPoint = event.getPoint();
                    repaint();
                }
            }
            else if( getCursor() == CursorEngine.getInstance().getApronEdgeLightCursor() )
            {
                if( startPoint != null )
                {
                    endPoint = event.getPoint();
                    repaint();
                }
            }
            else if( getCursor() == CursorEngine.getInstance().getFenceCursor() )
            {
                if( startPoint != null )
                {
                    endPoint = event.getPoint();
                    repaint();
                }
            }
            else if( getCursor() == CursorEngine.getInstance().getBlastFenceCursor() && startPoint != null )
            {
                endPoint = event.getPoint();
                repaint();
            }
        }
    }

    public void mouseWheelMoved(MouseWheelEvent event)
    {
        if( event.getSource() == this )
            zoomFromWheel( null, event.getWheelRotation() );
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if( event.getSource() instanceof SettingsEngine )
        {
            if( event.getPropertyName().equals( "bgImagesBelow" ) )
                bgImagesBelow = ((Boolean) event.getNewValue()).booleanValue();
            else if( event.getPropertyName().equals( "displayLights" ) )
                displayLights = ((Boolean) event.getNewValue()).booleanValue();
            else if( event.getPropertyName().equals( "displayFPS" ) )
                displayFPS = ((Boolean) event.getNewValue()).booleanValue();
            else if( event.getPropertyName().equals( "doubleBuffer" ) )
                doubleBuffer = ((Boolean) event.getNewValue()).booleanValue();
        }
    }

    static TaxiwayPathModel access$000(AirportDisplay x0)
    {
        return x0.taxiwayPathModel;
    }

    static AirportModel access$100(AirportDisplay x0)
    {
        return x0.airportModel;
    }
}