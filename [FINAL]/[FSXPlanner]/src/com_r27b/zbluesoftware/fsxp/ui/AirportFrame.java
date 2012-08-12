package com.zbluesoftware.fsxp.ui;

import com.zbluesoftware.fsxp.engine.LogEngine;
import com.zbluesoftware.fsxp.menu.FSXPMenuBar;
import com.zbluesoftware.fsxp.model.AirportModel;
import com.zbluesoftware.fsxp.model.ApronEdgeLightModel;
import com.zbluesoftware.fsxp.model.ApronModel;
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
import com.zbluesoftware.fsxp.model.OpenAirportModel;
import com.zbluesoftware.fsxp.model.PlaneModel;
import com.zbluesoftware.fsxp.model.RunwayModel;
import com.zbluesoftware.fsxp.model.SceneryModel;
import com.zbluesoftware.fsxp.model.SelectedItem;
import com.zbluesoftware.fsxp.model.StartModel;
import com.zbluesoftware.fsxp.model.TaxiwayParkingModel;
import com.zbluesoftware.fsxp.model.TaxiwayPathModel;
import com.zbluesoftware.fsxp.model.TaxiwayPointModel;
import com.zbluesoftware.fsxp.model.TaxiwaySignModel;
import com.zbluesoftware.fsxp.model.TowerModel;
import com.zbluesoftware.fsxp.model.TriggerModel;
import com.zbluesoftware.fsxp.model.VORModel;
import com.zbluesoftware.fsxp.model.WindsockModel;
import com.zbluesoftware.fsxp.thread.ILSLineUpdates;
import com.zbluesoftware.fsxp.ui.data.AirportData;
import com.zbluesoftware.fsxp.ui.data.ApronData;
import com.zbluesoftware.fsxp.ui.data.ApronEdgeLightData;
import com.zbluesoftware.fsxp.ui.data.BlastFenceData;
import com.zbluesoftware.fsxp.ui.data.ExclusionData;
import com.zbluesoftware.fsxp.ui.data.FenceData;
import com.zbluesoftware.fsxp.ui.data.HelipadData;
import com.zbluesoftware.fsxp.ui.data.ILSData;
import com.zbluesoftware.fsxp.ui.data.JetwayData;
import com.zbluesoftware.fsxp.ui.data.MarkerData;
import com.zbluesoftware.fsxp.ui.data.NDBData;
import com.zbluesoftware.fsxp.ui.data.ObjectData;
import com.zbluesoftware.fsxp.ui.data.PlaneData;
import com.zbluesoftware.fsxp.ui.data.RunwayData;
import com.zbluesoftware.fsxp.ui.data.SceneryData;
import com.zbluesoftware.fsxp.ui.data.StartData;
import com.zbluesoftware.fsxp.ui.data.TaxiwayParkingData;
import com.zbluesoftware.fsxp.ui.data.TaxiwayPathData;
import com.zbluesoftware.fsxp.ui.data.TaxiwayPointData;
import com.zbluesoftware.fsxp.ui.data.TaxiwaySignData;
import com.zbluesoftware.fsxp.ui.data.TowerData;
import com.zbluesoftware.fsxp.ui.data.TriggerData;
import com.zbluesoftware.fsxp.ui.data.VORData;
import com.zbluesoftware.fsxp.ui.data.WindsockData;
import com.zbluesoftware.fsxp.ui.display.AirportDisplay;
import com.zbluesoftware.fsxp.util.LatLonPoint;
import com.zbluesoftware.fsxp.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

class AirportFrame$1 extends AbstractAction {

    AirportFrame$1(AirportFrame airportframe1)
    {
        this$0 = airportframe1;
    }

    AirportFrame this$0;     // final access specifier removed

    public void actionPerformed(ActionEvent e)
    {
        this$0.deleteSelectedItem();
    }
}


public class AirportFrame extends JInternalFrame implements MouseListener, MouseMotionListener, PropertyChangeListener {

    public AirportFrame(AirportModel airportModel)
    {
        super( "Airport", true, true, true, true );
        this.airportModel = airportModel;
        init();
        setFrameTitle();
    }

    private AirportModel airportModel;
    private AirportDisplay airportDisplay;
    private AirportData airportData;
    private ApronData apronData;
    private RunwayData runwayData;
    private StartData startData;
    private TaxiwayParkingData taxiwayParkingData;
    private TaxiwayPathData taxiwayPathData;
    private TaxiwayPointData taxiwayPointData;
    private TaxiwaySignData taxiwaySignData;
    private TowerData towerData;
    private HelipadData helipadData;
    private ApronEdgeLightData apronEdgeLightData;
    private FenceData fenceData;
    private BlastFenceData blastFenceData;
    private JetwayData jetwayData;
    private ILSData ilsData;
    private MarkerData markerData;
    private VORData vorData;
    private NDBData ndbData;
    private WindsockData windsockData;
    private ExclusionData exclusionData;
    private TriggerData triggerData;
    private SceneryData sceneryData;
    private PlaneData planeData;
    private JSplitPane splitPane;
    private JSplitPane dataSplitPane;
    private FloatingWindow airportWindow;
    private FloatingWindow objectWindow;
    private int airportPanelDisplay;
    private int objectPanelDisplay;
    private int airportMouseXPos;
    private int airportMouseYPos;
    private int objectMouseXPos;
    private int objectMouseYPos;
    private int airportDragMode;
    private int objectDragMode;
    private boolean inHeader;
    public static final int DOCKED = 1;
    public static final int FLOATING = 2;
    public static final int HIDDEN = 3;
    public static final int DM_NONE = 1;
    public static final int DM_MOVE = 2;
    public static final int DM_RESIZE = 3;
    public static final int DM_RESIZE_WIDTH = 4;
    public static final int DM_RESIZE_HEIGHT = 5;

    private void setFrameTitle()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append( airportModel.getName() ).append( " - " );
        buffer.append( airportModel.getIdent() );
        if( airportModel.getFileName().length() > 0 )
        {
            String fileName = airportModel.getFileName();

            if( fileName.length() > 50 )
                buffer.append( " [" ).append( fileName.substring( 0, 15 ) ).append( "..." ).append( fileName.substring( fileName.length() - 35 ) ).append( "]" );
            else
                buffer.append( " [" ).append( fileName ).append( "]" );
        }
        setTitle( buffer.toString() );
    }

    private void init()
    {
        Object deleteItem;

        airportPanelDisplay = 1;
        objectPanelDisplay = 1;
        airportDragMode = 1;
        objectDragMode = 1;
        airportMouseXPos = 0;
        airportMouseYPos = 0;
        objectMouseXPos = 0;
        objectMouseYPos = 0;
        setBackground( Utilities.LIGHT_BG_COLOR );
        airportData = new AirportData( airportModel );
        runwayData = new RunwayData();
        apronData = new ApronData();
        taxiwayParkingData = new TaxiwayParkingData();
        taxiwayPathData = new TaxiwayPathData();
        taxiwayPointData = new TaxiwayPointData();
        taxiwaySignData = new TaxiwaySignData();
        towerData = new TowerData();
        startData = new StartData();
        helipadData = new HelipadData();
        apronEdgeLightData = new ApronEdgeLightData();
        fenceData = new FenceData();
        blastFenceData = new BlastFenceData();
        jetwayData = new JetwayData();
        ilsData = new ILSData();
        markerData = new MarkerData();
        vorData = new VORData();
        ndbData = new NDBData();
        windsockData = new WindsockData();
        exclusionData = new ExclusionData();
        triggerData = new TriggerData();
        sceneryData = new SceneryData();
        planeData = new PlaneData();
        airportWindow = new FloatingWindow( Utilities.MAIN_FRAME );
        objectWindow = new FloatingWindow( Utilities.MAIN_FRAME );
        taxiwayPathData.updateNames( airportModel.getTaxiNameAL() );
        ilsData.updateRunways( airportModel.getRunwayAL() );
        dataSplitPane = new JSplitPane( 0, (Component) airportData, (Component) runwayData );
        airportDisplay = new AirportDisplay( airportModel );
        splitPane = new JSplitPane( 1, (Component) dataSplitPane, (Component) airportDisplay );
        splitPane.setDividerLocation( 300 );
        splitPane.setOneTouchExpandable( true );
        splitPane.setDividerSize( 10 );
        getContentPane().add( (Component) splitPane );
        setSize( getMinimumSize() );
        airportModel.addPropertyChangeListener( (PropertyChangeListener) taxiwayPathData );
        airportModel.addPropertyChangeListener( this );
        runwayData.addPropertyChangeListener( this );
        apronData.addPropertyChangeListener( this );
        taxiwayParkingData.addPropertyChangeListener( this );
        taxiwayPathData.addPropertyChangeListener( this );
        taxiwayPointData.addPropertyChangeListener( this );
        taxiwaySignData.addPropertyChangeListener( this );
        towerData.addPropertyChangeListener( this );
        startData.addPropertyChangeListener( this );
        helipadData.addPropertyChangeListener( this );
        apronEdgeLightData.addPropertyChangeListener( this );
        fenceData.addPropertyChangeListener( this );
        blastFenceData.addPropertyChangeListener( this );
        jetwayData.addPropertyChangeListener( this );
        ilsData.addPropertyChangeListener( this );
        markerData.addPropertyChangeListener( this );
        vorData.addPropertyChangeListener( this );
        ndbData.addPropertyChangeListener( this );
        windsockData.addPropertyChangeListener( this );
        exclusionData.addPropertyChangeListener( this );
        triggerData.addPropertyChangeListener( this );
        sceneryData.addPropertyChangeListener( this );
        planeData.addPropertyChangeListener( this );
        airportData.addMouseListener( this );
        runwayData.addMouseListener( this );
        apronData.addMouseListener( this );
        taxiwayParkingData.addMouseListener( this );
        taxiwayPathData.addMouseListener( this );
        taxiwayPointData.addMouseListener( this );
        taxiwaySignData.addMouseListener( this );
        towerData.addMouseListener( this );
        startData.addMouseListener( this );
        helipadData.addMouseListener( this );
        apronEdgeLightData.addMouseListener( this );
        fenceData.addMouseListener( this );
        blastFenceData.addMouseListener( this );
        jetwayData.addMouseListener( this );
        ilsData.addMouseListener( this );
        markerData.addMouseListener( this );
        vorData.addMouseListener( this );
        ndbData.addMouseListener( this );
        windsockData.addMouseListener( this );
        exclusionData.addMouseListener( this );
        triggerData.addMouseListener( this );
        sceneryData.addMouseListener( this );
        planeData.addMouseListener( this );
        airportData.addMouseMotionListener( this );
        runwayData.addMouseMotionListener( this );
        apronData.addMouseMotionListener( this );
        taxiwayParkingData.addMouseMotionListener( this );
        taxiwayPathData.addMouseMotionListener( this );
        taxiwayPointData.addMouseMotionListener( this );
        taxiwaySignData.addMouseMotionListener( this );
        towerData.addMouseMotionListener( this );
        startData.addMouseMotionListener( this );
        helipadData.addMouseMotionListener( this );
        apronEdgeLightData.addMouseMotionListener( this );
        fenceData.addMouseMotionListener( this );
        blastFenceData.addMouseMotionListener( this );
        jetwayData.addMouseMotionListener( this );
        ilsData.addMouseMotionListener( this );
        markerData.addMouseMotionListener( this );
        vorData.addMouseMotionListener( this );
        ndbData.addMouseMotionListener( this );
        windsockData.addMouseMotionListener( this );
        exclusionData.addMouseMotionListener( this );
        triggerData.addMouseMotionListener( this );
        sceneryData.addMouseMotionListener( this );
        planeData.addMouseMotionListener( this );
        deleteItem = new AirportFrame$1( this );
        airportDisplay.getInputMap().put( KeyStroke.getKeyStroke( 127, 0 ), "deleteItem" );
        airportDisplay.getActionMap().put( "deleteItem", (Action) deleteItem );
    }

    public Dimension getMinimumSize()
    {
        return new Dimension( 636, 470 );
    }

    public AirportModel getAirportModel()
    {
        return airportModel;
    }

    public AirportDisplay getAirportDisplay()
    {
        return airportDisplay;
    }

    public RunwayData getRunwayData()
    {
        return runwayData;
    }

    public ApronEdgeLightData getApronEdgeLightData()
    {
        return apronEdgeLightData;
    }

    public BlastFenceData getBlastFenceData()
    {
        return blastFenceData;
    }

    public FenceData getFenceData()
    {
        return fenceData;
    }

    public TaxiwayParkingData getTaxiwayParkingData()
    {
        return taxiwayParkingData;
    }

    public ILSData getILSData()
    {
        return ilsData;
    }

    public VORData getVORData()
    {
        return vorData;
    }

    public NDBData getNDBData()
    {
        return ndbData;
    }

    public void clearDataDisplays()
    {
        airportModel.removePropertyChangeListener( (PropertyChangeListener) taxiwayPathData );
        airportModel.removePropertyChangeListener( this );
        runwayData.updateDisplay( null );
        apronData.updateDisplay( null );
        taxiwayParkingData.updateDisplay( null );
        taxiwayPathData.updateDisplay( null );
        taxiwayPointData.updateDisplay( null );
        taxiwaySignData.updateDisplay( null );
        towerData.updateDisplay( null );
        startData.updateDisplay( null );
        helipadData.updateDisplay( null );
        apronEdgeLightData.updateDisplay( null );
        fenceData.updateDisplay( null );
        blastFenceData.updateDisplay( null );
        jetwayData.updateDisplay( null );
        ilsData.updateDisplay( null );
        markerData.updateDisplay( null );
        vorData.updateDisplay( null );
        ndbData.updateDisplay( null );
        windsockData.updateDisplay( null );
        exclusionData.updateDisplay( null );
        triggerData.updateDisplay( null );
        sceneryData.updateDisplay( null );
        planeData.updateDisplay( null );
    }

    public void deleteSelectedItem()
    {
        BaseModel baseModel = SelectedItem.getInstance().getBaseModel();

        if( baseModel instanceof RunwayModel )
        {
            airportModel.removeRunwayModel( (RunwayModel) baseModel );
            runwayData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof ApronModel )
        {
            airportModel.removeApronModel( (ApronModel) baseModel );
            apronData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TaxiwayParkingModel )
        {
            airportModel.removeTaxiwayParkingModel( (TaxiwayParkingModel) baseModel );
            taxiwayParkingData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TaxiwayPathModel )
        {
            airportModel.removeTaxiwayPathModel( (TaxiwayPathModel) baseModel );
            taxiwayPathData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TaxiwayPointModel )
        {
            airportModel.removeTaxiwayPointModel( (TaxiwayPointModel) baseModel );
            taxiwayPointData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TaxiwaySignModel )
        {
            airportModel.removeTaxiwaySignModel( (TaxiwaySignModel) baseModel );
            taxiwaySignData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TowerModel )
        {
            airportModel.removeTowerModel( (TowerModel) baseModel );
            towerData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof StartModel )
        {
            airportModel.removeStartModel( (StartModel) baseModel );
            startData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof HelipadModel )
        {
            airportModel.removeHelipadModel( (HelipadModel) baseModel );
            helipadData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof ApronEdgeLightModel )
        {
            airportModel.removeApronEdgeLightModel( (ApronEdgeLightModel) baseModel );
            apronEdgeLightData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof BoundaryFenceModel )
        {
            airportModel.removeBoundaryFenceModel( (BoundaryFenceModel) baseModel );
            fenceData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof BlastFenceModel )
        {
            airportModel.removeBlastFenceModel( (BlastFenceModel) baseModel );
            blastFenceData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof JetwayModel )
        {
            airportModel.removeJetwayModel( (JetwayModel) baseModel );
            jetwayData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof ILSModel )
        {
            airportModel.removeILSModel( (ILSModel) baseModel );
            ilsData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof MarkerModel )
        {
            airportModel.removeMarkerModel( (MarkerModel) baseModel );
            markerData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof VORModel )
        {
            airportModel.removeVORModel( (VORModel) baseModel );
            vorData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof NDBModel )
        {
            airportModel.removeNDBModel( (NDBModel) baseModel );
            ndbData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof WindsockModel )
        {
            airportModel.removeWindsockModel( (WindsockModel) baseModel );
            windsockData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof ExclusionModel )
        {
            airportModel.removeExclusionModel( (ExclusionModel) baseModel );
            exclusionData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof TriggerModel )
        {
            airportModel.removeTriggerModel( (TriggerModel) baseModel );
            triggerData.updateDisplay( null );
            repaint();
        }
        else if( baseModel instanceof SceneryModel )
        {
            airportModel.removeSceneryModel( (SceneryModel) baseModel );
            sceneryData.updateDisplay( null );
            repaint();
        }
    }

    public void pasteItem(BaseModel baseModel)
    {
        baseModel.setShouldNotify( false );
        if( baseModel instanceof RunwayModel )
        {
            ((RunwayModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addRunwayModel( (RunwayModel) baseModel );
            runwayData.updateDisplay( (BaseModel) (RunwayModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof ApronModel )
        {
            ((ApronModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addApronModel( (ApronModel) baseModel );
            apronData.updateDisplay( (BaseModel) (ApronModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof TaxiwayParkingModel )
        {
            ((TaxiwayParkingModel) baseModel).setIndex( airportModel.incrementTaxiIndex() );
            ((TaxiwayParkingModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addTaxiwayParkingModel( (TaxiwayParkingModel) baseModel );
            taxiwayParkingData.updateDisplay( (BaseModel) (TaxiwayParkingModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof TaxiwayPointModel )
        {
            ((TaxiwayPointModel) baseModel).setIndex( airportModel.incrementTaxiIndex() );
            ((TaxiwayPointModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addTaxiwayPointModel( (TaxiwayPointModel) baseModel );
            taxiwayPointData.updateDisplay( (BaseModel) (TaxiwayPointModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof TaxiwaySignModel )
        {
            ((TaxiwaySignModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addTaxiwaySignModel( (TaxiwaySignModel) baseModel );
            taxiwaySignData.updateDisplay( (BaseModel) (TaxiwaySignModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof TowerModel )
        {
            ((TowerModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addTowerModel( (TowerModel) baseModel );
            towerData.updateDisplay( (BaseModel) (TowerModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof StartModel )
        {
            ((StartModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addStartModel( (StartModel) baseModel );
            startData.updateDisplay( (BaseModel) (StartModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof HelipadModel )
        {
            ((HelipadModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addHelipadModel( (HelipadModel) baseModel );
            helipadData.updateDisplay( (BaseModel) (HelipadModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof ApronEdgeLightModel )
        {
            ((ApronEdgeLightModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addApronEdgeLightModel( (ApronEdgeLightModel) baseModel );
            apronEdgeLightData.updateDisplay( (BaseModel) (ApronEdgeLightModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof BoundaryFenceModel )
        {
            ((BoundaryFenceModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addBoundaryFenceModel( (BoundaryFenceModel) baseModel );
            fenceData.updateDisplay( (BaseModel) (BoundaryFenceModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof BlastFenceModel )
        {
            ((BlastFenceModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addBlastFenceModel( (BlastFenceModel) baseModel );
            blastFenceData.updateDisplay( (BaseModel) (BlastFenceModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof JetwayModel )
        {
            ((JetwayModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addJetwayModel( (JetwayModel) baseModel );
            jetwayData.updateDisplay( (BaseModel) (JetwayModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof ILSModel )
        {
            ((ILSModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            if( ((ILSModel) baseModel).getGlideSlopeModel() != null )
            {
                ((ILSModel) baseModel).getGlideSlopeModel().setShouldNotify( false );
                ((ILSModel) baseModel).getGlideSlopeModel().setLatLon( new LatLonPoint( airportDisplay.getMouseLatLon().getLat() + 0.001000000000000000020816682, airportDisplay.getMouseLatLon().getLon() + 0.001000000000000000020816682 ) );
                ((ILSModel) baseModel).getGlideSlopeModel().setShouldNotify( true );
            }
            if( ((ILSModel) baseModel).getDMEModel() != null )
            {
                ((ILSModel) baseModel).getDMEModel().setShouldNotify( false );
                ((ILSModel) baseModel).getDMEModel().setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
                ((ILSModel) baseModel).getDMEModel().setShouldNotify( true );
            }
            airportModel.addILSModel( (ILSModel) baseModel );
            ilsData.updateDisplay( (BaseModel) (ILSModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof MarkerModel )
        {
            ((MarkerModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addMarkerModel( (MarkerModel) baseModel );
            markerData.updateDisplay( (BaseModel) (MarkerModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof VORModel )
        {
            ((VORModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            if( ((VORModel) baseModel).getDMEModel() != null )
            {
                ((VORModel) baseModel).getDMEModel().setShouldNotify( false );
                ((VORModel) baseModel).getDMEModel().setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
                ((VORModel) baseModel).getDMEModel().setShouldNotify( true );
            }
            airportModel.addVORModel( (VORModel) baseModel );
            vorData.updateDisplay( (BaseModel) (VORModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof NDBModel )
        {
            ((NDBModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addNDBModel( (NDBModel) baseModel );
            ndbData.updateDisplay( (BaseModel) (NDBModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof WindsockModel )
        {
            ((WindsockModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addWindsockModel( (WindsockModel) baseModel );
            windsockData.updateDisplay( (BaseModel) (WindsockModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof ExclusionModel )
        {
            ((ExclusionModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addExclusionModel( (ExclusionModel) baseModel );
            exclusionData.updateDisplay( (BaseModel) (ExclusionModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof TriggerModel )
        {
            ((TriggerModel) baseModel).moveCenterTo( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addTriggerModel( (TriggerModel) baseModel );
            triggerData.updateDisplay( (BaseModel) (TriggerModel) baseModel );
            repaint();
        }
        else if( baseModel instanceof SceneryModel )
        {
            ((SceneryModel) baseModel).setLatLon( (LatLonPoint) airportDisplay.getMouseLatLon().clone() );
            airportModel.addSceneryModel( (SceneryModel) baseModel );
            sceneryData.updateDisplay( (BaseModel) (SceneryModel) baseModel );
            repaint();
        }
        baseModel.setShouldNotify( true );
    }

    private void updateDataPanel(PropertyChangeEvent event)
    {
        if( event.getNewValue() != null && event.getNewValue() instanceof BaseModel )
        {
            BaseModel baseModel = (BaseModel) event.getNewValue();

            if( baseModel instanceof ApronModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) apronData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) apronData );
                apronData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof RunwayModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) runwayData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) runwayData );
                runwayData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TaxiwayParkingModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) taxiwayParkingData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) taxiwayParkingData );
                taxiwayParkingData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TaxiwayPathModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) taxiwayPathData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) taxiwayPathData );
                taxiwayPathData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TaxiwayPointModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) taxiwayPointData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) taxiwayPointData );
                taxiwayPointData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TaxiwaySignModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) taxiwaySignData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) taxiwaySignData );
                taxiwaySignData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TowerModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) towerData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) towerData );
                towerData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof StartModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) startData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) startData );
                startData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof HelipadModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) helipadData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) helipadData );
                helipadData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof ApronEdgeLightModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) apronEdgeLightData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) apronEdgeLightData );
                apronEdgeLightData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof BoundaryFenceModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) fenceData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) fenceData );
                fenceData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof BlastFenceModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) blastFenceData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) blastFenceData );
                blastFenceData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof JetwayModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) jetwayData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) jetwayData );
                jetwayData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof ILSModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) ilsData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) ilsData );
                ilsData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof DMEModel )
            {
                if( ((DMEModel) baseModel).getParentModel() instanceof ILSModel )
                {
                    if( objectPanelDisplay == 1 )
                        dataSplitPane.setBottomComponent( (Component) ilsData );
                    else if( objectPanelDisplay == 2 )
                        updateObjectWindow( (ObjectData) ilsData );
                    ilsData.updateDisplay( (BaseModel) (ILSModel) ((DMEModel) baseModel).getParentModel() );
                }
                else if( ((DMEModel) baseModel).getParentModel() instanceof VORModel )
                {
                    if( objectPanelDisplay == 1 )
                        dataSplitPane.setBottomComponent( (Component) vorData );
                    else if( objectPanelDisplay == 2 )
                        updateObjectWindow( (ObjectData) vorData );
                    vorData.updateDisplay( (BaseModel) (VORModel) ((DMEModel) baseModel).getParentModel() );
                }
            }
            else if( baseModel instanceof GlideSlopeModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) ilsData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) ilsData );
                ilsData.updateDisplay( (BaseModel) (ILSModel) ((GlideSlopeModel) baseModel).getParentModel() );
            }
            else if( baseModel instanceof MarkerModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) markerData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) markerData );
                markerData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof VORModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) vorData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) vorData );
                vorData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof NDBModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) ndbData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) ndbData );
                ndbData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof WindsockModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) windsockData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) windsockData );
                windsockData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof ExclusionModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) exclusionData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) exclusionData );
                exclusionData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof TriggerModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) triggerData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) triggerData );
                triggerData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof SceneryModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) sceneryData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) sceneryData );
                sceneryData.updateDisplay( baseModel );
            }
            else if( baseModel instanceof PlaneModel )
            {
                if( objectPanelDisplay == 1 )
                    dataSplitPane.setBottomComponent( (Component) planeData );
                else if( objectPanelDisplay == 2 )
                    updateObjectWindow( (ObjectData) planeData );
                planeData.updateDisplay( baseModel );
            }
            else if( objectPanelDisplay == 1 )
                ((ObjectData) dataSplitPane.getBottomComponent()).updateDisplay( null );
            else if( objectPanelDisplay == 2 )
            {
                int i;

                for( i = objectWindow.getContentPane().getComponentCount() - 1; i >= 0; --i )
                {
                    if( objectWindow.getContentPane().getComponent( i ) instanceof ObjectData )
                        ((ObjectData) objectWindow.getContentPane().getComponent( i )).updateDisplay( null );
                }
            }
        }
        else if( event.getNewValue() == null )
        {
            if( objectPanelDisplay == 1 )
                ((ObjectData) dataSplitPane.getBottomComponent()).updateDisplay( null );
            else if( objectPanelDisplay == 2 )
            {
                int i;

                for( i = objectWindow.getContentPane().getComponentCount() - 1; i >= 0; --i )
                {
                    if( objectWindow.getContentPane().getComponent( i ) instanceof ObjectData )
                        ((ObjectData) objectWindow.getContentPane().getComponent( i )).updateDisplay( null );
                }
            }
        }
    }

    private void updateObjectWindow(ObjectData objectData)
    {
        objectWindow.getContentPane().removeAll();
        objectWindow.getContentPane().add( (Component) objectData );
        objectWindow.validate();
        objectWindow.repaint();
    }

    public void setAirportDataDisplay(boolean displayed)
    {
        if( displayed )
        {
            dataSplitPane.setTopComponent( (Component) airportData );
            airportPanelDisplay = 1;
            if( objectPanelDisplay != 1 )
                windowDocked();
        }
        else if( airportPanelDisplay == 1 )
        {
            dataSplitPane.setTopComponent( null );
            airportPanelDisplay = 3;
            if( objectPanelDisplay != 1 )
                bothWindowsHidden();
        }
        else if( airportPanelDisplay == 2 )
        {
            airportWindow.setVisible( false );
            airportPanelDisplay = 3;
        }
    }

    public void setObjectDataDisplay(boolean displayed)
    {
        if( displayed )
        {
            dataSplitPane.setBottomComponent( (Component) runwayData );
            objectPanelDisplay = 1;
            if( airportPanelDisplay != 1 )
                windowDocked();
        }
        else if( objectPanelDisplay == 1 )
        {
            dataSplitPane.setBottomComponent( null );
            objectPanelDisplay = 3;
            if( airportPanelDisplay != 1 )
                bothWindowsHidden();
        }
        else if( objectPanelDisplay == 2 )
        {
            objectWindow.setVisible( false );
            objectPanelDisplay = 3;
        }
    }

    private void bothWindowsFloating()
    {
        splitPane.setDividerLocation( 0 );
    }

    private void windowDocked()
    {
        splitPane.setDividerLocation( 300 );
    }

    private void bothWindowsHidden()
    {
        splitPane.setDividerLocation( 0 );
    }

    public void showHideAirportWindow()
    {
        if( airportPanelDisplay == 3 )
        {
            dataSplitPane.setTopComponent( (Component) airportData );
            airportPanelDisplay = 1;
            if( objectPanelDisplay != 1 )
                windowDocked();
        }
        else if( airportPanelDisplay == 2 )
        {
            airportWindow.setVisible( false );
            airportPanelDisplay = 3;
        }
        else
        {
            dataSplitPane.setTopComponent( null );
            airportPanelDisplay = 3;
            if( objectPanelDisplay != 1 )
                bothWindowsHidden();
        }
    }

    public void showHideObjectWindow(ObjectData objectData)
    {
        if( objectPanelDisplay == 3 )
        {
            dataSplitPane.setBottomComponent( (Component) objectData );
            objectPanelDisplay = 1;
            if( airportPanelDisplay != 1 )
                windowDocked();
        }
        else if( objectPanelDisplay == 2 )
        {
            objectWindow.setVisible( false );
            objectPanelDisplay = 3;
        }
        else
        {
            dataSplitPane.setBottomComponent( null );
            objectPanelDisplay = 3;
            if( airportPanelDisplay != 1 )
                bothWindowsHidden();
        }
    }

    private void displayAirportWindow()
    {
        Dimension windowSize = new Dimension( airportData.getSize().width, airportData.getPreferredSize().height );
        Point windowLocation = airportData.getLocationOnScreen();
        int dividerLocation = dataSplitPane.getDividerLocation();

        dataSplitPane.setTopComponent( null );
        dataSplitPane.setDividerLocation( dividerLocation );
        airportWindow.getContentPane().add( (Component) airportData );
        airportWindow.setSize( windowSize );
        airportWindow.setLocation( windowLocation );
        airportWindow.setVisible( true );
        airportPanelDisplay = 2;
        if( objectPanelDisplay != 1 )
            bothWindowsFloating();
    }

    private void resizeAirportWindow(MouseEvent event)
    {
        int resizeWidth = Math.max( event.getX(), 300 );
        int resizeHeight = Math.max( event.getY(), 280 );

        airportWindow.setSize( resizeWidth, resizeHeight );
        airportWindow.validate();
        airportDragMode = 3;
    }

    private void resizeAirportWindowHeight(MouseEvent event)
    {
        int resizeHeight = Math.max( event.getY(), 280 );

        airportWindow.setSize( airportWindow.getSize().width, resizeHeight );
        airportWindow.validate();
        airportDragMode = 5;
    }

    private void resizeAirportWindowWidth(MouseEvent event)
    {
        int resizeWidth = Math.max( event.getX(), 300 );

        airportWindow.setSize( resizeWidth, airportWindow.getSize().height );
        airportWindow.validate();
        airportDragMode = 4;
    }

    private void moveAirportWindow(MouseEvent event)
    {
        if( airportWindow.isVisible() )
        {
            Point location = airportWindow.getLocationOnScreen();

            airportWindow.setLocation( location.x + event.getX() - airportMouseXPos, location.y + event.getY() - airportMouseYPos );
            airportDragMode = 2;
        }
    }

    private void displayObjectWindow(ObjectData objectData)
    {
        Dimension windowSize = objectData.getSize();
        Point windowLocation = objectData.getLocationOnScreen();
        int dividerLocation = dataSplitPane.getDividerLocation();

        dataSplitPane.setBottomComponent( null );
        dataSplitPane.setDividerLocation( dividerLocation );
        objectWindow.getContentPane().add( (Component) objectData );
        objectWindow.setSize( windowSize );
        objectWindow.setLocation( windowLocation );
        objectWindow.setVisible( true );
        objectPanelDisplay = 2;
        if( airportPanelDisplay != 1 )
            bothWindowsFloating();
    }

    private void resizeObjectWindow(MouseEvent event)
    {
        int resizeWidth = Math.max( event.getX(), 300 );
        int resizeHeight = Math.max( event.getY(), 300 );

        objectWindow.setSize( resizeWidth, resizeHeight );
        objectWindow.validate();
        objectDragMode = 3;
    }

    private void resizeObjectWindowHeight(MouseEvent event)
    {
        int resizeHeight = Math.max( event.getY(), 300 );

        objectWindow.setSize( objectWindow.getSize().width, resizeHeight );
        objectWindow.validate();
        objectDragMode = 5;
    }

    private void resizeObjectWindowWidth(MouseEvent event)
    {
        int resizeWidth = Math.max( event.getX(), 300 );

        objectWindow.setSize( resizeWidth, objectWindow.getSize().height );
        objectWindow.validate();
        objectDragMode = 4;
    }

    private void moveObjectWindow(MouseEvent event)
    {
        if( objectWindow.isVisible() )
        {
            Point location = objectWindow.getLocationOnScreen();

            objectWindow.setLocation( location.x + event.getX() - objectMouseXPos, location.y + event.getY() - objectMouseYPos );
            objectDragMode = 2;
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

    public void mouseClicked(MouseEvent event)
    {
        if( event.getSource() == airportData )
        {
            if( airportData.getWindowBorder().isInCloseBox( event.getPoint() ) )
            {
                if( airportPanelDisplay == 2 )
                {
                    airportWindow.setVisible( false );
                    dataSplitPane.setTopComponent( (Component) airportData );
                    airportPanelDisplay = 1;
                    if( objectPanelDisplay != 1 )
                        windowDocked();
                }
                else
                {
                    dataSplitPane.setTopComponent( null );
                    airportPanelDisplay = 3;
                    if( objectPanelDisplay != 1 )
                        bothWindowsHidden();
                    ((JCheckBoxMenuItem) FSXPMenuBar.getInstance().getMenu( "View" ).getItem( 0 )).setState( false );
                }
            }
        }
        else if( event.getSource() instanceof ObjectData && ((ObjectData) event.getSource()).getWindowBorder().isInCloseBox( event.getPoint() ) )
        {
            if( objectPanelDisplay == 2 )
            {
                objectWindow.setVisible( false );
                dataSplitPane.setBottomComponent( (Component) (ObjectData) event.getSource() );
                objectPanelDisplay = 1;
                if( airportPanelDisplay != 1 )
                    windowDocked();
            }
            else
            {
                dataSplitPane.setBottomComponent( null );
                objectPanelDisplay = 3;
                if( airportPanelDisplay != 1 )
                    bothWindowsHidden();
                ((JCheckBoxMenuItem) FSXPMenuBar.getInstance().getMenu( "View" ).getItem( 1 )).setState( false );
            }
        }
    }

    public void mousePressed(MouseEvent event)
    {
        if( event.getSource() == airportData )
        {
            airportMouseXPos = event.getX();
            airportMouseYPos = event.getY();
            inHeader = airportData.getWindowBorder().isInHeader( event.getPoint() );
        }
        else if( event.getSource() instanceof ObjectData )
        {
            objectMouseXPos = event.getX();
            objectMouseYPos = event.getY();
            inHeader = ((ObjectData) event.getSource()).getWindowBorder().isInHeader( event.getPoint() );
        }
    }

    public void mouseReleased(MouseEvent event)
    {
        if( event.getSource() == airportData )
            airportDragMode = 1;
        else if( event.getSource() instanceof ObjectData )
            objectDragMode = 1;
    }

    public void mouseEntered(MouseEvent event)
    {
    }

    public void mouseExited(MouseEvent event)
    {
    }

    public void mouseDragged(MouseEvent event)
    {
        if( event.getSource() == airportData )
        {
            if( airportDragMode == 1 )
            {
                if( airportPanelDisplay == 1 )
                {
                    if( inHeader )
                        displayAirportWindow();
                }
                else if( airportData.getWindowBorder().isInResizeBox( event.getPoint() ) )
                    resizeAirportWindow( event );
                else if( airportData.getWindowBorder().isInBottomLine( event.getPoint() ) )
                    resizeAirportWindowHeight( event );
                else if( airportData.getWindowBorder().isInRightLine( event.getPoint() ) )
                    resizeAirportWindowWidth( event );
                else
                    moveAirportWindow( event );
            }
            else if( airportDragMode == 3 )
                resizeAirportWindow( event );
            else if( airportDragMode == 5 )
                resizeAirportWindowHeight( event );
            else if( airportDragMode == 4 )
                resizeAirportWindowWidth( event );
            else if( inHeader )
                moveAirportWindow( event );
        }
        else if( event.getSource() instanceof ObjectData )
        {
            if( objectDragMode == 1 )
            {
                if( objectPanelDisplay == 1 )
                {
                    if( inHeader )
                        displayObjectWindow( (ObjectData) event.getSource() );
                }
                else if( ((ObjectData) event.getSource()).getWindowBorder().isInResizeBox( event.getPoint() ) )
                    resizeObjectWindow( event );
                else if( ((ObjectData) event.getSource()).getWindowBorder().isInBottomLine( event.getPoint() ) )
                    resizeObjectWindowHeight( event );
                else if( ((ObjectData) event.getSource()).getWindowBorder().isInRightLine( event.getPoint() ) )
                    resizeObjectWindowWidth( event );
                else
                    moveObjectWindow( event );
            }
            else if( objectDragMode == 3 )
                resizeObjectWindow( event );
            else if( objectDragMode == 5 )
                resizeObjectWindowHeight( event );
            else if( objectDragMode == 4 )
                resizeObjectWindowWidth( event );
            else if( inHeader )
                moveObjectWindow( event );
        }
    }

    public void mouseMoved(MouseEvent event)
    {
        if( event.getSource() == airportData && airportPanelDisplay == 2 )
        {
            if( airportData.getWindowBorder().isInResizeBox( event.getPoint() ) )
            {
                if( airportData.getCursor().getType() != 5 )
                    airportData.setCursor( new Cursor( 5 ) );
            }
            else if( airportData.getWindowBorder().isInBottomLine( event.getPoint() ) )
            {
                if( airportData.getCursor().getType() != 9 )
                    airportData.setCursor( new Cursor( 9 ) );
            }
            else if( airportData.getWindowBorder().isInRightLine( event.getPoint() ) )
            {
                if( airportData.getCursor().getType() != 11 )
                    airportData.setCursor( new Cursor( 11 ) );
            }
            else if( airportData.getCursor().getType() != 0 )
                airportData.setCursor( new Cursor( 0 ) );
        }
        else if( event.getSource() instanceof ObjectData && objectPanelDisplay == 2 )
        {
            ObjectData objectData = (ObjectData) event.getSource();

            if( objectData.getWindowBorder().isInResizeBox( event.getPoint() ) )
            {
                if( objectData.getCursor().getType() != 5 )
                    objectData.setCursor( new Cursor( 5 ) );
            }
            else if( objectData.getWindowBorder().isInBottomLine( event.getPoint() ) )
            {
                if( objectData.getCursor().getType() != 9 )
                    objectData.setCursor( new Cursor( 9 ) );
            }
            else if( objectData.getWindowBorder().isInRightLine( event.getPoint() ) )
            {
                if( objectData.getCursor().getType() != 11 )
                    objectData.setCursor( new Cursor( 11 ) );
            }
            else if( objectData.getCursor().getType() != 0 )
                objectData.setCursor( new Cursor( 0 ) );
        }
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        if( event.getSource() instanceof SelectedItem )
            updateDataPanel( event );
        else if( event.getSource() == runwayData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeRunwayModel( (RunwayModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "addILS" ) )
            {
                airportModel.addILSModelToRunway( new ILSModel(), runwayData.getRunwayModel(), ((Boolean) event.getNewValue()).booleanValue() );
                selectDisplayMenu( 33 );
                repaint();
            }
        }
        else if( event.getSource() == apronData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeApronModel( (ApronModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == taxiwayParkingData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTaxiwayParkingModel( (TaxiwayParkingModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "addTrigger" ) )
            {
                airportModel.addTriggerToParking( (TaxiwayParkingModel) event.getNewValue() );
                selectDisplayMenu( 60 );
                repaint();
            }
            else if( event.getPropertyName().equals( "addStation" ) )
            {
                airportModel.addStationToParking( (TaxiwayParkingModel) event.getNewValue() );
                selectDisplayMenu( 61 );
                repaint();
            }
        }
        else if( event.getSource() == taxiwayPathData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
            {
                if( event.getPropertyName().equals( "update-width" ) || event.getPropertyName().equals( "update-widthMeasure" ) )
                    ILSLineUpdates.getInstance().addAirportModel( airportModel );
                airportDisplay.repaint();
            }
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTaxiwayPathModel( (TaxiwayPathModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "highlight" ) )
            {
                try
                {
                    airportModel.setHighlightedTWPath( Integer.parseInt( (String) event.getNewValue() ) );
                    repaint();
                }
                catch( NumberFormatException nfe )
                {
                    LogEngine.getInstance().log( nfe );
                }
            }
        }
        else if( event.getSource() == taxiwayPointData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
            {
                if( event.getPropertyName().equals( "update-type" ) )
                    ILSLineUpdates.getInstance().addAirportModel( airportModel );
                airportDisplay.repaint();
            }
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTaxiwayPointModel( (TaxiwayPointModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == taxiwaySignData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTaxiwaySignModel( (TaxiwaySignModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == towerData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTowerModel( (TowerModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == startData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeStartModel( (StartModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == helipadData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeHelipadModel( (HelipadModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == apronEdgeLightData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeApronEdgeLightModel( (ApronEdgeLightModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "cut" ) )
            {
                airportModel.cutApronEdgeLightModel( apronEdgeLightData.getApronEdgeLightModel(), ((Integer) event.getNewValue()).intValue() );
                repaint();
            }
        }
        else if( event.getSource() == fenceData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeBoundaryFenceModel( (BoundaryFenceModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "cut" ) )
            {
                airportModel.cutBoundaryFenceModel( fenceData.getBoundaryFenceModel(), ((Integer) event.getNewValue()).intValue() );
                repaint();
            }
        }
        else if( event.getSource() == blastFenceData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeBlastFenceModel( (BlastFenceModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "cut" ) )
            {
                airportModel.cutBlastFenceModel( blastFenceData.getBlastFenceModel(), ((Integer) event.getNewValue()).intValue() );
                repaint();
            }
        }
        else if( event.getSource() == jetwayData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeJetwayModel( (JetwayModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == ilsData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeILSModel( (ILSModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "addLocalizerArray" ) )
            {
                airportModel.addLocalizerToILS( (ILSModel) event.getNewValue() );
                selectDisplayMenu( 61 );
                repaint();
            }
        }
        else if( event.getSource() == markerData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeMarkerModel( (MarkerModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == vorData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeVORModel( (VORModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "addStation" ) )
            {
                airportModel.addStationToVOR( (VORModel) event.getNewValue() );
                selectDisplayMenu( 61 );
                repaint();
            }
        }
        else if( event.getSource() == ndbData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeNDBModel( (NDBModel) event.getNewValue() );
                repaint();
            }
            else if( event.getPropertyName().equals( "addAntenna" ) )
            {
                airportModel.addAntennaToNDB( (NDBModel) event.getNewValue() );
                selectDisplayMenu( 61 );
                repaint();
            }
        }
        else if( event.getSource() == windsockData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeWindsockModel( (WindsockModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == exclusionData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeExclusionModel( (ExclusionModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == triggerData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeTriggerModel( (TriggerModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == sceneryData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
                airportDisplay.repaint();
            else if( event.getPropertyName().equals( "delete" ) )
            {
                airportModel.removeSceneryModel( (SceneryModel) event.getNewValue() );
                repaint();
            }
        }
        else if( event.getSource() == planeData )
        {
            if( event.getPropertyName().startsWith( "update" ) )
            {
                if( event.getPropertyName().equals( "update-position" ) )
                    airportDisplay.movePlaneToFSX( false, false );
                else
                    airportDisplay.repaint();
            }
        }
        else if( event.getSource() == airportModel )
        {
            if( event.getPropertyName().equals( "latLon" ) )
                airportDisplay.resetCenterPoint( (LatLonPoint) event.getNewValue() );
            else if( event.getPropertyName().equals( "ident" ) )
            {
                String s;

                setFrameTitle();
                s = airportModel.getFileName();
                if( s.lastIndexOf( "\\" ) > 0 )
                    s = s.substring( s.lastIndexOf( "\\" ) + 1 );
                OpenAirportModel.getInstance().updateAirportName( s, (String) event.getOldValue(), s, (String) event.getNewValue() );
            }
        }
    }
}