package com.zbluesoftware.fsxp.menu;

import com.zbluesoftware.fsxp.engine.ColorsEngine;
import com.zbluesoftware.fsxp.engine.SettingsEngine;
import com.zbluesoftware.fsxp.graphics.ColorIcon;
import com.zbluesoftware.fsxp.graphics.IconFactory;
import com.zbluesoftware.fsxp.graphics.RibbonIcon;
import com.zbluesoftware.fsxp.graphics.RibbonIcon32;
import com.zbluesoftware.fsxp.ribbon.IconPopupGallery;
import com.zbluesoftware.fsxp.ribbon.ResizableIcon;
import com.zbluesoftware.fsxp.ribbon.ZPopupGallery;
import com.zbluesoftware.fsxp.ribbon.ZRibbon;
import com.zbluesoftware.fsxp.ribbon.ZRibbonBand;
import com.zbluesoftware.fsxp.ribbon.ZRibbonButton;
import com.zbluesoftware.fsxp.ribbon.ZRibbonTask;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.ToolTipManager;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.IconUIResource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import java.awt.*;
import javax.swing.Icon;

public class RibbonMenu extends ZRibbon {
    private static RibbonMenu ribbonMenu = null;

    public static RibbonMenu getInstance()
    {
        if( ribbonMenu == null )
            ribbonMenu = new RibbonMenu();
        return ribbonMenu;
    }






    public RibbonMenu initializeRibbon()
    {
/*
	Object[] defaults = { 
            "GalleryButton.expandIcon", new IconUIResource( (Icon) new RibbonMenu$1( this ) ), "ZControlPanel.border", null, "ZControlPanel.background", new ColorUIResource( new Color( 207, 222, 239 ) ), "ZControlPanel.foreground", new ColorUIResource( Color.red ), "ZRibbonBand.background", new ColorUIResource( new Color( 52, 91, 154 ) ), "ZRibbonBand.font", new Font( "Tahoma", 0, 30 ), "ZRibbonBand.foreground", new ColorUIResource( Color.red ), "ZRibbonBand.expandIcon", new IconUIResource( (Icon) new RibbonMenu$2( this ) ), "ZRibbon.font", new Font( "Tahoma", 0, 11 ), "ZRibbon.text", new ColorUIResource( Color.red ), "ToggleTabButton.disabledText", Color.blue
            };
*/
        Object defaults[] = {
            "GalleryButton.expandIcon", new IconUIResource(new Icon() {

                public int getIconHeight()
                {
                    return 7;
                }

                public int getIconWidth()
                {
                    return 7;
                }

                public void paintIcon(Component c, Graphics g, int x, int y)
                {
                    g.setColor(Color.black);
                    g.drawLine(x, y + 1, x + 3, y + 4);
                    g.drawLine(x + 3, y + 4, x + 6, y + 1);
                }

                final RibbonMenu this$0;

            
            {
                this$0 = RibbonMenu.this;
            }
            }
), "ZControlPanel.border", null, "ZControlPanel.background", new ColorUIResource(new Color(207, 222, 239)), "ZControlPanel.foreground", new ColorUIResource(Color.red), "ZRibbonBand.background", new ColorUIResource(new Color(52, 91, 154)), 
            "ZRibbonBand.font", new Font("Tahoma", 0, 30), "ZRibbonBand.foreground", new ColorUIResource(Color.red), "ZRibbonBand.expandIcon", new IconUIResource(new Icon() {

                public int getIconHeight()
                {
                    return 9;
                }

                public int getIconWidth()
                {
                    return 8;
                }

                public void paintIcon(Component c, Graphics g, int x, int y)
                {
                    g.setColor(Color.black);
                    g.drawLine(x + 1, y + 1, x + 6, y + 4);
                    g.drawLine(x + 1, y + 7, x + 6, y + 4);
                }

                final RibbonMenu this$0;

            
            {
                this$0 = RibbonMenu.this;
            }
            }
), "ZRibbon.font", new Font("Tahoma", 0, 11), "ZRibbon.text", new ColorUIResource(Color.red), 
            "ToggleTabButton.disabledText", Color.blue
        };
        UIManager.getDefaults().putDefaults( defaults );
        JFrame.setDefaultLookAndFeelDecorated( true );
        System.setProperty( "sun.awt.noerasebackground", "false" );
        configureRibbon();
        add( (Component) FSXPMenuBar.getInstance() );
        add( (Component) new JSeparator( 1 ) );
        ToolTipManager.sharedInstance().setDismissDelay( 60000 );
        return getInstance();
    }


    public void configureRibbon()
    {
        ZRibbonTask toolTask = new ZRibbonTask();
        ZRibbonTask optionTask = null;
        ZRibbonTask displayTask = null;
        ZRibbonTask compileTask = null;
        ZRibbonTask helpTask = null;

        toolTask.setGrouped( true );
        toolTask.addBand( getMovementBand() );
        toolTask.addBand( getRunwayBand() );
        toolTask.addBand( getTaxiwayBand() );
        toolTask.addBand( getParkingBand() );
        toolTask.addBand( getSceneryBand() );
        toolTask.addBand( getNavigationBand() );
        optionTask = new ZRibbonTask();
        optionTask.addBand( getClipboardBand() );
        optionTask.addBand( getHistoryBand() );
        optionTask.addBand( getFindBand() );
        optionTask.addBand( getNavBand() );
        optionTask.addBand( getUtilitiesBand() );
        optionTask.addBand( getSimConnectBand() );
        displayTask = new ZRibbonTask();
        displayTask.addBand( getDisplayRunwayBand() );
        displayTask.addBand( getDisplayTaxiwayBand() );
        displayTask.addBand( getDisplayParkingBand() );
        displayTask.addBand( getDisplaySceneryBand() );
        displayTask.addBand( getDisplayNavBand() );
        displayTask.addBand( getDisplayOtherBand() );
        compileTask = new ZRibbonTask();
        compileTask.addBand( getCompileBand() );
        compileTask.addBand( getPrepareBand() );
        compileTask.addBand( getCleanUpBand() );
        helpTask = new ZRibbonTask();
        helpTask.addBand( getKarmaBand() );
        helpTask.addBand( getBasicHelpBand() );
        helpTask.addBand( getDetailedHelpBand() );
        helpTask.addBand( getMoreHelpBand() );
        addTask( "Tools", toolTask );
        addTask( "Options", optionTask );
        addTask( "Display", displayTask );
        addTask( "Compile", compileTask );
        addTask( "Help", helpTask );
    }







    private ZRibbonBand getMovementBand()
    {
        ZRibbonBand movementBand = new ZRibbonBand( "Movement", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton cursorButton = new ZRibbonButton( "Cursor", IconFactory.getInstance().getIcon( "select" ).getImage() );
        ZRibbonButton zoomInButton = null;
        ZRibbonButton zoomOutButton = null;
        ZRibbonButton scrollerButton = null;

        cursorButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 115 ) ) );
        cursorButton.setToolTipTitle( "Cursor Tool" );
        cursorButton.setToolTipText( "Use the cursor to select any item in the airport." );
        cursorButton.setToolTipSize( new Dimension( 200, 75 ) );
        cursorButton.setSelected( true );
        movementBand.addGalleryButton( cursorButton, ZRibbonBand.RibbonElementPriority.TOP );
        zoomInButton = new ZRibbonButton( "Zoom In", IconFactory.getInstance().getIcon( "zoomIn" ).getImage() );
        zoomInButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 117 ) ) );
        zoomInButton.setToolTipTitle( "Zoom In Tool" );
        zoomInButton.setToolTipText( "Use this tool to increase the magnification of the display." );
        zoomInButton.addToolTipText( " When using this tool the airport will be centered and magnified at the current mouse location.\n\n" );
        zoomInButton.addToolTipText( "Alternative zoom method:\n" );
        zoomInButton.addToolTipText( "Roll the wheel of your mouse to increase or decrease the magnification." );
        zoomInButton.setToolTipSize( new Dimension( 225, 150 ) );
        movementBand.addGalleryButton( zoomInButton, ZRibbonBand.RibbonElementPriority.LOW );
        zoomOutButton = new ZRibbonButton( "Zoom Out", IconFactory.getInstance().getIcon( "zoomOut" ).getImage() );
        zoomOutButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 118 ) ) );
        zoomOutButton.setToolTipTitle( "Zoom Out Tool" );
        zoomOutButton.setToolTipText( "Use this tool to decrease the magnification of the display." );
        zoomOutButton.addToolTipText( " When using this tool the airport will be centered and zoomed out at the current mouse location.\n\n" );
        zoomOutButton.addToolTipText( "Alternative zoom method:\n" );
        zoomOutButton.addToolTipText( "Roll the wheel of your mouse to increase or decrease the magnification." );
        zoomOutButton.setToolTipSize( new Dimension( 225, 150 ) );
        movementBand.addGalleryButton( zoomOutButton, ZRibbonBand.RibbonElementPriority.LOW );
        scrollerButton = new ZRibbonButton( "Hand Scroller", IconFactory.getInstance().getIcon( "scroll" ).getImage() );
        scrollerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 116 ) ) );
        scrollerButton.setToolTipTitle( "Hand Scroller" );
        scrollerButton.setToolTipText( "Use this tool to scroll around the airport.\n\n" );
        scrollerButton.addToolTipText( "Alternative scrolling method:\n" );
        scrollerButton.addToolTipText( "Press and hold the wheel of your mouse, or other third button, while moving the mouse around" );
        scrollerButton.addToolTipText( " to scroll around the airport." );
        scrollerButton.setToolTipSize( new Dimension( 225, 125 ) );
        movementBand.addGalleryButton( scrollerButton, ZRibbonBand.RibbonElementPriority.LOW );
        return movementBand;
    }


    private ZRibbonBand getRunwayBand()
    {
        ZRibbonBand runwayBand = new ZRibbonBand( "Runways", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "runway16" ).getImage() ) );
        ZRibbonButton runwayButton = new ZRibbonButton( getRunwayOptions(), "Runway", (ResizableIcon) new RibbonIcon32( "runway", 32 ) );

        runwayButton.setToolTipTitle( "Insert A Runway" );
        runwayButton.setToolTipText( "Use this tool to insert a new runway into your airport." );
        runwayButton.addToolTipText( " The surface of the newly created runway will reflect the surface selected from the popup.\n\n" );
        runwayButton.addToolTipText( "You may change the type of surface at any point in the future from within the General tab" );
        runwayButton.addToolTipText( " of the runway properties display." );
        runwayButton.setToolTipSize( new Dimension( 225, 160 ) );
        runwayBand.addGalleryButton( runwayButton, ZRibbonBand.RibbonElementPriority.TOP );
        return runwayBand;
    }





    private ZPopupGallery getRunwayOptions()
    {
        Object gallery = new IconPopupGallery( 315, 400, 65 );
        IconPopupGallery.IconGroup group = ((IconPopupGallery) gallery).createNewGroup( "Runway Surfaces", 0 );

        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "ASPHALT" ) ), "Asphalt" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 147 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "BITUMINOUS" ) ), "Bituminous" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 148 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "BRICK" ) ), "Brick" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 149 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "CLAY" ) ), "Clay" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 150 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "CEMENT" ) ), "Cement" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 151 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "CONCRETE" ) ), "Concrete" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 152 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "CORAL" ) ), "Coral" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 153 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "DIRT" ) ), "Dirt" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 154 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "GRASS" ) ), "Grass" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 155 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "GRAVEL" ) ), "Gravel" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 156 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "ICE" ) ), "Ice" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 157 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "MACADAM" ) ), "Macadam" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 158 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "OIL_TREATED, PLANKS" ) ), "Oil Treated Planks" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 159 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "SAND" ) ), "Sand" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 160 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "SHALE" ) ), "Shale" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 161 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "SNOW" ) ), "Snow" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 162 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "STEEL_MATS" ) ), "Steel Mats" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 163 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "TARMAC" ) ), "Tarmac" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 164 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "UNKNOWN" ) ), "Unknown" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 165 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "runwayJPG" ).getImage(), ColorsEngine.getInstance().getSurfaceColor( "WATER" ) ), "Water" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 166 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getTaxiwayBand()
    {
        ZRibbonBand taxiwayBand = new ZRibbonBand( "Taxiways", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "taxiway16" ).getImage() ) );
        ZRibbonButton taxiwayButton = new ZRibbonButton( getTaxiwayOptions(), "Taxiway", (ResizableIcon) new RibbonIcon32( "taxiway", 32 ) );
        ZRibbonButton taxiwaySignButton = null;
        ZRibbonButton taxiwayNodeButton = null;
        ZRibbonButton ilsNodeButton = null;
        ZRibbonButton holdShortButton = null;

        taxiwayButton.setToolTipTitle( "Insert Taxiway" );
        taxiwayButton.setToolTipText( "Inserts the selected type of taxiway into the airport." );
        taxiwayButton.addToolTipText( " To add a taxiway click and hold the mouse button down for the beginning of the taxiway," );
        taxiwayButton.addToolTipText( " then drag to the end and release the mouse button.\n\n" );
        taxiwayButton.addToolTipText( "Adding to an existing node:\n" );
        taxiwayButton.addToolTipText( "Position the cursor over the node so that the node is highlighted and then press or release" );
        taxiwayButton.addToolTipText( " the mouse to start or finish the taxiway.\n\n" );
        taxiwayButton.addToolTipText( "Adding to the middle of a taxiway:\n" );
        taxiwayButton.addToolTipText( "Position the cursor over the existing taxiway and hold down the Shift key while clicking" );
        taxiwayButton.addToolTipText( " to insert the new taxiway." );
        taxiwayButton.setToolTipSize( new Dimension( 275, 240 ) );
        taxiwayBand.addGalleryButton( taxiwayButton, ZRibbonBand.RibbonElementPriority.TOP );
        taxiwaySignButton = new ZRibbonButton( "Taxiway Sign", (ResizableIcon) new RibbonIcon32( "taxiwaySign", 32 ) );
        taxiwaySignButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 137 ) ) );
        taxiwaySignButton.setToolTipTitle( "Insert Taxiway Sign" );
        taxiwaySignButton.setToolTipText( "Places a new taxiway sign into the airport.\n\n" );
        taxiwaySignButton.addToolTipText( "Once the sign is placed you can use the Taxiway Sign Wizard to easily create the text for the sign by" );
        taxiwaySignButton.addToolTipText( " selecting the sign and clicking the wizard button in the properties display." );
        taxiwaySignButton.setToolTipSize( new Dimension( 230, 140 ) );
        taxiwayBand.addGalleryButton( taxiwaySignButton, ZRibbonBand.RibbonElementPriority.TOP );
        taxiwayNodeButton = new ZRibbonButton( "Taxiway Node", (ResizableIcon) new RibbonIcon32( "taxiPoint", 16 ) );
        taxiwayNodeButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 134 ) ) );
        taxiwayNodeButton.setToolTipTitle( "Insert Taxiway Node" );
        taxiwayNodeButton.setToolTipText( "Places a new taxiway node at the cursor's location from which a taxiway can then be created.\n\n" );
        taxiwayNodeButton.addToolTipText( "Orientation:\n" );
        taxiwayNodeButton.addToolTipText( "The Forward/Backward orientation has no effect on default taxiway nodes." );
        taxiwayNodeButton.setToolTipSize( new Dimension( 230, 140 ) );
        taxiwayBand.addGalleryButton( taxiwayNodeButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        ilsNodeButton = new ZRibbonButton( "ILS Node", (ResizableIcon) new RibbonIcon32( "ilsTaxiPoint", 16 ) );
        ilsNodeButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 135 ) ) );
        ilsNodeButton.setToolTipTitle( "Insert ILS Taxiway Node" );
        ilsNodeButton.setToolTipText( "Places a new ILS taxiway node at the cursor's location from which a taxiway can then be created.\n\n" );
        ilsNodeButton.addToolTipText( "An ILS node tells the plane where to hold short of a runway during ILS conditions and is usually placed" );
        ilsNodeButton.addToolTipText( " a little further from the runway than a Hold Short node." );
        ilsNodeButton.setToolTipSize( new Dimension( 230, 155 ) );
        taxiwayBand.addGalleryButton( ilsNodeButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        holdShortButton = new ZRibbonButton( "Hold Short Node", (ResizableIcon) new RibbonIcon32( "hsTaxiPoint", 16 ) );
        holdShortButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 136 ) ) );
        holdShortButton.setToolTipTitle( "Insert Hold Short Taxiway Node" );
        holdShortButton.setToolTipText( "Places a new Hold Short taxiway node at the cursor's location from which a taxiway can then be created." );
        holdShortButton.addToolTipText( " A Hold Short node tells the plane where to hold short of a runway.\n\n" );
        holdShortButton.addToolTipText( "Orientation:\n" );
        holdShortButton.addToolTipText( "The dashed yellow lines should always face the runway. In case they do not, the Forward/ Backward" );
        holdShortButton.addToolTipText( " orientation option allows you to orient them correctly by flipping them 180 degrees." );
        holdShortButton.setToolTipSize( new Dimension( 250, 190 ) );
        taxiwayBand.addGalleryButton( holdShortButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return taxiwayBand;
    }

    private ZPopupGallery getTaxiwayOptions()
    {
        Object gallery = new IconPopupGallery( 290, 400, 50 );
        IconPopupGallery.IconGroup group = ((IconPopupGallery) gallery).createNewGroup( "Taxiway Types", 0 );

        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.green ), "Runway" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 140 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.yellow ), "Parking" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 141 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), new Color( 153, 0, 0 ) ), "Taxi" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 142 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.lightGray ), "Path" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 143 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.darkGray ), "Closed" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 144 ) ) );
        group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.blue ), "Vehicle" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 145 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getParkingBand()
    {
        ZRibbonBand parkingBand = new ZRibbonBand( "Parking", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking16" ).getImage() ) );
        ZRibbonButton parkingButton = new ZRibbonButton( getParkingOptions(), "Parking Location", (ResizableIcon) new RibbonIcon32( "parking", 32 ) );
        ZRibbonButton jetwayButton = null;
        ZRibbonButton startButton = null;
        ZRibbonButton triggerButton = null;

        parkingButton.setToolTipTitle( "Insert A Parking Location" );
        parkingButton.setToolTipText( "Places a new parking location at your airport. Chose from gates, ramps, miltary and other options." );
        parkingButton.setToolTipSize( new Dimension( 250, 80 ) );
        parkingBand.addGalleryButton( parkingButton, ZRibbonBand.RibbonElementPriority.TOP );
        jetwayButton = new ZRibbonButton( "Jetway", IconFactory.getInstance().getIcon( "jetway" ).getImage() );
        jetwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 177 ) ) );
        jetwayButton.setToolTipTitle( "Insert A Jetway" );
        jetwayButton.setToolTipText( "Places a new Jetway at your airport.  Jetways are usually associated with a Gate parking location" );
        jetwayButton.addToolTipText( " and should have the same Gate Name and Number as their associated parking location." );
        jetwayButton.setToolTipSize( new Dimension( 250, 105 ) );
        parkingBand.addGalleryButton( jetwayButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        startButton = new ZRibbonButton( "Start Location", (ResizableIcon) new RibbonIcon32( "startPoint", 16 ) );
        startButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 132 ) ) );
        startButton.setToolTipTitle( "Insert A Start Location" );
        startButton.setToolTipText( "Start Locations allow you to begin a new flight at a location other than a parking spot." );
        startButton.addToolTipText( " These additional locations usually include the ends of a runway and helipads." );
        startButton.setToolTipSize( new Dimension( 250, 90 ) );
        parkingBand.addGalleryButton( startButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        triggerButton = new ZRibbonButton( "Fuel Trigger", (ResizableIcon) new RibbonIcon32( "fuel", 16 ) );
        triggerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 184 ) ) );
        triggerButton.setToolTipTitle( "Draw A Fuel Trigger" );
        triggerButton.setToolTipText( "A Fuel Trigger is an invisible polygon that allows your plane to be refueled and repaired when" );
        triggerButton.addToolTipText( " stopped within it. Fuel Triggers are usually placed at ramp parking locations which have been designated" );
        triggerButton.addToolTipText( " as type 'Fuel'.  In addition a fuel scenery object will often accompany the trigger.\n\n" );
        triggerButton.addToolTipText( "Alternate way to add fuel triggers:\n" );
        triggerButton.addToolTipText( "Selecting a parking location and clicking the Fuel Trigger icon in the properties display will create" );
        triggerButton.addToolTipText( " a fuel trigger around that parking location.  Clicking the Fuel Scenery object button will place a" );
        triggerButton.addToolTipText( " fuel station scenery object next to that parking location to help complete the refueling location." );
        triggerButton.setToolTipSize( new Dimension( 275, 230 ) );
        parkingBand.addGalleryButton( triggerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return parkingBand;
    }

    private ZPopupGallery getParkingOptions()
    {
        Object gallery = new IconPopupGallery( 265, 400, 70 );
        IconPopupGallery.IconGroup gates = ((IconPopupGallery) gallery).createNewGroup( "Gates", 0 );
        IconPopupGallery.IconGroup ramps = null;
        IconPopupGallery.IconGroup military = null;
        IconPopupGallery.IconGroup other = null;

        gates.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGateLarge32" ).getImage() ), "Heavy" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 121 ) ) );
        gates.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGateMedium32" ).getImage() ), "Medium" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 122 ) ) );
        gates.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGateSmall32" ).getImage() ), "Small" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 123 ) ) );
        ramps = ((IconPopupGallery) gallery).createNewGroup( "Ramps", 1 );
        ramps.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGeneralAviation32" ).getImage() ), "General Aviation" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 125 ) ) );
        ramps.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGALarge32" ).getImage() ), "GA Large" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 126 ) ) );
        ramps.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGAMedium32" ).getImage() ), "GA Medium" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 127 ) ) );
        ramps.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pGASmall32" ).getImage() ), "GA Small" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 128 ) ) );
        ramps.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pCargo32" ).getImage() ), "Cargo" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 124 ) ) );
        military = ((IconPopupGallery) gallery).createNewGroup( "Military", 2 );
        military.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pMilitaryCargo32" ).getImage() ), "Military Cargo" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 129 ) ) );
        military.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pMilitary32" ).getImage() ), "Military Combat" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 130 ) ) );
        other = ((IconPopupGallery) gallery).createNewGroup( "Other", 3 );
        other.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pVehicle32" ).getImage() ), "Vehicle" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 131 ) ) );
        other.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pDock32" ).getImage() ), "Dock" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 119 ) ) );
        other.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "pFuel32" ).getImage() ), "Fuel" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 120 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getSceneryBand()
    {
        ZRibbonBand sceneryBand = new ZRibbonBand( "Scenery", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "scenery16" ).getImage() ) );
        ZRibbonButton sceneryButton = new ZRibbonButton( "Scenery", IconFactory.getInstance().getIcon( "scenery32" ).getImage() );
        ZRibbonButton apronButton = null;
        ZRibbonButton edgeLightButton = null;
        ZRibbonButton boundaryButton = null;
        ZRibbonButton blastButton = null;
        ZRibbonButton helipadButton = null;
        ZRibbonButton towerButton = null;
        ZRibbonButton excludeButton = null;

        sceneryButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 185 ) ) );
        sceneryButton.setToolTipTitle( "Add A Scenery Object" );
        sceneryButton.setToolTipText( "Select from one of several included scenery objects, or choose the Custom Object and" );
        sceneryButton.addToolTipText( " enter the GUID for any object you wish." );
        sceneryButton.setToolTipSize( new Dimension( 250, 80 ) );
        sceneryBand.addGalleryButton( sceneryButton, ZRibbonBand.RibbonElementPriority.TOP );
        apronButton = new ZRibbonButton( "Apron", IconFactory.getInstance().getIcon( "apron32" ).getImage() );
        apronButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 146 ) ) );
        apronButton.setToolTipTitle( "Create An Apron" );
        apronButton.setToolTipText( "This tool allows you to draw Apron polygons. Click the mouse at each desired vertex location." );
        apronButton.addToolTipText( " When placing the last vertex, double click the mouse the finish the apron.\n\n" );
        apronButton.addToolTipText( "Inserting Vertices\n" );
        apronButton.addToolTipText( "Hold down the Shift key while clicking at the desired location to add a new vertex into an existing apron.\n\n" );
        apronButton.addToolTipText( "Deleting Vertices\n" );
        apronButton.addToolTipText( "Hold down the Control key while clicking on the vertex you wish to delete." );
        apronButton.setToolTipSize( new Dimension( 275, 200 ) );
        sceneryBand.addGalleryButton( apronButton, ZRibbonBand.RibbonElementPriority.TOP );
        edgeLightButton = new ZRibbonButton( "Edge Light", IconFactory.getInstance().getIcon( "apronEdgeLight" ).getImage() );
        edgeLightButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 138 ) ) );
        edgeLightButton.setToolTipTitle( "Create An Apron Edge Light" );
        edgeLightButton.setToolTipText( "This tool allows you to draw Apron Edge Light strings. Click the mouse at each desired vertex location." );
        edgeLightButton.addToolTipText( " When placing the last vertex, double click the mouse the finish the string.\n\n" );
        edgeLightButton.addToolTipText( "For more information on how to join two strings; how to cut a string in two; or how to add or" );
        edgeLightButton.addToolTipText( " delete vertices please see the manual.\n\n" );
        edgeLightButton.addToolTipText( "NOTE: Edge lights appear at 200 foot intervals in FSX.  So if your string is less than 200 feet long" );
        edgeLightButton.addToolTipText( " no edge lights will be displayed." );
        edgeLightButton.setToolTipSize( new Dimension( 275, 205 ) );
        sceneryBand.addGalleryButton( edgeLightButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        boundaryButton = new ZRibbonButton( getBoundaryFenceOptions(), "Boundary", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ) );
        boundaryButton.setToolTipTitle( "Create A Boundary Fence" );
        boundaryButton.setToolTipText( "This tool allows you to draw small, medium or large Boundary Fences, which usually surround an airport." );
        boundaryButton.addToolTipText( " Click the mouse at each desired vertex location. When placing the last vertex, double click the mouse" );
        boundaryButton.addToolTipText( " the finish the fence.\n\n" );
        boundaryButton.addToolTipText( "For more information on how to join two fences; how to cut a fence in two; or how to add or" );
        boundaryButton.addToolTipText( " delete vertices please see the manual.\n\n" );
        boundaryButton.setToolTipSize( new Dimension( 275, 165 ) );
        sceneryBand.addGalleryButton( boundaryButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        blastButton = new ZRibbonButton( getBlastFenceOptions(), "Blast", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ) );
        blastButton.setToolTipTitle( "Create A Blast Fence" );
        blastButton.setToolTipText( "This tool allows you to draw small, medium or large Blast Fences. Click the mouse at each desired" );
        blastButton.addToolTipText( " vertex location.When placing the last vertex, double click the mouse the finish the fence.\n\n" );
        blastButton.addToolTipText( "For more information on how to join two fences; how to cut a fence in two; or how to add or" );
        blastButton.addToolTipText( " delete vertices please see the manual.\n\n" );
        blastButton.setToolTipSize( new Dimension( 250, 165 ) );
        sceneryBand.addGalleryButton( blastButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        helipadButton = new ZRibbonButton( "Helipad", IconFactory.getInstance().getIcon( "helipad" ).getImage() );
        helipadButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 139 ) ) );
        helipadButton.setToolTipTitle( "Create A Helipad" );
        helipadButton.setToolTipText( "This tool allows you to place helipads at your airport. Note that you will need to place a Start Location on top" );
        helipadButton.addToolTipText( " of the helipad if you want to be able to start a flight from the helipad." );
        helipadButton.setToolTipSize( new Dimension( 200, 110 ) );
        sceneryBand.addGalleryButton( helipadButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        towerButton = new ZRibbonButton( "Tower", IconFactory.getInstance().getIcon( "tower16" ).getImage() );
        towerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 133 ) ) );
        towerButton.setToolTipTitle( "Create A Control Tower" );
        towerButton.setToolTipText( "This tool allows you to place a Control Tower at your airport. Note that this is the tower viewpoint and" );
        towerButton.addToolTipText( " scenery object only. If you want to have ATC at your airport then you will need to create the appropriate" );
        towerButton.addToolTipText( " Communication Frequencies." );
        towerButton.setToolTipSize( new Dimension( 220, 120 ) );
        sceneryBand.addGalleryButton( towerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        excludeButton = new ZRibbonButton( "Exclude", IconFactory.getInstance().getIcon( "exclude16" ).getImage() );
        excludeButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 183 ) ) );
        excludeButton.setToolTipTitle( "Create An Exclusion Rectangle" );
        excludeButton.setToolTipText( "An Exclusion Rectangle allows you to remove default objects from your airport. It is best to have" );
        excludeButton.addToolTipText( " multiple rectangles which exclude specific items, instead of one large rectangle excluding all items." );
        excludeButton.setToolTipSize( new Dimension( 220, 110 ) );
        sceneryBand.addGalleryButton( excludeButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return sceneryBand;
    }

    private ZPopupGallery getBoundaryFenceOptions()
    {
        Object gallery = new IconPopupGallery( 140, 400, 50 );
        IconPopupGallery.IconGroup group = ((IconPopupGallery) gallery).createNewGroup( "Boundary Fences", 0 );

        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ), "Tiny" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 167 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ), "Small" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 168 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ), "Medium" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 169 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ), "Large" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 170 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "fence" ).getImage() ), "Huge" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 171 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZPopupGallery getBlastFenceOptions()
    {
        Object gallery = new IconPopupGallery( 140, 400, 50 );
        IconPopupGallery.IconGroup group = ((IconPopupGallery) gallery).createNewGroup( "Blast Fences", 0 );

        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ), "Tiny" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 172 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ), "Small" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 173 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ), "Medium" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 174 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ), "Large" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 175 ) ) );
        group.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "blastFence" ).getImage() ), "Huge" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 176 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getNavigationBand()
    {
        ZRibbonBand navigationBand = new ZRibbonBand( "Navigation", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton ilsButton = new ZRibbonButton( "ILS Feather", IconFactory.getInstance().getIcon( "ilsBeam" ).getImage() );
        ZRibbonButton vorButton = null;
        ZRibbonButton ndbButton = null;
        ZRibbonButton markerButton = null;
        ZRibbonButton windsockButton = null;

        ilsButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 178 ) ) );
        ilsButton.setToolTipTitle( "Create An ILS Beacon" );
        ilsButton.setToolTipText( "This tool allows you to insert an ILS beacon, along with its associated" );
        ilsButton.addToolTipText( " DME and Glide Slope, if they are present.\n\n" );
        ilsButton.addToolTipText( "Note: To include a scenery item representation of the ILS location," );
        ilsButton.addToolTipText( " click the Scenery button in the ILS properties display." );
        ilsButton.setToolTipSize( new Dimension( 220, 150 ) );
        navigationBand.addGalleryButton( ilsButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        vorButton = new ZRibbonButton( "VOR", IconFactory.getInstance().getIcon( "vor" ).getImage() );
        vorButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 180 ) ) );
        vorButton.setToolTipTitle( "Create A VOR" );
        vorButton.setToolTipText( "This tool allows you to insert a VOR, along with its associated" );
        vorButton.addToolTipText( " DME, if present.\n\n" );
        vorButton.addToolTipText( "Note: To include a scenery item representation of the VOR location," );
        vorButton.addToolTipText( " click the Scenery button in the VOR properties display." );
        vorButton.setToolTipSize( new Dimension( 230, 135 ) );
        navigationBand.addGalleryButton( vorButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        ndbButton = new ZRibbonButton( "NDB", IconFactory.getInstance().getIcon( "ndb" ).getImage() );
        ndbButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 181 ) ) );
        ndbButton.setToolTipTitle( "Create An NDB" );
        ndbButton.setToolTipText( "This tool allows you to insert an NDB at the airport.\n\n" );
        ndbButton.addToolTipText( "Note: To include a scenery item representation of the NDB location," );
        ndbButton.addToolTipText( " click the Scenery button in the NDB properties display." );
        ndbButton.setToolTipSize( new Dimension( 220, 135 ) );
        navigationBand.addGalleryButton( ndbButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        markerButton = new ZRibbonButton( "Marker", IconFactory.getInstance().getIcon( "marker" ).getImage() );
        markerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 179 ) ) );
        markerButton.setToolTipTitle( "Create A Marker" );
        markerButton.setToolTipText( "This tool allows you to insert a marker at the airport.\n\n" );
        markerButton.addToolTipText( "Markers are used to help guide the plane down to the runway. They can be either an inner," );
        markerButton.addToolTipText( " middle or outer type marker." );
        markerButton.setToolTipSize( new Dimension( 220, 120 ) );
        navigationBand.addGalleryButton( markerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        windsockButton = new ZRibbonButton( "Windsock", IconFactory.getInstance().getIcon( "windsock" ).getImage() );
        windsockButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 182 ) ) );
        windsockButton.setToolTipTitle( "Create A Windsock" );
        windsockButton.setToolTipText( "This tool allows you to insert a windsock at the airport.\n\n" );
        windsockButton.setToolTipSize( new Dimension( 220, 65 ) );
        navigationBand.addGalleryButton( windsockButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return navigationBand;
    }

    private ZRibbonBand getClipboardBand()
    {
        ZRibbonBand clipboardBand = new ZRibbonBand( "Clipboard", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton cutButton = new ZRibbonButton( "Cut", (ResizableIcon) new RibbonIcon32( "cut", 32 ) );
        ZRibbonButton copyButton = null;
        ZRibbonButton pasteButton = null;

        cutButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 21 ) ) );
        cutButton.setToolTipTitle( "Cut (Ctrl-X)" );
        cutButton.setToolTipText( "Cut the item from the airport and put it in the clipboard." );
        cutButton.setToolTipSize( new Dimension( 200, 65 ) );
        clipboardBand.addGalleryButton( cutButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        copyButton = new ZRibbonButton( "Copy", (ResizableIcon) new RibbonIcon32( "copy", 32 ) );
        copyButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 22 ) ) );
        copyButton.setToolTipTitle( "Copy (Ctrl-C)" );
        copyButton.setToolTipText( "Copies the item from the airport and put it in the clipboard." );
        copyButton.setToolTipSize( new Dimension( 200, 65 ) );
        clipboardBand.addGalleryButton( copyButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        pasteButton = new ZRibbonButton( "Paste", (ResizableIcon) new RibbonIcon32( "paste", 32 ) );
        pasteButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 23 ) ) );
        pasteButton.setToolTipTitle( "Paste (Ctrl-V)" );
        pasteButton.setToolTipText( "Pastes the contents of the clipboard into the airport at the cursor's current location." );
        pasteButton.setToolTipSize( new Dimension( 200, 80 ) );
        clipboardBand.addGalleryButton( pasteButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return clipboardBand;
    }

    private ZRibbonBand getHistoryBand()
    {
        ZRibbonBand historyBand = new ZRibbonBand( "History", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton undoButton = new ZRibbonButton( "Undo", (ResizableIcon) new RibbonIcon32( "undo", 32 ) );
        ZRibbonButton redoButton = null;
        ZRibbonButton historyButton = null;

        undoButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 18 ) ) );
        undoButton.setToolTipTitle( "Undo (Ctrl-Z)" );
        undoButton.setToolTipText( "Undoes the last performed action. Not all actions are undoable." );
        undoButton.setToolTipSize( new Dimension( 200, 65 ) );
        historyBand.addGalleryButton( undoButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        redoButton = new ZRibbonButton( "Redo", (ResizableIcon) new RibbonIcon32( "redo", 32 ) );
        redoButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 19 ) ) );
        redoButton.setToolTipTitle( "Redo (Shift-Ctrl-Z)" );
        redoButton.setToolTipText( "Redoes the last undone action." );
        redoButton.setToolTipSize( new Dimension( 200, 50 ) );
        historyBand.addGalleryButton( redoButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        historyButton = new ZRibbonButton( "Display History", (ResizableIcon) new RibbonIcon32( "history", 32 ) );
        historyButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 20 ) ) );
        historyButton.setToolTipTitle( "Display History Panel (Ctrl-H)" );
        historyButton.setToolTipText( "Displays the History Panel, which shows details on the last 30 changes made" );
        historyButton.addToolTipText( " that can be undone. To change the number of items stored in the history dialog, use the" );
        historyButton.addToolTipText( " Display tab of the Preferences dialog." );
        historyButton.setToolTipSize( new Dimension( 220, 105 ) );
        historyBand.addGalleryButton( historyButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return historyBand;
    }

    private ZRibbonBand getFindBand()
    {
        ZRibbonBand findBand = new ZRibbonBand( "Find", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton findButton = new ZRibbonButton( "Find Taxiway Point", (ResizableIcon) new RibbonIcon32( "find", 32 ) );

        findButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 25 ) ) );
        findButton.setToolTipTitle( "Find Taxiway Point" );
        findButton.setToolTipText( "Displays a dialog allowing you to search for a taxiway point or parking" );
        findButton.addToolTipText( " location by its internal index number. This is the number which is used in the compiler" );
        findButton.addToolTipText( " output and the XML code." );
        findButton.setToolTipSize( new Dimension( 220, 105 ) );
        findBand.addGalleryButton( findButton, ZRibbonBand.RibbonElementPriority.TOP );
        return findBand;
    }

    private ZRibbonBand getNavBand()
    {
        ZRibbonBand navBand = new ZRibbonBand( "Navigation", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton comButton = new ZRibbonButton( "Edit Com Frequencies", (ResizableIcon) new RibbonIcon32( "com", 32 ) );
        ZRibbonButton approachButton = null;
        ZRibbonButton lightingButton = null;

        comButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 71 ) ) );
        comButton.setToolTipTitle( "Edit Com Frequencies" );
        comButton.setToolTipText( "Allows you to edit the communication frequencies of the airport." );
        comButton.addToolTipText( " This includes Tower, ATIS, Approach, Ground, etc frequencies." );
        comButton.setToolTipSize( new Dimension( 220, 90 ) );
        navBand.addGalleryButton( comButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        approachButton = new ZRibbonButton( "Edit Approaches", (ResizableIcon) new RibbonIcon32( "approach", 32 ) );
        approachButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 72 ) ) );
        approachButton.setToolTipTitle( "Edit Approaches" );
        approachButton.setToolTipText( "Allows you to edit the approach information for the airport." );
        approachButton.addToolTipText( " This includes the type of approach, as well as the data on each leg of the approach." );
        approachButton.setToolTipSize( new Dimension( 220, 90 ) );
        navBand.addGalleryButton( approachButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        lightingButton = new ZRibbonButton( "Display Lighting", (ResizableIcon) new RibbonIcon32( "lighting", 32 ) );
        lightingButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 73 ) ) );
        lightingButton.setToolTipTitle( "Display Lighting" );
        lightingButton.setToolTipText( "This option displays the airport at night allowing you to view" );
        lightingButton.addToolTipText( " the lighting for the aprons, taxiways and runways." );
        lightingButton.setToolTipSize( new Dimension( 220, 80 ) );
        navBand.addGalleryButton( lightingButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return navBand;
    }

    private ZRibbonBand getUtilitiesBand()
    {
        ZRibbonBand utilityBand = new ZRibbonBand( "Utilities", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton centerButton = new ZRibbonButton( "Return To Center", (ResizableIcon) new RibbonIcon32( "center", 32 ) );
        ZRibbonButton backgroundButton = null;
        ZRibbonButton lockingButton = null;
        ZRibbonButton countsButton = null;
        ZRibbonButton namesButton = null;
        ZRibbonButton servicesButton = null;
        ZRibbonButton moveButton = null;

        centerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 24 ) ) );
        centerButton.setToolTipTitle( "Return To Center (Ctrl-R)" );
        centerButton.setToolTipText( "Centers the display over the airport reference point and restores" );
        centerButton.addToolTipText( " the default magnification and rotation." );
        centerButton.setToolTipSize( new Dimension( 220, 80 ) );
        utilityBand.addGalleryButton( centerButton, ZRibbonBand.RibbonElementPriority.TOP );
        backgroundButton = new ZRibbonButton( "Background Images", (ResizableIcon) new RibbonIcon32( "background", 32 ) );
        backgroundButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 26 ) ) );
        backgroundButton.setToolTipTitle( "Manage Background Images" );
        backgroundButton.setToolTipText( "Displays the dialog which allows you to add, remove and position" );
        backgroundButton.addToolTipText( "background images within FSX Planner." );
        backgroundButton.setToolTipSize( new Dimension( 220, 80 ) );
        utilityBand.addGalleryButton( backgroundButton, ZRibbonBand.RibbonElementPriority.TOP );
        lockingButton = new ZRibbonButton( "Object Locking", (ResizableIcon) new RibbonIcon32( "padlock", 32 ) );
        lockingButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 27 ) ) );
        lockingButton.setToolTipTitle( "Object Locking" );
        lockingButton.setToolTipText( "Displays the dialog which allows you modify the locked status" );
        lockingButton.addToolTipText( " of objects within FSX Planner.  A locked object cannot be moved.\n\n" );
        lockingButton.addToolTipText( "Alternative Way To Lock Objects:\n" );
        lockingButton.addToolTipText( "Click the padlock icon in the properties dialog to lock and unlock the object." );
        lockingButton.setToolTipSize( new Dimension( 220, 150 ) );
        utilityBand.addGalleryButton( lockingButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        countsButton = new ZRibbonButton( "Display Counts", (ResizableIcon) new RibbonIcon32( "counts", 32 ) );
        countsButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 28 ) ) );
        countsButton.setToolTipTitle( "Display Object Counts" );
        countsButton.setToolTipText( "Shows, in real time, the total number of each type of component (runways, aprons, etc) used within your airport." );
        countsButton.setToolTipSize( new Dimension( 220, 80 ) );
        utilityBand.addGalleryButton( countsButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        namesButton = new ZRibbonButton( "Taxiway names", (ResizableIcon) new RibbonIcon32( "taxiwayNames", 32 ) );
        namesButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 29 ) ) );
        namesButton.setToolTipTitle( "Edit Taxiway Names" );
        namesButton.setToolTipText( "Allows you to add, remove and edit taxiway names. These are usually single" );
        namesButton.addToolTipText( " letters, such as A, G, F, etc.\n\n" );
        namesButton.addToolTipText( "Alternative Way To Edit Taxiway Names:\n" );
        namesButton.addToolTipText( "Clicking on the 'add names' button next to the Name drop down in the Taxiway properties dialog" );
        namesButton.addToolTipText( " will allow you to modify taxiway names." );
        namesButton.setToolTipSize( new Dimension( 250, 150 ) );
        utilityBand.addGalleryButton( namesButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        servicesButton = new ZRibbonButton( "Airport Services", (ResizableIcon) new RibbonIcon32( "services", 32 ) );
        servicesButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 30 ) ) );
        servicesButton.setToolTipTitle( "Edit Airport Services" );
        servicesButton.setToolTipText( "This option allows you to edit the fuel services that are available at" );
        servicesButton.addToolTipText( " this airport.  This information will appear in the airplane's GPS system." );
        servicesButton.setToolTipSize( new Dimension( 200, 95 ) );
        utilityBand.addGalleryButton( servicesButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        moveButton = new ZRibbonButton( "Move Airport", (ResizableIcon) new RibbonIcon32( "moveAirport", 32 ) );
        moveButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 31 ) ) );
        moveButton.setToolTipTitle( "Move Airport" );
        moveButton.setToolTipText( "This option allows you to quickly reposition the airport by specifying" );
        moveButton.addToolTipText( " a new location for the Airport Reference Point.  All physical objects will then be" );
        moveButton.addToolTipText( " moved to corrispond with the new point.\n\n" );
        moveButton.addToolTipText( "Note: This does not update approach data." );
        moveButton.setToolTipSize( new Dimension( 250, 140 ) );
        utilityBand.addGalleryButton( moveButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return utilityBand;
    }

    private ZRibbonBand getSimConnectBand()
    {
        ZRibbonBand simConnectBand = new ZRibbonBand( "Sim Connect", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton connectionButton = new ZRibbonButton( "FSX Connection Status", (ResizableIcon) new RibbonIcon32( "connectionStatus", 32 ) );
        ZRibbonButton planeMakeButton = null;
        ZRibbonButton showPlaneButton = null;
        ZRibbonButton followButton = null;

        connectionButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 74 ) ) );
        connectionButton.setToolTipTitle( "FSX Connection Status" );
        connectionButton.setToolTipText( "This option displays the FSX Connection dialog, allowing you to connect to FSX through various methods." );
        connectionButton.setToolTipSize( new Dimension( 200, 90 ) );
        simConnectBand.addGalleryButton( connectionButton, ZRibbonBand.RibbonElementPriority.TOP );
        planeMakeButton = new ZRibbonButton( getPlaneMakeOptions(), "Plane Make", (ResizableIcon) new RibbonIcon32( "planeMake", 16 ) );
        planeMakeButton.setToolTipTitle( "Select Plane Make" );
        planeMakeButton.setToolTipText( "This option allows you to select the make of plane or helicopter displayed" );
        planeMakeButton.addToolTipText( " by the Plane Icon. The Plane Icon is used to show the position of the FSX plane, as well" );
        planeMakeButton.addToolTipText( " as checking for appropriate taxiway and runway widths.\n\n" );
        planeMakeButton.addToolTipText( "All default FSX planes and helicopters are included as options.  You can also specify" );
        planeMakeButton.addToolTipText( " a custom plane or helicopter size." );
        planeMakeButton.setToolTipSize( new Dimension( 250, 165 ) );
        simConnectBand.addGalleryButton( planeMakeButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        showPlaneButton = new ZRibbonButton( "Show Plane", (ResizableIcon) new RibbonIcon32( "showPlane", 16 ) );
        showPlaneButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 78 ) ) );
        showPlaneButton.setToolTipTitle( "Show Plane" );
        showPlaneButton.setToolTipText( "This option shows or hides the Plane Icon." );
        showPlaneButton.setToolTipSize( new Dimension( 160, 65 ) );
        simConnectBand.addGalleryButton( showPlaneButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        followButton = new ZRibbonButton( getAutoFollowOptions(), "Auto Follow", (ResizableIcon) new RibbonIcon32( "autoFollow", 16 ) );
        followButton.setToolTipTitle( "Auto Follow" );
        followButton.setToolTipText( "This option allows you to select the current auto follow option.\n\n" );
        followButton.addToolTipText( "When connected to FSX you can have the Plane Icon automatically follow the plane within FSX." );
        followButton.addToolTipText( "Alternatively you can have the plane within FSX automatically follow the Plane Icon." );
        followButton.setToolTipSize( new Dimension( 250, 135 ) );
        simConnectBand.addGalleryButton( followButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return simConnectBand;
    }

    private ZPopupGallery getPlaneMakeOptions()
    {
        Object gallery = new IconPopupGallery( 275, 500, 70 );
        IconPopupGallery.IconGroup jets = ((IconPopupGallery) gallery).createNewGroup( "Jets", 0 );
        IconPopupGallery.IconGroup props = null;
        IconPopupGallery.IconGroup helicopters = null;
        IconPopupGallery.IconGroup other = null;

        jets.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "jet32" ).getImage() ), "Airbus A321" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 81 ) ) );
        jets.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "jet32" ).getImage() ), "Boeing 737" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 82 ) ) );
        jets.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "jet74732" ).getImage() ), "Boeing 747" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 83 ) ) );
        jets.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "jet32" ).getImage() ), "Bombardier CRJ700" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 84 ) ) );
        jets.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "learJet32" ).getImage() ), "Bombardier LearJet" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 85 ) ) );
        props = ((IconPopupGallery) gallery).createNewGroup( "Props", 1 );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "baron5832" ).getImage() ), "Beechcraft Baron 58" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 86 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "kingAir35032" ).getImage() ), "Beechcraft Kingair 350" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 87 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "cessna32" ).getImage() ), "Cessna 172" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 88 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "cessna20832" ).getImage() ), "Cessna C208B" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 89 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "beaver32" ).getImage() ), "de Havilland Beaver" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 90 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "dc332" ).getImage() ), "Douglas DC-3" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 91 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "extra32" ).getImage() ), "Extra 300S" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 92 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "goose32" ).getImage() ), "Grumman Goose" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 93 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "maule32" ).getImage() ), "Maule Orion" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 94 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "mooney32" ).getImage() ), "Mooney Bravo" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 95 ) ) );
        props.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "piper32" ).getImage() ), "Piper Cub" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 96 ) ) );
        helicopters = ((IconPopupGallery) gallery).createNewGroup( "Helicopters", 2 );
        helicopters.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "bell32" ).getImage() ), "Bell Jet Ranger" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 97 ) ) );
        helicopters.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "robinson32" ).getImage() ), "Robinson R22" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 98 ) ) );
        other = ((IconPopupGallery) gallery).createNewGroup( "Other", 3 );
        other.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "trike32" ).getImage() ), "AirCreation Trike" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 99 ) ) );
        other.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "flugzeubau32" ).getImage() ), "DG Flugzeubau" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 100 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZPopupGallery getAutoFollowOptions()
    {
        Object gallery = new IconPopupGallery( 126, 300, 70 );
        IconPopupGallery.IconGroup follow = ((IconPopupGallery) gallery).createNewGroup( "Options", 0 );

        follow.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "followCursor32" ).getImage() ), "Follow Cursor" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 79 ) ) );
        follow.addIcon( (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "followPlane32" ).getImage() ), "Follow FSX Plane" ).setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 80 ) ) );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getDisplayRunwayBand()
    {
        ZRibbonBand runwaysBand = new ZRibbonBand( "Show Runways", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton runwayButton = new ZRibbonButton( "Runways", (ResizableIcon) new RibbonIcon32( "runway", 32 ) );

        runwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 32 ) ) );
        runwayButton.setToolTipTitle( "Display Runways" );
        runwayButton.setToolTipText( "Shows or hides airport runways." );
        runwayButton.setToolTipSize( new Dimension( 180, 50 ) );
        runwayButton.setDisplayX( true );
        runwayButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayRunway()) ? true : false );
        runwaysBand.addGalleryButton( runwayButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return runwaysBand;
    }

    private ZRibbonBand getDisplayTaxiwayBand()
    {
        ZRibbonBand taxiwayBand = new ZRibbonBand( "Show Taxiways", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton pathButton = new ZRibbonButton( getTaxiwayTypeOptions(), "Taxiway Paths", (ResizableIcon) new RibbonIcon32( "taxiway", 32 ) );
        ZRibbonButton taxiwayButton = null;
        ZRibbonButton signsButton = null;
        ResizableIcon tresizableicon1 = null;

        pathButton.setToolTipTitle( "Display Taxiways" );
        pathButton.setToolTipText( "Shows or hides airport taxiways." );
        pathButton.setToolTipSize( new Dimension( 180, 50 ) );
        taxiwayBand.addGalleryButton( pathButton, ZRibbonBand.RibbonElementPriority.TOP );
        taxiwayButton = new ZRibbonButton( "Taxiway Points", (ResizableIcon) new RibbonIcon32( "taxiPoint", 32 ) );
        taxiwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 36 ) ) );
        taxiwayButton.setToolTipTitle( "Display Taxiway Point" );
        taxiwayButton.setToolTipText( "Shows or hides taxiway points." );
        taxiwayButton.setToolTipSize( new Dimension( 180, 50 ) );
        taxiwayButton.setDisplayX( true );
        taxiwayButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWPoint()) ? true : false );
        taxiwayBand.addGalleryButton( taxiwayButton, ZRibbonBand.RibbonElementPriority.TOP );
        signsButton = new ZRibbonButton( "Taxiway Signs", (ResizableIcon) new RibbonIcon32( "taxiwaySign", 32 ) );
        signsButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 37 ) ) );
        signsButton.setToolTipTitle( "Display Taxiway Signs" );
        signsButton.setToolTipText( "Shows or hides taxiway signs." );
        signsButton.setToolTipSize( new Dimension( 180, 50 ) );
        signsButton.setDisplayX( true );
        tresizableicon1 = signsButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTWSign()) ? true : false );
        taxiwayBand.addGalleryButton( signsButton, ZRibbonBand.RibbonElementPriority.TOP );
        return taxiwayBand;
    }

    private ZPopupGallery getTaxiwayTypeOptions()
    {
        Object gallery = new IconPopupGallery( 290, 500, 65 );
        IconPopupGallery.IconGroup group = ((IconPopupGallery) gallery).createNewGroup( "Taxiway Types", 0 );
        ZRibbonButton runwayButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.green ), "Runway" );
        ZRibbonButton parkingButton = null;
        ZRibbonButton taxiButton = null;
        ZRibbonButton pathButton = null;
        ZRibbonButton closedButton = null;
        ZRibbonButton vehicleButton = null;
        ResizableIcon tresizableicon1 = null;
        ResizableIcon tresizableicon2 = null;
        ResizableIcon tresizableicon3 = null;
        ResizableIcon tresizableicon4 = null;
        ResizableIcon tresizableicon5 = null;

        runwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 40 ) ) );
        runwayButton.setDisplayX( true );
        runwayButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowRunwayPaths()) ? true : false );
        parkingButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.yellow ), "Parking" );
        parkingButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 41 ) ) );
        parkingButton.setDisplayX( true );
        tresizableicon1 = parkingButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowParkingPaths()) ? true : false );
        taxiButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), new Color( 153, 0, 0 ) ), "Taxi" );
        taxiButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 42 ) ) );
        taxiButton.setDisplayX( true );
        tresizableicon2 = taxiButton.getIcon();
        tresizableicon2.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowTaxiPaths()) ? true : false );
        pathButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.lightGray ), "Path" );
        pathButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 43 ) ) );
        pathButton.setDisplayX( true );
        tresizableicon3 = pathButton.getIcon();
        tresizableicon3.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowPathPaths()) ? true : false );
        closedButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.darkGray ), "Closed" );
        closedButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 44 ) ) );
        closedButton.setDisplayX( true );
        tresizableicon4 = closedButton.getIcon();
        tresizableicon4.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowClosedPaths()) ? true : false );
        vehicleButton = group.addIcon( (ResizableIcon) new ColorIcon( IconFactory.getInstance().getIcon( "taxiwayJPG" ).getImage(), Color.blue ), "Vehicle" );
        vehicleButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 45 ) ) );
        vehicleButton.setDisplayX( true );
        tresizableicon5 = vehicleButton.getIcon();
        tresizableicon5.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getShowVehiclePaths()) ? true : false );
        return (ZPopupGallery) gallery;
    }

    private ZRibbonBand getDisplayParkingBand()
    {
        ZRibbonBand parkingBand = new ZRibbonBand( "Show Parking", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton parkingButton = new ZRibbonButton( "Parking Locations", (ResizableIcon) new RibbonIcon32( "parking", 32 ) );
        ZRibbonButton jetwaysButton = null;
        ZRibbonButton triggerButton = null;
        ZRibbonButton startButton = null;
        ResizableIcon tresizableicon1 = null;
        ResizableIcon tresizableicon2 = null;
        ResizableIcon tresizableicon3 = null;

        parkingButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 38 ) ) );
        parkingButton.setToolTipTitle( "Display Parking Locations" );
        parkingButton.setToolTipText( "Shows or hides airport parking locations." );
        parkingButton.setToolTipSize( new Dimension( 150, 65 ) );
        parkingButton.setDisplayX( true );
        parkingButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayParking()) ? true : false );
        parkingBand.addGalleryButton( parkingButton, ZRibbonBand.RibbonElementPriority.TOP );
        jetwaysButton = new ZRibbonButton( "Jetways", IconFactory.getInstance().getIcon( "jetway" ).getImage() );
        jetwaysButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 39 ) ) );
        jetwaysButton.setToolTipTitle( "Display Jetways" );
        jetwaysButton.setToolTipText( "Shows or hides airport jetways." );
        jetwaysButton.setToolTipSize( new Dimension( 180, 50 ) );
        jetwaysButton.setDisplayX( true );
        tresizableicon1 = jetwaysButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayJetways()) ? true : false );
        parkingBand.addGalleryButton( jetwaysButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        triggerButton = new ZRibbonButton( "Fuel Triggers", (ResizableIcon) new RibbonIcon32( "fuel", 16 ) );
        triggerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 60 ) ) );
        triggerButton.setToolTipTitle( "Display Fuel Triggers" );
        triggerButton.setToolTipText( "Shows or hides fuel triggers." );
        triggerButton.setToolTipSize( new Dimension( 180, 50 ) );
        triggerButton.setDisplayX( true );
        tresizableicon2 = triggerButton.getIcon();
        tresizableicon2.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTriggers()) ? true : false );
        parkingBand.addGalleryButton( triggerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        startButton = new ZRibbonButton( "Start Locations", (ResizableIcon) new RibbonIcon32( "startPoint", 16 ) );
        startButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 50 ) ) );
        startButton.setToolTipTitle( "Display Start Locations" );
        startButton.setToolTipText( "Shows or hides airport start locations." );
        startButton.setToolTipSize( new Dimension( 150, 65 ) );
        startButton.setDisplayX( true );
        tresizableicon3 = startButton.getIcon();
        tresizableicon3.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayStart()) ? true : false );
        parkingBand.addGalleryButton( startButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return parkingBand;
    }

    private ZRibbonBand getDisplaySceneryBand()
    {
        ZRibbonBand sceneryBand = new ZRibbonBand( "Show Scenery", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton sceneryButton = new ZRibbonButton( "Scenery Objects", IconFactory.getInstance().getIcon( "scenery32" ).getImage() );
        ZRibbonButton apronButton = null;
        ZRibbonButton edgeLightButton = null;
        ZRibbonButton boundaryButton = null;
        ZRibbonButton blastButton = null;
        ZRibbonButton helipadButton = null;
        ZRibbonButton towerButton = null;
        ResizableIcon tresizableicon1 = null;
        ResizableIcon tresizableicon2 = null;
        ResizableIcon tresizableicon3 = null;
        ResizableIcon tresizableicon4 = null;
        ResizableIcon tresizableicon5 = null;
        ResizableIcon tresizableicon6 = null;

        sceneryButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 61 ) ) );
        sceneryButton.setToolTipTitle( "Display Scenery Objects" );
        sceneryButton.setToolTipText( "Shows or hides scenery objects." );
        sceneryButton.setToolTipSize( new Dimension( 180, 50 ) );
        sceneryButton.setDisplayX( true );
        sceneryButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayScenery()) ? true : false );
        sceneryBand.addGalleryButton( sceneryButton, ZRibbonBand.RibbonElementPriority.TOP );
        apronButton = new ZRibbonButton( "Aprons", (ResizableIcon) new RibbonIcon32( "apron", 16 ) );
        apronButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 34 ) ) );
        apronButton.setToolTipTitle( "Display Aprons" );
        apronButton.setToolTipText( "Shows or hides airport aprons." );
        apronButton.setToolTipSize( new Dimension( 180, 50 ) );
        apronButton.setDisplayX( true );
        tresizableicon1 = apronButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayApron()) ? true : false );
        sceneryBand.addGalleryButton( apronButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        edgeLightButton = new ZRibbonButton( "Apron Edge Lights", IconFactory.getInstance().getIcon( "apronEdgeLight" ).getImage() );
        edgeLightButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 35 ) ) );
        edgeLightButton.setToolTipTitle( "Display Apron Edge Lights" );
        edgeLightButton.setToolTipText( "Shows or hides apron edge lights." );
        edgeLightButton.setToolTipSize( new Dimension( 190, 50 ) );
        edgeLightButton.setDisplayX( true );
        tresizableicon2 = edgeLightButton.getIcon();
        tresizableicon2.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayApronEL()) ? true : false );
        sceneryBand.addGalleryButton( edgeLightButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        boundaryButton = new ZRibbonButton( "Boundary Fences", IconFactory.getInstance().getIcon( "fence" ).getImage() );
        boundaryButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 47 ) ) );
        boundaryButton.setToolTipTitle( "Display Boundary Fences" );
        boundaryButton.setToolTipText( "Shows or hides boundary fences." );
        boundaryButton.setToolTipSize( new Dimension( 180, 50 ) );
        boundaryButton.setDisplayX( true );
        tresizableicon3 = boundaryButton.getIcon();
        tresizableicon3.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBoundFence()) ? true : false );
        sceneryBand.addGalleryButton( boundaryButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        blastButton = new ZRibbonButton( "Blast Fences", IconFactory.getInstance().getIcon( "blastFence" ).getImage() );
        blastButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 48 ) ) );
        blastButton.setToolTipTitle( "Display Blast Fences" );
        blastButton.setToolTipText( "Shows or hides blast fences." );
        blastButton.setToolTipSize( new Dimension( 180, 50 ) );
        blastButton.setDisplayX( true );
        tresizableicon4 = blastButton.getIcon();
        tresizableicon4.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBlastFence()) ? true : false );
        sceneryBand.addGalleryButton( blastButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        helipadButton = new ZRibbonButton( "Helipads", IconFactory.getInstance().getIcon( "helipad" ).getImage() );
        helipadButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 49 ) ) );
        helipadButton.setToolTipTitle( "Display Helipads" );
        helipadButton.setToolTipText( "Shows or hides airport helipads." );
        helipadButton.setToolTipSize( new Dimension( 180, 50 ) );
        helipadButton.setDisplayX( true );
        tresizableicon5 = helipadButton.getIcon();
        tresizableicon5.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayHelipad()) ? true : false );
        sceneryBand.addGalleryButton( helipadButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        towerButton = new ZRibbonButton( "Towers", IconFactory.getInstance().getIcon( "tower16" ).getImage() );
        towerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 46 ) ) );
        towerButton.setToolTipTitle( "Display Control Towers" );
        towerButton.setToolTipText( "Shows or hides airport control towers." );
        towerButton.setToolTipSize( new Dimension( 150, 65 ) );
        towerButton.setDisplayX( true );
        tresizableicon6 = towerButton.getIcon();
        tresizableicon6.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTower()) ? true : false );
        sceneryBand.addGalleryButton( towerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return sceneryBand;
    }

    private ZRibbonBand getDisplayNavBand()
    {
        ZRibbonBand navigationBand = new ZRibbonBand( "Show Navigation", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton ilsButton = new ZRibbonButton( "ILS Systems", IconFactory.getInstance().getIcon( "ilsBeam" ).getImage() );
        ZRibbonButton vorButton = null;
        ZRibbonButton ndbButton = null;
        ZRibbonButton markerButton = null;
        ZRibbonButton windsockButton = null;
        ResizableIcon tresizableicon1 = null;
        ResizableIcon tresizableicon2 = null;
        ResizableIcon tresizableicon3 = null;
        ResizableIcon tresizableicon4 = null;

        ilsButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 33 ) ) );
        ilsButton.setToolTipTitle( "Display ILS Systems" );
        ilsButton.setToolTipText( "Shows or hides ILS, Glide Slope and DME systems." );
        ilsButton.setToolTipSize( new Dimension( 150, 65 ) );
        ilsButton.setDisplayX( true );
        ilsButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayILS()) ? true : false );
        navigationBand.addGalleryButton( ilsButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        vorButton = new ZRibbonButton( "VORs", IconFactory.getInstance().getIcon( "vor" ).getImage() );
        vorButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 58 ) ) );
        vorButton.setToolTipTitle( "Display VORs" );
        vorButton.setToolTipText( "Shows or hides VOR systems." );
        vorButton.setToolTipSize( new Dimension( 180, 50 ) );
        vorButton.setDisplayX( true );
        tresizableicon1 = vorButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayVORs()) ? true : false );
        navigationBand.addGalleryButton( vorButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        ndbButton = new ZRibbonButton( "NDBs", IconFactory.getInstance().getIcon( "ndb" ).getImage() );
        ndbButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 57 ) ) );
        ndbButton.setToolTipTitle( "Display NDBs" );
        ndbButton.setToolTipText( "Shows or hides NDB systems." );
        ndbButton.setToolTipSize( new Dimension( 180, 50 ) );
        ndbButton.setDisplayX( true );
        tresizableicon2 = ndbButton.getIcon();
        tresizableicon2.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayNDBs()) ? true : false );
        navigationBand.addGalleryButton( ndbButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        markerButton = new ZRibbonButton( "Markers", IconFactory.getInstance().getIcon( "marker" ).getImage() );
        markerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 56 ) ) );
        markerButton.setToolTipTitle( "Display Markers" );
        markerButton.setToolTipText( "Shows or hides inner, middle and outer markers." );
        markerButton.setToolTipSize( new Dimension( 150, 65 ) );
        markerButton.setDisplayX( true );
        tresizableicon3 = markerButton.getIcon();
        tresizableicon3.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayMarkers()) ? true : false );
        navigationBand.addGalleryButton( markerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        windsockButton = new ZRibbonButton( "Windsocks", IconFactory.getInstance().getIcon( "windsock" ).getImage() );
        windsockButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 59 ) ) );
        windsockButton.setToolTipTitle( "Display Windsocks" );
        windsockButton.setToolTipText( "Shows or hides airport windsocks." );
        windsockButton.setToolTipSize( new Dimension( 190, 50 ) );
        windsockButton.setDisplayX( true );
        tresizableicon4 = windsockButton.getIcon();
        tresizableicon4.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayWindsocks()) ? true : false );
        navigationBand.addGalleryButton( windsockButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return navigationBand;
    }

    private ZRibbonBand getDisplayOtherBand()
    {
        ZRibbonBand otherBand = new ZRibbonBand( "Show Other", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton latLonButton = new ZRibbonButton( "Lat/Lon Lines", (ResizableIcon) new RibbonIcon32( "latLon", 16 ) );
        ZRibbonButton exclusionButton = null;
        ZRibbonButton centerButton = null;
        ZRibbonButton testRadiusButton = null;
        ZRibbonButton imageButton = null;
        ZRibbonButton rotateButton = null;
        ResizableIcon tresizableicon1 = null;
        ResizableIcon tresizableicon2 = null;
        ResizableIcon tresizableicon3 = null;
        ResizableIcon tresizableicon4 = null;
        ResizableIcon tresizableicon5 = null;

        latLonButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 51 ) ) );
        latLonButton.setToolTipTitle( "Display Lat/Lon Lines" );
        latLonButton.setToolTipText( "Shows or hides latitude/ longitude background lines." );
        latLonButton.setToolTipSize( new Dimension( 160, 65 ) );
        latLonButton.setDisplayX( true );
        latLonButton.getIcon().setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayLatLon()) ? true : false );
        otherBand.addGalleryButton( latLonButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        exclusionButton = new ZRibbonButton( "Exclusion Rectangles", (ResizableIcon) new RibbonIcon32( "exclude", 16 ) );
        exclusionButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 52 ) ) );
        exclusionButton.setToolTipTitle( "Display Exclusion Rectangles" );
        exclusionButton.setToolTipText( "Shows or hides exclusion rectangles." );
        exclusionButton.setToolTipSize( new Dimension( 150, 65 ) );
        exclusionButton.setDisplayX( true );
        tresizableicon1 = exclusionButton.getIcon();
        tresizableicon1.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayExcludes()) ? true : false );
        otherBand.addGalleryButton( exclusionButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        centerButton = new ZRibbonButton( "Airport Center", (ResizableIcon) new RibbonIcon32( "airportCenter", 16 ) );
        centerButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 53 ) ) );
        centerButton.setToolTipTitle( "Display Airport Reference Point" );
        centerButton.setToolTipText( "Shows or hides the airport reference point." );
        centerButton.setToolTipSize( new Dimension( 165, 65 ) );
        centerButton.setDisplayX( true );
        tresizableicon2 = centerButton.getIcon();
        tresizableicon2.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayAirportCtr()) ? true : false );
        otherBand.addGalleryButton( centerButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        testRadiusButton = new ZRibbonButton( "Airport Test Radius", (ResizableIcon) new RibbonIcon32( "testRadius", 16 ) );
        testRadiusButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 54 ) ) );
        testRadiusButton.setToolTipTitle( "Display Airport Test Radius" );
        testRadiusButton.setToolTipText( "Shows or hides airport statest radius." );
        testRadiusButton.setToolTipSize( new Dimension( 150, 65 ) );
        testRadiusButton.setDisplayX( true );
        tresizableicon3 = testRadiusButton.getIcon();
        tresizableicon3.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayTestRadius()) ? true : false );
        otherBand.addGalleryButton( testRadiusButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        imageButton = new ZRibbonButton( "Background Images", (ResizableIcon) new RibbonIcon32( "background", 16 ) );
        imageButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 55 ) ) );
        imageButton.setToolTipTitle( "Display Background Images" );
        imageButton.setToolTipText( "Shows or hides background images." );
        imageButton.setToolTipSize( new Dimension( 150, 65 ) );
        imageButton.setDisplayX( true );
        tresizableicon4 = imageButton.getIcon();
        tresizableicon4.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBGImage()) ? true : false );
        otherBand.addGalleryButton( imageButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        rotateButton = new ZRibbonButton( "Map Rotation", (ResizableIcon) new RibbonIcon32( "rotate", 16 ) );
        rotateButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 62 ) ) );
        rotateButton.setToolTipTitle( "Display Rotation" );
        rotateButton.setToolTipText( "Shows or hides the map rotation icon.\n\n" );
        rotateButton.addToolTipText( "The map can be rotated by:\n" );
        rotateButton.addToolTipText( "1) holding the shift key down and rotating the mouse wheel\n" );
        rotateButton.addToolTipText( "2) or by entering a specific roation in the Rotation Properties display." );
        rotateButton.setToolTipSize( new Dimension( 250, 130 ) );
        rotateButton.setDisplayX( true );
        tresizableicon5 = rotateButton.getIcon();
        tresizableicon5.setDrawX( (!com.zbluesoftware.fsxp.engine.SettingsEngine.getInstance().getDisplayBGImage()) ? true : false );
        otherBand.addGalleryButton( rotateButton, ZRibbonBand.RibbonElementPriority.MEDIUM );
        return otherBand;
    }

    private ZRibbonBand getCompileBand()
    {
        ZRibbonBand compileBand = new ZRibbonBand( "Compile", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton compileButton = new ZRibbonButton( "Compile BGL File", (ResizableIcon) new RibbonIcon32( "compile", 32 ) );

        compileButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 70 ) ) );
        compileButton.setToolTipTitle( "Compile to BGL" );
        compileButton.setToolTipText( "Displays the dialog that allows you to compile your airport into a BGL file." );
        compileButton.setToolTipSize( new Dimension( 160, 80 ) );
        compileBand.addGalleryButton( compileButton, ZRibbonBand.RibbonElementPriority.TOP );
        return compileBand;
    }

    private ZRibbonBand getPrepareBand()
    {
        ZRibbonBand prepareBand = new ZRibbonBand( "Prepare Options", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton prepareButton = new ZRibbonButton( "Prepare Options", (ResizableIcon) new RibbonIcon32( "prepare", 32 ) );
        ZRibbonButton errorCheckButton = null;

        prepareButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 63 ) ) );
        prepareButton.setToolTipTitle( "Prepare Options" );
        prepareButton.setToolTipText( "Displays a dialog allowing you to select the parts of your airport to be compiled into the BGL file." );
        prepareButton.setToolTipText( " Also allows you to specify which delete flags are to be set." );
        prepareButton.setToolTipSize( new Dimension( 180, 65 ) );
        prepareBand.addGalleryButton( prepareButton, ZRibbonBand.RibbonElementPriority.TOP );
        errorCheckButton = new ZRibbonButton( "Error Checking", (ResizableIcon) new RibbonIcon32( "errorChecking", 32 ) );
        errorCheckButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 64 ) ) );
        errorCheckButton.setToolTipTitle( "Error Checking" );
        errorCheckButton.setToolTipText( "Checks your airport for common hard to find errors." );
        errorCheckButton.setToolTipSize( new Dimension( 180, 65 ) );
        prepareBand.addGalleryButton( errorCheckButton, ZRibbonBand.RibbonElementPriority.TOP );
        return prepareBand;
    }

    private ZRibbonBand getCleanUpBand()
    {
        ZRibbonBand cleanUpBand = new ZRibbonBand( "Clean Options", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton cleanApronButton = new ZRibbonButton( "Clean Up Aprons", (ResizableIcon) new RibbonIcon32( "cleanAprons", 32 ) );
        ZRibbonButton cleanFenceButton = null;
        ZRibbonButton cleanTaxiwayButton = null;
        ZRibbonButton cleanTaxiPointButton = null;
        ZRibbonButton cleanAllButon = null;

        cleanApronButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 65 ) ) );
        cleanApronButton.setToolTipTitle( "Clean Aprons" );
        cleanApronButton.setToolTipText( "Cleans up orphan apron and apron edge light vertices." );
        cleanApronButton.setToolTipSize( new Dimension( 160, 65 ) );
        cleanUpBand.addGalleryButton( cleanApronButton, ZRibbonBand.RibbonElementPriority.TOP );
        cleanFenceButton = new ZRibbonButton( "Clean Up Fences", (ResizableIcon) new RibbonIcon32( "cleanFences", 32 ) );
        cleanFenceButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 66 ) ) );
        cleanFenceButton.setToolTipTitle( "Clean Fences" );
        cleanFenceButton.setToolTipText( "Cleans up orphan boundary and blast fence vertices." );
        cleanFenceButton.setToolTipSize( new Dimension( 190, 65 ) );
        cleanUpBand.addGalleryButton( cleanFenceButton, ZRibbonBand.RibbonElementPriority.TOP );
        cleanTaxiwayButton = new ZRibbonButton( "Clean Up Taxiways", (ResizableIcon) new RibbonIcon32( "cleanTW", 32 ) );
        cleanTaxiwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 67 ) ) );
        cleanTaxiwayButton.setToolTipTitle( "Clean Taxiways" );
        cleanTaxiwayButton.setToolTipText( "Cleans up taxiways." );
        cleanTaxiwayButton.setToolTipSize( new Dimension( 165, 50 ) );
        cleanUpBand.addGalleryButton( cleanTaxiwayButton, ZRibbonBand.RibbonElementPriority.TOP );
        cleanTaxiPointButton = new ZRibbonButton( "Clean Up Taxiway Points", (ResizableIcon) new RibbonIcon32( "cleanTWP", 32 ) );
        cleanTaxiPointButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 68 ) ) );
        cleanTaxiPointButton.setToolTipTitle( "Clean Taxiway Points" );
        cleanTaxiPointButton.setToolTipText( "Cleans up orphaned taxiway points." );
        cleanTaxiPointButton.setToolTipSize( new Dimension( 150, 65 ) );
        cleanUpBand.addGalleryButton( cleanTaxiPointButton, ZRibbonBand.RibbonElementPriority.TOP );
        cleanAllButon = new ZRibbonButton( "Clean All", (ResizableIcon) new RibbonIcon32( "cleanAll", 32 ) );
        cleanAllButon.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 69 ) ) );
        cleanAllButon.setToolTipTitle( "Clean All" );
        cleanAllButon.setToolTipText( "Performs all the clean up actions." );
        cleanAllButon.setToolTipSize( new Dimension( 150, 65 ) );
        cleanUpBand.addGalleryButton( cleanAllButon, ZRibbonBand.RibbonElementPriority.TOP );
        return cleanUpBand;
    }

    private ZRibbonBand getKarmaBand()
    {
        ZRibbonBand karmaBand = new ZRibbonBand( "Karma", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton karmaButton = new ZRibbonButton( "Instant Karma", (ResizableIcon) new RibbonIcon32( "karma", 32 ) );

        karmaButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 104 ) ) );
        karmaButton.setToolTipTitle( "Instant Karma" );
        karmaButton.setToolTipText( "Help Support FSX Planner." );
        karmaButton.setToolTipSize( new Dimension( 160, 65 ) );
        karmaBand.addGalleryButton( karmaButton, ZRibbonBand.RibbonElementPriority.TOP );
        return karmaBand;
    }

    private ZRibbonBand getBasicHelpBand()
    {
        ZRibbonBand basicBand = new ZRibbonBand( "Basic Help", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton basicsButton = new ZRibbonButton( "The Basics", (ResizableIcon) new RibbonIcon32( "helpBasics", 32 ) );
        ZRibbonButton bglButton = null;
        ZRibbonButton compileButton = null;
        ZRibbonButton fsxButton = null;

        basicsButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 105 ) ) );
        basicsButton.setToolTipTitle( "The Basics" );
        basicsButton.setToolTipText( "Get help with FSX Planner general concepts." );
        basicsButton.setToolTipSize( new Dimension( 160, 65 ) );
        basicBand.addGalleryButton( basicsButton, ZRibbonBand.RibbonElementPriority.TOP );
        bglButton = new ZRibbonButton( "Opening BGL Files", (ResizableIcon) new RibbonIcon32( "helpBGL", 32 ) );
        bglButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 106 ) ) );
        bglButton.setToolTipTitle( "Opening BGL Files" );
        bglButton.setToolTipText( "Get help with opening BGL files." );
        bglButton.setToolTipSize( new Dimension( 150, 65 ) );
        basicBand.addGalleryButton( bglButton, ZRibbonBand.RibbonElementPriority.TOP );
        compileButton = new ZRibbonButton( "Compiling Your Airport", (ResizableIcon) new RibbonIcon32( "helpCompile", 32 ) );
        compileButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 107 ) ) );
        compileButton.setToolTipTitle( "Compiling Your Airport" );
        compileButton.setToolTipText( "Get help with compiling your airport." );
        compileButton.setToolTipSize( new Dimension( 150, 65 ) );
        basicBand.addGalleryButton( compileButton, ZRibbonBand.RibbonElementPriority.TOP );
        fsxButton = new ZRibbonButton( "Connecting to FSX", (ResizableIcon) new RibbonIcon32( "helpConnect", 32 ) );
        fsxButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 108 ) ) );
        fsxButton.setToolTipTitle( "Connecting to FSX" );
        fsxButton.setToolTipText( "Get help with connecting to FSX." );
        fsxButton.setToolTipSize( new Dimension( 150, 65 ) );
        basicBand.addGalleryButton( fsxButton, ZRibbonBand.RibbonElementPriority.TOP );
        return basicBand;
    }

    private ZRibbonBand getDetailedHelpBand()
    {
        ZRibbonBand detailedBand = new ZRibbonBand( "Detailed Help", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton apronButton = new ZRibbonButton( "Creating and Editing Aprons", (ResizableIcon) new RibbonIcon32( "helpApron", 32 ) );
        ZRibbonButton taxiwayButton = null;
        ZRibbonButton signButton = null;
        ZRibbonButton refuelButton = null;
        ZRibbonButton imageButton = null;

        apronButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 109 ) ) );
        apronButton.setToolTipTitle( "Aprons" );
        apronButton.setToolTipText( "Get help with creating and editing aprons." );
        apronButton.setToolTipSize( new Dimension( 160, 65 ) );
        detailedBand.addGalleryButton( apronButton, ZRibbonBand.RibbonElementPriority.TOP );
        taxiwayButton = new ZRibbonButton( "Creating and Editing Taxiways", (ResizableIcon) new RibbonIcon32( "helpTaxiway", 32 ) );
        taxiwayButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 110 ) ) );
        taxiwayButton.setToolTipTitle( "Taxiways" );
        taxiwayButton.setToolTipText( "Get help with creating and editing taxiways." );
        taxiwayButton.setToolTipSize( new Dimension( 150, 65 ) );
        detailedBand.addGalleryButton( taxiwayButton, ZRibbonBand.RibbonElementPriority.TOP );
        signButton = new ZRibbonButton( "Creating and Editing Taxiway Signs", (ResizableIcon) new RibbonIcon32( "helpSign", 32 ) );
        signButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 111 ) ) );
        signButton.setToolTipTitle( "Taxiway Signs" );
        signButton.setToolTipText( "Get help with creating and editing taxiways signs." );
        signButton.setToolTipSize( new Dimension( 150, 65 ) );
        detailedBand.addGalleryButton( signButton, ZRibbonBand.RibbonElementPriority.TOP );
        refuelButton = new ZRibbonButton( "Creating and Editing Refueling Locations", (ResizableIcon) new RibbonIcon32( "helpFuel", 32 ) );
        refuelButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 112 ) ) );
        refuelButton.setToolTipTitle( "Refueling Locations" );
        refuelButton.setToolTipText( "Get help with creating and editing refueling locations." );
        refuelButton.setToolTipSize( new Dimension( 150, 65 ) );
        detailedBand.addGalleryButton( refuelButton, ZRibbonBand.RibbonElementPriority.TOP );
        imageButton = new ZRibbonButton( "Working with Background Images", (ResizableIcon) new RibbonIcon32( "helpBackground", 32 ) );
        imageButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 113 ) ) );
        imageButton.setToolTipTitle( "Background Images" );
        imageButton.setToolTipText( "Get help on working with background images." );
        imageButton.setToolTipSize( new Dimension( 150, 65 ) );
        detailedBand.addGalleryButton( imageButton, ZRibbonBand.RibbonElementPriority.TOP );
        return detailedBand;
    }

    private ZRibbonBand getMoreHelpBand()
    {
        ZRibbonBand moreHelpBand = new ZRibbonBand( "More Help", (ResizableIcon) new RibbonIcon( IconFactory.getInstance().getIcon( "parking" ).getImage() ) );
        ZRibbonButton moreButton = new ZRibbonButton( "More Help Options", (ResizableIcon) new RibbonIcon32( "helpMore", 32 ) );

        moreButton.setAction( (Action) MenuFactory.getInstance().getMenuAction( Integer.valueOf( 114 ) ) );
        moreButton.setToolTipTitle( "More Help" );
        moreButton.setToolTipText( "See the full list of help options." );
        moreButton.setToolTipSize( new Dimension( 160, 65 ) );
        moreHelpBand.addGalleryButton( moreButton, ZRibbonBand.RibbonElementPriority.TOP );
        return moreHelpBand;
    }
}