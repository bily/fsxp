package flightsim.aicarriers;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.util.*;

import org.lc0277lib.conf.Config;
import org.lc0277lib.geography.ConvertUnits;
import org.lc0277lib.geography.FormatLatLon;
import org.lc0277lib.geography.GeoCalc;
import org.lc0277lib.geography.FormatLatLon.FormatType;
import org.lc0277lib.geom.Point2D;

import flightsim.aicarriers.Formation.UnitPosition;
import flightsim.simconnect.*;
import flightsim.simconnect.config.*;
import flightsim.simconnect.data.*;
import flightsim.simconnect.recv.*;

public class AICarriersManager implements OpenHandler, 
			EventHandler,
			SimObjectDataHandler,
			EventObjectHandler,
			QuitHandler,
			ExceptionHandler,
			AssignedObjectHandler {

	private static final String[] POSITION_TYPES = {
		"User position",
		"1 nm ahead",
		"5 nm ahead",
		"10 nm ahead",
		"Facility waypoint",
		"Return",
	};
	
	private static final String[] MOVE_TYPES = {
		"Forward",				// 1
		"Full stop",			// 2
		"Turn left 10 degrees",	// 3
		"Turn right 10 degrees", // 4		
		"Turn left 90 degrees",  // 5
		"Turn right 90 degrees", // 6
		"Move to waypoint",		// 7
		"Delete",	 			 // 8
		"Close window"			// 9
	};
	
	public static final String MENU_TITLE = "AI Ships";
	
	// Machine states
	enum MenuState {
		FORMATION_TYPE,
		POSITION_TYPE,
		SELECT_WAYPOINT,
		MOVE,
		SELECT_DESTINATION_WAYPOINT,
	}
	
	private Config conf;
	private NumberFormat df = NumberFormat.getNumberInstance(Locale.ENGLISH);
	
	// connection data
	private SimConnect sc;
	private DispatcherTask dt;
	
	// current formation data
	private Formation currentFormation;
	private Unit[] units;
	private double currentHeading;
	private FacilityWaypoint destWpt;
	
	// menu pointers
	private MenuState state = MenuState.FORMATION_TYPE;
	private int currentlyDisplayed = -1;
	private FormationList formList;
	private WaypointList wpList;
	
	// user position
	private UserTracker userTracker;
	
	
	
	/**
	 * Construct a new carrier managers
	 * @throws IOException
	 */
	AICarriersManager(String confDirectory) throws IOException {
		File confFile = new File(confDirectory, "aicarriers.cfg");
		conf = Config.buildConfig(confFile);	// read config
		
		formList = new FormationList(confDirectory, conf);		// read formations from config
		
		initConnection();
		initErrorTracking();
		
		
		// init dispatching
		dt = new DispatcherTask(sc);
		dt.addHandlers(this);

		// user tracker
		userTracker = new UserTracker(dt);

		// waypoint list
		wpList = new WaypointList(userTracker, dt);
		
	}
	
	private void initConnection() throws IOException {
		//
		// get a configuration block if user provided a simconnect.cfg
		//
		Configuration cfg = null;
		try {
			cfg = ConfigurationManager.getConfiguration(0);
		} catch (Exception cfgEx) {
			cfg = new Configuration();
		}
		
		// fix port number (with automatic settings)
		int port = cfg.getInt(Configuration.PORT, -1);
		if (port == -1) {
			port = Configuration.findSimConnectPortIPv4();
			if (port <= 0) {
				port = Configuration.findSimConnectPortIPv6();
				cfg.setProtocol(6);
			} else {
				cfg.setProtocol(4);
			}
			cfg.setPort(port);
		}
		
		// fix host
		String host = cfg.get(Configuration.ADDRESS, null);
		if (host == null) {
			if (cfg.getInt(Configuration.PROTOCOL, 4) == 6)
				cfg.setAddress("::1");
			else
				cfg.setAddress("localhost");
		}
		
		// force simconnect version 0x3
		sc = new SimConnect(MENU_TITLE, cfg, 0x3);
	}
	
	public void run() {
		dt.createThread().start();
	}
	
	public DispatcherTask getDispatcher() {
		return dt;
	}
	
	
	/* **************************************************************
	 * 
	 * Simconnect handlers
	 * 
	 * **************************************************************
	 */



	public void handleOpen(SimConnect sender, RecvOpen e) {
		//
		// create basic simconnect requests
		// 
		try {
			
			// move ai using throttle (forward/stop) or desired heading
			sc.mapClientEventToSimEvent(ID.EVENT_AI_THROTTLE, "THROTTLE_SET");
			sc.addToDataDefinition(ID.DATA_DEF_DESIRED_HEADING, 
					"AI DESIRED HEADING", "radians", 
					SimConnectDataType.FLOAT64);
			
			// system events
			sc.subscribeToSystemEvent(ID.EVENT_SIMSTART, "Sim");
			sc.subscribeToSystemEvent(ID.EVENT_OBJECT_REMOVED, "ObjectRemoved");
			
			// shortcut key event
			String keyShort = conf.get("base", "menu_shortcut", "Shift+U");
			sc.mapClientEventToSimEvent(ID.EVENT_CALL_MENU);
			sc.addClientEventToNotificationGroup(ID.GROUP_KEYS, 
					ID.EVENT_CALL_MENU, true);
			sc.setNotificationGroupPriority(ID.GROUP_KEYS, 
					NotificationPriority.HIGHEST);
			sc.mapInputEventToClientEvent(ID.INPUT_KEYS, 
					keyShort,
					ID.EVENT_CALL_MENU);
			sc.setInputGroupState(ID.INPUT_KEYS, true);

			// init menu
			sc.menuAddItem("AI Ships", ID.EVENT_TITLE_MENU, 0);
			sc.menuAddSubItem(ID.EVENT_TITLE_MENU, "Show/hide menu (" + keyShort + ")", 
					ID.EVENT_TITLE_MENU_SHOW, 0);
			
		} catch (IOException e1) {
		}
		
	}
	
	public void handleEvent(SimConnect sender, RecvEvent e) {
		if (ID.EVENT_SIMSTART.isEvent(e)) {
			// sim start
			// showMenu();		// just for debugging
		} else if (ID.EVENT_CALL_MENU.isEvent(e)) {
			// keyboard shortcut
			showOrHideMenu();
			
		} else if (ID.EVENT_TITLE_MENU_SHOW.isEvent(e)) {
			showOrHideMenu();		// show main menu
			
		} else if (ID.EVENT_MENU_SELECT_FORMATION.isEvent(e)) {
			//
			// formation select mode
			//
			TextResult tr = TextResult.type(e);
			menuDisplayed(tr, e.getEventID());
			currentFormation = formList.processEvent(sc, tr);
			if (currentFormation != null &&
					currentFormation.size() > 0) {
				// next state
				changeState(MenuState.POSITION_TYPE);
				showMenu();
			}
			
		} else if (ID.EVENT_MENU_SELECT_POSITION_TYPE.isEvent(e)) {
			//
			// position type
			// 
			TextResult tr = TextResult.type(e);
			menuDisplayed(tr, e.getEventID());
			
			processPositionMenuEntry(tr);
					
		} else if (ID.EVENT_MENU_SELECT_WAYPOINT.isEvent(e)) {
			//
			// waypoint select mode 
			//
			TextResult tr = TextResult.type(e);
			menuDisplayed(tr, e.getEventID());
					
			FacilityWaypoint fw = wpList.processEvent(sc, tr);
			if (fw != null) {
				if (state == MenuState.SELECT_WAYPOINT) {
					// insert at select waypoint
					insertAt(Math.toRadians(fw.getLatitude()), 
							Math.toRadians(fw.getLongitude()), 0);
				} else if (state == MenuState.SELECT_DESTINATION_WAYPOINT) {
					// destination
					moveTo(fw);
				}
				
				// next state
				changeState(MenuState.MOVE);
			}
		} else if (ID.EVENT_MENU_SELECT_MOVE.isEvent(e)) {
			//
			// select move
			//
			TextResult tr = TextResult.type(e);
			menuDisplayed(tr, e.getEventID());
					
			doMove(tr);
		} else if (ID.EVENT_TITLE_MENU_AI_POSITION.isEvent(e)) {
			requestFleetPosition();
		}
	}
	
	public void handleAssignedObject(SimConnect sender, RecvAssignedObjectID e) {
		if (e.getRequestID() >= ID.LAST.ordinal()) {
			int cid = e.getObjectID();
			int fid = e.getRequestID() - ID.LAST.ordinal();
			units[fid].setContainerId(cid);
		}
	}
	
	public void handleEventObject(SimConnect sender, RecvEventAddRemove e) {
		if (state != MenuState.MOVE)
			return;		// objects are not present in other menu modes
		
		int cid = e.getData();
		int nObj = 0;
		if (units != null) {
			for (int i = 0; i < units.length; i++) {
				Unit un = units[i];
				if (un == null)
					continue;
				
				if (un.getContainerId() == cid) {
					// oops we deleted
					un.setContainerId(-1);
				}
				// count object
				if (un.getContainerId() != -1) nObj++;
			}
		}
		if (nObj == 0) {
			// all deleted
			changeState(MenuState.FORMATION_TYPE);
			currentFormation = null;
			units = null;
		}
		
	}
	
	public void handleSimObject(SimConnect sender, RecvSimObjectData e) {
		if (ID.REQUEST_AI_POSITION.isRequest(e) && destWpt != null) {
			moveTo(e, destWpt);
		} else if (ID.REQUEST_AI_POSITION_FOR_LOCATE.isRequest(e)) {
			gotFleetPosition(e);
		}
	}

	public void handleQuit(SimConnect sc, RecvQuit rq) {
		System.exit(0);
	}
	
	
	/* **************************************************************
	 * 
	 * Error tracking
	 * 
	 * **************************************************************
	 */
	
	private int[] requestIDs;
	private String[] insertedContainers;
	private int insertIDptr;
	
	private void initErrorTracking() {
		requestIDs = new int[10];
		insertedContainers = new String[10];
		
		for (int i = 0; i < requestIDs.length; i++) {
			requestIDs[i] = -1;
		}
		insertIDptr = 0;
	}
	
	private synchronized void logInsertionPacket(String container) {
		requestIDs[insertIDptr] = sc.getLastSentPacketID();
		insertedContainers[insertIDptr] = container;
		
		insertIDptr++;
		if (insertIDptr == requestIDs.length) {
			insertIDptr = 0;
		}
	}
	

	public void handleException(SimConnect sender, RecvException e) {
		int reqId = e.getSendID();
		if (reqId <= 0)
			return;
		
		// 
		// find erroring packet
		for (int i = 0; i < requestIDs.length; i++) {
			if (requestIDs[i] != -1 &&
					requestIDs[i] == reqId) {
				// print error message
				try {
					sc.text(TextType.PRINT_RED, 8.0f, ID.REQUEST_ERROR_TEXT, 
							"Error inserting object '" +
							insertedContainers[i] + "': " +
							e.getException().getMessage());
				} catch (IOException e1) {}
			}
		}
		
	}
	
	
	
	/* **************************************************************
	 * 
	 * Change state
	 * 
	 * **************************************************************
	 */
	
	private void changeState(MenuState newState) {
		MenuState oldState = state;
		if (oldState == newState)
			return;
		
		state = newState;
		
		if (newState == MenuState.MOVE) {
			// activate request position menu
			try {
				sc.menuAddSubItem(ID.EVENT_TITLE_MENU, "Request position", 
						ID.EVENT_TITLE_MENU_AI_POSITION, 0);
			} catch (IOException e) {}
		}
		if (oldState == MenuState.MOVE) {
			try {
				sc.menuDeleteSubItem(ID.EVENT_TITLE_MENU, 
						ID.EVENT_TITLE_MENU_AI_POSITION);
			} catch (IOException e) {}
		}
		
	}

	
	/* **************************************************************
	 * 
	 * Menu display
	 * 
	 * **************************************************************
	 */

	
	private void menuDisplayed(TextResult tr, int eventId) {
		if (tr == TextResult.DISPLAYED) {
			currentlyDisplayed = eventId;
		} else {
			currentlyDisplayed = -1;
		}
	}
	
	private void showOrHideMenu() {
		if (currentlyDisplayed != -1) {
			try {
				sc.menu(0.0f, currentlyDisplayed, null, null, (String[])null);
			} catch (IOException e) {
			}
			currentlyDisplayed = -1;
		} else {
			showMenu();
		}
	}
	
	private void showMenu() {
		
		switch (state) {
		case FORMATION_TYPE:
			formList.showMenu(sc);
			break;
		case POSITION_TYPE:
			try {
				sc.menu(0.0f, ID.EVENT_MENU_SELECT_POSITION_TYPE, MENU_TITLE, 
						"Select position type",
						POSITION_TYPES);
			} catch (IOException e) {}
			break;
		case SELECT_DESTINATION_WAYPOINT:
		case SELECT_WAYPOINT:
			wpList.showMenu(sc);
			break;
		case MOVE:
			try {
				sc.menu(5.0f, ID.EVENT_MENU_SELECT_MOVE, MENU_TITLE, 
						"Moves (" + currentFormation.getTitle() + ") : ",
						MOVE_TYPES);
			} catch (IOException e) {}
			break;
		}
	}
	
	/* **************************************************************
	 * 
	 * Ships movement
	 * 
	 * **************************************************************
	 */

	private void doMove(TextResult tr) {
		if (units == null)
			return;
		
		switch (tr) {
		case MENU_SELECT_1:
			// forward
			setThrottle(conf.getInteger("base", "throttle", 60));
			break;
		case MENU_SELECT_2:
			// full stop 
			setThrottle(0);
			break;
		case MENU_SELECT_3:
			// left 10
			moveTurn(-10);
			break;
		case MENU_SELECT_4:
			// right 10
			moveTurn(10);
			break;
		case MENU_SELECT_5:
			// left 90
			moveTurn(-90);
			break;
		case MENU_SELECT_6:
			// right 90
			moveTurn(90);
			break;
		case MENU_SELECT_7:
			//move to waypoint
			state = MenuState.SELECT_DESTINATION_WAYPOINT;
			try {
				sc.requestFacilitiesList(FacilityListType.WAYPOINT, 
						ID.REQUEST_WAYPOINT_LIST);
			} catch (IOException e1) {}
			// do not call showMenu() now because the facility
			// list may not be fully filled
			showMenu();
			break;
		case MENU_SELECT_8:
			//delete
			deleteAll();
			break;
			
		}
	}

	private void deleteAll() {
		
		for (int i = 0; i < units.length; i++) {
			Unit un = units[i];
			if (un == null)
				continue;
			
			int cid = un.getContainerId();
			if (cid == -1) 
				continue;
			
			// send delete command to FS
			try {
				sc.aIRemoveObject(cid, ID.REQUEST_DELETE_AI);
			} catch (IOException e) {
			}
		}
		// set to initial state, don't wait for deletion ack
		// this is because can have all stale handles (failed insertions)
		changeState(MenuState.FORMATION_TYPE);
	}

	private void moveTurn(double angleDeg) {
		currentHeading += Math.toRadians(angleDeg);
		if (currentHeading > (2*Math.PI)) currentHeading -= (2*Math.PI);
		if (currentHeading < 0) currentHeading += (2*Math.PI);
		
		setHeading();
	}

	private void setHeading() {
		double[] data = new double[] { currentHeading };
		
		for (int i = 0; i < units.length; i++) {
			Unit un = units[i];
			if (un == null)
				continue;
			
			int cid = un.getContainerId();
			if (cid == -1) 
				continue;
			
			// send heading value
			try {
				sc.setDataOnSimObject(ID.DATA_DEF_DESIRED_HEADING, cid, data);
			} catch (IOException e) {}
		}
	}
	
	private void setThrottle(double percent) {
		for (int i = 0; i < units.length; i++) {
			Unit un = units[i];
			if (un == null)
				continue;
			
			int cid = un.getContainerId();
			if (cid == -1) 
				continue;
			
			// send throttle command
			int val = (int) ( 16384.0 * percent /100.0);
			try {
				sc.transmitClientEvent(cid, ID.EVENT_AI_THROTTLE, val,
						NotificationPriority.HIGHEST,
						SimConnectConstants.EVENT_FLAG_GROUPID_IS_PRIORITY);
			} catch (IOException e) {}
		}
	}


	private void moveTo(FacilityWaypoint fwp) {
		// memorize destination waypoint
		destWpt = fwp;
		
		// request position of first ship
		for (int i = 0; i < units.length; i++) {
			Unit un = units[i];
			if (un == null)
				continue;
			
			int cid = un.getContainerId();
			if (cid == -1) 
				continue;
			
			try {
				sc.requestDataOnSimObject(ID.REQUEST_AI_POSITION, 
						ID.DATA_DEF_POSITION, 
						cid, SimConnectPeriod.ONCE);
			} catch (IOException e) {}
			// only ask for the first ship (they all sail with
			// the same bearing)
			break;
		}
	}
	
	private void moveTo(RecvSimObjectData e, FacilityWaypoint fwp) {
		// calculate position based on static offset and current position
		double curLat = e.getDataFloat64();
		double curLon = e.getDataFloat64();
		
		currentHeading = GeoCalc.headingRadians(curLat, curLon, 
				Math.toRadians(fwp.getLatitude()), 
				Math.toRadians(fwp.getLongitude()));
		setHeading();
		
		// clean up
		destWpt = null;
	}

	
	/* **************************************************************
	 * 
	 * Insertion
	 * 
	 * **************************************************************
	 */
	
	private void processPositionMenuEntry(TextResult tr) {
		if (tr == TextResult.MENU_SELECT_1) {
			// user position
			insertAt(userTracker.getUserLat(), 
					userTracker.getUserLon(),
					userTracker.getUserHdg());
			changeState(MenuState.MOVE);
		} else if (tr == TextResult.MENU_SELECT_2) {
			// 1nm ahead
			insertAhead(1);
			changeState(MenuState.MOVE);
			
		} else if (tr == TextResult.MENU_SELECT_3) {
			// 5nm ahead
			insertAhead(5);
			changeState(MenuState.MOVE);
			
		} else if (tr == TextResult.MENU_SELECT_4) {
			// 10nm ahead
			insertAhead(10);
			changeState(MenuState.MOVE);
			
		} else if (tr == TextResult.MENU_SELECT_5) {
			changeState(MenuState.SELECT_WAYPOINT);
			try {
				sc.requestFacilitiesList(FacilityListType.WAYPOINT, 
						ID.REQUEST_WAYPOINT_LIST);
			} catch (IOException e1) {}
			// do not call showMenu() now because the facility
			// list may not be fully filled
			
		} else if (tr == TextResult.MENU_SELECT_6) {
			// back
			changeState(MenuState.FORMATION_TYPE);
			showMenu();
		}
	}
	
	private void insertAhead(double distanceNm) {
		double dist = ConvertUnits.nauticMilesToMeters(distanceNm);
		double userLat = userTracker.getUserLat();
		double userLon = userTracker.getUserLon();
		double userHdg = userTracker.getUserHdg();
		Point2D pos = new Point2D(dist, 0);
		pos.scale(1 /GeoCalc.RADIUS_EARTH_M);
		pos.rotate(userHdg);
		pos.translate(userLat, userLon);
		insertAt(pos.x, pos.y, userHdg);
	}
	
	/**
	 * Insert current formation 
	 * @param latitude radians
	 * @param longitude radians
	 * @param heading heading, in radians
	 */
	private void insertAt(double latitude, double longitude, double heading) {
		if (currentFormation == null)
			return;
		
		currentHeading = heading;
		Point2D	offset = new Point2D();
		
		// basic initposition data
		InitPosition ip = new InitPosition();
		ip.altitude = 0;
		ip.airspeed = 0;
		ip.pitch = 0;
		ip.bank = 0;
		ip.heading = Math.toDegrees(heading);
		ip.onGround = true;

		// allocate an array containing container IDs
        int sz = currentFormation.size();
        units = new Unit[sz];
        for (int i = 0; i < sz; i++) {
        	UnitPosition up = currentFormation.getUnitPosition(i);
        	units[i] = new Unit(up);
        }

        double scale = (2 * Math.PI / (GeoCalc.RADIUS_EARTH_M * 2 * Math.PI));
        for (int i = 0; i < sz; i++) {
        	UnitPosition up = currentFormation.getUnitPosition(i);
        	
            // convert coordinates from relative meters to absolute radians
			offset.set(up.getYOffset(), up.getXOffset());
			offset.rotate(heading);
			offset.scale(scale, scale / Math.cos(latitude));
			offset.translate(latitude, longitude);
			
			// simconnect insert
			ip.latitude = Math.toDegrees(offset.x);
			ip.longitude  = Math.toDegrees(offset.y);
			
			try {
				sc.aICreateSimulatedObject(up.getContainerTitle(), ip, 
						ID.LAST.ordinal() + i);
				// log packet for error tracking
				logInsertionPacket(up.getContainerTitle());
			} catch (IOException e) {}
        }
	}
	
	/* **************************************************************
	 * 
	 * Request Fleet position
	 * 
	 * **************************************************************
	 */
	
	private void requestFleetPosition() {
		// send a request
		
		// request position of first ship
		for (Unit un : units) {
			int cid = un.getContainerId();
			if (cid == -1) continue;
			
			try {
				sc.requestDataOnSimObject(ID.REQUEST_AI_POSITION_FOR_LOCATE, 
						ID.DATA_DEF_POSITION, 
						cid, SimConnectPeriod.ONCE);
			} catch (IOException e) {}
			// only ask for the first ship (they all sail with
			// the same bearing)
			break;
		}
	}
	
	private void gotFleetPosition(RecvSimObjectData re) {
		double fleetLat = re.getDataFloat64();
		double fleetLon = re.getDataFloat64();
//		double fleetHdg = re.getDataFloat64();
		
		double userLat = userTracker.getUserLat();
		double userLon = userTracker.getUserLon();
		double dist = GeoCalc.distanceRadians(userLat, userLon, 
				fleetLat, fleetLon);
		double hdg = GeoCalc.headingRadians(userLat, userLon, 
				fleetLat, fleetLon) - userTracker.getMagVar();
		
		// fix heading
		if (hdg < 0) 
			hdg += (2*Math.PI);
		if (hdg > (2*Math.PI))
			hdg -= (2*Math.PI);
		
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		String distStr = df.format(ConvertUnits.metersToNauticMiles(dist));
		df.setMinimumFractionDigits(0);
		df.setMaximumFractionDigits(0);
		String hdgStr = df.format(Math.toDegrees(hdg));
//		String fleetHdgStr = df.format(Math.toDegrees(fleetHdg));
		
		FormatType ft = conf.getEnum("base", "format", FormatType.DMS);
		Format latFormat = FormatLatLon.getLatitudeFormatter(ft);
		Format lonFormat = FormatLatLon.getLongitudeFormatter(ft);
		
		try {
			sc.text(TextType.PRINT_WHITE, 15.0f, ID.EVENT_TEXT_FLEET_POSITION,
					"\nFleet is at " + distStr + " nm, course " + hdgStr + 
					" degrees\nposition " +
					latFormat.format(fleetLat) + " " +
					lonFormat.format(fleetLon)
			);
		} catch (IOException e) {}
	}


}
